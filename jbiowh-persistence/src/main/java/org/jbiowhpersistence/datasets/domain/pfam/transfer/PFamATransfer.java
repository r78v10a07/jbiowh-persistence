package org.jbiowhpersistence.datasets.domain.pfam.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhcore.utility.fileformats.bioml.xml.Protein;
import org.jbiowhpersistence.datasets.domain.pfam.controller.PfamAbioWHJpaController;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the PFAM transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 21, 2012
 */
public class PFamATransfer extends JpaTransferBackEnd {
    
    private PfamAbioWHJpaController instance;

    /**
     * Create the PFamA transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public PFamATransfer(EntityManagerFactory emf) {
        instance = new PfamAbioWHJpaController(emf);
    }

    /**
     * Create the PFamA transfer
     *
     */
    public PFamATransfer(JBioWHUserData factory) {
        instance = new PfamAbioWHJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }
    
    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof PfamAbioWH) {
            PfamAbioWH obj = (PfamAbioWH) object;
            if (instance.findPfamA(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProteinInsignificant(null);
                    obj.setProteinSignificant(null);
                }
                instance.create(obj);
            }
        }
    }
}
