package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the MIFFeatureFeatureType entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFFeatureFeatureType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFFeatureFeatureType.findAll", query = "SELECT m FROM MIFFeatureFeatureType m"),
    @NamedQuery(name = "MIFFeatureFeatureType.findByWid", query = "SELECT m FROM MIFFeatureFeatureType m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFFeatureFeatureType.findByMIFParticipantFeatureWID", query = "SELECT m FROM MIFFeatureFeatureType m WHERE m.mIFParticipantFeatureWID = :mIFParticipantFeatureWID"),
    @NamedQuery(name = "MIFFeatureFeatureType.findByShortLabel", query = "SELECT m FROM MIFFeatureFeatureType m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFFeatureFeatureType.findByFullName", query = "SELECT m FROM MIFFeatureFeatureType m WHERE m.fullName = :fullName")})
public class MIFFeatureFeatureType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFParticipantFeature_WID")
    private long mIFParticipantFeatureWID;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "MIFParticipantFeature_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantFeature mifParticipantFeature;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifFeatureFeatureType")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifFeatureFeatureType")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifFeatureFeatureType")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifFeatureFeatureType")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifFeatureFeatureType")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifFeatureFeatureType")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;

    public MIFFeatureFeatureType() {
    }

    public MIFFeatureFeatureType(Long wid) {
        this.wid = wid;
    }

    public MIFFeatureFeatureType(Long wid, long mIFParticipantFeatureWID) {
        this.wid = wid;
        this.mIFParticipantFeatureWID = mIFParticipantFeatureWID;
    }

    public long getmIFParticipantFeatureWID() {
        return mIFParticipantFeatureWID;
    }

    public void setmIFParticipantFeatureWID(long mIFParticipantFeatureWID) {
        this.mIFParticipantFeatureWID = mIFParticipantFeatureWID;
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

    public MIFParticipantFeature getMifParticipantFeature() {
        return mifParticipantFeature;
    }

    public void setMifParticipantFeature(MIFParticipantFeature mifParticipantFeature) {
        this.mifParticipantFeature = mifParticipantFeature;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getMIFParticipantFeatureWID() {
        return mIFParticipantFeatureWID;
    }

    public void setMIFParticipantFeatureWID(long mIFParticipantFeatureWID) {
        this.mIFParticipantFeatureWID = mIFParticipantFeatureWID;
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
        final MIFFeatureFeatureType other = (MIFFeatureFeatureType) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFParticipantFeatureWID != other.mIFParticipantFeatureWID) {
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
        return "MIFFeatureFeatureType{" + "wid=" + wid + ", mIFParticipantFeatureWID=" + mIFParticipantFeatureWID + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
