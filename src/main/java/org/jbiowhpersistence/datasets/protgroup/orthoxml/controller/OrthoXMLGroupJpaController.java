package org.jbiowhpersistence.datasets.protgroup.orthoxml.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXML;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXMLGroupGeneRef;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXMLGroup;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the OrthoXMLGroup Jpa Controller
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
public class OrthoXMLGroupJpaController extends AbstractJpaController<OrthoXMLGroup> implements Serializable {

    public OrthoXMLGroupJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrthoXMLGroup orthoXMLGroup) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + orthoXMLGroup.getWid());
        if (orthoXMLGroup.getOrthoXMLGroupGeneRef() == null) {
            orthoXMLGroup.setOrthoXMLGroupGeneRef(new HashSet<OrthoXMLGroupGeneRef>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrthoXMLJpaController oCtrl = new OrthoXMLJpaController(emf);
            OrthoXML orthoXML = orthoXMLGroup.getOrthoXML();
            if (orthoXML != null) {
                orthoXML = em.find(orthoXML.getClass(), orthoXML.getWid());
                if (orthoXML != null) {
                    orthoXMLGroup.setOrthoXML(orthoXML);
                } else {
                    orthoXML = orthoXMLGroup.getOrthoXML();
                    orthoXML.setOrthoXMLGroup(null);
                    oCtrl.create(orthoXML);
                    orthoXMLGroup.setOrthoXML(em.getReference(orthoXML.getClass(), orthoXML.getWid()));
                }
            }
            Set<OrthoXMLGroupGeneRef> attachedOrthoXMLGroupGeneRef = new HashSet<OrthoXMLGroupGeneRef>();
            for (OrthoXMLGroupGeneRef orthoXMLGroupGeneRefOrthoXMLGroupGeneRefToAttach : orthoXMLGroup.getOrthoXMLGroupGeneRef()) {
                OrthoXMLGroupGeneRef orthoXMLGroupGeneRefOrthoXMLGroupGeneRef = em.find(orthoXMLGroupGeneRefOrthoXMLGroupGeneRefToAttach.getClass(), orthoXMLGroupGeneRefOrthoXMLGroupGeneRefToAttach.getWid());
                if (orthoXMLGroupGeneRefOrthoXMLGroupGeneRef != null) {
                    attachedOrthoXMLGroupGeneRef.add(orthoXMLGroupGeneRefOrthoXMLGroupGeneRef);
                } else {
                    attachedOrthoXMLGroupGeneRef.add(orthoXMLGroupGeneRefOrthoXMLGroupGeneRefToAttach);
                }
            }
            orthoXMLGroup.setOrthoXMLGroupGeneRef(attachedOrthoXMLGroupGeneRef);
            em.persist(orthoXMLGroup);
            if (orthoXML != null) {
                orthoXML.getOrthoXMLGroup().add(orthoXMLGroup);
                orthoXML = em.merge(orthoXML);
            }
            for (OrthoXMLGroupGeneRef orthoXMLGroupGeneRefOrthoXMLGroupGeneRef : orthoXMLGroup.getOrthoXMLGroupGeneRef()) {
                OrthoXMLGroup oldOrthoXMLGroupOfOrthoXMLGroupGeneRefOrthoXMLGroupGeneRef = orthoXMLGroupGeneRefOrthoXMLGroupGeneRef.getOrthoXMLGroup();
                orthoXMLGroupGeneRefOrthoXMLGroupGeneRef.setOrthoXMLGroup(orthoXMLGroup);
                orthoXMLGroupGeneRefOrthoXMLGroupGeneRef = em.merge(orthoXMLGroupGeneRefOrthoXMLGroupGeneRef);
                if (oldOrthoXMLGroupOfOrthoXMLGroupGeneRefOrthoXMLGroupGeneRef != null) {
                    oldOrthoXMLGroupOfOrthoXMLGroupGeneRefOrthoXMLGroupGeneRef.getOrthoXMLGroupGeneRef().remove(orthoXMLGroupGeneRefOrthoXMLGroupGeneRef);
                    oldOrthoXMLGroupOfOrthoXMLGroupGeneRefOrthoXMLGroupGeneRef = em.merge(oldOrthoXMLGroupOfOrthoXMLGroupGeneRefOrthoXMLGroupGeneRef);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrthoXMLGroup(orthoXMLGroup.getWid()) != null) {
                throw new PreexistingEntityException("OrthoXMLGroup " + orthoXMLGroup + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrthoXMLGroup orthoXMLGroup) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + orthoXMLGroup.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrthoXMLGroup persistentOrthoXMLGroup = em.find(OrthoXMLGroup.class, orthoXMLGroup.getWid());
            OrthoXML orthoXMLOld = persistentOrthoXMLGroup.getOrthoXML();
            OrthoXML orthoXMLNew = orthoXMLGroup.getOrthoXML();
            Set<OrthoXMLGroupGeneRef> orthoXMLGroupGeneRefOld = persistentOrthoXMLGroup.getOrthoXMLGroupGeneRef();
            Set<OrthoXMLGroupGeneRef> orthoXMLGroupGeneRefNew = orthoXMLGroup.getOrthoXMLGroupGeneRef();
            if (orthoXMLNew != null) {
                orthoXMLNew = em.getReference(orthoXMLNew.getClass(), orthoXMLNew.getWid());
                orthoXMLGroup.setOrthoXML(orthoXMLNew);
            }
            Set<OrthoXMLGroupGeneRef> attachedOrthoXMLGroupGeneRefNew = new HashSet<OrthoXMLGroupGeneRef>();
            for (OrthoXMLGroupGeneRef orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRefToAttach : orthoXMLGroupGeneRefNew) {
                orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRefToAttach = em.getReference(orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRefToAttach.getClass(), orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRefToAttach.getWid());
                attachedOrthoXMLGroupGeneRefNew.add(orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRefToAttach);
            }
            orthoXMLGroupGeneRefNew = attachedOrthoXMLGroupGeneRefNew;
            orthoXMLGroup.setOrthoXMLGroupGeneRef(orthoXMLGroupGeneRefNew);
            orthoXMLGroup = em.merge(orthoXMLGroup);
            if (orthoXMLOld != null && !orthoXMLOld.equals(orthoXMLNew)) {
                orthoXMLOld.getOrthoXMLGroup().remove(orthoXMLGroup);
                orthoXMLOld = em.merge(orthoXMLOld);
            }
            if (orthoXMLNew != null && !orthoXMLNew.equals(orthoXMLOld)) {
                orthoXMLNew.getOrthoXMLGroup().add(orthoXMLGroup);
                orthoXMLNew = em.merge(orthoXMLNew);
            }
            for (OrthoXMLGroupGeneRef orthoXMLGroupGeneRefOldOrthoXMLGroupGeneRef : orthoXMLGroupGeneRefOld) {
                if (!orthoXMLGroupGeneRefNew.contains(orthoXMLGroupGeneRefOldOrthoXMLGroupGeneRef)) {
                    orthoXMLGroupGeneRefOldOrthoXMLGroupGeneRef.setOrthoXMLGroup(null);
                    orthoXMLGroupGeneRefOldOrthoXMLGroupGeneRef = em.merge(orthoXMLGroupGeneRefOldOrthoXMLGroupGeneRef);
                }
            }
            for (OrthoXMLGroupGeneRef orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef : orthoXMLGroupGeneRefNew) {
                if (!orthoXMLGroupGeneRefOld.contains(orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef)) {
                    OrthoXMLGroup oldOrthoXMLGroupOfOrthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef = orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef.getOrthoXMLGroup();
                    orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef.setOrthoXMLGroup(orthoXMLGroup);
                    orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef = em.merge(orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef);
                    if (oldOrthoXMLGroupOfOrthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef != null && !oldOrthoXMLGroupOfOrthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef.equals(orthoXMLGroup)) {
                        oldOrthoXMLGroupOfOrthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef.getOrthoXMLGroupGeneRef().remove(orthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef);
                        oldOrthoXMLGroupOfOrthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef = em.merge(oldOrthoXMLGroupOfOrthoXMLGroupGeneRefNewOrthoXMLGroupGeneRef);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = orthoXMLGroup.getWid();
                if (findOrthoXMLGroup(id) == null) {
                    throw new NonexistentEntityException("The orthoXMLGroup with id " + id + " no longer exists.");
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
            OrthoXMLGroup orthoXMLGroup;
            try {
                orthoXMLGroup = em.getReference(OrthoXMLGroup.class, id);
                orthoXMLGroup.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orthoXMLGroup with id " + id + " no longer exists.", enfe);
            }
            OrthoXML orthoXML = orthoXMLGroup.getOrthoXML();
            if (orthoXML != null) {
                orthoXML.getOrthoXMLGroup().remove(orthoXMLGroup);
                orthoXML = em.merge(orthoXML);
            }
            Set<OrthoXMLGroupGeneRef> orthoXMLGroupGeneRef = orthoXMLGroup.getOrthoXMLGroupGeneRef();
            for (OrthoXMLGroupGeneRef orthoXMLGroupGeneRefOrthoXMLGroupGeneRef : orthoXMLGroupGeneRef) {
                orthoXMLGroupGeneRefOrthoXMLGroupGeneRef.setOrthoXMLGroup(null);
                orthoXMLGroupGeneRefOrthoXMLGroupGeneRef = em.merge(orthoXMLGroupGeneRefOrthoXMLGroupGeneRef);
            }
            em.remove(orthoXMLGroup);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrthoXMLGroup> findOrthoXMLGroupEntities() {
        return findOrthoXMLGroupEntities(true, -1, -1);
    }

    public List<OrthoXMLGroup> findOrthoXMLGroupEntities(int maxResults, int firstResult) {
        return findOrthoXMLGroupEntities(false, maxResults, firstResult);
    }

    private List<OrthoXMLGroup> findOrthoXMLGroupEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrthoXMLGroup.class));
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

    public OrthoXMLGroup findOrthoXMLGroup(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrthoXMLGroup.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrthoXMLGroupCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrthoXMLGroup> rt = cq.from(OrthoXMLGroup.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
