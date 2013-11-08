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
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the KEGGGene Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Sep 17, 2012
 */
public class KEGGGeneJpaController extends AbstractKEGGJpaController<KEGGGene> implements Serializable {

    public KEGGGeneJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KEGGGene keggGene) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + keggGene.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            keggGene.setGeneInfos(createGeneInfo(emf, em, keggGene.getGeneInfos()));
            keggGene.setkEGGPathways(createKEGGPathway(emf, em, keggGene.getkEGGPathways()));
            keggGene.setDataSet(createDataSet(emf, em, keggGene.getDataSet()));
            em.persist(keggGene);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKEGGGene(keggGene.getWid()) != null) {
                throw new PreexistingEntityException("KEGGGene " + keggGene + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KEGGGene keggGene) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + keggGene.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KEGGGene persistentKEGGGene = em.find(KEGGGene.class, keggGene.getWid());
            Set<GeneInfo> geneInfosOld = persistentKEGGGene.getGeneInfos();
            Set<GeneInfo> geneInfosNew = keggGene.getGeneInfos();
            Set<KEGGPathway> kEGGPathwaysOld = persistentKEGGGene.getkEGGPathways();
            Set<KEGGPathway> kEGGPathwaysNew = keggGene.getkEGGPathways();
            Set<GeneInfo> attachedGeneInfosNew = new HashSet();
            for (GeneInfo geneInfosNewGeneInfoToAttach : geneInfosNew) {
                geneInfosNewGeneInfoToAttach = em.getReference(geneInfosNewGeneInfoToAttach.getClass(), geneInfosNewGeneInfoToAttach.getWid());
                attachedGeneInfosNew.add(geneInfosNewGeneInfoToAttach);
            }
            geneInfosNew = attachedGeneInfosNew;
            keggGene.setGeneInfos(geneInfosNew);
            Set<KEGGPathway> attachedkEGGPathwaysNew = new HashSet();
            for (KEGGPathway kEGGPathwaysNewKEGGPathwayToAttach : kEGGPathwaysNew) {
                kEGGPathwaysNewKEGGPathwayToAttach = em.getReference(kEGGPathwaysNewKEGGPathwayToAttach.getClass(), kEGGPathwaysNewKEGGPathwayToAttach.getWid());
                attachedkEGGPathwaysNew.add(kEGGPathwaysNewKEGGPathwayToAttach);
            }
            kEGGPathwaysNew = attachedkEGGPathwaysNew;
            keggGene.setkEGGPathways(kEGGPathwaysNew);
            keggGene = em.merge(keggGene);
            for (GeneInfo geneInfosOldGeneInfo : geneInfosOld) {
                if (!geneInfosNew.contains(geneInfosOldGeneInfo)) {
                    geneInfosOldGeneInfo.getkEGGGenes().remove(keggGene);
                    geneInfosOldGeneInfo = em.merge(geneInfosOldGeneInfo);
                }
            }
            for (GeneInfo geneInfosNewGeneInfo : geneInfosNew) {
                if (!geneInfosOld.contains(geneInfosNewGeneInfo)) {
                    geneInfosNewGeneInfo.getkEGGGenes().add(keggGene);
                    geneInfosNewGeneInfo = em.merge(geneInfosNewGeneInfo);
                }
            }
            for (KEGGPathway kEGGPathwaysOldKEGGPathway : kEGGPathwaysOld) {
                if (!kEGGPathwaysNew.contains(kEGGPathwaysOldKEGGPathway)) {
                    kEGGPathwaysOldKEGGPathway.getkEGGGenes().remove(keggGene);
                    kEGGPathwaysOldKEGGPathway = em.merge(kEGGPathwaysOldKEGGPathway);
                }
            }
            for (KEGGPathway kEGGPathwaysNewKEGGPathway : kEGGPathwaysNew) {
                if (!kEGGPathwaysOld.contains(kEGGPathwaysNewKEGGPathway)) {
                    kEGGPathwaysNewKEGGPathway.getkEGGGenes().add(keggGene);
                    kEGGPathwaysNewKEGGPathway = em.merge(kEGGPathwaysNewKEGGPathway);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = keggGene.getWid();
                if (findKEGGGene(id) == null) {
                    throw new NonexistentEntityException("The kEGGGene with id " + id + " no longer exists.");
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
            KEGGGene KEGGGene;
            try {
                KEGGGene = em.getReference(KEGGGene.class, id);
                KEGGGene.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The KEGGGene with id " + id + " no longer exists.", enfe);
            }
            Set<GeneInfo> geneInfos = KEGGGene.getGeneInfos();
            for (GeneInfo geneInfosGeneInfo : geneInfos) {
                geneInfosGeneInfo.getkEGGGenes().remove(KEGGGene);
                geneInfosGeneInfo = em.merge(geneInfosGeneInfo);
            }
            Set<KEGGPathway> kEGGPathways = KEGGGene.getkEGGPathways();
            for (KEGGPathway kEGGPathwaysKEGGPathway : kEGGPathways) {
                kEGGPathwaysKEGGPathway.getkEGGGenes().remove(KEGGGene);
                kEGGPathwaysKEGGPathway = em.merge(kEGGPathwaysKEGGPathway);
            }
            em.remove(KEGGGene);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KEGGGene> findKEGGGeneEntities() {
        return findKEGGGeneEntities(true, -1, -1);
    }

    public List<KEGGGene> findKEGGGeneEntities(int maxResults, int firstResult) {
        return findKEGGGeneEntities(false, maxResults, firstResult);
    }

    private List<KEGGGene> findKEGGGeneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KEGGGene.class));
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

    public KEGGGene findKEGGGene(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KEGGGene.class, id);
        } finally {
            em.close();
        }
    }

    public int getKEGGGeneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KEGGGene> rt = cq.from(KEGGGene.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
