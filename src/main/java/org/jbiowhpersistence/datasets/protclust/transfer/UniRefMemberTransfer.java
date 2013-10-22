package org.jbiowhpersistence.datasets.protclust.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.protclust.controller.UniRefMemberJpaController;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMember;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the UniRefMember transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-26 16:29:19 +0100 (Mon, 26 Nov 2012) $ 
 * $LastChangedRevision: 323 $
 * @since Oct 5, 2012
 */
public class UniRefMemberTransfer extends JpaTransferBackEnd {

    private UniRefMemberJpaController instance;

    /**
     * Create the UniRefMember transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public UniRefMemberTransfer(EntityManagerFactory emf) {
        instance = new UniRefMemberJpaController(emf);
    }

    /**
     * Create the UniRefMember transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public UniRefMemberTransfer(JBioWHUserData factory) {
        instance = new UniRefMemberJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof UniRefMember) {
            UniRefMember obj = (UniRefMember) object;
            if (instance.findUniRefMember(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProtein(null);
                }
                instance.create(obj);
            }
        }
    }
}
