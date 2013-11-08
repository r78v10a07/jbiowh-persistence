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
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the KEGG Compound Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Nov 17, 2011
 */
public class KEGGCompoundJpaController extends AbstractKEGGJpaController<KEGGCompound> implements Serializable {

    public KEGGCompoundJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KEGGCompound keggCompound) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + keggCompound.getWid());
        if (keggCompound.getkEGGRPair() == null) {
            keggCompound.setkEGGRPair(new HashSet<KEGGRPair>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!keggCompound.getkEGGRPair().isEmpty()) {
                Set<KEGGRPair> attachedkEGGRPair = new HashSet();
                for (KEGGRPair kEGGRPairKEGGRPairToAttach : keggCompound.getkEGGRPair()) {
                    KEGGRPair kEGGRPairKEGG = em.find(kEGGRPairKEGGRPairToAttach.getClass(), kEGGRPairKEGGRPairToAttach.getWid());
                    if (kEGGRPairKEGG != null) {
                        attachedkEGGRPair.add(kEGGRPairKEGG);
                    } else {
                        attachedkEGGRPair.add(kEGGRPairKEGGRPairToAttach);
                    }
                }
                keggCompound.setkEGGRPair(attachedkEGGRPair);
            }
            
            keggCompound.setkEGGEnzymeAsCofactor(createKEGGEnzyme(emf, em, keggCompound.getkEGGEnzymeAsCofactor()));
            keggCompound.setkEGGEnzymeAsEffector(createKEGGEnzyme(emf, em, keggCompound.getkEGGEnzymeAsEffector()));
            keggCompound.setkEGGEnzymeAsInhibitor(createKEGGEnzyme(emf, em, keggCompound.getkEGGEnzymeAsInhibitor()));
            keggCompound.setkEGGReactionAsProduct(createKEGGReaction(emf, em, keggCompound.getkEGGReactionAsProduct()));
            keggCompound.setkEGGReactionAsSubstrate(createKEGGReaction(emf, em, keggCompound.getkEGGReactionAsSubstrate()));
            keggCompound.setDrugBanks(createDrugBank(emf, em, keggCompound.getDrugBanks()));
            keggCompound.setkEGGPathways(createKEGGPathway(emf, em, keggCompound.getkEGGPathways()));
            keggCompound.setDataSet(createDataSet(emf, em, keggCompound.getDataSet()));
            em.persist(keggCompound);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKEGGCompound(keggCompound.getWid()) != null) {
                throw new PreexistingEntityException("KEGGCompound " + keggCompound + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KEGGCompound keggCompound) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + keggCompound.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KEGGCompound persistentKEGGCompound = em.find(KEGGCompound.class, keggCompound.getWid());
            Set<KEGGRPair> kEGGRPairOld = persistentKEGGCompound.getkEGGRPair();
            Set<KEGGRPair> kEGGRPairNew = keggCompound.getkEGGRPair();
            Set<KEGGEnzyme> kEGGEnzymeAsCofactorOld = persistentKEGGCompound.getkEGGEnzymeAsCofactor();
            Set<KEGGEnzyme> kEGGEnzymeAsCofactorNew = keggCompound.getkEGGEnzymeAsCofactor();
            Set<KEGGEnzyme> kEGGEnzymeAsEffectorOld = persistentKEGGCompound.getkEGGEnzymeAsEffector();
            Set<KEGGEnzyme> kEGGEnzymeAsEffectorNew = keggCompound.getkEGGEnzymeAsEffector();
            Set<KEGGEnzyme> kEGGEnzymeAsInhibitorOld = persistentKEGGCompound.getkEGGEnzymeAsInhibitor();
            Set<KEGGEnzyme> kEGGEnzymeAsInhibitorNew = keggCompound.getkEGGEnzymeAsInhibitor();
            Set<KEGGReaction> kEGGReactionAsProductOld = persistentKEGGCompound.getkEGGReactionAsProduct();
            Set<KEGGReaction> kEGGReactionAsProductNew = keggCompound.getkEGGReactionAsProduct();
            Set<KEGGReaction> kEGGReactionAsSubstrateOld = persistentKEGGCompound.getkEGGReactionAsSubstrate();
            Set<KEGGReaction> kEGGReactionAsSubstrateNew = keggCompound.getkEGGReactionAsSubstrate();
            Set<KEGGRPair> attachedkEGGRPairNew = new HashSet();
            for (KEGGRPair kEGGRPairNewKEGGRPairToAttach : kEGGRPairNew) {
                kEGGRPairNewKEGGRPairToAttach = em.getReference(kEGGRPairNewKEGGRPairToAttach.getClass(), kEGGRPairNewKEGGRPairToAttach.getWid());
                attachedkEGGRPairNew.add(kEGGRPairNewKEGGRPairToAttach);
            }
            kEGGRPairNew = attachedkEGGRPairNew;
            keggCompound.setkEGGRPair(kEGGRPairNew);
            Set<KEGGEnzyme> attachedkEGGEnzymeAsCofactorNew = new HashSet();
            for (KEGGEnzyme kEGGEnzymeAsCofactorNewKEGGEnzymeToAttach : kEGGEnzymeAsCofactorNew) {
                kEGGEnzymeAsCofactorNewKEGGEnzymeToAttach = em.getReference(kEGGEnzymeAsCofactorNewKEGGEnzymeToAttach.getClass(), kEGGEnzymeAsCofactorNewKEGGEnzymeToAttach.getWid());
                attachedkEGGEnzymeAsCofactorNew.add(kEGGEnzymeAsCofactorNewKEGGEnzymeToAttach);
            }
            kEGGEnzymeAsCofactorNew = attachedkEGGEnzymeAsCofactorNew;
            keggCompound.setkEGGEnzymeAsCofactor(kEGGEnzymeAsCofactorNew);
            Set<KEGGEnzyme> attachedkEGGEnzymeAsEffectorNew = new HashSet();
            for (KEGGEnzyme kEGGEnzymeAsEffectorNewKEGGEnzymeToAttach : kEGGEnzymeAsEffectorNew) {
                kEGGEnzymeAsEffectorNewKEGGEnzymeToAttach = em.getReference(kEGGEnzymeAsEffectorNewKEGGEnzymeToAttach.getClass(), kEGGEnzymeAsEffectorNewKEGGEnzymeToAttach.getWid());
                attachedkEGGEnzymeAsEffectorNew.add(kEGGEnzymeAsEffectorNewKEGGEnzymeToAttach);
            }
            kEGGEnzymeAsEffectorNew = attachedkEGGEnzymeAsEffectorNew;
            keggCompound.setkEGGEnzymeAsEffector(kEGGEnzymeAsEffectorNew);
            Set<KEGGEnzyme> attachedkEGGEnzymeAsInhibitorNew = new HashSet();
            for (KEGGEnzyme kEGGEnzymeAsInhibitorNewKEGGEnzymeToAttach : kEGGEnzymeAsInhibitorNew) {
                kEGGEnzymeAsInhibitorNewKEGGEnzymeToAttach = em.getReference(kEGGEnzymeAsInhibitorNewKEGGEnzymeToAttach.getClass(), kEGGEnzymeAsInhibitorNewKEGGEnzymeToAttach.getWid());
                attachedkEGGEnzymeAsInhibitorNew.add(kEGGEnzymeAsInhibitorNewKEGGEnzymeToAttach);
            }
            kEGGEnzymeAsInhibitorNew = attachedkEGGEnzymeAsInhibitorNew;
            keggCompound.setkEGGEnzymeAsInhibitor(kEGGEnzymeAsInhibitorNew);
            Set<KEGGReaction> attachedkEGGReactionAsProductNew = new HashSet();
            for (KEGGReaction kEGGReactionAsProductNewKEGGReactionToAttach : kEGGReactionAsProductNew) {
                kEGGReactionAsProductNewKEGGReactionToAttach = em.getReference(kEGGReactionAsProductNewKEGGReactionToAttach.getClass(), kEGGReactionAsProductNewKEGGReactionToAttach.getWid());
                attachedkEGGReactionAsProductNew.add(kEGGReactionAsProductNewKEGGReactionToAttach);
            }
            kEGGReactionAsProductNew = attachedkEGGReactionAsProductNew;
            keggCompound.setkEGGReactionAsProduct(kEGGReactionAsProductNew);
            Set<KEGGReaction> attachedkEGGReactionAsSubstrateNew = new HashSet();
            for (KEGGReaction kEGGReactionAsSubstrateNewKEGGReactionToAttach : kEGGReactionAsSubstrateNew) {
                kEGGReactionAsSubstrateNewKEGGReactionToAttach = em.getReference(kEGGReactionAsSubstrateNewKEGGReactionToAttach.getClass(), kEGGReactionAsSubstrateNewKEGGReactionToAttach.getWid());
                attachedkEGGReactionAsSubstrateNew.add(kEGGReactionAsSubstrateNewKEGGReactionToAttach);
            }
            kEGGReactionAsSubstrateNew = attachedkEGGReactionAsSubstrateNew;
            keggCompound.setkEGGReactionAsSubstrate(kEGGReactionAsSubstrateNew);
            keggCompound = em.merge(keggCompound);
            for (KEGGRPair kEGGRPairOldKEGGRPair : kEGGRPairOld) {
                if (!kEGGRPairNew.contains(kEGGRPairOldKEGGRPair)) {
                    kEGGRPairOldKEGGRPair.getkEGGCompound().remove(keggCompound);
                    kEGGRPairOldKEGGRPair = em.merge(kEGGRPairOldKEGGRPair);
                }
            }
            for (KEGGRPair kEGGRPairNewKEGGRPair : kEGGRPairNew) {
                if (!kEGGRPairOld.contains(kEGGRPairNewKEGGRPair)) {
                    kEGGRPairNewKEGGRPair.getkEGGCompound().add(keggCompound);
                    kEGGRPairNewKEGGRPair = em.merge(kEGGRPairNewKEGGRPair);
                }
            }
            for (KEGGEnzyme kEGGEnzymeAsCofactorOldKEGGEnzyme : kEGGEnzymeAsCofactorOld) {
                if (!kEGGEnzymeAsCofactorNew.contains(kEGGEnzymeAsCofactorOldKEGGEnzyme)) {
                    kEGGEnzymeAsCofactorOldKEGGEnzyme.getkEGGCompoundAsCofactor().remove(keggCompound);
                    kEGGEnzymeAsCofactorOldKEGGEnzyme = em.merge(kEGGEnzymeAsCofactorOldKEGGEnzyme);
                }
            }
            for (KEGGEnzyme kEGGEnzymeAsCofactorNewKEGGEnzyme : kEGGEnzymeAsCofactorNew) {
                if (!kEGGEnzymeAsCofactorOld.contains(kEGGEnzymeAsCofactorNewKEGGEnzyme)) {
                    kEGGEnzymeAsCofactorNewKEGGEnzyme.getkEGGCompoundAsCofactor().add(keggCompound);
                    kEGGEnzymeAsCofactorNewKEGGEnzyme = em.merge(kEGGEnzymeAsCofactorNewKEGGEnzyme);
                }
            }
            for (KEGGEnzyme kEGGEnzymeAsEffectorOldKEGGEnzyme : kEGGEnzymeAsEffectorOld) {
                if (!kEGGEnzymeAsEffectorNew.contains(kEGGEnzymeAsEffectorOldKEGGEnzyme)) {
                    kEGGEnzymeAsEffectorOldKEGGEnzyme.getkEGGCompoundAsCofactor().remove(keggCompound);
                    kEGGEnzymeAsEffectorOldKEGGEnzyme = em.merge(kEGGEnzymeAsEffectorOldKEGGEnzyme);
                }
            }
            for (KEGGEnzyme kEGGEnzymeAsEffectorNewKEGGEnzyme : kEGGEnzymeAsEffectorNew) {
                if (!kEGGEnzymeAsEffectorOld.contains(kEGGEnzymeAsEffectorNewKEGGEnzyme)) {
                    kEGGEnzymeAsEffectorNewKEGGEnzyme.getkEGGCompoundAsCofactor().add(keggCompound);
                    kEGGEnzymeAsEffectorNewKEGGEnzyme = em.merge(kEGGEnzymeAsEffectorNewKEGGEnzyme);
                }
            }
            for (KEGGEnzyme kEGGEnzymeAsInhibitorOldKEGGEnzyme : kEGGEnzymeAsInhibitorOld) {
                if (!kEGGEnzymeAsInhibitorNew.contains(kEGGEnzymeAsInhibitorOldKEGGEnzyme)) {
                    kEGGEnzymeAsInhibitorOldKEGGEnzyme.getkEGGCompoundAsCofactor().remove(keggCompound);
                    kEGGEnzymeAsInhibitorOldKEGGEnzyme = em.merge(kEGGEnzymeAsInhibitorOldKEGGEnzyme);
                }
            }
            for (KEGGEnzyme kEGGEnzymeAsInhibitorNewKEGGEnzyme : kEGGEnzymeAsInhibitorNew) {
                if (!kEGGEnzymeAsInhibitorOld.contains(kEGGEnzymeAsInhibitorNewKEGGEnzyme)) {
                    kEGGEnzymeAsInhibitorNewKEGGEnzyme.getkEGGCompoundAsCofactor().add(keggCompound);
                    kEGGEnzymeAsInhibitorNewKEGGEnzyme = em.merge(kEGGEnzymeAsInhibitorNewKEGGEnzyme);
                }
            }
            for (KEGGReaction kEGGReactionAsProductOldKEGGReaction : kEGGReactionAsProductOld) {
                if (!kEGGReactionAsProductNew.contains(kEGGReactionAsProductOldKEGGReaction)) {
                    kEGGReactionAsProductOldKEGGReaction.getkEGGCompoundAsProduct().remove(keggCompound);
                    kEGGReactionAsProductOldKEGGReaction = em.merge(kEGGReactionAsProductOldKEGGReaction);
                }
            }
            for (KEGGReaction kEGGReactionAsProductNewKEGGReaction : kEGGReactionAsProductNew) {
                if (!kEGGReactionAsProductOld.contains(kEGGReactionAsProductNewKEGGReaction)) {
                    kEGGReactionAsProductNewKEGGReaction.getkEGGCompoundAsProduct().add(keggCompound);
                    kEGGReactionAsProductNewKEGGReaction = em.merge(kEGGReactionAsProductNewKEGGReaction);
                }
            }
            for (KEGGReaction kEGGReactionAsSubstrateOldKEGGReaction : kEGGReactionAsSubstrateOld) {
                if (!kEGGReactionAsSubstrateNew.contains(kEGGReactionAsSubstrateOldKEGGReaction)) {
                    kEGGReactionAsSubstrateOldKEGGReaction.getkEGGCompoundAsProduct().remove(keggCompound);
                    kEGGReactionAsSubstrateOldKEGGReaction = em.merge(kEGGReactionAsSubstrateOldKEGGReaction);
                }
            }
            for (KEGGReaction kEGGReactionAsSubstrateNewKEGGReaction : kEGGReactionAsSubstrateNew) {
                if (!kEGGReactionAsSubstrateOld.contains(kEGGReactionAsSubstrateNewKEGGReaction)) {
                    kEGGReactionAsSubstrateNewKEGGReaction.getkEGGCompoundAsProduct().add(keggCompound);
                    kEGGReactionAsSubstrateNewKEGGReaction = em.merge(kEGGReactionAsSubstrateNewKEGGReaction);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = keggCompound.getWid();
                if (findKEGGCompound(id) == null) {
                    throw new NonexistentEntityException("The kEGGCompound with id " + id + " no longer exists.");
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
            KEGGCompound KEGGCompound;
            try {
                KEGGCompound = em.getReference(KEGGCompound.class, id);
                KEGGCompound.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The KEGGCompound with id " + id + " no longer exists.", enfe);
            }
            Set<KEGGRPair> kEGGRPair = KEGGCompound.getkEGGRPair();
            for (KEGGRPair kEGGRPairKEGGRPair : kEGGRPair) {
                kEGGRPairKEGGRPair.getkEGGCompound().remove(KEGGCompound);
                kEGGRPairKEGGRPair = em.merge(kEGGRPairKEGGRPair);
            }
            Set<KEGGEnzyme> kEGGEnzymeAsCofactor = KEGGCompound.getkEGGEnzymeAsCofactor();
            for (KEGGEnzyme kEGGEnzymeAsCofactorKEGGEnzyme : kEGGEnzymeAsCofactor) {
                kEGGEnzymeAsCofactorKEGGEnzyme.getkEGGCompoundAsCofactor().remove(KEGGCompound);
                kEGGEnzymeAsCofactorKEGGEnzyme = em.merge(kEGGEnzymeAsCofactorKEGGEnzyme);
            }
            Set<KEGGEnzyme> kEGGEnzymeAsEffector = KEGGCompound.getkEGGEnzymeAsEffector();
            for (KEGGEnzyme kEGGEnzymeAsEffectorKEGGEnzyme : kEGGEnzymeAsEffector) {
                kEGGEnzymeAsEffectorKEGGEnzyme.getkEGGCompoundAsCofactor().remove(KEGGCompound);
                kEGGEnzymeAsEffectorKEGGEnzyme = em.merge(kEGGEnzymeAsEffectorKEGGEnzyme);
            }
            Set<KEGGEnzyme> kEGGEnzymeAsInhibitor = KEGGCompound.getkEGGEnzymeAsInhibitor();
            for (KEGGEnzyme kEGGEnzymeAsInhibitorKEGGEnzyme : kEGGEnzymeAsInhibitor) {
                kEGGEnzymeAsInhibitorKEGGEnzyme.getkEGGCompoundAsCofactor().remove(KEGGCompound);
                kEGGEnzymeAsInhibitorKEGGEnzyme = em.merge(kEGGEnzymeAsInhibitorKEGGEnzyme);
            }
            Set<KEGGReaction> kEGGReactionAsProduct = KEGGCompound.getkEGGReactionAsProduct();
            for (KEGGReaction kEGGReactionAsProductKEGGReaction : kEGGReactionAsProduct) {
                kEGGReactionAsProductKEGGReaction.getkEGGCompoundAsProduct().remove(KEGGCompound);
                kEGGReactionAsProductKEGGReaction = em.merge(kEGGReactionAsProductKEGGReaction);
            }
            Set<KEGGReaction> kEGGReactionAsSubstrate = KEGGCompound.getkEGGReactionAsSubstrate();
            for (KEGGReaction kEGGReactionAsSubstrateKEGGReaction : kEGGReactionAsSubstrate) {
                kEGGReactionAsSubstrateKEGGReaction.getkEGGCompoundAsProduct().remove(KEGGCompound);
                kEGGReactionAsSubstrateKEGGReaction = em.merge(kEGGReactionAsSubstrateKEGGReaction);
            }
            em.remove(KEGGCompound);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KEGGCompound> findKEGGCompoundEntities() {
        return findKEGGCompoundEntities(true, -1, -1);
    }

    public List<KEGGCompound> findKEGGCompoundEntities(int maxResults, int firstResult) {
        return findKEGGCompoundEntities(false, maxResults, firstResult);
    }

    private List<KEGGCompound> findKEGGCompoundEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KEGGCompound.class));
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

    public KEGGCompound findKEGGCompound(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KEGGCompound.class, id);
        } finally {
            em.close();
        }
    }

    public int getKEGGCompoundCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KEGGCompound> rt = cq.from(KEGGCompound.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
