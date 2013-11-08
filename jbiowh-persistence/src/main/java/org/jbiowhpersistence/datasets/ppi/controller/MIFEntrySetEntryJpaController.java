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
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryExperiment;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteractor;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySet;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the MIFEntrySetEntry Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 14, 2012
 */
public class MIFEntrySetEntryJpaController extends AbstractMIFJpaController<MIFEntrySetEntry> implements Serializable {

    public MIFEntrySetEntryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MIFEntrySetEntry mifEntrySetEntry) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + mifEntrySetEntry.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MIFEntrySet mIFEntrySet = mifEntrySetEntry.getmIFEntrySet();
            if (mIFEntrySet != null) {
                mIFEntrySet = em.find(mIFEntrySet.getClass(), mIFEntrySet.getWid());
                if (mIFEntrySet != null) {
                    mifEntrySetEntry.setmIFEntrySet(mIFEntrySet);
                } else {
                    MIFEntrySetJpaController controller = new MIFEntrySetJpaController(emf);
                    mIFEntrySet = mifEntrySetEntry.getmIFEntrySet();
                    mIFEntrySet.setmIFEntrySetEntry(null);
                    controller.create(mIFEntrySet);
                    mifEntrySetEntry.setmIFEntrySet(em.getReference(MIFEntrySet.class, mIFEntrySet.getWid()));
                }
            }

            mifEntrySetEntry.setMifEntrySource(createMIFEntrySource(emf, em, mifEntrySetEntry.getMifEntrySource()));
            mifEntrySetEntry.setMifEntryExperiment(createMIFEntryExperiment(emf, em, mifEntrySetEntry.getMifEntryExperiment()));
            mifEntrySetEntry.setMifEntryInteractor(createMIFEntryInteractor(emf, em, mifEntrySetEntry.getMifEntryInteractor()));
            mifEntrySetEntry.setMifEntryInteraction(createMIFEntryInteraction(emf, em, mifEntrySetEntry.getMifEntryInteraction()));
            em.persist(mifEntrySetEntry);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMIFEntrySetEntry(mifEntrySetEntry.getWid()) != null) {
                throw new PreexistingEntityException("MIFEntrySetEntry " + mifEntrySetEntry + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MIFEntrySetEntry mifEntrySetEntry) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + mifEntrySetEntry.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MIFEntrySetEntry persistentMIFEntrySetEntry = em.find(MIFEntrySetEntry.class, mifEntrySetEntry.getWid());
            MIFEntrySet mIFEntrySetOld = persistentMIFEntrySetEntry.getmIFEntrySet();
            MIFEntrySet mIFEntrySetNew = mifEntrySetEntry.getmIFEntrySet();
            Set<MIFEntryExperiment> mifEntryExperimentOld = persistentMIFEntrySetEntry.getMifEntryExperiment();
            Set<MIFEntryExperiment> mifEntryExperimentNew = mifEntrySetEntry.getMifEntryExperiment();
            Set<MIFEntryInteractor> mifEntryInteractorOld = persistentMIFEntrySetEntry.getMifEntryInteractor();
            Set<MIFEntryInteractor> mifEntryInteractorNew = mifEntrySetEntry.getMifEntryInteractor();
            if (mIFEntrySetNew != null) {
                mIFEntrySetNew = em.getReference(mIFEntrySetNew.getClass(), mIFEntrySetNew.getWid());
                mifEntrySetEntry.setmIFEntrySet(mIFEntrySetNew);
            }
            Set<MIFEntryExperiment> attachedMifEntryExperimentNew = new HashSet();
            for (MIFEntryExperiment mifEntryExperimentNewMIFEntryExperimentToAttach : mifEntryExperimentNew) {
                mifEntryExperimentNewMIFEntryExperimentToAttach = em.getReference(mifEntryExperimentNewMIFEntryExperimentToAttach.getClass(), mifEntryExperimentNewMIFEntryExperimentToAttach.getWid());
                attachedMifEntryExperimentNew.add(mifEntryExperimentNewMIFEntryExperimentToAttach);
            }
            mifEntryExperimentNew = attachedMifEntryExperimentNew;
            mifEntrySetEntry.setMifEntryExperiment(mifEntryExperimentNew);
            Set<MIFEntryInteractor> attachedMifEntryInteractorNew = new HashSet();
            for (MIFEntryInteractor mifEntryInteractorNewMIFEntryInteractorToAttach : mifEntryInteractorNew) {
                mifEntryInteractorNewMIFEntryInteractorToAttach = em.getReference(mifEntryInteractorNewMIFEntryInteractorToAttach.getClass(), mifEntryInteractorNewMIFEntryInteractorToAttach.getWid());
                attachedMifEntryInteractorNew.add(mifEntryInteractorNewMIFEntryInteractorToAttach);
            }
            mifEntryInteractorNew = attachedMifEntryInteractorNew;
            mifEntrySetEntry.setMifEntryInteractor(mifEntryInteractorNew);
            mifEntrySetEntry = em.merge(mifEntrySetEntry);
            if (mIFEntrySetOld != null && !mIFEntrySetOld.equals(mIFEntrySetNew)) {
                mIFEntrySetOld.getmIFEntrySetEntry().remove(mifEntrySetEntry);
                mIFEntrySetOld = em.merge(mIFEntrySetOld);
            }
            if (mIFEntrySetNew != null && !mIFEntrySetNew.equals(mIFEntrySetOld)) {
                mIFEntrySetNew.getmIFEntrySetEntry().add(mifEntrySetEntry);
                em.merge(mIFEntrySetNew);
            }
            for (MIFEntryExperiment mifEntryExperimentOldMIFEntryExperiment : mifEntryExperimentOld) {
                if (!mifEntryExperimentNew.contains(mifEntryExperimentOldMIFEntryExperiment)) {
                    mifEntryExperimentOldMIFEntryExperiment.setMifEntrySetEntry(null);
                    mifEntryExperimentOldMIFEntryExperiment = em.merge(mifEntryExperimentOldMIFEntryExperiment);
                }
            }
            for (MIFEntryExperiment mifEntryExperimentNewMIFEntryExperiment : mifEntryExperimentNew) {
                if (!mifEntryExperimentOld.contains(mifEntryExperimentNewMIFEntryExperiment)) {
                    MIFEntrySetEntry oldMifEntrySetEntryOfMifEntryExperimentNewMIFEntryExperiment = mifEntryExperimentNewMIFEntryExperiment.getMifEntrySetEntry();
                    mifEntryExperimentNewMIFEntryExperiment.setMifEntrySetEntry(mifEntrySetEntry);
                    mifEntryExperimentNewMIFEntryExperiment = em.merge(mifEntryExperimentNewMIFEntryExperiment);
                    if (oldMifEntrySetEntryOfMifEntryExperimentNewMIFEntryExperiment != null && !oldMifEntrySetEntryOfMifEntryExperimentNewMIFEntryExperiment.equals(mifEntrySetEntry)) {
                        oldMifEntrySetEntryOfMifEntryExperimentNewMIFEntryExperiment.getMifEntryExperiment().remove(mifEntryExperimentNewMIFEntryExperiment);
                        em.merge(oldMifEntrySetEntryOfMifEntryExperimentNewMIFEntryExperiment);
                    }
                }
            }
            for (MIFEntryInteractor mifEntryInteractorOldMIFEntryInteractor : mifEntryInteractorOld) {
                if (!mifEntryInteractorNew.contains(mifEntryInteractorOldMIFEntryInteractor)) {
                    mifEntryInteractorOldMIFEntryInteractor.setMifEntrySetEntry(null);
                    mifEntryInteractorOldMIFEntryInteractor = em.merge(mifEntryInteractorOldMIFEntryInteractor);
                }
            }
            for (MIFEntryInteractor mifEntryInteractorNewMIFEntryInteractor : mifEntryInteractorNew) {
                if (!mifEntryInteractorOld.contains(mifEntryInteractorNewMIFEntryInteractor)) {
                    MIFEntrySetEntry oldMifEntrySetEntryOfMifEntryInteractorNewMIFEntryInteractor = mifEntryInteractorNewMIFEntryInteractor.getMifEntrySetEntry();
                    mifEntryInteractorNewMIFEntryInteractor.setMifEntrySetEntry(mifEntrySetEntry);
                    mifEntryInteractorNewMIFEntryInteractor = em.merge(mifEntryInteractorNewMIFEntryInteractor);
                    if (oldMifEntrySetEntryOfMifEntryInteractorNewMIFEntryInteractor != null && !oldMifEntrySetEntryOfMifEntryInteractorNewMIFEntryInteractor.equals(mifEntrySetEntry)) {
                        oldMifEntrySetEntryOfMifEntryInteractorNewMIFEntryInteractor.getMifEntryInteractor().remove(mifEntryInteractorNewMIFEntryInteractor);
                        em.merge(oldMifEntrySetEntryOfMifEntryInteractorNewMIFEntryInteractor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mifEntrySetEntry.getWid();
                if (findMIFEntrySetEntry(id) == null) {
                    throw new NonexistentEntityException("The mIFEntrySetEntry with id " + id + " no longer exists.");
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
            MIFEntrySetEntry MIFEntrySetEntry;
            try {
                MIFEntrySetEntry = em.getReference(MIFEntrySetEntry.class, id);
                MIFEntrySetEntry.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MIFEntrySetEntry with id " + id + " no longer exists.", enfe);
            }
            MIFEntrySet mIFEntrySet = MIFEntrySetEntry.getmIFEntrySet();
            if (mIFEntrySet != null) {
                mIFEntrySet.getmIFEntrySetEntry().remove(MIFEntrySetEntry);
                em.merge(mIFEntrySet);
            }
            Set<MIFEntryExperiment> mifEntryExperiment = MIFEntrySetEntry.getMifEntryExperiment();
            for (MIFEntryExperiment mifEntryExperimentMIFEntryExperiment : mifEntryExperiment) {
                mifEntryExperimentMIFEntryExperiment.setMifEntrySetEntry(null);
                mifEntryExperimentMIFEntryExperiment = em.merge(mifEntryExperimentMIFEntryExperiment);
            }
            Set<MIFEntryInteractor> mifEntryInteractor = MIFEntrySetEntry.getMifEntryInteractor();
            for (MIFEntryInteractor mifEntryInteractorMIFEntryInteractor : mifEntryInteractor) {
                mifEntryInteractorMIFEntryInteractor.setMifEntrySetEntry(null);
                mifEntryInteractorMIFEntryInteractor = em.merge(mifEntryInteractorMIFEntryInteractor);
            }
            em.remove(MIFEntrySetEntry);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MIFEntrySetEntry> findMIFEntrySetEntryEntities() {
        return findMIFEntrySetEntryEntities(true, -1, -1);
    }

    public List<MIFEntrySetEntry> findMIFEntrySetEntryEntities(int maxResults, int firstResult) {
        return findMIFEntrySetEntryEntities(false, maxResults, firstResult);
    }

    private List<MIFEntrySetEntry> findMIFEntrySetEntryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MIFEntrySetEntry.class));
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

    public MIFEntrySetEntry findMIFEntrySetEntry(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MIFEntrySetEntry.class, id);
        } finally {
            em.close();
        }
    }

    public int getMIFEntrySetEntryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MIFEntrySetEntry> rt = cq.from(MIFEntrySetEntry.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
