package org.jbiowhpersistence.datasets.pathway.kegg.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the KEGGGene module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 14, 2012
 */
public class SearchKEGGGene extends SearchFactory {

    public final String ENTRY = "Entry";
    public final String NAME = "Name";
    public final String DEFINITION = "Definition";
    public final String DISEASEENTRY = "Disease Entry";
    public final String DISEASENAME = "Disease Name";
    public final String DBLINKID = "DBLinkID";
    public final String DBLINKDB = "DBLinkDB";
    public final String ORTHOLOGYNAME = "Orthology Name";
    public final String ORTHOLOGYENTRY = "Orthology Entry";
    public final String DRUGTARGET = "DrugTarget";

    public SearchKEGGGene() {
        HashMap<String, Class> fields = new HashMap<>();
        fields.put(ENTRY, String.class);
        fields.put(NAME, String.class);
        fields.put(DEFINITION, String.class);
        fields.put(DISEASEENTRY, String.class);
        fields.put(DISEASENAME, String.class);
        fields.put(DBLINKID, String.class);
        fields.put(DBLINKDB, String.class);
        fields.put(ORTHOLOGYNAME, String.class);
        fields.put(ORTHOLOGYENTRY, String.class);
        fields.put(DRUGTARGET, String.class);
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
        if (BioWHPattern.getInstance().isKEGGPathwayEntry(search)) {
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
        return KEGGGene.class;
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
                case DISEASEENTRY:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " dE ");
                    break;
                case DISEASENAME:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " dN ");
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
                case DRUGTARGET:
                    data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " d ");
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
                case DEFINITION:
                    data.put(field, "g.definition");
                    break;
                case DISEASEENTRY:
                    data.put(field, "dE.entry");
                    break;
                case DISEASENAME:
                    data.put(field, "dN.disease");
                    break;
                case DBLINKID:
                    data.put(field, "dId.kegggenedblinkPK.id");
                    break;
                case DBLINKDB:
                    data.put(field, "dDb.kegggenedblinkPK.db");
                    break;
                case ORTHOLOGYNAME:
                    data.put(field, "oN.name");
                    break;
                case ORTHOLOGYENTRY:
                    data.put(field, "oE.kEGGGeneOrthologyPK.entry");
                    break;
                case DRUGTARGET:
                    data.put(field, "d.kEGGGeneDrugTargetPK.name");
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
                    data.put(field, "g.kEGGGeneName");
                    break;
                case DISEASEENTRY:
                    data.put(field, "g.kEGGGeneDisease");
                    break;
                case DISEASENAME:
                    data.put(field, "g.kEGGGeneDisease");
                    break;
                case DBLINKID:
                    data.put(field, "g.kEGGGeneDBLink");
                    break;
                case DBLINKDB:
                    data.put(field, "g.kEGGGeneDBLink");
                    break;
                case ORTHOLOGYNAME:
                    data.put(field, "g.kEGGGeneOrthology");
                    break;
                case ORTHOLOGYENTRY:
                    data.put(field, "g.kEGGGeneOrthology");
                    break;
                case DRUGTARGET:
                    data.put(field, "g.kEGGGeneDrugTarget");
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

        List listG = new ArrayList();
        listG.add("g.geneInfos");
        listG.add("wid");
        data.put(GeneInfo.class, listG);

        return data;
    }

    @Override
    public String getMainField() {
        return ENTRY;
    }
}
