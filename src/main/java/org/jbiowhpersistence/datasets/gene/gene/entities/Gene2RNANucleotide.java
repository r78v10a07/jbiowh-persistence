package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the Gene2RNANucleotide entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Nov 14, 2013
 */
@Embeddable
@Table(name = "Gene2RNANucleotide")
@XmlRootElement
public class Gene2RNANucleotide implements Serializable {

    @Basic(optional = false)
    @Column(name = "RNANucleotideGi")
    private Long rNANucleotideGi;
    @Basic(optional = false)
    @Column(name = "RNANucleotideAccession")
    private String rNANucleotideAccession;
    @Basic(optional = false)
    @Column(name = "RNANucleotideAccessionVersion")
    private int rNANucleotideAccessionVersion;

    public Gene2RNANucleotide() {
    }

    public Gene2RNANucleotide(Long rNANucleotideGi) {
        this.rNANucleotideGi = rNANucleotideGi;
    }

    public Gene2RNANucleotide(Long rNANucleotideGi, String rNANucleotideAccession, int rNANucleotideAccessionVersion) {
        this.rNANucleotideGi = rNANucleotideGi;
        this.rNANucleotideAccession = rNANucleotideAccession;
        this.rNANucleotideAccessionVersion = rNANucleotideAccessionVersion;
    }

    public Long getRNANucleotideGi() {
        return rNANucleotideGi;
    }

    public void setRNANucleotideGi(Long rNANucleotideGi) {
        this.rNANucleotideGi = rNANucleotideGi;
    }

    public String getRNANucleotideAccession() {
        return rNANucleotideAccession;
    }

    public void setRNANucleotideAccession(String rNANucleotideAccession) {
        this.rNANucleotideAccession = rNANucleotideAccession;
    }

    public int getRNANucleotideAccessionVersion() {
        return rNANucleotideAccessionVersion;
    }

    public void setRNANucleotideAccessionVersion(int rNANucleotideAccessionVersion) {
        this.rNANucleotideAccessionVersion = rNANucleotideAccessionVersion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.rNANucleotideGi != null ? this.rNANucleotideGi.hashCode() : 0);
        hash = 97 * hash + (this.rNANucleotideAccession != null ? this.rNANucleotideAccession.hashCode() : 0);
        hash = 97 * hash + this.rNANucleotideAccessionVersion;
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
        final Gene2RNANucleotide other = (Gene2RNANucleotide) obj;
        if (this.rNANucleotideGi != other.rNANucleotideGi && (this.rNANucleotideGi == null || !this.rNANucleotideGi.equals(other.rNANucleotideGi))) {
            return false;
        }
        if ((this.rNANucleotideAccession == null) ? (other.rNANucleotideAccession != null) : !this.rNANucleotideAccession.equals(other.rNANucleotideAccession)) {
            return false;
        }
        return this.rNANucleotideAccessionVersion == other.rNANucleotideAccessionVersion;
    }

    @Override
    public String toString() {
        return "Gene2RNANucleotide{" + "rNANucleotideGi=" + rNANucleotideGi + ", rNANucleotideAccession=" + rNANucleotideAccession + ", rNANucleotideAccessionVersion=" + rNANucleotideAccessionVersion + '}';
    }
}
