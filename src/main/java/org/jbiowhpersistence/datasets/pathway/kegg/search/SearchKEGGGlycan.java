package org.jbiowhpersistence.datasets.pathway.kegg.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the KEGGGlycan module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since May 14, 2012
 */
public class SearchKEGGGlycan extends SearchFactory {

    public final String ENTRY = "Entry";
    public final String NAME = "Name";
    public final String COMMENT = "Coment";
    public final String CLASS = "Class";
    public final String DBLINKID = "DBLinkID";
    public final String DBLINKDB = "DBLinkDB";
    public final String REMARK = "Remark";

    public SearchKEGGGlycan() {
        HashMap<String, Class> fields = new HashMap<>();
        fields.put(ENTRY, String.class);
        fields.put(NAME, String.class);
        fields.put(COMMENT, String.class);
        fields.put(CLASS, String.class);
        fields.put(DBLINKID, String.class);
        fields.put(DBLINKDB, String.class);
        fields.put(REMARK, String.class);
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
        if (BioWHPattern.getInstance().isKEGGGlycanEntry(search)) {
            searchRow.add(ENTRY);
            searchRow.add("like");
            searchRow.add(search);
            searchList.add(searchRow);
            return search(searchList, constrains);
        }

        searchRow.add(NAME);
        searchRow.add("like");
        searchRow.add(search);
        searchList.add(searchRow);
        return search(searchList, constrains);
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return KEGGGlycan.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap<>();
        HashMap<String, String> fieldOnEntity = getFieldOnEntity();
        for (String field : getFieldsSet()) {
            switch (field) {
                case NAME:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " n ");
                    break;
                case CLASS:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " c ");
                    break;
                case DBLINKID:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " dId ");
                    break;
                case DBLINKDB:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " dDb ");
                    break;
                default:
                    data.put(field, "");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldAfterWhere() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            switch (field) {
                case ENTRY:
                    data.put(field, "g.entry");
                    break;
                case NAME:
                    data.put(field, "n.name");
                    break;
                case COMMENT:
                    data.put(field, "g.comment");
                    break;
                case CLASS:
                    data.put(field, "c.class1");
                    break;
                case DBLINKID:
                    data.put(field, "dId.kEGGEnzymeDBLinkPK.id");
                    break;
                case DBLINKDB:
                    data.put(field, "dDb.kEGGEnzymeDBLinkPK.db");
                    break;
                case REMARK:
                    data.put(field, "g.remark");
                    break;
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            switch (field) {
                case NAME:
                    data.put(field, "g.kEGGGlycanName");
                    break;
                case CLASS:
                    data.put(field, "g.kEGGGlycanClass");
                    break;
                case DBLINKID:
                    data.put(field, "g.kEGGGlycanDBLink");
                    break;
                case DBLINKDB:
                    data.put(field, "g.kEGGGlycanDBLink");
                    break;
                default:
                    data.put(field, "");
                    break;
            }
        }
        return data;
    }

    @Override
    protected HashMap<Class, List> getConstrainFieldOnEntity() {
        HashMap<Class, List> data = new HashMap<>();
        return data;
    }

    @Override
    public String getMainField() {
        return ENTRY;
    }
}
