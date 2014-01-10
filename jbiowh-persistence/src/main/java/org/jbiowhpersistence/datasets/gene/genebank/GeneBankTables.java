package org.jbiowhpersistence.datasets.gene.genebank;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the GeneBank Table list
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Apr 30, 2013
 */
public class GeneBankTables {

    public final String GENEBANK = "GeneBank";
    public final String GENEBANKACCESSION = "GeneBankAccession";
    public final String GENEBANKCDS = "GeneBankCDS";
    public final String GENEBANKCDSLOCATION = "GeneBankCDSLocation";
    public final String GENEBANKCDSTEMP = "GeneBankCDSTemp";
    public final String GENEBANKFEATURES = "GeneBankFeatures";
    public final String GENEBANKCDSDBXREF = "GeneBankCDSDBXref";
    public final String GENEBANKCOG = "GeneBankCOG";
    public static final String GENEBANKCDS_HAS_GENEINFO = "GeneBankCDS_has_GeneInfo";
    private static GeneBankTables singleton;
    private static List<String> tables;

    private GeneBankTables() {
    }

    /**
     * Return a GeneBankTables instance
     *
     * @return a GeneBankTables instance
     */
    public static synchronized GeneBankTables getInstance() {
        if (singleton == null) {
            singleton = new GeneBankTables();
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
            tables.add(GENEBANK);
            tables.add(GENEBANKACCESSION);
            tables.add(GENEBANKCDS);
            tables.add(GENEBANKCDSLOCATION);
            tables.add(GENEBANKCDSTEMP);
            tables.add(GENEBANKCDSDBXREF);
            tables.add(GENEBANKFEATURES);
            tables.add(GENEBANKCOG);
            tables.add(GENEBANKCDS_HAS_GENEINFO);
        }

        return tables;
    }
}
