package org.jbiowhpersistence.datasets.protein.controller;

import java.io.Serializable;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genome.controller.GenePTTJpaController;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMember;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protein.entities.ProteinAccessionNumber;
import org.jbiowhpersistence.datasets.protein.entities.ProteinAccessionNumberPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinBioCyc;
import org.jbiowhpersistence.datasets.protein.entities.ProteinBioCycPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinComment;
import org.jbiowhpersistence.datasets.protein.entities.ProteinDBReference;
import org.jbiowhpersistence.datasets.protein.entities.ProteinDIP;
import org.jbiowhpersistence.datasets.protein.entities.ProteinDIPPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinDrugBank;
import org.jbiowhpersistence.datasets.protein.entities.ProteinDrugBankPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinEC;
import org.jbiowhpersistence.datasets.protein.entities.ProteinECPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinFeature;
import org.jbiowhpersistence.datasets.protein.entities.ProteinIntAct;
import org.jbiowhpersistence.datasets.protein.entities.ProteinIntActPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinKEGG;
import org.jbiowhpersistence.datasets.protein.entities.ProteinKEGGPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinKeyword;
import org.jbiowhpersistence.datasets.protein.entities.ProteinLongName;
import org.jbiowhpersistence.datasets.protein.entities.ProteinMINT;
import org.jbiowhpersistence.datasets.protein.entities.ProteinMINTPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinName;
import org.jbiowhpersistence.datasets.protein.entities.ProteinNamePK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinPDB;
import org.jbiowhpersistence.datasets.protein.entities.ProteinPDBPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinPFAM;
import org.jbiowhpersistence.datasets.protein.entities.ProteinPFAMPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinPMID;
import org.jbiowhpersistence.datasets.protein.entities.ProteinPMIDPK;
import org.jbiowhpersistence.datasets.protein.entities.ProteinhasProteinKeyword;
import org.jbiowhpersistence.datasets.protein.entities.ProteinhasProteinKeywordPK;
import org.jbiowhpersistence.datasets.taxonomy.controller.TaxonomyJpaController;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the Protein Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Sep 4, 2012
 */
public class ProteinJpaController extends AbstractJpaController<Protein> implements Serializable {

    public ProteinJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Protein protein) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + protein.getWid());
        if (protein.getProteinComment() == null) {
            protein.setProteinComment(new HashSet<ProteinComment>());
        }
        if (protein.getProteinFeature() == null) {
            protein.setProteinFeature(new HashSet<ProteinFeature>());
        }
        if (protein.getProteinDBReference() == null) {
            protein.setProteinDBReference(new HashSet<ProteinDBReference>());
        }
        if (protein.getProteinLongName() == null) {
            protein.setProteinLongName(new HashSet<ProteinLongName>());
        }
        if (protein.getProteinhasProteinKeyword() == null) {
            protein.setProteinhasProteinKeyword(new HashMap<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword>());
        }
        if (protein.getProteinName() == null) {
            protein.setProteinName(new HashMap<ProteinNamePK, ProteinName>());
        }
        if (protein.getProteinAccessionNumber() == null) {
            protein.setProteinAccessionNumber(new HashMap<ProteinAccessionNumberPK, ProteinAccessionNumber>());
        }
        if (protein.getProteinPMID() == null) {
            protein.setProteinPMID(new HashMap<ProteinPMIDPK, ProteinPMID>());
        }
        if (protein.getProteinEC() == null) {
            protein.setProteinEC(new HashMap<ProteinECPK, ProteinEC>());
        }
        if (protein.getProteinKEGG() == null) {
            protein.setProteinKEGG(new HashMap<ProteinKEGGPK, ProteinKEGG>());
        }
        if (protein.getProteinBioCyc() == null) {
            protein.setProteinBioCyc(new HashMap<ProteinBioCycPK, ProteinBioCyc>());
        }
        if (protein.getProteinPDB() == null) {
            protein.setProteinPDB(new HashMap<ProteinPDBPK, ProteinPDB>());
        }
        if (protein.getProteinIntAct() == null) {
            protein.setProteinIntAct(new HashMap<ProteinIntActPK, ProteinIntAct>());
        }
        if (protein.getProteinDIP() == null) {
            protein.setProteinDIP(new HashMap<ProteinDIPPK, ProteinDIP>());
        }
        if (protein.getProteinMINT() == null) {
            protein.setProteinMINT(new HashMap<ProteinMINTPK, ProteinMINT>());
        }
        if (protein.getProteinDrugBank() == null) {
            protein.setProteinDrugBank(new HashMap<ProteinDrugBankPK, ProteinDrugBank>());
        }
        if (protein.getProteinPFAM() == null) {
            protein.setProteinPFAM(new HashMap<ProteinPFAMPK, ProteinPFAM>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!protein.getProteinFeature().isEmpty()) {
                Set<ProteinFeature> attachedProteinFeature = new HashSet<>();
                for (ProteinFeature proteinFeatureProteinFeature : protein.getProteinFeature()) {
                    ProteinFeature proteinFeatureProteinFeatureOnDB = em.find(proteinFeatureProteinFeature.getClass(), proteinFeatureProteinFeature.getWid());
                    if (proteinFeatureProteinFeatureOnDB != null) {
                        attachedProteinFeature.add(proteinFeatureProteinFeatureOnDB);
                    } else {
                        attachedProteinFeature.add(proteinFeatureProteinFeature);
                    }
                }
                protein.setProteinFeature(attachedProteinFeature);
            }
            if (!protein.getProteinDBReference().isEmpty()) {
                Set<ProteinDBReference> attachedProteinDBReference = new HashSet<>();
                for (ProteinDBReference proteinDBReferenceProteinDBReferenceToAttach : protein.getProteinDBReference()) {
                    ProteinDBReference proteinDBReferenceProteinDBReference = em.find(proteinDBReferenceProteinDBReferenceToAttach.getClass(), proteinDBReferenceProteinDBReferenceToAttach.getWid());
                    if (proteinDBReferenceProteinDBReference != null) {
                        attachedProteinDBReference.add(proteinDBReferenceProteinDBReference);
                    } else {
                        attachedProteinDBReference.add(proteinDBReferenceProteinDBReferenceToAttach);
                    }
                }
                protein.setProteinDBReference(attachedProteinDBReference);
            }
            if (!protein.getProteinLongName().isEmpty()) {
                Set<ProteinLongName> attachedProteinLongName = new HashSet<>();
                for (ProteinLongName proteinLongNameProteinLongNameToAttach : protein.getProteinLongName()) {
                    ProteinLongName proteinLongNameProteinLongName = em.find(proteinLongNameProteinLongNameToAttach.getClass(), proteinLongNameProteinLongNameToAttach.getWid());
                    if (proteinLongNameProteinLongName != null) {
                        attachedProteinLongName.add(proteinLongNameProteinLongName);
                    } else {
                        attachedProteinLongName.add(proteinLongNameProteinLongNameToAttach);
                    }
                }
                protein.setProteinLongName(attachedProteinLongName);
            }
            if (!protein.getProteinComment().isEmpty()) {
                Set<ProteinComment> attachedProteinComment = new HashSet<>();
                for (ProteinComment proteinCommentProteinCommentToAttach : protein.getProteinComment()) {
                    ProteinComment proteinCommentProteinComment = em.find(proteinCommentProteinCommentToAttach.getClass(), proteinCommentProteinCommentToAttach.getWid());
                    if (proteinCommentProteinComment != null) {
                        attachedProteinComment.add(proteinCommentProteinComment);
                    } else {
                        attachedProteinComment.add(proteinCommentProteinCommentToAttach);
                    }
                }
                protein.setProteinComment(attachedProteinComment);
            }
            if (!protein.getProteinhasProteinKeyword().isEmpty()) {
                ProteinKeywordJpaController kController = new ProteinKeywordJpaController(emf);
                Map<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword> attachProteinhasProteinKeyword = new HashMap<>();
                for (ProteinhasProteinKeyword proteinhasProteinKeywordToAttach : protein.getProteinhasProteinKeyword().values()) {
                    ProteinhasProteinKeyword proteinhasProteinKeyword = em.find(proteinhasProteinKeywordToAttach.getClass(), proteinhasProteinKeywordToAttach.getProteinhasProteinKeywordPK());
                    if (proteinhasProteinKeyword != null) {
                        attachProteinhasProteinKeyword.put(proteinhasProteinKeyword.getProteinhasProteinKeywordPK(), proteinhasProteinKeyword);
                    } else {
                        ProteinKeyword proteinKeyword = em.find(ProteinKeyword.class, proteinhasProteinKeywordToAttach.getProteinKeyword().getWid());
                        if (proteinKeyword != null) {
                            proteinhasProteinKeywordToAttach.setProteinKeyword(proteinKeyword);
                        } else {
                            proteinKeyword = proteinhasProteinKeywordToAttach.getProteinKeyword();
                            proteinKeyword.setProteinhasProteinKeyword(null);
                            kController.create(proteinKeyword);
                            proteinhasProteinKeywordToAttach.setProteinKeyword(em.find(ProteinKeyword.class, proteinKeyword.getWid()));
                        }
                        attachProteinhasProteinKeyword.put(proteinhasProteinKeywordToAttach.getProteinhasProteinKeywordPK(), proteinhasProteinKeywordToAttach);
                    }
                }
                protein.setProteinhasProteinKeyword(attachProteinhasProteinKeyword);
            }
            protein.setTaxonomy(createTaxonomy(emf, em, protein.getTaxonomy()));
            if (!protein.getTaxonomyHost().isEmpty()) {
                TaxonomyJpaController taxController = new TaxonomyJpaController(emf);
                Set<Taxonomy> attachTaxonomyHost = new HashSet<>();
                for (Taxonomy taxToAttach : protein.getTaxonomyHost()) {
                    Taxonomy tax = em.find(Taxonomy.class, taxToAttach.getWid());
                    if (tax != null) {
                        attachTaxonomyHost.add(tax);
                    } else {
                        taxController.create(taxToAttach);
                        attachTaxonomyHost.add(em.getReference(Taxonomy.class, taxToAttach.getWid()));
                    }
                }
                protein.setTaxonomyHost(attachTaxonomyHost);
            }
            GenePTT genePTT = protein.getGenePTT();            
            protein.setTaxonomy(createTaxonomy(emf, em, protein.getTaxonomy()));
            if (genePTT != null) {
                GenePTTJpaController genController = new GenePTTJpaController(emf);
                genePTT = em.find(GenePTT.class, protein.getGenePTT().getProteinGi());
                if (genePTT != null) {
                    protein.setGenePTT(genePTT);
                } else {
                    genePTT = protein.getGenePTT();
                    genePTT.setGeneInfo(null);
                    genController.create(genePTT);
                    genePTT = em.getReference(GenePTT.class, genePTT.getProteinGi());
                    protein.setGenePTT(genePTT);
                }
            }   
            protein.setOntology(createOntology(emf, em, protein.getOntology()));
            protein.setGeneInfo(createGeneInfo(emf, em, protein.getGeneInfo()));
            protein.setDrugBank(createDrugBank(emf, em, protein.getDrugBank()));
            protein.setDrugBankAsCarriers(createDrugBank(emf, em, protein.getDrugBankAsCarriers()));
            protein.setDrugBankAsTransporters(createDrugBank(emf, em, protein.getDrugBankAsTransporters()));
            protein.setDrugBankAsEnzyme(createDrugBank(emf, em, protein.getDrugBankAsEnzyme()));
            protein.setkEGGPathways(createKEGGPathway(emf, em, protein.getkEGGPathways()));
            protein.setkEGGEnzymes(createKEGGEnzyme(emf, em, protein.getkEGGEnzymes()));
            protein.setUniRefMember(null);
            protein.setUniRefEntry(createUniRefEntry(emf, em, protein.getUniRefEntry(), protein));
            protein.setmIFEntryInteraction(createMIFEntryInteraction(emf, em, protein.getmIFEntryInteraction()));
            protein.setPfamAInsignificant(createPfamA(emf, em, protein.getPfamAInsignificant()));
            protein.setPfamASignificant(createPfamA(emf, em, protein.getPfamASignificant()));
            protein.setDataSet(createDataSet(emf, em, protein.getDataSet()));
            em.persist(protein);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProtein(protein.getWid()) != null) {
                throw new PreexistingEntityException("Protein " + protein + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Protein protein) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + protein.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Protein persistentProtein = em.find(Protein.class, protein.getWid());
            GenePTT genePTTOld = persistentProtein.getGenePTT();
            GenePTT genePTTNew = protein.getGenePTT();
            Set<GeneInfo> geneInfoOld = persistentProtein.getGeneInfo();
            Set<GeneInfo> geneInfoNew = protein.getGeneInfo();
            Set<KEGGPathway> kEGGPathwaysOld = persistentProtein.getkEGGPathways();
            Set<KEGGPathway> kEGGPathwaysNew = protein.getkEGGPathways();
            Set<KEGGEnzyme> kEGGEnzymesOld = persistentProtein.getkEGGEnzymes();
            Set<KEGGEnzyme> kEGGEnzymesNew = protein.getkEGGEnzymes();
            Set<UniRefEntry> uniRefEntryOld = persistentProtein.getUniRefEntry();
            Set<UniRefEntry> uniRefEntryNew = protein.getUniRefEntry();
            Set<DrugBank> drugBankAsCarriersOld = persistentProtein.getDrugBankAsCarriers();
            Set<DrugBank> drugBankAsCarriersNew = protein.getDrugBankAsCarriers();
            Set<DrugBank> drugBankAsTransportersOld = persistentProtein.getDrugBankAsTransporters();
            Set<DrugBank> drugBankAsTransportersNew = protein.getDrugBankAsTransporters();
            Set<DrugBank> drugBankAsEnzymeOld = persistentProtein.getDrugBankAsEnzyme();
            Set<DrugBank> drugBankAsEnzymeNew = protein.getDrugBankAsEnzyme();
            Set<DrugBank> drugBankOld = persistentProtein.getDrugBank();
            Set<DrugBank> drugBankNew = protein.getDrugBank();
            Set<UniRefMember> uniRefMemberOld = persistentProtein.getUniRefMember();
            Set<UniRefMember> uniRefMemberNew = protein.getUniRefMember();
            Set<MIFEntryInteraction> mIFEntryInteractionOld = persistentProtein.getmIFEntryInteraction();
            Set<MIFEntryInteraction> mIFEntryInteractionNew = protein.getmIFEntryInteraction();
            Set<ProteinComment> proteinCommentOld = persistentProtein.getProteinComment();
            Set<ProteinComment> proteinCommentNew = protein.getProteinComment();
            Set<ProteinFeature> proteinFeatureOld = persistentProtein.getProteinFeature();
            Set<ProteinFeature> proteinFeatureNew = protein.getProteinFeature();
            Set<ProteinDBReference> proteinDBReferenceOld = persistentProtein.getProteinDBReference();
            Set<ProteinDBReference> proteinDBReferenceNew = protein.getProteinDBReference();
            Set<ProteinLongName> proteinLongNameOld = persistentProtein.getProteinLongName();
            Set<ProteinLongName> proteinLongNameNew = protein.getProteinLongName();
            Set<Ontology> ontologyOld = persistentProtein.getOntology();
            Set<Ontology> ontologyNew = protein.getOntology();
            Set<GeneInfo> attachedGeneInfoNew = new HashSet<>();
            if (genePTTNew != null) {
                genePTTNew = em.getReference(genePTTNew.getClass(), genePTTNew.getProteinGi());
                protein.setGenePTT(genePTTNew);
            }
            for (GeneInfo geneInfoNewGeneInfoToAttach : geneInfoNew) {
                geneInfoNewGeneInfoToAttach = em.getReference(geneInfoNewGeneInfoToAttach.getClass(), geneInfoNewGeneInfoToAttach.getWid());
                attachedGeneInfoNew.add(geneInfoNewGeneInfoToAttach);
            }
            geneInfoNew = attachedGeneInfoNew;
            protein.setGeneInfo(geneInfoNew);
            Set<KEGGPathway> attachedkEGGPathwaysNew = new HashSet<>();
            for (KEGGPathway kEGGPathwaysNewKEGGPathwayToAttach : kEGGPathwaysNew) {
                kEGGPathwaysNewKEGGPathwayToAttach = em.getReference(kEGGPathwaysNewKEGGPathwayToAttach.getClass(), kEGGPathwaysNewKEGGPathwayToAttach.getWid());
                attachedkEGGPathwaysNew.add(kEGGPathwaysNewKEGGPathwayToAttach);
            }
            kEGGPathwaysNew = attachedkEGGPathwaysNew;
            protein.setkEGGPathways(kEGGPathwaysNew);
            Set<KEGGEnzyme> attachedkEGGEnzymesNew = new HashSet<>();
            for (KEGGEnzyme kEGGEnzymesNewKEGGEnzymeToAttach : kEGGEnzymesNew) {
                kEGGEnzymesNewKEGGEnzymeToAttach = em.getReference(kEGGEnzymesNewKEGGEnzymeToAttach.getClass(), kEGGEnzymesNewKEGGEnzymeToAttach.getWid());
                attachedkEGGEnzymesNew.add(kEGGEnzymesNewKEGGEnzymeToAttach);
            }
            kEGGEnzymesNew = attachedkEGGEnzymesNew;
            protein.setkEGGEnzymes(kEGGEnzymesNew);
            Set<UniRefEntry> attachedUniRefEntryNew = new HashSet<>();
            for (UniRefEntry uniRefEntryNewUniRefEntryToAttach : uniRefEntryNew) {
                uniRefEntryNewUniRefEntryToAttach = em.getReference(uniRefEntryNewUniRefEntryToAttach.getClass(), uniRefEntryNewUniRefEntryToAttach.getWid());
                attachedUniRefEntryNew.add(uniRefEntryNewUniRefEntryToAttach);
            }
            uniRefEntryNew = attachedUniRefEntryNew;
            protein.setUniRefEntry(uniRefEntryNew);
            Set<DrugBank> attachedDrugBankAsCarriersNew = new HashSet<>();
            for (DrugBank drugBankAsCarriersNewDrugBankToAttach : drugBankAsCarriersNew) {
                drugBankAsCarriersNewDrugBankToAttach = em.getReference(drugBankAsCarriersNewDrugBankToAttach.getClass(), drugBankAsCarriersNewDrugBankToAttach.getWid());
                attachedDrugBankAsCarriersNew.add(drugBankAsCarriersNewDrugBankToAttach);
            }
            drugBankAsCarriersNew = attachedDrugBankAsCarriersNew;
            protein.setDrugBankAsCarriers(drugBankAsCarriersNew);
            Set<DrugBank> attachedDrugBankAsTransportersNew = new HashSet<>();
            for (DrugBank drugBankAsTransportersNewDrugBankToAttach : drugBankAsTransportersNew) {
                drugBankAsTransportersNewDrugBankToAttach = em.getReference(drugBankAsTransportersNewDrugBankToAttach.getClass(), drugBankAsTransportersNewDrugBankToAttach.getWid());
                attachedDrugBankAsTransportersNew.add(drugBankAsTransportersNewDrugBankToAttach);
            }
            drugBankAsTransportersNew = attachedDrugBankAsTransportersNew;
            protein.setDrugBankAsTransporters(drugBankAsTransportersNew);
            Set<DrugBank> attachedDrugBankAsEnzymeNew = new HashSet<>();
            for (DrugBank drugBankAsEnzymeNewDrugBankToAttach : drugBankAsEnzymeNew) {
                drugBankAsEnzymeNewDrugBankToAttach = em.getReference(drugBankAsEnzymeNewDrugBankToAttach.getClass(), drugBankAsEnzymeNewDrugBankToAttach.getWid());
                attachedDrugBankAsEnzymeNew.add(drugBankAsEnzymeNewDrugBankToAttach);
            }
            drugBankAsEnzymeNew = attachedDrugBankAsEnzymeNew;
            protein.setDrugBankAsEnzyme(drugBankAsEnzymeNew);
            Set<DrugBank> attachedDrugBankNew = new HashSet<>();
            for (DrugBank drugBankNewDrugBankToAttach : drugBankNew) {
                drugBankNewDrugBankToAttach = em.getReference(drugBankNewDrugBankToAttach.getClass(), drugBankNewDrugBankToAttach.getWid());
                attachedDrugBankNew.add(drugBankNewDrugBankToAttach);
            }
            drugBankNew = attachedDrugBankNew;
            protein.setDrugBank(drugBankNew);
            Set<UniRefMember> attachedUniRefMemberNew = new HashSet<>();
            for (UniRefMember uniRefMemberNewUniRefMemberToAttach : uniRefMemberNew) {
                uniRefMemberNewUniRefMemberToAttach = em.getReference(uniRefMemberNewUniRefMemberToAttach.getClass(), uniRefMemberNewUniRefMemberToAttach.getWid());
                attachedUniRefMemberNew.add(uniRefMemberNewUniRefMemberToAttach);
            }
            uniRefMemberNew = attachedUniRefMemberNew;
            protein.setUniRefMember(uniRefMemberNew);
            Set<MIFEntryInteraction> attachedmIFEntryInteractionNew = new HashSet<>();
            for (MIFEntryInteraction mIFEntryInteractionNewMIFEntryInteractionToAttach : mIFEntryInteractionNew) {
                mIFEntryInteractionNewMIFEntryInteractionToAttach = em.getReference(mIFEntryInteractionNewMIFEntryInteractionToAttach.getClass(), mIFEntryInteractionNewMIFEntryInteractionToAttach.getWid());
                attachedmIFEntryInteractionNew.add(mIFEntryInteractionNewMIFEntryInteractionToAttach);
            }
            mIFEntryInteractionNew = attachedmIFEntryInteractionNew;
            protein.setmIFEntryInteraction(mIFEntryInteractionNew);
            Set<ProteinComment> attachedProteinCommentNew = new HashSet<>();
            for (ProteinComment proteinCommentNewProteinCommentToAttach : proteinCommentNew) {
                proteinCommentNewProteinCommentToAttach = em.getReference(proteinCommentNewProteinCommentToAttach.getClass(), proteinCommentNewProteinCommentToAttach.getWid());
                attachedProteinCommentNew.add(proteinCommentNewProteinCommentToAttach);
            }
            proteinCommentNew = attachedProteinCommentNew;
            protein.setProteinComment(proteinCommentNew);
            Set<ProteinFeature> attachedProteinFeatureNew = new HashSet<>();
            for (ProteinFeature proteinFeatureNewProteinFeatureToAttach : proteinFeatureNew) {
                proteinFeatureNewProteinFeatureToAttach = em.getReference(proteinFeatureNewProteinFeatureToAttach.getClass(), proteinFeatureNewProteinFeatureToAttach.getWid());
                attachedProteinFeatureNew.add(proteinFeatureNewProteinFeatureToAttach);
            }
            proteinFeatureNew = attachedProteinFeatureNew;
            protein.setProteinFeature(proteinFeatureNew);
            Set<ProteinDBReference> attachedProteinDBReferenceNew = new HashSet<>();
            for (ProteinDBReference proteinDBReferenceNewProteinDBReferenceToAttach : proteinDBReferenceNew) {
                proteinDBReferenceNewProteinDBReferenceToAttach = em.getReference(proteinDBReferenceNewProteinDBReferenceToAttach.getClass(), proteinDBReferenceNewProteinDBReferenceToAttach.getWid());
                attachedProteinDBReferenceNew.add(proteinDBReferenceNewProteinDBReferenceToAttach);
            }
            proteinDBReferenceNew = attachedProteinDBReferenceNew;
            protein.setProteinDBReference(proteinDBReferenceNew);
            Set<ProteinLongName> attachedProteinLongNameNew = new HashSet<>();
            for (ProteinLongName proteinLongNameNewProteinLongNameToAttach : proteinLongNameNew) {
                proteinLongNameNewProteinLongNameToAttach = em.getReference(proteinLongNameNewProteinLongNameToAttach.getClass(), proteinLongNameNewProteinLongNameToAttach.getWid());
                attachedProteinLongNameNew.add(proteinLongNameNewProteinLongNameToAttach);
            }
            proteinLongNameNew = attachedProteinLongNameNew;
            protein.setProteinLongName(proteinLongNameNew);
            Set<Ontology> attachedOntologyNew = new HashSet<>();
            for (Ontology ontologyNewOntologyToAttach : ontologyNew) {
                ontologyNewOntologyToAttach = em.getReference(ontologyNewOntologyToAttach.getClass(), ontologyNewOntologyToAttach.getWid());
                attachedOntologyNew.add(ontologyNewOntologyToAttach);
            }
            ontologyNew = attachedOntologyNew;
            protein.setOntology(ontologyNew);            
            protein = em.merge(protein);
            if (genePTTNew != null && !genePTTNew.equals(genePTTOld)) {
                Protein oldProteinOfGenePTT = genePTTNew.getProtein();
                if (oldProteinOfGenePTT != null) {
                    oldProteinOfGenePTT.setGenePTT(null);
                    em.merge(oldProteinOfGenePTT);
                }
                genePTTNew.setProtein(protein);
                em.merge(genePTTNew);
            }
            for (GeneInfo geneInfoOldGeneInfo : geneInfoOld) {
                if (!geneInfoNew.contains(geneInfoOldGeneInfo)) {
                    geneInfoOldGeneInfo.getProtein().remove(protein);
                    geneInfoOldGeneInfo = em.merge(geneInfoOldGeneInfo);
                }
            }
            for (GeneInfo geneInfoNewGeneInfo : geneInfoNew) {
                if (!geneInfoOld.contains(geneInfoNewGeneInfo)) {
                    geneInfoNewGeneInfo.getProtein().add(protein);
                    geneInfoNewGeneInfo = em.merge(geneInfoNewGeneInfo);
                }
            }
            for (KEGGPathway kEGGPathwaysOldKEGGPathway : kEGGPathwaysOld) {
                if (!kEGGPathwaysNew.contains(kEGGPathwaysOldKEGGPathway)) {
                    kEGGPathwaysOldKEGGPathway.getProtein().remove(protein);
                    kEGGPathwaysOldKEGGPathway = em.merge(kEGGPathwaysOldKEGGPathway);
                }
            }
            for (KEGGPathway kEGGPathwaysNewKEGGPathway : kEGGPathwaysNew) {
                if (!kEGGPathwaysOld.contains(kEGGPathwaysNewKEGGPathway)) {
                    kEGGPathwaysNewKEGGPathway.getProtein().add(protein);
                    kEGGPathwaysNewKEGGPathway = em.merge(kEGGPathwaysNewKEGGPathway);
                }
            }
            for (KEGGEnzyme kEGGEnzymesOldKEGGEnzyme : kEGGEnzymesOld) {
                if (!kEGGEnzymesNew.contains(kEGGEnzymesOldKEGGEnzyme)) {
                    kEGGEnzymesOldKEGGEnzyme.getProtein().remove(protein);
                    kEGGEnzymesOldKEGGEnzyme = em.merge(kEGGEnzymesOldKEGGEnzyme);
                }
            }
            for (KEGGEnzyme kEGGEnzymesNewKEGGEnzyme : kEGGEnzymesNew) {
                if (!kEGGEnzymesOld.contains(kEGGEnzymesNewKEGGEnzyme)) {
                    kEGGEnzymesNewKEGGEnzyme.getProtein().add(protein);
                    kEGGEnzymesNewKEGGEnzyme = em.merge(kEGGEnzymesNewKEGGEnzyme);
                }
            }
            for (UniRefEntry uniRefEntryOldUniRefEntry : uniRefEntryOld) {
                if (!uniRefEntryNew.contains(uniRefEntryOldUniRefEntry)) {
                    uniRefEntryOldUniRefEntry.getProtein().remove(protein);
                    uniRefEntryOldUniRefEntry = em.merge(uniRefEntryOldUniRefEntry);
                }
            }
            for (UniRefEntry uniRefEntryNewUniRefEntry : uniRefEntryNew) {
                if (!uniRefEntryOld.contains(uniRefEntryNewUniRefEntry)) {
                    uniRefEntryNewUniRefEntry.getProtein().add(protein);
                    uniRefEntryNewUniRefEntry = em.merge(uniRefEntryNewUniRefEntry);
                }
            }
            for (DrugBank drugBankAsCarriersOldDrugBank : drugBankAsCarriersOld) {
                if (!drugBankAsCarriersNew.contains(drugBankAsCarriersOldDrugBank)) {
                    drugBankAsCarriersOldDrugBank.getProteinAsCarriers().remove(protein);
                    drugBankAsCarriersOldDrugBank = em.merge(drugBankAsCarriersOldDrugBank);
                }
            }
            for (DrugBank drugBankAsCarriersNewDrugBank : drugBankAsCarriersNew) {
                if (!drugBankAsCarriersOld.contains(drugBankAsCarriersNewDrugBank)) {
                    drugBankAsCarriersNewDrugBank.getProteinAsCarriers().add(protein);
                    drugBankAsCarriersNewDrugBank = em.merge(drugBankAsCarriersNewDrugBank);
                }
            }
            for (DrugBank drugBankAsTransportersOldDrugBank : drugBankAsTransportersOld) {
                if (!drugBankAsTransportersNew.contains(drugBankAsTransportersOldDrugBank)) {
                    drugBankAsTransportersOldDrugBank.getProteinAsCarriers().remove(protein);
                    drugBankAsTransportersOldDrugBank = em.merge(drugBankAsTransportersOldDrugBank);
                }
            }
            for (DrugBank drugBankAsTransportersNewDrugBank : drugBankAsTransportersNew) {
                if (!drugBankAsTransportersOld.contains(drugBankAsTransportersNewDrugBank)) {
                    drugBankAsTransportersNewDrugBank.getProteinAsCarriers().add(protein);
                    drugBankAsTransportersNewDrugBank = em.merge(drugBankAsTransportersNewDrugBank);
                }
            }
            for (DrugBank drugBankAsEnzymeOldDrugBank : drugBankAsEnzymeOld) {
                if (!drugBankAsEnzymeNew.contains(drugBankAsEnzymeOldDrugBank)) {
                    drugBankAsEnzymeOldDrugBank.getProteinAsCarriers().remove(protein);
                    drugBankAsEnzymeOldDrugBank = em.merge(drugBankAsEnzymeOldDrugBank);
                }
            }
            for (DrugBank drugBankAsEnzymeNewDrugBank : drugBankAsEnzymeNew) {
                if (!drugBankAsEnzymeOld.contains(drugBankAsEnzymeNewDrugBank)) {
                    drugBankAsEnzymeNewDrugBank.getProteinAsCarriers().add(protein);
                    drugBankAsEnzymeNewDrugBank = em.merge(drugBankAsEnzymeNewDrugBank);
                }
            }
            for (DrugBank drugBankOldDrugBank : drugBankOld) {
                if (!drugBankNew.contains(drugBankOldDrugBank)) {
                    drugBankOldDrugBank.getProteinAsCarriers().remove(protein);
                    drugBankOldDrugBank = em.merge(drugBankOldDrugBank);
                }
            }
            for (DrugBank drugBankNewDrugBank : drugBankNew) {
                if (!drugBankOld.contains(drugBankNewDrugBank)) {
                    drugBankNewDrugBank.getProteinAsCarriers().add(protein);
                    drugBankNewDrugBank = em.merge(drugBankNewDrugBank);
                }
            }
            for (UniRefMember uniRefMemberOldUniRefMember : uniRefMemberOld) {
                if (!uniRefMemberNew.contains(uniRefMemberOldUniRefMember)) {
                    uniRefMemberOldUniRefMember.setProtein(null);
                    uniRefMemberOldUniRefMember = em.merge(uniRefMemberOldUniRefMember);
                }
            }
            for (UniRefMember uniRefMemberNewUniRefMember : uniRefMemberNew) {
                if (!uniRefMemberOld.contains(uniRefMemberNewUniRefMember)) {
                    Protein oldProteinOfUniRefMemberNewUniRefMember = uniRefMemberNewUniRefMember.getProtein();
                    uniRefMemberNewUniRefMember.setProtein(protein);
                    uniRefMemberNewUniRefMember = em.merge(uniRefMemberNewUniRefMember);
                    if (oldProteinOfUniRefMemberNewUniRefMember != null && !oldProteinOfUniRefMemberNewUniRefMember.equals(protein)) {
                        oldProteinOfUniRefMemberNewUniRefMember.getUniRefMember().remove(uniRefMemberNewUniRefMember);
                        em.merge(oldProteinOfUniRefMemberNewUniRefMember);
                    }
                }
            }
            for (MIFEntryInteraction mIFEntryInteractionOldMIFEntryInteraction : mIFEntryInteractionOld) {
                if (!mIFEntryInteractionNew.contains(mIFEntryInteractionOldMIFEntryInteraction)) {
                    mIFEntryInteractionOldMIFEntryInteraction.getProtein().remove(protein);
                    mIFEntryInteractionOldMIFEntryInteraction = em.merge(mIFEntryInteractionOldMIFEntryInteraction);
                }
            }
            for (MIFEntryInteraction mIFEntryInteractionNewMIFEntryInteraction : mIFEntryInteractionNew) {
                if (!mIFEntryInteractionOld.contains(mIFEntryInteractionNewMIFEntryInteraction)) {
                    mIFEntryInteractionNewMIFEntryInteraction.getProtein().add(protein);
                    mIFEntryInteractionNewMIFEntryInteraction = em.merge(mIFEntryInteractionNewMIFEntryInteraction);
                }
            }
            for (ProteinComment proteinCommentOldProteinComment : proteinCommentOld) {
                if (!proteinCommentNew.contains(proteinCommentOldProteinComment)) {
                    proteinCommentOldProteinComment.setProtein(null);
                    proteinCommentOldProteinComment = em.merge(proteinCommentOldProteinComment);
                }
            }
            for (ProteinComment proteinCommentNewProteinComment : proteinCommentNew) {
                if (!proteinCommentOld.contains(proteinCommentNewProteinComment)) {
                    Protein oldProteinOfProteinCommentNewProteinComment = proteinCommentNewProteinComment.getProtein();
                    proteinCommentNewProteinComment.setProtein(protein);
                    proteinCommentNewProteinComment = em.merge(proteinCommentNewProteinComment);
                    if (oldProteinOfProteinCommentNewProteinComment != null && !oldProteinOfProteinCommentNewProteinComment.equals(protein)) {
                        oldProteinOfProteinCommentNewProteinComment.getProteinComment().remove(proteinCommentNewProteinComment);
                        em.merge(oldProteinOfProteinCommentNewProteinComment);
                    }
                }
            }
            for (ProteinFeature proteinFeatureOldProteinFeature : proteinFeatureOld) {
                if (!proteinFeatureNew.contains(proteinFeatureOldProteinFeature)) {
                    proteinFeatureOldProteinFeature.setProtein(null);
                    proteinFeatureOldProteinFeature = em.merge(proteinFeatureOldProteinFeature);
                }
            }
            for (ProteinFeature proteinFeatureNewProteinFeature : proteinFeatureNew) {
                if (!proteinFeatureOld.contains(proteinFeatureNewProteinFeature)) {
                    Protein oldProteinOfProteinFeatureNewProteinFeature = proteinFeatureNewProteinFeature.getProtein();
                    proteinFeatureNewProteinFeature.setProtein(protein);
                    proteinFeatureNewProteinFeature = em.merge(proteinFeatureNewProteinFeature);
                    if (oldProteinOfProteinFeatureNewProteinFeature != null && !oldProteinOfProteinFeatureNewProteinFeature.equals(protein)) {
                        oldProteinOfProteinFeatureNewProteinFeature.getProteinFeature().remove(proteinFeatureNewProteinFeature);
                        em.merge(oldProteinOfProteinFeatureNewProteinFeature);
                    }
                }
            }
            for (ProteinDBReference proteinDBReferenceOldProteinDBReference : proteinDBReferenceOld) {
                if (!proteinDBReferenceNew.contains(proteinDBReferenceOldProteinDBReference)) {
                    proteinDBReferenceOldProteinDBReference.setProtein(null);
                    proteinDBReferenceOldProteinDBReference = em.merge(proteinDBReferenceOldProteinDBReference);
                }
            }
            for (ProteinDBReference proteinDBReferenceNewProteinDBReference : proteinDBReferenceNew) {
                if (!proteinDBReferenceOld.contains(proteinDBReferenceNewProteinDBReference)) {
                    Protein oldProteinOfProteinDBReferenceNewProteinDBReference = proteinDBReferenceNewProteinDBReference.getProtein();
                    proteinDBReferenceNewProteinDBReference.setProtein(protein);
                    proteinDBReferenceNewProteinDBReference = em.merge(proteinDBReferenceNewProteinDBReference);
                    if (oldProteinOfProteinDBReferenceNewProteinDBReference != null && !oldProteinOfProteinDBReferenceNewProteinDBReference.equals(protein)) {
                        oldProteinOfProteinDBReferenceNewProteinDBReference.getProteinDBReference().remove(proteinDBReferenceNewProteinDBReference);
                        em.merge(oldProteinOfProteinDBReferenceNewProteinDBReference);
                    }
                }
            }
            for (ProteinLongName proteinLongNameOldProteinLongName : proteinLongNameOld) {
                if (!proteinLongNameNew.contains(proteinLongNameOldProteinLongName)) {
                    proteinLongNameOldProteinLongName.setProtein(null);
                    proteinLongNameOldProteinLongName = em.merge(proteinLongNameOldProteinLongName);
                }
            }
            for (ProteinLongName proteinLongNameNewProteinLongName : proteinLongNameNew) {
                if (!proteinLongNameOld.contains(proteinLongNameNewProteinLongName)) {
                    Protein oldProteinOfProteinLongNameNewProteinLongName = proteinLongNameNewProteinLongName.getProtein();
                    proteinLongNameNewProteinLongName.setProtein(protein);
                    proteinLongNameNewProteinLongName = em.merge(proteinLongNameNewProteinLongName);
                    if (oldProteinOfProteinLongNameNewProteinLongName != null && !oldProteinOfProteinLongNameNewProteinLongName.equals(protein)) {
                        oldProteinOfProteinLongNameNewProteinLongName.getProteinLongName().remove(proteinLongNameNewProteinLongName);
                        em.merge(oldProteinOfProteinLongNameNewProteinLongName);
                    }
                }
            }
            for (Ontology ontologyOldOntology : ontologyOld) {
                if (!ontologyNew.contains(ontologyOldOntology)) {
                    ontologyOldOntology.getProtein().remove(protein);
                    ontologyOldOntology = em.merge(ontologyOldOntology);
                }
            }
            for (Ontology ontologyNewOntology : ontologyNew) {
                if (!ontologyOld.contains(ontologyNewOntology)) {
                    ontologyNewOntology.getProtein().add(protein);
                    ontologyNewOntology = em.merge(ontologyNewOntology);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = protein.getWid();
                if (findProtein(id) == null) {
                    throw new NonexistentEntityException("The protein with id " + id + " no longer exists.");
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
            Protein protein;
            try {
                protein = em.getReference(Protein.class, id);
                protein.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The protein with id " + id + " no longer exists.", enfe);
            }
            GenePTT genePTT = protein.getGenePTT();
            if (genePTT != null) {
                genePTT.setProtein(null);
                em.merge(genePTT);
            }
            Set<GeneInfo> geneInfo = protein.getGeneInfo();
            for (GeneInfo geneInfoGeneInfo : geneInfo) {
                geneInfoGeneInfo.getProtein().remove(protein);
                geneInfoGeneInfo = em.merge(geneInfoGeneInfo);
            }
            Set<KEGGPathway> kEGGPathways = protein.getkEGGPathways();
            for (KEGGPathway kEGGPathwaysKEGGPathway : kEGGPathways) {
                kEGGPathwaysKEGGPathway.getProtein().remove(protein);
                kEGGPathwaysKEGGPathway = em.merge(kEGGPathwaysKEGGPathway);
            }
            Set<KEGGEnzyme> kEGGEnzymes = protein.getkEGGEnzymes();
            for (KEGGEnzyme kEGGEnzymesKEGGEnzyme : kEGGEnzymes) {
                kEGGEnzymesKEGGEnzyme.getProtein().remove(protein);
                kEGGEnzymesKEGGEnzyme = em.merge(kEGGEnzymesKEGGEnzyme);
            }
            Set<UniRefEntry> uniRefEntry = protein.getUniRefEntry();
            for (UniRefEntry uniRefEntryUniRefEntry : uniRefEntry) {
                uniRefEntryUniRefEntry.getProtein().remove(protein);
                uniRefEntryUniRefEntry = em.merge(uniRefEntryUniRefEntry);
            }
            Set<DrugBank> drugBankAsCarriers = protein.getDrugBankAsCarriers();
            for (DrugBank drugBankAsCarriersDrugBank : drugBankAsCarriers) {
                drugBankAsCarriersDrugBank.getProteinAsCarriers().remove(protein);
                drugBankAsCarriersDrugBank = em.merge(drugBankAsCarriersDrugBank);
            }
            Set<DrugBank> drugBankAsTransporters = protein.getDrugBankAsTransporters();
            for (DrugBank drugBankAsTransportersDrugBank : drugBankAsTransporters) {
                drugBankAsTransportersDrugBank.getProteinAsCarriers().remove(protein);
                drugBankAsTransportersDrugBank = em.merge(drugBankAsTransportersDrugBank);
            }
            Set<DrugBank> drugBankAsEnzyme = protein.getDrugBankAsEnzyme();
            for (DrugBank drugBankAsEnzymeDrugBank : drugBankAsEnzyme) {
                drugBankAsEnzymeDrugBank.getProteinAsCarriers().remove(protein);
                drugBankAsEnzymeDrugBank = em.merge(drugBankAsEnzymeDrugBank);
            }
            Set<DrugBank> drugBank = protein.getDrugBank();
            for (DrugBank drugBankDrugBank : drugBank) {
                drugBankDrugBank.getProteinAsCarriers().remove(protein);
                drugBankDrugBank = em.merge(drugBankDrugBank);
            }
            Set<UniRefMember> uniRefMember = protein.getUniRefMember();
            for (UniRefMember uniRefMemberUniRefMember : uniRefMember) {
                uniRefMemberUniRefMember.setProtein(null);
                uniRefMemberUniRefMember = em.merge(uniRefMemberUniRefMember);
            }
            Set<MIFEntryInteraction> mIFEntryInteraction = protein.getmIFEntryInteraction();
            for (MIFEntryInteraction mIFEntryInteractionMIFEntryInteraction : mIFEntryInteraction) {
                mIFEntryInteractionMIFEntryInteraction.getProtein().remove(protein);
                mIFEntryInteractionMIFEntryInteraction = em.merge(mIFEntryInteractionMIFEntryInteraction);
            }
            Set<ProteinComment> proteinComment = protein.getProteinComment();
            for (ProteinComment proteinCommentProteinComment : proteinComment) {
                proteinCommentProteinComment.setProtein(null);
                proteinCommentProteinComment = em.merge(proteinCommentProteinComment);
            }
            Set<ProteinFeature> proteinFeature = protein.getProteinFeature();
            for (ProteinFeature proteinFeatureProteinFeature : proteinFeature) {
                proteinFeatureProteinFeature.setProtein(null);
                proteinFeatureProteinFeature = em.merge(proteinFeatureProteinFeature);
            }
            Set<ProteinDBReference> proteinDBReference = protein.getProteinDBReference();
            for (ProteinDBReference proteinDBReferenceProteinDBReference : proteinDBReference) {
                proteinDBReferenceProteinDBReference.setProtein(null);
                proteinDBReferenceProteinDBReference = em.merge(proteinDBReferenceProteinDBReference);
            }
            Set<ProteinLongName> proteinLongName = protein.getProteinLongName();
            for (ProteinLongName proteinLongNameProteinLongName : proteinLongName) {
                proteinLongNameProteinLongName.setProtein(null);
                proteinLongNameProteinLongName = em.merge(proteinLongNameProteinLongName);
            }
            Set<Ontology> ontology = protein.getOntology();
            for (Ontology ontologyOntology : ontology) {
                ontologyOntology.getProtein().remove(protein);
                ontologyOntology = em.merge(ontologyOntology);
            }
            em.remove(protein);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Protein> findProteinEntities() {
        return findProteinEntities(true, -1, -1);
    }

    public List<Protein> findProteinEntities(int maxResults, int firstResult) {
        return findProteinEntities(false, maxResults, firstResult);
    }

    private List<Protein> findProteinEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Protein.class));
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

    /**
     * Find by primary key. Search for an entity of the specified class and
     * primary key. If the entity instance is contained in the persistence
     * context, it is returned from there.
     *
     * @param id the Protein WID
     * @return the protein
     */
    public Protein findProtein(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Protein.class, id);
        } finally {
            em.close();
        }
    }

    public int getProteinCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Protein> rt = cq.from(Protein.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
