package org.jbiowhpersistence.datasets.ppi.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySet;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the MIFEntrySet Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Sep 17, 2012
 */
public class MIFEntrySetJpaController extends AbstractMIFJpaController<MIFEntrySet> implements Serializable {

    public MIFEntrySetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MIFEntrySet mifEntrySet) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mifEntrySet.setDataSet(createDataSet(emf, em, mifEntrySet.getDataSet()));
            mifEntrySet.setmIFEntrySetEntry(createMIFEntrySetEntry(emf, em, mifEntrySet.getmIFEntrySetEntry()));
            em.persist(mifEntrySet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMIFEntrySet(mifEntrySet.getWid()) != null) {
                throw new PreexistingEntityException("MIFEntrySet " + mifEntrySet + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MIFEntrySet mifEntrySet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(mifEntrySet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mifEntrySet.getWid();
                if (findMIFEntrySet(id) == null) {
                    throw new NonexistentEntityException("The mIFEntrySet with id " + id + " no longer exists.");
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
            MIFEntrySet MIFEntrySet;
            try {
                MIFEntrySet = em.getReference(MIFEntrySet.class, id);
                MIFEntrySet.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MIFEntrySet with id " + id + " no longer exists.", enfe);
            }
            em.remove(MIFEntrySet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MIFEntrySet> findMIFEntrySetEntities() {
        return findMIFEntrySetEntities(true, -1, -1);
    }

    public List<MIFEntrySet> findMIFEntrySetEntities(int maxResults, int firstResult) {
        return findMIFEntrySetEntities(false, maxResults, firstResult);
    }

    private List<MIFEntrySet> findMIFEntrySetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MIFEntrySet.class));
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

    public MIFEntrySet findMIFEntrySet(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MIFEntrySet.class, id);
        } finally {
            em.close();
        }
    }

    public int getMIFEntrySetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MIFEntrySet> rt = cq.from(MIFEntrySet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
