package org.jbiowhpersistence.datasets.domain.pfam.controller;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAPDBReg;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamARegFullSignificant;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamSeqhasProtein;
import org.jbiowhpersistence.utils.controller.exceptions.IllegalOrphanException;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;

/**
 * This class is the PfamARegFullSignificant Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ $LastChangedRevision: 396 $
 *
 * @since Nov 22, 2012
 */
public class PfamARegFullSignificantJpaController extends AbstractPFamJpaController<PfamARegFullSignificant> implements Serializable {

    public PfamARegFullSignificantJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PfamARegFullSignificant pfamARegFullSignificant) throws Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + pfamARegFullSignificant.getWid());
        if (pfamARegFullSignificant.getPfamAPDBRegs() == null) {
            pfamARegFullSignificant.setPfamAPDBRegs(new HashSet<PfamAPDBReg>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            pfamARegFullSignificant.setPfamA(createPfamA(emf, em, pfamARegFullSignificant.getPfamA()));

            PfamSeqhasProtein pfamSeqhasProtein = pfamARegFullSignificant.getPfamSeqhasProtein();
            if (pfamSeqhasProtein != null) {
                PfamSeqhasProtein pfamSeqhasProteinOnDB = em.find(pfamSeqhasProtein.getClass(), pfamSeqhasProtein.getPfamSeqhasProteinPK());
                if (pfamSeqhasProteinOnDB != null) {
                    pfamARegFullSignificant.setPfamSeqhasProtein(pfamSeqhasProteinOnDB);
                } else {
                    pfamSeqhasProtein.setPfamARegFullInsignificant(null);
                    pfamSeqhasProtein.setPfamARegFullSignificant(null);
                    pfamARegFullSignificant.setPfamSeqhasProtein(pfamSeqhasProtein);
                }
            }
            if (!pfamARegFullSignificant.getPfamAPDBRegs().isEmpty()) {
                Set<PfamAPDBReg> attachedPfamAPDBRegs = new HashSet();
                for (PfamAPDBReg pfamAPDBRegsToAttach : pfamARegFullSignificant.getPfamAPDBRegs()) {
                    PfamAPDBReg pfamAPDBRegsToAttachOnDB = em.find(pfamAPDBRegsToAttach.getClass(), pfamAPDBRegsToAttach.getWid());
                    if (pfamAPDBRegsToAttachOnDB != null) {
                        attachedPfamAPDBRegs.add(pfamAPDBRegsToAttachOnDB);
                    } else {
                        pfamAPDBRegsToAttach.setPfamARegFullSignificant(null);
                        attachedPfamAPDBRegs.add(pfamAPDBRegsToAttach);
                    }
                }
                pfamARegFullSignificant.setPfamAPDBRegs(attachedPfamAPDBRegs);
            }
            em.persist(pfamARegFullSignificant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PfamARegFullSignificant pfamARegFullSignificant) throws IllegalOrphanException, NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editting " + this.getClass().getSimpleName() + ": " + pfamARegFullSignificant.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PfamARegFullSignificant persistentPfamARegFullSignificant = em.find(PfamARegFullSignificant.class, pfamARegFullSignificant.getWid());
            PfamAbioWH pfamAOld = persistentPfamARegFullSignificant.getPfamA();
            PfamAbioWH pfamANew = pfamARegFullSignificant.getPfamA();
            PfamSeqhasProtein pfamSeqhasProteinOld = persistentPfamARegFullSignificant.getPfamSeqhasProtein();
            PfamSeqhasProtein pfamSeqhasProteinNew = pfamARegFullSignificant.getPfamSeqhasProtein();
            Set<PfamAPDBReg> pfamAPDBRegsOld = persistentPfamARegFullSignificant.getPfamAPDBRegs();
            Set<PfamAPDBReg> pfamAPDBRegsNew = pfamARegFullSignificant.getPfamAPDBRegs();
            List<String> illegalOrphanMessages = null;
            for (PfamAPDBReg pfamAPDBRegsOldPfamAPDBReg : pfamAPDBRegsOld) {
                if (!pfamAPDBRegsNew.contains(pfamAPDBRegsOldPfamAPDBReg)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList();
                    }
                    illegalOrphanMessages.add("You must retain PfamAPDBReg " + pfamAPDBRegsOldPfamAPDBReg + " since its pfamARegFullSignificant field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pfamANew != null) {
                pfamANew = em.getReference(pfamANew.getClass(), pfamANew.getWid());
                pfamARegFullSignificant.setPfamA(pfamANew);
            }
            if (pfamSeqhasProteinNew != null) {
                pfamSeqhasProteinNew = em.getReference(pfamSeqhasProteinNew.getClass(), pfamSeqhasProteinNew.getPfamSeqhasProteinPK());
                pfamARegFullSignificant.setPfamSeqhasProtein(pfamSeqhasProteinNew);
            }
            Set<PfamAPDBReg> attachedPfamAPDBRegsNew = new HashSet();
            for (PfamAPDBReg pfamAPDBRegsNewPfamAPDBRegToAttach : pfamAPDBRegsNew) {
                pfamAPDBRegsNewPfamAPDBRegToAttach = em.getReference(pfamAPDBRegsNewPfamAPDBRegToAttach.getClass(), pfamAPDBRegsNewPfamAPDBRegToAttach.getWid());
                attachedPfamAPDBRegsNew.add(pfamAPDBRegsNewPfamAPDBRegToAttach);
            }
            pfamAPDBRegsNew = attachedPfamAPDBRegsNew;
            pfamARegFullSignificant.setPfamAPDBRegs(pfamAPDBRegsNew);
            pfamARegFullSignificant = em.merge(pfamARegFullSignificant);
            if (pfamAOld != null && !pfamAOld.equals(pfamANew)) {
                pfamAOld.getPfamARegFullSignificants().remove(pfamARegFullSignificant);
                pfamAOld = em.merge(pfamAOld);
            }
            if (pfamANew != null && !pfamANew.equals(pfamAOld)) {
                pfamANew.getPfamARegFullSignificants().add(pfamARegFullSignificant);
                pfamANew = em.merge(pfamANew);
            }
            if (pfamSeqhasProteinOld != null && !pfamSeqhasProteinOld.equals(pfamSeqhasProteinNew)) {
                pfamSeqhasProteinOld.setPfamARegFullSignificant(null);
                pfamSeqhasProteinOld = em.merge(pfamSeqhasProteinOld);
            }
            if (pfamSeqhasProteinNew != null && !pfamSeqhasProteinNew.equals(pfamSeqhasProteinOld)) {
                PfamARegFullSignificant oldPfamARegFullSignificantOfPfamSeqhasProtein = pfamSeqhasProteinNew.getPfamARegFullSignificant();
                if (oldPfamARegFullSignificantOfPfamSeqhasProtein != null) {
                    oldPfamARegFullSignificantOfPfamSeqhasProtein.setPfamSeqhasProtein(null);
                    oldPfamARegFullSignificantOfPfamSeqhasProtein = em.merge(oldPfamARegFullSignificantOfPfamSeqhasProtein);
                }
                pfamSeqhasProteinNew.setPfamARegFullSignificant(pfamARegFullSignificant);
                pfamSeqhasProteinNew = em.merge(pfamSeqhasProteinNew);
            }
            for (PfamAPDBReg pfamAPDBRegsNewPfamAPDBReg : pfamAPDBRegsNew) {
                if (!pfamAPDBRegsOld.contains(pfamAPDBRegsNewPfamAPDBReg)) {
                    PfamARegFullSignificant oldPfamARegFullSignificantOfPfamAPDBRegsNewPfamAPDBReg = pfamAPDBRegsNewPfamAPDBReg.getPfamARegFullSignificant();
                    pfamAPDBRegsNewPfamAPDBReg.setPfamARegFullSignificant(pfamARegFullSignificant);
                    pfamAPDBRegsNewPfamAPDBReg = em.merge(pfamAPDBRegsNewPfamAPDBReg);
                    if (oldPfamARegFullSignificantOfPfamAPDBRegsNewPfamAPDBReg != null && !oldPfamARegFullSignificantOfPfamAPDBRegsNewPfamAPDBReg.equals(pfamARegFullSignificant)) {
                        oldPfamARegFullSignificantOfPfamAPDBRegsNewPfamAPDBReg.getPfamAPDBRegs().remove(pfamAPDBRegsNewPfamAPDBReg);
                        oldPfamARegFullSignificantOfPfamAPDBRegsNewPfamAPDBReg = em.merge(oldPfamARegFullSignificantOfPfamAPDBRegsNewPfamAPDBReg);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pfamARegFullSignificant.getWid();
                if (findPfamARegFullSignificant(id) == null) {
                    throw new NonexistentEntityException("The pfamARegFullSignificant with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PfamARegFullSignificant pfamARegFullSignificant;
            try {
                pfamARegFullSignificant = em.getReference(PfamARegFullSignificant.class, id);
                pfamARegFullSignificant.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pfamARegFullSignificant with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<PfamAPDBReg> pfamAPDBRegsOrphanCheck = pfamARegFullSignificant.getPfamAPDBRegs();
            for (PfamAPDBReg pfamAPDBRegsOrphanCheckPfamAPDBReg : pfamAPDBRegsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList();
                }
                illegalOrphanMessages.add("This PfamARegFullSignificant (" + pfamARegFullSignificant + ") cannot be destroyed since the PfamAPDBReg " + pfamAPDBRegsOrphanCheckPfamAPDBReg + " in its pfamAPDBRegs field has a non-nullable pfamARegFullSignificant field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            PfamAbioWH pfamA = pfamARegFullSignificant.getPfamA();
            if (pfamA != null) {
                pfamA.getPfamARegFullSignificants().remove(pfamARegFullSignificant);
                pfamA = em.merge(pfamA);
            }
            PfamSeqhasProtein pfamSeqhasProtein = pfamARegFullSignificant.getPfamSeqhasProtein();
            if (pfamSeqhasProtein != null) {
                pfamSeqhasProtein.setPfamARegFullSignificant(null);
                pfamSeqhasProtein = em.merge(pfamSeqhasProtein);
            }
            em.remove(pfamARegFullSignificant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PfamARegFullSignificant> findPfamARegFullSignificantEntities() {
        return findPfamARegFullSignificantEntities(true, -1, -1);
    }

    public List<PfamARegFullSignificant> findPfamARegFullSignificantEntities(int maxResults, int firstResult) {
        return findPfamARegFullSignificantEntities(false, maxResults, firstResult);
    }

    private List<PfamARegFullSignificant> findPfamARegFullSignificantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PfamARegFullSignificant.class));
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

    public PfamARegFullSignificant findPfamARegFullSignificant(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PfamARegFullSignificant.class, id);
        } finally {
            em.close();
        }
    }

    public int getPfamARegFullSignificantCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PfamARegFullSignificant> rt = cq.from(PfamARegFullSignificant.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
