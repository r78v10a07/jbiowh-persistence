package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OntologyRelation entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Entity
@Table(name = "OntologyRelation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OntologyRelation.findAll", query = "SELECT o FROM OntologyRelation o"),
    @NamedQuery(name = "OntologyRelation.findByOntologyWID", query = "SELECT o FROM OntologyRelation o WHERE o.ontologyRelationPK.ontologyWID = :ontologyWID"),
    @NamedQuery(name = "OntologyRelation.findByOtherOntologyWID", query = "SELECT o FROM OntologyRelation o WHERE o.ontologyRelationPK.otherOntologyWID = :otherOntologyWID"),
    @NamedQuery(name = "OntologyRelation.findByType", query = "SELECT o FROM OntologyRelation o WHERE o.type = :type")})
public class OntologyRelation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OntologyRelationPK ontologyRelationPK;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "Ontology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology ontology;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OtherOntology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology otherOntology;

    public OntologyRelation() {
    }

    public OntologyRelation(OntologyRelationPK ontologyRelationPK) {
        this.ontologyRelationPK = ontologyRelationPK;
    }

    public OntologyRelation(OntologyRelationPK ontologyRelationPK, String type) {
        this.ontologyRelationPK = ontologyRelationPK;
        this.type = type;
    }

    public OntologyRelation(long ontologyWID, long otherOntologyWID) {
        this.ontologyRelationPK = new OntologyRelationPK(ontologyWID, otherOntologyWID);
    }

    public Ontology getOtherOntology() {
        return otherOntology;
    }

    public void setOtherOntology(Ontology otherOntology) {
        this.otherOntology = otherOntology;
    }

    public Ontology getOntology() {
        return ontology;
    }

    public void setOntology(Ontology ontology) {
        this.ontology = ontology;
    }

    public OntologyRelationPK getOntologyRelationPK() {
        return ontologyRelationPK;
    }

    public void setOntologyRelationPK(OntologyRelationPK ontologyRelationPK) {
        this.ontologyRelationPK = ontologyRelationPK;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OntologyRelation other = (OntologyRelation) obj;
        if (!Objects.equals(this.ontologyRelationPK, other.ontologyRelationPK)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.ontologyRelationPK);
        return hash;
    }

    @Override
    public String toString() {
        return "OntologyRelation["
                + " Ontology_WID=" + ontologyRelationPK.getOntologyWID()
                + " OtherOntology_WID=" + ontologyRelationPK.getOtherOntologyWID()
                + " type=" + type + ']';
    }
}
