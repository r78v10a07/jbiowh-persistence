package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the MIFParticipantExperimentalInteractor entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFParticipantExperimentalInteractor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFParticipantExperimentalInteractor.findAll", query = "SELECT m FROM MIFParticipantExperimentalInteractor m"),
    @NamedQuery(name = "MIFParticipantExperimentalInteractor.findByWid", query = "SELECT m FROM MIFParticipantExperimentalInteractor m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFParticipantExperimentalInteractor.findByMIFInteractionParticipantWID", query = "SELECT m FROM MIFParticipantExperimentalInteractor m WHERE m.mIFInteractionParticipantWID = :mIFInteractionParticipantWID"),
    @NamedQuery(name = "MIFParticipantExperimentalInteractor.findByInteractorRef", query = "SELECT m FROM MIFParticipantExperimentalInteractor m WHERE m.interactorRef = :interactorRef")})
public class MIFParticipantExperimentalInteractor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFInteractionParticipant_WID")
    private long mIFInteractionParticipantWID;
    @Column(name = "InteractorRef")
    private Integer interactorRef;
    @ManyToOne
    @JoinColumn(name = "MIFInteractionParticipant_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionParticipant mifInteractionParticipant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantExperimentalInteractor")
    private Set<MIFEntryInteractor> mifEntryInteractor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantExperimentalInteractor")
    @MapKey(name = "mIFOtherExperimentRefPK")
    private Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherExperimentRef;

    public MIFParticipantExperimentalInteractor() {
    }

    public MIFParticipantExperimentalInteractor(Long wid) {
        this.wid = wid;
    }

    public MIFParticipantExperimentalInteractor(Long wid, long mIFInteractionParticipantWID) {
        this.wid = wid;
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
    }

    public long getmIFInteractionParticipantWID() {
        return mIFInteractionParticipantWID;
    }

    public void setmIFInteractionParticipantWID(long mIFInteractionParticipantWID) {
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
    }

    @XmlTransient
    public Set<MIFEntryInteractor> getMifEntryInteractor() {
        return mifEntryInteractor;
    }

    public void setMifEntryInteractor(Set<MIFEntryInteractor> mifEntryInteractor) {
        this.mifEntryInteractor = mifEntryInteractor;
    }

    public MIFInteractionParticipant getMifInteractionParticipant() {
        return mifInteractionParticipant;
    }

    public void setMifInteractionParticipant(MIFInteractionParticipant mifInteractionParticipant) {
        this.mifInteractionParticipant = mifInteractionParticipant;
    }

    @XmlTransient
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

    public long getMIFInteractionParticipantWID() {
        return mIFInteractionParticipantWID;
    }

    public void setMIFInteractionParticipantWID(long mIFInteractionParticipantWID) {
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
    }

    public Integer getInteractorRef() {
        return interactorRef;
    }

    public void setInteractorRef(Integer interactorRef) {
        this.interactorRef = interactorRef;
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
        final MIFParticipantExperimentalInteractor other = (MIFParticipantExperimentalInteractor) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFInteractionParticipantWID != other.mIFInteractionParticipantWID) {
            return false;
        }
        if (!Objects.equals(this.interactorRef, other.interactorRef)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFParticipantExperimentalInteractor{" + "wid=" + wid + ", mIFInteractionParticipantWID=" + mIFInteractionParticipantWID + ", interactorRef=" + interactorRef + '}';
    }
}
