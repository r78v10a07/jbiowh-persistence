package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Intact entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinIntAct")
@XmlRootElement
public class ProteinIntAct implements Serializable {

    @Basic(optional = false)
    @Column(name = "Id")
    private String id;

    public ProteinIntAct() {
    }

    public ProteinIntAct(String id) {
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
        final ProteinIntAct other = (ProteinIntAct) obj;
        return !((this.id == null) ? (other.id != null) : !this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "ProteinIntAct{id=" + id + '}';
    }
}
