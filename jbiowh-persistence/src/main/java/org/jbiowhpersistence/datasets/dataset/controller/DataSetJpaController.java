package org.jbiowhpersistence.datasets.dataset.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This Class is the DataSet entity JPA controller class
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-08-31 15:46:36 +0200
 * (Fri, 31 Aug 2012) $ $LastChangedRevision: 322 $
 * @since Jun 17, 2011
 */
public class DataSetJpaController extends AbstractJpaController<DataSet> implements Serializable {

    /**
     * Creates a DataSet JPA Controller and initialize the EntityManagerFactory
     *
     * @param emf the EntityManagerFactory
     */
    public DataSetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Creates a DataSet entity
     *
     * @param dataSet the Dataset entity object
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void create(DataSet dataSet) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dataSet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDataSet(dataSet.getWid()) != null) {
                throw new PreexistingEntityException("DataSet " + dataSet + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Edit a DataSet entity object
     *
     * @param dataSet the DataSet entity object
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(DataSet dataSet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dataSet = em.merge(dataSet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = dataSet.getWid();
                if (findDataSet(id) == null) {
                    throw new NonexistentEntityException("The dataSet with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Destroy a DataSet entity object from its Id
     *
     * @param id the dataSet's Id
     * @throws NonexistentEntityException
     */
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DataSet dataSet;
            try {
                dataSet = em.getReference(DataSet.class, id);
                dataSet.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dataSet with id " + id + " no longer exists.", enfe);
            }
            em.remove(dataSet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Find all DataSet entities
     *
     * @return a List object with all DataSet entities
     */
    public List<DataSet> findDataSetEntities() {
        return findDataSetEntities(true, -1, -1);
    }

    /**
     * Find all DataSet entities between the desired limits
     *
     * @param maxResults the amount of results to be returned
     * @param firstResult the first DataSet entity to be returned
     * @return a List object with all DataSet entities between the desired
     * limits
     */
    public List<DataSet> findDataSetEntities(int maxResults, int firstResult) {
        return findDataSetEntities(false, maxResults, firstResult);
    }

    private List<DataSet> findDataSetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DataSet.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Find a DataSet from its Id
     *
     * @param id the DataSet Id
     * @return a DataSet entity
     */
    public DataSet findDataSet(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataSet.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Count all DataSet entities objects
     *
     * @return a int with the amount of Dataset entities inserted
     */
    public int getDataSetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DataSet> rt = cq.from(DataSet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
