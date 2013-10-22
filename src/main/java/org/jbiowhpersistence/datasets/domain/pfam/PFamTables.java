package org.jbiowhpersistence.datasets.domain.pfam;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the Pfam Table list
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Oct 24, 2012
 */
public class PFamTables {

    public final String PFAMABIOWH = "PfamAbioWH";
    public final String PFAMA_ORIG = "pfamA";
    public final String PFAMADATABASELINKS = "PfamADatabaseLinks";
    public final String PFAMAINTERACTIONS = "PfamAInteractions";
    public final String PFAMANCBIREG = "PfamANCBIReg";
    public final String PFAMAPDBREG = "PfamAPDBReg";
    public static final String PFAMAREGFULLINSIGNIFICANT = "PfamARegFullInsignificant";
    public static final String PFAMAREGFULLSIGNIFICANT = "PfamARegFullSignificant";
    public final String PFAMAREGFULLSIGNIFICANTTEMP = "PfamARegFullSignificantTemp";
    public final String PFAMAREGSEED = "PfamARegSeed";
    public static final String PFAMA_HAS_ONTOLOGY = "PfamA_has_Ontology";
    public final String GENE_ONTOLOGY = "gene_ontology";
    public static final String PFAMA_HAS_PFAMARCHITECTURE = "PfamA_has_PfamArchitecture";
    public static final String PFAMA_HAS_PFAMLITERATUREREFERENCES = "PfamA_has_PfamLiteratureReferences";
    public static final String PFAMA_HAS_TAXONOMY = "PfamA_has_Taxonomy";
    public final String PFAMA_NCBI = "pfamA_ncbi";
    public final String PFAMARCHITECTURE = "PfamArchitecture";
    public final String PFAMBBIOWH = "PfamBbioWH";
    public final String PFAMBREG = "PfamBReg";
    public final String PFAMCLANDATABASELINKS = "PfamClanDatabaseLinks";
    public final String PFAMCLANS = "PfamClans";
    public static final String PFAMCLANS_HAS_PFAMA = "PfamClans_has_PfamA";
    public static final String PFAMCLANS_HAS_PFAMARCHITECTURE = "PfamClans_has_PfamArchitecture";
    public static final String PFAMCLANS_HAS_PFAMLITERATUREREFERENCES = "PfamClans_has_PfamLiteratureReferences";
    public final String PFAMCOMPLETEPROTEOMES = "PfamCompleteProteomes";
    public final String PFAMCOMPLETEPROTEOMES_HAS_PFAMSEQ = "PfamCompleteProteomes_has_PfamSeq";
    public final String PFAMCONTEXTREGIONS = "PfamContextRegions";
    public final String PFAMINTERPRO = "PfamInterpro";
    public final String PFAMLITERATUREREFERENCES = "PfamLiteratureReferences";
    public final String PFAMMARKUPKEY = "PfamMarkupKey";
    public final String PFAMMARKUP_HAS_PROTEIN = "PfamMarkup_has_Protein";
    public final String PFAMNESTEDLOCATIONS = "PfamNestedLocations";
    public final String PFAMOTHERREG = "PfamOtherReg";
    public final String PFAMPDB = "PfamPDB";
    public final String PFAMPDBRESIDUEDATA = "PfamPDBResidueData";
    public final String PFAMPROTEOMEREGIONS = "PfamProteomeRegions";
    public final String PFAMSEQDISULPHIDE = "PfamSeqDisulphide";
    public static final String PFAMSEQ_HAS_PROTEIN = "PfamSeq_has_Protein";
    public final String PFAMSEQ_HAS_UNIPROTID = "PfamSeq_has_UniProtId";
    
    public final String MySQLSCRIPT = "sqlscripts/mysql/pfam/pfam.sql";
    
    private static PFamTables singleton;
    private static List<String> tables;

    private PFamTables() {
    }

    /**
     * Return a PFamTables instance
     *
     * @return a PFamTables instance
     */
    public static synchronized PFamTables getInstance() {
        if (singleton == null) {
            singleton = new PFamTables();
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
            tables.add(PFAMABIOWH);
            tables.add(PFAMA_ORIG);
            tables.add(PFAMADATABASELINKS);
            tables.add(PFAMAINTERACTIONS);
            tables.add(PFAMANCBIREG);
            tables.add(PFAMAPDBREG);
            tables.add(PFAMAREGFULLINSIGNIFICANT);
            tables.add(PFAMAREGFULLSIGNIFICANT);
            tables.add(PFAMAREGFULLSIGNIFICANTTEMP);
            tables.add(PFAMAREGSEED);
            tables.add(PFAMA_HAS_ONTOLOGY);
            tables.add(GENE_ONTOLOGY);
            tables.add(PFAMA_HAS_PFAMARCHITECTURE);
            tables.add(PFAMA_HAS_PFAMLITERATUREREFERENCES);
            tables.add(PFAMA_HAS_TAXONOMY);
            tables.add(PFAMA_NCBI);
            tables.add(PFAMARCHITECTURE);
            tables.add(PFAMBBIOWH);
            tables.add(PFAMBREG);
            tables.add(PFAMCLANDATABASELINKS);
            tables.add(PFAMCLANS);
            tables.add(PFAMCLANS_HAS_PFAMA);
            tables.add(PFAMCLANS_HAS_PFAMARCHITECTURE);
            tables.add(PFAMCLANS_HAS_PFAMLITERATUREREFERENCES);
            tables.add(PFAMCOMPLETEPROTEOMES);
            tables.add(PFAMCOMPLETEPROTEOMES_HAS_PFAMSEQ);
            tables.add(PFAMCONTEXTREGIONS);
            tables.add(PFAMINTERPRO);
            tables.add(PFAMLITERATUREREFERENCES);
            tables.add(PFAMMARKUPKEY);
            tables.add(PFAMMARKUP_HAS_PROTEIN);
            tables.add(PFAMNESTEDLOCATIONS);
            tables.add(PFAMOTHERREG);
            tables.add(PFAMPDB);
            tables.add(PFAMPDBRESIDUEDATA);
            tables.add(PFAMPROTEOMEREGIONS);
            tables.add(PFAMSEQDISULPHIDE);
            tables.add(PFAMSEQ_HAS_PROTEIN);
            tables.add(PFAMSEQ_HAS_UNIPROTID);
        }
        return tables;
    }
}
