package org.jbiowhpersistence.datasets.protgroup.cog;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the the COG/KOG database tables
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 19, 2013
 */
public class COGTables {

    public final String COGFUNCCLASSGROUP = "COGFuncClassGroup";
    public final String COGFUNCCLASS = "COGFuncClass";
    public final String COGORTHOLOGOUSGROUP = "COGOrthologousGroup";
    public final String COGMEMBER_HAS_GI = "COGMember_has_Gi";
    public final String COGMEMBER_TAXID = "COGMember_TaxId";
    public final String COGMEMBER = "COGMember";
    public static final String COGORTHOLOGOUSGROUP_HAS_TAXONOMY = "COGOrthologousGroup_has_Taxonomy";
    public static final String COGORTHOLOGOUSGROUP_HAS_GENEINFO = "COGOrthologousGroup_has_GeneInfo";
    public static final String COGORTHOLOGOUSGROUP_HAS_PROTEIN = "COGOrthologousGroup_has_Protein";
    public static final String COGORTHOLOGOUSGROUP_HAS_COGFUNCCLASS = "COGOrthologousGroup_has_COGFuncClass";
    private static COGTables singleton;
    private static List<String> tables;

    private COGTables() {
    }

    /**
     * Return a COGTables instance
     *
     * @return a COGTables instance
     */
    public static synchronized COGTables getInstance() {
        if (singleton == null) {
            singleton = new COGTables();
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
            tables.add(COGFUNCCLASSGROUP);
            tables.add(COGFUNCCLASS);
            tables.add(COGORTHOLOGOUSGROUP);
            tables.add(COGMEMBER_HAS_GI);
            tables.add(COGMEMBER_TAXID);
            tables.add(COGMEMBER);
            tables.add(COGORTHOLOGOUSGROUP_HAS_TAXONOMY);
            tables.add(COGORTHOLOGOUSGROUP_HAS_GENEINFO);
            tables.add(COGORTHOLOGOUSGROUP_HAS_PROTEIN);
            tables.add(COGORTHOLOGOUSGROUP_HAS_COGFUNCCLASS);
        }
        return tables;
    }
}
