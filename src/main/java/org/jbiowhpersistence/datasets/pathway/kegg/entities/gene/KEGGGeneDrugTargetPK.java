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
public class KEGGGeneDrugTargetPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "KEGGGene_WID")
    private long kEGGGeneWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;

    public KEGGGeneDrugTargetPK() {
    }

    public KEGGGeneDrugTargetPK(long kEGGGeneWID, String name) {
        this.kEGGGeneWID = kEGGGeneWID;
        this.name = name;
    }

    public long getKEGGGeneWID() {
        return kEGGGeneWID;
    }

    public void setKEGGGeneWID(long kEGGGeneWID) {
        this.kEGGGeneWID = kEGGGeneWID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) kEGGGeneWID;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGGeneDrugTargetPK)) {
            return false;
        }
        KEGGGeneDrugTargetPK other = (KEGGGeneDrugTargetPK) object;
        if (this.kEGGGeneWID != other.kEGGGeneWID) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGeneDrugTargetPK[ kEGGGeneWID=" + kEGGGeneWID + ", name=" + name + " ]";
    }

}
