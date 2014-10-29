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
    @NamedQuery(name = "DrugBankCarrier.findById", query = "SELECT d FROM DrugBankCarrier d WHERE d.id = :id"),
    @NamedQuery(name = "DrugBankCarrier.findByName", query = "SELECT d FROM DrugBankCarrier d WHERE d.name = :name")})
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
    @Column(name = "Id")
    private String id;
    @Column(name = "Name")
    private String name;
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
    @ElementCollection
    @CollectionTable(
            name = "DrugBankCarrierPolypeptide",
            joinColumns
            = @JoinColumn(name = "DrugBankCarrier_WID"))
    @XmlElementWrapper(name = "DrugBankCarrierPolypeptides")
    private Collection<DrugBankCarrierPolypeptide> drugBankCarrierPolypeptide;

    public DrugBankCarrier() {
    }

    public DrugBankCarrier(Long wid) {
        this.wid = wid;
    }

    public DrugBankCarrier(Long wid, long drugBankWID) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<DrugBankCarrierPolypeptide> getDrugBankCarrierPolypeptide() {
        return drugBankCarrierPolypeptide;
    }

    public void setDrugBankCarrierPolypeptide(Collection<DrugBankCarrierPolypeptide> drugBankCarrierPolypeptide) {
        this.drugBankCarrierPolypeptide = drugBankCarrierPolypeptide;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.wid);
        hash = 97 * hash + (int) (this.drugBankWID ^ (this.drugBankWID >>> 32));
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
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

        if (drugBankCarrierPolypeptide != null) {
            for (DrugBankCarrierPolypeptide r : drugBankCarrierPolypeptide) {
                pData.append("\t").append(r).append("\n");
            }
        }

        return "DrugBankCarrier{"
                + "wid=" + wid
                + ", drugBankWID=" + drugBankWID
                + ", id=" + id
                + ", name=" + name
                + "}\n"
                + pData;
    }
}
