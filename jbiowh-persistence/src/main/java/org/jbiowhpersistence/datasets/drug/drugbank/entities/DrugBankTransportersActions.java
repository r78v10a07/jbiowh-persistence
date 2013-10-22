package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTransportersActions entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTransportersActions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTransportersActions.findAll", query = "SELECT d FROM DrugBankTransportersActions d"),
    @NamedQuery(name = "DrugBankTransportersActions.findByWid", query = "SELECT d FROM DrugBankTransportersActions d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTransportersActions.findByDrugBankTransportersWID", query = "SELECT d FROM DrugBankTransportersActions d WHERE d.drugBankTransportersWID = :drugBankTransportersWID"),
    @NamedQuery(name = "DrugBankTransportersActions.findByAction", query = "SELECT d FROM DrugBankTransportersActions d WHERE d.action = :action")})
public class DrugBankTransportersActions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBankTransporters_WID")
    private long drugBankTransportersWID;
    @Column(name = "Action")
    private String action;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBankTransporters_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBankTransporters drugBankTransporters;

    public DrugBankTransportersActions() {
    }

    public DrugBankTransportersActions(Long wid) {
        this.wid = wid;
    }

    public DrugBankTransportersActions(Long wid, long drugBankTransportersWID) {
        this.wid = wid;
        this.drugBankTransportersWID = drugBankTransportersWID;
    }

    public DrugBankTransporters getDrugBankTransporters() {
        return drugBankTransporters;
    }

    public void setDrugBankTransporters(DrugBankTransporters drugBankTransporters) {
        this.drugBankTransporters = drugBankTransporters;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getDrugBankTransportersWID() {
        return drugBankTransportersWID;
    }

    public void setDrugBankTransportersWID(long drugBankTransportersWID) {
        this.drugBankTransportersWID = drugBankTransportersWID;
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
        final DrugBankTransportersActions other = (DrugBankTransportersActions) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankTransportersWID != other.drugBankTransportersWID) {
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
        return "DrugBankTransportersActions{" + "wid=" + wid + ", drugBankTransportersWID=" + drugBankTransportersWID + ", action=" + action + '}';
    }
}
