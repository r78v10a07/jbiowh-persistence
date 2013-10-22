package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankEnzymesRef entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankEnzymesRef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankEnzymesRef.findAll", query = "SELECT d FROM DrugBankEnzymesRef d"),
    @NamedQuery(name = "DrugBankEnzymesRef.findByWid", query = "SELECT d FROM DrugBankEnzymesRef d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankEnzymesRef.findByDrugBankEnzymesWID", query = "SELECT d FROM DrugBankEnzymesRef d WHERE d.drugBankEnzymesWID = :drugBankEnzymesWID")})
public class DrugBankEnzymesRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBankEnzymes_WID")
    private long drugBankEnzymesWID;
    @Lob
    @Column(name = "Cite")
    private String cite;
    @Lob
    @Column(name = "Link")
    private String link;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBankEnzymes_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBankEnzymes drugBankEnzymes;

    public DrugBankEnzymesRef() {
    }

    public DrugBankEnzymesRef(Long wid) {
        this.wid = wid;
    }

    public DrugBankEnzymesRef(Long wid, long drugBankEnzymesWID) {
        this.wid = wid;
        this.drugBankEnzymesWID = drugBankEnzymesWID;
    }

    public DrugBankEnzymes getDrugBankEnzymes() {
        return drugBankEnzymes;
    }

    public void setDrugBankEnzymes(DrugBankEnzymes drugBankEnzymes) {
        this.drugBankEnzymes = drugBankEnzymes;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getDrugBankEnzymesWID() {
        return drugBankEnzymesWID;
    }

    public void setDrugBankEnzymesWID(long drugBankEnzymesWID) {
        this.drugBankEnzymesWID = drugBankEnzymesWID;
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
        final DrugBankEnzymesRef other = (DrugBankEnzymesRef) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankEnzymesWID != other.drugBankEnzymesWID) {
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
        return "DrugBankEnzymesRef{" + "wid=" + wid + ", drugBankEnzymesWID=" + drugBankEnzymesWID + ", cite=" + cite + ", link=" + link + '}';
    }
}
