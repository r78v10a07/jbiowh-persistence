package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the OntologyToConsider entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jun 28, 2011
 */
@Embeddable
@Table(name = "OntologyToConsider")
@XmlRootElement
public class OntologyToConsider implements Serializable {

    @Basic(optional = false)
    @Column(name = "Ontology_WID")
    private long ontologyWID;
    @Basic(optional = false)
    @Column(name = "ToConsiderOntology_WID")
    private long toConsiderOntologyWID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ToConsiderOntology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology toConsiderOntology;

    public OntologyToConsider() {
    }

    public OntologyToConsider(long ontologyWID, long toConsiderOntologyWID) {
        this.ontologyWID = ontologyWID;
        this.toConsiderOntologyWID = toConsiderOntologyWID;
    }

    public long getOntologyWID() {
        return ontologyWID;
    }

    public void setOntologyWID(long ontologyWID) {
        this.ontologyWID = ontologyWID;
    }

    public long getToConsiderOntologyWID() {
        return toConsiderOntologyWID;
    }

    public void setToConsiderOntologyWID(long toConsiderOntologyWID) {
        this.toConsiderOntologyWID = toConsiderOntologyWID;
    }

    @XmlTransient
    public Ontology getToConsiderOntology() {
        return toConsiderOntology;
    }

    public void setToConsiderOntology(Ontology toConsiderOntology) {
        this.toConsiderOntology = toConsiderOntology;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.ontologyWID ^ (this.ontologyWID >>> 32));
        hash = 97 * hash + (int) (this.toConsiderOntologyWID ^ (this.toConsiderOntologyWID >>> 32));
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
        final OntologyToConsider other = (OntologyToConsider) obj;
        if (this.ontologyWID != other.ontologyWID) {
            return false;
        }
        return this.toConsiderOntologyWID == other.toConsiderOntologyWID;
    }

    @Override
    public String toString() {
        return "OntologyToConsider{" + "ontologyWID=" + ontologyWID + ", toConsiderOntologyWID=" + toConsiderOntologyWID + ", toConsiderOntology=" + toConsiderOntology + '}';
    }
}
