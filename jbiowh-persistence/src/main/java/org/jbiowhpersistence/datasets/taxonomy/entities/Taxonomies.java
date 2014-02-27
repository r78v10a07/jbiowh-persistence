package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the XML Taxonomy class to hold an Taxonomy array
 *
 * $Author$ 
 * $LastChangedDate$ 
 * $LastChangedRevision$
 * @since Feb 26, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "taxonomies")
public class Taxonomies {

    @XmlElement(name = "taxonomy", type = Taxonomy.class)
    private List<Taxonomy> taxonomies = new ArrayList<Taxonomy>();

    public Taxonomies() {
    }

    public Taxonomies(List<Taxonomy> proteins) {
        this.taxonomies = proteins;
    }

    public List<Taxonomy> getTaxonomys() {
        return taxonomies;
    }

    public void setTaxonomys(List<Taxonomy> proteins) {
        this.taxonomies = proteins;
    }
}
