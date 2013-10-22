package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OntologyToConsider entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Entity
@Table(name = "OntologyToConsider")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OntologyToConsider.findAll", query = "SELECT o FROM OntologyToConsider o"),
    @NamedQuery(name = "OntologyToConsider.findByOntologyWID", query = "SELECT o FROM OntologyToConsider o WHERE o.ontologyToConsiderPK.ontologyWID = :ontologyWID"),
    @NamedQuery(name = "OntologyToConsider.findByToConsiderOntologyWID", query = "SELECT o FROM OntologyToConsider o WHERE o.ontologyToConsiderPK.toConsiderOntologyWID = :toConsiderOntologyWID")})
public class OntologyToConsider implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OntologyToConsiderPK ontologyToConsiderPK;
    @ManyToOne
    @JoinColumn(name = "Ontology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology ontology;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ToConsiderOntology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology toConsiderOntology;

    public OntologyToConsider() {
    }

    public OntologyToConsider(OntologyToConsiderPK ontologyToConsiderPK) {
        this.ontologyToConsiderPK = ontologyToConsiderPK;
    }

    public OntologyToConsider(long ontologyWID, long toConsiderOntologyWID) {
        this.ontologyToConsiderPK = new OntologyToConsiderPK(ontologyWID, toConsiderOntologyWID);
    }

    public Ontology getToConsiderOntology() {
        return toConsiderOntology;
    }

    public void setToConsiderOntology(Ontology toConsiderOntology) {
        this.toConsiderOntology = toConsiderOntology;
    }

    public Ontology getOntology() {
        return ontology;
    }

    public void setOntology(Ontology ontology) {
        this.ontology = ontology;
    }

    public OntologyToConsiderPK getOntologyToConsiderPK() {
        return ontologyToConsiderPK;
    }

    public void setOntologyToConsiderPK(OntologyToConsiderPK ontologyToConsiderPK) {
        this.ontologyToConsiderPK = ontologyToConsiderPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ontologyToConsiderPK != null ? ontologyToConsiderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyToConsider)) {
            return false;
        }
        OntologyToConsider other = (OntologyToConsider) object;
        if ((this.ontologyToConsiderPK == null && other.ontologyToConsiderPK != null) || (this.ontologyToConsiderPK != null && !this.ontologyToConsiderPK.equals(other.ontologyToConsiderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OntologyToConsider["
                + "ontology_WID=" + ontologyToConsiderPK.getOntologyWID()
                + " ToConsiderOntology_WID=" + ontologyToConsiderPK.getToConsiderOntologyWID()
                + ']';
    }
}
