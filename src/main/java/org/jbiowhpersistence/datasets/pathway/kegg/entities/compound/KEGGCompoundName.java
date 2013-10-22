package org.jbiowhpersistence.datasets.pathway.kegg.entities.compound;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Compound Name entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGCompoundName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGCompoundName.findAll", query = "SELECT k FROM KEGGCompoundName k"),
    @NamedQuery(name = "KEGGCompoundName.findByWid", query = "SELECT k FROM KEGGCompoundName k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGCompoundName.findByKEGGCompoundWID", query = "SELECT k FROM KEGGCompoundName k WHERE k.kEGGCompoundWID = :kEGGCompoundWID"),
    @NamedQuery(name = "KEGGCompoundName.findByName", query = "SELECT k FROM KEGGCompoundName k WHERE k.name = :name")})
public class KEGGCompoundName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGCompound_WID")
    private long kEGGCompoundWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "KEGGCompound_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGCompound kEGGCompound;

    public KEGGCompoundName() {
    }

    public KEGGCompoundName(Long wid) {
        this.wid = wid;
    }

    public KEGGCompoundName(Long wid, long kEGGCompoundWID, String name) {
        this.wid = wid;
        this.kEGGCompoundWID = kEGGCompoundWID;
        this.name = name;
    }

    public KEGGCompound getkEGGCompound() {
        return kEGGCompound;
    }

    public void setkEGGCompound(KEGGCompound kEGGCompound) {
        this.kEGGCompound = kEGGCompound;
    }

    public long getkEGGCompoundWID() {
        return kEGGCompoundWID;
    }

    public void setkEGGCompoundWID(long kEGGCompoundWID) {
        this.kEGGCompoundWID = kEGGCompoundWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getKEGGCompoundWID() {
        return kEGGCompoundWID;
    }

    public void setKEGGCompoundWID(long kEGGCompoundWID) {
        this.kEGGCompoundWID = kEGGCompoundWID;
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
        final KEGGCompoundName other = (KEGGCompoundName) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGCompoundWID != other.kEGGCompoundWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGCompoundName{" + "wid=" + wid + ", kEGGCompoundWID=" + kEGGCompoundWID + ", name=" + name + '}';
    }
}
