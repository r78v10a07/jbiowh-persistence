package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankEnzymesActions entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankEnzymesActions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankEnzymesActions.findAll", query = "SELECT d FROM DrugBankEnzymesActions d"),
    @NamedQuery(name = "DrugBankEnzymesActions.findByWid", query = "SELECT d FROM DrugBankEnzymesActions d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankEnzymesActions.findByDrugBankEnzymesWID", query = "SELECT d FROM DrugBankEnzymesActions d WHERE d.drugBankEnzymesWID = :drugBankEnzymesWID"),
    @NamedQuery(name = "DrugBankEnzymesActions.findByAction", query = "SELECT d FROM DrugBankEnzymesActions d WHERE d.action = :action")})
public class DrugBankEnzymesActions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBankEnzymes_WID")
    private long drugBankEnzymesWID;
    @Column(name = "Action")
    private String action;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBankEnzymes_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBankEnzymes drugBankEnzymes;

    public DrugBankEnzymesActions() {
    }

    public DrugBankEnzymesActions(Long wid) {
        this.wid = wid;
    }

    public DrugBankEnzymesActions(Long wid, long drugBankEnzymesWID) {
        this.wid = wid;
        this.drugBankEnzymesWID = drugBankEnzymesWID;
    }

    public DrugBankEnzymes getDrugBankEnzymes() {
        return drugBankEnzymes;
    }

    public void setDrugBankEnzymes(DrugBankEnzymes drugBankEnzymes) {
        this.drugBankEnzymes = drugBankEnzymes;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getDrugBankEnzymesWID() {
        return drugBankEnzymesWID;
    }

    public void setDrugBankEnzymesWID(long drugBankEnzymesWID) {
        this.drugBankEnzymesWID = drugBankEnzymesWID;
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
        final DrugBankEnzymesActions other = (DrugBankEnzymesActions) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankEnzymesWID != other.drugBankEnzymesWID) {
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
        return "DrugBankEnzymesActions{" + "wid=" + wid + ", drugBankEnzymesWID=" + drugBankEnzymesWID + ", action=" + action + '}';
    }
}
