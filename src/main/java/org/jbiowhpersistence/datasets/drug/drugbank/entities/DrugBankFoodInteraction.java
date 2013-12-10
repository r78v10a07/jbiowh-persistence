package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankFoodInteraction entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankFoodInteraction")
@XmlRootElement
public class DrugBankFoodInteraction implements Serializable {

    @Lob
    @Column(name = "FoodInteractions")
    private String foodInteractions;

    public DrugBankFoodInteraction() {
    }

    public DrugBankFoodInteraction(String foodInteractions) {
        this.foodInteractions = foodInteractions;
    }

    public String getFoodInteractions() {
        return foodInteractions;
    }

    public void setFoodInteractions(String foodInteractions) {
        this.foodInteractions = foodInteractions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.foodInteractions != null ? this.foodInteractions.hashCode() : 0);
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
        final DrugBankFoodInteraction other = (DrugBankFoodInteraction) obj;
        return !((this.foodInteractions == null) ? (other.foodInteractions != null) : !this.foodInteractions.equals(other.foodInteractions));
    }

    @Override
    public String toString() {
        return "DrugBankFoodInteraction{" + "foodInteractions=" + foodInteractions + '}';
    }
}
