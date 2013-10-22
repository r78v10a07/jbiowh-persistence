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
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the KEGG Reaction Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $
 * $LastChangedRevision: 396 $
 * @since Nov 17, 2011
 */
public class KEGGReactionJpaController extends AbstractKEGGJpaController<KEGGReaction> implements Serializable {

    public KEGGReactionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KEGGReaction keggReaction) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + keggReaction.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            keggReaction.setkEGGEnzyme(createKEGGEnzyme(emf, em, keggReaction.getkEGGEnzyme()));
            keggReaction.setkEGGGlycanAsProduct(createKEGGGlycan(emf, em, keggReaction.getkEGGGlycanAsProduct()));
            keggReaction.setkEGGGlycanAsSubstrate(createKEGGGlycan(emf, em, keggReaction.getkEGGGlycanAsSubstrate()));
            keggReaction.setkEGGCompoundAsProduct(createKEGGCompound(emf, em, keggReaction.getkEGGCompoundAsProduct()));
            keggReaction.setkEGGCompoundAsSubstrate(createKEGGCompound(emf, em, keggReaction.getkEGGCompoundAsSubstrate()));
            keggReaction.setkEGGPathways(createKEGGPathway(emf, em, keggReaction.getkEGGPathways()));
            keggReaction.setDataSet(createDataSet(emf, em, keggReaction.getDataSet()));            
            em.persist(keggReaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKEGGReaction(keggReaction.getWid()) != null) {
                throw new PreexistingEntityException("KEGGReaction " + keggReaction + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KEGGReaction keggReaction) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + keggReaction.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KEGGReaction persistentKEGGReaction = em.find(KEGGReaction.class, keggReaction.getWid());
            Set<KEGGEnzyme> kEGGEnzymeOld = persistentKEGGReaction.getkEGGEnzyme();
            Set<KEGGEnzyme> kEGGEnzymeNew = keggReaction.getkEGGEnzyme();
            Set<KEGGGlycan> kEGGGlycanAsProductOld = persistentKEGGReaction.getkEGGGlycanAsProduct();
            Set<KEGGGlycan> kEGGGlycanAsProductNew = keggReaction.getkEGGGlycanAsProduct();
            Set<KEGGGlycan> kEGGGlycanAsSubstrateOld = persistentKEGGReaction.getkEGGGlycanAsSubstrate();
            Set<KEGGGlycan> kEGGGlycanAsSubstrateNew = keggReaction.getkEGGGlycanAsSubstrate();
            Set<KEGGCompound> kEGGCompoundAsProductOld = persistentKEGGReaction.getkEGGCompoundAsProduct();
            Set<KEGGCompound> kEGGCompoundAsProductNew = keggReaction.getkEGGCompoundAsProduct();
            Set<KEGGCompound> kEGGCompoundAsSubstrateOld = persistentKEGGReaction.getkEGGCompoundAsSubstrate();
            Set<KEGGCompound> kEGGCompoundAsSubstrateNew = keggReaction.getkEGGCompoundAsSubstrate();
            Set<KEGGEnzyme> attachedkEGGEnzymeNew = new HashSet<>();
            for (KEGGEnzyme kEGGEnzymeNewKEGGEnzymeToAttach : kEGGEnzymeNew) {
                kEGGEnzymeNewKEGGEnzymeToAttach = em.getReference(kEGGEnzymeNewKEGGEnzymeToAttach.getClass(), kEGGEnzymeNewKEGGEnzymeToAttach.getWid());
                attachedkEGGEnzymeNew.add(kEGGEnzymeNewKEGGEnzymeToAttach);
            }
            kEGGEnzymeNew = attachedkEGGEnzymeNew;
            keggReaction.setkEGGEnzyme(kEGGEnzymeNew);
            Set<KEGGGlycan> attachedkEGGGlycanAsProductNew = new HashSet<>();
            for (KEGGGlycan kEGGGlycanAsProductNewKEGGGlycanToAttach : kEGGGlycanAsProductNew) {
                kEGGGlycanAsProductNewKEGGGlycanToAttach = em.getReference(kEGGGlycanAsProductNewKEGGGlycanToAttach.getClass(), kEGGGlycanAsProductNewKEGGGlycanToAttach.getWid());
                attachedkEGGGlycanAsProductNew.add(kEGGGlycanAsProductNewKEGGGlycanToAttach);
            }
            kEGGGlycanAsProductNew = attachedkEGGGlycanAsProductNew;
            keggReaction.setkEGGGlycanAsProduct(kEGGGlycanAsProductNew);
            Set<KEGGGlycan> attachedkEGGGlycanAsSubstrateNew = new HashSet<>();
            for (KEGGGlycan kEGGGlycanAsSubstrateNewKEGGGlycanToAttach : kEGGGlycanAsSubstrateNew) {
                kEGGGlycanAsSubstrateNewKEGGGlycanToAttach = em.getReference(kEGGGlycanAsSubstrateNewKEGGGlycanToAttach.getClass(), kEGGGlycanAsSubstrateNewKEGGGlycanToAttach.getWid());
                attachedkEGGGlycanAsSubstrateNew.add(kEGGGlycanAsSubstrateNewKEGGGlycanToAttach);
            }
            kEGGGlycanAsSubstrateNew = attachedkEGGGlycanAsSubstrateNew;
            keggReaction.setkEGGGlycanAsSubstrate(kEGGGlycanAsSubstrateNew);
            Set<KEGGCompound> attachedkEGGCompoundAsProductNew = new HashSet<>();
            for (KEGGCompound kEGGCompoundAsProductNewKEGGCompoundToAttach : kEGGCompoundAsProductNew) {
                kEGGCompoundAsProductNewKEGGCompoundToAttach = em.getReference(kEGGCompoundAsProductNewKEGGCompoundToAttach.getClass(), kEGGCompoundAsProductNewKEGGCompoundToAttach.getWid());
                attachedkEGGCompoundAsProductNew.add(kEGGCompoundAsProductNewKEGGCompoundToAttach);
            }
            kEGGCompoundAsProductNew = attachedkEGGCompoundAsProductNew;
            keggReaction.setkEGGCompoundAsProduct(kEGGCompoundAsProductNew);
            Set<KEGGCompound> attachedkEGGCompoundAsSubstrateNew = new HashSet<>();
            for (KEGGCompound kEGGCompoundAsSubstrateNewKEGGCompoundToAttach : kEGGCompoundAsSubstrateNew) {
                kEGGCompoundAsSubstrateNewKEGGCompoundToAttach = em.getReference(kEGGCompoundAsSubstrateNewKEGGCompoundToAttach.getClass(), kEGGCompoundAsSubstrateNewKEGGCompoundToAttach.getWid());
                attachedkEGGCompoundAsSubstrateNew.add(kEGGCompoundAsSubstrateNewKEGGCompoundToAttach);
            }
            kEGGCompoundAsSubstrateNew = attachedkEGGCompoundAsSubstrateNew;
            keggReaction.setkEGGCompoundAsSubstrate(kEGGCompoundAsSubstrateNew);
            keggReaction = em.merge(keggReaction);
            for (KEGGEnzyme kEGGEnzymeOldKEGGEnzyme : kEGGEnzymeOld) {
                if (!kEGGEnzymeNew.contains(kEGGEnzymeOldKEGGEnzyme)) {
                    kEGGEnzymeOldKEGGEnzyme.getkEGGReaction().remove(keggReaction);
                    kEGGEnzymeOldKEGGEnzyme = em.merge(kEGGEnzymeOldKEGGEnzyme);
                }
            }
            for (KEGGEnzyme kEGGEnzymeNewKEGGEnzyme : kEGGEnzymeNew) {
                if (!kEGGEnzymeOld.contains(kEGGEnzymeNewKEGGEnzyme)) {
                    kEGGEnzymeNewKEGGEnzyme.getkEGGReaction().add(keggReaction);
                    kEGGEnzymeNewKEGGEnzyme = em.merge(kEGGEnzymeNewKEGGEnzyme);
                }
            }
            for (KEGGGlycan kEGGGlycanAsProductOldKEGGGlycan : kEGGGlycanAsProductOld) {
                if (!kEGGGlycanAsProductNew.contains(kEGGGlycanAsProductOldKEGGGlycan)) {
                    kEGGGlycanAsProductOldKEGGGlycan.getkEGGReactionAsProduct().remove(keggReaction);
                    kEGGGlycanAsProductOldKEGGGlycan = em.merge(kEGGGlycanAsProductOldKEGGGlycan);
                }
            }
            for (KEGGGlycan kEGGGlycanAsProductNewKEGGGlycan : kEGGGlycanAsProductNew) {
                if (!kEGGGlycanAsProductOld.contains(kEGGGlycanAsProductNewKEGGGlycan)) {
                    kEGGGlycanAsProductNewKEGGGlycan.getkEGGReactionAsProduct().add(keggReaction);
                    kEGGGlycanAsProductNewKEGGGlycan = em.merge(kEGGGlycanAsProductNewKEGGGlycan);
                }
            }
            for (KEGGGlycan kEGGGlycanAsSubstrateOldKEGGGlycan : kEGGGlycanAsSubstrateOld) {
                if (!kEGGGlycanAsSubstrateNew.contains(kEGGGlycanAsSubstrateOldKEGGGlycan)) {
                    kEGGGlycanAsSubstrateOldKEGGGlycan.getkEGGReactionAsProduct().remove(keggReaction);
                    kEGGGlycanAsSubstrateOldKEGGGlycan = em.merge(kEGGGlycanAsSubstrateOldKEGGGlycan);
                }
            }
            for (KEGGGlycan kEGGGlycanAsSubstrateNewKEGGGlycan : kEGGGlycanAsSubstrateNew) {
                if (!kEGGGlycanAsSubstrateOld.contains(kEGGGlycanAsSubstrateNewKEGGGlycan)) {
                    kEGGGlycanAsSubstrateNewKEGGGlycan.getkEGGReactionAsProduct().add(keggReaction);
                    kEGGGlycanAsSubstrateNewKEGGGlycan = em.merge(kEGGGlycanAsSubstrateNewKEGGGlycan);
                }
            }
            for (KEGGCompound kEGGCompoundAsProductOldKEGGCompound : kEGGCompoundAsProductOld) {
                if (!kEGGCompoundAsProductNew.contains(kEGGCompoundAsProductOldKEGGCompound)) {
                    kEGGCompoundAsProductOldKEGGCompound.getkEGGReactionAsProduct().remove(keggReaction);
                    kEGGCompoundAsProductOldKEGGCompound = em.merge(kEGGCompoundAsProductOldKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundAsProductNewKEGGCompound : kEGGCompoundAsProductNew) {
                if (!kEGGCompoundAsProductOld.contains(kEGGCompoundAsProductNewKEGGCompound)) {
                    kEGGCompoundAsProductNewKEGGCompound.getkEGGReactionAsProduct().add(keggReaction);
                    kEGGCompoundAsProductNewKEGGCompound = em.merge(kEGGCompoundAsProductNewKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundAsSubstrateOldKEGGCompound : kEGGCompoundAsSubstrateOld) {
                if (!kEGGCompoundAsSubstrateNew.contains(kEGGCompoundAsSubstrateOldKEGGCompound)) {
                    kEGGCompoundAsSubstrateOldKEGGCompound.getkEGGReactionAsProduct().remove(keggReaction);
                    kEGGCompoundAsSubstrateOldKEGGCompound = em.merge(kEGGCompoundAsSubstrateOldKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundAsSubstrateNewKEGGCompound : kEGGCompoundAsSubstrateNew) {
                if (!kEGGCompoundAsSubstrateOld.contains(kEGGCompoundAsSubstrateNewKEGGCompound)) {
                    kEGGCompoundAsSubstrateNewKEGGCompound.getkEGGReactionAsProduct().add(keggReaction);
                    kEGGCompoundAsSubstrateNewKEGGCompound = em.merge(kEGGCompoundAsSubstrateNewKEGGCompound);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = keggReaction.getWid();
                if (findKEGGReaction(id) == null) {
                    throw new NonexistentEntityException("The kEGGReaction with id " + id + " no longer exists.");
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
            KEGGReaction KEGGReaction;
            try {
                KEGGReaction = em.getReference(KEGGReaction.class, id);
                KEGGReaction.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The KEGGReaction with id " + id + " no longer exists.", enfe);
            }
            Set<KEGGEnzyme> kEGGEnzyme = KEGGReaction.getkEGGEnzyme();
            for (KEGGEnzyme kEGGEnzymeKEGGEnzyme : kEGGEnzyme) {
                kEGGEnzymeKEGGEnzyme.getkEGGReaction().remove(KEGGReaction);
                kEGGEnzymeKEGGEnzyme = em.merge(kEGGEnzymeKEGGEnzyme);
            }
            Set<KEGGGlycan> kEGGGlycanAsProduct = KEGGReaction.getkEGGGlycanAsProduct();
            for (KEGGGlycan kEGGGlycanAsProductKEGGGlycan : kEGGGlycanAsProduct) {
                kEGGGlycanAsProductKEGGGlycan.getkEGGReactionAsProduct().remove(KEGGReaction);
                kEGGGlycanAsProductKEGGGlycan = em.merge(kEGGGlycanAsProductKEGGGlycan);
            }
            Set<KEGGGlycan> kEGGGlycanAsSubstrate = KEGGReaction.getkEGGGlycanAsSubstrate();
            for (KEGGGlycan kEGGGlycanAsSubstrateKEGGGlycan : kEGGGlycanAsSubstrate) {
                kEGGGlycanAsSubstrateKEGGGlycan.getkEGGReactionAsProduct().remove(KEGGReaction);
                kEGGGlycanAsSubstrateKEGGGlycan = em.merge(kEGGGlycanAsSubstrateKEGGGlycan);
            }
            Set<KEGGCompound> kEGGCompoundAsProduct = KEGGReaction.getkEGGCompoundAsProduct();
            for (KEGGCompound kEGGCompoundAsProductKEGGCompound : kEGGCompoundAsProduct) {
                kEGGCompoundAsProductKEGGCompound.getkEGGReactionAsProduct().remove(KEGGReaction);
                kEGGCompoundAsProductKEGGCompound = em.merge(kEGGCompoundAsProductKEGGCompound);
            }
            Set<KEGGCompound> kEGGCompoundAsSubstrate = KEGGReaction.getkEGGCompoundAsSubstrate();
            for (KEGGCompound kEGGCompoundAsSubstrateKEGGCompound : kEGGCompoundAsSubstrate) {
                kEGGCompoundAsSubstrateKEGGCompound.getkEGGReactionAsProduct().remove(KEGGReaction);
                kEGGCompoundAsSubstrateKEGGCompound = em.merge(kEGGCompoundAsSubstrateKEGGCompound);
            }
            em.remove(KEGGReaction);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KEGGReaction> findKEGGReactionEntities() {
        return findKEGGReactionEntities(true, -1, -1);
    }

    public List<KEGGReaction> findKEGGReactionEntities(int maxResults, int firstResult) {
        return findKEGGReactionEntities(false, maxResults, firstResult);
    }

    private List<KEGGReaction> findKEGGReactionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KEGGReaction.class));
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

    public KEGGReaction findKEGGReaction(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KEGGReaction.class, id);
        } finally {
            em.close();
        }
    }

    public int getKEGGReactionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KEGGReaction> rt = cq.from(KEGGReaction.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
