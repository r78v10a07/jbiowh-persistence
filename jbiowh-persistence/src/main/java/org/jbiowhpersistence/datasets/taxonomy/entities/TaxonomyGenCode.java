package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the TaxonomyGenCode entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 21, 2011
 */
@Entity
@Table(name = "TaxonomyGenCode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxonomyGenCode.findAll", query = "SELECT t FROM TaxonomyGenCode t"),
    @NamedQuery(name = "TaxonomyGenCode.findByWid", query = "SELECT t FROM TaxonomyGenCode t WHERE t.wid = :wid"),
    @NamedQuery(name = "TaxonomyGenCode.findByGenCodeId", query = "SELECT t FROM TaxonomyGenCode t WHERE t.genCodeId = :genCodeId"),
    @NamedQuery(name = "TaxonomyGenCode.findByAbbreviation", query = "SELECT t FROM TaxonomyGenCode t WHERE t.abbreviation = :abbreviation"),
    @NamedQuery(name = "TaxonomyGenCode.findByName", query = "SELECT t FROM TaxonomyGenCode t WHERE t.name = :name"),
    @NamedQuery(name = "TaxonomyGenCode.findByCode", query = "SELECT t FROM TaxonomyGenCode t WHERE t.code = :code"),
    @NamedQuery(name = "TaxonomyGenCode.findByStart", query = "SELECT t FROM TaxonomyGenCode t WHERE t.start = :start")})
public class TaxonomyGenCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "GenCodeId")
    private int genCodeId;
    @Column(name = "Abbreviation")
    private String abbreviation;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Column(name = "Start")
    private String start;

    public TaxonomyGenCode() {
    }

    public TaxonomyGenCode(Long wid) {
        this.wid = wid;
    }

    public TaxonomyGenCode(Long wid, int genCodeId, String name, String code) {
        this.wid = wid;
        this.genCodeId = genCodeId;
        this.name = name;
        this.code = code;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public int getGenCodeId() {
        return genCodeId;
    }

    public void setGenCodeId(int genCodeId) {
        this.genCodeId = genCodeId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaxonomyGenCode other = (TaxonomyGenCode) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.genCodeId != other.genCodeId) {
            return false;
        }
        if (!Objects.equals(this.abbreviation, other.abbreviation)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.wid);
        return hash;
    }

    @Override
    public String toString() {
        return "TaxonomyGenCode[ wid=" + wid
                + ", genCodeId=" + genCodeId
                + ", abbreviation=" + abbreviation
                + ", name=" + name
                + ", code=" + code
                + ", start=" + start
                + "]";
    }
}
