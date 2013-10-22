package org.jbiowhpersistence.datasets.pathway.kegg.entities.gene;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGGGeneDisease entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Dec 16, 2011
 */
@Entity
@Table(name = "KEGGGeneDisease")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGeneDisease.findAll", query = "SELECT k FROM KEGGGeneDisease k"),
    @NamedQuery(name = "KEGGGeneDisease.findByWid", query = "SELECT k FROM KEGGGeneDisease k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGGeneDisease.findByKEGGGeneWID", query = "SELECT k FROM KEGGGeneDisease k WHERE k.kEGGGeneWID = :kEGGGeneWID"),
    @NamedQuery(name = "KEGGGeneDisease.findByEntry", query = "SELECT k FROM KEGGGeneDisease k WHERE k.entry = :entry"),
    @NamedQuery(name = "KEGGGeneDisease.findByDisease", query = "SELECT k FROM KEGGGeneDisease k WHERE k.disease = :disease")})
public class KEGGGeneDisease implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGGene_WID")
    private long kEGGGeneWID;
    @Basic(optional = false)
    @Column(name = "Entry")
    private String entry;
    @Column(name = "Disease")
    private String disease;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGGene_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGGene kEGGGene;

    public KEGGGeneDisease() {
    }

    public KEGGGeneDisease(Long wid) {
        this.wid = wid;
    }

    public KEGGGeneDisease(Long wid, long kEGGGeneWID, String entry) {
        this.wid = wid;
        this.kEGGGeneWID = kEGGGeneWID;
        this.entry = entry;
    }

    public KEGGGene getkEGGGene() {
        return kEGGGene;
    }

    public void setkEGGGene(KEGGGene kEGGGene) {
        this.kEGGGene = kEGGGene;
    }

    public long getkEGGGeneWID() {
        return kEGGGeneWID;
    }

    public void setkEGGGeneWID(long kEGGGeneWID) {
        this.kEGGGeneWID = kEGGGeneWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getKEGGGeneWID() {
        return kEGGGeneWID;
    }

    public void setKEGGGeneWID(long kEGGGeneWID) {
        this.kEGGGeneWID = kEGGGeneWID;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
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
        final KEGGGeneDisease other = (KEGGGeneDisease) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGGeneWID != other.kEGGGeneWID) {
            return false;
        }
        if (!Objects.equals(this.entry, other.entry)) {
            return false;
        }
        if (!Objects.equals(this.disease, other.disease)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGeneDisease{" + "wid=" + wid + ", kEGGGeneWID=" + kEGGGeneWID + ", entry=" + entry + ", disease=" + disease + '}';
    }
}
