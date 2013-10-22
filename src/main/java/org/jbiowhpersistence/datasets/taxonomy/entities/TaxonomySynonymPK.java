package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the TaxonomySynonymPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 21, 2011
 */
@Embeddable
public class TaxonomySynonymPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Taxonomy_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private long taxonomyWID;
    @Basic(optional = false)
    @Column(name = "Synonym")
    private String synonym;
    @Basic(optional = false)
    @Column(name = "TaxonomySynonymNameClass_WID")
    private long taxonomySynonymNameClassWID;

    public TaxonomySynonymPK() {
    }

    public TaxonomySynonymPK(long taxonomyWID, String synonym, long taxonomySynonymNameClassWID) {
        this.taxonomyWID = taxonomyWID;
        this.synonym = synonym;
        this.taxonomySynonymNameClassWID = taxonomySynonymNameClassWID;
    }

    public long getTaxonomyWID() {
        return taxonomyWID;
    }

    public void setTaxonomyWID(long taxonomyWID) {
        this.taxonomyWID = taxonomyWID;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public long getTaxonomySynonymNameClassWID() {
        return taxonomySynonymNameClassWID;
    }

    public void setTaxonomySynonymNameClassWID(long taxonomySynonymNameClassWID) {
        this.taxonomySynonymNameClassWID = taxonomySynonymNameClassWID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaxonomySynonymPK other = (TaxonomySynonymPK) obj;
        if (this.taxonomyWID != other.taxonomyWID) {
            return false;
        }
        if (!Objects.equals(this.synonym, other.synonym)) {
            return false;
        }
        if (this.taxonomySynonymNameClassWID != other.taxonomySynonymNameClassWID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) taxonomyWID;
        hash += (synonym != null ? synonym.hashCode() : 0);
        hash += (int) taxonomySynonymNameClassWID;
        return hash;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.taxonomy.entities.TaxonomySynonymPK[ taxonomyWID=" + taxonomyWID + ", synonym=" + synonym + ", taxonomySynonymNameClassWID=" + taxonomySynonymNameClassWID + " ]";
    }
}
