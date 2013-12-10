package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankGroup entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankGroup")
@XmlRootElement
public class DrugBankGroup implements Serializable {

    @Column(name = "GroupName")
    private String groupName;

    public DrugBankGroup() {
    }

    public DrugBankGroup(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.groupName != null ? this.groupName.hashCode() : 0);
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
        final DrugBankGroup other = (DrugBankGroup) obj;
        return !((this.groupName == null) ? (other.groupName != null) : !this.groupName.equals(other.groupName));
    }

    @Override
    public String toString() {
        return "DrugBankGroup{groupName=" + groupName + '}';
    }
}
