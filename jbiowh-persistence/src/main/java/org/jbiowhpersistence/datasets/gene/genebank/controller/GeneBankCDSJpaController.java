package org.jbiowhpersistence.datasets.gene.genebank.controller;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;
import org.jbiowhpersistence.datasets.gene.genome.controller.GenePTTJpaController;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the GeneBankCDS Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 13, 2013
 */
public class GeneBankCDSJpaController extends AbstractJpaController<GeneBankCDS> implements Serializable {

    public GeneBankCDSJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GeneBankCDS geneBankCDS) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + geneBankCDS.getWid());
        if (geneBankCDS.getGeneInfo() == null) {
            geneBankCDS.setGeneInfo(new HashSet<GeneInfo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneBank geneBank = geneBankCDS.getGeneBank();
            if (geneBank != null) {
                geneBank = em.find(geneBank.getClass(), geneBank.getWid());
                if (geneBank != null) {
                    geneBankCDS.setGeneBank(geneBank);
                } else {
                    GeneBankJpaController gController = new GeneBankJpaController(emf);
                    geneBank = geneBankCDS.getGeneBank();
                    geneBank.setGeneBankCDS(null);
                    gController.create(geneBank);
                    geneBank = em.getReference(GeneBank.class, geneBank.getWid());
                    geneBankCDS.setGeneBank(geneBank);
                }
            }
            GenePTT genePTT = geneBankCDS.getGenePTT();
            if (genePTT != null) {                
                genePTT = em.find(GenePTT.class, geneBankCDS.getGenePTT().getProteinGi());
                if (genePTT != null) {
                    geneBankCDS.setGenePTT(genePTT);
                } else {
                    GenePTTJpaController genController = new GenePTTJpaController(emf);
                    genePTT = geneBankCDS.getGenePTT();
                    genController.create(genePTT);
                    genePTT = em.getReference(GenePTT.class, genePTT.getProteinGi());
                    geneBankCDS.setGenePTT(genePTT);
                }
            }
            geneBankCDS.setGeneInfo(createGeneInfo(emf, em, geneBankCDS.getGeneInfo()));
            em.persist(geneBankCDS);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGeneBankCDS(geneBankCDS.getWid()) != null) {
                throw new PreexistingEntityException("GeneBankCDS " + geneBankCDS + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GeneBankCDS geneBankCDS) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + geneBankCDS.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneBankCDS persistentGeneBankCDS = em.find(GeneBankCDS.class, geneBankCDS.getWid());
            GeneBank geneBankOld = persistentGeneBankCDS.getGeneBank();
            GeneBank geneBankNew = geneBankCDS.getGeneBank();
            GenePTT genePTTOld = persistentGeneBankCDS.getGenePTT();
            GenePTT genePTTNew = geneBankCDS.getGenePTT();
            Collection<GeneInfo> geneInfoOld = persistentGeneBankCDS.getGeneInfo();
            Collection<GeneInfo> geneInfoNew = geneBankCDS.getGeneInfo();
            if (geneBankNew != null) {
                geneBankNew = em.getReference(geneBankNew.getClass(), geneBankNew.getWid());
                geneBankCDS.setGeneBank(geneBankNew);
            }
            if (genePTTNew != null) {
                genePTTNew = em.getReference(genePTTNew.getClass(), genePTTNew.getProteinGi());
                geneBankCDS.setGenePTT(genePTTNew);
            }
            Set<GeneInfo> attachedGeneInfoNew = new HashSet();
            for (GeneInfo geneInfoNewGeneInfoToAttach : geneInfoNew) {
                geneInfoNewGeneInfoToAttach = em.getReference(geneInfoNewGeneInfoToAttach.getClass(), geneInfoNewGeneInfoToAttach.getWid());
                attachedGeneInfoNew.add(geneInfoNewGeneInfoToAttach);
            }
            geneInfoNew = attachedGeneInfoNew;
            geneBankCDS.setGeneInfo(geneInfoNew);
            geneBankCDS = em.merge(geneBankCDS);
            if (geneBankOld != null && !geneBankOld.equals(geneBankNew)) {
                geneBankOld.getGeneBankCDS().remove(geneBankCDS);
                geneBankOld = em.merge(geneBankOld);
            }
            if (geneBankNew != null && !geneBankNew.equals(geneBankOld)) {
                geneBankNew.getGeneBankCDS().add(geneBankCDS);
                geneBankNew = em.merge(geneBankNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = geneBankCDS.getWid();
                if (findGeneBankCDS(id) == null) {
                    throw new NonexistentEntityException("The geneBankCDS with id " + id + " no longer exists.");
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
            GeneBankCDS geneBankCDS;
            try {
                geneBankCDS = em.getReference(GeneBankCDS.class, id);
                geneBankCDS.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The geneBankCDS with id " + id + " no longer exists.", enfe);
            }
            GeneBank geneBank = geneBankCDS.getGeneBank();
            if (geneBank != null) {
                geneBank.getGeneBankCDS().remove(geneBankCDS);
                geneBank = em.merge(geneBank);
                geneBankCDS.setGeneBank(null);
            }
            geneBankCDS.setGeneInfo(null);
            em.remove(geneBankCDS);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GeneBankCDS> findGeneBankCDSEntities() {
        return findGeneBankCDSEntities(true, -1, -1);
    }

    public List<GeneBankCDS> findGeneBankCDSEntities(int maxResults, int firstResult) {
        return findGeneBankCDSEntities(false, maxResults, firstResult);
    }

    private List<GeneBankCDS> findGeneBankCDSEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GeneBankCDS.class));
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

    public GeneBankCDS findGeneBankCDS(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GeneBankCDS.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneBankCDSCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GeneBankCDS> rt = cq.from(GeneBankCDS.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
