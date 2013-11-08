package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the MIFEntryExperiment entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFEntryExperiment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFEntryExperiment.findAll", query = "SELECT m FROM MIFEntryExperiment m"),
    @NamedQuery(name = "MIFEntryExperiment.findByWid", query = "SELECT m FROM MIFEntryExperiment m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFEntryExperiment.findByMIFEntrySetEntryWID", query = "SELECT m FROM MIFEntryExperiment m WHERE m.mIFEntrySetEntryWID = :mIFEntrySetEntryWID"),
    @NamedQuery(name = "MIFEntryExperiment.findById", query = "SELECT m FROM MIFEntryExperiment m WHERE m.id = :id"),
    @NamedQuery(name = "MIFEntryExperiment.findByShortLabel", query = "SELECT m FROM MIFEntryExperiment m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFEntryExperiment.findByFullName", query = "SELECT m FROM MIFEntryExperiment m WHERE m.fullName = :fullName")})
public class MIFEntryExperiment implements Serializable {

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
    @ManyToOne
    @JoinColumn(name = "MIFEntrySetEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntrySetEntry mifEntrySetEntry;
    @ManyToOne
    @JoinColumn(name = "MIFEntrySetEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherBibRef> mifOtherBibRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherAttribute> mifOtherAttribute;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherBioSourceType> mifOtherBioSourceType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFExperimentInterDetecMethod> mifExperimentInterDetecMethod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFExperimentPartIdentMethod> mifExperimentPartIdentMethod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFExperimentFeatDetecMethod> mifExperimentFeatDetecMethod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryExperiment")
    private Set<MIFOtherConfidence> mifOtherConfidence;

    public MIFEntryExperiment() {
    }

    public MIFEntryExperiment(Long wid) {
        this.wid = wid;
    }

    public MIFEntryExperiment(Long wid, long mIFEntrySetEntryWID, int id) {
        this.wid = wid;
        this.mIFEntrySetEntryWID = mIFEntrySetEntryWID;
        this.id = id;
    }
    
    public void setRelationsToNull() {
        setMifEntryInteraction(null);
        setMifEntrySetEntry(null);
    }

    public MIFEntryInteraction getMifEntryInteraction() {
        return mifEntryInteraction;
    }

    public void setMifEntryInteraction(MIFEntryInteraction mifEntryInteraction) {
        this.mifEntryInteraction = mifEntryInteraction;
    }

    public MIFEntrySetEntry getMifEntrySetEntry() {
        return mifEntrySetEntry;
    }

    public void setMifEntrySetEntry(MIFEntrySetEntry mifEntrySetEntry) {
        this.mifEntrySetEntry = mifEntrySetEntry;
    }

    @XmlTransient
    public Set<MIFExperimentFeatDetecMethod> getMifExperimentFeatDetecMethod() {
        return mifExperimentFeatDetecMethod;
    }

    public void setMifExperimentFeatDetecMethod(Set<MIFExperimentFeatDetecMethod> mifExperimentFeatDetecMethod) {
        this.mifExperimentFeatDetecMethod = mifExperimentFeatDetecMethod;
    }

    @XmlTransient
    public Set<MIFExperimentInterDetecMethod> getMifExperimentInterDetecMethod() {
        return mifExperimentInterDetecMethod;
    }

    public void setMifExperimentInterDetecMethod(Set<MIFExperimentInterDetecMethod> mifExperimentInterDetecMethod) {
        this.mifExperimentInterDetecMethod = mifExperimentInterDetecMethod;
    }

    @XmlTransient
    public Set<MIFExperimentPartIdentMethod> getMifExperimentPartIdentMethod() {
        return mifExperimentPartIdentMethod;
    }

    public void setMifExperimentPartIdentMethod(Set<MIFExperimentPartIdentMethod> mifExperimentPartIdentMethod) {
        this.mifExperimentPartIdentMethod = mifExperimentPartIdentMethod;
    }

    @XmlTransient
    public Set<MIFOtherAlias> getMifOtherAlias() {
        return mifOtherAlias;
    }

    public void setMifOtherAlias(Set<MIFOtherAlias> mifOtherAlias) {
        this.mifOtherAlias = mifOtherAlias;
    }

    @XmlTransient
    public Set<MIFOtherAttribute> getMifOtherAttribute() {
        return mifOtherAttribute;
    }

    public void setMifOtherAttribute(Set<MIFOtherAttribute> mifOtherAttribute) {
        this.mifOtherAttribute = mifOtherAttribute;
    }

    @XmlTransient
    public Set<MIFOtherBibRef> getMifOtherBibRef() {
        return mifOtherBibRef;
    }

    public void setMifOtherBibRef(Set<MIFOtherBibRef> mifOtherBibRef) {
        this.mifOtherBibRef = mifOtherBibRef;
    }

    @XmlTransient
    public Set<MIFOtherBioSourceType> getMifOtherBioSourceType() {
        return mifOtherBioSourceType;
    }

    public void setMifOtherBioSourceType(Set<MIFOtherBioSourceType> mifOtherBioSourceType) {
        this.mifOtherBioSourceType = mifOtherBioSourceType;
    }

    @XmlTransient
    public Set<MIFOtherConfidence> getMifOtherConfidence() {
        return mifOtherConfidence;
    }

    public void setMifOtherConfidence(Set<MIFOtherConfidence> mifOtherConfidence) {
        this.mifOtherConfidence = mifOtherConfidence;
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

    public long getmIFEntrySetEntryWID() {
        return mIFEntrySetEntryWID;
    }

    public void setmIFEntrySetEntryWID(long mIFEntrySetEntryWID) {
        this.mIFEntrySetEntryWID = mIFEntrySetEntryWID;
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
        final MIFEntryExperiment other = (MIFEntryExperiment) obj;
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
        return true;
    }

    @Override
    public String toString() {
        return "MIFEntryExperiment{" + "wid=" + wid + ", mIFEntrySetEntryWID=" + mIFEntrySetEntryWID + ", id=" + id + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
