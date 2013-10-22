package org.jbiowhpersistence.datasets.gene.genome.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.gene.genome.controller.GeneRNTJpaController;
import org.jbiowhpersistence.datasets.gene.genome.entities.GeneRNT;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This Class handled the GeneRNT transfer process
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 24, 2013
 */
public class GeneRNTTransfer extends JpaTransferBackEnd {

    private GeneRNTJpaController instance;
    private static GeneRNTTransfer singleton;

    /**
     * Create the GeneRNT transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public GeneRNTTransfer(EntityManagerFactory emf) {
        instance = new GeneRNTJpaController(emf);
    }

    /**
     * Create the GeneRNT transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public GeneRNTTransfer(JBioWHUserData factory) {
        instance = new GeneRNTJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof GeneRNT) {
            GeneRNT obj = (GeneRNT) object;
            if (instance.findGeneRNT(obj.getWid()) == null) {
                instance.create(obj);
            }
        }
    }
}
