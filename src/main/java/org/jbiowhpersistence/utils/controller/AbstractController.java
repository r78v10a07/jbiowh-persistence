package org.jbiowhpersistence.utils.controller;

import java.util.HashMap;

/**
 * This class is used to have a check controller class for
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Mar 15, 2013
 */
public abstract class AbstractController {

    private HashMap<Class, Object> controllers = null;

    /**
     * Creates the JPA controller
     *
     * @return the JPA controller
     */
    protected abstract HashMap<Class, Object> createController();

    /**
     * Get the JPA controller and check is it is created or not
     *
     * @param controllerClass the controller class
     * @return the JPA controller
     */
    protected Object getController(Class controllerClass) {
        if (controllers == null) {
            controllers = createController();
        }        
        return controllers.get(controllerClass);
    }
}
