package org.jbiowhpersistence.datasets.protgroup.cog.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGFuncClass;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGFuncClassGroup;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the COGFuncClassGroup Jpa Controller
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 19, 2013
 */
public class COGFuncClassGroupJpaController extends AbstractJpaController<COGFuncClassGroup> implements Serializable {

    public COGFuncClassGroupJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(COGFuncClassGroup COGFuncClassGroup) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + COGFuncClassGroup.getWid());
        if (COGFuncClassGroup.getCogFuncClass() == null) {
            COGFuncClassGroup.setCogFuncClass(new HashSet<COGFuncClass>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            COGFuncClassJpaController fCtrl = new COGFuncClassJpaController(emf);
            Set<COGFuncClass> attachedCogFuncClass = new HashSet<COGFuncClass>();
            for (COGFuncClass cogFuncClassCOGFuncClassToAttach : COGFuncClassGroup.getCogFuncClass()) {
                COGFuncClass cogFuncClassCOGFuncClass = em.find(cogFuncClassCOGFuncClassToAttach.getClass(), cogFuncClassCOGFuncClassToAttach.getWid());
                if (cogFuncClassCOGFuncClass != null) {
                    attachedCogFuncClass.add(cogFuncClassCOGFuncClass);
                } else {
                    cogFuncClassCOGFuncClassToAttach.setCogFuncClassGroup(null);
                    fCtrl.create(cogFuncClassCOGFuncClassToAttach);
                    attachedCogFuncClass.add(em.getReference(COGFuncClass.class, cogFuncClassCOGFuncClassToAttach.getWid()));
                }
            }
            COGFuncClassGroup.setCogFuncClass(attachedCogFuncClass);
            em.persist(COGFuncClassGroup);
            for (COGFuncClass cogFuncClassCOGFuncClass : COGFuncClassGroup.getCogFuncClass()) {
                COGFuncClassGroup oldCogFuncClassGroupOfCogFuncClassCOGFuncClass = cogFuncClassCOGFuncClass.getCogFuncClassGroup();
                cogFuncClassCOGFuncClass.setCogFuncClassGroup(COGFuncClassGroup);
                cogFuncClassCOGFuncClass = em.merge(cogFuncClassCOGFuncClass);
                if (oldCogFuncClassGroupOfCogFuncClassCOGFuncClass != null) {
                    oldCogFuncClassGroupOfCogFuncClassCOGFuncClass.getCogFuncClass().remove(cogFuncClassCOGFuncClass);
                    oldCogFuncClassGroupOfCogFuncClassCOGFuncClass = em.merge(oldCogFuncClassGroupOfCogFuncClassCOGFuncClass);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCOGFuncClassGroup(COGFuncClassGroup.getWid()) != null) {
                throw new PreexistingEntityException("COGFuncClassGroup " + COGFuncClassGroup + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(COGFuncClassGroup COGFuncClassGroup) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + COGFuncClassGroup.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            COGFuncClassGroup persistentCOGFuncClassGroup = em.find(COGFuncClassGroup.class, COGFuncClassGroup.getWid());
            Set<COGFuncClass> cogFuncClassOld = persistentCOGFuncClassGroup.getCogFuncClass();
            Set<COGFuncClass> cogFuncClassNew = COGFuncClassGroup.getCogFuncClass();
            Set<COGFuncClass> attachedCogFuncClassNew = new HashSet<COGFuncClass>();
            for (COGFuncClass cogFuncClassNewCOGFuncClassToAttach : cogFuncClassNew) {
                cogFuncClassNewCOGFuncClassToAttach = em.getReference(cogFuncClassNewCOGFuncClassToAttach.getClass(), cogFuncClassNewCOGFuncClassToAttach.getWid());
                attachedCogFuncClassNew.add(cogFuncClassNewCOGFuncClassToAttach);
            }
            cogFuncClassNew = attachedCogFuncClassNew;
            COGFuncClassGroup.setCogFuncClass(cogFuncClassNew);
            COGFuncClassGroup = em.merge(COGFuncClassGroup);
            for (COGFuncClass cogFuncClassOldCOGFuncClass : cogFuncClassOld) {
                if (!cogFuncClassNew.contains(cogFuncClassOldCOGFuncClass)) {
                    cogFuncClassOldCOGFuncClass.setCogFuncClassGroup(null);
                    cogFuncClassOldCOGFuncClass = em.merge(cogFuncClassOldCOGFuncClass);
                }
            }
            for (COGFuncClass cogFuncClassNewCOGFuncClass : cogFuncClassNew) {
                if (!cogFuncClassOld.contains(cogFuncClassNewCOGFuncClass)) {
                    COGFuncClassGroup oldCogFuncClassGroupOfCogFuncClassNewCOGFuncClass = cogFuncClassNewCOGFuncClass.getCogFuncClassGroup();
                    cogFuncClassNewCOGFuncClass.setCogFuncClassGroup(COGFuncClassGroup);
                    cogFuncClassNewCOGFuncClass = em.merge(cogFuncClassNewCOGFuncClass);
                    if (oldCogFuncClassGroupOfCogFuncClassNewCOGFuncClass != null && !oldCogFuncClassGroupOfCogFuncClassNewCOGFuncClass.equals(COGFuncClassGroup)) {
                        oldCogFuncClassGroupOfCogFuncClassNewCOGFuncClass.getCogFuncClass().remove(cogFuncClassNewCOGFuncClass);
                        oldCogFuncClassGroupOfCogFuncClassNewCOGFuncClass = em.merge(oldCogFuncClassGroupOfCogFuncClassNewCOGFuncClass);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = COGFuncClassGroup.getWid();
                if (findCOGFuncClassGroup(id) == null) {
                    throw new NonexistentEntityException("The cOGFuncClassGroup with id " + id + " no longer exists.");
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
            COGFuncClassGroup COGFuncClassGroup;
            try {
                COGFuncClassGroup = em.getReference(COGFuncClassGroup.class, id);
                COGFuncClassGroup.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The COGFuncClassGroup with id " + id + " no longer exists.", enfe);
            }
            Set<COGFuncClass> cogFuncClass = COGFuncClassGroup.getCogFuncClass();
            for (COGFuncClass cogFuncClassCOGFuncClass : cogFuncClass) {
                cogFuncClassCOGFuncClass.setCogFuncClassGroup(null);
                cogFuncClassCOGFuncClass = em.merge(cogFuncClassCOGFuncClass);
            }
            em.remove(COGFuncClassGroup);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<COGFuncClassGroup> findCOGFuncClassGroupEntities() {
        return findCOGFuncClassGroupEntities(true, -1, -1);
    }

    public List<COGFuncClassGroup> findCOGFuncClassGroupEntities(int maxResults, int firstResult) {
        return findCOGFuncClassGroupEntities(false, maxResults, firstResult);
    }

    private List<COGFuncClassGroup> findCOGFuncClassGroupEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(COGFuncClassGroup.class));
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

    public COGFuncClassGroup findCOGFuncClassGroup(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(COGFuncClassGroup.class, id);
        } finally {
            em.close();
        }
    }

    public int getCOGFuncClassGroupCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<COGFuncClassGroup> rt = cq.from(COGFuncClassGroup.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
