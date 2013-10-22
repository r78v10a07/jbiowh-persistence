package org.jbiowhpersistence.datasets.gene.genome.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.dataset.controller.DataSetJpaController;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.controller.GeneInfoJpaController;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.protein.controller.ProteinJpaController;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the GenePTT Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-30 18:40:01 +0100
 * (Sat, 30 Mar 2013) $ $LastChangedRevision: 591 $
 * @since Aug 30, 2012
 */
public class GenePTTJpaController extends AbstractJpaController<GenePTT> implements Serializable {

    public GenePTTJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GenePTT genePTT) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating GenePTT: " + genePTT.getProteinGi());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneInfo geneInfo = genePTT.getGeneInfo();
            genePTT.setTaxonomy(createTaxonomy(emf, em, genePTT.getTaxonomy()));
            if (geneInfo != null) {
                GeneInfo geneInfoOnDB = em.find(GeneInfo.class, genePTT.getGeneInfo().getWid());
                if (geneInfoOnDB != null) {
                    genePTT.setGeneInfo(geneInfoOnDB);
                } else {
                    GeneInfoJpaController gController = new GeneInfoJpaController(emf);
                    geneInfo.setGenePTT(null);
                    geneInfo.setRelationsToNull();
                    gController.create(geneInfo);
                    genePTT.setGeneInfo(em.find(GeneInfo.class, geneInfo.getWid()));
                }
            }
            Protein protein = genePTT.getProtein();
            if (protein != null) {
                Protein proteinOnDB = em.find(Protein.class, genePTT.getProtein().getWid());
                if (proteinOnDB != null) {
                    genePTT.setProtein(proteinOnDB);
                } else {
                    ProteinJpaController pController = new ProteinJpaController(emf);
                    protein.setGenePTT(null);
                    protein.setRelationsToNull();
                    pController.create(protein);
                    genePTT.setProtein(em.find(Protein.class, protein.getWid()));
                }
            }
            if (genePTT.getDataSet() != null) {
                DataSet dataSet = em.find(DataSet.class, genePTT.getDataSet().getWid());
                if (dataSet == null) {
                    DataSetJpaController dController = new DataSetJpaController(emf);
                    dController.create(genePTT.getDataSet());
                    genePTT.setDataSet(em.getReference(DataSet.class, genePTT.getDataSet().getWid()));
                } else {
                    genePTT.setDataSet(dataSet);
                }
            }
            em.persist(genePTT);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGenePTT(genePTT.getProteinGi()) != null) {
                throw new PreexistingEntityException("GenePTT " + genePTT + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GenePTT genePTT) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing GenePTT: " + genePTT.getProteinGi());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GenePTT persistentGenePTT = em.find(GenePTT.class, genePTT.getProteinGi());
            GeneInfo geneInfoOld = persistentGenePTT.getGeneInfo();
            GeneInfo geneInfoNew = genePTT.getGeneInfo();
            if (geneInfoNew != null) {
                geneInfoNew = em.getReference(geneInfoNew.getClass(), geneInfoNew.getWid());
                genePTT.setGeneInfo(geneInfoNew);
            }
            genePTT = em.merge(genePTT);
            if (geneInfoOld != null && !geneInfoOld.equals(geneInfoNew)) {
                geneInfoOld.setGenePTT(null);
                geneInfoOld = em.merge(geneInfoOld);
            }
            if (geneInfoNew != null && !geneInfoNew.equals(geneInfoOld)) {
                GenePTT oldGenePTTOfGeneInfo = geneInfoNew.getGenePTT();
                if (oldGenePTTOfGeneInfo != null) {
                    oldGenePTTOfGeneInfo.setGeneInfo(null);
                    em.merge(oldGenePTTOfGeneInfo);
                }
                geneInfoNew.setGenePTT(genePTT);
                em.merge(geneInfoNew);
            }

            Protein proteinOld = persistentGenePTT.getProtein();
            Protein proteinNew = genePTT.getProtein();
            if (proteinNew != null) {
                proteinNew = em.getReference(proteinNew.getClass(), proteinNew.getWid());
                genePTT.setProtein(proteinNew);
            }
            genePTT = em.merge(genePTT);
            if (proteinOld != null && !proteinOld.equals(proteinNew)) {
                proteinOld.setGenePTT(null);
                proteinOld = em.merge(proteinOld);
            }
            if (proteinNew != null && !proteinNew.equals(proteinOld)) {
                GenePTT oldGenePTTOfGeneInfo = proteinNew.getGenePTT();
                if (oldGenePTTOfGeneInfo != null) {
                    oldGenePTTOfGeneInfo.setGeneInfo(null);
                    em.merge(oldGenePTTOfGeneInfo);
                }
                proteinNew.setGenePTT(genePTT);
                em.merge(proteinNew);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = genePTT.getProteinGi();
                if (findGenePTT(id) == null) {
                    throw new NonexistentEntityException("The genePTT with id " + id + " no longer exists.");
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
            GenePTT genePTT;
            try {
                genePTT = em.getReference(GenePTT.class, id);
                genePTT.getProteinGi();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genePTT with id " + id + " no longer exists.", enfe);
            }
            GeneInfo geneInfo = genePTT.getGeneInfo();
            if (geneInfo != null) {
                geneInfo.setGenePTT(null);
                em.merge(geneInfo);
            }
            Protein protein = genePTT.getProtein();
            if (protein != null) {
                protein.setGenePTT(null);
                em.merge(protein);
            }
            em.remove(genePTT);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GenePTT> findGenePTTEntities() {
        return findGenePTTEntities(true, -1, -1);
    }

    public List<GenePTT> findGenePTTEntities(int maxResults, int firstResult) {
        return findGenePTTEntities(false, maxResults, firstResult);
    }

    private List<GenePTT> findGenePTTEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GenePTT.class));
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

    public GenePTT findGenePTT(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GenePTT.class, id);
        } finally {
            em.close();
        }
    }

    public int getGenePTTCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GenePTT> rt = cq.from(GenePTT.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
