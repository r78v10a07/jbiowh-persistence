package org.jbiowhpersistence.datasets.protein.entities;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This class is
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 24, 2014
 */
@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Proteins }
     *
     */
    public Proteins createProteins() {
        return new Proteins();
    }

    /**
     * Create an instance of {@link Protein }
     *
     */
    public Protein createProtein() {
        return new Protein();
    }
}
