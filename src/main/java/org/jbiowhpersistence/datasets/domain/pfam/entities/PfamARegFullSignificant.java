package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamARegFullSignificant entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $
 * $LastChangedRevision: 377 $
 * @since Nov 22, 2012
 */
@Entity
@Table(name = "PfamARegFullSignificant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamARegFullSignificant.findAll", query = "SELECT p FROM PfamARegFullSignificant p"),
    @NamedQuery(name = "PfamARegFullSignificant.findByWid", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamARegFullSignificant.findByPfamAWID", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.pfamAWID = :pfamAWID"),
    @NamedQuery(name = "PfamARegFullSignificant.findByProteinWID", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.proteinWID = :proteinWID"),
    @NamedQuery(name = "PfamARegFullSignificant.findByAutoPfamseq", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamARegFullSignificant.findBySeqStart", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.seqStart = :seqStart"),
    @NamedQuery(name = "PfamARegFullSignificant.findBySeqEnd", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.seqEnd = :seqEnd"),
    @NamedQuery(name = "PfamARegFullSignificant.findByAliStart", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.aliStart = :aliStart"),
    @NamedQuery(name = "PfamARegFullSignificant.findByAliEnd", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.aliEnd = :aliEnd"),
    @NamedQuery(name = "PfamARegFullSignificant.findByModelStart", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.modelStart = :modelStart"),
    @NamedQuery(name = "PfamARegFullSignificant.findByModelEnd", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.modelEnd = :modelEnd"),
    @NamedQuery(name = "PfamARegFullSignificant.findByDomainBitsScore", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.domainBitsScore = :domainBitsScore"),
    @NamedQuery(name = "PfamARegFullSignificant.findByDomainEvalueScore", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.domainEvalueScore = :domainEvalueScore"),
    @NamedQuery(name = "PfamARegFullSignificant.findBySequenceBitsScore", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.sequenceBitsScore = :sequenceBitsScore"),
    @NamedQuery(name = "PfamARegFullSignificant.findBySequenceEvalueScore", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.sequenceEvalueScore = :sequenceEvalueScore"),
    @NamedQuery(name = "PfamARegFullSignificant.findByInFull", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.inFull = :inFull"),
    @NamedQuery(name = "PfamARegFullSignificant.findByTreeOrder", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.treeOrder = :treeOrder"),
    @NamedQuery(name = "PfamARegFullSignificant.findByDomainOrder", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.domainOrder = :domainOrder"),
    @NamedQuery(name = "PfamARegFullSignificant.findByDomainOder", query = "SELECT p FROM PfamARegFullSignificant p WHERE p.domainOder = :domainOder")})
public class PfamARegFullSignificant implements Serializable {

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
    @Column(name = "ali_start")
    private int aliStart;
    @Basic(optional = false)
    @Column(name = "ali_end")
    private int aliEnd;
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
    @Lob
    @Column(name = "cigar")
    private String cigar;
    @Basic(optional = false)
    @Column(name = "in_full")
    private short inFull;
    @Column(name = "tree_order")
    private Integer treeOrder;
    @Column(name = "domain_order")
    private Integer domainOrder;
    @Column(name = "domain_oder")
    private Short domainOder;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamA_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamAbioWH pfamA;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamARegFullSignificant")
    private Set<PfamAPDBReg> pfamAPDBRegs;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pfamARegFullSignificant")
    private PfamSeqhasProtein pfamSeqhasProtein;

    public PfamARegFullSignificant() {
    }

    public PfamARegFullSignificant(Long wid) {
        this.wid = wid;
    }

    public PfamARegFullSignificant(Long wid, long pfamAWID, long proteinWID, long autoPfamseq, int seqStart, int seqEnd, int aliStart, int aliEnd, int modelStart, int modelEnd, double domainBitsScore, String domainEvalueScore, double sequenceBitsScore, String sequenceEvalueScore, short inFull) {
        this.wid = wid;
        this.pfamAWID = pfamAWID;
        this.proteinWID = proteinWID;
        this.autoPfamseq = autoPfamseq;
        this.seqStart = seqStart;
        this.seqEnd = seqEnd;
        this.aliStart = aliStart;
        this.aliEnd = aliEnd;
        this.modelStart = modelStart;
        this.modelEnd = modelEnd;
        this.domainBitsScore = domainBitsScore;
        this.domainEvalueScore = domainEvalueScore;
        this.sequenceBitsScore = sequenceBitsScore;
        this.sequenceEvalueScore = sequenceEvalueScore;
        this.inFull = inFull;
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

    public Set<PfamAPDBReg> getPfamAPDBRegs() {
        return pfamAPDBRegs;
    }

    public void setPfamAPDBRegs(Set<PfamAPDBReg> pfamAPDBRegs) {
        this.pfamAPDBRegs = pfamAPDBRegs;
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

    public int getAliStart() {
        return aliStart;
    }

    public void setAliStart(int aliStart) {
        this.aliStart = aliStart;
    }

    public int getAliEnd() {
        return aliEnd;
    }

    public void setAliEnd(int aliEnd) {
        this.aliEnd = aliEnd;
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

    public String getCigar() {
        return cigar;
    }

    public void setCigar(String cigar) {
        this.cigar = cigar;
    }

    public short getInFull() {
        return inFull;
    }

    public void setInFull(short inFull) {
        this.inFull = inFull;
    }

    public Integer getTreeOrder() {
        return treeOrder;
    }

    public void setTreeOrder(Integer treeOrder) {
        this.treeOrder = treeOrder;
    }

    public Integer getDomainOrder() {
        return domainOrder;
    }

    public void setDomainOrder(Integer domainOrder) {
        this.domainOrder = domainOrder;
    }

    public Short getDomainOder() {
        return domainOder;
    }

    public void setDomainOder(Short domainOder) {
        this.domainOder = domainOder;
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
        final PfamARegFullSignificant other = (PfamARegFullSignificant) obj;
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
        if (this.aliStart != other.aliStart) {
            return false;
        }
        if (this.aliEnd != other.aliEnd) {
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
        if (!Objects.equals(this.cigar, other.cigar)) {
            return false;
        }
        if (this.inFull != other.inFull) {
            return false;
        }
        if (!Objects.equals(this.treeOrder, other.treeOrder)) {
            return false;
        }
        if (!Objects.equals(this.domainOrder, other.domainOrder)) {
            return false;
        }
        if (!Objects.equals(this.domainOder, other.domainOder)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamARegFullSignificant{" + "wid=" + wid + ", pfamAWID=" + pfamAWID + ", proteinWID=" + proteinWID + ", autoPfamseq=" + autoPfamseq + ", seqStart=" + seqStart + ", seqEnd=" + seqEnd + ", aliStart=" + aliStart + ", aliEnd=" + aliEnd + ", modelStart=" + modelStart + ", modelEnd=" + modelEnd + ", domainBitsScore=" + domainBitsScore + ", domainEvalueScore=" + domainEvalueScore + ", sequenceBitsScore=" + sequenceBitsScore + ", sequenceEvalueScore=" + sequenceEvalueScore + ", cigar=" + cigar + ", inFull=" + inFull + ", treeOrder=" + treeOrder + ", domainOrder=" + domainOrder + ", domainOder=" + domainOder + '}';
    }
}
