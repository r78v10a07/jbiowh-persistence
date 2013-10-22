package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFEntryInteractor entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFEntryInteractor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFEntryInteractor.findAll", query = "SELECT m FROM MIFEntryInteractor m"),
    @NamedQuery(name = "MIFEntryInteractor.findByWid", query = "SELECT m FROM MIFEntryInteractor m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFEntryInteractor.findByMIFEntrySetEntryWID", query = "SELECT m FROM MIFEntryInteractor m WHERE m.mIFEntrySetEntryWID = :mIFEntrySetEntryWID"),
    @NamedQuery(name = "MIFEntryInteractor.findById", query = "SELECT m FROM MIFEntryInteractor m WHERE m.id = :id"),
    @NamedQuery(name = "MIFEntryInteractor.findByShortLabel", query = "SELECT m FROM MIFEntryInteractor m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFEntryInteractor.findByFullName", query = "SELECT m FROM MIFEntryInteractor m WHERE m.fullName = :fullName")})
public class MIFEntryInteractor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFEntrySetEntry_WID")
    private long mIFEntrySetEntryWID;
    @Basic(optional = false)
    @Column(name = "Id")
    private int id;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @Lob
    @Column(name = "Sequence")
    private String sequence;
    @ManyToOne
    @JoinColumn(name = "MIFEntrySetEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntrySetEntry mifEntrySetEntry;
    @ManyToOne
    @JoinColumn(name = "MIFEntrySetEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionParticipant mifInteractionParticipant;
    @ManyToOne
    @JoinColumn(name = "MIFEntrySetEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantExperimentalInteractor mifParticipantExperimentalInteractor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteractor")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteractor")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteractor")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteractor")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteractor")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteractor")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteractor")
    private Set<MIFInteractorInteractorType> mifInteractorInteractorType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteractor")
    private Set<MIFOtherBioSourceType> mifOtherBioSourceType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteractor")
    private Set<MIFOtherAttribute> mifOtherAttribute;

    public MIFEntryInteractor() {
    }

    public MIFEntryInteractor(Long wid) {
        this.wid = wid;
    }

    public MIFEntryInteractor(Long wid, long mIFEntrySetEntryWID, int id) {
        this.wid = wid;
        this.mIFEntrySetEntryWID = mIFEntrySetEntryWID;
        this.id = id;
    }

    public long getmIFEntrySetEntryWID() {
        return mIFEntrySetEntryWID;
    }

    public void setmIFEntrySetEntryWID(long mIFEntrySetEntryWID) {
        this.mIFEntrySetEntryWID = mIFEntrySetEntryWID;
    }

    public MIFEntrySetEntry getMifEntrySetEntry() {
        return mifEntrySetEntry;
    }

    public void setMifEntrySetEntry(MIFEntrySetEntry mifEntrySetEntry) {
        this.mifEntrySetEntry = mifEntrySetEntry;
    }

    public MIFInteractionParticipant getMifInteractionParticipant() {
        return mifInteractionParticipant;
    }

    public void setMifInteractionParticipant(MIFInteractionParticipant mifInteractionParticipant) {
        this.mifInteractionParticipant = mifInteractionParticipant;
    }

    public Set<MIFInteractorInteractorType> getMifInteractorInteractorType() {
        return mifInteractorInteractorType;
    }

    public void setMifInteractorInteractorType(Set<MIFInteractorInteractorType> mifInteractorInteractorType) {
        this.mifInteractorInteractorType = mifInteractorInteractorType;
    }

    public Set<MIFOtherAlias> getMifOtherAlias() {
        return mifOtherAlias;
    }

    public void setMifOtherAlias(Set<MIFOtherAlias> mifOtherAlias) {
        this.mifOtherAlias = mifOtherAlias;
    }

    public Set<MIFOtherAttribute> getMifOtherAttribute() {
        return mifOtherAttribute;
    }

    public void setMifOtherAttribute(Set<MIFOtherAttribute> mifOtherAttribute) {
        this.mifOtherAttribute = mifOtherAttribute;
    }

    public Set<MIFOtherBioSourceType> getMifOtherBioSourceType() {
        return mifOtherBioSourceType;
    }

    public void setMifOtherBioSourceType(Set<MIFOtherBioSourceType> mifOtherBioSourceType) {
        this.mifOtherBioSourceType = mifOtherBioSourceType;
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

    public MIFParticipantExperimentalInteractor getMifParticipantExperimentalInteractor() {
        return mifParticipantExperimentalInteractor;
    }

    public void setMifParticipantExperimentalInteractor(MIFParticipantExperimentalInteractor mifParticipantExperimentalInteractor) {
        this.mifParticipantExperimentalInteractor = mifParticipantExperimentalInteractor;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getMIFEntrySetEntryWID() {
        return mIFEntrySetEntryWID;
    }

    public void setMIFEntrySetEntryWID(long mIFEntrySetEntryWID) {
        this.mIFEntrySetEntryWID = mIFEntrySetEntryWID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
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
        final MIFEntryInteractor other = (MIFEntryInteractor) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFEntrySetEntryWID != other.mIFEntrySetEntryWID) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.shortLabel, other.shortLabel)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.sequence, other.sequence)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFEntryInteractor{" + "wid=" + wid + ", mIFEntrySetEntryWID=" + mIFEntrySetEntryWID + ", id=" + id + ", shortLabel=" + shortLabel + ", fullName=" + fullName + ", sequence=" + sequence + '}';
    }
}
