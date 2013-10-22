package org.jbiowhpersistence.datasets.pathway.kegg.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGPathwayJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the KEGGPathway transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $ 
 * $LastChangedRevision: 285 $
 * @since Oct 5, 2012
 */
public class KEGGPathwayTransfer extends JpaTransferBackEnd {

    private KEGGPathwayJpaController instance;

    /**
     * Create the KEGGPathway transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public KEGGPathwayTransfer(EntityManagerFactory emf) {
        instance = new KEGGPathwayJpaController(emf);
    }

    /**
     * Create the KEGGPathway transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public KEGGPathwayTransfer(JBioWHUserData factory) {
        instance = new KEGGPathwayJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof KEGGPathway) {
            KEGGPathway obj = (KEGGPathway) object;
            if (instance.findKEGGPathway(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProtein(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGGene.class)) {
                    obj.setkEGGGenes(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGCompound.class)) {
                    obj.setkEGGCompounds(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGEnzyme.class)) {
                    obj.setkEGGEnzymes(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGReaction.class)) {
                    obj.setkEGGReactions(null);
                }
                instance.create(obj);
            }
        }
    }
}
