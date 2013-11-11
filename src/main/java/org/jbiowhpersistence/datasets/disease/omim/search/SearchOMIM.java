package org.jbiowhpersistence.datasets.disease.omim.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the OMIM module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jul 25, 2012
 */
public class SearchOMIM extends SearchFactory implements JBioWHSearch {

    public final String OMIM_ID = "OMIM_ID";
    public final String TITLE = "Title";
    public final String TX = "Text";

    public SearchOMIM() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(OMIM_ID, Long.class);
        fields.put(TITLE, String.class);
        fields.put(TX, String.class);
        setFields(fields);
    }

    @Override
    public String getMainField() {
        return OMIM_ID;
    }

    @Override
    public List search(String search, JPLConstrains constrains) throws SQLException {
        List searchList = new ArrayList();
        List searchRow = new ArrayList();

        if (search == null || search.isEmpty()) {
            return search(searchList, constrains);
        }

        searchRow.add("");
        if (BioWHPattern.getInstance().isLong(search)) {
            searchRow.add(OMIM_ID);
            searchRow.add("=");
            searchRow.add(search);
            searchList.add(searchRow);
            return search(searchList, constrains);
        } else {
            searchRow.add(TITLE);
            searchRow.add("like");
            searchRow.add(search);
            searchList.add(searchRow);
            return search(searchList, constrains);
        }
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return OMIM.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap();
        HashMap<String, String> fieldOnEntity = getFieldOnEntity();
        for (String field : getFieldsSet()) {
            if (field.equals(TITLE)) {
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
            if (field.equals(OMIM_ID)) {
                data.put(field, "g.omimId");
            } else if (field.equals(TITLE)) {
                data.put(field, "s.oMIMTIPK.ti");
            } else if (field.equals(TX)) {
                data.put(field, "g.tx");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(TITLE)) {
                data.put(field, "g.omimTIs");
            } else {
                data.put(field, "");
            }
        }
        return data;
    }

    @Override
    protected HashMap<Class, List> getConstrainFieldOnEntity() {
        HashMap<Class, List> data = new HashMap();

        List listG = new ArrayList();
        listG.add("g.geneInfo");
        listG.add("wid");
        data.put(GeneInfo.class, listG);

        return data;
    }
}
