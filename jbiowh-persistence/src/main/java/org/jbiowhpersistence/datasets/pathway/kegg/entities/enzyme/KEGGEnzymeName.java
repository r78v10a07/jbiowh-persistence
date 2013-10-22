package org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Enzyme Name entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGEnzymeName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGEnzymeName.findAll", query = "SELECT k FROM KEGGEnzymeName k"),
    @NamedQuery(name = "KEGGEnzymeName.findByWid", query = "SELECT k FROM KEGGEnzymeName k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGEnzymeName.findByKEGGEnzymeWID", query = "SELECT k FROM KEGGEnzymeName k WHERE k.kEGGEnzymeWID = :kEGGEnzymeWID"),
    @NamedQuery(name = "KEGGEnzymeName.findByName", query = "SELECT k FROM KEGGEnzymeName k WHERE k.name = :name")})
public class KEGGEnzymeName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGEnzyme_WID")
    private long kEGGEnzymeWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGEnzyme_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGEnzyme kEGGEnzyme;

    public KEGGEnzymeName() {
    }

    public KEGGEnzymeName(Long wid) {
        this.wid = wid;
    }

    public KEGGEnzymeName(Long wid, long kEGGEnzymeWID, String name) {
        this.wid = wid;
        this.kEGGEnzymeWID = kEGGEnzymeWID;
        this.name = name;
    }

    public KEGGEnzyme getkEGGEnzyme() {
        return kEGGEnzyme;
    }

    public void setkEGGEnzyme(KEGGEnzyme kEGGEnzyme) {
        this.kEGGEnzyme = kEGGEnzyme;
    }

    public long getkEGGEnzymeWID() {
        return kEGGEnzymeWID;
    }

    public void setkEGGEnzymeWID(long kEGGEnzymeWID) {
        this.kEGGEnzymeWID = kEGGEnzymeWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getKEGGEnzymeWID() {
        return kEGGEnzymeWID;
    }

    public void setKEGGEnzymeWID(long kEGGEnzymeWID) {
        this.kEGGEnzymeWID = kEGGEnzymeWID;
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
        final KEGGEnzymeName other = (KEGGEnzymeName) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGEnzymeWID != other.kEGGEnzymeWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGEnzymeName{" + "wid=" + wid + ", kEGGEnzymeWID=" + kEGGEnzymeWID + ", name=" + name + '}';
    }
}
