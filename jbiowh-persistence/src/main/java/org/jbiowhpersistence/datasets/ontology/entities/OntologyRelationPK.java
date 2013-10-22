package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OntologyRelationPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Embeddable
public class OntologyRelationPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Ontology_WID")
    private long ontologyWID;
    @Basic(optional = false)
    @Column(name = "OtherOntology_WID")
    private long otherOntologyWID;

    public OntologyRelationPK() {
    }

    public OntologyRelationPK(long ontologyWID, long otherOntologyWID) {
        this.ontologyWID = ontologyWID;
        this.otherOntologyWID = otherOntologyWID;
    }

    public long getOntologyWID() {
        return ontologyWID;
    }

    public void setOntologyWID(long ontologyWID) {
        this.ontologyWID = ontologyWID;
    }

    public long getOtherOntologyWID() {
        return otherOntologyWID;
    }

    public void setOtherOntologyWID(long otherOntologyWID) {
        this.otherOntologyWID = otherOntologyWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ontologyWID;
        hash += (int) otherOntologyWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyRelationPK)) {
            return false;
        }
        OntologyRelationPK other = (OntologyRelationPK) object;
        if (this.ontologyWID != other.ontologyWID) {
            return false;
        }
        if (this.otherOntologyWID != other.otherOntologyWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.ontology.entities.OntologyRelationPK[ ontologyWID=" + ontologyWID + ", otherOntologyWID=" + otherOntologyWID + " ]";
    }
}
