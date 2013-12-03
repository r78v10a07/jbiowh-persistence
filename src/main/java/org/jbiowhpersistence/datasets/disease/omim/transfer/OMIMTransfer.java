package org.jbiowhpersistence.datasets.disease.omim.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.disease.omim.controller.OMIMJpaController;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the OMIM transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Oct 5, 2012
 */
public class OMIMTransfer extends JpaTransferBackEnd {

    private OMIMJpaController instance;

    /**
     * Create the OMIM transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public OMIMTransfer(EntityManagerFactory emf) {
        instance = new OMIMJpaController(emf);
    }

    /**
     * Create the OMIM transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public OMIMTransfer(JBioWHUserData factory) {
        instance = new OMIMJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof OMIM) {
            OMIM obj = (OMIM) object;
            if (instance.findOMIM(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
                    obj.setGeneInfo(null);
                }
                instance.create(obj);
            }
        }
    }
}
