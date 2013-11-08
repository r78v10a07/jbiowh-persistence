package org.jbiowhpersistence.datasets.domain.pfam.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClanDatabaseLinks;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClans;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClanshasPfamLiteratureReferences;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClanshasPfamLiteratureReferencesPK;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamLiteratureReferences;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the PfamClans Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Nov 26, 2012
 */
public class PfamClansJpaController extends AbstractPFamJpaController<PfamClans> implements Serializable {

    public PfamClansJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PfamClans pfamClans) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + pfamClans.getWid());
        if (pfamClans.getPfamClanDatabaseLinkses() == null) {
            pfamClans.setPfamClanDatabaseLinkses(new HashSet<PfamClanDatabaseLinks>());
        }
        if (pfamClans.getPfamClanshasPfamLiteratureReferences() == null){
            pfamClans.setPfamClanshasPfamLiteratureReferences(new HashMap<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            pfamClans.setPfamA(createPfamA(emf, em, pfamClans.getPfamA()));
            pfamClans.setPfamArchitectures(createPfamArchitecture(emf, em, pfamClans.getPfamArchitectures()));

            if (!pfamClans.getPfamClanDatabaseLinkses().isEmpty()) {
                Set<PfamClanDatabaseLinks> attachedPfamClanDatabaseLinkses = new HashSet();
                for (PfamClanDatabaseLinks pfamClanDatabaseLinksesPfamClanDatabaseLinksToAttach : pfamClans.getPfamClanDatabaseLinkses()) {
                    PfamClanDatabaseLinks pfamClanDatabaseLinksesPfamClanDatabaseLinksToAttachOnDB = em.find(pfamClanDatabaseLinksesPfamClanDatabaseLinksToAttach.getClass(), pfamClanDatabaseLinksesPfamClanDatabaseLinksToAttach.getWid());
                    if (pfamClanDatabaseLinksesPfamClanDatabaseLinksToAttachOnDB != null) {
                        attachedPfamClanDatabaseLinkses.add(pfamClanDatabaseLinksesPfamClanDatabaseLinksToAttachOnDB);
                    } else {
                        attachedPfamClanDatabaseLinkses.add(pfamClanDatabaseLinksesPfamClanDatabaseLinksToAttach);
                    }
                }
                pfamClans.setPfamClanDatabaseLinkses(attachedPfamClanDatabaseLinkses);
            }
            
            if (!pfamClans.getPfamClanshasPfamLiteratureReferences().isEmpty()) {
                PfamLiteratureReferencesJpaController kController = new PfamLiteratureReferencesJpaController(emf);
                Map<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences> attachPfamLiteratureReferences = new HashMap();
                for (PfamClanshasPfamLiteratureReferences pfamClanshasPfamLiteratureReferencesToAttach : pfamClans.getPfamClanshasPfamLiteratureReferences().values()) {
                    PfamClanshasPfamLiteratureReferences pfamClanshasPfamLiteratureReferencesOnDB = em.find(pfamClanshasPfamLiteratureReferencesToAttach.getClass(), pfamClanshasPfamLiteratureReferencesToAttach.getPfamClanshasPfamLiteratureReferencesPK());
                    if (pfamClanshasPfamLiteratureReferencesOnDB != null) {
                        attachPfamLiteratureReferences.put(pfamClanshasPfamLiteratureReferencesOnDB.getPfamClanshasPfamLiteratureReferencesPK(), pfamClanshasPfamLiteratureReferencesOnDB);
                    } else {
                        PfamLiteratureReferences pfamLiteratureReferencesOnDB = em.find(PfamLiteratureReferences.class, pfamClanshasPfamLiteratureReferencesToAttach.getPfamClanshasPfamLiteratureReferencesPK().getPfamLiteratureReferencesWID());
                        if (pfamLiteratureReferencesOnDB != null) {
                            pfamClanshasPfamLiteratureReferencesToAttach.setPfamLiteratureReferences(pfamLiteratureReferencesOnDB);
                        } else {
                            pfamLiteratureReferencesOnDB = pfamClanshasPfamLiteratureReferencesToAttach.getPfamLiteratureReferences();
                            pfamLiteratureReferencesOnDB.setPfamAhasPfamLiteratureReferences(null);
                            kController.create(pfamLiteratureReferencesOnDB);
                            pfamClanshasPfamLiteratureReferencesToAttach.setPfamLiteratureReferences(em.find(PfamLiteratureReferences.class, pfamLiteratureReferencesOnDB.getWid()));
                        }
                        attachPfamLiteratureReferences.put(pfamClanshasPfamLiteratureReferencesToAttach.getPfamClanshasPfamLiteratureReferencesPK(), pfamClanshasPfamLiteratureReferencesToAttach);
                    }
                }
                pfamClans.setPfamClanshasPfamLiteratureReferences(attachPfamLiteratureReferences);
            }
            
            em.persist(pfamClans);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPfamClans(pfamClans.getWid()) != null) {
                throw new PreexistingEntityException("PfamClans " + pfamClans + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PfamClans pfamClans) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + pfamClans.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PfamClans persistentPfamClans = em.find(PfamClans.class, pfamClans.getWid());
            Set<PfamClanDatabaseLinks> pfamClanDatabaseLinksesOld = persistentPfamClans.getPfamClanDatabaseLinkses();
            Set<PfamClanDatabaseLinks> pfamClanDatabaseLinksesNew = pfamClans.getPfamClanDatabaseLinkses();
            Set<PfamAbioWH> pfamAOld = persistentPfamClans.getPfamA();
            Set<PfamAbioWH> pfamANew = pfamClans.getPfamA();
            Set<PfamArchitecture> pfamArchitecturesOld = persistentPfamClans.getPfamArchitectures();
            Set<PfamArchitecture> pfamArchitecturesNew = pfamClans.getPfamArchitectures();
            Set<PfamClanDatabaseLinks> attachedPfamClanDatabaseLinksesNew = new HashSet();
            for (PfamClanDatabaseLinks pfamClanDatabaseLinksesNewPfamClanDatabaseLinksToAttach : pfamClanDatabaseLinksesNew) {
                pfamClanDatabaseLinksesNewPfamClanDatabaseLinksToAttach = em.getReference(pfamClanDatabaseLinksesNewPfamClanDatabaseLinksToAttach.getClass(), pfamClanDatabaseLinksesNewPfamClanDatabaseLinksToAttach.getWid());
                attachedPfamClanDatabaseLinksesNew.add(pfamClanDatabaseLinksesNewPfamClanDatabaseLinksToAttach);
            }
            pfamClanDatabaseLinksesNew = attachedPfamClanDatabaseLinksesNew;
            pfamClans.setPfamClanDatabaseLinkses(pfamClanDatabaseLinksesNew);
            Set<PfamAbioWH> attachedPfamANew = new HashSet();
            for (PfamAbioWH pfamANewPfamAToAttach : pfamANew) {
                pfamANewPfamAToAttach = em.getReference(pfamANewPfamAToAttach.getClass(), pfamANewPfamAToAttach.getWid());
                attachedPfamANew.add(pfamANewPfamAToAttach);
            }
            pfamANew = attachedPfamANew;
            pfamClans.setPfamA(pfamANew);
            Set<PfamArchitecture> attachedPfamArchitecturesNew = new HashSet();
            for (PfamArchitecture pfamArchitecturesNewPfamArchitectureToAttach : pfamArchitecturesNew) {
                pfamArchitecturesNewPfamArchitectureToAttach = em.getReference(pfamArchitecturesNewPfamArchitectureToAttach.getClass(), pfamArchitecturesNewPfamArchitectureToAttach.getWid());
                attachedPfamArchitecturesNew.add(pfamArchitecturesNewPfamArchitectureToAttach);
            }
            pfamArchitecturesNew = attachedPfamArchitecturesNew;
            pfamClans.setPfamArchitectures(pfamArchitecturesNew);
            pfamClans = em.merge(pfamClans);
            for (PfamClanDatabaseLinks pfamClanDatabaseLinksesOldPfamClanDatabaseLinks : pfamClanDatabaseLinksesOld) {
                if (!pfamClanDatabaseLinksesNew.contains(pfamClanDatabaseLinksesOldPfamClanDatabaseLinks)) {
                    pfamClanDatabaseLinksesOldPfamClanDatabaseLinks.setPfamClans(null);
                    pfamClanDatabaseLinksesOldPfamClanDatabaseLinks = em.merge(pfamClanDatabaseLinksesOldPfamClanDatabaseLinks);
                }
            }
            for (PfamClanDatabaseLinks pfamClanDatabaseLinksesNewPfamClanDatabaseLinks : pfamClanDatabaseLinksesNew) {
                if (!pfamClanDatabaseLinksesOld.contains(pfamClanDatabaseLinksesNewPfamClanDatabaseLinks)) {
                    PfamClans oldPfamClansOfPfamClanDatabaseLinksesNewPfamClanDatabaseLinks = pfamClanDatabaseLinksesNewPfamClanDatabaseLinks.getPfamClans();
                    pfamClanDatabaseLinksesNewPfamClanDatabaseLinks.setPfamClans(pfamClans);
                    pfamClanDatabaseLinksesNewPfamClanDatabaseLinks = em.merge(pfamClanDatabaseLinksesNewPfamClanDatabaseLinks);
                    if (oldPfamClansOfPfamClanDatabaseLinksesNewPfamClanDatabaseLinks != null && !oldPfamClansOfPfamClanDatabaseLinksesNewPfamClanDatabaseLinks.equals(pfamClans)) {
                        oldPfamClansOfPfamClanDatabaseLinksesNewPfamClanDatabaseLinks.getPfamClanDatabaseLinkses().remove(pfamClanDatabaseLinksesNewPfamClanDatabaseLinks);
                        oldPfamClansOfPfamClanDatabaseLinksesNewPfamClanDatabaseLinks = em.merge(oldPfamClansOfPfamClanDatabaseLinksesNewPfamClanDatabaseLinks);
                    }
                }
            }
            for (PfamAbioWH pfamAOldPfamA : pfamAOld) {
                if (!pfamANew.contains(pfamAOldPfamA)) {
                    pfamAOldPfamA.getPfamClanses().remove(pfamClans);
                    pfamAOldPfamA = em.merge(pfamAOldPfamA);
                }
            }
            for (PfamAbioWH pfamANewPfamA : pfamANew) {
                if (!pfamAOld.contains(pfamANewPfamA)) {
                    pfamANewPfamA.getPfamClanses().add(pfamClans);
                    pfamANewPfamA = em.merge(pfamANewPfamA);
                }
            }
            for (PfamArchitecture pfamArchitecturesOldPfamArchitecture : pfamArchitecturesOld) {
                if (!pfamArchitecturesNew.contains(pfamArchitecturesOldPfamArchitecture)) {
                    pfamArchitecturesOldPfamArchitecture.getPfamClanses().remove(pfamClans);
                    pfamArchitecturesOldPfamArchitecture = em.merge(pfamArchitecturesOldPfamArchitecture);
                }
            }
            for (PfamArchitecture pfamArchitecturesNewPfamArchitecture : pfamArchitecturesNew) {
                if (!pfamArchitecturesOld.contains(pfamArchitecturesNewPfamArchitecture)) {
                    pfamArchitecturesNewPfamArchitecture.getPfamClanses().add(pfamClans);
                    pfamArchitecturesNewPfamArchitecture = em.merge(pfamArchitecturesNewPfamArchitecture);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pfamClans.getWid();
                if (findPfamClans(id) == null) {
                    throw new NonexistentEntityException("The pfamClans with id " + id + " no longer exists.");
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
            PfamClans pfamClans;
            try {
                pfamClans = em.getReference(PfamClans.class, id);
                pfamClans.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pfamClans with id " + id + " no longer exists.", enfe);
            }
            Set<PfamClanDatabaseLinks> pfamClanDatabaseLinkses = pfamClans.getPfamClanDatabaseLinkses();
            for (PfamClanDatabaseLinks pfamClanDatabaseLinksesPfamClanDatabaseLinks : pfamClanDatabaseLinkses) {
                pfamClanDatabaseLinksesPfamClanDatabaseLinks.setPfamClans(null);
                pfamClanDatabaseLinksesPfamClanDatabaseLinks = em.merge(pfamClanDatabaseLinksesPfamClanDatabaseLinks);
            }
            Set<PfamAbioWH> pfamA = pfamClans.getPfamA();
            for (PfamAbioWH pfamAPfamA : pfamA) {
                pfamAPfamA.getPfamClanses().remove(pfamClans);
                pfamAPfamA = em.merge(pfamAPfamA);
            }
            Set<PfamArchitecture> pfamArchitectures = pfamClans.getPfamArchitectures();
            for (PfamArchitecture pfamArchitecturesPfamArchitecture : pfamArchitectures) {
                pfamArchitecturesPfamArchitecture.getPfamClanses().remove(pfamClans);
                pfamArchitecturesPfamArchitecture = em.merge(pfamArchitecturesPfamArchitecture);
            }
            em.remove(pfamClans);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PfamClans> findPfamClansEntities() {
        return findPfamClansEntities(true, -1, -1);
    }

    public List<PfamClans> findPfamClansEntities(int maxResults, int firstResult) {
        return findPfamClansEntities(false, maxResults, firstResult);
    }

    private List<PfamClans> findPfamClansEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PfamClans.class));
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

    public PfamClans findPfamClans(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PfamClans.class, id);
        } finally {
            em.close();
        }
    }

    public int getPfamClansCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PfamClans> rt = cq.from(PfamClans.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
