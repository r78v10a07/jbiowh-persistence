package org.jbiowhpersistence.datasets.ontology.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the Ontology module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jun 29, 2011
 */
public class SearchOntology extends SearchFactory implements JBioWHSearch {

    public final String ID = "Id";
    public final String NAME = "Name";
    public final String DEFINITION = "Definition";
    public final String COMMENT = "Comment";
    public final String SYNONYM = "Synonym";
    public final String ALTID = "AlternativeId";

    /**
     * This Class perform the search over the Ontology module
     */
    public SearchOntology() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(ID, String.class);
        fields.put(NAME, String.class);
        fields.put(DEFINITION, String.class);
        fields.put(COMMENT, String.class);
        fields.put(SYNONYM, String.class);
        fields.put(ALTID, String.class);
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
        if (BioWHPattern.getInstance().isGOId(search)) {
            searchRow.add(ID);
            searchRow.add("like");
            searchRow.add(search);
            searchList.add(searchRow);
            List result = search(searchList, constrains);
            if (result != null && !result.isEmpty()) {
                return result;
            } else {
                searchRow.clear();
                searchRow.add("");
                searchRow.add(ALTID);
                searchRow.add("like");
                searchRow.add(search);
                searchList.clear();
                searchList.add(searchRow);
                return search(searchList, constrains);
            }
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
            searchRow.add(DEFINITION);
            searchRow.add("like");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }

            searchRow.clear();
            searchRow.add("");
            searchRow.add(SYNONYM);
            searchRow.add("like");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            return search(searchList, constrains);
        }
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return Ontology.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(SYNONYM)) {
                data.put(field, " INNER JOIN " + getFieldOnEntity().get(field) + " n ");
            } else if (field.equals(ALTID)) {
                data.put(field, " INNER JOIN " + getFieldOnEntity().get(field) + " a ");
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
            } else if (field.equals(COMMENT)) {
                data.put(field, "g.comment");
            } else if (field.equals(DEFINITION)) {
                data.put(field, "g.def");
            } else if (field.equals(SYNONYM)) {
                data.put(field, "n.ontologySynonym.synonym");
            } else if (field.equals(ALTID)) {
                data.put(field, "a.altId");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(SYNONYM)) {
                data.put(field, "g.ontologyhasOntologySynonym");
            } else if (field.equals(ALTID)) {
                data.put(field, "g.ontologyAlternativeId");
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
        return ID;
    }
}
