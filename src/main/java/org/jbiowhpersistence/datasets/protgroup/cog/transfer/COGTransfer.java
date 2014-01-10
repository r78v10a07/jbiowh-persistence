package org.jbiowhpersistence.datasets.protgroup.cog.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.cog.controller.COGOrthologousGroupJpaController;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the COG transfer process
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 9, 2014
 */
public class COGTransfer extends JpaTransferBackEnd {

    private COGOrthologousGroupJpaController instance;

    /**
     * Create the COG transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public COGTransfer(EntityManagerFactory emf) {
        instance = new COGOrthologousGroupJpaController(emf);
    }

    /**
     * Create the COG transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public COGTransfer(JBioWHUserData factory) {
        instance = new COGOrthologousGroupJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof COGOrthologousGroup) {
            COGOrthologousGroup obj = (COGOrthologousGroup) object;
            if (instance.findCOGOrthologousGroup(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(GeneInfo.class)) {
                    obj.setGeneInfo(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProtein(null);
                }
                instance.create(obj);
            }
        }
    }

}
