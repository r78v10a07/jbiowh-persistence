package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankAffectedOrganism entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankAffectedOrganism")
@XmlRootElement
public class DrugBankAffectedOrganism implements Serializable {

    @Lob
    @Column(name = "AffectedOrganisms")
    private String affectedOrganisms;

    public DrugBankAffectedOrganism() {
    }

    public DrugBankAffectedOrganism(String affectedOrganisms) {
        this.affectedOrganisms = affectedOrganisms;
    }

    public String getAffectedOrganisms() {
        return affectedOrganisms;
    }

    public void setAffectedOrganisms(String affectedOrganisms) {
        this.affectedOrganisms = affectedOrganisms;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.affectedOrganisms != null ? this.affectedOrganisms.hashCode() : 0);
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
        final DrugBankAffectedOrganism other = (DrugBankAffectedOrganism) obj;
        return !((this.affectedOrganisms == null) ? (other.affectedOrganisms != null) : !this.affectedOrganisms.equals(other.affectedOrganisms));
    }

    @Override
    public String toString() {
        return "DrugBankAffectedOrganism{" + "affectedOrganisms=" + affectedOrganisms + '}';
    }
}
