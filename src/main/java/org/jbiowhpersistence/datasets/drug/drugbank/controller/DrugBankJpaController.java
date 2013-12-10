package org.jbiowhpersistence.datasets.drug.drugbank.controller;

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
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankCarrier;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankCategory;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankEnzyme;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankPatent;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankTarget;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankTransporter;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the DrugBank Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100
 * (Tue, 19 Mar 2013) $ $LastChangedRevision: 396 $
 *
 * @since Sep 6, 2012
 */
public class DrugBankJpaController extends AbstractJpaController<DrugBank> implements Serializable {

    public DrugBankJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DrugBank drugBank) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + drugBank.getWid());
        if (drugBank.getDrugBankCategory() == null) {
            drugBank.setDrugBankCategory(new HashSet<DrugBankCategory>());
        }
        if (drugBank.getDrugBankPatent() == null) {
            drugBank.setDrugBankPatent(new HashSet<DrugBankPatent>());
        }
        if (drugBank.getDrugBankTarget() == null) {
            drugBank.setDrugBankTarget(new HashSet<DrugBankTarget>());
        }
        if (drugBank.getDrugBankEnzyme() == null) {
            drugBank.setDrugBankEnzyme(new HashSet<DrugBankEnzyme>());
        }
        if (drugBank.getDrugBankTransporter() == null) {
            drugBank.setDrugBankTransporter(new HashSet<DrugBankTransporter>());
        }
        if (drugBank.getDrugBankCarrier() == null) {
            drugBank.setDrugBankCarrier(new HashSet<DrugBankCarrier>());
        }
        if (drugBank.getProtein() == null) {
            drugBank.setProtein(new HashSet<Protein>());
        }
        if (drugBank.getProteinAsEnzyme() == null) {
            drugBank.setProteinAsEnzyme(new HashSet<Protein>());
        }
        if (drugBank.getProteinAsTransporters() == null) {
            drugBank.setProteinAsTransporters(new HashSet<Protein>());
        }
        if (drugBank.getProteinAsCarriers() == null) {
            drugBank.setProteinAsCarriers(new HashSet<Protein>());
        }
        if (drugBank.getkEGGCompounds() == null) {
            drugBank.setkEGGCompounds(new HashSet<KEGGCompound>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!drugBank.getDrugBankCarrier().isEmpty()) {
                Set<DrugBankCarrier> attachedDrugBankCarriers = new HashSet();
                for (DrugBankCarrier drugBankCarriersDrugBankCarriersToAttach : drugBank.getDrugBankCarrier()) {
                    DrugBankCarrier drugBankCarriers = em.find(drugBankCarriersDrugBankCarriersToAttach.getClass(), drugBankCarriersDrugBankCarriersToAttach.getWid());
                    if (drugBankCarriers != null) {
                        attachedDrugBankCarriers.add(drugBankCarriers);
                    } else {
                        attachedDrugBankCarriers.add(drugBankCarriersDrugBankCarriersToAttach);
                    }
                }
                drugBank.setDrugBankCarrier(attachedDrugBankCarriers);
            }
            if (!drugBank.getDrugBankTransporter().isEmpty()) {
                Set<DrugBankTransporter> attachedDrugBankTransporters = new HashSet();
                for (DrugBankTransporter drugBankTransportersDrugBankTransportersToAttach : drugBank.getDrugBankTransporter()) {
                    DrugBankTransporter drugBankTransporters = em.find(drugBankTransportersDrugBankTransportersToAttach.getClass(), drugBankTransportersDrugBankTransportersToAttach.getWid());
                    if (drugBankTransporters != null) {
                        attachedDrugBankTransporters.add(drugBankTransporters);
                    } else {
                        attachedDrugBankTransporters.add(drugBankTransportersDrugBankTransportersToAttach);
                    }
                }
                drugBank.setDrugBankTransporter(attachedDrugBankTransporters);
            }
            if (!drugBank.getDrugBankEnzyme().isEmpty()) {
                Set<DrugBankEnzyme> attachedDrugBankEnzymes = new HashSet();
                for (DrugBankEnzyme drugBankEnzymesDrugBankEnzymesToAttach : drugBank.getDrugBankEnzyme()) {
                    DrugBankEnzyme drugBankEnzymes = em.find(drugBankEnzymesDrugBankEnzymesToAttach.getClass(), drugBankEnzymesDrugBankEnzymesToAttach.getWid());
                    if (drugBankEnzymes != null) {
                        attachedDrugBankEnzymes.add(drugBankEnzymes);
                    } else {
                        attachedDrugBankEnzymes.add(drugBankEnzymesDrugBankEnzymesToAttach);
                    }
                }
                drugBank.setDrugBankEnzyme(attachedDrugBankEnzymes);
            }
            if (!drugBank.getDrugBankTarget().isEmpty()) {
                Set<DrugBankTarget> attachedDrugBankTargets = new HashSet();
                for (DrugBankTarget drugBankTargetsDrugBankTargetsToAttach : drugBank.getDrugBankTarget()) {
                    DrugBankTarget drugBankTargets = em.find(drugBankTargetsDrugBankTargetsToAttach.getClass(), drugBankTargetsDrugBankTargetsToAttach.getWid());
                    if (drugBankTargets != null) {
                        attachedDrugBankTargets.add(drugBankTargets);
                    } else {
                        attachedDrugBankTargets.add(drugBankTargetsDrugBankTargetsToAttach);
                    }
                }
                drugBank.setDrugBankTarget(attachedDrugBankTargets);
            }
            if (!drugBank.getDrugBankPatent().isEmpty()) {
                Set<DrugBankPatent> attachedDrugBankPatents = new HashSet();
                for (DrugBankPatent drugBankPatentsDrugBankPatentsToAttach : drugBank.getDrugBankPatent()) {
                    DrugBankPatent drugBankPatents = em.find(drugBankPatentsDrugBankPatentsToAttach.getClass(), drugBankPatentsDrugBankPatentsToAttach.getWid());
                    if (drugBankPatents != null) {
                        attachedDrugBankPatents.add(drugBankPatents);
                    } else {
                        drugBankPatentsDrugBankPatentsToAttach.setDrugBank(null);
                        attachedDrugBankPatents.add(drugBankPatentsDrugBankPatentsToAttach);
                    }
                }
                drugBank.setDrugBankPatent(attachedDrugBankPatents);
            }
            if (!drugBank.getDrugBankCategory().isEmpty()) {
                Set<DrugBankCategory> attachedDrugBankCategories = new HashSet();
                for (DrugBankCategory drugBankCategoriesDrugBankCategoriesToAttach : drugBank.getDrugBankCategory()) {
                    DrugBankCategory drugBankCategories = em.find(drugBankCategoriesDrugBankCategoriesToAttach.getClass(), drugBankCategoriesDrugBankCategoriesToAttach.getWid());
                    if (drugBankCategories != null) {
                        attachedDrugBankCategories.add(drugBankCategories);
                    } else {
                        drugBankCategoriesDrugBankCategoriesToAttach.setDrugBank(null);
                        attachedDrugBankCategories.add(drugBankCategoriesDrugBankCategoriesToAttach);
                    }
                }
                drugBank.setDrugBankCategory(attachedDrugBankCategories);
            }
            drugBank.setProteinAsCarriers(createProtein(emf, em, drugBank.getProteinAsCarriers()));
            drugBank.setProteinAsEnzyme(createProtein(emf, em, drugBank.getProteinAsEnzyme()));
            drugBank.setProteinAsTransporters(createProtein(emf, em, drugBank.getProteinAsTransporters()));
            drugBank.setProtein(createProtein(emf, em, drugBank.getProtein()));
            drugBank.setkEGGCompounds(createKEGGCompound(emf, em, drugBank.getkEGGCompounds()));
            drugBank.setDataSet(createDataSet(emf, em, drugBank.getDataSet()));
            em.persist(drugBank);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDrugBank(drugBank.getWid()) != null) {
                throw new PreexistingEntityException("DrugBank " + drugBank + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DrugBank drugBank) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editting " + this.getClass().getSimpleName() + ": " + drugBank.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DrugBank persistentDrugBank = em.find(DrugBank.class, drugBank.getWid());
            Set<DrugBankCategory> drugBankCategoryOld = persistentDrugBank.getDrugBankCategory();
            Set<DrugBankCategory> drugBankCategoryNew = drugBank.getDrugBankCategory();
            Set<DrugBankPatent> drugBankPatentOld = persistentDrugBank.getDrugBankPatent();
            Set<DrugBankPatent> drugBankPatentNew = drugBank.getDrugBankPatent();
            Set<DrugBankTarget> drugBankTargetOld = persistentDrugBank.getDrugBankTarget();
            Set<DrugBankTarget> drugBankTargetNew = drugBank.getDrugBankTarget();
            Set<DrugBankEnzyme> drugBankEnzymeOld = persistentDrugBank.getDrugBankEnzyme();
            Set<DrugBankEnzyme> drugBankEnzymeNew = drugBank.getDrugBankEnzyme();
            Set<DrugBankTransporter> drugBankTransporterOld = persistentDrugBank.getDrugBankTransporter();
            Set<DrugBankTransporter> drugBankTransporterNew = drugBank.getDrugBankTransporter();
            Set<DrugBankCarrier> drugBankCarrierOld = persistentDrugBank.getDrugBankCarrier();
            Set<DrugBankCarrier> drugBankCarrierNew = drugBank.getDrugBankCarrier();
            Set<Protein> proteinOld = persistentDrugBank.getProtein();
            Set<Protein> proteinNew = drugBank.getProtein();
            Set<Protein> proteinAsEnzymeOld = persistentDrugBank.getProteinAsEnzyme();
            Set<Protein> proteinAsEnzymeNew = drugBank.getProteinAsEnzyme();
            Set<Protein> proteinAsTransportersOld = persistentDrugBank.getProteinAsTransporters();
            Set<Protein> proteinAsTransportersNew = drugBank.getProteinAsTransporters();
            Set<Protein> proteinAsCarriersOld = persistentDrugBank.getProteinAsCarriers();
            Set<Protein> proteinAsCarriersNew = drugBank.getProteinAsCarriers();
            Set<KEGGCompound> kEGGCompoundsOld = persistentDrugBank.getkEGGCompounds();
            Set<KEGGCompound> kEGGCompoundsNew = drugBank.getkEGGCompounds();
            Set<DrugBankCategory> attachedDrugBankCategoryNew = new HashSet<DrugBankCategory>();
            for (DrugBankCategory drugBankCategoryNewDrugBankCategoryToAttach : drugBankCategoryNew) {
                drugBankCategoryNewDrugBankCategoryToAttach = em.getReference(drugBankCategoryNewDrugBankCategoryToAttach.getClass(), drugBankCategoryNewDrugBankCategoryToAttach.getWid());
                attachedDrugBankCategoryNew.add(drugBankCategoryNewDrugBankCategoryToAttach);
            }
            drugBankCategoryNew = attachedDrugBankCategoryNew;
            drugBank.setDrugBankCategory(drugBankCategoryNew);
            Set<DrugBankPatent> attachedDrugBankPatentNew = new HashSet<DrugBankPatent>();
            for (DrugBankPatent drugBankPatentNewDrugBankPatentToAttach : drugBankPatentNew) {
                drugBankPatentNewDrugBankPatentToAttach = em.getReference(drugBankPatentNewDrugBankPatentToAttach.getClass(), drugBankPatentNewDrugBankPatentToAttach.getWid());
                attachedDrugBankPatentNew.add(drugBankPatentNewDrugBankPatentToAttach);
            }
            drugBankPatentNew = attachedDrugBankPatentNew;
            drugBank.setDrugBankPatent(drugBankPatentNew);
            Set<DrugBankTarget> attachedDrugBankTargetNew = new HashSet<DrugBankTarget>();
            for (DrugBankTarget drugBankTargetNewDrugBankTargetToAttach : drugBankTargetNew) {
                drugBankTargetNewDrugBankTargetToAttach = em.getReference(drugBankTargetNewDrugBankTargetToAttach.getClass(), drugBankTargetNewDrugBankTargetToAttach.getWid());
                attachedDrugBankTargetNew.add(drugBankTargetNewDrugBankTargetToAttach);
            }
            drugBankTargetNew = attachedDrugBankTargetNew;
            drugBank.setDrugBankTarget(drugBankTargetNew);
            Set<DrugBankEnzyme> attachedDrugBankEnzymeNew = new HashSet<DrugBankEnzyme>();
            for (DrugBankEnzyme drugBankEnzymeNewDrugBankEnzymeToAttach : drugBankEnzymeNew) {
                drugBankEnzymeNewDrugBankEnzymeToAttach = em.getReference(drugBankEnzymeNewDrugBankEnzymeToAttach.getClass(), drugBankEnzymeNewDrugBankEnzymeToAttach.getWid());
                attachedDrugBankEnzymeNew.add(drugBankEnzymeNewDrugBankEnzymeToAttach);
            }
            drugBankEnzymeNew = attachedDrugBankEnzymeNew;
            drugBank.setDrugBankEnzyme(drugBankEnzymeNew);
            Set<DrugBankTransporter> attachedDrugBankTransporterNew = new HashSet<DrugBankTransporter>();
            for (DrugBankTransporter drugBankTransporterNewDrugBankTransporterToAttach : drugBankTransporterNew) {
                drugBankTransporterNewDrugBankTransporterToAttach = em.getReference(drugBankTransporterNewDrugBankTransporterToAttach.getClass(), drugBankTransporterNewDrugBankTransporterToAttach.getWid());
                attachedDrugBankTransporterNew.add(drugBankTransporterNewDrugBankTransporterToAttach);
            }
            drugBankTransporterNew = attachedDrugBankTransporterNew;
            drugBank.setDrugBankTransporter(drugBankTransporterNew);
            Set<DrugBankCarrier> attachedDrugBankCarrierNew = new HashSet<DrugBankCarrier>();
            for (DrugBankCarrier drugBankCarrierNewDrugBankCarrierToAttach : drugBankCarrierNew) {
                drugBankCarrierNewDrugBankCarrierToAttach = em.getReference(drugBankCarrierNewDrugBankCarrierToAttach.getClass(), drugBankCarrierNewDrugBankCarrierToAttach.getWid());
                attachedDrugBankCarrierNew.add(drugBankCarrierNewDrugBankCarrierToAttach);
            }
            drugBankCarrierNew = attachedDrugBankCarrierNew;
            drugBank.setDrugBankCarrier(drugBankCarrierNew);
            Set<Protein> attachedProteinNew = new HashSet<Protein>();
            for (Protein proteinNewProteinToAttach : proteinNew) {
                proteinNewProteinToAttach = em.getReference(proteinNewProteinToAttach.getClass(), proteinNewProteinToAttach.getWid());
                attachedProteinNew.add(proteinNewProteinToAttach);
            }
            proteinNew = attachedProteinNew;
            drugBank.setProtein(proteinNew);
            Set<Protein> attachedProteinAsEnzymeNew = new HashSet<Protein>();
            for (Protein proteinAsEnzymeNewProteinToAttach : proteinAsEnzymeNew) {
                proteinAsEnzymeNewProteinToAttach = em.getReference(proteinAsEnzymeNewProteinToAttach.getClass(), proteinAsEnzymeNewProteinToAttach.getWid());
                attachedProteinAsEnzymeNew.add(proteinAsEnzymeNewProteinToAttach);
            }
            proteinAsEnzymeNew = attachedProteinAsEnzymeNew;
            drugBank.setProteinAsEnzyme(proteinAsEnzymeNew);
            Set<Protein> attachedProteinAsTransportersNew = new HashSet<Protein>();
            for (Protein proteinAsTransportersNewProteinToAttach : proteinAsTransportersNew) {
                proteinAsTransportersNewProteinToAttach = em.getReference(proteinAsTransportersNewProteinToAttach.getClass(), proteinAsTransportersNewProteinToAttach.getWid());
                attachedProteinAsTransportersNew.add(proteinAsTransportersNewProteinToAttach);
            }
            proteinAsTransportersNew = attachedProteinAsTransportersNew;
            drugBank.setProteinAsTransporters(proteinAsTransportersNew);
            Set<Protein> attachedProteinAsCarriersNew = new HashSet<Protein>();
            for (Protein proteinAsCarriersNewProteinToAttach : proteinAsCarriersNew) {
                proteinAsCarriersNewProteinToAttach = em.getReference(proteinAsCarriersNewProteinToAttach.getClass(), proteinAsCarriersNewProteinToAttach.getWid());
                attachedProteinAsCarriersNew.add(proteinAsCarriersNewProteinToAttach);
            }
            proteinAsCarriersNew = attachedProteinAsCarriersNew;
            drugBank.setProteinAsCarriers(proteinAsCarriersNew);
            Set<KEGGCompound> attachedkEGGCompoundsNew = new HashSet<KEGGCompound>();
            for (KEGGCompound kEGGCompoundsNewKEGGCompoundToAttach : kEGGCompoundsNew) {
                kEGGCompoundsNewKEGGCompoundToAttach = em.getReference(kEGGCompoundsNewKEGGCompoundToAttach.getClass(), kEGGCompoundsNewKEGGCompoundToAttach.getWid());
                attachedkEGGCompoundsNew.add(kEGGCompoundsNewKEGGCompoundToAttach);
            }
            kEGGCompoundsNew = attachedkEGGCompoundsNew;
            drugBank.setkEGGCompounds(kEGGCompoundsNew);
            drugBank = em.merge(drugBank);
            for (DrugBankCategory drugBankCategoryOldDrugBankCategory : drugBankCategoryOld) {
                if (!drugBankCategoryNew.contains(drugBankCategoryOldDrugBankCategory)) {
                    drugBankCategoryOldDrugBankCategory.getDrugBank().remove(drugBank);
                    drugBankCategoryOldDrugBankCategory = em.merge(drugBankCategoryOldDrugBankCategory);
                }
            }
            for (DrugBankCategory drugBankCategoryNewDrugBankCategory : drugBankCategoryNew) {
                if (!drugBankCategoryOld.contains(drugBankCategoryNewDrugBankCategory)) {
                    drugBankCategoryNewDrugBankCategory.getDrugBank().add(drugBank);
                    drugBankCategoryNewDrugBankCategory = em.merge(drugBankCategoryNewDrugBankCategory);
                }
            }
            for (DrugBankPatent drugBankPatentOldDrugBankPatent : drugBankPatentOld) {
                if (!drugBankPatentNew.contains(drugBankPatentOldDrugBankPatent)) {
                    drugBankPatentOldDrugBankPatent.getDrugBank().remove(drugBank);
                    drugBankPatentOldDrugBankPatent = em.merge(drugBankPatentOldDrugBankPatent);
                }
            }
            for (DrugBankPatent drugBankPatentNewDrugBankPatent : drugBankPatentNew) {
                if (!drugBankPatentOld.contains(drugBankPatentNewDrugBankPatent)) {
                    drugBankPatentNewDrugBankPatent.getDrugBank().add(drugBank);
                    drugBankPatentNewDrugBankPatent = em.merge(drugBankPatentNewDrugBankPatent);
                }
            }
            for (DrugBankTarget drugBankTargetOldDrugBankTarget : drugBankTargetOld) {
                if (!drugBankTargetNew.contains(drugBankTargetOldDrugBankTarget)) {
                    drugBankTargetOldDrugBankTarget.setDrugBank(null);
                    drugBankTargetOldDrugBankTarget = em.merge(drugBankTargetOldDrugBankTarget);
                }
            }
            for (DrugBankTarget drugBankTargetNewDrugBankTarget : drugBankTargetNew) {
                if (!drugBankTargetOld.contains(drugBankTargetNewDrugBankTarget)) {
                    DrugBank oldDrugBankOfDrugBankTargetNewDrugBankTarget = drugBankTargetNewDrugBankTarget.getDrugBank();
                    drugBankTargetNewDrugBankTarget.setDrugBank(drugBank);
                    drugBankTargetNewDrugBankTarget = em.merge(drugBankTargetNewDrugBankTarget);
                    if (oldDrugBankOfDrugBankTargetNewDrugBankTarget != null && !oldDrugBankOfDrugBankTargetNewDrugBankTarget.equals(drugBank)) {
                        oldDrugBankOfDrugBankTargetNewDrugBankTarget.getDrugBankTarget().remove(drugBankTargetNewDrugBankTarget);
                        oldDrugBankOfDrugBankTargetNewDrugBankTarget = em.merge(oldDrugBankOfDrugBankTargetNewDrugBankTarget);
                    }
                }
            }
            for (DrugBankEnzyme drugBankEnzymeOldDrugBankEnzyme : drugBankEnzymeOld) {
                if (!drugBankEnzymeNew.contains(drugBankEnzymeOldDrugBankEnzyme)) {
                    drugBankEnzymeOldDrugBankEnzyme.setDrugBank(null);
                    drugBankEnzymeOldDrugBankEnzyme = em.merge(drugBankEnzymeOldDrugBankEnzyme);
                }
            }
            for (DrugBankEnzyme drugBankEnzymeNewDrugBankEnzyme : drugBankEnzymeNew) {
                if (!drugBankEnzymeOld.contains(drugBankEnzymeNewDrugBankEnzyme)) {
                    DrugBank oldDrugBankOfDrugBankEnzymeNewDrugBankEnzyme = drugBankEnzymeNewDrugBankEnzyme.getDrugBank();
                    drugBankEnzymeNewDrugBankEnzyme.setDrugBank(drugBank);
                    drugBankEnzymeNewDrugBankEnzyme = em.merge(drugBankEnzymeNewDrugBankEnzyme);
                    if (oldDrugBankOfDrugBankEnzymeNewDrugBankEnzyme != null && !oldDrugBankOfDrugBankEnzymeNewDrugBankEnzyme.equals(drugBank)) {
                        oldDrugBankOfDrugBankEnzymeNewDrugBankEnzyme.getDrugBankEnzyme().remove(drugBankEnzymeNewDrugBankEnzyme);
                        oldDrugBankOfDrugBankEnzymeNewDrugBankEnzyme = em.merge(oldDrugBankOfDrugBankEnzymeNewDrugBankEnzyme);
                    }
                }
            }
            for (DrugBankTransporter drugBankTransporterOldDrugBankTransporter : drugBankTransporterOld) {
                if (!drugBankTransporterNew.contains(drugBankTransporterOldDrugBankTransporter)) {
                    drugBankTransporterOldDrugBankTransporter.setDrugBank(null);
                    drugBankTransporterOldDrugBankTransporter = em.merge(drugBankTransporterOldDrugBankTransporter);
                }
            }
            for (DrugBankTransporter drugBankTransporterNewDrugBankTransporter : drugBankTransporterNew) {
                if (!drugBankTransporterOld.contains(drugBankTransporterNewDrugBankTransporter)) {
                    DrugBank oldDrugBankOfDrugBankTransporterNewDrugBankTransporter = drugBankTransporterNewDrugBankTransporter.getDrugBank();
                    drugBankTransporterNewDrugBankTransporter.setDrugBank(drugBank);
                    drugBankTransporterNewDrugBankTransporter = em.merge(drugBankTransporterNewDrugBankTransporter);
                    if (oldDrugBankOfDrugBankTransporterNewDrugBankTransporter != null && !oldDrugBankOfDrugBankTransporterNewDrugBankTransporter.equals(drugBank)) {
                        oldDrugBankOfDrugBankTransporterNewDrugBankTransporter.getDrugBankTransporter().remove(drugBankTransporterNewDrugBankTransporter);
                        oldDrugBankOfDrugBankTransporterNewDrugBankTransporter = em.merge(oldDrugBankOfDrugBankTransporterNewDrugBankTransporter);
                    }
                }
            }
            for (DrugBankCarrier drugBankCarrierOldDrugBankCarrier : drugBankCarrierOld) {
                if (!drugBankCarrierNew.contains(drugBankCarrierOldDrugBankCarrier)) {
                    drugBankCarrierOldDrugBankCarrier.setDrugBank(null);
                    drugBankCarrierOldDrugBankCarrier = em.merge(drugBankCarrierOldDrugBankCarrier);
                }
            }
            for (DrugBankCarrier drugBankCarrierNewDrugBankCarrier : drugBankCarrierNew) {
                if (!drugBankCarrierOld.contains(drugBankCarrierNewDrugBankCarrier)) {
                    DrugBank oldDrugBankOfDrugBankCarrierNewDrugBankCarrier = drugBankCarrierNewDrugBankCarrier.getDrugBank();
                    drugBankCarrierNewDrugBankCarrier.setDrugBank(drugBank);
                    drugBankCarrierNewDrugBankCarrier = em.merge(drugBankCarrierNewDrugBankCarrier);
                    if (oldDrugBankOfDrugBankCarrierNewDrugBankCarrier != null && !oldDrugBankOfDrugBankCarrierNewDrugBankCarrier.equals(drugBank)) {
                        oldDrugBankOfDrugBankCarrierNewDrugBankCarrier.getDrugBankCarrier().remove(drugBankCarrierNewDrugBankCarrier);
                        oldDrugBankOfDrugBankCarrierNewDrugBankCarrier = em.merge(oldDrugBankOfDrugBankCarrierNewDrugBankCarrier);
                    }
                }
            }
            for (Protein proteinOldProtein : proteinOld) {
                if (!proteinNew.contains(proteinOldProtein)) {
                    proteinOldProtein.getDrugBank().remove(drugBank);
                    proteinOldProtein = em.merge(proteinOldProtein);
                }
            }
            for (Protein proteinNewProtein : proteinNew) {
                if (!proteinOld.contains(proteinNewProtein)) {
                    proteinNewProtein.getDrugBank().add(drugBank);
                    proteinNewProtein = em.merge(proteinNewProtein);
                }
            }
            for (Protein proteinAsEnzymeOldProtein : proteinAsEnzymeOld) {
                if (!proteinAsEnzymeNew.contains(proteinAsEnzymeOldProtein)) {
                    proteinAsEnzymeOldProtein.getDrugBank().remove(drugBank);
                    proteinAsEnzymeOldProtein = em.merge(proteinAsEnzymeOldProtein);
                }
            }
            for (Protein proteinAsEnzymeNewProtein : proteinAsEnzymeNew) {
                if (!proteinAsEnzymeOld.contains(proteinAsEnzymeNewProtein)) {
                    proteinAsEnzymeNewProtein.getDrugBank().add(drugBank);
                    proteinAsEnzymeNewProtein = em.merge(proteinAsEnzymeNewProtein);
                }
            }
            for (Protein proteinAsTransportersOldProtein : proteinAsTransportersOld) {
                if (!proteinAsTransportersNew.contains(proteinAsTransportersOldProtein)) {
                    proteinAsTransportersOldProtein.getDrugBank().remove(drugBank);
                    proteinAsTransportersOldProtein = em.merge(proteinAsTransportersOldProtein);
                }
            }
            for (Protein proteinAsTransportersNewProtein : proteinAsTransportersNew) {
                if (!proteinAsTransportersOld.contains(proteinAsTransportersNewProtein)) {
                    proteinAsTransportersNewProtein.getDrugBank().add(drugBank);
                    proteinAsTransportersNewProtein = em.merge(proteinAsTransportersNewProtein);
                }
            }
            for (Protein proteinAsCarriersOldProtein : proteinAsCarriersOld) {
                if (!proteinAsCarriersNew.contains(proteinAsCarriersOldProtein)) {
                    proteinAsCarriersOldProtein.getDrugBank().remove(drugBank);
                    proteinAsCarriersOldProtein = em.merge(proteinAsCarriersOldProtein);
                }
            }
            for (Protein proteinAsCarriersNewProtein : proteinAsCarriersNew) {
                if (!proteinAsCarriersOld.contains(proteinAsCarriersNewProtein)) {
                    proteinAsCarriersNewProtein.getDrugBank().add(drugBank);
                    proteinAsCarriersNewProtein = em.merge(proteinAsCarriersNewProtein);
                }
            }
            for (KEGGCompound kEGGCompoundsOldKEGGCompound : kEGGCompoundsOld) {
                if (!kEGGCompoundsNew.contains(kEGGCompoundsOldKEGGCompound)) {
                    kEGGCompoundsOldKEGGCompound.getDrugBanks().remove(drugBank);
                    kEGGCompoundsOldKEGGCompound = em.merge(kEGGCompoundsOldKEGGCompound);
                }
            }
            for (KEGGCompound kEGGCompoundsNewKEGGCompound : kEGGCompoundsNew) {
                if (!kEGGCompoundsOld.contains(kEGGCompoundsNewKEGGCompound)) {
                    kEGGCompoundsNewKEGGCompound.getDrugBanks().add(drugBank);
                    kEGGCompoundsNewKEGGCompound = em.merge(kEGGCompoundsNewKEGGCompound);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = drugBank.getWid();
                if (findDrugBank(id) == null) {
                    throw new NonexistentEntityException("The drugBank with id " + id + " no longer exists.");
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
            DrugBank drugBank;
            try {
                drugBank = em.getReference(DrugBank.class, id);
                drugBank.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The drugBank with id " + id + " no longer exists.", enfe);
            }
            Set<DrugBankCategory> drugBankCategory = drugBank.getDrugBankCategory();
            for (DrugBankCategory drugBankCategoryDrugBankCategory : drugBankCategory) {
                drugBankCategoryDrugBankCategory.getDrugBank().remove(drugBank);
                drugBankCategoryDrugBankCategory = em.merge(drugBankCategoryDrugBankCategory);
            }
            Set<DrugBankPatent> drugBankPatent = drugBank.getDrugBankPatent();
            for (DrugBankPatent drugBankPatentDrugBankPatent : drugBankPatent) {
                drugBankPatentDrugBankPatent.getDrugBank().remove(drugBank);
                drugBankPatentDrugBankPatent = em.merge(drugBankPatentDrugBankPatent);
            }
            Set<DrugBankTarget> drugBankTarget = drugBank.getDrugBankTarget();
            for (DrugBankTarget drugBankTargetDrugBankTarget : drugBankTarget) {
                drugBankTargetDrugBankTarget.setDrugBank(null);
                drugBankTargetDrugBankTarget = em.merge(drugBankTargetDrugBankTarget);
            }
            Set<DrugBankEnzyme> drugBankEnzyme = drugBank.getDrugBankEnzyme();
            for (DrugBankEnzyme drugBankEnzymeDrugBankEnzyme : drugBankEnzyme) {
                drugBankEnzymeDrugBankEnzyme.setDrugBank(null);
                drugBankEnzymeDrugBankEnzyme = em.merge(drugBankEnzymeDrugBankEnzyme);
            }
            Set<DrugBankTransporter> drugBankTransporter = drugBank.getDrugBankTransporter();
            for (DrugBankTransporter drugBankTransporterDrugBankTransporter : drugBankTransporter) {
                drugBankTransporterDrugBankTransporter.setDrugBank(null);
                drugBankTransporterDrugBankTransporter = em.merge(drugBankTransporterDrugBankTransporter);
            }
            Set<DrugBankCarrier> drugBankCarrier = drugBank.getDrugBankCarrier();
            for (DrugBankCarrier drugBankCarrierDrugBankCarrier : drugBankCarrier) {
                drugBankCarrierDrugBankCarrier.setDrugBank(null);
                drugBankCarrierDrugBankCarrier = em.merge(drugBankCarrierDrugBankCarrier);
            }
            Set<Protein> protein = drugBank.getProtein();
            for (Protein proteinProtein : protein) {
                proteinProtein.getDrugBank().remove(drugBank);
                proteinProtein = em.merge(proteinProtein);
            }
            Set<Protein> proteinAsEnzyme = drugBank.getProteinAsEnzyme();
            for (Protein proteinAsEnzymeProtein : proteinAsEnzyme) {
                proteinAsEnzymeProtein.getDrugBank().remove(drugBank);
                proteinAsEnzymeProtein = em.merge(proteinAsEnzymeProtein);
            }
            Set<Protein> proteinAsTransporters = drugBank.getProteinAsTransporters();
            for (Protein proteinAsTransportersProtein : proteinAsTransporters) {
                proteinAsTransportersProtein.getDrugBank().remove(drugBank);
                proteinAsTransportersProtein = em.merge(proteinAsTransportersProtein);
            }
            Set<Protein> proteinAsCarriers = drugBank.getProteinAsCarriers();
            for (Protein proteinAsCarriersProtein : proteinAsCarriers) {
                proteinAsCarriersProtein.getDrugBank().remove(drugBank);
                proteinAsCarriersProtein = em.merge(proteinAsCarriersProtein);
            }
            Set<KEGGCompound> kEGGCompounds = drugBank.getkEGGCompounds();
            for (KEGGCompound kEGGCompoundsKEGGCompound : kEGGCompounds) {
                kEGGCompoundsKEGGCompound.getDrugBanks().remove(drugBank);
                kEGGCompoundsKEGGCompound = em.merge(kEGGCompoundsKEGGCompound);
            }
            em.remove(drugBank);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DrugBank> findDrugBankEntities() {
        return findDrugBankEntities(true, -1, -1);
    }

    public List<DrugBank> findDrugBankEntities(int maxResults, int firstResult) {
        return findDrugBankEntities(false, maxResults, firstResult);
    }

    private List<DrugBank> findDrugBankEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DrugBank.class));
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

    public DrugBank findDrugBank(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DrugBank.class, id);
        } finally {
            em.close();
        }
    }

    public int getDrugBankCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DrugBank> rt = cq.from(DrugBank.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
