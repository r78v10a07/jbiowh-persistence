package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the GeneGroup entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "GeneGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneGroup.findAll", query = "SELECT g FROM GeneGroup g"),
    @NamedQuery(name = "GeneGroup.findByGeneInfoWID", query = "SELECT g FROM GeneGroup g WHERE g.geneGroupPK.geneInfoWID = :geneInfoWID"),
    @NamedQuery(name = "GeneGroup.findByRelationship", query = "SELECT g FROM GeneGroup g WHERE g.geneGroupPK.relationship = :relationship"),
    @NamedQuery(name = "GeneGroup.findByOtherGeneInfoWID", query = "SELECT g FROM GeneGroup g WHERE g.geneGroupPK.otherGeneInfoWID = :otherGeneInfoWID")})
public class GeneGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GeneGroupPK geneGroupPK;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneInfo_WID", insertable = false, unique = false, nullable = false, updatable = false)
    private GeneInfo geneInfo;

    public GeneGroup() {
    }

    public GeneGroup(GeneGroupPK geneGroupPK) {
        this.geneGroupPK = geneGroupPK;
    }

    public GeneGroup(long geneInfoWID, String relationship, long otherGeneInfoWID) {
        this.geneGroupPK = new GeneGroupPK(geneInfoWID, relationship, otherGeneInfoWID);
    }

    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }

    public GeneGroupPK getGeneGroupPK() {
        return geneGroupPK;
    }

    public void setGeneGroupPK(GeneGroupPK geneGroupPK) {
        this.geneGroupPK = geneGroupPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geneGroupPK != null ? geneGroupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneGroup)) {
            return false;
        }
        GeneGroup other = (GeneGroup) object;
        if ((this.geneGroupPK == null && other.geneGroupPK != null) || (this.geneGroupPK != null && !this.geneGroupPK.equals(other.geneGroupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneGroup{"
                + " GeneWID=" + geneGroupPK.getGeneInfoWID()
                + " relationship=" + geneGroupPK.getRelationship()
                + " OtherGeneWID=" + geneGroupPK.getOtherGeneInfoWID()
                + '}';
    }
}
