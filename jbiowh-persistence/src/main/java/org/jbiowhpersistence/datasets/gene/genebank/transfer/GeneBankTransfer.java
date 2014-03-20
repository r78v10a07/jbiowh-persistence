package org.jbiowhpersistence.datasets.gene.genebank.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.gene.genebank.controller.GeneBankJpaController;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the GeneBank transfer process
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 13, 2013
 */
public class GeneBankTransfer extends JpaTransferBackEnd {

    private GeneBankJpaController instance;

    /**
     * Create the GeneBank transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public GeneBankTransfer(EntityManagerFactory emf) {
        instance = new GeneBankJpaController(emf);
    }

    /**
     * Create the GeneBank transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public GeneBankTransfer(JBioWHUserData factory) {
        instance = new GeneBankJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof GeneBank) {
            GeneBank obj = (GeneBank) object;
            if (instance.findGeneBank(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(GeneBankCDS.class)) {
                    obj.setGeneBankCDS(null);
                }
                instance.create(obj);
            }
        }
    }
}
