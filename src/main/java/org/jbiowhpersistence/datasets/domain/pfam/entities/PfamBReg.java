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
 * This class is the PfamBReg entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamBReg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamBReg.findAll", query = "SELECT p FROM PfamBReg p"),
    @NamedQuery(name = "PfamBReg.findByWid", query = "SELECT p FROM PfamBReg p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamBReg.findByPfamBWID", query = "SELECT p FROM PfamBReg p WHERE p.pfamBWID = :pfamBWID"),
    @NamedQuery(name = "PfamBReg.findByAutoPfamseq", query = "SELECT p FROM PfamBReg p WHERE p.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamBReg.findBySeqStart", query = "SELECT p FROM PfamBReg p WHERE p.seqStart = :seqStart"),
    @NamedQuery(name = "PfamBReg.findBySeqEnd", query = "SELECT p FROM PfamBReg p WHERE p.seqEnd = :seqEnd")})
public class PfamBReg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "PfamB_WID")
    private long pfamBWID;
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "seq_start")
    private int seqStart;
    @Basic(optional = false)
    @Column(name = "seq_end")
    private int seqEnd;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamB_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamBbioWH pfamB;

    public PfamBReg() {
    }

    public PfamBReg(Long wid) {
        this.wid = wid;
    }

    public PfamBReg(Long wid, long pfamBWID, long autoPfamseq, int seqStart, int seqEnd) {
        this.wid = wid;
        this.pfamBWID = pfamBWID;
        this.autoPfamseq = autoPfamseq;
        this.seqStart = seqStart;
        this.seqEnd = seqEnd;
    }

    public PfamBbioWH getPfamB() {
        return pfamB;
    }

    public void setPfamB(PfamBbioWH pfamB) {
        this.pfamB = pfamB;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getPfamBWID() {
        return pfamBWID;
    }

    public void setPfamBWID(long pfamBWID) {
        this.pfamBWID = pfamBWID;
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
        final PfamBReg other = (PfamBReg) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.seqStart != other.seqStart) {
            return false;
        }
        if (this.seqEnd != other.seqEnd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamBReg{" + "wid=" + wid + ", pfamBWID=" + pfamBWID + ", autoPfamseq=" + autoPfamseq + ", seqStart=" + seqStart + ", seqEnd=" + seqEnd + '}';
    }
}
