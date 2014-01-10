package org.jbiowhpersistence.datasets.protgroup.orthoxml.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXMLSpecie;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXML;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXMLGroup;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the OrthoXML Jpa Controller
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
public class OrthoXMLJpaController extends AbstractJpaController<OrthoXML> implements Serializable {

    public OrthoXMLJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrthoXML orthoXML) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + orthoXML.getWid());
        if (orthoXML.getOrthoXMLSpecie() == null) {
            orthoXML.setOrthoXMLSpecie(new HashSet<OrthoXMLSpecie>());
        }
        if (orthoXML.getOrthoXMLGroup() == null) {
            orthoXML.setOrthoXMLGroup(new HashSet<OrthoXMLGroup>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrthoXMLSpecieJpaController sCtrl = new OrthoXMLSpecieJpaController(emf);
            Set<OrthoXMLSpecie> attachedOrthoXMLSpecie = new HashSet<OrthoXMLSpecie>();
            for (OrthoXMLSpecie orthoXMLSpecieOrthoXMLSpecieToAttach : orthoXML.getOrthoXMLSpecie()) {
                OrthoXMLSpecie orthoXMLSpecieOrthoXMLSpecie = em.find(orthoXMLSpecieOrthoXMLSpecieToAttach.getClass(), orthoXMLSpecieOrthoXMLSpecieToAttach.getWid());
                if (orthoXMLSpecieOrthoXMLSpecie != null) {
                    attachedOrthoXMLSpecie.add(orthoXMLSpecieOrthoXMLSpecie);
                } else {
                    orthoXMLSpecieOrthoXMLSpecieToAttach.setOrthoXML(null);
                    sCtrl.create(orthoXMLSpecieOrthoXMLSpecieToAttach);
                    attachedOrthoXMLSpecie.add(em.getReference(orthoXMLSpecieOrthoXMLSpecieToAttach.getClass(), orthoXMLSpecieOrthoXMLSpecieToAttach.getWid()));
                }
            }
            orthoXML.setOrthoXMLSpecie(attachedOrthoXMLSpecie);
            OrthoXMLGroupJpaController gCtrl = new OrthoXMLGroupJpaController(emf);
            Set<OrthoXMLGroup> attachedOrthoXMLGroup = new HashSet<OrthoXMLGroup>();
            for (OrthoXMLGroup orthoXMLGroupOrthoXMLGroupToAttach : orthoXML.getOrthoXMLGroup()) {
                OrthoXMLGroup orthoXMLGroupOrthoXMLGroup = em.find(orthoXMLGroupOrthoXMLGroupToAttach.getClass(), orthoXMLGroupOrthoXMLGroupToAttach.getWid());
                if (orthoXMLGroupOrthoXMLGroup != null) {
                    attachedOrthoXMLGroup.add(orthoXMLGroupOrthoXMLGroup);
                } else {
                    orthoXMLGroupOrthoXMLGroupToAttach.setOrthoXML(null);
                    gCtrl.create(orthoXMLGroupOrthoXMLGroupToAttach);
                    attachedOrthoXMLGroup.add(em.getReference(orthoXMLGroupOrthoXMLGroupToAttach.getClass(), orthoXMLGroupOrthoXMLGroupToAttach.getWid()));

                }
            }
            orthoXML.setOrthoXMLGroup(attachedOrthoXMLGroup);
            em.persist(orthoXML);
            for (OrthoXMLSpecie orthoXMLSpecieOrthoXMLSpecie : orthoXML.getOrthoXMLSpecie()) {
                OrthoXML oldOrthoXMLOfOrthoXMLSpecieOrthoXMLSpecie = orthoXMLSpecieOrthoXMLSpecie.getOrthoXML();
                orthoXMLSpecieOrthoXMLSpecie.setOrthoXML(orthoXML);
                orthoXMLSpecieOrthoXMLSpecie = em.merge(orthoXMLSpecieOrthoXMLSpecie);
                if (oldOrthoXMLOfOrthoXMLSpecieOrthoXMLSpecie != null) {
                    oldOrthoXMLOfOrthoXMLSpecieOrthoXMLSpecie.getOrthoXMLSpecie().remove(orthoXMLSpecieOrthoXMLSpecie);
                    oldOrthoXMLOfOrthoXMLSpecieOrthoXMLSpecie = em.merge(oldOrthoXMLOfOrthoXMLSpecieOrthoXMLSpecie);
                }
            }
            for (OrthoXMLGroup orthoXMLGroupOrthoXMLGroup : orthoXML.getOrthoXMLGroup()) {
                OrthoXML oldOrthoXMLOfOrthoXMLGroupOrthoXMLGroup = orthoXMLGroupOrthoXMLGroup.getOrthoXML();
                orthoXMLGroupOrthoXMLGroup.setOrthoXML(orthoXML);
                orthoXMLGroupOrthoXMLGroup = em.merge(orthoXMLGroupOrthoXMLGroup);
                if (oldOrthoXMLOfOrthoXMLGroupOrthoXMLGroup != null) {
                    oldOrthoXMLOfOrthoXMLGroupOrthoXMLGroup.getOrthoXMLGroup().remove(orthoXMLGroupOrthoXMLGroup);
                    oldOrthoXMLOfOrthoXMLGroupOrthoXMLGroup = em.merge(oldOrthoXMLOfOrthoXMLGroupOrthoXMLGroup);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrthoXML(orthoXML.getWid()) != null) {
                throw new PreexistingEntityException("OrthoXML " + orthoXML + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrthoXML orthoXML) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + orthoXML.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrthoXML persistentOrthoXML = em.find(OrthoXML.class, orthoXML.getWid());
            Set<OrthoXMLSpecie> orthoXMLSpecieOld = persistentOrthoXML.getOrthoXMLSpecie();
            Set<OrthoXMLSpecie> orthoXMLSpecieNew = orthoXML.getOrthoXMLSpecie();
            Set<OrthoXMLGroup> orthoXMLGroupOld = persistentOrthoXML.getOrthoXMLGroup();
            Set<OrthoXMLGroup> orthoXMLGroupNew = orthoXML.getOrthoXMLGroup();
            Set<OrthoXMLSpecie> attachedOrthoXMLSpecieNew = new HashSet<OrthoXMLSpecie>();
            for (OrthoXMLSpecie orthoXMLSpecieNewOrthoXMLSpecieToAttach : orthoXMLSpecieNew) {
                orthoXMLSpecieNewOrthoXMLSpecieToAttach = em.getReference(orthoXMLSpecieNewOrthoXMLSpecieToAttach.getClass(), orthoXMLSpecieNewOrthoXMLSpecieToAttach.getWid());
                attachedOrthoXMLSpecieNew.add(orthoXMLSpecieNewOrthoXMLSpecieToAttach);
            }
            orthoXMLSpecieNew = attachedOrthoXMLSpecieNew;
            orthoXML.setOrthoXMLSpecie(orthoXMLSpecieNew);
            Set<OrthoXMLGroup> attachedOrthoXMLGroupNew = new HashSet<OrthoXMLGroup>();
            for (OrthoXMLGroup orthoXMLGroupNewOrthoXMLGroupToAttach : orthoXMLGroupNew) {
                orthoXMLGroupNewOrthoXMLGroupToAttach = em.getReference(orthoXMLGroupNewOrthoXMLGroupToAttach.getClass(), orthoXMLGroupNewOrthoXMLGroupToAttach.getWid());
                attachedOrthoXMLGroupNew.add(orthoXMLGroupNewOrthoXMLGroupToAttach);
            }
            orthoXMLGroupNew = attachedOrthoXMLGroupNew;
            orthoXML.setOrthoXMLGroup(orthoXMLGroupNew);
            orthoXML = em.merge(orthoXML);
            for (OrthoXMLSpecie orthoXMLSpecieOldOrthoXMLSpecie : orthoXMLSpecieOld) {
                if (!orthoXMLSpecieNew.contains(orthoXMLSpecieOldOrthoXMLSpecie)) {
                    orthoXMLSpecieOldOrthoXMLSpecie.setOrthoXML(null);
                    orthoXMLSpecieOldOrthoXMLSpecie = em.merge(orthoXMLSpecieOldOrthoXMLSpecie);
                }
            }
            for (OrthoXMLSpecie orthoXMLSpecieNewOrthoXMLSpecie : orthoXMLSpecieNew) {
                if (!orthoXMLSpecieOld.contains(orthoXMLSpecieNewOrthoXMLSpecie)) {
                    OrthoXML oldOrthoXMLOfOrthoXMLSpecieNewOrthoXMLSpecie = orthoXMLSpecieNewOrthoXMLSpecie.getOrthoXML();
                    orthoXMLSpecieNewOrthoXMLSpecie.setOrthoXML(orthoXML);
                    orthoXMLSpecieNewOrthoXMLSpecie = em.merge(orthoXMLSpecieNewOrthoXMLSpecie);
                    if (oldOrthoXMLOfOrthoXMLSpecieNewOrthoXMLSpecie != null && !oldOrthoXMLOfOrthoXMLSpecieNewOrthoXMLSpecie.equals(orthoXML)) {
                        oldOrthoXMLOfOrthoXMLSpecieNewOrthoXMLSpecie.getOrthoXMLSpecie().remove(orthoXMLSpecieNewOrthoXMLSpecie);
                        oldOrthoXMLOfOrthoXMLSpecieNewOrthoXMLSpecie = em.merge(oldOrthoXMLOfOrthoXMLSpecieNewOrthoXMLSpecie);
                    }
                }
            }
            for (OrthoXMLGroup orthoXMLGroupOldOrthoXMLGroup : orthoXMLGroupOld) {
                if (!orthoXMLGroupNew.contains(orthoXMLGroupOldOrthoXMLGroup)) {
                    orthoXMLGroupOldOrthoXMLGroup.setOrthoXML(null);
                    orthoXMLGroupOldOrthoXMLGroup = em.merge(orthoXMLGroupOldOrthoXMLGroup);
                }
            }
            for (OrthoXMLGroup orthoXMLGroupNewOrthoXMLGroup : orthoXMLGroupNew) {
                if (!orthoXMLGroupOld.contains(orthoXMLGroupNewOrthoXMLGroup)) {
                    OrthoXML oldOrthoXMLOfOrthoXMLGroupNewOrthoXMLGroup = orthoXMLGroupNewOrthoXMLGroup.getOrthoXML();
                    orthoXMLGroupNewOrthoXMLGroup.setOrthoXML(orthoXML);
                    orthoXMLGroupNewOrthoXMLGroup = em.merge(orthoXMLGroupNewOrthoXMLGroup);
                    if (oldOrthoXMLOfOrthoXMLGroupNewOrthoXMLGroup != null && !oldOrthoXMLOfOrthoXMLGroupNewOrthoXMLGroup.equals(orthoXML)) {
                        oldOrthoXMLOfOrthoXMLGroupNewOrthoXMLGroup.getOrthoXMLGroup().remove(orthoXMLGroupNewOrthoXMLGroup);
                        oldOrthoXMLOfOrthoXMLGroupNewOrthoXMLGroup = em.merge(oldOrthoXMLOfOrthoXMLGroupNewOrthoXMLGroup);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = orthoXML.getWid();
                if (findOrthoXML(id) == null) {
                    throw new NonexistentEntityException("The orthoXML with id " + id + " no longer exists.");
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
            OrthoXML orthoXML;
            try {
                orthoXML = em.getReference(OrthoXML.class, id);
                orthoXML.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orthoXML with id " + id + " no longer exists.", enfe);
            }
            Set<OrthoXMLSpecie> orthoXMLSpecie = orthoXML.getOrthoXMLSpecie();
            for (OrthoXMLSpecie orthoXMLSpecieOrthoXMLSpecie : orthoXMLSpecie) {
                orthoXMLSpecieOrthoXMLSpecie.setOrthoXML(null);
                orthoXMLSpecieOrthoXMLSpecie = em.merge(orthoXMLSpecieOrthoXMLSpecie);
            }
            Set<OrthoXMLGroup> orthoXMLGroup = orthoXML.getOrthoXMLGroup();
            for (OrthoXMLGroup orthoXMLGroupOrthoXMLGroup : orthoXMLGroup) {
                orthoXMLGroupOrthoXMLGroup.setOrthoXML(null);
                orthoXMLGroupOrthoXMLGroup = em.merge(orthoXMLGroupOrthoXMLGroup);
            }
            em.remove(orthoXML);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrthoXML> findOrthoXMLEntities() {
        return findOrthoXMLEntities(true, -1, -1);
    }

    public List<OrthoXML> findOrthoXMLEntities(int maxResults, int firstResult) {
        return findOrthoXMLEntities(false, maxResults, firstResult);
    }

    private List<OrthoXML> findOrthoXMLEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrthoXML.class));
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

    public OrthoXML findOrthoXML(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrthoXML.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrthoXMLCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrthoXML> rt = cq.from(OrthoXML.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
