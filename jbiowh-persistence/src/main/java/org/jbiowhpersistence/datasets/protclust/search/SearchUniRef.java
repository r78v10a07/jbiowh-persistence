package org.jbiowhpersistence.datasets.protclust.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the UniRef module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-11-08 14:37:19 +0100
 * (Thu, 08 Nov 2012) $ $LastChangedRevision: 322 $
 *
 * @since Sep 1, 2011
 */
public class SearchUniRef extends SearchFactory implements JBioWHSearch {

    public final String ID = "Id";
    public final String NAME = "Name";

    /**
     * This Class perform the search over the UniRef module
     */
    public SearchUniRef() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(ID, String.class);
        fields.put(NAME, String.class);
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
        if (search.toUpperCase().startsWith("UNIREF")) {
            searchRow.add(ID);
        } else {
            searchRow.add(NAME);
        }
        searchRow.add("like");
        searchRow.add(search);
        searchList.add(searchRow);
        return search(searchList, constrains);
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return UniRefEntry.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            data.put(field, "");
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldAfterWhere() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(ID)) {
                data.put(field, "g.id");
            } else if (field.equals(NAME)) {
                data.put(field, "g.name");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            data.put(field, "");
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
        return ID;
    }
}
