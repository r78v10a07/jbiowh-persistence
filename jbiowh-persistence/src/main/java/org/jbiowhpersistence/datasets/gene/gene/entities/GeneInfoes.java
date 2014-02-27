package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the XML GeneInfo class to hold an GeneInfo array
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 24, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "geneInfoes")
public class GeneInfoes {

    @XmlElement(name = "geneInfo", type = GeneInfo.class)
    private List<GeneInfo> geneInfoes = new ArrayList<GeneInfo>();

    public GeneInfoes() {
    }

    public GeneInfoes(List<GeneInfo> geneInfoes) {
        this.geneInfoes = geneInfoes;
    }

    public List<GeneInfo> getGeneInfoes() {
        return geneInfoes;
    }

    public void setGeneInfoes(List<GeneInfo> genePTTs) {
        this.geneInfoes = genePTTs;
    }
}
