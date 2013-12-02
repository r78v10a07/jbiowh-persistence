package org.jbiowhpersistence.datasets.taxonomy.controller;

import java.io.Serializable;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.datasets.taxonomy.entities.TaxonomyDivision;
import org.jbiowhpersistence.datasets.taxonomy.entities.TaxonomyGenCode;
import org.jbiowhpersistence.datasets.taxonomy.entities.TaxonomyUnParseCitation;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the Taxonomy Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Aug 30, 2012
 */
public class TaxonomyJpaController extends AbstractJpaController<Taxonomy> implements Serializable {

    public TaxonomyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Taxonomy taxonomy) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + taxonomy.getWid());
        if (taxonomy.getUnparse() == null) {
            taxonomy.setUnparse(new HashSet<TaxonomyUnParseCitation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (taxonomy.getDivision() != null) {
                TaxonomyDivision division = em.find(TaxonomyDivision.class, taxonomy.getDivision().getWid());
                if (division != null) {
                    taxonomy.setDivision(division);
                }
            }
            if (taxonomy.getGencode() != null) {
                TaxonomyGenCode genCode = em.find(TaxonomyGenCode.class, taxonomy.getGencode().getWid());
                if (genCode != null) {
                    taxonomy.setGencode(genCode);
                }
            }
            if (taxonomy.getMcgencode() != null) {
                TaxonomyGenCode mcGenCode = em.find(TaxonomyGenCode.class, taxonomy.getMcgencode().getWid());
                if (mcGenCode != null) {
                    taxonomy.setMcgencode(mcGenCode);
                }
            }
            if (!taxonomy.getUnparse().isEmpty()) {
                Set<TaxonomyUnParseCitation> unParse = new HashSet();
                for (TaxonomyUnParseCitation unp : taxonomy.getUnparse()) {
                    TaxonomyUnParseCitation u = em.find(TaxonomyUnParseCitation.class, unp.getWid());
                    if (u != null) {
                        unParse.add(u);
                    } else {
                        unParse.add(unp);
                    }
                }
                taxonomy.setUnparse(unParse);
            }
            taxonomy.setDataSet(createDataSet(emf, em, taxonomy.getDataSet()));
            em.persist(taxonomy);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTaxonomy(taxonomy.getWid()) != null) {
                throw new PreexistingEntityException("Taxonomy " + taxonomy + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Taxonomy taxonomy) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editting " + this.getClass().getSimpleName() + ": " + taxonomy.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(taxonomy);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = taxonomy.getWid();
                if (findTaxonomy(id) == null) {
                    throw new NonexistentEntityException("The taxonomy with id " + id + " no longer exists.");
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
            Taxonomy taxonomy;
            try {
                taxonomy = em.getReference(Taxonomy.class, id);
                taxonomy.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The taxonomy with id " + id + " no longer exists.", enfe);
            }
            em.remove(taxonomy);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Taxonomy> findTaxonomyEntities() {
        return findTaxonomyEntities(true, -1, -1);
    }

    public List<Taxonomy> findTaxonomyEntities(int maxResults, int firstResult) {
        return findTaxonomyEntities(false, maxResults, firstResult);
    }

    private List<Taxonomy> findTaxonomyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Taxonomy.class));
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

    public Taxonomy findTaxonomy(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Taxonomy.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaxonomyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Taxonomy> rt = cq.from(Taxonomy.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
