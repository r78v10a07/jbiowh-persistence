package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the DrugBankEnzyme entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankEnzyme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankEnzyme.findAll", query = "SELECT d FROM DrugBankEnzyme d"),
    @NamedQuery(name = "DrugBankEnzyme.findByWid", query = "SELECT d FROM DrugBankEnzyme d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankEnzyme.findByDrugBankWID", query = "SELECT d FROM DrugBankEnzyme d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankEnzyme.findById", query = "SELECT d FROM DrugBankEnzyme d WHERE d.id = :id"),
    @NamedQuery(name = "DrugBankEnzyme.findByName", query = "SELECT d FROM DrugBankEnzyme d WHERE d.name = :name")})
public class DrugBankEnzyme implements Serializable {

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
            name = "DrugBankEnzymeRef",
            joinColumns
            = @JoinColumn(name = "DrugBankEnzyme_WID"))
    @XmlElementWrapper(name = "DrugBankEnzymeRefs")
    private Collection<DrugBankEnzymeRef> drugBankEnzymeRef;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankEnzymeAction",
            joinColumns
            = @JoinColumn(name = "DrugBankEnzyme_WID"))
    @XmlElementWrapper(name = "DrugBankEnzymeActions")
    private Collection<DrugBankEnzymeAction> drugBankEnzymeAction;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankEnzymePolypeptide",
            joinColumns
            = @JoinColumn(name = "DrugBankEnzyme_WID"))
    @XmlElementWrapper(name = "DrugBankEnzymePolypeptides")
    private Collection<DrugBankEnzymePolypeptide> drugBankEnzymePolypeptide;

    public DrugBankEnzyme() {
    }

    public DrugBankEnzyme(Long wid) {
        this.wid = wid;
    }

    public DrugBankEnzyme(Long wid, long drugBankWID) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
    }

    public Collection<DrugBankEnzymeRef> getDrugBankEnzymeRef() {
        return drugBankEnzymeRef;
    }

    public void setDrugBankEnzymeRef(Collection<DrugBankEnzymeRef> drugBankEnzymeRef) {
        this.drugBankEnzymeRef = drugBankEnzymeRef;
    }

    public Collection<DrugBankEnzymeAction> getDrugBankEnzymeAction() {
        return drugBankEnzymeAction;
    }

    public void setDrugBankEnzymeAction(Collection<DrugBankEnzymeAction> drugBankEnzymeAction) {
        this.drugBankEnzymeAction = drugBankEnzymeAction;
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

    public Collection<DrugBankEnzymePolypeptide> getDrugBankEnzymePolypeptide() {
        return drugBankEnzymePolypeptide;
    }

    public void setDrugBankEnzymePolypeptide(Collection<DrugBankEnzymePolypeptide> drugBankEnzymePolypeptide) {
        this.drugBankEnzymePolypeptide = drugBankEnzymePolypeptide;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.wid);
        hash = 73 * hash + (int) (this.drugBankWID ^ (this.drugBankWID >>> 32));
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.name);
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
        final DrugBankEnzyme other = (DrugBankEnzyme) obj;
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

        if (drugBankEnzymeAction != null) {
            for (DrugBankEnzymeAction a : drugBankEnzymeAction) {
                pData.append("\t").append(a).append("\n");
            }
        }

        if (drugBankEnzymeRef != null) {
            for (DrugBankEnzymeRef a : drugBankEnzymeRef) {
                pData.append("\t").append(a).append("\n");
            }
        }

        if (drugBankEnzymePolypeptide != null) {
            for (DrugBankEnzymePolypeptide r : drugBankEnzymePolypeptide) {
                pData.append("\t").append(r).append("\n");
            }
        }

        return "DrugBankEnzyme{"
                + "wid=" + wid
                + ", drugBankWID=" + drugBankWID
                + ", id=" + id
                + ", name=" + name
                + "}\n"
                + pData;
    }
}
