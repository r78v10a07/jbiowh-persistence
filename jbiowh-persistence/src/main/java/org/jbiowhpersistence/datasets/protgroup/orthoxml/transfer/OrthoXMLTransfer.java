package org.jbiowhpersistence.datasets.protgroup.orthoxml.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.controller.OrthoXMLGroupJpaController;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.entities.OrthoXMLGroup;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the OrthoXML transfer process
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 9, 2014
 */
public class OrthoXMLTransfer extends JpaTransferBackEnd {

    private OrthoXMLGroupJpaController instance;

    /**
     * Create the COG transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public OrthoXMLTransfer(EntityManagerFactory emf) {
        instance = new OrthoXMLGroupJpaController(emf);
    }

    /**
     * Create the COG transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public OrthoXMLTransfer(JBioWHUserData factory) {
        instance = new OrthoXMLGroupJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof OrthoXMLGroup) {
            OrthoXMLGroup obj = (OrthoXMLGroup) object;
            if (instance.findOrthoXMLGroup(obj.getWid()) == null) {
                instance.create(obj);
            }
        }
    }

}
