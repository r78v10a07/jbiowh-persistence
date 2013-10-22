package org.jbiowhpersistence.utils.msms;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.xml.bind.JAXBException;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 * This interface is the MS parser factory
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Sep 5, 2013
 */
public interface MSFactory {

    /**
     * Get the protein list extracted from the MS XML result file
     *
     * @return the protein list
     */
    public LinkedList<Map.Entry<String, List<Protein>>> getProteinList();

    /**
     * Get the proteins id not found in the JBioWH relational schema
     *
     * @return the protein list
     */
    public LinkedList<String> getNotFound();

    /**
     * Parse the XML file and search the proteins using the EntityManagerFactory
     *
     * @param xmlFile the XML file
     * @param factory the EntityManagerFactory
     * @throws IllegalAccessException
     * @throws javax.xml.bind.JAXBException
     */
    public void parseXML(File xmlFile, EntityManagerFactory factory) throws IllegalAccessException, JAXBException;
}
