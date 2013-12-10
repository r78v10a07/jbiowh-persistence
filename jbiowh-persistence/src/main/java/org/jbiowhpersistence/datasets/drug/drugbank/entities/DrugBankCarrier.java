package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the DrugBankCarrier entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankCarrier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankCarrier.findAll", query = "SELECT d FROM DrugBankCarrier d"),
    @NamedQuery(name = "DrugBankCarrier.findByWid", query = "SELECT d FROM DrugBankCarrier d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankCarrier.findByDrugBankWID", query = "SELECT d FROM DrugBankCarrier d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankCarrier.findByPartner", query = "SELECT d FROM DrugBankCarrier d WHERE d.partner = :partner"),
    @NamedQuery(name = "DrugBankCarrier.findByPosition", query = "SELECT d FROM DrugBankCarrier d WHERE d.position = :position")})
public class DrugBankCarrier implements Serializable {

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
    @ElementCollection
    @CollectionTable(
            name = "DrugBankCarrierRef",
            joinColumns
            = @JoinColumn(name = "DrugBankCarrier_WID"))
    @XmlElementWrapper(name = "DrugBankCarrierRefs")
    private Collection<DrugBankCarrierRef> drugBankCarrierRef;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankCarrierAction",
            joinColumns
            = @JoinColumn(name = "DrugBankCarrier_WID"))
    @XmlElementWrapper(name = "DrugBankCarrierActions")
    private Collection<DrugBankCarrierAction> drugBankCarrierAction;

    public DrugBankCarrier() {
    }

    public DrugBankCarrier(Long wid) {
        this.wid = wid;
    }

    public DrugBankCarrier(Long wid, long drugBankWID, int partner) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
        this.partner = partner;
    }

    public Collection<DrugBankCarrierRef> getDrugBankCarrierRef() {
        return drugBankCarrierRef;
    }

    public void setDrugBankCarrierRef(Collection<DrugBankCarrierRef> drugBankCarrierRef) {
        this.drugBankCarrierRef = drugBankCarrierRef;
    }

    public Collection<DrugBankCarrierAction> getDrugBankCarrierAction() {
        return drugBankCarrierAction;
    }

    public void setDrugBankCarrierAction(Collection<DrugBankCarrierAction> drugBankCarrierAction) {
        this.drugBankCarrierAction = drugBankCarrierAction;
    }

    @XmlTransient
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
        final DrugBankCarrier other = (DrugBankCarrier) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (this.partner != other.partner) {
            return false;
        }
        return Objects.equals(this.position, other.position);
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

        if (drugBankCarrierRef != null) {
            for (DrugBankCarrierRef r : drugBankCarrierRef) {
                pData.append("\t").append(r).append("\n");
            }
        }

        if (drugBankCarrierAction != null) {
            for (DrugBankCarrierAction r : drugBankCarrierAction) {
                pData.append("\t").append(r).append("\n");
            }
        }

        return "DrugBankCarrier{"
                + "wid=" + wid
                + ", drugBankWID=" + drugBankWID
                + ", partner=" + partner
                + ", position=" + position + "}\n"
                + pData;
    }
}
