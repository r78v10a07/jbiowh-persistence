package org.jbiowhpersistence.datasets.ppi.controller;

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
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySource;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAlias;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAttribute;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherBibRef;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRef;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the MIFEntrySource Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 14, 2012
 */
public class MIFEntrySourceJpaController extends AbstractMIFJpaController<MIFEntrySource> implements Serializable {

    public MIFEntrySourceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MIFEntrySource mifEntrySource) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + mifEntrySource.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mifEntrySource.setmIFEntrySetEntry(createMIFEntrySetEntry(emf, em, mifEntrySource.getmIFEntrySetEntry()));
            mifEntrySource.setmIFOtherAlias(createMIFOtherAlias(emf, em, mifEntrySource.getmIFOtherAlias()));
            mifEntrySource.setmIFOtherAttribute(createMIFOtherAttribute(emf, em, mifEntrySource.getmIFOtherAttribute()));
            mifEntrySource.setmIFOtherBibRef(createMIFOtherBibRef(emf, em, mifEntrySource.getmIFOtherBibRef()));
            mifEntrySource.setmIFOtherXRef(createMIFOtherXRef(emf, em, mifEntrySource.getmIFOtherXRef()));
            em.persist(mifEntrySource);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMIFEntrySource(mifEntrySource.getWid()) != null) {
                throw new PreexistingEntityException("MIFEntrySource " + mifEntrySource + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MIFEntrySource mifEntrySource) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + mifEntrySource.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MIFEntrySource persistentMIFEntrySource = em.find(MIFEntrySource.class, mifEntrySource.getWid());
            MIFEntrySetEntry mIFEntrySetEntryOld = persistentMIFEntrySource.getmIFEntrySetEntry();
            MIFEntrySetEntry mIFEntrySetEntryNew = mifEntrySource.getmIFEntrySetEntry();
            Set<MIFOtherAlias> mIFOtherAliasOld = persistentMIFEntrySource.getmIFOtherAlias();
            Set<MIFOtherAlias> mIFOtherAliasNew = mifEntrySource.getmIFOtherAlias();
            Set<MIFOtherAttribute> mIFOtherAttributeOld = persistentMIFEntrySource.getmIFOtherAttribute();
            Set<MIFOtherAttribute> mIFOtherAttributeNew = mifEntrySource.getmIFOtherAttribute();
            Set<MIFOtherBibRef> mIFOtherBibRefOld = persistentMIFEntrySource.getmIFOtherBibRef();
            Set<MIFOtherBibRef> mIFOtherBibRefNew = mifEntrySource.getmIFOtherBibRef();
            Set<MIFOtherXRef> mIFOtherXRefOld = persistentMIFEntrySource.getmIFOtherXRef();
            Set<MIFOtherXRef> mIFOtherXRefNew = mifEntrySource.getmIFOtherXRef();
            if (mIFEntrySetEntryNew != null) {
                mIFEntrySetEntryNew = em.getReference(mIFEntrySetEntryNew.getClass(), mIFEntrySetEntryNew.getWid());
                mifEntrySource.setmIFEntrySetEntry(mIFEntrySetEntryNew);
            }
            Set<MIFOtherAlias> attachedmIFOtherAliasNew = new HashSet();
            for (MIFOtherAlias mIFOtherAliasNewMIFOtherAliasToAttach : mIFOtherAliasNew) {
                mIFOtherAliasNewMIFOtherAliasToAttach = em.getReference(mIFOtherAliasNewMIFOtherAliasToAttach.getClass(), mIFOtherAliasNewMIFOtherAliasToAttach.getWid());
                attachedmIFOtherAliasNew.add(mIFOtherAliasNewMIFOtherAliasToAttach);
            }
            mIFOtherAliasNew = attachedmIFOtherAliasNew;
            mifEntrySource.setmIFOtherAlias(mIFOtherAliasNew);
            Set<MIFOtherAttribute> attachedmIFOtherAttributeNew = new HashSet();
            for (MIFOtherAttribute mIFOtherAttributeNewMIFOtherAttributeToAttach : mIFOtherAttributeNew) {
                mIFOtherAttributeNewMIFOtherAttributeToAttach = em.getReference(mIFOtherAttributeNewMIFOtherAttributeToAttach.getClass(), mIFOtherAttributeNewMIFOtherAttributeToAttach.getWid());
                attachedmIFOtherAttributeNew.add(mIFOtherAttributeNewMIFOtherAttributeToAttach);
            }
            mIFOtherAttributeNew = attachedmIFOtherAttributeNew;
            mifEntrySource.setmIFOtherAttribute(mIFOtherAttributeNew);
            Set<MIFOtherBibRef> attachedmIFOtherBibRefNew = new HashSet();
            for (MIFOtherBibRef mIFOtherBibRefNewMIFOtherBibRefToAttach : mIFOtherBibRefNew) {
                mIFOtherBibRefNewMIFOtherBibRefToAttach = em.getReference(mIFOtherBibRefNewMIFOtherBibRefToAttach.getClass(), mIFOtherBibRefNewMIFOtherBibRefToAttach.getWid());
                attachedmIFOtherBibRefNew.add(mIFOtherBibRefNewMIFOtherBibRefToAttach);
            }
            mIFOtherBibRefNew = attachedmIFOtherBibRefNew;
            mifEntrySource.setmIFOtherBibRef(mIFOtherBibRefNew);
            Set<MIFOtherXRef> attachedmIFOtherXRefNew = new HashSet();
            for (MIFOtherXRef mIFOtherXRefNewMIFOtherXRefToAttach : mIFOtherXRefNew) {
                mIFOtherXRefNewMIFOtherXRefToAttach = em.getReference(mIFOtherXRefNewMIFOtherXRefToAttach.getClass(), mIFOtherXRefNewMIFOtherXRefToAttach.getWid());
                attachedmIFOtherXRefNew.add(mIFOtherXRefNewMIFOtherXRefToAttach);
            }
            mIFOtherXRefNew = attachedmIFOtherXRefNew;
            mifEntrySource.setmIFOtherXRef(mIFOtherXRefNew);
            mifEntrySource = em.merge(mifEntrySource);
            if (mIFEntrySetEntryOld != null && !mIFEntrySetEntryOld.equals(mIFEntrySetEntryNew)) {
                mIFEntrySetEntryOld.getMifEntrySource().remove(mifEntrySource);
                mIFEntrySetEntryOld = em.merge(mIFEntrySetEntryOld);
            }
            if (mIFEntrySetEntryNew != null && !mIFEntrySetEntryNew.equals(mIFEntrySetEntryOld)) {
                mIFEntrySetEntryNew.getMifEntrySource().add(mifEntrySource);
                em.merge(mIFEntrySetEntryNew);
            }
            for (MIFOtherAlias mIFOtherAliasOldMIFOtherAlias : mIFOtherAliasOld) {
                if (!mIFOtherAliasNew.contains(mIFOtherAliasOldMIFOtherAlias)) {
                    mIFOtherAliasOldMIFOtherAlias.setMifEntrySource(null);
                    mIFOtherAliasOldMIFOtherAlias = em.merge(mIFOtherAliasOldMIFOtherAlias);
                }
            }
            for (MIFOtherAlias mIFOtherAliasNewMIFOtherAlias : mIFOtherAliasNew) {
                if (!mIFOtherAliasOld.contains(mIFOtherAliasNewMIFOtherAlias)) {
                    MIFEntrySource oldMifEntrySourceOfMIFOtherAliasNewMIFOtherAlias = mIFOtherAliasNewMIFOtherAlias.getMifEntrySource();
                    mIFOtherAliasNewMIFOtherAlias.setMifEntrySource(mifEntrySource);
                    mIFOtherAliasNewMIFOtherAlias = em.merge(mIFOtherAliasNewMIFOtherAlias);
                    if (oldMifEntrySourceOfMIFOtherAliasNewMIFOtherAlias != null && !oldMifEntrySourceOfMIFOtherAliasNewMIFOtherAlias.equals(mifEntrySource)) {
                        oldMifEntrySourceOfMIFOtherAliasNewMIFOtherAlias.getmIFOtherAlias().remove(mIFOtherAliasNewMIFOtherAlias);
                        em.merge(oldMifEntrySourceOfMIFOtherAliasNewMIFOtherAlias);
                    }
                }
            }
            for (MIFOtherAttribute mIFOtherAttributeOldMIFOtherAttribute : mIFOtherAttributeOld) {
                if (!mIFOtherAttributeNew.contains(mIFOtherAttributeOldMIFOtherAttribute)) {
                    mIFOtherAttributeOldMIFOtherAttribute.setMifEntrySource(null);
                    mIFOtherAttributeOldMIFOtherAttribute = em.merge(mIFOtherAttributeOldMIFOtherAttribute);
                }
            }
            for (MIFOtherAttribute mIFOtherAttributeNewMIFOtherAttribute : mIFOtherAttributeNew) {
                if (!mIFOtherAttributeOld.contains(mIFOtherAttributeNewMIFOtherAttribute)) {
                    MIFEntrySource oldMifEntrySourceOfMIFOtherAttributeNewMIFOtherAttribute = mIFOtherAttributeNewMIFOtherAttribute.getMifEntrySource();
                    mIFOtherAttributeNewMIFOtherAttribute.setMifEntrySource(mifEntrySource);
                    mIFOtherAttributeNewMIFOtherAttribute = em.merge(mIFOtherAttributeNewMIFOtherAttribute);
                    if (oldMifEntrySourceOfMIFOtherAttributeNewMIFOtherAttribute != null && !oldMifEntrySourceOfMIFOtherAttributeNewMIFOtherAttribute.equals(mifEntrySource)) {
                        oldMifEntrySourceOfMIFOtherAttributeNewMIFOtherAttribute.getmIFOtherAttribute().remove(mIFOtherAttributeNewMIFOtherAttribute);
                        em.merge(oldMifEntrySourceOfMIFOtherAttributeNewMIFOtherAttribute);
                    }
                }
            }
            for (MIFOtherBibRef mIFOtherBibRefOldMIFOtherBibRef : mIFOtherBibRefOld) {
                if (!mIFOtherBibRefNew.contains(mIFOtherBibRefOldMIFOtherBibRef)) {
                    mIFOtherBibRefOldMIFOtherBibRef.setMifEntrySource(null);
                    mIFOtherBibRefOldMIFOtherBibRef = em.merge(mIFOtherBibRefOldMIFOtherBibRef);
                }
            }
            for (MIFOtherBibRef mIFOtherBibRefNewMIFOtherBibRef : mIFOtherBibRefNew) {
                if (!mIFOtherBibRefOld.contains(mIFOtherBibRefNewMIFOtherBibRef)) {
                    MIFEntrySource oldMifEntrySourceOfMIFOtherBibRefNewMIFOtherBibRef = mIFOtherBibRefNewMIFOtherBibRef.getMifEntrySource();
                    mIFOtherBibRefNewMIFOtherBibRef.setMifEntrySource(mifEntrySource);
                    mIFOtherBibRefNewMIFOtherBibRef = em.merge(mIFOtherBibRefNewMIFOtherBibRef);
                    if (oldMifEntrySourceOfMIFOtherBibRefNewMIFOtherBibRef != null && !oldMifEntrySourceOfMIFOtherBibRefNewMIFOtherBibRef.equals(mifEntrySource)) {
                        oldMifEntrySourceOfMIFOtherBibRefNewMIFOtherBibRef.getmIFOtherBibRef().remove(mIFOtherBibRefNewMIFOtherBibRef);
                        em.merge(oldMifEntrySourceOfMIFOtherBibRefNewMIFOtherBibRef);
                    }
                }
            }
            for (MIFOtherXRef mIFOtherXRefOldMIFOtherXRef : mIFOtherXRefOld) {
                if (!mIFOtherXRefNew.contains(mIFOtherXRefOldMIFOtherXRef)) {
                    mIFOtherXRefOldMIFOtherXRef.setMifEntrySource(null);
                    mIFOtherXRefOldMIFOtherXRef = em.merge(mIFOtherXRefOldMIFOtherXRef);
                }
            }
            for (MIFOtherXRef mIFOtherXRefNewMIFOtherXRef : mIFOtherXRefNew) {
                if (!mIFOtherXRefOld.contains(mIFOtherXRefNewMIFOtherXRef)) {
                    MIFEntrySource oldMifEntrySourceOfMIFOtherXRefNewMIFOtherXRef = mIFOtherXRefNewMIFOtherXRef.getMifEntrySource();
                    mIFOtherXRefNewMIFOtherXRef.setMifEntrySource(mifEntrySource);
                    mIFOtherXRefNewMIFOtherXRef = em.merge(mIFOtherXRefNewMIFOtherXRef);
                    if (oldMifEntrySourceOfMIFOtherXRefNewMIFOtherXRef != null && !oldMifEntrySourceOfMIFOtherXRefNewMIFOtherXRef.equals(mifEntrySource)) {
                        oldMifEntrySourceOfMIFOtherXRefNewMIFOtherXRef.getmIFOtherXRef().remove(mIFOtherXRefNewMIFOtherXRef);
                        em.merge(oldMifEntrySourceOfMIFOtherXRefNewMIFOtherXRef);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mifEntrySource.getWid();
                if (findMIFEntrySource(id) == null) {
                    throw new NonexistentEntityException("The mIFEntrySource with id " + id + " no longer exists.");
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
            MIFEntrySource MIFEntrySource;
            try {
                MIFEntrySource = em.getReference(MIFEntrySource.class, id);
                MIFEntrySource.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MIFEntrySource with id " + id + " no longer exists.", enfe);
            }
            MIFEntrySetEntry mIFEntrySetEntry = MIFEntrySource.getmIFEntrySetEntry();
            if (mIFEntrySetEntry != null) {
                mIFEntrySetEntry.getMifEntrySource().remove(MIFEntrySource);
                em.merge(mIFEntrySetEntry);
            }
            Set<MIFOtherAlias> mIFOtherAlias = MIFEntrySource.getmIFOtherAlias();
            for (MIFOtherAlias mIFOtherAliasMIFOtherAlias : mIFOtherAlias) {
                mIFOtherAliasMIFOtherAlias.setMifEntrySource(null);
                mIFOtherAliasMIFOtherAlias = em.merge(mIFOtherAliasMIFOtherAlias);
            }
            Set<MIFOtherAttribute> mIFOtherAttribute = MIFEntrySource.getmIFOtherAttribute();
            for (MIFOtherAttribute mIFOtherAttributeMIFOtherAttribute : mIFOtherAttribute) {
                mIFOtherAttributeMIFOtherAttribute.setMifEntrySource(null);
                mIFOtherAttributeMIFOtherAttribute = em.merge(mIFOtherAttributeMIFOtherAttribute);
            }
            Set<MIFOtherBibRef> mIFOtherBibRef = MIFEntrySource.getmIFOtherBibRef();
            for (MIFOtherBibRef mIFOtherBibRefMIFOtherBibRef : mIFOtherBibRef) {
                mIFOtherBibRefMIFOtherBibRef.setMifEntrySource(null);
                mIFOtherBibRefMIFOtherBibRef = em.merge(mIFOtherBibRefMIFOtherBibRef);
            }
            Set<MIFOtherXRef> mIFOtherXRef = MIFEntrySource.getmIFOtherXRef();
            for (MIFOtherXRef mIFOtherXRefMIFOtherXRef : mIFOtherXRef) {
                mIFOtherXRefMIFOtherXRef.setMifEntrySource(null);
                mIFOtherXRefMIFOtherXRef = em.merge(mIFOtherXRefMIFOtherXRef);
            }
            em.remove(MIFEntrySource);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MIFEntrySource> findMIFEntrySourceEntities() {
        return findMIFEntrySourceEntities(true, -1, -1);
    }

    public List<MIFEntrySource> findMIFEntrySourceEntities(int maxResults, int firstResult) {
        return findMIFEntrySourceEntities(false, maxResults, firstResult);
    }

    private List<MIFEntrySource> findMIFEntrySourceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MIFEntrySource.class));
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

    public MIFEntrySource findMIFEntrySource(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MIFEntrySource.class, id);
        } finally {
            em.close();
        }
    }

    public int getMIFEntrySourceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MIFEntrySource> rt = cq.from(MIFEntrySource.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
