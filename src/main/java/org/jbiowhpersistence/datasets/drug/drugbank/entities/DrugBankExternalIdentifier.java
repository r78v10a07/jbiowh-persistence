package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankExternalIdentifier entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankExternalIdentifier")
@XmlRootElement
public class DrugBankExternalIdentifier implements Serializable {

    @Column(name = "Resource")
    private String resource;
    @Column(name = "Identifier")
    private String identifier;

    public DrugBankExternalIdentifier() {
    }

    public DrugBankExternalIdentifier(String resource, String identifier) {
        this.resource = resource;
        this.identifier = identifier;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.resource != null ? this.resource.hashCode() : 0);
        hash = 29 * hash + (this.identifier != null ? this.identifier.hashCode() : 0);
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
        final DrugBankExternalIdentifier other = (DrugBankExternalIdentifier) obj;
        if ((this.resource == null) ? (other.resource != null) : !this.resource.equals(other.resource)) {
            return false;
        }
        return !((this.identifier == null) ? (other.identifier != null) : !this.identifier.equals(other.identifier));
    }

    @Override
    public String toString() {
        return "DrugBankExternalIdentifier{" + "resource=" + resource + ", identifier=" + identifier + '}';
    }
}
