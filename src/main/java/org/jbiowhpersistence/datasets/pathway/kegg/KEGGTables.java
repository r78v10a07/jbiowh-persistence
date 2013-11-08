package org.jbiowhpersistence.datasets.pathway.kegg;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the KEGG tables
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 30, 2011
 */
public class KEGGTables {

    public final String KEGGCOMPOUND = "KEGGCompound";
    public final String KEGGCOMPOUNDENZYME = "KEGGCompoundEnzyme";
    public final String KEGGCOMPOUNDNAME = "KEGGCompoundName";
    public final String KEGGCOMPOUNDPATHWAY = "KEGGCompoundPathway";
    public final String KEGGCOMPOUNDREACTION = "KEGGCompoundReaction";
    public final String KEGGCOMPOUNDDBLINK = "KEGGCompoundDBLink";
    public static final String KEGGCOMPOUND_HAS_KEGGPATHWAY = "KEGGCompound_has_KEGGPathway";
    public static final String KEGGCOMPOUND_HAS_DRUGBANK = "KEGGCompound_has_DrugBank";
    public final String KEGGENZYME = "KEGGEnzyme";
    public static final String KEGGENZYME_HAS_KEGGCOMPOUND_AS_COFACTOR = "KEGGEnzyme_has_KEGGCompound_as_Cofactor";
    public static final String KEGGENZYME_HAS_KEGGCOMPOUND_AS_INHIBITOR = "KEGGEnzyme_has_KEGGCompound_as_Inhibitor";
    public static final String KEGGENZYME_HAS_KEGGCOMPOUND_AS_EFFECTOR = "KEGGEnzyme_has_KEGGCompound_as_Effector";
    public static final String KEGGENZYME_HAS_KEGGENZYMECLASS = "KEGGEnzyme_has_KEGGEnzymeClass";
    public static final String KEGGENZYME_HAS_KEGGPATHWAY = "KEGGEnzyme_has_KEGGPathway";
    public final String KEGGENZYMEALLREAC = "KEGGEnzymeAllReac";
    public final String KEGGENZYMECLASS = "KEGGEnzymeClass";
    public final String KEGGENZYMECLASSTEMP = "KEGGEnzymeClassTemp";
    public final String KEGGENZYMECOFACTOR = "KEGGEnzymeCofactor";
    public final String KEGGENZYMEDBLINK = "KEGGEnzymeDBLink";
    public final String KEGGENZYMEEFFECTOR = "KEGGEnzymeEffector";
    public final String KEGGENZYMEGENES = "KEGGEnzymeGenes";
    public final String KEGGENZYMEINHIBITOR = "KEGGEnzymeInhibitor";
    public final String KEGGENZYMENAME = "KEGGEnzymeName";
    public final String KEGGENZYMEORTHOLOGY = "KEGGEnzymeOrthology";
    public final String KEGGENZYMEPATHWAY = "KEGGEnzymePathway";
    public final String KEGGENZYMEPRODUCT = "KEGGEnzymeProduct";
    public final String KEGGENZYMEREACTION = "KEGGEnzymeReaction";
    public final String KEGGENZYMESUBSTRATE = "KEGGEnzymeSubstrate";
    public final String KEGGENZYMESYSNAME = "KEGGEnzymeSysName";
    public final String KEGGREACTION = "KEGGReaction";
    public static final String KEGGREACTION_HAS_KEGGCOMPOUND_AS_PRODUCT = "KEGGReaction_has_KEGGCompound_as_Product";
    public static final String KEGGREACTION_HAS_KEGGCOMPOUND_AS_SUBSTRATE = "KEGGReaction_has_KEGGCompound_as_Substrate";
    public static final String KEGGREACTION_HAS_KEGGGLYCAN_AS_PRODUCT = "KEGGReaction_has_KEGGGlycan_as_Product";
    public static final String KEGGREACTION_HAS_KEGGGLYCAN_AS_SUBSTRATE = "KEGGReaction_has_KEGGGlycan_as_Substrate";
    public static final String KEGGREACTION_HAS_KEGGENZYME = "KEGGReaction_has_KEGGEnzyme";
    public static final String KEGGREACTION_HAS_KEGGPATHWAY = "KEGGReaction_has_KEGGPathway";
    public final String KEGGREACTIONENZYME = "KEGGReactionEnzyme";
    public final String KEGGREACTIONNAME = "KEGGReactionName";
    public final String KEGGREACTIONORTHOLOGY = "KEGGReactionOrthology";
    public final String KEGGREACTIONPATHWAY = "KEGGReactionPathway";
    public final String KEGGREACTIONRPAIR = "KEGGReactionRPair";
    public final String KEGGREACTIONPRODUCT = "KEGGReactionProduct";
    public final String KEGGREACTIONSUBSTRATE = "KEGGReactionSubstrate";
    public final String KEGGGLYCAN = "KEGGGlycan";
    public final String KEGGGLYCANNAME = "KEGGGlycanName";
    public static final String KEGGGLYCAN_HAS_KEGGGLYCANCLASS = "KEGGGlycan_has_KEGGGlycanClass";
    public final String KEGGGLYCANCLASS = "KEGGGlycanClass";
    public final String KEGGGLYCANCLASSTEMP = "KEGGGlycanClassTemp";
    public final String KEGGGLYCANDBLINK = "KEGGGlycanDBLink";
    public final String KEGGGLYCANENZYME = "KEGGGlycanEnzyme";
    public final String KEGGGLYCANORTHOLOGY = "KEGGGlycanOrthology";
    public final String KEGGGLYCANPATHWAY = "KEGGGlycanPathway";
    public final String KEGGGLYCANREACTION = "KEGGGlycanReaction";
    public final String KEGGRPAIR = "KEGGRPair";
    public static final String KEGGRPAIR_HAS_KEGGCOMPOUND = "KEGGRPair_has_KEGGCompound";
    public final String KEGGRPAIRCOMPOUND = "KEGGRPairCompound";
    public final String KEGGRPAIRENZYME = "KEGGRPairEnzyme";
    public final String KEGGRPAIRREACTION = "KEGGRPairReaction";
    public final String KEGGRPAIRRELATEDPAIR = "KEGGRPairRelatedPair";
    public final String KEGGRPAIRRELATEDPAIRTEMP = "KEGGRPairRelatedPairTemp";
    public final String KEGGGENE = "KEGGGene";
    public final String KEGGGENEDBLINK = "KEGGGeneDBLink";
    public final String KEGGGENEDISEASE = "KEGGGeneDisease";
    public final String KEGGGENEDRUGTARGET = "KEGGGeneDrugTarget";
    public final String KEGGGENENAME = "KEGGGeneName";
    public final String KEGGGENEORTHOLOGY = "KEGGGeneOrthology";
    public final String KEGGGENEPATHWAY = "KEGGGenePathway";
    public static final String KEGGGENE_HAS_KEGGPATHWAY = "KEGGGene_has_KEGGPathway";
    public final String KEGGPATHWAY = "KEGGPathway";
    public final String KEGGPATHWAYENTRY = "KEGGPathwayEntry";
    public final String KEGGPATHWAYENTRYGRAPHIC = "KEGGPathwayEntryGraphic";
    public final String KEGGPATHWAYREACTION = "KEGGPathwayReaction";
    public final String KEGGPATHWAYREACTIONPRODUCT = "KEGGPathwayReactionProduct";
    public final String KEGGPATHWAYREACTIONSUBSTRATE = "KEGGPathwayReactionSubstrate";
    public final String KEGGPATHWAYRELATION = "KEGGPathwayRelation";
    public final String KEGGPATHWAYRELATIONSUBTYPE = "KEGGPathwayRelationSubType";
    public final String KEGGPATHWAYENTRYCOMPOUND = "KEGGPathwayEntryCompound";
    public final String KEGGPATHWAYENTRYENZYME = "KEGGPathwayEntryEnzyme";
    public final String KEGGPATHWAYENTRYGENE = "KEGGPathwayEntryGene";
    public final String KEGGPATHWAYENTRYORTHOLOGY = "KEGGPathwayEntryOrthology";
    public final String KEGGPATHWAYENTRYREACTION = "KEGGPathwayEntryReaction";
    public static final String KEGGPATHWAY_HAS_TAXONOMY = "KEGGPathway_has_Taxonomy";
    public static final String KEGGPATHWAY_HAS_PROTEN = "KEGGPathway_has_Protein";
    public static final String KEGGPATHWAY_HAS_GENEINFO = "KEGGPathway_has_GeneInfo";
    private static KEGGTables singleton;
    private static List<String> tables;

    private KEGGTables() {
    }

    /**
     * Return a
     *
     * @return a
     */
    public static synchronized KEGGTables getInstance() {
        if (singleton == null) {
            singleton = new KEGGTables();
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

            tables.add(KEGGCOMPOUND);
            tables.add(KEGGCOMPOUNDENZYME);
            tables.add(KEGGCOMPOUNDNAME);
            tables.add(KEGGCOMPOUNDPATHWAY);
            tables.add(KEGGCOMPOUNDREACTION);
            tables.add(KEGGCOMPOUNDDBLINK);
            tables.add(KEGGCOMPOUND_HAS_KEGGPATHWAY);
            tables.add(KEGGCOMPOUND_HAS_DRUGBANK);
            tables.add(KEGGENZYME);
            tables.add(KEGGENZYME_HAS_KEGGCOMPOUND_AS_COFACTOR);
            tables.add(KEGGENZYME_HAS_KEGGCOMPOUND_AS_INHIBITOR);
            tables.add(KEGGENZYME_HAS_KEGGCOMPOUND_AS_EFFECTOR);
            tables.add(KEGGENZYME_HAS_KEGGENZYMECLASS);
            tables.add(KEGGENZYME_HAS_KEGGPATHWAY);
            tables.add(KEGGENZYMEALLREAC);
            tables.add(KEGGENZYMECLASS);
            tables.add(KEGGENZYMECLASSTEMP);
            tables.add(KEGGENZYMECOFACTOR);
            tables.add(KEGGENZYMEDBLINK);
            tables.add(KEGGENZYMEEFFECTOR);
            tables.add(KEGGENZYMEGENES);
            tables.add(KEGGENZYMEINHIBITOR);
            tables.add(KEGGENZYMENAME);
            tables.add(KEGGENZYMEORTHOLOGY);
            tables.add(KEGGENZYMEPATHWAY);
            tables.add(KEGGENZYMEPRODUCT);
            tables.add(KEGGENZYMEREACTION);
            tables.add(KEGGENZYMESUBSTRATE);
            tables.add(KEGGENZYMESYSNAME);
            tables.add(KEGGREACTION);
            tables.add(KEGGREACTION_HAS_KEGGCOMPOUND_AS_PRODUCT);
            tables.add(KEGGREACTION_HAS_KEGGCOMPOUND_AS_SUBSTRATE);
            tables.add(KEGGREACTION_HAS_KEGGGLYCAN_AS_PRODUCT);
            tables.add(KEGGREACTION_HAS_KEGGGLYCAN_AS_SUBSTRATE);
            tables.add(KEGGREACTION_HAS_KEGGENZYME);
            tables.add(KEGGREACTION_HAS_KEGGPATHWAY);
            tables.add(KEGGREACTIONENZYME);
            tables.add(KEGGREACTIONNAME);
            tables.add(KEGGREACTIONORTHOLOGY);
            tables.add(KEGGREACTIONPATHWAY);
            tables.add(KEGGREACTIONRPAIR);
            tables.add(KEGGREACTIONPRODUCT);
            tables.add(KEGGREACTIONSUBSTRATE);
            tables.add(KEGGGLYCAN);
            tables.add(KEGGGLYCANNAME);
            tables.add(KEGGGLYCAN_HAS_KEGGGLYCANCLASS);
            tables.add(KEGGGLYCANCLASS);
            tables.add(KEGGGLYCANCLASSTEMP);
            tables.add(KEGGGLYCANDBLINK);
            tables.add(KEGGGLYCANENZYME);
            tables.add(KEGGGLYCANORTHOLOGY);
            tables.add(KEGGGLYCANPATHWAY);
            tables.add(KEGGGLYCANREACTION);
            tables.add(KEGGRPAIR);
            tables.add(KEGGRPAIR_HAS_KEGGCOMPOUND);
            tables.add(KEGGRPAIRCOMPOUND);
            tables.add(KEGGRPAIRENZYME);
            tables.add(KEGGRPAIRREACTION);
            tables.add(KEGGRPAIRRELATEDPAIR);
            tables.add(KEGGRPAIRRELATEDPAIRTEMP);
            tables.add(KEGGGENE);
            tables.add(KEGGGENEDBLINK);
            tables.add(KEGGGENEDISEASE);
            tables.add(KEGGGENEDRUGTARGET);
            tables.add(KEGGGENENAME);
            tables.add(KEGGGENEORTHOLOGY);
            tables.add(KEGGGENEPATHWAY);
            tables.add(KEGGGENE_HAS_KEGGPATHWAY);
            tables.add(KEGGPATHWAY);
            tables.add(KEGGPATHWAYENTRY);
            tables.add(KEGGPATHWAYENTRYGRAPHIC);
            tables.add(KEGGPATHWAYREACTION);
            tables.add(KEGGPATHWAYREACTIONPRODUCT);
            tables.add(KEGGPATHWAYREACTIONSUBSTRATE);
            tables.add(KEGGPATHWAYRELATION);
            tables.add(KEGGPATHWAYRELATIONSUBTYPE);
            tables.add(KEGGPATHWAYENTRYCOMPOUND);
            tables.add(KEGGPATHWAYENTRYENZYME);
            tables.add(KEGGPATHWAYENTRYGENE);
            tables.add(KEGGPATHWAYENTRYORTHOLOGY);
            tables.add(KEGGPATHWAYENTRYREACTION);
            tables.add(KEGGPATHWAY_HAS_TAXONOMY);
            tables.add(KEGGPATHWAY_HAS_PROTEN);
            tables.add(KEGGPATHWAY_HAS_GENEINFO);
        }
        return tables;
    }
}
