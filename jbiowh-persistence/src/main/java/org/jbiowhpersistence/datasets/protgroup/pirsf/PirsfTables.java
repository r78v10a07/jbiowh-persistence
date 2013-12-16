package org.jbiowhpersistence.datasets.protgroup.pirsf;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the PIRSF database tables
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 11, 2013
 */
public class PirsfTables {

    public final String PIRSF = "PIRSF";
    public static final String PIRSFPROTEIN = "PIRSFProtein";
    public static final String PIRSF_HAS_PROTEIN = "PIRSF_has_Protein";
    public final String PIRSF_FILE = "pirsfinfo.dat";
    private static PirsfTables singleton;
    private static List<String> tables;

    private PirsfTables() {
    }

    /**
     * Return a ProteinTables
     *
     * @return a ProteinTables
     */
    public static synchronized PirsfTables getInstance() {
        if (singleton == null) {
            singleton = new PirsfTables();
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
            tables.add(PIRSF);
            tables.add(PIRSFPROTEIN);
            tables.add(PIRSF_HAS_PROTEIN);
        }
        return tables;
    }

}
