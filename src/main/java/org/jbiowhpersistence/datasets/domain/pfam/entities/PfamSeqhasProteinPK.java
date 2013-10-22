package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the PfamSeqhasProtein PK
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ 
 * $LastChangedRevision: 344 $
 * @since Nov 22, 2012
 */
@Embeddable
public class PfamSeqhasProteinPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    
    public PfamSeqhasProteinPK() {
    }

    public PfamSeqhasProteinPK(long autoPfamseq, long proteinWID) {
        this.autoPfamseq = autoPfamseq;
        this.proteinWID = proteinWID;
    }

    public long getAutoPfamseq() {
        return autoPfamseq;
    }

    public void setAutoPfamseq(long autoPfamseq) {
        this.autoPfamseq = autoPfamseq;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) autoPfamseq;
        hash += (int) proteinWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PfamSeqhasProteinPK)) {
            return false;
        }
        PfamSeqhasProteinPK other = (PfamSeqhasProteinPK) object;
        if (this.autoPfamseq != other.autoPfamseq) {
            return false;
        }
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamSeqhasProteinPK{" + "autoPfamseq=" + autoPfamseq + ", proteinWID=" + proteinWID + '}';
    }
}
