package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFInferredInteractionParticipant entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFInferredInteractionParticipant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFInferredInteractionParticipant.findAll", query = "SELECT m FROM MIFInferredInteractionParticipant m"),
    @NamedQuery(name = "MIFInferredInteractionParticipant.findByMIFInteractionInferredInteractionWID", query = "SELECT m FROM MIFInferredInteractionParticipant m WHERE m.mIFInferredInteractionParticipantPK.mIFInteractionInferredInteractionWID = :mIFInteractionInferredInteractionWID"),
    @NamedQuery(name = "MIFInferredInteractionParticipant.findByParticipant", query = "SELECT m FROM MIFInferredInteractionParticipant m WHERE m.mIFInferredInteractionParticipantPK.participant = :participant"),
    @NamedQuery(name = "MIFInferredInteractionParticipant.findByParticipantType", query = "SELECT m FROM MIFInferredInteractionParticipant m WHERE m.participantType = :participantType")})
public class MIFInferredInteractionParticipant implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MIFInferredInteractionParticipantPK mIFInferredInteractionParticipantPK;
    @Column(name = "ParticipantType")
    private String participantType;
    @ManyToOne
    @JoinColumn(name = "mIFInferredInteractionWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionInferredInteraction mifInteractionInferredInteraction;

    public MIFInferredInteractionParticipant() {
    }

    public MIFInferredInteractionParticipant(MIFInferredInteractionParticipantPK mIFInferredInteractionParticipantPK) {
        this.mIFInferredInteractionParticipantPK = mIFInferredInteractionParticipantPK;
    }

    public MIFInferredInteractionParticipant(long mIFInteractionInferredInteractionWID, int participant) {
        this.mIFInferredInteractionParticipantPK = new MIFInferredInteractionParticipantPK(mIFInteractionInferredInteractionWID, participant);
    }

    public MIFInferredInteractionParticipantPK getMIFInferredInteractionParticipantPK() {
        return mIFInferredInteractionParticipantPK;
    }

    public void setMIFInferredInteractionParticipantPK(MIFInferredInteractionParticipantPK mIFInferredInteractionParticipantPK) {
        this.mIFInferredInteractionParticipantPK = mIFInferredInteractionParticipantPK;
    }

    public MIFInferredInteractionParticipantPK getmIFInferredInteractionParticipantPK() {
        return mIFInferredInteractionParticipantPK;
    }

    public void setmIFInferredInteractionParticipantPK(MIFInferredInteractionParticipantPK mIFInferredInteractionParticipantPK) {
        this.mIFInferredInteractionParticipantPK = mIFInferredInteractionParticipantPK;
    }

    public MIFInteractionInferredInteraction getMifInteractionInferredInteraction() {
        return mifInteractionInferredInteraction;
    }

    public void setMifInteractionInferredInteraction(MIFInteractionInferredInteraction mifInteractionInferredInteraction) {
        this.mifInteractionInferredInteraction = mifInteractionInferredInteraction;
    }

    public String getParticipantType() {
        return participantType;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mIFInferredInteractionParticipantPK != null ? mIFInferredInteractionParticipantPK.hashCode() : 0);
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
        final MIFInferredInteractionParticipant other = (MIFInferredInteractionParticipant) obj;
        if (!Objects.equals(this.mIFInferredInteractionParticipantPK, other.mIFInferredInteractionParticipantPK)) {
            return false;
        }
        if (!Objects.equals(this.participantType, other.participantType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFInferredInteractionParticipant{" + "mIFInferredInteractionParticipantPK=" + mIFInferredInteractionParticipantPK + ", participantType=" + participantType + '}';
    }
}
