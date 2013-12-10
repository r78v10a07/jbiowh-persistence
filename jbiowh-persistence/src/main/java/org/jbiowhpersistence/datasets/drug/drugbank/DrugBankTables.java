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
    public static final String DRUGBANK_HAS_DRUGBANKCATEGORIES = "DrugBank_has_DrugBankCategory";
    public static final String DRUGBANK_HAS_DRUGBANKPATENTS = "DrugBank_has_DrugBankPatent";
    public final String DRUGBANKAFFECTEDORGANISMS = "DrugBankAffectedOrganism";
    public final String DRUGBANKAHFSCODES = "DrugBankAHFSCode";
    public final String DRUGBANKATCCODES = "DrugBankATCCode";
    public final String DRUGBANKBRANDS = "DrugBankBrand";
    public final String DRUGBANKCALCULATEDPROPERTIES = "DrugBankCalculatedProperty";
    public final String DRUGBANKCARRIERS = "DrugBankCarrier";
    public final String DRUGBANKCARRIERSACTIONS = "DrugBankCarrierAction";
    public final String DRUGBANKCARRIERSREF = "DrugBankCarrierRef";
    public final String DRUGBANKCATEGORIES = "DrugBankCategory";
    public final String DRUGBANKCATEGORIESTEMP = "DrugBankCategoriesTemp";
    public final String DRUGBANKDOSAGES = "DrugBankDosage";
    public final String DRUGBANKDRUGINTERACTIONS = "DrugBankDrugInteraction";
    public final String DRUGBANKDRUGINTERACTIONSTEMP = "DrugBankDrugInteractionsTemp";
    public final String DRUGBANKENZYMES = "DrugBankEnzyme";
    public final String DRUGBANKENZYMESACTIONS = "DrugBankEnzymeAction";
    public final String DRUGBANKENZYMESREF = "DrugBankEnzymeRef";
    public final String DRUGBANKEXPERIMENTALPROPERTIES = "DrugBankExperimentalProperty";
    public final String DRUGBANKEXTERNALIDENTIFIERS = "DrugBankExternalIdentifier";
    public final String DRUGBANKEXTERNALLINKS = "DrugBankExternalLink";
    public final String DRUGBANKFOODINTERACTIONS = "DrugBankFoodInteraction";
    public final String DRUGBANKGENERALREF = "DrugBankGeneralRef";
    public final String DRUGBANKGROUP = "DrugBankGroup";
    public final String DRUGBANKMANUFACTURERS = "DrugBankManufacturer";
    public final String DRUGBANKMIXTURES = "DrugBankMixture";
    public final String DRUGBANKPACKAGERS = "DrugBankPackager";
    public final String DRUGBANKPARTNERS = "DrugBankPartners";
    public final String DRUGBANKPARTNEREXTERNALIDENTIFIERS = "DrugBankPartnerExternalIdentifiers";
    public final String DRUGBANKPARTNERPFAM = "DrugBankPartnerPFam";
    public final String DRUGBANKPARTNERREF = "DrugBankPartnerRef";
    public final String DRUGBANKPARTNERSYNONYMS = "DrugBankPartnerSynonyms";
    public final String DRUGBANKPATENTS = "DrugBankPatent";
    public final String DRUGBANKPATENTSTEMP = "DrugBankPatentsTemp";
    public final String DRUGBANKPRICES = "DrugBankPrice";
    public final String DRUGBANKPROTEINSEQUENCES = "DrugBankProteinSequence";
    public final String DRUGBANKSECONDACCESSIONNUMBERS = "DrugBankSecondAccessionNumber";
    public final String DRUGBANKSYNONYMS = "DrugBankSynonym";
    public final String DRUGBANKTARGETS = "DrugBankTarget";
    public final String DRUGBANKTARGETSACTIONS = "DrugBankTargetAction";
    public final String DRUGBANKTARGETSREF = "DrugBankTargetRef";
    public final String DRUGBANKTAXONOMYSUBSTRUCTURES = "DrugBankTaxonomySubstructure";
    public final String DRUGBANKTAXONOMY = "DrugBankTaxonomy";
    public final String DRUGBANKTRANSPORTERS = "DrugBankTransporter";
    public final String DRUGBANKTRANSPORTERSACTIONS = "DrugBankTransporterAction";
    public final String DRUGBANKTRANSPORTERSREF = "DrugBankTransporterRef";

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
            tables = new ArrayList();

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
