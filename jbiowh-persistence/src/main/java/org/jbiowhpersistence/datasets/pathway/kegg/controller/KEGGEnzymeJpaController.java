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
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the KEGG Enzyme Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $
 * $LastChangedRevision: 396 $
 * @since Nov 17, 2011
 */
public class KEGGEnzymeJpaController extends AbstractKEGGJpaController<KEGGEnzyme> implements Serializable {

    public KEGGEnzymeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KEGGEnzyme keggEnzyme) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + keggEnzyme.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            keggEnzyme.setkEGGCompoundAsCofactor(createKEGGCompound(emf, em, keggEnzyme.getkEGGCompoundAsCofactor()));
            keggEnzyme.setkEGGCompoundAsEffector(createKEGGCompound(emf, em, keggEnzyme.getkEGGCompoundAsEffector()));
            keggEnzyme.setkEGGCompoundAsInhibitor(createKEGGCompound(emf, em, keggEnzyme.getkEGGCompoundAsInhibitor()));
            keggEnzyme.setkEGGReaction(createKEGGReaction(emf, em, keggEnzyme.getkEGGReaction()));
            keggEnzyme.setkEGGPathways(createKEGGPathway(emf, em, keggEnzyme.getkEGGPathways()));
            keggEnzyme.setProtein(createProtein(emf, em, keggEnzyme.getProtein()));
            keggEnzyme.setDataSet(createDataSet(emf, em, keggEnzyme.getDataSet()));
            em.persist(keggEnzyme);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKEGGEnzyme(keggEnzyme.getWid()) != null) {
                throw new PreexistingEntityException("KEGGEnzyme " + keggEnzyme + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KEGGEnzyme keggEnzyme) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + keggEnzyme.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KEGGEnzyme persistentKEGGEnzyme = em.find(KEGGEnzyme.class, keggEnzyme.getWid());
            Set<KEGGCompound> kEGGCompoundAsCofactorOld = persistentKEGGEnzyme.getkEGGCompoundAsCofactor();
            Set<KEGGCompound> kEGGCompoundAsCofactorNew = keggEnzyme.getkEGGCompoundAsCofactor();
            Set<KEGGCompound> kEGGCompoundAsEffectorOld = persistentKEGGEnzyme.getkEGGCompoundAsEffector();
            Set<KEGGCompound> kEGGCompoundAsEffectorNew = keggEnzyme.getkEGGCompoundAsEffector();
            Set<KEGGCompound> kEGGCompoundAsInhibitorOld = persistentKEGGEnzyme.getkEGGCompoundAsInhibitor();
            Set<KEGGCompound> kEGGCompoundAsInhibitorNew = keggEnzyme.getkEGGCompoundAsInhibitor();
            Set<KEGGReaction> kEGGReactionOld = persistentKEGGEnzyme.getkEGGReaction();
            Set<KEGGReaction> kEGGReactionNew = keggEnzyme.getkEGGReaction();
            Set<KEGGCompound> attachedkEGGCompoundAsCofactorNew = new HashSet<>();
            for (KEGGCompound kEGGCompoundAsCofactorNewKEGGCompoundToAttach : kEGGCompoundAsCofactorNew) {
                kEGGCompoundAsCofactorNewKEGGCompoundToAttach = em.getReference(kEGGCompoundAsCofactorNewKEGGCompoundToAttach.getClass(), kEGGCompoundAsCofactorNewKEGGCompoundToAttach.getWid());
                attachedkEGGCompoundAsCofactorNew.add(kEGGCompoundAsCofactorNewKEGGCompoundToAttach);
            }
            kEGGCompoundAsCofactorNew = attachedkEGGCompoundAsCofactorNew;
            keggEnzyme.setkEGGCompoundAsCofactor(kEGGCompoundAsCofactorNew);
            Set<KEGGCompound> attachedkEGGCompoundAsEffectorNew = new HashSet<>();
            for (KEGGCompound kEGGCompoundAsEffectorNewKEGGCompoundToAttach : kEGGCompoundAsEffectorNew) {
                kEGGCompoundAsEffectorNewKEGGCompoundToAttach = em.getReference(kEGGCompoundAsEffectorNewKEGGCompoundToAttach.getClass(), kEGGCompoundAsEffectorNewKEGGCompoundToAttach.getWid());
                attachedkEGGCompoundAsEffectorNew.add(kEGGCompoundAsEffectorNewKEGGCompoundToAttach);
            }
            kEGGCompoundAsEffectorNew = attachedkEGGCompoundAsEffectorNew;
            keggEnzyme.setkEGGCompoundAsEffector(kEGGCompoundAsEffectorNew);
            Set<KEGGCompound> attachedkEGGCompoundAsInhibitorNew = new HashSet<>();
            for (KEGGCompound kEGGCompoundAsInhibitorNewKEGGCompoundToAttach : kEGGCompoundAsInhibitorNew) {
                kEGGCompoundAsInhibitorNewKEGGCompoundToAttach = em.getReference(kEGGCompoundAsInhibitorNewKEGGCompoundToAttach.getClass(), kEGGCompoundAsInhibitorNewKEGGCompoundToAttach.getWid());
                attachedkEGGCompoundAsInhibitorNew.add(kEGGCompoundAsInhibitorNewKEGGCompoundToAttach);
            }
            kEGGCompoundAsInhibitorNew = attachedkEGGCompoundAsInhibitorNew;
            keggEnzyme.setkEGGCompoundAsInhibitor(kEGGCompoundAsInhibitorNew);
            Set<KEGGReaction> attachedkEGGReactionNew = new HashSet<>();
            for (KEGGReaction kEGGReactionNewKEGGReactionToAttach : kEGGReactionNew) {
                kEGGReactionNewKEGGReactionToAttach = em.getReference(kEGGReactionNewKEGGReactionToAttach.getClass(), kEGGReactionNewKEGGReactionToAttach.getWid());
                attachedkEGGReactionNew.add(kEGGReactionNewKEGGReactionToAttach);
            }
            kEGGReactionNew = attachedkEGGReactionNew;
            keggEnzyme.setkEGGReaction(kEGGReactionNew);
            keggEnzyme = em.merge(keggEnzyme);
            for (KEGGCompound kEGGCompoundAsCofactorOldKEGGCompound : kEGGCompoundAsCofactorOld) {
                if (!kEGGCompoundAsCofactorNew.contains(kEGGCompoundAsCofactorOldKEGGCompound)) {
                    kEGGCompoundAsCofactorOldKEGGCompound.getkEGGEnzymeAsCofactor().remove(keggEnzyme);
                    kEGGCompoundAsCofactorOldKEGGCompound = em.merge(kEGGCompoundAsCofactorOldKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundAsCofactorNewKEGGCompound : kEGGCompoundAsCofactorNew) {
                if (!kEGGCompoundAsCofactorOld.contains(kEGGCompoundAsCofactorNewKEGGCompound)) {
                    kEGGCompoundAsCofactorNewKEGGCompound.getkEGGEnzymeAsCofactor().add(keggEnzyme);
                    kEGGCompoundAsCofactorNewKEGGCompound = em.merge(kEGGCompoundAsCofactorNewKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundAsEffectorOldKEGGCompound : kEGGCompoundAsEffectorOld) {
                if (!kEGGCompoundAsEffectorNew.contains(kEGGCompoundAsEffectorOldKEGGCompound)) {
                    kEGGCompoundAsEffectorOldKEGGCompound.getkEGGEnzymeAsCofactor().remove(keggEnzyme);
                    kEGGCompoundAsEffectorOldKEGGCompound = em.merge(kEGGCompoundAsEffectorOldKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundAsEffectorNewKEGGCompound : kEGGCompoundAsEffectorNew) {
                if (!kEGGCompoundAsEffectorOld.contains(kEGGCompoundAsEffectorNewKEGGCompound)) {
                    kEGGCompoundAsEffectorNewKEGGCompound.getkEGGEnzymeAsCofactor().add(keggEnzyme);
                    kEGGCompoundAsEffectorNewKEGGCompound = em.merge(kEGGCompoundAsEffectorNewKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundAsInhibitorOldKEGGCompound : kEGGCompoundAsInhibitorOld) {
                if (!kEGGCompoundAsInhibitorNew.contains(kEGGCompoundAsInhibitorOldKEGGCompound)) {
                    kEGGCompoundAsInhibitorOldKEGGCompound.getkEGGEnzymeAsCofactor().remove(keggEnzyme);
                    kEGGCompoundAsInhibitorOldKEGGCompound = em.merge(kEGGCompoundAsInhibitorOldKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundAsInhibitorNewKEGGCompound : kEGGCompoundAsInhibitorNew) {
                if (!kEGGCompoundAsInhibitorOld.contains(kEGGCompoundAsInhibitorNewKEGGCompound)) {
                    kEGGCompoundAsInhibitorNewKEGGCompound.getkEGGEnzymeAsCofactor().add(keggEnzyme);
                    kEGGCompoundAsInhibitorNewKEGGCompound = em.merge(kEGGCompoundAsInhibitorNewKEGGCompound);
                }
            }
            for (KEGGReaction kEGGReactionOldKEGGReaction : kEGGReactionOld) {
                if (!kEGGReactionNew.contains(kEGGReactionOldKEGGReaction)) {
                    kEGGReactionOldKEGGReaction.getkEGGEnzyme().remove(keggEnzyme);
                    kEGGReactionOldKEGGReaction = em.merge(kEGGReactionOldKEGGReaction);
                }
            }
            for (KEGGReaction kEGGReactionNewKEGGReaction : kEGGReactionNew) {
                if (!kEGGReactionOld.contains(kEGGReactionNewKEGGReaction)) {
                    kEGGReactionNewKEGGReaction.getkEGGEnzyme().add(keggEnzyme);
                    kEGGReactionNewKEGGReaction = em.merge(kEGGReactionNewKEGGReaction);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = keggEnzyme.getWid();
                if (findKEGGEnzyme(id) == null) {
                    throw new NonexistentEntityException("The kEGGEnzyme with id " + id + " no longer exists.");
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
            KEGGEnzyme KEGGEnzyme;
            try {
                KEGGEnzyme = em.getReference(KEGGEnzyme.class, id);
                KEGGEnzyme.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The KEGGEnzyme with id " + id + " no longer exists.", enfe);
            }
            Set<KEGGCompound> kEGGCompoundAsCofactor = KEGGEnzyme.getkEGGCompoundAsCofactor();
            for (KEGGCompound kEGGCompoundAsCofactorKEGGCompound : kEGGCompoundAsCofactor) {
                kEGGCompoundAsCofactorKEGGCompound.getkEGGEnzymeAsCofactor().remove(KEGGEnzyme);
                kEGGCompoundAsCofactorKEGGCompound = em.merge(kEGGCompoundAsCofactorKEGGCompound);
            }
            Set<KEGGCompound> kEGGCompoundAsEffector = KEGGEnzyme.getkEGGCompoundAsEffector();
            for (KEGGCompound kEGGCompoundAsEffectorKEGGCompound : kEGGCompoundAsEffector) {
                kEGGCompoundAsEffectorKEGGCompound.getkEGGEnzymeAsCofactor().remove(KEGGEnzyme);
                kEGGCompoundAsEffectorKEGGCompound = em.merge(kEGGCompoundAsEffectorKEGGCompound);
            }
            Set<KEGGCompound> kEGGCompoundAsInhibitor = KEGGEnzyme.getkEGGCompoundAsInhibitor();
            for (KEGGCompound kEGGCompoundAsInhibitorKEGGCompound : kEGGCompoundAsInhibitor) {
                kEGGCompoundAsInhibitorKEGGCompound.getkEGGEnzymeAsCofactor().remove(KEGGEnzyme);
                kEGGCompoundAsInhibitorKEGGCompound = em.merge(kEGGCompoundAsInhibitorKEGGCompound);
            }
            Set<KEGGReaction> kEGGReaction = KEGGEnzyme.getkEGGReaction();
            for (KEGGReaction kEGGReactionKEGGReaction : kEGGReaction) {
                kEGGReactionKEGGReaction.getkEGGEnzyme().remove(KEGGEnzyme);
                kEGGReactionKEGGReaction = em.merge(kEGGReactionKEGGReaction);
            }
            em.remove(KEGGEnzyme);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KEGGEnzyme> findKEGGEnzymeEntities() {
        return findKEGGEnzymeEntities(true, -1, -1);
    }

    public List<KEGGEnzyme> findKEGGEnzymeEntities(int maxResults, int firstResult) {
        return findKEGGEnzymeEntities(false, maxResults, firstResult);
    }

    private List<KEGGEnzyme> findKEGGEnzymeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KEGGEnzyme.class));
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

    public KEGGEnzyme findKEGGEnzyme(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KEGGEnzyme.class, id);
        } finally {
            em.close();
        }
    }

    public int getKEGGEnzymeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KEGGEnzyme> rt = cq.from(KEGGEnzyme.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
