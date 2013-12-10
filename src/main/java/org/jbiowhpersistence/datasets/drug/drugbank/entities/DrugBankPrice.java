package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankPrice entity

 $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankPrice")
@XmlRootElement
public class DrugBankPrice implements Serializable {

    @Lob
    @Column(name = "Description")
    private String description;
    @Column(name = "Cost")
    private String cost;
    @Column(name = "Currency")
    private String currency;
    @Column(name = "Unit")
    private String unit;

    public DrugBankPrice() {
    }

    public DrugBankPrice(String description, String cost, String currency, String unit) {
        this.description = description;
        this.cost = cost;
        this.currency = currency;
        this.unit = unit;
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
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 13 * hash + (this.cost != null ? this.cost.hashCode() : 0);
        hash = 13 * hash + (this.currency != null ? this.currency.hashCode() : 0);
        hash = 13 * hash + (this.unit != null ? this.unit.hashCode() : 0);
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
        final DrugBankPrice other = (DrugBankPrice) obj;
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if ((this.cost == null) ? (other.cost != null) : !this.cost.equals(other.cost)) {
            return false;
        }
        if ((this.currency == null) ? (other.currency != null) : !this.currency.equals(other.currency)) {
            return false;
        }
        return !((this.unit == null) ? (other.unit != null) : !this.unit.equals(other.unit));
    }

    @Override
    public String toString() {
        return "DrugBankPrice{" + "description=" + description + ", cost=" + cost + ", currency=" + currency + ", unit=" + unit + '}';
    }
}
