package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFBioSourceTypeCellType entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFBioSourceTypeCellType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFBioSourceTypeCellType.findAll", query = "SELECT m FROM MIFBioSourceTypeCellType m"),
    @NamedQuery(name = "MIFBioSourceTypeCellType.findByWid", query = "SELECT m FROM MIFBioSourceTypeCellType m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFBioSourceTypeCellType.findByMIFOtherBioSourceTypeWID", query = "SELECT m FROM MIFBioSourceTypeCellType m WHERE m.mIFOtherBioSourceTypeWID = :mIFOtherBioSourceTypeWID"),
    @NamedQuery(name = "MIFBioSourceTypeCellType.findByShortLabel", query = "SELECT m FROM MIFBioSourceTypeCellType m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFBioSourceTypeCellType.findByFullName", query = "SELECT m FROM MIFBioSourceTypeCellType m WHERE m.fullName = :fullName")})
public class MIFBioSourceTypeCellType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFOtherBioSourceType_WID")
    private long mIFOtherBioSourceTypeWID;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "MIFOtherBioSourceType_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherBioSourceType mifOtherBioSourceType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifBioSourceTypeCellType")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifBioSourceTypeCellType")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifBioSourceTypeCellType")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifBioSourceTypeCellType")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifBioSourceTypeCellType")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifBioSourceTypeCellType")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifBioSourceTypeCellType")
    private Set<MIFOtherAttribute> mifOtherAttribute;

    public MIFBioSourceTypeCellType() {
    }

    public MIFBioSourceTypeCellType(Long wid) {
        this.wid = wid;
    }

    public MIFBioSourceTypeCellType(Long wid, long mIFOtherBioSourceTypeWID) {
        this.wid = wid;
        this.mIFOtherBioSourceTypeWID = mIFOtherBioSourceTypeWID;
    }

    public long getmIFOtherBioSourceTypeWID() {
        return mIFOtherBioSourceTypeWID;
    }

    public void setmIFOtherBioSourceTypeWID(long mIFOtherBioSourceTypeWID) {
        this.mIFOtherBioSourceTypeWID = mIFOtherBioSourceTypeWID;
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

    public MIFOtherBioSourceType getMifOtherBioSourceType() {
        return mifOtherBioSourceType;
    }

    public void setMifOtherBioSourceType(MIFOtherBioSourceType mifOtherBioSourceType) {
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

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getMIFOtherBioSourceTypeWID() {
        return mIFOtherBioSourceTypeWID;
    }

    public void setMIFOtherBioSourceTypeWID(long mIFOtherBioSourceTypeWID) {
        this.mIFOtherBioSourceTypeWID = mIFOtherBioSourceTypeWID;
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
        final MIFBioSourceTypeCellType other = (MIFBioSourceTypeCellType) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFOtherBioSourceTypeWID != other.mIFOtherBioSourceTypeWID) {
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
        return "MIFBioSourceTypeCellType{" + "wid=" + wid + ", mIFOtherBioSourceTypeWID=" + mIFOtherBioSourceTypeWID + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
