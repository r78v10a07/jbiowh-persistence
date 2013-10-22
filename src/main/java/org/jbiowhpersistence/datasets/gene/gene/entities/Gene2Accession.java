package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Gene2Accession entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 591 $
 * @since Jul 23, 2012
 */
@Entity
@Table(name = "Gene2Accession")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gene2Accession.findAll", query = "SELECT g FROM Gene2Accession g"),
    @NamedQuery(name = "Gene2Accession.findByWid", query = "SELECT g FROM Gene2Accession g WHERE g.gene2AccessionPK.wid = :wid"),
    @NamedQuery(name = "Gene2Accession.findByGeneInfoWID", query = "SELECT g FROM Gene2Accession g WHERE g.gene2AccessionPK.geneInfoWID = :geneInfoWID"),
    @NamedQuery(name = "Gene2Accession.findByStatus", query = "SELECT g FROM Gene2Accession g WHERE g.status = :status"),
    @NamedQuery(name = "Gene2Accession.findByRNANucleotideAccession", query = "SELECT g FROM Gene2Accession g WHERE g.rNANucleotideAccession = :rNANucleotideAccession"),
    @NamedQuery(name = "Gene2Accession.findByRNANucleotideAccessionVersion", query = "SELECT g FROM Gene2Accession g WHERE g.rNANucleotideAccessionVersion = :rNANucleotideAccessionVersion"),
    @NamedQuery(name = "Gene2Accession.findByRNANucleotideGi", query = "SELECT g FROM Gene2Accession g WHERE g.rNANucleotideGi = :rNANucleotideGi"),
    @NamedQuery(name = "Gene2Accession.findByProteinAccession", query = "SELECT g FROM Gene2Accession g WHERE g.proteinAccession = :proteinAccession"),
    @NamedQuery(name = "Gene2Accession.findByProteinAccessionVersion", query = "SELECT g FROM Gene2Accession g WHERE g.proteinAccessionVersion = :proteinAccessionVersion"),
    @NamedQuery(name = "Gene2Accession.findByProteinGi", query = "SELECT g FROM Gene2Accession g WHERE g.proteinGi = :proteinGi"),
    @NamedQuery(name = "Gene2Accession.findByGenomicNucleotideAccession", query = "SELECT g FROM Gene2Accession g WHERE g.genomicNucleotideAccession = :genomicNucleotideAccession"),
    @NamedQuery(name = "Gene2Accession.findByGenomicNucleotideAccessionVersion", query = "SELECT g FROM Gene2Accession g WHERE g.genomicNucleotideAccessionVersion = :genomicNucleotideAccessionVersion"),
    @NamedQuery(name = "Gene2Accession.findByGenomicNucleotideGi", query = "SELECT g FROM Gene2Accession g WHERE g.genomicNucleotideGi = :genomicNucleotideGi"),
    @NamedQuery(name = "Gene2Accession.findByStartPositionOnTheGenomicAccession", query = "SELECT g FROM Gene2Accession g WHERE g.startPositionOnTheGenomicAccession = :startPositionOnTheGenomicAccession"),
    @NamedQuery(name = "Gene2Accession.findByEndPositionOnTheGenomicAccession", query = "SELECT g FROM Gene2Accession g WHERE g.endPositionOnTheGenomicAccession = :endPositionOnTheGenomicAccession"),
    @NamedQuery(name = "Gene2Accession.findByOrientation", query = "SELECT g FROM Gene2Accession g WHERE g.orientation = :orientation"),
    @NamedQuery(name = "Gene2Accession.findByAssembly", query = "SELECT g FROM Gene2Accession g WHERE g.assembly = :assembly")})
public class Gene2Accession implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Gene2AccessionPK gene2AccessionPK;
    @Column(name = "Status")
    private String status;
    @Column(name = "RNANucleotideAccession")
    private String rNANucleotideAccession;
    @Column(name = "RNANucleotideAccessionVersion")
    private Integer rNANucleotideAccessionVersion;
    @Column(name = "RNANucleotideGi")
    private BigInteger rNANucleotideGi;
    @Column(name = "ProteinAccession")
    private String proteinAccession;
    @Column(name = "ProteinAccessionVersion")
    private Integer proteinAccessionVersion;
    @Basic(optional = false)
    @Column(name = "ProteinGi")
    private long proteinGi;
    @Column(name = "GenomicNucleotideAccession")
    private String genomicNucleotideAccession;
    @Column(name = "GenomicNucleotideAccessionVersion")
    private Integer genomicNucleotideAccessionVersion;
    @Column(name = "GenomicNucleotideGi")
    private BigInteger genomicNucleotideGi;
    @Column(name = "StartPositionOnTheGenomicAccession")
    private String startPositionOnTheGenomicAccession;
    @Column(name = "EndPositionOnTheGenomicAccession")
    private String endPositionOnTheGenomicAccession;
    @Column(name = "Orientation")
    private String orientation;
    @Column(name = "Assembly")
    private String assembly;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneInfo_WID", insertable = false, unique = false, nullable = false, updatable = false)
    private GeneInfo geneInfo;

    public Gene2Accession() {
    }

    public Gene2Accession(Gene2AccessionPK gene2AccessionPK) {
        this.gene2AccessionPK = gene2AccessionPK;
    }

    public Gene2Accession(long wid, long geneInfoWID) {
        this.gene2AccessionPK = new Gene2AccessionPK(wid, geneInfoWID);
    }

    public Gene2AccessionPK getGene2AccessionPK() {
        return gene2AccessionPK;
    }

    public void setGene2AccessionPK(Gene2AccessionPK gene2AccessionPK) {
        this.gene2AccessionPK = gene2AccessionPK;
    }

    public long getProteinGi() {
        return proteinGi;
    }

    public void setProteinGi(long proteinGi) {
        this.proteinGi = proteinGi;
    }

    public String getStatus() {
        return status;
    }

    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRNANucleotideAccession() {
        return rNANucleotideAccession;
    }

    public void setRNANucleotideAccession(String rNANucleotideAccession) {
        this.rNANucleotideAccession = rNANucleotideAccession;
    }

    public Integer getRNANucleotideAccessionVersion() {
        return rNANucleotideAccessionVersion;
    }

    public void setRNANucleotideAccessionVersion(Integer rNANucleotideAccessionVersion) {
        this.rNANucleotideAccessionVersion = rNANucleotideAccessionVersion;
    }

    public BigInteger getRNANucleotideGi() {
        return rNANucleotideGi;
    }

    public void setRNANucleotideGi(BigInteger rNANucleotideGi) {
        this.rNANucleotideGi = rNANucleotideGi;
    }

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public Integer getProteinAccessionVersion() {
        return proteinAccessionVersion;
    }

    public void setProteinAccessionVersion(Integer proteinAccessionVersion) {
        this.proteinAccessionVersion = proteinAccessionVersion;
    }

    public String getGenomicNucleotideAccession() {
        return genomicNucleotideAccession;
    }

    public void setGenomicNucleotideAccession(String genomicNucleotideAccession) {
        this.genomicNucleotideAccession = genomicNucleotideAccession;
    }

    public Integer getGenomicNucleotideAccessionVersion() {
        return genomicNucleotideAccessionVersion;
    }

    public void setGenomicNucleotideAccessionVersion(Integer genomicNucleotideAccessionVersion) {
        this.genomicNucleotideAccessionVersion = genomicNucleotideAccessionVersion;
    }

    public BigInteger getGenomicNucleotideGi() {
        return genomicNucleotideGi;
    }

    public void setGenomicNucleotideGi(BigInteger genomicNucleotideGi) {
        this.genomicNucleotideGi = genomicNucleotideGi;
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gene2Accession other = (Gene2Accession) obj;
        if (!Objects.equals(this.gene2AccessionPK, other.gene2AccessionPK)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.rNANucleotideAccession, other.rNANucleotideAccession)) {
            return false;
        }
        if (!Objects.equals(this.rNANucleotideAccessionVersion, other.rNANucleotideAccessionVersion)) {
            return false;
        }
        if (!Objects.equals(this.rNANucleotideGi, other.rNANucleotideGi)) {
            return false;
        }
        if (!Objects.equals(this.proteinAccession, other.proteinAccession)) {
            return false;
        }
        if (!Objects.equals(this.proteinAccessionVersion, other.proteinAccessionVersion)) {
            return false;
        }
        if (this.proteinGi != other.proteinGi) {
            return false;
        }
        if (!Objects.equals(this.genomicNucleotideAccession, other.genomicNucleotideAccession)) {
            return false;
        }
        if (!Objects.equals(this.genomicNucleotideAccessionVersion, other.genomicNucleotideAccessionVersion)) {
            return false;
        }
        if (!Objects.equals(this.genomicNucleotideGi, other.genomicNucleotideGi)) {
            return false;
        }
        if (!Objects.equals(this.startPositionOnTheGenomicAccession, other.startPositionOnTheGenomicAccession)) {
            return false;
        }
        if (!Objects.equals(this.endPositionOnTheGenomicAccession, other.endPositionOnTheGenomicAccession)) {
            return false;
        }
        if (!Objects.equals(this.orientation, other.orientation)) {
            return false;
        }
        if (!Objects.equals(this.assembly, other.assembly)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gene2AccessionPK != null ? gene2AccessionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Gene2Accession{" + "gene2AccessionPK=" + gene2AccessionPK + ", status=" + status + ", rNANucleotideAccession=" + rNANucleotideAccession + ", rNANucleotideAccessionVersion=" + rNANucleotideAccessionVersion + ", rNANucleotideGi=" + rNANucleotideGi + ", proteinAccession=" + proteinAccession + ", proteinAccessionVersion=" + proteinAccessionVersion + ", proteinGi=" + proteinGi + ", genomicNucleotideAccession=" + genomicNucleotideAccession + ", genomicNucleotideAccessionVersion=" + genomicNucleotideAccessionVersion + ", genomicNucleotideGi=" + genomicNucleotideGi + ", startPositionOnTheGenomicAccession=" + startPositionOnTheGenomicAccession + ", endPositionOnTheGenomicAccession=" + endPositionOnTheGenomicAccession + ", orientation=" + orientation + ", assembly=" + assembly + '}';
    }
}
