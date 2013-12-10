package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTaxonomy entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankTaxonomy")
@XmlRootElement
public class DrugBankTaxonomy implements Serializable {

    @Column(name = "Kingdom")
    private String kingdom;

    public DrugBankTaxonomy() {
    }

    public DrugBankTaxonomy(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.kingdom != null ? this.kingdom.hashCode() : 0);
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
        final DrugBankTaxonomy other = (DrugBankTaxonomy) obj;
        return !((this.kingdom == null) ? (other.kingdom != null) : !this.kingdom.equals(other.kingdom));
    }

    @Override
    public String toString() {
        return "DrugBankTaxonomy{" + "kingdom=" + kingdom + '}';
    }
}
