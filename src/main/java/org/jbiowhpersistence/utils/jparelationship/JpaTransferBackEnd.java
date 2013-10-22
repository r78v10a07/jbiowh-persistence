package org.jbiowhpersistence.utils.jparelationship;

import java.util.Collection;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the backend class that implement the JpaTransfer interface to
 * transfer a entity or a collection of entities to a JBioWH relational schema
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-27 15:25:50 +0100 (Wed, 27 Mar 2013) $ 
 * $LastChangedRevision: 515 $
 * @since Oct 5, 2012
 */
public abstract class JpaTransferBackEnd implements JpaTransfer {

    /**
     * Transfer the object into a JBioWH relational schema.<br/> The object can
     * be a collection of entities or a entity
     *
     * @param object the object to transfer into a JBioWH relational schema
     * @throws PreexistingEntityException
     * @throws Exception
     */
    protected abstract void insert(Object object) throws PreexistingEntityException, Exception;

    @Override
    public void transfer(Object object) throws PreexistingEntityException, Exception {
        if (object != null) {
            if (object instanceof Collection) {
                Collection collection = (Collection) object;
                if (!collection.isEmpty()) {
                    for (Object obj : collection) {
                        insert(obj);
                    }
                }
            } else {
                insert(object);
            }
        }
    }
}
