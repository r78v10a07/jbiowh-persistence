package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is Gene2STS entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "Gene2STS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gene2STS.findAll", query = "SELECT g FROM Gene2STS g"),
    @NamedQuery(name = "Gene2STS.findByGeneInfoWID", query = "SELECT g FROM Gene2STS g WHERE g.gene2STSPK.geneInfoWID = :geneInfoWID"),
    @NamedQuery(name = "Gene2STS.findByUniSTSID", query = "SELECT g FROM Gene2STS g WHERE g.gene2STSPK.uniSTSID = :uniSTSID")})
public class Gene2STS implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Gene2STSPK gene2STSPK;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneInfo_WID", insertable = false, unique = false, nullable = false, updatable = false)
    private GeneInfo geneInfo;

    public Gene2STS() {
    }

    public Gene2STS(Gene2STSPK gene2STSPK) {
        this.gene2STSPK = gene2STSPK;
    }

    public Gene2STS(long geneInfoWID, long uniSTSID) {
        this.gene2STSPK = new Gene2STSPK(geneInfoWID, uniSTSID);
    }

    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }

    public Gene2STSPK getGene2STSPK() {
        return gene2STSPK;
    }

    public void setGene2STSPK(Gene2STSPK gene2STSPK) {
        this.gene2STSPK = gene2STSPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gene2STSPK != null ? gene2STSPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gene2STS)) {
            return false;
        }
        Gene2STS other = (Gene2STS) object;
        if ((this.gene2STSPK == null && other.gene2STSPK != null) || (this.gene2STSPK != null && !this.gene2STSPK.equals(other.gene2STSPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gene2STS{"
                + " GeneWID=" + gene2STSPK.getGeneInfoWID()
                + " UniSTSID=" + gene2STSPK.getUniSTSID()
                + '}';
    }
}
