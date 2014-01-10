package org.jbiowhpersistence.datasets.protgroup.cog.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This class perform the search over the COG module
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 9, 2014
 */
public class SearchCOG extends SearchFactory implements JBioWHSearch {

    public final String ID = "Id";
    public final String GROUPFUNCTION = "GroupFunction";

    public SearchCOG() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(ID, String.class);
        setFields(fields);
    }

    @Override
    public String getMainField() {
        return ID;
    }

    @Override
    public List search(String search, JPLConstrains constrains) throws SQLException {
        List searchList = new ArrayList();
        List searchRow = new ArrayList();

        if (search == null || search.isEmpty()) {
            return search(searchList, constrains);
        }

        searchRow.add("");
        searchRow.add(ID);
        searchRow.add("like");
        searchRow.add(search);
        searchList.add(searchRow);
        List result = search(searchList, constrains);
        if (result.isEmpty()) {
            searchRow.clear();
            searchRow.add("");
            searchRow.add(GROUPFUNCTION);
            searchRow.add("like");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            return search(searchList, constrains);
        }
        return result;
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return COGOrthologousGroup.class;
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
            } else if (field.equals(GROUPFUNCTION)) {
                data.put(field, "g.groupFunction");
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

        List listT = new ArrayList();
        listT.add("");
        listT.add("g.taxonomy.wid");
        data.put(Taxonomy.class, listT);

        List listG = new ArrayList();
        listG.add("g.geneInfo");
        listG.add("wid");
        data.put(GeneInfo.class, listG);

        List listP = new ArrayList();
        listP.add("g.protein");
        listP.add("wid");
        data.put(Protein.class, listP);

        return data;
    }

}
