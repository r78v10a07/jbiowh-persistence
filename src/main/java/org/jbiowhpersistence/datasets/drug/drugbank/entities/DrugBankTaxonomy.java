package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTaxonomy entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTaxonomy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTaxonomy.findAll", query = "SELECT d FROM DrugBankTaxonomy d"),
    @NamedQuery(name = "DrugBankTaxonomy.findByWid", query = "SELECT d FROM DrugBankTaxonomy d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTaxonomy.findByDrugBankWID", query = "SELECT d FROM DrugBankTaxonomy d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankTaxonomy.findByKingdom", query = "SELECT d FROM DrugBankTaxonomy d WHERE d.kingdom = :kingdom")})
public class DrugBankTaxonomy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Column(name = "Kingdom")
    private String kingdom;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankTaxonomy() {
    }

    public DrugBankTaxonomy(Long wid) {
        this.wid = wid;
    }

    public DrugBankTaxonomy(Long wid, long drugBankWID) {
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

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankTaxonomy other = (DrugBankTaxonomy) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.kingdom, other.kingdom)) {
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
        return "DrugBankTaxonomy{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", kingdom=" + kingdom + '}';
    }
}
