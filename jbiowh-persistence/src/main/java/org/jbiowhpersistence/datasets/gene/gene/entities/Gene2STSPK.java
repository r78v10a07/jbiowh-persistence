package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the Gene2STSPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Embeddable
public class Gene2STSPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "UniSTSID")
    private long uniSTSID;

    public Gene2STSPK() {
    }

    public Gene2STSPK(long geneInfoWID, long uniSTSID) {
        this.geneInfoWID = geneInfoWID;
        this.uniSTSID = uniSTSID;
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    public long getUniSTSID() {
        return uniSTSID;
    }

    public void setUniSTSID(long uniSTSID) {
        this.uniSTSID = uniSTSID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) geneInfoWID;
        hash += (int) uniSTSID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gene2STSPK)) {
            return false;
        }
        Gene2STSPK other = (Gene2STSPK) object;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if (this.uniSTSID != other.uniSTSID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.gene.entities.Gene2STSPK[ geneInfoWID=" + geneInfoWID + ", uniSTSID=" + uniSTSID + " ]";
    }
}
