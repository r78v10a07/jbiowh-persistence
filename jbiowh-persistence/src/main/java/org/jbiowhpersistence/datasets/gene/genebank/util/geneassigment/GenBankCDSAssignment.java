package org.jbiowhpersistence.datasets.gene.genebank.util.geneassigment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.GZIPInputStream;
import javax.persistence.NoResultException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.fileformats.blast.xml.BlastOutput;
import org.jbiowhcore.utility.fileformats.blast.xml.BlastOutputIterations;
import org.jbiowhcore.utility.fileformats.blast.xml.Hit;
import org.jbiowhcore.utility.fileformats.blast.xml.Hsp;
import org.jbiowhcore.utility.fileformats.blast.xml.Iteration;
import org.jbiowhcore.utility.utils.JBioWHMessage;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genebank.controller.GeneBankCDSJpaController;
import org.jbiowhpersistence.datasets.gene.genebank.controller.GeneBankJpaController;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities.ProtClust;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;

/**
 * This class is used to find the GeneBank WID with the CDS WID from a blast
 * like result.
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 17, 2013
 */
public class GenBankCDSAssignment {

    private Map<Long, List<GenBankCDSAssignmentData>> geneBankWIDMap;
    private Set<Long> missingGi;

    /**
     * Creates the GeneAssignment object
     */
    public GenBankCDSAssignment() {
        geneBankWIDMap = new HashMap<Long, List<GenBankCDSAssignmentData>>();
        missingGi = new TreeSet<Long>();
    }

    /**
     * Get the GeneBank WIDs Map
     *
     * @return the GeneBank WIDs Map
     */
    public Map<Long, List<GenBankCDSAssignmentData>> getGenBankWIDMap() {
        return geneBankWIDMap;
    }

    /**
     * Get the list with the missing Gi
     *
     * @return a list with the missing Gi
     */
    public Set<Long> getMissingGi() {
        return missingGi;
    }

    public List<GeneBank> getLocusByTaxId(int taxId) {
        List<GeneBank> genBanks = new ArrayList();

        for (Long genBank_WID : getGenBankWIDMap().keySet()) {
            GeneBank g = JBioWHPersistence.getInstance().createEntityManager().find(GeneBank.class, genBank_WID);
            if (g.getTaxId() == taxId) {
                genBanks.add(g);
            }
        }
        return genBanks;
    }

    public List<GeneBankCDS> getCDSByTaxId(int taxId) {
        List<GeneBankCDS> genBankCDSs = new ArrayList();

        for (Long genBank_WID : getGenBankWIDMap().keySet()) {
            GeneBank g = JBioWHPersistence.getInstance().createEntityManager().find(GeneBank.class, genBank_WID);
            if (g.getTaxId() == taxId) {
                for (GenBankCDSAssignmentData assData : getGenBankWIDMap().get(genBank_WID)) {
                    genBankCDSs.add(JBioWHPersistence.getInstance().createEntityManager().find(GeneBankCDS.class, assData.getGeneBankCDS_WID()));
                }
            }
        }
        return genBankCDSs;
    }

    public List<COGOrthologousGroup> getCOGByTaxId(int taxId) {
        List<COGOrthologousGroup> cogGroups = new ArrayList();

        for (Long genBank_WID : getGenBankWIDMap().keySet()) {
            GeneBank g = JBioWHPersistence.getInstance().createEntityManager().find(GeneBank.class, genBank_WID);
            if (g.getTaxId() == taxId) {
                for (GenBankCDSAssignmentData assData : getGenBankWIDMap().get(genBank_WID)) {
                    GeneBankCDS c = JBioWHPersistence.getInstance().createEntityManager().find(GeneBankCDS.class, assData.getGeneBankCDS_WID());
                    if (c.getCogOrthologousGroup() != null) {
                        for (COGOrthologousGroup o : c.getCogOrthologousGroup()) {
                            cogGroups.add(o);
                        }
                    }
                }
            }
        }
        return cogGroups;
    }

    public List<ProtClust> getProtClustByTaxId(int taxId) {
        List<ProtClust> protClust = new ArrayList();

        for (Long genBank_WID : getGenBankWIDMap().keySet()) {
            GeneBank g = JBioWHPersistence.getInstance().createEntityManager().find(GeneBank.class, genBank_WID);
            if (g.getTaxId() == taxId) {
                for (GenBankCDSAssignmentData assData : getGenBankWIDMap().get(genBank_WID)) {
                    GeneBankCDS c = JBioWHPersistence.getInstance().createEntityManager().find(GeneBankCDS.class, assData.getGeneBankCDS_WID());
                    if (c.getProtClust() != null) {
                        if (c.getGeneInfo() != null) {
                            for (GeneInfo gene : c.getGeneInfo()) {
                                if (gene.getProtClust() != null) {
                                    for (ProtClust p : gene.getProtClust()) {
                                        protClust.add(p);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return protClust;
    }

    /**
     * Get a map with the GeneInfo WID
     *
     * @return a map with the GeneInfo WID
     */
    public Map<Long, List<Long>> getGenBankWIDMapGeneInfo() {
        Map<Long, List<Long>> map = new LinkedHashMap<Long, List<Long>>();

        for (Long geneBank_WID : getGenBankWIDMap().keySet()) {
            List<Long> geneInfo_WIDs = new LinkedList<Long>();
            for (GenBankCDSAssignmentData assData : getGenBankWIDMap().get(geneBank_WID)) {
                GeneBankCDS c = JBioWHPersistence.getInstance().createEntityManager().find(GeneBankCDS.class, assData.getGeneBankCDS_WID());
                if (c.getGeneInfo() != null) {
                    for (GeneInfo g : c.getGeneInfo()) {
                        geneInfo_WIDs.add(g.getWid());
                    }
                }
            }
            map.put(geneBank_WID, geneInfo_WIDs);
        }

        return map;
    }

    private void findGenBankCDS(String source, GeneBank g, Integer fromR, Integer toR,
            Float bitScore, Float score, Double eValue) {
        HashMap parm = new HashMap();
        GeneBankCDSJpaController cCtrl = new GeneBankCDSJpaController(JBioWHPersistence.getInstance().getWHEntityManager());

        parm.put("geneBankWID", g.getWid());
        parm.put("pFrom", fromR);
        parm.put("pTo", toR);

        for (GeneBankCDS c : (List<GeneBankCDS>) cCtrl.useNamedQuery("GeneBankCDS.findByLocationFromGeneBankWID", parm)) {
            GenBankCDSAssignmentData assData = new GenBankCDSAssignmentData(c.getWid());
            if (getGenBankWIDMap().containsKey(g.getWid())) {
                int index = getGenBankWIDMap().get(g.getWid()).indexOf(assData);
                if (index != -1) {
                    assData = getGenBankWIDMap().get(g.getWid()).get(index);
                    assData.getBitScore().add(bitScore);
                    assData.getScore().add(score);
                    assData.getEvalue().add(eValue);
                    assData.getSource().add(source);
                } else {
                    assData.getBitScore().add(bitScore);
                    assData.getScore().add(score);
                    assData.getEvalue().add(eValue);
                    assData.getSource().add(source);
                    getGenBankWIDMap().get(g.getWid()).add(assData);
                }
            } else {
                List<GenBankCDSAssignmentData> cds = new LinkedList<GenBankCDSAssignmentData>();
                assData.getBitScore().add(bitScore);
                assData.getScore().add(score);
                assData.getEvalue().add(eValue);
                assData.getSource().add(source);
                cds.add(assData);
                getGenBankWIDMap().put(g.getWid(), cds);
            }
        }
    }

    /**
     * Parse a Taxoner TXT result and creates the GeneBank WIDs Map
     *
     * @param taxonerFile
     * @throws java.io.IOException
     */
    public void parseTaxoner(File taxonerFile) throws IOException {
        InputStream in;
        if (taxonerFile.isFile() && taxonerFile.getCanonicalPath().endsWith(".gz")) {
            in = new GZIPInputStream(new FileInputStream(taxonerFile));
        } else {
            in = new FileInputStream(taxonerFile);
        }
        parseTaxoner(in);
    }

    /**
     * Parse a Taxoner TXT result and creates the GeneBank WIDs Map
     *
     * @param in
     * @throws java.io.IOException
     */
    public void parseTaxoner(InputStream in) throws IOException {
        try {
            String line;
            HashMap parm = new HashMap();
            getGenBankWIDMap().clear();
            getMissingGi().clear();
            GeneBankJpaController gCtrl = new GeneBankJpaController(JBioWHPersistence.getInstance().getWHEntityManager());

            int i = 1;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("[ +\t]");
                if (fields.length != 6) {
                    throw new IOException("Can parse the line: " + line);
                }
                try {
                    parm.put("gi", Integer.valueOf(fields[2]));
                    GeneBank g = (GeneBank) gCtrl.useNamedQuerySingleResult("GeneBank.findByGi", parm);
                    JBioWHMessage.getInstance().setMessage("Reading " + i + " lines. Identified LocusName: " + g.getLocusName());
                    JBioWHMessage.getInstance().setValue(i);
                    VerbLogger.getInstance().log(this.getClass(), i + "\t" + g.getLocusName() + "\t" + fields[4] + "\t" + fields[5]);
                    findGenBankCDS(line, g, Integer.valueOf(fields[4]), Integer.valueOf(fields[5]), Float.NaN, Float.valueOf(fields[3]), Double.NaN);
                } catch (NoResultException ex) {
                    missingGi.add(Long.valueOf(fields[2]));
                }
                i++;
            }
        } catch (IOException ex) {
            VerbLogger.getInstance().log(this.getClass(), ex.getMessage());
            throw ex;
        }
    }

    /**
     * Parse a blast XML result and creates the GeneBank WIDs Map
     *
     * @param blastFile a blast XML result
     * @throws javax.xml.bind.JAXBException
     */
    public void parseBlastXML(File blastFile) throws JAXBException, IOException {
        InputStream in;
        if (blastFile.isFile() && blastFile.getCanonicalPath().endsWith(".gz")) {
            in = new GZIPInputStream(new FileInputStream(blastFile));
        } else {
            in = new FileInputStream(blastFile);
        }
        parseBlastXML(in);

    }

    /**
     * Parse a blast XML result and creates the GeneBank WIDs Map
     *
     * @param in
     * @throws javax.xml.bind.JAXBException
     */
    public void parseBlastXML(InputStream in) throws JAXBException {
        geneBankWIDMap.clear();
        getMissingGi().clear();
        HashMap parm = new HashMap();
        GeneBankJpaController gCtrl = new GeneBankJpaController(JBioWHPersistence.getInstance().getWHEntityManager());

        JAXBContext jc = JAXBContext.newInstance("org.jbiowhcore.utility.fileformats.blast.xml");

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        BlastOutput o = (BlastOutput) unmarshaller.unmarshal(in);

        BlastOutputIterations bi = o.getBlastOutputIterations();
        for (Iteration i : bi.getIteration()) {
            VerbLogger.getInstance().log(this.getClass(), "IterNum: " + i.getIterationIterNum());
            VerbLogger.getInstance().log(this.getClass(), "QueryID: " + i.getIterationQueryID());
            VerbLogger.getInstance().log(this.getClass(), "QueryDef: " + i.getIterationQueryDef());
            VerbLogger.getInstance().log(this.getClass(), "QueryLen: " + i.getIterationQueryLen());
            if (i.getIterationHits() != null) {
                for (Hit h : i.getIterationHits().getHit()) {
                    VerbLogger.getInstance().log(this.getClass(), "\t" + h.getHitAccession());
                    parm.put("locusName", h.getHitAccession());
                    try {
                        GeneBank g = (GeneBank) gCtrl.useNamedQuerySingleResult("GeneBank.findByLocusName", parm);
                        VerbLogger.getInstance().log(this.getClass(), g.getLocusName());
                        JBioWHMessage.getInstance().setMessage("Reading " + i.getIterationIterNum() + " iteration. Identified LocusName: " + g.getLocusName());
                        JBioWHMessage.getInstance().setValue(Integer.valueOf(i.getIterationIterNum()));
                        if (h.getHitHsps() != null) {
                            for (Hsp hs : h.getHitHsps().getHsp()) {
                                Integer fromR = new Integer(hs.getHspHitFrom());
                                Integer toR = new Integer(hs.getHspHitTo());
                                if (fromR > toR) {
                                    fromR = toR;
                                    toR = new Integer(hs.getHspHitFrom());
                                }
                                VerbLogger.getInstance().log(this.getClass(), "\t\t" + fromR + "\t" + toR);
                                findGenBankCDS(h.getHitAccession(),
                                        g, fromR, toR,
                                        Float.valueOf(hs.getHspBitScore()),
                                        Float.valueOf(hs.getHspScore()),
                                        Double.valueOf(hs.getHspEvalue()));
                            }
                        }
                    } catch (NoResultException ex) {
                    }
                }
            }
        }
    }
}
