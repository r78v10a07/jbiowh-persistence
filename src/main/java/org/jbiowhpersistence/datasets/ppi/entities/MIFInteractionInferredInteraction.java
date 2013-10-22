package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFInteractionInferredInteraction entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFInteractionInferredInteraction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFInteractionInferredInteraction.findAll", query = "SELECT m FROM MIFInteractionInferredInteraction m"),
    @NamedQuery(name = "MIFInteractionInferredInteraction.findByWid", query = "SELECT m FROM MIFInteractionInferredInteraction m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFInteractionInferredInteraction.findByMIFEntryInteractionWID", query = "SELECT m FROM MIFInteractionInferredInteraction m WHERE m.mIFEntryInteractionWID = :mIFEntryInteractionWID")})
public class MIFInteractionInferredInteraction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFEntryInteraction_WID")
    private long mIFEntryInteractionWID;
    @ManyToOne
    @JoinColumn(name = "MIFEntryInteraction_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionInferredInteraction")
    @MapKey(name = "mIFInferredInteractionParticipantPK")
    private Map<MIFInferredInteractionParticipantPK, MIFInferredInteractionParticipant> mifInferredInteractionParticipant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionInferredInteraction")
    @MapKey(name = "mIFOtherExperimentRefPK")
    private Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherExperimentRef;

    public MIFInteractionInferredInteraction() {
    }

    public MIFInteractionInferredInteraction(Long wid) {
        this.wid = wid;
    }

    public MIFInteractionInferredInteraction(Long wid, long mIFEntryInteractionWID) {
        this.wid = wid;
        this.mIFEntryInteractionWID = mIFEntryInteractionWID;
    }

    public long getmIFEntryInteractionWID() {
        return mIFEntryInteractionWID;
    }

    public void setmIFEntryInteractionWID(long mIFEntryInteractionWID) {
        this.mIFEntryInteractionWID = mIFEntryInteractionWID;
    }

    public MIFEntryInteraction getMifEntryInteraction() {
        return mifEntryInteraction;
    }

    public void setMifEntryInteraction(MIFEntryInteraction mifEntryInteraction) {
        this.mifEntryInteraction = mifEntryInteraction;
    }

    public Map<MIFInferredInteractionParticipantPK, MIFInferredInteractionParticipant> getMifInferredInteractionParticipant() {
        return mifInferredInteractionParticipant;
    }

    public void setMifInferredInteractionParticipant(Map<MIFInferredInteractionParticipantPK, MIFInferredInteractionParticipant> mifInferredInteractionParticipant) {
        this.mifInferredInteractionParticipant = mifInferredInteractionParticipant;
    }

    public Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> getMifOtherExperimentRef() {
        return mifOtherExperimentRef;
    }

    public void setMifOtherExperimentRef(Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherExperimentRef) {
        this.mifOtherExperimentRef = mifOtherExperimentRef;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getMIFEntryInteractionWID() {
        return mIFEntryInteractionWID;
    }

    public void setMIFEntryInteractionWID(long mIFEntryInteractionWID) {
        this.mIFEntryInteractionWID = mIFEntryInteractionWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
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
        final MIFInteractionInferredInteraction other = (MIFInteractionInferredInteraction) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFEntryInteractionWID != other.mIFEntryInteractionWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFInteractionInferredInteraction{" + "wid=" + wid + ", mIFEntryInteractionWID=" + mIFEntryInteractionWID + '}';
    }
}
