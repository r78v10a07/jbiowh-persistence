package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankManufacturer entity

 $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankManufacturer")
@XmlRootElement
public class DrugBankManufacturer implements Serializable {

    @Column(name = "Manufacturer")
    private String manufacturer;
    @Column(name = "Generic")
    private String generic;

    public DrugBankManufacturer() {
    }

    public DrugBankManufacturer(String manufacturer, String generic) {
        this.manufacturer = manufacturer;
        this.generic = generic;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.manufacturer != null ? this.manufacturer.hashCode() : 0);
        hash = 23 * hash + (this.generic != null ? this.generic.hashCode() : 0);
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
        final DrugBankManufacturer other = (DrugBankManufacturer) obj;
        if ((this.manufacturer == null) ? (other.manufacturer != null) : !this.manufacturer.equals(other.manufacturer)) {
            return false;
        }
        return !((this.generic == null) ? (other.generic != null) : !this.generic.equals(other.generic));
    }

    @Override
    public String toString() {
        return "DrugBankManufacturer{" + "manufacturer=" + manufacturer + ", generic=" + generic + '}';
    }
}
