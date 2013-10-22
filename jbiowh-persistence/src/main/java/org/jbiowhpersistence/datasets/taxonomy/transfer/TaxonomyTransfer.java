package org.jbiowhpersistence.datasets.taxonomy.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.taxonomy.controller.TaxonomyJpaController;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the Taxonomy transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $ 
 * $LastChangedRevision: 285 $
 * @since Oct 5, 2012
 */
public class TaxonomyTransfer extends JpaTransferBackEnd {

    private TaxonomyJpaController instance;

    /**
     * Create the Taxonomy transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public TaxonomyTransfer(EntityManagerFactory emf) {
        instance = new TaxonomyJpaController(emf);
    }

    /**
     * Create the Taxonomy transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public TaxonomyTransfer(JBioWHUserData factory) {
        instance = new TaxonomyJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof Taxonomy) {
            Taxonomy obj = (Taxonomy) object;
            if (instance.findTaxonomy(obj.getWid()) == null) {
                instance.create(obj);
            }
        }
    }
}
