package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is 
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-26 16:29:19 +0100 (Mon, 26 Nov 2012) $
 * $LastChangedRevision: 323 $
 * @since Aug 28, 2012
 */
@Embeddable
public class OntologyhasOntologySynonymPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Ontology_WID")
    private long ontologyWID;
    @Basic(optional = false)
    @Column(name = "OntologySynonym_WID")
    private long ontologySynonymWID;
    @Basic(optional = false)
    @Column(name = "Scope")
    private String scope;

    public OntologyhasOntologySynonymPK() {
    }

    public OntologyhasOntologySynonymPK(long ontologyWID, long ontologySynonymWID, String scope) {
        this.ontologyWID = ontologyWID;
        this.ontologySynonymWID = ontologySynonymWID;
        this.scope = scope;
    }

    public long getOntologyWID() {
        return ontologyWID;
    }

    public void setOntologyWID(long ontologyWID) {
        this.ontologyWID = ontologyWID;
    }

    public long getOntologySynonymWID() {
        return ontologySynonymWID;
    }

    public void setOntologySynonymWID(long ontologySynonymWID) {
        this.ontologySynonymWID = ontologySynonymWID;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ontologyWID;
        hash += (int) ontologySynonymWID;
        hash += (scope != null ? scope.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OntologyhasOntologySynonymPK)) {
            return false;
        }
        OntologyhasOntologySynonymPK other = (OntologyhasOntologySynonymPK) object;
        if (this.ontologyWID != other.ontologyWID) {
            return false;
        }
        if (this.ontologySynonymWID != other.ontologySynonymWID) {
            return false;
        }
        if ((this.scope == null && other.scope != null) || (this.scope != null && !this.scope.equals(other.scope))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OntologyhasOntologySynonymPK[ ontologyWID=" + ontologyWID + ", ontologySynonymWID=" + ontologySynonymWID + ", scope=" + scope + " ]";
    }

}
