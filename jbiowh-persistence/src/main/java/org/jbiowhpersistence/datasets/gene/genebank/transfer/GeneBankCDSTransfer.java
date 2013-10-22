package org.jbiowhpersistence.datasets.gene.genebank.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genebank.controller.GeneBankCDSJpaController;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the GeneBankCDS transfer process
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 13, 2013
 */
public class GeneBankCDSTransfer extends JpaTransferBackEnd {

    private GeneBankCDSJpaController instance;

    /**
     * Create the GeneBankCDS transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public GeneBankCDSTransfer(EntityManagerFactory emf) {
        instance = new GeneBankCDSJpaController(emf);
    }

    /**
     * Create the GeneBankCDS transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public GeneBankCDSTransfer(JBioWHUserData factory) {
        instance = new GeneBankCDSJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof GeneBankCDS) {
            GeneBankCDS obj = (GeneBankCDS) object;
            if (instance.findGeneBankCDS(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(GeneBank.class)) {
                    obj.setGeneBank(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
                    obj.setGeneInfo(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(GenePTT.class)) {
                    obj.setGenePTT(null);
                }
                instance.create(obj);
            }
        }
    }
}
