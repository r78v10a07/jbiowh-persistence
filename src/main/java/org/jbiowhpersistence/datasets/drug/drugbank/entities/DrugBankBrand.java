package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankBrand entity

 $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankBrand")
@XmlRootElement
public class DrugBankBrand implements Serializable {

    @Column(name = "Brand")
    private String brand;

    public DrugBankBrand() {
    }

    public DrugBankBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.brand != null ? this.brand.hashCode() : 0);
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
        final DrugBankBrand other = (DrugBankBrand) obj;
        return !((this.brand == null) ? (other.brand != null) : !this.brand.equals(other.brand));
    }

    @Override
    public String toString() {
        return "DrugBankBrand{" + "brand=" + brand + '}';
    }
}
