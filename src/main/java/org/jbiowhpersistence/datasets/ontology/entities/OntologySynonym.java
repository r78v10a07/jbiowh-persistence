package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OntologySynonym entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Entity
@Table(name = "OntologySynonym")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OntologySynonym.findAll", query = "SELECT o FROM OntologySynonym o"),
    @NamedQuery(name = "OntologySynonym.findByWid", query = "SELECT o FROM OntologySynonym o WHERE o.wid = :wid"),
    @NamedQuery(name = "OntologySynonym.findBySynonym", query = "SELECT o FROM OntologySynonym o WHERE o.synonym = :synonym")})
public class OntologySynonym implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Synonym")
    private String synonym;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ontologySynonym")
    @MapKey(name = "ontologyhasOntologySynonymPK")
    private Map<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym> ontologyhasOntologySynonym;

    public OntologySynonym() {
    }

    public OntologySynonym(Long wid) {
        this.wid = wid;
    }

    public OntologySynonym(Long wid, String synonym) {
        this.wid = wid;
        this.synonym = synonym;
    }

    public Map<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym> getOntologyhasOntologySynonym() {
        return ontologyhasOntologySynonym;
    }

    public void setOntologyhasOntologySynonym(Map<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym> ontologyhasOntologySynonym) {
        this.ontologyhasOntologySynonym = ontologyhasOntologySynonym;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OntologySynonym other = (OntologySynonym) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.synonym, other.synonym)) {
            return false;
        }
        if (!Objects.equals(this.ontologyhasOntologySynonym, other.ontologyhasOntologySynonym)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "OntologySynonym["
                + " wid=" + wid
                + " synonym=" + synonym + ']';
    }
}
