package org.jbiowhpersistence.datasets.taxonomy;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the taxonomy tables
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 18, 2011
 */
public class TaxonomyTables {
    /*
     * List of tables names for the Taxonomy module
     */

    public final String CITTAX = "CitTax";
    public final String DIVISIONTEMP = "DivisionTemp";
    public final String GENCODETEMP = "GenCodeTemp";
    public final String NAMES = "Names";
    public final String NCBICITATIONTEMP = "NCBICitationTemp";
    public final String NODES = "Nodes";
    public final String TAXONOMY = "Taxonomy";
    public final String TAXONOMY_HAS_TAXONOMYUNPARSECITATION = "Taxonomy_has_TaxonomyUnParseCitation";
    public final String TAXONOMYDIVISION = "TaxonomyDivision";
    public final String TAXONOMYGENCODE = "TaxonomyGenCode";
    public final String TAXONOMYPMID = "TaxonomyPMID";
    public final String TAXONOMYSYNONYM = "TaxonomySynonym";
    public final String TAXONOMYSYNONYMNAMECLASS = "TaxonomySynonymNameClass";
    public final String TAXONOMYUNPARSECITATION = "TaxonomyUnParseCitation";
    public final String DIVISIONFILE = "division.dmp";
    public final String GENCODEFILE = "gencode.dmp";
    public final String CITATIONSFILE = "citations.dmp";
    public final String CITTAXFILE = "cittax.tsv";
    public final String NAMESFILE = "names.dmp";
    public final String NODESFILE = "nodes.dmp";
    private static TaxonomyTables singleton;
    private static List<String> tables;

    private TaxonomyTables() {
    }

    /**
     * Return a TaxonomyTables
     *
     * @return a TaxonomyTables
     */
    public static synchronized TaxonomyTables getInstance() {
        if (singleton == null) {
            singleton = new TaxonomyTables();
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
            tables.add(CITTAX);
            tables.add(DIVISIONTEMP);
            tables.add(GENCODETEMP);
            tables.add(NAMES);
            tables.add(NCBICITATIONTEMP);
            tables.add(NODES);
            tables.add(TAXONOMY);
            tables.add(TAXONOMY_HAS_TAXONOMYUNPARSECITATION);
            tables.add(TAXONOMYDIVISION);
            tables.add(TAXONOMYGENCODE);
            tables.add(TAXONOMYPMID);
            tables.add(TAXONOMYSYNONYM);
            tables.add(TAXONOMYSYNONYMNAMECLASS);
            tables.add(TAXONOMYUNPARSECITATION);
        }
        return tables;
    }
}
