package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
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
@Embeddable
@Table(name = "Gene2Ensembl")
@XmlRootElement
public class Gene2Ensembl implements Serializable {
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

    public Gene2Ensembl() {
    }
    
    public String getrNANucleotideAccession() {
        return rNANucleotideAccession;
    }

    public void setrNANucleotideAccession(String rNANucleotideAccession) {
        this.rNANucleotideAccession = rNANucleotideAccession;
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
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.ensemblGeneIdentifier != null ? this.ensemblGeneIdentifier.hashCode() : 0);
        hash = 89 * hash + (this.rNANucleotideAccession != null ? this.rNANucleotideAccession.hashCode() : 0);
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
        final Gene2Ensembl other = (Gene2Ensembl) obj;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if ((this.ensemblGeneIdentifier == null) ? (other.ensemblGeneIdentifier != null) : !this.ensemblGeneIdentifier.equals(other.ensemblGeneIdentifier)) {
            return false;
        }
        if ((this.rNANucleotideAccession == null) ? (other.rNANucleotideAccession != null) : !this.rNANucleotideAccession.equals(other.rNANucleotideAccession)) {
            return false;
        }
        if ((this.ensemblRNAIdentifier == null) ? (other.ensemblRNAIdentifier != null) : !this.ensemblRNAIdentifier.equals(other.ensemblRNAIdentifier)) {
            return false;
        }
        if ((this.proteinAccession == null) ? (other.proteinAccession != null) : !this.proteinAccession.equals(other.proteinAccession)) {
            return false;
        }
        return !((this.ensemblProteinIdentifier == null) ? (other.ensemblProteinIdentifier != null) : !this.ensemblProteinIdentifier.equals(other.ensemblProteinIdentifier));
    }

    @Override
    public String toString() {
        return "Gene2Ensembl{"
                + " geneWID=" + geneInfoWID
                + " ensemblGeneIdentifier=" + ensemblGeneIdentifier
                + " rNANucleotideAccession=" + rNANucleotideAccession
                + " ensemblRNAIdentifier=" + ensemblRNAIdentifier
                + " proteinAccession=" + proteinAccession
                + " ensemblProteinIdentifier=" + ensemblProteinIdentifier
                + '}';
    }
}
