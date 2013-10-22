package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTargetsActions entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTargetsActions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTargetsActions.findAll", query = "SELECT d FROM DrugBankTargetsActions d"),
    @NamedQuery(name = "DrugBankTargetsActions.findByWid", query = "SELECT d FROM DrugBankTargetsActions d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTargetsActions.findByDrugBankTargetsWID", query = "SELECT d FROM DrugBankTargetsActions d WHERE d.drugBankTargetsWID = :drugBankTargetsWID"),
    @NamedQuery(name = "DrugBankTargetsActions.findByAction", query = "SELECT d FROM DrugBankTargetsActions d WHERE d.action = :action")})
public class DrugBankTargetsActions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBankTargets_WID")
    private long drugBankTargetsWID;
    @Column(name = "Action")
    private String action;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBankTargets_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBankTargets drugBankTargets;

    public DrugBankTargetsActions() {
    }

    public DrugBankTargetsActions(Long wid) {
        this.wid = wid;
    }

    public DrugBankTargetsActions(Long wid, long drugBankTargetsWID) {
        this.wid = wid;
        this.drugBankTargetsWID = drugBankTargetsWID;
    }

    public DrugBankTargets getDrugBankTargets() {
        return drugBankTargets;
    }

    public void setDrugBankTargets(DrugBankTargets drugBankTargets) {
        this.drugBankTargets = drugBankTargets;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getDrugBankTargetsWID() {
        return drugBankTargetsWID;
    }

    public void setDrugBankTargetsWID(long drugBankTargetsWID) {
        this.drugBankTargetsWID = drugBankTargetsWID;
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
        final DrugBankTargetsActions other = (DrugBankTargetsActions) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankTargetsWID != other.drugBankTargetsWID) {
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
        return "DrugBankTargetsActions{" + "wid=" + wid + ", drugBankTargetsWID=" + drugBankTargetsWID + ", action=" + action + '}';
    }
}
