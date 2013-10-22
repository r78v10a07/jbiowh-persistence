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
 * This class is the PfamNestedLocations entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamNestedLocations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamNestedLocations.findAll", query = "SELECT p FROM PfamNestedLocations p"),
    @NamedQuery(name = "PfamNestedLocations.findByWid", query = "SELECT p FROM PfamNestedLocations p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamNestedLocations.findByPfamAWID", query = "SELECT p FROM PfamNestedLocations p WHERE p.pfamAWID = :pfamAWID"),
    @NamedQuery(name = "PfamNestedLocations.findByOtherPfamAWID", query = "SELECT p FROM PfamNestedLocations p WHERE p.otherPfamAWID = :otherPfamAWID"),
    @NamedQuery(name = "PfamNestedLocations.findByAutoPfamseq", query = "SELECT p FROM PfamNestedLocations p WHERE p.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamNestedLocations.findBySeqVersion", query = "SELECT p FROM PfamNestedLocations p WHERE p.seqVersion = :seqVersion"),
    @NamedQuery(name = "PfamNestedLocations.findBySeqStart", query = "SELECT p FROM PfamNestedLocations p WHERE p.seqStart = :seqStart"),
    @NamedQuery(name = "PfamNestedLocations.findBySeqEnd", query = "SELECT p FROM PfamNestedLocations p WHERE p.seqEnd = :seqEnd")})
public class PfamNestedLocations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "PfamA_WID")
    private long pfamAWID;
    @Basic(optional = false)
    @Column(name = "OtherPfamA_WID")
    private long otherPfamAWID;
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Column(name = "seq_version")
    private Short seqVersion;
    @Column(name = "seq_start")
    private Integer seqStart;
    @Column(name = "seq_end")
    private Integer seqEnd;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamA_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamAbioWH pfamA;

    public PfamNestedLocations() {
    }

    public PfamNestedLocations(Long wid) {
        this.wid = wid;
    }

    public PfamNestedLocations(Long wid, long pfamAWID, long otherPfamAWID, long autoPfamseq) {
        this.wid = wid;
        this.pfamAWID = pfamAWID;
        this.otherPfamAWID = otherPfamAWID;
        this.autoPfamseq = autoPfamseq;
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

    public long getPfamAWID() {
        return pfamAWID;
    }

    public void setPfamAWID(long pfamAWID) {
        this.pfamAWID = pfamAWID;
    }

    public long getOtherPfamAWID() {
        return otherPfamAWID;
    }

    public void setOtherPfamAWID(long otherPfamAWID) {
        this.otherPfamAWID = otherPfamAWID;
    }

    public long getAutoPfamseq() {
        return autoPfamseq;
    }

    public void setAutoPfamseq(long autoPfamseq) {
        this.autoPfamseq = autoPfamseq;
    }

    public Short getSeqVersion() {
        return seqVersion;
    }

    public void setSeqVersion(Short seqVersion) {
        this.seqVersion = seqVersion;
    }

    public Integer getSeqStart() {
        return seqStart;
    }

    public void setSeqStart(Integer seqStart) {
        this.seqStart = seqStart;
    }

    public Integer getSeqEnd() {
        return seqEnd;
    }

    public void setSeqEnd(Integer seqEnd) {
        this.seqEnd = seqEnd;
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
        final PfamNestedLocations other = (PfamNestedLocations) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.autoPfamseq != other.autoPfamseq) {
            return false;
        }
        if (!Objects.equals(this.seqVersion, other.seqVersion)) {
            return false;
        }
        if (!Objects.equals(this.seqStart, other.seqStart)) {
            return false;
        }
        if (!Objects.equals(this.seqEnd, other.seqEnd)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamNestedLocations{" + "wid=" + wid + ", pfamAWID=" + pfamAWID + ", otherPfamAWID=" + otherPfamAWID + ", autoPfamseq=" + autoPfamseq + ", seqVersion=" + seqVersion + ", seqStart=" + seqStart + ", seqEnd=" + seqEnd + '}';
    }
}
