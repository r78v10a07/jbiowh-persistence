package org.jbiowhpersistence.utils.msms;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 * This class is the MS parser data
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Sep 5, 2013
 */
public class MSData {

    private LinkedList<Map.Entry<String, List<Protein>>> proteinList;
    private LinkedList<String> notFound;

    /**
     * Get the protein list extracted from the MS XML result file
     *
     * @return the protein list
     */
    public LinkedList<Map.Entry<String, List<Protein>>> getProteinList() {
        if (proteinList == null) {
            proteinList = new LinkedList();
        }
        return proteinList;
    }

    /**
     * Get the proteins id not found in the JBioWH relational schema
     *
     * @return the protein list
     */
    public LinkedList<String> getNotFound() {
        if (notFound == null) {
            notFound = new LinkedList();
        }
        return notFound;
    }
}
