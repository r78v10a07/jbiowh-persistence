package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankAHFSCode entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankAHFSCode")
@XmlRootElement
public class DrugBankAHFSCode implements Serializable {

    @Column(name = "AHFSCodes")
    private String aHFSCodes;

    public DrugBankAHFSCode() {
    }

    public DrugBankAHFSCode(String aHFSCodes) {
        this.aHFSCodes = aHFSCodes;
    }

    public String getaHFSCodes() {
        return aHFSCodes;
    }

    public void setaHFSCodes(String aHFSCodes) {
        this.aHFSCodes = aHFSCodes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.aHFSCodes != null ? this.aHFSCodes.hashCode() : 0);
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
        final DrugBankAHFSCode other = (DrugBankAHFSCode) obj;
        return !((this.aHFSCodes == null) ? (other.aHFSCodes != null) : !this.aHFSCodes.equals(other.aHFSCodes));
    }

    @Override
    public String toString() {
        return "DrugBankAHFSCode{" + "aHFSCodes=" + aHFSCodes + '}';
    }
}
