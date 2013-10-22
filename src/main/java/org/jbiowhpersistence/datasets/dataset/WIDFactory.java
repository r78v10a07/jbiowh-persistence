package org.jbiowhpersistence.datasets.dataset;

import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.dataset.controller.WidTableJpaController;
import org.jbiowhpersistence.datasets.dataset.entities.WidTable;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;


/**
 * This Class is the WID Factory to handled the WID value
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $
 * $LastChangedRevision: 396 $
 * @since Jun 17, 2011
 */
public class WIDFactory {

    private static WIDFactory widFactory;
    private WidTable wid = null;

    private WIDFactory() {
    }

    /**
     * Return a WIDFactory
     *
     * @return a WIDFactory
     */
    public static synchronized WIDFactory getInstance() {
        if (widFactory == null) {
            widFactory = new WIDFactory();
        }
        return widFactory;
    }

    /**
     * Increase the WID value by one
     */
    public void increaseWid() {
        setWid(getWid() + 1);
    }

    /**
     * Return the WID value
     *
     * @return a Long object with the WID value
     */
    public Long getWid() {
        return wid.getPreviousWID();
    }

    /**
     * Set the WID value
     *
     * @param wid a Long object with the WID value
     */
    public void setWid(Long wid) {
        this.wid.setPreviousWID(wid);
    }

    /**
     * Retrieve the WID entity from the database
     */
    public void getWIDFromDataBase() {
        WidTableJpaController instance = new WidTableJpaController(JBioWHPersistence.getInstance().getWHEntityManager());

        wid = instance.findWidTable(1L);
        if (wid == null) {
            wid = new WidTable(1L, 1000000L);
            try {
                instance.create(wid);
            } catch (PreexistingEntityException ex) {
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                VerbLogger.getInstance().log(this.getClass(), ex.toString());
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
            } catch (Exception ex) {
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                VerbLogger.getInstance().log(this.getClass(), ex.toString());
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
            }
        }
        VerbLogger.getInstance().log(this.getClass(), "Global WID = " + wid.getPreviousWID());
    }

    /**
     * Update the WID value into the database
     */
    public void updateWIDTable() {
        WidTableJpaController instance = new WidTableJpaController(JBioWHPersistence.getInstance().getWHEntityManager());

        try {
            VerbLogger.getInstance().log(this.getClass(), "Setting Global WID = " + wid.getPreviousWID());
            instance.edit(wid);
        } catch (NonexistentEntityException ex) {
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
            VerbLogger.getInstance().log(this.getClass(), ex.toString());
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
        } catch (Exception ex) {
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
            VerbLogger.getInstance().log(this.getClass(), ex.toString());
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
        }
    }
}
