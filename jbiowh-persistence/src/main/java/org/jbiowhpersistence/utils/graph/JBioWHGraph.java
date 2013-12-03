package org.jbiowhpersistence.utils.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.graph.HideEdge;
import org.jbiowhcore.utility.graph.HideWeightedEdge;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGRPair;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySet;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMember;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.entitymanager.EntityParserFieldProxy;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

/**
 * This class creates a graph from any JBioWH main entity
 *
 * $Author$ $LastChangedDate: 2013-03-27 15:25:50 +0100
 * (Wed, 27 Mar 2013) $ $LastChangedRevision$
 *
 * @since Mar 25, 2013
 */
public class JBioWHGraph {

    /**
     * The recursivity depth limit
     */
    protected int depth;
    /**
     * The graph object
     */
    protected ListenableGraph graph;

    /**
     * Creates a JBioWHGraph
     *
     * The depth is set to 0 and the Graph is created as an undirected graph
     */
    public JBioWHGraph() {
        depth = 0;
        graph = new ListenableUndirectedGraph(HideEdge.class);
    }

    /**
     * Creates a JBioWHGraph
     *
     * @param graph the graph to be handled
     */
    public JBioWHGraph(ListenableGraph graph) {
        this.graph = graph;
    }

    /**
     * Creates a JBioWHGraph
     *
     * The Graph is created as an undirected graph
     *
     * @param depth the recursivity depth limit
     */
    public JBioWHGraph(int depth) {
        this.depth = depth;
        graph = new ListenableUndirectedGraph(HideEdge.class);
    }

    /**
     * Creates a JBioWHGraph
     *
     * @param depth the recursivity depth limit
     * @param graph the graph to be handled
     */
    public JBioWHGraph(int depth, ListenableGraph graph) {
        this.depth = depth;
        this.graph = graph;
    }

    /**
     * Get the T tree in a graph object
     *
     * @return the T tree in a graph object
     */
    public ListenableGraph getGraph() {
        return graph;
    }

    /**
     * Get the graph vertex in a collection object
     *
     * @return the graph vertex in a collection object
     */
    public Collection vertexSet() {
        if (graph != null) {
            return graph.vertexSet();
        }
        return new HashSet();
    }

    /**
     * Count the graph vertex
     *
     * @return the graph vertex number
     */
    public int vertexCount() {
        if (graph != null) {
            return vertexSet().size();
        }
        return 0;
    }

    /**
     * Count the graph edges
     *
     * @return the graph edges number
     */
    public int edgeCount() {
        if (graph != null) {
            return graph.edgeSet().size();
        }
        return 0;
    }

    /**
     * Return true if the graph contains the object as vertex
     *
     * @param obj the object to be checked as a graph vertex
     * @return true if the graph contains the object as vertex
     */
    public boolean containsVertex(Object obj) {
        if (graph != null) {
            return graph.containsVertex(obj);
        }
        return false;
    }

    /**
     * Return true if the graph is connected
     *
     * @return true if the graph is connected
     */
    public boolean isGraphConnected() {
        ConnectivityInspector insp;
        if (graph instanceof DirectedGraph) {
            insp = new ConnectivityInspector((DirectedGraph) graph);
        } else if (graph instanceof UndirectedGraph) {
            insp = new ConnectivityInspector((UndirectedGraph) graph);
        } else {
            return false;
        }
        return insp.isGraphConnected();
    }

    /**
     * Convenience method to find the shortest path via a single static method
     * call. If you need a more advanced search (e.g. limited by radius, or
     * computation of the path length), use the constructor instead.
     *
     * @param vertex1 the vertex at which the path should start
     * @param vertex2 the vertex at which the path should end
     * @return a JBioWHGraph with the path found
     */
    public JBioWHGraph getGraphDijkstraShortestPath(Object vertex1, Object vertex2) {
        JBioWHGraph shortestGraph = new JBioWHGraph();
        List<DefaultEdge> path = DijkstraShortestPath.findPathBetween(graph, vertex1, vertex2);
        for (DefaultEdge o : path) {
            shortestGraph.getGraph().addVertex(graph.getEdgeSource(o));
            shortestGraph.getGraph().addVertex(graph.getEdgeTarget(o));
            shortestGraph.getGraph().addEdge(graph.getEdgeSource(o), graph.getEdgeTarget(o));
        }
        return shortestGraph;
    }

    /**
     * Populates the graph from the JBioWH main entity in the object o
     *
     * @param o the JBioWH main entity to start the graph
     */
    public void populate(Object o) {
        populate(o, 0);
    }

    /**
     * Return a string with the vertex object in a format with tab in front
     *
     * @param vertex the vertex name
     * @param varDepth the vertex depth
     * @return a string with the vertex object in a format with tab in front
     */
    protected String vertexToString(Object vertex, int varDepth) {
        return tabToString(varDepth) + EntityParserFieldProxy.getInstance().getId(vertex) + " " + varDepth;
    }

    /**
     * Return a string with the edge object in a format with tab in front
     *
     * @param vertex1 the first vertex name
     * @param vertex2 the second vertex name
     * @param varDepth the vertex depth
     * @return a string with the edge object in a format with tab in front
     */
    protected String edgeToString(Object vertex1, Object vertex2, int varDepth) {
        return tabToString(varDepth) + EntityParserFieldProxy.getInstance().getId(vertex1) + " <--> " + EntityParserFieldProxy.getInstance().getId(vertex2) + " " + varDepth;
    }

    /**
     * The recursive function to populate the graph from the JBioWH main entity
     * in the object o
     *
     * @param o the JBioWH main entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(Object o, int varDepth) {
        if (varDepth < depth) {
            varDepth++;
            if (o instanceof Protein) {
                populate((Protein) o, varDepth);
            } else if (o instanceof DrugBank) {
                populate((DrugBank) o, varDepth);
            } else if (o instanceof KEGGPathway) {
                populate((KEGGPathway) o, varDepth);
            } else if (o instanceof GeneInfo) {
                populate((GeneInfo) o, varDepth);
            } else if (o instanceof GenePTT) {
                populate((GenePTT) o, varDepth);
            } else if (o instanceof MIFEntrySet) {
                populate((MIFEntrySet) o, varDepth);
            } else if (o instanceof MIFEntrySetEntry) {
                populate((MIFEntrySetEntry) o, varDepth);
            } else if (o instanceof MIFEntryInteraction) {
                populate((MIFEntryInteraction) o, varDepth);
            } else if (o instanceof KEGGCompound) {
                populate((KEGGCompound) o, varDepth);
            } else if (o instanceof KEGGEnzyme) {
                populate((KEGGEnzyme) o, varDepth);
            } else if (o instanceof KEGGGene) {
                populate((KEGGGene) o, varDepth);
            } else if (o instanceof KEGGGlycan) {
                populate((KEGGGlycan) o, varDepth);
            } else if (o instanceof KEGGReaction) {
                populate((KEGGReaction) o, varDepth);
            } else if (o instanceof UniRefEntry) {
                populate((UniRefEntry) o, varDepth);
            } else if (o instanceof UniRefMember) {
                populate((UniRefMember) o, varDepth);
            } else if (o instanceof OMIM) {
                populate((OMIM) o, varDepth);
            } else if (o instanceof PfamAbioWH) {
                populate((PfamAbioWH) o, varDepth);
            } else if (o instanceof GeneBank) {
                populate((GeneBank) o, varDepth);
            } else if (o instanceof GeneBankCDS) {
                populate((GeneBankCDS) o, varDepth);
            }
        }
    }

    /**
     * The recursive function to populate the graph from PfamAbioWH entity
     *
     * @param o the PfamAbioWH entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(PfamAbioWH o, int varDepth) {
        if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
            populateCollection(o.getProteinSignificant(), o, varDepth);
            populateCollection(o.getProteinInsignificant(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from OMIM entity
     *
     * @param o the OMIM entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(OMIM o, int varDepth) {
        if (!JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
            populateCollection(o.getGeneInfo(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from UniRefMember entity
     *
     * @param o the UniRefMember entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(UniRefMember o, int varDepth) {
        if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
            addBioWHNewVertex(o, o.getProtein(), varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from UniRefEntry entity
     *
     * @param o the UniRefEntry entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(UniRefEntry o, int varDepth) {
        if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
            populateCollection(o.getProtein(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from KEGGReaction entity
     *
     * @param o the KEGGReaction entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(KEGGReaction o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGCompound.class)) {
            populateCollection(o.getkEGGCompoundAsProduct(), o, varDepth);
            populateCollection(o.getkEGGCompoundAsSubstrate(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGGlycan.class)) {
            populateCollection(o.getkEGGGlycanAsProduct(), o, varDepth);
            populateCollection(o.getkEGGGlycanAsSubstrate(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
            populateCollection(o.getkEGGPathways(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGEnzyme.class)) {
            populateCollection(o.getkEGGEnzyme(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from KEGGGlycan entity
     *
     * @param o the KEGGGlycan entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(KEGGGlycan o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGReaction.class)) {
            populateCollection(o.getkEGGReactionAsProduct(), o, varDepth);
            populateCollection(o.getkEGGReactionAsSubstrate(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from KEGGGene entity
     *
     * @param o the KEGGGene entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(KEGGGene o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
            populateCollection(o.getGeneInfos(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
            populateCollection(o.getkEGGPathways(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from KEGGEnzyme entity
     *
     * @param o the KEGGEnzyme entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(KEGGEnzyme o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
            populateCollection(o.getProtein(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGReaction.class)) {
            populateCollection(o.getkEGGReaction(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
            populateCollection(o.getkEGGPathways(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGCompound.class)) {
            populateCollection(o.getkEGGCompoundAsCofactor(), o, varDepth);
            populateCollection(o.getkEGGCompoundAsEffector(), o, varDepth);
            populateCollection(o.getkEGGCompoundAsInhibitor(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from KEGGCompound entity
     *
     * @param o the KEGGCompound entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(KEGGCompound o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGReaction.class)) {
            populateCollection(o.getkEGGReactionAsProduct(), o, varDepth);
            populateCollection(o.getkEGGReactionAsSubstrate(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGEnzyme.class)) {
            populateCollection(o.getkEGGEnzymeAsCofactor(), o, varDepth);
            populateCollection(o.getkEGGEnzymeAsEffector(), o, varDepth);
            populateCollection(o.getkEGGEnzymeAsInhibitor(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
            populateCollection(o.getkEGGPathways(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGRPair.class)) {
            populateCollection(o.getkEGGRPair(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(DrugBank.class)) {
            populateCollection(o.getDrugBanks(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from MIFEntryInteraction
     * entity
     *
     * @param o the MIFEntryInteraction entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(MIFEntryInteraction o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(MIFEntrySetEntry.class)) {
            addBioWHNewVertex(o, o.getmIFEntrySetEntry(), varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
            populateCollection(o.getProtein(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from MIFEntrySetEntry entity
     *
     * @param o the MIFEntrySetEntry entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(MIFEntrySetEntry o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(MIFEntrySet.class)) {
            addBioWHNewVertex(o, o.getmIFEntrySet(), varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(MIFEntryInteraction.class)) {
            populateCollection(o.getMifEntryInteraction(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from MIFEntrySet entity
     *
     * @param o the MIFEntrySet entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(MIFEntrySet o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(MIFEntrySetEntry.class)) {
            populateCollection(o.getmIFEntrySetEntry(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from GenePTT entity
     *
     * @param o the GenePTT entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(GenePTT o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
            addBioWHNewVertex(o, o.getProtein(), varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
            addBioWHNewVertex(o, o.getGeneInfo(), varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from GeneInfo entity
     *
     * @param o the GeneInfo entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(GeneInfo o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
            populateCollection(o.getProtein(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGGene.class)) {
            populateCollection(o.getkEGGGenes(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
            populateCollection(o.getkEGGPathways(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(OMIM.class)) {
            populateCollection(o.getOmim(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(GenePTT.class)) {
            addBioWHNewVertex(o.getGenePTT(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from GeneBank entity
     *
     * @param o the GenePTT entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(GeneBank o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(GeneBankCDS.class)) {
            populateCollection(o.getGeneBankCDSs(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from GeneBankCDS entity
     *
     * @param o the GenePTT entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(GeneBankCDS o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(GeneBank.class)) {
            addBioWHNewVertex(o, o.getGeneBank(), varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
            populateCollection(o.getGeneInfo(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(GenePTT.class)) {
            addBioWHNewVertex(o.getGenePTT(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from Protein entity
     *
     * @param o the Protein entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(Protein o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(DrugBank.class)) {
            populateCollection(o.getDrugBank(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
            populateCollection(o.getGeneInfo(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
            for (GeneInfo g : o.getGeneInfo()) {
                populateCollection(g.getkEGGPathways(), o, varDepth);
            }
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGEnzyme.class)) {
            populateCollection(o.getkEGGEnzymes(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(MIFEntryInteraction.class)) {
            populateCollection(o.getmIFEntryInteraction(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(UniRefEntry.class)) {
            populateCollection(o.getUniRefEntry(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(UniRefMember.class)) {
            populateCollection(o.getUniRefMember(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(PfamAbioWH.class)) {
            populateCollection(o.getPfamASignificant(), o, varDepth);
            populateCollection(o.getPfamAInsignificant(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from DrugBank entity
     *
     * @param o the DrugBank entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(DrugBank o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
            populateCollection(o.getProtein(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGCompound.class)) {
            populateCollection(o.getkEGGCompounds(), o, varDepth);
        }
    }

    /**
     * The recursive function to populate the graph from KEGGPathway entity
     *
     * @param o the KEGGPathway entity to start the graph
     * @param varDepth the recursive depth for parent level
     */
    protected void populate(KEGGPathway o, int varDepth) {
        if (JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
            for (GeneInfo g : o.getGeneInfo()) {
                populateCollection(g.getProtein(), o, varDepth);
            }
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGGene.class)) {
            populateCollection(o.getkEGGGenes(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGCompound.class)) {
            populateCollection(o.getkEGGCompounds(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGEnzyme.class)) {
            populateCollection(o.getkEGGEnzymes(), o, varDepth);
        }
        if (JpaEntitiesSelected.getInstance().getEntities().contains(KEGGReaction.class)) {
            populateCollection(o.getkEGGReactions(), o, varDepth);
        }
    }

    /**
     * Add each element of the collection as a new vertex connected to the
     * object o
     *
     * @param col collection with the JBioWH entities to be added as new
     * vertexes
     * @param o JBioWH entity which is a vertex that is connected to all
     * elements in the collection
     * @param varDepth the recursive depth for parent level
     */
    protected void populateCollection(Collection col, Object o, int varDepth) {
        for (Object p : col) {
            if (!p.equals(o)) {
                addBioWHNewVertex(o, p, varDepth);
            }
        }
    }

    /**
     * Add each element of the collection as a new vertex connected to the
     * object o in a weighted graph
     *
     * @param col collection with the JBioWH entities to be added as new
     * vertexes
     * @param o JBioWH entity which is a vertex that is connected to all
     * elements in the collection
     * @param varDepth the recursive depth for parent level
     * @param weightObject the object to increment the edge weight
     */
    protected void populateCollection(Collection col, Object o, int varDepth, Object weightObject) {
        for (Object p : col) {
            if (!p.equals(o)) {
                addBioWHNewVertex(o, p, varDepth, weightObject);
            }
        }
    }

    /**
     * Return a string with a number of tabs equal to varDepth
     *
     * @param varDepth the recursive depth for parent level
     * @return a string with a number of tabs equal to varDepth
     */
    protected String tabToString(int varDepth) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < varDepth; i++) {
            str.append("\t");
        }
        return str.toString();
    }

    /**
     * Add the vertex dest and create the edge with the vertex source. If the
     * vertex already exist then just the edge is created
     *
     * @param source source vertex to be connected with the dest vertex
     * @param dest destination vertex to be connected with the source vertex
     * @param varDepth the recursive depth for parent level
     */
    protected void addBioWHNewVertex(Object source, Object dest, int varDepth) {
        if (!source.equals(dest)) {
            graph.addVertex(source);
            if (!graph.containsVertex(dest)) {
                VerbLogger.getInstance().log(this.getClass(), vertexToString(dest, varDepth));
                VerbLogger.getInstance().log(this.getClass(), edgeToString(source, dest, varDepth));
                graph.addVertex(dest);
                graph.addEdge(source, dest);
            } else if (!graph.containsEdge(source, dest)) {
                if (varDepth < 2) {
                    VerbLogger.getInstance().log(this.getClass(), edgeToString(source, dest, varDepth));
                }
                graph.addEdge(source, dest);
            } else {
                Object e = graph.getEdge(source, dest);
                if (e instanceof HideWeightedEdge) {
                    HideWeightedEdge edge = (HideWeightedEdge) e;
                    if (!edge.getWeightObject().contains(dest)) {
                        double weight = ((ListenableUndirectedWeightedGraph) graph).getEdgeWeight(edge) + 1;
                        edge.getWeightObject().add(dest);
                        VerbLogger.getInstance().log(this.getClass(), edgeToString(source, dest, varDepth) + " edge weight object: " + EntityParserFieldProxy.getInstance().getId(dest) + " incrementing weight to " + weight);
                        ((ListenableUndirectedWeightedGraph) graph).setEdgeWeight(edge, weight);
                    }
                }
            }
            populate(dest, varDepth);
        }
    }

    /**
     * Add the vertex dest and create the edge with the vertex source. If the
     * vertex already exist then just the edge is created.
     *
     * This method verify if the edge is created then increment the weight of
     * this edge
     *
     * @param source source vertex to be connected with the dest vertex
     * @param dest destination vertex to be connected with the source vertex
     * @param varDepth the recursive depth for parent level
     * @param weightObject the MIF25 interaction that report the protein-protein
     * interaction
     */
    protected void addBioWHNewVertex(Object source, Object dest, int varDepth, Object weightObject) {
        if (!source.equals(dest)) {
            graph.addVertex(source);
            if (!graph.containsVertex(dest)) {
                VerbLogger.getInstance().log(this.getClass(), vertexToString(dest, varDepth));
                VerbLogger.getInstance().log(this.getClass(), edgeToString(source, dest, varDepth) + " edge weight object: " + EntityParserFieldProxy.getInstance().getId(weightObject));
                graph.addVertex(dest);
                HideWeightedEdge edge = (HideWeightedEdge) graph.addEdge(source, dest);
                edge.getWeightObject().add(weightObject);
            } else {
                if (!graph.containsEdge(source, dest)) {
                    VerbLogger.getInstance().log(this.getClass(), edgeToString(source, dest, varDepth) + " edge weight object: " + EntityParserFieldProxy.getInstance().getId(weightObject));
                    HideWeightedEdge edge = (HideWeightedEdge) graph.addEdge(source, dest);
                    edge.getWeightObject().add(weightObject);
                } else {
                    HideWeightedEdge edge = (HideWeightedEdge) graph.getEdge(source, dest);
                    if (!edge.getWeightObject().contains(weightObject)) {
                        double weight = ((ListenableUndirectedWeightedGraph) graph).getEdgeWeight(edge) + 1;
                        edge.getWeightObject().add(weightObject);
                        VerbLogger.getInstance().log(this.getClass(), edgeToString(source, dest, varDepth) + " edge weight object: " + EntityParserFieldProxy.getInstance().getId(weightObject) + " incrementing weight to " + weight);
                        ((ListenableUndirectedWeightedGraph) graph).setEdgeWeight(edge, weight);
                    }
                }
            }
        }
        populate(dest, varDepth);
    }

    public List<Object> getPathInList() {
        return getPathInList(graph.edgeSet());
    }

    public List<Object> getPathInList(Collection<DefaultEdge> edges) {
        Object source = null;
        Object dest = null;
        List myPath = new ArrayList();
        for (DefaultEdge o : edges) {
            if (source == null) {
                source = graph.getEdgeSource(o);
                dest = graph.getEdgeTarget(o);
                myPath.add(source);
                myPath.add(dest);
            } else {
                if (graph.getEdgeSource(o).equals(dest)) {
                    source = dest;
                    dest = graph.getEdgeTarget(o);
                    myPath.add(dest);
                } else if (graph.getEdgeTarget(o).equals(dest)) {
                    source = dest;
                    dest = graph.getEdgeSource(o);
                    myPath.add(dest);
                } else if (graph.getEdgeSource(o).equals(source)) {
                    myPath.add(myPath.indexOf(source), graph.getEdgeTarget(o));
                    dest = source;
                    source = graph.getEdgeTarget(o);
                } else if (graph.getEdgeTarget(o).equals(source)) {
                    myPath.add(myPath.indexOf(source), graph.getEdgeSource(o));
                    dest = source;
                    source = graph.getEdgeSource(o);
                }
            }
        }
        return myPath;
    }
}
