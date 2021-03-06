package org.jbiowhpersistence.datasets.protein.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.pirsf.entities.Pirsf;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the Protein module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-12-27 15:38:46 +0100
 * (Thu, 27 Dec 2012) $ $LastChangedRevision: 591 $
 *
 * @since Aug 12, 2011
 */
public class SearchProtein extends SearchFactory implements JBioWHSearch {

    public final String ACC = "AccNumber";
    public final String NAME = "Name";
    public final String LONGNAME = "LongName";
    public final String EC = "EC";

    /**
     * This Class perform the search over the Protein module
     */
    public SearchProtein() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(ACC, String.class);
        fields.put(NAME, String.class);
        fields.put(LONGNAME, String.class);
        fields.put(EC, String.class);
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
        if (BioWHPattern.getInstance().isProteinName(search)) {
            searchRow.add(NAME);
        } else if (BioWHPattern.getInstance().isEC(search)) {
            searchRow.add(EC);
        } else {
            if (search.length() <= 6) {
                searchRow.add(ACC);
                searchRow.add("like");
                searchRow.add(search);
                searchList.add(searchRow);
                List result = search(searchList, constrains);
                if (!result.isEmpty()) {
                    return result;
                }
            }

            searchRow.clear();
            searchRow.add("");
            searchRow.add(LONGNAME);
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
        return Protein.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap();
        HashMap<String, String> fieldOnEntity = getFieldOnEntity();
        for (String field : getFieldsSet()) {
            if (field.equals(ACC)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " a ");
            } else if (field.equals(NAME)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " n ");
            } else if (field.equals(LONGNAME)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " l ");
            } else if (field.equals(EC)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " e ");
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
            if (field.equals(ACC)) {
                data.put(field, "a.accessionNumber");
            } else if (field.equals(NAME)) {
                data.put(field, "n.name");
            } else if (field.equals(LONGNAME)) {
                data.put(field, "l.name");
            } else if (field.equals(EC)) {
                data.put(field, "e.id");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(ACC)) {
                data.put(field, "g.proteinAccessionNumber");
            } else if (field.equals(NAME)) {
                data.put(field, "g.proteinName");
            } else if (field.equals(LONGNAME)) {
                data.put(field, "g.proteinLongName");
            } else if (field.equals(EC)) {
                data.put(field, "g.proteinEC");
            } else {
                data.put(field, "");
            }
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

        List listO = new ArrayList();
        listO.add("g.ontology");
        listO.add("wid");
        data.put(Ontology.class, listO);

        List listG = new ArrayList();
        listG.add("g.geneInfo");
        listG.add("wid");
        data.put(GeneInfo.class, listG);

        List listGP = new ArrayList();
        listGP.add("g.genePTT");
        listGP.add("proteinGi");
        data.put(GenePTT.class, listGP);

        List listU = new ArrayList();
        listU.add("g.uniRefEntry");
        listU.add("wid");
        data.put(UniRefEntry.class, listU);

        List listD = new ArrayList();
        listD.add("g.drugBank");
        listD.add("wid");
        listD.add("g.drugBankAsEnzyme");
        listD.add("wid");
        listD.add("g.drugBankAsTransporters");
        listD.add("wid");
        listD.add("g.drugBankAsCarriers");
        listD.add("wid");
        data.put(DrugBank.class, listD);

        List listE = new ArrayList();
        listE.add("g.kEGGEnzymes");
        listE.add("wid");
        data.put(KEGGEnzyme.class, listE);

        List listP = new ArrayList();
        listP.add("g.kEGGPathways");
        listP.add("wid");
        data.put(KEGGPathway.class, listP);

        List listPfS = new ArrayList();
        listPfS.add("g.pfamASignificant");
        listPfS.add("wid");
        listPfS.add("g.pfamAInsignificant");
        listPfS.add("wid");
        data.put(PfamAbioWH.class, listPfS);
        
        List listPI = new ArrayList();
        listPI.add("g.pIRSFhasProtein");
        listPI.add("pIRSFhasProteinPK.pirsfWid");
        data.put(Pirsf.class, listPI);

        return data;
    }

    @Override
    public String getMainField() {
        return NAME;
    }
}
