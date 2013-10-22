package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the GeneGroupPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Embeddable
public class GeneGroupPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "Relationship")
    private String relationship;
    @Basic(optional = false)
    @Column(name = "OtherGeneInfo_WID")
    private long otherGeneInfoWID;

    public GeneGroupPK() {
    }

    public GeneGroupPK(long geneInfoWID, String relationship, long otherGeneInfoWID) {
        this.geneInfoWID = geneInfoWID;
        this.relationship = relationship;
        this.otherGeneInfoWID = otherGeneInfoWID;
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public long getOtherGeneInfoWID() {
        return otherGeneInfoWID;
    }

    public void setOtherGeneInfoWID(long otherGeneInfoWID) {
        this.otherGeneInfoWID = otherGeneInfoWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) geneInfoWID;
        hash += (relationship != null ? relationship.hashCode() : 0);
        hash += (int) otherGeneInfoWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneGroupPK)) {
            return false;
        }
        GeneGroupPK other = (GeneGroupPK) object;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if ((this.relationship == null && other.relationship != null) || (this.relationship != null && !this.relationship.equals(other.relationship))) {
            return false;
        }
        if (this.otherGeneInfoWID != other.otherGeneInfoWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.gene.entities.GeneGroupPK[ geneInfoWID=" + geneInfoWID + ", relationship=" + relationship + ", otherGeneInfoWID=" + otherGeneInfoWID + " ]";
    }
}
