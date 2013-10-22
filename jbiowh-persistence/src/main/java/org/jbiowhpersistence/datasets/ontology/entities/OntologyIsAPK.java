package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OntologyIsAPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Embeddable
public class OntologyIsAPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Ontology_WID")
    private long ontologyWID;
    @Basic(optional = false)
    @Column(name = "IsAOntology_WID")
    private long isAOntologyWID;

    public OntologyIsAPK() {
    }

    public OntologyIsAPK(long ontologyWID, long isAOntologyWID) {
        this.ontologyWID = ontologyWID;
        this.isAOntologyWID = isAOntologyWID;
    }

    public long getOntologyWID() {
        return ontologyWID;
    }

    public void setOntologyWID(long ontologyWID) {
        this.ontologyWID = ontologyWID;
    }

    public long getIsAOntologyWID() {
        return isAOntologyWID;
    }

    public void setIsAOntologyWID(long isAOntologyWID) {
        this.isAOntologyWID = isAOntologyWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ontologyWID;
        hash += (int) isAOntologyWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyIsAPK)) {
            return false;
        }
        OntologyIsAPK other = (OntologyIsAPK) object;
        if (this.ontologyWID != other.ontologyWID) {
            return false;
        }
        if (this.isAOntologyWID != other.isAOntologyWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.ontology.entities.OntologyIsAPK[ ontologyWID=" + ontologyWID + ", isAOntologyWID=" + isAOntologyWID + " ]";
    }
}
