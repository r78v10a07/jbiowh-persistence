package org.jbiowhpersistence.datasets.pathway.kegg.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the KEGGEnzyme module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since May 14, 2012
 */
public class SearchKEGGEnzyme extends SearchFactory {

    public final String ENTRY = "Entry";
    public final String NAME = "Name";
    public final String SYSNAME = "SysName";
    public final String CLASS = "Class";
    public final String COMMENT = "Coment";
    public final String DBLINKID = "DBLinkID";
    public final String DBLINKDB = "DBLinkDB";
    public final String ORTHOLOGYNAME = "Orthology Name";
    public final String ORTHOLOGYENTRY = "Orthology Entry";

    public SearchKEGGEnzyme() {
        HashMap<String, Class> fields = new HashMap<>();
        fields.put(ENTRY, String.class);
        fields.put(NAME, String.class);
        fields.put(SYSNAME, String.class);
        fields.put(CLASS, String.class);
        fields.put(COMMENT, String.class);
        fields.put(DBLINKID, String.class);
        fields.put(DBLINKDB, String.class);
        fields.put(ORTHOLOGYNAME, String.class);
        fields.put(ORTHOLOGYENTRY, String.class);
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
        if (BioWHPattern.getInstance().isEC(search)) {
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
        return KEGGEnzyme.class;
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
                case SYSNAME:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " s ");
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
                case ORTHOLOGYNAME:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " oN ");
                    break;
                case ORTHOLOGYENTRY:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " oE ");
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
                case SYSNAME:
                    data.put(field, "s.sySName");
                    break;
                case CLASS:
                    data.put(field, "c.class1");
                    break;
                case COMMENT:
                    data.put(field, "g.comment");
                    break;
                case DBLINKID:
                    data.put(field, "dId.kEGGEnzymeDBLinkPK.id");
                    break;
                case DBLINKDB:
                    data.put(field, "dDb.kEGGEnzymeDBLinkPK.db");
                    break;
                case ORTHOLOGYNAME:
                    data.put(field, "oN.name");
                    break;
                case ORTHOLOGYENTRY:
                    data.put(field, "oE.kEGGEnzymeOrthologyPK.entry");
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
                    data.put(field, "g.kEGGEnzymeName");
                    break;
                case SYSNAME:
                    data.put(field, "g.kEGGEnzymeSysName");
                    break;
                case CLASS:
                    data.put(field, "g.kEGGEnzymeClass");
                    break;
                case DBLINKID:
                    data.put(field, "g.kEGGEnzymeDBLink");
                    break;
                case DBLINKDB:
                    data.put(field, "g.kEGGEnzymeDBLink");
                    break;
                case ORTHOLOGYNAME:
                    data.put(field, "g.kEGGEnzymeOrthology");
                    break;
                case ORTHOLOGYENTRY:
                    data.put(field, "g.kEGGEnzymeOrthology");
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

        List listP = new ArrayList();
        listP.add("g.kEGGPathways");
        listP.add("wid");
        data.put(KEGGPathway.class, listP);

        List listPr = new ArrayList();
        listPr.add("g.protein");
        listPr.add("wid");
        data.put(Protein.class, listPr);

        return data;
    }

    @Override
    public String getMainField() {
        return ENTRY;
    }
}
