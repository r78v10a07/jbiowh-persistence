package org.jbiowhpersistence.datasets.taxonomy.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the Taxonomy module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100
 * (Tue, 19 Mar 2013) $ $LastChangedRevision: 396 $
 *
 * @since Jun 23, 2011
 */
public class SearchTaxonomy extends SearchFactory implements JBioWHSearch {

    public final String TAXID = "TaxId";
    public final String SYNONYM = "Synonym";

    /**
     * This Class perform the search over the Taxonomy module
     */
    public SearchTaxonomy() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(TAXID, Long.class);
        fields.put(SYNONYM, String.class);
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
            searchRow.add(TAXID);
            searchRow.add("=");
            searchRow.add(search);
        } else {
            searchRow.add(SYNONYM);
            searchRow.add("like");
            searchRow.add(search);
        }
        searchList.add(searchRow);
        return search(searchList, constrains);
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return Taxonomy.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(SYNONYM)) {
                data.put(field, " INNER JOIN " + getFieldOnEntity().get(field) + " n ");
            } else {
                data.put(field, "");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldAfterWhere() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(TAXID)) {
                data.put(field, "g.taxId");
            } else if (field.equals(SYNONYM)) {
                data.put(field, "n.synonym");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(SYNONYM)) {
                data.put(field, "g.synonym");
            } else {
                data.put(field, "");
            }
        }
        return data;
    }

    @Override
    protected HashMap<Class, List> getConstrainFieldOnEntity() {
        HashMap<Class, List> data = new HashMap();
        return data;
    }

    @Override
    public String getMainField() {
        return TAXID;
    }
}
