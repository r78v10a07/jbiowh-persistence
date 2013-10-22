package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFInteractorInteractorType entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFInteractorInteractorType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFInteractorInteractorType.findAll", query = "SELECT m FROM MIFInteractorInteractorType m"),
    @NamedQuery(name = "MIFInteractorInteractorType.findByWid", query = "SELECT m FROM MIFInteractorInteractorType m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFInteractorInteractorType.findByMIFEntryInteractorWID", query = "SELECT m FROM MIFInteractorInteractorType m WHERE m.mIFEntryInteractorWID = :mIFEntryInteractorWID"),
    @NamedQuery(name = "MIFInteractorInteractorType.findByShortLabel", query = "SELECT m FROM MIFInteractorInteractorType m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFInteractorInteractorType.findByFullName", query = "SELECT m FROM MIFInteractorInteractorType m WHERE m.fullName = :fullName")})
public class MIFInteractorInteractorType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFEntryInteractor_WID")
    private long mIFEntryInteractorWID;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "MIFEntryInteractor_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteractor mifEntryInteractor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractorInteractorType")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractorInteractorType")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractorInteractorType")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractorInteractorType")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractorInteractorType")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractorInteractorType")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;

    public MIFInteractorInteractorType() {
    }

    public MIFInteractorInteractorType(Long wid) {
        this.wid = wid;
    }

    public MIFInteractorInteractorType(Long wid, long mIFEntryInteractorWID) {
        this.wid = wid;
        this.mIFEntryInteractorWID = mIFEntryInteractorWID;
    }

    public long getmIFEntryInteractorWID() {
        return mIFEntryInteractorWID;
    }

    public void setmIFEntryInteractorWID(long mIFEntryInteractorWID) {
        this.mIFEntryInteractorWID = mIFEntryInteractorWID;
    }

    public MIFEntryInteractor getMifEntryInteractor() {
        return mifEntryInteractor;
    }

    public void setMifEntryInteractor(MIFEntryInteractor mifEntryInteractor) {
        this.mifEntryInteractor = mifEntryInteractor;
    }

    public Set<MIFOtherAlias> getMifOtherAlias() {
        return mifOtherAlias;
    }

    public void setMifOtherAlias(Set<MIFOtherAlias> mifOtherAlias) {
        this.mifOtherAlias = mifOtherAlias;
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

    public long getMIFEntryInteractorWID() {
        return mIFEntryInteractorWID;
    }

    public void setMIFEntryInteractorWID(long mIFEntryInteractorWID) {
        this.mIFEntryInteractorWID = mIFEntryInteractorWID;
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
        final MIFInteractorInteractorType other = (MIFInteractorInteractorType) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFEntryInteractorWID != other.mIFEntryInteractorWID) {
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
        return "MIFInteractorInteractorType{" + "wid=" + wid + ", mIFEntryInteractorWID=" + mIFEntryInteractorWID + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
