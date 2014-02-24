package org.jbiowhpersistence.datasets.ontology.entities;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the XML Ontology class to hold an Ontology array
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 24, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ontologies")
public class Ontologies {

    @XmlElement(name = "ontology", type = Ontology.class)
    private List<Ontology> ontologies;

    /**
     * Get the value of ontologies
     *
     * @return the value of ontologies
     */
    public List<Ontology> getOntologies() {
        return ontologies;
    }

    /**
     * Set the value of ontologies
     *
     * @param ontologies new value of ontologies
     */
    public void setOntologies(List<Ontology> ontologies) {
        this.ontologies = ontologies;
    }

    public Ontologies() {
    }

}
