package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the MIFParticipantParameterPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Embeddable
public class MIFParticipantParameterPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MIFInteractionParticipant_WID")
    private long mIFInteractionParticipantWID;
    @Basic(optional = false)
    @Column(name = "TermAc")
    private String termAc;

    public MIFParticipantParameterPK() {
    }

    public MIFParticipantParameterPK(long mIFInteractionParticipantWID, String termAc) {
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
        this.termAc = termAc;
    }

    public long getMIFInteractionParticipantWID() {
        return mIFInteractionParticipantWID;
    }

    public void setMIFInteractionParticipantWID(long mIFInteractionParticipantWID) {
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
    }

    public String getTermAc() {
        return termAc;
    }

    public void setTermAc(String termAc) {
        this.termAc = termAc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mIFInteractionParticipantWID;
        hash += (termAc != null ? termAc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MIFParticipantParameterPK)) {
            return false;
        }
        MIFParticipantParameterPK other = (MIFParticipantParameterPK) object;
        if (this.mIFInteractionParticipantWID != other.mIFInteractionParticipantWID) {
            return false;
        }
        if ((this.termAc == null && other.termAc != null) || (this.termAc != null && !this.termAc.equals(other.termAc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.mif25.entities.MIFParticipantParameterPK[ mIFInteractionParticipantWID=" + mIFInteractionParticipantWID + ", termAc=" + termAc + " ]";
    }
}
