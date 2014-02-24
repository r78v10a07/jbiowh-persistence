package org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the ProtClustPMID
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 13, 2014
 */
@Embeddable
@Table(name = "ProtClustPMID")
@XmlRootElement
public class ProtClustPMID implements Serializable {

    @Basic(optional = false)
    @Column(name = "PMID")
    private long pmid;

    public ProtClustPMID() {
    }

    public ProtClustPMID(long pmid) {
        this.pmid = pmid;
    }

    public long getPmid() {
        return pmid;
    }

    public void setPmid(long pmid) {
        this.pmid = pmid;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.pmid ^ (this.pmid >>> 32));
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
        final ProtClustPMID other = (ProtClustPMID) obj;
        return this.pmid == other.pmid;
    }

    @Override
    public String toString() {
        return "ProtClustPMID{" + "pmid=" + pmid + '}';
    }
}
