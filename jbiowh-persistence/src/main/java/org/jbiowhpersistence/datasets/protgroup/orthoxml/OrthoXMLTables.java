package org.jbiowhpersistence.datasets.protgroup.orthoxml;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
public class OrthoXMLTables {

    public final String ORTHOXML = "OrthoXML";
    public final String ORTHOXMLSPECIE = "OrthoXMLSpecie";
    public final String ORTHOXMLSPECIESDATABASE = "OrthoXMLSpeciesDatabase";
    public static final String ORTHOXMLSPECIESDATABASEGENE = "OrthoXMLSpeciesDatabaseGene";
    public static final String ORTHOXMLSCORE = "OrthoXMLScore";
    public static final String ORTHOXMLGROUPPROPERTY = "OrthoXMLGroupProperty";
    public static final String ORTHOXMLGROUPSCORE = "OrthoXMLGroupScore";
    public final String ORTHOXMLGROUP = "OrthoXMLGroup";
    public final String ORTHOXMLGROUPGENEREF = "OrthoXMLGroupGeneRef";
    public static final String ORTHOXMLGROUPGENEREFSCORE = "OrthoXMLGroupGeneRefScore";
    private static OrthoXMLTables singleton;
    private static List<String> tables;

    private OrthoXMLTables() {
    }

    /**
     * Return a OrthoXMLTables instance
     *
     * @return a OrthoXMLTables instance
     */
    public static synchronized OrthoXMLTables getInstance() {
        if (singleton == null) {
            singleton = new OrthoXMLTables();
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
            tables = new ArrayList();
            tables.add(ORTHOXML);
            tables.add(ORTHOXMLSPECIE);
            tables.add(ORTHOXMLSPECIESDATABASE);
            tables.add(ORTHOXMLSPECIESDATABASEGENE);
            tables.add(ORTHOXMLSCORE);
            tables.add(ORTHOXMLGROUPPROPERTY);
            tables.add(ORTHOXMLGROUPSCORE);
            tables.add(ORTHOXMLGROUP);
            tables.add(ORTHOXMLGROUPGENEREF);
            tables.add(ORTHOXMLGROUPGENEREFSCORE);
        }
        return tables;
    }
}
