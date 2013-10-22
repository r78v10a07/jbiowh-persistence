package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the Ontology_has_OntologySynonym entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 28, 2012
 */
@Entity
@Table(name = "Ontology_has_OntologySynonym")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OntologyhasOntologySynonym.findAll", query = "SELECT o FROM OntologyhasOntologySynonym o"),
    @NamedQuery(name = "OntologyhasOntologySynonym.findByOntologyWID", query = "SELECT o FROM OntologyhasOntologySynonym o WHERE o.ontologyhasOntologySynonymPK.ontologyWID = :ontologyWID"),
    @NamedQuery(name = "OntologyhasOntologySynonym.findByOntologySynonymWID", query = "SELECT o FROM OntologyhasOntologySynonym o WHERE o.ontologyhasOntologySynonymPK.ontologySynonymWID = :ontologySynonymWID"),
    @NamedQuery(name = "OntologyhasOntologySynonym.findByScope", query = "SELECT o FROM OntologyhasOntologySynonym o WHERE o.ontologyhasOntologySynonymPK.scope = :scope")})
public class OntologyhasOntologySynonym implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OntologyhasOntologySynonymPK ontologyhasOntologySynonymPK;
    @ManyToOne
    @JoinColumn(name = "Ontology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology ontology;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OntologySynonym_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OntologySynonym ontologySynonym;

    public OntologyhasOntologySynonym() {
    }

    public OntologyhasOntologySynonym(OntologyhasOntologySynonymPK ontologyhasOntologySynonymPK) {
        this.ontologyhasOntologySynonymPK = ontologyhasOntologySynonymPK;
    }

    public OntologyhasOntologySynonym(long ontologyWID, long ontologySynonymWID, String scope) {
        this.ontologyhasOntologySynonymPK = new OntologyhasOntologySynonymPK(ontologyWID, ontologySynonymWID, scope);
    }

    public Ontology getOntology() {
        return ontology;
    }

    public void setOntology(Ontology ontology) {
        this.ontology = ontology;
    }

    public OntologySynonym getOntologySynonym() {
        return ontologySynonym;
    }

    public void setOntologySynonym(OntologySynonym ontologySynonym) {
        this.ontologySynonym = ontologySynonym;
    }

    public OntologyhasOntologySynonymPK getOntologyhasOntologySynonymPK() {
        return ontologyhasOntologySynonymPK;
    }

    public void setOntologyhasOntologySynonymPK(OntologyhasOntologySynonymPK ontologyhasOntologySynonymPK) {
        this.ontologyhasOntologySynonymPK = ontologyhasOntologySynonymPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ontologyhasOntologySynonymPK != null ? ontologyhasOntologySynonymPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyhasOntologySynonym)) {
            return false;
        }
        OntologyhasOntologySynonym other = (OntologyhasOntologySynonym) object;
        if ((this.ontologyhasOntologySynonymPK == null && other.ontologyhasOntologySynonymPK != null) || (this.ontologyhasOntologySynonymPK != null && !this.ontologyhasOntologySynonymPK.equals(other.ontologyhasOntologySynonymPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ontologySynonym.toString() + " scope: " + ontologyhasOntologySynonymPK.getScope();
    }
}
