package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the MIFInteractionInteractionType entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFInteractionInteractionType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFInteractionInteractionType.findAll", query = "SELECT m FROM MIFInteractionInteractionType m"),
    @NamedQuery(name = "MIFInteractionInteractionType.findByWid", query = "SELECT m FROM MIFInteractionInteractionType m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFInteractionInteractionType.findByMIFEntryInteractionWID", query = "SELECT m FROM MIFInteractionInteractionType m WHERE m.mIFEntryInteractionWID = :mIFEntryInteractionWID"),
    @NamedQuery(name = "MIFInteractionInteractionType.findByShortLabel", query = "SELECT m FROM MIFInteractionInteractionType m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFInteractionInteractionType.findByFullName", query = "SELECT m FROM MIFInteractionInteractionType m WHERE m.fullName = :fullName")})
public class MIFInteractionInteractionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFEntryInteraction_WID")
    private long mIFEntryInteractionWID;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "MIFEntryInteraction_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionInteractionType")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionInteractionType")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionInteractionType")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionInteractionType")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionInteractionType")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionInteractionType")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;

    public MIFInteractionInteractionType() {
    }

    public MIFInteractionInteractionType(Long wid) {
        this.wid = wid;
    }

    public MIFInteractionInteractionType(Long wid, long mIFEntryInteractionWID) {
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

    @XmlTransient
    public Set<MIFOtherAlias> getMifOtherAlias() {
        return mifOtherAlias;
    }

    public void setMifOtherAlias(Set<MIFOtherAlias> mifOtherAlias) {
        this.mifOtherAlias = mifOtherAlias;
    }

    @XmlTransient
    public Set<MIFOtherXRef> getMifOtherXRef() {
        return mifOtherXRef;
    }

    public void setMifOtherXRef(Set<MIFOtherXRef> mifOtherXRef) {
        this.mifOtherXRef = mifOtherXRef;
    }

    @XmlTransient
    public Set<MIFOtherXRefGO> getMifOtherXRefGO() {
        return mifOtherXRefGO;
    }

    public void setMifOtherXRefGO(Set<MIFOtherXRefGO> mifOtherXRefGO) {
        this.mifOtherXRefGO = mifOtherXRefGO;
    }

    @XmlTransient
    public Set<MIFOtherXRefPubMed> getMifOtherXRefPubMed() {
        return mifOtherXRefPubMed;
    }

    public void setMifOtherXRefPubMed(Set<MIFOtherXRefPubMed> mifOtherXRefPubMed) {
        this.mifOtherXRefPubMed = mifOtherXRefPubMed;
    }

    @XmlTransient
    public Set<MIFOtherXRefRefSeq> getMifOtherXRefRefSeq() {
        return mifOtherXRefRefSeq;
    }

    public void setMifOtherXRefRefSeq(Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq) {
        this.mifOtherXRefRefSeq = mifOtherXRefRefSeq;
    }

    @XmlTransient
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

    public long getMIFEntryInteractionWID() {
        return mIFEntryInteractionWID;
    }

    public void setMIFEntryInteractionWID(long mIFEntryInteractionWID) {
        this.mIFEntryInteractionWID = mIFEntryInteractionWID;
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
        final MIFInteractionInteractionType other = (MIFInteractionInteractionType) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFEntryInteractionWID != other.mIFEntryInteractionWID) {
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
        return "MIFInteractionInteractionType{" + "wid=" + wid + ", mIFEntryInteractionWID=" + mIFEntryInteractionWID + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
