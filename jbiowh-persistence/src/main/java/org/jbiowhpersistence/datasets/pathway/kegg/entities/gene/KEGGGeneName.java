package org.jbiowhpersistence.datasets.pathway.kegg.entities.gene;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Gene Name entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGGeneName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGeneName.findAll", query = "SELECT k FROM KEGGGeneName k"),
    @NamedQuery(name = "KEGGGeneName.findByWid", query = "SELECT k FROM KEGGGeneName k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGGeneName.findByKEGGGeneWID", query = "SELECT k FROM KEGGGeneName k WHERE k.kEGGGeneWID = :kEGGGeneWID"),
    @NamedQuery(name = "KEGGGeneName.findByName", query = "SELECT k FROM KEGGGeneName k WHERE k.name = :name")})
public class KEGGGeneName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGGene_WID")
    private long kEGGGeneWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGGene_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGGene kEGGGene;

    public KEGGGeneName() {
    }

    public KEGGGeneName(Long wid) {
        this.wid = wid;
    }

    public KEGGGeneName(Long wid, long kEGGGeneWID, String name) {
        this.wid = wid;
        this.kEGGGeneWID = kEGGGeneWID;
        this.name = name;
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
        final KEGGGeneName other = (KEGGGeneName) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGGeneWID != other.kEGGGeneWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGeneName{" + "wid=" + wid + ", kEGGGeneWID=" + kEGGGeneWID + ", name=" + name + '}';
    }
}
