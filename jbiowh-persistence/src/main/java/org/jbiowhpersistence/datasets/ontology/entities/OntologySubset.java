package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OntologySubset entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Entity
@Table(name = "OntologySubset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OntologySubset.findAll", query = "SELECT o FROM OntologySubset o"),
    @NamedQuery(name = "OntologySubset.findByWid", query = "SELECT o FROM OntologySubset o WHERE o.wid = :wid"),
    @NamedQuery(name = "OntologySubset.findById", query = "SELECT o FROM OntologySubset o WHERE o.id = :id"),
    @NamedQuery(name = "OntologySubset.findByName", query = "SELECT o FROM OntologySubset o WHERE o.name = :name")})
public class OntologySubset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;

    public OntologySubset() {
    }

    public OntologySubset(Long wid) {
        this.wid = wid;
    }

    public OntologySubset(Long wid, String id, String name) {
        this.wid = wid;
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OntologySubset other = (OntologySubset) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "OntologySubset["
                + " wid=" + wid
                + " id=" + id
                + " name=" + name + ']';
    }
}
