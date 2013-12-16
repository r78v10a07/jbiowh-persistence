package org.jbiowhpersistence.datasets.protgroup.pirsf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PIRSF protein data entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 11, 2013
 */
@Embeddable
@Table(name = "PIRSFProtein")
@XmlRootElement
public class PirsfProtein implements Serializable {

    @Basic(optional = false)
    @Column(name = "AccessionNumber")
    private String accessionNumber;
    @Basic(optional = false)
    @Column(name = "Status")
    private int status;
    @Basic(optional = false)
    @Column(name = "Seed")
    private int seed;

    public PirsfProtein() {
    }

    public PirsfProtein(String accessionNumber, int status, int seed) {
        this.accessionNumber = accessionNumber;
        this.status = status;
        this.seed = seed;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.accessionNumber != null ? this.accessionNumber.hashCode() : 0);
        hash = 59 * hash + this.status;
        hash = 59 * hash + this.seed;
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
        final PirsfProtein other = (PirsfProtein) obj;
        if ((this.accessionNumber == null) ? (other.accessionNumber != null) : !this.accessionNumber.equals(other.accessionNumber)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return this.seed == other.seed;
    }

    @Override
    public String toString() {
        return "PirsfProtein{" + "accessionNumber=" + accessionNumber + ", status=" + status + ", seed=" + seed + '}';
    }
}
