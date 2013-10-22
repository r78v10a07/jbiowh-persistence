package org.jbiowhpersistence.datasets.pathway.kegg.entities.compound;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;

/**
 * This class is the KEGGRPair entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGRPair")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGRPair.findAll", query = "SELECT k FROM KEGGRPair k"),
    @NamedQuery(name = "KEGGRPair.findByWid", query = "SELECT k FROM KEGGRPair k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGRPair.findByEntry", query = "SELECT k FROM KEGGRPair k WHERE k.entry = :entry"),
    @NamedQuery(name = "KEGGRPair.findByName", query = "SELECT k FROM KEGGRPair k WHERE k.name = :name"),
    @NamedQuery(name = "KEGGRPair.findByType", query = "SELECT k FROM KEGGRPair k WHERE k.type = :type"),
    @NamedQuery(name = "KEGGRPair.findByRClass", query = "SELECT k FROM KEGGRPair k WHERE k.rClass = :rClass"),
    @NamedQuery(name = "KEGGRPair.findByDataSetWID", query = "SELECT k FROM KEGGRPair k WHERE k.dataSetWID = :dataSetWID")})
public class KEGGRPair implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Entry")
    private String entry;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @Column(name = "RClass")
    private String rClass;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGRPAIR_HAS_KEGGCOMPOUND,
    joinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGRPair_WID", referencedColumnName = "WID"))
    private Set<KEGGCompound> kEGGCompound;

    public KEGGRPair() {
    }

    public KEGGRPair(Long wid) {
        this.wid = wid;
    }

    public KEGGRPair(Long wid, String entry, String name, String type, String rClass, long dataSetWID) {
        this.wid = wid;
        this.entry = entry;
        this.name = name;
        this.type = type;
        this.rClass = rClass;
        this.dataSetWID = dataSetWID;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Set<KEGGCompound> getkEGGCompound() {
        return kEGGCompound;
    }

    public void setkEGGCompound(Set<KEGGCompound> kEGGCompound) {
        this.kEGGCompound = kEGGCompound;
    }

    public String getrClass() {
        return rClass;
    }

    public void setrClass(String rClass) {
        this.rClass = rClass;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRClass() {
        return rClass;
    }

    public void setRClass(String rClass) {
        this.rClass = rClass;
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
        final KEGGRPair other = (KEGGRPair) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.entry, other.entry)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.rClass, other.rClass)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGRPair{" + "wid=" + wid + ", entry=" + entry + ", name=" + name + ", type=" + type + ", rClass=" + rClass + ", dataSetWID=" + dataSetWID + '}';
    }
}
