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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamAPDBReg entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamAPDBReg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamAPDBReg.findAll", query = "SELECT p FROM PfamAPDBReg p"),
    @NamedQuery(name = "PfamAPDBReg.findByWid", query = "SELECT p FROM PfamAPDBReg p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamAPDBReg.findByPfamARegFullSignificantWID", query = "SELECT p FROM PfamAPDBReg p WHERE p.pfamARegFullSignificantWID = :pfamARegFullSignificantWID"),
    @NamedQuery(name = "PfamAPDBReg.findByChain", query = "SELECT p FROM PfamAPDBReg p WHERE p.chain = :chain"),
    @NamedQuery(name = "PfamAPDBReg.findByPdbResStart", query = "SELECT p FROM PfamAPDBReg p WHERE p.pdbResStart = :pdbResStart"),
    @NamedQuery(name = "PfamAPDBReg.findByPdbStartIcode", query = "SELECT p FROM PfamAPDBReg p WHERE p.pdbStartIcode = :pdbStartIcode"),
    @NamedQuery(name = "PfamAPDBReg.findByPdbResEnd", query = "SELECT p FROM PfamAPDBReg p WHERE p.pdbResEnd = :pdbResEnd"),
    @NamedQuery(name = "PfamAPDBReg.findByPdbEndIcode", query = "SELECT p FROM PfamAPDBReg p WHERE p.pdbEndIcode = :pdbEndIcode"),
    @NamedQuery(name = "PfamAPDBReg.findBySeqStart", query = "SELECT p FROM PfamAPDBReg p WHERE p.seqStart = :seqStart"),
    @NamedQuery(name = "PfamAPDBReg.findBySeqEnd", query = "SELECT p FROM PfamAPDBReg p WHERE p.seqEnd = :seqEnd"),
    @NamedQuery(name = "PfamAPDBReg.findByHexColour", query = "SELECT p FROM PfamAPDBReg p WHERE p.hexColour = :hexColour")})
public class PfamAPDBReg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "PfamARegFullSignificant_WID")
    private long pfamARegFullSignificantWID;
    @Column(name = "chain")
    private String chain;
    @Column(name = "pdb_res_start")
    private Integer pdbResStart;
    @Column(name = "pdb_start_icode")
    private String pdbStartIcode;
    @Column(name = "pdb_res_end")
    private Integer pdbResEnd;
    @Column(name = "pdb_end_icode")
    private String pdbEndIcode;
    @Basic(optional = false)
    @Column(name = "seq_start")
    private int seqStart;
    @Basic(optional = false)
    @Column(name = "seq_end")
    private int seqEnd;
    @Column(name = "hex_colour")
    private String hexColour;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamARegFullSignificant_WID", insertable = false, unique = false, nullable = false, updatable = false)
    private PfamARegFullSignificant pfamARegFullSignificant;

    public PfamAPDBReg() {
    }

    public PfamAPDBReg(Long wid) {
        this.wid = wid;
    }

    public PfamAPDBReg(Long wid, long pfamARegFullSignificantWID, int seqStart, int seqEnd) {
        this.wid = wid;
        this.pfamARegFullSignificantWID = pfamARegFullSignificantWID;
        this.seqStart = seqStart;
        this.seqEnd = seqEnd;
    }

    public PfamARegFullSignificant getPfamARegFullSignificant() {
        return pfamARegFullSignificant;
    }

    public void setPfamARegFullSignificant(PfamARegFullSignificant pfamARegFullSignificant) {
        this.pfamARegFullSignificant = pfamARegFullSignificant;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getPfamARegFullSignificantWID() {
        return pfamARegFullSignificantWID;
    }

    public void setPfamARegFullSignificantWID(long pfamARegFullSignificantWID) {
        this.pfamARegFullSignificantWID = pfamARegFullSignificantWID;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public Integer getPdbResStart() {
        return pdbResStart;
    }

    public void setPdbResStart(Integer pdbResStart) {
        this.pdbResStart = pdbResStart;
    }

    public String getPdbStartIcode() {
        return pdbStartIcode;
    }

    public void setPdbStartIcode(String pdbStartIcode) {
        this.pdbStartIcode = pdbStartIcode;
    }

    public Integer getPdbResEnd() {
        return pdbResEnd;
    }

    public void setPdbResEnd(Integer pdbResEnd) {
        this.pdbResEnd = pdbResEnd;
    }

    public String getPdbEndIcode() {
        return pdbEndIcode;
    }

    public void setPdbEndIcode(String pdbEndIcode) {
        this.pdbEndIcode = pdbEndIcode;
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

    public String getHexColour() {
        return hexColour;
    }

    public void setHexColour(String hexColour) {
        this.hexColour = hexColour;
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
        final PfamAPDBReg other = (PfamAPDBReg) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.chain, other.chain)) {
            return false;
        }
        if (!Objects.equals(this.pdbResStart, other.pdbResStart)) {
            return false;
        }
        if (!Objects.equals(this.pdbStartIcode, other.pdbStartIcode)) {
            return false;
        }
        if (!Objects.equals(this.pdbResEnd, other.pdbResEnd)) {
            return false;
        }
        if (!Objects.equals(this.pdbEndIcode, other.pdbEndIcode)) {
            return false;
        }
        if (this.seqStart != other.seqStart) {
            return false;
        }
        if (this.seqEnd != other.seqEnd) {
            return false;
        }
        if (!Objects.equals(this.hexColour, other.hexColour)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamAPDBReg{" + "wid=" + wid + ", pfamARegFullSignificantWID=" + pfamARegFullSignificantWID + ", chain=" + chain + ", pdbResStart=" + pdbResStart + ", pdbStartIcode=" + pdbStartIcode + ", pdbResEnd=" + pdbResEnd + ", pdbEndIcode=" + pdbEndIcode + ", seqStart=" + seqStart + ", seqEnd=" + seqEnd + ", hexColour=" + hexColour + '}';
    }
}
