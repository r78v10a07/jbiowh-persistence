package org.jbiowhpersistence.utils.entitymanager;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.utils.BioWHTime;
import org.jbiowhpersistence.datasets.dataset.controller.DataSetJpaController;

/**
 * This Class is to handled the systems DBMS connections
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-09-25 00:06:19 +0200
 * (Tue, 25 Sep 2012) $ $LastChangedRevision: 396 $
 *
 * @since Jun 17, 2011
 */
public class JBioWHPersistence {

    private String mainURL;
    private Map<String, JBioWHUserData> schemas;
    private Map<String, EntityManagerFactory> entityManagers;
    private Thread testThread = null;
    private static JBioWHPersistence singleton;

    private JBioWHPersistence() {
        schemas = new TreeMap();
        entityManagers = new TreeMap();
    }

    /**
     * Return a JBioWHPersistence
     *
     * @return a JBioWHPersistence
     */
    public static synchronized JBioWHPersistence getInstance() {
        if (singleton == null) {
            singleton = new JBioWHPersistence();
        }
        return singleton;
    }

    public String getMainURL() {
        return mainURL;
    }

    public void setMainURL(String mainURL) {
        this.mainURL = parseURL(mainURL);
    }

    /**
     * Return the opened schemas
     *
     * @return a map with the opened schemas
     */
    public Map<String, JBioWHUserData> getSchemas() {
        return schemas;
    }

    /**
     * Return the URL without the jdbc:// prefix
     *
     * @return the URL without the jdbc:// prefix
     */
    public String getMainURLParsed() {

        if (mainURL != null) {
            return mainURL.substring(mainURL.indexOf("//") + 2);
        }
        return null;
    }

    /**
     * Open the schema provided by the JBioWHUserData
     *
     * @param factory the JBioWHUserData to be opened
     * @param startThread true to setup a thread to check the connection every
     * hour
     * @param isMain
     */
    public void openSchema(JBioWHUserData factory, boolean startThread, boolean isMain) {
        if (factory == null) {
            throw new NullPointerException("The WHDBMS Factory is not open");
        }
        VerbLogger.getInstance().log(this.getClass(), "Opening JPA connection to:");
        VerbLogger.getInstance().log(this.getClass(), "\tDriver: " + factory.getDriver());
        VerbLogger.getInstance().log(this.getClass(), "\tURL: " + factory.getUrl());
        VerbLogger.getInstance().log(this.getClass(), "\tUser: " + factory.getUser());
        VerbLogger.getInstance().log(this.getClass(), "Adding the WHDBMSFactory to the Map");
        getWHEntityManager(factory);
        schemas.put(factory.getUrl(), factory);
        if (isMain) {
            mainURL = factory.getUrl();
            if (startThread) {
                setWHEntityManager(startThread);
            }
        }
    }

    /**
     * Close the main schema
     *
     * @param startThread true to setup a thread to check the connection every
     * hour
     */
    public void closeSchema(boolean startThread) {
        VerbLogger.getInstance().log(this.getClass(), "Closing main schema");
        closeSchema(mainURL, startThread);
    }

    /**
     * Close the schema using its URL
     *
     * @param url the schema's URL
     * @param startThread true to setup a thread to check the connection every
     * hour
     */
    public void closeSchema(String url, boolean startThread) {
        VerbLogger.getInstance().log(this.getClass(), "Closing schema by url: " + url);
        closeSchema(schemas.get(parseURL(url)), startThread);
    }

    /**
     * Close all active Schemas
     *
     */
    public void closeAll() {
        for (String url : entityManagers.keySet()) {
            entityManagers.get(url).close();
        }
    }

    /**
     * Close the schema using its JBioWHUserData
     *
     * @param factory the JBioWHUserData to be closed
     * @param startThread true to setup a thread to check the connection every
     * hour
     */
    public void closeSchema(JBioWHUserData factory, boolean startThread) {
        VerbLogger.getInstance().log(this.getClass(), "Closing schema by WHDBMSFactory: " + factory);
        if (factory != null && factory.getUrl() != null) {
            VerbLogger.getInstance().log(this.getClass(), "Removing the WHDBMSFactory from the Map");
            schemas.remove(factory.getUrl());
            if (factory.getUrl().equals(mainURL)) {
                VerbLogger.getInstance().log(this.getClass(), "The WHDBMSFactory is the main schema");
                VerbLogger.getInstance().log(this.getClass(), "Closing the EntityManager: " + getWHEntityManager());
                if (startThread && testThread != null && testThread.isAlive()) {
                    testThread.interrupt();
                }
                getWHEntityManager().close();
                entityManagers.remove(mainURL);
                if (!schemas.values().isEmpty()) {
                    VerbLogger.getInstance().log(this.getClass(), "Activating the next schema as main schema");
                    setWhdbmsFactory(schemas.values().iterator().next(), startThread);
                } else {
                    mainURL = null;
                }
            }
        } else {
            throw new NullPointerException("The WHDBMS Factory is not open");
        }
    }

    /**
     * Return the JBioWHUserData from its URL
     *
     * @param url the JBioWHUserData's URL
     * @return the JBioWHUserData from its URL
     */
    public JBioWHUserData getSchema(String url) {
        return schemas.get(parseURL(url));
    }

    /**
     * Return true is the JBioWHUserData is open
     *
     * @param factory the JBioWHUserData to be checked
     * @return true is the JBioWHUserData is open
     */
    public boolean isWHDBMSFactoryOpen(JBioWHUserData factory) {
        return schemas.containsKey(factory.getUrl());
    }

    /**
     * Set the URL as main schema
     *
     * @param url the URL to be set as main schema
     * @param startThread true to setup a thread to check the connection every
     * hour
     */
    public void setAsMainSchema(String url, boolean startThread) {
        setAsMainSchema(schemas.get(parseURL(url)), startThread);
    }

    private String parseURL(String url) {
        String newUrl;
        if (url.startsWith("jdbc")) {
            newUrl = url;
        } else {
            newUrl = "jdbc:mysql://" + url;
        }
        return newUrl;
    }

    private void setAsMainSchema(JBioWHUserData factory, boolean startThread) {
        if (factory != null && factory.isJbiowhSchema()) {
            setWhdbmsFactory(factory, startThread);
        }
    }

    private void setWhdbmsFactory(JBioWHUserData factory, boolean startThread) {
        if (factory != null) {
            mainURL = factory.getUrl();
            schemas.put(factory.getUrl(), factory);
            setWHEntityManager(startThread);
        }
    }

    //TODO Change name
    /**
     * Return the DBMS Factory
     *
     * @return A JBioWHUserData object with the active DBMS connection
     */
    public JBioWHUserData getWhdbmsFactory() {
        if (mainURL != null) {
            return schemas.get(mainURL);
        }
        return null;
    }

    //TODO Change name
    /**
     * Return the Persistence EntityManagerFactory
     *
     * @return a EntityManagerFactory object
     */
    public EntityManagerFactory getWHEntityManager() {
        if (mainURL != null) {
            return entityManagers.get(mainURL);
        }
        return null;
    }

    /**
     * Return the Persistence EntityManagerFactory
     *
     * @param url
     * @return a EntityManagerFactory object
     */
    public EntityManagerFactory getWHEntityManager(String url) {
        if (url == null) {
            return getWHEntityManager();
        }
        return entityManagers.get(url);
    }

    //TODO Change name
    /**
     * Return the Persistence EntityManagerFactory for a JBioWHPersistence
     * factory<br><br>
     * Create the connection if the EntityManagerFactory does not exist
     *
     * @param factory A JBioWHUserData object with the active DBMS connection
     * @return
     */
    public EntityManagerFactory getWHEntityManager(JBioWHUserData factory) {
        if (factory != null) {
            EntityManagerFactory entityManager = entityManagers.get(factory.getUrl());
            if (entityManager == null) {
                HashMap<String, String> properties = new HashMap();
                properties.put(PersistenceUnitProperties.JDBC_URL, factory.getUrl().trim() + "?autoReconnect=true");
                properties.put(PersistenceUnitProperties.JDBC_PASSWORD, factory.getPasswd());
                properties.put(PersistenceUnitProperties.JDBC_DRIVER, factory.getDriver());
                properties.put(PersistenceUnitProperties.JDBC_USER, factory.getUser());
                properties.put(PersistenceUnitProperties.LOGGING_LEVEL, "INFO");

                entityManager = Persistence.createEntityManagerFactory(("JBioWH"), properties);
                entityManagers.put(factory.getUrl(), entityManager);
                VerbLogger.getInstance().log(this.getClass(), "Open EntityManagerFactory: " + entityManager);
            }
            return entityManager;
        }
        return null;
    }

    //TODO Change name
    /**
     * Set the Persistence EntityManagerFactory with autoReconnect=true
     */
    private void setWHEntityManager(boolean startThread) {
        if (startThread && (testThread == null || !testThread.isAlive())) {
            testThread = new Thread(new TestEntityManager());
            testThread.start();
        }
    }

    private class TestEntityManager implements Runnable {

        private DataSetJpaController instance;

        public TestEntityManager() {
            instance = new DataSetJpaController(getWHEntityManager());
        }

        @Override
        public void run() {
            while (true) {
                if (getWHEntityManager() != null && getWHEntityManager().isOpen()) {
                    try {
                        if (instance != null) {
                            instance.getDataSetCount();
                        }
                        VerbLogger.getInstance().log(this.getClass(), "JPA Check: " + BioWHTime.getInstance().currentTime() + " message: connection OK");
                        Thread.sleep(3600000);
                    } catch (InterruptedException ex) {
                        VerbLogger.getInstance().log(this.getClass(), "JPA Check: " + BioWHTime.getInstance().currentTime() + " message: " + ex.getMessage());
                    }
                } else {
                    VerbLogger.getInstance().log(this.getClass(), "JPA Check: " + BioWHTime.getInstance().currentTime() + " message: Closing test connection");
                    break;
                }
            }
            VerbLogger.getInstance().log(this.getClass(), "JPA Check at: " + BioWHTime.getInstance().currentTime() + " message: Closing test connection by break");
        }
    }
}
