package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the OntologyRelation entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jun 28, 2011
 */
@Embeddable
@Table(name = "OntologyRelation")
@XmlRootElement
public class OntologyRelation implements Serializable {

    @Basic(optional = false)
    @Column(name = "OtherOntology_WID")
    private long otherOntologyWID;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @XmlTransient
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OtherOntology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology otherOntology;

    public OntologyRelation() {
    }

    public OntologyRelation(long otherOntologyWID, String type) {
        this.otherOntologyWID = otherOntologyWID;
        this.type = type;
    }

    public long getOtherOntologyWID() {
        return otherOntologyWID;
    }

    public void setOtherOntologyWID(long otherOntologyWID) {
        this.otherOntologyWID = otherOntologyWID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Ontology getOtherOntology() {
        return otherOntology;
    }

    public void setOtherOntology(Ontology otherOntology) {
        this.otherOntology = otherOntology;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.otherOntologyWID ^ (this.otherOntologyWID >>> 32));
        hash = 41 * hash + (this.type != null ? this.type.hashCode() : 0);
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
        final OntologyRelation other = (OntologyRelation) obj;
        if (this.otherOntologyWID != other.otherOntologyWID) {
            return false;
        }
        return !((this.type == null) ? (other.type != null) : !this.type.equals(other.type));
    }

    @Override
    public String toString() {
        return "OntologyRelation{otherOntologyWID=" + otherOntologyWID + ", type=" + type + ", otherOntology=" + otherOntology + '}';
    }
}
