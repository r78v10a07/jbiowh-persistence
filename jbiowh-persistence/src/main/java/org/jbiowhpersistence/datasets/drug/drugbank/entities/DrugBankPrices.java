package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankPrices entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankPrices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankPrices.findAll", query = "SELECT d FROM DrugBankPrices d"),
    @NamedQuery(name = "DrugBankPrices.findByWid", query = "SELECT d FROM DrugBankPrices d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankPrices.findByDrugBankWID", query = "SELECT d FROM DrugBankPrices d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankPrices.findByCost", query = "SELECT d FROM DrugBankPrices d WHERE d.cost = :cost"),
    @NamedQuery(name = "DrugBankPrices.findByCurrency", query = "SELECT d FROM DrugBankPrices d WHERE d.currency = :currency"),
    @NamedQuery(name = "DrugBankPrices.findByUnit", query = "SELECT d FROM DrugBankPrices d WHERE d.unit = :unit")})
public class DrugBankPrices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Lob
    @Column(name = "Description")
    private String description;
    @Column(name = "Cost")
    private String cost;
    @Column(name = "Currency")
    private String currency;
    @Column(name = "Unit")
    private String unit;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankPrices() {
    }

    public DrugBankPrices(Long wid) {
        this.wid = wid;
    }

    public DrugBankPrices(Long wid, long drugBankWID) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankPrices other = (DrugBankPrices) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
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
        return "DrugBankPrices{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", description=" + description + ", cost=" + cost + ", currency=" + currency + ", unit=" + unit + '}';
    }
}
