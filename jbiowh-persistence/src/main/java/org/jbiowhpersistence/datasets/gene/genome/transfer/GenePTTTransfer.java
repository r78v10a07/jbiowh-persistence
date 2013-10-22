package org.jbiowhpersistence.datasets.gene.genome.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhcore.utility.fileformats.bioml.xml.Protein;
import org.jbiowhpersistence.datasets.gene.genome.controller.GenePTTJpaController;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the GenePTT transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Oct 5, 2012
 */
public class GenePTTTransfer extends JpaTransferBackEnd {

    private GenePTTJpaController instance;

    /**
     * Create the GenePTT transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public GenePTTTransfer(EntityManagerFactory emf) {
        instance = new GenePTTJpaController(emf);
    }

    /**
     * Create the GenePTT transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public GenePTTTransfer(JBioWHUserData factory) {
        instance = new GenePTTJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof GenePTT) {
            GenePTT obj = (GenePTT) object;
            if (instance.findGenePTT(obj.getProteinGi()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProtein(null);
                }
                instance.create(obj);
            }
        }
    }
}
