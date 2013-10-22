package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OntologyToConsiderPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Embeddable
public class OntologyToConsiderPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Ontology_WID")
    private long ontologyWID;
    @Basic(optional = false)
    @Column(name = "ToConsiderOntology_WID")
    private long toConsiderOntologyWID;

    public OntologyToConsiderPK() {
    }

    public OntologyToConsiderPK(long ontologyWID, long toConsiderOntologyWID) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ontologyWID;
        hash += (int) toConsiderOntologyWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyToConsiderPK)) {
            return false;
        }
        OntologyToConsiderPK other = (OntologyToConsiderPK) object;
        if (this.ontologyWID != other.ontologyWID) {
            return false;
        }
        if (this.toConsiderOntologyWID != other.toConsiderOntologyWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.ontology.entities.OntologyToConsiderPK[ ontologyWID=" + ontologyWID + ", toConsiderOntologyWID=" + toConsiderOntologyWID + " ]";
    }
}
