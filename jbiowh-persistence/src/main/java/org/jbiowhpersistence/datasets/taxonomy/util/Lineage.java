package org.jbiowhpersistence.datasets.taxonomy.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.NoResultException;
import org.jbiowhpersistence.datasets.taxonomy.controller.TaxonomyJpaController;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.controller.AbstractController;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;

/**
 * This Class retrieve the Taxonomy lineage
 *
 * $Author$ $LastChangedDate: 2013-06-05 15:04:58 +0200
 * (Wed, 05 Jun 2013) $ $LastChangedRevision$
 *
 * @since Jun 5, 2013
 */
public class Lineage extends AbstractController {

    private List<Object> lineage;
    private static Lineage singleton;

    private Lineage() {
        lineage = new LinkedList();
    }

    /**
     * Return a Lineage instance
     *
     * @return a Lineage instance
     */
    public static synchronized Lineage getInstance() {
        if (singleton == null) {
            singleton = new Lineage();
        }
        return singleton;
    }

    public List<Object> getLineage() {
        return lineage;
    }

    /**
     * Create a linked list with the lineage tax ids
     *
     * @param tax the starting taxId
     */
    public void getLineage(long tax) {
        HashMap parm = new HashMap();
        lineage.add(0, tax);
        parm.put("taxId", tax);
        try {
            Taxonomy parent = (Taxonomy) ((TaxonomyJpaController) getController(TaxonomyJpaController.class)).useNamedQuerySingleResult("Taxonomy.findByTaxId", parm);
            if (parent != null && parent.getParentTaxId() != 1) {
                getLineage(parent.getParentTaxId());
            }
        } catch (NoResultException ex) {
        }
    }

    /**
     * Create a linked list with the lineage tax names
     *
     * @param tax the starting taxId
     */
    public void getLineageByString(long tax) {
        HashMap parm = new HashMap();
        parm.put("taxId", tax);
        try {
            Taxonomy parent = (Taxonomy) ((TaxonomyJpaController) getController(TaxonomyJpaController.class)).useNamedQuerySingleResult("Taxonomy.findByTaxId", parm);
            if (parent != null) {
                lineage.add(0, parent.getTaxonomySynonym());
                if (parent.getParentTaxId() != 1) {
                    getLineageByString(parent.getParentTaxId());
                }
            }
        } catch (NoResultException ex) {
        }
    }

    /**
     * Get the lineage in a string using the *delimiter* between them
     *
     * @param delimiter the delimiter for the taxids
     * @return the lineage in a string using the *delimiter* between them
     */
    public String getLineageString(String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lineage.size(); i++) {
            builder.append(lineage.get(i).toString());
            if (i < lineage.size() - 1) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }

    /**
     * Get the lineage for the taxId in a string using the *delimiter* between
     * them
     *
     * @param tax the starting taxId
     * @param delimiter the delimiter for the taxids
     * @return the lineage in a string using the *delimiter* between them
     */
    public String getLineageString(long tax, String delimiter) {
        StringBuilder builder = new StringBuilder();
        lineage.clear();
        getLineage(tax);
        for (int i = 0; i < lineage.size(); i++) {
            builder.append(lineage.get(i).toString());
            if (i < lineage.size() - 1) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }

    @Override
    protected HashMap<Class, Object> createController() {
        HashMap<Class, Object> controllers = new HashMap();
        controllers.put(TaxonomyJpaController.class, new TaxonomyJpaController(JBioWHPersistence.getInstance().getWHEntityManager()));
        return controllers;
    }
}
