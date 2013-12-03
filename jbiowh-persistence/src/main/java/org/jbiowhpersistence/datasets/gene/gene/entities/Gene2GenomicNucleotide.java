package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the Gene2GenomicNucleotide entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Nov 14, 2013
 */
@Embeddable
@Table(name = "Gene2GenomicNucleotide")
@XmlRootElement
public class Gene2GenomicNucleotide implements Serializable {

    @Basic(optional = false)
    @Column(name = "GenomicNucleotideGi")
    private Long genomicNucleotideGi;
    @Basic(optional = false)
    @Column(name = "GenomicNucleotideAccession")
    private String genomicNucleotideAccession;
    @Basic(optional = false)
    @Column(name = "GenomicNucleotideAccessionVersion")
    private int genomicNucleotideAccessionVersion;
    @Column(name = "StartPositionOnTheGenomicAccession")
    private String startPositionOnTheGenomicAccession;
    @Column(name = "EndPositionOnTheGenomicAccession")
    private String endPositionOnTheGenomicAccession;
    @Column(name = "Orientation")
    private String orientation;
    @Column(name = "Assembly")
    private String assembly;

    public Gene2GenomicNucleotide() {
    }

    public Gene2GenomicNucleotide(Long genomicNucleotideGi, String genomicNucleotideAccession, int genomicNucleotideAccessionVersion, String startPositionOnTheGenomicAccession, String endPositionOnTheGenomicAccession, String orientation, String assembly) {
        this.genomicNucleotideGi = genomicNucleotideGi;
        this.genomicNucleotideAccession = genomicNucleotideAccession;
        this.genomicNucleotideAccessionVersion = genomicNucleotideAccessionVersion;
        this.startPositionOnTheGenomicAccession = startPositionOnTheGenomicAccession;
        this.endPositionOnTheGenomicAccession = endPositionOnTheGenomicAccession;
        this.orientation = orientation;
        this.assembly = assembly;
    }

    public Long getGenomicNucleotideGi() {
        return genomicNucleotideGi;
    }

    public void setGenomicNucleotideGi(Long genomicNucleotideGi) {
        this.genomicNucleotideGi = genomicNucleotideGi;
    }

    public String getGenomicNucleotideAccession() {
        return genomicNucleotideAccession;
    }

    public void setGenomicNucleotideAccession(String genomicNucleotideAccession) {
        this.genomicNucleotideAccession = genomicNucleotideAccession;
    }

    public int getGenomicNucleotideAccessionVersion() {
        return genomicNucleotideAccessionVersion;
    }

    public void setGenomicNucleotideAccessionVersion(int genomicNucleotideAccessionVersion) {
        this.genomicNucleotideAccessionVersion = genomicNucleotideAccessionVersion;
    }

    public String getStartPositionOnTheGenomicAccession() {
        return startPositionOnTheGenomicAccession;
    }

    public void setStartPositionOnTheGenomicAccession(String startPositionOnTheGenomicAccession) {
        this.startPositionOnTheGenomicAccession = startPositionOnTheGenomicAccession;
    }

    public String getEndPositionOnTheGenomicAccession() {
        return endPositionOnTheGenomicAccession;
    }

    public void setEndPositionOnTheGenomicAccession(String endPositionOnTheGenomicAccession) {
        this.endPositionOnTheGenomicAccession = endPositionOnTheGenomicAccession;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.genomicNucleotideGi != null ? this.genomicNucleotideGi.hashCode() : 0);
        hash = 89 * hash + (this.genomicNucleotideAccession != null ? this.genomicNucleotideAccession.hashCode() : 0);
        hash = 89 * hash + (this.startPositionOnTheGenomicAccession != null ? this.startPositionOnTheGenomicAccession.hashCode() : 0);
        hash = 89 * hash + (this.endPositionOnTheGenomicAccession != null ? this.endPositionOnTheGenomicAccession.hashCode() : 0);
        hash = 89 * hash + (this.orientation != null ? this.orientation.hashCode() : 0);
        hash = 89 * hash + (this.assembly != null ? this.assembly.hashCode() : 0);
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
        final Gene2GenomicNucleotide other = (Gene2GenomicNucleotide) obj;
        if (this.genomicNucleotideGi != other.genomicNucleotideGi && (this.genomicNucleotideGi == null || !this.genomicNucleotideGi.equals(other.genomicNucleotideGi))) {
            return false;
        }
        if ((this.genomicNucleotideAccession == null) ? (other.genomicNucleotideAccession != null) : !this.genomicNucleotideAccession.equals(other.genomicNucleotideAccession)) {
            return false;
        }
        if (this.genomicNucleotideAccessionVersion != other.genomicNucleotideAccessionVersion) {
            return false;
        }
        if ((this.startPositionOnTheGenomicAccession == null) ? (other.startPositionOnTheGenomicAccession != null) : !this.startPositionOnTheGenomicAccession.equals(other.startPositionOnTheGenomicAccession)) {
            return false;
        }
        if ((this.endPositionOnTheGenomicAccession == null) ? (other.endPositionOnTheGenomicAccession != null) : !this.endPositionOnTheGenomicAccession.equals(other.endPositionOnTheGenomicAccession)) {
            return false;
        }
        if ((this.orientation == null) ? (other.orientation != null) : !this.orientation.equals(other.orientation)) {
            return false;
        }
        return !((this.assembly == null) ? (other.assembly != null) : !this.assembly.equals(other.assembly));
    }

    @Override
    public String toString() {
        return "Gene2GenomicNucleotide{" + "genomicNucleotideGi=" + genomicNucleotideGi + ", genomicNucleotideAccession=" + genomicNucleotideAccession + ", genomicNucleotideAccessionVersion=" + genomicNucleotideAccessionVersion + ", startPositionOnTheGenomicAccession=" + startPositionOnTheGenomicAccession + ", endPositionOnTheGenomicAccession=" + endPositionOnTheGenomicAccession + ", orientation=" + orientation + ", assembly=" + assembly + '}';
    }
}
