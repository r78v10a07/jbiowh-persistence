package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankExperimentalProperties entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Entity
@Table(name = "DrugBankExperimentalProperties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankExperimentalProperties.findAll", query = "SELECT d FROM DrugBankExperimentalProperties d"),
    @NamedQuery(name = "DrugBankExperimentalProperties.findByWid", query = "SELECT d FROM DrugBankExperimentalProperties d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBankExperimentalProperties.findByDrugBankWID", query = "SELECT d FROM DrugBankExperimentalProperties d WHERE d.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankExperimentalProperties.findByKind", query = "SELECT d FROM DrugBankExperimentalProperties d WHERE d.kind = :kind"),
    @NamedQuery(name = "DrugBankExperimentalProperties.findByValue", query = "SELECT d FROM DrugBankExperimentalProperties d WHERE d.value = :value"),
    @NamedQuery(name = "DrugBankExperimentalProperties.findBySource", query = "SELECT d FROM DrugBankExperimentalProperties d WHERE d.source = :source")})
public class DrugBankExperimentalProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Column(name = "Kind")
    private String kind;
    @Column(name = "Value")
    private String value;
    @Column(name = "Source")
    private String source;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankExperimentalProperties() {
    }

    public DrugBankExperimentalProperties(Long wid) {
        this.wid = wid;
    }

    public DrugBankExperimentalProperties(Long wid, long drugBankWID) {
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankExperimentalProperties other = (DrugBankExperimentalProperties) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (!Objects.equals(this.kind, other.kind)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
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
        return "DrugBankExperimentalProperties{" + "wid=" + wid + ", drugBankWID=" + drugBankWID + ", kind=" + kind + ", value=" + value + ", source=" + source + '}';
    }
}
