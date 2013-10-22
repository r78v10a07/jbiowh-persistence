package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTargetsRef entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankTargetsRef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankTargetsRef.findAll", query = "SELECT d FROM DrugBankTargetsRef d"),
    @NamedQuery(name = "DrugBankTargetsRef.findByWid", query = "SELECT d FROM DrugBankTargetsRef d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankTargetsRef.findByDrugBankTargetsWID", query = "SELECT d FROM DrugBankTargetsRef d WHERE d.drugBankTargetsWID = :drugBankTargetsWID")})
public class DrugBankTargetsRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBankTargets_WID")
    private long drugBankTargetsWID;
    @Lob
    @Column(name = "Cite")
    private String cite;
    @Lob
    @Column(name = "Link")
    private String link;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBankTargets_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBankTargets drugBankTargets;

    public DrugBankTargetsRef() {
    }

    public DrugBankTargetsRef(Long wid) {
        this.wid = wid;
    }

    public DrugBankTargetsRef(Long wid, long drugBankTargetsWID) {
        this.wid = wid;
        this.drugBankTargetsWID = drugBankTargetsWID;
    }

    public DrugBankTargets getDrugBankTargets() {
        return drugBankTargets;
    }

    public void setDrugBankTargets(DrugBankTargets drugBankTargets) {
        this.drugBankTargets = drugBankTargets;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getDrugBankTargetsWID() {
        return drugBankTargetsWID;
    }

    public void setDrugBankTargetsWID(long drugBankTargetsWID) {
        this.drugBankTargetsWID = drugBankTargetsWID;
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
        final DrugBankTargetsRef other = (DrugBankTargetsRef) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankTargetsWID != other.drugBankTargetsWID) {
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
        return "DrugBankTargetsRef{" + "wid=" + wid + ", drugBankTargetsWID=" + drugBankTargetsWID + ", cite=" + cite + ", link=" + link + '}';
    }
}
