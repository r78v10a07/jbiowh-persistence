package org.jbiowhpersistence.utils.entitymanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySet;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMember;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protein.search.SearchProtein;
import org.jbiowhpersistence.datasets.protein.util.ProteinToFasta;
import org.jbiowhpersistence.datasets.protgroup.pirsf.entities.Pirsf;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This Class is to extract from each Dataset Entity the selected field
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100
 * (Tue, 19 Mar 2013) $ $LastChangedRevision: 591 $
 *
 * @since Feb 15, 2012
 */
public class EntityParserFieldProxy {

    private final String TXT = "txt";
    private final String TXTDESCRIPTION = "Text File (*.txt)";
    private final String FASTA = "fasta";
    private final String FASTADESCRIPTION = "Fasta File (*.fasta)";
    private static EntityParserFieldProxy singleton;

    private EntityParserFieldProxy() {
    }

    /**
     * Return a EntityParserFieldProxy
     *
     * @return a EntityParserFieldProxy
     */
    public static synchronized EntityParserFieldProxy getInstance() {
        if (singleton == null) {
            singleton = new EntityParserFieldProxy();
        }
        return singleton;
    }

    /**
     * Get the identifier for each DataSet entity
     *
     * @param data the data object to extract its id
     * @return a String object
     */
    public String getId(Object data) {
        if (data instanceof String) {
            return (String) data;
        } else if (data instanceof Taxonomy) {
            return ((Taxonomy) data).getTaxonomySynonym();
        } else if (data instanceof Ontology) {
            return ((Ontology) data).getId();
        } else if (data instanceof GeneInfo) {
            return ((GeneInfo) data).getGeneID() + " " + ((GeneInfo) data).getSymbol();
        } else if (data instanceof GenePTT) {
            return ((GenePTT) data).getProteinGi().toString();
        } else if (data instanceof Protein) {
            return ((Protein) data).getProteinNameDirected();
        } else if (data instanceof DrugBank) {
            return ((DrugBank) data).getId();
        } else if (data instanceof KEGGCompound) {
            return ((KEGGCompound) data).getEntry();
        } else if (data instanceof KEGGEnzyme) {
            return ((KEGGEnzyme) data).getEntry();
        } else if (data instanceof KEGGGene) {
            return ((KEGGGene) data).getEntry();
        } else if (data instanceof KEGGGlycan) {
            return ((KEGGGlycan) data).getEntry();
        } else if (data instanceof KEGGReaction) {
            return ((KEGGReaction) data).getEntry();
        } else if (data instanceof KEGGPathway) {
            return ((KEGGPathway) data).getEntry();
        } else if (data instanceof UniRefEntry) {
            return ((UniRefEntry) data).getName();
        } else if (data instanceof UniRefMember) {
            return ((UniRefMember) data).getId();
        } else if (data instanceof OMIM) {
            return (new Long(((OMIM) data).getOmimId())).toString();
        } else if (data instanceof PfamAbioWH) {
            return ((PfamAbioWH) data).getPfamAacc();
        } else if (data instanceof MIFEntrySet) {
            return ((MIFEntrySet) data).getWid().toString();
        } else if (data instanceof MIFEntrySetEntry) {
            return ((MIFEntrySetEntry) data).getWid().toString();
        } else if (data instanceof MIFEntryInteraction) {
            return Integer.toString(((MIFEntryInteraction) data).getId());
        } else if (data instanceof GeneBank) {
            return Integer.toString(((GeneBank) data).getGi());
        } else if (data instanceof Pirsf) {
            return ((Pirsf) data).getpIRSFnumber();
        }
        return null;
    }

    /**
     * Get the fields to be printed in txt format for each Dataset
     *
     * @param data the data object to extract the fields
     * @return an array with the fields
     */
    public String[] getTxtData(Object data) {
        if (data instanceof Taxonomy) {
            return new String[]{new Long(((Taxonomy) data).getTaxId()).toString(), ((Taxonomy) data).getTaxonomySynonym()};
        } else if (data instanceof Ontology) {
            return new String[]{((Ontology) data).getId(), ((Ontology) data).getName()};
        } else if (data instanceof GeneInfo) {
            return new String[]{new Long(((GeneInfo) data).getGeneID()).toString(), ((GeneInfo) data).getSymbol(), ((GeneInfo) data).getLocusTag()};
        } else if (data instanceof GenePTT) {
            return new String[]{new Long(((GenePTT) data).getProteinGi()).toString(),
                ((GenePTT) data).getGeneSymbol(), ((GenePTT) data).getCog(),
                new Long(((GenePTT) data).getPFrom()).toString(),
                new Long(((GenePTT) data).getPTo()).toString(),
                ((GenePTT) data).getPTTFile()};
        } else if (data instanceof Protein) {
            return new String[]{((Protein) data).getProteinNameDirected(), ((Protein) data).getProteinFirstAccessionNumber()};
        } else if (data instanceof DrugBank) {
            return new String[]{((DrugBank) data).getId(), ((DrugBank) data).getName()};
        } else if (data instanceof KEGGCompound) {
            return new String[]{((KEGGCompound) data).getEntry(), ((KEGGCompound) data).getFormula()};
        } else if (data instanceof KEGGEnzyme) {
            return new String[]{((KEGGEnzyme) data).getEntry(), ((KEGGEnzyme) data).getkEGGEnzymeNameDirectly()};
        } else if (data instanceof KEGGGene) {
            return new String[]{((KEGGGene) data).getEntry(), ((KEGGGene) data).getkEGGGeneNameDirectly()};
        } else if (data instanceof KEGGGlycan) {
            return new String[]{((KEGGGlycan) data).getEntry()};
        } else if (data instanceof KEGGReaction) {
            return new String[]{((KEGGReaction) data).getEntry(), ((KEGGReaction) data).getDefinition()};
        } else if (data instanceof KEGGPathway) {
            return new String[]{((KEGGPathway) data).getEntry(), ((KEGGPathway) data).getTitle()};
        } else if (data instanceof UniRefEntry) {
            return new String[]{((UniRefEntry) data).getId(), ((UniRefEntry) data).getName()};
        } else if (data instanceof OMIM) {
            return new String[]{(new Long(((OMIM) data).getOmimId())).toString()};
        } else if (data instanceof PfamAbioWH) {
            return new String[]{((PfamAbioWH) data).getPfamAacc(), ((PfamAbioWH) data).getPfamAid()};
        } else if (data instanceof GeneBank) {
            return new String[]{new Long(((GeneBank) data).getGi()).toString(), ((GeneBank) data).getLocusName(), ((GeneBank) data).getLocation()};
        } else if (data instanceof Pirsf) {
            return new String[]{((Pirsf) data).getpIRSFnumber(), ((Pirsf) data).getName(), ((Pirsf) data).getCurationStatus(), ((Pirsf) data).getParent()};
        }
        return null;
    }

    /**
     * Get the available file extension to each kind of Dataset
     *
     * @param data the data object to extract the fields
     * @return an list with the available file extension
     */
    public List<FileNameExtensionFilter> getFileExtensions(Object data) {
        List<FileNameExtensionFilter> extensions = new ArrayList();
        Object toTest;
        if (data instanceof Collection) {
            toTest = ((Collection) data).iterator().next();
        } else {
            toTest = data;
        }
        if (toTest instanceof Protein
                || toTest instanceof SearchProtein) {
            extensions.add(new FileNameExtensionFilter(FASTADESCRIPTION, FASTA));
            extensions.add(new FileNameExtensionFilter(TXTDESCRIPTION, TXT));
        } else {
            extensions.add(new FileNameExtensionFilter(TXTDESCRIPTION, TXT));
        }
        return extensions;
    }

    /**
     * Get a list with all fields to be printed from a Dataset collection
     *
     * @param data the Dataset collection
     * @return a list with all fields to be printed from a Dataset collection
     */
    public List getTxtDataFromCollection(Collection data) {
        ArrayList result = new ArrayList();

        for (Object d : data) {
            result.add(getTxtData(d));
        }

        return result;
    }

    /**
     * Get the fields to print for a collection of DataSet and the file name
     *
     * @param data the DataSet collection
     * @param name the file name to print
     * @return a list with all fields to be printed from a Dataset collection
     */
    public List getListToPrint(Collection data, String name) {
        if (name.toLowerCase().endsWith(TXT)) {
            return getTxtDataFromCollection((Collection) data);
        } else if (name.toLowerCase().endsWith(FASTA)) {
            ProteinToFasta toFasta = new ProteinToFasta(null);
            return toFasta.getFastaFromCollection((Collection) data);
        }

        return new ArrayList();
    }

    /**
     * Get the fields to print for a collection of DataSet and the file name
     *
     * @param data the DataSet collection
     * @param name the file name to print
     * @return a list with all fields to be printed from a Dataset collection
     */
    public List getListToPrint(Object data, String name) {
        List result = new ArrayList();
        if (data instanceof Collection) {
            if (name.toLowerCase().endsWith(TXT)) {
                return getTxtDataFromCollection((Collection) data);
            } else if (name.toLowerCase().endsWith(FASTA)) {
                ProteinToFasta toFasta = new ProteinToFasta(null);
                return toFasta.getFastaFromCollection((Collection) data);
            }
        } else {
            if (name.toLowerCase().endsWith(TXT)) {
                result.add(getTxtData(data));
            } else if (name.toLowerCase().endsWith(FASTA)) {
                ProteinToFasta toFasta = new ProteinToFasta(null);
                String fasta = toFasta.getFasta(data);
                if (fasta != null && !fasta.isEmpty()) {
                    result.add(toFasta.getFasta(data));
                }
            }
        }

        return result;
    }

    /**
     * True if the field belongs to some DataSet entity
     *
     * @param data the data object to extract its id
     * @param field the filed to be looking for
     * @return true if the field belongs to some DataSet entity
     */
    public boolean isId(Object data, String field) {
        if (data instanceof Taxonomy) {
            if (((Taxonomy) data).getTaxonomySynonym().equals(field)) {
                return true;
            }
        } else if (data instanceof Ontology) {
            if (((Ontology) data).getId().equals(field)) {
                return true;
            }
        } else if (data instanceof GeneInfo) {
            if ((((GeneInfo) data).getGeneID() + " " + ((GeneInfo) data).getSymbol()).equals(field)) {
                return true;
            }
        } else if (data instanceof GenePTT) {
            if (((GenePTT) data).getProteinGi().toString().equals(field)) {
                return true;
            }
        } else if (data instanceof Protein) {
            if (((Protein) data).getProteinNameDirected().equals(field)) {
                return true;
            }
        } else if (data instanceof DrugBank) {
            if (((DrugBank) data).getId().equals(field)) {
                return true;
            }
        } else if (data instanceof KEGGCompound) {
            if (((KEGGCompound) data).getEntry().equals(field)) {
                return true;
            }
        } else if (data instanceof KEGGEnzyme) {
            if (((KEGGEnzyme) data).getEntry().equals(field)) {
                return true;
            }
        } else if (data instanceof KEGGGene) {
            if (((KEGGGene) data).getEntry().equals(field)) {
                return true;
            }
        } else if (data instanceof KEGGGlycan) {
            if (((KEGGGlycan) data).getEntry().equals(field)) {
                return true;
            }
        } else if (data instanceof KEGGReaction) {
            if (((KEGGReaction) data).getEntry().equals(field)) {
                return true;
            }
        } else if (data instanceof KEGGPathway) {
            if (((KEGGPathway) data).getEntry().equals(field)) {
                return true;
            }
        } else if (data instanceof UniRefEntry) {
            if (((UniRefEntry) data).getName().equals(field)) {
                return true;
            }
        } else if (data instanceof OMIM) {
            if (((OMIM) data).getOmimId() == new Long(field)) {
                return true;
            }
        } else if (data instanceof PfamAbioWH) {
            if (((PfamAbioWH) data).getPfamAacc().equals(field)) {
                return true;
            }
        } else if (data instanceof GeneBank) {
            if (((GeneBank) data).getGi() == new Integer(field)) {
                return true;
            }
        } else if (data instanceof Pirsf) {
            if (((Pirsf) data).getpIRSFnumber().equals(field)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the WID for each DataSet entity
     *
     * @param data the data object to extract its WID
     * @return a Long object with the WID value
     */
    public Long getWID(Object data) {
        if (data instanceof Taxonomy) {
            return ((Taxonomy) data).getWid();
        } else if (data instanceof Ontology) {
            return ((Ontology) data).getWid();
        } else if (data instanceof GeneInfo) {
            return ((GeneInfo) data).getWid();
        } else if (data instanceof GenePTT) {
            return ((GenePTT) data).getProteinGi();
        } else if (data instanceof Protein) {
            return ((Protein) data).getWid();
        } else if (data instanceof DrugBank) {
            return ((DrugBank) data).getWid();
        } else if (data instanceof KEGGCompound) {
            return ((KEGGCompound) data).getWid();
        } else if (data instanceof KEGGEnzyme) {
            return ((KEGGEnzyme) data).getWid();
        } else if (data instanceof KEGGGene) {
            return ((KEGGGene) data).getWid();
        } else if (data instanceof KEGGGlycan) {
            return ((KEGGGlycan) data).getWid();
        } else if (data instanceof KEGGReaction) {
            return ((KEGGReaction) data).getWid();
        } else if (data instanceof KEGGPathway) {
            return ((KEGGPathway) data).getWid();
        } else if (data instanceof UniRefEntry) {
            return ((UniRefEntry) data).getWid();
        } else if (data instanceof OMIM) {
            return ((OMIM) data).getWid();
        } else if (data instanceof PfamAbioWH) {
            return ((PfamAbioWH) data).getWid();
        } else if (data instanceof GeneBank) {
            return ((GeneBank) data).getWid();
        } else if (data instanceof Pirsf) {
            return ((Pirsf) data).getWid();
        }
        return null;
    }
}
