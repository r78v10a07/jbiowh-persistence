package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the PfamARegSeed PK
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ 
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Embeddable
public class PfamARegSeedPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "PfamA_WID")
    private long pfamAWID;
    @Basic(optional = false)
    @Column(name = "tree_order")
    private int treeOrder;

    public PfamARegSeedPK() {
    }

    public PfamARegSeedPK(long autoPfamseq, long pfamAWID, int treeOrder) {
        this.autoPfamseq = autoPfamseq;
        this.pfamAWID = pfamAWID;
        this.treeOrder = treeOrder;
    }

    public long getAutoPfamseq() {
        return autoPfamseq;
    }

    public void setAutoPfamseq(long autoPfamseq) {
        this.autoPfamseq = autoPfamseq;
    }

    public long getPfamAWID() {
        return pfamAWID;
    }

    public void setPfamAWID(long pfamAWID) {
        this.pfamAWID = pfamAWID;
    }

    public int getTreeOrder() {
        return treeOrder;
    }

    public void setTreeOrder(int treeOrder) {
        this.treeOrder = treeOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) autoPfamseq;
        hash += (int) pfamAWID;
        hash += (int) treeOrder;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PfamARegSeedPK)) {
            return false;
        }
        PfamARegSeedPK other = (PfamARegSeedPK) object;
        if (this.autoPfamseq != other.autoPfamseq) {
            return false;
        }
        if (this.pfamAWID != other.pfamAWID) {
            return false;
        }
        if (this.treeOrder != other.treeOrder) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamARegSeedPK{" + "autoPfamseq=" + autoPfamseq + ", pfamAWID=" + pfamAWID + ", treeOrder=" + treeOrder + '}';
    }
}
