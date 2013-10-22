package org.jbiowhpersistence.datasets.ppi.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.ppi.controller.MIFEntrySetJpaController;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySet;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the MIFEntrySet transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Oct 5, 2012
 */
public class MIFEntrySetTransfer extends JpaTransferBackEnd {

    private MIFEntrySetJpaController instance;

    /**
     * Create the MIFEntrySet transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public MIFEntrySetTransfer(EntityManagerFactory emf) {
        instance = new MIFEntrySetJpaController(emf);
    }

    /**
     * Create the MIFEntrySet transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public MIFEntrySetTransfer(JBioWHUserData factory) {
        instance = new MIFEntrySetJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof MIFEntrySet) {
            MIFEntrySet obj = (MIFEntrySet) object;
            if (instance.findMIFEntrySet(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(MIFEntrySetEntry.class)) {
                    obj.setmIFEntrySetEntry(null);
                }
                instance.create(obj);
            }
        }
    }
}
