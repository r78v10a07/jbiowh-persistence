package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamADatabaseLinks entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamADatabaseLinks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamADatabaseLinks.findAll", query = "SELECT p FROM PfamADatabaseLinks p"),
    @NamedQuery(name = "PfamADatabaseLinks.findByWid", query = "SELECT p FROM PfamADatabaseLinks p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamADatabaseLinks.findByPfamAWID", query = "SELECT p FROM PfamADatabaseLinks p WHERE p.pfamAWID = :pfamAWID"),
    @NamedQuery(name = "PfamADatabaseLinks.findByDbId", query = "SELECT p FROM PfamADatabaseLinks p WHERE p.dbId = :dbId"),
    @NamedQuery(name = "PfamADatabaseLinks.findByComment", query = "SELECT p FROM PfamADatabaseLinks p WHERE p.comment = :comment"),
    @NamedQuery(name = "PfamADatabaseLinks.findByDbLink", query = "SELECT p FROM PfamADatabaseLinks p WHERE p.dbLink = :dbLink"),
    @NamedQuery(name = "PfamADatabaseLinks.findByOtherParams", query = "SELECT p FROM PfamADatabaseLinks p WHERE p.otherParams = :otherParams")})
public class PfamADatabaseLinks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "PfamA_WID")
    private long pfamAWID;
    @Basic(optional = false)
    @Column(name = "db_id")
    private String dbId;
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "db_link")
    private String dbLink;
    @Column(name = "other_params")
    private String otherParams;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamA_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamAbioWH pfamA;

    public PfamADatabaseLinks() {
    }

    public PfamADatabaseLinks(Long wid) {
        this.wid = wid;
    }

    public PfamADatabaseLinks(Long wid, long pfamAWID, String dbId, String dbLink) {
        this.wid = wid;
        this.pfamAWID = pfamAWID;
        this.dbId = dbId;
        this.dbLink = dbLink;
    }

    public PfamAbioWH getPfamA() {
        return pfamA;
    }

    public void setPfamA(PfamAbioWH pfamA) {
        this.pfamA = pfamA;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getPfamAWID() {
        return pfamAWID;
    }

    public void setPfamAWID(long pfamAWID) {
        this.pfamAWID = pfamAWID;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDbLink() {
        return dbLink;
    }

    public void setDbLink(String dbLink) {
        this.dbLink = dbLink;
    }

    public String getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(String otherParams) {
        this.otherParams = otherParams;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PfamADatabaseLinks)) {
            return false;
        }
        PfamADatabaseLinks other = (PfamADatabaseLinks) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamADatabaseLinks{" + "wid=" + wid + ", pfamAWID=" + pfamAWID + ", dbId=" + dbId + ", comment=" + comment + ", dbLink=" + dbLink + ", otherParams=" + otherParams + '}';
    }
}
