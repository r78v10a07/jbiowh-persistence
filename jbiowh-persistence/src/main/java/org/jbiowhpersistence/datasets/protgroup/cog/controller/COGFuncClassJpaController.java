package org.jbiowhpersistence.datasets.protgroup.cog.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGFuncClassGroup;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGFuncClass;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the COGFuncClass Jpa Controller
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 20, 2013
 */
public class COGFuncClassJpaController extends AbstractJpaController<COGFuncClass> implements Serializable {

    public COGFuncClassJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(COGFuncClass COGFuncClass) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + COGFuncClass.getWid());
        if (COGFuncClass.getCogOrthologousGroup() == null) {
            COGFuncClass.setCogOrthologousGroup(new HashSet<COGOrthologousGroup>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            COGFuncClassGroupJpaController cCtrl = new COGFuncClassGroupJpaController(emf);
            COGFuncClassGroup cogFuncClassGroup = COGFuncClass.getCogFuncClassGroup();
            if (cogFuncClassGroup != null) {
                cogFuncClassGroup = em.find(cogFuncClassGroup.getClass(), cogFuncClassGroup.getWid());
                if (cogFuncClassGroup != null) {
                    COGFuncClass.setCogFuncClassGroup(cogFuncClassGroup);
                } else {
                    cogFuncClassGroup = COGFuncClass.getCogFuncClassGroup();
                    cogFuncClassGroup.setCogFuncClass(null);
                    cCtrl.create(cogFuncClassGroup);
                    COGFuncClass.setCogFuncClassGroup(em.getReference(cogFuncClassGroup.getClass(), cogFuncClassGroup.getWid()));
                }
            }
            COGFuncClass.setCogOrthologousGroup(createCOGOrthologousGroup(emf, em, COGFuncClass.getCogOrthologousGroup()));
            em.persist(COGFuncClass);
            if (cogFuncClassGroup != null) {
                cogFuncClassGroup.getCogFuncClass().add(COGFuncClass);
                cogFuncClassGroup = em.merge(cogFuncClassGroup);
            }
            for (COGOrthologousGroup cogOrthologousGroupCOGOrthologousGroup : COGFuncClass.getCogOrthologousGroup()) {
                cogOrthologousGroupCOGOrthologousGroup.getCogFuncClass().add(COGFuncClass);
                cogOrthologousGroupCOGOrthologousGroup = em.merge(cogOrthologousGroupCOGOrthologousGroup);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCOGFuncClass(COGFuncClass.getWid()) != null) {
                throw new PreexistingEntityException("COGFuncClass " + COGFuncClass + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(COGFuncClass COGFuncClass) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + COGFuncClass.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            COGFuncClass persistentCOGFuncClass = em.find(COGFuncClass.class, COGFuncClass.getWid());
            COGFuncClassGroup cogFuncClassGroupOld = persistentCOGFuncClass.getCogFuncClassGroup();
            COGFuncClassGroup cogFuncClassGroupNew = COGFuncClass.getCogFuncClassGroup();
            Set<COGOrthologousGroup> cogOrthologousGroupOld = persistentCOGFuncClass.getCogOrthologousGroup();
            Set<COGOrthologousGroup> cogOrthologousGroupNew = COGFuncClass.getCogOrthologousGroup();
            if (cogFuncClassGroupNew != null) {
                cogFuncClassGroupNew = em.getReference(cogFuncClassGroupNew.getClass(), cogFuncClassGroupNew.getWid());
                COGFuncClass.setCogFuncClassGroup(cogFuncClassGroupNew);
            }
            Set<COGOrthologousGroup> attachedCogOrthologousGroupNew = new HashSet<COGOrthologousGroup>();
            for (COGOrthologousGroup cogOrthologousGroupNewCOGOrthologousGroupToAttach : cogOrthologousGroupNew) {
                cogOrthologousGroupNewCOGOrthologousGroupToAttach = em.getReference(cogOrthologousGroupNewCOGOrthologousGroupToAttach.getClass(), cogOrthologousGroupNewCOGOrthologousGroupToAttach.getWid());
                attachedCogOrthologousGroupNew.add(cogOrthologousGroupNewCOGOrthologousGroupToAttach);
            }
            cogOrthologousGroupNew = attachedCogOrthologousGroupNew;
            COGFuncClass.setCogOrthologousGroup(cogOrthologousGroupNew);
            COGFuncClass = em.merge(COGFuncClass);
            if (cogFuncClassGroupOld != null && !cogFuncClassGroupOld.equals(cogFuncClassGroupNew)) {
                cogFuncClassGroupOld.getCogFuncClass().remove(COGFuncClass);
                cogFuncClassGroupOld = em.merge(cogFuncClassGroupOld);
            }
            if (cogFuncClassGroupNew != null && !cogFuncClassGroupNew.equals(cogFuncClassGroupOld)) {
                cogFuncClassGroupNew.getCogFuncClass().add(COGFuncClass);
                cogFuncClassGroupNew = em.merge(cogFuncClassGroupNew);
            }
            for (COGOrthologousGroup cogOrthologousGroupOldCOGOrthologousGroup : cogOrthologousGroupOld) {
                if (!cogOrthologousGroupNew.contains(cogOrthologousGroupOldCOGOrthologousGroup)) {
                    cogOrthologousGroupOldCOGOrthologousGroup.getCogFuncClass().remove(COGFuncClass);
                    cogOrthologousGroupOldCOGOrthologousGroup = em.merge(cogOrthologousGroupOldCOGOrthologousGroup);
                }
            }
            for (COGOrthologousGroup cogOrthologousGroupNewCOGOrthologousGroup : cogOrthologousGroupNew) {
                if (!cogOrthologousGroupOld.contains(cogOrthologousGroupNewCOGOrthologousGroup)) {
                    cogOrthologousGroupNewCOGOrthologousGroup.getCogFuncClass().add(COGFuncClass);
                    cogOrthologousGroupNewCOGOrthologousGroup = em.merge(cogOrthologousGroupNewCOGOrthologousGroup);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = COGFuncClass.getWid();
                if (findCOGFuncClass(id) == null) {
                    throw new NonexistentEntityException("The cOGFuncClass with id " + id + " no longer exists.");
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
            COGFuncClass COGFuncClass;
            try {
                COGFuncClass = em.getReference(COGFuncClass.class, id);
                COGFuncClass.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The COGFuncClass with id " + id + " no longer exists.", enfe);
            }
            COGFuncClassGroup cogFuncClassGroup = COGFuncClass.getCogFuncClassGroup();
            if (cogFuncClassGroup != null) {
                cogFuncClassGroup.getCogFuncClass().remove(COGFuncClass);
                cogFuncClassGroup = em.merge(cogFuncClassGroup);
            }
            Set<COGOrthologousGroup> cogOrthologousGroup = COGFuncClass.getCogOrthologousGroup();
            for (COGOrthologousGroup cogOrthologousGroupCOGOrthologousGroup : cogOrthologousGroup) {
                cogOrthologousGroupCOGOrthologousGroup.getCogFuncClass().remove(COGFuncClass);
                cogOrthologousGroupCOGOrthologousGroup = em.merge(cogOrthologousGroupCOGOrthologousGroup);
            }
            em.remove(COGFuncClass);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<COGFuncClass> findCOGFuncClassEntities() {
        return findCOGFuncClassEntities(true, -1, -1);
    }

    public List<COGFuncClass> findCOGFuncClassEntities(int maxResults, int firstResult) {
        return findCOGFuncClassEntities(false, maxResults, firstResult);
    }

    private List<COGFuncClass> findCOGFuncClassEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(COGFuncClass.class));
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

    public COGFuncClass findCOGFuncClass(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(COGFuncClass.class, id);
        } finally {
            em.close();
        }
    }

    public int getCOGFuncClassCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<COGFuncClass> rt = cq.from(COGFuncClass.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
