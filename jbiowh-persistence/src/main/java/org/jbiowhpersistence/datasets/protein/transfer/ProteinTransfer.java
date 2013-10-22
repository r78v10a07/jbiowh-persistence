package org.jbiowhpersistence.datasets.protein.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMember;
import org.jbiowhpersistence.datasets.protein.controller.ProteinJpaController;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the Protein transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Oct 5, 2012
 */
public class ProteinTransfer extends JpaTransferBackEnd {

    private ProteinJpaController instance;

    /**
     * Create the Protein transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public ProteinTransfer(EntityManagerFactory emf) {
        instance = new ProteinJpaController(emf);
    }

    /**
     * Create the Protein transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public ProteinTransfer(JBioWHUserData factory) {
        instance = new ProteinJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof Protein) {
            Protein obj = (Protein) object;
            if (instance.findProtein(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(DrugBank.class)) {
                    obj.setDrugBank(null);
                    obj.setDrugBankAsCarriers(null);
                    obj.setDrugBankAsEnzyme(null);
                    obj.setDrugBankAsTransporters(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
                    obj.setGeneInfo(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
                    obj.setkEGGPathways(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGEnzyme.class)) {
                    obj.setkEGGEnzymes(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(MIFEntryInteraction.class)) {
                    obj.setmIFEntryInteraction(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(UniRefEntry.class)) {
                    obj.setUniRefEntry(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(UniRefMember.class)) {
                    obj.setUniRefMember(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(PfamAbioWH.class)) {
                    obj.setPfamAInsignificant(null);
                    obj.setPfamASignificant(null);
                }
                instance.create(obj);
            }
        }
    }
}
