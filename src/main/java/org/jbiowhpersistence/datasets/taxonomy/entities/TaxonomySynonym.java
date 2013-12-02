package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the TaxonomySynonym entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 21, 2011
 */
@Embeddable
@Table(name = "TaxonomySynonym")
@XmlRootElement
public class TaxonomySynonym implements Serializable {

    @Basic(optional = false)
    @Column(name = "Taxonomy_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private long taxonomyWID;
    @Basic(optional = false)
    @Column(name = "Synonym")
    private String synonym;
    @Basic(optional = false)
    @Column(name = "TaxonomySynonymNameClass_WID")
    private long taxonomySynonymNameClassWID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TaxonomySynonymNameClass_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private TaxonomySynonymNameClass nameClass;

    public TaxonomySynonym() {
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

    public TaxonomySynonymNameClass getNameClass() {
        return nameClass;
    }

    public void setNameClass(TaxonomySynonymNameClass nameClass) {
        this.nameClass = nameClass;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.taxonomyWID ^ (this.taxonomyWID >>> 32));
        hash = 41 * hash + (this.synonym != null ? this.synonym.hashCode() : 0);
        hash = 41 * hash + (int) (this.taxonomySynonymNameClassWID ^ (this.taxonomySynonymNameClassWID >>> 32));
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
        final TaxonomySynonym other = (TaxonomySynonym) obj;
        if (this.taxonomyWID != other.taxonomyWID) {
            return false;
        }
        if ((this.synonym == null) ? (other.synonym != null) : !this.synonym.equals(other.synonym)) {
            return false;
        }
        return this.taxonomySynonymNameClassWID == other.taxonomySynonymNameClassWID;
    }

    @Override
    public String toString() {
        return "TaxonomySynonym{" + "taxonomyWID=" + taxonomyWID + ", synonym=" + synonym + ", taxonomySynonymNameClassWID=" + taxonomySynonymNameClassWID + ", nameClass=" + nameClass + '}';
    }
}
