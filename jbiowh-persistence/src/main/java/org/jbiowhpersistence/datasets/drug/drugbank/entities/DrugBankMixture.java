package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankMixture entity

 $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankMixture")
@XmlRootElement
public class DrugBankMixture implements Serializable {

    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "Ingredients")
    private String ingredients;

    public DrugBankMixture() {
    }

    public DrugBankMixture(String name, String ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + (this.ingredients != null ? this.ingredients.hashCode() : 0);
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
        final DrugBankMixture other = (DrugBankMixture) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return !((this.ingredients == null) ? (other.ingredients != null) : !this.ingredients.equals(other.ingredients));
    }

    @Override
    public String toString() {
        return "DrugBankMixture{" + "name=" + name + ", ingredients=" + ingredients + '}';
    }
}
