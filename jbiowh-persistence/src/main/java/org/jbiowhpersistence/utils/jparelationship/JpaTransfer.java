package org.jbiowhpersistence.utils.jparelationship;

import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This interface handled the JPA insertion into a JBioWH relational schema
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $ 
 * $LastChangedRevision: 285 $
 * @since Oct 4, 2012
 */
public interface JpaTransfer {
    
    /**
     * Start the transfer of the object to the JBioWH WHDBMS Factory.<br/>
     * The object can be a collection of entities or a entity.
     *
     * @param object the objects to be transfered
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void transfer(Object object)
            throws PreexistingEntityException, Exception;
}
