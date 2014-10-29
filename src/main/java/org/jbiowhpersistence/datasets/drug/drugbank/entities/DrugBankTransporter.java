package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the DrugBankTransporter entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTransporter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTransporter.findAll", query = "SELECT d FROM DrugBankTransporter d"),
    @NamedQuery(name = "DrugBankTransporter.findByWid", query = "SELECT d FROM DrugBankTransporter d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTransporter.findByDrugBankWID", query = "SELECT d FROM DrugBankTransporter d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankTransporter.findById", query = "SELECT d FROM DrugBankTransporter d WHERE d.id = :id"),
    @NamedQuery(name = "DrugBankTransporter.findByName", query = "SELECT d FROM DrugBankTransporter d WHERE d.name = :name")})
public class DrugBankTransporter implements Serializable {

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
            name = "DrugBankTransporterRef",
            joinColumns
            = @JoinColumn(name = "DrugBankTransporter_WID"))
    @XmlElementWrapper(name = "DrugBankTransporterRefs")
    private Collection<DrugBankTransporterRef> drugBankTransporterRef;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankTransporterAction",
            joinColumns
            = @JoinColumn(name = "DrugBankTransporter_WID"))
    @XmlElementWrapper(name = "DrugBankTransporterActions")
    private Collection<DrugBankTransporterAction> drugBankTransporterAction;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankTransporterPolypeptide",
            joinColumns
            = @JoinColumn(name = "DrugBankTransporter_WID"))
    @XmlElementWrapper(name = "DrugBankTransporterPolypeptides")
    private Collection<DrugBankTransporterPolypeptide> drugBankTransporterPolypeptide;

    public DrugBankTransporter() {
    }

    public DrugBankTransporter(Long wid) {
        this.wid = wid;
    }

    public DrugBankTransporter(Long wid, long drugBankWID) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
    }

    public Collection<DrugBankTransporterRef> getDrugBankTransporterRef() {
        return drugBankTransporterRef;
    }

    public void setDrugBankTransporterRef(Collection<DrugBankTransporterRef> drugBankTransporterRef) {
        this.drugBankTransporterRef = drugBankTransporterRef;
    }

    public Collection<DrugBankTransporterAction> getDrugBankTransporterAction() {
        return drugBankTransporterAction;
    }

    public void setDrugBankTransporterAction(Collection<DrugBankTransporterAction> drugBankTransporterAction) {
        this.drugBankTransporterAction = drugBankTransporterAction;
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

    public Collection<DrugBankTransporterPolypeptide> getDrugBankTransporterPolypeptide() {
        return drugBankTransporterPolypeptide;
    }

    public void setDrugBankTransporterPolypeptide(Collection<DrugBankTransporterPolypeptide> drugBankTransporterPolypeptide) {
        this.drugBankTransporterPolypeptide = drugBankTransporterPolypeptide;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.wid);
        hash = 79 * hash + (int) (this.drugBankWID ^ (this.drugBankWID >>> 32));
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
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
        final DrugBankTransporter other = (DrugBankTransporter) obj;
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
        StringBuilder pData = new StringBuilder();

        if (drugBankTransporterRef != null) {
            for (DrugBankTransporterRef r : drugBankTransporterRef) {
                pData.append("\t").append(r).append("\n");
            }
        }

        if (drugBankTransporterAction != null) {
            for (DrugBankTransporterAction r : drugBankTransporterAction) {
                pData.append("\t").append(r).append("\n");
            }
        }

        if (drugBankTransporterPolypeptide != null) {
            for (DrugBankTransporterPolypeptide r : drugBankTransporterPolypeptide) {
                pData.append("\t").append(r).append("\n");
            }
        }

        return "DrugBankTransporter{"
                + "wid=" + wid
                + ", drugBankWID=" + drugBankWID
                + ", id=" + id
                + ", name=" + name
                + "}\n"
                + pData;
    }
}
