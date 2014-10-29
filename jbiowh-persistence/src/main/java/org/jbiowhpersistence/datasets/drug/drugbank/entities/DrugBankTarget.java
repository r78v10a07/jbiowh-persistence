package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the DrugBankTarget entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTarget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTarget.findAll", query = "SELECT d FROM DrugBankTarget d"),
    @NamedQuery(name = "DrugBankTarget.findByWid", query = "SELECT d FROM DrugBankTarget d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTarget.findByDrugBankWID", query = "SELECT d FROM DrugBankTarget d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankTarget.findById", query = "SELECT d FROM DrugBankTarget d WHERE d.id = :id"),
    @NamedQuery(name = "DrugBankTarget.findByName", query = "SELECT d FROM DrugBankTarget d WHERE d.name = :name"),
    @NamedQuery(name = "DrugBankTarget.findByKnownAction", query = "SELECT d FROM DrugBankTarget d WHERE d.knownAction = :knownAction")})
public class DrugBankTarget implements Serializable {

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
    @Column(name = "KnownAction")
    private String knownAction;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankTargetRef",
            joinColumns
            = @JoinColumn(name = "DrugBankTarget_WID"))
    @XmlElementWrapper(name = "DrugBankTargetRefs")
    private Collection<DrugBankTargetRef> drugBankTargetRef;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankTargetAction",
            joinColumns
            = @JoinColumn(name = "DrugBankTarget_WID"))
    @XmlElementWrapper(name = "DrugBankTargetActions")
    private Collection<DrugBankTargetAction> drugBankTargetAction;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankTargetPolypeptide",
            joinColumns
            = @JoinColumn(name = "DrugBankTarget_WID"))
    @XmlElementWrapper(name = "DrugBankTargetPolypeptides")
    private Collection<DrugBankTargetPolypeptide> drugBankTargetPolypeptide;

    public DrugBankTarget() {
    }

    public DrugBankTarget(Long wid) {
        this.wid = wid;
    }

    public DrugBankTarget(Long wid, long drugBankWID) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
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

    public String getKnownAction() {
        return knownAction;
    }

    public void setKnownAction(String knownAction) {
        this.knownAction = knownAction;
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

    @XmlTransient
    public DrugBank getDrugBank() {
        return drugBank;
    }

    public void setDrugBank(DrugBank drugBank) {
        this.drugBank = drugBank;
    }

    public Collection<DrugBankTargetRef> getDrugBankTargetRef() {
        return drugBankTargetRef;
    }

    public void setDrugBankTargetRef(Collection<DrugBankTargetRef> drugBankTargetRef) {
        this.drugBankTargetRef = drugBankTargetRef;
    }

    public Collection<DrugBankTargetAction> getDrugBankTargetAction() {
        return drugBankTargetAction;
    }

    public void setDrugBankTargetAction(Collection<DrugBankTargetAction> drugBankTargetAction) {
        this.drugBankTargetAction = drugBankTargetAction;
    }

    public Collection<DrugBankTargetPolypeptide> getDrugBankTargetPolypeptide() {
        return drugBankTargetPolypeptide;
    }

    public void setDrugBankTargetPolypeptide(Collection<DrugBankTargetPolypeptide> drugBankTargetPolypeptide) {
        this.drugBankTargetPolypeptide = drugBankTargetPolypeptide;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.wid);
        hash = 53 * hash + (int) (this.drugBankWID ^ (this.drugBankWID >>> 32));
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.knownAction);
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
        final DrugBankTarget other = (DrugBankTarget) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.knownAction, other.knownAction);
    }

    @Override
    public String toString() {
        StringBuilder pData = new StringBuilder();

        if (drugBankTargetRef != null) {
            for (DrugBankTargetRef r : drugBankTargetRef) {
                pData.append("\t").append(r).append("\n");
            }
        }

        if (drugBankTargetAction != null) {
            for (DrugBankTargetAction r : drugBankTargetAction) {
                pData.append("\t").append(r).append("\n");
            }
        }

        if (drugBankTargetPolypeptide != null) {
            for (DrugBankTargetPolypeptide r : drugBankTargetPolypeptide) {
                pData.append("\t").append(r).append("\n");
            }
        }

        return "DrugBankTarget{"
                + "wid=" + wid
                + ", drugBankWID=" + drugBankWID
                + ", id=" + id
                + ", name=" + name
                + ", knownAction=" + knownAction
                + "}\n"
                + pData;
    }
}
