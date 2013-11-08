package org.jbiowhpersistence.datasets.ppi.controller;

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
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteractor;
import org.jbiowhpersistence.datasets.ppi.entities.MIFInteractionParticipant;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAlias;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAttribute;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherBioSourceType;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherConfidence;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRef;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefGO;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefPubMed;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefRefSeq;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefUniprot;
import org.jbiowhpersistence.datasets.ppi.entities.MIFParticipantExperimentalPreparation;
import org.jbiowhpersistence.datasets.ppi.entities.MIFParticipantExperimentalRole;
import org.jbiowhpersistence.datasets.ppi.entities.MIFParticipantFeature;
import org.jbiowhpersistence.datasets.ppi.entities.MIFParticipantPartIdentMeth;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the MIFInteractionParticipant Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 17, 2012
 */
public class MIFInteractionParticipantJpaController extends AbstractMIFJpaController<MIFInteractionParticipant> implements Serializable {

    public MIFInteractionParticipantJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MIFInteractionParticipant mifInteractionParticipant) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + mifInteractionParticipant.getWid());

        if (mifInteractionParticipant.getMifParticipantExperimentalPreparation() == null) {
            mifInteractionParticipant.setMifParticipantExperimentalPreparation(new HashSet<MIFParticipantExperimentalPreparation>());
        }
        if (mifInteractionParticipant.getMifParticipantExperimentalRole() == null) {
            mifInteractionParticipant.setMifParticipantExperimentalRole(new HashSet<MIFParticipantExperimentalRole>());
        }
        if (mifInteractionParticipant.getMifParticipantFeature() == null) {
            mifInteractionParticipant.setMifParticipantFeature(new HashSet<MIFParticipantFeature>());
        }
        if (mifInteractionParticipant.getMifParticipantPartIdentMeth() == null) {
            mifInteractionParticipant.setMifParticipantPartIdentMeth(new HashSet<MIFParticipantPartIdentMeth>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mifInteractionParticipant.setMifEntryInteraction(createMIFEntryInteraction(emf, em, mifInteractionParticipant.getMifEntryInteraction()));
            mifInteractionParticipant.setMifEntryInteractor(createMIFEntryInteractor(emf, em, mifInteractionParticipant.getMifEntryInteractor()));
            mifInteractionParticipant.setMifOtherAlias(createMIFOtherAlias(emf, em, mifInteractionParticipant.getMifOtherAlias()));
            mifInteractionParticipant.setMifOtherAttribute(createMIFOtherAttribute(emf, em, mifInteractionParticipant.getMifOtherAttribute()));
            mifInteractionParticipant.setMifOtherBioSourceType(createMIFOtherBioSourceType(emf, em, mifInteractionParticipant.getMifOtherBioSourceType()));
            mifInteractionParticipant.setMifOtherConfidence(createMIFOtherConfidence(emf, em, mifInteractionParticipant.getMifOtherConfidence()));
            mifInteractionParticipant.setMifOtherXRef(createMIFOtherXRef(emf, em, mifInteractionParticipant.getMifOtherXRef()));
            mifInteractionParticipant.setMifOtherXRefGO(createMIFOtherXRefGO(emf, em, mifInteractionParticipant.getMifOtherXRefGO()));
            mifInteractionParticipant.setMifOtherXRefPubMed(createMIFOtherXRefPubMed(emf, em, mifInteractionParticipant.getMifOtherXRefPubMed()));
            mifInteractionParticipant.setMifOtherXRefRefSeq(createMIFOtherXRefRefSeq(emf, em, mifInteractionParticipant.getMifOtherXRefRefSeq()));
            mifInteractionParticipant.setMifOtherXRefUniprot(createMIFOtherXRefUniprot(emf, em, mifInteractionParticipant.getMifOtherXRefUniprot()));

            if (!mifInteractionParticipant.getMifParticipantExperimentalPreparation().isEmpty()) {
                Set<MIFParticipantExperimentalPreparation> attachedMifParticipantExperimentalPreparation = new HashSet();
                for (MIFParticipantExperimentalPreparation mifParticipantExperimentalPreparationMIFParticipantExperimentalPreparationToAttach : mifInteractionParticipant.getMifParticipantExperimentalPreparation()) {
                    MIFParticipantExperimentalPreparation mifParticipantExperimentalPreparation = em.find(mifParticipantExperimentalPreparationMIFParticipantExperimentalPreparationToAttach.getClass(), mifParticipantExperimentalPreparationMIFParticipantExperimentalPreparationToAttach.getWid());
                    if (mifParticipantExperimentalPreparation != null) {
                        attachedMifParticipantExperimentalPreparation.add(mifParticipantExperimentalPreparation);
                    } else {
                        attachedMifParticipantExperimentalPreparation.add(mifParticipantExperimentalPreparationMIFParticipantExperimentalPreparationToAttach);
                    }
                }
                mifInteractionParticipant.setMifParticipantExperimentalPreparation(attachedMifParticipantExperimentalPreparation);
            }
            if (!mifInteractionParticipant.getMifParticipantExperimentalRole().isEmpty()) {
                Set<MIFParticipantExperimentalRole> attachedMifParticipantExperimentalRole = new HashSet();
                for (MIFParticipantExperimentalRole mifParticipantExperimentalRoleMIFParticipantExperimentalRoleToAttach : mifInteractionParticipant.getMifParticipantExperimentalRole()) {
                    MIFParticipantExperimentalRole mifParticipantExperimentalRole = em.find(mifParticipantExperimentalRoleMIFParticipantExperimentalRoleToAttach.getClass(), mifParticipantExperimentalRoleMIFParticipantExperimentalRoleToAttach.getWid());
                    if (mifParticipantExperimentalRole != null) {
                        attachedMifParticipantExperimentalRole.add(mifParticipantExperimentalRole);
                    } else {
                        attachedMifParticipantExperimentalRole.add(mifParticipantExperimentalRoleMIFParticipantExperimentalRoleToAttach);
                    }
                }
                mifInteractionParticipant.setMifParticipantExperimentalRole(attachedMifParticipantExperimentalRole);
            }
            if (!mifInteractionParticipant.getMifParticipantFeature().isEmpty()) {
                Set<MIFParticipantFeature> attachedMifParticipantFeature = new HashSet();
                for (MIFParticipantFeature mifParticipantFeatureMIFParticipantFeatureToAttach : mifInteractionParticipant.getMifParticipantFeature()) {
                    MIFParticipantFeature mifParticipantFeature = em.find(mifParticipantFeatureMIFParticipantFeatureToAttach.getClass(), mifParticipantFeatureMIFParticipantFeatureToAttach.getWid());
                    if (mifParticipantFeature != null) {
                        attachedMifParticipantFeature.add(mifParticipantFeature);
                    } else {
                        attachedMifParticipantFeature.add(mifParticipantFeatureMIFParticipantFeatureToAttach);
                    }
                }
                mifInteractionParticipant.setMifParticipantFeature(attachedMifParticipantFeature);
            }
            if (!mifInteractionParticipant.getMifParticipantPartIdentMeth().isEmpty()) {
                Set<MIFParticipantPartIdentMeth> attachedMifParticipantPartIdentMeth = new HashSet();
                for (MIFParticipantPartIdentMeth mifParticipantPartIdentMethMIFParticipantPartIdentMethToAttach : mifInteractionParticipant.getMifParticipantPartIdentMeth()) {
                    MIFParticipantPartIdentMeth mifParticipantPartIdentMeth = em.find(mifParticipantPartIdentMethMIFParticipantPartIdentMethToAttach.getClass(), mifParticipantPartIdentMethMIFParticipantPartIdentMethToAttach.getWid());
                    if (mifParticipantPartIdentMeth != null) {
                        attachedMifParticipantPartIdentMeth.add(mifParticipantPartIdentMeth);
                    } else {
                        attachedMifParticipantPartIdentMeth.add(mifParticipantPartIdentMethMIFParticipantPartIdentMethToAttach);
                    }
                }
                mifInteractionParticipant.setMifParticipantPartIdentMeth(attachedMifParticipantPartIdentMeth);
            }
            em.persist(mifInteractionParticipant);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMIFInteractionParticipant(mifInteractionParticipant.getWid()) != null) {
                throw new PreexistingEntityException("MIFInteractionParticipant " + mifInteractionParticipant + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MIFInteractionParticipant mifInteractionParticipant) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + mifInteractionParticipant.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MIFInteractionParticipant persistentMIFInteractionParticipant = em.find(MIFInteractionParticipant.class, mifInteractionParticipant.getWid());
            MIFEntryInteraction mifEntryInteractionOld = persistentMIFInteractionParticipant.getMifEntryInteraction();
            MIFEntryInteraction mifEntryInteractionNew = mifInteractionParticipant.getMifEntryInteraction();
            Set<MIFEntryInteractor> mifEntryInteractorOld = persistentMIFInteractionParticipant.getMifEntryInteractor();
            Set<MIFEntryInteractor> mifEntryInteractorNew = mifInteractionParticipant.getMifEntryInteractor();
            Set<MIFOtherAlias> mifOtherAliasOld = persistentMIFInteractionParticipant.getMifOtherAlias();
            Set<MIFOtherAlias> mifOtherAliasNew = mifInteractionParticipant.getMifOtherAlias();
            Set<MIFOtherAttribute> mifOtherAttributeOld = persistentMIFInteractionParticipant.getMifOtherAttribute();
            Set<MIFOtherAttribute> mifOtherAttributeNew = mifInteractionParticipant.getMifOtherAttribute();
            Set<MIFOtherBioSourceType> mifOtherBioSourceTypeOld = persistentMIFInteractionParticipant.getMifOtherBioSourceType();
            Set<MIFOtherBioSourceType> mifOtherBioSourceTypeNew = mifInteractionParticipant.getMifOtherBioSourceType();
            Set<MIFOtherConfidence> mifOtherConfidenceOld = persistentMIFInteractionParticipant.getMifOtherConfidence();
            Set<MIFOtherConfidence> mifOtherConfidenceNew = mifInteractionParticipant.getMifOtherConfidence();
            Set<MIFOtherXRef> mifOtherXRefOld = persistentMIFInteractionParticipant.getMifOtherXRef();
            Set<MIFOtherXRef> mifOtherXRefNew = mifInteractionParticipant.getMifOtherXRef();
            Set<MIFOtherXRefGO> mifOtherXRefGOOld = persistentMIFInteractionParticipant.getMifOtherXRefGO();
            Set<MIFOtherXRefGO> mifOtherXRefGONew = mifInteractionParticipant.getMifOtherXRefGO();
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMedOld = persistentMIFInteractionParticipant.getMifOtherXRefPubMed();
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMedNew = mifInteractionParticipant.getMifOtherXRefPubMed();
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeqOld = persistentMIFInteractionParticipant.getMifOtherXRefRefSeq();
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeqNew = mifInteractionParticipant.getMifOtherXRefRefSeq();
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprotOld = persistentMIFInteractionParticipant.getMifOtherXRefUniprot();
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprotNew = mifInteractionParticipant.getMifOtherXRefUniprot();
            Set<MIFParticipantExperimentalPreparation> mifParticipantExperimentalPreparationOld = persistentMIFInteractionParticipant.getMifParticipantExperimentalPreparation();
            Set<MIFParticipantExperimentalPreparation> mifParticipantExperimentalPreparationNew = mifInteractionParticipant.getMifParticipantExperimentalPreparation();
            Set<MIFParticipantExperimentalRole> mifParticipantExperimentalRoleOld = persistentMIFInteractionParticipant.getMifParticipantExperimentalRole();
            Set<MIFParticipantExperimentalRole> mifParticipantExperimentalRoleNew = mifInteractionParticipant.getMifParticipantExperimentalRole();
            Set<MIFParticipantFeature> mifParticipantFeatureOld = persistentMIFInteractionParticipant.getMifParticipantFeature();
            Set<MIFParticipantFeature> mifParticipantFeatureNew = mifInteractionParticipant.getMifParticipantFeature();
            Set<MIFParticipantPartIdentMeth> mifParticipantPartIdentMethOld = persistentMIFInteractionParticipant.getMifParticipantPartIdentMeth();
            Set<MIFParticipantPartIdentMeth> mifParticipantPartIdentMethNew = mifInteractionParticipant.getMifParticipantPartIdentMeth();
            if (mifEntryInteractionNew != null) {
                mifEntryInteractionNew = em.getReference(mifEntryInteractionNew.getClass(), mifEntryInteractionNew.getWid());
                mifInteractionParticipant.setMifEntryInteraction(mifEntryInteractionNew);
            }
            Set<MIFEntryInteractor> attachedMifEntryInteractorNew = new HashSet();
            for (MIFEntryInteractor mifEntryInteractorNewMIFEntryInteractorToAttach : mifEntryInteractorNew) {
                mifEntryInteractorNewMIFEntryInteractorToAttach = em.getReference(mifEntryInteractorNewMIFEntryInteractorToAttach.getClass(), mifEntryInteractorNewMIFEntryInteractorToAttach.getWid());
                attachedMifEntryInteractorNew.add(mifEntryInteractorNewMIFEntryInteractorToAttach);
            }
            mifEntryInteractorNew = attachedMifEntryInteractorNew;
            mifInteractionParticipant.setMifEntryInteractor(mifEntryInteractorNew);
            Set<MIFOtherAlias> attachedMifOtherAliasNew = new HashSet();
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAliasToAttach : mifOtherAliasNew) {
                mifOtherAliasNewMIFOtherAliasToAttach = em.getReference(mifOtherAliasNewMIFOtherAliasToAttach.getClass(), mifOtherAliasNewMIFOtherAliasToAttach.getWid());
                attachedMifOtherAliasNew.add(mifOtherAliasNewMIFOtherAliasToAttach);
            }
            mifOtherAliasNew = attachedMifOtherAliasNew;
            mifInteractionParticipant.setMifOtherAlias(mifOtherAliasNew);
            Set<MIFOtherAttribute> attachedMifOtherAttributeNew = new HashSet();
            for (MIFOtherAttribute mifOtherAttributeNewMIFOtherAttributeToAttach : mifOtherAttributeNew) {
                mifOtherAttributeNewMIFOtherAttributeToAttach = em.getReference(mifOtherAttributeNewMIFOtherAttributeToAttach.getClass(), mifOtherAttributeNewMIFOtherAttributeToAttach.getWid());
                attachedMifOtherAttributeNew.add(mifOtherAttributeNewMIFOtherAttributeToAttach);
            }
            mifOtherAttributeNew = attachedMifOtherAttributeNew;
            mifInteractionParticipant.setMifOtherAttribute(mifOtherAttributeNew);
            Set<MIFOtherBioSourceType> attachedMifOtherBioSourceTypeNew = new HashSet();
            for (MIFOtherBioSourceType mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach : mifOtherBioSourceTypeNew) {
                mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach = em.getReference(mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach.getClass(), mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach.getWid());
                attachedMifOtherBioSourceTypeNew.add(mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach);
            }
            mifOtherBioSourceTypeNew = attachedMifOtherBioSourceTypeNew;
            mifInteractionParticipant.setMifOtherBioSourceType(mifOtherBioSourceTypeNew);
            Set<MIFOtherConfidence> attachedMifOtherConfidenceNew = new HashSet();
            for (MIFOtherConfidence mifOtherConfidenceNewMIFOtherConfidenceToAttach : mifOtherConfidenceNew) {
                mifOtherConfidenceNewMIFOtherConfidenceToAttach = em.getReference(mifOtherConfidenceNewMIFOtherConfidenceToAttach.getClass(), mifOtherConfidenceNewMIFOtherConfidenceToAttach.getWid());
                attachedMifOtherConfidenceNew.add(mifOtherConfidenceNewMIFOtherConfidenceToAttach);
            }
            mifOtherConfidenceNew = attachedMifOtherConfidenceNew;
            mifInteractionParticipant.setMifOtherConfidence(mifOtherConfidenceNew);
            Set<MIFOtherXRef> attachedMifOtherXRefNew = new HashSet();
            for (MIFOtherXRef mifOtherXRefNewMIFOtherXRefToAttach : mifOtherXRefNew) {
                mifOtherXRefNewMIFOtherXRefToAttach = em.getReference(mifOtherXRefNewMIFOtherXRefToAttach.getClass(), mifOtherXRefNewMIFOtherXRefToAttach.getWid());
                attachedMifOtherXRefNew.add(mifOtherXRefNewMIFOtherXRefToAttach);
            }
            mifOtherXRefNew = attachedMifOtherXRefNew;
            mifInteractionParticipant.setMifOtherXRef(mifOtherXRefNew);
            Set<MIFOtherXRefGO> attachedMifOtherXRefGONew = new HashSet();
            for (MIFOtherXRefGO mifOtherXRefGONewMIFOtherXRefGOToAttach : mifOtherXRefGONew) {
                mifOtherXRefGONewMIFOtherXRefGOToAttach = em.getReference(mifOtherXRefGONewMIFOtherXRefGOToAttach.getClass(), mifOtherXRefGONewMIFOtherXRefGOToAttach.getWid());
                attachedMifOtherXRefGONew.add(mifOtherXRefGONewMIFOtherXRefGOToAttach);
            }
            mifOtherXRefGONew = attachedMifOtherXRefGONew;
            mifInteractionParticipant.setMifOtherXRefGO(mifOtherXRefGONew);
            Set<MIFOtherXRefPubMed> attachedMifOtherXRefPubMedNew = new HashSet();
            for (MIFOtherXRefPubMed mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach : mifOtherXRefPubMedNew) {
                mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach = em.getReference(mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach.getClass(), mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach.getWid());
                attachedMifOtherXRefPubMedNew.add(mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach);
            }
            mifOtherXRefPubMedNew = attachedMifOtherXRefPubMedNew;
            mifInteractionParticipant.setMifOtherXRefPubMed(mifOtherXRefPubMedNew);
            Set<MIFOtherXRefRefSeq> attachedMifOtherXRefRefSeqNew = new HashSet();
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach : mifOtherXRefRefSeqNew) {
                mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach = em.getReference(mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach.getClass(), mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach.getWid());
                attachedMifOtherXRefRefSeqNew.add(mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach);
            }
            mifOtherXRefRefSeqNew = attachedMifOtherXRefRefSeqNew;
            mifInteractionParticipant.setMifOtherXRefRefSeq(mifOtherXRefRefSeqNew);
            Set<MIFOtherXRefUniprot> attachedMifOtherXRefUniprotNew = new HashSet();
            for (MIFOtherXRefUniprot mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach : mifOtherXRefUniprotNew) {
                mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach = em.getReference(mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach.getClass(), mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach.getWid());
                attachedMifOtherXRefUniprotNew.add(mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach);
            }
            mifOtherXRefUniprotNew = attachedMifOtherXRefUniprotNew;
            mifInteractionParticipant.setMifOtherXRefUniprot(mifOtherXRefUniprotNew);
            Set<MIFParticipantExperimentalPreparation> attachedMifParticipantExperimentalPreparationNew = new HashSet();
            for (MIFParticipantExperimentalPreparation mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparationToAttach : mifParticipantExperimentalPreparationNew) {
                mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparationToAttach = em.getReference(mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparationToAttach.getClass(), mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparationToAttach.getWid());
                attachedMifParticipantExperimentalPreparationNew.add(mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparationToAttach);
            }
            mifParticipantExperimentalPreparationNew = attachedMifParticipantExperimentalPreparationNew;
            mifInteractionParticipant.setMifParticipantExperimentalPreparation(mifParticipantExperimentalPreparationNew);
            Set<MIFParticipantExperimentalRole> attachedMifParticipantExperimentalRoleNew = new HashSet();
            for (MIFParticipantExperimentalRole mifParticipantExperimentalRoleNewMIFParticipantExperimentalRoleToAttach : mifParticipantExperimentalRoleNew) {
                mifParticipantExperimentalRoleNewMIFParticipantExperimentalRoleToAttach = em.getReference(mifParticipantExperimentalRoleNewMIFParticipantExperimentalRoleToAttach.getClass(), mifParticipantExperimentalRoleNewMIFParticipantExperimentalRoleToAttach.getWid());
                attachedMifParticipantExperimentalRoleNew.add(mifParticipantExperimentalRoleNewMIFParticipantExperimentalRoleToAttach);
            }
            mifParticipantExperimentalRoleNew = attachedMifParticipantExperimentalRoleNew;
            mifInteractionParticipant.setMifParticipantExperimentalRole(mifParticipantExperimentalRoleNew);
            Set<MIFParticipantFeature> attachedMifParticipantFeatureNew = new HashSet();
            for (MIFParticipantFeature mifParticipantFeatureNewMIFParticipantFeatureToAttach : mifParticipantFeatureNew) {
                mifParticipantFeatureNewMIFParticipantFeatureToAttach = em.getReference(mifParticipantFeatureNewMIFParticipantFeatureToAttach.getClass(), mifParticipantFeatureNewMIFParticipantFeatureToAttach.getWid());
                attachedMifParticipantFeatureNew.add(mifParticipantFeatureNewMIFParticipantFeatureToAttach);
            }
            mifParticipantFeatureNew = attachedMifParticipantFeatureNew;
            mifInteractionParticipant.setMifParticipantFeature(mifParticipantFeatureNew);
            Set<MIFParticipantPartIdentMeth> attachedMifParticipantPartIdentMethNew = new HashSet();
            for (MIFParticipantPartIdentMeth mifParticipantPartIdentMethNewMIFParticipantPartIdentMethToAttach : mifParticipantPartIdentMethNew) {
                mifParticipantPartIdentMethNewMIFParticipantPartIdentMethToAttach = em.getReference(mifParticipantPartIdentMethNewMIFParticipantPartIdentMethToAttach.getClass(), mifParticipantPartIdentMethNewMIFParticipantPartIdentMethToAttach.getWid());
                attachedMifParticipantPartIdentMethNew.add(mifParticipantPartIdentMethNewMIFParticipantPartIdentMethToAttach);
            }
            mifParticipantPartIdentMethNew = attachedMifParticipantPartIdentMethNew;
            mifInteractionParticipant.setMifParticipantPartIdentMeth(mifParticipantPartIdentMethNew);
            mifInteractionParticipant = em.merge(mifInteractionParticipant);
            if (mifEntryInteractionOld != null && !mifEntryInteractionOld.equals(mifEntryInteractionNew)) {
                mifEntryInteractionOld.getMifInteractionParticipant().remove(mifInteractionParticipant);
                mifEntryInteractionOld = em.merge(mifEntryInteractionOld);
            }
            if (mifEntryInteractionNew != null && !mifEntryInteractionNew.equals(mifEntryInteractionOld)) {
                mifEntryInteractionNew.getMifInteractionParticipant().add(mifInteractionParticipant);
                em.merge(mifEntryInteractionNew);
            }
            for (MIFEntryInteractor mifEntryInteractorOldMIFEntryInteractor : mifEntryInteractorOld) {
                if (!mifEntryInteractorNew.contains(mifEntryInteractorOldMIFEntryInteractor)) {
                    mifEntryInteractorOldMIFEntryInteractor.setMifInteractionParticipant(null);
                    mifEntryInteractorOldMIFEntryInteractor = em.merge(mifEntryInteractorOldMIFEntryInteractor);
                }
            }
            for (MIFEntryInteractor mifEntryInteractorNewMIFEntryInteractor : mifEntryInteractorNew) {
                if (!mifEntryInteractorOld.contains(mifEntryInteractorNewMIFEntryInteractor)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifEntryInteractorNewMIFEntryInteractor = mifEntryInteractorNewMIFEntryInteractor.getMifInteractionParticipant();
                    mifEntryInteractorNewMIFEntryInteractor.setMifInteractionParticipant(mifInteractionParticipant);
                    mifEntryInteractorNewMIFEntryInteractor = em.merge(mifEntryInteractorNewMIFEntryInteractor);
                    if (oldMifInteractionParticipantOfMifEntryInteractorNewMIFEntryInteractor != null && !oldMifInteractionParticipantOfMifEntryInteractorNewMIFEntryInteractor.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifEntryInteractorNewMIFEntryInteractor.getMifEntryInteractor().remove(mifEntryInteractorNewMIFEntryInteractor);
                        em.merge(oldMifInteractionParticipantOfMifEntryInteractorNewMIFEntryInteractor);
                    }
                }
            }
            for (MIFOtherAlias mifOtherAliasOldMIFOtherAlias : mifOtherAliasOld) {
                if (!mifOtherAliasNew.contains(mifOtherAliasOldMIFOtherAlias)) {
                    mifOtherAliasOldMIFOtherAlias.setMifInteractionParticipant(null);
                    mifOtherAliasOldMIFOtherAlias = em.merge(mifOtherAliasOldMIFOtherAlias);
                }
            }
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAlias : mifOtherAliasNew) {
                if (!mifOtherAliasOld.contains(mifOtherAliasNewMIFOtherAlias)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifOtherAliasNewMIFOtherAlias = mifOtherAliasNewMIFOtherAlias.getMifInteractionParticipant();
                    mifOtherAliasNewMIFOtherAlias.setMifInteractionParticipant(mifInteractionParticipant);
                    mifOtherAliasNewMIFOtherAlias = em.merge(mifOtherAliasNewMIFOtherAlias);
                    if (oldMifInteractionParticipantOfMifOtherAliasNewMIFOtherAlias != null && !oldMifInteractionParticipantOfMifOtherAliasNewMIFOtherAlias.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifOtherAliasNewMIFOtherAlias.getMifOtherAlias().remove(mifOtherAliasNewMIFOtherAlias);
                        em.merge(oldMifInteractionParticipantOfMifOtherAliasNewMIFOtherAlias);
                    }
                }
            }
            for (MIFOtherAttribute mifOtherAttributeOldMIFOtherAttribute : mifOtherAttributeOld) {
                if (!mifOtherAttributeNew.contains(mifOtherAttributeOldMIFOtherAttribute)) {
                    mifOtherAttributeOldMIFOtherAttribute.setMifInteractionParticipant(null);
                    mifOtherAttributeOldMIFOtherAttribute = em.merge(mifOtherAttributeOldMIFOtherAttribute);
                }
            }
            for (MIFOtherAttribute mifOtherAttributeNewMIFOtherAttribute : mifOtherAttributeNew) {
                if (!mifOtherAttributeOld.contains(mifOtherAttributeNewMIFOtherAttribute)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifOtherAttributeNewMIFOtherAttribute = mifOtherAttributeNewMIFOtherAttribute.getMifInteractionParticipant();
                    mifOtherAttributeNewMIFOtherAttribute.setMifInteractionParticipant(mifInteractionParticipant);
                    mifOtherAttributeNewMIFOtherAttribute = em.merge(mifOtherAttributeNewMIFOtherAttribute);
                    if (oldMifInteractionParticipantOfMifOtherAttributeNewMIFOtherAttribute != null && !oldMifInteractionParticipantOfMifOtherAttributeNewMIFOtherAttribute.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifOtherAttributeNewMIFOtherAttribute.getMifOtherAttribute().remove(mifOtherAttributeNewMIFOtherAttribute);
                        em.merge(oldMifInteractionParticipantOfMifOtherAttributeNewMIFOtherAttribute);
                    }
                }
            }
            for (MIFOtherBioSourceType mifOtherBioSourceTypeOldMIFOtherBioSourceType : mifOtherBioSourceTypeOld) {
                if (!mifOtherBioSourceTypeNew.contains(mifOtherBioSourceTypeOldMIFOtherBioSourceType)) {
                    mifOtherBioSourceTypeOldMIFOtherBioSourceType.setMifInteractionParticipant(null);
                    mifOtherBioSourceTypeOldMIFOtherBioSourceType = em.merge(mifOtherBioSourceTypeOldMIFOtherBioSourceType);
                }
            }
            for (MIFOtherBioSourceType mifOtherBioSourceTypeNewMIFOtherBioSourceType : mifOtherBioSourceTypeNew) {
                if (!mifOtherBioSourceTypeOld.contains(mifOtherBioSourceTypeNewMIFOtherBioSourceType)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifOtherBioSourceTypeNewMIFOtherBioSourceType = mifOtherBioSourceTypeNewMIFOtherBioSourceType.getMifInteractionParticipant();
                    mifOtherBioSourceTypeNewMIFOtherBioSourceType.setMifInteractionParticipant(mifInteractionParticipant);
                    mifOtherBioSourceTypeNewMIFOtherBioSourceType = em.merge(mifOtherBioSourceTypeNewMIFOtherBioSourceType);
                    if (oldMifInteractionParticipantOfMifOtherBioSourceTypeNewMIFOtherBioSourceType != null && !oldMifInteractionParticipantOfMifOtherBioSourceTypeNewMIFOtherBioSourceType.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifOtherBioSourceTypeNewMIFOtherBioSourceType.getMifOtherBioSourceType().remove(mifOtherBioSourceTypeNewMIFOtherBioSourceType);
                        em.merge(oldMifInteractionParticipantOfMifOtherBioSourceTypeNewMIFOtherBioSourceType);
                    }
                }
            }
            for (MIFOtherConfidence mifOtherConfidenceOldMIFOtherConfidence : mifOtherConfidenceOld) {
                if (!mifOtherConfidenceNew.contains(mifOtherConfidenceOldMIFOtherConfidence)) {
                    mifOtherConfidenceOldMIFOtherConfidence.setMifInteractionParticipant(null);
                    mifOtherConfidenceOldMIFOtherConfidence = em.merge(mifOtherConfidenceOldMIFOtherConfidence);
                }
            }
            for (MIFOtherConfidence mifOtherConfidenceNewMIFOtherConfidence : mifOtherConfidenceNew) {
                if (!mifOtherConfidenceOld.contains(mifOtherConfidenceNewMIFOtherConfidence)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifOtherConfidenceNewMIFOtherConfidence = mifOtherConfidenceNewMIFOtherConfidence.getMifInteractionParticipant();
                    mifOtherConfidenceNewMIFOtherConfidence.setMifInteractionParticipant(mifInteractionParticipant);
                    mifOtherConfidenceNewMIFOtherConfidence = em.merge(mifOtherConfidenceNewMIFOtherConfidence);
                    if (oldMifInteractionParticipantOfMifOtherConfidenceNewMIFOtherConfidence != null && !oldMifInteractionParticipantOfMifOtherConfidenceNewMIFOtherConfidence.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifOtherConfidenceNewMIFOtherConfidence.getMifOtherConfidence().remove(mifOtherConfidenceNewMIFOtherConfidence);
                        em.merge(oldMifInteractionParticipantOfMifOtherConfidenceNewMIFOtherConfidence);
                    }
                }
            }
            for (MIFOtherXRef mifOtherXRefOldMIFOtherXRef : mifOtherXRefOld) {
                if (!mifOtherXRefNew.contains(mifOtherXRefOldMIFOtherXRef)) {
                    mifOtherXRefOldMIFOtherXRef.setMifInteractionParticipant(null);
                    mifOtherXRefOldMIFOtherXRef = em.merge(mifOtherXRefOldMIFOtherXRef);
                }
            }
            for (MIFOtherXRef mifOtherXRefNewMIFOtherXRef : mifOtherXRefNew) {
                if (!mifOtherXRefOld.contains(mifOtherXRefNewMIFOtherXRef)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifOtherXRefNewMIFOtherXRef = mifOtherXRefNewMIFOtherXRef.getMifInteractionParticipant();
                    mifOtherXRefNewMIFOtherXRef.setMifInteractionParticipant(mifInteractionParticipant);
                    mifOtherXRefNewMIFOtherXRef = em.merge(mifOtherXRefNewMIFOtherXRef);
                    if (oldMifInteractionParticipantOfMifOtherXRefNewMIFOtherXRef != null && !oldMifInteractionParticipantOfMifOtherXRefNewMIFOtherXRef.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifOtherXRefNewMIFOtherXRef.getMifOtherXRef().remove(mifOtherXRefNewMIFOtherXRef);
                        em.merge(oldMifInteractionParticipantOfMifOtherXRefNewMIFOtherXRef);
                    }
                }
            }
            for (MIFOtherXRefGO mifOtherXRefGOOldMIFOtherXRefGO : mifOtherXRefGOOld) {
                if (!mifOtherXRefGONew.contains(mifOtherXRefGOOldMIFOtherXRefGO)) {
                    mifOtherXRefGOOldMIFOtherXRefGO.setMifInteractionParticipant(null);
                    mifOtherXRefGOOldMIFOtherXRefGO = em.merge(mifOtherXRefGOOldMIFOtherXRefGO);
                }
            }
            for (MIFOtherXRefGO mifOtherXRefGONewMIFOtherXRefGO : mifOtherXRefGONew) {
                if (!mifOtherXRefGOOld.contains(mifOtherXRefGONewMIFOtherXRefGO)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifOtherXRefGONewMIFOtherXRefGO = mifOtherXRefGONewMIFOtherXRefGO.getMifInteractionParticipant();
                    mifOtherXRefGONewMIFOtherXRefGO.setMifInteractionParticipant(mifInteractionParticipant);
                    mifOtherXRefGONewMIFOtherXRefGO = em.merge(mifOtherXRefGONewMIFOtherXRefGO);
                    if (oldMifInteractionParticipantOfMifOtherXRefGONewMIFOtherXRefGO != null && !oldMifInteractionParticipantOfMifOtherXRefGONewMIFOtherXRefGO.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifOtherXRefGONewMIFOtherXRefGO.getMifOtherXRefGO().remove(mifOtherXRefGONewMIFOtherXRefGO);
                        em.merge(oldMifInteractionParticipantOfMifOtherXRefGONewMIFOtherXRefGO);
                    }
                }
            }
            for (MIFOtherXRefPubMed mifOtherXRefPubMedOldMIFOtherXRefPubMed : mifOtherXRefPubMedOld) {
                if (!mifOtherXRefPubMedNew.contains(mifOtherXRefPubMedOldMIFOtherXRefPubMed)) {
                    mifOtherXRefPubMedOldMIFOtherXRefPubMed.setMifInteractionParticipant(null);
                    mifOtherXRefPubMedOldMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedOldMIFOtherXRefPubMed);
                }
            }
            for (MIFOtherXRefPubMed mifOtherXRefPubMedNewMIFOtherXRefPubMed : mifOtherXRefPubMedNew) {
                if (!mifOtherXRefPubMedOld.contains(mifOtherXRefPubMedNewMIFOtherXRefPubMed)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifOtherXRefPubMedNewMIFOtherXRefPubMed = mifOtherXRefPubMedNewMIFOtherXRefPubMed.getMifInteractionParticipant();
                    mifOtherXRefPubMedNewMIFOtherXRefPubMed.setMifInteractionParticipant(mifInteractionParticipant);
                    mifOtherXRefPubMedNewMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedNewMIFOtherXRefPubMed);
                    if (oldMifInteractionParticipantOfMifOtherXRefPubMedNewMIFOtherXRefPubMed != null && !oldMifInteractionParticipantOfMifOtherXRefPubMedNewMIFOtherXRefPubMed.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifOtherXRefPubMedNewMIFOtherXRefPubMed.getMifOtherXRefPubMed().remove(mifOtherXRefPubMedNewMIFOtherXRefPubMed);
                        em.merge(oldMifInteractionParticipantOfMifOtherXRefPubMedNewMIFOtherXRefPubMed);
                    }
                }
            }
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqOldMIFOtherXRefRefSeq : mifOtherXRefRefSeqOld) {
                if (!mifOtherXRefRefSeqNew.contains(mifOtherXRefRefSeqOldMIFOtherXRefRefSeq)) {
                    mifOtherXRefRefSeqOldMIFOtherXRefRefSeq.setMifInteractionParticipant(null);
                    mifOtherXRefRefSeqOldMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqOldMIFOtherXRefRefSeq);
                }
            }
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqNewMIFOtherXRefRefSeq : mifOtherXRefRefSeqNew) {
                if (!mifOtherXRefRefSeqOld.contains(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq = mifOtherXRefRefSeqNewMIFOtherXRefRefSeq.getMifInteractionParticipant();
                    mifOtherXRefRefSeqNewMIFOtherXRefRefSeq.setMifInteractionParticipant(mifInteractionParticipant);
                    mifOtherXRefRefSeqNewMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                    if (oldMifInteractionParticipantOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq != null && !oldMifInteractionParticipantOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq.getMifOtherXRefRefSeq().remove(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                        em.merge(oldMifInteractionParticipantOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                    }
                }
            }
            for (MIFOtherXRefUniprot mifOtherXRefUniprotOldMIFOtherXRefUniprot : mifOtherXRefUniprotOld) {
                if (!mifOtherXRefUniprotNew.contains(mifOtherXRefUniprotOldMIFOtherXRefUniprot)) {
                    mifOtherXRefUniprotOldMIFOtherXRefUniprot.setMifInteractionParticipant(null);
                    mifOtherXRefUniprotOldMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotOldMIFOtherXRefUniprot);
                }
            }
            for (MIFOtherXRefUniprot mifOtherXRefUniprotNewMIFOtherXRefUniprot : mifOtherXRefUniprotNew) {
                if (!mifOtherXRefUniprotOld.contains(mifOtherXRefUniprotNewMIFOtherXRefUniprot)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifOtherXRefUniprotNewMIFOtherXRefUniprot = mifOtherXRefUniprotNewMIFOtherXRefUniprot.getMifInteractionParticipant();
                    mifOtherXRefUniprotNewMIFOtherXRefUniprot.setMifInteractionParticipant(mifInteractionParticipant);
                    mifOtherXRefUniprotNewMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotNewMIFOtherXRefUniprot);
                    if (oldMifInteractionParticipantOfMifOtherXRefUniprotNewMIFOtherXRefUniprot != null && !oldMifInteractionParticipantOfMifOtherXRefUniprotNewMIFOtherXRefUniprot.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifOtherXRefUniprotNewMIFOtherXRefUniprot.getMifOtherXRefUniprot().remove(mifOtherXRefUniprotNewMIFOtherXRefUniprot);
                        em.merge(oldMifInteractionParticipantOfMifOtherXRefUniprotNewMIFOtherXRefUniprot);
                    }
                }
            }
            for (MIFParticipantExperimentalPreparation mifParticipantExperimentalPreparationOldMIFParticipantExperimentalPreparation : mifParticipantExperimentalPreparationOld) {
                if (!mifParticipantExperimentalPreparationNew.contains(mifParticipantExperimentalPreparationOldMIFParticipantExperimentalPreparation)) {
                    mifParticipantExperimentalPreparationOldMIFParticipantExperimentalPreparation.setMifInteractionParticipant(null);
                    mifParticipantExperimentalPreparationOldMIFParticipantExperimentalPreparation = em.merge(mifParticipantExperimentalPreparationOldMIFParticipantExperimentalPreparation);
                }
            }
            for (MIFParticipantExperimentalPreparation mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation : mifParticipantExperimentalPreparationNew) {
                if (!mifParticipantExperimentalPreparationOld.contains(mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation = mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation.getMifInteractionParticipant();
                    mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation.setMifInteractionParticipant(mifInteractionParticipant);
                    mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation = em.merge(mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation);
                    if (oldMifInteractionParticipantOfMifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation != null && !oldMifInteractionParticipantOfMifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation.getMifParticipantExperimentalPreparation().remove(mifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation);
                        em.merge(oldMifInteractionParticipantOfMifParticipantExperimentalPreparationNewMIFParticipantExperimentalPreparation);
                    }
                }
            }
            for (MIFParticipantExperimentalRole mifParticipantExperimentalRoleOldMIFParticipantExperimentalRole : mifParticipantExperimentalRoleOld) {
                if (!mifParticipantExperimentalRoleNew.contains(mifParticipantExperimentalRoleOldMIFParticipantExperimentalRole)) {
                    mifParticipantExperimentalRoleOldMIFParticipantExperimentalRole.setMifInteractionParticipant(null);
                    mifParticipantExperimentalRoleOldMIFParticipantExperimentalRole = em.merge(mifParticipantExperimentalRoleOldMIFParticipantExperimentalRole);
                }
            }
            for (MIFParticipantExperimentalRole mifParticipantExperimentalRoleNewMIFParticipantExperimentalRole : mifParticipantExperimentalRoleNew) {
                if (!mifParticipantExperimentalRoleOld.contains(mifParticipantExperimentalRoleNewMIFParticipantExperimentalRole)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifParticipantExperimentalRoleNewMIFParticipantExperimentalRole = mifParticipantExperimentalRoleNewMIFParticipantExperimentalRole.getMifInteractionParticipant();
                    mifParticipantExperimentalRoleNewMIFParticipantExperimentalRole.setMifInteractionParticipant(mifInteractionParticipant);
                    mifParticipantExperimentalRoleNewMIFParticipantExperimentalRole = em.merge(mifParticipantExperimentalRoleNewMIFParticipantExperimentalRole);
                    if (oldMifInteractionParticipantOfMifParticipantExperimentalRoleNewMIFParticipantExperimentalRole != null && !oldMifInteractionParticipantOfMifParticipantExperimentalRoleNewMIFParticipantExperimentalRole.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifParticipantExperimentalRoleNewMIFParticipantExperimentalRole.getMifParticipantExperimentalRole().remove(mifParticipantExperimentalRoleNewMIFParticipantExperimentalRole);
                        em.merge(oldMifInteractionParticipantOfMifParticipantExperimentalRoleNewMIFParticipantExperimentalRole);
                    }
                }
            }
            for (MIFParticipantFeature mifParticipantFeatureOldMIFParticipantFeature : mifParticipantFeatureOld) {
                if (!mifParticipantFeatureNew.contains(mifParticipantFeatureOldMIFParticipantFeature)) {
                    mifParticipantFeatureOldMIFParticipantFeature.setMifInteractionParticipant(null);
                    mifParticipantFeatureOldMIFParticipantFeature = em.merge(mifParticipantFeatureOldMIFParticipantFeature);
                }
            }
            for (MIFParticipantFeature mifParticipantFeatureNewMIFParticipantFeature : mifParticipantFeatureNew) {
                if (!mifParticipantFeatureOld.contains(mifParticipantFeatureNewMIFParticipantFeature)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifParticipantFeatureNewMIFParticipantFeature = mifParticipantFeatureNewMIFParticipantFeature.getMifInteractionParticipant();
                    mifParticipantFeatureNewMIFParticipantFeature.setMifInteractionParticipant(mifInteractionParticipant);
                    mifParticipantFeatureNewMIFParticipantFeature = em.merge(mifParticipantFeatureNewMIFParticipantFeature);
                    if (oldMifInteractionParticipantOfMifParticipantFeatureNewMIFParticipantFeature != null && !oldMifInteractionParticipantOfMifParticipantFeatureNewMIFParticipantFeature.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifParticipantFeatureNewMIFParticipantFeature.getMifParticipantFeature().remove(mifParticipantFeatureNewMIFParticipantFeature);
                        em.merge(oldMifInteractionParticipantOfMifParticipantFeatureNewMIFParticipantFeature);
                    }
                }
            }
            for (MIFParticipantPartIdentMeth mifParticipantPartIdentMethOldMIFParticipantPartIdentMeth : mifParticipantPartIdentMethOld) {
                if (!mifParticipantPartIdentMethNew.contains(mifParticipantPartIdentMethOldMIFParticipantPartIdentMeth)) {
                    mifParticipantPartIdentMethOldMIFParticipantPartIdentMeth.setMifInteractionParticipant(null);
                    mifParticipantPartIdentMethOldMIFParticipantPartIdentMeth = em.merge(mifParticipantPartIdentMethOldMIFParticipantPartIdentMeth);
                }
            }
            for (MIFParticipantPartIdentMeth mifParticipantPartIdentMethNewMIFParticipantPartIdentMeth : mifParticipantPartIdentMethNew) {
                if (!mifParticipantPartIdentMethOld.contains(mifParticipantPartIdentMethNewMIFParticipantPartIdentMeth)) {
                    MIFInteractionParticipant oldMifInteractionParticipantOfMifParticipantPartIdentMethNewMIFParticipantPartIdentMeth = mifParticipantPartIdentMethNewMIFParticipantPartIdentMeth.getMifInteractionParticipant();
                    mifParticipantPartIdentMethNewMIFParticipantPartIdentMeth.setMifInteractionParticipant(mifInteractionParticipant);
                    mifParticipantPartIdentMethNewMIFParticipantPartIdentMeth = em.merge(mifParticipantPartIdentMethNewMIFParticipantPartIdentMeth);
                    if (oldMifInteractionParticipantOfMifParticipantPartIdentMethNewMIFParticipantPartIdentMeth != null && !oldMifInteractionParticipantOfMifParticipantPartIdentMethNewMIFParticipantPartIdentMeth.equals(mifInteractionParticipant)) {
                        oldMifInteractionParticipantOfMifParticipantPartIdentMethNewMIFParticipantPartIdentMeth.getMifParticipantPartIdentMeth().remove(mifParticipantPartIdentMethNewMIFParticipantPartIdentMeth);
                        em.merge(oldMifInteractionParticipantOfMifParticipantPartIdentMethNewMIFParticipantPartIdentMeth);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mifInteractionParticipant.getWid();
                if (findMIFInteractionParticipant(id) == null) {
                    throw new NonexistentEntityException("The mIFInteractionParticipant with id " + id + " no longer exists.");
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
            MIFInteractionParticipant MIFInteractionParticipant;
            try {
                MIFInteractionParticipant = em.getReference(MIFInteractionParticipant.class, id);
                MIFInteractionParticipant.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MIFInteractionParticipant with id " + id + " no longer exists.", enfe);
            }
            MIFEntryInteraction mifEntryInteraction = MIFInteractionParticipant.getMifEntryInteraction();
            if (mifEntryInteraction != null) {
                mifEntryInteraction.getMifInteractionParticipant().remove(MIFInteractionParticipant);
                em.merge(mifEntryInteraction);
            }
            Set<MIFEntryInteractor> mifEntryInteractor = MIFInteractionParticipant.getMifEntryInteractor();
            for (MIFEntryInteractor mifEntryInteractorMIFEntryInteractor : mifEntryInteractor) {
                mifEntryInteractorMIFEntryInteractor.setMifInteractionParticipant(null);
                mifEntryInteractorMIFEntryInteractor = em.merge(mifEntryInteractorMIFEntryInteractor);
            }
            Set<MIFOtherAlias> mifOtherAlias = MIFInteractionParticipant.getMifOtherAlias();
            for (MIFOtherAlias mifOtherAliasMIFOtherAlias : mifOtherAlias) {
                mifOtherAliasMIFOtherAlias.setMifInteractionParticipant(null);
                mifOtherAliasMIFOtherAlias = em.merge(mifOtherAliasMIFOtherAlias);
            }
            Set<MIFOtherAttribute> mifOtherAttribute = MIFInteractionParticipant.getMifOtherAttribute();
            for (MIFOtherAttribute mifOtherAttributeMIFOtherAttribute : mifOtherAttribute) {
                mifOtherAttributeMIFOtherAttribute.setMifInteractionParticipant(null);
                mifOtherAttributeMIFOtherAttribute = em.merge(mifOtherAttributeMIFOtherAttribute);
            }
            Set<MIFOtherBioSourceType> mifOtherBioSourceType = MIFInteractionParticipant.getMifOtherBioSourceType();
            for (MIFOtherBioSourceType mifOtherBioSourceTypeMIFOtherBioSourceType : mifOtherBioSourceType) {
                mifOtherBioSourceTypeMIFOtherBioSourceType.setMifInteractionParticipant(null);
                mifOtherBioSourceTypeMIFOtherBioSourceType = em.merge(mifOtherBioSourceTypeMIFOtherBioSourceType);
            }
            Set<MIFOtherConfidence> mifOtherConfidence = MIFInteractionParticipant.getMifOtherConfidence();
            for (MIFOtherConfidence mifOtherConfidenceMIFOtherConfidence : mifOtherConfidence) {
                mifOtherConfidenceMIFOtherConfidence.setMifInteractionParticipant(null);
                mifOtherConfidenceMIFOtherConfidence = em.merge(mifOtherConfidenceMIFOtherConfidence);
            }
            Set<MIFOtherXRef> mifOtherXRef = MIFInteractionParticipant.getMifOtherXRef();
            for (MIFOtherXRef mifOtherXRefMIFOtherXRef : mifOtherXRef) {
                mifOtherXRefMIFOtherXRef.setMifInteractionParticipant(null);
                mifOtherXRefMIFOtherXRef = em.merge(mifOtherXRefMIFOtherXRef);
            }
            Set<MIFOtherXRefGO> mifOtherXRefGO = MIFInteractionParticipant.getMifOtherXRefGO();
            for (MIFOtherXRefGO mifOtherXRefGOMIFOtherXRefGO : mifOtherXRefGO) {
                mifOtherXRefGOMIFOtherXRefGO.setMifInteractionParticipant(null);
                mifOtherXRefGOMIFOtherXRefGO = em.merge(mifOtherXRefGOMIFOtherXRefGO);
            }
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMed = MIFInteractionParticipant.getMifOtherXRefPubMed();
            for (MIFOtherXRefPubMed mifOtherXRefPubMedMIFOtherXRefPubMed : mifOtherXRefPubMed) {
                mifOtherXRefPubMedMIFOtherXRefPubMed.setMifInteractionParticipant(null);
                mifOtherXRefPubMedMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedMIFOtherXRefPubMed);
            }
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq = MIFInteractionParticipant.getMifOtherXRefRefSeq();
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqMIFOtherXRefRefSeq : mifOtherXRefRefSeq) {
                mifOtherXRefRefSeqMIFOtherXRefRefSeq.setMifInteractionParticipant(null);
                mifOtherXRefRefSeqMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqMIFOtherXRefRefSeq);
            }
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprot = MIFInteractionParticipant.getMifOtherXRefUniprot();
            for (MIFOtherXRefUniprot mifOtherXRefUniprotMIFOtherXRefUniprot : mifOtherXRefUniprot) {
                mifOtherXRefUniprotMIFOtherXRefUniprot.setMifInteractionParticipant(null);
                mifOtherXRefUniprotMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotMIFOtherXRefUniprot);
            }
            Set<MIFParticipantExperimentalPreparation> mifParticipantExperimentalPreparation = MIFInteractionParticipant.getMifParticipantExperimentalPreparation();
            for (MIFParticipantExperimentalPreparation mifParticipantExperimentalPreparationMIFParticipantExperimentalPreparation : mifParticipantExperimentalPreparation) {
                mifParticipantExperimentalPreparationMIFParticipantExperimentalPreparation.setMifInteractionParticipant(null);
                mifParticipantExperimentalPreparationMIFParticipantExperimentalPreparation = em.merge(mifParticipantExperimentalPreparationMIFParticipantExperimentalPreparation);
            }
            Set<MIFParticipantExperimentalRole> mifParticipantExperimentalRole = MIFInteractionParticipant.getMifParticipantExperimentalRole();
            for (MIFParticipantExperimentalRole mifParticipantExperimentalRoleMIFParticipantExperimentalRole : mifParticipantExperimentalRole) {
                mifParticipantExperimentalRoleMIFParticipantExperimentalRole.setMifInteractionParticipant(null);
                mifParticipantExperimentalRoleMIFParticipantExperimentalRole = em.merge(mifParticipantExperimentalRoleMIFParticipantExperimentalRole);
            }
            Set<MIFParticipantFeature> mifParticipantFeature = MIFInteractionParticipant.getMifParticipantFeature();
            for (MIFParticipantFeature mifParticipantFeatureMIFParticipantFeature : mifParticipantFeature) {
                mifParticipantFeatureMIFParticipantFeature.setMifInteractionParticipant(null);
                mifParticipantFeatureMIFParticipantFeature = em.merge(mifParticipantFeatureMIFParticipantFeature);
            }
            Set<MIFParticipantPartIdentMeth> mifParticipantPartIdentMeth = MIFInteractionParticipant.getMifParticipantPartIdentMeth();
            for (MIFParticipantPartIdentMeth mifParticipantPartIdentMethMIFParticipantPartIdentMeth : mifParticipantPartIdentMeth) {
                mifParticipantPartIdentMethMIFParticipantPartIdentMeth.setMifInteractionParticipant(null);
                mifParticipantPartIdentMethMIFParticipantPartIdentMeth = em.merge(mifParticipantPartIdentMethMIFParticipantPartIdentMeth);
            }
            em.remove(MIFInteractionParticipant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MIFInteractionParticipant> findMIFInteractionParticipantEntities() {
        return findMIFInteractionParticipantEntities(true, -1, -1);
    }

    public List<MIFInteractionParticipant> findMIFInteractionParticipantEntities(int maxResults, int firstResult) {
        return findMIFInteractionParticipantEntities(false, maxResults, firstResult);
    }

    private List<MIFInteractionParticipant> findMIFInteractionParticipantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MIFInteractionParticipant.class));
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

    public MIFInteractionParticipant findMIFInteractionParticipant(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MIFInteractionParticipant.class, id);
        } finally {
            em.close();
        }
    }

    public int getMIFInteractionParticipantCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MIFInteractionParticipant> rt = cq.from(MIFInteractionParticipant.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
