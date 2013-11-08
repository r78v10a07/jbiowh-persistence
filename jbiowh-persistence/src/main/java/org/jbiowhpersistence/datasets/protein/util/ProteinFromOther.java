/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbiowhpersistence.datasets.protein.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protein.search.SearchProtein;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.datasets.taxonomy.search.SearchTaxonomy;

/**
 * This class is used to retrieve proteins from other JBioWH biological objects
 *
 * $Author$ $LastChangedDate: 2013-03-19 09:38:47 +0100
 * (Tue, 19 Mar 2013) $ $LastChangedRevision$
 *
 * @since Sep 28, 2012
 */
public class ProteinFromOther {

    private List<Protein> proteins;
    private SearchProtein sProt;

    /**
     * Create a ProteinFromOther object to retrieve proteins
     */
    public ProteinFromOther() {
        proteins = new ArrayList();
        sProt = new SearchProtein();
    }

    /**
     * Return the list of founded proteins
     *
     * @return the list of founded proteins
     */
    public List<Protein> getProteins() {
        return proteins;
    }

    public List<String> getProteinFromId(List<String> ids) {
        List<String> missIds = new ArrayList();
        for (String id : ids) {
            List prots = getProteinFromId(id);
            if (!prots.isEmpty()) {
                proteins.addAll(prots);
            } else {
                missIds.add(id);
            }
        }
        return missIds;
    }

    public List<Protein> getProteinFromId(String id) {
        try {
            return sProt.search(id, null);
        } catch (SQLException ex) {
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
            VerbLogger.getInstance().log(this.getClass(), ex.getMessage());
            System.exit(1);
        }
        return new ArrayList();
    }

    /**
     * Retrieve the proteins that belong the Taxonomy
     *
     * @param taxonomyName the taxonomy search string (regular expression can be
     * used)
     * @return a list with the taxonomies that does not have proteins on JBioWH
     */
    public List<String> getProteinFromTaxonomy(String taxonomyName) {
        SearchTaxonomy sTax = new SearchTaxonomy();
        proteins = new ArrayList();
        List<String> taxNoProtein = new ArrayList();
        try {
            List<Taxonomy> taxs = sTax.search(taxonomyName, null);
            return getProteinFromTaxonomy(taxs);
        } catch (SQLException ex) {
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
            VerbLogger.getInstance().log(this.getClass(), ex.getMessage());
            System.exit(1);
        }
        return taxNoProtein;
    }

    /**
     * Retrieve the proteins that belong the Taxonomy list
     *
     * @param taxonomies the taxonomy list to search the proteins
     * @return a list with the taxonomies that does not have proteins on JBioWH
     */
    public List<String> getProteinFromTaxonomy(List<Taxonomy> taxonomies) {
        List<String> taxNoProtein = new ArrayList();
        if (taxonomies != null && !taxonomies.isEmpty()) {
            for (Taxonomy tax : taxonomies) {
                List<Protein> prots = getProteinFromTaxonomy(tax);
                if (!prots.isEmpty()) {
                    proteins.addAll(prots);
                } else {
                    taxNoProtein.add(tax.getTaxonomySynonym());
                }
            }
        }
        return taxNoProtein;
    }

    /**
     * Return the proteins that belong the Taxonomy
     *
     * @param taxonomy the taxonomy entity
     * @return the proteins that belong the Taxonomy
     */
    public List<Protein> getProteinFromTaxonomy(Taxonomy taxonomy) {
        if (taxonomy != null) {
            try {
                List constrains = new ArrayList();
                List operations = new ArrayList();
                constrains.add(Arrays.asList(taxonomy));
                operations.add("IN");
                JPLConstrains taxAsConstrain = new JPLConstrains(constrains, operations, null);
                return sProt.search("", taxAsConstrain);
            } catch (SQLException ex) {
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                VerbLogger.getInstance().log(this.getClass(), ex.getMessage());
                System.exit(1);
            }
        }
        return new ArrayList();
    }
}
