package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankSecondAccessionNumber entity

 $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankSecondAccessionNumber")
@XmlRootElement
public class DrugBankSecondAccessionNumber implements Serializable {

    @Column(name = "AccessionNumber")
    private String accessionNumber;

    public DrugBankSecondAccessionNumber() {
    }

    public DrugBankSecondAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.accessionNumber != null ? this.accessionNumber.hashCode() : 0);
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
        final DrugBankSecondAccessionNumber other = (DrugBankSecondAccessionNumber) obj;
        return !((this.accessionNumber == null) ? (other.accessionNumber != null) : !this.accessionNumber.equals(other.accessionNumber));
    }

    @Override
    public String toString() {
        return "DrugBankSecondAccessionNumber{accessionNumber=" + accessionNumber + '}';
    }
}
