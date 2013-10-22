package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamSeqDisulphide entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamSeqDisulphide")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamSeqDisulphide.findAll", query = "SELECT p FROM PfamSeqDisulphide p"),
    @NamedQuery(name = "PfamSeqDisulphide.findByWid", query = "SELECT p FROM PfamSeqDisulphide p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamSeqDisulphide.findByAutoPfamseq", query = "SELECT p FROM PfamSeqDisulphide p WHERE p.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamSeqDisulphide.findByBondStart", query = "SELECT p FROM PfamSeqDisulphide p WHERE p.bondStart = :bondStart"),
    @NamedQuery(name = "PfamSeqDisulphide.findByBondEnd", query = "SELECT p FROM PfamSeqDisulphide p WHERE p.bondEnd = :bondEnd")})
public class PfamSeqDisulphide implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "bond_start")
    private int bondStart;
    @Column(name = "bond_end")
    private Integer bondEnd;

    public PfamSeqDisulphide() {
    }

    public PfamSeqDisulphide(Long wid) {
        this.wid = wid;
    }

    public PfamSeqDisulphide(Long wid, long autoPfamseq, int bondStart) {
        this.wid = wid;
        this.autoPfamseq = autoPfamseq;
        this.bondStart = bondStart;
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

    public int getBondStart() {
        return bondStart;
    }

    public void setBondStart(int bondStart) {
        this.bondStart = bondStart;
    }

    public Integer getBondEnd() {
        return bondEnd;
    }

    public void setBondEnd(Integer bondEnd) {
        this.bondEnd = bondEnd;
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
        final PfamSeqDisulphide other = (PfamSeqDisulphide) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.bondStart != other.bondStart) {
            return false;
        }
        if (!Objects.equals(this.bondEnd, other.bondEnd)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamSeqDisulphide{" + "wid=" + wid + ", autoPfamseq=" + autoPfamseq + ", bondStart=" + bondStart + ", bondEnd=" + bondEnd + '}';
    }
}
