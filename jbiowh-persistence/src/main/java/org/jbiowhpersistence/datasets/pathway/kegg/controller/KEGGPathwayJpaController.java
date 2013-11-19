package org.jbiowhpersistence.datasets.pathway.kegg.controller;

import java.io.Serializable;
import java.util.Collection;
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
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the KEGGPathway Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Sep 17, 2012
 */
public class KEGGPathwayJpaController extends AbstractKEGGJpaController<KEGGPathway> implements Serializable {

    public KEGGPathwayJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KEGGPathway keggPathway) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + keggPathway.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            keggPathway.setGeneInfo(createGeneInfo(emf, em, keggPathway.getGeneInfo()));
            keggPathway.setProtein(createProtein(emf, em, keggPathway.getProtein()));
            keggPathway.setkEGGCompounds(createKEGGCompound(emf, em, keggPathway.getkEGGCompounds()));
            keggPathway.setkEGGEnzymes(createKEGGEnzyme(emf, em, keggPathway.getkEGGEnzymes()));
            keggPathway.setkEGGGenes(createKEGGGene(emf, em, keggPathway.getkEGGGenes()));
            keggPathway.setkEGGReactions(createKEGGReaction(emf, em, keggPathway.getkEGGReactions()));
            keggPathway.setTaxonomy(createTaxonomy(emf, em, keggPathway.getTaxonomy()));
            keggPathway.setDataSet(createDataSet(emf, em, keggPathway.getDataSet()));
            em.persist(keggPathway);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKEGGPathway(keggPathway.getWid()) != null) {
                throw new PreexistingEntityException("KEGGPathway " + keggPathway + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KEGGPathway keggPathway) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + keggPathway.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KEGGPathway persistentKEGGPathway = em.find(KEGGPathway.class, keggPathway.getWid());
            Collection<GeneInfo> geneInfoOld = persistentKEGGPathway.getGeneInfo();
            Collection<GeneInfo> geneInfoNew = keggPathway.getGeneInfo();
            Set<Protein> proteinOld = persistentKEGGPathway.getProtein();
            Set<Protein> proteinNew = keggPathway.getProtein();
            Set<KEGGCompound> kEGGCompoundsOld = persistentKEGGPathway.getkEGGCompounds();
            Set<KEGGCompound> kEGGCompoundsNew = keggPathway.getkEGGCompounds();
            Set<KEGGEnzyme> kEGGEnzymesOld = persistentKEGGPathway.getkEGGEnzymes();
            Set<KEGGEnzyme> kEGGEnzymesNew = keggPathway.getkEGGEnzymes();
            Set<KEGGGene> kEGGGenesOld = persistentKEGGPathway.getkEGGGenes();
            Set<KEGGGene> kEGGGenesNew = keggPathway.getkEGGGenes();
            Set<KEGGReaction> kEGGReactionsOld = persistentKEGGPathway.getkEGGReactions();
            Set<KEGGReaction> kEGGReactionsNew = keggPathway.getkEGGReactions();
            Set<GeneInfo> attachedGeneInfoNew = new HashSet();
            for (GeneInfo geneInfoNewGeneInfoToAttach : geneInfoNew) {
                geneInfoNewGeneInfoToAttach = em.getReference(geneInfoNewGeneInfoToAttach.getClass(), geneInfoNewGeneInfoToAttach.getWid());
                attachedGeneInfoNew.add(geneInfoNewGeneInfoToAttach);
            }
            geneInfoNew = attachedGeneInfoNew;
            keggPathway.setGeneInfo(geneInfoNew);
            Set<Protein> attachedProteinNew = new HashSet();
            for (Protein proteinNewProteinToAttach : proteinNew) {
                proteinNewProteinToAttach = em.getReference(proteinNewProteinToAttach.getClass(), proteinNewProteinToAttach.getWid());
                attachedProteinNew.add(proteinNewProteinToAttach);
            }
            proteinNew = attachedProteinNew;
            keggPathway.setProtein(proteinNew);
            Set<KEGGCompound> attachedkEGGCompoundsNew = new HashSet();
            for (KEGGCompound kEGGCompoundsNewKEGGCompoundToAttach : kEGGCompoundsNew) {
                kEGGCompoundsNewKEGGCompoundToAttach = em.getReference(kEGGCompoundsNewKEGGCompoundToAttach.getClass(), kEGGCompoundsNewKEGGCompoundToAttach.getWid());
                attachedkEGGCompoundsNew.add(kEGGCompoundsNewKEGGCompoundToAttach);
            }
            kEGGCompoundsNew = attachedkEGGCompoundsNew;
            keggPathway.setkEGGCompounds(kEGGCompoundsNew);
            Set<KEGGEnzyme> attachedkEGGEnzymesNew = new HashSet();
            for (KEGGEnzyme kEGGEnzymesNewKEGGEnzymeToAttach : kEGGEnzymesNew) {
                kEGGEnzymesNewKEGGEnzymeToAttach = em.getReference(kEGGEnzymesNewKEGGEnzymeToAttach.getClass(), kEGGEnzymesNewKEGGEnzymeToAttach.getWid());
                attachedkEGGEnzymesNew.add(kEGGEnzymesNewKEGGEnzymeToAttach);
            }
            kEGGEnzymesNew = attachedkEGGEnzymesNew;
            keggPathway.setkEGGEnzymes(kEGGEnzymesNew);
            Set<KEGGGene> attachedkEGGGenesNew = new HashSet();
            for (KEGGGene kEGGGenesNewKEGGGeneToAttach : kEGGGenesNew) {
                kEGGGenesNewKEGGGeneToAttach = em.getReference(kEGGGenesNewKEGGGeneToAttach.getClass(), kEGGGenesNewKEGGGeneToAttach.getWid());
                attachedkEGGGenesNew.add(kEGGGenesNewKEGGGeneToAttach);
            }
            kEGGGenesNew = attachedkEGGGenesNew;
            keggPathway.setkEGGGenes(kEGGGenesNew);
            Set<KEGGReaction> attachedkEGGReactionsNew = new HashSet();
            for (KEGGReaction kEGGReactionsNewKEGGReactionToAttach : kEGGReactionsNew) {
                kEGGReactionsNewKEGGReactionToAttach = em.getReference(kEGGReactionsNewKEGGReactionToAttach.getClass(), kEGGReactionsNewKEGGReactionToAttach.getWid());
                attachedkEGGReactionsNew.add(kEGGReactionsNewKEGGReactionToAttach);
            }
            kEGGReactionsNew = attachedkEGGReactionsNew;
            keggPathway.setkEGGReactions(kEGGReactionsNew);
            keggPathway = em.merge(keggPathway);
            for (GeneInfo geneInfoOldGeneInfo : geneInfoOld) {
                if (!geneInfoNew.contains(geneInfoOldGeneInfo)) {
                    geneInfoOldGeneInfo.getkEGGPathways().remove(keggPathway);
                    geneInfoOldGeneInfo = em.merge(geneInfoOldGeneInfo);
                }
            }
            for (GeneInfo geneInfoNewGeneInfo : geneInfoNew) {
                if (!geneInfoOld.contains(geneInfoNewGeneInfo)) {
                    geneInfoNewGeneInfo.getkEGGPathways().add(keggPathway);
                    geneInfoNewGeneInfo = em.merge(geneInfoNewGeneInfo);
                }
            }
            for (Protein proteinOldProtein : proteinOld) {
                if (!proteinNew.contains(proteinOldProtein)) {
                    proteinOldProtein.getkEGGPathways().remove(keggPathway);
                    proteinOldProtein = em.merge(proteinOldProtein);
                }
            }
            for (Protein proteinNewProtein : proteinNew) {
                if (!proteinOld.contains(proteinNewProtein)) {
                    proteinNewProtein.getkEGGPathways().add(keggPathway);
                    proteinNewProtein = em.merge(proteinNewProtein);
                }
            }
            for (KEGGCompound kEGGCompoundsOldKEGGCompound : kEGGCompoundsOld) {
                if (!kEGGCompoundsNew.contains(kEGGCompoundsOldKEGGCompound)) {
                    kEGGCompoundsOldKEGGCompound.getkEGGPathways().remove(keggPathway);
                    kEGGCompoundsOldKEGGCompound = em.merge(kEGGCompoundsOldKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundsNewKEGGCompound : kEGGCompoundsNew) {
                if (!kEGGCompoundsOld.contains(kEGGCompoundsNewKEGGCompound)) {
                    kEGGCompoundsNewKEGGCompound.getkEGGPathways().add(keggPathway);
                    kEGGCompoundsNewKEGGCompound = em.merge(kEGGCompoundsNewKEGGCompound);
                }
            }
            for (KEGGEnzyme kEGGEnzymesOldKEGGEnzyme : kEGGEnzymesOld) {
                if (!kEGGEnzymesNew.contains(kEGGEnzymesOldKEGGEnzyme)) {
                    kEGGEnzymesOldKEGGEnzyme.getkEGGPathways().remove(keggPathway);
                    kEGGEnzymesOldKEGGEnzyme = em.merge(kEGGEnzymesOldKEGGEnzyme);
                }
            }
            for (KEGGEnzyme kEGGEnzymesNewKEGGEnzyme : kEGGEnzymesNew) {
                if (!kEGGEnzymesOld.contains(kEGGEnzymesNewKEGGEnzyme)) {
                    kEGGEnzymesNewKEGGEnzyme.getkEGGPathways().add(keggPathway);
                    kEGGEnzymesNewKEGGEnzyme = em.merge(kEGGEnzymesNewKEGGEnzyme);
                }
            }
            for (KEGGGene kEGGGenesOldKEGGGene : kEGGGenesOld) {
                if (!kEGGGenesNew.contains(kEGGGenesOldKEGGGene)) {
                    kEGGGenesOldKEGGGene.getkEGGPathways().remove(keggPathway);
                    kEGGGenesOldKEGGGene = em.merge(kEGGGenesOldKEGGGene);
                }
            }
            for (KEGGGene kEGGGenesNewKEGGGene : kEGGGenesNew) {
                if (!kEGGGenesOld.contains(kEGGGenesNewKEGGGene)) {
                    kEGGGenesNewKEGGGene.getkEGGPathways().add(keggPathway);
                    kEGGGenesNewKEGGGene = em.merge(kEGGGenesNewKEGGGene);
                }
            }
            for (KEGGReaction kEGGReactionsOldKEGGReaction : kEGGReactionsOld) {
                if (!kEGGReactionsNew.contains(kEGGReactionsOldKEGGReaction)) {
                    kEGGReactionsOldKEGGReaction.getkEGGPathways().remove(keggPathway);
                    kEGGReactionsOldKEGGReaction = em.merge(kEGGReactionsOldKEGGReaction);
                }
            }
            for (KEGGReaction kEGGReactionsNewKEGGReaction : kEGGReactionsNew) {
                if (!kEGGReactionsOld.contains(kEGGReactionsNewKEGGReaction)) {
                    kEGGReactionsNewKEGGReaction.getkEGGPathways().add(keggPathway);
                    kEGGReactionsNewKEGGReaction = em.merge(kEGGReactionsNewKEGGReaction);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = keggPathway.getWid();
                if (findKEGGPathway(id) == null) {
                    throw new NonexistentEntityException("The kEGGPathway with id " + id + " no longer exists.");
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
            KEGGPathway KEGGPathway;
            try {
                KEGGPathway = em.getReference(KEGGPathway.class, id);
                KEGGPathway.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The KEGGPathway with id " + id + " no longer exists.", enfe);
            }
            Collection<GeneInfo> geneInfo = KEGGPathway.getGeneInfo();
            for (GeneInfo geneInfoGeneInfo : geneInfo) {
                geneInfoGeneInfo.getkEGGPathways().remove(KEGGPathway);
                geneInfoGeneInfo = em.merge(geneInfoGeneInfo);
            }
            Set<Protein> protein = KEGGPathway.getProtein();
            for (Protein proteinProtein : protein) {
                proteinProtein.getkEGGPathways().remove(KEGGPathway);
                proteinProtein = em.merge(proteinProtein);
            }
            Set<KEGGCompound> kEGGCompounds = KEGGPathway.getkEGGCompounds();
            for (KEGGCompound kEGGCompoundsKEGGCompound : kEGGCompounds) {
                kEGGCompoundsKEGGCompound.getkEGGPathways().remove(KEGGPathway);
                kEGGCompoundsKEGGCompound = em.merge(kEGGCompoundsKEGGCompound);
            }
            Set<KEGGEnzyme> kEGGEnzymes = KEGGPathway.getkEGGEnzymes();
            for (KEGGEnzyme kEGGEnzymesKEGGEnzyme : kEGGEnzymes) {
                kEGGEnzymesKEGGEnzyme.getkEGGPathways().remove(KEGGPathway);
                kEGGEnzymesKEGGEnzyme = em.merge(kEGGEnzymesKEGGEnzyme);
            }
            Set<KEGGGene> kEGGGenes = KEGGPathway.getkEGGGenes();
            for (KEGGGene kEGGGenesKEGGGene : kEGGGenes) {
                kEGGGenesKEGGGene.getkEGGPathways().remove(KEGGPathway);
                kEGGGenesKEGGGene = em.merge(kEGGGenesKEGGGene);
            }
            Set<KEGGReaction> kEGGReactions = KEGGPathway.getkEGGReactions();
            for (KEGGReaction kEGGReactionsKEGGReaction : kEGGReactions) {
                kEGGReactionsKEGGReaction.getkEGGPathways().remove(KEGGPathway);
                kEGGReactionsKEGGReaction = em.merge(kEGGReactionsKEGGReaction);
            }
            em.remove(KEGGPathway);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KEGGPathway> findKEGGPathwayEntities() {
        return findKEGGPathwayEntities(true, -1, -1);
    }

    public List<KEGGPathway> findKEGGPathwayEntities(int maxResults, int firstResult) {
        return findKEGGPathwayEntities(false, maxResults, firstResult);
    }

    private List<KEGGPathway> findKEGGPathwayEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KEGGPathway.class));
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

    public KEGGPathway findKEGGPathway(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KEGGPathway.class, id);
        } finally {
            em.close();
        }
    }

    public int getKEGGPathwayCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KEGGPathway> rt = cq.from(KEGGPathway.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
