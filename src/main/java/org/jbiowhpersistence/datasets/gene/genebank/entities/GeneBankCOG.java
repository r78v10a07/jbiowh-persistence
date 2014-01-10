package org.jbiowhpersistence.datasets.gene.genebank.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the GeneBankCOG entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 28, 2013
 */
@Embeddable
public class GeneBankCOG implements Serializable {

    @Basic(optional = false)
    @Column(name = "COGId")
    private String cogId;

    public GeneBankCOG() {
    }

    public GeneBankCOG(String cogId) {
        this.cogId = cogId;
    }

    public String getCogId() {
        return cogId;
    }

    public void setCogId(String cogId) {
        this.cogId = cogId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.cogId != null ? this.cogId.hashCode() : 0);
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
        final GeneBankCOG other = (GeneBankCOG) obj;
        return !((this.cogId == null) ? (other.cogId != null) : !this.cogId.equals(other.cogId));
    }

    @Override
    public String toString() {
        return "GeneBankCOG{" + "cogId=" + cogId + '}';
    }
}
