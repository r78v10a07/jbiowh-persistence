package org.jbiowhpersistence.datasets.gene.gene;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the Gene Table list
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 5, 2011
 */
public class GeneTables {
    /*
     * List of tables names for the Taxonomy module
     */

    public static final String GENE2ACCESSION = "Gene2Accession";
    public static final String GENE2ACCESSION_HAS_PROTEIN = "Gene2Accession_has_Protein";
    public final String GENE2ACCESSIONTEMP = "gene2accessiontemp";
    public final String GENE2ENSEMBL = "Gene2Ensembl";
    public final String GENE2ENSEMBLTEMP = "gene2ensembltemp";
    public final String GENE2GO = "Gene2GO";
    public final String GENE2GOTEMP = "gene2gotemp";
    public final String GENE2PMID = "Gene2PMID";
    public final String GENE2PUBMEDTEMP = "gene2pubmedtemp";
    public final String GENE2STS = "Gene2STS";
    public final String GENE2STSTEMP = "gene2ststemp";
    public final String GENE2UNIGENE = "Gene2UniGene";
    public final String GENE2UNIGENETEMP = "gene2unigenetemp";
    public final String GENE_GROUP = "gene_group";
    public final String GENE_HISTORY = "gene_history";
    public final String GENE_INFO = "gene_info";
    public final String GENE_REFSEQ_UNIPROTKB_COLLAB = "gene_refseq_uniprotkb_collab";
    public final String GENEGROUP = "GeneGroup";
    public final String GENEHISTORY = "GeneHistory";
    public final String GENEINFO = "GeneInfo";
    public static final String GENEINFO_HAS_ONTOLOGY = "GeneInfo_has_Ontology";
    public static final String GENEINFO_HAS_KEGGGENE = "GeneInfo_has_KEGGGene";
    public static final String GENEINFO_HAS_OMIM = "GeneInfo_has_OMIM";
    public static final String GENEINFO_HAS_GENEPTT = "GeneInfo_has_GenePTT";
    public static final String GENEINFO_HAS_GENERNT = "GeneInfo_has_GeneRNT";
    public final String GENEINFODBXREFS = "GeneInfoDBXrefs";
    public final String GENEINFOSYNONYMS = "GeneInfoSynonyms";
    public final String GENEINFOWIDDBXREFSTEMP = "GeneInfoWIDDBXrefsTemp";
    public final String GENEINFOWIDSYNONYMSTEMP = "GeneInfoWIDSynonymsTemp";
    public final String GENEREFSEQUNIPROT = "GeneRefseqUniProt";
    public final String MIM2GENE = "mim2gene";
    public final String GENE2ACCESSIONFILE = "gene2accession";
    public final String GENE2ENSEMBLFILE = "gene2ensembl";
    public final String GENE2GOFILE = "gene2go";
    public final String GENE2PMIDFILE = "gene2pubmed";
    public final String GENE2STSFILE = "gene2sts";
    public final String GENE2UNIGENEFILE = "gene2unigene";
    private static GeneTables singleton;
    private static List<String> tables;

    private GeneTables() {
    }

    /**
     * Return a GeneTables
     *
     * @return a GeneTables
     */
    public static synchronized GeneTables getInstance() {
        if (singleton == null) {
            singleton = new GeneTables();
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
            tables.add(GENE2ACCESSION);
            tables.add(GENE2ACCESSION_HAS_PROTEIN);
            tables.add(GENE2ACCESSIONTEMP);
            tables.add(GENE2ENSEMBL);
            tables.add(GENE2ENSEMBLTEMP);
            tables.add(GENE2GO);
            tables.add(GENE2GOTEMP);
            tables.add(GENE2PMID);
            tables.add(GENE2PUBMEDTEMP);
            tables.add(GENE2STS);
            tables.add(GENE2STSTEMP);
            tables.add(GENE2UNIGENE);
            tables.add(GENE2UNIGENETEMP);
            tables.add(GENE_GROUP);
            tables.add(GENE_INFO);
            tables.add(GENE_REFSEQ_UNIPROTKB_COLLAB);
            tables.add(GENEGROUP);
            tables.add(GENEHISTORY);
            tables.add(GENEINFO);
            tables.add(GENEINFO_HAS_ONTOLOGY);
            tables.add(GENEINFO_HAS_KEGGGENE);
            tables.add(GENEINFO_HAS_OMIM);
            tables.add(GENEINFO_HAS_GENEPTT);
            tables.add(GENEINFO_HAS_GENERNT);
            tables.add(GENEINFODBXREFS);
            tables.add(GENEINFOSYNONYMS);
            tables.add(GENEINFOWIDDBXREFSTEMP);
            tables.add(GENEINFOWIDSYNONYMSTEMP);
            tables.add(GENEREFSEQUNIPROT);
            tables.add(MIM2GENE);
        }
        
        return tables;
    }
}
