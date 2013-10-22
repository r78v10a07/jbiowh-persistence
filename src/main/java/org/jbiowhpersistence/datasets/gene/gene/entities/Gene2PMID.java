package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is Gene2PMID entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "Gene2PMID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gene2PMID.findAll", query = "SELECT g FROM Gene2PMID g"),
    @NamedQuery(name = "Gene2PMID.findByGeneInfoWID", query = "SELECT g FROM Gene2PMID g WHERE g.gene2PMIDPK.geneInfoWID = :geneInfoWID"),
    @NamedQuery(name = "Gene2PMID.findByPmid", query = "SELECT g FROM Gene2PMID g WHERE g.gene2PMIDPK.pmid = :pmid")})
public class Gene2PMID implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Gene2PMIDPK gene2PMIDPK;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneInfo_WID", insertable = false, unique = false, nullable = false, updatable = false)
    private GeneInfo geneInfo;

    public Gene2PMID() {
    }

    public Gene2PMID(Gene2PMIDPK gene2PMIDPK) {
        this.gene2PMIDPK = gene2PMIDPK;
    }

    public Gene2PMID(long geneInfoWID, long pmid) {
        this.gene2PMIDPK = new Gene2PMIDPK(geneInfoWID, pmid);
    }

    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }

    public Gene2PMIDPK getGene2PMIDPK() {
        return gene2PMIDPK;
    }

    public void setGene2PMIDPK(Gene2PMIDPK gene2PMIDPK) {
        this.gene2PMIDPK = gene2PMIDPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gene2PMIDPK != null ? gene2PMIDPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gene2PMID)) {
            return false;
        }
        Gene2PMID other = (Gene2PMID) object;
        if ((this.gene2PMIDPK == null && other.gene2PMIDPK != null) || (this.gene2PMIDPK != null && !this.gene2PMIDPK.equals(other.gene2PMIDPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gene2PMID{"
                + " GeneWID=" + gene2PMIDPK.getGeneInfoWID()
                + " Pmid=" + gene2PMIDPK.getPmid()
                + '}';
    }
}
