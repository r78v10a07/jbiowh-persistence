package org.jbiowhpersistence.datasets.taxonomy.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.NoResultException;
import org.jbiowhcore.logger.VerbLogger;

/**
 * This class is used to create sets of Taxonomy lineage from a taxonomy list
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since May 28, 2013
 */
public class LineageMap {

    private Map<Integer, TaxonomyTaxIdGraph> lineageMap;
    private Set<Integer> notTaxonomy;

    /**
     * Creates sets of Taxonomy lineage from a taxonomy list
     *
     * @param taxIds a taxonomy list
     */
    public LineageMap(String[] taxIds) {
        lineageMap = new HashMap();
        notTaxonomy = new HashSet();
        createTaxMap(taxIds);
    }

    /**
     * Add a set of lineages to the map
     *
     * @param taxIds a taxonomy list
     */
    public void addLineage(String[] taxIds) {
        createTaxMap(taxIds);
    }

    /**
     * Creates sets of Taxonomy lineage from a taxonomy list
     *
     * @param taxIds a taxonomy list
     * @return a lineage map
     */
    private void createTaxMap(String[] taxIds) {
        if (taxIds != null) {
            for (String tax : taxIds) {
                try {
                    TaxonomyTaxIdGraph g = new TaxonomyTaxIdGraph(-1);
                    g.populate(Long.parseLong(tax));
                    lineageMap.put(Integer.parseInt(tax), g);
                } catch (NoResultException ex) {
                    notTaxonomy.add(Integer.parseInt(tax));
                }
            }
            for (int i = 0; i < taxIds.length; i++) {
                for (int j = i + 1; j < taxIds.length; j++) {
                    int minor, higher;
                    if (lineageMap.get(Integer.parseInt(taxIds[i])).getGraph().vertexSet().size() < lineageMap.get(Integer.parseInt(taxIds[j])).getGraph().vertexSet().size()) {
                        minor = i;
                        higher = j;
                    } else {
                        minor = j;
                        higher = i;
                    }
                    for (Long tax : (Set<Long>) lineageMap.get(Integer.parseInt(taxIds[minor])).getGraph().vertexSet()) {
                        if (lineageMap.get(Integer.parseInt(taxIds[higher])).getGraph().removeVertex(tax)) {
                            VerbLogger.getInstance().log(this.getClass(), "Removing tax: " + tax + " from graph " + taxIds[higher]);
                        }
                    }
                }
            }
        }
    }

    /**
     * Get the lineage map
     *
     * @return the lineage map
     */
    public Map<Integer, TaxonomyTaxIdGraph> getLineageMap() {
        return lineageMap;
    }

    /**
     * Get the set of taxId that are not included into the lineage
     *
     * @return the set of taxId that are not included into the lineage
     */
    public Set<Integer> getNotTaxonomy() {
        return notTaxonomy;
    }
}
