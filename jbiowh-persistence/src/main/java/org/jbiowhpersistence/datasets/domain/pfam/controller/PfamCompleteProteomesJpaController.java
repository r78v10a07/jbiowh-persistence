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
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamCompleteProteomes;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;

/**
 * This class is the PfamCompleteProteomes Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Nov 21, 2012
 */
public class PfamCompleteProteomesJpaController extends AbstractPFamJpaController<PfamCompleteProteomes> implements Serializable {

    public PfamCompleteProteomesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PfamCompleteProteomes pfamCompleteProteomes) throws Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + pfamCompleteProteomes.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            pfamCompleteProteomes.setTaxonomy(createTaxonomy(emf, em, pfamCompleteProteomes.getTaxonomy()));
            pfamCompleteProteomes.setPfamProteomeRegions(createPfamProteomeRegions(emf, em, pfamCompleteProteomes.getPfamProteomeRegions()));
            
            em.persist(pfamCompleteProteomes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PfamCompleteProteomes pfamCompleteProteomes) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + pfamCompleteProteomes.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pfamCompleteProteomes = em.merge(pfamCompleteProteomes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pfamCompleteProteomes.getWid();
                if (findPfamCompleteProteomes(id) == null) {
                    throw new NonexistentEntityException("The pfamCompleteProteomes with id " + id + " no longer exists.");
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
            PfamCompleteProteomes pfamCompleteProteomes;
            try {
                pfamCompleteProteomes = em.getReference(PfamCompleteProteomes.class, id);
                pfamCompleteProteomes.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pfamCompleteProteomes with id " + id + " no longer exists.", enfe);
            }
            em.remove(pfamCompleteProteomes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PfamCompleteProteomes> findPfamCompleteProteomesEntities() {
        return findPfamCompleteProteomesEntities(true, -1, -1);
    }

    public List<PfamCompleteProteomes> findPfamCompleteProteomesEntities(int maxResults, int firstResult) {
        return findPfamCompleteProteomesEntities(false, maxResults, firstResult);
    }

    private List<PfamCompleteProteomes> findPfamCompleteProteomesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PfamCompleteProteomes.class));
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

    public PfamCompleteProteomes findPfamCompleteProteomes(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PfamCompleteProteomes.class, id);
        } finally {
            em.close();
        }
    }

    public int getPfamCompleteProteomesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PfamCompleteProteomes> rt = cq.from(PfamCompleteProteomes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
