package org.jbiowhpersistence.datasets.gene.genome.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the GenePTT module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-11-08 14:37:19 +0100
 * (Thu, 08 Nov 2012) $ $LastChangedRevision: 591 $
 * @since Jul 23, 2012
 */
public class SearchGenePTT extends SearchFactory {

    public final String PTO = "pTo";
    public final String PFROM = "pFrom";
    public final String STRAND = "Strand";
    public final String PLENGTH = "PLength";
    public final String PROTEINGI = "ProteinGi";
    public final String GENESYMBOL = "GeneSymbol";
    public final String GENELOCUSTAG = "GeneLocusTag";
    public final String CODE = "Code";
    public final String COG = "COG";
    public final String PRODUCT = "Product";
    public final String PTTFILE = "PTTFile";

    /**
     * Creates the GenePTT search object
     */
    public SearchGenePTT() {
        HashMap<String, Class> fields = new HashMap<>();
        fields.put(PTO, Long.class);
        fields.put(PFROM, String.class);
        fields.put(STRAND, String.class);
        fields.put(PLENGTH, String.class);
        fields.put(PROTEINGI, Long.class);
        fields.put(GENESYMBOL, Long.class);
        fields.put(GENELOCUSTAG, String.class);
        fields.put(CODE, String.class);
        fields.put(COG, String.class);
        fields.put(PRODUCT, String.class);
        fields.put(PTTFILE, String.class);
        setFields(fields);
    }

    @Override
    public String getMainField() {
        return GENESYMBOL;
    }

    @Override
    public List search(String search, JPLConstrains constrains) throws SQLException {
        List searchList = new ArrayList();
        List searchRow = new ArrayList();

        if (search == null || search.isEmpty()) {
            return search(searchList, constrains);
        }

        searchRow.add("");
        searchRow.add(GENESYMBOL);
        searchRow.add("like");
        searchRow.add(search);
        searchList.add(searchRow);
        return search(searchList, constrains);
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return GenePTT.class;
    }

    @Override
    protected String getFieldId() {
        return "proteinGi";
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
                case PTO:
                    data.put(field, "g.pTo");
                    break;
                case PFROM:
                    data.put(field, "g.pFrom");
                    break;
                case STRAND:
                    data.put(field, "g.strand");
                    break;
                case PLENGTH:
                    data.put(field, "g.pLength");
                    break;
                case PROTEINGI:
                    data.put(field, "g.proteinGi");
                    break;
                case GENESYMBOL:
                    data.put(field, "g.geneSymbol");
                    break;
                case GENELOCUSTAG:
                    data.put(field, "g.geneLocusTag");
                    break;
                case COG:
                    data.put(field, "g.cog");
                    break;
                case PRODUCT:
                    data.put(field, "g.product");
                    break;
                case PTTFILE:
                    data.put(field, "g.pTTFile");
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
