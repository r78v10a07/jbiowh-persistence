package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the Gene2PMIDPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Embeddable
public class Gene2PMIDPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "PMID")
    private long pmid;

    public Gene2PMIDPK() {
    }

    public Gene2PMIDPK(long geneInfoWID, long pmid) {
        this.geneInfoWID = geneInfoWID;
        this.pmid = pmid;
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    public long getPmid() {
        return pmid;
    }

    public void setPmid(long pmid) {
        this.pmid = pmid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) geneInfoWID;
        hash += (int) pmid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gene2PMIDPK)) {
            return false;
        }
        Gene2PMIDPK other = (Gene2PMIDPK) object;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if (this.pmid != other.pmid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.gene.entities.Gene2PMIDPK[ geneInfoWID=" + geneInfoWID + ", pmid=" + pmid + " ]";
    }
}
