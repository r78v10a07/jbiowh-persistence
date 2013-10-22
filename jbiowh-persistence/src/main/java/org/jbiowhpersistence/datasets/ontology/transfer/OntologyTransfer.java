package org.jbiowhpersistence.datasets.ontology.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.ontology.controller.OntologyJpaController;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the Ontology transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $ 
 * $LastChangedRevision: 285 $
 * @since Oct 5, 2012
 */
public class OntologyTransfer extends JpaTransferBackEnd {

    private OntologyJpaController instance;

    /**
     * Create the Ontology transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public OntologyTransfer(EntityManagerFactory emf) {
        instance = new OntologyJpaController(emf);
    }

    /**
     * Create the Ontology transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public OntologyTransfer(JBioWHUserData factory) {
        instance = new OntologyJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof Ontology) {
            Ontology obj = (Ontology) object;
            if (instance.findOntology(obj.getWid()) == null) {
                obj.setRelationsToNull();
                instance.create(obj);
            }
        }
    }
}
