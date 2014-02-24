package org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the ProtClustProteins entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 13, 2014
 */
@Embeddable
@Table(name = "ProtClustProteins")
@XmlRootElement
public class ProtClustProteins implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneGi")
    private long geneGi;
    @Column(name = "LocusName")
    private String locusName;
    @Column(name = "ProteinGi")
    private BigInteger proteinGi;
    @Column(name = "ProteinName")
    private String proteinName;

    public ProtClustProteins() {
    }

    public ProtClustProteins(long geneGi, String locusName, BigInteger proteinGi, String proteinName) {
        this.geneGi = geneGi;
        this.locusName = locusName;
        this.proteinGi = proteinGi;
        this.proteinName = proteinName;
    }

    public long getGeneGi() {
        return geneGi;
    }

    public void setGeneGi(long geneGi) {
        this.geneGi = geneGi;
    }

    public String getLocusName() {
        return locusName;
    }

    public void setLocusName(String locusName) {
        this.locusName = locusName;
    }

    public BigInteger getProteinGi() {
        return proteinGi;
    }

    public void setProteinGi(BigInteger proteinGi) {
        this.proteinGi = proteinGi;
    }

    public String getProteinName() {
        return proteinName;
    }

    public void setProteinName(String proteinName) {
        this.proteinName = proteinName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.geneGi ^ (this.geneGi >>> 32));
        hash = 67 * hash + (this.locusName != null ? this.locusName.hashCode() : 0);
        hash = 67 * hash + (this.proteinGi != null ? this.proteinGi.hashCode() : 0);
        hash = 67 * hash + (this.proteinName != null ? this.proteinName.hashCode() : 0);
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
        final ProtClustProteins other = (ProtClustProteins) obj;
        if (this.geneGi != other.geneGi) {
            return false;
        }
        if ((this.locusName == null) ? (other.locusName != null) : !this.locusName.equals(other.locusName)) {
            return false;
        }
        if (this.proteinGi != other.proteinGi && (this.proteinGi == null || !this.proteinGi.equals(other.proteinGi))) {
            return false;
        }
        return !((this.proteinName == null) ? (other.proteinName != null) : !this.proteinName.equals(other.proteinName));
    }

    @Override
    public String toString() {
        return "ProtClustProteins{" + "geneGi=" + geneGi + ", locusName=" + locusName + ", proteinGi=" + proteinGi + ", proteinName=" + proteinName + '}';
    }
}
