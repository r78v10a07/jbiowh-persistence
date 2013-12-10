package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankDosage entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankDosage")
@XmlRootElement
public class DrugBankDosage implements Serializable {

    @Column(name = "Form")
    private String form;
    @Column(name = "Route")
    private String route;
    @Column(name = "Strength")
    private String strength;

    public DrugBankDosage() {
    }

    public DrugBankDosage(String form, String route, String strength) {
        this.form = form;
        this.route = route;
        this.strength = strength;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.form != null ? this.form.hashCode() : 0);
        hash = 79 * hash + (this.route != null ? this.route.hashCode() : 0);
        hash = 79 * hash + (this.strength != null ? this.strength.hashCode() : 0);
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
        final DrugBankDosage other = (DrugBankDosage) obj;
        if ((this.form == null) ? (other.form != null) : !this.form.equals(other.form)) {
            return false;
        }
        if ((this.route == null) ? (other.route != null) : !this.route.equals(other.route)) {
            return false;
        }
        return !((this.strength == null) ? (other.strength != null) : !this.strength.equals(other.strength));
    }

    @Override
    public String toString() {
        return "DrugBankDosage{" + "form=" + form + ", route=" + route + ", strength=" + strength + '}';
    }

}
