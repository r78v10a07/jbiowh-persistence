package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTransporters entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTransporters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTransporters.findAll", query = "SELECT d FROM DrugBankTransporters d"),
    @NamedQuery(name = "DrugBankTransporters.findByWid", query = "SELECT d FROM DrugBankTransporters d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTransporters.findByDrugBankWID", query = "SELECT d FROM DrugBankTransporters d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankTransporters.findByPartner", query = "SELECT d FROM DrugBankTransporters d WHERE d.partner = :partner"),
    @NamedQuery(name = "DrugBankTransporters.findByPosition", query = "SELECT d FROM DrugBankTransporters d WHERE d.position = :position")})
public class DrugBankTransporters implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBankTransporters")
    private Set<DrugBankTransportersRef> drugBankTransportersRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBankTransporters")
    private Set<DrugBankTransportersActions> drugBankTransportersActions;

    public DrugBankTransporters() {
    }

    public DrugBankTransporters(Long wid) {
        this.wid = wid;
    }

    public DrugBankTransporters(Long wid, long drugBankWID, int partner) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
        this.partner = partner;
    }

    public Set<DrugBankTransportersActions> getDrugBankTransportersActions() {
        return drugBankTransportersActions;
    }

    public void setDrugBankTransportersActions(Set<DrugBankTransportersActions> drugBankTransportersActions) {
        this.drugBankTransportersActions = drugBankTransportersActions;
    }

    public Set<DrugBankTransportersRef> getDrugBankTransportersRef() {
        return drugBankTransportersRef;
    }

    public void setDrugBankTransportersRef(Set<DrugBankTransportersRef> drugBankTransportersRef) {
        this.drugBankTransportersRef = drugBankTransportersRef;
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
        final DrugBankTransporters other = (DrugBankTransporters) obj;
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

        if (!getDrugBankTransportersRef().isEmpty()) {
            it = getDrugBankTransportersRef().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankTransportersActions().isEmpty()) {
            it = getDrugBankTransportersActions().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        return "DrugBankTransporters{"
                + "wid=" + wid
                + ", drugBankWID=" + drugBankWID
                + ", partner=" + partner
                + ", position=" + position + "}\n"
                + pData;
    }
}
