package org.jbiowhpersistence.datasets.gene.gene.controller;

import java.io.Serializable;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.disease.omim.controller.OMIMJpaController;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.gene.gene.entities.Gene2Ensembl;
import org.jbiowhpersistence.datasets.gene.gene.entities.Gene2GenomicNucleotide;
import org.jbiowhpersistence.datasets.gene.gene.entities.Gene2PMID;
import org.jbiowhpersistence.datasets.gene.gene.entities.Gene2ProteinAccession;
import org.jbiowhpersistence.datasets.gene.gene.entities.Gene2RNANucleotide;
import org.jbiowhpersistence.datasets.gene.gene.entities.Gene2STS;
import org.jbiowhpersistence.datasets.gene.gene.entities.Gene2UniGene;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneGroup;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfoDBXrefs;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfoSynonyms;
import org.jbiowhpersistence.datasets.gene.genome.controller.GenePTTJpaController;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.IllegalOrphanException;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the GeneInfo Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Aug 30, 2012
 */
public class GeneInfoJpaController extends AbstractJpaController<GeneInfo> implements Serializable {

    public GeneInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GeneInfo geneInfo) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + geneInfo.getWid());
        if (geneInfo.getGeneInfoSynonym() == null) {
            geneInfo.setGeneInfoSynonym(new ArrayList<GeneInfoSynonyms>());
        }
        if (geneInfo.getGeneInfoDBXref() == null) {
            geneInfo.setGeneInfoDBXref(new ArrayList<GeneInfoDBXrefs>());
        }
        if (geneInfo.getGeneGroup() == null) {
            geneInfo.setGeneGroup(new ArrayList<GeneGroup>());
        }
        if (geneInfo.getGene2Ensembl() == null) {
            geneInfo.setGene2Ensembl(new HashSet<Gene2Ensembl>());
        }
        if (geneInfo.getGene2PMID() == null) {
            geneInfo.setGene2PMID(new ArrayList<Gene2PMID>());
        }
        if (geneInfo.getGene2STS() == null) {
            geneInfo.setGene2STS(new ArrayList<Gene2STS>());
        }
        if (geneInfo.getGene2UniGene() == null) {
            geneInfo.setGene2UniGene(new ArrayList<Gene2UniGene>());
        }
        if (geneInfo.getGene2GenomicNucleotide() == null) {
            geneInfo.setGene2GenomicNucleotide(new ArrayList<Gene2GenomicNucleotide>());
        }
        if (geneInfo.getGene2ProteinAccession() == null) {
            geneInfo.setGene2ProteinAccession(new ArrayList<Gene2ProteinAccession>());
        }
        if (geneInfo.getGene2RNANucleotide() == null) {
            geneInfo.setGene2RNANucleotide(new ArrayList<Gene2RNANucleotide>());
        }
        if (geneInfo.getOmim() == null) {
            geneInfo.setOmim(new HashSet<OMIM>());
        }
        EntityManager em = null;
        try {
            GenePTT genePTT = geneInfo.getGenePTT();
            em = getEntityManager();
            em.getTransaction().begin();
            geneInfo.setTaxonomy(createTaxonomy(emf, em, geneInfo.getTaxonomy()));
            if (genePTT != null) {
                GenePTTJpaController genController = new GenePTTJpaController(emf);
                genePTT = em.find(GenePTT.class, geneInfo.getGenePTT().getProteinGi());
                if (genePTT != null) {
                    geneInfo.setGenePTT(genePTT);
                } else {
                    genePTT = geneInfo.getGenePTT();
                    genePTT.setGeneInfo(null);
                    genController.create(genePTT);
                    genePTT = em.getReference(GenePTT.class, genePTT.getProteinGi());
                    geneInfo.setGenePTT(genePTT);
                }
            }
            if (!geneInfo.getOmim().isEmpty()) {
                OMIMJpaController mController = new OMIMJpaController(emf);
                Set<OMIM> objSet = new HashSet();
                for (OMIM object : geneInfo.getOmim()) {
                    OMIM objectOnDB = em.find(OMIM.class, object.getWid());
                    if (objectOnDB != null) {
                        objSet.add(objectOnDB);
                    } else {
                        mController.create(object);
                        objSet.add(em.getReference(OMIM.class, object.getWid()));
                    }
                }
                geneInfo.setOmim(objSet);
            }
            geneInfo.setOntology(createOntology(emf, em, geneInfo.getOntology()));
            geneInfo.setProtein(createProtein(emf, em, geneInfo.getProtein()));
            geneInfo.setkEGGGenes(createKEGGGene(emf, em, geneInfo.getkEGGGenes()));
            geneInfo.setkEGGPathways(createKEGGPathway(emf, em, geneInfo.getkEGGPathways()));
            geneInfo.setDataSet(createDataSet(emf, em, geneInfo.getDataSet()));
            em.persist(geneInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGeneInfo(geneInfo.getWid()) != null) {
                throw new PreexistingEntityException("GeneInfo " + geneInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GeneInfo geneInfo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + geneInfo.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneInfo persistentGeneInfo = em.find(GeneInfo.class, geneInfo.getWid());
            GenePTT genePTTOld = persistentGeneInfo.getGenePTT();
            GenePTT genePTTNew = geneInfo.getGenePTT();
            Set<Ontology> ontologyOld = persistentGeneInfo.getOntology();
            Set<Ontology> ontologyNew = geneInfo.getOntology();
            Set<OMIM> omimOld = persistentGeneInfo.getOmim();
            Set<OMIM> omimNew = geneInfo.getOmim();
            Set<KEGGPathway> kEGGPathwaysOld = persistentGeneInfo.getkEGGPathways();
            Set<KEGGPathway> kEGGPathwaysNew = geneInfo.getkEGGPathways();
            Set<KEGGGene> kEGGGenesOld = persistentGeneInfo.getkEGGGenes();
            Set<KEGGGene> kEGGGenesNew = geneInfo.getkEGGGenes();
            Set<Protein> proteinOld = persistentGeneInfo.getProtein();
            Set<Protein> proteinNew = geneInfo.getProtein();
            if (genePTTNew != null) {
                genePTTNew = em.getReference(genePTTNew.getClass(), genePTTNew.getProteinGi());
                geneInfo.setGenePTT(genePTTNew);
            }
            Set<Ontology> attachedOntologyNew = new HashSet();
            for (Ontology ontologyNewOntologyToAttach : ontologyNew) {
                ontologyNewOntologyToAttach = em.getReference(ontologyNewOntologyToAttach.getClass(), ontologyNewOntologyToAttach.getWid());
                attachedOntologyNew.add(ontologyNewOntologyToAttach);
            }
            ontologyNew = attachedOntologyNew;
            geneInfo.setOntology(ontologyNew);
            Set<OMIM> attachedOmimNew = new HashSet();
            for (OMIM omimNewOMIMToAttach : omimNew) {
                omimNewOMIMToAttach = em.getReference(omimNewOMIMToAttach.getClass(), omimNewOMIMToAttach.getWid());
                attachedOmimNew.add(omimNewOMIMToAttach);
            }
            omimNew = attachedOmimNew;
            geneInfo.setOmim(omimNew);
            Set<KEGGPathway> attachedkEGGPathwaysNew = new HashSet();
            for (KEGGPathway kEGGPathwaysNewKEGGPathwayToAttach : kEGGPathwaysNew) {
                kEGGPathwaysNewKEGGPathwayToAttach = em.getReference(kEGGPathwaysNewKEGGPathwayToAttach.getClass(), kEGGPathwaysNewKEGGPathwayToAttach.getWid());
                attachedkEGGPathwaysNew.add(kEGGPathwaysNewKEGGPathwayToAttach);
            }
            kEGGPathwaysNew = attachedkEGGPathwaysNew;
            geneInfo.setkEGGPathways(kEGGPathwaysNew);
            Set<KEGGGene> attachedkEGGGenesNew = new HashSet();
            for (KEGGGene kEGGGenesNewKEGGGeneToAttach : kEGGGenesNew) {
                kEGGGenesNewKEGGGeneToAttach = em.getReference(kEGGGenesNewKEGGGeneToAttach.getClass(), kEGGGenesNewKEGGGeneToAttach.getWid());
                attachedkEGGGenesNew.add(kEGGGenesNewKEGGGeneToAttach);
            }
            kEGGGenesNew = attachedkEGGGenesNew;
            geneInfo.setkEGGGenes(kEGGGenesNew);
            Set<Protein> attachedProteinNew = new HashSet();
            for (Protein proteinNewProteinToAttach : proteinNew) {
                proteinNewProteinToAttach = em.getReference(proteinNewProteinToAttach.getClass(), proteinNewProteinToAttach.getWid());
                attachedProteinNew.add(proteinNewProteinToAttach);
            }
            proteinNew = attachedProteinNew;
            geneInfo.setProtein(proteinNew);
            geneInfo = em.merge(geneInfo);
            if (genePTTOld != null && !genePTTOld.equals(genePTTNew)) {
                genePTTOld.setGeneInfo(null);
                genePTTOld = em.merge(genePTTOld);
            }
            if (genePTTNew != null && !genePTTNew.equals(genePTTOld)) {
                GeneInfo oldGeneInfoOfGenePTT = genePTTNew.getGeneInfo();
                if (oldGeneInfoOfGenePTT != null) {
                    oldGeneInfoOfGenePTT.setGenePTT(null);
                    em.merge(oldGeneInfoOfGenePTT);
                }
                genePTTNew.setGeneInfo(geneInfo);
                em.merge(genePTTNew);
            }
            for (Ontology ontologyOldOntology : ontologyOld) {
                if (!ontologyNew.contains(ontologyOldOntology)) {
                    ontologyOldOntology.getGeneInfo().remove(geneInfo);
                    ontologyOldOntology = em.merge(ontologyOldOntology);
                }
            }
            for (Ontology ontologyNewOntology : ontologyNew) {
                if (!ontologyOld.contains(ontologyNewOntology)) {
                    ontologyNewOntology.getGeneInfo().add(geneInfo);
                    ontologyNewOntology = em.merge(ontologyNewOntology);
                }
            }
            for (OMIM omimOldOMIM : omimOld) {
                if (!omimNew.contains(omimOldOMIM)) {
                    omimOldOMIM.getGeneInfo().remove(geneInfo);
                    omimOldOMIM = em.merge(omimOldOMIM);
                }
            }
            for (OMIM omimNewOMIM : omimNew) {
                if (!omimOld.contains(omimNewOMIM)) {
                    omimNewOMIM.getGeneInfo().add(geneInfo);
                    omimNewOMIM = em.merge(omimNewOMIM);
                }
            }
            for (KEGGPathway kEGGPathwaysOldKEGGPathway : kEGGPathwaysOld) {
                if (!kEGGPathwaysNew.contains(kEGGPathwaysOldKEGGPathway)) {
                    kEGGPathwaysOldKEGGPathway.getGeneInfo().remove(geneInfo);
                    kEGGPathwaysOldKEGGPathway = em.merge(kEGGPathwaysOldKEGGPathway);
                }
            }
            for (KEGGPathway kEGGPathwaysNewKEGGPathway : kEGGPathwaysNew) {
                if (!kEGGPathwaysOld.contains(kEGGPathwaysNewKEGGPathway)) {
                    kEGGPathwaysNewKEGGPathway.getGeneInfo().add(geneInfo);
                    kEGGPathwaysNewKEGGPathway = em.merge(kEGGPathwaysNewKEGGPathway);
                }
            }
            for (KEGGGene kEGGGenesOldKEGGGene : kEGGGenesOld) {
                if (!kEGGGenesNew.contains(kEGGGenesOldKEGGGene)) {
                    kEGGGenesOldKEGGGene.getGeneInfos().remove(geneInfo);
                    kEGGGenesOldKEGGGene = em.merge(kEGGGenesOldKEGGGene);
                }
            }
            for (KEGGGene kEGGGenesNewKEGGGene : kEGGGenesNew) {
                if (!kEGGGenesOld.contains(kEGGGenesNewKEGGGene)) {
                    kEGGGenesNewKEGGGene.getGeneInfos().add(geneInfo);
                    kEGGGenesNewKEGGGene = em.merge(kEGGGenesNewKEGGGene);
                }
            }
            for (Protein proteinOldProtein : proteinOld) {
                if (!proteinNew.contains(proteinOldProtein)) {
                    proteinOldProtein.getGeneInfo().remove(geneInfo);
                    proteinOldProtein = em.merge(proteinOldProtein);
                }
            }
            for (Protein proteinNewProtein : proteinNew) {
                if (!proteinOld.contains(proteinNewProtein)) {
                    proteinNewProtein.getGeneInfo().add(geneInfo);
                    proteinNewProtein = em.merge(proteinNewProtein);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = geneInfo.getWid();
                if (findGeneInfo(id) == null) {
                    throw new NonexistentEntityException("The geneInfo with id " + id + " no longer exists.");
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
            GeneInfo geneInfo;
            try {
                geneInfo = em.getReference(GeneInfo.class, id);
                geneInfo.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The geneInfo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Gene2Ensembl> gene2EnsemblOrphanCheck = geneInfo.getGene2Ensembl();
            for (Gene2Ensembl gene2EnsemblOrphanCheckGene2Ensembl : gene2EnsemblOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList();
                }
                illegalOrphanMessages.add("This GeneInfo (" + geneInfo + ") cannot be destroyed since the Gene2Ensembl " + gene2EnsemblOrphanCheckGene2Ensembl + " in its gene2Ensembl field has a non-nullable geneInfo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            GenePTT genePTT = geneInfo.getGenePTT();
            if (genePTT != null) {
                genePTT.setGeneInfo(null);
                em.merge(genePTT);
            }
            Set<Ontology> ontology = geneInfo.getOntology();
            for (Ontology ontologyOntology : ontology) {
                ontologyOntology.getGeneInfo().remove(geneInfo);
                ontologyOntology = em.merge(ontologyOntology);
            }
            Set<OMIM> omim = geneInfo.getOmim();
            for (OMIM omimOMIM : omim) {
                omimOMIM.getGeneInfo().remove(geneInfo);
                omimOMIM = em.merge(omimOMIM);
            }
            Set<KEGGPathway> kEGGPathways = geneInfo.getkEGGPathways();
            for (KEGGPathway kEGGPathwaysKEGGPathway : kEGGPathways) {
                kEGGPathwaysKEGGPathway.getGeneInfo().remove(geneInfo);
                kEGGPathwaysKEGGPathway = em.merge(kEGGPathwaysKEGGPathway);
            }
            Set<KEGGGene> kEGGGenes = geneInfo.getkEGGGenes();
            for (KEGGGene kEGGGenesKEGGGene : kEGGGenes) {
                kEGGGenesKEGGGene.getGeneInfos().remove(geneInfo);
                kEGGGenesKEGGGene = em.merge(kEGGGenesKEGGGene);
            }
            Set<Protein> protein = geneInfo.getProtein();
            for (Protein proteinProtein : protein) {
                proteinProtein.getGeneInfo().remove(geneInfo);
                proteinProtein = em.merge(proteinProtein);
            }
            em.remove(geneInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GeneInfo> findGeneInfoEntities() {
        return findGeneInfoEntities(true, -1, -1);
    }

    public List<GeneInfo> findGeneInfoEntities(int maxResults, int firstResult) {
        return findGeneInfoEntities(false, maxResults, firstResult);
    }

    private List<GeneInfo> findGeneInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GeneInfo.class));
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

    public GeneInfo findGeneInfo(Long id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(GeneInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GeneInfo> rt = cq.from(GeneInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
