package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankATCCode entity

 $Author: r78v10a07@gmail.com $
 $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 $LastChangedRevision: 270 $
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankATCCode")
@XmlRootElement
public class DrugBankATCCode implements Serializable {

    @Column(name = "ATCCode")
    private String aTCCode;

    public DrugBankATCCode() {
    }

    public DrugBankATCCode(String aTCCode) {
        this.aTCCode = aTCCode;
    }

    public String getaTCCode() {
        return aTCCode;
    }

    public void setaTCCode(String aTCCode) {
        this.aTCCode = aTCCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.aTCCode != null ? this.aTCCode.hashCode() : 0);
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
        final DrugBankATCCode other = (DrugBankATCCode) obj;
        return !((this.aTCCode == null) ? (other.aTCCode != null) : !this.aTCCode.equals(other.aTCCode));
    }

    @Override
    public String toString() {
        return "DrugBankATCCode{" + "aTCCode=" + aTCCode + '}';
    }    
}
