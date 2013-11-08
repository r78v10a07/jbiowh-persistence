package org.jbiowhpersistence.utils.jparelationship;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import org.jbiowhcore.utility.graph.HideEdge;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.jgrapht.Graph;
import org.jgrapht.graph.ListenableDirectedGraph;

/**
 * This a class to handled JPA external relationship between the JBioWH entities
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-30 18:40:01 +0100 (Sat, 30 Mar 2013) $ 
 * $LastChangedRevision: 548 $
 * @since Sep 19, 2012
 */
public class JpaRelationship {

    private static JpaRelationship singleton;
    private Graph graph;

    private JpaRelationship() {
    }

    /**
     * Return a JpaRelationship instance
     *
     * @return a JpaRelationship instance
     */
    public static synchronized JpaRelationship getInstance() {
        if (singleton == null) {
            singleton = new JpaRelationship();
        }
        return singleton;
    }

    /**
     * Return the graph generated for the entities classes
     *
     * @return the graph generated for the entities classes
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public Graph getGraph() throws ClassNotFoundException, NoSuchFieldException {
        if (graph == null) {
            createAllExternalJpaRelationship(JBioWHPersistence.getInstance().getWHEntityManager());
        }
        return graph;
    }

    /**
     * Create a graph for the entities classes starting on the target entity
     * class
     *
     * @param target the entity class to start the graph
     * @param emf the entity manager factory
     * @return a graph for the entities classes starting on the target entity
     * class
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public Graph createExternalJpaRelationshipGraph(Class target, EntityManagerFactory emf)
            throws ClassNotFoundException, NoSuchFieldException {
        graph = new ListenableDirectedGraph(HideEdge.class);
        addJpaRelationshipGraph(target, emf);
        return graph;
    }

    /**
     * Create a graph with all entities classes relationship
     *
     * @param emf the entity manager factory
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public void createAllExternalJpaRelationship(EntityManagerFactory emf)
            throws ClassNotFoundException, NoSuchFieldException {
        graph = new ListenableDirectedGraph(HideEdge.class);
        Metamodel metamodel = emf.getMetamodel();
        for (EntityType e : metamodel.getEntities()) {
            Type type = e.getJavaType();
            String[] classesNames = type.toString().split(" ");
            if (classesNames.length == 2) {
                Class c = Class.forName(classesNames[1], false, this.getClass().getClassLoader());
                addJpaRelationshipGraph(c, emf);
            }
        }
        List toRemove = new ArrayList();
        for (Object c : graph.vertexSet()) {
            if (graph.edgesOf(c).isEmpty()) {
                toRemove.add(c);
            }
        }
        for (Object c : toRemove) {
            graph.removeVertex(c);
        }
    }

    /**
     * Add to the graph the entities classes relationship for this class
     *
     * @param target the entity class to start the graph
     * @param emf the entity manager factory
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public void addJpaRelationshipGraph(Class target, EntityManagerFactory emf)
            throws ClassNotFoundException, NoSuchFieldException {
        addVertex(target, emf);
    }

    private void addVertex(Class target, EntityManagerFactory emf)
            throws ClassNotFoundException, NoSuchFieldException {
        graph.addVertex(target.getSimpleName());
        Map<String, Class> classes = getJpaExternalRelationship(target, emf);
        for (String c : classes.keySet()) {
            if (!graph.containsVertex(classes.get(c).getSimpleName())) {
                graph.addVertex(classes.get(c).getSimpleName());
                graph.addEdge(target.getSimpleName(), classes.get(c).getSimpleName());
                addVertex(classes.get(c), emf);
            } else {
                graph.addEdge(target.getSimpleName(), classes.get(c).getSimpleName());
            }
        }
    }

    /**
     * Return a map with the external relationship fields and its class for a
     * JBioWH entity
     *
     * @param target the JPA entity
     * @param emf the entity manager factory
     * @return a map with the external relationship fields and its class for a
     * JBioWH entity
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public Map<String, Class> getJpaExternalRelationship(Class target, EntityManagerFactory emf)
            throws ClassNotFoundException, NoSuchFieldException {
        return getJpaExternalRelationship(target, emf.createEntityManager());
    }

    /**
     * Return a map with the external relationship fields and its class for a
     * JBioWH entity
     *
     * @param target the JPA entity
     * @param em the entity manager instance
     * @return a map with the external relationship fields and its class for a
     * JBioWH entity
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public Map<String, Class> getJpaExternalRelationship(Class target, EntityManager em)
            throws ClassNotFoundException, NoSuchFieldException {
        Map<String, Class> classes = new HashMap();
        Metamodel metamodel = em.getMetamodel();
        ManagedType<?> targetType = metamodel.managedType(target);
        Set attributes = targetType.getDeclaredAttributes();
        for (Attribute a : (Set<Attribute>) attributes) {
            if (a.getPersistentAttributeType() != Attribute.PersistentAttributeType.BASIC
                    && a.getPersistentAttributeType() != Attribute.PersistentAttributeType.EMBEDDED) {
                classes.putAll(genericType(target, a.getName()));
            }
        }
        return classes;
    }

    /**
     * Return a map with the external relationship fields and its class for a
     * JBioWH entity
     *
     * @param target the JPA entity
     * @param name the field name
     * @return a map with the external relationship fields and its class for a
     * JBioWH entity
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public Map<String, Class> genericType(Class target, String name)
            throws ClassNotFoundException, NoSuchFieldException {
        Map<String, Class> classes = new HashMap();
        Type type = target.getDeclaredField(name).getGenericType();

        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            for (Type t : pt.getActualTypeArguments()) {
                String[] classesNames = t.toString().split(" ");
                if (classesNames.length == 2) {
                    Class c = Class.forName(classesNames[1], false, this.getClass().getClassLoader());
                    if (!target.getPackage().equals(c.getPackage())) {
                        classes.put(name, c);
                    }
                }
            }
        } else {
            String[] classesNames = type.toString().split(" ");
            if (classesNames.length == 2) {
                Class c = Class.forName(classesNames[1], false, this.getClass().getClassLoader());
                if (!target.getPackage().equals(c.getPackage())) {
                    classes.put(name, c);
                }
            }
        }
        return classes;
    }
}
