package org.jbiowhpersistence.utils.graph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.view.mxGraph;
import java.util.HashMap;
import org.jbiowhpersistence.utils.entitymanager.EntityParserFieldProxy;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

/**
 * This class is the adapter to visualize a JGrapht into the mxGraph
 *
 * $Author$ $LastChangedDate: 2013-03-27 15:25:50 +0100
 * (Wed, 27 Mar 2013) $ $LastChangedRevision$
 *
 * @param <V> Graph vertex
 * @param <E> Graph edge
 * @since Mar 21, 2013
 */
public class JGraphXAdapter<V, E> extends mxGraph implements GraphListener<V, E> {

    private ListenableGraph<V, E> graphT;
    private HashMap<V, mxCell> vertexToCellMap;
    private HashMap<E, mxCell> edgeToCellMap;
    private HashMap<mxCell, V> cellToVertexMap;
    private HashMap<mxCell, E> cellToEdgeMap;

    /**
     * Creates the JGraphXAdapter
     *
     * @param graphT the JgraphT
     */
    public JGraphXAdapter(final ListenableGraph<V, E> graphT) {
        super();
        this.graphT = graphT;
        vertexToCellMap = new HashMap<V, mxCell>();
        edgeToCellMap = new HashMap<E, mxCell>();
        cellToVertexMap = new HashMap<mxCell, V>();
        cellToEdgeMap = new HashMap<mxCell, E>();
        insertJGraphT(graphT);
    }

    /**
     * Add a JGraphT Vertex
     *
     * @param vertex a Jgrapht vertex
     */
    public void addJGraphTVertex(V vertex) {
        getModel().beginUpdate();
        try {
            mxCell cell = new mxCell(EntityParserFieldProxy.getInstance().getId(vertex));
            cell.setVertex(true);
            cell.setId(null);
            cell.setGeometry(new mxGeometry(10, 10, 65, 15));
            cell.setStyle(EntityParserFieldProxy.getInstance().getJgraphStyle(vertex));
            addCell(cell, defaultParent);
            vertexToCellMap.put(vertex, cell);
            cellToVertexMap.put(cell, vertex);
        } finally {
            getModel().endUpdate();
        }
    }

    /**
     * Add a JGraphT Edge
     *
     * @param edge a JGraphT Edge
     */
    public void addJGraphTEdge(E edge) {
        getModel().beginUpdate();
        try {
            V source = graphT.getEdgeSource(edge);
            V target = graphT.getEdgeTarget(edge);
            mxCell cell = new mxCell(edge);
            cell.setEdge(true);
            cell.setId(null);
            cell.setGeometry(new mxGeometry());
            cell.getGeometry().setRelative(true);
            addEdge(cell, defaultParent, vertexToCellMap.get(source), vertexToCellMap.get(target), null);
            edgeToCellMap.put(edge, cell);
            cellToEdgeMap.put(cell, edge);
        } finally {
            getModel().endUpdate();
        }
    }

    /**
     * Return a map with the vertexes and mxCell
     *
     * @return a map with the vertexes and mxCell
     */
    public HashMap<V, mxCell> getVertexToCellMap() {
        return vertexToCellMap;
    }

    /**
     * Return a map with the edges and mxCell
     *
     * @return a map with the edges and mxCell
     */
    public HashMap<E, mxCell> getEdgeToCellMap() {
        return edgeToCellMap;
    }

    /**
     * Return a map with the mxCell and edge map
     *
     * @return a map with the mxCell and edge map
     */
    public HashMap<mxCell, E> getCellToEdgeMap() {
        return cellToEdgeMap;
    }

    /**
     * Return a map with the mxCell and vertex map
     *
     * @return a map with the mxCell and vertex map
     */
    public HashMap<mxCell, V> getCellToVertexMap() {
        return cellToVertexMap;
    }

    /**
     * Graph add vertex listener
     *
     * @param e listener
     */
    @Override
    public void vertexAdded(GraphVertexChangeEvent<V> e) {
        addJGraphTVertex(e.getVertex());
    }

    /**
     * Graph remove vertex listener
     *
     * @param e listener
     */
    @Override
    public void vertexRemoved(GraphVertexChangeEvent<V> e) {
        mxCell cell = vertexToCellMap.remove(e.getVertex());
        removeCells(new Object[]{cell});
    }

    /**
     * Graph add edge listener
     *
     * @param e listener
     */
    @Override
    public void edgeAdded(GraphEdgeChangeEvent<V, E> e) {
        addJGraphTEdge(e.getEdge());
    }

    /**
     * Graph remove edge listener
     *
     * @param e listener
     */
    @Override
    public void edgeRemoved(GraphEdgeChangeEvent<V, E> e) {
        mxCell cell = edgeToCellMap.remove(e.getEdge());
        removeCells(new Object[]{cell});
    }

    private void insertJGraphT(Graph<V, E> graphT) {
        this.graphT.addGraphListener(this);
        defaultParent = getDefaultParent();
        getModel().beginUpdate();
        try {
            for (V vertex : graphT.vertexSet()) {
                addJGraphTVertex(vertex);
            }
            for (E edge : graphT.edgeSet()) {
                addJGraphTEdge(edge);
            }
        } finally {
            getModel().endUpdate();
        }

    }
}
