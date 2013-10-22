package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankDosages entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankDosages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankDosages.findAll", query = "SELECT d FROM DrugBankDosages d"),
    @NamedQuery(name = "DrugBankDosages.findByWid", query = "SELECT d FROM DrugBankDosages d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankDosages.findByDrugBankWID", query = "SELECT d FROM DrugBankDosages d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankDosages.findByForm", query = "SELECT d FROM DrugBankDosages d WHERE d.form = :form"),
    @NamedQuery(name = "DrugBankDosages.findByRoute", query = "SELECT d FROM DrugBankDosages d WHERE d.route = :route"),
    @NamedQuery(name = "DrugBankDosages.findByStrength", query = "SELECT d FROM DrugBankDosages d WHERE d.strength = :strength")})
public class DrugBankDosages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Column(name = "Form")
    private String form;
    @Column(name = "Route")
    private String route;
    @Column(name = "Strength")
    private String strength;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankDosages() {
    }

    public DrugBankDosages(Long wid) {
        this.wid = wid;
    }

    public DrugBankDosages(Long wid, long drugBankWID) {
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

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankDosages other = (DrugBankDosages) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.form, other.form)) {
            return false;
        }
        if (!Objects.equals(this.route, other.route)) {
            return false;
        }
        if (!Objects.equals(this.strength, other.strength)) {
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
        return "DrugBankDosages{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", form=" + form + ", route=" + route + ", strength=" + strength + '}';
    }
}
