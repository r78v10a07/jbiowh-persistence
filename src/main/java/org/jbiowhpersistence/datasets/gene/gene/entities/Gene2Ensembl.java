package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Gene2Ensembl entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "Gene2Ensembl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gene2Ensembl.findAll", query = "SELECT g FROM Gene2Ensembl g"),
    @NamedQuery(name = "Gene2Ensembl.findByWid", query = "SELECT g FROM Gene2Ensembl g WHERE g.wid = :wid"),
    @NamedQuery(name = "Gene2Ensembl.findByGeneInfoWID", query = "SELECT g FROM Gene2Ensembl g WHERE g.geneInfoWID = :geneInfoWID"),
    @NamedQuery(name = "Gene2Ensembl.findByEnsemblGeneIdentifier", query = "SELECT g FROM Gene2Ensembl g WHERE g.ensemblGeneIdentifier = :ensemblGeneIdentifier"),
    @NamedQuery(name = "Gene2Ensembl.findByRNANucleotideAccession", query = "SELECT g FROM Gene2Ensembl g WHERE g.rNANucleotideAccession = :rNANucleotideAccession"),
    @NamedQuery(name = "Gene2Ensembl.findByEnsemblRNAIdentifier", query = "SELECT g FROM Gene2Ensembl g WHERE g.ensemblRNAIdentifier = :ensemblRNAIdentifier"),
    @NamedQuery(name = "Gene2Ensembl.findByProteinAccession", query = "SELECT g FROM Gene2Ensembl g WHERE g.proteinAccession = :proteinAccession"),
    @NamedQuery(name = "Gene2Ensembl.findByEnsemblProteinIdentifier", query = "SELECT g FROM Gene2Ensembl g WHERE g.ensemblProteinIdentifier = :ensemblProteinIdentifier")})
public class Gene2Ensembl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Column(name = "EnsemblGeneIdentifier")
    private String ensemblGeneIdentifier;
    @Column(name = "RNANucleotideAccession")
    private String rNANucleotideAccession;
    @Column(name = "EnsemblRNAIdentifier")
    private String ensemblRNAIdentifier;
    @Column(name = "ProteinAccession")
    private String proteinAccession;
    @Column(name = "EnsemblProteinIdentifier")
    private String ensemblProteinIdentifier;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneInfo_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private GeneInfo geneInfo;

    public Gene2Ensembl() {
    }

    public Gene2Ensembl(Long wid) {
        this.wid = wid;
    }

    public Gene2Ensembl(Long wid, long geneInfoWID) {
        this.wid = wid;
        this.geneInfoWID = geneInfoWID;
    }

    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }
    
    public String getrNANucleotideAccession() {
        return rNANucleotideAccession;
    }

    public void setrNANucleotideAccession(String rNANucleotideAccession) {
        this.rNANucleotideAccession = rNANucleotideAccession;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    public String getEnsemblGeneIdentifier() {
        return ensemblGeneIdentifier;
    }

    public void setEnsemblGeneIdentifier(String ensemblGeneIdentifier) {
        this.ensemblGeneIdentifier = ensemblGeneIdentifier;
    }

    public String getRNANucleotideAccession() {
        return rNANucleotideAccession;
    }

    public void setRNANucleotideAccession(String rNANucleotideAccession) {
        this.rNANucleotideAccession = rNANucleotideAccession;
    }

    public String getEnsemblRNAIdentifier() {
        return ensemblRNAIdentifier;
    }

    public void setEnsemblRNAIdentifier(String ensemblRNAIdentifier) {
        this.ensemblRNAIdentifier = ensemblRNAIdentifier;
    }

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public String getEnsemblProteinIdentifier() {
        return ensemblProteinIdentifier;
    }

    public void setEnsemblProteinIdentifier(String ensemblProteinIdentifier) {
        this.ensemblProteinIdentifier = ensemblProteinIdentifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gene2Ensembl other = (Gene2Ensembl) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if (!Objects.equals(this.ensemblGeneIdentifier, other.ensemblGeneIdentifier)) {
            return false;
        }
        if (!Objects.equals(this.rNANucleotideAccession, other.rNANucleotideAccession)) {
            return false;
        }
        if (!Objects.equals(this.ensemblRNAIdentifier, other.ensemblRNAIdentifier)) {
            return false;
        }
        if (!Objects.equals(this.proteinAccession, other.proteinAccession)) {
            return false;
        }
        if (!Objects.equals(this.ensemblProteinIdentifier, other.ensemblProteinIdentifier)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Gene2Ensembl{"
                + " wid=" + wid
                + " geneWID=" + geneInfoWID
                + " ensemblGeneIdentifier=" + ensemblGeneIdentifier
                + " rNANucleotideAccession=" + rNANucleotideAccession
                + " ensemblRNAIdentifier=" + ensemblRNAIdentifier
                + " proteinAccession=" + proteinAccession
                + " ensemblProteinIdentifier=" + ensemblProteinIdentifier
                + '}';
    }
}
