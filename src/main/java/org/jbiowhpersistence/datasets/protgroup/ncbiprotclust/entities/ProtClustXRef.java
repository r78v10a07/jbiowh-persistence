package org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is theProtClustXRef entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 13, 2014
 */
@Embeddable
@Table(name = "ProtClustXRef")
@XmlRootElement
public class ProtClustXRef implements Serializable {

    @Basic(optional = false)
    @Column(name = "Id")
    private String id;

    public ProtClustXRef() {
    }

    public ProtClustXRef(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final ProtClustXRef other = (ProtClustXRef) obj;
        return !((this.id == null) ? (other.id != null) : !this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "ProtClustXRef{" + "id=" + id + '}';
    }
}
