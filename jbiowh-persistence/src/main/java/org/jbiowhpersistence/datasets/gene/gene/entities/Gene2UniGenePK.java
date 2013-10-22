package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the Gene2UniGenePK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Embeddable
public class Gene2UniGenePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "UniGene")
    private String uniGene;

    public Gene2UniGenePK() {
    }

    public Gene2UniGenePK(long geneInfoWID, String uniGene) {
        this.geneInfoWID = geneInfoWID;
        this.uniGene = uniGene;
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    public String getUniGene() {
        return uniGene;
    }

    public void setUniGene(String uniGene) {
        this.uniGene = uniGene;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) geneInfoWID;
        hash += (uniGene != null ? uniGene.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gene2UniGenePK)) {
            return false;
        }
        Gene2UniGenePK other = (Gene2UniGenePK) object;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if ((this.uniGene == null && other.uniGene != null) || (this.uniGene != null && !this.uniGene.equals(other.uniGene))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.gene.entities.Gene2UniGenePK[ geneInfoWID=" + geneInfoWID + ", uniGene=" + uniGene + " ]";
    }
}
