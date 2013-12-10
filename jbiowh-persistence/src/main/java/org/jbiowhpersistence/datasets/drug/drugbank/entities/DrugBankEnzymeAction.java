package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankEnzymeActions entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankEnzymeAction")
@XmlRootElement
public class DrugBankEnzymeAction implements Serializable {

    @Column(name = "Action")
    private String action;

    public DrugBankEnzymeAction() {
    }

    public DrugBankEnzymeAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.action != null ? this.action.hashCode() : 0);
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
        final DrugBankEnzymeAction other = (DrugBankEnzymeAction) obj;
        return !((this.action == null) ? (other.action != null) : !this.action.equals(other.action));
    }

    @Override
    public String toString() {
        return "DrugBankEnzymeAction{" + "action=" + action + '}';
    }
}
