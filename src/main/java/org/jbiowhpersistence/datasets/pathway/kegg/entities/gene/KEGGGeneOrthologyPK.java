package org.jbiowhpersistence.datasets.pathway.kegg.entities.gene;

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
public class KEGGGeneOrthologyPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "KEGGGene_WID")
    private long kEGGGeneWID;
    @Basic(optional = false)
    @Column(name = "Entry")
    private String entry;

    public KEGGGeneOrthologyPK() {
    }

    public KEGGGeneOrthologyPK(long kEGGGeneWID, String entry) {
        this.kEGGGeneWID = kEGGGeneWID;
        this.entry = entry;
    }

    public long getKEGGGeneWID() {
        return kEGGGeneWID;
    }

    public void setKEGGGeneWID(long kEGGGeneWID) {
        this.kEGGGeneWID = kEGGGeneWID;
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
        hash += (int) kEGGGeneWID;
        hash += (entry != null ? entry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGGeneOrthologyPK)) {
            return false;
        }
        KEGGGeneOrthologyPK other = (KEGGGeneOrthologyPK) object;
        if (this.kEGGGeneWID != other.kEGGGeneWID) {
            return false;
        }
        if ((this.entry == null && other.entry != null) || (this.entry != null && !this.entry.equals(other.entry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGeneOrthologyPK[ kEGGGeneWID=" + kEGGGeneWID + ", entry=" + entry + " ]";
    }

}
