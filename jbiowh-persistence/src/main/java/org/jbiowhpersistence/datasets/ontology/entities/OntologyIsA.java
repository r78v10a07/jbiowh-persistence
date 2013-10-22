package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is OntologyIsA entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Entity
@Table(name = "OntologyIsA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OntologyIsA.findAll", query = "SELECT o FROM OntologyIsA o"),
    @NamedQuery(name = "OntologyIsA.findByOntologyWID", query = "SELECT o FROM OntologyIsA o WHERE o.ontologyIsAPK.ontologyWID = :ontologyWID"),
    @NamedQuery(name = "OntologyIsA.findByIsAOntologyWID", query = "SELECT o FROM OntologyIsA o WHERE o.ontologyIsAPK.isAOntologyWID = :isAOntologyWID")})
public class OntologyIsA implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OntologyIsAPK ontologyIsAPK;
    @ManyToOne
    @JoinColumn(name = "Ontology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology ontology;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IsAOntology_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Ontology isAOntology;

    public OntologyIsA() {
    }

    public OntologyIsA(OntologyIsAPK ontologyIsAPK) {
        this.ontologyIsAPK = ontologyIsAPK;
    }

    public OntologyIsA(long ontologyWID, long isAOntologyWID) {
        this.ontologyIsAPK = new OntologyIsAPK(ontologyWID, isAOntologyWID);
    }

    public Ontology getIsAOntology() {
        return isAOntology;
    }

    public void setIsAOntology(Ontology isAOntology) {
        this.isAOntology = isAOntology;
    }

    public Ontology getOntology() {
        return ontology;
    }

    public void setOntology(Ontology ontology) {
        this.ontology = ontology;
    }

    public OntologyIsAPK getOntologyIsAPK() {
        return ontologyIsAPK;
    }

    public void setOntologyIsAPK(OntologyIsAPK ontologyIsAPK) {
        this.ontologyIsAPK = ontologyIsAPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ontologyIsAPK != null ? ontologyIsAPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyIsA)) {
            return false;
        }
        OntologyIsA other = (OntologyIsA) object;
        if ((this.ontologyIsAPK == null && other.ontologyIsAPK != null) || (this.ontologyIsAPK != null && !this.ontologyIsAPK.equals(other.ontologyIsAPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OntologyIsA["
                + " ontology_WID=" + ontologyIsAPK.getOntologyWID()
                + " IsAOntology_WID=" + ontologyIsAPK.getIsAOntologyWID()
                + ']';
    }
}
