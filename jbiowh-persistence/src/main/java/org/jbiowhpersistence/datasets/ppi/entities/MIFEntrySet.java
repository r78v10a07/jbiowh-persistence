package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;

/**
 * This Class is the MIFEntrySet entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFEntrySet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFEntrySet.findAll", query = "SELECT m FROM MIFEntrySet m"),
    @NamedQuery(name = "MIFEntrySet.findByWid", query = "SELECT m FROM MIFEntrySet m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFEntrySet.findByLevel", query = "SELECT m FROM MIFEntrySet m WHERE m.level = :level"),
    @NamedQuery(name = "MIFEntrySet.findByVersion", query = "SELECT m FROM MIFEntrySet m WHERE m.version = :version"),
    @NamedQuery(name = "MIFEntrySet.findByMinorVersion", query = "SELECT m FROM MIFEntrySet m WHERE m.minorVersion = :minorVersion"),
    @NamedQuery(name = "MIFEntrySet.findByDataSetWID", query = "SELECT m FROM MIFEntrySet m WHERE m.dataSetWID = :dataSetWID")})
public class MIFEntrySet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Level")
    private Integer level;
    @Column(name = "Version")
    private Integer version;
    @Column(name = "MinorVersion")
    private Integer minorVersion;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mIFEntrySet")
    private Set<MIFEntrySetEntry> mIFEntrySetEntry;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSetWID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;

    public MIFEntrySet() {
    }

    public MIFEntrySet(Long wid) {
        this.wid = wid;
    }

    public MIFEntrySet(Long wid, long dataSetWID) {
        this.wid = wid;
        this.dataSetWID = dataSetWID;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Set<MIFEntrySetEntry> getmIFEntrySetEntry() {
        return mIFEntrySetEntry;
    }

    public void setmIFEntrySetEntry(Set<MIFEntrySetEntry> mIFEntrySetEntry) {
        this.mIFEntrySetEntry = mIFEntrySetEntry;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
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
        final MIFEntrySet other = (MIFEntrySet) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.minorVersion, other.minorVersion)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFEntrySet[ wid=" + wid + " ]";
    }
}
