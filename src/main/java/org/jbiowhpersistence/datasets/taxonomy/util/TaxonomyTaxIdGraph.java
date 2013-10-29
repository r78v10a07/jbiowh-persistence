package org.jbiowhpersistence.datasets.taxonomy.util;

import java.util.HashMap;
import java.util.List;
import javax.persistence.NoResultException;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.graph.HideEdge;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jgrapht.graph.ListenableDirectedGraph;

/**
 * This class handled a Taxonomy taxid graph 
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since May 4, 2013
 */
public class TaxonomyTaxIdGraph extends TaxonomyGraph{

    public TaxonomyTaxIdGraph(int depth) {
        super(depth);
    }

    /**
     * Creates the Taxonomy graph from the parent Tax Id
     *
     * @param obj the parent Tax Id
     */
    @Override
    public void populate(Object obj) {
        populate(obj, 0);
    }

    /**
     * Creates the Taxonomy graph from the parent Tax Id with a depth limit If
     * depth is equal to -1 there is not limit
     *
     * @param obj the parent Tax Id
     */
    @Override
    public void populate(Object obj, int depth) {
        HashMap parm = new HashMap();

        if (obj instanceof Long) {
            long startTime = System.currentTimeMillis();            
            parm.put("taxId", obj);
            graph = new ListenableDirectedGraph(HideEdge.class);
            try {
                Taxonomy parent = (Taxonomy) getController().useNamedQuerySingleResult("Taxonomy.findByTaxId", parm);
                graph.addVertex(parent.getTaxId());
                populate(parent.getTaxId(), 0);
            } catch (NoResultException ex) {
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().DEBUG);
                VerbLogger.getInstance().log(this.getClass(), "There is not Taxonomy with TaxId: " + obj);
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
                throw ex;
            }
            VerbLogger.getInstance().log(this.getClass(), "Created a graph with "
                    + graph.vertexSet().size()
                    + " Taxonomies as vertex and "
                    + graph.edgeSet().size()
                    + " direct edges in "
                    + ((long) (System.currentTimeMillis() - startTime) / 1000) + " s");
        } else {
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().DEBUG);
            VerbLogger.getInstance().log(this.getClass(), "This is a Taxonomy graph and the parameter obj should be Long (TaxId) instead of " + obj.getClass().getSimpleName());
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
            throw new NoResultException("This is a Taxonomy graph and the parameter obj should be Long (TaxId) instead of " + obj.getClass().getSimpleName());
        }
    }

    private void populate(Long taxId, int depth) {
        if (this.depth == -1 || (this.depth != -1 && depth <= this.depth)) {
            depth++;
            HashMap parm = new HashMap();
            parm.put("parentTaxId", taxId);
            List<Taxonomy> childs = getController().useNamedQuery("Taxonomy.findByParentTaxId", parm);
            for (Taxonomy t : childs) {
                graph.addVertex(t.getTaxId());
                graph.addEdge(taxId, t.getTaxId());
                populate(t.getTaxId(), depth);
            }
        }
    }
}
