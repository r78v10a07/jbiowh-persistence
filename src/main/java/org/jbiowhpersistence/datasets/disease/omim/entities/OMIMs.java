package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 24, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "oMIMs")
public class OMIMs {

    @XmlElement(name = "omim", type = OMIM.class)
    private List<OMIM> omims;

    /**
     * Get the value of omims
     *
     * @return the value of omims
     */
    public List<OMIM> getOmims() {
        return omims;
    }

    /**
     * Set the value of omims
     *
     * @param omims new value of omims
     */
    public void setOmims(List<OMIM> omims) {
        this.omims = omims;
    }

    public OMIMs() {
    }

}
