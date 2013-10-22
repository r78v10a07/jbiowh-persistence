package org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGGEnzymeClass entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGEnzymeClass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGEnzymeClass.findAll", query = "SELECT k FROM KEGGEnzymeClass k"),
    @NamedQuery(name = "KEGGEnzymeClass.findByWid", query = "SELECT k FROM KEGGEnzymeClass k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGEnzymeClass.findByClass1", query = "SELECT k FROM KEGGEnzymeClass k WHERE k.class1 = :class1")})
public class KEGGEnzymeClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Class")
    private String class1;

    public KEGGEnzymeClass() {
    }

    public KEGGEnzymeClass(Long wid) {
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
        final KEGGEnzymeClass other = (KEGGEnzymeClass) obj;
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
        return "KEGGEnzymeClass{" + "wid=" + wid + ", class1=" + class1 + '}';
    }
}
