package org.jbiowhpersistence.utils.msms.modules;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.fileformats.bioml.xml.Bioml;
import org.jbiowhcore.utility.fileformats.bioml.xml.Group;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.controller.ProteinJpaController;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.msms.MSData;
import org.jbiowhpersistence.utils.msms.MSFactory;

/**
 * This class is the the XML Bioml parser
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Sep 5, 2013
 */
public class MSBioMLParser extends MSData implements MSFactory {

    @Override
    public void parseXML(File xmlFile, EntityManagerFactory factory) throws IllegalAccessException, JAXBException {
        try {
            HashMap<String, List<Protein>> map = new HashMap<>();
            HashMap parm = new HashMap();
            ProteinJpaController pCont = new ProteinJpaController(factory);
            JAXBContext jc = JAXBContext.newInstance("org.jbiowhcore.utility.fileformats.bioml.xml");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            Bioml bioml = (Bioml) unmarshaller.unmarshal(xmlFile);

            if (bioml != null && bioml.getGroup() != null && !bioml.getGroup().isEmpty()) {
                for (Group g : bioml.getGroup()) {
                    if (g.getType().equals("model")) {
                        LinkedHashSet<Protein> proteinSet = new  LinkedHashSet();
                        map.clear();
                        parm.clear();
                        parm.put("id", g.getLabel());
                        List<Protein> proteins = pCont.useNamedQuery("Protein.findEnsemblId", parm);
                        if (proteins.isEmpty()) {
                            parm.clear();
                            parm.put("ensemblProteinIdentifier", g.getLabel());
                            List<GeneInfo> genes = pCont.useNamedQuery("GeneInfo.findByEnsemblProteinIdentifier", parm);
                            for (GeneInfo ge : genes) {
                                if (ge.getProtein() != null && !ge.getProtein().isEmpty()) {
                                    proteinSet.addAll(ge.getProtein());
                                }
                            }
                        }else{
                            proteinSet.addAll(proteins);
                        }
                        for (org.jbiowhcore.utility.fileformats.bioml.xml.Protein p : g.getProtein()) {
                            parm.clear();
                            parm.put("id", p.getLabel());
                            proteins = pCont.useNamedQuery("Protein.findEnsemblId", parm);
                            if (proteins.isEmpty()) {
                                parm.clear();
                                parm.put("ensemblProteinIdentifier", p.getLabel());
                                List<GeneInfo> genes = pCont.useNamedQuery("GeneInfo.findByEnsemblProteinIdentifier", parm);
                                for (GeneInfo ge : genes) {
                                    if (ge.getProtein() != null && !ge.getProtein().isEmpty()) {
                                        proteinSet.addAll(ge.getProtein());
                                    }
                                }
                            } else {
                                proteinSet.addAll(proteins);
                            }
                        }
                        VerbLogger.getInstance().log(this.getClass(), "MS XML Parser. Adding Id: " + g.getLabel());
                        if (!proteinSet.isEmpty()) {                            
                            map.put(g.getLabel(), new ArrayList<>(proteinSet));
                            getProteinList().add((Map.Entry<String, List<Protein>>) map.entrySet().iterator().next());
                        } else {
                            getNotFound().add(g.getLabel());
                        }
                    }
                }
            }

        } catch (JAXBException ex) {
            try {
                VerbLogger.getInstance().log(this.getClass(), "Error parsing XML file: " + xmlFile.getCanonicalPath());
                ex.printStackTrace(VerbLogger.getInstance().getOutput());
                throw new JAXBException("This XML is not a BioML type\n" + ex.getMessage());
            } catch (IOException ex1) {
                VerbLogger.getInstance().log(this.getClass(), "Error parsing XML file: " + xmlFile);
            }
        }
    }
}
