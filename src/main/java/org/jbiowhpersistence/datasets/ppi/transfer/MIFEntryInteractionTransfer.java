package org.jbiowhpersistence.datasets.ppi.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.ppi.controller.MIFEntryInteractionJpaController;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the MIFEntryInteraction transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Oct 5, 2012
 */
public class MIFEntryInteractionTransfer extends JpaTransferBackEnd {

    private MIFEntryInteractionJpaController instance;

    /**
     * Create the MIFEntryInteraction transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public MIFEntryInteractionTransfer(EntityManagerFactory emf) {
        instance = new MIFEntryInteractionJpaController(emf);
    }

    /**
     * Create the MIFEntryInteraction transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public MIFEntryInteractionTransfer(JBioWHUserData factory) {
        instance = new MIFEntryInteractionJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof MIFEntryInteraction) {
            MIFEntryInteraction obj = (MIFEntryInteraction) object;
            if (instance.findMIFEntryInteraction(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProtein(null);
                }
                instance.create(obj);
            }
        }
    }
}
