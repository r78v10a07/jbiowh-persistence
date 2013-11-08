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
import org.jbiowhpersistence.datasets.ppi.entities.MIFExperimentFeatDetecMethod;
import org.jbiowhpersistence.datasets.ppi.entities.MIFExperimentInterDetecMethod;
import org.jbiowhpersistence.datasets.ppi.entities.MIFExperimentPartIdentMethod;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAlias;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAttribute;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherBibRef;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherBioSourceType;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherConfidence;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRef;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefGO;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefPubMed;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefRefSeq;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherXRefUniprot;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the MIFEntryExperiment Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 14, 2012
 */
public class MIFEntryExperimentJpaController extends AbstractMIFJpaController<MIFEntryExperiment> implements Serializable {

    public MIFEntryExperimentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MIFEntryExperiment mifEntryExperiment) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + mifEntryExperiment.getWid());
        if (mifEntryExperiment.getMifExperimentFeatDetecMethod() == null) {
            mifEntryExperiment.setMifExperimentFeatDetecMethod(new HashSet<MIFExperimentFeatDetecMethod>());
        }
        if (mifEntryExperiment.getMifExperimentInterDetecMethod() == null) {
            mifEntryExperiment.setMifExperimentInterDetecMethod(new HashSet<MIFExperimentInterDetecMethod>());
        }
        if (mifEntryExperiment.getMifExperimentPartIdentMethod() == null) {
            mifEntryExperiment.setMifExperimentPartIdentMethod(new HashSet<MIFExperimentPartIdentMethod>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mifEntryExperiment.setMifEntrySetEntry(createMIFEntrySetEntry(emf, em, mifEntryExperiment.getMifEntrySetEntry()));
            mifEntryExperiment.setMifEntryInteraction(createMIFEntryInteraction(emf, em, mifEntryExperiment.getMifEntryInteraction()));

            if (!mifEntryExperiment.getMifExperimentFeatDetecMethod().isEmpty()) {
                Set<MIFExperimentFeatDetecMethod> attachedMifExperimentFeatDetecMethod = new HashSet();
                for (MIFExperimentFeatDetecMethod mifExperimentFeatDetecMethodMIFExperimentFeatDetecMethodToAttach : mifEntryExperiment.getMifExperimentFeatDetecMethod()) {
                    MIFExperimentFeatDetecMethod mifExperimentFeatDetecMethod = em.find(mifExperimentFeatDetecMethodMIFExperimentFeatDetecMethodToAttach.getClass(), mifExperimentFeatDetecMethodMIFExperimentFeatDetecMethodToAttach.getWid());
                    if (mifExperimentFeatDetecMethod != null) {
                        attachedMifExperimentFeatDetecMethod.add(mifExperimentFeatDetecMethod);
                    } else {
                        attachedMifExperimentFeatDetecMethod.add(mifExperimentFeatDetecMethodMIFExperimentFeatDetecMethodToAttach);
                    }
                }
                mifEntryExperiment.setMifExperimentFeatDetecMethod(attachedMifExperimentFeatDetecMethod);
            }
            if (!mifEntryExperiment.getMifExperimentInterDetecMethod().isEmpty()) {
                Set<MIFExperimentInterDetecMethod> attachedMifExperimentInterDetecMethod = new HashSet();
                for (MIFExperimentInterDetecMethod mifExperimentInterDetecMethodMIFExperimentInterDetecMethodToAttach : mifEntryExperiment.getMifExperimentInterDetecMethod()) {
                    MIFExperimentInterDetecMethod mifExperimentInterDetecMethod = em.find(mifExperimentInterDetecMethodMIFExperimentInterDetecMethodToAttach.getClass(), mifExperimentInterDetecMethodMIFExperimentInterDetecMethodToAttach.getWid());
                    if (mifExperimentInterDetecMethod != null) {
                        attachedMifExperimentInterDetecMethod.add(mifExperimentInterDetecMethod);
                    } else {
                        attachedMifExperimentInterDetecMethod.add(mifExperimentInterDetecMethodMIFExperimentInterDetecMethodToAttach);
                    }
                }
                mifEntryExperiment.setMifExperimentInterDetecMethod(attachedMifExperimentInterDetecMethod);
            }
            if (!mifEntryExperiment.getMifExperimentPartIdentMethod().isEmpty()) {
                Set<MIFExperimentPartIdentMethod> attachedMifExperimentPartIdentMethod = new HashSet();
                for (MIFExperimentPartIdentMethod mifExperimentPartIdentMethodMIFExperimentPartIdentMethodToAttach : mifEntryExperiment.getMifExperimentPartIdentMethod()) {
                    MIFExperimentPartIdentMethod mifExperimentPartIdent = em.find(mifExperimentPartIdentMethodMIFExperimentPartIdentMethodToAttach.getClass(), mifExperimentPartIdentMethodMIFExperimentPartIdentMethodToAttach.getWid());
                    if (mifExperimentPartIdent != null) {
                        attachedMifExperimentPartIdentMethod.add(mifExperimentPartIdent);
                    } else {
                        attachedMifExperimentPartIdentMethod.add(mifExperimentPartIdentMethodMIFExperimentPartIdentMethodToAttach);
                    }
                }
                mifEntryExperiment.setMifExperimentPartIdentMethod(attachedMifExperimentPartIdentMethod);
            }
            mifEntryExperiment.setMifOtherAlias(createMIFOtherAlias(emf, em, mifEntryExperiment.getMifOtherAlias()));
            mifEntryExperiment.setMifOtherAttribute(createMIFOtherAttribute(emf, em, mifEntryExperiment.getMifOtherAttribute()));
            mifEntryExperiment.setMifOtherBibRef(createMIFOtherBibRef(emf, em, mifEntryExperiment.getMifOtherBibRef()));
            mifEntryExperiment.setMifOtherBioSourceType(createMIFOtherBioSourceType(emf, em, mifEntryExperiment.getMifOtherBioSourceType()));
            mifEntryExperiment.setMifOtherXRef(createMIFOtherXRef(emf, em, mifEntryExperiment.getMifOtherXRef()));
            mifEntryExperiment.setMifOtherXRefGO(createMIFOtherXRefGO(emf, em, mifEntryExperiment.getMifOtherXRefGO()));
            mifEntryExperiment.setMifOtherXRefPubMed(createMIFOtherXRefPubMed(emf, em, mifEntryExperiment.getMifOtherXRefPubMed()));
            mifEntryExperiment.setMifOtherXRefRefSeq(createMIFOtherXRefRefSeq(emf, em, mifEntryExperiment.getMifOtherXRefRefSeq()));
            mifEntryExperiment.setMifOtherXRefUniprot(createMIFOtherXRefUniprot(emf, em, mifEntryExperiment.getMifOtherXRefUniprot()));
            mifEntryExperiment.setMifOtherConfidence(createMIFOtherConfidence(emf, em, mifEntryExperiment.getMifOtherConfidence()));
            em.persist(mifEntryExperiment);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMIFEntryExperiment(mifEntryExperiment.getWid()) != null) {
                throw new PreexistingEntityException("MIFEntryExperiment " + mifEntryExperiment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MIFEntryExperiment mifEntryExperiment) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + mifEntryExperiment.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MIFEntryExperiment persistentMIFEntryExperiment = em.find(MIFEntryExperiment.class, mifEntryExperiment.getWid());
            MIFEntryInteraction mifEntryInteractionOld = persistentMIFEntryExperiment.getMifEntryInteraction();
            MIFEntryInteraction mifEntryInteractionNew = mifEntryExperiment.getMifEntryInteraction();
            MIFEntrySetEntry mifEntrySetEntryOld = persistentMIFEntryExperiment.getMifEntrySetEntry();
            MIFEntrySetEntry mifEntrySetEntryNew = mifEntryExperiment.getMifEntrySetEntry();
            Set<MIFExperimentFeatDetecMethod> mifExperimentFeatDetecMethodOld = persistentMIFEntryExperiment.getMifExperimentFeatDetecMethod();
            Set<MIFExperimentFeatDetecMethod> mifExperimentFeatDetecMethodNew = mifEntryExperiment.getMifExperimentFeatDetecMethod();
            Set<MIFExperimentInterDetecMethod> mifExperimentInterDetecMethodOld = persistentMIFEntryExperiment.getMifExperimentInterDetecMethod();
            Set<MIFExperimentInterDetecMethod> mifExperimentInterDetecMethodNew = mifEntryExperiment.getMifExperimentInterDetecMethod();
            Set<MIFExperimentPartIdentMethod> mifExperimentPartIdentMethodOld = persistentMIFEntryExperiment.getMifExperimentPartIdentMethod();
            Set<MIFExperimentPartIdentMethod> mifExperimentPartIdentMethodNew = mifEntryExperiment.getMifExperimentPartIdentMethod();
            Set<MIFOtherAlias> mifOtherAliasOld = persistentMIFEntryExperiment.getMifOtherAlias();
            Set<MIFOtherAlias> mifOtherAliasNew = mifEntryExperiment.getMifOtherAlias();
            Set<MIFOtherAttribute> mifOtherAttributeOld = persistentMIFEntryExperiment.getMifOtherAttribute();
            Set<MIFOtherAttribute> mifOtherAttributeNew = mifEntryExperiment.getMifOtherAttribute();
            Set<MIFOtherBibRef> mifOtherBibRefOld = persistentMIFEntryExperiment.getMifOtherBibRef();
            Set<MIFOtherBibRef> mifOtherBibRefNew = mifEntryExperiment.getMifOtherBibRef();
            Set<MIFOtherBioSourceType> mifOtherBioSourceTypeOld = persistentMIFEntryExperiment.getMifOtherBioSourceType();
            Set<MIFOtherBioSourceType> mifOtherBioSourceTypeNew = mifEntryExperiment.getMifOtherBioSourceType();
            Set<MIFOtherConfidence> mifOtherConfidenceOld = persistentMIFEntryExperiment.getMifOtherConfidence();
            Set<MIFOtherConfidence> mifOtherConfidenceNew = mifEntryExperiment.getMifOtherConfidence();
            Set<MIFOtherXRef> mifOtherXRefOld = persistentMIFEntryExperiment.getMifOtherXRef();
            Set<MIFOtherXRef> mifOtherXRefNew = mifEntryExperiment.getMifOtherXRef();
            Set<MIFOtherXRefGO> mifOtherXRefGOOld = persistentMIFEntryExperiment.getMifOtherXRefGO();
            Set<MIFOtherXRefGO> mifOtherXRefGONew = mifEntryExperiment.getMifOtherXRefGO();
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMedOld = persistentMIFEntryExperiment.getMifOtherXRefPubMed();
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMedNew = mifEntryExperiment.getMifOtherXRefPubMed();
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeqOld = persistentMIFEntryExperiment.getMifOtherXRefRefSeq();
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeqNew = mifEntryExperiment.getMifOtherXRefRefSeq();
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprotOld = persistentMIFEntryExperiment.getMifOtherXRefUniprot();
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprotNew = mifEntryExperiment.getMifOtherXRefUniprot();
            if (mifEntryInteractionNew != null) {
                mifEntryInteractionNew = em.getReference(mifEntryInteractionNew.getClass(), mifEntryInteractionNew.getWid());
                mifEntryExperiment.setMifEntryInteraction(mifEntryInteractionNew);
            }
            if (mifEntrySetEntryNew != null) {
                mifEntrySetEntryNew = em.getReference(mifEntrySetEntryNew.getClass(), mifEntrySetEntryNew.getWid());
                mifEntryExperiment.setMifEntrySetEntry(mifEntrySetEntryNew);
            }
            Set<MIFExperimentFeatDetecMethod> attachedMifExperimentFeatDetecMethodNew = new HashSet();
            for (MIFExperimentFeatDetecMethod mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethodToAttach : mifExperimentFeatDetecMethodNew) {
                mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethodToAttach = em.getReference(mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethodToAttach.getClass(), mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethodToAttach.getWid());
                attachedMifExperimentFeatDetecMethodNew.add(mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethodToAttach);
            }
            mifExperimentFeatDetecMethodNew = attachedMifExperimentFeatDetecMethodNew;
            mifEntryExperiment.setMifExperimentFeatDetecMethod(mifExperimentFeatDetecMethodNew);
            Set<MIFExperimentInterDetecMethod> attachedMifExperimentInterDetecMethodNew = new HashSet();
            for (MIFExperimentInterDetecMethod mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethodToAttach : mifExperimentInterDetecMethodNew) {
                mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethodToAttach = em.getReference(mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethodToAttach.getClass(), mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethodToAttach.getWid());
                attachedMifExperimentInterDetecMethodNew.add(mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethodToAttach);
            }
            mifExperimentInterDetecMethodNew = attachedMifExperimentInterDetecMethodNew;
            mifEntryExperiment.setMifExperimentInterDetecMethod(mifExperimentInterDetecMethodNew);
            Set<MIFExperimentPartIdentMethod> attachedMifExperimentPartIdentMethodNew = new HashSet();
            for (MIFExperimentPartIdentMethod mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethodToAttach : mifExperimentPartIdentMethodNew) {
                mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethodToAttach = em.getReference(mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethodToAttach.getClass(), mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethodToAttach.getWid());
                attachedMifExperimentPartIdentMethodNew.add(mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethodToAttach);
            }
            mifExperimentPartIdentMethodNew = attachedMifExperimentPartIdentMethodNew;
            mifEntryExperiment.setMifExperimentPartIdentMethod(mifExperimentPartIdentMethodNew);
            Set<MIFOtherAlias> attachedMifOtherAliasNew = new HashSet();
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAliasToAttach : mifOtherAliasNew) {
                mifOtherAliasNewMIFOtherAliasToAttach = em.getReference(mifOtherAliasNewMIFOtherAliasToAttach.getClass(), mifOtherAliasNewMIFOtherAliasToAttach.getWid());
                attachedMifOtherAliasNew.add(mifOtherAliasNewMIFOtherAliasToAttach);
            }
            mifOtherAliasNew = attachedMifOtherAliasNew;
            mifEntryExperiment.setMifOtherAlias(mifOtherAliasNew);
            Set<MIFOtherAttribute> attachedMifOtherAttributeNew = new HashSet();
            for (MIFOtherAttribute mifOtherAttributeNewMIFOtherAttributeToAttach : mifOtherAttributeNew) {
                mifOtherAttributeNewMIFOtherAttributeToAttach = em.getReference(mifOtherAttributeNewMIFOtherAttributeToAttach.getClass(), mifOtherAttributeNewMIFOtherAttributeToAttach.getWid());
                attachedMifOtherAttributeNew.add(mifOtherAttributeNewMIFOtherAttributeToAttach);
            }
            mifOtherAttributeNew = attachedMifOtherAttributeNew;
            mifEntryExperiment.setMifOtherAttribute(mifOtherAttributeNew);
            Set<MIFOtherBibRef> attachedMifOtherBibRefNew = new HashSet();
            for (MIFOtherBibRef mifOtherBibRefNewMIFOtherBibRefToAttach : mifOtherBibRefNew) {
                mifOtherBibRefNewMIFOtherBibRefToAttach = em.getReference(mifOtherBibRefNewMIFOtherBibRefToAttach.getClass(), mifOtherBibRefNewMIFOtherBibRefToAttach.getWid());
                attachedMifOtherBibRefNew.add(mifOtherBibRefNewMIFOtherBibRefToAttach);
            }
            mifOtherBibRefNew = attachedMifOtherBibRefNew;
            mifEntryExperiment.setMifOtherBibRef(mifOtherBibRefNew);
            Set<MIFOtherBioSourceType> attachedMifOtherBioSourceTypeNew = new HashSet();
            for (MIFOtherBioSourceType mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach : mifOtherBioSourceTypeNew) {
                mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach = em.getReference(mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach.getClass(), mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach.getWid());
                attachedMifOtherBioSourceTypeNew.add(mifOtherBioSourceTypeNewMIFOtherBioSourceTypeToAttach);
            }
            mifOtherBioSourceTypeNew = attachedMifOtherBioSourceTypeNew;
            mifEntryExperiment.setMifOtherBioSourceType(mifOtherBioSourceTypeNew);
            Set<MIFOtherConfidence> attachedMifOtherConfidenceNew = new HashSet();
            for (MIFOtherConfidence mifOtherConfidenceNewMIFOtherConfidenceToAttach : mifOtherConfidenceNew) {
                mifOtherConfidenceNewMIFOtherConfidenceToAttach = em.getReference(mifOtherConfidenceNewMIFOtherConfidenceToAttach.getClass(), mifOtherConfidenceNewMIFOtherConfidenceToAttach.getWid());
                attachedMifOtherConfidenceNew.add(mifOtherConfidenceNewMIFOtherConfidenceToAttach);
            }
            mifOtherConfidenceNew = attachedMifOtherConfidenceNew;
            mifEntryExperiment.setMifOtherConfidence(mifOtherConfidenceNew);
            Set<MIFOtherXRef> attachedMifOtherXRefNew = new HashSet();
            for (MIFOtherXRef mifOtherXRefNewMIFOtherXRefToAttach : mifOtherXRefNew) {
                mifOtherXRefNewMIFOtherXRefToAttach = em.getReference(mifOtherXRefNewMIFOtherXRefToAttach.getClass(), mifOtherXRefNewMIFOtherXRefToAttach.getWid());
                attachedMifOtherXRefNew.add(mifOtherXRefNewMIFOtherXRefToAttach);
            }
            mifOtherXRefNew = attachedMifOtherXRefNew;
            mifEntryExperiment.setMifOtherXRef(mifOtherXRefNew);
            Set<MIFOtherXRefGO> attachedMifOtherXRefGONew = new HashSet();
            for (MIFOtherXRefGO mifOtherXRefGONewMIFOtherXRefGOToAttach : mifOtherXRefGONew) {
                mifOtherXRefGONewMIFOtherXRefGOToAttach = em.getReference(mifOtherXRefGONewMIFOtherXRefGOToAttach.getClass(), mifOtherXRefGONewMIFOtherXRefGOToAttach.getWid());
                attachedMifOtherXRefGONew.add(mifOtherXRefGONewMIFOtherXRefGOToAttach);
            }
            mifOtherXRefGONew = attachedMifOtherXRefGONew;
            mifEntryExperiment.setMifOtherXRefGO(mifOtherXRefGONew);
            Set<MIFOtherXRefPubMed> attachedMifOtherXRefPubMedNew = new HashSet();
            for (MIFOtherXRefPubMed mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach : mifOtherXRefPubMedNew) {
                mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach = em.getReference(mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach.getClass(), mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach.getWid());
                attachedMifOtherXRefPubMedNew.add(mifOtherXRefPubMedNewMIFOtherXRefPubMedToAttach);
            }
            mifOtherXRefPubMedNew = attachedMifOtherXRefPubMedNew;
            mifEntryExperiment.setMifOtherXRefPubMed(mifOtherXRefPubMedNew);
            Set<MIFOtherXRefRefSeq> attachedMifOtherXRefRefSeqNew = new HashSet();
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach : mifOtherXRefRefSeqNew) {
                mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach = em.getReference(mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach.getClass(), mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach.getWid());
                attachedMifOtherXRefRefSeqNew.add(mifOtherXRefRefSeqNewMIFOtherXRefRefSeqToAttach);
            }
            mifOtherXRefRefSeqNew = attachedMifOtherXRefRefSeqNew;
            mifEntryExperiment.setMifOtherXRefRefSeq(mifOtherXRefRefSeqNew);
            Set<MIFOtherXRefUniprot> attachedMifOtherXRefUniprotNew = new HashSet();
            for (MIFOtherXRefUniprot mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach : mifOtherXRefUniprotNew) {
                mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach = em.getReference(mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach.getClass(), mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach.getWid());
                attachedMifOtherXRefUniprotNew.add(mifOtherXRefUniprotNewMIFOtherXRefUniprotToAttach);
            }
            mifOtherXRefUniprotNew = attachedMifOtherXRefUniprotNew;
            mifEntryExperiment.setMifOtherXRefUniprot(mifOtherXRefUniprotNew);
            mifEntryExperiment = em.merge(mifEntryExperiment);
            if (mifEntryInteractionOld != null && !mifEntryInteractionOld.equals(mifEntryInteractionNew)) {
                mifEntryInteractionOld.getMifEntryExperiment().remove(mifEntryExperiment);
                mifEntryInteractionOld = em.merge(mifEntryInteractionOld);
            }
            if (mifEntryInteractionNew != null && !mifEntryInteractionNew.equals(mifEntryInteractionOld)) {
                mifEntryInteractionNew.getMifEntryExperiment().add(mifEntryExperiment);
                em.merge(mifEntryInteractionNew);
            }
            if (mifEntrySetEntryOld != null && !mifEntrySetEntryOld.equals(mifEntrySetEntryNew)) {
                mifEntrySetEntryOld.getMifEntryExperiment().remove(mifEntryExperiment);
                mifEntrySetEntryOld = em.merge(mifEntrySetEntryOld);
            }
            if (mifEntrySetEntryNew != null && !mifEntrySetEntryNew.equals(mifEntrySetEntryOld)) {
                mifEntrySetEntryNew.getMifEntryExperiment().add(mifEntryExperiment);
                em.merge(mifEntrySetEntryNew);
            }
            for (MIFExperimentFeatDetecMethod mifExperimentFeatDetecMethodOldMIFExperimentFeatDetecMethod : mifExperimentFeatDetecMethodOld) {
                if (!mifExperimentFeatDetecMethodNew.contains(mifExperimentFeatDetecMethodOldMIFExperimentFeatDetecMethod)) {
                    mifExperimentFeatDetecMethodOldMIFExperimentFeatDetecMethod.setMifEntryExperiment(null);
                    mifExperimentFeatDetecMethodOldMIFExperimentFeatDetecMethod = em.merge(mifExperimentFeatDetecMethodOldMIFExperimentFeatDetecMethod);
                }
            }
            for (MIFExperimentFeatDetecMethod mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod : mifExperimentFeatDetecMethodNew) {
                if (!mifExperimentFeatDetecMethodOld.contains(mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod = mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod.getMifEntryExperiment();
                    mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod.setMifEntryExperiment(mifEntryExperiment);
                    mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod = em.merge(mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod);
                    if (oldMifEntryExperimentOfMifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod != null && !oldMifEntryExperimentOfMifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod.getMifExperimentFeatDetecMethod().remove(mifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod);
                        em.merge(oldMifEntryExperimentOfMifExperimentFeatDetecMethodNewMIFExperimentFeatDetecMethod);
                    }
                }
            }
            for (MIFExperimentInterDetecMethod mifExperimentInterDetecMethodOldMIFExperimentInterDetecMethod : mifExperimentInterDetecMethodOld) {
                if (!mifExperimentInterDetecMethodNew.contains(mifExperimentInterDetecMethodOldMIFExperimentInterDetecMethod)) {
                    mifExperimentInterDetecMethodOldMIFExperimentInterDetecMethod.setMifEntryExperiment(null);
                    mifExperimentInterDetecMethodOldMIFExperimentInterDetecMethod = em.merge(mifExperimentInterDetecMethodOldMIFExperimentInterDetecMethod);
                }
            }
            for (MIFExperimentInterDetecMethod mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod : mifExperimentInterDetecMethodNew) {
                if (!mifExperimentInterDetecMethodOld.contains(mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod = mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod.getMifEntryExperiment();
                    mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod.setMifEntryExperiment(mifEntryExperiment);
                    mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod = em.merge(mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod);
                    if (oldMifEntryExperimentOfMifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod != null && !oldMifEntryExperimentOfMifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod.getMifExperimentInterDetecMethod().remove(mifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod);
                        em.merge(oldMifEntryExperimentOfMifExperimentInterDetecMethodNewMIFExperimentInterDetecMethod);
                    }
                }
            }
            for (MIFExperimentPartIdentMethod mifExperimentPartIdentMethodOldMIFExperimentPartIdentMethod : mifExperimentPartIdentMethodOld) {
                if (!mifExperimentPartIdentMethodNew.contains(mifExperimentPartIdentMethodOldMIFExperimentPartIdentMethod)) {
                    mifExperimentPartIdentMethodOldMIFExperimentPartIdentMethod.setMifEntryExperiment(null);
                    mifExperimentPartIdentMethodOldMIFExperimentPartIdentMethod = em.merge(mifExperimentPartIdentMethodOldMIFExperimentPartIdentMethod);
                }
            }
            for (MIFExperimentPartIdentMethod mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod : mifExperimentPartIdentMethodNew) {
                if (!mifExperimentPartIdentMethodOld.contains(mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod = mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod.getMifEntryExperiment();
                    mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod.setMifEntryExperiment(mifEntryExperiment);
                    mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod = em.merge(mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod);
                    if (oldMifEntryExperimentOfMifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod != null && !oldMifEntryExperimentOfMifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod.getMifExperimentPartIdentMethod().remove(mifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod);
                        em.merge(oldMifEntryExperimentOfMifExperimentPartIdentMethodNewMIFExperimentPartIdentMethod);
                    }
                }
            }
            for (MIFOtherAlias mifOtherAliasOldMIFOtherAlias : mifOtherAliasOld) {
                if (!mifOtherAliasNew.contains(mifOtherAliasOldMIFOtherAlias)) {
                    mifOtherAliasOldMIFOtherAlias.setMifEntryExperiment(null);
                    mifOtherAliasOldMIFOtherAlias = em.merge(mifOtherAliasOldMIFOtherAlias);
                }
            }
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAlias : mifOtherAliasNew) {
                if (!mifOtherAliasOld.contains(mifOtherAliasNewMIFOtherAlias)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherAliasNewMIFOtherAlias = mifOtherAliasNewMIFOtherAlias.getMifEntryExperiment();
                    mifOtherAliasNewMIFOtherAlias.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherAliasNewMIFOtherAlias = em.merge(mifOtherAliasNewMIFOtherAlias);
                    if (oldMifEntryExperimentOfMifOtherAliasNewMIFOtherAlias != null && !oldMifEntryExperimentOfMifOtherAliasNewMIFOtherAlias.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherAliasNewMIFOtherAlias.getMifOtherAlias().remove(mifOtherAliasNewMIFOtherAlias);
                        em.merge(oldMifEntryExperimentOfMifOtherAliasNewMIFOtherAlias);
                    }
                }
            }
            for (MIFOtherAttribute mifOtherAttributeOldMIFOtherAttribute : mifOtherAttributeOld) {
                if (!mifOtherAttributeNew.contains(mifOtherAttributeOldMIFOtherAttribute)) {
                    mifOtherAttributeOldMIFOtherAttribute.setMifEntryExperiment(null);
                    mifOtherAttributeOldMIFOtherAttribute = em.merge(mifOtherAttributeOldMIFOtherAttribute);
                }
            }
            for (MIFOtherAttribute mifOtherAttributeNewMIFOtherAttribute : mifOtherAttributeNew) {
                if (!mifOtherAttributeOld.contains(mifOtherAttributeNewMIFOtherAttribute)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherAttributeNewMIFOtherAttribute = mifOtherAttributeNewMIFOtherAttribute.getMifEntryExperiment();
                    mifOtherAttributeNewMIFOtherAttribute.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherAttributeNewMIFOtherAttribute = em.merge(mifOtherAttributeNewMIFOtherAttribute);
                    if (oldMifEntryExperimentOfMifOtherAttributeNewMIFOtherAttribute != null && !oldMifEntryExperimentOfMifOtherAttributeNewMIFOtherAttribute.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherAttributeNewMIFOtherAttribute.getMifOtherAttribute().remove(mifOtherAttributeNewMIFOtherAttribute);
                        em.merge(oldMifEntryExperimentOfMifOtherAttributeNewMIFOtherAttribute);
                    }
                }
            }
            for (MIFOtherBibRef mifOtherBibRefOldMIFOtherBibRef : mifOtherBibRefOld) {
                if (!mifOtherBibRefNew.contains(mifOtherBibRefOldMIFOtherBibRef)) {
                    mifOtherBibRefOldMIFOtherBibRef.setMifEntryExperiment(null);
                    mifOtherBibRefOldMIFOtherBibRef = em.merge(mifOtherBibRefOldMIFOtherBibRef);
                }
            }
            for (MIFOtherBibRef mifOtherBibRefNewMIFOtherBibRef : mifOtherBibRefNew) {
                if (!mifOtherBibRefOld.contains(mifOtherBibRefNewMIFOtherBibRef)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherBibRefNewMIFOtherBibRef = mifOtherBibRefNewMIFOtherBibRef.getMifEntryExperiment();
                    mifOtherBibRefNewMIFOtherBibRef.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherBibRefNewMIFOtherBibRef = em.merge(mifOtherBibRefNewMIFOtherBibRef);
                    if (oldMifEntryExperimentOfMifOtherBibRefNewMIFOtherBibRef != null && !oldMifEntryExperimentOfMifOtherBibRefNewMIFOtherBibRef.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherBibRefNewMIFOtherBibRef.getMifOtherBibRef().remove(mifOtherBibRefNewMIFOtherBibRef);
                        em.merge(oldMifEntryExperimentOfMifOtherBibRefNewMIFOtherBibRef);
                    }
                }
            }
            for (MIFOtherBioSourceType mifOtherBioSourceTypeOldMIFOtherBioSourceType : mifOtherBioSourceTypeOld) {
                if (!mifOtherBioSourceTypeNew.contains(mifOtherBioSourceTypeOldMIFOtherBioSourceType)) {
                    mifOtherBioSourceTypeOldMIFOtherBioSourceType.setMifEntryExperiment(null);
                    mifOtherBioSourceTypeOldMIFOtherBioSourceType = em.merge(mifOtherBioSourceTypeOldMIFOtherBioSourceType);
                }
            }
            for (MIFOtherBioSourceType mifOtherBioSourceTypeNewMIFOtherBioSourceType : mifOtherBioSourceTypeNew) {
                if (!mifOtherBioSourceTypeOld.contains(mifOtherBioSourceTypeNewMIFOtherBioSourceType)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherBioSourceTypeNewMIFOtherBioSourceType = mifOtherBioSourceTypeNewMIFOtherBioSourceType.getMifEntryExperiment();
                    mifOtherBioSourceTypeNewMIFOtherBioSourceType.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherBioSourceTypeNewMIFOtherBioSourceType = em.merge(mifOtherBioSourceTypeNewMIFOtherBioSourceType);
                    if (oldMifEntryExperimentOfMifOtherBioSourceTypeNewMIFOtherBioSourceType != null && !oldMifEntryExperimentOfMifOtherBioSourceTypeNewMIFOtherBioSourceType.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherBioSourceTypeNewMIFOtherBioSourceType.getMifOtherBioSourceType().remove(mifOtherBioSourceTypeNewMIFOtherBioSourceType);
                        em.merge(oldMifEntryExperimentOfMifOtherBioSourceTypeNewMIFOtherBioSourceType);
                    }
                }
            }
            for (MIFOtherConfidence mifOtherConfidenceOldMIFOtherConfidence : mifOtherConfidenceOld) {
                if (!mifOtherConfidenceNew.contains(mifOtherConfidenceOldMIFOtherConfidence)) {
                    mifOtherConfidenceOldMIFOtherConfidence.setMifEntryExperiment(null);
                    mifOtherConfidenceOldMIFOtherConfidence = em.merge(mifOtherConfidenceOldMIFOtherConfidence);
                }
            }
            for (MIFOtherConfidence mifOtherConfidenceNewMIFOtherConfidence : mifOtherConfidenceNew) {
                if (!mifOtherConfidenceOld.contains(mifOtherConfidenceNewMIFOtherConfidence)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherConfidenceNewMIFOtherConfidence = mifOtherConfidenceNewMIFOtherConfidence.getMifEntryExperiment();
                    mifOtherConfidenceNewMIFOtherConfidence.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherConfidenceNewMIFOtherConfidence = em.merge(mifOtherConfidenceNewMIFOtherConfidence);
                    if (oldMifEntryExperimentOfMifOtherConfidenceNewMIFOtherConfidence != null && !oldMifEntryExperimentOfMifOtherConfidenceNewMIFOtherConfidence.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherConfidenceNewMIFOtherConfidence.getMifOtherConfidence().remove(mifOtherConfidenceNewMIFOtherConfidence);
                        em.merge(oldMifEntryExperimentOfMifOtherConfidenceNewMIFOtherConfidence);
                    }
                }
            }
            for (MIFOtherXRef mifOtherXRefOldMIFOtherXRef : mifOtherXRefOld) {
                if (!mifOtherXRefNew.contains(mifOtherXRefOldMIFOtherXRef)) {
                    mifOtherXRefOldMIFOtherXRef.setMifEntryExperiment(null);
                    mifOtherXRefOldMIFOtherXRef = em.merge(mifOtherXRefOldMIFOtherXRef);
                }
            }
            for (MIFOtherXRef mifOtherXRefNewMIFOtherXRef : mifOtherXRefNew) {
                if (!mifOtherXRefOld.contains(mifOtherXRefNewMIFOtherXRef)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherXRefNewMIFOtherXRef = mifOtherXRefNewMIFOtherXRef.getMifEntryExperiment();
                    mifOtherXRefNewMIFOtherXRef.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherXRefNewMIFOtherXRef = em.merge(mifOtherXRefNewMIFOtherXRef);
                    if (oldMifEntryExperimentOfMifOtherXRefNewMIFOtherXRef != null && !oldMifEntryExperimentOfMifOtherXRefNewMIFOtherXRef.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherXRefNewMIFOtherXRef.getMifOtherXRef().remove(mifOtherXRefNewMIFOtherXRef);
                        em.merge(oldMifEntryExperimentOfMifOtherXRefNewMIFOtherXRef);
                    }
                }
            }
            for (MIFOtherXRefGO mifOtherXRefGOOldMIFOtherXRefGO : mifOtherXRefGOOld) {
                if (!mifOtherXRefGONew.contains(mifOtherXRefGOOldMIFOtherXRefGO)) {
                    mifOtherXRefGOOldMIFOtherXRefGO.setMifEntryExperiment(null);
                    mifOtherXRefGOOldMIFOtherXRefGO = em.merge(mifOtherXRefGOOldMIFOtherXRefGO);
                }
            }
            for (MIFOtherXRefGO mifOtherXRefGONewMIFOtherXRefGO : mifOtherXRefGONew) {
                if (!mifOtherXRefGOOld.contains(mifOtherXRefGONewMIFOtherXRefGO)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherXRefGONewMIFOtherXRefGO = mifOtherXRefGONewMIFOtherXRefGO.getMifEntryExperiment();
                    mifOtherXRefGONewMIFOtherXRefGO.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherXRefGONewMIFOtherXRefGO = em.merge(mifOtherXRefGONewMIFOtherXRefGO);
                    if (oldMifEntryExperimentOfMifOtherXRefGONewMIFOtherXRefGO != null && !oldMifEntryExperimentOfMifOtherXRefGONewMIFOtherXRefGO.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherXRefGONewMIFOtherXRefGO.getMifOtherXRefGO().remove(mifOtherXRefGONewMIFOtherXRefGO);
                        em.merge(oldMifEntryExperimentOfMifOtherXRefGONewMIFOtherXRefGO);
                    }
                }
            }
            for (MIFOtherXRefPubMed mifOtherXRefPubMedOldMIFOtherXRefPubMed : mifOtherXRefPubMedOld) {
                if (!mifOtherXRefPubMedNew.contains(mifOtherXRefPubMedOldMIFOtherXRefPubMed)) {
                    mifOtherXRefPubMedOldMIFOtherXRefPubMed.setMifEntryExperiment(null);
                    mifOtherXRefPubMedOldMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedOldMIFOtherXRefPubMed);
                }
            }
            for (MIFOtherXRefPubMed mifOtherXRefPubMedNewMIFOtherXRefPubMed : mifOtherXRefPubMedNew) {
                if (!mifOtherXRefPubMedOld.contains(mifOtherXRefPubMedNewMIFOtherXRefPubMed)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherXRefPubMedNewMIFOtherXRefPubMed = mifOtherXRefPubMedNewMIFOtherXRefPubMed.getMifEntryExperiment();
                    mifOtherXRefPubMedNewMIFOtherXRefPubMed.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherXRefPubMedNewMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedNewMIFOtherXRefPubMed);
                    if (oldMifEntryExperimentOfMifOtherXRefPubMedNewMIFOtherXRefPubMed != null && !oldMifEntryExperimentOfMifOtherXRefPubMedNewMIFOtherXRefPubMed.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherXRefPubMedNewMIFOtherXRefPubMed.getMifOtherXRefPubMed().remove(mifOtherXRefPubMedNewMIFOtherXRefPubMed);
                        em.merge(oldMifEntryExperimentOfMifOtherXRefPubMedNewMIFOtherXRefPubMed);
                    }
                }
            }
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqOldMIFOtherXRefRefSeq : mifOtherXRefRefSeqOld) {
                if (!mifOtherXRefRefSeqNew.contains(mifOtherXRefRefSeqOldMIFOtherXRefRefSeq)) {
                    mifOtherXRefRefSeqOldMIFOtherXRefRefSeq.setMifEntryExperiment(null);
                    mifOtherXRefRefSeqOldMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqOldMIFOtherXRefRefSeq);
                }
            }
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqNewMIFOtherXRefRefSeq : mifOtherXRefRefSeqNew) {
                if (!mifOtherXRefRefSeqOld.contains(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq = mifOtherXRefRefSeqNewMIFOtherXRefRefSeq.getMifEntryExperiment();
                    mifOtherXRefRefSeqNewMIFOtherXRefRefSeq.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherXRefRefSeqNewMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                    if (oldMifEntryExperimentOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq != null && !oldMifEntryExperimentOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq.getMifOtherXRefRefSeq().remove(mifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                        em.merge(oldMifEntryExperimentOfMifOtherXRefRefSeqNewMIFOtherXRefRefSeq);
                    }
                }
            }
            for (MIFOtherXRefUniprot mifOtherXRefUniprotOldMIFOtherXRefUniprot : mifOtherXRefUniprotOld) {
                if (!mifOtherXRefUniprotNew.contains(mifOtherXRefUniprotOldMIFOtherXRefUniprot)) {
                    mifOtherXRefUniprotOldMIFOtherXRefUniprot.setMifEntryExperiment(null);
                    mifOtherXRefUniprotOldMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotOldMIFOtherXRefUniprot);
                }
            }
            for (MIFOtherXRefUniprot mifOtherXRefUniprotNewMIFOtherXRefUniprot : mifOtherXRefUniprotNew) {
                if (!mifOtherXRefUniprotOld.contains(mifOtherXRefUniprotNewMIFOtherXRefUniprot)) {
                    MIFEntryExperiment oldMifEntryExperimentOfMifOtherXRefUniprotNewMIFOtherXRefUniprot = mifOtherXRefUniprotNewMIFOtherXRefUniprot.getMifEntryExperiment();
                    mifOtherXRefUniprotNewMIFOtherXRefUniprot.setMifEntryExperiment(mifEntryExperiment);
                    mifOtherXRefUniprotNewMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotNewMIFOtherXRefUniprot);
                    if (oldMifEntryExperimentOfMifOtherXRefUniprotNewMIFOtherXRefUniprot != null && !oldMifEntryExperimentOfMifOtherXRefUniprotNewMIFOtherXRefUniprot.equals(mifEntryExperiment)) {
                        oldMifEntryExperimentOfMifOtherXRefUniprotNewMIFOtherXRefUniprot.getMifOtherXRefUniprot().remove(mifOtherXRefUniprotNewMIFOtherXRefUniprot);
                        em.merge(oldMifEntryExperimentOfMifOtherXRefUniprotNewMIFOtherXRefUniprot);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mifEntryExperiment.getWid();
                if (findMIFEntryExperiment(id) == null) {
                    throw new NonexistentEntityException("The mIFEntryExperiment with id " + id + " no longer exists.");
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
            MIFEntryExperiment MIFEntryExperiment;
            try {
                MIFEntryExperiment = em.getReference(MIFEntryExperiment.class, id);
                MIFEntryExperiment.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MIFEntryExperiment with id " + id + " no longer exists.", enfe);
            }
            MIFEntryInteraction mifEntryInteraction = MIFEntryExperiment.getMifEntryInteraction();
            if (mifEntryInteraction != null) {
                mifEntryInteraction.getMifEntryExperiment().remove(MIFEntryExperiment);
                em.merge(mifEntryInteraction);
            }
            MIFEntrySetEntry mifEntrySetEntry = MIFEntryExperiment.getMifEntrySetEntry();
            if (mifEntrySetEntry != null) {
                mifEntrySetEntry.getMifEntryExperiment().remove(MIFEntryExperiment);
                em.merge(mifEntrySetEntry);
            }
            Set<MIFExperimentFeatDetecMethod> mifExperimentFeatDetecMethod = MIFEntryExperiment.getMifExperimentFeatDetecMethod();
            for (MIFExperimentFeatDetecMethod mifExperimentFeatDetecMethodMIFExperimentFeatDetecMethod : mifExperimentFeatDetecMethod) {
                mifExperimentFeatDetecMethodMIFExperimentFeatDetecMethod.setMifEntryExperiment(null);
                mifExperimentFeatDetecMethodMIFExperimentFeatDetecMethod = em.merge(mifExperimentFeatDetecMethodMIFExperimentFeatDetecMethod);
            }
            Set<MIFExperimentInterDetecMethod> mifExperimentInterDetecMethod = MIFEntryExperiment.getMifExperimentInterDetecMethod();
            for (MIFExperimentInterDetecMethod mifExperimentInterDetecMethodMIFExperimentInterDetecMethod : mifExperimentInterDetecMethod) {
                mifExperimentInterDetecMethodMIFExperimentInterDetecMethod.setMifEntryExperiment(null);
                mifExperimentInterDetecMethodMIFExperimentInterDetecMethod = em.merge(mifExperimentInterDetecMethodMIFExperimentInterDetecMethod);
            }
            Set<MIFExperimentPartIdentMethod> mifExperimentPartIdentMethod = MIFEntryExperiment.getMifExperimentPartIdentMethod();
            for (MIFExperimentPartIdentMethod mifExperimentPartIdentMethodMIFExperimentPartIdentMethod : mifExperimentPartIdentMethod) {
                mifExperimentPartIdentMethodMIFExperimentPartIdentMethod.setMifEntryExperiment(null);
                mifExperimentPartIdentMethodMIFExperimentPartIdentMethod = em.merge(mifExperimentPartIdentMethodMIFExperimentPartIdentMethod);
            }
            Set<MIFOtherAlias> mifOtherAlias = MIFEntryExperiment.getMifOtherAlias();
            for (MIFOtherAlias mifOtherAliasMIFOtherAlias : mifOtherAlias) {
                mifOtherAliasMIFOtherAlias.setMifEntryExperiment(null);
                mifOtherAliasMIFOtherAlias = em.merge(mifOtherAliasMIFOtherAlias);
            }
            Set<MIFOtherAttribute> mifOtherAttribute = MIFEntryExperiment.getMifOtherAttribute();
            for (MIFOtherAttribute mifOtherAttributeMIFOtherAttribute : mifOtherAttribute) {
                mifOtherAttributeMIFOtherAttribute.setMifEntryExperiment(null);
                mifOtherAttributeMIFOtherAttribute = em.merge(mifOtherAttributeMIFOtherAttribute);
            }
            Set<MIFOtherBibRef> mifOtherBibRef = MIFEntryExperiment.getMifOtherBibRef();
            for (MIFOtherBibRef mifOtherBibRefMIFOtherBibRef : mifOtherBibRef) {
                mifOtherBibRefMIFOtherBibRef.setMifEntryExperiment(null);
                mifOtherBibRefMIFOtherBibRef = em.merge(mifOtherBibRefMIFOtherBibRef);
            }
            Set<MIFOtherBioSourceType> mifOtherBioSourceType = MIFEntryExperiment.getMifOtherBioSourceType();
            for (MIFOtherBioSourceType mifOtherBioSourceTypeMIFOtherBioSourceType : mifOtherBioSourceType) {
                mifOtherBioSourceTypeMIFOtherBioSourceType.setMifEntryExperiment(null);
                mifOtherBioSourceTypeMIFOtherBioSourceType = em.merge(mifOtherBioSourceTypeMIFOtherBioSourceType);
            }
            Set<MIFOtherConfidence> mifOtherConfidence = MIFEntryExperiment.getMifOtherConfidence();
            for (MIFOtherConfidence mifOtherConfidenceMIFOtherConfidence : mifOtherConfidence) {
                mifOtherConfidenceMIFOtherConfidence.setMifEntryExperiment(null);
                mifOtherConfidenceMIFOtherConfidence = em.merge(mifOtherConfidenceMIFOtherConfidence);
            }
            Set<MIFOtherXRef> mifOtherXRef = MIFEntryExperiment.getMifOtherXRef();
            for (MIFOtherXRef mifOtherXRefMIFOtherXRef : mifOtherXRef) {
                mifOtherXRefMIFOtherXRef.setMifEntryExperiment(null);
                mifOtherXRefMIFOtherXRef = em.merge(mifOtherXRefMIFOtherXRef);
            }
            Set<MIFOtherXRefGO> mifOtherXRefGO = MIFEntryExperiment.getMifOtherXRefGO();
            for (MIFOtherXRefGO mifOtherXRefGOMIFOtherXRefGO : mifOtherXRefGO) {
                mifOtherXRefGOMIFOtherXRefGO.setMifEntryExperiment(null);
                mifOtherXRefGOMIFOtherXRefGO = em.merge(mifOtherXRefGOMIFOtherXRefGO);
            }
            Set<MIFOtherXRefPubMed> mifOtherXRefPubMed = MIFEntryExperiment.getMifOtherXRefPubMed();
            for (MIFOtherXRefPubMed mifOtherXRefPubMedMIFOtherXRefPubMed : mifOtherXRefPubMed) {
                mifOtherXRefPubMedMIFOtherXRefPubMed.setMifEntryExperiment(null);
                mifOtherXRefPubMedMIFOtherXRefPubMed = em.merge(mifOtherXRefPubMedMIFOtherXRefPubMed);
            }
            Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq = MIFEntryExperiment.getMifOtherXRefRefSeq();
            for (MIFOtherXRefRefSeq mifOtherXRefRefSeqMIFOtherXRefRefSeq : mifOtherXRefRefSeq) {
                mifOtherXRefRefSeqMIFOtherXRefRefSeq.setMifEntryExperiment(null);
                mifOtherXRefRefSeqMIFOtherXRefRefSeq = em.merge(mifOtherXRefRefSeqMIFOtherXRefRefSeq);
            }
            Set<MIFOtherXRefUniprot> mifOtherXRefUniprot = MIFEntryExperiment.getMifOtherXRefUniprot();
            for (MIFOtherXRefUniprot mifOtherXRefUniprotMIFOtherXRefUniprot : mifOtherXRefUniprot) {
                mifOtherXRefUniprotMIFOtherXRefUniprot.setMifEntryExperiment(null);
                mifOtherXRefUniprotMIFOtherXRefUniprot = em.merge(mifOtherXRefUniprotMIFOtherXRefUniprot);
            }
            em.remove(MIFEntryExperiment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MIFEntryExperiment> findMIFEntryExperimentEntities() {
        return findMIFEntryExperimentEntities(true, -1, -1);
    }

    public List<MIFEntryExperiment> findMIFEntryExperimentEntities(int maxResults, int firstResult) {
        return findMIFEntryExperimentEntities(false, maxResults, firstResult);
    }

    private List<MIFEntryExperiment> findMIFEntryExperimentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MIFEntryExperiment.class));
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

    public MIFEntryExperiment findMIFEntryExperiment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MIFEntryExperiment.class, id);
        } finally {
            em.close();
        }
    }

    public int getMIFEntryExperimentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MIFEntryExperiment> rt = cq.from(MIFEntryExperiment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
