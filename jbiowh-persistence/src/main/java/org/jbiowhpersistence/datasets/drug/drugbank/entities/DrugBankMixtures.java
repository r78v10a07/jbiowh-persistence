package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankMixtures entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankMixtures")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankMixtures.findAll", query = "SELECT d FROM DrugBankMixtures d"),
    @NamedQuery(name = "DrugBankMixtures.findByWid", query = "SELECT d FROM DrugBankMixtures d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankMixtures.findByDrugBankWID", query = "SELECT d FROM DrugBankMixtures d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankMixtures.findByName", query = "SELECT d FROM DrugBankMixtures d WHERE d.name = :name")})
public class DrugBankMixtures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "Ingredients")
    private String ingredients;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankMixtures() {
    }

    public DrugBankMixtures(Long wid) {
        this.wid = wid;
    }

    public DrugBankMixtures(Long wid, long drugBankWID) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankMixtures other = (DrugBankMixtures) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
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
        return "DrugBankMixtures{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", name=" + name + ", ingredients=" + ingredients + '}';
    }
}
