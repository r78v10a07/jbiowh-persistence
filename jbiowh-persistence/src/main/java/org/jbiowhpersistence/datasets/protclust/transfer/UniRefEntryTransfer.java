package org.jbiowhpersistence.datasets.protclust.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.protclust.controller.UniRefEntryJpaController;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the UniRefEntry transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Oct 5, 2012
 */
public class UniRefEntryTransfer extends JpaTransferBackEnd {

    private UniRefEntryJpaController instance;

    /**
     * Create the UniRefEntry transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public UniRefEntryTransfer(EntityManagerFactory emf) {
        instance = new UniRefEntryJpaController(emf);
    }

    /**
     * Create the UniRefEntry transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public UniRefEntryTransfer(JBioWHUserData factory) {
        instance = new UniRefEntryJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof UniRefEntry) {
            UniRefEntry obj = (UniRefEntry) object;
            if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                obj.setProtein(null);
            }
            instance.create(obj);
        }
    }
}
