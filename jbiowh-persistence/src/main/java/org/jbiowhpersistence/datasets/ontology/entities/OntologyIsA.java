package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is OntologyIsA entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jun 28, 2011
 */
@Embeddable
@Table(name = "OntologyIsA")
@XmlRootElement
public class OntologyIsA implements Serializable {

    @Basic(optional = false)
    @Column(name = "IsAOntology_WID")
    private long isAOntologyWID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IsAOntology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology isAOntology;

    public OntologyIsA() {
    }

    public OntologyIsA(long isAOntologyWID) {
        this.isAOntologyWID = isAOntologyWID;
    }

    public long getIsAOntologyWID() {
        return isAOntologyWID;
    }

    public void setIsAOntologyWID(long isAOntologyWID) {
        this.isAOntologyWID = isAOntologyWID;
    }

    @XmlTransient
    public Ontology getIsAOntology() {
        return isAOntology;
    }

    public void setIsAOntology(Ontology isAOntology) {
        this.isAOntology = isAOntology;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.isAOntologyWID ^ (this.isAOntologyWID >>> 32));
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
        final OntologyIsA other = (OntologyIsA) obj;
        return this.isAOntologyWID == other.isAOntologyWID;
    }

    @Override
    public String toString() {
        return "OntologyIsA{isAOntologyWID=" + isAOntologyWID + ", isAOntology=" + isAOntology + '}';
    }

}
