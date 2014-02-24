package org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.controller.ProtClustJpaController;
import org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities.ProtClust;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the NCBI Protein cluster transfer process
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 13, 2014
 */
public class ProtClustTransfer extends JpaTransferBackEnd {

    private ProtClustJpaController instance;

    /**
     * Create the COG transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public ProtClustTransfer(EntityManagerFactory emf) {
        instance = new ProtClustJpaController(emf);
    }

    /**
     * Create the COG transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public ProtClustTransfer(JBioWHUserData factory) {
        instance = new ProtClustJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof ProtClust) {
            ProtClust obj = (ProtClust) object;
            if (instance.findProtClust(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
                    obj.setGeneInfo(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProtein(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(COGOrthologousGroup.class)) {
                    obj.setCogOrthologousGroup(null);
                }
                instance.create(obj);
            }
        }
    }

}
