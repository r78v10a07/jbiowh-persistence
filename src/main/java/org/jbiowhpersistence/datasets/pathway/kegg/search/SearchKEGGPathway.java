package org.jbiowhpersistence.datasets.pathway.kegg.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This class perform the search over the KEGGPathway module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Dec 16, 2011
 */
public class SearchKEGGPathway extends SearchFactory {

    public final String ID = "Id";
    public final String NUMBER = "Number";
    public final String TITLE = "Title";

    public SearchKEGGPathway() {
        HashMap<String, Class> fields = new HashMap<>();
        fields.put(ID, String.class);
        fields.put(NUMBER, String.class);
        fields.put(TITLE, String.class);
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
        if (BioWHPattern.getInstance().isDigits(search)) {
            searchRow.add(NUMBER);
        } else if (BioWHPattern.getInstance().isKEGGPathwayEntry(search)) {
            searchRow.add(ID);
        } else {
            searchRow.add(TITLE);
        }
        searchRow.add("like");
        searchRow.add(search);
        searchList.add(searchRow);
        return search(searchList, constrains);
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return KEGGPathway.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            data.put(field, "");
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldAfterWhere() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            switch (field) {
                case ID:
                    data.put(field, "g.entry");
                    break;
                case NUMBER:
                    data.put(field, "g.number");
                    break;
                case TITLE:
                    data.put(field, "g.title");
                    break;
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap<>();
        for (String field : getFieldsSet()) {
            data.put(field, "");
        }
        return data;
    }

    @Override
    protected HashMap<Class, List> getConstrainFieldOnEntity() {
        HashMap<Class, List> data = new HashMap<>();

        List listT = new ArrayList();
        listT.add("");
        listT.add("g.taxonomy.wid");
        data.put(Taxonomy.class, listT);

        List listC = new ArrayList();
        listC.add("g.kEGGCompounds");
        listC.add("wid");
        data.put(KEGGCompound.class, listC);

        List listE = new ArrayList();
        listE.add("g.kEGGEnzymes");
        listE.add("wid");
        data.put(KEGGEnzyme.class, listE);

        List listKG = new ArrayList();
        listKG.add("g.kEGGGenes");
        listKG.add("wid");
        data.put(KEGGGene.class, listKG);

        List listR = new ArrayList();
        listR.add("g.kEGGReactions");
        listR.add("wid");
        data.put(KEGGReaction.class, listR);

        List listP = new ArrayList();
        listP.add("g.protein");
        listP.add("wid");
        data.put(Protein.class, listP);

        List listG = new ArrayList();
        listG.add("g.geneInfo");
        listG.add("wid");
        data.put(GeneInfo.class, listG);

        return data;
    }

    @Override
    public String getMainField() {
        return ID;
    }
}
