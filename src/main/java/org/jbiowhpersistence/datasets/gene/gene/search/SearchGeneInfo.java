package org.jbiowhpersistence.datasets.gene.gene.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.search.JBioWHSearch;
import org.jbiowhpersistence.utils.search.SearchFactory;

/**
 * This Class perform the search over the Gene module
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-09-25 00:06:19 +0200
 * (Tue, 25 Sep 2012) $ $LastChangedRevision: 591 $
 *
 * @since Jul 27, 2011
 */
public class SearchGeneInfo extends SearchFactory implements JBioWHSearch {

    public final String GENEID = "GeneId";
    public final String PROTEINGI = "ProteinGi";
    public final String RNANUCLEOTIDEGI = "RNANucleotideGi";
    public final String GENOMICNUCLEOTIDEGI = "GenomicNucleotideGi";
    public final String SYMBOL = "Symbol";
    public final String LOCUSTAG = "LocusTag";
    public final String DESCRIPTION = "Description";
    public final String SYNONYM = "Synonym";
    public final String GENOMICNUCLEOTIDEACCESSION = "GenomicNucleotideAccession";
    public final String PROTEINACCESSION = "ProteinAccession";
    public final String RNANUCLEOTIDEACCESSION = "RNANucleotideAccession";

    /**
     * Create the Gene search object
     */
    public SearchGeneInfo() {
        HashMap<String, Class> fields = new HashMap();
        fields.put(GENEID, Long.class);
        fields.put(PROTEINGI, Long.class);
        fields.put(GENOMICNUCLEOTIDEACCESSION, String.class);
        fields.put(RNANUCLEOTIDEGI, Long.class);
        fields.put(PROTEINACCESSION, String.class);
        fields.put(GENOMICNUCLEOTIDEGI, Long.class);
        fields.put(RNANUCLEOTIDEACCESSION, String.class);
        fields.put(SYMBOL, String.class);
        fields.put(LOCUSTAG, String.class);
        fields.put(DESCRIPTION, String.class);
        fields.put(SYNONYM, String.class);
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
        if (BioWHPattern.getInstance().isLong(search)) {
            searchRow.add(GENEID);
            searchRow.add("=");
            searchRow.add(search);
            searchList.add(searchRow);
            List result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }
            
            searchRow.clear();
            searchRow.add("");
            searchRow.add(PROTEINGI);
            searchRow.add("=");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }

            searchRow.clear();
            searchRow.add("");
            searchRow.add(RNANUCLEOTIDEGI);
            searchRow.add("=");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }

            searchRow.clear();
            searchRow.add("");
            searchRow.add(GENOMICNUCLEOTIDEGI);
            searchRow.add("=");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            return search(searchList, constrains);
        } else {
            searchRow.add(SYMBOL);
            searchRow.add("like");
            searchRow.add(search);
            searchList.add(searchRow);
            List result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }

            searchRow.clear();
            searchRow.add("");
            searchRow.add(LOCUSTAG);
            searchRow.add("like");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }
            
            searchRow.clear();
            searchRow.add("");
            searchRow.add(PROTEINACCESSION);
            searchRow.add("like");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }
            
            searchRow.clear();
            searchRow.add("");
            searchRow.add(GENOMICNUCLEOTIDEACCESSION);
            searchRow.add("like");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            result = search(searchList, constrains);
            if (!result.isEmpty()) {
                return result;
            }
            
            searchRow.clear();
            searchRow.add("");
            searchRow.add(RNANUCLEOTIDEACCESSION);
            searchRow.add("like");
            searchRow.add(search);
            searchList.clear();
            searchList.add(searchRow);
            return search(searchList, constrains);
        }
    }

    @Override
    protected Class getSearchFactoryEntity() {
        return GeneInfo.class;
    }

    @Override
    protected HashMap<String, String> getFieldBeforeWhere() {
        HashMap<String, String> data = new HashMap();
        HashMap<String, String> fieldOnEntity = getFieldOnEntity();
        for (String field : getFieldsSet()) {
            if (field.equals(SYNONYM)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " s ");
            } else if (field.equals(PROTEINGI)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " pg ");
            } else if (field.equals(RNANUCLEOTIDEGI)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " rg ");
            } else if (field.equals(GENOMICNUCLEOTIDEGI)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " gg ");
            } else if (field.equals(GENOMICNUCLEOTIDEACCESSION)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " ga ");
            } else if (field.equals(PROTEINACCESSION)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " pa ");
            } else if (field.equals(RNANUCLEOTIDEACCESSION)) {
                data.put(field, " INNER JOIN " + fieldOnEntity.get(field) + " ra ");
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
            if (field.equals(GENEID)) {
                data.put(field, "g.geneID");
            } else if (field.equals(SYMBOL)) {
                data.put(field, "g.symbol");
            } else if (field.equals(LOCUSTAG)) {
                data.put(field, "g.locusTag");
            } else if (field.equals(DESCRIPTION)) {
                data.put(field, "g.description");
            } else if (field.equals(SYNONYM)) {
                data.put(field, "s.synonyms");
            } else if (field.equals(PROTEINGI)) {
                data.put(field, "pg.proteinGi");
            } else if (field.equals(PROTEINACCESSION)) {
                data.put(field, "pa.proteinAccession");
            } else if (field.equals(RNANUCLEOTIDEGI)) {
                data.put(field, "rg.rNANucleotideGi");
            } else if (field.equals(RNANUCLEOTIDEACCESSION)) {
                data.put(field, "ra.rNANucleotideAccession");
            } else if (field.equals(GENOMICNUCLEOTIDEGI)) {
                data.put(field, "gg.genomicNucleotideGi");
            } else if (field.equals(GENOMICNUCLEOTIDEACCESSION)) {
                data.put(field, "ga.genomicNucleotideAccession");
            }
        }
        return data;
    }

    @Override
    protected HashMap<String, String> getFieldOnEntity() {
        HashMap<String, String> data = new HashMap();
        for (String field : getFieldsSet()) {
            if (field.equals(SYNONYM)) {
                data.put(field, "g.geneInfoSynonym");
            } else if (field.equals(PROTEINGI) || field.equals(PROTEINACCESSION)) {
                data.put(field, "g.gene2ProteinAccession");
            } else if (field.equals(RNANUCLEOTIDEGI) || field.equals(RNANUCLEOTIDEACCESSION)) {
                data.put(field, "g.gene2RNANucleotide");
            } else if (field.equals(GENOMICNUCLEOTIDEGI) || field.equals(GENOMICNUCLEOTIDEACCESSION)) {
                data.put(field, "g.gene2GenomicNucleotide");
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
        listT.add("g.taxonomy");
        listT.add("wid");
        data.put(Taxonomy.class, listT);

        List listO = new ArrayList();
        listO.add("g.ontology");
        listO.add("wid");
        data.put(Ontology.class, listO);

        List listP = new ArrayList();
        listP.add("g.protein");
        listP.add("wid");
        data.put(Protein.class, listP);

        List listK = new ArrayList();
        listK.add("g.kEGGGenes");
        listK.add("wid");
        data.put(KEGGGene.class, listK);

        List listKP = new ArrayList();
        listKP.add("g.kEGGPathways");
        listKP.add("wid");
        data.put(KEGGPathway.class, listKP);

        List listGP = new ArrayList();
        listGP.add("g.genePTT");
        listGP.add("proteinGi");
        data.put(GenePTT.class, listGP);

        List listOM = new ArrayList();
        listOM.add("g.omim");
        listOM.add("wid");
        data.put(OMIM.class, listOM);

        return data;
    }

    @Override
    public String getMainField() {
        return GENEID;
    }
}
