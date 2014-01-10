package org.jbiowhpersistence.datasets.protgroup.cog.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the COGMember entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 19, 2013
 */
@Embeddable
@Table(name = "COGMember")
@XmlRootElement
public class COGMember implements Serializable {

    @Basic(optional = false)
    @Column(name = "Organism")
    private String organism;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;

    public COGMember() {
    }

    public COGMember(String organism, String id) {
        this.organism = organism;
        this.id = id;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.organism != null ? this.organism.hashCode() : 0);
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final COGMember other = (COGMember) obj;
        if ((this.organism == null) ? (other.organism != null) : !this.organism.equals(other.organism)) {
            return false;
        }
        return !((this.id == null) ? (other.id != null) : !this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "COGMember{" + "organism=" + organism + ", id=" + id + '}';
    }
}
