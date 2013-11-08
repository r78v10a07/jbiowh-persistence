package org.jbiowhpersistence.datasets.pathway.kegg.controller;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.utils.controller.AbstractJpaController;

/**
 * This class is the Abstract KEGG Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-26 16:29:19 +0100 (Mon, 26 Nov 2012) $
 * $LastChangedRevision: 323 $
 * @param <T> the class to be used
 * @since Sep 17, 2012
 */
public abstract class AbstractKEGGJpaController<T> extends AbstractJpaController<T> {

    /**
     * Return the KEGGReaction list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the KEGGReaction list the be persisted
     * @return the KEGGReaction list the be persisted
     * @throws Exception
     */
    protected Set<KEGGReaction> createKEGGReaction(EntityManagerFactory emf, EntityManager em, Set<KEGGReaction> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            KEGGReactionJpaController instance = new KEGGReactionJpaController(emf);
            Set<KEGGReaction> attachObjSet = new HashSet();
            for (KEGGReaction object : objectList) {
                KEGGReaction objectOnDB = em.find(KEGGReaction.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(KEGGReaction.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet();
    }
    
    /**
     * Return the KEGGGlycan list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the KEGGGlycan list the be persisted
     * @return the KEGGGlycan list the be persisted
     * @throws Exception
     */
    protected Set<KEGGGlycan> createKEGGGlycan(EntityManagerFactory emf, EntityManager em, Set<KEGGGlycan> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            KEGGGlycanJpaController instance = new KEGGGlycanJpaController(emf);
            Set<KEGGGlycan> attachObjSet = new HashSet();
            for (KEGGGlycan object : objectList) {
                KEGGGlycan objectOnDB = em.find(KEGGGlycan.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(KEGGGlycan.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet();
    }
}
