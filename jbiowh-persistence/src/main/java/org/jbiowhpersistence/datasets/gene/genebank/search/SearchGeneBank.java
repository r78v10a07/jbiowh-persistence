package org.jbiowhpersistence.datasets.gene.genebank.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This class perform the search over the GeneBank module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 9, 2013
 */
public class SearchGeneBank extends SearchFactory {

    public final String GI = "Gi";
    public final String PROTEINGI = "ProteinGi";
    public final String TAXID = "TaxId";
    public final String LOCUSNAME = "LocusName";
    public final String DESCRIPTION = "Description";

    public SearchGeneBank() {
        HashMap<String, Class> fields = new HashMap<>();
        fields.put(GI, Long.class);
        fields.put(PROTEINGI, Long.class);
        fields.put(TAXID, Integer.class);
        fields.put(LOCUSNAME, String.class);
        fields.put(DESCRIPTION, String.class);
        setFields(fields);
    }

    @Override
    public List search(String search, JPLConstrains constrains)
            throws SQLException {
        List searchList = new ArrayList();
        List searchRow = new ArrayList();

        if (search == null || search.isEmpty()) {
            return search(searchList, constrains);
        }

        searchRow.add("");
        if (BioWHPattern.getInstance().isLong(search)) {
            searchRow.add(GI);
            searchRow.add("=");
            searchRow.add(search);
            searchList.add(searchRow);
            return search(searchList, constrains);
        } else {
            searchRow.add(LOCUSNAME);
            searchRow.add("like");
            searchRow.add(search);
            searchList.add(searchRow);
            return search(searchList, constrains);
        }
    }

    @Override
    public String getMainField() {
        return GI;
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return GeneBank.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap<>();
        HashMap<String, String> fieldOnEntity = getFieldOnEntity();
        for (String field : getFieldsSet()) {
            switch (field) {
                case PROTEINGI:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " p ");
                    break;
                default:
                    data.put(field, "");
                    break;
            }
        }
        return data;

    }

    @Override
    protected HashMap<String, String> getFieldAfterWhere() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            switch (field) {
                case GI:
                    data.put(field, "g.gi");
                    break;
                case LOCUSNAME:
                    data.put(field, "g.locusName");
                    break;
                case DESCRIPTION:
                    data.put(field, "g.description");
                    break;
                case TAXID:
                    data.put(field, "g.taxId");
                    break;
                case PROTEINGI:
                    data.put(field, "p.proteinGi");
                    break;
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            switch (field) {
                case PROTEINGI:
                    data.put(field, "g.geneBankCDSs");
                    break;
                default:
                    data.put(field, "");
                    break;
            }
        }
        return data;
    }

    @Override
    protected HashMap<Class, List> getConstrainFieldOnEntity() {
        HashMap<Class, List> data = new HashMap<>();

        List listT = new ArrayList();
        listT.add("g.taxonomy");
        listT.add("wid");
        data.put(Taxonomy.class, listT);

        return data;
    }
}
