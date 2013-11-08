package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the MIFEntrySource entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFEntrySource")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFEntrySource.findAll", query = "SELECT m FROM MIFEntrySource m"),
    @NamedQuery(name = "MIFEntrySource.findByWid", query = "SELECT m FROM MIFEntrySource m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFEntrySource.findByMIFEntrySetEntryWID", query = "SELECT m FROM MIFEntrySource m WHERE m.mIFEntrySetEntryWID = :mIFEntrySetEntryWID"),
    @NamedQuery(name = "MIFEntrySource.findByReleaseValue", query = "SELECT m FROM MIFEntrySource m WHERE m.releaseValue = :releaseValue"),
    @NamedQuery(name = "MIFEntrySource.findByReleaseDate", query = "SELECT m FROM MIFEntrySource m WHERE m.releaseDate = :releaseDate"),
    @NamedQuery(name = "MIFEntrySource.findByShortLabel", query = "SELECT m FROM MIFEntrySource m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFEntrySource.findByFullName", query = "SELECT m FROM MIFEntrySource m WHERE m.fullName = :fullName")})
public class MIFEntrySource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFEntrySetEntry_WID")
    private long mIFEntrySetEntryWID;
    @Column(name = "ReleaseValue")
    private String releaseValue;
    @Column(name = "ReleaseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    // Internal Interaction relationship
    @ManyToOne
    @JoinColumn(name = "MIFEntrySetEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntrySetEntry mIFEntrySetEntry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntrySource")
    private Set<MIFOtherAlias> mIFOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntrySource")
    private Set<MIFOtherBibRef> mIFOtherBibRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntrySource")
    private Set<MIFOtherXRef> mIFOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntrySource")
    private Set<MIFOtherAttribute> mIFOtherAttribute;

    public MIFEntrySource() {
    }

    public MIFEntrySource(Long wid) {
        this.wid = wid;
    }

    public MIFEntrySource(Long wid, long mIFEntrySetEntryWID) {
        this.wid = wid;
        this.mIFEntrySetEntryWID = mIFEntrySetEntryWID;
    }

    @XmlTransient
    public Set<MIFOtherAlias> getmIFOtherAlias() {
        return mIFOtherAlias;
    }

    public void setmIFOtherAlias(Set<MIFOtherAlias> mIFOtherAlias) {
        this.mIFOtherAlias = mIFOtherAlias;
    }

    @XmlTransient
    public Set<MIFOtherAttribute> getmIFOtherAttribute() {
        return mIFOtherAttribute;
    }

    public void setmIFOtherAttribute(Set<MIFOtherAttribute> mIFOtherAttribute) {
        this.mIFOtherAttribute = mIFOtherAttribute;
    }

    @XmlTransient
    public Set<MIFOtherBibRef> getmIFOtherBibRef() {
        return mIFOtherBibRef;
    }

    public void setmIFOtherBibRef(Set<MIFOtherBibRef> mIFOtherBibRef) {
        this.mIFOtherBibRef = mIFOtherBibRef;
    }

    @XmlTransient
    public Set<MIFOtherXRef> getmIFOtherXRef() {
        return mIFOtherXRef;
    }

    public void setmIFOtherXRef(Set<MIFOtherXRef> mIFOtherXRef) {
        this.mIFOtherXRef = mIFOtherXRef;
    }

    public MIFEntrySetEntry getmIFEntrySetEntry() {
        return mIFEntrySetEntry;
    }

    public void setmIFEntrySetEntry(MIFEntrySetEntry mIFEntrySetEntry) {
        this.mIFEntrySetEntry = mIFEntrySetEntry;
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

    public String getReleaseValue() {
        return releaseValue;
    }

    public void setReleaseValue(String releaseValue) {
        this.releaseValue = releaseValue;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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
        final MIFEntrySource other = (MIFEntrySource) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFEntrySetEntryWID != other.mIFEntrySetEntryWID) {
            return false;
        }
        if (!Objects.equals(this.releaseValue, other.releaseValue)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
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
        return "MIFEntrySource{" + "wid=" + wid + ", mIFEntrySetEntryWID=" + mIFEntrySetEntryWID + ", releaseValue=" + releaseValue + ", releaseDate=" + releaseDate + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
