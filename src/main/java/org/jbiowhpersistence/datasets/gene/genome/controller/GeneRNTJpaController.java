package org.jbiowhpersistence.datasets.gene.genome.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.dataset.controller.DataSetJpaController;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.controller.GeneInfoJpaController;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genome.entities.GeneRNT;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;

/**
 * This class is the GeneRNT Jpa Controller 
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since May 24, 2013
 */
public class GeneRNTJpaController  extends AbstractJpaController<GeneRNT> implements Serializable {

    public GeneRNTJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GeneRNT geneRNT) throws Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating GeneRNT: " + geneRNT.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneInfo geneInfo = geneRNT.getGeneInfo();
            geneRNT.setTaxonomy(createTaxonomy(emf, em, geneRNT.getTaxonomy()));
            if (geneInfo != null) {
                GeneInfo geneInfoOnDB = em.find(GeneInfo.class, geneRNT.getGeneInfo().getWid());
                if (geneInfoOnDB != null) {
                    geneRNT.setGeneInfo(geneInfoOnDB);
                } else {
                    GeneInfoJpaController gController = new GeneInfoJpaController(emf);
                    geneInfo.setGenePTT(null);
                    geneInfo.setRelationsToNull();
                    gController.create(geneInfo);
                    geneRNT.setGeneInfo(em.find(GeneInfo.class, geneInfo.getWid()));
                }
            }
            if (geneRNT.getDataSet() != null) {
                DataSet dataSet = em.find(DataSet.class, geneRNT.getDataSet().getWid());
                if (dataSet == null) {
                    DataSetJpaController dController = new DataSetJpaController(emf);
                    dController.create(geneRNT.getDataSet());
                    geneRNT.setDataSet(em.getReference(DataSet.class, geneRNT.getDataSet().getWid()));
                } else {
                    geneRNT.setDataSet(dataSet);
                }
            }
            em.persist(geneRNT);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GeneRNT geneRNT) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing GeneRNT: " + geneRNT.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneRNT persistentGeneRNT = em.find(GeneRNT.class, geneRNT.getWid());
            GeneInfo geneInfoOld = persistentGeneRNT.getGeneInfo();
            GeneInfo geneInfoNew = geneRNT.getGeneInfo();
            if (geneInfoNew != null) {
                geneInfoNew = em.getReference(geneInfoNew.getClass(), geneInfoNew.getWid());
                geneRNT.setGeneInfo(geneInfoNew);
            }
            geneRNT = em.merge(geneRNT);
            if (geneInfoOld != null && !geneInfoOld.equals(geneInfoNew)) {
                geneInfoOld.getGeneRNT().remove(geneRNT);
                geneInfoOld = em.merge(geneInfoOld);
            }
            if (geneInfoNew != null && !geneInfoNew.equals(geneInfoOld)) {
                geneInfoNew.getGeneRNT().add(geneRNT);
                geneInfoNew = em.merge(geneInfoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = geneRNT.getWid();
                if (findGeneRNT(id) == null) {
                    throw new NonexistentEntityException("The geneRNT with id " + id + " no longer exists.");
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
            GeneRNT geneRNT;
            try {
                geneRNT = em.getReference(GeneRNT.class, id);
                geneRNT.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The geneRNT with id " + id + " no longer exists.", enfe);
            }
            GeneInfo geneInfo = geneRNT.getGeneInfo();
            if (geneInfo != null) {
                geneInfo.getGeneRNT().remove(geneRNT);
                geneInfo = em.merge(geneInfo);
            }
            em.remove(geneRNT);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GeneRNT> findGeneRNTEntities() {
        return findGeneRNTEntities(true, -1, -1);
    }

    public List<GeneRNT> findGeneRNTEntities(int maxResults, int firstResult) {
        return findGeneRNTEntities(false, maxResults, firstResult);
    }

    private List<GeneRNT> findGeneRNTEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GeneRNT.class));
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

    public GeneRNT findGeneRNT(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GeneRNT.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneRNTCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GeneRNT> rt = cq.from(GeneRNT.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
