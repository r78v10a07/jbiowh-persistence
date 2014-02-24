package org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities.ProtClust;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This class is perform the search over the ProtClust module
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 13, 2014
 */
public class SearchProtClust extends SearchFactory implements JBioWHSearch {

    public final String ENTRY = "Entry";
    public final String DEFINITION = "Definition";
    public final String LOCUS = "Locus";

    public SearchProtClust() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(ENTRY, String.class);
        fields.put(DEFINITION, String.class);
        fields.put(LOCUS, String.class);
        setFields(fields);
    }

    @Override
    public String getMainField() {
        return ENTRY;
    }

    @Override
    public List search(String search, JPLConstrains constrains) throws SQLException {
        List searchList = new ArrayList();
        List searchRow = new ArrayList();

        if (search == null || search.isEmpty()) {
            return search(searchList, constrains);
        }

        for (String field : new String[]{"ENTRY", "LOCUS", "DEFINITION"}) {
            searchRow.clear();
            searchList.clear();
            searchRow.add("");
            searchRow.add(field);
            searchRow.add("like");
            searchRow.add(search);
            searchList.add(searchRow);
            List result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }
        }
        return new ArrayList();
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return ProtClust.class;
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
            if (field.equals(ENTRY)) {
                data.put(field, "g.entry");
            } else if (field.equals(LOCUS)) {
                data.put(field, "g.locus");
            } else if (field.equals(DEFINITION)) {
                data.put(field, "g.definition");
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

        List listC = new ArrayList();
        listC.add("g.cogOrthologousGroup");
        listC.add("wid");
        data.put(Protein.class, listC);

        return data;
    }

}
