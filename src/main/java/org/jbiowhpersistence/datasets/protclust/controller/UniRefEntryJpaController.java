package org.jbiowhpersistence.datasets.protclust.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntryProperty;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the UniRefEntry Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Aug 31, 2011
 */
public class UniRefEntryJpaController extends AbstractJpaController<UniRefEntry> implements Serializable {

    public UniRefEntryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UniRefEntry uniRefEntry) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + uniRefEntry.getWid());
        if (uniRefEntry.getUniRefEntryProperty() == null) {
            uniRefEntry.setUniRefEntryProperty(new HashSet<UniRefEntryProperty>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!uniRefEntry.getUniRefEntryProperty().isEmpty()) {
                Set<UniRefEntryProperty> attachedUniRefEntryProperty = new HashSet<>();
                for (UniRefEntryProperty uniRefEntryPropertyUniRefEntryPropertyToAttach : uniRefEntry.getUniRefEntryProperty()) {
                    UniRefEntryProperty uniRefEntryPropertyUniRefEntryProperty = em.find(uniRefEntryPropertyUniRefEntryPropertyToAttach.getClass(), uniRefEntryPropertyUniRefEntryPropertyToAttach.getWid());
                    if (uniRefEntryPropertyUniRefEntryProperty != null) {
                        attachedUniRefEntryProperty.add(uniRefEntryPropertyUniRefEntryProperty);
                    } else {
                        attachedUniRefEntryProperty.add(uniRefEntryPropertyUniRefEntryPropertyToAttach);
                    }
                }
                uniRefEntry.setUniRefEntryProperty(attachedUniRefEntryProperty);
            }
            uniRefEntry.setUniRefMember(createUniRefMember(emf, em, uniRefEntry.getUniRefMember()));
            uniRefEntry.setTaxonomy(createTaxonomy(emf, em, uniRefEntry.getTaxonomy()));
            uniRefEntry.setProtein(createProtein(emf, em, uniRefEntry.getProtein()));
            uniRefEntry.setDataSet(createDataSet(emf, em, uniRefEntry.getDataSet()));
            em.persist(uniRefEntry);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUniRefEntry(uniRefEntry.getWid()) != null) {
                throw new PreexistingEntityException("UniRefEntry " + uniRefEntry + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UniRefEntry uniRefEntry) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editting " + this.getClass().getSimpleName() + ": " + uniRefEntry.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UniRefEntry persistentUniRefEntry = em.find(UniRefEntry.class, uniRefEntry.getWid());
            Set<UniRefEntryProperty> uniRefEntryPropertyOld = persistentUniRefEntry.getUniRefEntryProperty();
            Set<UniRefEntryProperty> uniRefEntryPropertyNew = uniRefEntry.getUniRefEntryProperty();
            Set<UniRefEntryProperty> attachedUniRefEntryPropertyNew = new HashSet<>();
            for (UniRefEntryProperty uniRefEntryPropertyNewUniRefEntryPropertyToAttach : uniRefEntryPropertyNew) {
                uniRefEntryPropertyNewUniRefEntryPropertyToAttach = em.getReference(uniRefEntryPropertyNewUniRefEntryPropertyToAttach.getClass(), uniRefEntryPropertyNewUniRefEntryPropertyToAttach.getWid());
                attachedUniRefEntryPropertyNew.add(uniRefEntryPropertyNewUniRefEntryPropertyToAttach);
            }
            uniRefEntryPropertyNew = attachedUniRefEntryPropertyNew;
            uniRefEntry.setUniRefEntryProperty(uniRefEntryPropertyNew);
            uniRefEntry = em.merge(uniRefEntry);
            for (UniRefEntryProperty uniRefEntryPropertyOldUniRefEntryProperty : uniRefEntryPropertyOld) {
                if (!uniRefEntryPropertyNew.contains(uniRefEntryPropertyOldUniRefEntryProperty)) {
                    uniRefEntryPropertyOldUniRefEntryProperty.setUniRefEntry(null);
                    uniRefEntryPropertyOldUniRefEntryProperty = em.merge(uniRefEntryPropertyOldUniRefEntryProperty);
                }
            }
            for (UniRefEntryProperty uniRefEntryPropertyNewUniRefEntryProperty : uniRefEntryPropertyNew) {
                if (!uniRefEntryPropertyOld.contains(uniRefEntryPropertyNewUniRefEntryProperty)) {
                    UniRefEntry oldUniRefEntryOfUniRefEntryPropertyNewUniRefEntryProperty = uniRefEntryPropertyNewUniRefEntryProperty.getUniRefEntry();
                    uniRefEntryPropertyNewUniRefEntryProperty.setUniRefEntry(uniRefEntry);
                    uniRefEntryPropertyNewUniRefEntryProperty = em.merge(uniRefEntryPropertyNewUniRefEntryProperty);
                    if (oldUniRefEntryOfUniRefEntryPropertyNewUniRefEntryProperty != null && !oldUniRefEntryOfUniRefEntryPropertyNewUniRefEntryProperty.equals(uniRefEntry)) {
                        oldUniRefEntryOfUniRefEntryPropertyNewUniRefEntryProperty.getUniRefEntryProperty().remove(uniRefEntryPropertyNewUniRefEntryProperty);
                        em.merge(oldUniRefEntryOfUniRefEntryPropertyNewUniRefEntryProperty);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = uniRefEntry.getWid();
                if (findUniRefEntry(id) == null) {
                    throw new NonexistentEntityException("The uniRefEntry with id " + id + " no longer exists.");
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
            UniRefEntry uniRefEntry;
            try {
                uniRefEntry = em.getReference(UniRefEntry.class, id);
                uniRefEntry.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uniRefEntry with id " + id + " no longer exists.", enfe);
            }
            Set<UniRefEntryProperty> uniRefEntryProperty = uniRefEntry.getUniRefEntryProperty();
            for (UniRefEntryProperty uniRefEntryPropertyUniRefEntryProperty : uniRefEntryProperty) {
                uniRefEntryPropertyUniRefEntryProperty.setUniRefEntry(null);
                uniRefEntryPropertyUniRefEntryProperty = em.merge(uniRefEntryPropertyUniRefEntryProperty);
            }
            em.remove(uniRefEntry);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UniRefEntry> findUniRefEntryEntities() {
        return findUniRefEntryEntities(true, -1, -1);
    }

    public List<UniRefEntry> findUniRefEntryEntities(int maxResults, int firstResult) {
        return findUniRefEntryEntities(false, maxResults, firstResult);
    }

    private List<UniRefEntry> findUniRefEntryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UniRefEntry.class));
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

    public UniRefEntry findUniRefEntry(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UniRefEntry.class, id);
        } finally {
            em.close();
        }
    }

    public int getUniRefEntryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UniRefEntry> rt = cq.from(UniRefEntry.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
