package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTaxonomySubstructures entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTaxonomySubstructures")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTaxonomySubstructures.findAll", query = "SELECT d FROM DrugBankTaxonomySubstructures d"),
    @NamedQuery(name = "DrugBankTaxonomySubstructures.findByWid", query = "SELECT d FROM DrugBankTaxonomySubstructures d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTaxonomySubstructures.findByDrugBankWID", query = "SELECT d FROM DrugBankTaxonomySubstructures d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankTaxonomySubstructures.findBySubstructure", query = "SELECT d FROM DrugBankTaxonomySubstructures d WHERE d.substructure = :substructure"),
    @NamedQuery(name = "DrugBankTaxonomySubstructures.findByClass1", query = "SELECT d FROM DrugBankTaxonomySubstructures d WHERE d.class1 = :class1")})
public class DrugBankTaxonomySubstructures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Column(name = "Substructure")
    private String substructure;
    @Column(name = "Class")
    private String class1;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankTaxonomySubstructures() {
    }

    public DrugBankTaxonomySubstructures(Long wid) {
        this.wid = wid;
    }

    public DrugBankTaxonomySubstructures(Long wid, long drugBankWID) {
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

    public String getSubstructure() {
        return substructure;
    }

    public void setSubstructure(String substructure) {
        this.substructure = substructure;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankTaxonomySubstructures other = (DrugBankTaxonomySubstructures) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.substructure, other.substructure)) {
            return false;
        }
        if (!Objects.equals(this.class1, other.class1)) {
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
        return "DrugBankTaxonomySubstructures{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", substructure=" + substructure + ", class1=" + class1 + '}';
    }
}
