package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Gene2UniGene entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jul 27, 2011
 */
@Embeddable
@Table(name = "Gene2UniGene")
@XmlRootElement
public class Gene2UniGene implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "UniGene")
    private String uniGene;

    public Gene2UniGene() {
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
        int hash = 5;
        hash = 37 * hash + (int) (this.geneInfoWID ^ (this.geneInfoWID >>> 32));
        hash = 37 * hash + (this.uniGene != null ? this.uniGene.hashCode() : 0);
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
        final Gene2UniGene other = (Gene2UniGene) obj;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        return !((this.uniGene == null) ? (other.uniGene != null) : !this.uniGene.equals(other.uniGene));
    }

    @Override
    public String toString() {
        return "Gene2UniGene{" + "geneInfoWID=" + geneInfoWID + ", uniGene=" + uniGene + '}';
    }
}
