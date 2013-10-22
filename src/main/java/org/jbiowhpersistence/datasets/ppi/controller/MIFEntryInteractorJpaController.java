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
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteractor;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.datasets.ppi.entities.MIFInteractionParticipant;
import org.jbiowhpersistence.datasets.ppi.entities.MIFInteractorInteractorType;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAlias;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAttribute;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherBioSourceType;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRef;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefGO;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefPubMed;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefRefSeq;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefUniprot;
import org.jbiowhpersistence.datasets.ppi.entities.MIFParticipantExperimentalInteractor;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the MIFEntryInteractor Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 14, 2012
 */
public class MIFEntryInteractorJpaController extends AbstractMIFJpaController<MIFEntryInteractor> implements Serializable {

    public MIFEntryInteractorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MIFEntryInteractor mifEntryInteractor) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + mifEntryInteractor.getWid());
        if (mifEntryInteractor.getMifInteractorInteractorType() == null) {
            mifEntryInteractor.setMifInteractorInteractorType(new HashSet<MIFInteractorInteractorType>());
        }        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mifEntryInteractor.setMifEntrySetEntry(createMIFEntrySetEntry(emf, em, mifEntryInteractor.getMifEntrySetEntry()));

            MIFInteractionParticipant mifInteractionParticipant = mifEntryInteractor.getMifInteractionParticipant();
            if (mifInteractionParticipant != null) {
                mifInteractionParticipant = em.find(mifInteractionParticipant.getClass(), mifInteractionParticipant.getWid());
                if (mifInteractionParticipant != null) {
                    mifEntryInteractor.setMifInteractionParticipant(mifInteractionParticipant);
                } else {
                    mifEntryInteractor.setMifInteractionParticipant(mifEntryInteractor.getMifInteractionParticipant());
                }
            }
            MIFParticipantExperimentalInteractor mifParticipantExperimentalInteractor = mifEntryInteractor.getMifParticipantExperimentalInteractor();
            if (mifParticipantExperimentalInteractor != null) {
                mifParticipantExperimentalInteractor = em.find(mifParticipantExperimentalInteractor.getClass(), mifParticipantExperimentalInteractor.getWid());
                if (mifParticipantExperimentalInteractor != null) {
                    mifEntryInteractor.setMifParticipantExperimentalInteractor(mifParticipantExperimentalInteractor);
                } else {
                    mifEntryInteractor.setMifParticipantExperimentalInteractor(mifEntryInteractor.getMifParticipantExperimentalInteractor());
                }
            }
            if (!mifEntryInteractor.getMifInteractorInteractorType().isEmpty()) {
                Set<MIFInteractorInteractorType> attachedMifInteractorInteractorType = new HashSet<>();
                for (MIFInteractorInteractorType mifInteractorInteractorTypeMIFInteractorInteractorTypeToAttach : mifEntryInteractor.getMifInteractorInteractorType()) {
                    MIFInteractorInteractorType mifInteractorInteractorType = em.find(mifInteractorInteractorTypeMIFInteractorInteractorTypeToAttach.getClass(), mifInteractorInteractorTypeMIFInteractorInteractorTypeToAttach.getWid());
                    if (mifInteractorInteractorType != null) {
                        attachedMifInteractorInteractorType.add(mifInteractorInteractorType);
                    } else {
                        attachedMifInteractorInteractorType.add(mifInteractorInteractorTypeMIFInteractorInteractorTypeToAttach);
                    }
                }
                mifEntryInteractor.setMifInteractorInteractorType(attachedMifInteractorInteractorType);
            }
            mifEntryInteractor.setMifOtherAlias(createMIFOtherAlias(emf, em, mifEntryInteractor.getMifOtherAlias()));
            mifEntryInteractor.setMifOtherAttribute(createMIFOtherAttribute(emf, em, mifEntryInteractor.getMifOtherAttribute()));
            mifEntryInteractor.setMifOtherBioSourceType(createMIFOtherBioSourceType(emf, em, mifEntryInteractor.getMifOtherBioSourceType()));
            mifEntryInteractor.setMifOtherXRef(createMIFOtherXRef(emf, em, mifEntryInteractor.getMifOtherXRef()));
            mifEntryInteractor.setMifOtherXRefGO(createMIFOtherXRefGO(emf, em, mifEntryInteractor.getMifOtherXRefGO()));
            mifEntryInteractor.setMifOtherXRefPubMed(createMIFOtherXRefPubMed(emf, em, mifEntryInteractor.getMifOtherXRefPubMed()));
            mifEntryInteractor.setMifOtherXRefRefSeq(createMIFOtherXRefRefSeq(emf, em, mifEntryInteractor.getMifOtherXRefRefSeq()));
            mifEntryInteractor.setMifOtherXRefUniprot(createMIFOtherXRefUniprot(emf, em, mifEntryInteractor.getMifOtherXRefUniprot()));
            em.persist(mifEntryInteractor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMIFEntryInteractor(mifEntryInteractor.getWid()) != null) {
                throw new PreexistingEntityException("MIFEntryInteractor " + mifEntryInteractor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MIFEntryInteractor mifEntryInteractor) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + mifEntryInteractor.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MIFEntryInteractor persistentMIFEntryInteractor = em.find(MIFEntryInteractor.class, mifEntryInteractor.getWid());
            MIFEntrySetEntry mifEntrySetEntryOld = persistentMIFEntryInteractor.getMifEntrySetEntry();
            MIFEntrySetEntry mifEntrySetEntryNew = mifEntryInteractor.getMifEntrySetEntry();
            MIFInteractionParticipant mifInteractionParticipantOld = persistentMIFEntryInteractor.getMifInteractionParticipant();
            MIFInteractionParticipant mifInteractionParticipantNew = mifEntryInteractor.getMifInteractionParticipant();
            MIFParticipantExperimentalInteractor mifParticipantExperimentalInteractorOld = persistentMIFEntryInteractor.getMifParticipantExperimentalInteractor();
            MIFParticipantExperimentalInteractor mifParticipantExperimentalInteractorNew = mifEntryInteractor.getMifParticipantExperimentalInteractor();
            Set<MIFInteractorInteractorType> mifInteractorInteractorTypeOld = persistentMIFEntryInteractor.getMifInteractorInteractorType();
            Set<MIFInteractorInteractorType> mifInteractorInteractorTypeNew = mifEntryInteractor.getMifInteractorInteractorType();
            Set<MIFOtherAlias> mifOtherAliasOld = persistentMIFEntryInteractor.getMifOtherAlias();
            Set<MIFOtherAlias> mifOtherAliasNew = mifEntryInteractor.getMifOtherAlias();
            Set<MIFOtherAttribute> mifOtherAttributeOld = persistentMIFEntryInteractor.getMifOtherAttribute();
            Set<MIFOtherAttribute> mifOtherAttributeNew = mifEntryInteractor.getMifOtherAttribute();
            Set<MIFOtherBioSourceType> mifOtherBioSourceTypeOld = persistentMIFEntryInteractor.getMifOtherBioSourceType();
            Set<MIFOtherBioSourceType> mifOtherBioSourceTypeNew = mifEntryInteractor.getMifOtherBioSourceType();
            Set<MIFOtherXRef> mifOtherXRefOld = persistentMIFEntryInteractor.getMifOtherXRef();
            Set<MIFOtherXRef> mifOtherXRefNew = mifEntryInteractor.getMifOtherXRef();
            Set<MIFOtherXRefGO> mifOtherXRefGOOld = persistentMIFEntryInteractor.getMifOtherXRefGO();
            Set<MIFOtherXRefGO> mifOtherXRefGONew = mifEntryInteractor.getMifOtherXRefGO();
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMedOld = persistentMIFEntryInteractor.getMifOtherXRefPubMed();
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMedNew = mifEntryInteractor.getMifOtherXRefPubMed();
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeqOld = persistentMIFEntryInteractor.getMifOtherXRefRefSeq();
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeqNew = mifEntryInteractor.getMifOtherXRefRefSeq();
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprotOld = persistentMIFEntryInteractor.getMifOtherXRefUniprot();
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprotNew = mifEntryInteractor.getMifOtherXRefUniprot();
            if (mifEntrySetEntryNew != null) {
                mifEntrySetEntryNew = em.getReference(mifEntrySetEntryNew.getClass(), mifEntrySetEntryNew.getWid());
                mifEntryInteractor.setMifEntrySetEntry(mifEntrySetEntryNew);
            }
            if (mifInteractionParticipantNew != null) {
                mifInteractionParticipantNew = em.getReference(mifInteractionParticipantNew.getClass(), mifInteractionParticipantNew.getWid());
                mifEntryInteractor.setMifInteractionParticipant(mifInteractionParticipantNew);
            }
            if (mifParticipantExperimentalInteractorNew != null) {
                mifParticipantExperimentalInteractorNew = em.getReference(mifParticipantExperimentalInteractorNew.getClass(), mifParticipantExperimentalInteractorNew.getWid());
                mifEntryInteractor.setMifParticipantExperimentalInteractor(mifParticipantExperimentalInteractorNew);
            }
            Set<MIFInteractorInteractorType> attachedMifInteractorInteractorTypeNew = new HashSet<>();
            for (MIFInteractorInteractorType mifInteractorInteractorTypeNewMIFInteractorInteractorTypeToAttach : mifInteractorInteractorTypeNew) {
                mifInteractorInteractorTypeNewMIFInteractorInteractorTypeToAttach = em.getReference(mifInteractorInteractorTypeNewMIFInteractorInteractorTypeToAttach.getClass(), mifInteractorInteractorTypeNewMIFInteractorInteractorTypeToAttach.getWid());
                attachedMifInteractorInteractorTypeNew.add(mifInteractorInteractorTypeNewMIFInteractorInteractorTypeToAttach);
            }
            mifInteractorInteractorTypeNew = attachedMifInteractorInteractorTypeNew;
            mifEntryInteractor.setMifInteractorInteractorType(mifInteractorInteractorTypeNew);
            Set<MIFOtherAlias> attachedMifOtherAliasNew = new HashSet<>();
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAliasToAttach : mifOtherAliasNew) {
                mifOtherAliasNewMIFOtherAliasToAttach = em.getReference(mifOtherAliasNewMIFOtherAliasToAttach.getClass(), mifOtherAliasNewMIFOtherAliasToAttach.getWid());
                attachedMifOtherAliasNew.add(mifOtherAliasNewMIFOtherAliasToAttach);
            }
            mifOtherAliasNew = attachedMifOtherAliasNew;
            mifEntryInteractor.setMifOtherAlias(mifOtherAliasNew);
            Set<MIFOtherAttribute> attachedMifOtherAttributeNew = new HashSet<>();
            for (MIFOtherAttribute mifOtherAttributeNewMIFOtherAttributeToAttach : mifOtherAttributeNew) {
                mifOtherAttributeNewMIFOtherAttributeToAttach = em.getReference(mifOtherAttributeNewMIFOtherAttributeToAttach.getClass(), mifOtherAttributeNewMIFOtherAttributeToAttach.getWid());
                attachedMifOtherAttributeNew.add(mifOtherAttributeNewMIFOtherAttributeToAttach);
            }
            mifOtherAttributeNew = attachedMifOtherAttributeNew;
            mifEntryInteractor.setMifOtherAttribute(mifOtherAttributeNew);
            Set<MIFOtherBioSourceType> attachedMifOtherBioSourceTypeNew = new HashSet<>();
            for (MIFOtherBioSourceType mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach : mifOtherBioSourceTypeNew) {
                mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach = em.getReference(mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach.getClass(), mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach.getWid());
                attachedMifOtherBioSourceTypeNew.add(mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach);
            }
            mifOtherBioSourceTypeNew = attachedMifOtherBioSourceTypeNew;
            mifEntryInteractor.setMifOtherBioSourceType(mifOtherBioSourceTypeNew);
            Set<MIFOtherXRef> attachedMifOtherXRefNew = new HashSet<>();
            for (MIFOtherXRef mifOtherXRefNewMIFOtherXRefToAttach : mifOtherXRefNew) {
                mifOtherXRefNewMIFOtherXRefToAttach = em.getReference(mifOtherXRefNewMIFOtherXRefToAttach.getClass(), mifOtherXRefNewMIFOtherXRefToAttach.getWid());
                attachedMifOtherXRefNew.add(mifOtherXRefNewMIFOtherXRefToAttach);
            }
            mifOtherXRefNew = attachedMifOtherXRefNew;
            mifEntryInteractor.setMifOtherXRef(mifOtherXRefNew);
            Set<MIFOtherXRefGO> attachedMifOtherXRefGONew = new HashSet<>();
            for (MIFOtherXRefGO mifOtherXRefGONewMIFOtherXRefGOToAttach : mifOtherXRefGONew) {
                mifOtherXRefGONewMIFOtherXRefGOToAttach = em.getReference(mifOtherXRefGONewMIFOtherXRefGOToAttach.getClass(), mifOtherXRefGONewMIFOtherXRefGOToAttach.getWid());
                attachedMifOtherXRefGONew.add(mifOtherXRefGONewMIFOtherXRefGOToAttach);
            }
            mifOtherXRefGONew = attachedMifOtherXRefGONew;
            mifEntryInteractor.setMifOtherXRefGO(mifOtherXRefGONew);
            Set<MIFOtherXRefPubMed> attachedMifOtherXRefPubMedNew = new HashSet<>();
            for (MIFOtherXRefPubMed mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach : mifOtherXRefPubMedNew) {
                mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach = em.getReference(mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach.getClass(), mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach.getWid());
                attachedMifOtherXRefPubMedNew.add(mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach);
            }
            mifOtherXRefPubMedNew = attachedMifOtherXRefPubMedNew;
            mifEntryInteractor.setMifOtherXRefPubMed(mifOtherXRefPubMedNew);
            Set<MIFOtherXRefRefSeq> attachedMifOtherXRefRefSeqNew = new HashSet<>();
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach : mifOtherXRefRefSeqNew) {
                mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach = em.getReference(mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach.getClass(), mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach.getWid());
                attachedMifOtherXRefRefSeqNew.add(mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach);
            }
            mifOtherXRefRefSeqNew = attachedMifOtherXRefRefSeqNew;
            mifEntryInteractor.setMifOtherXRefRefSeq(mifOtherXRefRefSeqNew);
            Set<MIFOtherXRefUniprot> attachedMifOtherXRefUniprotNew = new HashSet<>();
            for (MIFOtherXRefUniprot mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach : mifOtherXRefUniprotNew) {
                mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach = em.getReference(mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach.getClass(), mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach.getWid());
                attachedMifOtherXRefUniprotNew.add(mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach);
            }
            mifOtherXRefUniprotNew = attachedMifOtherXRefUniprotNew;
            mifEntryInteractor.setMifOtherXRefUniprot(mifOtherXRefUniprotNew);
            mifEntryInteractor = em.merge(mifEntryInteractor);
            if (mifEntrySetEntryOld != null && !mifEntrySetEntryOld.equals(mifEntrySetEntryNew)) {
                mifEntrySetEntryOld.getMifEntryInteractor().remove(mifEntryInteractor);
                mifEntrySetEntryOld = em.merge(mifEntrySetEntryOld);
            }
            if (mifEntrySetEntryNew != null && !mifEntrySetEntryNew.equals(mifEntrySetEntryOld)) {
                mifEntrySetEntryNew.getMifEntryInteractor().add(mifEntryInteractor);
                em.merge(mifEntrySetEntryNew);
            }
            if (mifInteractionParticipantOld != null && !mifInteractionParticipantOld.equals(mifInteractionParticipantNew)) {
                mifInteractionParticipantOld.getMifEntryInteractor().remove(mifEntryInteractor);
                mifInteractionParticipantOld = em.merge(mifInteractionParticipantOld);
            }
            if (mifInteractionParticipantNew != null && !mifInteractionParticipantNew.equals(mifInteractionParticipantOld)) {
                mifInteractionParticipantNew.getMifEntryInteractor().add(mifEntryInteractor);
                em.merge(mifInteractionParticipantNew);
            }
            if (mifParticipantExperimentalInteractorOld != null && !mifParticipantExperimentalInteractorOld.equals(mifParticipantExperimentalInteractorNew)) {
                mifParticipantExperimentalInteractorOld.getMifEntryInteractor().remove(mifEntryInteractor);
                mifParticipantExperimentalInteractorOld = em.merge(mifParticipantExperimentalInteractorOld);
            }
            if (mifParticipantExperimentalInteractorNew != null && !mifParticipantExperimentalInteractorNew.equals(mifParticipantExperimentalInteractorOld)) {
                mifParticipantExperimentalInteractorNew.getMifEntryInteractor().add(mifEntryInteractor);
                em.merge(mifParticipantExperimentalInteractorNew);
            }
            for (MIFInteractorInteractorType mifInteractorInteractorTypeOldMIFInteractorInteractorType : mifInteractorInteractorTypeOld) {
                if (!mifInteractorInteractorTypeNew.contains(mifInteractorInteractorTypeOldMIFInteractorInteractorType)) {
                    mifInteractorInteractorTypeOldMIFInteractorInteractorType.setMifEntryInteractor(null);
                    mifInteractorInteractorTypeOldMIFInteractorInteractorType = em.merge(mifInteractorInteractorTypeOldMIFInteractorInteractorType);
                }
            }
            for (MIFInteractorInteractorType mifInteractorInteractorTypeNewMIFInteractorInteractorType : mifInteractorInteractorTypeNew) {
                if (!mifInteractorInteractorTypeOld.contains(mifInteractorInteractorTypeNewMIFInteractorInteractorType)) {
                    MIFEntryInteractor oldMifEntryInteractorOfMifInteractorInteractorTypeNewMIFInteractorInteractorType = mifInteractorInteractorTypeNewMIFInteractorInteractorType.getMifEntryInteractor();
                    mifInteractorInteractorTypeNewMIFInteractorInteractorType.setMifEntryInteractor(mifEntryInteractor);
                    mifInteractorInteractorTypeNewMIFInteractorInteractorType = em.merge(mifInteractorInteractorTypeNewMIFInteractorInteractorType);
                    if (oldMifEntryInteractorOfMifInteractorInteractorTypeNewMIFInteractorInteractorType != null && !oldMifEntryInteractorOfMifInteractorInteractorTypeNewMIFInteractorInteractorType.equals(mifEntryInteractor)) {
                        oldMifEntryInteractorOfMifInteractorInteractorTypeNewMIFInteractorInteractorType.getMifInteractorInteractorType().remove(mifInteractorInteractorTypeNewMIFInteractorInteractorType);
                        em.merge(oldMifEntryInteractorOfMifInteractorInteractorTypeNewMIFInteractorInteractorType);
                    }
                }
            }
            for (MIFOtherAlias mifOtherAliasOldMIFOtherAlias : mifOtherAliasOld) {
                if (!mifOtherAliasNew.contains(mifOtherAliasOldMIFOtherAlias)) {
                    mifOtherAliasOldMIFOtherAlias.setMifEntryInteractor(null);
                    mifOtherAliasOldMIFOtherAlias = em.merge(mifOtherAliasOldMIFOtherAlias);
                }
            }
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAlias : mifOtherAliasNew) {
                if (!mifOtherAliasOld.contains(mifOtherAliasNewMIFOtherAlias)) {
                    MIFEntryInteractor oldMifEntryInteractorOfMifOtherAliasNewMIFOtherAlias = mifOtherAliasNewMIFOtherAlias.getMifEntryInteractor();
                    mifOtherAliasNewMIFOtherAlias.setMifEntryInteractor(mifEntryInteractor);
                    mifOtherAliasNewMIFOtherAlias = em.merge(mifOtherAliasNewMIFOtherAlias);
                    if (oldMifEntryInteractorOfMifOtherAliasNewMIFOtherAlias != null && !oldMifEntryInteractorOfMifOtherAliasNewMIFOtherAlias.equals(mifEntryInteractor)) {
                        oldMifEntryInteractorOfMifOtherAliasNewMIFOtherAlias.getMifOtherAlias().remove(mifOtherAliasNewMIFOtherAlias);
                        em.merge(oldMifEntryInteractorOfMifOtherAliasNewMIFOtherAlias);
                    }
                }
            }
            for (MIFOtherAttribute mifOtherAttributeOldMIFOtherAttribute : mifOtherAttributeOld) {
                if (!mifOtherAttributeNew.contains(mifOtherAttributeOldMIFOtherAttribute)) {
                    mifOtherAttributeOldMIFOtherAttribute.setMifEntryInteractor(null);
                    mifOtherAttributeOldMIFOtherAttribute = em.merge(mifOtherAttributeOldMIFOtherAttribute);
                }
            }
            for (MIFOtherAttribute mifOtherAttributeNewMIFOtherAttribute : mifOtherAttributeNew) {
                if (!mifOtherAttributeOld.contains(mifOtherAttributeNewMIFOtherAttribute)) {
                    MIFEntryInteractor oldMifEntryInteractorOfMifOtherAttributeNewMIFOtherAttribute = mifOtherAttributeNewMIFOtherAttribute.getMifEntryInteractor();
                    mifOtherAttributeNewMIFOtherAttribute.setMifEntryInteractor(mifEntryInteractor);
                    mifOtherAttributeNewMIFOtherAttribute = em.merge(mifOtherAttributeNewMIFOtherAttribute);
                    if (oldMifEntryInteractorOfMifOtherAttributeNewMIFOtherAttribute != null && !oldMifEntryInteractorOfMifOtherAttributeNewMIFOtherAttribute.equals(mifEntryInteractor)) {
                        oldMifEntryInteractorOfMifOtherAttributeNewMIFOtherAttribute.getMifOtherAttribute().remove(mifOtherAttributeNewMIFOtherAttribute);
                        em.merge(oldMifEntryInteractorOfMifOtherAttributeNewMIFOtherAttribute);
                    }
                }
            }
            for (MIFOtherBioSourceType mifOtherBioSourceTypeOldMIFOtherBioSourceType : mifOtherBioSourceTypeOld) {
                if (!mifOtherBioSourceTypeNew.contains(mifOtherBioSourceTypeOldMIFOtherBioSourceType)) {
                    mifOtherBioSourceTypeOldMIFOtherBioSourceType.setMifEntryInteractor(null);
                    mifOtherBioSourceTypeOldMIFOtherBioSourceType = em.merge(mifOtherBioSourceTypeOldMIFOtherBioSourceType);
                }
            }
            for (MIFOtherBioSourceType mifOtherBioSourceTypeNewMIFOtherBioSourceType : mifOtherBioSourceTypeNew) {
                if (!mifOtherBioSourceTypeOld.contains(mifOtherBioSourceTypeNewMIFOtherBioSourceType)) {
                    MIFEntryInteractor oldMifEntryInteractorOfMifOtherBioSourceTypeNewMIFOtherBioSourceType = mifOtherBioSourceTypeNewMIFOtherBioSourceType.getMifEntryInteractor();
                    mifOtherBioSourceTypeNewMIFOtherBioSourceType.setMifEntryInteractor(mifEntryInteractor);
                    mifOtherBioSourceTypeNewMIFOtherBioSourceType = em.merge(mifOtherBioSourceTypeNewMIFOtherBioSourceType);
                    if (oldMifEntryInteractorOfMifOtherBioSourceTypeNewMIFOtherBioSourceType != null && !oldMifEntryInteractorOfMifOtherBioSourceTypeNewMIFOtherBioSourceType.equals(mifEntryInteractor)) {
                        oldMifEntryInteractorOfMifOtherBioSourceTypeNewMIFOtherBioSourceType.getMifOtherBioSourceType().remove(mifOtherBioSourceTypeNewMIFOtherBioSourceType);
                        em.merge(oldMifEntryInteractorOfMifOtherBioSourceTypeNewMIFOtherBioSourceType);
                    }
                }
            }
            for (MIFOtherXRef mifOtherXRefOldMIFOtherXRef : mifOtherXRefOld) {
                if (!mifOtherXRefNew.contains(mifOtherXRefOldMIFOtherXRef)) {
                    mifOtherXRefOldMIFOtherXRef.setMifEntryInteractor(null);
                    mifOtherXRefOldMIFOtherXRef = em.merge(mifOtherXRefOldMIFOtherXRef);
                }
            }
            for (MIFOtherXRef mifOtherXRefNewMIFOtherXRef : mifOtherXRefNew) {
                if (!mifOtherXRefOld.contains(mifOtherXRefNewMIFOtherXRef)) {
                    MIFEntryInteractor oldMifEntryInteractorOfMifOtherXRefNewMIFOtherXRef = mifOtherXRefNewMIFOtherXRef.getMifEntryInteractor();
                    mifOtherXRefNewMIFOtherXRef.setMifEntryInteractor(mifEntryInteractor);
                    mifOtherXRefNewMIFOtherXRef = em.merge(mifOtherXRefNewMIFOtherXRef);
                    if (oldMifEntryInteractorOfMifOtherXRefNewMIFOtherXRef != null && !oldMifEntryInteractorOfMifOtherXRefNewMIFOtherXRef.equals(mifEntryInteractor)) {
                        oldMifEntryInteractorOfMifOtherXRefNewMIFOtherXRef.getMifOtherXRef().remove(mifOtherXRefNewMIFOtherXRef);
                        em.merge(oldMifEntryInteractorOfMifOtherXRefNewMIFOtherXRef);
                    }
                }
            }
            for (MIFOtherXRefGO mifOtherXRefGOOldMIFOtherXRefGO : mifOtherXRefGOOld) {
                if (!mifOtherXRefGONew.contains(mifOtherXRefGOOldMIFOtherXRefGO)) {
                    mifOtherXRefGOOldMIFOtherXRefGO.setMifEntryInteractor(null);
                    mifOtherXRefGOOldMIFOtherXRefGO = em.merge(mifOtherXRefGOOldMIFOtherXRefGO);
                }
            }
            for (MIFOtherXRefGO mifOtherXRefGONewMIFOtherXRefGO : mifOtherXRefGONew) {
                if (!mifOtherXRefGOOld.contains(mifOtherXRefGONewMIFOtherXRefGO)) {
                    MIFEntryInteractor oldMifEntryInteractorOfMifOtherXRefGONewMIFOtherXRefGO = mifOtherXRefGONewMIFOtherXRefGO.getMifEntryInteractor();
                    mifOtherXRefGONewMIFOtherXRefGO.setMifEntryInteractor(mifEntryInteractor);
                    mifOtherXRefGONewMIFOtherXRefGO = em.merge(mifOtherXRefGONewMIFOtherXRefGO);
                    if (oldMifEntryInteractorOfMifOtherXRefGONewMIFOtherXRefGO != null && !oldMifEntryInteractorOfMifOtherXRefGONewMIFOtherXRefGO.equals(mifEntryInteractor)) {
                        oldMifEntryInteractorOfMifOtherXRefGONewMIFOtherXRefGO.getMifOtherXRefGO().remove(mifOtherXRefGONewMIFOtherXRefGO);
                        em.merge(oldMifEntryInteractorOfMifOtherXRefGONewMIFOtherXRefGO);
                    }
                }
            }
            for (MIFOtherXRefPubMed mifOtherXRefPubMedOldMIFOtherXRefPubMed : mifOtherXRefPubMedOld) {
                if (!mifOtherXRefPubMedNew.contains(mifOtherXRefPubMedOldMIFOtherXRefPubMed)) {
                    mifOtherXRefPubMedOldMIFOtherXRefPubMed.setMifEntryInteractor(null);
                    mifOtherXRefPubMedOldMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedOldMIFOtherXRefPubMed);
                }
            }
            for (MIFOtherXRefPubMed mifOtherXRefPubMedNewMIFOtherXRefPubMed : mifOtherXRefPubMedNew) {
                if (!mifOtherXRefPubMedOld.contains(mifOtherXRefPubMedNewMIFOtherXRefPubMed)) {
                    MIFEntryInteractor oldMifEntryInteractorOfMifOtherXRefPubMedNewMIFOtherXRefPubMed = mifOtherXRefPubMedNewMIFOtherXRefPubMed.getMifEntryInteractor();
                    mifOtherXRefPubMedNewMIFOtherXRefPubMed.setMifEntryInteractor(mifEntryInteractor);
                    mifOtherXRefPubMedNewMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedNewMIFOtherXRefPubMed);
                    if (oldMifEntryInteractorOfMifOtherXRefPubMedNewMIFOtherXRefPubMed != null && !oldMifEntryInteractorOfMifOtherXRefPubMedNewMIFOtherXRefPubMed.equals(mifEntryInteractor)) {
                        oldMifEntryInteractorOfMifOtherXRefPubMedNewMIFOtherXRefPubMed.getMifOtherXRefPubMed().remove(mifOtherXRefPubMedNewMIFOtherXRefPubMed);
                        em.merge(oldMifEntryInteractorOfMifOtherXRefPubMedNewMIFOtherXRefPubMed);
                    }
                }
            }
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqOldMIFOtherXRefRefSeq : mifOtherXRefRefSeqOld) {
                if (!mifOtherXRefRefSeqNew.contains(mifOtherXRefRefSeqOldMIFOtherXRefRefSeq)) {
                    mifOtherXRefRefSeqOldMIFOtherXRefRefSeq.setMifEntryInteractor(null);
                    mifOtherXRefRefSeqOldMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqOldMIFOtherXRefRefSeq);
                }
            }
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqNewMIFOtherXRefRefSeq : mifOtherXRefRefSeqNew) {
                if (!mifOtherXRefRefSeqOld.contains(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq)) {
                    MIFEntryInteractor oldMifEntryInteractorOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq = mifOtherXRefRefSeqNewMIFOtherXRefRefSeq.getMifEntryInteractor();
                    mifOtherXRefRefSeqNewMIFOtherXRefRefSeq.setMifEntryInteractor(mifEntryInteractor);
                    mifOtherXRefRefSeqNewMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                    if (oldMifEntryInteractorOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq != null && !oldMifEntryInteractorOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq.equals(mifEntryInteractor)) {
                        oldMifEntryInteractorOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq.getMifOtherXRefRefSeq().remove(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                        em.merge(oldMifEntryInteractorOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                    }
                }
            }
            for (MIFOtherXRefUniprot mifOtherXRefUniprotOldMIFOtherXRefUniprot : mifOtherXRefUniprotOld) {
                if (!mifOtherXRefUniprotNew.contains(mifOtherXRefUniprotOldMIFOtherXRefUniprot)) {
                    mifOtherXRefUniprotOldMIFOtherXRefUniprot.setMifEntryInteractor(null);
                    mifOtherXRefUniprotOldMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotOldMIFOtherXRefUniprot);
                }
            }
            for (MIFOtherXRefUniprot mifOtherXRefUniprotNewMIFOtherXRefUniprot : mifOtherXRefUniprotNew) {
                if (!mifOtherXRefUniprotOld.contains(mifOtherXRefUniprotNewMIFOtherXRefUniprot)) {
                    MIFEntryInteractor oldMifEntryInteractorOfMifOtherXRefUniprotNewMIFOtherXRefUniprot = mifOtherXRefUniprotNewMIFOtherXRefUniprot.getMifEntryInteractor();
                    mifOtherXRefUniprotNewMIFOtherXRefUniprot.setMifEntryInteractor(mifEntryInteractor);
                    mifOtherXRefUniprotNewMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotNewMIFOtherXRefUniprot);
                    if (oldMifEntryInteractorOfMifOtherXRefUniprotNewMIFOtherXRefUniprot != null && !oldMifEntryInteractorOfMifOtherXRefUniprotNewMIFOtherXRefUniprot.equals(mifEntryInteractor)) {
                        oldMifEntryInteractorOfMifOtherXRefUniprotNewMIFOtherXRefUniprot.getMifOtherXRefUniprot().remove(mifOtherXRefUniprotNewMIFOtherXRefUniprot);
                        em.merge(oldMifEntryInteractorOfMifOtherXRefUniprotNewMIFOtherXRefUniprot);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mifEntryInteractor.getWid();
                if (findMIFEntryInteractor(id) == null) {
                    throw new NonexistentEntityException("The mIFEntryInteractor with id " + id + " no longer exists.");
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
            MIFEntryInteractor MIFEntryInteractor;
            try {
                MIFEntryInteractor = em.getReference(MIFEntryInteractor.class, id);
                MIFEntryInteractor.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MIFEntryInteractor with id " + id + " no longer exists.", enfe);
            }
            MIFEntrySetEntry mifEntrySetEntry = MIFEntryInteractor.getMifEntrySetEntry();
            if (mifEntrySetEntry != null) {
                mifEntrySetEntry.getMifEntryInteractor().remove(MIFEntryInteractor);
                em.merge(mifEntrySetEntry);
            }
            MIFInteractionParticipant mifInteractionParticipant = MIFEntryInteractor.getMifInteractionParticipant();
            if (mifInteractionParticipant != null) {
                mifInteractionParticipant.getMifEntryInteractor().remove(MIFEntryInteractor);
                em.merge(mifInteractionParticipant);
            }
            MIFParticipantExperimentalInteractor mifParticipantExperimentalInteractor = MIFEntryInteractor.getMifParticipantExperimentalInteractor();
            if (mifParticipantExperimentalInteractor != null) {
                mifParticipantExperimentalInteractor.getMifEntryInteractor().remove(MIFEntryInteractor);
                em.merge(mifParticipantExperimentalInteractor);
            }
            Set<MIFInteractorInteractorType> mifInteractorInteractorType = MIFEntryInteractor.getMifInteractorInteractorType();
            for (MIFInteractorInteractorType mifInteractorInteractorTypeMIFInteractorInteractorType : mifInteractorInteractorType) {
                mifInteractorInteractorTypeMIFInteractorInteractorType.setMifEntryInteractor(null);
                mifInteractorInteractorTypeMIFInteractorInteractorType = em.merge(mifInteractorInteractorTypeMIFInteractorInteractorType);
            }
            Set<MIFOtherAlias> mifOtherAlias = MIFEntryInteractor.getMifOtherAlias();
            for (MIFOtherAlias mifOtherAliasMIFOtherAlias : mifOtherAlias) {
                mifOtherAliasMIFOtherAlias.setMifEntryInteractor(null);
                mifOtherAliasMIFOtherAlias = em.merge(mifOtherAliasMIFOtherAlias);
            }
            Set<MIFOtherAttribute> mifOtherAttribute = MIFEntryInteractor.getMifOtherAttribute();
            for (MIFOtherAttribute mifOtherAttributeMIFOtherAttribute : mifOtherAttribute) {
                mifOtherAttributeMIFOtherAttribute.setMifEntryInteractor(null);
                mifOtherAttributeMIFOtherAttribute = em.merge(mifOtherAttributeMIFOtherAttribute);
            }
            Set<MIFOtherBioSourceType> mifOtherBioSourceType = MIFEntryInteractor.getMifOtherBioSourceType();
            for (MIFOtherBioSourceType mifOtherBioSourceTypeMIFOtherBioSourceType : mifOtherBioSourceType) {
                mifOtherBioSourceTypeMIFOtherBioSourceType.setMifEntryInteractor(null);
                mifOtherBioSourceTypeMIFOtherBioSourceType = em.merge(mifOtherBioSourceTypeMIFOtherBioSourceType);
            }
            Set<MIFOtherXRef> mifOtherXRef = MIFEntryInteractor.getMifOtherXRef();
            for (MIFOtherXRef mifOtherXRefMIFOtherXRef : mifOtherXRef) {
                mifOtherXRefMIFOtherXRef.setMifEntryInteractor(null);
                mifOtherXRefMIFOtherXRef = em.merge(mifOtherXRefMIFOtherXRef);
            }
            Set<MIFOtherXRefGO> mifOtherXRefGO = MIFEntryInteractor.getMifOtherXRefGO();
            for (MIFOtherXRefGO mifOtherXRefGOMIFOtherXRefGO : mifOtherXRefGO) {
                mifOtherXRefGOMIFOtherXRefGO.setMifEntryInteractor(null);
                mifOtherXRefGOMIFOtherXRefGO = em.merge(mifOtherXRefGOMIFOtherXRefGO);
            }
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMed = MIFEntryInteractor.getMifOtherXRefPubMed();
            for (MIFOtherXRefPubMed mifOtherXRefPubMedMIFOtherXRefPubMed : mifOtherXRefPubMed) {
                mifOtherXRefPubMedMIFOtherXRefPubMed.setMifEntryInteractor(null);
                mifOtherXRefPubMedMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedMIFOtherXRefPubMed);
            }
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq = MIFEntryInteractor.getMifOtherXRefRefSeq();
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqMIFOtherXRefRefSeq : mifOtherXRefRefSeq) {
                mifOtherXRefRefSeqMIFOtherXRefRefSeq.setMifEntryInteractor(null);
                mifOtherXRefRefSeqMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqMIFOtherXRefRefSeq);
            }
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprot = MIFEntryInteractor.getMifOtherXRefUniprot();
            for (MIFOtherXRefUniprot mifOtherXRefUniprotMIFOtherXRefUniprot : mifOtherXRefUniprot) {
                mifOtherXRefUniprotMIFOtherXRefUniprot.setMifEntryInteractor(null);
                mifOtherXRefUniprotMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotMIFOtherXRefUniprot);
            }
            em.remove(MIFEntryInteractor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MIFEntryInteractor> findMIFEntryInteractorEntities() {
        return findMIFEntryInteractorEntities(true, -1, -1);
    }

    public List<MIFEntryInteractor> findMIFEntryInteractorEntities(int maxResults, int firstResult) {
        return findMIFEntryInteractorEntities(false, maxResults, firstResult);
    }

    private List<MIFEntryInteractor> findMIFEntryInteractorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MIFEntryInteractor.class));
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

    public MIFEntryInteractor findMIFEntryInteractor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MIFEntryInteractor.class, id);
        } finally {
            em.close();
        }
    }

    public int getMIFEntryInteractorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MIFEntryInteractor> rt = cq.from(MIFEntryInteractor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
