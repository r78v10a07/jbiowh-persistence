package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is OntologyAlternativeId entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Entity
@Table(name = "OntologyAlternativeId")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OntologyAlternativeId.findAll", query = "SELECT o FROM OntologyAlternativeId o"),
    @NamedQuery(name = "OntologyAlternativeId.findByOntologyWID", query = "SELECT o FROM OntologyAlternativeId o WHERE o.ontologyAlternativeIdPK.ontologyWID = :ontologyWID"),
    @NamedQuery(name = "OntologyAlternativeId.findByAltId", query = "SELECT o FROM OntologyAlternativeId o WHERE o.ontologyAlternativeIdPK.altId = :altId")})
public class OntologyAlternativeId implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OntologyAlternativeIdPK ontologyAlternativeIdPK;
    @ManyToOne
    @JoinColumn(name = "Ontology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology ontology;

    public OntologyAlternativeId() {
    }

    public OntologyAlternativeId(OntologyAlternativeIdPK ontologyAlternativeIdPK) {
        this.ontologyAlternativeIdPK = ontologyAlternativeIdPK;
    }

    public OntologyAlternativeId(long ontologyWID, String altId) {
        this.ontologyAlternativeIdPK = new OntologyAlternativeIdPK(ontologyWID, altId);
    }

    public Ontology getOntology() {
        return ontology;
    }

    public void setOntology(Ontology ontology) {
        this.ontology = ontology;
    }

    public OntologyAlternativeIdPK getOntologyAlternativeIdPK() {
        return ontologyAlternativeIdPK;
    }

    public void setOntologyAlternativeIdPK(OntologyAlternativeIdPK ontologyAlternativeIdPK) {
        this.ontologyAlternativeIdPK = ontologyAlternativeIdPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ontologyAlternativeIdPK != null ? ontologyAlternativeIdPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyAlternativeId)) {
            return false;
        }
        OntologyAlternativeId other = (OntologyAlternativeId) object;
        if ((this.ontologyAlternativeIdPK == null && other.ontologyAlternativeIdPK != null) || (this.ontologyAlternativeIdPK != null && !this.ontologyAlternativeIdPK.equals(other.ontologyAlternativeIdPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OntologyAlternativeId["
                + " Ontology_WID=" + ontologyAlternativeIdPK.getOntologyWID()
                + " AltId=" + ontologyAlternativeIdPK.getAltId()
                + "]";
    }
}
