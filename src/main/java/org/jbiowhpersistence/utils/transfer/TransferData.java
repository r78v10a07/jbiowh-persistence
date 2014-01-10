package org.jbiowhpersistence.utils.transfer;

import java.util.Collection;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.disease.omim.transfer.OMIMTransfer;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.domain.pfam.transfer.PFamATransfer;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.drug.drugbank.transfer.DrugBankTransfer;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.gene.transfer.GeneTransfer;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;
import org.jbiowhpersistence.datasets.gene.genebank.transfer.GeneBankCDSTransfer;
import org.jbiowhpersistence.datasets.gene.genebank.transfer.GeneBankTransfer;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.gene.genome.entities.GeneRNT;
import org.jbiowhpersistence.datasets.gene.genome.transfer.GenePTTTransfer;
import org.jbiowhpersistence.datasets.gene.genome.transfer.GeneRNTTransfer;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.ontology.transfer.OntologyTransfer;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.datasets.pathway.kegg.transfer.KEGGCompoundTransfer;
import org.jbiowhpersistence.datasets.pathway.kegg.transfer.KEGGEnzymeTransfer;
import org.jbiowhpersistence.datasets.pathway.kegg.transfer.KEGGGeneTransfer;
import org.jbiowhpersistence.datasets.pathway.kegg.transfer.KEGGGlycanTransfer;
import org.jbiowhpersistence.datasets.pathway.kegg.transfer.KEGGPathwayTransfer;
import org.jbiowhpersistence.datasets.pathway.kegg.transfer.KEGGReactionTransfer;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySet;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.datasets.ppi.transfer.MIFEntryInteractionTransfer;
import org.jbiowhpersistence.datasets.ppi.transfer.MIFEntrySetEntryTransfer;
import org.jbiowhpersistence.datasets.ppi.transfer.MIFEntrySetTransfer;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMember;
import org.jbiowhpersistence.datasets.protclust.transfer.UniRefEntryTransfer;
import org.jbiowhpersistence.datasets.protclust.transfer.UniRefMemberTransfer;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protein.transfer.ProteinTransfer;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import org.jbiowhpersistence.datasets.protgroup.cog.transfer.COGTransfer;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXMLGroup;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.transfer.OrthoXMLTransfer;
import org.jbiowhpersistence.datasets.protgroup.pirsf.entities.Pirsf;
import org.jbiowhpersistence.datasets.protgroup.pirsf.transfer.PirsfTransfer;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.datasets.taxonomy.transfer.TaxonomyTransfer;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.jparelationship.JpaTransfer;

/**
 * This Class is the
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100
 * (Tue, 19 Mar 2013) $ $LastChangedRevision: 591 $
 *
 * @since Oct 4, 2012
 */
public class TransferData {

    private static TransferData singleton;

    private TransferData() {
    }

    public void startTransfer(JBioWHUserData factory, Object object)
            throws PreexistingEntityException, Exception {
        if (object != null) {
            Object toCompare = object;
            JpaTransfer transfer = null;
            if (object instanceof Collection) {
                if (!((Collection) object).isEmpty()) {
                    toCompare = ((Collection) object).iterator().next();
                } else {
                    return;
                }
            }
            if (toCompare instanceof Taxonomy) {
                transfer = new TaxonomyTransfer(factory);
            } else if (toCompare instanceof Ontology) {
                transfer = new OntologyTransfer(factory);
            } else if (toCompare instanceof DrugBank) {
                transfer = new DrugBankTransfer(factory);
            } else if (toCompare instanceof GeneInfo) {
                transfer = new GeneTransfer(factory);
            } else if (toCompare instanceof GenePTT) {
                transfer = new GenePTTTransfer(factory);
            } else if (toCompare instanceof GeneRNT) {
                transfer = new GeneRNTTransfer(factory);
            } else if (toCompare instanceof KEGGCompound) {
                transfer = new KEGGCompoundTransfer(factory);
            } else if (toCompare instanceof KEGGEnzyme) {
                transfer = new KEGGEnzymeTransfer(factory);
            } else if (toCompare instanceof KEGGGene) {
                transfer = new KEGGGeneTransfer(factory);
            } else if (toCompare instanceof KEGGGlycan) {
                transfer = new KEGGGlycanTransfer(factory);
            } else if (toCompare instanceof KEGGReaction) {
                transfer = new KEGGReactionTransfer(factory);
            } else if (toCompare instanceof KEGGPathway) {
                transfer = new KEGGPathwayTransfer(factory);
            } else if (toCompare instanceof Protein) {
                transfer = new ProteinTransfer(factory);
            } else if (toCompare instanceof UniRefEntry) {
                transfer = new UniRefEntryTransfer(factory);
            } else if (toCompare instanceof UniRefMember) {
                transfer = new UniRefMemberTransfer(factory);
            } else if (toCompare instanceof OMIM) {
                transfer = new OMIMTransfer(factory);
            } else if (toCompare instanceof MIFEntrySet) {
                transfer = new MIFEntrySetTransfer(factory);
            } else if (toCompare instanceof MIFEntrySetEntry) {
                transfer = new MIFEntrySetEntryTransfer(factory);
            } else if (toCompare instanceof MIFEntryInteraction) {
                transfer = new MIFEntryInteractionTransfer(factory);
            } else if (toCompare instanceof PfamAbioWH) {
                transfer = new PFamATransfer(factory);
            } else if (toCompare instanceof GeneBank) {
                transfer = new GeneBankTransfer(factory);
            } else if (toCompare instanceof GeneBankCDS) {
                transfer = new GeneBankCDSTransfer(factory);
            } else if (toCompare instanceof Pirsf) {
                transfer = new PirsfTransfer(factory);
            } else if (toCompare instanceof COGOrthologousGroup) {
                transfer = new COGTransfer(factory);
            } else if (toCompare instanceof OrthoXMLGroup) {
                transfer = new OrthoXMLTransfer(factory);
            }
            if (transfer != null) {
                transfer.transfer(object);
            }
        }
    }

    /**
     * Return a TransferData instance
     *
     * @return a TransferData instance
     */
    public static synchronized TransferData getInstance() {
        if (singleton == null) {
            singleton = new TransferData();
        }
        return singleton;
    }
}
