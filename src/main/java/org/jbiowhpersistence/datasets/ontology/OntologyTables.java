package org.jbiowhpersistence.datasets.ontology;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the Ontology Table list
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 27, 2011
 */
public class OntologyTables {
    /*
     * List of tables names for the Taxonomy module
     */

    public final String ONTOLOGY = "Ontology";
    public final String ONTOLOGY_HAS_ONTOLOGYSUBSET = "Ontology_has_OntologySubset";
    public final String ONTOLOGY_HAS_ONTOLOGYSYNONYM = "Ontology_has_OntologySynonym";
    public final String ONTOLOGY_HAS_ONTOLOGYXREF = "Ontology_has_OntologyXRef";
    public final String ONTOLOGYALTERNATIVEID = "OntologyAlternativeId";
    public final String ONTOLOGYISA = "OntologyIsA";
    public final String ONTOLOGYPMID = "OntologyPMID";
    public final String ONTOLOGYRELATION = "OntologyRelation";
    public final String ONTOLOGYSUBSET = "OntologySubset";
    public final String ONTOLOGYSYNONYM = "OntologySynonym";
    public final String ONTOLOGYTOCONSIDER = "OntologyToConsider";
    public final String ONTOLOGYWIDISATEMP = "OntologyWIDIsATemp";
    public final String ONTOLOGYWIDONTOLOGYSUBSET = "OntologyWIDOntologySubset";
    public final String ONTOLOGYWIDRELATIONTEMP = "OntologyWIDRelationTemp";
    public final String ONTOLOGYWIDSYNONYMTEMP = "OntologyWIDSynonymTemp";
    public final String ONTOLOGYWIDTOCONSIDERTEMP = "OntologyWIDToConsiderTemp";
    public final String ONTOLOGYWIDXREFTEMP = "OntologyWIDXRefTemp";
    public final String ONTOLOGYXREF = "OntologyXRef";
    private static OntologyTables singleton;
    private static List<String> tables;

    private OntologyTables() {
    }

    /**
     * Return a OntologyTables
     *
     * @return a OntologyTables
     */
    public static synchronized OntologyTables getInstance() {
        if (singleton == null) {
            singleton = new OntologyTables();
        }
        return singleton;
    }

    /**
     * Return the Map object with the tables names and TSV files names
     *
     * @return
     */
    public List<String> getTables() {
        if (tables == null) {
            tables = new ArrayList<>();
            tables.add(ONTOLOGY);
            tables.add(ONTOLOGY_HAS_ONTOLOGYSUBSET);
            tables.add(ONTOLOGY_HAS_ONTOLOGYSYNONYM);
            tables.add(ONTOLOGY_HAS_ONTOLOGYXREF);
            tables.add(ONTOLOGYALTERNATIVEID);
            tables.add(ONTOLOGYISA);
            tables.add(ONTOLOGYPMID);
            tables.add(ONTOLOGYRELATION);
            tables.add(ONTOLOGYSUBSET);
            tables.add(ONTOLOGYSYNONYM);
            tables.add(ONTOLOGYTOCONSIDER);
            tables.add(ONTOLOGYWIDISATEMP);
            tables.add(ONTOLOGYWIDONTOLOGYSUBSET);
            tables.add(ONTOLOGYWIDRELATIONTEMP);
            tables.add(ONTOLOGYWIDSYNONYMTEMP);
            tables.add(ONTOLOGYWIDTOCONSIDERTEMP);
            tables.add(ONTOLOGYWIDXREFTEMP);
            tables.add(ONTOLOGYXREF);
        }
        return tables;
    }
}
