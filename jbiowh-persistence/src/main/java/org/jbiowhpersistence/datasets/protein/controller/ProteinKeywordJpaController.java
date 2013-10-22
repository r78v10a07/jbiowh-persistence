package org.jbiowhpersistence.datasets.protein.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protein.entities.ProteinKeyword;
import org.jbiowhpersistence.datasets.protein.entities.ProteinhasProteinKeyword;
import org.jbiowhpersistence.datasets.protein.entities.ProteinhasProteinKeywordPK;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the ProteinKeyword Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 4, 2012
 */
public class ProteinKeywordJpaController extends AbstractJpaController<ProteinKeyword> implements Serializable {
    
    public ProteinKeywordJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(ProteinKeyword proteinKeyword) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + proteinKeyword.getWid());
        if (proteinKeyword.getProteinhasProteinKeyword() == null) {
            proteinKeyword.setProteinhasProteinKeyword(new HashMap<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!proteinKeyword.getProteinhasProteinKeyword().isEmpty()) {
                ProteinJpaController pController = new ProteinJpaController(emf);
                Map<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword> attachProteinhasProteinKeyword = new HashMap<>();
                for (ProteinhasProteinKeyword proteinhasProteinKeywordToAttach : proteinKeyword.getProteinhasProteinKeyword().values()) {
                    ProteinhasProteinKeyword proteinhasProteinKeyword = em.find(proteinhasProteinKeywordToAttach.getClass(), proteinhasProteinKeywordToAttach.getProteinhasProteinKeywordPK());
                    if (proteinhasProteinKeyword != null) {
                        attachProteinhasProteinKeyword.put(proteinhasProteinKeyword.getProteinhasProteinKeywordPK(), proteinhasProteinKeyword);
                    } else {
                        Protein protein = em.find(Protein.class, proteinhasProteinKeywordToAttach.getProtein().getWid());
                        if (protein != null) {
                            proteinhasProteinKeywordToAttach.setProtein(protein);
                        } else {
                            protein = proteinhasProteinKeywordToAttach.getProtein();
                            protein.setRelationsToNull();
                            protein.setProteinhasProteinKeyword(null);
                            pController.create(protein);
                            proteinhasProteinKeywordToAttach.setProtein(em.find(Protein.class, protein.getWid()));
                        }
                        attachProteinhasProteinKeyword.put(proteinhasProteinKeywordToAttach.getProteinhasProteinKeywordPK(), proteinhasProteinKeywordToAttach);
                    }
                }
                proteinKeyword.setProteinhasProteinKeyword(attachProteinhasProteinKeyword);
            }
            em.persist(proteinKeyword);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProteinKeyword(proteinKeyword.getWid()) != null) {
                throw new PreexistingEntityException("ProteinKeyword " + proteinKeyword + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(ProteinKeyword proteinKeyword) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editting " + this.getClass().getSimpleName() + ": " + proteinKeyword.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(proteinKeyword);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = proteinKeyword.getWid();
                if (findProteinKeyword(id) == null) {
                    throw new NonexistentEntityException("The proteinKeyword with id " + id + " no longer exists.");
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
            ProteinKeyword proteinKeyword;
            try {
                proteinKeyword = em.getReference(ProteinKeyword.class, id);
                proteinKeyword.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proteinKeyword with id " + id + " no longer exists.", enfe);
            }
            em.remove(proteinKeyword);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<ProteinKeyword> findProteinKeywordEntities() {
        return findProteinKeywordEntities(true, -1, -1);
    }
    
    public List<ProteinKeyword> findProteinKeywordEntities(int maxResults, int firstResult) {
        return findProteinKeywordEntities(false, maxResults, firstResult);
    }
    
    private List<ProteinKeyword> findProteinKeywordEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProteinKeyword.class));
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
    
    public ProteinKeyword findProteinKeyword(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProteinKeyword.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getProteinKeywordCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProteinKeyword> rt = cq.from(ProteinKeyword.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
