package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Gene2UniGene entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "Gene2UniGene")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gene2UniGene.findAll", query = "SELECT g FROM Gene2UniGene g"),
    @NamedQuery(name = "Gene2UniGene.findByGeneInfoWID", query = "SELECT g FROM Gene2UniGene g WHERE g.gene2UniGenePK.geneInfoWID = :geneInfoWID"),
    @NamedQuery(name = "Gene2UniGene.findByUniGene", query = "SELECT g FROM Gene2UniGene g WHERE g.gene2UniGenePK.uniGene = :uniGene")})
public class Gene2UniGene implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Gene2UniGenePK gene2UniGenePK;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneInfo_WID", insertable = false, unique = false, nullable = false, updatable = false)
    private GeneInfo geneInfo;

    public Gene2UniGene() {
    }

    public Gene2UniGene(Gene2UniGenePK gene2UniGenePK) {
        this.gene2UniGenePK = gene2UniGenePK;
    }

    public Gene2UniGene(long geneInfoWID, String uniGene) {
        this.gene2UniGenePK = new Gene2UniGenePK(geneInfoWID, uniGene);
    }

    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }

    public Gene2UniGenePK getGene2UniGenePK() {
        return gene2UniGenePK;
    }

    public void setGene2UniGenePK(Gene2UniGenePK gene2UniGenePK) {
        this.gene2UniGenePK = gene2UniGenePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gene2UniGenePK != null ? gene2UniGenePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gene2UniGene)) {
            return false;
        }
        Gene2UniGene other = (Gene2UniGene) object;
        if ((this.gene2UniGenePK == null && other.gene2UniGenePK != null) || (this.gene2UniGenePK != null && !this.gene2UniGenePK.equals(other.gene2UniGenePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gene2UniGene{"
                + " GeneWID=" + gene2UniGenePK.getGeneInfoWID()
                + " UniGene=" + gene2UniGenePK.getUniGene()
                + '}';
    }
}
