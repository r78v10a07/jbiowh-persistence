package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamARegSeed entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamARegSeed")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamARegSeed.findAll", query = "SELECT p FROM PfamARegSeed p"),
    @NamedQuery(name = "PfamARegSeed.findByAutoPfamseq", query = "SELECT p FROM PfamARegSeed p WHERE p.pfamARegSeedPK.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamARegSeed.findByPfamAWID", query = "SELECT p FROM PfamARegSeed p WHERE p.pfamARegSeedPK.pfamAWID = :pfamAWID"),
    @NamedQuery(name = "PfamARegSeed.findBySeqStart", query = "SELECT p FROM PfamARegSeed p WHERE p.seqStart = :seqStart"),
    @NamedQuery(name = "PfamARegSeed.findBySeqEnd", query = "SELECT p FROM PfamARegSeed p WHERE p.seqEnd = :seqEnd"),
    @NamedQuery(name = "PfamARegSeed.findByTreeOrder", query = "SELECT p FROM PfamARegSeed p WHERE p.pfamARegSeedPK.treeOrder = :treeOrder")})
public class PfamARegSeed implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PfamARegSeedPK pfamARegSeedPK;
    @Basic(optional = false)
    @Column(name = "seq_start")
    private int seqStart;
    @Basic(optional = false)
    @Column(name = "seq_end")
    private int seqEnd;
    @Lob
    @Column(name = "cigar")
    private String cigar;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamA_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamAbioWH pfamA;

    public PfamARegSeed() {
    }

    public PfamARegSeed(PfamARegSeedPK pfamARegSeedPK) {
        this.pfamARegSeedPK = pfamARegSeedPK;
    }

    public PfamARegSeed(PfamARegSeedPK pfamARegSeedPK, int seqStart, int seqEnd) {
        this.pfamARegSeedPK = pfamARegSeedPK;
        this.seqStart = seqStart;
        this.seqEnd = seqEnd;
    }

    public PfamARegSeed(long autoPfamseq, long pfamAWID, int treeOrder) {
        this.pfamARegSeedPK = new PfamARegSeedPK(autoPfamseq, pfamAWID, treeOrder);
    }

    public PfamARegSeedPK getPfamARegSeedPK() {
        return pfamARegSeedPK;
    }

    public void setPfamARegSeedPK(PfamARegSeedPK pfamARegSeedPK) {
        this.pfamARegSeedPK = pfamARegSeedPK;
    }

    public PfamAbioWH getPfamA() {
        return pfamA;
    }

    public void setPfamA(PfamAbioWH pfamA) {
        this.pfamA = pfamA;
    }

    public int getSeqStart() {
        return seqStart;
    }

    public void setSeqStart(int seqStart) {
        this.seqStart = seqStart;
    }

    public int getSeqEnd() {
        return seqEnd;
    }

    public void setSeqEnd(int seqEnd) {
        this.seqEnd = seqEnd;
    }

    public String getCigar() {
        return cigar;
    }

    public void setCigar(String cigar) {
        this.cigar = cigar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfamARegSeedPK != null ? pfamARegSeedPK.hashCode() : 0);
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
        final PfamARegSeed other = (PfamARegSeed) obj;
        if (!Objects.equals(this.pfamARegSeedPK, other.pfamARegSeedPK)) {
            return false;
        }
        if (this.seqStart != other.seqStart) {
            return false;
        }
        if (this.seqEnd != other.seqEnd) {
            return false;
        }
        if (!Objects.equals(this.cigar, other.cigar)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamARegSeed{" + "pfamARegSeedPK=" + pfamARegSeedPK + ", seqStart=" + seqStart + ", seqEnd=" + seqEnd + ", cigar=" + cigar + '}';
    }
}
