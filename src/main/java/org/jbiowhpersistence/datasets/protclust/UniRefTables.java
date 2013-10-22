package org.jbiowhpersistence.datasets.protclust;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the UniRef Tables
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 19, 2011
 */
public class UniRefTables {

    public final String UNIREFENTRY = "UniRefEntry";
    public final String UNIREFENTRYPROPERTY = "UniRefEntryProperty";
    public static final String UNIREFMEMBER = "UniRefMember";
    public final String UNIREFMEMBERTEMP = "UniRefMemberTemp";
    public final String UNIREFMEMBERTEMP1 = "UniRefMemberTemp1";
    public final String UNIREFMEMBERTEMP2 = "UniRefMemberTemp2";
    public final String UNIREFMEMBERPROPERTY = "UniRefMemberProperty";
    public static final String UNIREFENTRY_HAS_PROTEIN = "UniRefEntry_has_Protein";
    private static UniRefTables singleton;
    private static List<String> tables;

    private UniRefTables() {
    }

    /**
     * Return a UniRefTables
     *
     * @return a UniRefTables
     */
    public static synchronized UniRefTables getInstance() {
        if (singleton == null) {
            singleton = new UniRefTables();
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

            tables.add(UNIREFENTRY);
            tables.add(UNIREFENTRYPROPERTY);
            tables.add(UNIREFMEMBER);
            tables.add(UNIREFMEMBERTEMP);
            tables.add(UNIREFMEMBERTEMP1);
            tables.add(UNIREFMEMBERTEMP2);
            tables.add(UNIREFMEMBERPROPERTY);
            tables.add(UNIREFENTRY_HAS_PROTEIN);
        }
        return tables;
    }
}
