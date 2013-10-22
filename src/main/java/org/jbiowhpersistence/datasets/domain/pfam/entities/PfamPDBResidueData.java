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
 * This class is the PfamPDBResidueData entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ 
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamPDBResidueData")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamPDBResidueData.findAll", query = "SELECT p FROM PfamPDBResidueData p"),
    @NamedQuery(name = "PfamPDBResidueData.findByWid", query = "SELECT p FROM PfamPDBResidueData p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamPDBResidueData.findByAutoPfamseq", query = "SELECT p FROM PfamPDBResidueData p WHERE p.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamPDBResidueData.findByPfamPDBWID", query = "SELECT p FROM PfamPDBResidueData p WHERE p.pfamPDBWID = :pfamPDBWID"),
    @NamedQuery(name = "PfamPDBResidueData.findByChain", query = "SELECT p FROM PfamPDBResidueData p WHERE p.chain = :chain"),
    @NamedQuery(name = "PfamPDBResidueData.findBySerial", query = "SELECT p FROM PfamPDBResidueData p WHERE p.serial = :serial"),
    @NamedQuery(name = "PfamPDBResidueData.findByPdbRes", query = "SELECT p FROM PfamPDBResidueData p WHERE p.pdbRes = :pdbRes"),
    @NamedQuery(name = "PfamPDBResidueData.findByPdbSeqNumber", query = "SELECT p FROM PfamPDBResidueData p WHERE p.pdbSeqNumber = :pdbSeqNumber"),
    @NamedQuery(name = "PfamPDBResidueData.findByPdbInsertCode", query = "SELECT p FROM PfamPDBResidueData p WHERE p.pdbInsertCode = :pdbInsertCode"),
    @NamedQuery(name = "PfamPDBResidueData.findByObserved", query = "SELECT p FROM PfamPDBResidueData p WHERE p.observed = :observed"),
    @NamedQuery(name = "PfamPDBResidueData.findByDsspCode", query = "SELECT p FROM PfamPDBResidueData p WHERE p.dsspCode = :dsspCode"),
    @NamedQuery(name = "PfamPDBResidueData.findByPfamseqRes", query = "SELECT p FROM PfamPDBResidueData p WHERE p.pfamseqRes = :pfamseqRes"),
    @NamedQuery(name = "PfamPDBResidueData.findByPfamseqSeqNumber", query = "SELECT p FROM PfamPDBResidueData p WHERE p.pfamseqSeqNumber = :pfamseqSeqNumber")})
public class PfamPDBResidueData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "PfamPDB_WID")
    private long pfamPDBWID;
    @Basic(optional = false)
    @Column(name = "chain")
    private String chain;
    @Basic(optional = false)
    @Column(name = "serial")
    private int serial;
    @Column(name = "pdb_res")
    private String pdbRes;
    @Column(name = "pdb_seq_number")
    private Integer pdbSeqNumber;
    @Column(name = "pdb_insert_code")
    private String pdbInsertCode;
    @Column(name = "observed")
    private Integer observed;
    @Column(name = "dssp_code")
    private String dsspCode;
    @Column(name = "pfamseq_res")
    private String pfamseqRes;
    @Column(name = "pfamseq_seq_number")
    private Integer pfamseqSeqNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamPDB_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamPDB pfamPDB;

    public PfamPDBResidueData() {
    }

    public PfamPDBResidueData(Long wid) {
        this.wid = wid;
    }

    public PfamPDBResidueData(Long wid, long autoPfamseq, long pfamPDBWID, String chain, int serial) {
        this.wid = wid;
        this.autoPfamseq = autoPfamseq;
        this.pfamPDBWID = pfamPDBWID;
        this.chain = chain;
        this.serial = serial;
    }

    public PfamPDB getPfamPDB() {
        return pfamPDB;
    }

    public void setPfamPDB(PfamPDB pfamPDB) {
        this.pfamPDB = pfamPDB;
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

    public long getPfamPDBWID() {
        return pfamPDBWID;
    }

    public void setPfamPDBWID(long pfamPDBWID) {
        this.pfamPDBWID = pfamPDBWID;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getPdbRes() {
        return pdbRes;
    }

    public void setPdbRes(String pdbRes) {
        this.pdbRes = pdbRes;
    }

    public Integer getPdbSeqNumber() {
        return pdbSeqNumber;
    }

    public void setPdbSeqNumber(Integer pdbSeqNumber) {
        this.pdbSeqNumber = pdbSeqNumber;
    }

    public String getPdbInsertCode() {
        return pdbInsertCode;
    }

    public void setPdbInsertCode(String pdbInsertCode) {
        this.pdbInsertCode = pdbInsertCode;
    }

    public Integer getObserved() {
        return observed;
    }

    public void setObserved(Integer observed) {
        this.observed = observed;
    }

    public String getDsspCode() {
        return dsspCode;
    }

    public void setDsspCode(String dsspCode) {
        this.dsspCode = dsspCode;
    }

    public String getPfamseqRes() {
        return pfamseqRes;
    }

    public void setPfamseqRes(String pfamseqRes) {
        this.pfamseqRes = pfamseqRes;
    }

    public Integer getPfamseqSeqNumber() {
        return pfamseqSeqNumber;
    }

    public void setPfamseqSeqNumber(Integer pfamseqSeqNumber) {
        this.pfamseqSeqNumber = pfamseqSeqNumber;
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
        final PfamPDBResidueData other = (PfamPDBResidueData) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.chain, other.chain)) {
            return false;
        }
        if (this.serial != other.serial) {
            return false;
        }
        if (!Objects.equals(this.pdbRes, other.pdbRes)) {
            return false;
        }
        if (!Objects.equals(this.pdbSeqNumber, other.pdbSeqNumber)) {
            return false;
        }
        if (!Objects.equals(this.pdbInsertCode, other.pdbInsertCode)) {
            return false;
        }
        if (!Objects.equals(this.observed, other.observed)) {
            return false;
        }
        if (!Objects.equals(this.dsspCode, other.dsspCode)) {
            return false;
        }
        if (!Objects.equals(this.pfamseqRes, other.pfamseqRes)) {
            return false;
        }
        if (!Objects.equals(this.pfamseqSeqNumber, other.pfamseqSeqNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamPDBResidueData{" + "wid=" + wid + ", autoPfamseq=" + autoPfamseq + ", pfamPDBWID=" + pfamPDBWID + ", chain=" + chain + ", serial=" + serial + ", pdbRes=" + pdbRes + ", pdbSeqNumber=" + pdbSeqNumber + ", pdbInsertCode=" + pdbInsertCode + ", observed=" + observed + ", dsspCode=" + dsspCode + ", pfamseqRes=" + pfamseqRes + ", pfamseqSeqNumber=" + pfamseqSeqNumber + '}';
    }
}
