package org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the ProtClustEC entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 13, 2014
 */
@Embeddable
@Table(name = "ProtClustEC")
@XmlRootElement
public class ProtClustEC implements Serializable {

    @Basic(optional = false)
    @Column(name = "EC")
    private String ec;

    public ProtClustEC() {
    }

    public ProtClustEC(String ec) {
        this.ec = ec;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.ec != null ? this.ec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProtClustEC other = (ProtClustEC) obj;
        if ((this.ec == null) ? (other.ec != null) : !this.ec.equals(other.ec)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProtClustEC{" + "ec=" + ec + '}';
    }
}
