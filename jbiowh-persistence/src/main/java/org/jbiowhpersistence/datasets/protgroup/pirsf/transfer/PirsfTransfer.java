package org.jbiowhpersistence.datasets.protgroup.pirsf.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.pirsf.controller.PirsfJpaController;
import org.jbiowhpersistence.datasets.protgroup.pirsf.entities.Pirsf;
import org.jbiowhpersistence.datasets.protgroup.pirsf.entities.PirsfhasProtein;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the PIRSF transfer process
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 12, 2013
 */
public class PirsfTransfer extends JpaTransferBackEnd {

    private PirsfJpaController instance;

    /**
     * Create the Pirsf transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public PirsfTransfer(EntityManagerFactory emf) {
        instance = new PirsfJpaController(emf);
    }

    /**
     * Create the Pirsf transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public PirsfTransfer(JBioWHUserData factory) {
        instance = new PirsfJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof Pirsf) {
            Pirsf obj = (Pirsf) object;
            if (instance.findPirsf(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    for (PirsfhasProtein p : obj.getpIRSFhasProtein().values()) {
                        p.setProtein(null);
                    }
                }
                instance.create(obj);
            }
        }
    }

}
