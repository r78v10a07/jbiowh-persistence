package org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is KEGG Glycan Class entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGGlycanClass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGlycanClass.findAll", query = "SELECT k FROM KEGGGlycanClass k"),
    @NamedQuery(name = "KEGGGlycanClass.findByWid", query = "SELECT k FROM KEGGGlycanClass k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGGlycanClass.findByClass1", query = "SELECT k FROM KEGGGlycanClass k WHERE k.class1 = :class1")})
public class KEGGGlycanClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Class")
    private String class1;

    public KEGGGlycanClass() {
    }

    public KEGGGlycanClass(Long wid) {
        this.wid = wid;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
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
        final KEGGGlycanClass other = (KEGGGlycanClass) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.class1, other.class1)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGlycanClass{" + "wid=" + wid + ", class1=" + class1 + '}';
    }
}
