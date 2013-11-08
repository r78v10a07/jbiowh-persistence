package org.jbiowhpersistence.datasets.disease.omim;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the OMIM tables
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 12, 2012
 */
public class OMIMTables {

    private static OMIMTables omimTables;
    private static List<String> tables;
    public final String OMIM = "OMIM";
    public final String OMIMAV = "OMIMAV";
    public final String OMIMCN = "OMIMCN";
    public final String OMIMCS = "OMIMCS";
    public final String OMIMCSDATA = "OMIMCSData";
    public final String OMIMED = "OMIMED";
    public final String OMIMCD = "OMIMCD";
    public final String OMIMRF = "OMIMRF";
    public final String OMIMSA = "OMIMSA";
    public final String OMIMTI = "OMIMTI";
    public final String OMIMTX = "OMIMTX";
    public final String OMIMGENEMAP = "OMIMGeneMap";
    public static final String OMIMGENEMAP_HAS_GENESYMBOL = "OMIMGeneMap_has_GeneSymbol";
    public final String OMIMGENEMAP_HAS_GENESYMBOLTEMP = "OMIMGeneMap_has_GeneSymbolTemp";
    public static final String OMIMGENEMAP_HAS_OMIMMETHOD = "OMIMGeneMap_has_OMIMMethod";
    public static final String OMIMGENEMAP_HAS_OMIMMORBIDMAP = "OMIMGeneMap_has_OMIMMorbidMap";
    public final String OMIMGENEMAPTEMP = "OMIMGeneMapTemp";
    public final String OMIMMETHOD = "OMIMMethod";
    public final String OMIMMETHODTEMP = "OMIMMethodTemp";
    public final String OMIMMORBIDMAP = "OMIMMorbidMap";
    public static final String OMIMMORBIDMAP_HAS_GENESYMBOL = "OMIMMorbidMap_has_GeneSymbol";
    public final String OMIMMORBIDMAP_HAS_GENESYMBOLTEMP = "OMIMMorbidMap_has_GeneSymbolTemp";
    public final String GENEMAPFILE = "genemap";
    public final String MORBIDMAPFILE = "morbidmap";
    public final String OMIMFILE = "omim.txt";

    private OMIMTables() {
    }

    /**
     * Get the OMIM tables instance
     *
     * @return the OMIM tables instance
     */
    public static synchronized OMIMTables getInstance() {
        if (omimTables == null) {
            omimTables = new OMIMTables();
        }
        return omimTables;
    }

    /**
     * Return the OMIM's tables names in a list
     *
     * @return the OMIM's tables names in a list
     */
    public List<String> getTables() {
        if (tables == null) {
            tables = new ArrayList();

            tables.add(OMIM);
            tables.add(OMIMAV);
            tables.add(OMIMCN);
            tables.add(OMIMCS);
            tables.add(OMIMCSDATA);
            tables.add(OMIMED);
            tables.add(OMIMCD);
            tables.add(OMIMRF);
            tables.add(OMIMSA);
            tables.add(OMIMTI);
            tables.add(OMIMTX);
            tables.add(OMIMGENEMAP);
            tables.add(OMIMGENEMAP_HAS_GENESYMBOL);
            tables.add(OMIMGENEMAP_HAS_GENESYMBOLTEMP);
            tables.add(OMIMGENEMAP_HAS_OMIMMETHOD);
            tables.add(OMIMGENEMAP_HAS_OMIMMORBIDMAP);
            tables.add(OMIMGENEMAPTEMP);
            tables.add(OMIMMETHOD);
            tables.add(OMIMMETHODTEMP);
            tables.add(OMIMMORBIDMAP);
            tables.add(OMIMMORBIDMAP_HAS_GENESYMBOL);
            tables.add(OMIMMORBIDMAP_HAS_GENESYMBOLTEMP);
        }

        return tables;
    }
}
