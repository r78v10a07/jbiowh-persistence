package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
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
 * This class is the PfamClanDatabaseLinks entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ 
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamClanDatabaseLinks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamClanDatabaseLinks.findAll", query = "SELECT p FROM PfamClanDatabaseLinks p"),
    @NamedQuery(name = "PfamClanDatabaseLinks.findByWid", query = "SELECT p FROM PfamClanDatabaseLinks p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamClanDatabaseLinks.findByPfamClansWID", query = "SELECT p FROM PfamClanDatabaseLinks p WHERE p.pfamClansWID = :pfamClansWID"),
    @NamedQuery(name = "PfamClanDatabaseLinks.findByDbId", query = "SELECT p FROM PfamClanDatabaseLinks p WHERE p.dbId = :dbId"),
    @NamedQuery(name = "PfamClanDatabaseLinks.findByComment", query = "SELECT p FROM PfamClanDatabaseLinks p WHERE p.comment = :comment"),
    @NamedQuery(name = "PfamClanDatabaseLinks.findByDbLink", query = "SELECT p FROM PfamClanDatabaseLinks p WHERE p.dbLink = :dbLink"),
    @NamedQuery(name = "PfamClanDatabaseLinks.findByOtherParams", query = "SELECT p FROM PfamClanDatabaseLinks p WHERE p.otherParams = :otherParams")})
public class PfamClanDatabaseLinks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "PfamClans_WID")
    private long pfamClansWID;
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
    @JoinColumn(name = "PfamClans_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamClans pfamClans;

    public PfamClanDatabaseLinks() {
    }

    public PfamClanDatabaseLinks(Long wid) {
        this.wid = wid;
    }

    public PfamClanDatabaseLinks(Long wid, long pfamClansWID, String dbId, String dbLink) {
        this.wid = wid;
        this.pfamClansWID = pfamClansWID;
        this.dbId = dbId;
        this.dbLink = dbLink;
    }

    public PfamClans getPfamClans() {
        return pfamClans;
    }

    public void setPfamClans(PfamClans pfamClans) {
        this.pfamClans = pfamClans;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getPfamClansWID() {
        return pfamClansWID;
    }

    public void setPfamClansWID(long pfamClansWID) {
        this.pfamClansWID = pfamClansWID;
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PfamClanDatabaseLinks other = (PfamClanDatabaseLinks) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.dbId, other.dbId)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.dbLink, other.dbLink)) {
            return false;
        }
        if (!Objects.equals(this.otherParams, other.otherParams)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamClanDatabaseLinks{" + "wid=" + wid + ", pfamClansWID=" + pfamClansWID + ", dbId=" + dbId + ", comment=" + comment + ", dbLink=" + dbLink + ", otherParams=" + otherParams + '}';
    }
}
