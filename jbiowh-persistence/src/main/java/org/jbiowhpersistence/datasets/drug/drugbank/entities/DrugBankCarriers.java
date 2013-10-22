package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankCarriers entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankCarriers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankCarriers.findAll", query = "SELECT d FROM DrugBankCarriers d"),
    @NamedQuery(name = "DrugBankCarriers.findByWid", query = "SELECT d FROM DrugBankCarriers d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankCarriers.findByDrugBankWID", query = "SELECT d FROM DrugBankCarriers d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankCarriers.findByPartner", query = "SELECT d FROM DrugBankCarriers d WHERE d.partner = :partner"),
    @NamedQuery(name = "DrugBankCarriers.findByPosition", query = "SELECT d FROM DrugBankCarriers d WHERE d.position = :position")})
public class DrugBankCarriers implements Serializable {

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBankCarriers")
    private Set<DrugBankCarriersRef> drugBankCarriersRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBankCarriers")
    private Set<DrugBankCarriersActions> drugBankCarriersActions;

    public DrugBankCarriers() {
    }

    public DrugBankCarriers(Long wid) {
        this.wid = wid;
    }

    public DrugBankCarriers(Long wid, long drugBankWID, int partner) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
        this.partner = partner;
    }

    public Set<DrugBankCarriersActions> getDrugBankCarriersActions() {
        return drugBankCarriersActions;
    }

    public void setDrugBankCarriersActions(Set<DrugBankCarriersActions> drugBankCarriersActions) {
        this.drugBankCarriersActions = drugBankCarriersActions;
    }

    public Set<DrugBankCarriersRef> getDrugBankCarriersRef() {
        return drugBankCarriersRef;
    }

    public void setDrugBankCarriersRef(Set<DrugBankCarriersRef> drugBankCarriersRef) {
        this.drugBankCarriersRef = drugBankCarriersRef;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankCarriers other = (DrugBankCarriers) obj;
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

        if (!getDrugBankCarriersRef().isEmpty()) {
            it = getDrugBankCarriersRef().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankCarriersActions().isEmpty()) {
            it = getDrugBankCarriersActions().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        return "DrugBankCarriers{"
                + "wid=" + wid
                + ", drugBankWID=" + drugBankWID
                + ", partner=" + partner
                + ", position=" + position + "}\n"
                + pData;
    }
}
