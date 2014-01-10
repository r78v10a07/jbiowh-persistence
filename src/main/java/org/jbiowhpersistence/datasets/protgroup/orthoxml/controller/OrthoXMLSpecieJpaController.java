package org.jbiowhpersistence.datasets.protgroup.orthoxml.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXML;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXMLSpeciesDatabase;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXMLSpecie;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the OrthoXMLSpecie Jpa Controller
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 9, 2014
 */
public class OrthoXMLSpecieJpaController extends AbstractJpaController<OrthoXMLSpecie> implements Serializable {

    public OrthoXMLSpecieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrthoXMLSpecie orthoXMLSpecie) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + orthoXMLSpecie.getWid());
        if (orthoXMLSpecie.getOrthoXMLSpeciesDatabase() == null) {
            orthoXMLSpecie.setOrthoXMLSpeciesDatabase(new HashSet<OrthoXMLSpeciesDatabase>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrthoXMLJpaController oCtrl = new OrthoXMLJpaController(emf);
            OrthoXML orthoXML = orthoXMLSpecie.getOrthoXML();
            if (orthoXML != null) {
                orthoXML = em.find(orthoXML.getClass(), orthoXML.getWid());
                if (orthoXML != null) {
                    orthoXMLSpecie.setOrthoXML(orthoXML);
                } else {
                    orthoXML = orthoXMLSpecie.getOrthoXML();
                    orthoXML.setOrthoXMLGroup(null);
                    oCtrl.create(orthoXML);
                    orthoXMLSpecie.setOrthoXML(em.getReference(orthoXML.getClass(), orthoXML.getWid()));
                }
            }
            Set<OrthoXMLSpeciesDatabase> attachedOrthoXMLSpeciesDatabase = new HashSet<OrthoXMLSpeciesDatabase>();
            for (OrthoXMLSpeciesDatabase orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabaseToAttach : orthoXMLSpecie.getOrthoXMLSpeciesDatabase()) {
                OrthoXMLSpeciesDatabase orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase = em.find(orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabaseToAttach.getClass(), orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabaseToAttach.getWid());
                if (orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase != null) {
                    attachedOrthoXMLSpeciesDatabase.add(orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase);
                } else {
                    attachedOrthoXMLSpeciesDatabase.add(orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabaseToAttach);
                }
            }
            orthoXMLSpecie.setOrthoXMLSpeciesDatabase(attachedOrthoXMLSpeciesDatabase);
            em.persist(orthoXMLSpecie);
            if (orthoXML != null) {
                orthoXML.getOrthoXMLSpecie().add(orthoXMLSpecie);
                orthoXML = em.merge(orthoXML);
            }
            for (OrthoXMLSpeciesDatabase orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase : orthoXMLSpecie.getOrthoXMLSpeciesDatabase()) {
                OrthoXMLSpecie oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase = orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase.getOrthoXMLSpecie();
                orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase.setOrthoXMLSpecie(orthoXMLSpecie);
                orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase = em.merge(orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase);
                if (oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase != null) {
                    oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase.getOrthoXMLSpeciesDatabase().remove(orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase);
                    oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase = em.merge(oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrthoXMLSpecie(orthoXMLSpecie.getWid()) != null) {
                throw new PreexistingEntityException("OrthoXMLSpecie " + orthoXMLSpecie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrthoXMLSpecie orthoXMLSpecie) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + orthoXMLSpecie.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrthoXMLSpecie persistentOrthoXMLSpecie = em.find(OrthoXMLSpecie.class, orthoXMLSpecie.getWid());
            OrthoXML orthoXMLOld = persistentOrthoXMLSpecie.getOrthoXML();
            OrthoXML orthoXMLNew = orthoXMLSpecie.getOrthoXML();
            Set<OrthoXMLSpeciesDatabase> orthoXMLSpeciesDatabaseOld = persistentOrthoXMLSpecie.getOrthoXMLSpeciesDatabase();
            Set<OrthoXMLSpeciesDatabase> orthoXMLSpeciesDatabaseNew = orthoXMLSpecie.getOrthoXMLSpeciesDatabase();
            if (orthoXMLNew != null) {
                orthoXMLNew = em.getReference(orthoXMLNew.getClass(), orthoXMLNew.getWid());
                orthoXMLSpecie.setOrthoXML(orthoXMLNew);
            }
            Set<OrthoXMLSpeciesDatabase> attachedOrthoXMLSpeciesDatabaseNew = new HashSet<OrthoXMLSpeciesDatabase>();
            for (OrthoXMLSpeciesDatabase orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabaseToAttach : orthoXMLSpeciesDatabaseNew) {
                orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabaseToAttach = em.getReference(orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabaseToAttach.getClass(), orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabaseToAttach.getWid());
                attachedOrthoXMLSpeciesDatabaseNew.add(orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabaseToAttach);
            }
            orthoXMLSpeciesDatabaseNew = attachedOrthoXMLSpeciesDatabaseNew;
            orthoXMLSpecie.setOrthoXMLSpeciesDatabase(orthoXMLSpeciesDatabaseNew);
            orthoXMLSpecie = em.merge(orthoXMLSpecie);
            if (orthoXMLOld != null && !orthoXMLOld.equals(orthoXMLNew)) {
                orthoXMLOld.getOrthoXMLSpecie().remove(orthoXMLSpecie);
                orthoXMLOld = em.merge(orthoXMLOld);
            }
            if (orthoXMLNew != null && !orthoXMLNew.equals(orthoXMLOld)) {
                orthoXMLNew.getOrthoXMLSpecie().add(orthoXMLSpecie);
                orthoXMLNew = em.merge(orthoXMLNew);
            }
            for (OrthoXMLSpeciesDatabase orthoXMLSpeciesDatabaseOldOrthoXMLSpeciesDatabase : orthoXMLSpeciesDatabaseOld) {
                if (!orthoXMLSpeciesDatabaseNew.contains(orthoXMLSpeciesDatabaseOldOrthoXMLSpeciesDatabase)) {
                    orthoXMLSpeciesDatabaseOldOrthoXMLSpeciesDatabase.setOrthoXMLSpecie(null);
                    orthoXMLSpeciesDatabaseOldOrthoXMLSpeciesDatabase = em.merge(orthoXMLSpeciesDatabaseOldOrthoXMLSpeciesDatabase);
                }
            }
            for (OrthoXMLSpeciesDatabase orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase : orthoXMLSpeciesDatabaseNew) {
                if (!orthoXMLSpeciesDatabaseOld.contains(orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase)) {
                    OrthoXMLSpecie oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase = orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase.getOrthoXMLSpecie();
                    orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase.setOrthoXMLSpecie(orthoXMLSpecie);
                    orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase = em.merge(orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase);
                    if (oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase != null && !oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase.equals(orthoXMLSpecie)) {
                        oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase.getOrthoXMLSpeciesDatabase().remove(orthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase);
                        oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase = em.merge(oldOrthoXMLSpecieOfOrthoXMLSpeciesDatabaseNewOrthoXMLSpeciesDatabase);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = orthoXMLSpecie.getWid();
                if (findOrthoXMLSpecie(id) == null) {
                    throw new NonexistentEntityException("The orthoXMLSpecie with id " + id + " no longer exists.");
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
            OrthoXMLSpecie orthoXMLSpecie;
            try {
                orthoXMLSpecie = em.getReference(OrthoXMLSpecie.class, id);
                orthoXMLSpecie.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orthoXMLSpecie with id " + id + " no longer exists.", enfe);
            }
            OrthoXML orthoXML = orthoXMLSpecie.getOrthoXML();
            if (orthoXML != null) {
                orthoXML.getOrthoXMLSpecie().remove(orthoXMLSpecie);
                orthoXML = em.merge(orthoXML);
            }
            Set<OrthoXMLSpeciesDatabase> orthoXMLSpeciesDatabase = orthoXMLSpecie.getOrthoXMLSpeciesDatabase();
            for (OrthoXMLSpeciesDatabase orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase : orthoXMLSpeciesDatabase) {
                orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase.setOrthoXMLSpecie(null);
                orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase = em.merge(orthoXMLSpeciesDatabaseOrthoXMLSpeciesDatabase);
            }
            em.remove(orthoXMLSpecie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrthoXMLSpecie> findOrthoXMLSpecieEntities() {
        return findOrthoXMLSpecieEntities(true, -1, -1);
    }

    public List<OrthoXMLSpecie> findOrthoXMLSpecieEntities(int maxResults, int firstResult) {
        return findOrthoXMLSpecieEntities(false, maxResults, firstResult);
    }

    private List<OrthoXMLSpecie> findOrthoXMLSpecieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrthoXMLSpecie.class));
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

    public OrthoXMLSpecie findOrthoXMLSpecie(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrthoXMLSpecie.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrthoXMLSpecieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrthoXMLSpecie> rt = cq.from(OrthoXMLSpecie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
