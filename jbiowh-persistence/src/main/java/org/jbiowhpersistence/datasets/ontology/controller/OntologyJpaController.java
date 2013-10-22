package org.jbiowhpersistence.datasets.ontology.controller;

import java.io.Serializable;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyIsA;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyIsAPK;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyRelation;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyRelationPK;
import org.jbiowhpersistence.datasets.ontology.entities.OntologySubset;
import org.jbiowhpersistence.datasets.ontology.entities.OntologySynonym;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyToConsider;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyToConsiderPK;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyXRef;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyhasOntologySynonym;
import org.jbiowhpersistence.datasets.ontology.entities.OntologyhasOntologySynonymPK;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the Ontology Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Aug 30, 2012
 */
public class OntologyJpaController extends AbstractJpaController<Ontology> implements Serializable {

    public OntologyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ontology ontology) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + ontology.getWid());
        if (ontology.getOntologyIsA() == null) {
            ontology.setOntologyIsA(new HashMap<OntologyIsAPK, OntologyIsA>());
        }
        if (ontology.getOntologyRelation() == null) {
            ontology.setOntologyRelation(new HashMap<OntologyRelationPK, OntologyRelation>());
        }
        if (ontology.getOntologySubset() == null) {
            ontology.setOntologySubset(new HashSet<OntologySubset>());
        }
        if (ontology.getOntologyToConsider() == null) {
            ontology.setOntologyToConsider(new HashMap<OntologyToConsiderPK, OntologyToConsider>());
        }
        if (ontology.getOntologyXRef() == null) {
            ontology.setOntologyXRef(new HashSet<OntologyXRef>());
        }
        if (ontology.getOntologyhasOntologySynonym() == null) {
            ontology.setOntologyhasOntologySynonym(new HashMap<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym>());
        }
        OntologyJpaController controller = new OntologyJpaController(emf);
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!ontology.getOntologyIsA().isEmpty()) {
                Map<OntologyIsAPK, OntologyIsA> isAMap = new HashMap<>();
                for (OntologyIsA isA : ontology.getOntologyIsA().values()) {
                    OntologyIsA isAOnDB = em.find(OntologyIsA.class, isA.getOntologyIsAPK());
                    if (isAOnDB != null) {
                        isAMap.put(isAOnDB.getOntologyIsAPK(), isAOnDB);
                    } else {
                        Ontology isAOntology = em.find(Ontology.class, isA.getIsAOntology().getWid());
                        if (isAOntology == null) {
                            isAOntology = isA.getIsAOntology();
                            isAOntology.setRelationsToNull();
                            controller.create(isAOntology);
                            isA.setIsAOntology(em.getReference(Ontology.class, isA.getIsAOntology().getWid()));
                        } else {
                            isA.setIsAOntology(isAOntology);
                        }
                        isAMap.put(isA.getOntologyIsAPK(), isA);
                    }
                }
                ontology.setOntologyIsA(isAMap);
            }
            if (!ontology.getOntologyRelation().isEmpty()) {
                Map<OntologyRelationPK, OntologyRelation> relMap = new HashMap<>();
                for (OntologyRelation rel : ontology.getOntologyRelation().values()) {
                    OntologyRelation relOnDB = em.find(OntologyRelation.class, rel.getOntologyRelationPK());
                    if (relOnDB != null) {
                        relMap.put(relOnDB.getOntologyRelationPK(), relOnDB);
                    } else {
                        Ontology relOntology = em.find(Ontology.class, rel.getOtherOntology().getWid());
                        if (relOntology == null) {
                            relOntology = rel.getOtherOntology();
                            relOntology.setRelationsToNull();
                            controller.create(relOntology);
                            rel.setOtherOntology(em.getReference(Ontology.class, rel.getOtherOntology().getWid()));
                        } else {
                            rel.setOtherOntology(relOntology);
                        }
                        relMap.put(rel.getOntologyRelationPK(), rel);
                    }
                }
                ontology.setOntologyRelation(relMap);
            }
            if (!ontology.getOntologySubset().isEmpty()) {
                Set<OntologySubset> subSet = new HashSet<>();
                for (OntologySubset sub : ontology.getOntologySubset()) {
                    OntologySubset subOnDB = em.find(OntologySubset.class, sub.getWid());
                    if (subOnDB != null) {
                        subSet.add(subOnDB);
                    } else {
                        subSet.add(sub);
                    }
                }
                ontology.setOntologySubset(subSet);
            }
            if (!ontology.getOntologyToConsider().isEmpty()) {
                Map<OntologyToConsiderPK, OntologyToConsider> toMap = new HashMap<>();
                for (OntologyToConsider to : ontology.getOntologyToConsider().values()) {
                    OntologyToConsider toOnDB = em.find(OntologyToConsider.class, to.getOntologyToConsiderPK());
                    if (toOnDB != null) {
                        toMap.put(toOnDB.getOntologyToConsiderPK(), toOnDB);
                    } else {
                        Ontology toOntology = em.find(Ontology.class, to.getToConsiderOntology().getWid());
                        if (toOntology == null) {
                            toOntology = to.getToConsiderOntology();
                            toOntology.setRelationsToNull();
                            controller.create(toOntology);
                            to.setToConsiderOntology(em.getReference(Ontology.class, to.getToConsiderOntology().getWid()));
                        } else {
                            to.setToConsiderOntology(toOntology);
                        }
                        toMap.put(to.getOntologyToConsiderPK(), to);
                    }
                }
                ontology.setOntologyToConsider(toMap);
            }
            if (!ontology.getOntologyXRef().isEmpty()) {
                Set<OntologyXRef> xrefSet = new HashSet<>();
                for (OntologyXRef xref : ontology.getOntologyXRef()) {
                    OntologyXRef xrefOnDB = em.find(OntologyXRef.class, xref.getWid());
                    if (xrefOnDB != null) {
                        xrefSet.add(xrefOnDB);
                    } else {
                        xrefSet.add(xref);
                    }
                }
                ontology.setOntologyXRef(xrefSet);
            }
            if (!ontology.getOntologyhasOntologySynonym().isEmpty()) {
                OntologySynonymJpaController sController = new OntologySynonymJpaController(emf);
                Map<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym> hasSynMap = new HashMap<>();
                for (OntologyhasOntologySynonym hasSyn : ontology.getOntologyhasOntologySynonym().values()) {
                    OntologyhasOntologySynonym hasSynOnDB = em.find(OntologyhasOntologySynonym.class, hasSyn.getOntologyhasOntologySynonymPK());
                    if (hasSynOnDB != null) {
                        hasSynMap.put(hasSynOnDB.getOntologyhasOntologySynonymPK(), hasSynOnDB);
                    } else {
                        OntologySynonym syn = em.find(OntologySynonym.class, hasSyn.getOntologySynonym().getWid());
                        if (syn != null) {
                            hasSyn.setOntologySynonym(syn);
                        } else {
                            syn = hasSyn.getOntologySynonym();
                            syn.setOntologyhasOntologySynonym(null);
                            sController.create(syn);
                            hasSyn.setOntologySynonym(em.getReference(OntologySynonym.class, hasSyn.getOntologySynonym().getWid()));
                        }
                        hasSynMap.put(hasSyn.getOntologyhasOntologySynonymPK(), hasSyn);
                    }
                }
                ontology.setOntologyhasOntologySynonym(hasSynMap);
            }
            ontology.setGeneInfo(createGeneInfo(emf, em, ontology.getGeneInfo()));
            ontology.setProtein(createProtein(emf, em, ontology.getProtein()));
            ontology.setDataSet(createDataSet(emf, em, ontology.getDataSet()));
            em.persist(ontology);
            for (GeneInfo geneInfoGeneInfo : ontology.getGeneInfo()) {
                geneInfoGeneInfo.getOntology().add(ontology);
                geneInfoGeneInfo = em.merge(geneInfoGeneInfo);
            }
            for (Protein proteinProtein : ontology.getProtein()) {
                proteinProtein.getOntology().add(ontology);
                proteinProtein = em.merge(proteinProtein);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOntology(ontology.getWid()) != null) {
                throw new PreexistingEntityException("Ontology " + ontology.getWid() + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ontology ontology) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + ontology.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ontology persistentOntology = em.find(Ontology.class, ontology.getWid());
            Set<GeneInfo> geneInfoOld = persistentOntology.getGeneInfo();
            Set<GeneInfo> geneInfoNew = ontology.getGeneInfo();
            Set<Protein> proteinOld = persistentOntology.getProtein();
            Set<Protein> proteinNew = ontology.getProtein();
            Set<GeneInfo> attachedGeneInfoNew = new HashSet<>();
            for (GeneInfo geneInfoNewGeneInfoToAttach : geneInfoNew) {
                geneInfoNewGeneInfoToAttach = em.getReference(geneInfoNewGeneInfoToAttach.getClass(), geneInfoNewGeneInfoToAttach.getWid());
                attachedGeneInfoNew.add(geneInfoNewGeneInfoToAttach);
            }
            geneInfoNew = attachedGeneInfoNew;
            ontology.setGeneInfo(geneInfoNew);
            Set<Protein> attachedProteinNew = new HashSet<>();
            for (Protein proteinNewProteinToAttach : proteinNew) {
                proteinNewProteinToAttach = em.getReference(proteinNewProteinToAttach.getClass(), proteinNewProteinToAttach.getWid());
                attachedProteinNew.add(proteinNewProteinToAttach);
            }
            proteinNew = attachedProteinNew;
            ontology.setProtein(proteinNew);
            ontology = em.merge(ontology);
            for (GeneInfo geneInfoOldGeneInfo : geneInfoOld) {
                if (!geneInfoNew.contains(geneInfoOldGeneInfo)) {
                    geneInfoOldGeneInfo.getOntology().remove(ontology);
                    geneInfoOldGeneInfo = em.merge(geneInfoOldGeneInfo);
                }
            }
            for (GeneInfo geneInfoNewGeneInfo : geneInfoNew) {
                if (!geneInfoOld.contains(geneInfoNewGeneInfo)) {
                    geneInfoNewGeneInfo.getOntology().add(ontology);
                    geneInfoNewGeneInfo = em.merge(geneInfoNewGeneInfo);
                }
            }
            for (Protein proteinOldProtein : proteinOld) {
                if (!proteinNew.contains(proteinOldProtein)) {
                    proteinOldProtein.getOntology().remove(ontology);
                    proteinOldProtein = em.merge(proteinOldProtein);
                }
            }
            for (Protein proteinNewProtein : proteinNew) {
                if (!proteinOld.contains(proteinNewProtein)) {
                    proteinNewProtein.getOntology().add(ontology);
                    proteinNewProtein = em.merge(proteinNewProtein);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ontology.getWid();
                if (findOntology(id) == null) {
                    throw new NonexistentEntityException("The ontology with id " + id + " no longer exists.");
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
            Ontology ontology;
            try {
                ontology = em.getReference(Ontology.class, id);
                ontology.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ontology with id " + id + " no longer exists.", enfe);
            }
            Set<GeneInfo> geneInfo = ontology.getGeneInfo();
            for (GeneInfo geneInfoGeneInfo : geneInfo) {
                geneInfoGeneInfo.getOntology().remove(ontology);
                geneInfoGeneInfo = em.merge(geneInfoGeneInfo);
            }
            Set<Protein> protein = ontology.getProtein();
            for (Protein proteinProtein : protein) {
                proteinProtein.getOntology().remove(ontology);
                proteinProtein = em.merge(proteinProtein);
            }
            em.remove(ontology);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ontology> findOntologyEntities() {
        return findOntologyEntities(true, -1, -1);
    }

    public List<Ontology> findOntologyEntities(int maxResults, int firstResult) {
        return findOntologyEntities(false, maxResults, firstResult);
    }

    private List<Ontology> findOntologyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ontology.class));
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

    public Ontology findOntology(Long id) {
        EntityManager em = getEntityManager();




        try {
            return em.find(Ontology.class, id);
        } finally {
            em.close();
        }
    }

    public int getOntologyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ontology> rt = cq.from(Ontology.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
