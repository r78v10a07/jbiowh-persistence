package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the DrugBankPatent entity

 $Author: r78v10a07@gmail.com $
 $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankPatent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankPatent.findAll", query = "SELECT d FROM DrugBankPatent d"),
    @NamedQuery(name = "DrugBankPatent.findByWid", query = "SELECT d FROM DrugBankPatent d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankPatent.findByNumber", query = "SELECT d FROM DrugBankPatent d WHERE d.number = :number"),
    @NamedQuery(name = "DrugBankPatent.findByCountry", query = "SELECT d FROM DrugBankPatent d WHERE d.country = :country"),
    @NamedQuery(name = "DrugBankPatent.findByApproved", query = "SELECT d FROM DrugBankPatent d WHERE d.approved = :approved"),
    @NamedQuery(name = "DrugBankPatent.findByExpires", query = "SELECT d FROM DrugBankPatent d WHERE d.expires = :expires")})
public class DrugBankPatent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Number")
    private long number;
    @Column(name = "Country")
    private String country;
    @Column(name = "Approved")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approved;
    @Column(name = "Expires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expires;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "drugBankPatent")
    private Set<DrugBank> drugBank;

    public DrugBankPatent() {
    }

    public DrugBankPatent(Long wid) {
        this.wid = wid;
    }

    public DrugBankPatent(Long wid, long number) {
        this.wid = wid;
        this.number = number;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBank() {
        return drugBank;
    }

    public void setDrugBank(Set<DrugBank> drugBank) {
        this.drugBank = drugBank;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getApproved() {
        return approved;
    }

    public void setApproved(Date approved) {
        this.approved = approved;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankPatent other = (DrugBankPatent) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.approved, other.approved)) {
            return false;
        }
        return Objects.equals(this.expires, other.expires);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "DrugBankPatent{" + "wid=" + wid + ", number=" + number + ", country=" + country + ", approved=" + approved + ", expires=" + expires + '}';
    }
}
