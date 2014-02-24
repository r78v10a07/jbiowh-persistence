package org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities.ProtClust;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the ProtClust Jpa Controller
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 13, 2014
 */
public class ProtClustJpaController extends AbstractJpaController<ProtClust> implements Serializable {

    public ProtClustJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProtClust protClust) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + protClust.getWid());        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            protClust.setGeneInfo(createGeneInfo(emf, em, protClust.getGeneInfo()));
            protClust.setTaxonomy(createTaxonomy(emf, em, protClust.getTaxonomy()));
            protClust.setProtein(createProtein(emf, em, protClust.getProtein()));
            protClust.setCogOrthologousGroup(createCOGOrthologousGroup(emf, em, protClust.getCogOrthologousGroup()));
            em.persist(protClust);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProtClust(protClust.getWid()) != null) {
                throw new PreexistingEntityException("ProtClust " + protClust + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProtClust protClust) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + protClust.getWid());        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            protClust = em.merge(protClust);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = protClust.getWid();
                if (findProtClust(id) == null) {
                    throw new NonexistentEntityException("The protClust with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProtClust protClust;
            try {
                protClust = em.getReference(ProtClust.class, id);
                protClust.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The protClust with id " + id + " no longer exists.", enfe);
            }
            em.remove(protClust);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProtClust> findProtClustEntities() {
        return findProtClustEntities(true, -1, -1);
    }

    public List<ProtClust> findProtClustEntities(int maxResults, int firstResult) {
        return findProtClustEntities(false, maxResults, firstResult);
    }

    private List<ProtClust> findProtClustEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProtClust.class));
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

    public ProtClust findProtClust(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProtClust.class, id);
        } finally {
            em.close();
        }
    }

    public int getProtClustCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProtClust> rt = cq.from(ProtClust.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
