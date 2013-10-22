package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamARegFullInsignificant entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $
 * $LastChangedRevision: 377 $
 * @since Nov 22, 2012
 */
@Entity
@Table(name = "PfamARegFullInsignificant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamARegFullInsignificant.findAll", query = "SELECT p FROM PfamARegFullInsignificant p"),
    @NamedQuery(name = "PfamARegFullInsignificant.findByWid", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamARegFullInsignificant.findByPfamAWID", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.pfamAWID = :pfamAWID"),
    @NamedQuery(name = "PfamARegFullInsignificant.findByProteinWID", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.proteinWID = :proteinWID"),
    @NamedQuery(name = "PfamARegFullInsignificant.findByAutoPfamseq", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamARegFullInsignificant.findBySeqStart", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.seqStart = :seqStart"),
    @NamedQuery(name = "PfamARegFullInsignificant.findBySeqEnd", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.seqEnd = :seqEnd"),
    @NamedQuery(name = "PfamARegFullInsignificant.findByModelStart", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.modelStart = :modelStart"),
    @NamedQuery(name = "PfamARegFullInsignificant.findByModelEnd", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.modelEnd = :modelEnd"),
    @NamedQuery(name = "PfamARegFullInsignificant.findByDomainBitsScore", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.domainBitsScore = :domainBitsScore"),
    @NamedQuery(name = "PfamARegFullInsignificant.findByDomainEvalueScore", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.domainEvalueScore = :domainEvalueScore"),
    @NamedQuery(name = "PfamARegFullInsignificant.findBySequenceBitsScore", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.sequenceBitsScore = :sequenceBitsScore"),
    @NamedQuery(name = "PfamARegFullInsignificant.findBySequenceEvalueScore", query = "SELECT p FROM PfamARegFullInsignificant p WHERE p.sequenceEvalueScore = :sequenceEvalueScore")})
public class PfamARegFullInsignificant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "PfamA_WID")
    private long pfamAWID;
    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "seq_start")
    private int seqStart;
    @Basic(optional = false)
    @Column(name = "seq_end")
    private int seqEnd;
    @Basic(optional = false)
    @Column(name = "model_start")
    private int modelStart;
    @Basic(optional = false)
    @Column(name = "model_end")
    private int modelEnd;
    @Basic(optional = false)
    @Column(name = "domain_bits_score")
    private double domainBitsScore;
    @Basic(optional = false)
    @Column(name = "domain_evalue_score")
    private String domainEvalueScore;
    @Basic(optional = false)
    @Column(name = "sequence_bits_score")
    private double sequenceBitsScore;
    @Basic(optional = false)
    @Column(name = "sequence_evalue_score")
    private String sequenceEvalueScore;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamA_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamAbioWH pfamA;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pfamARegFullInsignificant")
    private PfamSeqhasProtein pfamSeqhasProtein;

    public PfamARegFullInsignificant() {
    }

    public PfamARegFullInsignificant(Long wid) {
        this.wid = wid;
    }

    public PfamARegFullInsignificant(Long wid, long pfamAWID, long proteinWID, long autoPfamseq, int seqStart, int seqEnd, int modelStart, int modelEnd, double domainBitsScore, String domainEvalueScore, double sequenceBitsScore, String sequenceEvalueScore) {
        this.wid = wid;
        this.pfamAWID = pfamAWID;
        this.proteinWID = proteinWID;
        this.autoPfamseq = autoPfamseq;
        this.seqStart = seqStart;
        this.seqEnd = seqEnd;
        this.modelStart = modelStart;
        this.modelEnd = modelEnd;
        this.domainBitsScore = domainBitsScore;
        this.domainEvalueScore = domainEvalueScore;
        this.sequenceBitsScore = sequenceBitsScore;
        this.sequenceEvalueScore = sequenceEvalueScore;
    }
    
    public void setRelationsToNull() {
        setPfamA(null);
    }

    public PfamAbioWH getPfamA() {
        return pfamA;
    }

    public void setPfamA(PfamAbioWH pfamA) {
        this.pfamA = pfamA;
    }

    public PfamSeqhasProtein getPfamSeqhasProtein() {
        return pfamSeqhasProtein;
    }

    public void setPfamSeqhasProtein(PfamSeqhasProtein pfamSeqhasProtein) {
        this.pfamSeqhasProtein = pfamSeqhasProtein;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getPfamAWID() {
        return pfamAWID;
    }

    public void setPfamAWID(long pfamAWID) {
        this.pfamAWID = pfamAWID;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
    }

    public long getAutoPfamseq() {
        return autoPfamseq;
    }

    public void setAutoPfamseq(long autoPfamseq) {
        this.autoPfamseq = autoPfamseq;
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

    public int getModelStart() {
        return modelStart;
    }

    public void setModelStart(int modelStart) {
        this.modelStart = modelStart;
    }

    public int getModelEnd() {
        return modelEnd;
    }

    public void setModelEnd(int modelEnd) {
        this.modelEnd = modelEnd;
    }

    public double getDomainBitsScore() {
        return domainBitsScore;
    }

    public void setDomainBitsScore(double domainBitsScore) {
        this.domainBitsScore = domainBitsScore;
    }

    public String getDomainEvalueScore() {
        return domainEvalueScore;
    }

    public void setDomainEvalueScore(String domainEvalueScore) {
        this.domainEvalueScore = domainEvalueScore;
    }

    public double getSequenceBitsScore() {
        return sequenceBitsScore;
    }

    public void setSequenceBitsScore(double sequenceBitsScore) {
        this.sequenceBitsScore = sequenceBitsScore;
    }

    public String getSequenceEvalueScore() {
        return sequenceEvalueScore;
    }

    public void setSequenceEvalueScore(String sequenceEvalueScore) {
        this.sequenceEvalueScore = sequenceEvalueScore;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
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
        final PfamARegFullInsignificant other = (PfamARegFullInsignificant) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.autoPfamseq != other.autoPfamseq) {
            return false;
        }
        if (this.seqStart != other.seqStart) {
            return false;
        }
        if (this.seqEnd != other.seqEnd) {
            return false;
        }
        if (this.modelStart != other.modelStart) {
            return false;
        }
        if (this.modelEnd != other.modelEnd) {
            return false;
        }
        if (Double.doubleToLongBits(this.domainBitsScore) != Double.doubleToLongBits(other.domainBitsScore)) {
            return false;
        }
        if (!Objects.equals(this.domainEvalueScore, other.domainEvalueScore)) {
            return false;
        }
        if (Double.doubleToLongBits(this.sequenceBitsScore) != Double.doubleToLongBits(other.sequenceBitsScore)) {
            return false;
        }
        if (!Objects.equals(this.sequenceEvalueScore, other.sequenceEvalueScore)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamARegFullInsignificant{" + "wid=" + wid + ", pfamAWID=" + pfamAWID + ", proteinWID=" + proteinWID + ", autoPfamseq=" + autoPfamseq + ", seqStart=" + seqStart + ", seqEnd=" + seqEnd + ", modelStart=" + modelStart + ", modelEnd=" + modelEnd + ", domainBitsScore=" + domainBitsScore + ", domainEvalueScore=" + domainEvalueScore + ", sequenceBitsScore=" + sequenceBitsScore + ", sequenceEvalueScore=" + sequenceEvalueScore + '}';
    }
}
