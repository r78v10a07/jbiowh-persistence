package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the PfamPDB entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ 
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamPDB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamPDB.findAll", query = "SELECT p FROM PfamPDB p"),
    @NamedQuery(name = "PfamPDB.findByWid", query = "SELECT p FROM PfamPDB p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamPDB.findByPdbId", query = "SELECT p FROM PfamPDB p WHERE p.pdbId = :pdbId"),
    @NamedQuery(name = "PfamPDB.findByKeywords", query = "SELECT p FROM PfamPDB p WHERE p.keywords = :keywords"),
    @NamedQuery(name = "PfamPDB.findByDate", query = "SELECT p FROM PfamPDB p WHERE p.date = :date"),
    @NamedQuery(name = "PfamPDB.findByResolution", query = "SELECT p FROM PfamPDB p WHERE p.resolution = :resolution"),
    @NamedQuery(name = "PfamPDB.findByMethod", query = "SELECT p FROM PfamPDB p WHERE p.method = :method")})
public class PfamPDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "pdb_id")
    private String pdbId;
    @Column(name = "keywords")
    private String keywords;
    @Lob
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private String date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "resolution")
    private BigDecimal resolution;
    @Column(name = "method")
    private String method;
    @Lob
    @Column(name = "author")
    private String author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamPDB")
    private Set<PfamPDBResidueData> pfamPDBResidueDatas;

    public PfamPDB() {
    }

    public PfamPDB(Long wid) {
        this.wid = wid;
    }

    public PfamPDB(Long wid, String pdbId) {
        this.wid = wid;
        this.pdbId = pdbId;
    }

    @XmlTransient
    public Set<PfamPDBResidueData> getPfamPDBResidueDatas() {
        return pfamPDBResidueDatas;
    }

    public void setPfamPDBResidueDatas(Set<PfamPDBResidueData> pfamPDBResidueDatas) {
        this.pfamPDBResidueDatas = pfamPDBResidueDatas;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getPdbId() {
        return pdbId;
    }

    public void setPdbId(String pdbId) {
        this.pdbId = pdbId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getResolution() {
        return resolution;
    }

    public void setResolution(BigDecimal resolution) {
        this.resolution = resolution;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        final PfamPDB other = (PfamPDB) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.pdbId, other.pdbId)) {
            return false;
        }
        if (!Objects.equals(this.keywords, other.keywords)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.resolution, other.resolution)) {
            return false;
        }
        if (!Objects.equals(this.method, other.method)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamPDB{" + "wid=" + wid + ", pdbId=" + pdbId + ", keywords=" + keywords + ", title=" + title + ", date=" + date + ", resolution=" + resolution + ", method=" + method + ", author=" + author + '}';
    }
}
