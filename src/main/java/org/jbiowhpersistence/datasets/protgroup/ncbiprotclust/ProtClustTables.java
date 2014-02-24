package org.jbiowhpersistence.datasets.protgroup.ncbiprotclust;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the NCBI ProtClustTables tables
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 13, 2014
 */
public class ProtClustTables {

    public final String PROTCLUST = "ProtClust";
    public static final String PROTCLUSTPROTEINS = "ProtClustProteins";
    public final String PROTCLUSTXREF = "ProtClustXRef";
    public final String PROTCLUSTPMID = "ProtClustPMID";
    public final String PROTCLUSTEC = "ProtClustEC";
    public final static String PROTCLUST_HAS_GENEINFO = "ProtClust_has_GeneInfo";
    public final static String PROTCLUST_HAS_PROTEIN = "ProtClust_has_Protein";
    public final static String PROTCLUST_HAS_TAXONOMY = "ProtClust_has_Taxonomy";
    public final static String PROTCLUST_HAS_COGORTHOLOGOUSGROUP = "ProtClust_has_COGOrthologousGroup";

    private static List<String> tables;
    private static ProtClustTables singleton;

    private ProtClustTables() {
    }

    /**
     * Return a ProtClustTables instance
     *
     * @return a ProtClustTables instance
     */
    public static synchronized ProtClustTables getInstance() {
        if (singleton == null) {
            singleton = new ProtClustTables();
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
            tables = new ArrayList();
            tables.add(PROTCLUST);
            tables.add(PROTCLUSTPROTEINS);
            tables.add(PROTCLUSTXREF);
            tables.add(PROTCLUSTPMID);
            tables.add(PROTCLUSTEC);
            tables.add(PROTCLUST_HAS_GENEINFO);
            tables.add(PROTCLUST_HAS_PROTEIN);
            tables.add(PROTCLUST_HAS_TAXONOMY);
            tables.add(PROTCLUST_HAS_COGORTHOLOGOUSGROUP);
        }
        return tables;
    }
}
