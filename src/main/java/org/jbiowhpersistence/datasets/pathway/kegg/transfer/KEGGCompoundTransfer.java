package org.jbiowhpersistence.datasets.pathway.kegg.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGCompoundJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGRPair;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the KEGGCompound transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $ 
 * $LastChangedRevision: 285 $
 * @since Oct 5, 2012
 */
public class KEGGCompoundTransfer extends JpaTransferBackEnd {

    private KEGGCompoundJpaController instance;

    /**
     * Create the KEGGCompound transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public KEGGCompoundTransfer(EntityManagerFactory emf) {
        instance = new KEGGCompoundJpaController(emf);
    }

    /**
     * Create the KEGGCompound transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public KEGGCompoundTransfer(JBioWHUserData factory) {
        instance = new KEGGCompoundJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof KEGGCompound) {
            KEGGCompound obj = (KEGGCompound) object;
            if (instance.findKEGGCompound(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGReaction.class)) {
                    obj.setkEGGReactionAsProduct(null);
                    obj.setkEGGReactionAsSubstrate(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGEnzyme.class)) {
                    obj.setkEGGEnzymeAsCofactor(null);
                    obj.setkEGGEnzymeAsEffector(null);
                    obj.setkEGGEnzymeAsInhibitor(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
                    obj.setkEGGPathways(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGRPair.class)) {
                    obj.setkEGGRPair(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(DrugBank.class)) {
                    obj.setDrugBanks(null);
                }
                instance.create(obj);
            }
        }
    }
}
