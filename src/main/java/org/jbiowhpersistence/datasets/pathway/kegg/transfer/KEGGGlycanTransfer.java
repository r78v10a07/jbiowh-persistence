package org.jbiowhpersistence.datasets.pathway.kegg.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGGlycanJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the KEGGGlycan transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $ 
 * $LastChangedRevision: 285 $
 * @since Oct 5, 2012
 */
public class KEGGGlycanTransfer extends JpaTransferBackEnd {

    private KEGGGlycanJpaController instance;

    /**
     * Create the KEGGGlycan transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public KEGGGlycanTransfer(EntityManagerFactory emf) {
        instance = new KEGGGlycanJpaController(emf);
    }

    /**
     * Create the KEGGGlycan transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public KEGGGlycanTransfer(JBioWHUserData factory) {
        instance = new KEGGGlycanJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof KEGGGlycan) {
            KEGGGlycan obj = (KEGGGlycan) object;
            if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGReaction.class)) {
                obj.setkEGGReactionAsProduct(null);
                obj.setkEGGReactionAsSubstrate(null);
            }
            instance.create(obj);
        }
    }
}
