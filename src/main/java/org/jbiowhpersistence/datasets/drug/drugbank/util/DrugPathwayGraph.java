package org.jbiowhpersistence.datasets.drug.drugbank.util;

import org.jbiowhcore.utility.graph.HideWeightedEdge;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.graph.JBioWHGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

/**
 * This class creates a Drug-Pathway graph
 *
 * $Author$ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision$
 *
 * @since Apr 11, 2013
 */
public class DrugPathwayGraph extends JBioWHGraph {

    public DrugPathwayGraph(int depth) {
        super(depth, new ListenableUndirectedWeightedGraph(HideWeightedEdge.class));
    }

    /**
     * The recursive function to populate the graph from DrugBank entity. The
     * Protein entity is used as edge between drugs and pathways.
     *
     * The method creates a weighted graph where the vertex are drugs or
     * pathways and the edges are proteins
     *
     *
     * @param o the DrugBank entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    @Override
    protected void populate(DrugBank o, int varDepth) {
        for (Protein p : o.getProtein()) {
            for (GeneInfo g : p.getGeneInfo()) {
                populateCollection(g.getkEGGPathways(), o, varDepth);
            }
        }
    }

    /**
     * The recursive function to populate the graph from KEGGPathway entity. The
     * Protein entity is used as edge.
     *
     * The method creates a weighted graph where the vertex are drugs or
     * pathways and the edges are proteins
     *
     * @param o the DrugBank entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    @Override
    protected void populate(KEGGPathway o, int varDepth) {
        for (GeneInfo g : o.getGeneInfo()) {
            for (Protein p : g.getProtein()) {
                populateCollection(p.getDrugBank(), o, varDepth);
            }
        }
    }
}
