package org.jbiowhpersistence.datasets.gene.genebank.util;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genebank.controller.GeneBankJpaController;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;
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

    Map<Long, List<GenBankCDSAssignmentData>> geneBankWIDMap;

    /**
     * Creates the GeneAssignment object
     */
    public GenBankCDSAssignment() {
        geneBankWIDMap = new LinkedHashMap<Long, List<GenBankCDSAssignmentData>>();
    }

    /**
     * Get the GeneBank WIDs Map
     *
     * @return the GeneBank WIDs Map
     */
    public Map<Long, List<GenBankCDSAssignmentData>> getGenBankWIDMap() {
        return geneBankWIDMap;
    }

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

    /**
     * Parse a blast XML result and creates the GeneBank WIDs Map
     *
     * @param blastFile a blast XML result
     */
    public void parseBlastXML(File blastFile) {
        try {
            geneBankWIDMap.clear();
            Pattern p = Pattern.compile("\\D*(\\d+)\\D*(\\d+)\\D*");
            Matcher m;
            HashMap parm = new HashMap();
            GeneBankJpaController gCtrl = new GeneBankJpaController(JBioWHPersistence.getInstance().getWHEntityManager());

            JAXBContext jc = JAXBContext.newInstance("org.jbiowhcore.utility.fileformats.blast.xml");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            BlastOutput o = (BlastOutput) unmarshaller.unmarshal(blastFile);

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
                            if (g.getGeneBankCDSs() != null && !g.getGeneBankCDSs().isEmpty()) {
                                if (h.getHitHsps() != null) {
                                    for (Hsp hs : h.getHitHsps().getHsp()) {
                                        Long fromR = new Long(hs.getHspHitFrom());
                                        Long toR = new Long(hs.getHspHitTo());
                                        if (fromR > toR) {
                                            fromR = toR;
                                            toR = new Long(hs.getHspHitFrom());
                                        }
                                        VerbLogger.getInstance().log(this.getClass(), "\t\t" + fromR + "\t" + toR);
                                        for (GeneBankCDS c : g.getGeneBankCDSs()) {
                                            if (c.getLocation() != null) {
                                                try {
                                                    m = p.matcher(c.getLocation());
                                                    while (m.find()) {
                                                        Long from = new Long(m.group(1));
                                                        Long to = new Long(m.group(m.groupCount()));
                                                        if ((fromR >= from && fromR <= to)
                                                                || (toR >= from && toR <= to)) {
                                                            GenBankCDSAssignmentData assData = new GenBankCDSAssignmentData(c.getWid());
                                                            if (geneBankWIDMap.containsKey(g.getWid())) {
                                                                int index = geneBankWIDMap.get(g.getWid()).indexOf(assData);
                                                                if (index != -1) {
                                                                    assData = geneBankWIDMap.get(g.getWid()).get(index);
                                                                    assData.getBitScore().add(Float.valueOf(hs.getHspBitScore()));
                                                                    assData.getScore().add(Float.valueOf(hs.getHspScore()));
                                                                    assData.getEvalue().add(Double.valueOf(hs.getHspEvalue()));
                                                                } else {
                                                                    assData.getBitScore().add(Float.valueOf(hs.getHspBitScore()));
                                                                    assData.getScore().add(Float.valueOf(hs.getHspScore()));
                                                                    assData.getEvalue().add(Double.valueOf(hs.getHspEvalue()));
                                                                    geneBankWIDMap.get(g.getWid()).add(assData);
                                                                }
                                                            } else {
                                                                List<GenBankCDSAssignmentData> cds = new LinkedList<GenBankCDSAssignmentData>();
                                                                assData.getBitScore().add(Float.valueOf(hs.getHspBitScore()));
                                                                assData.getScore().add(Float.valueOf(hs.getHspScore()));
                                                                assData.getEvalue().add(Double.valueOf(hs.getHspEvalue()));
                                                                cds.add(assData);
                                                                geneBankWIDMap.put(g.getWid(), cds);
                                                            }
                                                            break;
                                                        }
                                                    }
                                                } catch (IllegalStateException ex) {
                                                    VerbLogger.getInstance().log(this.getClass(), "\t" + ex.getMessage() + ":\t" + c.getLocation());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (NoResultException ex) {
                        }
                    }
                }
            }

        } catch (JAXBException ex) {
            VerbLogger.getInstance().log(this.getClass(), ex.getMessage());
        }
    }
}
