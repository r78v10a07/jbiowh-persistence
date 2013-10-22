package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankCarriersRef entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankCarriersRef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankCarriersRef.findAll", query = "SELECT d FROM DrugBankCarriersRef d"),
    @NamedQuery(name = "DrugBankCarriersRef.findByWid", query = "SELECT d FROM DrugBankCarriersRef d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankCarriersRef.findByDrugBankCarriersWID", query = "SELECT d FROM DrugBankCarriersRef d WHERE d.drugBankCarriersWID = :drugBankCarriersWID")})
public class DrugBankCarriersRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBankCarriers_WID")
    private long drugBankCarriersWID;
    @Lob
    @Column(name = "Cite")
    private String cite;
    @Lob
    @Column(name = "Link")
    private String link;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBankCarriers_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBankCarriers drugBankCarriers;

    public DrugBankCarriersRef() {
    }

    public DrugBankCarriersRef(Long wid) {
        this.wid = wid;
    }

    public DrugBankCarriersRef(Long wid, long drugBankCarriersWID) {
        this.wid = wid;
        this.drugBankCarriersWID = drugBankCarriersWID;
    }

    public DrugBankCarriers getDrugBankCarriers() {
        return drugBankCarriers;
    }

    public void setDrugBankCarriers(DrugBankCarriers drugBankCarriers) {
        this.drugBankCarriers = drugBankCarriers;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getDrugBankCarriersWID() {
        return drugBankCarriersWID;
    }

    public void setDrugBankCarriersWID(long drugBankCarriersWID) {
        this.drugBankCarriersWID = drugBankCarriersWID;
    }

    public String getCite() {
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankCarriersRef other = (DrugBankCarriersRef) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankCarriersWID != other.drugBankCarriersWID) {
            return false;
        }
        if (!Objects.equals(this.cite, other.cite)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
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
        return "DrugBankCarriersRef{" + "wid=" + wid + ", drugBankCarriersWID=" + drugBankCarriersWID + ", cite=" + cite + ", link=" + link + '}';
    }
}
