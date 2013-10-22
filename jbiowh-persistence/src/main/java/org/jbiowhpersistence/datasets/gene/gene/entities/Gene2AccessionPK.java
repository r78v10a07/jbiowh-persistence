package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the Gene2Accession PK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 23, 2012
 */
@Embeddable
public class Gene2AccessionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "WID")
    private long wid;
    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;

    public Gene2AccessionPK() {
    }

    public Gene2AccessionPK(long wid, long geneInfoWID) {
        this.wid = wid;
        this.geneInfoWID = geneInfoWID;
    }

    public long getWid() {
        return wid;
    }

    public void setWid(long wid) {
        this.wid = wid;
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) wid;
        hash += (int) geneInfoWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gene2AccessionPK)) {
            return false;
        }
        Gene2AccessionPK other = (Gene2AccessionPK) object;
        if (this.wid != other.wid) {
            return false;
        }
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gene2AccessionPK{" + "wid=" + wid + ", geneInfoWID=" + geneInfoWID + '}';
    }
}
