package org.jbiowhpersistence.datasets.pathway.kegg.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGGeneJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the KEGGGene transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Oct 5, 2012
 */
public class KEGGGeneTransfer extends JpaTransferBackEnd {

    private KEGGGeneJpaController instance;

    /**
     * Create the KEGGGene transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public KEGGGeneTransfer(EntityManagerFactory emf) {
        instance = new KEGGGeneJpaController(emf);
    }

    /**
     * Create the KEGGGene transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public KEGGGeneTransfer(JBioWHUserData factory) {
        instance = new KEGGGeneJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof KEGGGene) {
            KEGGGene obj = (KEGGGene) object;
            if (instance.findKEGGGene(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
                    obj.setGeneInfos(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
                    obj.setkEGGPathways(null);
                }
                instance.create(obj);
            }
        }
    }
}
