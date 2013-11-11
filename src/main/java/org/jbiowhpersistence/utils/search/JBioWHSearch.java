package org.jbiowhpersistence.utils.search;

import java.sql.SQLException;
import java.util.List;
import org.jbiowhcore.utility.constrains.JPLConstrains;

/**
 * This interface is to search over the datasets
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Nov 9, 2013
 */
public interface JBioWHSearch {

    /**
     * Perform the search
     *
     * @param search The search string
     * @param constrains The JPLConstrains object to be used in the search
     * @return a List object with the result obtained
     * @throws java.sql.SQLException
     */
    public List search(String search, JPLConstrains constrains)  throws SQLException;

    /**
     * Perform the search
     *
     * @param search is a List<List> object with the internal list has 4
     * elements. The first element is the logical operator to link with the
     * previous condition (empty string for the first list). The second element
     * is the FIELD on the DataSet to be used to compare. The third element is
     * the operator to compare. The fourth element is the string to be searched.
     * @param constrains the JPLConstrains object to be used in the search
     * @return a List object with the result obtained
     * @throws java.sql.SQLException
     */
    public List search(List search, JPLConstrains constrains) throws SQLException;

    /**
     * Search Entity by WID
     *
     * @param WID The entity WID
     * @param entity The entity class to be used
     * @return a List of entities object with the search result
     */
    public List searchByWID(long WID, Class entity);

    /**
     * Search Entity from a WID list
     *
     * @param widList The WID list to be search
     * @param entity The entity class to be used
     * @return a List of entities object with the search result
     */
    public List searchByWID(List widList, Class entity);
}
