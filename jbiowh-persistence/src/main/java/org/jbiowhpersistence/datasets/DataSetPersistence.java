package org.jbiowhpersistence.datasets;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jbiowhcore.basic.JBioWHUserData;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.dataset.controller.DataSetJpaController;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This Class handled the datasets methods using the persistence
 *
 * $Author$
 * $LastChangedDate$
 * $LastChangedRevision$
 * @since Oct 18, 2013
 */
public class DataSetPersistence {

    private static DataSetPersistence singleton;
    private DataSet dataSet = null;
    private boolean droptables = true;
    private boolean runlinks = true;
    private String tempdir = "/tmp/";
    private String directory = null;
    private String xsdfiledef = null;
    private String type = null;

    private DataSetPersistence() {
    }

    /**
     * Return a DataSetPersistence instance
     *
     * @return a DataSetPersistence instance
     */
    public static synchronized DataSetPersistence getInstance() {
        if (singleton == null) {
            singleton = new DataSetPersistence();
        }
        return singleton;
    }
    
    /**
     * Read the Data source information from the XML file
     *
     * @param filename the absolute file name including complete path
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws SQLException
     */
    public void readDatasetFromFile(String filename)
            throws ParserConfigurationException,
            SAXException,
            IOException,
            SQLException {
        VerbLogger.getInstance().log(this.getClass(), "Reading XML file: " + filename);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));
        dataSet = new DataSet();

        NodeList nodes = document.getElementsByTagName(XMLROOT);
        if (getElement(XMLVERBOSE, (Element) nodes.item(0)).equalsIgnoreCase("info")) {
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().INFO);
        } else if (getElement(XMLVERBOSE, (Element) nodes.item(0)).equalsIgnoreCase("debug")) {
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().DEBUG);
        } else {
            VerbLogger.getInstance().setLevel(0);
        }
        VerbLogger.getInstance().log(this.getClass(), "Setting variables from the XML file");
        dataSet.setName(getElement(XMLNAME, (Element) nodes.item(0)));
        dataSet.setVersion(getElement(XMLVERSION, (Element) nodes.item(0)));
        dataSet.setHomeURL(getElement(XMLHOMEURL, (Element) nodes.item(0)));
        try {
            dataSet.setReleaseDate(DateFormat.getDateInstance(DateFormat.SHORT).parse(
                    getElement(XMLRELEASEDATE, (Element) nodes.item(0))));
            dataSet.setLoadDate(new Date());
            dataSet.setChangeDate(new Date());
            dataSet.setLoadedBy(getElement(XMLDBUSER, (Element) nodes.item(0)));
            dataSet.setStatus("created");
        } catch (ParseException ex) {
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
            VerbLogger.getInstance().log(this.getClass(), ex.toString());
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
        }

        if (getElement(XMLDRIVER, (Element) nodes.item(0)).contains("mysql")) {            
            JBioWHPersistence.getInstance().openSchema(new JBioWHUserData(getElement(XMLDRIVER, (Element) nodes.item(0)),
                    getElement(XMLURL, (Element) nodes.item(0)) + getElement(XMLDATABASE, (Element) nodes.item(0)),
                    getElement(XMLDBUSER, (Element) nodes.item(0)),
                    getElement(XMLDBPASSWD, (Element) nodes.item(0)), true), true, true);
        }

        setType(getElement(XMLTYPE, (Element) nodes.item(0)));
        setDirectory(getElement(XMLDIRECTORY, (Element) nodes.item(0)));
        setTempdir(getElement(XMLTEMPDIR, (Element) nodes.item(0)));
        setXsdfiledef(getElement(XMLXSDFILEDEF, (Element) nodes.item(0)));

        if (getElement(XMLDROPTABLES, (Element) nodes.item(0)).equals("true")) {
            setDroptables(true);
        } else {
            setDroptables(false);
        }

        if (getElement(XMLRUNLINKS, (Element) nodes.item(0)).equals("true")) {
            setRunlinks(true);
        } else {
            setRunlinks(false);
        }
    }

    /**
     * Check if the DataSet is inserted
     *
     * @return a Boolean
     */
    public boolean isDataSetInserted() {
        HashMap<String, Object> parameters = new HashMap();
        DataSetJpaController instance = new DataSetJpaController(JBioWHPersistence.getInstance().getWHEntityManager());

        if (instance.getDataSetCount() == 0) {
            return false;
        }

        parameters.put("name", dataSet.getName());

        List<DataSet> result = instance.useNamedQuery("DataSet.findByName", parameters);
        if (result.isEmpty()) {
            return false;
        }
        dataSet.setWid(result.get(0).getWid());
        return true;
    }

    /**
     * Insert the DataSet into the database
     *
     * @return a boolean value which is true if the dataset was inserted and
     * false otherwise
     */
    public boolean insertDataSet() {
        if (!isDataSetInserted()) {
            DataSetJpaController instance = new DataSetJpaController(JBioWHPersistence.getInstance().getWHEntityManager());

            Long maxWID = (Long) instance.useNamedQuerySingleResult("DataSet.findMaxWID");
            if (maxWID == null) {
                maxWID = new Long(0L);
            }
            dataSet.setWid(maxWID + 1);

            VerbLogger.getInstance().log(this.getClass(), "Inserting DataSet: " + dataSet.getName() + " with WID = " + dataSet.getWid());

            try {
                instance.create(dataSet);
            } catch (PreexistingEntityException ex) {
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                VerbLogger.getInstance().log(this.getClass(), ex.toString());
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
            } catch (Exception ex) {
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                VerbLogger.getInstance().log(this.getClass(), ex.toString());
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
            }
            return true;
        } else {
            VerbLogger.getInstance().log(this.getClass(), "DataSet: " + dataSet.getName() + " is inserted with WID = " + dataSet.getWid());
        }
        return false;
    }

    /**
     * Update the DataSet into the database
     */
    public void updateDataSet() {
        if (isDataSetInserted()) {
            DataSetJpaController instance = new DataSetJpaController(JBioWHPersistence.getInstance().getWHEntityManager());

            VerbLogger.getInstance().log(this.getClass(), "Updating DataSet: " + dataSet.getName() + " with WID = " + dataSet.getWid());

            try {
                instance.edit(dataSet);
            } catch (NonexistentEntityException ex) {
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                VerbLogger.getInstance().log(this.getClass(), ex.toString());
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
            } catch (Exception ex) {
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                VerbLogger.getInstance().log(this.getClass(), ex.toString());
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
            }
        } else {
            insertDataSet();
        }
    }    

    /**
     * Return the DataSet object loaded
     *
     * @return a DataSet entity object
     */
    public DataSet getDataset() {
        return dataSet;
    }

    /**
     * Set the DataSet object loaded
     *
     * @param ds a DataSet entity object
     */
    public void setDataset(DataSet ds) {
        dataSet = ds;
    }

    /**
     * Return the data source directory
     *
     * @return a String object with the data source directory
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Set the data source directory
     *
     * @param dir a String object with the data source directory
     */
    public void setDirectory(String dir) {
        if (File.separator.equals("\\")) {
            dir = dir.replace("\\", "/");
        }
        if ((new File(dir)).isDirectory() && !dir.endsWith("/")) {
            directory = dir + "/";
        } else {
            directory = dir;
        }
    }

    /**
     * Return the drop tables parameter
     *
     * @return a boolean
     */
    public boolean isDroptables() {
        return droptables;
    }

    /**
     * Set the drop tables parameter
     *
     * @param dptables
     */
    public void setDroptables(boolean dptables) {
        droptables = dptables;
    }

    /**
     * Return true if the parser have to run the DataSet's links
     *
     * @return true if the parser have to run the DataSet's links, false
     * otherwise
     */
    public boolean isRunlinks() {
        return runlinks;
    }

    /**
     * Set the runlinks flag.
     *
     * @param runlinks true if the parser have to run the DataSet's links, false
     * otherwise
     */
    public void setRunlinks(boolean runlinks) {
        this.runlinks = runlinks;
    }

    /**
     * Return the temporal directory use to parse the data source
     *
     * @return a String object with the temporal directory
     */
    public String getTempdir() {
        return tempdir;
    }

    /**
     * Set the temporal directory use to parse the data source
     *
     * @param tmpdir a String object with the temporal directory
     */
    public void setTempdir(String tmpdir) {
        if (tmpdir != null) {
            File d = new File(tmpdir);
            if (!d.exists()) {
                VerbLogger.getInstance().log(this.getClass(), "Temporal dir: " + tmpdir + " does not exist");
                if (!d.mkdir()) {
                    VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                    VerbLogger.getInstance().log(this.getClass(), "Can't create temporal dir: " + tmpdir);
                    System.exit(1);
                }
            }
            if (d.isDirectory()) {
                if (File.separator.equals("\\")) {
                    tmpdir = tmpdir.replace("\\", "/");
                }
                if (!tmpdir.endsWith("/")) {
                    tempdir = tmpdir + "/";
                } else {
                    tempdir = tmpdir;
                }
            } else {
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                VerbLogger.getInstance().log(this.getClass(), "Temporal dir: " + tmpdir + " is not a directory");
                VerbLogger.getInstance().log(this.getClass(), "Setting temporal dir: " + "/tmp");
                tempdir = "/tmp/";
                VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
            }
        } else {
            tempdir = "/tmp/";
        }
    }

    /**
     * Return the data source type
     *
     * @return a String object with the data source type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the data source type
     *
     * @param type a String object with the data source type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Return the Xsdfiledef parameter
     *
     * @return a String with the Xsdfiledef parameter
     */
    public String getXsdfiledef() {
        return xsdfiledef;
    }

    /**
     * Set the Xsdfiledef parameter
     *
     * @param xsdfiledef a String with the Xsdfiledef parameter
     */
    public void setXsdfiledef(String xsdfiledef) {
        this.xsdfiledef = xsdfiledef;
    }

    /**
     * Get the character data in the XML element name from node 0
     *
     * @return the character data in the XML element name
     */
    private String getElement(String name, Element element) throws NullPointerException {
        NodeList namenode = element.getElementsByTagName(name);
        if (namenode == null) {
            throw new NullPointerException("Element '" + name + "' is not on XML config file");
        }
        if (namenode.getLength() == 0) {
            throw new NullPointerException("Element '" + name + "' is not on XML config file");
        }

        Element line = (Element) namenode.item(0);

        Node child = line.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return null;
    }

    @Override
    public String toString() {
        return "DataSet insert information: \n\n"
                + "Drop tables = " + droptables + "\n"
                + "Directory = " + directory + "\n"
                + "Tempdir = " + tempdir + "\n"
                + "xsdfiledef = " + xsdfiledef + "\n\n";
    }
        
    /**
     * Load all DataSets into a Object[][] object
     *
     * @return a Object[][] object with the all DataSets data
     */
    public List<List<Object>> loadDataSet() {
        return loadDataSet(JBioWHPersistence.getInstance().getWHEntityManager());
    }

    /**
     * Load all DataSets into a Object[][] object
     *
     * @param emf the Entity Manager Factory
     * @return a Object[][] object with the all DataSets data
     */
    public List<List<Object>> loadDataSet(EntityManagerFactory emf) {
        ArrayList<List<Object>> arrayList = new ArrayList();

        if (emf != null) {
            DataSetJpaController instance = new DataSetJpaController(emf);
            List<DataSet> result = instance.useNamedQuery("DataSet.findAll");

            if (!result.isEmpty()) {
                for (DataSet dataset : result) {
                    ArrayList<Object> list = new ArrayList();
                    list.add(dataset.getName());
                    list.add(DateFormat.getDateInstance().format(dataset.getReleaseDate()));
                    list.add(DateFormat.getDateInstance().format(dataset.getLoadDate()));
                    list.add(DateFormat.getDateInstance().format(dataset.getChangeDate()));
                    list.add(dataset.getHomeURL());
                    list.add(dataset.getApplication());
                    list.add(dataset.getStatus());
                    arrayList.add(list);
                }
                result.clear();
            }
        }
        return arrayList;
    }
    
    /**
     * WHDataSet XML Tag Definitions
     */
    private final String XMLROOT = "warehouse";
    private final String XMLNAME = "name";
    private final String XMLTYPE = "type";
    private final String XMLVERSION = "version";
    private final String XMLHOMEURL = "homeurl";
    private final String XMLRELEASEDATE = "releaseDate";
    private final String XMLDATABASE = "database";
    private final String XMLDBUSER = "dbuser";
    private final String XMLDBPASSWD = "dbpassword";
    private final String XMLDIRECTORY = "directory";
    private final String XMLTEMPDIR = "temporal";
    private final String XMLDRIVER = "driver";
    private final String XMLURL = "url";
    private final String XMLXSDFILEDEF = "xsdfiledef";
    private final String XMLVERBOSE = "verbose";
    private final String XMLDROPTABLES = "droptables";
    private final String XMLRUNLINKS = "runlinks";
 }
