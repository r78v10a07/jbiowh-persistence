package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the GeneRefSeqUniprotPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Embeddable
public class GeneRefSeqUniprotPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinAccession")
    private String proteinAccession;
    @Basic(optional = false)
    @Column(name = "UniProtKBProteinAccession")
    private String uniProtKBProteinAccession;

    public GeneRefSeqUniprotPK() {
    }

    public GeneRefSeqUniprotPK(String proteinAccession, String uniProtKBProteinAccession) {
        this.proteinAccession = proteinAccession;
        this.uniProtKBProteinAccession = uniProtKBProteinAccession;
    }

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public String getUniProtKBProteinAccession() {
        return uniProtKBProteinAccession;
    }

    public void setUniProtKBProteinAccession(String uniProtKBProteinAccession) {
        this.uniProtKBProteinAccession = uniProtKBProteinAccession;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinAccession != null ? proteinAccession.hashCode() : 0);
        hash += (uniProtKBProteinAccession != null ? uniProtKBProteinAccession.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneRefSeqUniprotPK)) {
            return false;
        }
        GeneRefSeqUniprotPK other = (GeneRefSeqUniprotPK) object;
        if ((this.proteinAccession == null && other.proteinAccession != null) || (this.proteinAccession != null && !this.proteinAccession.equals(other.proteinAccession))) {
            return false;
        }
        if ((this.uniProtKBProteinAccession == null && other.uniProtKBProteinAccession != null) || (this.uniProtKBProteinAccession != null && !this.uniProtKBProteinAccession.equals(other.uniProtKBProteinAccession))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.gene.entities.GeneRefSeqUniprotPK[ proteinAccession=" + proteinAccession + ", uniProtKBProteinAccession=" + uniProtKBProteinAccession + " ]";
    }
}
