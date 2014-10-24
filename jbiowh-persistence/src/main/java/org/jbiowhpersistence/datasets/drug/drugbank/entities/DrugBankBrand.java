package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
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
    @Column(name = "Company")
    private String company;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.brand);
        hash = 47 * hash + Objects.hashCode(this.company);
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
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        return Objects.equals(this.company, other.company);
    }

    @Override
    public String toString() {
        return "DrugBankBrand{" + "brand=" + brand + "company=" + company + '}';
    }
}
