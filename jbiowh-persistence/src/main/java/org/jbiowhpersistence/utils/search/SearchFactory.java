package org.jbiowhpersistence.utils.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jpql.JPQLBuilder;

/**
 * This Class is the abstract search factory
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 27, 2011
 */
public abstract class SearchFactory {

    private HashMap<String, Class> fields;

    /**
     * Get the main field name used it in the search
     *
     * @return the main field name used it in the search
     */
    public abstract String getMainField();

    /**
     * Get the available search fields
     *
     * @return a Set<String> object with the available search fields
     */
    public Set<String> getFieldsSet() {
        return fields.keySet();
    }

    /**
     * Set the available search fields
     *
     * @param fields the available search fields
     */
    public void setFields(HashMap<String, Class> fields) {
        this.fields = fields;
    }

    /**
     * Get the available search fields
     *
     * @return a HashMap<String, Class> object with the available search fields
     */
    public HashMap<String, Class> getFields() {
        return fields;
    }

    /**
     * Perform the search
     *
     * @param search The search string
     * @param constrains The JPLConstrains object to be used in the search
     * @return a List object with the result obtained
     * @throws java.sql.SQLException
     */
    public abstract List search(String search, JPLConstrains constrains) throws SQLException;

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
    public List search(List search, JPLConstrains constrains) throws SQLException {
        EntityManager em = JBioWHPersistence.getInstance().getWHEntityManager().createEntityManager();

        JPQLBuilder jpqlBuilder = new JPQLBuilder(getSearchFactoryEntity(), getFieldId(),
                fields, getFieldAfterWhere(), getFieldBeforeWhere(), getFieldOnEntity(), getConstrainFieldOnEntity());

        Query q = em.createQuery(jpqlBuilder.getJpqlQuery(search, constrains));

        for (String key : jpqlBuilder.getParm().keySet()) {
            q.setParameter(key, jpqlBuilder.getParm().get(key));
        }

        List result = q.getResultList();
        em.close();
        if (!result.isEmpty()) {
            if (result.get(0) instanceof Long) {
                return searchByWID(result, getSearchFactoryEntity());
            }
        }
        return result;
    }

    /**
     * Get the main entity class
     *
     * @return the main entity class
     */
    protected abstract Class getSearchFactoryEntity();

    /**
     * Get the field that will be used as Id on queries
     *
     * @return the field that will be used as Id on queries
     */
    protected String getFieldId(){
        return "wid";
    }

    /**
     * Get the SQL syntax to put before WHERE
     *
     * @return a HashMap<String, String> object with the SQL syntax to put
     * before WHERE
     */
    protected abstract HashMap<String, String> getFieldBeforeWhere();

    /**
     * Get the SQL syntax to put after WHERE
     *
     * @return a HashMap<String, String> object with the SQL syntax to put after
     * WHERE
     */
    protected abstract HashMap<String, String> getFieldAfterWhere();

    /**
     * Get the field definition on the entity
     *
     * @return a HashMap<String, String> object with the field definition on the
     * entity
     */
    protected abstract HashMap<String, String> getFieldOnEntity();

    /**
     * Get the constrain field definition on the entity
     *
     * @return a HashMap<Class, String> object with the constrain field
     * definition on the entity
     */
    protected abstract HashMap<Class, List> getConstrainFieldOnEntity();

    /**
     * Search Entity by WID
     *
     * @param WID The entity WID
     * @param entity The entity class to be used
     * @return a List of entities object with the search result
     */
    public List searchByWID(long WID, Class entity) {
        ArrayList result = new ArrayList();
        EntityManager em = JBioWHPersistence.getInstance().getWHEntityManager().createEntityManager();
        try {
            result.add(em.find(entity, WID));
        } finally {
            em.close();
        }
        return result;
    }

    /**
     * Search Entity from a WID list
     *
     * @param widList The WID list to be search
     * @param entity The entity class to be used
     * @return a List of entities object with the search result
     */
    public List searchByWID(List widList, Class entity) {
        ArrayList result = new ArrayList();
        EntityManager em = JBioWHPersistence.getInstance().getWHEntityManager().createEntityManager();
        try {
            for (Long wid : (List<Long>) widList) {
                result.add(em.find(entity, wid));
            }
        } finally {
            em.close();
        }
        return result;
    }

    /**
     * True is the operation is a boolean operation or not
     *
     * @param ope
     * @return
     */
    public boolean isBooleanOperation(String ope) {
        return ope.equals("is null") || ope.equals("is not null");
    }

    @Override
    public String toString() {
        return "SearchFactory{" + getSearchFactoryEntity().getSimpleName() + " fields=" + fields + '}';
    }
}
