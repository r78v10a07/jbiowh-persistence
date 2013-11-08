package org.jbiowhpersistence.datasets.gene.genome;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the GenePTT Table list
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 5, 2011
 */
public class GenePTTTables {
    /*
     * List of tables names for the Taxonomy module
     */

    public static final String GENEPTT = "GenePTT";
    public static final String GENEPTT_HAS_TAXONOMY = "GenePTT_has_Taxonomy";
    public final String GENEPTTTAXONOMY = "GenePTTTaxonomy";
    public static final String GENERNT = "GeneRNT";
    public static final String GENERNT_HAS_TAXONOMY = "GeneRNT_has_Taxonomy";
    private static GenePTTTables singleton;
    private static List<String> tables;

    private GenePTTTables() {
    }

    /**
     * Return a GenePTTTables
     *
     * @return a GenePTTTables
     */
    public static synchronized GenePTTTables getInstance() {
        if (singleton == null) {
            singleton = new GenePTTTables();
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
            tables.add(GENEPTT);
            tables.add(GENEPTT_HAS_TAXONOMY);
            tables.add(GENEPTTTAXONOMY);
            tables.add(GENERNT);
            tables.add(GENERNT_HAS_TAXONOMY);
        }
        return tables;
    }
}
