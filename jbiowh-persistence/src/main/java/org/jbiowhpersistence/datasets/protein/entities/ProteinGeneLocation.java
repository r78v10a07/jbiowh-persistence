package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the ProteinGeneLocation entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinGeneLocation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinGeneLocation.findAll", query = "SELECT p FROM ProteinGeneLocation p"),
    @NamedQuery(name = "ProteinGeneLocation.findByWid", query = "SELECT p FROM ProteinGeneLocation p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinGeneLocation.findByProteinWID", query = "SELECT p FROM ProteinGeneLocation p WHERE p.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinGeneLocation.findByName", query = "SELECT p FROM ProteinGeneLocation p WHERE p.name = :name"),
    @NamedQuery(name = "ProteinGeneLocation.findByType", query = "SELECT p FROM ProteinGeneLocation p WHERE p.type = :type"),
    @NamedQuery(name = "ProteinGeneLocation.findByStatus", query = "SELECT p FROM ProteinGeneLocation p WHERE p.status = :status")})
public class ProteinGeneLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Column(name = "Name")
    private String name;
    @Column(name = "Type")
    private String type;
    @Column(name = "Status")
    private String status;

    public ProteinGeneLocation() {
    }

    public ProteinGeneLocation(Long wid) {
        this.wid = wid;
    }

    public ProteinGeneLocation(Long wid, long proteinWID) {
        this.wid = wid;
        this.proteinWID = proteinWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinGeneLocation other = (ProteinGeneLocation) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinGeneLocation[ wid=" + wid + " ]";
    }
}
