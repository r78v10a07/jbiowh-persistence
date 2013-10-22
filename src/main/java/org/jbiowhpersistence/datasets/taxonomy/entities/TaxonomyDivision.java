package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the TaxonomyDivision entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 21, 2011
 */
@Entity
@Table(name = "TaxonomyDivision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxonomyDivision.findAll", query = "SELECT t FROM TaxonomyDivision t"),
    @NamedQuery(name = "TaxonomyDivision.findByWid", query = "SELECT t FROM TaxonomyDivision t WHERE t.wid = :wid"),
    @NamedQuery(name = "TaxonomyDivision.findById", query = "SELECT t FROM TaxonomyDivision t WHERE t.id = :id"),
    @NamedQuery(name = "TaxonomyDivision.findByCode", query = "SELECT t FROM TaxonomyDivision t WHERE t.code = :code"),
    @NamedQuery(name = "TaxonomyDivision.findByName", query = "SELECT t FROM TaxonomyDivision t WHERE t.name = :name"),
    @NamedQuery(name = "TaxonomyDivision.findByComment", query = "SELECT t FROM TaxonomyDivision t WHERE t.comment = :comment")})
public class TaxonomyDivision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Comment")
    private String comment;

    public TaxonomyDivision() {
    }

    public TaxonomyDivision(Long wid) {
        this.wid = wid;
    }

    public TaxonomyDivision(Long wid, int id, String code, String name) {
        this.wid = wid;
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaxonomyDivision other = (TaxonomyDivision) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.wid);
        return hash;
    }

    @Override
    public String toString() {
        return "TaxonomyDivision[ wid=" + wid
                + ", id=" + id
                + ", code=" + code
                + ", name=" + name
                + ", comment=" + comment
                + "]";
    }
}
