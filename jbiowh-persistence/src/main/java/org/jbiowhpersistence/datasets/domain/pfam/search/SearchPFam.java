package org.jbiowhpersistence.datasets.domain.pfam.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This class perform the search over the PFam module
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $
 * $LastChangedRevision: 377 $
 * @since Nov 27, 2012
 */
public class SearchPFam extends SearchFactory {

    public final String ACC = "Acc";
    public final String NAME = "Id";

    public SearchPFam() {
        HashMap<String, Class> fields = new HashMap<>();
        fields.put(ACC, String.class);
        fields.put(NAME, String.class);
        setFields(fields);
    }

    @Override
    public String getMainField() {
        return ACC;
    }

    @Override
    public List search(String search, JPLConstrains constrains) throws SQLException {
        List searchList = new ArrayList();
        List searchRow = new ArrayList();

        if (search == null || search.isEmpty()) {
            return search(searchList, constrains);
        }

        if (search.length() <= 7) {
            searchRow.add("");
            searchRow.add(ACC);
            searchRow.add("like");
            searchRow.add(search);
            searchList.add(searchRow);
            List result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }
        }

        searchRow.clear();
        searchRow.add("");
        searchRow.add(NAME);
        searchRow.add("like");
        searchRow.add(search);
        searchList.clear();
        searchList.add(searchRow);
        return search(searchList, constrains);
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return PfamAbioWH.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            data.put(field, "");
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldAfterWhere() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            switch (field) {
                case ACC:
                    data.put(field, "g.pfamAacc");
                    break;
                case NAME:
                    data.put(field, "g.pfamAid");
                    break;
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            data.put(field, "");
        }
        return data;
    }

    @Override
    protected HashMap<Class, List> getConstrainFieldOnEntity() {
        HashMap<Class, List> data = new HashMap<>();
        
        List listPS = new ArrayList();
        listPS.add("g.proteinSignificant");
        listPS.add("wid");
        listPS.add("g.proteinInsignificant");
        listPS.add("wid");
        data.put(Protein.class, listPS);
        
        return data;
    }
}
