package org.jbiowhpersistence.datasets.ontology.controller;

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
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.ontology.entities.OntologySynonym;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyhasOntologySynonym;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyhasOntologySynonymPK;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the OntologySynonym Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Aug 30, 2012
 */
public class OntologySynonymJpaController extends AbstractJpaController<OntologySynonym> implements Serializable {

    public OntologySynonymJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OntologySynonym ontologySynonym) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + ontologySynonym.getWid());
        if (ontologySynonym.getOntologyhasOntologySynonym() == null) {
            ontologySynonym.setOntologyhasOntologySynonym(new HashMap<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!ontologySynonym.getOntologyhasOntologySynonym().isEmpty()) {
                OntologyJpaController oController = new OntologyJpaController(emf);
                Map<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym> hasSynMap = new HashMap<>();
                for (OntologyhasOntologySynonym hasSyn : ontologySynonym.getOntologyhasOntologySynonym().values()) {
                    OntologyhasOntologySynonym hasSynOnDB = em.find(OntologyhasOntologySynonym.class, hasSyn.getOntologyhasOntologySynonymPK());
                    if (hasSynOnDB != null) {
                        hasSynMap.put(hasSynOnDB.getOntologyhasOntologySynonymPK(), hasSynOnDB);
                    } else {
                        Ontology ont = em.find(Ontology.class, hasSyn.getOntology().getWid());
                        if (ont != null) {
                            hasSyn.setOntology(ont);
                        } else {
                            ont = hasSyn.getOntology();
                            ont.setRelationsToNull();
                            ont.setOntologyhasOntologySynonym(null);
                            oController.create(ont);
                            hasSyn.setOntology(em.getReference(Ontology.class, hasSyn.getOntology().getWid()));
                        }
                        hasSynMap.put(hasSyn.getOntologyhasOntologySynonymPK(), hasSyn);
                    }
                }
                ontologySynonym.setOntologyhasOntologySynonym(hasSynMap);
            }
            em.persist(ontologySynonym);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOntologySynonym(ontologySynonym.getWid()) != null) {
                throw new PreexistingEntityException("OntologySynonym " + ontologySynonym + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OntologySynonym ontologySynonym) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editting " + this.getClass().getSimpleName() + ": " + ontologySynonym.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(ontologySynonym);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ontologySynonym.getWid();
                if (findOntologySynonym(id) == null) {
                    throw new NonexistentEntityException("The ontologySynonym with id " + id + " no longer exists.");
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
            OntologySynonym ontologySynonym;
            try {
                ontologySynonym = em.getReference(OntologySynonym.class, id);
                ontologySynonym.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ontologySynonym with id " + id + " no longer exists.", enfe);
            }
            em.remove(ontologySynonym);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OntologySynonym> findOntologySynonymEntities() {
        return findOntologySynonymEntities(true, -1, -1);
    }

    public List<OntologySynonym> findOntologySynonymEntities(int maxResults, int firstResult) {
        return findOntologySynonymEntities(false, maxResults, firstResult);
    }

    private List<OntologySynonym> findOntologySynonymEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OntologySynonym.class));
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

    public OntologySynonym findOntologySynonym(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OntologySynonym.class, id);
        } finally {
            em.close();
        }
    }

    public int getOntologySynonymCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OntologySynonym> rt = cq.from(OntologySynonym.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
