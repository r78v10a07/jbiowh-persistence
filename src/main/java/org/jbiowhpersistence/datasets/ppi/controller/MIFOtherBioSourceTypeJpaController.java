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
import org.jbiowhpersistence.datasets.ppi.entities.MIFBioSourceTypeCellType;
import org.jbiowhpersistence.datasets.ppi.entities.MIFBioSourceTypeCompartment;
import org.jbiowhpersistence.datasets.ppi.entities.MIFBioSourceTypeTissue;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryExperiment;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteractor;
import org.jbiowhpersistence.datasets.ppi.entities.MIFInteractionParticipant;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherAlias;
import org.jbiowhpersistence.datasets.ppi.entities.MIFOtherBioSourceType;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the MIFOtherBioSourceTypeJpaController
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Sep 17, 2012
 */
public class MIFOtherBioSourceTypeJpaController extends AbstractMIFJpaController<MIFOtherBioSourceType> implements Serializable {

    public MIFOtherBioSourceTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MIFOtherBioSourceType mifOtherBioSourceType) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + mifOtherBioSourceType.getWid());
        if (mifOtherBioSourceType.getMifBioSourceTypeCellType() == null) {
            mifOtherBioSourceType.setMifBioSourceTypeCellType(new HashSet<MIFBioSourceTypeCellType>());
        }
        if (mifOtherBioSourceType.getMifBioSourceTypeCompartment() == null) {
            mifOtherBioSourceType.setMifBioSourceTypeCompartment(new HashSet<MIFBioSourceTypeCompartment>());
        }
        if (mifOtherBioSourceType.getMifBioSourceTypeTissueD() == null) {
            mifOtherBioSourceType.setMifBioSourceTypeTissueD(new HashSet<MIFBioSourceTypeTissue>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mifOtherBioSourceType.setTaxonomy(createTaxonomy(emf, em, mifOtherBioSourceType.getTaxonomy()));
            mifOtherBioSourceType.setMifEntryExperiment(createMIFEntryExperiment(emf, em, mifOtherBioSourceType.getMifEntryExperiment()));
            mifOtherBioSourceType.setMifEntryInteractor(createMIFEntryInteractor(emf, em, mifOtherBioSourceType.getMifEntryInteractor()));
            mifOtherBioSourceType.setMifInteractionParticipant(createMIFInteractionParticipant(emf, em, mifOtherBioSourceType.getMifInteractionParticipant()));
            mifOtherBioSourceType.setMifOtherAlias(createMIFOtherAlias(emf, em, mifOtherBioSourceType.getMifOtherAlias()));

            if (!mifOtherBioSourceType.getMifBioSourceTypeCellType().isEmpty()) {
                Set<MIFBioSourceTypeCellType> attachedMifBioSourceTypeCellType = new HashSet<>();
                for (MIFBioSourceTypeCellType mifBioSourceTypeCellTypeMIFBioSourceTypeCellTypeToAttach : mifOtherBioSourceType.getMifBioSourceTypeCellType()) {
                    MIFBioSourceTypeCellType mifBioSourceTypeCell = em.find(mifBioSourceTypeCellTypeMIFBioSourceTypeCellTypeToAttach.getClass(), mifBioSourceTypeCellTypeMIFBioSourceTypeCellTypeToAttach.getWid());
                    if (mifBioSourceTypeCell != null) {
                        attachedMifBioSourceTypeCellType.add(mifBioSourceTypeCell);
                    } else {
                        attachedMifBioSourceTypeCellType.add(mifBioSourceTypeCellTypeMIFBioSourceTypeCellTypeToAttach);
                    }
                }
                mifOtherBioSourceType.setMifBioSourceTypeCellType(attachedMifBioSourceTypeCellType);
            }
            if (!mifOtherBioSourceType.getMifBioSourceTypeCompartment().isEmpty()) {
                Set<MIFBioSourceTypeCompartment> attachedMifBioSourceTypeCompartment = new HashSet<>();
                for (MIFBioSourceTypeCompartment mifBioSourceTypeCompartmentMIFBioSourceTypeCompartmentToAttach : mifOtherBioSourceType.getMifBioSourceTypeCompartment()) {
                    MIFBioSourceTypeCompartment mifBioSourceTypeCompartment = em.find(mifBioSourceTypeCompartmentMIFBioSourceTypeCompartmentToAttach.getClass(), mifBioSourceTypeCompartmentMIFBioSourceTypeCompartmentToAttach.getWid());
                    if (mifBioSourceTypeCompartment != null) {
                        attachedMifBioSourceTypeCompartment.add(mifBioSourceTypeCompartment);
                    } else {
                        attachedMifBioSourceTypeCompartment.add(mifBioSourceTypeCompartmentMIFBioSourceTypeCompartmentToAttach);
                    }
                }
                mifOtherBioSourceType.setMifBioSourceTypeCompartment(attachedMifBioSourceTypeCompartment);
            }
            if (!mifOtherBioSourceType.getMifBioSourceTypeTissueD().isEmpty()) {
                Set<MIFBioSourceTypeTissue> attachedMifBioSourceTypeTissueD = new HashSet<>();
                for (MIFBioSourceTypeTissue mifBioSourceTypeTissueDMIFBioSourceTypeTissueToAttach : mifOtherBioSourceType.getMifBioSourceTypeTissueD()) {
                    MIFBioSourceTypeTissue mifBioSourceTypeTissue = em.find(mifBioSourceTypeTissueDMIFBioSourceTypeTissueToAttach.getClass(), mifBioSourceTypeTissueDMIFBioSourceTypeTissueToAttach.getWid());
                    if (mifBioSourceTypeTissue != null) {
                        attachedMifBioSourceTypeTissueD.add(mifBioSourceTypeTissue);
                    } else {
                        attachedMifBioSourceTypeTissueD.add(mifBioSourceTypeTissueDMIFBioSourceTypeTissueToAttach);
                    }
                }
                mifOtherBioSourceType.setMifBioSourceTypeTissueD(attachedMifBioSourceTypeTissueD);
            }
            em.persist(mifOtherBioSourceType);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMIFOtherBioSourceType(mifOtherBioSourceType.getWid()) != null) {
                throw new PreexistingEntityException("MIFOtherBioSourceType " + mifOtherBioSourceType + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MIFOtherBioSourceType mifOtherBioSourceType) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + mifOtherBioSourceType.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MIFOtherBioSourceType persistentMIFOtherBioSourceType = em.find(MIFOtherBioSourceType.class, mifOtherBioSourceType.getWid());
            MIFEntryExperiment mifEntryExperimentOld = persistentMIFOtherBioSourceType.getMifEntryExperiment();
            MIFEntryExperiment mifEntryExperimentNew = mifOtherBioSourceType.getMifEntryExperiment();
            MIFEntryInteractor mifEntryInteractorOld = persistentMIFOtherBioSourceType.getMifEntryInteractor();
            MIFEntryInteractor mifEntryInteractorNew = mifOtherBioSourceType.getMifEntryInteractor();
            MIFInteractionParticipant mifInteractionParticipantOld = persistentMIFOtherBioSourceType.getMifInteractionParticipant();
            MIFInteractionParticipant mifInteractionParticipantNew = mifOtherBioSourceType.getMifInteractionParticipant();
            Set<MIFBioSourceTypeCellType> mifBioSourceTypeCellTypeOld = persistentMIFOtherBioSourceType.getMifBioSourceTypeCellType();
            Set<MIFBioSourceTypeCellType> mifBioSourceTypeCellTypeNew = mifOtherBioSourceType.getMifBioSourceTypeCellType();
            Set<MIFBioSourceTypeCompartment> mifBioSourceTypeCompartmentOld = persistentMIFOtherBioSourceType.getMifBioSourceTypeCompartment();
            Set<MIFBioSourceTypeCompartment> mifBioSourceTypeCompartmentNew = mifOtherBioSourceType.getMifBioSourceTypeCompartment();
            Set<MIFBioSourceTypeTissue> mifBioSourceTypeTissueDOld = persistentMIFOtherBioSourceType.getMifBioSourceTypeTissueD();
            Set<MIFBioSourceTypeTissue> mifBioSourceTypeTissueDNew = mifOtherBioSourceType.getMifBioSourceTypeTissueD();
            Set<MIFOtherAlias> mifOtherAliasOld = persistentMIFOtherBioSourceType.getMifOtherAlias();
            Set<MIFOtherAlias> mifOtherAliasNew = mifOtherBioSourceType.getMifOtherAlias();
            if (mifEntryExperimentNew != null) {
                mifEntryExperimentNew = em.getReference(mifEntryExperimentNew.getClass(), mifEntryExperimentNew.getWid());
                mifOtherBioSourceType.setMifEntryExperiment(mifEntryExperimentNew);
            }
            if (mifEntryInteractorNew != null) {
                mifEntryInteractorNew = em.getReference(mifEntryInteractorNew.getClass(), mifEntryInteractorNew.getWid());
                mifOtherBioSourceType.setMifEntryInteractor(mifEntryInteractorNew);
            }
            if (mifInteractionParticipantNew != null) {
                mifInteractionParticipantNew = em.getReference(mifInteractionParticipantNew.getClass(), mifInteractionParticipantNew.getWid());
                mifOtherBioSourceType.setMifInteractionParticipant(mifInteractionParticipantNew);
            }
            Set<MIFBioSourceTypeCellType> attachedMifBioSourceTypeCellTypeNew = new HashSet<>();
            for (MIFBioSourceTypeCellType mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellTypeToAttach : mifBioSourceTypeCellTypeNew) {
                mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellTypeToAttach = em.getReference(mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellTypeToAttach.getClass(), mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellTypeToAttach.getWid());
                attachedMifBioSourceTypeCellTypeNew.add(mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellTypeToAttach);
            }
            mifBioSourceTypeCellTypeNew = attachedMifBioSourceTypeCellTypeNew;
            mifOtherBioSourceType.setMifBioSourceTypeCellType(mifBioSourceTypeCellTypeNew);
            Set<MIFBioSourceTypeCompartment> attachedMifBioSourceTypeCompartmentNew = new HashSet<>();
            for (MIFBioSourceTypeCompartment mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartmentToAttach : mifBioSourceTypeCompartmentNew) {
                mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartmentToAttach = em.getReference(mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartmentToAttach.getClass(), mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartmentToAttach.getWid());
                attachedMifBioSourceTypeCompartmentNew.add(mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartmentToAttach);
            }
            mifBioSourceTypeCompartmentNew = attachedMifBioSourceTypeCompartmentNew;
            mifOtherBioSourceType.setMifBioSourceTypeCompartment(mifBioSourceTypeCompartmentNew);
            Set<MIFBioSourceTypeTissue> attachedMifBioSourceTypeTissueDNew = new HashSet<>();
            for (MIFBioSourceTypeTissue mifBioSourceTypeTissueDNewMIFBioSourceTypeTissueToAttach : mifBioSourceTypeTissueDNew) {
                mifBioSourceTypeTissueDNewMIFBioSourceTypeTissueToAttach = em.getReference(mifBioSourceTypeTissueDNewMIFBioSourceTypeTissueToAttach.getClass(), mifBioSourceTypeTissueDNewMIFBioSourceTypeTissueToAttach.getWid());
                attachedMifBioSourceTypeTissueDNew.add(mifBioSourceTypeTissueDNewMIFBioSourceTypeTissueToAttach);
            }
            mifBioSourceTypeTissueDNew = attachedMifBioSourceTypeTissueDNew;
            mifOtherBioSourceType.setMifBioSourceTypeTissueD(mifBioSourceTypeTissueDNew);
            Set<MIFOtherAlias> attachedMifOtherAliasNew = new HashSet<>();
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAliasToAttach : mifOtherAliasNew) {
                mifOtherAliasNewMIFOtherAliasToAttach = em.getReference(mifOtherAliasNewMIFOtherAliasToAttach.getClass(), mifOtherAliasNewMIFOtherAliasToAttach.getWid());
                attachedMifOtherAliasNew.add(mifOtherAliasNewMIFOtherAliasToAttach);
            }
            mifOtherAliasNew = attachedMifOtherAliasNew;
            mifOtherBioSourceType.setMifOtherAlias(mifOtherAliasNew);
            mifOtherBioSourceType = em.merge(mifOtherBioSourceType);
            if (mifEntryExperimentOld != null && !mifEntryExperimentOld.equals(mifEntryExperimentNew)) {
                mifEntryExperimentOld.getMifOtherBioSourceType().remove(mifOtherBioSourceType);
                mifEntryExperimentOld = em.merge(mifEntryExperimentOld);
            }
            if (mifEntryExperimentNew != null && !mifEntryExperimentNew.equals(mifEntryExperimentOld)) {
                mifEntryExperimentNew.getMifOtherBioSourceType().add(mifOtherBioSourceType);
                em.merge(mifEntryExperimentNew);
            }
            if (mifEntryInteractorOld != null && !mifEntryInteractorOld.equals(mifEntryInteractorNew)) {
                mifEntryInteractorOld.getMifOtherBioSourceType().remove(mifOtherBioSourceType);
                mifEntryInteractorOld = em.merge(mifEntryInteractorOld);
            }
            if (mifEntryInteractorNew != null && !mifEntryInteractorNew.equals(mifEntryInteractorOld)) {
                mifEntryInteractorNew.getMifOtherBioSourceType().add(mifOtherBioSourceType);
                em.merge(mifEntryInteractorNew);
            }
            if (mifInteractionParticipantOld != null && !mifInteractionParticipantOld.equals(mifInteractionParticipantNew)) {
                mifInteractionParticipantOld.getMifOtherBioSourceType().remove(mifOtherBioSourceType);
                mifInteractionParticipantOld = em.merge(mifInteractionParticipantOld);
            }
            if (mifInteractionParticipantNew != null && !mifInteractionParticipantNew.equals(mifInteractionParticipantOld)) {
                mifInteractionParticipantNew.getMifOtherBioSourceType().add(mifOtherBioSourceType);
                em.merge(mifInteractionParticipantNew);
            }
            for (MIFBioSourceTypeCellType mifBioSourceTypeCellTypeOldMIFBioSourceTypeCellType : mifBioSourceTypeCellTypeOld) {
                if (!mifBioSourceTypeCellTypeNew.contains(mifBioSourceTypeCellTypeOldMIFBioSourceTypeCellType)) {
                    mifBioSourceTypeCellTypeOldMIFBioSourceTypeCellType.setMifOtherBioSourceType(null);
                    mifBioSourceTypeCellTypeOldMIFBioSourceTypeCellType = em.merge(mifBioSourceTypeCellTypeOldMIFBioSourceTypeCellType);
                }
            }
            for (MIFBioSourceTypeCellType mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType : mifBioSourceTypeCellTypeNew) {
                if (!mifBioSourceTypeCellTypeOld.contains(mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType)) {
                    MIFOtherBioSourceType oldMifOtherBioSourceTypeOfMifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType = mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType.getMifOtherBioSourceType();
                    mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType.setMifOtherBioSourceType(mifOtherBioSourceType);
                    mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType = em.merge(mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType);
                    if (oldMifOtherBioSourceTypeOfMifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType != null && !oldMifOtherBioSourceTypeOfMifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType.equals(mifOtherBioSourceType)) {
                        oldMifOtherBioSourceTypeOfMifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType.getMifBioSourceTypeCellType().remove(mifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType);
                        em.merge(oldMifOtherBioSourceTypeOfMifBioSourceTypeCellTypeNewMIFBioSourceTypeCellType);
                    }
                }
            }
            for (MIFBioSourceTypeCompartment mifBioSourceTypeCompartmentOldMIFBioSourceTypeCompartment : mifBioSourceTypeCompartmentOld) {
                if (!mifBioSourceTypeCompartmentNew.contains(mifBioSourceTypeCompartmentOldMIFBioSourceTypeCompartment)) {
                    mifBioSourceTypeCompartmentOldMIFBioSourceTypeCompartment.setMifOtherBioSourceType(null);
                    mifBioSourceTypeCompartmentOldMIFBioSourceTypeCompartment = em.merge(mifBioSourceTypeCompartmentOldMIFBioSourceTypeCompartment);
                }
            }
            for (MIFBioSourceTypeCompartment mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment : mifBioSourceTypeCompartmentNew) {
                if (!mifBioSourceTypeCompartmentOld.contains(mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment)) {
                    MIFOtherBioSourceType oldMifOtherBioSourceTypeOfMifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment = mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment.getMifOtherBioSourceType();
                    mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment.setMifOtherBioSourceType(mifOtherBioSourceType);
                    mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment = em.merge(mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment);
                    if (oldMifOtherBioSourceTypeOfMifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment != null && !oldMifOtherBioSourceTypeOfMifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment.equals(mifOtherBioSourceType)) {
                        oldMifOtherBioSourceTypeOfMifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment.getMifBioSourceTypeCompartment().remove(mifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment);
                        em.merge(oldMifOtherBioSourceTypeOfMifBioSourceTypeCompartmentNewMIFBioSourceTypeCompartment);
                    }
                }
            }
            for (MIFBioSourceTypeTissue mifBioSourceTypeTissueDOldMIFBioSourceTypeTissue : mifBioSourceTypeTissueDOld) {
                if (!mifBioSourceTypeTissueDNew.contains(mifBioSourceTypeTissueDOldMIFBioSourceTypeTissue)) {
                    mifBioSourceTypeTissueDOldMIFBioSourceTypeTissue.setMifOtherBioSourceType(null);
                    mifBioSourceTypeTissueDOldMIFBioSourceTypeTissue = em.merge(mifBioSourceTypeTissueDOldMIFBioSourceTypeTissue);
                }
            }
            for (MIFBioSourceTypeTissue mifBioSourceTypeTissueDNewMIFBioSourceTypeTissue : mifBioSourceTypeTissueDNew) {
                if (!mifBioSourceTypeTissueDOld.contains(mifBioSourceTypeTissueDNewMIFBioSourceTypeTissue)) {
                    MIFOtherBioSourceType oldMifOtherBioSourceTypeOfMifBioSourceTypeTissueDNewMIFBioSourceTypeTissue = mifBioSourceTypeTissueDNewMIFBioSourceTypeTissue.getMifOtherBioSourceType();
                    mifBioSourceTypeTissueDNewMIFBioSourceTypeTissue.setMifOtherBioSourceType(mifOtherBioSourceType);
                    mifBioSourceTypeTissueDNewMIFBioSourceTypeTissue = em.merge(mifBioSourceTypeTissueDNewMIFBioSourceTypeTissue);
                    if (oldMifOtherBioSourceTypeOfMifBioSourceTypeTissueDNewMIFBioSourceTypeTissue != null && !oldMifOtherBioSourceTypeOfMifBioSourceTypeTissueDNewMIFBioSourceTypeTissue.equals(mifOtherBioSourceType)) {
                        oldMifOtherBioSourceTypeOfMifBioSourceTypeTissueDNewMIFBioSourceTypeTissue.getMifBioSourceTypeTissueD().remove(mifBioSourceTypeTissueDNewMIFBioSourceTypeTissue);
                        em.merge(oldMifOtherBioSourceTypeOfMifBioSourceTypeTissueDNewMIFBioSourceTypeTissue);
                    }
                }
            }
            for (MIFOtherAlias mifOtherAliasOldMIFOtherAlias : mifOtherAliasOld) {
                if (!mifOtherAliasNew.contains(mifOtherAliasOldMIFOtherAlias)) {
                    mifOtherAliasOldMIFOtherAlias.setMifOtherBioSourceType(null);
                    mifOtherAliasOldMIFOtherAlias = em.merge(mifOtherAliasOldMIFOtherAlias);
                }
            }
            for (MIFOtherAlias mifOtherAliasNewMIFOtherAlias : mifOtherAliasNew) {
                if (!mifOtherAliasOld.contains(mifOtherAliasNewMIFOtherAlias)) {
                    MIFOtherBioSourceType oldMifOtherBioSourceTypeOfMifOtherAliasNewMIFOtherAlias = mifOtherAliasNewMIFOtherAlias.getMifOtherBioSourceType();
                    mifOtherAliasNewMIFOtherAlias.setMifOtherBioSourceType(mifOtherBioSourceType);
                    mifOtherAliasNewMIFOtherAlias = em.merge(mifOtherAliasNewMIFOtherAlias);
                    if (oldMifOtherBioSourceTypeOfMifOtherAliasNewMIFOtherAlias != null && !oldMifOtherBioSourceTypeOfMifOtherAliasNewMIFOtherAlias.equals(mifOtherBioSourceType)) {
                        oldMifOtherBioSourceTypeOfMifOtherAliasNewMIFOtherAlias.getMifOtherAlias().remove(mifOtherAliasNewMIFOtherAlias);
                        em.merge(oldMifOtherBioSourceTypeOfMifOtherAliasNewMIFOtherAlias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mifOtherBioSourceType.getWid();
                if (findMIFOtherBioSourceType(id) == null) {
                    throw new NonexistentEntityException("The mIFOtherBioSourceType with id " + id + " no longer exists.");
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
            MIFOtherBioSourceType MIFOtherBioSourceType;
            try {
                MIFOtherBioSourceType = em.getReference(MIFOtherBioSourceType.class, id);
                MIFOtherBioSourceType.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MIFOtherBioSourceType with id " + id + " no longer exists.", enfe);
            }
            MIFEntryExperiment mifEntryExperiment = MIFOtherBioSourceType.getMifEntryExperiment();
            if (mifEntryExperiment != null) {
                mifEntryExperiment.getMifOtherBioSourceType().remove(MIFOtherBioSourceType);
                em.merge(mifEntryExperiment);
            }
            MIFEntryInteractor mifEntryInteractor = MIFOtherBioSourceType.getMifEntryInteractor();
            if (mifEntryInteractor != null) {
                mifEntryInteractor.getMifOtherBioSourceType().remove(MIFOtherBioSourceType);
                em.merge(mifEntryInteractor);
            }
            MIFInteractionParticipant mifInteractionParticipant = MIFOtherBioSourceType.getMifInteractionParticipant();
            if (mifInteractionParticipant != null) {
                mifInteractionParticipant.getMifOtherBioSourceType().remove(MIFOtherBioSourceType);
                em.merge(mifInteractionParticipant);
            }
            Set<MIFBioSourceTypeCellType> mifBioSourceTypeCellType = MIFOtherBioSourceType.getMifBioSourceTypeCellType();
            for (MIFBioSourceTypeCellType mifBioSourceTypeCellTypeMIFBioSourceTypeCellType : mifBioSourceTypeCellType) {
                mifBioSourceTypeCellTypeMIFBioSourceTypeCellType.setMifOtherBioSourceType(null);
                mifBioSourceTypeCellTypeMIFBioSourceTypeCellType = em.merge(mifBioSourceTypeCellTypeMIFBioSourceTypeCellType);
            }
            Set<MIFBioSourceTypeCompartment> mifBioSourceTypeCompartment = MIFOtherBioSourceType.getMifBioSourceTypeCompartment();
            for (MIFBioSourceTypeCompartment mifBioSourceTypeCompartmentMIFBioSourceTypeCompartment : mifBioSourceTypeCompartment) {
                mifBioSourceTypeCompartmentMIFBioSourceTypeCompartment.setMifOtherBioSourceType(null);
                mifBioSourceTypeCompartmentMIFBioSourceTypeCompartment = em.merge(mifBioSourceTypeCompartmentMIFBioSourceTypeCompartment);
            }
            Set<MIFBioSourceTypeTissue> mifBioSourceTypeTissueD = MIFOtherBioSourceType.getMifBioSourceTypeTissueD();
            for (MIFBioSourceTypeTissue mifBioSourceTypeTissueDMIFBioSourceTypeTissue : mifBioSourceTypeTissueD) {
                mifBioSourceTypeTissueDMIFBioSourceTypeTissue.setMifOtherBioSourceType(null);
                mifBioSourceTypeTissueDMIFBioSourceTypeTissue = em.merge(mifBioSourceTypeTissueDMIFBioSourceTypeTissue);
            }
            Set<MIFOtherAlias> mifOtherAlias = MIFOtherBioSourceType.getMifOtherAlias();
            for (MIFOtherAlias mifOtherAliasMIFOtherAlias : mifOtherAlias) {
                mifOtherAliasMIFOtherAlias.setMifOtherBioSourceType(null);
                mifOtherAliasMIFOtherAlias = em.merge(mifOtherAliasMIFOtherAlias);
            }
            em.remove(MIFOtherBioSourceType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MIFOtherBioSourceType> findMIFOtherBioSourceTypeEntities() {
        return findMIFOtherBioSourceTypeEntities(true, -1, -1);
    }

    public List<MIFOtherBioSourceType> findMIFOtherBioSourceTypeEntities(int maxResults, int firstResult) {
        return findMIFOtherBioSourceTypeEntities(false, maxResults, firstResult);
    }

    private List<MIFOtherBioSourceType> findMIFOtherBioSourceTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MIFOtherBioSourceType.class));
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

    public MIFOtherBioSourceType findMIFOtherBioSourceType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MIFOtherBioSourceType.class, id);
        } finally {
            em.close();
        }
    }

    public int getMIFOtherBioSourceTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MIFOtherBioSourceType> rt = cq.from(MIFOtherBioSourceType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
