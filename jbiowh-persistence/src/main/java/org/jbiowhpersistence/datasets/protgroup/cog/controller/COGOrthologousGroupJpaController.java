package org.jbiowhpersistence.datasets.protgroup.cog.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGFuncClass;
import java.util.HashSet;
import java.util.Set;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 20, 2013
 */
public class COGOrthologousGroupJpaController extends AbstractJpaController<COGOrthologousGroup> implements Serializable {

    public COGOrthologousGroupJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(COGOrthologousGroup COGOrthologousGroup) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + COGOrthologousGroup.getWid());
        if (COGOrthologousGroup.getCogFuncClass() == null) {
            COGOrthologousGroup.setCogFuncClass(new HashSet<COGFuncClass>());
        }
        if (COGOrthologousGroup.getGeneInfo() == null) {
            COGOrthologousGroup.setGeneInfo(new ArrayList<GeneInfo>());
        }
        if (COGOrthologousGroup.getProtein() == null) {
            COGOrthologousGroup.setProtein(new HashSet<Protein>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            COGFuncClassJpaController cTrl = new COGFuncClassJpaController(emf);
            Set<COGFuncClass> attachedCogFuncClass = new HashSet<COGFuncClass>();
            for (COGFuncClass cogFuncClassCOGFuncClassToAttach : COGOrthologousGroup.getCogFuncClass()) {
                COGFuncClass cogFuncClassCOGFuncClass = em.find(cogFuncClassCOGFuncClassToAttach.getClass(), cogFuncClassCOGFuncClassToAttach.getWid());
                if (cogFuncClassCOGFuncClass != null) {
                    attachedCogFuncClass.add(cogFuncClassCOGFuncClass);
                } else {
                    cogFuncClassCOGFuncClassToAttach.setCogOrthologousGroup(null);
                    cTrl.create(cogFuncClassCOGFuncClassToAttach);
                    attachedCogFuncClass.add(em.getReference(COGFuncClass.class, cogFuncClassCOGFuncClassToAttach.getWid()));
                }
            }
            COGOrthologousGroup.setCogFuncClass(attachedCogFuncClass);

            COGOrthologousGroup.setGeneInfo(createGeneInfo(emf, em, COGOrthologousGroup.getGeneInfo()));
            COGOrthologousGroup.setProtein(createProtein(emf, em, COGOrthologousGroup.getProtein()));
            COGOrthologousGroup.setTaxonomy(createTaxonomy(emf, em, COGOrthologousGroup.getTaxonomy()));
            em.persist(COGOrthologousGroup);
            for (COGFuncClass cogFuncClassCOGFuncClass : COGOrthologousGroup.getCogFuncClass()) {
                cogFuncClassCOGFuncClass.getCogOrthologousGroup().add(COGOrthologousGroup);
                cogFuncClassCOGFuncClass = em.merge(cogFuncClassCOGFuncClass);
            }
            for (GeneInfo geneInfoGeneInfo : COGOrthologousGroup.getGeneInfo()) {
                geneInfoGeneInfo.getCogOrthologousGroup().add(COGOrthologousGroup);
                geneInfoGeneInfo = em.merge(geneInfoGeneInfo);
            }
            for (Protein proteinProtein : COGOrthologousGroup.getProtein()) {
                proteinProtein.getCogOrthologousGroup().add(COGOrthologousGroup);
                proteinProtein = em.merge(proteinProtein);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCOGOrthologousGroup(COGOrthologousGroup.getWid()) != null) {
                throw new PreexistingEntityException("COGOrthologousGroup " + COGOrthologousGroup + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(COGOrthologousGroup COGOrthologousGroup) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + COGOrthologousGroup.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            COGOrthologousGroup persistentCOGOrthologousGroup = em.find(COGOrthologousGroup.class, COGOrthologousGroup.getWid());
            Set<COGFuncClass> cogFuncClassOld = persistentCOGOrthologousGroup.getCogFuncClass();
            Set<COGFuncClass> cogFuncClassNew = COGOrthologousGroup.getCogFuncClass();
            Collection<GeneInfo> geneInfoOld = persistentCOGOrthologousGroup.getGeneInfo();
            Collection<GeneInfo> geneInfoNew = COGOrthologousGroup.getGeneInfo();
            Set<Protein> proteinOld = persistentCOGOrthologousGroup.getProtein();
            Set<Protein> proteinNew = COGOrthologousGroup.getProtein();
            Set<COGFuncClass> attachedCogFuncClassNew = new HashSet<COGFuncClass>();
            for (COGFuncClass cogFuncClassNewCOGFuncClassToAttach : cogFuncClassNew) {
                cogFuncClassNewCOGFuncClassToAttach = em.getReference(cogFuncClassNewCOGFuncClassToAttach.getClass(), cogFuncClassNewCOGFuncClassToAttach.getWid());
                attachedCogFuncClassNew.add(cogFuncClassNewCOGFuncClassToAttach);
            }
            cogFuncClassNew = attachedCogFuncClassNew;
            COGOrthologousGroup.setCogFuncClass(cogFuncClassNew);
            Collection<GeneInfo> attachedGeneInfoNew = new ArrayList<GeneInfo>();
            for (GeneInfo geneInfoNewGeneInfoToAttach : geneInfoNew) {
                geneInfoNewGeneInfoToAttach = em.getReference(geneInfoNewGeneInfoToAttach.getClass(), geneInfoNewGeneInfoToAttach.getWid());
                attachedGeneInfoNew.add(geneInfoNewGeneInfoToAttach);
            }
            geneInfoNew = attachedGeneInfoNew;
            COGOrthologousGroup.setGeneInfo(geneInfoNew);
            Set<Protein> attachedProteinNew = new HashSet<Protein>();
            for (Protein proteinNewProteinToAttach : proteinNew) {
                proteinNewProteinToAttach = em.getReference(proteinNewProteinToAttach.getClass(), proteinNewProteinToAttach.getWid());
                attachedProteinNew.add(proteinNewProteinToAttach);
            }
            proteinNew = attachedProteinNew;
            COGOrthologousGroup.setProtein(proteinNew);
            COGOrthologousGroup = em.merge(COGOrthologousGroup);
            for (COGFuncClass cogFuncClassOldCOGFuncClass : cogFuncClassOld) {
                if (!cogFuncClassNew.contains(cogFuncClassOldCOGFuncClass)) {
                    cogFuncClassOldCOGFuncClass.getCogOrthologousGroup().remove(COGOrthologousGroup);
                    cogFuncClassOldCOGFuncClass = em.merge(cogFuncClassOldCOGFuncClass);
                }
            }
            for (COGFuncClass cogFuncClassNewCOGFuncClass : cogFuncClassNew) {
                if (!cogFuncClassOld.contains(cogFuncClassNewCOGFuncClass)) {
                    cogFuncClassNewCOGFuncClass.getCogOrthologousGroup().add(COGOrthologousGroup);
                    cogFuncClassNewCOGFuncClass = em.merge(cogFuncClassNewCOGFuncClass);
                }
            }
            for (GeneInfo geneInfoOldGeneInfo : geneInfoOld) {
                if (!geneInfoNew.contains(geneInfoOldGeneInfo)) {
                    geneInfoOldGeneInfo.getCogOrthologousGroup().remove(COGOrthologousGroup);
                    geneInfoOldGeneInfo = em.merge(geneInfoOldGeneInfo);
                }
            }
            for (GeneInfo geneInfoNewGeneInfo : geneInfoNew) {
                if (!geneInfoOld.contains(geneInfoNewGeneInfo)) {
                    geneInfoNewGeneInfo.getCogOrthologousGroup().add(COGOrthologousGroup);
                    geneInfoNewGeneInfo = em.merge(geneInfoNewGeneInfo);
                }
            }
            for (Protein proteinOldProtein : proteinOld) {
                if (!proteinNew.contains(proteinOldProtein)) {
                    proteinOldProtein.getCogOrthologousGroup().remove(COGOrthologousGroup);
                    proteinOldProtein = em.merge(proteinOldProtein);
                }
            }
            for (Protein proteinNewProtein : proteinNew) {
                if (!proteinOld.contains(proteinNewProtein)) {
                    proteinNewProtein.getCogOrthologousGroup().add(COGOrthologousGroup);
                    proteinNewProtein = em.merge(proteinNewProtein);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = COGOrthologousGroup.getWid();
                if (findCOGOrthologousGroup(id) == null) {
                    throw new NonexistentEntityException("The cOGOrthologousGroup with id " + id + " no longer exists.");
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
            COGOrthologousGroup COGOrthologousGroup;
            try {
                COGOrthologousGroup = em.getReference(COGOrthologousGroup.class, id);
                COGOrthologousGroup.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The COGOrthologousGroup with id " + id + " no longer exists.", enfe);
            }
            Set<COGFuncClass> cogFuncClass = COGOrthologousGroup.getCogFuncClass();
            for (COGFuncClass cogFuncClassCOGFuncClass : cogFuncClass) {
                cogFuncClassCOGFuncClass.getCogOrthologousGroup().remove(COGOrthologousGroup);
                cogFuncClassCOGFuncClass = em.merge(cogFuncClassCOGFuncClass);
            }
            Collection<GeneInfo> geneInfo = COGOrthologousGroup.getGeneInfo();
            for (GeneInfo geneInfoGeneInfo : geneInfo) {
                geneInfoGeneInfo.getCogOrthologousGroup().remove(COGOrthologousGroup);
                geneInfoGeneInfo = em.merge(geneInfoGeneInfo);
            }
            Set<Protein> protein = COGOrthologousGroup.getProtein();
            for (Protein proteinProtein : protein) {
                proteinProtein.getCogOrthologousGroup().remove(COGOrthologousGroup);
                proteinProtein = em.merge(proteinProtein);
            }
            em.remove(COGOrthologousGroup);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<COGOrthologousGroup> findCOGOrthologousGroupEntities() {
        return findCOGOrthologousGroupEntities(true, -1, -1);
    }

    public List<COGOrthologousGroup> findCOGOrthologousGroupEntities(int maxResults, int firstResult) {
        return findCOGOrthologousGroupEntities(false, maxResults, firstResult);
    }

    private List<COGOrthologousGroup> findCOGOrthologousGroupEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(COGOrthologousGroup.class));
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

    public COGOrthologousGroup findCOGOrthologousGroup(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(COGOrthologousGroup.class, id);
        } finally {
            em.close();
        }
    }

    public int getCOGOrthologousGroupCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<COGOrthologousGroup> rt = cq.from(COGOrthologousGroup.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
