package org.jbiowhpersistence.datasets.disease.omim.controller;

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
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIMAV;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIMCS;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIMRF;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIMSA;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This Class is the OMIM Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
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
        if (omim.getOmimAVs() == null) {
            omim.setOmimAVs(new HashSet<OMIMAV>());
        }
        if (omim.getOmimCSs() == null) {
            omim.setOmimCSs(new HashSet<OMIMCS>());
        }
        if (omim.getOmimRFs() == null) {
            omim.setOmimRFs(new HashSet<OMIMRF>());
        }
        if (omim.getOmimSAs() == null) {
            omim.setOmimSAs(new HashSet<OMIMSA>());
        }
        if (omim.getGeneInfos() == null) {
            omim.setGeneInfos(new HashSet<GeneInfo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!omim.getOmimAVs().isEmpty()) {
                Set<OMIMAV> attachedOmimAVs = new HashSet<>();
                for (OMIMAV omimAVsOMIMAVToAttach : omim.getOmimAVs()) {
                    OMIMAV omimAVsOMIMAV = em.find(omimAVsOMIMAVToAttach.getClass(), omimAVsOMIMAVToAttach.getWid());
                    if (omimAVsOMIMAV != null) {
                        attachedOmimAVs.add(omimAVsOMIMAV);
                    } else {
                        attachedOmimAVs.add(omimAVsOMIMAVToAttach);
                    }
                }
                omim.setOmimAVs(attachedOmimAVs);
            }
            if (!omim.getOmimCSs().isEmpty()) {
                Set<OMIMCS> attachedOmimCSs = new HashSet<>();
                for (OMIMCS omimCSsOMIMCSToAttach : omim.getOmimCSs()) {
                    OMIMCS omimCSsOMIMCS = em.find(omimCSsOMIMCSToAttach.getClass(), omimCSsOMIMCSToAttach.getWid());
                    if (omimCSsOMIMCS != null) {
                        attachedOmimCSs.add(omimCSsOMIMCS);
                    } else {
                        attachedOmimCSs.add(omimCSsOMIMCSToAttach);
                    }
                }
                omim.setOmimCSs(attachedOmimCSs);
            }
            if (!omim.getOmimRFs().isEmpty()) {
                Set<OMIMRF> attachedOmimRFs = new HashSet<>();
                for (OMIMRF omimRFsOMIMRFToAttach : omim.getOmimRFs()) {
                    OMIMRF omimRFsOMIMRF = em.find(omimRFsOMIMRFToAttach.getClass(), omimRFsOMIMRFToAttach.getWid());
                    if (omimRFsOMIMRF != null) {
                        attachedOmimRFs.add(omimRFsOMIMRF);
                    } else {
                        attachedOmimRFs.add(omimRFsOMIMRFToAttach);
                    }
                }
                omim.setOmimRFs(attachedOmimRFs);
            }
            if (!omim.getOmimSAs().isEmpty()) {
                Set<OMIMSA> attachedOmimSAs = new HashSet<>();
                for (OMIMSA omimSAsOMIMSAToAttach : omim.getOmimSAs()) {
                    OMIMSA omimSAsOMIMSA = em.find(omimSAsOMIMSAToAttach.getClass(), omimSAsOMIMSAToAttach.getWid());
                    if (omimSAsOMIMSA != null) {
                    } else {
                        attachedOmimSAs.add(omimSAsOMIMSAToAttach);
                    }
                }
                omim.setOmimSAs(attachedOmimSAs);
            }

            omim.setGeneInfos(createGeneInfo(emf, em, omim.getGeneInfos()));
            omim.setDataSet(createDataSet(emf, em, omim.getDataSet()));
            em.persist(omim);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOMIM(omim.getWid()) != null) {
                throw new PreexistingEntityException("OMIM " + omim + " already exists.", ex);
            }
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
            Set<OMIMAV> omimAVsOld = persistentOMIM.getOmimAVs();
            Set<OMIMAV> omimAVsNew = omim.getOmimAVs();
            Set<OMIMCS> omimCSsOld = persistentOMIM.getOmimCSs();
            Set<OMIMCS> omimCSsNew = omim.getOmimCSs();
            Set<OMIMRF> omimRFsOld = persistentOMIM.getOmimRFs();
            Set<OMIMRF> omimRFsNew = omim.getOmimRFs();
            Set<OMIMSA> omimSAsOld = persistentOMIM.getOmimSAs();
            Set<OMIMSA> omimSAsNew = omim.getOmimSAs();
            Set<GeneInfo> geneInfosOld = persistentOMIM.getGeneInfos();
            Set<GeneInfo> geneInfosNew = omim.getGeneInfos();
            Set<OMIMAV> attachedOmimAVsNew = new HashSet<>();
            for (OMIMAV omimAVsNewOMIMAVToAttach : omimAVsNew) {
                omimAVsNewOMIMAVToAttach = em.getReference(omimAVsNewOMIMAVToAttach.getClass(), omimAVsNewOMIMAVToAttach.getWid());
                attachedOmimAVsNew.add(omimAVsNewOMIMAVToAttach);
            }
            omimAVsNew = attachedOmimAVsNew;
            omim.setOmimAVs(omimAVsNew);
            Set<OMIMCS> attachedOmimCSsNew = new HashSet<>();
            for (OMIMCS omimCSsNewOMIMCSToAttach : omimCSsNew) {
                omimCSsNewOMIMCSToAttach = em.getReference(omimCSsNewOMIMCSToAttach.getClass(), omimCSsNewOMIMCSToAttach.getWid());
                attachedOmimCSsNew.add(omimCSsNewOMIMCSToAttach);
            }
            omimCSsNew = attachedOmimCSsNew;
            omim.setOmimCSs(omimCSsNew);
            Set<OMIMRF> attachedOmimRFsNew = new HashSet<>();
            for (OMIMRF omimRFsNewOMIMRFToAttach : omimRFsNew) {
                omimRFsNewOMIMRFToAttach = em.getReference(omimRFsNewOMIMRFToAttach.getClass(), omimRFsNewOMIMRFToAttach.getWid());
                attachedOmimRFsNew.add(omimRFsNewOMIMRFToAttach);
            }
            omimRFsNew = attachedOmimRFsNew;
            omim.setOmimRFs(omimRFsNew);
            Set<OMIMSA> attachedOmimSAsNew = new HashSet<>();
            for (OMIMSA omimSAsNewOMIMSAToAttach : omimSAsNew) {
                omimSAsNewOMIMSAToAttach = em.getReference(omimSAsNewOMIMSAToAttach.getClass(), omimSAsNewOMIMSAToAttach.getWid());
                attachedOmimSAsNew.add(omimSAsNewOMIMSAToAttach);
            }
            omimSAsNew = attachedOmimSAsNew;
            omim.setOmimSAs(omimSAsNew);
            Set<GeneInfo> attachedGeneInfosNew = new HashSet<>();
            for (GeneInfo geneInfosNewGeneInfoToAttach : geneInfosNew) {
                geneInfosNewGeneInfoToAttach = em.getReference(geneInfosNewGeneInfoToAttach.getClass(), geneInfosNewGeneInfoToAttach.getWid());
                attachedGeneInfosNew.add(geneInfosNewGeneInfoToAttach);
            }
            geneInfosNew = attachedGeneInfosNew;
            omim.setGeneInfos(geneInfosNew);
            omim = em.merge(omim);
            for (OMIMAV omimAVsOldOMIMAV : omimAVsOld) {
                if (!omimAVsNew.contains(omimAVsOldOMIMAV)) {
                    omimAVsOldOMIMAV.setOmim(null);
                    omimAVsOldOMIMAV = em.merge(omimAVsOldOMIMAV);
                }
            }
            for (OMIMAV omimAVsNewOMIMAV : omimAVsNew) {
                if (!omimAVsOld.contains(omimAVsNewOMIMAV)) {
                    OMIM oldOmimOfOmimAVsNewOMIMAV = omimAVsNewOMIMAV.getOmim();
                    omimAVsNewOMIMAV.setOmim(omim);
                    omimAVsNewOMIMAV = em.merge(omimAVsNewOMIMAV);
                    if (oldOmimOfOmimAVsNewOMIMAV != null && !oldOmimOfOmimAVsNewOMIMAV.equals(omim)) {
                        oldOmimOfOmimAVsNewOMIMAV.getOmimAVs().remove(omimAVsNewOMIMAV);
                        em.merge(oldOmimOfOmimAVsNewOMIMAV);
                    }
                }
            }
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
                        oldOmimOfOmimCSsNewOMIMCS.getOmimCSs().remove(omimCSsNewOMIMCS);
                        em.merge(oldOmimOfOmimCSsNewOMIMCS);
                    }
                }
            }
            for (OMIMRF omimRFsOldOMIMRF : omimRFsOld) {
                if (!omimRFsNew.contains(omimRFsOldOMIMRF)) {
                    omimRFsOldOMIMRF.setOmim(null);
                    omimRFsOldOMIMRF = em.merge(omimRFsOldOMIMRF);
                }
            }
            for (OMIMRF omimRFsNewOMIMRF : omimRFsNew) {
                if (!omimRFsOld.contains(omimRFsNewOMIMRF)) {
                    OMIM oldOmimOfOmimRFsNewOMIMRF = omimRFsNewOMIMRF.getOmim();
                    omimRFsNewOMIMRF.setOmim(omim);
                    omimRFsNewOMIMRF = em.merge(omimRFsNewOMIMRF);
                    if (oldOmimOfOmimRFsNewOMIMRF != null && !oldOmimOfOmimRFsNewOMIMRF.equals(omim)) {
                        oldOmimOfOmimRFsNewOMIMRF.getOmimRFs().remove(omimRFsNewOMIMRF);
                        em.merge(oldOmimOfOmimRFsNewOMIMRF);
                    }
                }
            }
            for (OMIMSA omimSAsOldOMIMSA : omimSAsOld) {
                if (!omimSAsNew.contains(omimSAsOldOMIMSA)) {
                    omimSAsOldOMIMSA.setOmim(null);
                    omimSAsOldOMIMSA = em.merge(omimSAsOldOMIMSA);
                }
            }
            for (OMIMSA omimSAsNewOMIMSA : omimSAsNew) {
                if (!omimSAsOld.contains(omimSAsNewOMIMSA)) {
                    OMIM oldOmimOfOmimSAsNewOMIMSA = omimSAsNewOMIMSA.getOmim();
                    omimSAsNewOMIMSA.setOmim(omim);
                    omimSAsNewOMIMSA = em.merge(omimSAsNewOMIMSA);
                    if (oldOmimOfOmimSAsNewOMIMSA != null && !oldOmimOfOmimSAsNewOMIMSA.equals(omim)) {
                        oldOmimOfOmimSAsNewOMIMSA.getOmimSAs().remove(omimSAsNewOMIMSA);
                        em.merge(oldOmimOfOmimSAsNewOMIMSA);
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
            Set<OMIMAV> omimAVs = OMIM.getOmimAVs();
            for (OMIMAV omimAVsOMIMAV : omimAVs) {
                omimAVsOMIMAV.setOmim(null);
                omimAVsOMIMAV = em.merge(omimAVsOMIMAV);
            }
            Set<OMIMCS> omimCSs = OMIM.getOmimCSs();
            for (OMIMCS omimCSsOMIMCS : omimCSs) {
                omimCSsOMIMCS.setOmim(null);
                omimCSsOMIMCS = em.merge(omimCSsOMIMCS);
            }
            Set<OMIMRF> omimRFs = OMIM.getOmimRFs();
            for (OMIMRF omimRFsOMIMRF : omimRFs) {
                omimRFsOMIMRF.setOmim(null);
                omimRFsOMIMRF = em.merge(omimRFsOMIMRF);
            }
            Set<OMIMSA> omimSAs = OMIM.getOmimSAs();
            for (OMIMSA omimSAsOMIMSA : omimSAs) {
                omimSAsOMIMSA.setOmim(null);
                omimSAsOMIMSA = em.merge(omimSAsOMIMSA);
            }
            Set<GeneInfo> geneInfos = OMIM.getGeneInfos();
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
