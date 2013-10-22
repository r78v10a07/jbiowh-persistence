package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the DrugBank Drug Interactions entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2012
 */
@Entity
@Table(name = "DrugBankDrugInteractions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBankDrugInteractions.findAll", query = "SELECT d FROM DrugBankDrugInteractions d"),
    @NamedQuery(name = "DrugBankDrugInteractions.findByDrugBankWID", query = "SELECT d FROM DrugBankDrugInteractions d WHERE d.drugBankDrugInteractionsPK.drugBankWID = :drugBankWID"),
    @NamedQuery(name = "DrugBankDrugInteractions.findByDrug", query = "SELECT d FROM DrugBankDrugInteractions d WHERE d.drugBankDrugInteractionsPK.drug = :drug")})
public class DrugBankDrugInteractions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DrugBankDrugInteractionsPK drugBankDrugInteractionsPK;
    @Lob
    @Column(name = "Description")
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DrugBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private DrugBank drugBank;

    public DrugBankDrugInteractions() {
    }

    public DrugBankDrugInteractions(DrugBankDrugInteractionsPK drugBankDrugInteractionsPK) {
        this.drugBankDrugInteractionsPK = drugBankDrugInteractionsPK;
    }

    public DrugBankDrugInteractions(long drugBankWID, long drug) {
        this.drugBankDrugInteractionsPK = new DrugBankDrugInteractionsPK(drugBankWID, drug);
    }

    public DrugBank getDrugBank() {
        return drugBank;
    }

    public void setDrugBank(DrugBank drugBank) {
        this.drugBank = drugBank;
    }

    public DrugBankDrugInteractionsPK getDrugBankDrugInteractionsPK() {
        return drugBankDrugInteractionsPK;
    }

    public void setDrugBankDrugInteractionsPK(DrugBankDrugInteractionsPK drugBankDrugInteractionsPK) {
        this.drugBankDrugInteractionsPK = drugBankDrugInteractionsPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankDrugInteractions other = (DrugBankDrugInteractions) obj;
        if (!Objects.equals(this.drugBankDrugInteractionsPK, other.drugBankDrugInteractionsPK)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (drugBankDrugInteractionsPK != null ? drugBankDrugInteractionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "DrugBankDrugInteractions{" + "drugBankDrugInteractionsPK=" + drugBankDrugInteractionsPK + ", description=" + description + '}';
    }
}
