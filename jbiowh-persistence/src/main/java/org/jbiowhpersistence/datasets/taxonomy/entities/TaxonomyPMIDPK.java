package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the TaxonomyPMIDPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 21, 2011
 */
@Embeddable
public class TaxonomyPMIDPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Taxonomy_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private long taxonomyWID;
    @Basic(optional = false)
    @Column(name = "PMID")
    private long pmid;

    public TaxonomyPMIDPK() {
    }

    public TaxonomyPMIDPK(long taxonomyWID, long pmid) {
        this.taxonomyWID = taxonomyWID;
        this.pmid = pmid;
    }

    public long getTaxonomyWID() {
        return taxonomyWID;
    }

    public void setTaxonomyWID(long taxonomyWID) {
        this.taxonomyWID = taxonomyWID;
    }

    public long getPmid() {
        return pmid;
    }

    public void setPmid(long pmid) {
        this.pmid = pmid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) taxonomyWID;
        hash += (int) pmid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TaxonomyPMIDPK)) {
            return false;
        }
        TaxonomyPMIDPK other = (TaxonomyPMIDPK) object;
        if (this.taxonomyWID != other.taxonomyWID) {
            return false;
        }
        if (this.pmid != other.pmid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TaxonomyPMIDPK[ taxonomyWID=" + taxonomyWID + ", pmid=" + pmid + " ]";
    }
}
