package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the PfamProteomeRegions PK
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ 
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Embeddable
public class PfamProteomeRegionsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PfamCompleteProteomes_WID")
    private long pfamCompleteProteomesWID;
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "PfamA_WID")
    private long pfamAWID;

    public PfamProteomeRegionsPK() {
    }

    public PfamProteomeRegionsPK(long pfamCompleteProteomesWID, long autoPfamseq, long pfamAWID) {
        this.pfamCompleteProteomesWID = pfamCompleteProteomesWID;
        this.autoPfamseq = autoPfamseq;
        this.pfamAWID = pfamAWID;
    }

    public long getPfamCompleteProteomesWID() {
        return pfamCompleteProteomesWID;
    }

    public void setPfamCompleteProteomesWID(long pfamCompleteProteomesWID) {
        this.pfamCompleteProteomesWID = pfamCompleteProteomesWID;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfamCompleteProteomesWID;
        hash += (int) autoPfamseq;
        hash += (int) pfamAWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PfamProteomeRegionsPK)) {
            return false;
        }
        PfamProteomeRegionsPK other = (PfamProteomeRegionsPK) object;
        if (this.pfamCompleteProteomesWID != other.pfamCompleteProteomesWID) {
            return false;
        }
        if (this.autoPfamseq != other.autoPfamseq) {
            return false;
        }
        if (this.pfamAWID != other.pfamAWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamProteomeRegionsPK{" + "pfamCompleteProteomesWID=" + pfamCompleteProteomesWID + ", autoPfamseq=" + autoPfamseq + ", pfamAWID=" + pfamAWID + '}';
    }
}
