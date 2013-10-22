package org.jbiowhpersistence.datasets.pathway.kegg.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGEnzymeJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the KEGGEnzyme transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $ 
 * $LastChangedRevision: 285 $
 * @since Oct 5, 2012
 */
public class KEGGEnzymeTransfer extends JpaTransferBackEnd {

    private KEGGEnzymeJpaController instance;

    /**
     * Create the KEGGEnzyme transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public KEGGEnzymeTransfer(EntityManagerFactory emf) {
        instance = new KEGGEnzymeJpaController(emf);
    }

    /**
     * Create the KEGGEnzyme transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public KEGGEnzymeTransfer(JBioWHUserData factory) {
        instance = new KEGGEnzymeJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof KEGGEnzyme) {
            KEGGEnzyme obj = (KEGGEnzyme) object;
            if (instance.findKEGGEnzyme(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProtein(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGReaction.class)) {
                    obj.setkEGGReaction(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
                    obj.setkEGGPathways(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGCompound.class)) {
                    obj.setkEGGCompoundAsCofactor(null);
                    obj.setkEGGCompoundAsEffector(null);
                    obj.setkEGGCompoundAsInhibitor(null);
                }
                instance.create(obj);
            }
        }
    }
}
