package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OntologyPMIDPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Embeddable
public class OntologyPMIDPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Ontology_WID")
    private long ontologyWID;
    @Basic(optional = false)
    @Column(name = "PMID")
    private long pmid;

    public OntologyPMIDPK() {
    }

    public OntologyPMIDPK(long ontologyWID, long pmid) {
        this.ontologyWID = ontologyWID;
        this.pmid = pmid;
    }

    public long getOntologyWID() {
        return ontologyWID;
    }

    public void setOntologyWID(long ontologyWID) {
        this.ontologyWID = ontologyWID;
    }

    public long getPmid() {
        return pmid;
    }

    public void setPmid(long pmid) {
        this.pmid = pmid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ontologyWID;
        hash += (int) pmid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyPMIDPK)) {
            return false;
        }
        OntologyPMIDPK other = (OntologyPMIDPK) object;
        if (this.ontologyWID != other.ontologyWID) {
            return false;
        }
        if (this.pmid != other.pmid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.ontology.entities.OntologyPMIDPK[ ontologyWID=" + ontologyWID + ", pmid=" + pmid + " ]";
    }
}
