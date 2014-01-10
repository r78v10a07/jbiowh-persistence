package org.jbiowhpersistence.datasets.gene.genebank.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the GeneBankCDSLocation entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 2, 2013
 */
@Embeddable
public class GeneBankCDSLocation implements Serializable {

    @Basic(optional = false)
    @Column(name = "pFrom")
    private Integer pFrom;
    @Basic(optional = false)
    @Column(name = "pTo")
    private Integer pTo;

    public GeneBankCDSLocation() {
    }

    public GeneBankCDSLocation(Integer pFrom, Integer pTo) {
        this.pFrom = pFrom;
        this.pTo = pTo;
    }

    public Integer getpFrom() {
        return pFrom;
    }

    public void setpFrom(Integer pFrom) {
        this.pFrom = pFrom;
    }

    public Integer getpTo() {
        return pTo;
    }

    public void setpTo(Integer pTo) {
        this.pTo = pTo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.pFrom != null ? this.pFrom.hashCode() : 0);
        hash = 97 * hash + (this.pTo != null ? this.pTo.hashCode() : 0);
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
        final GeneBankCDSLocation other = (GeneBankCDSLocation) obj;
        if (this.pFrom != other.pFrom && (this.pFrom == null || !this.pFrom.equals(other.pFrom))) {
            return false;
        }
        return this.pTo == other.pTo || (this.pTo != null && this.pTo.equals(other.pTo));
    }

    @Override
    public String toString() {
        return "GeneBankCDSLocation{" + "pFrom=" + pFrom + ", pTo=" + pTo + '}';
    }
}
