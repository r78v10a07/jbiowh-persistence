package org.jbiowhpersistence.datasets.domain.pfam.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamARegFullInsignificant;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamSeqhasProtein;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;

/**
 * This class is the PfamARegFullInsignificant Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ $LastChangedRevision: 396 $
 *
 * @since Nov 22, 2012
 */
public class PfamARegFullInsignificantJpaController extends AbstractPFamJpaController<PfamARegFullInsignificant> implements Serializable {

    public PfamARegFullInsignificantJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PfamARegFullInsignificant pfamARegFullInsignificant) throws Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + pfamARegFullInsignificant.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            pfamARegFullInsignificant.setPfamA(createPfamA(emf, em, pfamARegFullInsignificant.getPfamA()));

            PfamSeqhasProtein pfamSeqhasProtein = pfamARegFullInsignificant.getPfamSeqhasProtein();
            if (pfamSeqhasProtein != null) {
                PfamSeqhasProtein pfamSeqhasProteinOnDB = em.find(pfamSeqhasProtein.getClass(), pfamSeqhasProtein.getPfamSeqhasProteinPK());
                if (pfamSeqhasProteinOnDB != null) {
                    pfamARegFullInsignificant.setPfamSeqhasProtein(pfamSeqhasProteinOnDB);
                } else {
                    pfamSeqhasProtein.setPfamARegFullInsignificant(null);
                    pfamSeqhasProtein.setPfamARegFullSignificant(null);
                    pfamARegFullInsignificant.setPfamSeqhasProtein(pfamSeqhasProtein);
                }
            }
            em.persist(pfamARegFullInsignificant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PfamARegFullInsignificant pfamARegFullInsignificant) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + pfamARegFullInsignificant.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PfamARegFullInsignificant persistentPfamARegFullInsignificant = em.find(PfamARegFullInsignificant.class, pfamARegFullInsignificant.getWid());
            PfamAbioWH pfamAOld = persistentPfamARegFullInsignificant.getPfamA();
            PfamAbioWH pfamANew = pfamARegFullInsignificant.getPfamA();
            PfamSeqhasProtein pfamSeqhasProteinOld = persistentPfamARegFullInsignificant.getPfamSeqhasProtein();
            PfamSeqhasProtein pfamSeqhasProteinNew = pfamARegFullInsignificant.getPfamSeqhasProtein();
            if (pfamANew != null) {
                pfamANew = em.getReference(pfamANew.getClass(), pfamANew.getWid());
                pfamARegFullInsignificant.setPfamA(pfamANew);
            }
            if (pfamSeqhasProteinNew != null) {
                pfamSeqhasProteinNew = em.getReference(pfamSeqhasProteinNew.getClass(), pfamSeqhasProteinNew.getPfamSeqhasProteinPK());
                pfamARegFullInsignificant.setPfamSeqhasProtein(pfamSeqhasProteinNew);
            }
            pfamARegFullInsignificant = em.merge(pfamARegFullInsignificant);
            if (pfamAOld != null && !pfamAOld.equals(pfamANew)) {
                pfamAOld.getPfamARegFullInsignificants().remove(pfamARegFullInsignificant);
                pfamAOld = em.merge(pfamAOld);
            }
            if (pfamANew != null && !pfamANew.equals(pfamAOld)) {
                pfamANew.getPfamARegFullInsignificants().add(pfamARegFullInsignificant);
                pfamANew = em.merge(pfamANew);
            }
            if (pfamSeqhasProteinOld != null && !pfamSeqhasProteinOld.equals(pfamSeqhasProteinNew)) {
                pfamSeqhasProteinOld.setPfamARegFullInsignificant(null);
                pfamSeqhasProteinOld = em.merge(pfamSeqhasProteinOld);
            }
            if (pfamSeqhasProteinNew != null && !pfamSeqhasProteinNew.equals(pfamSeqhasProteinOld)) {
                PfamARegFullInsignificant oldPfamARegFullInsignificantOfPfamSeqhasProtein = pfamSeqhasProteinNew.getPfamARegFullInsignificant();
                if (oldPfamARegFullInsignificantOfPfamSeqhasProtein != null) {
                    oldPfamARegFullInsignificantOfPfamSeqhasProtein.setPfamSeqhasProtein(null);
                    oldPfamARegFullInsignificantOfPfamSeqhasProtein = em.merge(oldPfamARegFullInsignificantOfPfamSeqhasProtein);
                }
                pfamSeqhasProteinNew.setPfamARegFullInsignificant(pfamARegFullInsignificant);
                pfamSeqhasProteinNew = em.merge(pfamSeqhasProteinNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pfamARegFullInsignificant.getWid();
                if (findPfamARegFullInsignificant(id) == null) {
                    throw new NonexistentEntityException("The pfamARegFullInsignificant with id " + id + " no longer exists.");
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
            PfamARegFullInsignificant pfamARegFullInsignificant;
            try {
                pfamARegFullInsignificant = em.getReference(PfamARegFullInsignificant.class, id);
                pfamARegFullInsignificant.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pfamARegFullInsignificant with id " + id + " no longer exists.", enfe);
            }
            PfamAbioWH pfamA = pfamARegFullInsignificant.getPfamA();
            if (pfamA != null) {
                pfamA.getPfamARegFullInsignificants().remove(pfamARegFullInsignificant);
                pfamA = em.merge(pfamA);
            }
            PfamSeqhasProtein pfamSeqhasProtein = pfamARegFullInsignificant.getPfamSeqhasProtein();
            if (pfamSeqhasProtein != null) {
                pfamSeqhasProtein.setPfamARegFullInsignificant(null);
                pfamSeqhasProtein = em.merge(pfamSeqhasProtein);
            }
            em.remove(pfamARegFullInsignificant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PfamARegFullInsignificant> findPfamARegFullInsignificantEntities() {
        return findPfamARegFullInsignificantEntities(true, -1, -1);
    }

    public List<PfamARegFullInsignificant> findPfamARegFullInsignificantEntities(int maxResults, int firstResult) {
        return findPfamARegFullInsignificantEntities(false, maxResults, firstResult);
    }

    private List<PfamARegFullInsignificant> findPfamARegFullInsignificantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PfamARegFullInsignificant.class));
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

    public PfamARegFullInsignificant findPfamARegFullInsignificant(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PfamARegFullInsignificant.class, id);
        } finally {
            em.close();
        }
    }

    public int getPfamARegFullInsignificantCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PfamARegFullInsignificant> rt = cq.from(PfamARegFullInsignificant.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
