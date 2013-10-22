package org.jbiowhpersistence.datasets.domain.pfam.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamARegFullInsignificant;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamARegFullSignificant;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamArchitecture;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClans;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamCompleteProteomes;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamLiteratureReferences;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamProteomeRegions;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamProteomeRegionsPK;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;

/**
 * This class is the AbstractPFam Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $
 * $LastChangedRevision: 377 $
 * @since Nov 21, 2012
 */
public abstract class AbstractPFamJpaController<T> extends AbstractJpaController<T> {

    /**
     * Return the PfamAbioWH the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param object the PfamAbioWH the be persisted
     * @return the Taxonomy the be persisted
     * @throws Exception
     */
    protected PfamAbioWH createPfamA(EntityManagerFactory emf, EntityManager em, PfamAbioWH object) throws Exception {
        if (object == null) {
            return null;
        }
        PfamAbioWH tax = em.find(PfamAbioWH.class, object.getWid());
        if (tax != null) {
            return tax;
        } else {
            PfamAbioWHJpaController pfamAController = new PfamAbioWHJpaController(emf);
            object.setRelationsToNull();
            pfamAController.create(object);
            return em.getReference(PfamAbioWH.class, object.getWid());
        }
    }

    /**
     * Return the PfamArchitectures list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the PfamArchitectures list the be persisted
     * @return the Ontology list the be persisted
     * @throws Exception
     */
    protected Set<PfamArchitecture> createPfamArchitecture(EntityManagerFactory emf, EntityManager em, Set<PfamArchitecture> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            PfamArchitectureJpaController instance = new PfamArchitectureJpaController(emf);
            Set<PfamArchitecture> attachObjSet = new HashSet<>();
            for (PfamArchitecture object : objectList) {
                PfamArchitecture objectOnDB = em.find(PfamArchitecture.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(PfamArchitecture.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the PfamClans list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the PfamClans list the be persisted
     * @return the Ontology list the be persisted
     * @throws Exception
     */
    protected Set<PfamClans> createPfamClans(EntityManagerFactory emf, EntityManager em, Set<PfamClans> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            PfamClansJpaController instance = new PfamClansJpaController(emf);
            Set<PfamClans> attachObjSet = new HashSet<>();
            for (PfamClans object : objectList) {
                PfamClans objectOnDB = em.find(PfamClans.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(PfamClans.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the PfamCompleteProteomes list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the PfamCompleteProteomes list the be persisted
     * @return the Ontology list the be persisted
     * @throws Exception
     */
    protected PfamCompleteProteomes createPfamCompleteProteomes(EntityManagerFactory emf, EntityManager em, PfamCompleteProteomes object) throws Exception {
        if (object == null) {
            return null;
        }
        PfamCompleteProteomes tax = em.find(PfamCompleteProteomes.class, object.getWid());
        if (tax != null) {
            return tax;
        } else {
            PfamCompleteProteomesJpaController pfamAController = new PfamCompleteProteomesJpaController(emf);
            object.setRelationsToNull();
            pfamAController.create(object);
            return em.getReference(PfamCompleteProteomes.class, object.getWid());
        }
    }

    /**
     * Return the PfamLiteratureReferences list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the PfamLiteratureReferences list the be persisted
     * @return the Ontology list the be persisted
     * @throws Exception
     */
    protected Set<PfamLiteratureReferences> createPfamLiteratureReferences(EntityManagerFactory emf, EntityManager em, Set<PfamLiteratureReferences> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            PfamLiteratureReferencesJpaController instance = new PfamLiteratureReferencesJpaController(emf);
            Set<PfamLiteratureReferences> attachObjSet = new HashSet<>();
            for (PfamLiteratureReferences object : objectList) {
                PfamLiteratureReferences objectOnDB = em.find(PfamLiteratureReferences.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(PfamLiteratureReferences.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the PfamProteomeRegions list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the PfamProteomeRegions list the be persisted
     * @return the Ontology list the be persisted
     * @throws Exception
     */
    protected Map<PfamProteomeRegionsPK, PfamProteomeRegions> createPfamProteomeRegions(EntityManagerFactory emf, EntityManager em, Map<PfamProteomeRegionsPK, PfamProteomeRegions> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            PfamProteomeRegionsJpaController instance = new PfamProteomeRegionsJpaController(emf);
            Map<PfamProteomeRegionsPK, PfamProteomeRegions> attachObjSet = new HashMap<>();
            for (PfamProteomeRegions object : objectList.values()) {
                PfamProteomeRegions objectOnDB = em.find(PfamProteomeRegions.class, object.getPfamProteomeRegionsPK());
                if (objectOnDB != null) {
                    attachObjSet.put(objectOnDB.getPfamProteomeRegionsPK(), objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    PfamProteomeRegions onDB = em.getReference(PfamProteomeRegions.class, object.getPfamProteomeRegionsPK());
                    attachObjSet.put(onDB.getPfamProteomeRegionsPK(), onDB);
                }
            }
            return attachObjSet;
        }
        return new HashMap<>();
    }

    /**
     * Return the PfamARegFullSignificant list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the PfamARegFullSignificant list the be persisted
     * @return the Ontology list the be persisted
     * @throws Exception
     */
    protected Set<PfamARegFullSignificant> createPfamARegFullSignificant(EntityManagerFactory emf, EntityManager em, Set<PfamARegFullSignificant> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            PfamARegFullSignificantJpaController instance = new PfamARegFullSignificantJpaController(emf);
            Set<PfamARegFullSignificant> attachObjSet = new HashSet<>();
            for (PfamARegFullSignificant object : objectList) {
                PfamARegFullSignificant objectOnDB = em.find(PfamARegFullSignificant.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(PfamARegFullSignificant.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the PfamARegFullInsignificant list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the PfamARegFullInsignificant list the be persisted
     * @return the Ontology list the be persisted
     * @throws Exception
     */
    protected Set<PfamARegFullInsignificant> createPfamARegFullInsignificant(EntityManagerFactory emf, EntityManager em, Set<PfamARegFullInsignificant> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            PfamARegFullInsignificantJpaController instance = new PfamARegFullInsignificantJpaController(emf);
            Set<PfamARegFullInsignificant> attachObjSet = new HashSet<>();
            for (PfamARegFullInsignificant object : objectList) {
                PfamARegFullInsignificant objectOnDB = em.find(PfamARegFullInsignificant.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(PfamARegFullInsignificant.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }
}
