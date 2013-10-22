package org.jbiowhpersistence.datasets.dataset;

import java.util.ArrayList;
import java.util.List;
import org.jbiowhpersistence.datasets.disease.omim.OMIMTables;
import org.jbiowhpersistence.datasets.domain.pfam.PFamTables;
import org.jbiowhpersistence.datasets.drug.drugbank.DrugBankTables;
import org.jbiowhpersistence.datasets.gene.gene.GeneTables;
import org.jbiowhpersistence.datasets.gene.genome.GenePTTTables;
import org.jbiowhpersistence.datasets.ontology.OntologyTables;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.ppi.MIF25Tables;
import org.jbiowhpersistence.datasets.protclust.UniRefTables;
import org.jbiowhpersistence.datasets.protein.ProteinTables;
import org.jbiowhpersistence.datasets.taxonomy.TaxonomyTables;

/**
 * This Class is return all Tables included into the relational schema
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jun 26, 2012
 */
public class DataSetsTables {

    private static DataSetsTables dataSetsTables;
    private static List<String> tables;

    private DataSetsTables() {
    }

    public static synchronized DataSetsTables getInstance() {
        if (dataSetsTables == null) {
            dataSetsTables = new DataSetsTables();
        }
        return dataSetsTables;
    }

    /**
     * Return a list object with the tables names
     *
     * @return A list object with the tables names
     */
    public List<String> getTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.addAll(DrugBankTables.getInstance().getTables());
            tables.addAll(GeneTables.getInstance().getTables());
            tables.addAll(GenePTTTables.getInstance().getTables());
            tables.addAll(MIF25Tables.getInstance().getTables());
            tables.addAll(OntologyTables.getInstance().getTables());
            tables.addAll(KEGGTables.getInstance().getTables());
            tables.addAll(ProteinTables.getInstance().getTables());
            tables.addAll(UniRefTables.getInstance().getTables());
            tables.addAll(TaxonomyTables.getInstance().getTables());
            tables.addAll(OMIMTables.getInstance().getTables());
            tables.addAll(PFamTables.getInstance().getTables());
            tables.addAll(GenePTTTables.getInstance().getTables());
        }
        return tables;
    }
}
