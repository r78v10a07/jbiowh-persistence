package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the DrugBankCarrierPolypeptide entity
 *
 * $Author:$ $LastChangedDate:$ $LastChangedRevision:$
 *
 * @since Oct 28, 2014
 */
@Embeddable
@Table(name = "DrugBankCarrierPolypeptide")
@XmlRootElement
public class DrugBankCarrierPolypeptide implements Serializable {

    @Column(name = "Id")
    private String id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Source")
    private String source;

    public DrugBankCarrierPolypeptide() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.source);
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
        final DrugBankCarrierPolypeptide other = (DrugBankCarrierPolypeptide) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.source, other.source);
    }

    @Override
    public String toString() {
        return "DrugBankCarrierPolypeptide{" + "id=" + id + ", name=" + name + ", source=" + source + '}';
    }
}
