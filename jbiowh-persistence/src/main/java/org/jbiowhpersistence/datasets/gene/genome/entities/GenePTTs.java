package org.jbiowhpersistence.datasets.gene.genome.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the XML GenePTT class to hold an GenePTT array
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 24, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "genePTTs")
public class GenePTTs {

    @XmlElement(name = "genePTT", type = GenePTT.class)
    private List<GenePTT> genePTTs = new ArrayList<GenePTT>();

    public GenePTTs() {
    }

    public GenePTTs(List<GenePTT> genePTTs) {
        this.genePTTs = genePTTs;
    }

    public List<GenePTT> getGenePTTs() {
        return genePTTs;
    }

    public void setGenePTTs(List<GenePTT> genePTTs) {
        this.genePTTs = genePTTs;
    }
}
