package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankPackagers entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankPackagers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankPackagers.findAll", query = "SELECT d FROM DrugBankPackagers d"),
    @NamedQuery(name = "DrugBankPackagers.findByWid", query = "SELECT d FROM DrugBankPackagers d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankPackagers.findByDrugBankWID", query = "SELECT d FROM DrugBankPackagers d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankPackagers.findByName", query = "SELECT d FROM DrugBankPackagers d WHERE d.name = :name")})
public class DrugBankPackagers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "URL")
    private String url;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankPackagers() {
    }

    public DrugBankPackagers(Long wid) {
        this.wid = wid;
    }

    public DrugBankPackagers(Long wid, long drugBankWID) {
        this.wid = wid;
        this.drugBankWID = drugBankWID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankPackagers other = (DrugBankPackagers) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
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
        return "DrugBankPackagers{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", name=" + name + ", url=" + url + '}';
    }
}
