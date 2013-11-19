package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the GeneGroup entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jul 27, 2011
 */
@Embeddable
@Table(name = "GeneGroup")
@XmlRootElement
public class GeneGroup implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "Relationship")
    private String relationship;
    @Basic(optional = false)
    @Column(name = "OtherGeneInfo_WID")
    private long otherGeneInfoWID;

    public GeneGroup() {
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
        int hash = 7;
        hash = 79 * hash + (int) (this.geneInfoWID ^ (this.geneInfoWID >>> 32));
        hash = 79 * hash + (this.relationship != null ? this.relationship.hashCode() : 0);
        hash = 79 * hash + (int) (this.otherGeneInfoWID ^ (this.otherGeneInfoWID >>> 32));
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
        final GeneGroup other = (GeneGroup) obj;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if ((this.relationship == null) ? (other.relationship != null) : !this.relationship.equals(other.relationship)) {
            return false;
        }
        return this.otherGeneInfoWID == other.otherGeneInfoWID;
    }

    @Override
    public String toString() {
        return "GeneGroup{" + "geneInfoWID=" + geneInfoWID + ", relationship=" + relationship + ", otherGeneInfoWID=" + otherGeneInfoWID + '}';
    }
}
