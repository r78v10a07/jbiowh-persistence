package org.jbiowhpersistence.datasets.protein.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the XML Protein class to hold an Protein array
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 24, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "proteins")
public class Proteins {

    @XmlElement(name = "protein", type = Protein.class)
    private List<Protein> proteins = new ArrayList<Protein>();

    public Proteins() {
    }

    public Proteins(List<Protein> proteins) {
        this.proteins = proteins;
    }

    public List<Protein> getProteins() {
        return proteins;
    }

    public void setProteins(List<Protein> proteins) {
        this.proteins = proteins;
    }
}
