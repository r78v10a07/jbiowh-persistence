package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFParticipantBiologicalRole entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFParticipantBiologicalRole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFParticipantBiologicalRole.findAll", query = "SELECT m FROM MIFParticipantBiologicalRole m"),
    @NamedQuery(name = "MIFParticipantBiologicalRole.findByWid", query = "SELECT m FROM MIFParticipantBiologicalRole m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFParticipantBiologicalRole.findByMIFInteractionParticipantWID", query = "SELECT m FROM MIFParticipantBiologicalRole m WHERE m.mIFInteractionParticipantWID = :mIFInteractionParticipantWID"),
    @NamedQuery(name = "MIFParticipantBiologicalRole.findByShortLabel", query = "SELECT m FROM MIFParticipantBiologicalRole m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFParticipantBiologicalRole.findByFullName", query = "SELECT m FROM MIFParticipantBiologicalRole m WHERE m.fullName = :fullName")})
public class MIFParticipantBiologicalRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFInteractionParticipant_WID")
    private long mIFInteractionParticipantWID;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "MIFInteractionParticipant_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionParticipant mifInteractionParticipant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantBiologicalRole")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantBiologicalRole")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantBiologicalRole")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantBiologicalRole")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantBiologicalRole")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantBiologicalRole")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;

    public MIFParticipantBiologicalRole() {
    }

    public MIFParticipantBiologicalRole(Long wid) {
        this.wid = wid;
    }

    public MIFParticipantBiologicalRole(Long wid, long mIFInteractionParticipantWID) {
        this.wid = wid;
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
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

    public String getShortLabel() {
        return shortLabel;
    }

    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        final MIFParticipantBiologicalRole other = (MIFParticipantBiologicalRole) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFInteractionParticipantWID != other.mIFInteractionParticipantWID) {
            return false;
        }
        if (!Objects.equals(this.shortLabel, other.shortLabel)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFParticipantBiologicalRole{" + "wid=" + wid + ", mIFInteractionParticipantWID=" + mIFInteractionParticipantWID + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
