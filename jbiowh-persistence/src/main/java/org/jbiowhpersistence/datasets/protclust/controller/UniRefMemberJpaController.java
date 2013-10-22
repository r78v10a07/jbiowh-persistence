package org.jbiowhpersistence.datasets.protclust.controller;

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
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMember;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMemberProperty;
import org.jbiowhpersistence.datasets.protein.controller.ProteinJpaController;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is UniRefMember Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 6, 2011
 */
public class UniRefMemberJpaController extends AbstractJpaController<UniRefMember> implements Serializable {

    public UniRefMemberJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UniRefMember uniRefMember) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + uniRefMember.getWid());
        if (uniRefMember.getUniRefMemberProperty() == null) {
            uniRefMember.setUniRefMemberProperty(new HashSet<UniRefMemberProperty>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Protein protein = uniRefMember.getProtein();
            if (protein != null) {
                protein = em.find(protein.getClass(), protein.getWid());
                if (protein != null) {
                    uniRefMember.setProtein(protein);
                } else {
                    ProteinJpaController pController = new ProteinJpaController(emf);
                    pController.create(uniRefMember.getProtein());
                    uniRefMember.setProtein(em.getReference(protein.getClass(), protein.getWid()));
                }
            }
            UniRefEntry uniRefEntry = uniRefMember.getUniRefEntry();
            if (uniRefEntry != null) {
                uniRefEntry = em.find(uniRefEntry.getClass(), uniRefEntry.getWid());
                if (uniRefEntry != null) {
                    uniRefMember.setUniRefEntry(uniRefEntry);
                } else {
                    UniRefEntryJpaController uController = new UniRefEntryJpaController(emf);
                    uController.create(uniRefMember.getUniRefEntry());
                    uniRefMember.setUniRefEntry(em.getReference(uniRefEntry.getClass(), uniRefEntry.getWid()));
                }
            }
            if (!uniRefMember.getUniRefMemberProperty().isEmpty()) {
                Set<UniRefMemberProperty> attachedUniRefMemberProperty = new HashSet();
                for (UniRefMemberProperty uniRefMemberPropertyUniRefMemberPropertyToAttach : uniRefMember.getUniRefMemberProperty()) {
                    UniRefMemberProperty uniRefMemberPropertyUniRefMemberProperty = em.find(uniRefMemberPropertyUniRefMemberPropertyToAttach.getClass(), uniRefMemberPropertyUniRefMemberPropertyToAttach.getWid());
                    if (uniRefMemberPropertyUniRefMemberProperty != null) {
                        attachedUniRefMemberProperty.add(uniRefMemberPropertyUniRefMemberProperty);
                    } else {
                        attachedUniRefMemberProperty.add(uniRefMemberPropertyUniRefMemberPropertyToAttach);
                    }
                }
                uniRefMember.setUniRefMemberProperty(attachedUniRefMemberProperty);
            }
            uniRefMember.setTaxonomy(createTaxonomy(emf, em, createTaxonomy(emf, em, uniRefMember.getTaxonomy())));
            em.persist(uniRefMember);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUniRefMember(uniRefMember.getWid()) != null) {
                throw new PreexistingEntityException("UniRefMember " + uniRefMember + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UniRefMember uniRefMember) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + uniRefMember.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UniRefMember persistentUniRefMember = em.find(UniRefMember.class, uniRefMember.getWid());
            Protein proteinOld = persistentUniRefMember.getProtein();
            Protein proteinNew = uniRefMember.getProtein();
            UniRefEntry uniRefEntryOld = persistentUniRefMember.getUniRefEntry();
            UniRefEntry uniRefEntryNew = uniRefMember.getUniRefEntry();
            Set<UniRefMemberProperty> uniRefMemberPropertyOld = persistentUniRefMember.getUniRefMemberProperty();
            Set<UniRefMemberProperty> uniRefMemberPropertyNew = uniRefMember.getUniRefMemberProperty();
            if (proteinNew != null) {
                proteinNew = em.getReference(proteinNew.getClass(), proteinNew.getWid());
                uniRefMember.setProtein(proteinNew);
            }
            if (uniRefEntryNew != null) {
                uniRefEntryNew = em.getReference(uniRefEntryNew.getClass(), uniRefEntryNew.getWid());
                uniRefMember.setUniRefEntry(uniRefEntryNew);
            }
            Set<UniRefMemberProperty> attachedUniRefMemberPropertyNew = new HashSet();
            for (UniRefMemberProperty uniRefMemberPropertyNewUniRefMemberPropertyToAttach : uniRefMemberPropertyNew) {
                uniRefMemberPropertyNewUniRefMemberPropertyToAttach = em.getReference(uniRefMemberPropertyNewUniRefMemberPropertyToAttach.getClass(), uniRefMemberPropertyNewUniRefMemberPropertyToAttach.getWid());
                attachedUniRefMemberPropertyNew.add(uniRefMemberPropertyNewUniRefMemberPropertyToAttach);
            }
            uniRefMemberPropertyNew = attachedUniRefMemberPropertyNew;
            uniRefMember.setUniRefMemberProperty(uniRefMemberPropertyNew);
            uniRefMember = em.merge(uniRefMember);
            if (proteinOld != null && !proteinOld.equals(proteinNew)) {
                proteinOld.getUniRefMember().remove(uniRefMember);
                proteinOld = em.merge(proteinOld);
            }
            if (proteinNew != null && !proteinNew.equals(proteinOld)) {
                proteinNew.getUniRefMember().add(uniRefMember);
                em.merge(proteinNew);
            }
            if (uniRefEntryOld != null && !uniRefEntryOld.equals(uniRefEntryNew)) {
                uniRefEntryOld.getUniRefMember().remove(uniRefMember);
                uniRefEntryOld = em.merge(uniRefEntryOld);
            }
            if (uniRefEntryNew != null && !uniRefEntryNew.equals(uniRefEntryOld)) {
                uniRefEntryNew.getUniRefMember().add(uniRefMember);
                em.merge(uniRefEntryNew);
            }
            for (UniRefMemberProperty uniRefMemberPropertyOldUniRefMemberProperty : uniRefMemberPropertyOld) {
                if (!uniRefMemberPropertyNew.contains(uniRefMemberPropertyOldUniRefMemberProperty)) {
                    uniRefMemberPropertyOldUniRefMemberProperty.setUniRefMember(null);
                    uniRefMemberPropertyOldUniRefMemberProperty = em.merge(uniRefMemberPropertyOldUniRefMemberProperty);
                }
            }
            for (UniRefMemberProperty uniRefMemberPropertyNewUniRefMemberProperty : uniRefMemberPropertyNew) {
                if (!uniRefMemberPropertyOld.contains(uniRefMemberPropertyNewUniRefMemberProperty)) {
                    UniRefMember oldUniRefMemberOfUniRefMemberPropertyNewUniRefMemberProperty = uniRefMemberPropertyNewUniRefMemberProperty.getUniRefMember();
                    uniRefMemberPropertyNewUniRefMemberProperty.setUniRefMember(uniRefMember);
                    uniRefMemberPropertyNewUniRefMemberProperty = em.merge(uniRefMemberPropertyNewUniRefMemberProperty);
                    if (oldUniRefMemberOfUniRefMemberPropertyNewUniRefMemberProperty != null && !oldUniRefMemberOfUniRefMemberPropertyNewUniRefMemberProperty.equals(uniRefMember)) {
                        oldUniRefMemberOfUniRefMemberPropertyNewUniRefMemberProperty.getUniRefMemberProperty().remove(uniRefMemberPropertyNewUniRefMemberProperty);
                        em.merge(oldUniRefMemberOfUniRefMemberPropertyNewUniRefMemberProperty);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = uniRefMember.getWid();
                if (findUniRefMember(id) == null) {
                    throw new NonexistentEntityException("The uniRefMember with id " + id + " no longer exists.");
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
            UniRefMember uniRefMember;
            try {
                uniRefMember = em.getReference(UniRefMember.class, id);
                uniRefMember.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uniRefMember with id " + id + " no longer exists.", enfe);
            }
            Set<UniRefMemberProperty> uniRefMemberProperty = uniRefMember.getUniRefMemberProperty();
            for (UniRefMemberProperty uniRefMemberPropertyUniRefMemberProperty : uniRefMemberProperty) {
                uniRefMemberPropertyUniRefMemberProperty.setUniRefMember(null);
                uniRefMemberPropertyUniRefMemberProperty = em.merge(uniRefMemberPropertyUniRefMemberProperty);
            }
            em.remove(uniRefMember);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UniRefMember> findUniRefMemberEntities() {
        return findUniRefMemberEntities(true, -1, -1);
    }

    public List<UniRefMember> findUniRefMemberEntities(int maxResults, int firstResult) {
        return findUniRefMemberEntities(false, maxResults, firstResult);
    }

    private List<UniRefMember> findUniRefMemberEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UniRefMember.class));
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

    public UniRefMember findUniRefMember(Long id) {
        EntityManager em = getEntityManager();
        try {
            return (UniRefMember) em.find(UniRefMember.class, id);
        } finally {
            em.close();
        }
    }

    public int getUniRefMemberCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UniRefMember> rt = cq.from(UniRefMember.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
