package org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGGGlycanName entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Dec 15, 2011
 */
@Entity
@Table(name = "KEGGGlycanName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGlycanName.findAll", query = "SELECT k FROM KEGGGlycanName k"),
    @NamedQuery(name = "KEGGGlycanName.findByWid", query = "SELECT k FROM KEGGGlycanName k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGGlycanName.findByKEGGGlycanWID", query = "SELECT k FROM KEGGGlycanName k WHERE k.kEGGGlycanWID = :kEGGGlycanWID"),
    @NamedQuery(name = "KEGGGlycanName.findByName", query = "SELECT k FROM KEGGGlycanName k WHERE k.name = :name")})
public class KEGGGlycanName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGGlycan_WID")
    private long kEGGGlycanWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGGlycan_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGGlycan kEGGGlycan;

    public KEGGGlycanName() {
    }

    public KEGGGlycanName(Long wid) {
        this.wid = wid;
    }

    public KEGGGlycanName(Long wid, long kEGGGlycanWID, String name) {
        this.wid = wid;
        this.kEGGGlycanWID = kEGGGlycanWID;
        this.name = name;
    }

    public KEGGGlycan getkEGGGlycan() {
        return kEGGGlycan;
    }

    public void setkEGGGlycan(KEGGGlycan kEGGGlycan) {
        this.kEGGGlycan = kEGGGlycan;
    }

    public long getkEGGGlycanWID() {
        return kEGGGlycanWID;
    }

    public void setkEGGGlycanWID(long kEGGGlycanWID) {
        this.kEGGGlycanWID = kEGGGlycanWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getKEGGGlycanWID() {
        return kEGGGlycanWID;
    }

    public void setKEGGGlycanWID(long kEGGGlycanWID) {
        this.kEGGGlycanWID = kEGGGlycanWID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        final KEGGGlycanName other = (KEGGGlycanName) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGGlycanWID != other.kEGGGlycanWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGlycanName{" + "wid=" + wid + ", kEGGGlycanWID=" + kEGGGlycanWID + ", name=" + name + '}';
    }
}
