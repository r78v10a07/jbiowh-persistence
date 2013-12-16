package org.jbiowhpersistence.datasets.protgroup.pirsf.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.ontology.entities.OntologySynonym;
import org.jbiowhpersistence.datasets.protein.controller.ProteinJpaController;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.pirsf.entities.Pirsf;
import org.jbiowhpersistence.datasets.protgroup.pirsf.entities.PirsfhasProtein;
import org.jbiowhpersistence.datasets.protgroup.pirsf.entities.PirsfhasProteinPK;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;

/**
 * This class is
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 11, 2013
 */
public class PirsfJpaController extends AbstractJpaController<Pirsf> implements Serializable {
    
    public PirsfJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Pirsf pirsf) throws Exception {
        if (pirsf.getpIRSFhasProtein() == null) {
            pirsf.setpIRSFhasProtein(new HashMap<PirsfhasProteinPK, PirsfhasProtein>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();   
            if (!pirsf.getpIRSFhasProtein().isEmpty()) {
                ProteinJpaController sController = new ProteinJpaController(emf);
                Map<PirsfhasProteinPK, PirsfhasProtein> hasSynMap = new HashMap();
                for (PirsfhasProtein hasSyn : pirsf.getpIRSFhasProtein().values()) {
                    PirsfhasProtein hasSynOnDB = em.find(PirsfhasProtein.class, hasSyn.getPIRSFhasProteinPK());
                    if (hasSynOnDB != null) {
                        hasSynMap.put(hasSynOnDB.getPIRSFhasProteinPK(), hasSynOnDB);
                    } else {
                        Protein syn = em.find(Protein.class, hasSyn.getProtein().getWid());
                        if (syn != null) {
                            hasSyn.setProtein(syn);
                        } else {
                            syn = hasSyn.getProtein();
                            syn.setpIRSFhasProtein(null);
                            sController.create(syn);
                            hasSyn.setProtein(em.getReference(Protein.class, hasSyn.getProtein().getWid()));
                        }
                        hasSynMap.put(hasSyn.getPIRSFhasProteinPK(), hasSyn);
                    }
                }
                pirsf.setpIRSFhasProtein(hasSynMap);
            }            
            em.persist(pirsf);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Pirsf pirsf) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pirsf = em.merge(pirsf);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pirsf.getWid();
                if (findPirsf(id) == null) {
                    throw new NonexistentEntityException("The pirsf with id " + id + " no longer exists.");
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
            Pirsf pirsf;
            try {
                pirsf = em.getReference(Pirsf.class, id);
                pirsf.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pirsf with id " + id + " no longer exists.", enfe);
            }
            em.remove(pirsf);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Pirsf> findPirsfEntities() {
        return findPirsfEntities(true, -1, -1);
    }
    
    public List<Pirsf> findPirsfEntities(int maxResults, int firstResult) {
        return findPirsfEntities(false, maxResults, firstResult);
    }
    
    private List<Pirsf> findPirsfEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pirsf.class));
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
    
    public Pirsf findPirsf(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pirsf.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getPirsfCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pirsf> rt = cq.from(Pirsf.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
