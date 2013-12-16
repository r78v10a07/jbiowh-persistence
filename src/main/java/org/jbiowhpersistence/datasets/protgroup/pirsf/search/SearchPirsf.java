package org.jbiowhpersistence.datasets.protgroup.pirsf.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.pirsf.entities.Pirsf;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This class is the search over the PIRSF module
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 11, 2013
 */
public class SearchPirsf extends SearchFactory implements JBioWHSearch {

    public final String ID = "PIRSFNumber";
    public final String ACC = "AccNumber";
    public final String NAME = "Name";

    public SearchPirsf() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(ACC, String.class);
        fields.put(NAME, String.class);
        fields.put(ID, String.class);
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
        if (BioWHPattern.getInstance().isPirsfID(search)) {
            searchRow.add(ID);
            searchRow.add("like");
            searchRow.add(search);
            searchList.add(searchRow);
            return search(searchList, constrains);
        } else {
            if (search.length() <= 6) {
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
    }

    @Override
    public String getMainField() {
        return ID;
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return Pirsf.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap();
        HashMap<String, String> fieldOnEntity = getFieldOnEntity();
        for (String field : getFieldsSet()) {
            if (field.equals(ACC)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " a ");
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
            if (field.equals(ACC)) {
                data.put(field, "a.accessionNumber");
            } else if (field.equals(NAME)) {
                data.put(field, "g.name");
            } else if (field.equals(ID)) {
                data.put(field, "g.pIRSFnumber");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(ACC)) {
                data.put(field, "g.pIRSFProtein");
            } else {
                data.put(field, "");
            }
        }
        return data;
    }

    @Override
    protected HashMap<Class, List> getConstrainFieldOnEntity() {
        HashMap<Class, List> data = new HashMap();

        List listP = new ArrayList();
        listP.add("g.pIRSFhasProtein");
        listP.add("pIRSFhasProteinPK.proteinWID");
        data.put(Protein.class, listP);

        return data;
    }

}
