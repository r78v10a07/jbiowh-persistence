package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OntologyAlternativeIdPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Embeddable
public class OntologyAlternativeIdPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Ontology_WID")
    private long ontologyWID;
    @Basic(optional = false)
    @Column(name = "AltId")
    private String altId;

    public OntologyAlternativeIdPK() {
    }

    public OntologyAlternativeIdPK(long ontologyWID, String altId) {
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
        int hash = 0;
        hash += (int) ontologyWID;
        hash += (altId != null ? altId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyAlternativeIdPK)) {
            return false;
        }
        OntologyAlternativeIdPK other = (OntologyAlternativeIdPK) object;
        if (this.ontologyWID != other.ontologyWID) {
            return false;
        }
        if ((this.altId == null && other.altId != null) || (this.altId != null && !this.altId.equals(other.altId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.ontology.entities.OntologyAlternativeIdPK[ ontologyWID=" + ontologyWID + ", altId=" + altId + " ]";
    }
}
