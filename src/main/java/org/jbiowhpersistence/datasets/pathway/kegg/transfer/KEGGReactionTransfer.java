package org.jbiowhpersistence.datasets.pathway.kegg.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGReactionJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the KEGGReaction transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $ 
 * $LastChangedRevision: 285 $
 * @since Oct 5, 2012
 */
public class KEGGReactionTransfer extends JpaTransferBackEnd {

    private KEGGReactionJpaController instance;

    /**
     * Create the KEGGReaction transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public KEGGReactionTransfer(EntityManagerFactory emf) {
        instance = new KEGGReactionJpaController(emf);
    }

    /**
     * Create the KEGGReaction transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public KEGGReactionTransfer(JBioWHUserData factory) {
        instance = new KEGGReactionJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof KEGGReaction) {
            KEGGReaction obj = (KEGGReaction) object;
            if (instance.findKEGGReaction(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGCompound.class)) {
                    obj.setkEGGCompoundAsProduct(null);
                    obj.setkEGGCompoundAsSubstrate(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGGlycan.class)) {
                    obj.setkEGGGlycanAsProduct(null);
                    obj.setkEGGGlycanAsSubstrate(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
                    obj.setkEGGPathways(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGEnzyme.class)) {
                    obj.setkEGGEnzyme(null);
                }
                instance.create(obj);
            }
        }
    }
}
