package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankExternalIdentifiers entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankExternalIdentifiers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankExternalIdentifiers.findAll", query = "SELECT d FROM DrugBankExternalIdentifiers d"),
    @NamedQuery(name = "DrugBankExternalIdentifiers.findByWid", query = "SELECT d FROM DrugBankExternalIdentifiers d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankExternalIdentifiers.findByDrugBankWID", query = "SELECT d FROM DrugBankExternalIdentifiers d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankExternalIdentifiers.findByResource", query = "SELECT d FROM DrugBankExternalIdentifiers d WHERE d.resource = :resource"),
    @NamedQuery(name = "DrugBankExternalIdentifiers.findByIdentifier", query = "SELECT d FROM DrugBankExternalIdentifiers d WHERE d.identifier = :identifier")})
public class DrugBankExternalIdentifiers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Column(name = "Resource")
    private String resource;
    @Column(name = "Identifier")
    private String identifier;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankExternalIdentifiers() {
    }

    public DrugBankExternalIdentifiers(Long wid) {
        this.wid = wid;
    }

    public DrugBankExternalIdentifiers(Long wid, long drugBankWID) {
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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankExternalIdentifiers other = (DrugBankExternalIdentifiers) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.resource, other.resource)) {
            return false;
        }
        if (!Objects.equals(this.identifier, other.identifier)) {
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
        return "DrugBankExternalIdentifiers{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", resource=" + resource + ", identifier=" + identifier + '}';
    }
}
