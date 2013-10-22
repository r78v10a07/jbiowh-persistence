package org.jbiowhpersistence.datasets.drug.drugbank.transfer;

import javax.persistence.EntityManagerFactory;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhpersistence.datasets.drug.drugbank.controller.DrugBankJpaController;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jbiowhpersistence.utils.jparelationship.JpaEntitiesSelected;
import org.jbiowhpersistence.utils.jparelationship.JpaTransferBackEnd;

/**
 * This class handled the DrugBank transfer process
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $ 
 * $LastChangedRevision: 285 $
 * @since Oct 5, 2012
 */
public class DrugBankTransfer extends JpaTransferBackEnd {

    private DrugBankJpaController instance;

    /**
     * Create the Drugbank transfer
     *
     * @param emf the JBioWH Entity Manager Factory
     */
    public DrugBankTransfer(EntityManagerFactory emf) {
        instance = new DrugBankJpaController(emf);
    }

    /**
     * Create the Drugbank transfer
     *
     * @param factory the JBioWH JBioWHPersistence Factory
     */
    public DrugBankTransfer(JBioWHUserData factory) {
        instance = new DrugBankJpaController(JBioWHPersistence.getInstance().getWHEntityManager(factory));
    }

    @Override
    protected void insert(Object object) throws PreexistingEntityException, Exception {
        if (object instanceof DrugBank) {
            DrugBank obj = (DrugBank) object;
            if (instance.findDrugBank(obj.getWid()) == null) {
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(Protein.class)) {
                    obj.setProtein(null);
                    obj.setProteinAsCarriers(null);
                    obj.setProteinAsEnzyme(null);
                    obj.setProteinAsTransporters(null);
                }
                if (!JpaEntitiesSelected.getInstance().getEntities().contains(KEGGCompound.class)) {
                    obj.setkEGGCompounds(null);
                }
                instance.create(obj);
            }
        }
    }
}
