package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the Protein Keyword entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinKeyword")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinKeyword.findAll", query = "SELECT p FROM ProteinKeyword p"),
    @NamedQuery(name = "ProteinKeyword.findByWid", query = "SELECT p FROM ProteinKeyword p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinKeyword.findById", query = "SELECT p FROM ProteinKeyword p WHERE p.id = :id"),
    @NamedQuery(name = "ProteinKeyword.findByKeyword", query = "SELECT p FROM ProteinKeyword p WHERE p.keyword = :keyword")})
public class ProteinKeyword implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Id")
    private String id;
    @Column(name = "Keyword")
    private String keyword;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "proteinKeyword")
    private Set<Protein> protein;

    public ProteinKeyword() {
    }

    public ProteinKeyword(Long wid) {
        this.wid = wid;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @XmlTransient    
    public Set<Protein> getProtein() {
        return protein;
    }

    public void setProtein(Set<Protein> protein) {
        this.protein = protein;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinKeyword other = (ProteinKeyword) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.keyword, other.keyword);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProteinKeyword{"
                + " wid=" + wid
                + " id=" + id
                + " keyword=" + keyword
                + '}';
    }
}
