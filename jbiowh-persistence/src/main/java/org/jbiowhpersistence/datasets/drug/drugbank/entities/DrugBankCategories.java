package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.drug.drugbank.DrugBankTables;

/**
 * This class is the DrugBankCategories entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankCategories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankCategories.findAll", query = "SELECT d FROM DrugBankCategories d"),
    @NamedQuery(name = "DrugBankCategories.findByWid", query = "SELECT d FROM DrugBankCategories d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankCategories.findByCategory", query = "SELECT d FROM DrugBankCategories d WHERE d.category = :category")})
public class DrugBankCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Category")
    private String category;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = DrugBankTables.DRUGBANK_HAS_DRUGBANKCATEGORIES,
    joinColumns =
    @JoinColumn(name = "DrugBankCategories_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    private Set<DrugBank> drugBank;

    public DrugBankCategories() {
    }

    public DrugBankCategories(Long wid) {
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
        final DrugBankCategories other = (DrugBankCategories) obj;
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
        return "DrugBankCategories{" + "wid=" + wid + ", category=" + category + '}';
    }
}
