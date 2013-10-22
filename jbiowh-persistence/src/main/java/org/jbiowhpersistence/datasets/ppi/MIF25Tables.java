package org.jbiowhpersistence.datasets.ppi;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the MIF25 Tables
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 15, 2011
 */
public class MIF25Tables {

    public final String MIFBIOSOURCETYPECELLTYPE = "MIFBioSourceTypeCellType";
    public final String MIFBIOSOURCETYPECOMPARTMENT = "MIFBioSourceTypeCompartment";
    public final String MIFBIOSOURCETYPETISSUE = "MIFBioSourceTypeTissue";
    public final String MIFENTRYEXPERIMENT = "MIFEntryExperiment";
    public final String MIFENTRYINTERACTION = "MIFEntryInteraction";
    public final String MIFENTRYINTERACTOR = "MIFEntryInteractor";
    public final String MIFENTRYSET = "MIFEntrySet";
    public final String MIFENTRYSETENTRY = "MIFEntrySetEntry";
    public final String MIFENTRYSOURCE = "MIFEntrySource";
    public final String MIFEXPERIMENTFEATDETECMETHOD = "MIFExperimentFeatDetecMethod";
    public final String MIFEXPERIMENTINTERDETECMETHOD = "MIFExperimentInterDetecMethod";
    public final String MIFEXPERIMENTPARTIDENTMETHOD = "MIFExperimentPartIdentMethod";
    public final String MIFFEATUREFEATDETMETH = "MIFFeatureFeatDetMeth";
    public final String MIFFEATUREFEATURERANGE = "MIFFeatureFeatureRange";
    public final String MIFFEATUREFEATURETYPE = "MIFFeatureFeatureType";
    public final String MIFINFERREDINTERACTIONPARTICIPANT = "MIFInferredInteractionParticipant";
    public final String MIFINTERACTIONINFERREDINTERACTION = "MIFInteractionInferredInteraction";
    public final String MIFINTERACTIONINTERACTIONTYPE = "MIFInteractionInteractionType";
    public final String MIFINTERACTIONPARTICIPANT = "MIFInteractionParticipant";
    public final String MIFINTERACTORINTERACTORTYPE = "MIFInteractorInteractorType";
    public final String MIFOTHERALIAS = "MIFOtherAlias";
    public final String MIFOTHERATTRIBUTE = "MIFOtherAttribute";
    public final String MIFOTHERAVAILABILITY = "MIFOtherAvailability";
    public final String MIFOTHERBIBREF = "MIFOtherBibRef";
    public final String MIFOTHERBIOSOURCETYPE = "MIFOtherBioSourceType";
    public final String MIFOTHERCONFIDENCE = "MIFOtherConfidence";
    public final String MIFOTHEREXPERIMENTREF = "MIFOtherExperimentRef";
    public final String MIFOTHERXREF = "MIFOtherXRef";
    public final String MIFOTHERXREFGO = "MIFOtherXRefGO";
    public final String MIFOTHERXREFPUBMED = "MIFOtherXRefPubMed";
    public final String MIFOTHERXREFREFSEQ = "MIFOtherXRefRefSeq";
    public final String MIFOTHERXREFUNIPROT = "MIFOtherXRefUniprot";
    public final String MIFPARTICIPANTBIOLOGICALROLE = "MIFParticipantBiologicalRole";
    public final String MIFPARTICIPANTEXPERIMENTALINTERACTOR = "MIFParticipantExperimentalInteractor";
    public final String MIFPARTICIPANTEXPERIMENTALPREPARATION = "MIFParticipantExperimentalPreparation";
    public final String MIFPARTICIPANTEXPERIMENTALROLE = "MIFParticipantExperimentalRole";
    public final String MIFPARTICIPANTFEATURE = "MIFParticipantFeature";
    public final String MIFPARTICIPANTPARAMETER = "MIFParticipantParameter";
    public final String MIFPARTICIPANTPARTIDENTMETH = "MIFParticipantPartIdentMeth";
    public final String MIFINTERACTION_HAS_PROTEIN_TEMP = "MIFInteraction_has_Protein_Temp";
    public static final String MIFINTERACTION_HAS_PROTEIN = "MIFInteraction_has_Protein";
    public final String MIFINTERACTIONCOUNT = "MIFInteractionCount";
    private static MIF25Tables singleton;
    private static List<String> tables;

    private MIF25Tables() {
    }

    /**
     * Return a MIF25Tables
     *
     * @return a MIF25Tables
     */
    public static synchronized MIF25Tables getInstance() {
        if (singleton == null) {
            singleton = new MIF25Tables();
        }
        return singleton;
    }

    /**
     * Return the Map object with the tables names and TSV files names
     *
     * @return
     */
    public List<String> getTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(MIFBIOSOURCETYPECELLTYPE);
            tables.add(MIFBIOSOURCETYPECOMPARTMENT);
            tables.add(MIFBIOSOURCETYPETISSUE);
            tables.add(MIFENTRYEXPERIMENT);
            tables.add(MIFENTRYINTERACTION);
            tables.add(MIFENTRYINTERACTOR);
            tables.add(MIFENTRYSET);
            tables.add(MIFENTRYSETENTRY);
            tables.add(MIFENTRYSOURCE);
            tables.add(MIFEXPERIMENTFEATDETECMETHOD);
            tables.add(MIFEXPERIMENTINTERDETECMETHOD);
            tables.add(MIFEXPERIMENTPARTIDENTMETHOD);
            tables.add(MIFFEATUREFEATDETMETH);
            tables.add(MIFFEATUREFEATURERANGE);
            tables.add(MIFFEATUREFEATURETYPE);
            tables.add(MIFINFERREDINTERACTIONPARTICIPANT);
            tables.add(MIFINTERACTIONINFERREDINTERACTION);
            tables.add(MIFINTERACTIONINTERACTIONTYPE);
            tables.add(MIFINTERACTIONPARTICIPANT);
            tables.add(MIFINTERACTORINTERACTORTYPE);
            tables.add(MIFOTHERALIAS);
            tables.add(MIFOTHERATTRIBUTE);
            tables.add(MIFOTHERAVAILABILITY);
            tables.add(MIFOTHERBIBREF);
            tables.add(MIFOTHERBIOSOURCETYPE);
            tables.add(MIFOTHERCONFIDENCE);
            tables.add(MIFOTHEREXPERIMENTREF);
            tables.add(MIFOTHERXREF);
            tables.add(MIFOTHERXREFGO);
            tables.add(MIFOTHERXREFPUBMED);
            tables.add(MIFOTHERXREFREFSEQ);
            tables.add(MIFOTHERXREFUNIPROT);
            tables.add(MIFPARTICIPANTBIOLOGICALROLE);
            tables.add(MIFPARTICIPANTEXPERIMENTALINTERACTOR);
            tables.add(MIFPARTICIPANTEXPERIMENTALPREPARATION);
            tables.add(MIFPARTICIPANTEXPERIMENTALROLE);
            tables.add(MIFPARTICIPANTFEATURE);
            tables.add(MIFPARTICIPANTPARAMETER);
            tables.add(MIFPARTICIPANTPARTIDENTMETH);
            tables.add(MIFINTERACTION_HAS_PROTEIN_TEMP);
            tables.add(MIFINTERACTION_HAS_PROTEIN);
            tables.add(MIFINTERACTIONCOUNT);
        }
        return tables;
    }
}
