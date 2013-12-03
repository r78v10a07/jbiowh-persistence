package org.jbiowhpersistence.datasets.disease.omim.controller;

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
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIMCS;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This Class is the OMIM Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jul 25, 2012
 */
public class OMIMJpaController extends AbstractJpaController<OMIM> implements Serializable {

    public OMIMJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OMIM omim) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + omim.getWid());
        if (omim.getOmimCS() == null) {
            omim.setOmimCS(new HashSet<OMIMCS>());
        }
        if (omim.getGeneInfo() == null) {
            omim.setGeneInfo(new HashSet<GeneInfo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!omim.getOmimCS().isEmpty()) {
                Set<OMIMCS> attachedOmimCSs = new HashSet();
                for (OMIMCS omimCSsOMIMCSToAttach : omim.getOmimCS()) {
                    OMIMCS omimCSsOMIMCS = em.find(omimCSsOMIMCSToAttach.getClass(), omimCSsOMIMCSToAttach.getWid());
                    if (omimCSsOMIMCS != null) {
                        attachedOmimCSs.add(omimCSsOMIMCS);
                    } else {
                        attachedOmimCSs.add(omimCSsOMIMCSToAttach);
                    }
                }
                omim.setOmimCS(attachedOmimCSs);
            }
            omim.setGeneInfo(createGeneInfo(emf, em, omim.getGeneInfo()));
            omim.setDataSet(createDataSet(emf, em, omim.getDataSet()));
            em.persist(omim);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOMIM(omim.getWid()) != null) {
                throw new PreexistingEntityException("OMIM " + omim + " already exists.", ex);
            }
            ex.printStackTrace(System.out);
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OMIM omim) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editting " + this.getClass().getSimpleName() + ": " + omim.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OMIM persistentOMIM = em.find(OMIM.class, omim.getWid());
            Set<OMIMCS> omimCSsOld = persistentOMIM.getOmimCS();
            Set<OMIMCS> omimCSsNew = omim.getOmimCS();
            Collection<GeneInfo> geneInfosOld = persistentOMIM.getGeneInfo();
            Collection<GeneInfo> geneInfosNew = omim.getGeneInfo();
            Set<OMIMCS> attachedOmimCSsNew = new HashSet();
            for (OMIMCS omimCSsNewOMIMCSToAttach : omimCSsNew) {
                omimCSsNewOMIMCSToAttach = em.getReference(omimCSsNewOMIMCSToAttach.getClass(), omimCSsNewOMIMCSToAttach.getWid());
                attachedOmimCSsNew.add(omimCSsNewOMIMCSToAttach);
            }
            omimCSsNew = attachedOmimCSsNew;
            omim.setOmimCS(omimCSsNew);

            Set<GeneInfo> attachedGeneInfosNew = new HashSet();
            for (GeneInfo geneInfosNewGeneInfoToAttach : geneInfosNew) {
                geneInfosNewGeneInfoToAttach = em.getReference(geneInfosNewGeneInfoToAttach.getClass(), geneInfosNewGeneInfoToAttach.getWid());
                attachedGeneInfosNew.add(geneInfosNewGeneInfoToAttach);
            }
            geneInfosNew = attachedGeneInfosNew;
            omim.setGeneInfo(geneInfosNew);
            omim = em.merge(omim);
            for (OMIMCS omimCSsOldOMIMCS : omimCSsOld) {
                if (!omimCSsNew.contains(omimCSsOldOMIMCS)) {
                    omimCSsOldOMIMCS.setOmim(null);
                    omimCSsOldOMIMCS = em.merge(omimCSsOldOMIMCS);
                }
            }
            for (OMIMCS omimCSsNewOMIMCS : omimCSsNew) {
                if (!omimCSsOld.contains(omimCSsNewOMIMCS)) {
                    OMIM oldOmimOfOmimCSsNewOMIMCS = omimCSsNewOMIMCS.getOmim();
                    omimCSsNewOMIMCS.setOmim(omim);
                    omimCSsNewOMIMCS = em.merge(omimCSsNewOMIMCS);
                    if (oldOmimOfOmimCSsNewOMIMCS != null && !oldOmimOfOmimCSsNewOMIMCS.equals(omim)) {
                        oldOmimOfOmimCSsNewOMIMCS.getOmimCS().remove(omimCSsNewOMIMCS);
                        em.merge(oldOmimOfOmimCSsNewOMIMCS);
                    }
                }
            }
            for (GeneInfo geneInfosOldGeneInfo : geneInfosOld) {
                if (!geneInfosNew.contains(geneInfosOldGeneInfo)) {
                    geneInfosOldGeneInfo.getOmim().remove(omim);
                    geneInfosOldGeneInfo = em.merge(geneInfosOldGeneInfo);
                }
            }
            for (GeneInfo geneInfosNewGeneInfo : geneInfosNew) {
                if (!geneInfosOld.contains(geneInfosNewGeneInfo)) {
                    geneInfosNewGeneInfo.getOmim().add(omim);
                    geneInfosNewGeneInfo = em.merge(geneInfosNewGeneInfo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = omim.getWid();
                if (findOMIM(id) == null) {
                    throw new NonexistentEntityException("The oMIM with id " + id + " no longer exists.");
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
            OMIM OMIM;
            try {
                OMIM = em.getReference(OMIM.class, id);
                OMIM.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The OMIM with id " + id + " no longer exists.", enfe);
            }
            Set<OMIMCS> omimCSs = OMIM.getOmimCS();
            for (OMIMCS omimCSsOMIMCS : omimCSs) {
                omimCSsOMIMCS.setOmim(null);
                omimCSsOMIMCS = em.merge(omimCSsOMIMCS);
            }
            Collection<GeneInfo> geneInfos = OMIM.getGeneInfo();
            for (GeneInfo geneInfosGeneInfo : geneInfos) {
                geneInfosGeneInfo.getOmim().remove(OMIM);
                geneInfosGeneInfo = em.merge(geneInfosGeneInfo);
            }
            em.remove(OMIM);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OMIM> findOMIMEntities() {
        return findOMIMEntities(true, -1, -1);
    }

    public List<OMIM> findOMIMEntities(int maxResults, int firstResult) {
        return findOMIMEntities(false, maxResults, firstResult);
    }

    private List<OMIM> findOMIMEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OMIM.class));
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

    public OMIM findOMIM(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OMIM.class, id);
        } finally {
            em.close();
        }
    }

    public int getOMIMCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OMIM> rt = cq.from(OMIM.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
