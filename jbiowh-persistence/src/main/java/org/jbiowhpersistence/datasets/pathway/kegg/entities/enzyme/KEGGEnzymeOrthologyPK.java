package org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is 
 * 
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since May 14, 2012
 */
@Embeddable
public class KEGGEnzymeOrthologyPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "KEGGEnzyme_WID")
    private long kEGGEnzymeWID;
    @Basic(optional = false)
    @Column(name = "Entry")
    private String entry;

    public KEGGEnzymeOrthologyPK() {
    }

    public KEGGEnzymeOrthologyPK(long kEGGEnzymeWID, String entry) {
        this.kEGGEnzymeWID = kEGGEnzymeWID;
        this.entry = entry;
    }

    public long getKEGGEnzymeWID() {
        return kEGGEnzymeWID;
    }

    public void setKEGGEnzymeWID(long kEGGEnzymeWID) {
        this.kEGGEnzymeWID = kEGGEnzymeWID;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) kEGGEnzymeWID;
        hash += (entry != null ? entry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGEnzymeOrthologyPK)) {
            return false;
        }
        KEGGEnzymeOrthologyPK other = (KEGGEnzymeOrthologyPK) object;
        if (this.kEGGEnzymeWID != other.kEGGEnzymeWID) {
            return false;
        }
        if ((this.entry == null && other.entry != null) || (this.entry != null && !this.entry.equals(other.entry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGEnzymeOrthologyPK[ kEGGEnzymeWID=" + kEGGEnzymeWID + ", entry=" + entry + " ]";
    }

}
