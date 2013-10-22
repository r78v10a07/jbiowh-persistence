package org.jbiowhpersistence.datasets.domain.pfam.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamProteomeRegions;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamProteomeRegionsPK;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the PfamProteomeRegions Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Nov 21, 2012
 */
public class PfamProteomeRegionsJpaController  extends AbstractPFamJpaController<PfamProteomeRegions> implements Serializable {

    public PfamProteomeRegionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PfamProteomeRegions pfamProteomeRegions) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + pfamProteomeRegions.getPfamProteomeRegionsPK());
        if (pfamProteomeRegions.getPfamProteomeRegionsPK() == null) {
            pfamProteomeRegions.setPfamProteomeRegionsPK(new PfamProteomeRegionsPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            pfamProteomeRegions.setPfamA(createPfamA(emf, em, pfamProteomeRegions.getPfamA()));
            pfamProteomeRegions.setPfamCompleteProteomes(createPfamCompleteProteomes(emf, em, pfamProteomeRegions.getPfamCompleteProteomes()));
            
            em.persist(pfamProteomeRegions);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPfamProteomeRegions(pfamProteomeRegions.getPfamProteomeRegionsPK()) != null) {
                throw new PreexistingEntityException("PfamProteomeRegions " + pfamProteomeRegions + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PfamProteomeRegions pfamProteomeRegions) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + pfamProteomeRegions.getPfamProteomeRegionsPK());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pfamProteomeRegions = em.merge(pfamProteomeRegions);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PfamProteomeRegionsPK id = pfamProteomeRegions.getPfamProteomeRegionsPK();
                if (findPfamProteomeRegions(id) == null) {
                    throw new NonexistentEntityException("The pfamProteomeRegions with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PfamProteomeRegionsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PfamProteomeRegions pfamProteomeRegions;
            try {
                pfamProteomeRegions = em.getReference(PfamProteomeRegions.class, id);
                pfamProteomeRegions.getPfamProteomeRegionsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pfamProteomeRegions with id " + id + " no longer exists.", enfe);
            }
            em.remove(pfamProteomeRegions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PfamProteomeRegions> findPfamProteomeRegionsEntities() {
        return findPfamProteomeRegionsEntities(true, -1, -1);
    }

    public List<PfamProteomeRegions> findPfamProteomeRegionsEntities(int maxResults, int firstResult) {
        return findPfamProteomeRegionsEntities(false, maxResults, firstResult);
    }

    private List<PfamProteomeRegions> findPfamProteomeRegionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PfamProteomeRegions.class));
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

    public PfamProteomeRegions findPfamProteomeRegions(PfamProteomeRegionsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PfamProteomeRegions.class, id);
        } finally {
            em.close();
        }
    }

    public int getPfamProteomeRegionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PfamProteomeRegions> rt = cq.from(PfamProteomeRegions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
