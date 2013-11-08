package org.jbiowhpersistence.utils.msms.modules;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.fileformats.pride.xml.ExperimentCollection;
import org.jbiowhcore.utility.fileformats.pride.xml.GelFreeIdentificationType;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.controller.ProteinJpaController;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.msms.MSData;
import org.jbiowhpersistence.utils.msms.MSFactory;

/**
 * This class is the XML pride parser
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Sep 5, 2013
 */
public class MSPrideParser extends MSData implements MSFactory {

    @Override
    public void parseXML(File xmlFile, EntityManagerFactory factory) throws IllegalAccessException, JAXBException {
        try {
            HashMap<String, List<Protein>> map = new HashMap();
            HashMap parm = new HashMap();
            ProteinJpaController pCont = new ProteinJpaController(factory);
            JAXBContext jc = JAXBContext.newInstance("org.jbiowhcore.utility.fileformats.pride.xml");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            ExperimentCollection expColl = (ExperimentCollection) unmarshaller.unmarshal(xmlFile);

            if (expColl != null && expColl.getExperiment() != null && expColl.getExperiment().getGelFreeIdentification() != null) {
                for (GelFreeIdentificationType g : expColl.getExperiment().getGelFreeIdentification()) {
                    map.clear();
                    parm.clear();
                    List<Protein> proteins = null;

                    if (g.getDatabase().toUpperCase().equals("IPI")) {
                        if (g.getAccession().contains(".")) {
                            parm.put("id", g.getAccession().substring(0, g.getAccession().indexOf(".")));
                        } else {
                            parm.put("id", g.getAccession());
                        }
                        proteins = pCont.useNamedQuery("Protein.findIPIId", parm);
                    } else if (g.getDatabase().toUpperCase().equals("UNIPROT")) {
                        parm.put("accessionNumber", g.getAccession());
                        proteins = pCont.useNamedQuery("Protein.findProteinByAccessionNumber", parm);
                    } else if (g.getDatabase().toUpperCase().equals("SWISS-PROT")) {
                        parm.put("accessionNumber", g.getAccession());
                        proteins = pCont.useNamedQuery("Protein.findProteinByAccessionNumber", parm);
                    } else if (g.getDatabase().toUpperCase().equals("NRDB")) {
                        proteins = new ArrayList();
                        parm.put("proteinGi", Long.parseLong(g.getAccession()));
                        List<GeneInfo> genes = pCont.useNamedQuery("GeneInfo.findByProteinGi", parm);
                        for (GeneInfo ge : genes) {
                            if (ge.getProtein() != null && !ge.getProtein().isEmpty()) {
                                proteins.addAll(ge.getProtein());
                            }
                        }
                    } else if (g.getDatabase().toUpperCase().equals("REFSEQ")) {
                        proteins = new ArrayList();
                        if (g.getAccession().contains(".")) {
                            parm.put("proteinAccession", g.getAccession().substring(0, g.getAccession().indexOf(".")));
                        } else {
                            parm.put("proteinAccession", g.getAccession());
                        }
                        List<GeneInfo> genes = pCont.useNamedQuery("GeneInfo.findByProteinAccession", parm);
                        for (GeneInfo ge : genes) {
                            if (ge.getProtein() != null && !ge.getProtein().isEmpty()) {
                                proteins.addAll(ge.getProtein());
                            }
                        }
                    } else {
                        throw new IllegalAccessException("The database: " + g.getDatabase() + " is not included in the parser");
                    }

                    VerbLogger.getInstance().log(this.getClass(), "MS XML Parser. Adding Id: " + g.getAccession());
                    if (proteins != null && !proteins.isEmpty()) {
                        map.put(g.getAccession(), proteins);
                        getProteinList().add((Map.Entry<String, List<Protein>>) map.entrySet().iterator().next());
                    } else {
                        getNotFound().add(g.getAccession());
                    }
                }
            }
        } catch (JAXBException ex) {
            try {
                VerbLogger.getInstance().log(this.getClass(), "Error parsing XML file: " + xmlFile.getCanonicalPath());
                ex.printStackTrace(VerbLogger.getInstance().getOutput());
                throw new JAXBException("This XML is not a Pride type\n" + ex.getMessage());
            } catch (IOException ex1) {
                VerbLogger.getInstance().log(this.getClass(), "Error parsing XML file: " + xmlFile);
            }
        }
    }
}
