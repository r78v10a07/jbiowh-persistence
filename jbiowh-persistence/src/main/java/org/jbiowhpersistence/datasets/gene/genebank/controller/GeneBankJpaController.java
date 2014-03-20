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
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the GeneBank Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 3, 2013
 */
public class GeneBankJpaController extends AbstractJpaController<GeneBank> implements Serializable {

    public GeneBankJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GeneBank geneBank) throws PreexistingEntityException, Exception {

        //VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + geneBank.getWid());
        if (geneBank.getGeneBankCDS() == null) {
            geneBank.setGeneBankCDS(new HashSet<GeneBankCDS>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!geneBank.getGeneBankCDS().isEmpty()) {
                GeneBankCDSJpaController cController = new GeneBankCDSJpaController(emf);
                Set<GeneBankCDS> attachedGeneBankCDSs = new HashSet();
                for (GeneBankCDS geneBankCDSsGeneBankCDSToAttach : geneBank.getGeneBankCDS()) {
                    GeneBankCDS geneBankCDSsGeneBankCDSToAttachToDB = em.find(geneBankCDSsGeneBankCDSToAttach.getClass(), geneBankCDSsGeneBankCDSToAttach.getWid());
                    if (geneBankCDSsGeneBankCDSToAttachToDB == null) {
                        geneBankCDSsGeneBankCDSToAttachToDB = geneBankCDSsGeneBankCDSToAttach;
                        geneBankCDSsGeneBankCDSToAttachToDB.setRelationsToNull();
                        cController.create(geneBankCDSsGeneBankCDSToAttachToDB);
                        geneBankCDSsGeneBankCDSToAttachToDB = em.getReference(geneBankCDSsGeneBankCDSToAttach.getClass(), geneBankCDSsGeneBankCDSToAttach.getWid());
                    }
                    attachedGeneBankCDSs.add(geneBankCDSsGeneBankCDSToAttachToDB);
                }
                geneBank.setGeneBankCDS(attachedGeneBankCDSs);
            }
            em.persist(geneBank);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GeneBank geneBank) throws NonexistentEntityException, Exception {
        //VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + geneBank.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneBank persistentGeneBank = em.find(GeneBank.class, geneBank.getWid());
            Collection<GeneBankCDS> geneBankCDSsOld = persistentGeneBank.getGeneBankCDS();
            Collection<GeneBankCDS> geneBankCDSsNew = geneBank.getGeneBankCDS();
            Collection<GeneBankCDS> attachedGeneBankCDSsNew = new HashSet();
            for (GeneBankCDS geneBankCDSsNewGeneBankCDSToAttach : geneBankCDSsNew) {
                geneBankCDSsNewGeneBankCDSToAttach = em.getReference(geneBankCDSsNewGeneBankCDSToAttach.getClass(), geneBankCDSsNewGeneBankCDSToAttach.getWid());
                attachedGeneBankCDSsNew.add(geneBankCDSsNewGeneBankCDSToAttach);
            }
            geneBankCDSsNew = attachedGeneBankCDSsNew;
            geneBank.setGeneBankCDS(geneBankCDSsNew);
            geneBank = em.merge(geneBank);
            for (GeneBankCDS geneBankCDSsOldGeneBankCDS : geneBankCDSsOld) {
                if (!geneBankCDSsNew.contains(geneBankCDSsOldGeneBankCDS)) {
                    geneBankCDSsOldGeneBankCDS.setGeneBank(null);
                    geneBankCDSsOldGeneBankCDS = em.merge(geneBankCDSsOldGeneBankCDS);
                }
            }
            for (GeneBankCDS geneBankCDSsNewGeneBankCDS : geneBankCDSsNew) {
                if (!geneBankCDSsOld.contains(geneBankCDSsNewGeneBankCDS)) {
                    GeneBank oldGeneBankOfGeneBankCDSsNewGeneBankCDS = geneBankCDSsNewGeneBankCDS.getGeneBank();
                    geneBankCDSsNewGeneBankCDS.setGeneBank(geneBank);
                    geneBankCDSsNewGeneBankCDS = em.merge(geneBankCDSsNewGeneBankCDS);
                    if (oldGeneBankOfGeneBankCDSsNewGeneBankCDS != null && !oldGeneBankOfGeneBankCDSsNewGeneBankCDS.equals(geneBank)) {
                        oldGeneBankOfGeneBankCDSsNewGeneBankCDS.getGeneBankCDS().remove(geneBankCDSsNewGeneBankCDS);
                        oldGeneBankOfGeneBankCDSsNewGeneBankCDS = em.merge(oldGeneBankOfGeneBankCDSsNewGeneBankCDS);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = geneBank.getWid();
                if (findGeneBank(id) == null) {
                    throw new NonexistentEntityException("The geneBank with id " + id + " no longer exists.");
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
            GeneBank geneBank;
            try {
                geneBank = em.getReference(GeneBank.class, id);
                geneBank.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The geneBank with id " + id + " no longer exists.", enfe);
            }
            Collection<GeneBankCDS> geneBankCDSs = geneBank.getGeneBankCDS();
            for (GeneBankCDS geneBankCDSsGeneBankCDS : geneBankCDSs) {
                geneBankCDSsGeneBankCDS.setGeneBank(null);
                geneBankCDSsGeneBankCDS = em.merge(geneBankCDSsGeneBankCDS);
            }
            em.remove(geneBank);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GeneBank> findGeneBankEntities() {
        return findGeneBankEntities(true, -1, -1);
    }

    public List<GeneBank> findGeneBankEntities(int maxResults, int firstResult) {
        return findGeneBankEntities(false, maxResults, firstResult);
    }

    private List<GeneBank> findGeneBankEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GeneBank.class));
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

    public GeneBank findGeneBank(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GeneBank.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneBankCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GeneBank> rt = cq.from(GeneBank.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
