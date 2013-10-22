package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamContextRegions entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamContextRegions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamContextRegions.findAll", query = "SELECT p FROM PfamContextRegions p"),
    @NamedQuery(name = "PfamContextRegions.findByWid", query = "SELECT p FROM PfamContextRegions p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamContextRegions.findByAutoPfamseq", query = "SELECT p FROM PfamContextRegions p WHERE p.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamContextRegions.findByPfamAWID", query = "SELECT p FROM PfamContextRegions p WHERE p.pfamAWID = :pfamAWID"),
    @NamedQuery(name = "PfamContextRegions.findBySeqStart", query = "SELECT p FROM PfamContextRegions p WHERE p.seqStart = :seqStart"),
    @NamedQuery(name = "PfamContextRegions.findBySeqEnd", query = "SELECT p FROM PfamContextRegions p WHERE p.seqEnd = :seqEnd"),
    @NamedQuery(name = "PfamContextRegions.findByDomainScore", query = "SELECT p FROM PfamContextRegions p WHERE p.domainScore = :domainScore")})
public class PfamContextRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "PfamA_WID")
    private long pfamAWID;
    @Basic(optional = false)
    @Column(name = "seq_start")
    private int seqStart;
    @Basic(optional = false)
    @Column(name = "seq_end")
    private int seqEnd;
    @Basic(optional = false)
    @Column(name = "domain_score")
    private double domainScore;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamA_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamAbioWH pfamA;

    public PfamContextRegions() {
    }

    public PfamContextRegions(Long wid) {
        this.wid = wid;
    }

    public PfamContextRegions(Long wid, long autoPfamseq, long pfamAWID, int seqStart, int seqEnd, double domainScore) {
        this.wid = wid;
        this.autoPfamseq = autoPfamseq;
        this.pfamAWID = pfamAWID;
        this.seqStart = seqStart;
        this.seqEnd = seqEnd;
        this.domainScore = domainScore;
    }

    public PfamAbioWH getPfamA() {
        return pfamA;
    }

    public void setPfamA(PfamAbioWH pfamA) {
        this.pfamA = pfamA;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getAutoPfamseq() {
        return autoPfamseq;
    }

    public void setAutoPfamseq(long autoPfamseq) {
        this.autoPfamseq = autoPfamseq;
    }

    public long getPfamAWID() {
        return pfamAWID;
    }

    public void setPfamAWID(long pfamAWID) {
        this.pfamAWID = pfamAWID;
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

    public double getDomainScore() {
        return domainScore;
    }

    public void setDomainScore(double domainScore) {
        this.domainScore = domainScore;
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
        final PfamContextRegions other = (PfamContextRegions) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.seqStart != other.seqStart) {
            return false;
        }
        if (this.seqEnd != other.seqEnd) {
            return false;
        }
        if (Double.doubleToLongBits(this.domainScore) != Double.doubleToLongBits(other.domainScore)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamContextRegions{" + "wid=" + wid + ", autoPfamseq=" + autoPfamseq + ", pfamAWID=" + pfamAWID + ", seqStart=" + seqStart + ", seqEnd=" + seqEnd + ", domainScore=" + domainScore + '}';
    }
}
