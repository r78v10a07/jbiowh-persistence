package org.jbiowhpersistence.datasets.dataset.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.dataset.entities.WidTable;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This Class is the WIDTable entity JPA controller class
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Jun 17, 2011
 */
public class WidTableJpaController extends AbstractJpaController<WidTable> implements Serializable {

    /**
     * Creates a WidTable JPA Controller and initialize the EntityManagerFactory
     *
     * @param emf the EntityManagerFactory
     */
    public WidTableJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    /**
     * Get the EntityManagerFactory
     *
     * @return the EntityManagerFactory object
     */
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Creates a WidTable entity
     *
     * @param widTable the WidTable entity object
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void create(WidTable widTable) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(widTable);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findWidTable(widTable.getWid()) != null) {
                throw new PreexistingEntityException("WidTable " + widTable + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Edit the WidTable entity
     *
     * @param widTable the WidTable entity
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(WidTable widTable) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(widTable);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = widTable.getWid();
                if (findWidTable(id) == null) {
                    throw new NonexistentEntityException("The widTable with id " + id + " no longer exists.");
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
     * Destroy a WidTable entity from its Id
     *
     * @param id the WidTable Id
     * @throws NonexistentEntityException
     */
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WidTable widTable;
            try {
                widTable = em.getReference(WidTable.class, id);
                widTable.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The widTable with id " + id + " no longer exists.", enfe);
            }
            em.remove(widTable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Find all WidTable entities
     *
     * @return a List object with all WidTable entities
     */
    public List<WidTable> findWidTableEntities() {
        return findWidTableEntities(true, -1, -1);
    }

    /**
     * Find all WidTable entities between the desired limits
     *
     * @param maxResults the amount of results to be returned
     * @param firstResult the first WidTable entity to be returned
     * @return a List object with all WidTable entities between the desired
     * limits
     */
    public List<WidTable> findWidTableEntities(int maxResults, int firstResult) {
        return findWidTableEntities(false, maxResults, firstResult);
    }

    private List<WidTable> findWidTableEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(WidTable.class));
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
     * Find a WidTable from its Id
     *
     * @param id the WidTable Id
     * @return a WidTable entity
     */
    public WidTable findWidTable(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WidTable.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Count all WidTable entities objects
     *
     * @return a int with the amount of WidTable entities inserted
     */
    public int getWidTableCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<WidTable> rt = cq.from(WidTable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
