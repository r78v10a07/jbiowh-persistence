package org.jbiowhpersistence.datasets.gene.gene.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.gene.gene.controller.GeneInfoJpaController;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the GeneInfo transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Oct 4, 2012
 */
public class GeneTransfer extends JpaTransferBackEnd {

    private GeneInfoJpaController instance;

    /**
     * Create the GeneInfo transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public GeneTransfer(EntityManagerFactory emf) {
        instance = new GeneInfoJpaController(emf);
    }

    /**
     * Create the GeneInfo transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public GeneTransfer(JBioWHUserData factory) {
        instance = new GeneInfoJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof GeneInfo) {
            GeneInfo obj = (GeneInfo) object;
            if (instance.findGeneInfo(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProtein(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGGene.class)) {
                    obj.setkEGGGenes(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGPathway.class)) {
                    obj.setkEGGPathways(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(OMIM.class)) {
                    obj.setOmim(null);
                }
                instance.create(obj);
            }
        }
    }
}
