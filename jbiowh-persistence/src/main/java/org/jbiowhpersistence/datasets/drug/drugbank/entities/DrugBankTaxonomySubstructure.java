package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTaxonomySubstructure entity

 $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankTaxonomySubstructure")
@XmlRootElement
public class DrugBankTaxonomySubstructure implements Serializable {

    @Column(name = "Substructure")
    private String substructure;
    @Column(name = "Class")
    private String class1;

    public DrugBankTaxonomySubstructure() {
    }

    public DrugBankTaxonomySubstructure(String substructure, String class1) {
        this.substructure = substructure;
        this.class1 = class1;
    }

    public String getSubstructure() {
        return substructure;
    }

    public void setSubstructure(String substructure) {
        this.substructure = substructure;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.substructure != null ? this.substructure.hashCode() : 0);
        hash = 23 * hash + (this.class1 != null ? this.class1.hashCode() : 0);
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
        final DrugBankTaxonomySubstructure other = (DrugBankTaxonomySubstructure) obj;
        if ((this.substructure == null) ? (other.substructure != null) : !this.substructure.equals(other.substructure)) {
            return false;
        }
        return !((this.class1 == null) ? (other.class1 != null) : !this.class1.equals(other.class1));
    }

    @Override
    public String toString() {
        return "DrugBankTaxonomySubstructure{" + "substructure=" + substructure + ", class1=" + class1 + '}';
    }
}
