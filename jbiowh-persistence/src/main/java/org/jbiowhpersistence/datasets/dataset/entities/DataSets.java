package org.jbiowhpersistence.datasets.dataset.entities;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the XML DataSet class to hold an DataSet array
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 24, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dataSets")
public class DataSets {

    @XmlElement(name = "dataSet", type = DataSet.class)
    private List<DataSet> dataSets;

    public DataSets() {
    }

    /**
     * Get the value of dataSets
     *
     * @return the value of dataSets
     */
    public List<DataSet> getDataSets() {
        return dataSets;
    }

    /**
     * Set the value of dataSets
     *
     * @param dataSets new value of dataSets
     */
    public void setDataSets(List<DataSet> dataSets) {
        this.dataSets = dataSets;
    }

}
