package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTargets entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTargets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTargets.findAll", query = "SELECT d FROM DrugBankTargets d"),
    @NamedQuery(name = "DrugBankTargets.findByWid", query = "SELECT d FROM DrugBankTargets d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTargets.findByDrugBankWID", query = "SELECT d FROM DrugBankTargets d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankTargets.findByPartner", query = "SELECT d FROM DrugBankTargets d WHERE d.partner = :partner"),
    @NamedQuery(name = "DrugBankTargets.findByPosition", query = "SELECT d FROM DrugBankTargets d WHERE d.position = :position"),
    @NamedQuery(name = "DrugBankTargets.findByKnownAction", query = "SELECT d FROM DrugBankTargets d WHERE d.knownAction = :knownAction")})
public class DrugBankTargets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Basic(optional = false)
    @Column(name = "Partner")
    private int partner;
    @Column(name = "Position")
    private Integer position;
    @Column(name = "KnownAction")
    private String knownAction;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBankTargets")
    private Set<DrugBankTargetsRef> drugBankTargetsRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBankTargets")
    private Set<DrugBankTargetsActions> drugBankTargetsActions;

    public DrugBankTargets() {
    }

    public DrugBankTargets(Long wid) {
        this.wid = wid;
    }

    public DrugBankTargets(Long wid, long drugBankWID, int partner) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
        this.partner = partner;
    }

    public Set<DrugBankTargetsActions> getDrugBankTargetsActions() {
        return drugBankTargetsActions;
    }

    public void setDrugBankTargetsActions(Set<DrugBankTargetsActions> drugBankTargetsActions) {
        this.drugBankTargetsActions = drugBankTargetsActions;
    }

    public Set<DrugBankTargetsRef> getDrugBankTargetsRef() {
        return drugBankTargetsRef;
    }

    public void setDrugBankTargetsRef(Set<DrugBankTargetsRef> drugBankTargetsRef) {
        this.drugBankTargetsRef = drugBankTargetsRef;
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

    public int getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        this.partner = partner;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getKnownAction() {
        return knownAction;
    }

    public void setKnownAction(String knownAction) {
        this.knownAction = knownAction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankTargets other = (DrugBankTargets) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (this.partner != other.partner) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.knownAction, other.knownAction)) {
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
        Iterator it;
        StringBuilder pData = new StringBuilder();

        if (!getDrugBankTargetsRef().isEmpty()) {
            it = getDrugBankTargetsRef().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankTargetsActions().isEmpty()) {
            it = getDrugBankTargetsActions().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        return "DrugBankTargets{"
                + "wid=" + wid
                + ", drugBankWID=" + drugBankWID
                + ", partner=" + partner
                + ", position=" + position
                + ", knownAction=" + knownAction + "}\n"
                + pData;
    }
}
