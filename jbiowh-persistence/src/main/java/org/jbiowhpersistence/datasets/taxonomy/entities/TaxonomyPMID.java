package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the TaxonomyPMID entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 21, 2011
 */
@Entity
@Table(name = "TaxonomyPMID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxonomyPMID.findAll", query = "SELECT t FROM TaxonomyPMID t"),
    @NamedQuery(name = "TaxonomyPMID.findByTaxonomyWID", query = "SELECT t FROM TaxonomyPMID t WHERE t.taxonomyPMIDPK.taxonomyWID = :taxonomyWID"),
    @NamedQuery(name = "TaxonomyPMID.findByPmid", query = "SELECT t FROM TaxonomyPMID t WHERE t.taxonomyPMIDPK.pmid = :pmid")})
public class TaxonomyPMID implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TaxonomyPMIDPK taxonomyPMIDPK;
    /*
     * TaxonomyPMID relationships
     */
    @ManyToOne
    private Taxonomy taxonomy;

    public TaxonomyPMID() {
    }

    public TaxonomyPMID(TaxonomyPMIDPK taxonomyPMIDPK) {
        this.taxonomyPMIDPK = taxonomyPMIDPK;
    }

    public TaxonomyPMID(long taxonomyWID, long pmid) {
        this.taxonomyPMIDPK = new TaxonomyPMIDPK(taxonomyWID, pmid);
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }
    /*
     * public void setTaxonomy(Taxonomy taxonomy) { this.taxonomy = taxonomy; }
     */

    public TaxonomyPMIDPK getTaxonomyPMIDPK() {
        return taxonomyPMIDPK;
    }

    public void setTaxonomyPMIDPK(TaxonomyPMIDPK taxonomyPMIDPK) {
        this.taxonomyPMIDPK = taxonomyPMIDPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxonomyPMIDPK != null ? taxonomyPMIDPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TaxonomyPMID)) {
            return false;
        }
        TaxonomyPMID other = (TaxonomyPMID) object;
        if ((this.taxonomyPMIDPK == null && other.taxonomyPMIDPK != null) || (this.taxonomyPMIDPK != null && !this.taxonomyPMIDPK.equals(other.taxonomyPMIDPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return taxonomyPMIDPK.toString();
    }
}
