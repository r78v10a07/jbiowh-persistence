package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankCarriersActions entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankCarriersActions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankCarriersActions.findAll", query = "SELECT d FROM DrugBankCarriersActions d"),
    @NamedQuery(name = "DrugBankCarriersActions.findByWid", query = "SELECT d FROM DrugBankCarriersActions d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankCarriersActions.findByDrugBankCarriersWID", query = "SELECT d FROM DrugBankCarriersActions d WHERE d.drugBankCarriersWID = :drugBankCarriersWID"),
    @NamedQuery(name = "DrugBankCarriersActions.findByAction", query = "SELECT d FROM DrugBankCarriersActions d WHERE d.action = :action")})
public class DrugBankCarriersActions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBankCarriers_WID")
    private long drugBankCarriersWID;
    @Column(name = "Action")
    private String action;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBankCarriers_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBankCarriers drugBankCarriers;

    public DrugBankCarriersActions() {
    }

    public DrugBankCarriersActions(Long wid) {
        this.wid = wid;
    }

    public DrugBankCarriersActions(Long wid, long drugBankCarriersWID) {
        this.wid = wid;
        this.drugBankCarriersWID = drugBankCarriersWID;
    }

    public DrugBankCarriers getDrugBankCarriers() {
        return drugBankCarriers;
    }

    public void setDrugBankCarriers(DrugBankCarriers drugBankCarriers) {
        this.drugBankCarriers = drugBankCarriers;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getDrugBankCarriersWID() {
        return drugBankCarriersWID;
    }

    public void setDrugBankCarriersWID(long drugBankCarriersWID) {
        this.drugBankCarriersWID = drugBankCarriersWID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankCarriersActions other = (DrugBankCarriersActions) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankCarriersWID != other.drugBankCarriersWID) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
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
        return "DrugBankCarriersActions{" + "wid=" + wid + ", drugBankCarriersWID=" + drugBankCarriersWID + ", action=" + action + '}';
    }
}
