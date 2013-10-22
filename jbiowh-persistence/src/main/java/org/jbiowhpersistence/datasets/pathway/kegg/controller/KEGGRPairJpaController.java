package org.jbiowhpersistence.datasets.pathway.kegg.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGRPair;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the KEGG RPair Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $
 * $LastChangedRevision: 396 $
 * @since Nov 17, 2011
 */
public class KEGGRPairJpaController extends AbstractKEGGJpaController<KEGGRPair> implements Serializable {

    public KEGGRPairJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KEGGRPair keggRPair) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + keggRPair.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            keggRPair.setkEGGCompound(createKEGGCompound(emf, em, keggRPair.getkEGGCompound()));
            keggRPair.setDataSet(createDataSet(emf, em, keggRPair.getDataSet()));
            em.persist(keggRPair);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKEGGRPair(keggRPair.getWid()) != null) {
                throw new PreexistingEntityException("KEGGRPair " + keggRPair + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KEGGRPair keggRPair) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + keggRPair.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KEGGRPair persistentKEGGRPair = em.find(KEGGRPair.class, keggRPair.getWid());
            Set<KEGGCompound> kEGGCompoundOld = persistentKEGGRPair.getkEGGCompound();
            Set<KEGGCompound> kEGGCompoundNew = keggRPair.getkEGGCompound();
            Set<KEGGCompound> attachedkEGGCompoundNew = new HashSet<>();
            for (KEGGCompound kEGGCompoundNewKEGGCompoundToAttach : kEGGCompoundNew) {
                kEGGCompoundNewKEGGCompoundToAttach = em.getReference(kEGGCompoundNewKEGGCompoundToAttach.getClass(), kEGGCompoundNewKEGGCompoundToAttach.getWid());
                attachedkEGGCompoundNew.add(kEGGCompoundNewKEGGCompoundToAttach);
            }
            kEGGCompoundNew = attachedkEGGCompoundNew;
            keggRPair.setkEGGCompound(kEGGCompoundNew);
            keggRPair = em.merge(keggRPair);
            for (KEGGCompound kEGGCompoundOldKEGGCompound : kEGGCompoundOld) {
                if (!kEGGCompoundNew.contains(kEGGCompoundOldKEGGCompound)) {
                    kEGGCompoundOldKEGGCompound.getkEGGRPair().remove(keggRPair);
                    kEGGCompoundOldKEGGCompound = em.merge(kEGGCompoundOldKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundNewKEGGCompound : kEGGCompoundNew) {
                if (!kEGGCompoundOld.contains(kEGGCompoundNewKEGGCompound)) {
                    kEGGCompoundNewKEGGCompound.getkEGGRPair().add(keggRPair);
                    kEGGCompoundNewKEGGCompound = em.merge(kEGGCompoundNewKEGGCompound);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = keggRPair.getWid();
                if (findKEGGRPair(id) == null) {
                    throw new NonexistentEntityException("The kEGGRPair with id " + id + " no longer exists.");
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
            KEGGRPair KEGGRPair;
            try {
                KEGGRPair = em.getReference(KEGGRPair.class, id);
                KEGGRPair.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The KEGGRPair with id " + id + " no longer exists.", enfe);
            }
            Set<KEGGCompound> kEGGCompound = KEGGRPair.getkEGGCompound();
            for (KEGGCompound kEGGCompoundKEGGCompound : kEGGCompound) {
                kEGGCompoundKEGGCompound.getkEGGRPair().remove(KEGGRPair);
                kEGGCompoundKEGGCompound = em.merge(kEGGCompoundKEGGCompound);
            }
            em.remove(KEGGRPair);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KEGGRPair> findKEGGRPairEntities() {
        return findKEGGRPairEntities(true, -1, -1);
    }

    public List<KEGGRPair> findKEGGRPairEntities(int maxResults, int firstResult) {
        return findKEGGRPairEntities(false, maxResults, firstResult);
    }

    private List<KEGGRPair> findKEGGRPairEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KEGGRPair.class));
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

    public KEGGRPair findKEGGRPair(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KEGGRPair.class, id);
        } finally {
            em.close();
        }
    }

    public int getKEGGRPairCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KEGGRPair> rt = cq.from(KEGGRPair.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
