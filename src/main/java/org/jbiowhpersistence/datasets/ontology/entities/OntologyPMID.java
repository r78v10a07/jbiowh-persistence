package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OntologyPMID entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Entity
@Table(name = "OntologyPMID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OntologyPMID.findAll", query = "SELECT o FROM OntologyPMID o"),
    @NamedQuery(name = "OntologyPMID.findByOntologyWID", query = "SELECT o FROM OntologyPMID o WHERE o.ontologyPMIDPK.ontologyWID = :ontologyWID"),
    @NamedQuery(name = "OntologyPMID.findByPmid", query = "SELECT o FROM OntologyPMID o WHERE o.ontologyPMIDPK.pmid = :pmid")})
public class OntologyPMID implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OntologyPMIDPK ontologyPMIDPK;
    @ManyToOne
    @JoinColumn(name = "Ontology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology ontology;

    public OntologyPMID() {
    }

    public OntologyPMID(OntologyPMIDPK ontologyPMIDPK) {
        this.ontologyPMIDPK = ontologyPMIDPK;
    }

    public OntologyPMID(long ontologyWID, long pmid) {
        this.ontologyPMIDPK = new OntologyPMIDPK(ontologyWID, pmid);
    }

    public Ontology getOntology() {
        return ontology;
    }

    public void setOntology(Ontology ontology) {
        this.ontology = ontology;
    }

    public OntologyPMIDPK getOntologyPMIDPK() {
        return ontologyPMIDPK;
    }

    public void setOntologyPMIDPK(OntologyPMIDPK ontologyPMIDPK) {
        this.ontologyPMIDPK = ontologyPMIDPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ontologyPMIDPK != null ? ontologyPMIDPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyPMID)) {
            return false;
        }
        OntologyPMID other = (OntologyPMID) object;
        if ((this.ontologyPMIDPK == null && other.ontologyPMIDPK != null) || (this.ontologyPMIDPK != null && !this.ontologyPMIDPK.equals(other.ontologyPMIDPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OntologyPMID["
                + " ontology_WID=" + ontologyPMIDPK.getOntologyWID()
                + " PMID=" + ontologyPMIDPK.getPmid()
                + ']';
    }
}
