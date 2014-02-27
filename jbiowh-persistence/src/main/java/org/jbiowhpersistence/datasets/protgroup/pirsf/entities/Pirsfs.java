package org.jbiowhpersistence.datasets.protgroup.pirsf.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the XML PirSF class to hold an PirSF array
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 24, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pirsfs")
public class Pirsfs {

    @XmlElement(name = "pirsf", type = Pirsf.class)
    private List<Pirsf> proteins = new ArrayList<Pirsf>();

    public Pirsfs() {
    }

    public Pirsfs(List<Pirsf> proteins) {
        this.proteins = proteins;
    }

    public List<Pirsf> getPirsfs() {
        return proteins;
    }

    public void setPirsfs(List<Pirsf> proteins) {
        this.proteins = proteins;
    }
}
