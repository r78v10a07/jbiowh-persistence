package org.jbiowhpersistence.datasets.pathway.kegg.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the KEGGCompound module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since May 14, 2012
 */
public class SearchKEGGCompound extends SearchFactory implements JBioWHSearch {

    public final String ENTRY = "Entry";
    public final String NAME = "Name";
    public final String REMARK = "Remark";
    public final String FORMULA = "Formula";
    public final String DBLINKID = "DBLinkID";
    public final String DBLINKDB = "DBLinkDB";

    public SearchKEGGCompound() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(ENTRY, String.class);
        fields.put(NAME, String.class);
        fields.put(REMARK, String.class);
        fields.put(FORMULA, String.class);
        fields.put(DBLINKID, String.class);
        fields.put(DBLINKDB, String.class);
        setFields(fields);
    }

    @Override
    public List search(String search, JPLConstrains constrains) throws SQLException {
        List searchList = new ArrayList();
        List searchRow = new ArrayList();

        if (search == null || search.isEmpty()) {
            return search(searchList, constrains);
        }

        searchRow.add("");
        if (BioWHPattern.getInstance().isKEGGCompoundEntry(search)) {
            searchRow.add(ENTRY);
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
        return KEGGCompound.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap();
        HashMap<String, String> fieldOnEntity = getFieldOnEntity();
        for (String field : getFieldsSet()) {
            if (field.equals(NAME)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " n ");
            } else if (field.equals(DBLINKID)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " dId ");
            } else if (field.equals(DBLINKDB)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " dDb ");
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
            if (field.equals(ENTRY)) {
                data.put(field, "g.entry");
            } else if (field.equals(NAME)) {
                data.put(field, "n.name");
            } else if (field.equals(REMARK)) {
                data.put(field, "g.remark");
            } else if (field.equals(FORMULA)) {
                data.put(field, "g.formula");
            } else if (field.equals(DBLINKID)) {
                data.put(field, "dId.keggcompounddblinkPK.id");
            } else if (field.equals(DBLINKDB)) {
                data.put(field, "dDb.keggcompounddblinkPK.db");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(NAME)) {
                data.put(field, "g.kEGGCompoundName");
            } else if (field.equals(DBLINKID)) {
                data.put(field, "g.kEGGCompoundDBLink");
            } else if (field.equals(DBLINKDB)) {
                data.put(field, "g.kEGGCompoundDBLink");
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
        listP.add("g.kEGGPathways");
        listP.add("wid");
        data.put(KEGGPathway.class, listP);

        List listD = new ArrayList();
        listD.add("g.drugBanks");
        listD.add("wid");
        data.put(DrugBank.class, listD);

        return data;
    }

    @Override
    public String getMainField() {
        return ENTRY;
    }
}
