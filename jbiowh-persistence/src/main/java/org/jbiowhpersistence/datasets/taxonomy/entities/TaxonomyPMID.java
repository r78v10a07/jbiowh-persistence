package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the TaxonomyPMID entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jun 21, 2011
 */
@Embeddable
@Table(name = "TaxonomyPMID")
@XmlRootElement
public class TaxonomyPMID implements Serializable {

    @Basic(optional = false)
    @Column(name = "Taxonomy_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private long taxonomyWID;
    @Basic(optional = false)
    @Column(name = "PMID")
    private long pmid;

    public TaxonomyPMID() {
    }

    public TaxonomyPMID(long taxonomyWID, long pmid) {
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
        int hash = 7;
        hash = 89 * hash + (int) (this.taxonomyWID ^ (this.taxonomyWID >>> 32));
        hash = 89 * hash + (int) (this.pmid ^ (this.pmid >>> 32));
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
        final TaxonomyPMID other = (TaxonomyPMID) obj;
        if (this.taxonomyWID != other.taxonomyWID) {
            return false;
        }
        return this.pmid == other.pmid;
    }

    @Override
    public String toString() {
        return "TaxonomyPMID{" + "taxonomyWID=" + taxonomyWID + ", pmid=" + pmid + '}';
    }
}
