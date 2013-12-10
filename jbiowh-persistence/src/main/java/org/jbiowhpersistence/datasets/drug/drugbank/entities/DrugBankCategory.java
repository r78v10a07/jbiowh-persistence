package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the DrugBankCategory entity

 $Author: r78v10a07@gmail.com $
 $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankCategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankCategory.findAll", query = "SELECT d FROM DrugBankCategory d"),
    @NamedQuery(name = "DrugBankCategory.findByWid", query = "SELECT d FROM DrugBankCategory d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankCategory.findByCategory", query = "SELECT d FROM DrugBankCategory d WHERE d.category = :category")})
public class DrugBankCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Category")
    private String category;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "drugBankCategory")
    private Set<DrugBank> drugBank;

    public DrugBankCategory() {
    }

    public DrugBankCategory(Long wid) {
        this.wid = wid;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBank() {
        return drugBank;
    }

    public void setDrugBank(Set<DrugBank> drugBank) {
        this.drugBank = drugBank;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankCategory other = (DrugBankCategory) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
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
        return "DrugBankCategory{" + "wid=" + wid + ", category=" + category + '}';
    }
}
