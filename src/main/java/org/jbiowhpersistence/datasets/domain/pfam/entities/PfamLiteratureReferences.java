package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the PfamLiteratureReferences entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ 
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamLiteratureReferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamLiteratureReferences.findAll", query = "SELECT p FROM PfamLiteratureReferences p"),
    @NamedQuery(name = "PfamLiteratureReferences.findByWid", query = "SELECT p FROM PfamLiteratureReferences p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamLiteratureReferences.findByPmid", query = "SELECT p FROM PfamLiteratureReferences p WHERE p.pmid = :pmid"),
    @NamedQuery(name = "PfamLiteratureReferences.findByTitle", query = "SELECT p FROM PfamLiteratureReferences p WHERE p.title = :title"),
    @NamedQuery(name = "PfamLiteratureReferences.findByJournal", query = "SELECT p FROM PfamLiteratureReferences p WHERE p.journal = :journal")})
public class PfamLiteratureReferences implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "pmid")
    private Integer pmid;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "author")
    private String author;
    @Column(name = "journal")
    private String journal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamLiteratureReferences")
    @MapKey(name = "pfamAhasPfamLiteratureReferencesPK")
    private Map<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences> pfamAhasPfamLiteratureReferences; 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamLiteratureReferences")
    @MapKey(name = "pfamClanshasPfamLiteratureReferencesPK")
    private Map<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences> pfamClanshasPfamLiteratureReferences; 
    

    public PfamLiteratureReferences() {
    }

    public PfamLiteratureReferences(Long wid) {
        this.wid = wid;
    }
    
    public void setRelationsToNull() {
        setPfamAhasPfamLiteratureReferences(null);
    }

    @XmlTransient
    public Map<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences> getPfamClanshasPfamLiteratureReferences() {
        return pfamClanshasPfamLiteratureReferences;
    }

    public void setPfamClanshasPfamLiteratureReferences(Map<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences> pfamClanshasPfamLiteratureReferences) {
        this.pfamClanshasPfamLiteratureReferences = pfamClanshasPfamLiteratureReferences;
    }

    @XmlTransient
    public Map<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences> getPfamAhasPfamLiteratureReferences() {
        return pfamAhasPfamLiteratureReferences;
    }

    public void setPfamAhasPfamLiteratureReferences(Map<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences> pfamAhasPfamLiteratureReferences) {
        this.pfamAhasPfamLiteratureReferences = pfamAhasPfamLiteratureReferences;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public Integer getPmid() {
        return pmid;
    }

    public void setPmid(Integer pmid) {
        this.pmid = pmid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
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
        final PfamLiteratureReferences other = (PfamLiteratureReferences) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.pmid, other.pmid)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.journal, other.journal)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamLiteratureReferences{" + "wid=" + wid + ", pmid=" + pmid + ", title=" + title + ", author=" + author + ", journal=" + journal + '}';
    }
}
