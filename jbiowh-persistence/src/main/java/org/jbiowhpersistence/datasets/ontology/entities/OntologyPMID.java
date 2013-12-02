package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OntologyPMID entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jun 28, 2011
 */
@Embeddable
@Table(name = "OntologyPMID")
@XmlRootElement
public class OntologyPMID implements Serializable {

    @Basic(optional = false)
    @Column(name = "Ontology_WID")
    private long ontologyWID;
    @Basic(optional = false)
    @Column(name = "PMID")
    private long pmid;

    public OntologyPMID() {
    }

    public OntologyPMID(long ontologyWID, long pmid) {
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
        int hash = 3;
        hash = 23 * hash + (int) (this.ontologyWID ^ (this.ontologyWID >>> 32));
        hash = 23 * hash + (int) (this.pmid ^ (this.pmid >>> 32));
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
        final OntologyPMID other = (OntologyPMID) obj;
        if (this.ontologyWID != other.ontologyWID) {
            return false;
        }
        return this.pmid == other.pmid;
    }

    @Override
    public String toString() {
        return "OntologyPMID{" + "ontologyWID=" + ontologyWID + ", pmid=" + pmid + '}';
    }
}
