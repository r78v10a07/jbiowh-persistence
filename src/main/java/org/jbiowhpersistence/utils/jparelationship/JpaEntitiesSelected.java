package org.jbiowhpersistence.utils.jparelationship;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;

/**
 * This class is used to storage the JBioWH entities  
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Sep 24, 2012
 */
public class JpaEntitiesSelected {

    private static JpaEntitiesSelected singleton;
    private List<Class> entities;
    private List<String> loadedByDefault;

    private JpaEntitiesSelected() {
        entities = new ArrayList();
        loadedByDefault = new ArrayList(
            Arrays.asList(new String[]{"DataSet", "Taxonomy", "Ontology"}));
    }

    /**
     * Return the JBioWH entity's names that always are inserted during the
     * JBioWH copy process
     *
     * @return the JBioWH entity's names that always are inserted during the
     * JBioWH copy process
     */
    public List<String> getLoadedByDefault() {
        return loadedByDefault;
    }

    /**
     * Return the list of entities to be inserted on the JBioWH copy process
     *
     * @return the list of entities to be inserted on the JBioWH copy process
     */
    public List<Class> getEntities() {
        return entities;
    }

    /**
     * Add a JBioWH entity to be inserted on the JBioWH copy process
     *
     * @param entity the entity class
     */
    public void addEntity(Class entity) {
        entities.add(entity);
    }

    /**
     * Add a JBioWH entity to be inserted on the JBioWH copy process
     *
     * @param entity a String object with the entity name
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public void addEntity(Object entity) throws ClassNotFoundException, NoSuchFieldException {
        addEntity(entity, JBioWHPersistence.getInstance().getWHEntityManager());
    }

    /**
     * Add a JBioWH entity to be inserted on the JBioWH copy process
     *
     * @param entity a String object with the entity name
     * @param emf the entity manager factory
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public void addEntity(Object entity, EntityManagerFactory emf) throws ClassNotFoundException, NoSuchFieldException {
        Metamodel metamodel = emf.getMetamodel();
        for (EntityType e : metamodel.getEntities()) {
            Type type = e.getJavaType();
            String[] classesNames = type.toString().split(" ");
            if (classesNames.length == 2 && classesNames[1].endsWith("." + entity.toString())) {
                Class c = Class.forName(classesNames[1], false, this.getClass().getClassLoader());
                addEntity(c);
                break;
            }
        }
    }

    /**
     * Remove the JBioWH entity from the list of entities that will be inserted
     * on the JBioWH copy process
     *
     * @param entity the entity class
     */
    public void rmEntity(Class entity) {
        entities.remove(entity);
    }

    /**
     * Remove the JBioWH entity from the list of entities that will be inserted
     * on the JBioWH copy process
     *
     * @param entity a String object with the entity name
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public void rmEntity(Object entity) throws ClassNotFoundException, NoSuchFieldException {
        rmEntity(entity, JBioWHPersistence.getInstance().getWHEntityManager());
    }

    /**
     * Remove the JBioWH entity from the list of entities that will be inserted
     * on the JBioWH copy process
     *
     * @param entity a String object with the entity name
     * @param emf the entity manager factory
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public void rmEntity(Object entity, EntityManagerFactory emf) throws ClassNotFoundException, NoSuchFieldException {
        Metamodel metamodel = emf.getMetamodel();
        for (EntityType e : metamodel.getEntities()) {
            Type type = e.getJavaType();
            String[] classesNames = type.toString().split(" ");
            if (classesNames.length == 2 && classesNames[1].endsWith("." + entity.toString())) {
                Class c = Class.forName(classesNames[1], false, this.getClass().getClassLoader());
                rmEntity(c);
                break;
            }
        }
    }

    /**
     * Return a JpaEntitiesSelected instance
     *
     * @return a JpaEntitiesSelected instance
     */
    public static synchronized JpaEntitiesSelected getInstance() {
        if (singleton == null) {
            singleton = new JpaEntitiesSelected();
        }
        return singleton;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Class a : entities){
            s.append("\t").append(a).append("\n");
        }
        return "Selected class:\n" + s.toString();
    }
}
