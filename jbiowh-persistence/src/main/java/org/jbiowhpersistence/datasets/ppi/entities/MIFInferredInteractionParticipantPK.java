package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the MIFInferredInteractionParticipantPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Embeddable
public class MIFInferredInteractionParticipantPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MIFInteractionInferredInteraction_WID")
    private long mIFInteractionInferredInteractionWID;
    @Basic(optional = false)
    @Column(name = "Participant")
    private int participant;

    public MIFInferredInteractionParticipantPK() {
    }

    public MIFInferredInteractionParticipantPK(long mIFInteractionInferredInteractionWID, int participant) {
        this.mIFInteractionInferredInteractionWID = mIFInteractionInferredInteractionWID;
        this.participant = participant;
    }

    public long getMIFInteractionInferredInteractionWID() {
        return mIFInteractionInferredInteractionWID;
    }

    public void setMIFInteractionInferredInteractionWID(long mIFInteractionInferredInteractionWID) {
        this.mIFInteractionInferredInteractionWID = mIFInteractionInferredInteractionWID;
    }

    public int getParticipant() {
        return participant;
    }

    public void setParticipant(int participant) {
        this.participant = participant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mIFInteractionInferredInteractionWID;
        hash += (int) participant;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MIFInferredInteractionParticipantPK)) {
            return false;
        }
        MIFInferredInteractionParticipantPK other = (MIFInferredInteractionParticipantPK) object;
        if (this.mIFInteractionInferredInteractionWID != other.mIFInteractionInferredInteractionWID) {
            return false;
        }
        if (this.participant != other.participant) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.mif25.entities.MIFInferredInteractionParticipantPK[ mIFInteractionInferredInteractionWID=" + mIFInteractionInferredInteractionWID + ", participant=" + participant + " ]";
    }
}
