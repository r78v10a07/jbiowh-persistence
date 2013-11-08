package org.jbiowhpersistence.datasets.domain.pfam.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAhasPfamLiteratureReferences;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAhasPfamLiteratureReferencesPK;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClans;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClanshasPfamLiteratureReferences;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClanshasPfamLiteratureReferencesPK;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamLiteratureReferences;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;


/**
 * This class is the PfamLiteratureReferences Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Nov 26, 2012
 */
public class PfamLiteratureReferencesJpaController extends AbstractPFamJpaController<PfamLiteratureReferences> implements Serializable {

    public PfamLiteratureReferencesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PfamLiteratureReferences pfamLiteratureReferences) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + pfamLiteratureReferences.getWid());
        if (pfamLiteratureReferences.getPfamAhasPfamLiteratureReferences() == null){
            pfamLiteratureReferences.setPfamAhasPfamLiteratureReferences(new HashMap<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences>());
        }
        if (pfamLiteratureReferences.getPfamClanshasPfamLiteratureReferences() == null){
            pfamLiteratureReferences.setPfamClanshasPfamLiteratureReferences(new HashMap<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            if (!pfamLiteratureReferences.getPfamAhasPfamLiteratureReferences().isEmpty()) {
                PfamAbioWHJpaController kController = new PfamAbioWHJpaController(emf);
                Map<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences> attachPfamLiteratureReferences = new HashMap();
                for (PfamAhasPfamLiteratureReferences pfamAhasPfamLiteratureReferencesToAttach : pfamLiteratureReferences.getPfamAhasPfamLiteratureReferences().values()) {
                    PfamAhasPfamLiteratureReferences pfamAhasPfamLiteratureReferencesOnDB = em.find(pfamAhasPfamLiteratureReferencesToAttach.getClass(), pfamAhasPfamLiteratureReferencesToAttach.getPfamAhasPfamLiteratureReferencesPK());
                    if (pfamAhasPfamLiteratureReferencesOnDB != null) {
                        attachPfamLiteratureReferences.put(pfamAhasPfamLiteratureReferencesOnDB.getPfamAhasPfamLiteratureReferencesPK(), pfamAhasPfamLiteratureReferencesOnDB);
                    } else {
                        PfamAbioWH pfamAOnDB = em.find(PfamAbioWH.class, pfamAhasPfamLiteratureReferencesToAttach.getPfamAhasPfamLiteratureReferencesPK().getPfamAWID());
                        if (pfamAOnDB != null) {
                            pfamAhasPfamLiteratureReferencesToAttach.setPfamA(pfamAOnDB);
                        } else {
                            pfamAOnDB = pfamAhasPfamLiteratureReferencesToAttach.getPfamA();
                            pfamAOnDB.setPfamAhasPfamLiteratureReferences(null);
                            kController.create(pfamAOnDB);
                            pfamAhasPfamLiteratureReferencesToAttach.setPfamA(em.find(PfamAbioWH.class, pfamAOnDB.getWid()));
                        }
                        attachPfamLiteratureReferences.put(pfamAhasPfamLiteratureReferencesToAttach.getPfamAhasPfamLiteratureReferencesPK(), pfamAhasPfamLiteratureReferencesToAttach);
                    }
                }
                pfamLiteratureReferences.setPfamAhasPfamLiteratureReferences(attachPfamLiteratureReferences);
            }
            
            if (!pfamLiteratureReferences.getPfamClanshasPfamLiteratureReferences().isEmpty()) {
                PfamClansJpaController kController = new PfamClansJpaController(emf);
                Map<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences> attachPfamLiteratureReferences = new HashMap();
                for (PfamClanshasPfamLiteratureReferences pfamClanshasPfamLiteratureReferencesToAttach : pfamLiteratureReferences.getPfamClanshasPfamLiteratureReferences().values()) {
                    PfamClanshasPfamLiteratureReferences pfamClanshasPfamLiteratureReferencesOnDB = em.find(pfamClanshasPfamLiteratureReferencesToAttach.getClass(), pfamClanshasPfamLiteratureReferencesToAttach.getPfamClanshasPfamLiteratureReferencesPK());
                    if (pfamClanshasPfamLiteratureReferencesOnDB != null) {
                        attachPfamLiteratureReferences.put(pfamClanshasPfamLiteratureReferencesOnDB.getPfamClanshasPfamLiteratureReferencesPK(), pfamClanshasPfamLiteratureReferencesOnDB);
                    } else {
                        PfamClans pfamClansOnDB = em.find(PfamClans.class, pfamClanshasPfamLiteratureReferencesToAttach.getPfamClanshasPfamLiteratureReferencesPK().getPfamClansWID());
                        if (pfamClansOnDB != null) {
                            pfamClanshasPfamLiteratureReferencesToAttach.setPfamClans(pfamClansOnDB);
                        } else {
                            pfamClansOnDB = pfamClanshasPfamLiteratureReferencesToAttach.getPfamClans();
                            pfamClansOnDB.setPfamClanshasPfamLiteratureReferences(null);
                            kController.create(pfamClansOnDB);
                            pfamClanshasPfamLiteratureReferencesToAttach.setPfamClans(em.find(PfamClans.class, pfamClansOnDB.getWid()));
                        }
                        attachPfamLiteratureReferences.put(pfamClanshasPfamLiteratureReferencesToAttach.getPfamClanshasPfamLiteratureReferencesPK(), pfamClanshasPfamLiteratureReferencesToAttach);
                    }
                }
                pfamLiteratureReferences.setPfamClanshasPfamLiteratureReferences(attachPfamLiteratureReferences);
            }
            
            em.persist(pfamLiteratureReferences);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPfamLiteratureReferences(pfamLiteratureReferences.getWid()) != null) {
                throw new PreexistingEntityException("PfamLiteratureReferences " + pfamLiteratureReferences + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PfamLiteratureReferences pfamLiteratureReferences) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + pfamLiteratureReferences.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pfamLiteratureReferences = em.merge(pfamLiteratureReferences);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pfamLiteratureReferences.getWid();
                if (findPfamLiteratureReferences(id) == null) {
                    throw new NonexistentEntityException("The pfamLiteratureReferences with id " + id + " no longer exists.");
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
            PfamLiteratureReferences pfamLiteratureReferences;
            try {
                pfamLiteratureReferences = em.getReference(PfamLiteratureReferences.class, id);
                pfamLiteratureReferences.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pfamLiteratureReferences with id " + id + " no longer exists.", enfe);
            }
            em.remove(pfamLiteratureReferences);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PfamLiteratureReferences> findPfamLiteratureReferencesEntities() {
        return findPfamLiteratureReferencesEntities(true, -1, -1);
    }

    public List<PfamLiteratureReferences> findPfamLiteratureReferencesEntities(int maxResults, int firstResult) {
        return findPfamLiteratureReferencesEntities(false, maxResults, firstResult);
    }

    private List<PfamLiteratureReferences> findPfamLiteratureReferencesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PfamLiteratureReferences.class));
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

    public PfamLiteratureReferences findPfamLiteratureReferences(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PfamLiteratureReferences.class, id);
        } finally {
            em.close();
        }
    }

    public int getPfamLiteratureReferencesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PfamLiteratureReferences> rt = cq.from(PfamLiteratureReferences.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
