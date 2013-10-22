package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTransportersRef entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTransportersRef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTransportersRef.findAll", query = "SELECT d FROM DrugBankTransportersRef d"),
    @NamedQuery(name = "DrugBankTransportersRef.findByWid", query = "SELECT d FROM DrugBankTransportersRef d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTransportersRef.findByDrugBankTransportersWID", query = "SELECT d FROM DrugBankTransportersRef d WHERE d.drugBankTransportersWID = :drugBankTransportersWID")})
public class DrugBankTransportersRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBankTransporters_WID")
    private long drugBankTransportersWID;
    @Lob
    @Column(name = "Cite")
    private String cite;
    @Lob
    @Column(name = "Link")
    private String link;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBankTransporters_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBankTransporters drugBankTransporters;

    public DrugBankTransportersRef() {
    }

    public DrugBankTransportersRef(Long wid) {
        this.wid = wid;
    }

    public DrugBankTransportersRef(Long wid, long drugBankTransportersWID) {
        this.wid = wid;
        this.drugBankTransportersWID = drugBankTransportersWID;
    }

    public DrugBankTransporters getDrugBankTransporters() {
        return drugBankTransporters;
    }

    public void setDrugBankTransporters(DrugBankTransporters drugBankTransporters) {
        this.drugBankTransporters = drugBankTransporters;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getDrugBankTransportersWID() {
        return drugBankTransportersWID;
    }

    public void setDrugBankTransportersWID(long drugBankTransportersWID) {
        this.drugBankTransportersWID = drugBankTransportersWID;
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
        final DrugBankTransportersRef other = (DrugBankTransportersRef) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankTransportersWID != other.drugBankTransportersWID) {
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
        return "DrugBankTransportersRef{" + "wid=" + wid + ", drugBankTransportersWID=" + drugBankTransportersWID + ", cite=" + cite + ", link=" + link + '}';
    }
}
