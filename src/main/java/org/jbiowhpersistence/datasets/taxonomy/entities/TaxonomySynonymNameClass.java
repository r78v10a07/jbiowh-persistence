package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the TaxonomySynonymNameClass entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 21, 2011
 */
@Entity
@Table(name = "TaxonomySynonymNameClass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxonomySynonymNameClass.findAll", query = "SELECT t FROM TaxonomySynonymNameClass t"),
    @NamedQuery(name = "TaxonomySynonymNameClass.findByWid", query = "SELECT t FROM TaxonomySynonymNameClass t WHERE t.wid = :wid"),
    @NamedQuery(name = "TaxonomySynonymNameClass.findByNameClass", query = "SELECT t FROM TaxonomySynonymNameClass t WHERE t.nameClass = :nameClass")})
public class TaxonomySynonymNameClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "NameClass")
    private String nameClass;

    public TaxonomySynonymNameClass() {
    }

    public TaxonomySynonymNameClass(Long wid) {
        this.wid = wid;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaxonomySynonymNameClass other = (TaxonomySynonymNameClass) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.nameClass, other.nameClass)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.wid);
        return hash;
    }

    @Override
    public String toString() {
        return "TaxonomySynonymNameClass{"
                + "wid = " + wid
                + "nameClass=" + nameClass + '}';
    }
}
