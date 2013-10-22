package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the GeneRefSeqUniprot entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "GeneRefSeqUniprot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneRefSeqUniprot.findAll", query = "SELECT g FROM GeneRefSeqUniprot g"),
    @NamedQuery(name = "GeneRefSeqUniprot.findByProteinAccession", query = "SELECT g FROM GeneRefSeqUniprot g WHERE g.geneRefSeqUniprotPK.proteinAccession = :proteinAccession"),
    @NamedQuery(name = "GeneRefSeqUniprot.findByUniProtKBProteinAccession", query = "SELECT g FROM GeneRefSeqUniprot g WHERE g.geneRefSeqUniprotPK.uniProtKBProteinAccession = :uniProtKBProteinAccession"),
    @NamedQuery(name = "GeneRefSeqUniprot.findByDataSetWID", query = "SELECT g FROM GeneRefSeqUniprot g WHERE g.dataSetWID = :dataSetWID")})
public class GeneRefSeqUniprot implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GeneRefSeqUniprotPK geneRefSeqUniprotPK;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;

    public GeneRefSeqUniprot() {
    }

    public GeneRefSeqUniprot(GeneRefSeqUniprotPK geneRefSeqUniprotPK) {
        this.geneRefSeqUniprotPK = geneRefSeqUniprotPK;
    }

    public GeneRefSeqUniprot(GeneRefSeqUniprotPK geneRefSeqUniprotPK, long dataSetWID) {
        this.geneRefSeqUniprotPK = geneRefSeqUniprotPK;
        this.dataSetWID = dataSetWID;
    }

    public GeneRefSeqUniprot(String proteinAccession, String uniProtKBProteinAccession) {
        this.geneRefSeqUniprotPK = new GeneRefSeqUniprotPK(proteinAccession, uniProtKBProteinAccession);
    }

    public GeneRefSeqUniprotPK getGeneRefSeqUniprotPK() {
        return geneRefSeqUniprotPK;
    }

    public void setGeneRefSeqUniprotPK(GeneRefSeqUniprotPK geneRefSeqUniprotPK) {
        this.geneRefSeqUniprotPK = geneRefSeqUniprotPK;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geneRefSeqUniprotPK != null ? geneRefSeqUniprotPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneRefSeqUniprot)) {
            return false;
        }
        GeneRefSeqUniprot other = (GeneRefSeqUniprot) object;
        if ((this.geneRefSeqUniprotPK == null && other.geneRefSeqUniprotPK != null) || (this.geneRefSeqUniprotPK != null && !this.geneRefSeqUniprotPK.equals(other.geneRefSeqUniprotPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneRefSeqUniprot{ "
                + " ProteinAccession=" + geneRefSeqUniprotPK.getProteinAccession()
                + " UniProtKBProteinAccession=" + geneRefSeqUniprotPK.getUniProtKBProteinAccession()
                + " dataSetWID = " + dataSetWID
                + '}';
    }
}
