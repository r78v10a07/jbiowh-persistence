package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the DrugBank Drug Interactions entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jun 28, 2012
 */
@Embeddable
@Table(name = "DrugBankDrugInteraction")
@XmlRootElement
public class DrugBankDrugInteraction implements Serializable {

    @Basic(optional = false)
    @Column(name = "Drug")
    private long drug;
    @Lob
    @Column(name = "Description")
    private String description;

    public DrugBankDrugInteraction() {
    }

    public DrugBankDrugInteraction(long drug, String description) {
        this.drug = drug;
        this.description = description;
    }

    public long getDrug() {
        return drug;
    }

    public void setDrug(long drug) {
        this.drug = drug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.drug ^ (this.drug >>> 32));
        hash = 29 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBankDrugInteraction other = (DrugBankDrugInteraction) obj;
        if (this.drug != other.drug) {
            return false;
        }
        return !((this.description == null) ? (other.description != null) : !this.description.equals(other.description));
    }

    @Override
    public String toString() {
        return "DrugBankDrugInteraction{" + "drug=" + drug + ", description=" + description + '}';
    }
}
