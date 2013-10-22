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
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the KEGGGlycan Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 17, 2012
 */
public class KEGGGlycanJpaController extends AbstractKEGGJpaController<KEGGGlycan> implements Serializable {

    public KEGGGlycanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KEGGGlycan keggGlycan) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + keggGlycan.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            keggGlycan.setkEGGReactionAsProduct(createKEGGReaction(emf, em, keggGlycan.getkEGGReactionAsProduct()));
            keggGlycan.setkEGGReactionAsSubstrate(createKEGGReaction(emf, em, keggGlycan.getkEGGReactionAsSubstrate()));
            keggGlycan.setDataSet(createDataSet(emf, em, keggGlycan.getDataSet()));
            em.persist(keggGlycan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKEGGGlycan(keggGlycan.getWid()) != null) {
                throw new PreexistingEntityException("KEGGGlycan " + keggGlycan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KEGGGlycan keggGlycan) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + keggGlycan.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KEGGGlycan persistentKEGGGlycan = em.find(KEGGGlycan.class, keggGlycan.getWid());
            Set<KEGGReaction> kEGGReactionAsProductOld = persistentKEGGGlycan.getkEGGReactionAsProduct();
            Set<KEGGReaction> kEGGReactionAsProductNew = keggGlycan.getkEGGReactionAsProduct();
            Set<KEGGReaction> kEGGReactionAsSubstrateOld = persistentKEGGGlycan.getkEGGReactionAsSubstrate();
            Set<KEGGReaction> kEGGReactionAsSubstrateNew = keggGlycan.getkEGGReactionAsSubstrate();
            Set<KEGGReaction> attachedkEGGReactionAsProductNew = new HashSet<>();
            for (KEGGReaction kEGGReactionAsProductNewKEGGReactionToAttach : kEGGReactionAsProductNew) {
                kEGGReactionAsProductNewKEGGReactionToAttach = em.getReference(kEGGReactionAsProductNewKEGGReactionToAttach.getClass(), kEGGReactionAsProductNewKEGGReactionToAttach.getWid());
                attachedkEGGReactionAsProductNew.add(kEGGReactionAsProductNewKEGGReactionToAttach);
            }
            kEGGReactionAsProductNew = attachedkEGGReactionAsProductNew;
            keggGlycan.setkEGGReactionAsProduct(kEGGReactionAsProductNew);
            Set<KEGGReaction> attachedkEGGReactionAsSubstrateNew = new HashSet<>();
            for (KEGGReaction kEGGReactionAsSubstrateNewKEGGReactionToAttach : kEGGReactionAsSubstrateNew) {
                kEGGReactionAsSubstrateNewKEGGReactionToAttach = em.getReference(kEGGReactionAsSubstrateNewKEGGReactionToAttach.getClass(), kEGGReactionAsSubstrateNewKEGGReactionToAttach.getWid());
                attachedkEGGReactionAsSubstrateNew.add(kEGGReactionAsSubstrateNewKEGGReactionToAttach);
            }
            kEGGReactionAsSubstrateNew = attachedkEGGReactionAsSubstrateNew;
            keggGlycan.setkEGGReactionAsSubstrate(kEGGReactionAsSubstrateNew);
            keggGlycan = em.merge(keggGlycan);
            for (KEGGReaction kEGGReactionAsProductOldKEGGReaction : kEGGReactionAsProductOld) {
                if (!kEGGReactionAsProductNew.contains(kEGGReactionAsProductOldKEGGReaction)) {
                    kEGGReactionAsProductOldKEGGReaction.getkEGGGlycanAsProduct().remove(keggGlycan);
                    kEGGReactionAsProductOldKEGGReaction = em.merge(kEGGReactionAsProductOldKEGGReaction);
                }
            }
            for (KEGGReaction kEGGReactionAsProductNewKEGGReaction : kEGGReactionAsProductNew) {
                if (!kEGGReactionAsProductOld.contains(kEGGReactionAsProductNewKEGGReaction)) {
                    kEGGReactionAsProductNewKEGGReaction.getkEGGGlycanAsProduct().add(keggGlycan);
                    kEGGReactionAsProductNewKEGGReaction = em.merge(kEGGReactionAsProductNewKEGGReaction);
                }
            }
            for (KEGGReaction kEGGReactionAsSubstrateOldKEGGReaction : kEGGReactionAsSubstrateOld) {
                if (!kEGGReactionAsSubstrateNew.contains(kEGGReactionAsSubstrateOldKEGGReaction)) {
                    kEGGReactionAsSubstrateOldKEGGReaction.getkEGGGlycanAsProduct().remove(keggGlycan);
                    kEGGReactionAsSubstrateOldKEGGReaction = em.merge(kEGGReactionAsSubstrateOldKEGGReaction);
                }
            }
            for (KEGGReaction kEGGReactionAsSubstrateNewKEGGReaction : kEGGReactionAsSubstrateNew) {
                if (!kEGGReactionAsSubstrateOld.contains(kEGGReactionAsSubstrateNewKEGGReaction)) {
                    kEGGReactionAsSubstrateNewKEGGReaction.getkEGGGlycanAsProduct().add(keggGlycan);
                    kEGGReactionAsSubstrateNewKEGGReaction = em.merge(kEGGReactionAsSubstrateNewKEGGReaction);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = keggGlycan.getWid();
                if (findKEGGGlycan(id) == null) {
                    throw new NonexistentEntityException("The kEGGGlycan with id " + id + " no longer exists.");
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
            KEGGGlycan KEGGGlycan;
            try {
                KEGGGlycan = em.getReference(KEGGGlycan.class, id);
                KEGGGlycan.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The KEGGGlycan with id " + id + " no longer exists.", enfe);
            }
            Set<KEGGReaction> kEGGReactionAsProduct = KEGGGlycan.getkEGGReactionAsProduct();
            for (KEGGReaction kEGGReactionAsProductKEGGReaction : kEGGReactionAsProduct) {
                kEGGReactionAsProductKEGGReaction.getkEGGGlycanAsProduct().remove(KEGGGlycan);
                kEGGReactionAsProductKEGGReaction = em.merge(kEGGReactionAsProductKEGGReaction);
            }
            Set<KEGGReaction> kEGGReactionAsSubstrate = KEGGGlycan.getkEGGReactionAsSubstrate();
            for (KEGGReaction kEGGReactionAsSubstrateKEGGReaction : kEGGReactionAsSubstrate) {
                kEGGReactionAsSubstrateKEGGReaction.getkEGGGlycanAsProduct().remove(KEGGGlycan);
                kEGGReactionAsSubstrateKEGGReaction = em.merge(kEGGReactionAsSubstrateKEGGReaction);
            }
            em.remove(KEGGGlycan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KEGGGlycan> findKEGGGlycanEntities() {
        return findKEGGGlycanEntities(true, -1, -1);
    }

    public List<KEGGGlycan> findKEGGGlycanEntities(int maxResults, int firstResult) {
        return findKEGGGlycanEntities(false, maxResults, firstResult);
    }

    private List<KEGGGlycan> findKEGGGlycanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KEGGGlycan.class));
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

    public KEGGGlycan findKEGGGlycan(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KEGGGlycan.class, id);
        } finally {
            em.close();
        }
    }

    public int getKEGGGlycanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KEGGGlycan> rt = cq.from(KEGGGlycan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
