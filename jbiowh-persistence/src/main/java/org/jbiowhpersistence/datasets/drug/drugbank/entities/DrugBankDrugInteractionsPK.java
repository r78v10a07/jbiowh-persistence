package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the DrugBankDrugInteractions PK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2012
 */
@Embeddable
public class DrugBankDrugInteractionsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "DrugBank_WID")
    private long drugBankWID;
    @Basic(optional = false)
    @Column(name = "Drug")
    private long drug;

    public DrugBankDrugInteractionsPK() {
    }

    public DrugBankDrugInteractionsPK(long drugBankWID, long drug) {
        this.drugBankWID = drugBankWID;
        this.drug = drug;
    }

    public long getDrugBankWID() {
        return drugBankWID;
    }

    public void setDrugBankWID(long drugBankWID) {
        this.drugBankWID = drugBankWID;
    }

    public long getDrug() {
        return drug;
    }

    public void setDrug(long drug) {
        this.drug = drug;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) drugBankWID;
        hash += (int) drug;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DrugBankDrugInteractionsPK)) {
            return false;
        }
        DrugBankDrugInteractionsPK other = (DrugBankDrugInteractionsPK) object;
        if (this.drugBankWID != other.drugBankWID) {
            return false;
        }
        if (this.drug != other.drug) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DrugBankDrugInteractionsPK{" + "drugBankWID=" + drugBankWID + ", drug=" + drug + '}';
    }
}
