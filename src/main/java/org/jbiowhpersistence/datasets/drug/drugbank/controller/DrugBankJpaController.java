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
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankAHFSCodes;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankATCCodes;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankAffectedOrganisms;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankBrands;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankCalculatedProperties;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankCarriers;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankCategories;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankDosages;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankEnzymes;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankExperimentalProperties;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankExternalIdentifiers;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankExternalLinks;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankFoodInteractions;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankGeneralRef;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankGroup;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankManufacturers;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankMixtures;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankPackagers;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankPatents;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankPrices;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankProteinSequences;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankSecondAccessionNumbers;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankSynonyms;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankTargets;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankTaxonomy;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankTaxonomySubstructures;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBankTransporters;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the DrugBank Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
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
        if (drugBank.getDrugBankGeneralRef() == null) {
            drugBank.setDrugBankGeneralRef(new HashSet<DrugBankGeneralRef>());
        }
        if (drugBank.getDrugBankCarriers() == null) {
            drugBank.setDrugBankCarriers(new HashSet<DrugBankCarriers>());
        }
        if (drugBank.getDrugBankTransporters() == null) {
            drugBank.setDrugBankTransporters(new HashSet<DrugBankTransporters>());
        }
        if (drugBank.getDrugBankEnzymes() == null) {
            drugBank.setDrugBankEnzymes(new HashSet<DrugBankEnzymes>());
        }
        if (drugBank.getDrugBankTargets() == null) {
            drugBank.setDrugBankTargets(new HashSet<DrugBankTargets>());
        }
        if (drugBank.getDrugBankExternalLinks() == null) {
            drugBank.setDrugBankExternalLinks(new HashSet<DrugBankExternalLinks>());
        }
        if (drugBank.getDrugBankExternalIdentifiers() == null) {
            drugBank.setDrugBankExternalIdentifiers(new HashSet<DrugBankExternalIdentifiers>());
        }
        if (drugBank.getDrugBankExperimentalProperties() == null) {
            drugBank.setDrugBankExperimentalProperties(new HashSet<DrugBankExperimentalProperties>());
        }
        if (drugBank.getDrugBankCalculatedProperties() == null) {
            drugBank.setDrugBankCalculatedProperties(new HashSet<DrugBankCalculatedProperties>());
        }
        if (drugBank.getDrugBankProteinSequences() == null) {
            drugBank.setDrugBankProteinSequences(new HashSet<DrugBankProteinSequences>());
        }
        if (drugBank.getDrugBankFoodInteractions() == null) {
            drugBank.setDrugBankFoodInteractions(new HashSet<DrugBankFoodInteractions>());
        }
        if (drugBank.getDrugBankPatents() == null) {
            drugBank.setDrugBankPatents(new HashSet<DrugBankPatents>());
        }
        if (drugBank.getDrugBankAHFSCodes() == null) {
            drugBank.setDrugBankAHFSCodes(new HashSet<DrugBankAHFSCodes>());
        }
        if (drugBank.getDrugBankATCCodes() == null) {
            drugBank.setDrugBankATCCodes(new HashSet<DrugBankATCCodes>());
        }
        if (drugBank.getDrugBankDosages() == null) {
            drugBank.setDrugBankDosages(new HashSet<DrugBankDosages>());
        }
        if (drugBank.getDrugBankAffectedOrganisms() == null) {
            drugBank.setDrugBankAffectedOrganisms(new HashSet<DrugBankAffectedOrganisms>());
        }
        if (drugBank.getDrugBankCategories() == null) {
            drugBank.setDrugBankCategories(new HashSet<DrugBankCategories>());
        }
        if (drugBank.getDrugBankPrices() == null) {
            drugBank.setDrugBankPrices(new HashSet<DrugBankPrices>());
        }
        if (drugBank.getDrugBankManufacturers() == null) {
            drugBank.setDrugBankManufacturers(new HashSet<DrugBankManufacturers>());
        }
        if (drugBank.getDrugBankPackagers() == null) {
            drugBank.setDrugBankPackagers(new HashSet<DrugBankPackagers>());
        }
        if (drugBank.getDrugBankMixtures() == null) {
            drugBank.setDrugBankMixtures(new HashSet<DrugBankMixtures>());
        }
        if (drugBank.getDrugBankBrands() == null) {
            drugBank.setDrugBankBrands(new HashSet<DrugBankBrands>());
        }
        if (drugBank.getDrugBankSynonyms() == null) {
            drugBank.setDrugBankSynonyms(new HashSet<DrugBankSynonyms>());
        }
        if (drugBank.getDrugBankTaxonomySubstructures() == null) {
            drugBank.setDrugBankTaxonomySubstructures(new HashSet<DrugBankTaxonomySubstructures>());
        }
        if (drugBank.getDrugBankTaxonomy() == null) {
            drugBank.setDrugBankTaxonomy(new HashSet<DrugBankTaxonomy>());
        }
        if (drugBank.getDrugBankGroup() == null) {
            drugBank.setDrugBankGroup(new HashSet<DrugBankGroup>());
        }
        if (drugBank.getDrugBankSecondAccessionNumbers() == null) {
            drugBank.setDrugBankSecondAccessionNumbers(new HashSet<DrugBankSecondAccessionNumbers>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (!drugBank.getDrugBankGeneralRef().isEmpty()) {
                Set<DrugBankGeneralRef> attachedDrugBankGeneralRef = new HashSet();
                for (DrugBankGeneralRef drugBankGeneralRefDrugBankGeneralRefToAttach : drugBank.getDrugBankGeneralRef()) {
                    DrugBankGeneralRef drugBankGeneral = em.find(drugBankGeneralRefDrugBankGeneralRefToAttach.getClass(), drugBankGeneralRefDrugBankGeneralRefToAttach.getWid());
                    if (drugBankGeneral != null) {
                        attachedDrugBankGeneralRef.add(drugBankGeneral);
                    } else {
                        attachedDrugBankGeneralRef.add(drugBankGeneralRefDrugBankGeneralRefToAttach);
                    }
                }
                drugBank.setDrugBankGeneralRef(attachedDrugBankGeneralRef);
            }
            if (!drugBank.getDrugBankCarriers().isEmpty()) {
                Set<DrugBankCarriers> attachedDrugBankCarriers = new HashSet();
                for (DrugBankCarriers drugBankCarriersDrugBankCarriersToAttach : drugBank.getDrugBankCarriers()) {
                    DrugBankCarriers drugBankCarriers = em.find(drugBankCarriersDrugBankCarriersToAttach.getClass(), drugBankCarriersDrugBankCarriersToAttach.getWid());
                    if (drugBankCarriers != null) {
                        attachedDrugBankCarriers.add(drugBankCarriers);
                    } else {
                        attachedDrugBankCarriers.add(drugBankCarriersDrugBankCarriersToAttach);
                    }
                }
                drugBank.setDrugBankCarriers(attachedDrugBankCarriers);
            }
            if (!drugBank.getDrugBankTransporters().isEmpty()) {
                Set<DrugBankTransporters> attachedDrugBankTransporters = new HashSet();
                for (DrugBankTransporters drugBankTransportersDrugBankTransportersToAttach : drugBank.getDrugBankTransporters()) {
                    DrugBankTransporters drugBankTransporters = em.find(drugBankTransportersDrugBankTransportersToAttach.getClass(), drugBankTransportersDrugBankTransportersToAttach.getWid());
                    if (drugBankTransporters != null) {
                        attachedDrugBankTransporters.add(drugBankTransporters);
                    } else {
                        attachedDrugBankTransporters.add(drugBankTransportersDrugBankTransportersToAttach);
                    }
                }
                drugBank.setDrugBankTransporters(attachedDrugBankTransporters);
            }
            if (!drugBank.getDrugBankEnzymes().isEmpty()) {
                Set<DrugBankEnzymes> attachedDrugBankEnzymes = new HashSet();
                for (DrugBankEnzymes drugBankEnzymesDrugBankEnzymesToAttach : drugBank.getDrugBankEnzymes()) {
                    DrugBankEnzymes drugBankEnzymes = em.find(drugBankEnzymesDrugBankEnzymesToAttach.getClass(), drugBankEnzymesDrugBankEnzymesToAttach.getWid());
                    if (drugBankEnzymes != null) {
                        attachedDrugBankEnzymes.add(drugBankEnzymes);
                    } else {
                        attachedDrugBankEnzymes.add(drugBankEnzymesDrugBankEnzymesToAttach);
                    }
                }
                drugBank.setDrugBankEnzymes(attachedDrugBankEnzymes);
            }
            if (!drugBank.getDrugBankTargets().isEmpty()) {
                Set<DrugBankTargets> attachedDrugBankTargets = new HashSet();
                for (DrugBankTargets drugBankTargetsDrugBankTargetsToAttach : drugBank.getDrugBankTargets()) {
                    DrugBankTargets drugBankTargets = em.find(drugBankTargetsDrugBankTargetsToAttach.getClass(), drugBankTargetsDrugBankTargetsToAttach.getWid());
                    if (drugBankTargets != null) {
                        attachedDrugBankTargets.add(drugBankTargets);
                    } else {
                        attachedDrugBankTargets.add(drugBankTargetsDrugBankTargetsToAttach);
                    }
                }
                drugBank.setDrugBankTargets(attachedDrugBankTargets);
            }
            if (!drugBank.getDrugBankExternalLinks().isEmpty()) {
                Set<DrugBankExternalLinks> attachedDrugBankExternalLinks = new HashSet();
                for (DrugBankExternalLinks drugBankExternalLinksDrugBankExternalLinksToAttach : drugBank.getDrugBankExternalLinks()) {
                    DrugBankExternalLinks drugBankExternalLinks = em.find(drugBankExternalLinksDrugBankExternalLinksToAttach.getClass(), drugBankExternalLinksDrugBankExternalLinksToAttach.getWid());
                    if (drugBankExternalLinks != null) {
                        attachedDrugBankExternalLinks.add(drugBankExternalLinks);
                    } else {
                        attachedDrugBankExternalLinks.add(drugBankExternalLinksDrugBankExternalLinksToAttach);
                    }
                }
                drugBank.setDrugBankExternalLinks(attachedDrugBankExternalLinks);
            }
            if (!drugBank.getDrugBankExternalIdentifiers().isEmpty()) {
                Set<DrugBankExternalIdentifiers> attachedDrugBankExternalIdentifiers = new HashSet();
                for (DrugBankExternalIdentifiers drugBankExternalIdentifiersDrugBankExternalIdentifiersToAttach : drugBank.getDrugBankExternalIdentifiers()) {
                    DrugBankExternalIdentifiers drugBankExternalIdentifiers = em.find(drugBankExternalIdentifiersDrugBankExternalIdentifiersToAttach.getClass(), drugBankExternalIdentifiersDrugBankExternalIdentifiersToAttach.getWid());
                    if (drugBankExternalIdentifiers != null) {
                        attachedDrugBankExternalIdentifiers.add(drugBankExternalIdentifiers);
                    } else {
                        attachedDrugBankExternalIdentifiers.add(drugBankExternalIdentifiersDrugBankExternalIdentifiersToAttach);
                    }
                }
                drugBank.setDrugBankExternalIdentifiers(attachedDrugBankExternalIdentifiers);
            }
            if (!drugBank.getDrugBankExperimentalProperties().isEmpty()) {
                Set<DrugBankExperimentalProperties> attachedDrugBankExperimentalProperties = new HashSet();
                for (DrugBankExperimentalProperties drugBankExperimentalPropertiesDrugBankExperimentalPropertiesToAttach : drugBank.getDrugBankExperimentalProperties()) {
                    DrugBankExperimentalProperties drugBankExperimentalProperties = em.find(drugBankExperimentalPropertiesDrugBankExperimentalPropertiesToAttach.getClass(), drugBankExperimentalPropertiesDrugBankExperimentalPropertiesToAttach.getWid());
                    if (drugBankExperimentalProperties != null) {
                        attachedDrugBankExperimentalProperties.add(drugBankExperimentalProperties);
                    } else {
                        attachedDrugBankExperimentalProperties.add(drugBankExperimentalPropertiesDrugBankExperimentalPropertiesToAttach);
                    }
                }
                drugBank.setDrugBankExperimentalProperties(attachedDrugBankExperimentalProperties);
            }
            if (!drugBank.getDrugBankCalculatedProperties().isEmpty()) {
                Set<DrugBankCalculatedProperties> attachedDrugBankCalculatedProperties = new HashSet();
                for (DrugBankCalculatedProperties drugBankCalculatedPropertiesDrugBankCalculatedPropertiesToAttach : drugBank.getDrugBankCalculatedProperties()) {
                    DrugBankCalculatedProperties drugBankCalculatedProperties = em.find(drugBankCalculatedPropertiesDrugBankCalculatedPropertiesToAttach.getClass(), drugBankCalculatedPropertiesDrugBankCalculatedPropertiesToAttach.getWid());
                    if (drugBankCalculatedProperties != null) {
                        attachedDrugBankCalculatedProperties.add(drugBankCalculatedProperties);
                    } else {
                        attachedDrugBankCalculatedProperties.add(drugBankCalculatedPropertiesDrugBankCalculatedPropertiesToAttach);
                    }
                }
                drugBank.setDrugBankCalculatedProperties(attachedDrugBankCalculatedProperties);
            }
            if (!drugBank.getDrugBankProteinSequences().isEmpty()) {
                Set<DrugBankProteinSequences> attachedDrugBankProteinSequences = new HashSet();
                for (DrugBankProteinSequences drugBankProteinSequencesDrugBankProteinSequencesToAttach : drugBank.getDrugBankProteinSequences()) {
                    DrugBankProteinSequences drugBankProteinSequences = em.find(drugBankProteinSequencesDrugBankProteinSequencesToAttach.getClass(), drugBankProteinSequencesDrugBankProteinSequencesToAttach.getWid());
                    if (drugBankProteinSequences != null) {
                        attachedDrugBankProteinSequences.add(drugBankProteinSequences);
                    } else {
                        attachedDrugBankProteinSequences.add(drugBankProteinSequencesDrugBankProteinSequencesToAttach);
                    }
                }
                drugBank.setDrugBankProteinSequences(attachedDrugBankProteinSequences);
            }
            if (!drugBank.getDrugBankFoodInteractions().isEmpty()) {
                Set<DrugBankFoodInteractions> attachedDrugBankFoodInteractions = new HashSet();
                for (DrugBankFoodInteractions drugBankFoodInteractionsDrugBankFoodInteractionsToAttach : drugBank.getDrugBankFoodInteractions()) {
                    DrugBankFoodInteractions drugBankFoodInteractions = em.find(drugBankFoodInteractionsDrugBankFoodInteractionsToAttach.getClass(), drugBankFoodInteractionsDrugBankFoodInteractionsToAttach.getWid());
                    if (drugBankFoodInteractions != null) {
                        attachedDrugBankFoodInteractions.add(drugBankFoodInteractions);
                    } else {
                        attachedDrugBankFoodInteractions.add(drugBankFoodInteractionsDrugBankFoodInteractionsToAttach);
                    }
                }
                drugBank.setDrugBankFoodInteractions(attachedDrugBankFoodInteractions);
            }
            if (!drugBank.getDrugBankPatents().isEmpty()) {
                Set<DrugBankPatents> attachedDrugBankPatents = new HashSet();
                for (DrugBankPatents drugBankPatentsDrugBankPatentsToAttach : drugBank.getDrugBankPatents()) {
                    DrugBankPatents drugBankPatents = em.find(drugBankPatentsDrugBankPatentsToAttach.getClass(), drugBankPatentsDrugBankPatentsToAttach.getWid());
                    if (drugBankPatents != null) {
                        attachedDrugBankPatents.add(drugBankPatents);
                    } else {
                        drugBankPatentsDrugBankPatentsToAttach.setDrugBank(null);
                        attachedDrugBankPatents.add(drugBankPatentsDrugBankPatentsToAttach);
                    }
                }
                drugBank.setDrugBankPatents(attachedDrugBankPatents);
            }
            if (!drugBank.getDrugBankAHFSCodes().isEmpty()) {
                Set<DrugBankAHFSCodes> attachedDrugBankAHFSCodes = new HashSet();
                for (DrugBankAHFSCodes drugBankAHFSCodesDrugBankAHFSCodesToAttach : drugBank.getDrugBankAHFSCodes()) {
                    DrugBankAHFSCodes drugBankAHFSCodes = em.find(drugBankAHFSCodesDrugBankAHFSCodesToAttach.getClass(), drugBankAHFSCodesDrugBankAHFSCodesToAttach.getWid());
                    if (drugBankAHFSCodes != null) {
                        attachedDrugBankAHFSCodes.add(drugBankAHFSCodes);
                    } else {
                        attachedDrugBankAHFSCodes.add(drugBankAHFSCodesDrugBankAHFSCodesToAttach);
                    }
                }
                drugBank.setDrugBankAHFSCodes(attachedDrugBankAHFSCodes);
            }
            if (!drugBank.getDrugBankATCCodes().isEmpty()) {
                Set<DrugBankATCCodes> attachedDrugBankATCCodes = new HashSet();
                for (DrugBankATCCodes drugBankATCCodesDrugBankATCCodesToAttach : drugBank.getDrugBankATCCodes()) {
                    DrugBankATCCodes drugBankATCCodes = em.find(drugBankATCCodesDrugBankATCCodesToAttach.getClass(), drugBankATCCodesDrugBankATCCodesToAttach.getWid());
                    if (drugBankATCCodes != null) {
                        attachedDrugBankATCCodes.add(drugBankATCCodes);
                    } else {
                        attachedDrugBankATCCodes.add(drugBankATCCodesDrugBankATCCodesToAttach);
                    }
                }
                drugBank.setDrugBankATCCodes(attachedDrugBankATCCodes);
            }
            if (!drugBank.getDrugBankDosages().isEmpty()) {
                Set<DrugBankDosages> attachedDrugBankDosages = new HashSet();
                for (DrugBankDosages drugBankDosagesDrugBankDosagesToAttach : drugBank.getDrugBankDosages()) {
                    DrugBankDosages drugBankDosages = em.find(drugBankDosagesDrugBankDosagesToAttach.getClass(), drugBankDosagesDrugBankDosagesToAttach.getWid());
                    if (drugBankDosages != null) {
                        attachedDrugBankDosages.add(drugBankDosages);
                    } else {
                        attachedDrugBankDosages.add(drugBankDosagesDrugBankDosagesToAttach);
                    }
                }
                drugBank.setDrugBankDosages(attachedDrugBankDosages);
            }
            if (!drugBank.getDrugBankAffectedOrganisms().isEmpty()) {
                Set<DrugBankAffectedOrganisms> attachedDrugBankAffectedOrganisms = new HashSet();
                for (DrugBankAffectedOrganisms drugBankAffectedOrganismsDrugBankAffectedOrganismsToAttach : drugBank.getDrugBankAffectedOrganisms()) {
                    DrugBankAffectedOrganisms drugBankAffectedOrganisms = em.find(drugBankAffectedOrganismsDrugBankAffectedOrganismsToAttach.getClass(), drugBankAffectedOrganismsDrugBankAffectedOrganismsToAttach.getWid());
                    if (drugBankAffectedOrganisms != null) {
                        attachedDrugBankAffectedOrganisms.add(drugBankAffectedOrganisms);
                    } else {
                        attachedDrugBankAffectedOrganisms.add(drugBankAffectedOrganismsDrugBankAffectedOrganismsToAttach);
                    }
                }
                drugBank.setDrugBankAffectedOrganisms(attachedDrugBankAffectedOrganisms);
            }
            if (!drugBank.getDrugBankCategories().isEmpty()) {
                Set<DrugBankCategories> attachedDrugBankCategories = new HashSet();
                for (DrugBankCategories drugBankCategoriesDrugBankCategoriesToAttach : drugBank.getDrugBankCategories()) {
                    DrugBankCategories drugBankCategories = em.find(drugBankCategoriesDrugBankCategoriesToAttach.getClass(), drugBankCategoriesDrugBankCategoriesToAttach.getWid());
                    if (drugBankCategories != null) {
                        attachedDrugBankCategories.add(drugBankCategories);
                    } else {
                        drugBankCategoriesDrugBankCategoriesToAttach.setDrugBank(null);
                        attachedDrugBankCategories.add(drugBankCategoriesDrugBankCategoriesToAttach);
                    }
                }
                drugBank.setDrugBankCategories(attachedDrugBankCategories);
            }
            if (!drugBank.getDrugBankPrices().isEmpty()) {
                Set<DrugBankPrices> attachedDrugBankPrices = new HashSet();
                for (DrugBankPrices drugBankPricesDrugBankPricesToAttach : drugBank.getDrugBankPrices()) {
                    DrugBankPrices drugBankPrices = em.find(drugBankPricesDrugBankPricesToAttach.getClass(), drugBankPricesDrugBankPricesToAttach.getWid());
                    if (drugBankPrices != null) {
                        attachedDrugBankPrices.add(drugBankPrices);
                    } else {
                        attachedDrugBankPrices.add(drugBankPricesDrugBankPricesToAttach);
                    }
                }
                drugBank.setDrugBankPrices(attachedDrugBankPrices);
            }
            if (!drugBank.getDrugBankManufacturers().isEmpty()) {
                Set<DrugBankManufacturers> attachedDrugBankManufacturers = new HashSet();
                for (DrugBankManufacturers drugBankManufacturersDrugBankManufacturersToAttach : drugBank.getDrugBankManufacturers()) {
                    DrugBankManufacturers drugBankManufacturers = em.find(drugBankManufacturersDrugBankManufacturersToAttach.getClass(), drugBankManufacturersDrugBankManufacturersToAttach.getWid());
                    if (drugBankManufacturers != null) {
                        attachedDrugBankManufacturers.add(drugBankManufacturers);
                    } else {
                        attachedDrugBankManufacturers.add(drugBankManufacturersDrugBankManufacturersToAttach);
                    }
                }
                drugBank.setDrugBankManufacturers(attachedDrugBankManufacturers);
            }
            if (!drugBank.getDrugBankPackagers().isEmpty()) {
                Set<DrugBankPackagers> attachedDrugBankPackagers = new HashSet();
                for (DrugBankPackagers drugBankPackagersDrugBankPackagersToAttach : drugBank.getDrugBankPackagers()) {
                    DrugBankPackagers drugBankPackagers = em.find(drugBankPackagersDrugBankPackagersToAttach.getClass(), drugBankPackagersDrugBankPackagersToAttach.getWid());
                    if (drugBankPackagers != null) {
                        attachedDrugBankPackagers.add(drugBankPackagers);
                    } else {
                        attachedDrugBankPackagers.add(drugBankPackagersDrugBankPackagersToAttach);
                    }
                }
                drugBank.setDrugBankPackagers(attachedDrugBankPackagers);
            }
            if (!drugBank.getDrugBankMixtures().isEmpty()) {
                Set<DrugBankMixtures> attachedDrugBankMixtures = new HashSet();
                for (DrugBankMixtures drugBankMixturesDrugBankMixturesToAttach : drugBank.getDrugBankMixtures()) {
                    DrugBankMixtures drugBankMixtures = em.find(drugBankMixturesDrugBankMixturesToAttach.getClass(), drugBankMixturesDrugBankMixturesToAttach.getWid());
                    if (drugBankMixtures != null) {
                        attachedDrugBankMixtures.add(drugBankMixtures);
                    } else {
                        attachedDrugBankMixtures.add(drugBankMixturesDrugBankMixturesToAttach);
                    }
                }
                drugBank.setDrugBankMixtures(attachedDrugBankMixtures);
            }
            if (!drugBank.getDrugBankBrands().isEmpty()) {
                Set<DrugBankBrands> attachedDrugBankBrands = new HashSet();
                for (DrugBankBrands drugBankBrandsDrugBankBrandsToAttach : drugBank.getDrugBankBrands()) {
                    DrugBankBrands drugBankBrands = em.find(drugBankBrandsDrugBankBrandsToAttach.getClass(), drugBankBrandsDrugBankBrandsToAttach.getWid());
                    if (drugBankBrands != null) {
                        attachedDrugBankBrands.add(drugBankBrands);
                    } else {
                        attachedDrugBankBrands.add(drugBankBrandsDrugBankBrandsToAttach);
                    }
                }
                drugBank.setDrugBankBrands(attachedDrugBankBrands);
            }
            if (!drugBank.getDrugBankSynonyms().isEmpty()) {
                Set<DrugBankSynonyms> attachedDrugBankSynonyms = new HashSet();
                for (DrugBankSynonyms drugBankSynonymsDrugBankSynonymsToAttach : drugBank.getDrugBankSynonyms()) {
                    DrugBankSynonyms drugBankSynonyms = em.find(drugBankSynonymsDrugBankSynonymsToAttach.getClass(), drugBankSynonymsDrugBankSynonymsToAttach.getWid());
                    if (drugBankSynonyms != null) {
                        attachedDrugBankSynonyms.add(drugBankSynonyms);
                    } else {
                        attachedDrugBankSynonyms.add(drugBankSynonymsDrugBankSynonymsToAttach);
                    }
                }
                drugBank.setDrugBankSynonyms(attachedDrugBankSynonyms);
            }
            if (!drugBank.getDrugBankTaxonomySubstructures().isEmpty()) {
                Set<DrugBankTaxonomySubstructures> attachedDrugBankTaxonomySubstructures = new HashSet();
                for (DrugBankTaxonomySubstructures drugBankTaxonomySubstructuresDrugBankTaxonomySubstructuresToAttach : drugBank.getDrugBankTaxonomySubstructures()) {
                    DrugBankTaxonomySubstructures drugBankTaxonomySubstructures = em.find(drugBankTaxonomySubstructuresDrugBankTaxonomySubstructuresToAttach.getClass(), drugBankTaxonomySubstructuresDrugBankTaxonomySubstructuresToAttach.getWid());
                    if (drugBankTaxonomySubstructures != null) {
                        attachedDrugBankTaxonomySubstructures.add(drugBankTaxonomySubstructures);
                    } else {
                        attachedDrugBankTaxonomySubstructures.add(drugBankTaxonomySubstructuresDrugBankTaxonomySubstructuresToAttach);
                    }
                }
                drugBank.setDrugBankTaxonomySubstructures(attachedDrugBankTaxonomySubstructures);
            }
            if (!drugBank.getDrugBankTaxonomy().isEmpty()) {
                Set<DrugBankTaxonomy> attachedDrugBankTaxonomy = new HashSet();
                for (DrugBankTaxonomy drugBankTaxonomyDrugBankTaxonomyToAttach : drugBank.getDrugBankTaxonomy()) {
                    DrugBankTaxonomy drugBankTaxonomy = em.find(drugBankTaxonomyDrugBankTaxonomyToAttach.getClass(), drugBankTaxonomyDrugBankTaxonomyToAttach.getWid());
                    if (drugBankTaxonomy != null) {
                        attachedDrugBankTaxonomy.add(drugBankTaxonomy);
                    } else {
                        attachedDrugBankTaxonomy.add(drugBankTaxonomyDrugBankTaxonomyToAttach);
                    }
                }
                drugBank.setDrugBankTaxonomy(attachedDrugBankTaxonomy);
            }
            if (!drugBank.getDrugBankGroup().isEmpty()) {
                Set<DrugBankGroup> attachedDrugBankGroup = new HashSet();
                for (DrugBankGroup drugBankGroupDrugBankGroupToAttach : drugBank.getDrugBankGroup()) {
                    DrugBankGroup drugBankGroup = em.find(drugBankGroupDrugBankGroupToAttach.getClass(), drugBankGroupDrugBankGroupToAttach.getWid());
                    if (drugBankGroup != null) {
                        attachedDrugBankGroup.add(drugBankGroup);
                    } else {
                        attachedDrugBankGroup.add(drugBankGroupDrugBankGroupToAttach);
                    }
                }
                drugBank.setDrugBankGroup(attachedDrugBankGroup);
            }
            if (!drugBank.getDrugBankSecondAccessionNumbers().isEmpty()) {
                Set<DrugBankSecondAccessionNumbers> attachedDrugBankSecondAccessionNumbers = new HashSet();
                for (DrugBankSecondAccessionNumbers drugBankSecondAccessionNumbersDrugBankSecondAccessionNumbersToAttach : drugBank.getDrugBankSecondAccessionNumbers()) {
                    DrugBankSecondAccessionNumbers drugBankSecondAccessionNumbers = em.find(drugBankSecondAccessionNumbersDrugBankSecondAccessionNumbersToAttach.getClass(), drugBankSecondAccessionNumbersDrugBankSecondAccessionNumbersToAttach.getWid());
                    if (drugBankSecondAccessionNumbers != null) {
                        attachedDrugBankSecondAccessionNumbers.add(drugBankSecondAccessionNumbers);
                    } else {
                        attachedDrugBankSecondAccessionNumbers.add(drugBankSecondAccessionNumbersDrugBankSecondAccessionNumbersToAttach);
                    }
                }
                drugBank.setDrugBankSecondAccessionNumbers(attachedDrugBankSecondAccessionNumbers);
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
            Set<KEGGCompound> kEGGCompoundsOld = persistentDrugBank.getkEGGCompounds();
            Set<KEGGCompound> kEGGCompoundsNew = drugBank.getkEGGCompounds();
            Set<DrugBankGeneralRef> drugBankGeneralRefOld = persistentDrugBank.getDrugBankGeneralRef();
            Set<DrugBankGeneralRef> drugBankGeneralRefNew = drugBank.getDrugBankGeneralRef();
            Set<DrugBankCarriers> drugBankCarriersOld = persistentDrugBank.getDrugBankCarriers();
            Set<DrugBankCarriers> drugBankCarriersNew = drugBank.getDrugBankCarriers();
            Set<DrugBankTransporters> drugBankTransportersOld = persistentDrugBank.getDrugBankTransporters();
            Set<DrugBankTransporters> drugBankTransportersNew = drugBank.getDrugBankTransporters();
            Set<DrugBankEnzymes> drugBankEnzymesOld = persistentDrugBank.getDrugBankEnzymes();
            Set<DrugBankEnzymes> drugBankEnzymesNew = drugBank.getDrugBankEnzymes();
            Set<DrugBankTargets> drugBankTargetsOld = persistentDrugBank.getDrugBankTargets();
            Set<DrugBankTargets> drugBankTargetsNew = drugBank.getDrugBankTargets();
            Set<DrugBankExternalLinks> drugBankExternalLinksOld = persistentDrugBank.getDrugBankExternalLinks();
            Set<DrugBankExternalLinks> drugBankExternalLinksNew = drugBank.getDrugBankExternalLinks();
            Set<DrugBankExternalIdentifiers> drugBankExternalIdentifiersOld = persistentDrugBank.getDrugBankExternalIdentifiers();
            Set<DrugBankExternalIdentifiers> drugBankExternalIdentifiersNew = drugBank.getDrugBankExternalIdentifiers();
            Set<DrugBankExperimentalProperties> drugBankExperimentalPropertiesOld = persistentDrugBank.getDrugBankExperimentalProperties();
            Set<DrugBankExperimentalProperties> drugBankExperimentalPropertiesNew = drugBank.getDrugBankExperimentalProperties();
            Set<DrugBankCalculatedProperties> drugBankCalculatedPropertiesOld = persistentDrugBank.getDrugBankCalculatedProperties();
            Set<DrugBankCalculatedProperties> drugBankCalculatedPropertiesNew = drugBank.getDrugBankCalculatedProperties();
            Set<DrugBankProteinSequences> drugBankProteinSequencesOld = persistentDrugBank.getDrugBankProteinSequences();
            Set<DrugBankProteinSequences> drugBankProteinSequencesNew = drugBank.getDrugBankProteinSequences();
            Set<DrugBankFoodInteractions> drugBankFoodInteractionsOld = persistentDrugBank.getDrugBankFoodInteractions();
            Set<DrugBankFoodInteractions> drugBankFoodInteractionsNew = drugBank.getDrugBankFoodInteractions();
            Set<DrugBankPatents> drugBankPatentsOld = persistentDrugBank.getDrugBankPatents();
            Set<DrugBankPatents> drugBankPatentsNew = drugBank.getDrugBankPatents();
            Set<DrugBankAHFSCodes> drugBankAHFSCodesOld = persistentDrugBank.getDrugBankAHFSCodes();
            Set<DrugBankAHFSCodes> drugBankAHFSCodesNew = drugBank.getDrugBankAHFSCodes();
            Set<DrugBankATCCodes> drugBankATCCodesOld = persistentDrugBank.getDrugBankATCCodes();
            Set<DrugBankATCCodes> drugBankATCCodesNew = drugBank.getDrugBankATCCodes();
            Set<DrugBankDosages> drugBankDosagesOld = persistentDrugBank.getDrugBankDosages();
            Set<DrugBankDosages> drugBankDosagesNew = drugBank.getDrugBankDosages();
            Set<DrugBankAffectedOrganisms> drugBankAffectedOrganismsOld = persistentDrugBank.getDrugBankAffectedOrganisms();
            Set<DrugBankAffectedOrganisms> drugBankAffectedOrganismsNew = drugBank.getDrugBankAffectedOrganisms();
            Set<DrugBankCategories> drugBankCategoriesOld = persistentDrugBank.getDrugBankCategories();
            Set<DrugBankCategories> drugBankCategoriesNew = drugBank.getDrugBankCategories();
            Set<DrugBankPrices> drugBankPricesOld = persistentDrugBank.getDrugBankPrices();
            Set<DrugBankPrices> drugBankPricesNew = drugBank.getDrugBankPrices();
            Set<DrugBankManufacturers> drugBankManufacturersOld = persistentDrugBank.getDrugBankManufacturers();
            Set<DrugBankManufacturers> drugBankManufacturersNew = drugBank.getDrugBankManufacturers();
            Set<DrugBankPackagers> drugBankPackagersOld = persistentDrugBank.getDrugBankPackagers();
            Set<DrugBankPackagers> drugBankPackagersNew = drugBank.getDrugBankPackagers();
            Set<DrugBankMixtures> drugBankMixturesOld = persistentDrugBank.getDrugBankMixtures();
            Set<DrugBankMixtures> drugBankMixturesNew = drugBank.getDrugBankMixtures();
            Set<DrugBankBrands> drugBankBrandsOld = persistentDrugBank.getDrugBankBrands();
            Set<DrugBankBrands> drugBankBrandsNew = drugBank.getDrugBankBrands();
            Set<DrugBankSynonyms> drugBankSynonymsOld = persistentDrugBank.getDrugBankSynonyms();
            Set<DrugBankSynonyms> drugBankSynonymsNew = drugBank.getDrugBankSynonyms();
            Set<DrugBankTaxonomySubstructures> drugBankTaxonomySubstructuresOld = persistentDrugBank.getDrugBankTaxonomySubstructures();
            Set<DrugBankTaxonomySubstructures> drugBankTaxonomySubstructuresNew = drugBank.getDrugBankTaxonomySubstructures();
            Set<DrugBankTaxonomy> drugBankTaxonomyOld = persistentDrugBank.getDrugBankTaxonomy();
            Set<DrugBankTaxonomy> drugBankTaxonomyNew = drugBank.getDrugBankTaxonomy();
            Set<DrugBankGroup> drugBankGroupOld = persistentDrugBank.getDrugBankGroup();
            Set<DrugBankGroup> drugBankGroupNew = drugBank.getDrugBankGroup();
            Set<DrugBankSecondAccessionNumbers> drugBankSecondAccessionNumbersOld = persistentDrugBank.getDrugBankSecondAccessionNumbers();
            Set<DrugBankSecondAccessionNumbers> drugBankSecondAccessionNumbersNew = drugBank.getDrugBankSecondAccessionNumbers();
            Set<Protein> proteinAsCarriersOld = persistentDrugBank.getProteinAsCarriers();
            Set<Protein> proteinAsCarriersNew = drugBank.getProteinAsCarriers();
            Set<Protein> proteinAsEnzymeOld = persistentDrugBank.getProteinAsEnzyme();
            Set<Protein> proteinAsEnzymeNew = drugBank.getProteinAsEnzyme();
            Set<Protein> proteinAsTransportersOld = persistentDrugBank.getProteinAsTransporters();
            Set<Protein> proteinAsTransportersNew = drugBank.getProteinAsTransporters();
            Set<Protein> proteinOld = persistentDrugBank.getProtein();
            Set<Protein> proteinNew = drugBank.getProtein();
            Set<KEGGCompound> attachedkEGGCompoundsNew = new HashSet();
            for (KEGGCompound kEGGCompoundsNewKEGGCompoundToAttach : kEGGCompoundsNew) {
                kEGGCompoundsNewKEGGCompoundToAttach = em.getReference(kEGGCompoundsNewKEGGCompoundToAttach.getClass(), kEGGCompoundsNewKEGGCompoundToAttach.getWid());
                attachedkEGGCompoundsNew.add(kEGGCompoundsNewKEGGCompoundToAttach);
            }
            kEGGCompoundsNew = attachedkEGGCompoundsNew;
            drugBank.setkEGGCompounds(kEGGCompoundsNew);
            Set<DrugBankGeneralRef> attachedDrugBankGeneralRefNew = new HashSet();
            for (DrugBankGeneralRef drugBankGeneralRefNewDrugBankGeneralRefToAttach : drugBankGeneralRefNew) {
                drugBankGeneralRefNewDrugBankGeneralRefToAttach = em.getReference(drugBankGeneralRefNewDrugBankGeneralRefToAttach.getClass(), drugBankGeneralRefNewDrugBankGeneralRefToAttach.getWid());
                attachedDrugBankGeneralRefNew.add(drugBankGeneralRefNewDrugBankGeneralRefToAttach);
            }
            drugBankGeneralRefNew = attachedDrugBankGeneralRefNew;
            drugBank.setDrugBankGeneralRef(drugBankGeneralRefNew);
            Set<DrugBankCarriers> attachedDrugBankCarriersNew = new HashSet();
            for (DrugBankCarriers drugBankCarriersNewDrugBankCarriersToAttach : drugBankCarriersNew) {
                drugBankCarriersNewDrugBankCarriersToAttach = em.getReference(drugBankCarriersNewDrugBankCarriersToAttach.getClass(), drugBankCarriersNewDrugBankCarriersToAttach.getWid());
                attachedDrugBankCarriersNew.add(drugBankCarriersNewDrugBankCarriersToAttach);
            }
            drugBankCarriersNew = attachedDrugBankCarriersNew;
            drugBank.setDrugBankCarriers(drugBankCarriersNew);
            Set<DrugBankTransporters> attachedDrugBankTransportersNew = new HashSet();
            for (DrugBankTransporters drugBankTransportersNewDrugBankTransportersToAttach : drugBankTransportersNew) {
                drugBankTransportersNewDrugBankTransportersToAttach = em.getReference(drugBankTransportersNewDrugBankTransportersToAttach.getClass(), drugBankTransportersNewDrugBankTransportersToAttach.getWid());
                attachedDrugBankTransportersNew.add(drugBankTransportersNewDrugBankTransportersToAttach);
            }
            drugBankTransportersNew = attachedDrugBankTransportersNew;
            drugBank.setDrugBankTransporters(drugBankTransportersNew);
            Set<DrugBankEnzymes> attachedDrugBankEnzymesNew = new HashSet();
            for (DrugBankEnzymes drugBankEnzymesNewDrugBankEnzymesToAttach : drugBankEnzymesNew) {
                drugBankEnzymesNewDrugBankEnzymesToAttach = em.getReference(drugBankEnzymesNewDrugBankEnzymesToAttach.getClass(), drugBankEnzymesNewDrugBankEnzymesToAttach.getWid());
                attachedDrugBankEnzymesNew.add(drugBankEnzymesNewDrugBankEnzymesToAttach);
            }
            drugBankEnzymesNew = attachedDrugBankEnzymesNew;
            drugBank.setDrugBankEnzymes(drugBankEnzymesNew);
            Set<DrugBankTargets> attachedDrugBankTargetsNew = new HashSet();
            for (DrugBankTargets drugBankTargetsNewDrugBankTargetsToAttach : drugBankTargetsNew) {
                drugBankTargetsNewDrugBankTargetsToAttach = em.getReference(drugBankTargetsNewDrugBankTargetsToAttach.getClass(), drugBankTargetsNewDrugBankTargetsToAttach.getWid());
                attachedDrugBankTargetsNew.add(drugBankTargetsNewDrugBankTargetsToAttach);
            }
            drugBankTargetsNew = attachedDrugBankTargetsNew;
            drugBank.setDrugBankTargets(drugBankTargetsNew);
            Set<DrugBankExternalLinks> attachedDrugBankExternalLinksNew = new HashSet();
            for (DrugBankExternalLinks drugBankExternalLinksNewDrugBankExternalLinksToAttach : drugBankExternalLinksNew) {
                drugBankExternalLinksNewDrugBankExternalLinksToAttach = em.getReference(drugBankExternalLinksNewDrugBankExternalLinksToAttach.getClass(), drugBankExternalLinksNewDrugBankExternalLinksToAttach.getWid());
                attachedDrugBankExternalLinksNew.add(drugBankExternalLinksNewDrugBankExternalLinksToAttach);
            }
            drugBankExternalLinksNew = attachedDrugBankExternalLinksNew;
            drugBank.setDrugBankExternalLinks(drugBankExternalLinksNew);
            Set<DrugBankExternalIdentifiers> attachedDrugBankExternalIdentifiersNew = new HashSet();
            for (DrugBankExternalIdentifiers drugBankExternalIdentifiersNewDrugBankExternalIdentifiersToAttach : drugBankExternalIdentifiersNew) {
                drugBankExternalIdentifiersNewDrugBankExternalIdentifiersToAttach = em.getReference(drugBankExternalIdentifiersNewDrugBankExternalIdentifiersToAttach.getClass(), drugBankExternalIdentifiersNewDrugBankExternalIdentifiersToAttach.getWid());
                attachedDrugBankExternalIdentifiersNew.add(drugBankExternalIdentifiersNewDrugBankExternalIdentifiersToAttach);
            }
            drugBankExternalIdentifiersNew = attachedDrugBankExternalIdentifiersNew;
            drugBank.setDrugBankExternalIdentifiers(drugBankExternalIdentifiersNew);
            Set<DrugBankExperimentalProperties> attachedDrugBankExperimentalPropertiesNew = new HashSet();
            for (DrugBankExperimentalProperties drugBankExperimentalPropertiesNewDrugBankExperimentalPropertiesToAttach : drugBankExperimentalPropertiesNew) {
                drugBankExperimentalPropertiesNewDrugBankExperimentalPropertiesToAttach = em.getReference(drugBankExperimentalPropertiesNewDrugBankExperimentalPropertiesToAttach.getClass(), drugBankExperimentalPropertiesNewDrugBankExperimentalPropertiesToAttach.getWid());
                attachedDrugBankExperimentalPropertiesNew.add(drugBankExperimentalPropertiesNewDrugBankExperimentalPropertiesToAttach);
            }
            drugBankExperimentalPropertiesNew = attachedDrugBankExperimentalPropertiesNew;
            drugBank.setDrugBankExperimentalProperties(drugBankExperimentalPropertiesNew);
            Set<DrugBankCalculatedProperties> attachedDrugBankCalculatedPropertiesNew = new HashSet();
            for (DrugBankCalculatedProperties drugBankCalculatedPropertiesNewDrugBankCalculatedPropertiesToAttach : drugBankCalculatedPropertiesNew) {
                drugBankCalculatedPropertiesNewDrugBankCalculatedPropertiesToAttach = em.getReference(drugBankCalculatedPropertiesNewDrugBankCalculatedPropertiesToAttach.getClass(), drugBankCalculatedPropertiesNewDrugBankCalculatedPropertiesToAttach.getWid());
                attachedDrugBankCalculatedPropertiesNew.add(drugBankCalculatedPropertiesNewDrugBankCalculatedPropertiesToAttach);
            }
            drugBankCalculatedPropertiesNew = attachedDrugBankCalculatedPropertiesNew;
            drugBank.setDrugBankCalculatedProperties(drugBankCalculatedPropertiesNew);
            Set<DrugBankProteinSequences> attachedDrugBankProteinSequencesNew = new HashSet();
            for (DrugBankProteinSequences drugBankProteinSequencesNewDrugBankProteinSequencesToAttach : drugBankProteinSequencesNew) {
                drugBankProteinSequencesNewDrugBankProteinSequencesToAttach = em.getReference(drugBankProteinSequencesNewDrugBankProteinSequencesToAttach.getClass(), drugBankProteinSequencesNewDrugBankProteinSequencesToAttach.getWid());
                attachedDrugBankProteinSequencesNew.add(drugBankProteinSequencesNewDrugBankProteinSequencesToAttach);
            }
            drugBankProteinSequencesNew = attachedDrugBankProteinSequencesNew;
            drugBank.setDrugBankProteinSequences(drugBankProteinSequencesNew);
            Set<DrugBankFoodInteractions> attachedDrugBankFoodInteractionsNew = new HashSet();
            for (DrugBankFoodInteractions drugBankFoodInteractionsNewDrugBankFoodInteractionsToAttach : drugBankFoodInteractionsNew) {
                drugBankFoodInteractionsNewDrugBankFoodInteractionsToAttach = em.getReference(drugBankFoodInteractionsNewDrugBankFoodInteractionsToAttach.getClass(), drugBankFoodInteractionsNewDrugBankFoodInteractionsToAttach.getWid());
                attachedDrugBankFoodInteractionsNew.add(drugBankFoodInteractionsNewDrugBankFoodInteractionsToAttach);
            }
            drugBankFoodInteractionsNew = attachedDrugBankFoodInteractionsNew;
            drugBank.setDrugBankFoodInteractions(drugBankFoodInteractionsNew);
            Set<DrugBankPatents> attachedDrugBankPatentsNew = new HashSet();
            for (DrugBankPatents drugBankPatentsNewDrugBankPatentsToAttach : drugBankPatentsNew) {
                drugBankPatentsNewDrugBankPatentsToAttach = em.getReference(drugBankPatentsNewDrugBankPatentsToAttach.getClass(), drugBankPatentsNewDrugBankPatentsToAttach.getWid());
                attachedDrugBankPatentsNew.add(drugBankPatentsNewDrugBankPatentsToAttach);
            }
            drugBankPatentsNew = attachedDrugBankPatentsNew;
            drugBank.setDrugBankPatents(drugBankPatentsNew);
            Set<DrugBankAHFSCodes> attachedDrugBankAHFSCodesNew = new HashSet();
            for (DrugBankAHFSCodes drugBankAHFSCodesNewDrugBankAHFSCodesToAttach : drugBankAHFSCodesNew) {
                drugBankAHFSCodesNewDrugBankAHFSCodesToAttach = em.getReference(drugBankAHFSCodesNewDrugBankAHFSCodesToAttach.getClass(), drugBankAHFSCodesNewDrugBankAHFSCodesToAttach.getWid());
                attachedDrugBankAHFSCodesNew.add(drugBankAHFSCodesNewDrugBankAHFSCodesToAttach);
            }
            drugBankAHFSCodesNew = attachedDrugBankAHFSCodesNew;
            drugBank.setDrugBankAHFSCodes(drugBankAHFSCodesNew);
            Set<DrugBankATCCodes> attachedDrugBankATCCodesNew = new HashSet();
            for (DrugBankATCCodes drugBankATCCodesNewDrugBankATCCodesToAttach : drugBankATCCodesNew) {
                drugBankATCCodesNewDrugBankATCCodesToAttach = em.getReference(drugBankATCCodesNewDrugBankATCCodesToAttach.getClass(), drugBankATCCodesNewDrugBankATCCodesToAttach.getWid());
                attachedDrugBankATCCodesNew.add(drugBankATCCodesNewDrugBankATCCodesToAttach);
            }
            drugBankATCCodesNew = attachedDrugBankATCCodesNew;
            drugBank.setDrugBankATCCodes(drugBankATCCodesNew);
            Set<DrugBankDosages> attachedDrugBankDosagesNew = new HashSet();
            for (DrugBankDosages drugBankDosagesNewDrugBankDosagesToAttach : drugBankDosagesNew) {
                drugBankDosagesNewDrugBankDosagesToAttach = em.getReference(drugBankDosagesNewDrugBankDosagesToAttach.getClass(), drugBankDosagesNewDrugBankDosagesToAttach.getWid());
                attachedDrugBankDosagesNew.add(drugBankDosagesNewDrugBankDosagesToAttach);
            }
            drugBankDosagesNew = attachedDrugBankDosagesNew;
            drugBank.setDrugBankDosages(drugBankDosagesNew);
            Set<DrugBankAffectedOrganisms> attachedDrugBankAffectedOrganismsNew = new HashSet();
            for (DrugBankAffectedOrganisms drugBankAffectedOrganismsNewDrugBankAffectedOrganismsToAttach : drugBankAffectedOrganismsNew) {
                drugBankAffectedOrganismsNewDrugBankAffectedOrganismsToAttach = em.getReference(drugBankAffectedOrganismsNewDrugBankAffectedOrganismsToAttach.getClass(), drugBankAffectedOrganismsNewDrugBankAffectedOrganismsToAttach.getWid());
                attachedDrugBankAffectedOrganismsNew.add(drugBankAffectedOrganismsNewDrugBankAffectedOrganismsToAttach);
            }
            drugBankAffectedOrganismsNew = attachedDrugBankAffectedOrganismsNew;
            drugBank.setDrugBankAffectedOrganisms(drugBankAffectedOrganismsNew);
            Set<DrugBankCategories> attachedDrugBankCategoriesNew = new HashSet();
            for (DrugBankCategories drugBankCategoriesNewDrugBankCategoriesToAttach : drugBankCategoriesNew) {
                drugBankCategoriesNewDrugBankCategoriesToAttach = em.getReference(drugBankCategoriesNewDrugBankCategoriesToAttach.getClass(), drugBankCategoriesNewDrugBankCategoriesToAttach.getWid());
                attachedDrugBankCategoriesNew.add(drugBankCategoriesNewDrugBankCategoriesToAttach);
            }
            drugBankCategoriesNew = attachedDrugBankCategoriesNew;
            drugBank.setDrugBankCategories(drugBankCategoriesNew);
            Set<DrugBankPrices> attachedDrugBankPricesNew = new HashSet();
            for (DrugBankPrices drugBankPricesNewDrugBankPricesToAttach : drugBankPricesNew) {
                drugBankPricesNewDrugBankPricesToAttach = em.getReference(drugBankPricesNewDrugBankPricesToAttach.getClass(), drugBankPricesNewDrugBankPricesToAttach.getWid());
                attachedDrugBankPricesNew.add(drugBankPricesNewDrugBankPricesToAttach);
            }
            drugBankPricesNew = attachedDrugBankPricesNew;
            drugBank.setDrugBankPrices(drugBankPricesNew);
            Set<DrugBankManufacturers> attachedDrugBankManufacturersNew = new HashSet();
            for (DrugBankManufacturers drugBankManufacturersNewDrugBankManufacturersToAttach : drugBankManufacturersNew) {
                drugBankManufacturersNewDrugBankManufacturersToAttach = em.getReference(drugBankManufacturersNewDrugBankManufacturersToAttach.getClass(), drugBankManufacturersNewDrugBankManufacturersToAttach.getWid());
                attachedDrugBankManufacturersNew.add(drugBankManufacturersNewDrugBankManufacturersToAttach);
            }
            drugBankManufacturersNew = attachedDrugBankManufacturersNew;
            drugBank.setDrugBankManufacturers(drugBankManufacturersNew);
            Set<DrugBankPackagers> attachedDrugBankPackagersNew = new HashSet();
            for (DrugBankPackagers drugBankPackagersNewDrugBankPackagersToAttach : drugBankPackagersNew) {
                drugBankPackagersNewDrugBankPackagersToAttach = em.getReference(drugBankPackagersNewDrugBankPackagersToAttach.getClass(), drugBankPackagersNewDrugBankPackagersToAttach.getWid());
                attachedDrugBankPackagersNew.add(drugBankPackagersNewDrugBankPackagersToAttach);
            }
            drugBankPackagersNew = attachedDrugBankPackagersNew;
            drugBank.setDrugBankPackagers(drugBankPackagersNew);
            Set<DrugBankMixtures> attachedDrugBankMixturesNew = new HashSet();
            for (DrugBankMixtures drugBankMixturesNewDrugBankMixturesToAttach : drugBankMixturesNew) {
                drugBankMixturesNewDrugBankMixturesToAttach = em.getReference(drugBankMixturesNewDrugBankMixturesToAttach.getClass(), drugBankMixturesNewDrugBankMixturesToAttach.getWid());
                attachedDrugBankMixturesNew.add(drugBankMixturesNewDrugBankMixturesToAttach);
            }
            drugBankMixturesNew = attachedDrugBankMixturesNew;
            drugBank.setDrugBankMixtures(drugBankMixturesNew);
            Set<DrugBankBrands> attachedDrugBankBrandsNew = new HashSet();
            for (DrugBankBrands drugBankBrandsNewDrugBankBrandsToAttach : drugBankBrandsNew) {
                drugBankBrandsNewDrugBankBrandsToAttach = em.getReference(drugBankBrandsNewDrugBankBrandsToAttach.getClass(), drugBankBrandsNewDrugBankBrandsToAttach.getWid());
                attachedDrugBankBrandsNew.add(drugBankBrandsNewDrugBankBrandsToAttach);
            }
            drugBankBrandsNew = attachedDrugBankBrandsNew;
            drugBank.setDrugBankBrands(drugBankBrandsNew);
            Set<DrugBankSynonyms> attachedDrugBankSynonymsNew = new HashSet();
            for (DrugBankSynonyms drugBankSynonymsNewDrugBankSynonymsToAttach : drugBankSynonymsNew) {
                drugBankSynonymsNewDrugBankSynonymsToAttach = em.getReference(drugBankSynonymsNewDrugBankSynonymsToAttach.getClass(), drugBankSynonymsNewDrugBankSynonymsToAttach.getWid());
                attachedDrugBankSynonymsNew.add(drugBankSynonymsNewDrugBankSynonymsToAttach);
            }
            drugBankSynonymsNew = attachedDrugBankSynonymsNew;
            drugBank.setDrugBankSynonyms(drugBankSynonymsNew);
            Set<DrugBankTaxonomySubstructures> attachedDrugBankTaxonomySubstructuresNew = new HashSet();
            for (DrugBankTaxonomySubstructures drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructuresToAttach : drugBankTaxonomySubstructuresNew) {
                drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructuresToAttach = em.getReference(drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructuresToAttach.getClass(), drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructuresToAttach.getWid());
                attachedDrugBankTaxonomySubstructuresNew.add(drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructuresToAttach);
            }
            drugBankTaxonomySubstructuresNew = attachedDrugBankTaxonomySubstructuresNew;
            drugBank.setDrugBankTaxonomySubstructures(drugBankTaxonomySubstructuresNew);
            Set<DrugBankTaxonomy> attachedDrugBankTaxonomyNew = new HashSet();
            for (DrugBankTaxonomy drugBankTaxonomyNewDrugBankTaxonomyToAttach : drugBankTaxonomyNew) {
                drugBankTaxonomyNewDrugBankTaxonomyToAttach = em.getReference(drugBankTaxonomyNewDrugBankTaxonomyToAttach.getClass(), drugBankTaxonomyNewDrugBankTaxonomyToAttach.getWid());
                attachedDrugBankTaxonomyNew.add(drugBankTaxonomyNewDrugBankTaxonomyToAttach);
            }
            drugBankTaxonomyNew = attachedDrugBankTaxonomyNew;
            drugBank.setDrugBankTaxonomy(drugBankTaxonomyNew);
            Set<DrugBankGroup> attachedDrugBankGroupNew = new HashSet();
            for (DrugBankGroup drugBankGroupNewDrugBankGroupToAttach : drugBankGroupNew) {
                drugBankGroupNewDrugBankGroupToAttach = em.getReference(drugBankGroupNewDrugBankGroupToAttach.getClass(), drugBankGroupNewDrugBankGroupToAttach.getWid());
                attachedDrugBankGroupNew.add(drugBankGroupNewDrugBankGroupToAttach);
            }
            drugBankGroupNew = attachedDrugBankGroupNew;
            drugBank.setDrugBankGroup(drugBankGroupNew);
            Set<DrugBankSecondAccessionNumbers> attachedDrugBankSecondAccessionNumbersNew = new HashSet();
            for (DrugBankSecondAccessionNumbers drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbersToAttach : drugBankSecondAccessionNumbersNew) {
                drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbersToAttach = em.getReference(drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbersToAttach.getClass(), drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbersToAttach.getWid());
                attachedDrugBankSecondAccessionNumbersNew.add(drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbersToAttach);
            }
            drugBankSecondAccessionNumbersNew = attachedDrugBankSecondAccessionNumbersNew;
            drugBank.setDrugBankSecondAccessionNumbers(drugBankSecondAccessionNumbersNew);
            Set<Protein> attachedProteinAsCarriersNew = new HashSet();
            for (Protein proteinAsCarriersNewProteinToAttach : proteinAsCarriersNew) {
                proteinAsCarriersNewProteinToAttach = em.getReference(proteinAsCarriersNewProteinToAttach.getClass(), proteinAsCarriersNewProteinToAttach.getWid());
                attachedProteinAsCarriersNew.add(proteinAsCarriersNewProteinToAttach);
            }
            proteinAsCarriersNew = attachedProteinAsCarriersNew;
            drugBank.setProteinAsCarriers(proteinAsCarriersNew);
            Set<Protein> attachedProteinAsEnzymeNew = new HashSet();
            for (Protein proteinAsEnzymeNewProteinToAttach : proteinAsEnzymeNew) {
                proteinAsEnzymeNewProteinToAttach = em.getReference(proteinAsEnzymeNewProteinToAttach.getClass(), proteinAsEnzymeNewProteinToAttach.getWid());
                attachedProteinAsEnzymeNew.add(proteinAsEnzymeNewProteinToAttach);
            }
            proteinAsEnzymeNew = attachedProteinAsEnzymeNew;
            drugBank.setProteinAsEnzyme(proteinAsEnzymeNew);
            Set<Protein> attachedProteinAsTransportersNew = new HashSet();
            for (Protein proteinAsTransportersNewProteinToAttach : proteinAsTransportersNew) {
                proteinAsTransportersNewProteinToAttach = em.getReference(proteinAsTransportersNewProteinToAttach.getClass(), proteinAsTransportersNewProteinToAttach.getWid());
                attachedProteinAsTransportersNew.add(proteinAsTransportersNewProteinToAttach);
            }
            proteinAsTransportersNew = attachedProteinAsTransportersNew;
            drugBank.setProteinAsTransporters(proteinAsTransportersNew);
            Set<Protein> attachedProteinNew = new HashSet();
            for (Protein proteinNewProteinToAttach : proteinNew) {
                proteinNewProteinToAttach = em.getReference(proteinNewProteinToAttach.getClass(), proteinNewProteinToAttach.getWid());
                attachedProteinNew.add(proteinNewProteinToAttach);
            }
            proteinNew = attachedProteinNew;
            drugBank.setProtein(proteinNew);
            drugBank = em.merge(drugBank);
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
            for (DrugBankGeneralRef drugBankGeneralRefOldDrugBankGeneralRef : drugBankGeneralRefOld) {
                if (!drugBankGeneralRefNew.contains(drugBankGeneralRefOldDrugBankGeneralRef)) {
                    drugBankGeneralRefOldDrugBankGeneralRef.setDrugBank(null);
                    drugBankGeneralRefOldDrugBankGeneralRef = em.merge(drugBankGeneralRefOldDrugBankGeneralRef);
                }
            }
            for (DrugBankGeneralRef drugBankGeneralRefNewDrugBankGeneralRef : drugBankGeneralRefNew) {
                if (!drugBankGeneralRefOld.contains(drugBankGeneralRefNewDrugBankGeneralRef)) {
                    DrugBank oldDrugBankOfDrugBankGeneralRefNewDrugBankGeneralRef = drugBankGeneralRefNewDrugBankGeneralRef.getDrugBank();
                    drugBankGeneralRefNewDrugBankGeneralRef.setDrugBank(drugBank);
                    drugBankGeneralRefNewDrugBankGeneralRef = em.merge(drugBankGeneralRefNewDrugBankGeneralRef);
                    if (oldDrugBankOfDrugBankGeneralRefNewDrugBankGeneralRef != null && !oldDrugBankOfDrugBankGeneralRefNewDrugBankGeneralRef.equals(drugBank)) {
                        oldDrugBankOfDrugBankGeneralRefNewDrugBankGeneralRef.getDrugBankGeneralRef().remove(drugBankGeneralRefNewDrugBankGeneralRef);
                        em.merge(oldDrugBankOfDrugBankGeneralRefNewDrugBankGeneralRef);
                    }
                }
            }
            for (DrugBankCarriers drugBankCarriersOldDrugBankCarriers : drugBankCarriersOld) {
                if (!drugBankCarriersNew.contains(drugBankCarriersOldDrugBankCarriers)) {
                    drugBankCarriersOldDrugBankCarriers.setDrugBank(null);
                    drugBankCarriersOldDrugBankCarriers = em.merge(drugBankCarriersOldDrugBankCarriers);
                }
            }
            for (DrugBankCarriers drugBankCarriersNewDrugBankCarriers : drugBankCarriersNew) {
                if (!drugBankCarriersOld.contains(drugBankCarriersNewDrugBankCarriers)) {
                    DrugBank oldDrugBankOfDrugBankCarriersNewDrugBankCarriers = drugBankCarriersNewDrugBankCarriers.getDrugBank();
                    drugBankCarriersNewDrugBankCarriers.setDrugBank(drugBank);
                    drugBankCarriersNewDrugBankCarriers = em.merge(drugBankCarriersNewDrugBankCarriers);
                    if (oldDrugBankOfDrugBankCarriersNewDrugBankCarriers != null && !oldDrugBankOfDrugBankCarriersNewDrugBankCarriers.equals(drugBank)) {
                        oldDrugBankOfDrugBankCarriersNewDrugBankCarriers.getDrugBankCarriers().remove(drugBankCarriersNewDrugBankCarriers);
                        em.merge(oldDrugBankOfDrugBankCarriersNewDrugBankCarriers);
                    }
                }
            }
            for (DrugBankTransporters drugBankTransportersOldDrugBankTransporters : drugBankTransportersOld) {
                if (!drugBankTransportersNew.contains(drugBankTransportersOldDrugBankTransporters)) {
                    drugBankTransportersOldDrugBankTransporters.setDrugBank(null);
                    drugBankTransportersOldDrugBankTransporters = em.merge(drugBankTransportersOldDrugBankTransporters);
                }
            }
            for (DrugBankTransporters drugBankTransportersNewDrugBankTransporters : drugBankTransportersNew) {
                if (!drugBankTransportersOld.contains(drugBankTransportersNewDrugBankTransporters)) {
                    DrugBank oldDrugBankOfDrugBankTransportersNewDrugBankTransporters = drugBankTransportersNewDrugBankTransporters.getDrugBank();
                    drugBankTransportersNewDrugBankTransporters.setDrugBank(drugBank);
                    drugBankTransportersNewDrugBankTransporters = em.merge(drugBankTransportersNewDrugBankTransporters);
                    if (oldDrugBankOfDrugBankTransportersNewDrugBankTransporters != null && !oldDrugBankOfDrugBankTransportersNewDrugBankTransporters.equals(drugBank)) {
                        oldDrugBankOfDrugBankTransportersNewDrugBankTransporters.getDrugBankTransporters().remove(drugBankTransportersNewDrugBankTransporters);
                        em.merge(oldDrugBankOfDrugBankTransportersNewDrugBankTransporters);
                    }
                }
            }
            for (DrugBankEnzymes drugBankEnzymesOldDrugBankEnzymes : drugBankEnzymesOld) {
                if (!drugBankEnzymesNew.contains(drugBankEnzymesOldDrugBankEnzymes)) {
                    drugBankEnzymesOldDrugBankEnzymes.setDrugBank(null);
                    drugBankEnzymesOldDrugBankEnzymes = em.merge(drugBankEnzymesOldDrugBankEnzymes);
                }
            }
            for (DrugBankEnzymes drugBankEnzymesNewDrugBankEnzymes : drugBankEnzymesNew) {
                if (!drugBankEnzymesOld.contains(drugBankEnzymesNewDrugBankEnzymes)) {
                    DrugBank oldDrugBankOfDrugBankEnzymesNewDrugBankEnzymes = drugBankEnzymesNewDrugBankEnzymes.getDrugBank();
                    drugBankEnzymesNewDrugBankEnzymes.setDrugBank(drugBank);
                    drugBankEnzymesNewDrugBankEnzymes = em.merge(drugBankEnzymesNewDrugBankEnzymes);
                    if (oldDrugBankOfDrugBankEnzymesNewDrugBankEnzymes != null && !oldDrugBankOfDrugBankEnzymesNewDrugBankEnzymes.equals(drugBank)) {
                        oldDrugBankOfDrugBankEnzymesNewDrugBankEnzymes.getDrugBankEnzymes().remove(drugBankEnzymesNewDrugBankEnzymes);
                        em.merge(oldDrugBankOfDrugBankEnzymesNewDrugBankEnzymes);
                    }
                }
            }
            for (DrugBankTargets drugBankTargetsOldDrugBankTargets : drugBankTargetsOld) {
                if (!drugBankTargetsNew.contains(drugBankTargetsOldDrugBankTargets)) {
                    drugBankTargetsOldDrugBankTargets.setDrugBank(null);
                    drugBankTargetsOldDrugBankTargets = em.merge(drugBankTargetsOldDrugBankTargets);
                }
            }
            for (DrugBankTargets drugBankTargetsNewDrugBankTargets : drugBankTargetsNew) {
                if (!drugBankTargetsOld.contains(drugBankTargetsNewDrugBankTargets)) {
                    DrugBank oldDrugBankOfDrugBankTargetsNewDrugBankTargets = drugBankTargetsNewDrugBankTargets.getDrugBank();
                    drugBankTargetsNewDrugBankTargets.setDrugBank(drugBank);
                    drugBankTargetsNewDrugBankTargets = em.merge(drugBankTargetsNewDrugBankTargets);
                    if (oldDrugBankOfDrugBankTargetsNewDrugBankTargets != null && !oldDrugBankOfDrugBankTargetsNewDrugBankTargets.equals(drugBank)) {
                        oldDrugBankOfDrugBankTargetsNewDrugBankTargets.getDrugBankTargets().remove(drugBankTargetsNewDrugBankTargets);
                        em.merge(oldDrugBankOfDrugBankTargetsNewDrugBankTargets);
                    }
                }
            }
            for (DrugBankExternalLinks drugBankExternalLinksOldDrugBankExternalLinks : drugBankExternalLinksOld) {
                if (!drugBankExternalLinksNew.contains(drugBankExternalLinksOldDrugBankExternalLinks)) {
                    drugBankExternalLinksOldDrugBankExternalLinks.setDrugBank(null);
                    drugBankExternalLinksOldDrugBankExternalLinks = em.merge(drugBankExternalLinksOldDrugBankExternalLinks);
                }
            }
            for (DrugBankExternalLinks drugBankExternalLinksNewDrugBankExternalLinks : drugBankExternalLinksNew) {
                if (!drugBankExternalLinksOld.contains(drugBankExternalLinksNewDrugBankExternalLinks)) {
                    DrugBank oldDrugBankOfDrugBankExternalLinksNewDrugBankExternalLinks = drugBankExternalLinksNewDrugBankExternalLinks.getDrugBank();
                    drugBankExternalLinksNewDrugBankExternalLinks.setDrugBank(drugBank);
                    drugBankExternalLinksNewDrugBankExternalLinks = em.merge(drugBankExternalLinksNewDrugBankExternalLinks);
                    if (oldDrugBankOfDrugBankExternalLinksNewDrugBankExternalLinks != null && !oldDrugBankOfDrugBankExternalLinksNewDrugBankExternalLinks.equals(drugBank)) {
                        oldDrugBankOfDrugBankExternalLinksNewDrugBankExternalLinks.getDrugBankExternalLinks().remove(drugBankExternalLinksNewDrugBankExternalLinks);
                        em.merge(oldDrugBankOfDrugBankExternalLinksNewDrugBankExternalLinks);
                    }
                }
            }
            for (DrugBankExternalIdentifiers drugBankExternalIdentifiersOldDrugBankExternalIdentifiers : drugBankExternalIdentifiersOld) {
                if (!drugBankExternalIdentifiersNew.contains(drugBankExternalIdentifiersOldDrugBankExternalIdentifiers)) {
                    drugBankExternalIdentifiersOldDrugBankExternalIdentifiers.setDrugBank(null);
                    drugBankExternalIdentifiersOldDrugBankExternalIdentifiers = em.merge(drugBankExternalIdentifiersOldDrugBankExternalIdentifiers);
                }
            }
            for (DrugBankExternalIdentifiers drugBankExternalIdentifiersNewDrugBankExternalIdentifiers : drugBankExternalIdentifiersNew) {
                if (!drugBankExternalIdentifiersOld.contains(drugBankExternalIdentifiersNewDrugBankExternalIdentifiers)) {
                    DrugBank oldDrugBankOfDrugBankExternalIdentifiersNewDrugBankExternalIdentifiers = drugBankExternalIdentifiersNewDrugBankExternalIdentifiers.getDrugBank();
                    drugBankExternalIdentifiersNewDrugBankExternalIdentifiers.setDrugBank(drugBank);
                    drugBankExternalIdentifiersNewDrugBankExternalIdentifiers = em.merge(drugBankExternalIdentifiersNewDrugBankExternalIdentifiers);
                    if (oldDrugBankOfDrugBankExternalIdentifiersNewDrugBankExternalIdentifiers != null && !oldDrugBankOfDrugBankExternalIdentifiersNewDrugBankExternalIdentifiers.equals(drugBank)) {
                        oldDrugBankOfDrugBankExternalIdentifiersNewDrugBankExternalIdentifiers.getDrugBankExternalIdentifiers().remove(drugBankExternalIdentifiersNewDrugBankExternalIdentifiers);
                        em.merge(oldDrugBankOfDrugBankExternalIdentifiersNewDrugBankExternalIdentifiers);
                    }
                }
            }
            for (DrugBankExperimentalProperties drugBankExperimentalPropertiesOldDrugBankExperimentalProperties : drugBankExperimentalPropertiesOld) {
                if (!drugBankExperimentalPropertiesNew.contains(drugBankExperimentalPropertiesOldDrugBankExperimentalProperties)) {
                    drugBankExperimentalPropertiesOldDrugBankExperimentalProperties.setDrugBank(null);
                    drugBankExperimentalPropertiesOldDrugBankExperimentalProperties = em.merge(drugBankExperimentalPropertiesOldDrugBankExperimentalProperties);
                }
            }
            for (DrugBankExperimentalProperties drugBankExperimentalPropertiesNewDrugBankExperimentalProperties : drugBankExperimentalPropertiesNew) {
                if (!drugBankExperimentalPropertiesOld.contains(drugBankExperimentalPropertiesNewDrugBankExperimentalProperties)) {
                    DrugBank oldDrugBankOfDrugBankExperimentalPropertiesNewDrugBankExperimentalProperties = drugBankExperimentalPropertiesNewDrugBankExperimentalProperties.getDrugBank();
                    drugBankExperimentalPropertiesNewDrugBankExperimentalProperties.setDrugBank(drugBank);
                    drugBankExperimentalPropertiesNewDrugBankExperimentalProperties = em.merge(drugBankExperimentalPropertiesNewDrugBankExperimentalProperties);
                    if (oldDrugBankOfDrugBankExperimentalPropertiesNewDrugBankExperimentalProperties != null && !oldDrugBankOfDrugBankExperimentalPropertiesNewDrugBankExperimentalProperties.equals(drugBank)) {
                        oldDrugBankOfDrugBankExperimentalPropertiesNewDrugBankExperimentalProperties.getDrugBankExperimentalProperties().remove(drugBankExperimentalPropertiesNewDrugBankExperimentalProperties);
                        em.merge(oldDrugBankOfDrugBankExperimentalPropertiesNewDrugBankExperimentalProperties);
                    }
                }
            }
            for (DrugBankCalculatedProperties drugBankCalculatedPropertiesOldDrugBankCalculatedProperties : drugBankCalculatedPropertiesOld) {
                if (!drugBankCalculatedPropertiesNew.contains(drugBankCalculatedPropertiesOldDrugBankCalculatedProperties)) {
                    drugBankCalculatedPropertiesOldDrugBankCalculatedProperties.setDrugBank(null);
                    drugBankCalculatedPropertiesOldDrugBankCalculatedProperties = em.merge(drugBankCalculatedPropertiesOldDrugBankCalculatedProperties);
                }
            }
            for (DrugBankCalculatedProperties drugBankCalculatedPropertiesNewDrugBankCalculatedProperties : drugBankCalculatedPropertiesNew) {
                if (!drugBankCalculatedPropertiesOld.contains(drugBankCalculatedPropertiesNewDrugBankCalculatedProperties)) {
                    DrugBank oldDrugBankOfDrugBankCalculatedPropertiesNewDrugBankCalculatedProperties = drugBankCalculatedPropertiesNewDrugBankCalculatedProperties.getDrugBank();
                    drugBankCalculatedPropertiesNewDrugBankCalculatedProperties.setDrugBank(drugBank);
                    drugBankCalculatedPropertiesNewDrugBankCalculatedProperties = em.merge(drugBankCalculatedPropertiesNewDrugBankCalculatedProperties);
                    if (oldDrugBankOfDrugBankCalculatedPropertiesNewDrugBankCalculatedProperties != null && !oldDrugBankOfDrugBankCalculatedPropertiesNewDrugBankCalculatedProperties.equals(drugBank)) {
                        oldDrugBankOfDrugBankCalculatedPropertiesNewDrugBankCalculatedProperties.getDrugBankCalculatedProperties().remove(drugBankCalculatedPropertiesNewDrugBankCalculatedProperties);
                        em.merge(oldDrugBankOfDrugBankCalculatedPropertiesNewDrugBankCalculatedProperties);
                    }
                }
            }
            for (DrugBankProteinSequences drugBankProteinSequencesOldDrugBankProteinSequences : drugBankProteinSequencesOld) {
                if (!drugBankProteinSequencesNew.contains(drugBankProteinSequencesOldDrugBankProteinSequences)) {
                    drugBankProteinSequencesOldDrugBankProteinSequences.setDrugBank(null);
                    drugBankProteinSequencesOldDrugBankProteinSequences = em.merge(drugBankProteinSequencesOldDrugBankProteinSequences);
                }
            }
            for (DrugBankProteinSequences drugBankProteinSequencesNewDrugBankProteinSequences : drugBankProteinSequencesNew) {
                if (!drugBankProteinSequencesOld.contains(drugBankProteinSequencesNewDrugBankProteinSequences)) {
                    DrugBank oldDrugBankOfDrugBankProteinSequencesNewDrugBankProteinSequences = drugBankProteinSequencesNewDrugBankProteinSequences.getDrugBank();
                    drugBankProteinSequencesNewDrugBankProteinSequences.setDrugBank(drugBank);
                    drugBankProteinSequencesNewDrugBankProteinSequences = em.merge(drugBankProteinSequencesNewDrugBankProteinSequences);
                    if (oldDrugBankOfDrugBankProteinSequencesNewDrugBankProteinSequences != null && !oldDrugBankOfDrugBankProteinSequencesNewDrugBankProteinSequences.equals(drugBank)) {
                        oldDrugBankOfDrugBankProteinSequencesNewDrugBankProteinSequences.getDrugBankProteinSequences().remove(drugBankProteinSequencesNewDrugBankProteinSequences);
                        em.merge(oldDrugBankOfDrugBankProteinSequencesNewDrugBankProteinSequences);
                    }
                }
            }
            for (DrugBankFoodInteractions drugBankFoodInteractionsOldDrugBankFoodInteractions : drugBankFoodInteractionsOld) {
                if (!drugBankFoodInteractionsNew.contains(drugBankFoodInteractionsOldDrugBankFoodInteractions)) {
                    drugBankFoodInteractionsOldDrugBankFoodInteractions.setDrugBank(null);
                    drugBankFoodInteractionsOldDrugBankFoodInteractions = em.merge(drugBankFoodInteractionsOldDrugBankFoodInteractions);
                }
            }
            for (DrugBankFoodInteractions drugBankFoodInteractionsNewDrugBankFoodInteractions : drugBankFoodInteractionsNew) {
                if (!drugBankFoodInteractionsOld.contains(drugBankFoodInteractionsNewDrugBankFoodInteractions)) {
                    DrugBank oldDrugBankOfDrugBankFoodInteractionsNewDrugBankFoodInteractions = drugBankFoodInteractionsNewDrugBankFoodInteractions.getDrugBank();
                    drugBankFoodInteractionsNewDrugBankFoodInteractions.setDrugBank(drugBank);
                    drugBankFoodInteractionsNewDrugBankFoodInteractions = em.merge(drugBankFoodInteractionsNewDrugBankFoodInteractions);
                    if (oldDrugBankOfDrugBankFoodInteractionsNewDrugBankFoodInteractions != null && !oldDrugBankOfDrugBankFoodInteractionsNewDrugBankFoodInteractions.equals(drugBank)) {
                        oldDrugBankOfDrugBankFoodInteractionsNewDrugBankFoodInteractions.getDrugBankFoodInteractions().remove(drugBankFoodInteractionsNewDrugBankFoodInteractions);
                        em.merge(oldDrugBankOfDrugBankFoodInteractionsNewDrugBankFoodInteractions);
                    }
                }
            }
            for (DrugBankPatents drugBankPatentsOldDrugBankPatents : drugBankPatentsOld) {
                if (!drugBankPatentsNew.contains(drugBankPatentsOldDrugBankPatents)) {
                    drugBankPatentsOldDrugBankPatents.getDrugBank().remove(drugBank);
                    drugBankPatentsOldDrugBankPatents = em.merge(drugBankPatentsOldDrugBankPatents);
                }
            }
            for (DrugBankPatents drugBankPatentsNewDrugBankPatents : drugBankPatentsNew) {
                if (!drugBankPatentsOld.contains(drugBankPatentsNewDrugBankPatents)) {
                    drugBankPatentsNewDrugBankPatents.getDrugBank().add(drugBank);
                    drugBankPatentsNewDrugBankPatents = em.merge(drugBankPatentsNewDrugBankPatents);
                }
            }
            for (DrugBankAHFSCodes drugBankAHFSCodesOldDrugBankAHFSCodes : drugBankAHFSCodesOld) {
                if (!drugBankAHFSCodesNew.contains(drugBankAHFSCodesOldDrugBankAHFSCodes)) {
                    drugBankAHFSCodesOldDrugBankAHFSCodes.setDrugBank(null);
                    drugBankAHFSCodesOldDrugBankAHFSCodes = em.merge(drugBankAHFSCodesOldDrugBankAHFSCodes);
                }
            }
            for (DrugBankAHFSCodes drugBankAHFSCodesNewDrugBankAHFSCodes : drugBankAHFSCodesNew) {
                if (!drugBankAHFSCodesOld.contains(drugBankAHFSCodesNewDrugBankAHFSCodes)) {
                    DrugBank oldDrugBankOfDrugBankAHFSCodesNewDrugBankAHFSCodes = drugBankAHFSCodesNewDrugBankAHFSCodes.getDrugBank();
                    drugBankAHFSCodesNewDrugBankAHFSCodes.setDrugBank(drugBank);
                    drugBankAHFSCodesNewDrugBankAHFSCodes = em.merge(drugBankAHFSCodesNewDrugBankAHFSCodes);
                    if (oldDrugBankOfDrugBankAHFSCodesNewDrugBankAHFSCodes != null && !oldDrugBankOfDrugBankAHFSCodesNewDrugBankAHFSCodes.equals(drugBank)) {
                        oldDrugBankOfDrugBankAHFSCodesNewDrugBankAHFSCodes.getDrugBankAHFSCodes().remove(drugBankAHFSCodesNewDrugBankAHFSCodes);
                        em.merge(oldDrugBankOfDrugBankAHFSCodesNewDrugBankAHFSCodes);
                    }
                }
            }
            for (DrugBankATCCodes drugBankATCCodesOldDrugBankATCCodes : drugBankATCCodesOld) {
                if (!drugBankATCCodesNew.contains(drugBankATCCodesOldDrugBankATCCodes)) {
                    drugBankATCCodesOldDrugBankATCCodes.setDrugBank(null);
                    drugBankATCCodesOldDrugBankATCCodes = em.merge(drugBankATCCodesOldDrugBankATCCodes);
                }
            }
            for (DrugBankATCCodes drugBankATCCodesNewDrugBankATCCodes : drugBankATCCodesNew) {
                if (!drugBankATCCodesOld.contains(drugBankATCCodesNewDrugBankATCCodes)) {
                    DrugBank oldDrugBankOfDrugBankATCCodesNewDrugBankATCCodes = drugBankATCCodesNewDrugBankATCCodes.getDrugBank();
                    drugBankATCCodesNewDrugBankATCCodes.setDrugBank(drugBank);
                    drugBankATCCodesNewDrugBankATCCodes = em.merge(drugBankATCCodesNewDrugBankATCCodes);
                    if (oldDrugBankOfDrugBankATCCodesNewDrugBankATCCodes != null && !oldDrugBankOfDrugBankATCCodesNewDrugBankATCCodes.equals(drugBank)) {
                        oldDrugBankOfDrugBankATCCodesNewDrugBankATCCodes.getDrugBankATCCodes().remove(drugBankATCCodesNewDrugBankATCCodes);
                        em.merge(oldDrugBankOfDrugBankATCCodesNewDrugBankATCCodes);
                    }
                }
            }
            for (DrugBankDosages drugBankDosagesOldDrugBankDosages : drugBankDosagesOld) {
                if (!drugBankDosagesNew.contains(drugBankDosagesOldDrugBankDosages)) {
                    drugBankDosagesOldDrugBankDosages.setDrugBank(null);
                    drugBankDosagesOldDrugBankDosages = em.merge(drugBankDosagesOldDrugBankDosages);
                }
            }
            for (DrugBankDosages drugBankDosagesNewDrugBankDosages : drugBankDosagesNew) {
                if (!drugBankDosagesOld.contains(drugBankDosagesNewDrugBankDosages)) {
                    DrugBank oldDrugBankOfDrugBankDosagesNewDrugBankDosages = drugBankDosagesNewDrugBankDosages.getDrugBank();
                    drugBankDosagesNewDrugBankDosages.setDrugBank(drugBank);
                    drugBankDosagesNewDrugBankDosages = em.merge(drugBankDosagesNewDrugBankDosages);
                    if (oldDrugBankOfDrugBankDosagesNewDrugBankDosages != null && !oldDrugBankOfDrugBankDosagesNewDrugBankDosages.equals(drugBank)) {
                        oldDrugBankOfDrugBankDosagesNewDrugBankDosages.getDrugBankDosages().remove(drugBankDosagesNewDrugBankDosages);
                        em.merge(oldDrugBankOfDrugBankDosagesNewDrugBankDosages);
                    }
                }
            }
            for (DrugBankAffectedOrganisms drugBankAffectedOrganismsOldDrugBankAffectedOrganisms : drugBankAffectedOrganismsOld) {
                if (!drugBankAffectedOrganismsNew.contains(drugBankAffectedOrganismsOldDrugBankAffectedOrganisms)) {
                    drugBankAffectedOrganismsOldDrugBankAffectedOrganisms.setDrugBank(null);
                    drugBankAffectedOrganismsOldDrugBankAffectedOrganisms = em.merge(drugBankAffectedOrganismsOldDrugBankAffectedOrganisms);
                }
            }
            for (DrugBankAffectedOrganisms drugBankAffectedOrganismsNewDrugBankAffectedOrganisms : drugBankAffectedOrganismsNew) {
                if (!drugBankAffectedOrganismsOld.contains(drugBankAffectedOrganismsNewDrugBankAffectedOrganisms)) {
                    DrugBank oldDrugBankOfDrugBankAffectedOrganismsNewDrugBankAffectedOrganisms = drugBankAffectedOrganismsNewDrugBankAffectedOrganisms.getDrugBank();
                    drugBankAffectedOrganismsNewDrugBankAffectedOrganisms.setDrugBank(drugBank);
                    drugBankAffectedOrganismsNewDrugBankAffectedOrganisms = em.merge(drugBankAffectedOrganismsNewDrugBankAffectedOrganisms);
                    if (oldDrugBankOfDrugBankAffectedOrganismsNewDrugBankAffectedOrganisms != null && !oldDrugBankOfDrugBankAffectedOrganismsNewDrugBankAffectedOrganisms.equals(drugBank)) {
                        oldDrugBankOfDrugBankAffectedOrganismsNewDrugBankAffectedOrganisms.getDrugBankAffectedOrganisms().remove(drugBankAffectedOrganismsNewDrugBankAffectedOrganisms);
                        em.merge(oldDrugBankOfDrugBankAffectedOrganismsNewDrugBankAffectedOrganisms);
                    }
                }
            }
            for (DrugBankCategories drugBankCategoriesOldDrugBankCategories : drugBankCategoriesOld) {
                if (!drugBankCategoriesNew.contains(drugBankCategoriesOldDrugBankCategories)) {
                    drugBankCategoriesOldDrugBankCategories.getDrugBank().remove(drugBank);
                    drugBankCategoriesOldDrugBankCategories = em.merge(drugBankCategoriesOldDrugBankCategories);
                }
            }
            for (DrugBankCategories drugBankCategoriesNewDrugBankCategories : drugBankCategoriesNew) {
                if (!drugBankCategoriesOld.contains(drugBankCategoriesNewDrugBankCategories)) {
                    drugBankCategoriesNewDrugBankCategories.getDrugBank().add(drugBank);
                    drugBankCategoriesNewDrugBankCategories = em.merge(drugBankCategoriesNewDrugBankCategories);
                }
            }
            for (DrugBankPrices drugBankPricesOldDrugBankPrices : drugBankPricesOld) {
                if (!drugBankPricesNew.contains(drugBankPricesOldDrugBankPrices)) {
                    drugBankPricesOldDrugBankPrices.setDrugBank(null);
                    drugBankPricesOldDrugBankPrices = em.merge(drugBankPricesOldDrugBankPrices);
                }
            }
            for (DrugBankPrices drugBankPricesNewDrugBankPrices : drugBankPricesNew) {
                if (!drugBankPricesOld.contains(drugBankPricesNewDrugBankPrices)) {
                    DrugBank oldDrugBankOfDrugBankPricesNewDrugBankPrices = drugBankPricesNewDrugBankPrices.getDrugBank();
                    drugBankPricesNewDrugBankPrices.setDrugBank(drugBank);
                    drugBankPricesNewDrugBankPrices = em.merge(drugBankPricesNewDrugBankPrices);
                    if (oldDrugBankOfDrugBankPricesNewDrugBankPrices != null && !oldDrugBankOfDrugBankPricesNewDrugBankPrices.equals(drugBank)) {
                        oldDrugBankOfDrugBankPricesNewDrugBankPrices.getDrugBankPrices().remove(drugBankPricesNewDrugBankPrices);
                        em.merge(oldDrugBankOfDrugBankPricesNewDrugBankPrices);
                    }
                }
            }
            for (DrugBankManufacturers drugBankManufacturersOldDrugBankManufacturers : drugBankManufacturersOld) {
                if (!drugBankManufacturersNew.contains(drugBankManufacturersOldDrugBankManufacturers)) {
                    drugBankManufacturersOldDrugBankManufacturers.setDrugBank(null);
                    drugBankManufacturersOldDrugBankManufacturers = em.merge(drugBankManufacturersOldDrugBankManufacturers);
                }
            }
            for (DrugBankManufacturers drugBankManufacturersNewDrugBankManufacturers : drugBankManufacturersNew) {
                if (!drugBankManufacturersOld.contains(drugBankManufacturersNewDrugBankManufacturers)) {
                    DrugBank oldDrugBankOfDrugBankManufacturersNewDrugBankManufacturers = drugBankManufacturersNewDrugBankManufacturers.getDrugBank();
                    drugBankManufacturersNewDrugBankManufacturers.setDrugBank(drugBank);
                    drugBankManufacturersNewDrugBankManufacturers = em.merge(drugBankManufacturersNewDrugBankManufacturers);
                    if (oldDrugBankOfDrugBankManufacturersNewDrugBankManufacturers != null && !oldDrugBankOfDrugBankManufacturersNewDrugBankManufacturers.equals(drugBank)) {
                        oldDrugBankOfDrugBankManufacturersNewDrugBankManufacturers.getDrugBankManufacturers().remove(drugBankManufacturersNewDrugBankManufacturers);
                        em.merge(oldDrugBankOfDrugBankManufacturersNewDrugBankManufacturers);
                    }
                }
            }
            for (DrugBankPackagers drugBankPackagersOldDrugBankPackagers : drugBankPackagersOld) {
                if (!drugBankPackagersNew.contains(drugBankPackagersOldDrugBankPackagers)) {
                    drugBankPackagersOldDrugBankPackagers.setDrugBank(null);
                    drugBankPackagersOldDrugBankPackagers = em.merge(drugBankPackagersOldDrugBankPackagers);
                }
            }
            for (DrugBankPackagers drugBankPackagersNewDrugBankPackagers : drugBankPackagersNew) {
                if (!drugBankPackagersOld.contains(drugBankPackagersNewDrugBankPackagers)) {
                    DrugBank oldDrugBankOfDrugBankPackagersNewDrugBankPackagers = drugBankPackagersNewDrugBankPackagers.getDrugBank();
                    drugBankPackagersNewDrugBankPackagers.setDrugBank(drugBank);
                    drugBankPackagersNewDrugBankPackagers = em.merge(drugBankPackagersNewDrugBankPackagers);
                    if (oldDrugBankOfDrugBankPackagersNewDrugBankPackagers != null && !oldDrugBankOfDrugBankPackagersNewDrugBankPackagers.equals(drugBank)) {
                        oldDrugBankOfDrugBankPackagersNewDrugBankPackagers.getDrugBankPackagers().remove(drugBankPackagersNewDrugBankPackagers);
                        em.merge(oldDrugBankOfDrugBankPackagersNewDrugBankPackagers);
                    }
                }
            }
            for (DrugBankMixtures drugBankMixturesOldDrugBankMixtures : drugBankMixturesOld) {
                if (!drugBankMixturesNew.contains(drugBankMixturesOldDrugBankMixtures)) {
                    drugBankMixturesOldDrugBankMixtures.setDrugBank(null);
                    drugBankMixturesOldDrugBankMixtures = em.merge(drugBankMixturesOldDrugBankMixtures);
                }
            }
            for (DrugBankMixtures drugBankMixturesNewDrugBankMixtures : drugBankMixturesNew) {
                if (!drugBankMixturesOld.contains(drugBankMixturesNewDrugBankMixtures)) {
                    DrugBank oldDrugBankOfDrugBankMixturesNewDrugBankMixtures = drugBankMixturesNewDrugBankMixtures.getDrugBank();
                    drugBankMixturesNewDrugBankMixtures.setDrugBank(drugBank);
                    drugBankMixturesNewDrugBankMixtures = em.merge(drugBankMixturesNewDrugBankMixtures);
                    if (oldDrugBankOfDrugBankMixturesNewDrugBankMixtures != null && !oldDrugBankOfDrugBankMixturesNewDrugBankMixtures.equals(drugBank)) {
                        oldDrugBankOfDrugBankMixturesNewDrugBankMixtures.getDrugBankMixtures().remove(drugBankMixturesNewDrugBankMixtures);
                        em.merge(oldDrugBankOfDrugBankMixturesNewDrugBankMixtures);
                    }
                }
            }
            for (DrugBankBrands drugBankBrandsOldDrugBankBrands : drugBankBrandsOld) {
                if (!drugBankBrandsNew.contains(drugBankBrandsOldDrugBankBrands)) {
                    drugBankBrandsOldDrugBankBrands.setDrugBank(null);
                    drugBankBrandsOldDrugBankBrands = em.merge(drugBankBrandsOldDrugBankBrands);
                }
            }
            for (DrugBankBrands drugBankBrandsNewDrugBankBrands : drugBankBrandsNew) {
                if (!drugBankBrandsOld.contains(drugBankBrandsNewDrugBankBrands)) {
                    DrugBank oldDrugBankOfDrugBankBrandsNewDrugBankBrands = drugBankBrandsNewDrugBankBrands.getDrugBank();
                    drugBankBrandsNewDrugBankBrands.setDrugBank(drugBank);
                    drugBankBrandsNewDrugBankBrands = em.merge(drugBankBrandsNewDrugBankBrands);
                    if (oldDrugBankOfDrugBankBrandsNewDrugBankBrands != null && !oldDrugBankOfDrugBankBrandsNewDrugBankBrands.equals(drugBank)) {
                        oldDrugBankOfDrugBankBrandsNewDrugBankBrands.getDrugBankBrands().remove(drugBankBrandsNewDrugBankBrands);
                        em.merge(oldDrugBankOfDrugBankBrandsNewDrugBankBrands);
                    }
                }
            }
            for (DrugBankSynonyms drugBankSynonymsOldDrugBankSynonyms : drugBankSynonymsOld) {
                if (!drugBankSynonymsNew.contains(drugBankSynonymsOldDrugBankSynonyms)) {
                    drugBankSynonymsOldDrugBankSynonyms.setDrugBank(null);
                    drugBankSynonymsOldDrugBankSynonyms = em.merge(drugBankSynonymsOldDrugBankSynonyms);
                }
            }
            for (DrugBankSynonyms drugBankSynonymsNewDrugBankSynonyms : drugBankSynonymsNew) {
                if (!drugBankSynonymsOld.contains(drugBankSynonymsNewDrugBankSynonyms)) {
                    DrugBank oldDrugBankOfDrugBankSynonymsNewDrugBankSynonyms = drugBankSynonymsNewDrugBankSynonyms.getDrugBank();
                    drugBankSynonymsNewDrugBankSynonyms.setDrugBank(drugBank);
                    drugBankSynonymsNewDrugBankSynonyms = em.merge(drugBankSynonymsNewDrugBankSynonyms);
                    if (oldDrugBankOfDrugBankSynonymsNewDrugBankSynonyms != null && !oldDrugBankOfDrugBankSynonymsNewDrugBankSynonyms.equals(drugBank)) {
                        oldDrugBankOfDrugBankSynonymsNewDrugBankSynonyms.getDrugBankSynonyms().remove(drugBankSynonymsNewDrugBankSynonyms);
                        em.merge(oldDrugBankOfDrugBankSynonymsNewDrugBankSynonyms);
                    }
                }
            }
            for (DrugBankTaxonomySubstructures drugBankTaxonomySubstructuresOldDrugBankTaxonomySubstructures : drugBankTaxonomySubstructuresOld) {
                if (!drugBankTaxonomySubstructuresNew.contains(drugBankTaxonomySubstructuresOldDrugBankTaxonomySubstructures)) {
                    drugBankTaxonomySubstructuresOldDrugBankTaxonomySubstructures.setDrugBank(null);
                    drugBankTaxonomySubstructuresOldDrugBankTaxonomySubstructures = em.merge(drugBankTaxonomySubstructuresOldDrugBankTaxonomySubstructures);
                }
            }
            for (DrugBankTaxonomySubstructures drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures : drugBankTaxonomySubstructuresNew) {
                if (!drugBankTaxonomySubstructuresOld.contains(drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures)) {
                    DrugBank oldDrugBankOfDrugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures = drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures.getDrugBank();
                    drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures.setDrugBank(drugBank);
                    drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures = em.merge(drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures);
                    if (oldDrugBankOfDrugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures != null && !oldDrugBankOfDrugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures.equals(drugBank)) {
                        oldDrugBankOfDrugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures.getDrugBankTaxonomySubstructures().remove(drugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures);
                        em.merge(oldDrugBankOfDrugBankTaxonomySubstructuresNewDrugBankTaxonomySubstructures);
                    }
                }
            }
            for (DrugBankTaxonomy drugBankTaxonomyOldDrugBankTaxonomy : drugBankTaxonomyOld) {
                if (!drugBankTaxonomyNew.contains(drugBankTaxonomyOldDrugBankTaxonomy)) {
                    drugBankTaxonomyOldDrugBankTaxonomy.setDrugBank(null);
                    drugBankTaxonomyOldDrugBankTaxonomy = em.merge(drugBankTaxonomyOldDrugBankTaxonomy);
                }
            }
            for (DrugBankTaxonomy drugBankTaxonomyNewDrugBankTaxonomy : drugBankTaxonomyNew) {
                if (!drugBankTaxonomyOld.contains(drugBankTaxonomyNewDrugBankTaxonomy)) {
                    DrugBank oldDrugBankOfDrugBankTaxonomyNewDrugBankTaxonomy = drugBankTaxonomyNewDrugBankTaxonomy.getDrugBank();
                    drugBankTaxonomyNewDrugBankTaxonomy.setDrugBank(drugBank);
                    drugBankTaxonomyNewDrugBankTaxonomy = em.merge(drugBankTaxonomyNewDrugBankTaxonomy);
                    if (oldDrugBankOfDrugBankTaxonomyNewDrugBankTaxonomy != null && !oldDrugBankOfDrugBankTaxonomyNewDrugBankTaxonomy.equals(drugBank)) {
                        oldDrugBankOfDrugBankTaxonomyNewDrugBankTaxonomy.getDrugBankTaxonomy().remove(drugBankTaxonomyNewDrugBankTaxonomy);
                        em.merge(oldDrugBankOfDrugBankTaxonomyNewDrugBankTaxonomy);
                    }
                }
            }
            for (DrugBankGroup drugBankGroupOldDrugBankGroup : drugBankGroupOld) {
                if (!drugBankGroupNew.contains(drugBankGroupOldDrugBankGroup)) {
                    drugBankGroupOldDrugBankGroup.setDrugBank(null);
                    drugBankGroupOldDrugBankGroup = em.merge(drugBankGroupOldDrugBankGroup);
                }
            }
            for (DrugBankGroup drugBankGroupNewDrugBankGroup : drugBankGroupNew) {
                if (!drugBankGroupOld.contains(drugBankGroupNewDrugBankGroup)) {
                    DrugBank oldDrugBankOfDrugBankGroupNewDrugBankGroup = drugBankGroupNewDrugBankGroup.getDrugBank();
                    drugBankGroupNewDrugBankGroup.setDrugBank(drugBank);
                    drugBankGroupNewDrugBankGroup = em.merge(drugBankGroupNewDrugBankGroup);
                    if (oldDrugBankOfDrugBankGroupNewDrugBankGroup != null && !oldDrugBankOfDrugBankGroupNewDrugBankGroup.equals(drugBank)) {
                        oldDrugBankOfDrugBankGroupNewDrugBankGroup.getDrugBankGroup().remove(drugBankGroupNewDrugBankGroup);
                        em.merge(oldDrugBankOfDrugBankGroupNewDrugBankGroup);
                    }
                }
            }
            for (DrugBankSecondAccessionNumbers drugBankSecondAccessionNumbersOldDrugBankSecondAccessionNumbers : drugBankSecondAccessionNumbersOld) {
                if (!drugBankSecondAccessionNumbersNew.contains(drugBankSecondAccessionNumbersOldDrugBankSecondAccessionNumbers)) {
                    drugBankSecondAccessionNumbersOldDrugBankSecondAccessionNumbers.setDrugBank(null);
                    drugBankSecondAccessionNumbersOldDrugBankSecondAccessionNumbers = em.merge(drugBankSecondAccessionNumbersOldDrugBankSecondAccessionNumbers);
                }
            }
            for (DrugBankSecondAccessionNumbers drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers : drugBankSecondAccessionNumbersNew) {
                if (!drugBankSecondAccessionNumbersOld.contains(drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers)) {
                    DrugBank oldDrugBankOfDrugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers = drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers.getDrugBank();
                    drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers.setDrugBank(drugBank);
                    drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers = em.merge(drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers);
                    if (oldDrugBankOfDrugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers != null && !oldDrugBankOfDrugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers.equals(drugBank)) {
                        oldDrugBankOfDrugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers.getDrugBankSecondAccessionNumbers().remove(drugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers);
                        em.merge(oldDrugBankOfDrugBankSecondAccessionNumbersNewDrugBankSecondAccessionNumbers);
                    }
                }
            }
            for (Protein proteinAsCarriersOldProtein : proteinAsCarriersOld) {
                if (!proteinAsCarriersNew.contains(proteinAsCarriersOldProtein)) {
                    proteinAsCarriersOldProtein.getDrugBankAsCarriers().remove(drugBank);
                    proteinAsCarriersOldProtein = em.merge(proteinAsCarriersOldProtein);
                }
            }
            for (Protein proteinAsCarriersNewProtein : proteinAsCarriersNew) {
                if (!proteinAsCarriersOld.contains(proteinAsCarriersNewProtein)) {
                    proteinAsCarriersNewProtein.getDrugBankAsCarriers().add(drugBank);
                    proteinAsCarriersNewProtein = em.merge(proteinAsCarriersNewProtein);
                }
            }
            for (Protein proteinAsEnzymeOldProtein : proteinAsEnzymeOld) {
                if (!proteinAsEnzymeNew.contains(proteinAsEnzymeOldProtein)) {
                    proteinAsEnzymeOldProtein.getDrugBankAsCarriers().remove(drugBank);
                    proteinAsEnzymeOldProtein = em.merge(proteinAsEnzymeOldProtein);
                }
            }
            for (Protein proteinAsEnzymeNewProtein : proteinAsEnzymeNew) {
                if (!proteinAsEnzymeOld.contains(proteinAsEnzymeNewProtein)) {
                    proteinAsEnzymeNewProtein.getDrugBankAsCarriers().add(drugBank);
                    proteinAsEnzymeNewProtein = em.merge(proteinAsEnzymeNewProtein);
                }
            }
            for (Protein proteinAsTransportersOldProtein : proteinAsTransportersOld) {
                if (!proteinAsTransportersNew.contains(proteinAsTransportersOldProtein)) {
                    proteinAsTransportersOldProtein.getDrugBankAsCarriers().remove(drugBank);
                    proteinAsTransportersOldProtein = em.merge(proteinAsTransportersOldProtein);
                }
            }
            for (Protein proteinAsTransportersNewProtein : proteinAsTransportersNew) {
                if (!proteinAsTransportersOld.contains(proteinAsTransportersNewProtein)) {
                    proteinAsTransportersNewProtein.getDrugBankAsCarriers().add(drugBank);
                    proteinAsTransportersNewProtein = em.merge(proteinAsTransportersNewProtein);
                }
            }
            for (Protein proteinOldProtein : proteinOld) {
                if (!proteinNew.contains(proteinOldProtein)) {
                    proteinOldProtein.getDrugBankAsCarriers().remove(drugBank);
                    proteinOldProtein = em.merge(proteinOldProtein);
                }
            }
            for (Protein proteinNewProtein : proteinNew) {
                if (!proteinOld.contains(proteinNewProtein)) {
                    proteinNewProtein.getDrugBankAsCarriers().add(drugBank);
                    proteinNewProtein = em.merge(proteinNewProtein);
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
            Set<KEGGCompound> kEGGCompounds = drugBank.getkEGGCompounds();
            for (KEGGCompound kEGGCompoundsKEGGCompound : kEGGCompounds) {
                kEGGCompoundsKEGGCompound.getDrugBanks().remove(drugBank);
                kEGGCompoundsKEGGCompound = em.merge(kEGGCompoundsKEGGCompound);
            }
            Set<DrugBankGeneralRef> drugBankGeneralRef = drugBank.getDrugBankGeneralRef();
            for (DrugBankGeneralRef drugBankGeneralRefDrugBankGeneralRef : drugBankGeneralRef) {
                drugBankGeneralRefDrugBankGeneralRef.setDrugBank(null);
                drugBankGeneralRefDrugBankGeneralRef = em.merge(drugBankGeneralRefDrugBankGeneralRef);
            }
            Set<DrugBankCarriers> drugBankCarriers = drugBank.getDrugBankCarriers();
            for (DrugBankCarriers drugBankCarriersDrugBankCarriers : drugBankCarriers) {
                drugBankCarriersDrugBankCarriers.setDrugBank(null);
                drugBankCarriersDrugBankCarriers = em.merge(drugBankCarriersDrugBankCarriers);
            }
            Set<DrugBankTransporters> drugBankTransporters = drugBank.getDrugBankTransporters();
            for (DrugBankTransporters drugBankTransportersDrugBankTransporters : drugBankTransporters) {
                drugBankTransportersDrugBankTransporters.setDrugBank(null);
                drugBankTransportersDrugBankTransporters = em.merge(drugBankTransportersDrugBankTransporters);
            }
            Set<DrugBankEnzymes> drugBankEnzymes = drugBank.getDrugBankEnzymes();
            for (DrugBankEnzymes drugBankEnzymesDrugBankEnzymes : drugBankEnzymes) {
                drugBankEnzymesDrugBankEnzymes.setDrugBank(null);
                drugBankEnzymesDrugBankEnzymes = em.merge(drugBankEnzymesDrugBankEnzymes);
            }
            Set<DrugBankTargets> drugBankTargets = drugBank.getDrugBankTargets();
            for (DrugBankTargets drugBankTargetsDrugBankTargets : drugBankTargets) {
                drugBankTargetsDrugBankTargets.setDrugBank(null);
                drugBankTargetsDrugBankTargets = em.merge(drugBankTargetsDrugBankTargets);
            }
            Set<DrugBankExternalLinks> drugBankExternalLinks = drugBank.getDrugBankExternalLinks();
            for (DrugBankExternalLinks drugBankExternalLinksDrugBankExternalLinks : drugBankExternalLinks) {
                drugBankExternalLinksDrugBankExternalLinks.setDrugBank(null);
                drugBankExternalLinksDrugBankExternalLinks = em.merge(drugBankExternalLinksDrugBankExternalLinks);
            }
            Set<DrugBankExternalIdentifiers> drugBankExternalIdentifiers = drugBank.getDrugBankExternalIdentifiers();
            for (DrugBankExternalIdentifiers drugBankExternalIdentifiersDrugBankExternalIdentifiers : drugBankExternalIdentifiers) {
                drugBankExternalIdentifiersDrugBankExternalIdentifiers.setDrugBank(null);
                drugBankExternalIdentifiersDrugBankExternalIdentifiers = em.merge(drugBankExternalIdentifiersDrugBankExternalIdentifiers);
            }
            Set<DrugBankExperimentalProperties> drugBankExperimentalProperties = drugBank.getDrugBankExperimentalProperties();
            for (DrugBankExperimentalProperties drugBankExperimentalPropertiesDrugBankExperimentalProperties : drugBankExperimentalProperties) {
                drugBankExperimentalPropertiesDrugBankExperimentalProperties.setDrugBank(null);
                drugBankExperimentalPropertiesDrugBankExperimentalProperties = em.merge(drugBankExperimentalPropertiesDrugBankExperimentalProperties);
            }
            Set<DrugBankCalculatedProperties> drugBankCalculatedProperties = drugBank.getDrugBankCalculatedProperties();
            for (DrugBankCalculatedProperties drugBankCalculatedPropertiesDrugBankCalculatedProperties : drugBankCalculatedProperties) {
                drugBankCalculatedPropertiesDrugBankCalculatedProperties.setDrugBank(null);
                drugBankCalculatedPropertiesDrugBankCalculatedProperties = em.merge(drugBankCalculatedPropertiesDrugBankCalculatedProperties);
            }
            Set<DrugBankProteinSequences> drugBankProteinSequences = drugBank.getDrugBankProteinSequences();
            for (DrugBankProteinSequences drugBankProteinSequencesDrugBankProteinSequences : drugBankProteinSequences) {
                drugBankProteinSequencesDrugBankProteinSequences.setDrugBank(null);
                drugBankProteinSequencesDrugBankProteinSequences = em.merge(drugBankProteinSequencesDrugBankProteinSequences);
            }
            Set<DrugBankFoodInteractions> drugBankFoodInteractions = drugBank.getDrugBankFoodInteractions();
            for (DrugBankFoodInteractions drugBankFoodInteractionsDrugBankFoodInteractions : drugBankFoodInteractions) {
                drugBankFoodInteractionsDrugBankFoodInteractions.setDrugBank(null);
                drugBankFoodInteractionsDrugBankFoodInteractions = em.merge(drugBankFoodInteractionsDrugBankFoodInteractions);
            }
            Set<DrugBankPatents> drugBankPatents = drugBank.getDrugBankPatents();
            for (DrugBankPatents drugBankPatentsDrugBankPatents : drugBankPatents) {
                drugBankPatentsDrugBankPatents.getDrugBank().remove(drugBank);
                drugBankPatentsDrugBankPatents = em.merge(drugBankPatentsDrugBankPatents);
            }
            Set<DrugBankAHFSCodes> drugBankAHFSCodes = drugBank.getDrugBankAHFSCodes();
            for (DrugBankAHFSCodes drugBankAHFSCodesDrugBankAHFSCodes : drugBankAHFSCodes) {
                drugBankAHFSCodesDrugBankAHFSCodes.setDrugBank(null);
                drugBankAHFSCodesDrugBankAHFSCodes = em.merge(drugBankAHFSCodesDrugBankAHFSCodes);
            }
            Set<DrugBankATCCodes> drugBankATCCodes = drugBank.getDrugBankATCCodes();
            for (DrugBankATCCodes drugBankATCCodesDrugBankATCCodes : drugBankATCCodes) {
                drugBankATCCodesDrugBankATCCodes.setDrugBank(null);
                drugBankATCCodesDrugBankATCCodes = em.merge(drugBankATCCodesDrugBankATCCodes);
            }
            Set<DrugBankDosages> drugBankDosages = drugBank.getDrugBankDosages();
            for (DrugBankDosages drugBankDosagesDrugBankDosages : drugBankDosages) {
                drugBankDosagesDrugBankDosages.setDrugBank(null);
                drugBankDosagesDrugBankDosages = em.merge(drugBankDosagesDrugBankDosages);
            }
            Set<DrugBankAffectedOrganisms> drugBankAffectedOrganisms = drugBank.getDrugBankAffectedOrganisms();
            for (DrugBankAffectedOrganisms drugBankAffectedOrganismsDrugBankAffectedOrganisms : drugBankAffectedOrganisms) {
                drugBankAffectedOrganismsDrugBankAffectedOrganisms.setDrugBank(null);
                drugBankAffectedOrganismsDrugBankAffectedOrganisms = em.merge(drugBankAffectedOrganismsDrugBankAffectedOrganisms);
            }
            Set<DrugBankCategories> drugBankCategories = drugBank.getDrugBankCategories();
            for (DrugBankCategories drugBankCategoriesDrugBankCategories : drugBankCategories) {
                drugBankCategoriesDrugBankCategories.getDrugBank().remove(drugBank);
                drugBankCategoriesDrugBankCategories = em.merge(drugBankCategoriesDrugBankCategories);
            }
            Set<DrugBankPrices> drugBankPrices = drugBank.getDrugBankPrices();
            for (DrugBankPrices drugBankPricesDrugBankPrices : drugBankPrices) {
                drugBankPricesDrugBankPrices.setDrugBank(null);
                drugBankPricesDrugBankPrices = em.merge(drugBankPricesDrugBankPrices);
            }
            Set<DrugBankManufacturers> drugBankManufacturers = drugBank.getDrugBankManufacturers();
            for (DrugBankManufacturers drugBankManufacturersDrugBankManufacturers : drugBankManufacturers) {
                drugBankManufacturersDrugBankManufacturers.setDrugBank(null);
                drugBankManufacturersDrugBankManufacturers = em.merge(drugBankManufacturersDrugBankManufacturers);
            }
            Set<DrugBankPackagers> drugBankPackagers = drugBank.getDrugBankPackagers();
            for (DrugBankPackagers drugBankPackagersDrugBankPackagers : drugBankPackagers) {
                drugBankPackagersDrugBankPackagers.setDrugBank(null);
                drugBankPackagersDrugBankPackagers = em.merge(drugBankPackagersDrugBankPackagers);
            }
            Set<DrugBankMixtures> drugBankMixtures = drugBank.getDrugBankMixtures();
            for (DrugBankMixtures drugBankMixturesDrugBankMixtures : drugBankMixtures) {
                drugBankMixturesDrugBankMixtures.setDrugBank(null);
                drugBankMixturesDrugBankMixtures = em.merge(drugBankMixturesDrugBankMixtures);
            }
            Set<DrugBankBrands> drugBankBrands = drugBank.getDrugBankBrands();
            for (DrugBankBrands drugBankBrandsDrugBankBrands : drugBankBrands) {
                drugBankBrandsDrugBankBrands.setDrugBank(null);
                drugBankBrandsDrugBankBrands = em.merge(drugBankBrandsDrugBankBrands);
            }
            Set<DrugBankSynonyms> drugBankSynonyms = drugBank.getDrugBankSynonyms();
            for (DrugBankSynonyms drugBankSynonymsDrugBankSynonyms : drugBankSynonyms) {
                drugBankSynonymsDrugBankSynonyms.setDrugBank(null);
                drugBankSynonymsDrugBankSynonyms = em.merge(drugBankSynonymsDrugBankSynonyms);
            }
            Set<DrugBankTaxonomySubstructures> drugBankTaxonomySubstructures = drugBank.getDrugBankTaxonomySubstructures();
            for (DrugBankTaxonomySubstructures drugBankTaxonomySubstructuresDrugBankTaxonomySubstructures : drugBankTaxonomySubstructures) {
                drugBankTaxonomySubstructuresDrugBankTaxonomySubstructures.setDrugBank(null);
                drugBankTaxonomySubstructuresDrugBankTaxonomySubstructures = em.merge(drugBankTaxonomySubstructuresDrugBankTaxonomySubstructures);
            }
            Set<DrugBankTaxonomy> drugBankTaxonomy = drugBank.getDrugBankTaxonomy();
            for (DrugBankTaxonomy drugBankTaxonomyDrugBankTaxonomy : drugBankTaxonomy) {
                drugBankTaxonomyDrugBankTaxonomy.setDrugBank(null);
                drugBankTaxonomyDrugBankTaxonomy = em.merge(drugBankTaxonomyDrugBankTaxonomy);
            }
            Set<DrugBankGroup> drugBankGroup = drugBank.getDrugBankGroup();
            for (DrugBankGroup drugBankGroupDrugBankGroup : drugBankGroup) {
                drugBankGroupDrugBankGroup.setDrugBank(null);
                drugBankGroupDrugBankGroup = em.merge(drugBankGroupDrugBankGroup);
            }
            Set<DrugBankSecondAccessionNumbers> drugBankSecondAccessionNumbers = drugBank.getDrugBankSecondAccessionNumbers();
            for (DrugBankSecondAccessionNumbers drugBankSecondAccessionNumbersDrugBankSecondAccessionNumbers : drugBankSecondAccessionNumbers) {
                drugBankSecondAccessionNumbersDrugBankSecondAccessionNumbers.setDrugBank(null);
                drugBankSecondAccessionNumbersDrugBankSecondAccessionNumbers = em.merge(drugBankSecondAccessionNumbersDrugBankSecondAccessionNumbers);
            }
            Set<Protein> proteinAsCarriers = drugBank.getProteinAsCarriers();
            for (Protein proteinAsCarriersProtein : proteinAsCarriers) {
                proteinAsCarriersProtein.getDrugBankAsCarriers().remove(drugBank);
                proteinAsCarriersProtein = em.merge(proteinAsCarriersProtein);
            }
            Set<Protein> proteinAsEnzyme = drugBank.getProteinAsEnzyme();
            for (Protein proteinAsEnzymeProtein : proteinAsEnzyme) {
                proteinAsEnzymeProtein.getDrugBankAsCarriers().remove(drugBank);
                proteinAsEnzymeProtein = em.merge(proteinAsEnzymeProtein);
            }
            Set<Protein> proteinAsTransporters = drugBank.getProteinAsTransporters();
            for (Protein proteinAsTransportersProtein : proteinAsTransporters) {
                proteinAsTransportersProtein.getDrugBankAsCarriers().remove(drugBank);
                proteinAsTransportersProtein = em.merge(proteinAsTransportersProtein);
            }
            Set<Protein> protein = drugBank.getProtein();
            for (Protein proteinProtein : protein) {
                proteinProtein.getDrugBankAsCarriers().remove(drugBank);
                proteinProtein = em.merge(proteinProtein);
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
