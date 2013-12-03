package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is Gene2PMID entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jul 27, 2011
 */
@Embeddable
@Table(name = "Gene2PMID")
@XmlRootElement
public class Gene2PMID implements Serializable {

    @Basic(optional = false)
    @Column(name = "PMID")
    private long pmid;

    public Gene2PMID() {
    }

    public Gene2PMID(long pmid) {
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
        int hash = 5;
        hash = 19 * hash + (int) (this.pmid ^ (this.pmid >>> 32));
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
        final Gene2PMID other = (Gene2PMID) obj;
        return this.pmid == other.pmid;
    }

    @Override
    public String toString() {
        return "Gene2PMID{pmid=" + pmid + '}';
    }
}
