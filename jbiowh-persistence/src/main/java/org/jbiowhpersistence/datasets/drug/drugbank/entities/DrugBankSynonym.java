package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankSynonym entity

 $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankSynonym")
@XmlRootElement
public class DrugBankSynonym implements Serializable {

    @Column(name = "Synonym")
    private String synonym;

    public DrugBankSynonym() {
    }

    public DrugBankSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.synonym != null ? this.synonym.hashCode() : 0);
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
        final DrugBankSynonym other = (DrugBankSynonym) obj;
        return !((this.synonym == null) ? (other.synonym != null) : !this.synonym.equals(other.synonym));
    }

    @Override
    public String toString() {
        return "DrugBankSynonym{" + "synonym=" + synonym + '}';
    }
}
