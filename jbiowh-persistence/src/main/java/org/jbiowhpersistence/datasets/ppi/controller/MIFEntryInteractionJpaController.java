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
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryExperiment;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.datasets.ppi.entities.MIFInteractionInferredInteraction;
import org.jbiowhpersistence.datasets.ppi.entities.MIFInteractionInteractionType;
import org.jbiowhpersistence.datasets.ppi.entities.MIFInteractionParticipant;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAlias;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAttribute;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherConfidence;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRef;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefGO;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefPubMed;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefRefSeq;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefUniprot;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the MIFEntryInteraction Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 4, 2012
 */
public class MIFEntryInteractionJpaController extends AbstractMIFJpaController<MIFEntryInteraction> implements Serializable {

    public MIFEntryInteractionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MIFEntryInteraction mifEntryInteraction) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + mifEntryInteraction.getWid());
        if (mifEntryInteraction.getMifInteractionInferredInteraction() == null) {
            mifEntryInteraction.setMifInteractionInferredInteraction(new HashSet<MIFInteractionInferredInteraction>());
        }
        if (mifEntryInteraction.getMifInteractionInteractionType() == null) {
            mifEntryInteraction.setMifInteractionInteractionType(new HashSet<MIFInteractionInteractionType>());
        }
        if (mifEntryInteraction.getMifInteractionParticipant() == null) {
            mifEntryInteraction.setMifInteractionParticipant(new HashSet<MIFInteractionParticipant>());
        }        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mifEntryInteraction.setmIFEntrySetEntry(createMIFEntrySetEntry(emf, em, mifEntryInteraction.getmIFEntrySetEntry()));
            mifEntryInteraction.setMifEntryExperiment(createMIFEntryExperiment(emf, em, mifEntryInteraction.getMifEntryExperiment()));            
            
            if (!mifEntryInteraction.getMifInteractionInferredInteraction().isEmpty()) {
                Set<MIFInteractionInferredInteraction> attachedMifInteractionInferredInteraction = new HashSet();
                for (MIFInteractionInferredInteraction mifInteractionInferredInteractionMIFInteractionInferredInteractionToAttach : mifEntryInteraction.getMifInteractionInferredInteraction()) {
                    MIFInteractionInferredInteraction mifInteractionInferredInteraction = em.find(mifInteractionInferredInteractionMIFInteractionInferredInteractionToAttach.getClass(), mifInteractionInferredInteractionMIFInteractionInferredInteractionToAttach.getWid());
                    if (mifInteractionInferredInteraction != null) {
                        attachedMifInteractionInferredInteraction.add(mifInteractionInferredInteraction);
                    } else {
                        attachedMifInteractionInferredInteraction.add(mifInteractionInferredInteractionMIFInteractionInferredInteractionToAttach);
                    }
                }
                mifEntryInteraction.setMifInteractionInferredInteraction(attachedMifInteractionInferredInteraction);
            }
            if (!mifEntryInteraction.getMifInteractionInteractionType().isEmpty()) {
                Set<MIFInteractionInteractionType> attachedMifInteractionInteractionType = new HashSet();
                for (MIFInteractionInteractionType mifInteractionInteractionTypeMIFInteractionInteractionTypeToAttach : mifEntryInteraction.getMifInteractionInteractionType()) {
                    MIFInteractionInteractionType mifInteractionInteractionType = em.find(mifInteractionInteractionTypeMIFInteractionInteractionTypeToAttach.getClass(), mifInteractionInteractionTypeMIFInteractionInteractionTypeToAttach.getWid());
                    if (mifInteractionInteractionType != null) {
                        attachedMifInteractionInteractionType.add(mifInteractionInteractionType);
                    } else {
                        attachedMifInteractionInteractionType.add(mifInteractionInteractionTypeMIFInteractionInteractionTypeToAttach);
                    }
                }
                mifEntryInteraction.setMifInteractionInteractionType(attachedMifInteractionInteractionType);
            }
            mifEntryInteraction.setMifInteractionParticipant(createMIFInteractionParticipant(emf, em, mifEntryInteraction.getMifInteractionParticipant()));
            mifEntryInteraction.setMifOtherAlias(createMIFOtherAlias(emf, em, mifEntryInteraction.getMifOtherAlias()));
            mifEntryInteraction.setMifOtherAttribute(createMIFOtherAttribute(emf, em, mifEntryInteraction.getMifOtherAttribute()));
            mifEntryInteraction.setMifOtherConfidence(createMIFOtherConfidence(emf, em, mifEntryInteraction.getMifOtherConfidence()));
            mifEntryInteraction.setMifOtherXRef(createMIFOtherXRef(emf, em, mifEntryInteraction.getMifOtherXRef()));
            mifEntryInteraction.setMifOtherXRefGO(createMIFOtherXRefGO(emf, em, mifEntryInteraction.getMifOtherXRefGO()));
            mifEntryInteraction.setMifOtherXRefPubMed(createMIFOtherXRefPubMed(emf, em, mifEntryInteraction.getMifOtherXRefPubMed()));
            mifEntryInteraction.setMifOtherXRefRefSeq(createMIFOtherXRefRefSeq(emf, em, mifEntryInteraction.getMifOtherXRefRefSeq()));
            mifEntryInteraction.setMifOtherXRefUniprot(createMIFOtherXRefUniprot(emf, em, mifEntryInteraction.getMifOtherXRefUniprot()));
            mifEntryInteraction.setProtein(createProtein(emf, em, mifEntryInteraction.getProtein()));
            
            em.persist(mifEntryInteraction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMIFEntryInteraction(mifEntryInteraction.getWid()) != null) {
                throw new PreexistingEntityException("MIFEntryInteraction " + mifEntryInteraction + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MIFEntryInteraction mifEntryInteraction) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + mifEntryInteraction.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MIFEntryInteraction persistentMIFEntryInteraction = em.find(MIFEntryInteraction.class, mifEntryInteraction.getWid());
            MIFEntrySetEntry mIFEntrySetEntryOld = persistentMIFEntryInteraction.getmIFEntrySetEntry();
            MIFEntrySetEntry mIFEntrySetEntryNew = mifEntryInteraction.getmIFEntrySetEntry();
            Set<MIFEntryExperiment> mifEntryExperimentOld = persistentMIFEntryInteraction.getMifEntryExperiment();
            Set<MIFEntryExperiment> mifEntryExperimentNew = mifEntryInteraction.getMifEntryExperiment();
            Set<MIFInteractionInferredInteraction> mifInteractionInferredInteractionOld = persistentMIFEntryInteraction.getMifInteractionInferredInteraction();
            Set<MIFInteractionInferredInteraction> mifInteractionInferredInteractionNew = mifEntryInteraction.getMifInteractionInferredInteraction();
            Set<MIFInteractionInteractionType> mifInteractionInteractionTypeOld = persistentMIFEntryInteraction.getMifInteractionInteractionType();
            Set<MIFInteractionInteractionType> mifInteractionInteractionTypeNew = mifEntryInteraction.getMifInteractionInteractionType();
            Set<MIFInteractionParticipant> mifInteractionParticipantOld = persistentMIFEntryInteraction.getMifInteractionParticipant();
            Set<MIFInteractionParticipant> mifInteractionParticipantNew = mifEntryInteraction.getMifInteractionParticipant();
            Set<MIFOtherAlias> mifOtherAliasOld = persistentMIFEntryInteraction.getMifOtherAlias();
            Set<MIFOtherAlias> mifOtherAliasNew = mifEntryInteraction.getMifOtherAlias();
            Set<MIFOtherAttribute> mifOtherAttributeOld = persistentMIFEntryInteraction.getMifOtherAttribute();
            Set<MIFOtherAttribute> mifOtherAttributeNew = mifEntryInteraction.getMifOtherAttribute();
            Set<MIFOtherConfidence> mifOtherConfidenceOld = persistentMIFEntryInteraction.getMifOtherConfidence();
            Set<MIFOtherConfidence> mifOtherConfidenceNew = mifEntryInteraction.getMifOtherConfidence();
            Set<MIFOtherXRef> mifOtherXRefOld = persistentMIFEntryInteraction.getMifOtherXRef();
            Set<MIFOtherXRef> mifOtherXRefNew = mifEntryInteraction.getMifOtherXRef();
            Set<MIFOtherXRefGO> mifOtherXRefGOOld = persistentMIFEntryInteraction.getMifOtherXRefGO();
            Set<MIFOtherXRefGO> mifOtherXRefGONew = mifEntryInteraction.getMifOtherXRefGO();
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMedOld = persistentMIFEntryInteraction.getMifOtherXRefPubMed();
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMedNew = mifEntryInteraction.getMifOtherXRefPubMed();
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeqOld = persistentMIFEntryInteraction.getMifOtherXRefRefSeq();
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeqNew = mifEntryInteraction.getMifOtherXRefRefSeq();
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprotOld = persistentMIFEntryInteraction.getMifOtherXRefUniprot();
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprotNew = mifEntryInteraction.getMifOtherXRefUniprot();
            Set<Protein> proteinOld = persistentMIFEntryInteraction.getProtein();
            Set<Protein> proteinNew = mifEntryInteraction.getProtein();
            if (mIFEntrySetEntryNew != null) {
                mIFEntrySetEntryNew = em.getReference(mIFEntrySetEntryNew.getClass(), mIFEntrySetEntryNew.getWid());
                mifEntryInteraction.setmIFEntrySetEntry(mIFEntrySetEntryNew);
            }
            Set<MIFEntryExperiment> attachedMifEntryExperimentNew = new HashSet();
            for (MIFEntryExperiment mifEntryExperimentNewMIFEntryExperimentToAttach : mifEntryExperimentNew) {
                mifEntryExperimentNewMIFEntryExperimentToAttach = em.getReference(mifEntryExperimentNewMIFEntryExperimentToAttach.getClass(), mifEntryExperimentNewMIFEntryExperimentToAttach.getWid());
                attachedMifEntryExperimentNew.add(mifEntryExperimentNewMIFEntryExperimentToAttach);
            }
            mifEntryExperimentNew = attachedMifEntryExperimentNew;
            mifEntryInteraction.setMifEntryExperiment(mifEntryExperimentNew);
            Set<MIFInteractionInferredInteraction> attachedMifInteractionInferredInteractionNew = new HashSet();
            for (MIFInteractionInferredInteraction mifInteractionInferredInteractionNewMIFInteractionInferredInteractionToAttach : mifInteractionInferredInteractionNew) {
                mifInteractionInferredInteractionNewMIFInteractionInferredInteractionToAttach = em.getReference(mifInteractionInferredInteractionNewMIFInteractionInferredInteractionToAttach.getClass(), mifInteractionInferredInteractionNewMIFInteractionInferredInteractionToAttach.getWid());
                attachedMifInteractionInferredInteractionNew.add(mifInteractionInferredInteractionNewMIFInteractionInferredInteractionToAttach);
            }
            mifInteractionInferredInteractionNew = attachedMifInteractionInferredInteractionNew;
            mifEntryInteraction.setMifInteractionInferredInteraction(mifInteractionInferredInteractionNew);
            Set<MIFInteractionInteractionType> attachedMifInteractionInteractionTypeNew = new HashSet();
            for (MIFInteractionInteractionType mifInteractionInteractionTypeNewMIFInteractionInteractionTypeToAttach : mifInteractionInteractionTypeNew) {
                mifInteractionInteractionTypeNewMIFInteractionInteractionTypeToAttach = em.getReference(mifInteractionInteractionTypeNewMIFInteractionInteractionTypeToAttach.getClass(), mifInteractionInteractionTypeNewMIFInteractionInteractionTypeToAttach.getWid());
                attachedMifInteractionInteractionTypeNew.add(mifInteractionInteractionTypeNewMIFInteractionInteractionTypeToAttach);
            }
            mifInteractionInteractionTypeNew = attachedMifInteractionInteractionTypeNew;
            mifEntryInteraction.setMifInteractionInteractionType(mifInteractionInteractionTypeNew);
            Set<MIFInteractionParticipant> attachedMifInteractionParticipantNew = new HashSet();
            for (MIFInteractionParticipant mifInteractionParticipantNewMIFInteractionParticipantToAttach : mifInteractionParticipantNew) {
                mifInteractionParticipantNewMIFInteractionParticipantToAttach = em.getReference(mifInteractionParticipantNewMIFInteractionParticipantToAttach.getClass(), mifInteractionParticipantNewMIFInteractionParticipantToAttach.getWid());
                attachedMifInteractionParticipantNew.add(mifInteractionParticipantNewMIFInteractionParticipantToAttach);
            }
            mifInteractionParticipantNew = attachedMifInteractionParticipantNew;
            mifEntryInteraction.setMifInteractionParticipant(mifInteractionParticipantNew);
            Set<MIFOtherAlias> attachedMifOtherAliasNew = new HashSet();
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAliasToAttach : mifOtherAliasNew) {
                mifOtherAliasNewMIFOtherAliasToAttach = em.getReference(mifOtherAliasNewMIFOtherAliasToAttach.getClass(), mifOtherAliasNewMIFOtherAliasToAttach.getWid());
                attachedMifOtherAliasNew.add(mifOtherAliasNewMIFOtherAliasToAttach);
            }
            mifOtherAliasNew = attachedMifOtherAliasNew;
            mifEntryInteraction.setMifOtherAlias(mifOtherAliasNew);
            Set<MIFOtherAttribute> attachedMifOtherAttributeNew = new HashSet();
            for (MIFOtherAttribute mifOtherAttributeNewMIFOtherAttributeToAttach : mifOtherAttributeNew) {
                mifOtherAttributeNewMIFOtherAttributeToAttach = em.getReference(mifOtherAttributeNewMIFOtherAttributeToAttach.getClass(), mifOtherAttributeNewMIFOtherAttributeToAttach.getWid());
                attachedMifOtherAttributeNew.add(mifOtherAttributeNewMIFOtherAttributeToAttach);
            }
            mifOtherAttributeNew = attachedMifOtherAttributeNew;
            mifEntryInteraction.setMifOtherAttribute(mifOtherAttributeNew);
            Set<MIFOtherConfidence> attachedMifOtherConfidenceNew = new HashSet();
            for (MIFOtherConfidence mifOtherConfidenceNewMIFOtherConfidenceToAttach : mifOtherConfidenceNew) {
                mifOtherConfidenceNewMIFOtherConfidenceToAttach = em.getReference(mifOtherConfidenceNewMIFOtherConfidenceToAttach.getClass(), mifOtherConfidenceNewMIFOtherConfidenceToAttach.getWid());
                attachedMifOtherConfidenceNew.add(mifOtherConfidenceNewMIFOtherConfidenceToAttach);
            }
            mifOtherConfidenceNew = attachedMifOtherConfidenceNew;
            mifEntryInteraction.setMifOtherConfidence(mifOtherConfidenceNew);
            Set<MIFOtherXRef> attachedMifOtherXRefNew = new HashSet();
            for (MIFOtherXRef mifOtherXRefNewMIFOtherXRefToAttach : mifOtherXRefNew) {
                mifOtherXRefNewMIFOtherXRefToAttach = em.getReference(mifOtherXRefNewMIFOtherXRefToAttach.getClass(), mifOtherXRefNewMIFOtherXRefToAttach.getWid());
                attachedMifOtherXRefNew.add(mifOtherXRefNewMIFOtherXRefToAttach);
            }
            mifOtherXRefNew = attachedMifOtherXRefNew;
            mifEntryInteraction.setMifOtherXRef(mifOtherXRefNew);
            Set<MIFOtherXRefGO> attachedMifOtherXRefGONew = new HashSet();
            for (MIFOtherXRefGO mifOtherXRefGONewMIFOtherXRefGOToAttach : mifOtherXRefGONew) {
                mifOtherXRefGONewMIFOtherXRefGOToAttach = em.getReference(mifOtherXRefGONewMIFOtherXRefGOToAttach.getClass(), mifOtherXRefGONewMIFOtherXRefGOToAttach.getWid());
                attachedMifOtherXRefGONew.add(mifOtherXRefGONewMIFOtherXRefGOToAttach);
            }
            mifOtherXRefGONew = attachedMifOtherXRefGONew;
            mifEntryInteraction.setMifOtherXRefGO(mifOtherXRefGONew);
            Set<MIFOtherXRefPubMed> attachedMifOtherXRefPubMedNew = new HashSet();
            for (MIFOtherXRefPubMed mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach : mifOtherXRefPubMedNew) {
                mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach = em.getReference(mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach.getClass(), mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach.getWid());
                attachedMifOtherXRefPubMedNew.add(mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach);
            }
            mifOtherXRefPubMedNew = attachedMifOtherXRefPubMedNew;
            mifEntryInteraction.setMifOtherXRefPubMed(mifOtherXRefPubMedNew);
            Set<MIFOtherXRefRefSeq> attachedMifOtherXRefRefSeqNew = new HashSet();
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach : mifOtherXRefRefSeqNew) {
                mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach = em.getReference(mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach.getClass(), mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach.getWid());
                attachedMifOtherXRefRefSeqNew.add(mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach);
            }
            mifOtherXRefRefSeqNew = attachedMifOtherXRefRefSeqNew;
            mifEntryInteraction.setMifOtherXRefRefSeq(mifOtherXRefRefSeqNew);
            Set<MIFOtherXRefUniprot> attachedMifOtherXRefUniprotNew = new HashSet();
            for (MIFOtherXRefUniprot mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach : mifOtherXRefUniprotNew) {
                mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach = em.getReference(mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach.getClass(), mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach.getWid());
                attachedMifOtherXRefUniprotNew.add(mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach);
            }
            mifOtherXRefUniprotNew = attachedMifOtherXRefUniprotNew;
            mifEntryInteraction.setMifOtherXRefUniprot(mifOtherXRefUniprotNew);
            Set<Protein> attachedProteinNew = new HashSet();
            for (Protein proteinNewProteinToAttach : proteinNew) {
                proteinNewProteinToAttach = em.getReference(proteinNewProteinToAttach.getClass(), proteinNewProteinToAttach.getWid());
                attachedProteinNew.add(proteinNewProteinToAttach);
            }
            proteinNew = attachedProteinNew;
            mifEntryInteraction.setProtein(proteinNew);
            mifEntryInteraction = em.merge(mifEntryInteraction);
            if (mIFEntrySetEntryOld != null && !mIFEntrySetEntryOld.equals(mIFEntrySetEntryNew)) {
                mIFEntrySetEntryOld.getMifEntryInteraction().remove(mifEntryInteraction);
                mIFEntrySetEntryOld = em.merge(mIFEntrySetEntryOld);
            }
            if (mIFEntrySetEntryNew != null && !mIFEntrySetEntryNew.equals(mIFEntrySetEntryOld)) {
                mIFEntrySetEntryNew.getMifEntryInteraction().add(mifEntryInteraction);
                em.merge(mIFEntrySetEntryNew);
            }
            for (MIFEntryExperiment mifEntryExperimentOldMIFEntryExperiment : mifEntryExperimentOld) {
                if (!mifEntryExperimentNew.contains(mifEntryExperimentOldMIFEntryExperiment)) {
                    mifEntryExperimentOldMIFEntryExperiment.setMifEntryInteraction(null);
                    mifEntryExperimentOldMIFEntryExperiment = em.merge(mifEntryExperimentOldMIFEntryExperiment);
                }
            }
            for (MIFEntryExperiment mifEntryExperimentNewMIFEntryExperiment : mifEntryExperimentNew) {
                if (!mifEntryExperimentOld.contains(mifEntryExperimentNewMIFEntryExperiment)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifEntryExperimentNewMIFEntryExperiment = mifEntryExperimentNewMIFEntryExperiment.getMifEntryInteraction();
                    mifEntryExperimentNewMIFEntryExperiment.setMifEntryInteraction(mifEntryInteraction);
                    mifEntryExperimentNewMIFEntryExperiment = em.merge(mifEntryExperimentNewMIFEntryExperiment);
                    if (oldMifEntryInteractionOfMifEntryExperimentNewMIFEntryExperiment != null && !oldMifEntryInteractionOfMifEntryExperimentNewMIFEntryExperiment.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifEntryExperimentNewMIFEntryExperiment.getMifEntryExperiment().remove(mifEntryExperimentNewMIFEntryExperiment);
                        em.merge(oldMifEntryInteractionOfMifEntryExperimentNewMIFEntryExperiment);
                    }
                }
            }
            for (MIFInteractionInferredInteraction mifInteractionInferredInteractionOldMIFInteractionInferredInteraction : mifInteractionInferredInteractionOld) {
                if (!mifInteractionInferredInteractionNew.contains(mifInteractionInferredInteractionOldMIFInteractionInferredInteraction)) {
                    mifInteractionInferredInteractionOldMIFInteractionInferredInteraction.setMifEntryInteraction(null);
                    mifInteractionInferredInteractionOldMIFInteractionInferredInteraction = em.merge(mifInteractionInferredInteractionOldMIFInteractionInferredInteraction);
                }
            }
            for (MIFInteractionInferredInteraction mifInteractionInferredInteractionNewMIFInteractionInferredInteraction : mifInteractionInferredInteractionNew) {
                if (!mifInteractionInferredInteractionOld.contains(mifInteractionInferredInteractionNewMIFInteractionInferredInteraction)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifInteractionInferredInteractionNewMIFInteractionInferredInteraction = mifInteractionInferredInteractionNewMIFInteractionInferredInteraction.getMifEntryInteraction();
                    mifInteractionInferredInteractionNewMIFInteractionInferredInteraction.setMifEntryInteraction(mifEntryInteraction);
                    mifInteractionInferredInteractionNewMIFInteractionInferredInteraction = em.merge(mifInteractionInferredInteractionNewMIFInteractionInferredInteraction);
                    if (oldMifEntryInteractionOfMifInteractionInferredInteractionNewMIFInteractionInferredInteraction != null && !oldMifEntryInteractionOfMifInteractionInferredInteractionNewMIFInteractionInferredInteraction.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifInteractionInferredInteractionNewMIFInteractionInferredInteraction.getMifInteractionInferredInteraction().remove(mifInteractionInferredInteractionNewMIFInteractionInferredInteraction);
                        em.merge(oldMifEntryInteractionOfMifInteractionInferredInteractionNewMIFInteractionInferredInteraction);
                    }
                }
            }
            for (MIFInteractionInteractionType mifInteractionInteractionTypeOldMIFInteractionInteractionType : mifInteractionInteractionTypeOld) {
                if (!mifInteractionInteractionTypeNew.contains(mifInteractionInteractionTypeOldMIFInteractionInteractionType)) {
                    mifInteractionInteractionTypeOldMIFInteractionInteractionType.setMifEntryInteraction(null);
                    mifInteractionInteractionTypeOldMIFInteractionInteractionType = em.merge(mifInteractionInteractionTypeOldMIFInteractionInteractionType);
                }
            }
            for (MIFInteractionInteractionType mifInteractionInteractionTypeNewMIFInteractionInteractionType : mifInteractionInteractionTypeNew) {
                if (!mifInteractionInteractionTypeOld.contains(mifInteractionInteractionTypeNewMIFInteractionInteractionType)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifInteractionInteractionTypeNewMIFInteractionInteractionType = mifInteractionInteractionTypeNewMIFInteractionInteractionType.getMifEntryInteraction();
                    mifInteractionInteractionTypeNewMIFInteractionInteractionType.setMifEntryInteraction(mifEntryInteraction);
                    mifInteractionInteractionTypeNewMIFInteractionInteractionType = em.merge(mifInteractionInteractionTypeNewMIFInteractionInteractionType);
                    if (oldMifEntryInteractionOfMifInteractionInteractionTypeNewMIFInteractionInteractionType != null && !oldMifEntryInteractionOfMifInteractionInteractionTypeNewMIFInteractionInteractionType.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifInteractionInteractionTypeNewMIFInteractionInteractionType.getMifInteractionInteractionType().remove(mifInteractionInteractionTypeNewMIFInteractionInteractionType);
                        em.merge(oldMifEntryInteractionOfMifInteractionInteractionTypeNewMIFInteractionInteractionType);
                    }
                }
            }
            for (MIFInteractionParticipant mifInteractionParticipantOldMIFInteractionParticipant : mifInteractionParticipantOld) {
                if (!mifInteractionParticipantNew.contains(mifInteractionParticipantOldMIFInteractionParticipant)) {
                    mifInteractionParticipantOldMIFInteractionParticipant.setMifEntryInteraction(null);
                    mifInteractionParticipantOldMIFInteractionParticipant = em.merge(mifInteractionParticipantOldMIFInteractionParticipant);
                }
            }
            for (MIFInteractionParticipant mifInteractionParticipantNewMIFInteractionParticipant : mifInteractionParticipantNew) {
                if (!mifInteractionParticipantOld.contains(mifInteractionParticipantNewMIFInteractionParticipant)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifInteractionParticipantNewMIFInteractionParticipant = mifInteractionParticipantNewMIFInteractionParticipant.getMifEntryInteraction();
                    mifInteractionParticipantNewMIFInteractionParticipant.setMifEntryInteraction(mifEntryInteraction);
                    mifInteractionParticipantNewMIFInteractionParticipant = em.merge(mifInteractionParticipantNewMIFInteractionParticipant);
                    if (oldMifEntryInteractionOfMifInteractionParticipantNewMIFInteractionParticipant != null && !oldMifEntryInteractionOfMifInteractionParticipantNewMIFInteractionParticipant.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifInteractionParticipantNewMIFInteractionParticipant.getMifInteractionParticipant().remove(mifInteractionParticipantNewMIFInteractionParticipant);
                        em.merge(oldMifEntryInteractionOfMifInteractionParticipantNewMIFInteractionParticipant);
                    }
                }
            }
            for (MIFOtherAlias mifOtherAliasOldMIFOtherAlias : mifOtherAliasOld) {
                if (!mifOtherAliasNew.contains(mifOtherAliasOldMIFOtherAlias)) {
                    mifOtherAliasOldMIFOtherAlias.setMifEntryInteraction(null);
                    mifOtherAliasOldMIFOtherAlias = em.merge(mifOtherAliasOldMIFOtherAlias);
                }
            }
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAlias : mifOtherAliasNew) {
                if (!mifOtherAliasOld.contains(mifOtherAliasNewMIFOtherAlias)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifOtherAliasNewMIFOtherAlias = mifOtherAliasNewMIFOtherAlias.getMifEntryInteraction();
                    mifOtherAliasNewMIFOtherAlias.setMifEntryInteraction(mifEntryInteraction);
                    mifOtherAliasNewMIFOtherAlias = em.merge(mifOtherAliasNewMIFOtherAlias);
                    if (oldMifEntryInteractionOfMifOtherAliasNewMIFOtherAlias != null && !oldMifEntryInteractionOfMifOtherAliasNewMIFOtherAlias.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifOtherAliasNewMIFOtherAlias.getMifOtherAlias().remove(mifOtherAliasNewMIFOtherAlias);
                        em.merge(oldMifEntryInteractionOfMifOtherAliasNewMIFOtherAlias);
                    }
                }
            }
            for (MIFOtherAttribute mifOtherAttributeOldMIFOtherAttribute : mifOtherAttributeOld) {
                if (!mifOtherAttributeNew.contains(mifOtherAttributeOldMIFOtherAttribute)) {
                    mifOtherAttributeOldMIFOtherAttribute.setMifEntryInteraction(null);
                    mifOtherAttributeOldMIFOtherAttribute = em.merge(mifOtherAttributeOldMIFOtherAttribute);
                }
            }
            for (MIFOtherAttribute mifOtherAttributeNewMIFOtherAttribute : mifOtherAttributeNew) {
                if (!mifOtherAttributeOld.contains(mifOtherAttributeNewMIFOtherAttribute)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifOtherAttributeNewMIFOtherAttribute = mifOtherAttributeNewMIFOtherAttribute.getMifEntryInteraction();
                    mifOtherAttributeNewMIFOtherAttribute.setMifEntryInteraction(mifEntryInteraction);
                    mifOtherAttributeNewMIFOtherAttribute = em.merge(mifOtherAttributeNewMIFOtherAttribute);
                    if (oldMifEntryInteractionOfMifOtherAttributeNewMIFOtherAttribute != null && !oldMifEntryInteractionOfMifOtherAttributeNewMIFOtherAttribute.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifOtherAttributeNewMIFOtherAttribute.getMifOtherAttribute().remove(mifOtherAttributeNewMIFOtherAttribute);
                        em.merge(oldMifEntryInteractionOfMifOtherAttributeNewMIFOtherAttribute);
                    }
                }
            }
            for (MIFOtherConfidence mifOtherConfidenceOldMIFOtherConfidence : mifOtherConfidenceOld) {
                if (!mifOtherConfidenceNew.contains(mifOtherConfidenceOldMIFOtherConfidence)) {
                    mifOtherConfidenceOldMIFOtherConfidence.setMifEntryInteraction(null);
                    mifOtherConfidenceOldMIFOtherConfidence = em.merge(mifOtherConfidenceOldMIFOtherConfidence);
                }
            }
            for (MIFOtherConfidence mifOtherConfidenceNewMIFOtherConfidence : mifOtherConfidenceNew) {
                if (!mifOtherConfidenceOld.contains(mifOtherConfidenceNewMIFOtherConfidence)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifOtherConfidenceNewMIFOtherConfidence = mifOtherConfidenceNewMIFOtherConfidence.getMifEntryInteraction();
                    mifOtherConfidenceNewMIFOtherConfidence.setMifEntryInteraction(mifEntryInteraction);
                    mifOtherConfidenceNewMIFOtherConfidence = em.merge(mifOtherConfidenceNewMIFOtherConfidence);
                    if (oldMifEntryInteractionOfMifOtherConfidenceNewMIFOtherConfidence != null && !oldMifEntryInteractionOfMifOtherConfidenceNewMIFOtherConfidence.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifOtherConfidenceNewMIFOtherConfidence.getMifOtherConfidence().remove(mifOtherConfidenceNewMIFOtherConfidence);
                        em.merge(oldMifEntryInteractionOfMifOtherConfidenceNewMIFOtherConfidence);
                    }
                }
            }
            for (MIFOtherXRef mifOtherXRefOldMIFOtherXRef : mifOtherXRefOld) {
                if (!mifOtherXRefNew.contains(mifOtherXRefOldMIFOtherXRef)) {
                    mifOtherXRefOldMIFOtherXRef.setMifEntryInteraction(null);
                    mifOtherXRefOldMIFOtherXRef = em.merge(mifOtherXRefOldMIFOtherXRef);
                }
            }
            for (MIFOtherXRef mifOtherXRefNewMIFOtherXRef : mifOtherXRefNew) {
                if (!mifOtherXRefOld.contains(mifOtherXRefNewMIFOtherXRef)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifOtherXRefNewMIFOtherXRef = mifOtherXRefNewMIFOtherXRef.getMifEntryInteraction();
                    mifOtherXRefNewMIFOtherXRef.setMifEntryInteraction(mifEntryInteraction);
                    mifOtherXRefNewMIFOtherXRef = em.merge(mifOtherXRefNewMIFOtherXRef);
                    if (oldMifEntryInteractionOfMifOtherXRefNewMIFOtherXRef != null && !oldMifEntryInteractionOfMifOtherXRefNewMIFOtherXRef.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifOtherXRefNewMIFOtherXRef.getMifOtherXRef().remove(mifOtherXRefNewMIFOtherXRef);
                        em.merge(oldMifEntryInteractionOfMifOtherXRefNewMIFOtherXRef);
                    }
                }
            }
            for (MIFOtherXRefGO mifOtherXRefGOOldMIFOtherXRefGO : mifOtherXRefGOOld) {
                if (!mifOtherXRefGONew.contains(mifOtherXRefGOOldMIFOtherXRefGO)) {
                    mifOtherXRefGOOldMIFOtherXRefGO.setMifEntryInteraction(null);
                    mifOtherXRefGOOldMIFOtherXRefGO = em.merge(mifOtherXRefGOOldMIFOtherXRefGO);
                }
            }
            for (MIFOtherXRefGO mifOtherXRefGONewMIFOtherXRefGO : mifOtherXRefGONew) {
                if (!mifOtherXRefGOOld.contains(mifOtherXRefGONewMIFOtherXRefGO)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifOtherXRefGONewMIFOtherXRefGO = mifOtherXRefGONewMIFOtherXRefGO.getMifEntryInteraction();
                    mifOtherXRefGONewMIFOtherXRefGO.setMifEntryInteraction(mifEntryInteraction);
                    mifOtherXRefGONewMIFOtherXRefGO = em.merge(mifOtherXRefGONewMIFOtherXRefGO);
                    if (oldMifEntryInteractionOfMifOtherXRefGONewMIFOtherXRefGO != null && !oldMifEntryInteractionOfMifOtherXRefGONewMIFOtherXRefGO.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifOtherXRefGONewMIFOtherXRefGO.getMifOtherXRefGO().remove(mifOtherXRefGONewMIFOtherXRefGO);
                        em.merge(oldMifEntryInteractionOfMifOtherXRefGONewMIFOtherXRefGO);
                    }
                }
            }
            for (MIFOtherXRefPubMed mifOtherXRefPubMedOldMIFOtherXRefPubMed : mifOtherXRefPubMedOld) {
                if (!mifOtherXRefPubMedNew.contains(mifOtherXRefPubMedOldMIFOtherXRefPubMed)) {
                    mifOtherXRefPubMedOldMIFOtherXRefPubMed.setMifEntryInteraction(null);
                    mifOtherXRefPubMedOldMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedOldMIFOtherXRefPubMed);
                }
            }
            for (MIFOtherXRefPubMed mifOtherXRefPubMedNewMIFOtherXRefPubMed : mifOtherXRefPubMedNew) {
                if (!mifOtherXRefPubMedOld.contains(mifOtherXRefPubMedNewMIFOtherXRefPubMed)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifOtherXRefPubMedNewMIFOtherXRefPubMed = mifOtherXRefPubMedNewMIFOtherXRefPubMed.getMifEntryInteraction();
                    mifOtherXRefPubMedNewMIFOtherXRefPubMed.setMifEntryInteraction(mifEntryInteraction);
                    mifOtherXRefPubMedNewMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedNewMIFOtherXRefPubMed);
                    if (oldMifEntryInteractionOfMifOtherXRefPubMedNewMIFOtherXRefPubMed != null && !oldMifEntryInteractionOfMifOtherXRefPubMedNewMIFOtherXRefPubMed.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifOtherXRefPubMedNewMIFOtherXRefPubMed.getMifOtherXRefPubMed().remove(mifOtherXRefPubMedNewMIFOtherXRefPubMed);
                        em.merge(oldMifEntryInteractionOfMifOtherXRefPubMedNewMIFOtherXRefPubMed);
                    }
                }
            }
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqOldMIFOtherXRefRefSeq : mifOtherXRefRefSeqOld) {
                if (!mifOtherXRefRefSeqNew.contains(mifOtherXRefRefSeqOldMIFOtherXRefRefSeq)) {
                    mifOtherXRefRefSeqOldMIFOtherXRefRefSeq.setMifEntryInteraction(null);
                    mifOtherXRefRefSeqOldMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqOldMIFOtherXRefRefSeq);
                }
            }
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqNewMIFOtherXRefRefSeq : mifOtherXRefRefSeqNew) {
                if (!mifOtherXRefRefSeqOld.contains(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq = mifOtherXRefRefSeqNewMIFOtherXRefRefSeq.getMifEntryInteraction();
                    mifOtherXRefRefSeqNewMIFOtherXRefRefSeq.setMifEntryInteraction(mifEntryInteraction);
                    mifOtherXRefRefSeqNewMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                    if (oldMifEntryInteractionOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq != null && !oldMifEntryInteractionOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq.getMifOtherXRefRefSeq().remove(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                        em.merge(oldMifEntryInteractionOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                    }
                }
            }
            for (MIFOtherXRefUniprot mifOtherXRefUniprotOldMIFOtherXRefUniprot : mifOtherXRefUniprotOld) {
                if (!mifOtherXRefUniprotNew.contains(mifOtherXRefUniprotOldMIFOtherXRefUniprot)) {
                    mifOtherXRefUniprotOldMIFOtherXRefUniprot.setMifEntryInteraction(null);
                    mifOtherXRefUniprotOldMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotOldMIFOtherXRefUniprot);
                }
            }
            for (MIFOtherXRefUniprot mifOtherXRefUniprotNewMIFOtherXRefUniprot : mifOtherXRefUniprotNew) {
                if (!mifOtherXRefUniprotOld.contains(mifOtherXRefUniprotNewMIFOtherXRefUniprot)) {
                    MIFEntryInteraction oldMifEntryInteractionOfMifOtherXRefUniprotNewMIFOtherXRefUniprot = mifOtherXRefUniprotNewMIFOtherXRefUniprot.getMifEntryInteraction();
                    mifOtherXRefUniprotNewMIFOtherXRefUniprot.setMifEntryInteraction(mifEntryInteraction);
                    mifOtherXRefUniprotNewMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotNewMIFOtherXRefUniprot);
                    if (oldMifEntryInteractionOfMifOtherXRefUniprotNewMIFOtherXRefUniprot != null && !oldMifEntryInteractionOfMifOtherXRefUniprotNewMIFOtherXRefUniprot.equals(mifEntryInteraction)) {
                        oldMifEntryInteractionOfMifOtherXRefUniprotNewMIFOtherXRefUniprot.getMifOtherXRefUniprot().remove(mifOtherXRefUniprotNewMIFOtherXRefUniprot);
                        em.merge(oldMifEntryInteractionOfMifOtherXRefUniprotNewMIFOtherXRefUniprot);
                    }
                }
            }
            for (Protein proteinOldProtein : proteinOld) {
                if (!proteinNew.contains(proteinOldProtein)) {
                    proteinOldProtein.getmIFEntryInteraction().remove(mifEntryInteraction);
                    proteinOldProtein = em.merge(proteinOldProtein);
                }
            }
            for (Protein proteinNewProtein : proteinNew) {
                if (!proteinOld.contains(proteinNewProtein)) {
                    proteinNewProtein.getmIFEntryInteraction().add(mifEntryInteraction);
                    proteinNewProtein = em.merge(proteinNewProtein);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mifEntryInteraction.getWid();
                if (findMIFEntryInteraction(id) == null) {
                    throw new NonexistentEntityException("The mIFEntryInteraction with id " + id + " no longer exists.");
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
            MIFEntryInteraction MIFEntryInteraction;
            try {
                MIFEntryInteraction = em.getReference(MIFEntryInteraction.class, id);
                MIFEntryInteraction.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MIFEntryInteraction with id " + id + " no longer exists.", enfe);
            }
            MIFEntrySetEntry mIFEntrySetEntry = MIFEntryInteraction.getmIFEntrySetEntry();
            if (mIFEntrySetEntry != null) {
                mIFEntrySetEntry.getMifEntryInteraction().remove(MIFEntryInteraction);
                em.merge(mIFEntrySetEntry);
            }
            Set<MIFEntryExperiment> mifEntryExperiment = MIFEntryInteraction.getMifEntryExperiment();
            for (MIFEntryExperiment mifEntryExperimentMIFEntryExperiment : mifEntryExperiment) {
                mifEntryExperimentMIFEntryExperiment.setMifEntryInteraction(null);
                mifEntryExperimentMIFEntryExperiment = em.merge(mifEntryExperimentMIFEntryExperiment);
            }
            Set<MIFInteractionInferredInteraction> mifInteractionInferredInteraction = MIFEntryInteraction.getMifInteractionInferredInteraction();
            for (MIFInteractionInferredInteraction mifInteractionInferredInteractionMIFInteractionInferredInteraction : mifInteractionInferredInteraction) {
                mifInteractionInferredInteractionMIFInteractionInferredInteraction.setMifEntryInteraction(null);
                mifInteractionInferredInteractionMIFInteractionInferredInteraction = em.merge(mifInteractionInferredInteractionMIFInteractionInferredInteraction);
            }
            Set<MIFInteractionInteractionType> mifInteractionInteractionType = MIFEntryInteraction.getMifInteractionInteractionType();
            for (MIFInteractionInteractionType mifInteractionInteractionTypeMIFInteractionInteractionType : mifInteractionInteractionType) {
                mifInteractionInteractionTypeMIFInteractionInteractionType.setMifEntryInteraction(null);
                mifInteractionInteractionTypeMIFInteractionInteractionType = em.merge(mifInteractionInteractionTypeMIFInteractionInteractionType);
            }
            Set<MIFInteractionParticipant> mifInteractionParticipant = MIFEntryInteraction.getMifInteractionParticipant();
            for (MIFInteractionParticipant mifInteractionParticipantMIFInteractionParticipant : mifInteractionParticipant) {
                mifInteractionParticipantMIFInteractionParticipant.setMifEntryInteraction(null);
                mifInteractionParticipantMIFInteractionParticipant = em.merge(mifInteractionParticipantMIFInteractionParticipant);
            }
            Set<MIFOtherAlias> mifOtherAlias = MIFEntryInteraction.getMifOtherAlias();
            for (MIFOtherAlias mifOtherAliasMIFOtherAlias : mifOtherAlias) {
                mifOtherAliasMIFOtherAlias.setMifEntryInteraction(null);
                mifOtherAliasMIFOtherAlias = em.merge(mifOtherAliasMIFOtherAlias);
            }
            Set<MIFOtherAttribute> mifOtherAttribute = MIFEntryInteraction.getMifOtherAttribute();
            for (MIFOtherAttribute mifOtherAttributeMIFOtherAttribute : mifOtherAttribute) {
                mifOtherAttributeMIFOtherAttribute.setMifEntryInteraction(null);
                mifOtherAttributeMIFOtherAttribute = em.merge(mifOtherAttributeMIFOtherAttribute);
            }
            Set<MIFOtherConfidence> mifOtherConfidence = MIFEntryInteraction.getMifOtherConfidence();
            for (MIFOtherConfidence mifOtherConfidenceMIFOtherConfidence : mifOtherConfidence) {
                mifOtherConfidenceMIFOtherConfidence.setMifEntryInteraction(null);
                mifOtherConfidenceMIFOtherConfidence = em.merge(mifOtherConfidenceMIFOtherConfidence);
            }
            Set<MIFOtherXRef> mifOtherXRef = MIFEntryInteraction.getMifOtherXRef();
            for (MIFOtherXRef mifOtherXRefMIFOtherXRef : mifOtherXRef) {
                mifOtherXRefMIFOtherXRef.setMifEntryInteraction(null);
                mifOtherXRefMIFOtherXRef = em.merge(mifOtherXRefMIFOtherXRef);
            }
            Set<MIFOtherXRefGO> mifOtherXRefGO = MIFEntryInteraction.getMifOtherXRefGO();
            for (MIFOtherXRefGO mifOtherXRefGOMIFOtherXRefGO : mifOtherXRefGO) {
                mifOtherXRefGOMIFOtherXRefGO.setMifEntryInteraction(null);
                mifOtherXRefGOMIFOtherXRefGO = em.merge(mifOtherXRefGOMIFOtherXRefGO);
            }
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMed = MIFEntryInteraction.getMifOtherXRefPubMed();
            for (MIFOtherXRefPubMed mifOtherXRefPubMedMIFOtherXRefPubMed : mifOtherXRefPubMed) {
                mifOtherXRefPubMedMIFOtherXRefPubMed.setMifEntryInteraction(null);
                mifOtherXRefPubMedMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedMIFOtherXRefPubMed);
            }
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq = MIFEntryInteraction.getMifOtherXRefRefSeq();
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqMIFOtherXRefRefSeq : mifOtherXRefRefSeq) {
                mifOtherXRefRefSeqMIFOtherXRefRefSeq.setMifEntryInteraction(null);
                mifOtherXRefRefSeqMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqMIFOtherXRefRefSeq);
            }
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprot = MIFEntryInteraction.getMifOtherXRefUniprot();
            for (MIFOtherXRefUniprot mifOtherXRefUniprotMIFOtherXRefUniprot : mifOtherXRefUniprot) {
                mifOtherXRefUniprotMIFOtherXRefUniprot.setMifEntryInteraction(null);
                mifOtherXRefUniprotMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotMIFOtherXRefUniprot);
            }
            Set<Protein> protein = MIFEntryInteraction.getProtein();
            for (Protein proteinProtein : protein) {
                proteinProtein.getmIFEntryInteraction().remove(MIFEntryInteraction);
                proteinProtein = em.merge(proteinProtein);
            }
            em.remove(MIFEntryInteraction);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MIFEntryInteraction> findMIFEntryInteractionEntities() {
        return findMIFEntryInteractionEntities(true, -1, -1);
    }

    public List<MIFEntryInteraction> findMIFEntryInteractionEntities(int maxResults, int firstResult) {
        return findMIFEntryInteractionEntities(false, maxResults, firstResult);
    }

    private List<MIFEntryInteraction> findMIFEntryInteractionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MIFEntryInteraction.class));
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

    public MIFEntryInteraction findMIFEntryInteraction(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MIFEntryInteraction.class, id);
        } finally {
            em.close();
        }
    }

    public int getMIFEntryInteractionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MIFEntryInteraction> rt = cq.from(MIFEntryInteraction.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
