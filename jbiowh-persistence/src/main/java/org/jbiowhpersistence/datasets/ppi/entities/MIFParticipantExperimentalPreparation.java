package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFParticipantExperimentalPreparation entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFParticipantExperimentalPreparation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFParticipantExperimentalPreparation.findAll", query = "SELECT m FROM MIFParticipantExperimentalPreparation m"),
    @NamedQuery(name = "MIFParticipantExperimentalPreparation.findByWid", query = "SELECT m FROM MIFParticipantExperimentalPreparation m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFParticipantExperimentalPreparation.findByMIFInteractionParticipantWID", query = "SELECT m FROM MIFParticipantExperimentalPreparation m WHERE m.mIFInteractionParticipantWID = :mIFInteractionParticipantWID"),
    @NamedQuery(name = "MIFParticipantExperimentalPreparation.findByShortLabel", query = "SELECT m FROM MIFParticipantExperimentalPreparation m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFParticipantExperimentalPreparation.findByFullName", query = "SELECT m FROM MIFParticipantExperimentalPreparation m WHERE m.fullName = :fullName")})
public class MIFParticipantExperimentalPreparation implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantExperimentalPreparation")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantExperimentalPreparation")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantExperimentalPreparation")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantExperimentalPreparation")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantExperimentalPreparation")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantExperimentalPreparation")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantExperimentalPreparation")
    @MapKey(name = "mIFOtherExperimentRefPK")
    private Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherExperimentRef;

    public MIFParticipantExperimentalPreparation() {
    }

    public MIFParticipantExperimentalPreparation(Long wid) {
        this.wid = wid;
    }

    public MIFParticipantExperimentalPreparation(Long wid, long mIFInteractionParticipantWID) {
        this.wid = wid;
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
    }

    public long getmIFInteractionParticipantWID() {
        return mIFInteractionParticipantWID;
    }

    public void setmIFInteractionParticipantWID(long mIFInteractionParticipantWID) {
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
    }

    public MIFInteractionParticipant getMifInteractionParticipant() {
        return mifInteractionParticipant;
    }

    public void setMifInteractionParticipant(MIFInteractionParticipant mifInteractionParticipant) {
        this.mifInteractionParticipant = mifInteractionParticipant;
    }

    public Set<MIFOtherAlias> getMifOtherAlias() {
        return mifOtherAlias;
    }

    public void setMifOtherAlias(Set<MIFOtherAlias> mifOtherAlias) {
        this.mifOtherAlias = mifOtherAlias;
    }

    public Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> getMifOtherExperimentRef() {
        return mifOtherExperimentRef;
    }

    public void setMifOtherExperimentRef(Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherExperimentRef) {
        this.mifOtherExperimentRef = mifOtherExperimentRef;
    }

    public Set<MIFOtherXRef> getMifOtherXRef() {
        return mifOtherXRef;
    }

    public void setMifOtherXRef(Set<MIFOtherXRef> mifOtherXRef) {
        this.mifOtherXRef = mifOtherXRef;
    }

    public Set<MIFOtherXRefGO> getMifOtherXRefGO() {
        return mifOtherXRefGO;
    }

    public void setMifOtherXRefGO(Set<MIFOtherXRefGO> mifOtherXRefGO) {
        this.mifOtherXRefGO = mifOtherXRefGO;
    }

    public Set<MIFOtherXRefPubMed> getMifOtherXRefPubMed() {
        return mifOtherXRefPubMed;
    }

    public void setMifOtherXRefPubMed(Set<MIFOtherXRefPubMed> mifOtherXRefPubMed) {
        this.mifOtherXRefPubMed = mifOtherXRefPubMed;
    }

    public Set<MIFOtherXRefRefSeq> getMifOtherXRefRefSeq() {
        return mifOtherXRefRefSeq;
    }

    public void setMifOtherXRefRefSeq(Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq) {
        this.mifOtherXRefRefSeq = mifOtherXRefRefSeq;
    }

    public Set<MIFOtherXRefUniprot> getMifOtherXRefUniprot() {
        return mifOtherXRefUniprot;
    }

    public void setMifOtherXRefUniprot(Set<MIFOtherXRefUniprot> mifOtherXRefUniprot) {
        this.mifOtherXRefUniprot = mifOtherXRefUniprot;
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
        final MIFParticipantExperimentalPreparation other = (MIFParticipantExperimentalPreparation) obj;
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
        return "MIFParticipantExperimentalPreparation{" + "wid=" + wid + ", mIFInteractionParticipantWID=" + mIFInteractionParticipantWID + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
