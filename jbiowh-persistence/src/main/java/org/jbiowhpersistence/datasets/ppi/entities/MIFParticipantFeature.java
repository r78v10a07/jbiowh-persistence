package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFParticipantFeature entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFParticipantFeature")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFParticipantFeature.findAll", query = "SELECT m FROM MIFParticipantFeature m"),
    @NamedQuery(name = "MIFParticipantFeature.findByWid", query = "SELECT m FROM MIFParticipantFeature m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFParticipantFeature.findByMIFInteractionParticipantWID", query = "SELECT m FROM MIFParticipantFeature m WHERE m.mIFInteractionParticipantWID = :mIFInteractionParticipantWID"),
    @NamedQuery(name = "MIFParticipantFeature.findById", query = "SELECT m FROM MIFParticipantFeature m WHERE m.id = :id"),
    @NamedQuery(name = "MIFParticipantFeature.findByShortLabel", query = "SELECT m FROM MIFParticipantFeature m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFParticipantFeature.findByFullName", query = "SELECT m FROM MIFParticipantFeature m WHERE m.fullName = :fullName")})
public class MIFParticipantFeature implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFInteractionParticipant_WID")
    private long mIFInteractionParticipantWID;
    @Column(name = "Id")
    private Integer id;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "MIFInteractionParticipant_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionParticipant mifInteractionParticipant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    @MapKey(name = "mIFOtherExperimentRefPK")
    private Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherExperimentRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFFeatureFeatureType> mifFeatureFeatureType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFFeatureFeatDetMeth> mifFeatureFeatDetMeth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFFeatureFeatureRange> mifFeatureFeatureRange;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifParticipantFeature")
    private Set<MIFOtherAttribute> mifOtherAttribute;

    public MIFParticipantFeature() {
    }

    public MIFParticipantFeature(Long wid) {
        this.wid = wid;
    }

    public MIFParticipantFeature(Long wid, long mIFInteractionParticipantWID) {
        this.wid = wid;
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
    }

    public long getmIFInteractionParticipantWID() {
        return mIFInteractionParticipantWID;
    }

    public void setmIFInteractionParticipantWID(long mIFInteractionParticipantWID) {
        this.mIFInteractionParticipantWID = mIFInteractionParticipantWID;
    }

    public Set<MIFFeatureFeatDetMeth> getMifFeatureFeatDetMeth() {
        return mifFeatureFeatDetMeth;
    }

    public void setMifFeatureFeatDetMeth(Set<MIFFeatureFeatDetMeth> mifFeatureFeatDetMeth) {
        this.mifFeatureFeatDetMeth = mifFeatureFeatDetMeth;
    }

    public Set<MIFFeatureFeatureRange> getMifFeatureFeatureRange() {
        return mifFeatureFeatureRange;
    }

    public void setMifFeatureFeatureRange(Set<MIFFeatureFeatureRange> mifFeatureFeatureRange) {
        this.mifFeatureFeatureRange = mifFeatureFeatureRange;
    }

    public Set<MIFFeatureFeatureType> getMifFeatureFeatureType() {
        return mifFeatureFeatureType;
    }

    public void setMifFeatureFeatureType(Set<MIFFeatureFeatureType> mifFeatureFeatureType) {
        this.mifFeatureFeatureType = mifFeatureFeatureType;
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

    public Set<MIFOtherAttribute> getMifOtherAttribute() {
        return mifOtherAttribute;
    }

    public void setMifOtherAttribute(Set<MIFOtherAttribute> mifOtherAttribute) {
        this.mifOtherAttribute = mifOtherAttribute;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        final MIFParticipantFeature other = (MIFParticipantFeature) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFInteractionParticipantWID != other.mIFInteractionParticipantWID) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
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
        return "MIFParticipantFeature{" + "wid=" + wid + ", mIFInteractionParticipantWID=" + mIFInteractionParticipantWID + ", id=" + id + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
