package org.jbiowhpersistence.datasets.domain.pfam.controller;

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
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamBReg;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamBbioWH;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;

/**
 * This Class is the PfamBbioWH Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Nov 19, 2012
 */
public class PfamBbioWHJpaController extends AbstractPFamJpaController<PfamBbioWH> implements Serializable {

    public PfamBbioWHJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PfamBbioWH pfamB) {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + pfamB.getWid());
        if (pfamB.getPfamBRegs() == null) {
            pfamB.setPfamBRegs(new HashSet<PfamBReg>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!pfamB.getPfamBRegs().isEmpty()) {
                Set<PfamBReg> attachedPfamBRegs = new HashSet<>();
                for (PfamBReg pfamBRegsPfamBRegToAttach : pfamB.getPfamBRegs()) {
                    PfamBReg pfamBRegsPfamBRegToAttachOnDB = em.find(pfamBRegsPfamBRegToAttach.getClass(), pfamBRegsPfamBRegToAttach.getWid());
                    if (pfamBRegsPfamBRegToAttachOnDB != null) {
                        attachedPfamBRegs.add(pfamBRegsPfamBRegToAttachOnDB);
                    } else {
                        attachedPfamBRegs.add(pfamBRegsPfamBRegToAttach);
                    }
                }
                pfamB.setPfamBRegs(attachedPfamBRegs);
            }
            em.persist(pfamB);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PfamBbioWH pfamB) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + pfamB.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PfamBbioWH persistentPfamB = em.find(PfamBbioWH.class, pfamB.getWid());
            Set<PfamBReg> pfamBRegsOld = persistentPfamB.getPfamBRegs();
            Set<PfamBReg> pfamBRegsNew = pfamB.getPfamBRegs();
            Set<PfamBReg> attachedPfamBRegsNew = new HashSet<>();
            for (PfamBReg pfamBRegsNewPfamBRegToAttach : pfamBRegsNew) {
                pfamBRegsNewPfamBRegToAttach = em.getReference(pfamBRegsNewPfamBRegToAttach.getClass(), pfamBRegsNewPfamBRegToAttach.getWid());
                attachedPfamBRegsNew.add(pfamBRegsNewPfamBRegToAttach);
            }
            pfamBRegsNew = attachedPfamBRegsNew;
            pfamB.setPfamBRegs(pfamBRegsNew);
            pfamB = em.merge(pfamB);
            for (PfamBReg pfamBRegsOldPfamBReg : pfamBRegsOld) {
                if (!pfamBRegsNew.contains(pfamBRegsOldPfamBReg)) {
                    pfamBRegsOldPfamBReg.setPfamB(null);
                    pfamBRegsOldPfamBReg = em.merge(pfamBRegsOldPfamBReg);
                }
            }
            for (PfamBReg pfamBRegsNewPfamBReg : pfamBRegsNew) {
                if (!pfamBRegsOld.contains(pfamBRegsNewPfamBReg)) {
                    PfamBbioWH oldPfamBOfPfamBRegsNewPfamBReg = pfamBRegsNewPfamBReg.getPfamB();
                    pfamBRegsNewPfamBReg.setPfamB(pfamB);
                    pfamBRegsNewPfamBReg = em.merge(pfamBRegsNewPfamBReg);
                    if (oldPfamBOfPfamBRegsNewPfamBReg != null && !oldPfamBOfPfamBRegsNewPfamBReg.equals(pfamB)) {
                        oldPfamBOfPfamBRegsNewPfamBReg.getPfamBRegs().remove(pfamBRegsNewPfamBReg);
                        oldPfamBOfPfamBRegsNewPfamBReg = em.merge(oldPfamBOfPfamBRegsNewPfamBReg);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pfamB.getWid();
                if (findPfamB(id) == null) {
                    throw new NonexistentEntityException("The pfamB with id " + id + " no longer exists.");
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
            PfamBbioWH pfamB;
            try {
                pfamB = em.getReference(PfamBbioWH.class, id);
                pfamB.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pfamB with id " + id + " no longer exists.", enfe);
            }
            Set<PfamBReg> pfamBRegs = pfamB.getPfamBRegs();
            for (PfamBReg pfamBRegsPfamBReg : pfamBRegs) {
                pfamBRegsPfamBReg.setPfamB(null);
                pfamBRegsPfamBReg = em.merge(pfamBRegsPfamBReg);
            }
            em.remove(pfamB);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PfamBbioWH> findPfamBEntities() {
        return findPfamBEntities(true, -1, -1);
    }

    public List<PfamBbioWH> findPfamBEntities(int maxResults, int firstResult) {
        return findPfamBEntities(false, maxResults, firstResult);
    }

    private List<PfamBbioWH> findPfamBEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PfamBbioWH.class));
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

    public PfamBbioWH findPfamB(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PfamBbioWH.class, id);
        } finally {
            em.close();
        }
    }

    public int getPfamBCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PfamBbioWH> rt = cq.from(PfamBbioWH.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
