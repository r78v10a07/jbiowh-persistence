package org.jbiowhpersistence.datasets.protein.util;

import org.jbiowhcore.utility.graph.HideWeightedEdge;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.graph.JBioWHGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

/**
 * This class is creates a Protein-protein interaction graph
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Mar 28, 2013
 */
public class ProteinProteinGraph extends JBioWHGraph {

    /**
     * Creates a ProteinProteinGraph object where the graph object is a weighted
     * graph. The weights represents how many times is present the interaction
     * into the MIF25 data sources.
     *
     * The graph uses the class HideWeightedEdge as edge.
     *
     * @param depth the recursivity depth limit
     * @see HideWeightedEdge
     */
    public ProteinProteinGraph(int depth) {
        super(depth, new ListenableUndirectedWeightedGraph(HideWeightedEdge.class));
    }

    /**
     * The recursive function to populate the graph from Protein entity. The
     * MIFEntryInteraction entity is used as edge.
     *
     * The method creates a weighted graph of protein-protein interaction where
     * the vertex are proteins and the edges are the interaction published by
     * the MIF25 data sources.
     *
     * The weights represents how how many times is present the interaction into
     * the MIF25 data sources.
     *
     * @param o the Protein entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    @Override
    protected void populate(Protein o, int varDepth) {
        for (MIFEntryInteraction i : o.getmIFEntryInteraction()) {
            populateCollection(i.getProtein(), o, varDepth, i);
        }
    }
}
