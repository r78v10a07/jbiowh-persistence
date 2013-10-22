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
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamArchitecture;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClans;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;

/**
 * This class is the PfamArchitecture Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Nov 21, 2012
 */
public class PfamArchitectureJpaController extends AbstractPFamJpaController<PfamArchitecture> implements Serializable {

    public PfamArchitectureJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PfamArchitecture pfamArchitecture) throws Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + pfamArchitecture.getWid());
        if (pfamArchitecture.getPfamA() == null) {
            pfamArchitecture.setPfamA(new HashSet<PfamAbioWH>());
        }
        if (pfamArchitecture.getPfamClanses() == null) {
            pfamArchitecture.setPfamClanses(new HashSet<PfamClans>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            pfamArchitecture.setPfamA(createPfamA(emf, em, pfamArchitecture.getPfamA()));
            pfamArchitecture.setPfamClanses(createPfamClans(emf, em, pfamArchitecture.getPfamClanses()));            
            
            em.persist(pfamArchitecture);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PfamArchitecture pfamArchitecture) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + pfamArchitecture.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PfamArchitecture persistentPfamArchitecture = em.find(PfamArchitecture.class, pfamArchitecture.getWid());
            Set<PfamAbioWH> pfamAOld = persistentPfamArchitecture.getPfamA();
            Set<PfamAbioWH> pfamANew = pfamArchitecture.getPfamA();
            Set<PfamClans> pfamClansesOld = persistentPfamArchitecture.getPfamClanses();
            Set<PfamClans> pfamClansesNew = pfamArchitecture.getPfamClanses();
            Set<PfamAbioWH> attachedPfamANew = new HashSet<>();
            for (PfamAbioWH pfamANewPfamAToAttach : pfamANew) {
                pfamANewPfamAToAttach = em.getReference(pfamANewPfamAToAttach.getClass(), pfamANewPfamAToAttach.getWid());
                attachedPfamANew.add(pfamANewPfamAToAttach);
            }
            pfamANew = attachedPfamANew;
            pfamArchitecture.setPfamA(pfamANew);
            Set<PfamClans> attachedPfamClansesNew = new HashSet<>();
            for (PfamClans pfamClansesNewPfamClansToAttach : pfamClansesNew) {
                pfamClansesNewPfamClansToAttach = em.getReference(pfamClansesNewPfamClansToAttach.getClass(), pfamClansesNewPfamClansToAttach.getWid());
                attachedPfamClansesNew.add(pfamClansesNewPfamClansToAttach);
            }
            pfamClansesNew = attachedPfamClansesNew;
            pfamArchitecture.setPfamClanses(pfamClansesNew);
            pfamArchitecture = em.merge(pfamArchitecture);
            for (PfamAbioWH pfamAOldPfamA : pfamAOld) {
                if (!pfamANew.contains(pfamAOldPfamA)) {
                    pfamAOldPfamA.getPfamArchitectures().remove(pfamArchitecture);
                    pfamAOldPfamA = em.merge(pfamAOldPfamA);
                }
            }
            for (PfamAbioWH pfamANewPfamA : pfamANew) {
                if (!pfamAOld.contains(pfamANewPfamA)) {
                    pfamANewPfamA.getPfamArchitectures().add(pfamArchitecture);
                    pfamANewPfamA = em.merge(pfamANewPfamA);
                }
            }
            for (PfamClans pfamClansesOldPfamClans : pfamClansesOld) {
                if (!pfamClansesNew.contains(pfamClansesOldPfamClans)) {
                    pfamClansesOldPfamClans.getPfamArchitectures().remove(pfamArchitecture);
                    pfamClansesOldPfamClans = em.merge(pfamClansesOldPfamClans);
                }
            }
            for (PfamClans pfamClansesNewPfamClans : pfamClansesNew) {
                if (!pfamClansesOld.contains(pfamClansesNewPfamClans)) {
                    pfamClansesNewPfamClans.getPfamArchitectures().add(pfamArchitecture);
                    pfamClansesNewPfamClans = em.merge(pfamClansesNewPfamClans);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pfamArchitecture.getWid();
                if (findPfamArchitecture(id) == null) {
                    throw new NonexistentEntityException("The pfamArchitecture with id " + id + " no longer exists.");
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
            PfamArchitecture pfamArchitecture;
            try {
                pfamArchitecture = em.getReference(PfamArchitecture.class, id);
                pfamArchitecture.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pfamArchitecture with id " + id + " no longer exists.", enfe);
            }
            Set<PfamAbioWH> pfamA = pfamArchitecture.getPfamA();
            for (PfamAbioWH pfamAPfamA : pfamA) {
                pfamAPfamA.getPfamArchitectures().remove(pfamArchitecture);
                pfamAPfamA = em.merge(pfamAPfamA);
            }
            Set<PfamClans> pfamClanses = pfamArchitecture.getPfamClanses();
            for (PfamClans pfamClansesPfamClans : pfamClanses) {
                pfamClansesPfamClans.getPfamArchitectures().remove(pfamArchitecture);
                pfamClansesPfamClans = em.merge(pfamClansesPfamClans);
            }
            em.remove(pfamArchitecture);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PfamArchitecture> findPfamArchitectureEntities() {
        return findPfamArchitectureEntities(true, -1, -1);
    }

    public List<PfamArchitecture> findPfamArchitectureEntities(int maxResults, int firstResult) {
        return findPfamArchitectureEntities(false, maxResults, firstResult);
    }

    private List<PfamArchitecture> findPfamArchitectureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PfamArchitecture.class));
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

    public PfamArchitecture findPfamArchitecture(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PfamArchitecture.class, id);
        } finally {
            em.close();
        }
    }

    public int getPfamArchitectureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PfamArchitecture> rt = cq.from(PfamArchitecture.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
