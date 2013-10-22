package org.jbiowhpersistence.datasets.protein;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the Protein tables
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $
 * $LastChangedRevision: 396 $
 * @since Aug 5, 2011
 */
public class ProteinTables {

    public final String PROTEIN = "Protein";
    public static final String PROTEIN_HAS_DRUGBANK = "Protein_has_DrugBank";
    public static final String PROTEIN_HAS_DRUGBANKASENZYME = "Protein_has_DrugBankAsEnzyme";
    public static final String PROTEIN_HAS_DRUGBANKASTRANSPORTERS = "Protein_has_DrugBankAsTransporters";
    public static final String PROTEIN_HAS_DRUGBANKASCARRIERS = "Protein_has_DrugBankAsCarriers";
    public static final String PROTEIN_HAS_GENEINFO = "Protein_has_GeneInfo";
    public static final String PROTEIN_HAS_GENEPTT = "Protein_has_GenePTT";
    public static final String PROTEIN_HAS_ONTOLOGY = "Protein_has_Ontology";
    public static final String PROTEIN_HAS_PROTEINKEYWORD = "Protein_has_ProteinKeyword";
    public static final String PROTEIN_HAS_TAXONOMYHOST = "Protein_has_TaxonomyHost";
    public static final String PROTEIN_HAS_TAXONOMY = "Protein_has_Taxonomy";
    public static final String PROTEIN_HAS_KEGGENZYME = "Protein_has_KEGGEnzyme";
    public final String PROTEINACCESSIONNUMBER = "ProteinAccessionNumber";
    public final String PROTEINBIOCYC = "ProteinBioCyc";
    public final String PROTEINBIOCYCTEMP = "ProteinBioCycTemp";
    public final String PROTEINCOMMENT = "ProteinComment";
    public final String PROTEINCOMMENTCONFLICT = "ProteinCommentConflict";
    public final String PROTEINCOMMENTEVENT = "ProteinCommentEvent";
    public final String PROTEINCOMMENTINTERACT = "ProteinCommentInteract";
    public final String PROTEINCOMMENTISOFORM = "ProteinCommentIsoform";
    public final String PROTEINCOMMENTLINK = "ProteinCommentLink";
    public final String PROTEINCOMMENTSUBCELLULARLOCATION = "ProteinCommentSubCellularLocation";
    public final String PROTEINDBREFERENCE = "ProteinDBReference";
    public final String PROTEINDBREFERENCEPROPERTY = "ProteinDBReferenceProperty";
    public final String PROTEINDIP = "ProteinDIP";
    public final String PROTEINDIPTEMP = "ProteinDIPTemp";
    public final String PROTEINDRUGBANK = "ProteinDrugBank";
    public final String PROTEINDRUGBANKTEMP = "ProteinDrugBankTemp";
    public final String PROTEINEC = "ProteinEC";
    public final String PROTEINECTEMP = "ProteinECTemp";
    public final String PROTEINFEATURE = "ProteinFeature";
    public final String PROTEINFEATUREVARIATION = "ProteinFeatureVariation";
    public final String PROTEINGENE = "ProteinGene";
    public final String PROTEINGENETEMP = "ProteinGeneTemp";
    public final String PROTEINGENELOCATION = "ProteinGeneLocation";
    public final String PROTEINGENENAME = "ProteinGeneName";
    public final String PROTEINGO = "ProteinGo";
    public final String PROTEINGOTEMP = "ProteinGoTemp";
    public final String PROTEININTACT = "ProteinIntAct";
    public final String PROTEININTACTTEMP = "ProteinIntActTemp";
    public final String PROTEINISOFORMID = "ProteinIsoformId";
    public final String PROTEINISOFORMNAME = "ProteinIsoformName";
    public final String PROTEINKEGG = "ProteinKEGG";
    public final String PROTEINKEGGTEMP = "ProteinKEGGTemp";
    public final String PROTEINKEYWORD = "ProteinKeyword";
    public final String PROTEINLONGNAME = "ProteinLongName";
    public final String PROTEINMINT = "ProteinMINT";
    public final String PROTEINMINTTEMP = "ProteinMINTTemp";
    public final String PROTEINTAXID = "ProteinTaxId";
    public final String PROTEINPFAM = "ProteinPFAM";
    public final String PROTEINPFAMTEMP = "ProteinPFAMTemp";
    public final String PROTEINNAME = "ProteinName";
    public final String PROTEINOTHERLOCATION = "ProteinOtherLocation";
    public final String PROTEINPDB = "ProteinPDB";
    public final String PROTEINPDBTEMP = "ProteinPDBTemp";
    public final String PROTEINPMID = "ProteinPMID";
    public final String PROTEINPMIDTEMP = "ProteinPMIDTemp";
    public final String PROTEINREFSEQ = "ProteinRefSeq";
    public final String PROTEINREFSEQTEMP = "ProteinRefSeqTemp";
    public final String PROTEINWIDKEYWORDTEMP = "ProteinWIDKeywordTemp";
    private static ProteinTables singleton;
    private static List<String> tables;

    private ProteinTables() {
    }

    /**
     * Return a ProteinTables
     *
     * @return a ProteinTables
     */
    public static synchronized ProteinTables getInstance() {
        if (singleton == null) {
            singleton = new ProteinTables();
        }
        return singleton;
    }

    /**
     * Return the Map object with the tables names and TSV files names
     *
     * @return
     */
    public List<String> getTables() {
        if (tables == null) {
            tables = new ArrayList<>();
            tables.add(PROTEIN);
            tables.add(PROTEIN_HAS_DRUGBANK);
            tables.add(PROTEIN_HAS_DRUGBANKASENZYME);
            tables.add(PROTEIN_HAS_DRUGBANKASTRANSPORTERS);
            tables.add(PROTEIN_HAS_DRUGBANKASCARRIERS);
            tables.add(PROTEIN_HAS_GENEINFO);
            tables.add(PROTEIN_HAS_GENEPTT);
            tables.add(PROTEIN_HAS_ONTOLOGY);
            tables.add(PROTEIN_HAS_PROTEINKEYWORD);
            tables.add(PROTEIN_HAS_TAXONOMYHOST);
            tables.add(PROTEIN_HAS_TAXONOMY);
            tables.add(PROTEIN_HAS_KEGGENZYME);
            tables.add(PROTEINACCESSIONNUMBER);
            tables.add(PROTEINBIOCYC);
            tables.add(PROTEINBIOCYCTEMP);
            tables.add(PROTEINCOMMENT);
            tables.add(PROTEINCOMMENTCONFLICT);
            tables.add(PROTEINCOMMENTEVENT);
            tables.add(PROTEINCOMMENTINTERACT);
            tables.add(PROTEINCOMMENTISOFORM);
            tables.add(PROTEINCOMMENTLINK);
            tables.add(PROTEINCOMMENTSUBCELLULARLOCATION);
            tables.add(PROTEINDBREFERENCE);
            tables.add(PROTEINDBREFERENCEPROPERTY);
            tables.add(PROTEINDIP);
            tables.add(PROTEINDIPTEMP);
            tables.add(PROTEINDRUGBANK);
            tables.add(PROTEINDRUGBANKTEMP);
            tables.add(PROTEINEC);
            tables.add(PROTEINECTEMP);
            tables.add(PROTEINFEATURE);
            tables.add(PROTEINFEATUREVARIATION);
            tables.add(PROTEINGENE);
            tables.add(PROTEINGENETEMP);
            tables.add(PROTEINGENELOCATION);
            tables.add(PROTEINGENENAME);
            tables.add(PROTEINGO);
            tables.add(PROTEINGOTEMP);
            tables.add(PROTEININTACT);
            tables.add(PROTEININTACTTEMP);
            tables.add(PROTEINISOFORMID);
            tables.add(PROTEINISOFORMNAME);
            tables.add(PROTEINKEGG);
            tables.add(PROTEINKEGGTEMP);
            tables.add(PROTEINKEYWORD);
            tables.add(PROTEINLONGNAME);
            tables.add(PROTEINMINT);
            tables.add(PROTEINMINTTEMP);
            tables.add(PROTEINTAXID);
            tables.add(PROTEINPFAM);
            tables.add(PROTEINPFAMTEMP);
            tables.add(PROTEINNAME);
            tables.add(PROTEINOTHERLOCATION);
            tables.add(PROTEINPDB);
            tables.add(PROTEINPDBTEMP);
            tables.add(PROTEINPMID);
            tables.add(PROTEINPMIDTEMP);
            tables.add(PROTEINREFSEQ);
            tables.add(PROTEINREFSEQTEMP);
            tables.add(PROTEINWIDKEYWORDTEMP);
        }
        return tables;
    }
}
