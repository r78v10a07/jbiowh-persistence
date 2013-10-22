package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankManufacturers entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankManufacturers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankManufacturers.findAll", query = "SELECT d FROM DrugBankManufacturers d"),
    @NamedQuery(name = "DrugBankManufacturers.findByWid", query = "SELECT d FROM DrugBankManufacturers d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankManufacturers.findByDrugBankWID", query = "SELECT d FROM DrugBankManufacturers d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankManufacturers.findByManufacturer", query = "SELECT d FROM DrugBankManufacturers d WHERE d.manufacturer = :manufacturer"),
    @NamedQuery(name = "DrugBankManufacturers.findByGeneric", query = "SELECT d FROM DrugBankManufacturers d WHERE d.generic = :generic")})
public class DrugBankManufacturers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Column(name = "Manufacturer")
    private String manufacturer;
    @Column(name = "Generic")
    private String generic;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankManufacturers() {
    }

    public DrugBankManufacturers(Long wid) {
        this.wid = wid;
    }

    public DrugBankManufacturers(Long wid, long drugBankWID) {
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankManufacturers other = (DrugBankManufacturers) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.generic, other.generic)) {
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
        return "DrugBankManufacturers{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", manufacturer=" + manufacturer + ", generic=" + generic + '}';
    }
}
