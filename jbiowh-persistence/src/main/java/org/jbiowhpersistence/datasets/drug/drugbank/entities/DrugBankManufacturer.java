package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
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
    private boolean generic;

    public DrugBankManufacturer() {
    }

    public DrugBankManufacturer(String manufacturer, boolean generic) {
        this.manufacturer = manufacturer;
        this.generic = generic;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.manufacturer);
        hash = 31 * hash + (this.generic ? 1 : 0);
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
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        return this.generic == other.generic;
    }

    @Override
    public String toString() {
        return "DrugBankManufacturer{" + "manufacturer=" + manufacturer + ", generic=" + generic + '}';
    }
}
