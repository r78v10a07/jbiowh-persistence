package org.jbiowhpersistence.datasets.drug.drugbank.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This class perform the search over the DrugBank module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-11-27 14:55:04 +0100
 * (Tue, 27 Nov 2012) $ $LastChangedRevision: 345 $
 *
 * @since Oct 8, 2011
 */
public class SearchDrugBank extends SearchFactory implements JBioWHSearch {

    public final String ID = "id";
    public final String NAME = "name";
    public final String CAS = "CASNumber";
    public final String INDICATION = "indication";
    public final String DESCRIPTION = "description";
    public final String CATEGORY = "category";
    public final String SYNONYM = "synonym";

    /**
     * Creates the DrugBank search object
     */
    public SearchDrugBank() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(ID, String.class);
        fields.put(NAME, String.class);
        fields.put(CAS, String.class);
        fields.put(INDICATION, String.class);
        fields.put(DESCRIPTION, String.class);
        fields.put(CATEGORY, String.class);
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
        if (BioWHPattern.getInstance().isCAS(search)) {
            searchRow.add(CAS);
        } else if (BioWHPattern.getInstance().isDrugBankId(search)) {
            searchRow.add(ID);
        } else {
            searchRow.add(NAME);
            searchRow.add("like");
            searchRow.add(search);
            searchList.add(searchRow);
            List result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }

            searchRow.clear();
            searchRow.add("");
            searchRow.add(INDICATION);
            searchRow.add("like");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            return search(searchList, constrains);
        }

        searchRow.add("like");
        searchRow.add(search);
        searchList.add(searchRow);
        return search(searchList, constrains);

    }

    @Override
    protected Class getSearchFactoryEntity() {
        return DrugBank.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap();
        HashMap<String, String> fieldOnEntity = getFieldOnEntity();
        for (String field : getFieldsSet()) {
            if (field.equals(CATEGORY)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " c ");
            } else if (field.equals(SYNONYM)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " s ");
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
            if (field.equals(ID)) {
                data.put(field, "g.id");
            } else if (field.equals(NAME)) {
                data.put(field, "g.name");
            } else if (field.equals(CAS)) {
                data.put(field, "g.cASNumber");
            } else if (field.equals(INDICATION)) {
                data.put(field, "g.indication");
            } else if (field.equals(DESCRIPTION)) {
                data.put(field, "g.description");
            } else if (field.equals(CATEGORY)) {
                data.put(field, "c.category");
            } else if (field.equals(SYNONYM)) {
                data.put(field, "s.synonym");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(CATEGORY)) {
                data.put(field, "g.drugBankCategories");
            } else if (field.equals(SYNONYM)) {
                data.put(field, "g.drugBankSynonyms");
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
        listP.add("g.protein");
        listP.add("wid");
        listP.add("g.proteinAsEnzyme");
        listP.add("wid");
        listP.add("g.proteinAsTransporters");
        listP.add("wid");
        listP.add("g.proteinAsCarriers");
        listP.add("wid");
        data.put(Protein.class, listP);

        return data;
    }

    @Override
    public String getMainField() {
        return ID;
    }
}
