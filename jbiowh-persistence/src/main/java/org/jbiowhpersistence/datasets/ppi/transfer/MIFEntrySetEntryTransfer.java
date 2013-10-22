package org.jbiowhpersistence.datasets.ppi.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.ppi.controller.MIFEntrySetEntryJpaController;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntrySetEntry;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the MIFEntrySetEntry transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Oct 5, 2012
 */
public class MIFEntrySetEntryTransfer extends JpaTransferBackEnd {

    private MIFEntrySetEntryJpaController instance;

    /**
     * Create the MIFEntrySetEntry transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public MIFEntrySetEntryTransfer(EntityManagerFactory emf) {
        instance = new MIFEntrySetEntryJpaController(emf);
    }

    /**
     * Create the MIFEntrySetEntry transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public MIFEntrySetEntryTransfer(JBioWHUserData factory) {
        instance = new MIFEntrySetEntryJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof MIFEntrySetEntry) {
            MIFEntrySetEntry obj = (MIFEntrySetEntry) object;
            if (instance.findMIFEntrySetEntry(obj.getWid()) == null) {
                instance.create(obj);
            }
        }
    }
}
