package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is OntologyAlternativeId entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jun 28, 2011
 */
@Embeddable
@Table(name = "OntologyAlternativeId")
@XmlRootElement
public class OntologyAlternativeId implements Serializable {

    @Basic(optional = false)
    @Column(name = "Ontology_WID")
    private long ontologyWID;
    @Basic(optional = false)
    @Column(name = "AltId")
    private String altId;

    public OntologyAlternativeId() {
    }

    public OntologyAlternativeId(long ontologyWID, String altId) {
        this.ontologyWID = ontologyWID;
        this.altId = altId;
    }

    public long getOntologyWID() {
        return ontologyWID;
    }

    public void setOntologyWID(long ontologyWID) {
        this.ontologyWID = ontologyWID;
    }

    public String getAltId() {
        return altId;
    }

    public void setAltId(String altId) {
        this.altId = altId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.ontologyWID ^ (this.ontologyWID >>> 32));
        hash = 41 * hash + (this.altId != null ? this.altId.hashCode() : 0);
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
        final OntologyAlternativeId other = (OntologyAlternativeId) obj;
        if (this.ontologyWID != other.ontologyWID) {
            return false;
        }
        return !((this.altId == null) ? (other.altId != null) : !this.altId.equals(other.altId));
    }

    @Override
    public String toString() {
        return "OntologyAlternativeId{" + "ontologyWID=" + ontologyWID + ", altId=" + altId + '}';
    }
}
