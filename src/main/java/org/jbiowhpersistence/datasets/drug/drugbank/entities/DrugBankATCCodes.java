package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankATCCodes entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankATCCodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankATCCodes.findAll", query = "SELECT d FROM DrugBankATCCodes d"),
    @NamedQuery(name = "DrugBankATCCodes.findByWid", query = "SELECT d FROM DrugBankATCCodes d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankATCCodes.findByDrugBankWID", query = "SELECT d FROM DrugBankATCCodes d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankATCCodes.findByATCCode", query = "SELECT d FROM DrugBankATCCodes d WHERE d.aTCCode = :aTCCode")})
public class DrugBankATCCodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Column(name = "ATCCode")
    private String aTCCode;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankATCCodes() {
    }

    public DrugBankATCCodes(Long wid) {
        this.wid = wid;
    }

    public DrugBankATCCodes(Long wid, long drugBankWID) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
    }

    public DrugBank getDrugBank() {
        return drugBank;
    }

    public void setDrugBank(DrugBank drugBank) {
        this.drugBank = drugBank;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getDrugBankWID() {
        return drugBankWID;
    }

    public void setDrugBankWID(long drugBankWID) {
        this.drugBankWID = drugBankWID;
    }

    public String getATCCode() {
        return aTCCode;
    }

    public void setATCCode(String aTCCode) {
        this.aTCCode = aTCCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankATCCodes other = (DrugBankATCCodes) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.aTCCode, other.aTCCode)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "DrugBankATCCodes{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", aTCCode=" + aTCCode + '}';
    }
}