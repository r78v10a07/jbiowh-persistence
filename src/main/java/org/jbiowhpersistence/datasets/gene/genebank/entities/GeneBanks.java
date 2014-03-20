package org.jbiowhpersistence.datasets.gene.genebank.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the XML GeneBank class to hold an GeneBank array
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 28, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "genebanks")
public class GeneBanks {

    @XmlElement(name = "genebank", type = GeneBank.class)
    private List<GeneBank> genebanks = new ArrayList<GeneBank>();

    public GeneBanks() {
    }

    public GeneBanks(List<GeneBank> genebanks) {
        this.genebanks = genebanks;
    }

    public List<GeneBank> getGeneBanks() {
        return genebanks;
    }

    public void setGeneBanks(List<GeneBank> genebanks) {
        this.genebanks = genebanks;
    }
}
