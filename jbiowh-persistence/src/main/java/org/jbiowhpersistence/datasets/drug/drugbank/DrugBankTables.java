package org.jbiowhpersistence.datasets.drug.drugbank;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the DrugBank Tables
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Sep 9, 2011
 */
public class DrugBankTables {

    private static DrugBankTables drugBankTables;
    private static List<String> tables;
    public final String DRUGBANK = "DrugBank";
    public static final String DRUGBANK_HAS_DRUGBANKCATEGORIES = "DrugBank_has_DrugBankCategories";
    public static final String DRUGBANK_HAS_DRUGBANKPATENTS = "DrugBank_has_DrugBankPatents";
    public final String DRUGBANKAFFECTEDORGANISMS = "DrugBankAffectedOrganisms";
    public final String DRUGBANKAHFSCODES = "DrugBankAHFSCodes";
    public final String DRUGBANKATCCODES = "DrugBankATCCodes";
    public final String DRUGBANKBRANDS = "DrugBankBrands";
    public final String DRUGBANKCALCULATEDPROPERTIES = "DrugBankCalculatedProperties";
    public final String DRUGBANKCARRIERS = "DrugBankCarriers";
    public final String DRUGBANKCARRIERSACTIONS = "DrugBankCarriersActions";
    public final String DRUGBANKCARRIERSREF = "DrugBankCarriersRef";
    public final String DRUGBANKCATEGORIES = "DrugBankCategories";
    public final String DRUGBANKCATEGORIESTEMP = "DrugBankCategoriesTemp";
    public final String DRUGBANKDOSAGES = "DrugBankDosages";
    public final String DRUGBANKDRUGINTERACTIONS = "DrugBankDrugInteractions";
    public final String DRUGBANKDRUGINTERACTIONSTEMP = "DrugBankDrugInteractionsTemp";
    public final String DRUGBANKENZYMES = "DrugBankEnzymes";
    public final String DRUGBANKENZYMESACTIONS = "DrugBankEnzymesActions";
    public final String DRUGBANKENZYMESREF = "DrugBankEnzymesRef";
    public final String DRUGBANKEXPERIMENTALPROPERTIES = "DrugBankExperimentalProperties";
    public final String DRUGBANKEXTERNALIDENTIFIERS = "DrugBankExternalIdentifiers";
    public final String DRUGBANKEXTERNALLINKS = "DrugBankExternalLinks";
    public final String DRUGBANKFOODINTERACTIONS = "DrugBankFoodInteractions";
    public final String DRUGBANKGENERALREF = "DrugBankGeneralRef";
    public final String DRUGBANKGROUP = "DrugBankGroup";
    public final String DRUGBANKMANUFACTURERS = "DrugBankManufacturers";
    public final String DRUGBANKMIXTURES = "DrugBankMixtures";
    public final String DRUGBANKPACKAGERS = "DrugBankPackagers";
    public final String DRUGBANKPARTNERS = "DrugBankPartners";
    public final String DRUGBANKPARTNEREXTERNALIDENTIFIERS = "DrugBankPartnerExternalIdentifiers";
    public final String DRUGBANKPARTNERPFAM = "DrugBankPartnerPFam";
    public final String DRUGBANKPARTNERREF = "DrugBankPartnerRef";
    public final String DRUGBANKPARTNERSYNONYMS = "DrugBankPartnerSynonyms";
    public final String DRUGBANKPATENTS = "DrugBankPatents";
    public final String DRUGBANKPATENTSTEMP = "DrugBankPatentsTemp";
    public final String DRUGBANKPRICES = "DrugBankPrices";
    public final String DRUGBANKPROTEINSEQUENCES = "DrugBankProteinSequences";
    public final String DRUGBANKSECONDACCESSIONNUMBERS = "DrugBankSecondAccessionNumbers";
    public final String DRUGBANKSYNONYMS = "DrugBankSynonyms";
    public final String DRUGBANKTARGETS = "DrugBankTargets";
    public final String DRUGBANKTARGETSACTIONS = "DrugBankTargetsActions";
    public final String DRUGBANKTARGETSREF = "DrugBankTargetsRef";
    public final String DRUGBANKTAXONOMYSUBSTRUCTURES = "DrugBankTaxonomySubstructures";
    public final String DRUGBANKTAXONOMY = "DrugBankTaxonomy";
    public final String DRUGBANKTRANSPORTERS = "DrugBankTransporters";
    public final String DRUGBANKTRANSPORTERSACTIONS = "DrugBankTransportersActions";
    public final String DRUGBANKTRANSPORTERSREF = "DrugBankTransportersRef";

    private DrugBankTables() {
    }

    /**
     * Return the DrugBank tables instance
     *
     * @return the DrugBank tables instance
     */
    public static synchronized DrugBankTables getInstance() {
        if (drugBankTables == null) {
            drugBankTables = new DrugBankTables();
        }
        return drugBankTables;
    }

    /**
     * Return the Drugbank's tables names in a list
     *
     * @return the Drugbank's tables names in a list
     */
    public List<String> getTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(DRUGBANK);
            tables.add(DRUGBANK_HAS_DRUGBANKCATEGORIES);
            tables.add(DRUGBANK_HAS_DRUGBANKPATENTS);
            tables.add(DRUGBANKAFFECTEDORGANISMS);
            tables.add(DRUGBANKAHFSCODES);
            tables.add(DRUGBANKATCCODES);
            tables.add(DRUGBANKBRANDS);
            tables.add(DRUGBANKCALCULATEDPROPERTIES);
            tables.add(DRUGBANKCARRIERS);
            tables.add(DRUGBANKCARRIERSACTIONS);
            tables.add(DRUGBANKCARRIERSREF);
            tables.add(DRUGBANKCATEGORIES);
            tables.add(DRUGBANKCATEGORIESTEMP);
            tables.add(DRUGBANKDOSAGES);
            tables.add(DRUGBANKDRUGINTERACTIONS);
            tables.add(DRUGBANKDRUGINTERACTIONSTEMP);
            tables.add(DRUGBANKENZYMES);
            tables.add(DRUGBANKENZYMESACTIONS);
            tables.add(DRUGBANKENZYMESREF);
            tables.add(DRUGBANKEXPERIMENTALPROPERTIES);
            tables.add(DRUGBANKEXTERNALIDENTIFIERS);
            tables.add(DRUGBANKEXTERNALLINKS);
            tables.add(DRUGBANKFOODINTERACTIONS);
            tables.add(DRUGBANKGENERALREF);
            tables.add(DRUGBANKGROUP);
            tables.add(DRUGBANKMANUFACTURERS);
            tables.add(DRUGBANKMIXTURES);
            tables.add(DRUGBANKPACKAGERS);
            tables.add(DRUGBANKPARTNERS);
            tables.add(DRUGBANKPARTNEREXTERNALIDENTIFIERS);
            tables.add(DRUGBANKPARTNERPFAM);
            tables.add(DRUGBANKPARTNERREF);
            tables.add(DRUGBANKPARTNERSYNONYMS);
            tables.add(DRUGBANKPATENTS);
            tables.add(DRUGBANKPATENTSTEMP);
            tables.add(DRUGBANKPRICES);
            tables.add(DRUGBANKPROTEINSEQUENCES);
            tables.add(DRUGBANKSECONDACCESSIONNUMBERS);
            tables.add(DRUGBANKSYNONYMS);
            tables.add(DRUGBANKTARGETS);
            tables.add(DRUGBANKTARGETSACTIONS);
            tables.add(DRUGBANKTARGETSREF);
            tables.add(DRUGBANKTAXONOMY);
            tables.add(DRUGBANKTAXONOMYSUBSTRUCTURES);
            tables.add(DRUGBANKTRANSPORTERS);
            tables.add(DRUGBANKTRANSPORTERSACTIONS);
            tables.add(DRUGBANKTRANSPORTERSREF);
        }

        return tables;
    }
}
