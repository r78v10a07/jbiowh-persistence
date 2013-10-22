package org.jbiowhpersistence.datasets.ppi.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryExperiment;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteractor;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySource;
import org.jbiowhpersistence.datasets.ppi.entities.MIFInteractionParticipant;
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
import org.jbiowhpersistence.utils.controller.AbstractJpaController;

/**
 * This class is a abstract controller for the 
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @param <T> the class to be used
 * @since Sep 14, 2012
 */
public abstract class AbstractMIFJpaController<T> extends AbstractJpaController<T> {

    /**
     * Return the MIFOtherAlias list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherAlias list the be persisted
     * @return the MIFOtherAlias list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherAlias> createMIFOtherAlias(EntityManagerFactory emf, EntityManager em, Set<MIFOtherAlias> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            Set<MIFOtherAlias> attachObjSet = new HashSet<>();
            for (MIFOtherAlias object : objectList) {
                MIFOtherAlias objectOnDB = em.find(MIFOtherAlias.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    attachObjSet.add(object);
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFOtherAttribute list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherAttribute list the be persisted
     * @return the MIFOtherAttribute list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherAttribute> createMIFOtherAttribute(EntityManagerFactory emf, EntityManager em, Set<MIFOtherAttribute> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            Set<MIFOtherAttribute> attachObjSet = new HashSet<>();
            for (MIFOtherAttribute object : objectList) {
                MIFOtherAttribute objectOnDB = em.find(MIFOtherAttribute.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    attachObjSet.add(object);
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFOtherBioSourceType list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherBioSourceType list the be persisted
     * @return the MIFOtherBioSourceType list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherBioSourceType> createMIFOtherBioSourceType(EntityManagerFactory emf, EntityManager em, Set<MIFOtherBioSourceType> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            MIFOtherBioSourceTypeJpaController controller = new MIFOtherBioSourceTypeJpaController(emf);
            Set<MIFOtherBioSourceType> attachObjSet = new HashSet<>();
            for (MIFOtherBioSourceType object : objectList) {
                MIFOtherBioSourceType objectOnDB = em.find(MIFOtherBioSourceType.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    controller.create(object);
                    attachObjSet.add(em.getReference(object.getClass(), object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFInteractionParticipant list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFInteractionParticipant list the be persisted
     * @return the MIFInteractionParticipant list the be persisted
     * @throws Exception
     */
    protected Set<MIFInteractionParticipant> createMIFInteractionParticipant(EntityManagerFactory emf, EntityManager em, Set<MIFInteractionParticipant> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            MIFInteractionParticipantJpaController controller = new MIFInteractionParticipantJpaController(emf);
            Set<MIFInteractionParticipant> attachObjSet = new HashSet<>();
            for (MIFInteractionParticipant object : objectList) {
                MIFInteractionParticipant objectOnDB = em.find(MIFInteractionParticipant.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    controller.create(object);
                    attachObjSet.add(em.getReference(MIFInteractionParticipant.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFInteractionParticipant object the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param object the MIFInteractionParticipant list the be persisted
     * @return the MIFInteractionParticipant object the be persisted
     * @throws Exception
     */
    protected MIFInteractionParticipant createMIFInteractionParticipant(EntityManagerFactory emf, EntityManager em, MIFInteractionParticipant object) throws Exception {
        if (object != null) {
            printMsg(new Exception().getStackTrace()[0].getMethodName());
            MIFInteractionParticipantJpaController controller = new MIFInteractionParticipantJpaController(emf);

            MIFInteractionParticipant objectOnDB = em.find(MIFInteractionParticipant.class, object.getWid());
            if (objectOnDB != null) {
                return objectOnDB;
            } else {
                object.setRelationsToNull();
                controller.create(object);
                return em.getReference(MIFInteractionParticipant.class, object.getWid());
            }
        }
        return null;
    }

    /**
     * Return the MIFOtherConfidence list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherConfidence list the be persisted
     * @return the MIFOtherConfidence list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherConfidence> createMIFOtherConfidence(EntityManagerFactory emf, EntityManager em, Set<MIFOtherConfidence> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            Set<MIFOtherConfidence> attachObjSet = new HashSet<>();
            for (MIFOtherConfidence object : objectList) {
                MIFOtherConfidence objectOnDB = em.find(MIFOtherConfidence.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    attachObjSet.add(object);
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFOtherXRef list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherXRef list the be persisted
     * @return the MIFOtherXRef list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherXRef> createMIFOtherXRef(EntityManagerFactory emf, EntityManager em, Set<MIFOtherXRef> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            Set<MIFOtherXRef> attachObjSet = new HashSet<>();
            for (MIFOtherXRef object : objectList) {
                MIFOtherXRef objectOnDB = em.find(MIFOtherXRef.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    attachObjSet.add(object);
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFOtherXRefGO list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherXRefGO list the be persisted
     * @return the MIFOtherXRefGO list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherXRefGO> createMIFOtherXRefGO(EntityManagerFactory emf, EntityManager em, Set<MIFOtherXRefGO> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            Set<MIFOtherXRefGO> attachObjSet = new HashSet<>();
            for (MIFOtherXRefGO object : objectList) {
                MIFOtherXRefGO objectOnDB = em.find(MIFOtherXRefGO.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    attachObjSet.add(object);
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFOtherXRefPubMed list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherXRefPubMed list the be persisted
     * @return the MIFOtherXRefPubMed list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherXRefPubMed> createMIFOtherXRefPubMed(EntityManagerFactory emf, EntityManager em, Set<MIFOtherXRefPubMed> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            Set<MIFOtherXRefPubMed> attachObjSet = new HashSet<>();
            for (MIFOtherXRefPubMed object : objectList) {
                MIFOtherXRefPubMed objectOnDB = em.find(MIFOtherXRefPubMed.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    attachObjSet.add(object);
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFOtherXRefRefSeq list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherXRefRefSeq list the be persisted
     * @return the MIFOtherXRefRefSeq list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherXRefRefSeq> createMIFOtherXRefRefSeq(EntityManagerFactory emf, EntityManager em, Set<MIFOtherXRefRefSeq> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            Set<MIFOtherXRefRefSeq> attachObjSet = new HashSet<>();
            for (MIFOtherXRefRefSeq object : objectList) {
                MIFOtherXRefRefSeq objectOnDB = em.find(MIFOtherXRefRefSeq.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    attachObjSet.add(object);
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFOtherXRefUniprot list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherXRefUniprot list the be persisted
     * @return the MIFOtherXRefUniprot list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherXRefUniprot> createMIFOtherXRefUniprot(EntityManagerFactory emf, EntityManager em, Set<MIFOtherXRefUniprot> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            Set<MIFOtherXRefUniprot> attachObjSet = new HashSet<>();
            for (MIFOtherXRefUniprot object : objectList) {
                MIFOtherXRefUniprot objectOnDB = em.find(MIFOtherXRefUniprot.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    attachObjSet.add(object);
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFEntrySetEntry list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFEntrySetEntry list the be persisted
     * @return the MIFEntrySetEntry object to be persisted
     * @throws Exception
     */
    protected MIFEntrySetEntry createMIFEntrySetEntry(EntityManagerFactory emf, EntityManager em, MIFEntrySetEntry objectList) throws Exception {
        if (objectList != null) {
            printMsg(new Exception().getStackTrace()[0].getMethodName());
            MIFEntrySetEntry mIFEntrySetEntry = em.find(objectList.getClass(), objectList.getWid());
            if (mIFEntrySetEntry != null) {
                return mIFEntrySetEntry;
            } else {
                MIFEntrySetEntryJpaController controller = new MIFEntrySetEntryJpaController(emf);
                objectList.setMifEntryExperiment(null);
                objectList.setMifEntryInteraction(null);
                objectList.setMifEntryInteractor(null);
                objectList.setMifEntrySource(null);
                controller.create(objectList);
                return em.getReference(MIFEntrySetEntry.class, objectList.getWid());
            }
        }
        return null;
    }

    /**
     * Return the MIFEntrySetEntry list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFEntrySetEntry list the be persisted
     * @return the MIFEntrySetEntry list the be persisted
     * @throws Exception
     */
    protected Set<MIFEntrySetEntry> createMIFEntrySetEntry(EntityManagerFactory emf, EntityManager em, Set<MIFEntrySetEntry> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            MIFEntrySetEntryJpaController controller = new MIFEntrySetEntryJpaController(emf);
            Set<MIFEntrySetEntry> attachObjSet = new HashSet<>();
            for (MIFEntrySetEntry object : objectList) {
                MIFEntrySetEntry objectOnDB = em.find(MIFEntrySetEntry.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setMifEntryExperiment(null);
                    object.setMifEntryInteraction(null);
                    object.setMifEntryInteractor(null);
                    object.setMifEntrySource(null);
                    controller.create(object);
                    attachObjSet.add(em.getReference(MIFEntrySetEntry.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFEntryInteraction list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFEntryInteraction list the be persisted
     * @return the MIFEntryInteraction object to be persisted
     * @throws Exception
     */
    protected MIFEntryInteraction createMIFEntryInteraction(EntityManagerFactory emf, EntityManager em, MIFEntryInteraction objectList) throws Exception {
        if (objectList != null) {
            printMsg(new Exception().getStackTrace()[0].getMethodName());
            MIFEntryInteraction mifEntryInteraction = em.find(objectList.getClass(), objectList.getWid());
            if (mifEntryInteraction != null) {
                return mifEntryInteraction;
            } else {
                MIFEntryInteractionJpaController controller = new MIFEntryInteractionJpaController(emf);
                objectList.setMifEntryExperiment(null);
                objectList.setmIFEntrySetEntry(null);
                controller.create(objectList);
                return em.getReference(MIFEntryInteraction.class, objectList.getWid());
            }
        }
        return null;
    }

    /**
     * Return the MIFEntryExperiment list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFEntryExperiment list the be persisted
     * @return the MIFEntryExperiment list the be persisted
     * @throws Exception
     */
    protected Set<MIFEntryExperiment> createMIFEntryExperiment(EntityManagerFactory emf, EntityManager em, Set<MIFEntryExperiment> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            MIFEntryExperimentJpaController controller = new MIFEntryExperimentJpaController(emf);
            Set<MIFEntryExperiment> attachObjSet = new HashSet<>();
            for (MIFEntryExperiment object : objectList) {
                MIFEntryExperiment objectOnDB = em.find(MIFEntryExperiment.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    controller.create(object);
                    attachObjSet.add(em.getReference(MIFEntryExperiment.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFEntryExperiment object the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param object the MIFEntryExperiment list the be persisted
     * @return the MIFEntryExperiment object the be persisted
     * @throws Exception
     */
    protected MIFEntryExperiment createMIFEntryExperiment(EntityManagerFactory emf, EntityManager em, MIFEntryExperiment object) throws Exception {
        if (object != null) {
            printMsg(new Exception().getStackTrace()[0].getMethodName());
            MIFEntryExperimentJpaController controller = new MIFEntryExperimentJpaController(emf);

            MIFEntryExperiment objectOnDB = em.find(MIFEntryExperiment.class, object.getWid());
            if (objectOnDB != null) {
                return objectOnDB;
            } else {
                object.setRelationsToNull();
                controller.create(object);
                return em.getReference(MIFEntryExperiment.class, object.getWid());
            }
        }
        return null;
    }

    /**
     * Return the MIFEntrySource list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFEntrySource list the be persisted
     * @return the MIFEntrySource list the be persisted
     * @throws Exception
     */
    protected Set<MIFEntrySource> createMIFEntrySource(EntityManagerFactory emf, EntityManager em, Set<MIFEntrySource> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            MIFEntrySourceJpaController controller = new MIFEntrySourceJpaController(emf);
            Set<MIFEntrySource> attachObjSet = new HashSet<>();
            for (MIFEntrySource object : objectList) {
                MIFEntrySource objectOnDB = em.find(MIFEntrySource.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setmIFEntrySetEntry(null);
                    controller.create(object);
                    attachObjSet.add(em.getReference(MIFEntrySource.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFEntryInteractor list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFEntryInteractor list the be persisted
     * @return the MIFEntryInteractor list the be persisted
     * @throws Exception
     */
    protected Set<MIFEntryInteractor> createMIFEntryInteractor(EntityManagerFactory emf, EntityManager em, Set<MIFEntryInteractor> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            MIFEntryInteractorJpaController controller = new MIFEntryInteractorJpaController(emf);
            Set<MIFEntryInteractor> attachObjSet = new HashSet<>();
            for (MIFEntryInteractor object : objectList) {
                MIFEntryInteractor objectOnDB = em.find(MIFEntryInteractor.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setMifEntrySetEntry(null);
                    controller.create(object);
                    attachObjSet.add(em.getReference(MIFEntryInteractor.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFEntryInteractor object the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param object the MIFEntryInteractor list the be persisted
     * @return the MIFEntryInteractor object the be persisted
     * @throws Exception
     */
    protected MIFEntryInteractor createMIFEntryInteractor(EntityManagerFactory emf, EntityManager em, MIFEntryInteractor object) throws Exception {
        if (object != null) {
            printMsg(new Exception().getStackTrace()[0].getMethodName());
            MIFEntryInteractorJpaController controller = new MIFEntryInteractorJpaController(emf);
            MIFEntryInteractor objectOnDB = em.find(MIFEntryInteractor.class, object.getWid());
            if (objectOnDB != null) {
                return objectOnDB;
            } else {
                object.setMifEntrySetEntry(null);
                controller.create(object);
                return em.getReference(MIFEntryInteractor.class, object.getWid());
            }
        }
        return null;
    }

    /**
     * Return the MIFOtherBibRef list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFOtherBibRef list the be persisted
     * @return the MIFOtherBibRef list the be persisted
     * @throws Exception
     */
    protected Set<MIFOtherBibRef> createMIFOtherBibRef(EntityManagerFactory emf, EntityManager em, Set<MIFOtherBibRef> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            printMsg(new Exception().getStackTrace()[0].getMethodName(), objectList);
            Set<MIFOtherBibRef> attachObjSet = new HashSet<>();
            for (MIFOtherBibRef object : objectList) {
                MIFOtherBibRef objectOnDB = em.find(MIFOtherBibRef.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    attachObjSet.add(object);
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    private void printMsg(String methodName) {
        //VerbLogger.getInstance().println(this.getClass().getSimpleName(), "\tCreating " + methodName);
    }

    private void printMsg(String methodName, Collection toInsert) {
        //VerbLogger.getInstance().println(this.getClass().getSimpleName(), "\tCreating " + methodName + " to insert " + toInsert.size() + " elements");
    }
}
