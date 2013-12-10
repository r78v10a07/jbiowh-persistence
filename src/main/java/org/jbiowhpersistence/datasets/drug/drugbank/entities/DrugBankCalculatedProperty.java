package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankCalculatedProperty entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankCalculatedProperty")
@XmlRootElement
public class DrugBankCalculatedProperty implements Serializable {

    @Column(name = "Kind")
    private String kind;
    @Column(name = "Value")
    private String value;
    @Column(name = "Source")
    private String source;

    public DrugBankCalculatedProperty() {
    }

    public DrugBankCalculatedProperty(String kind, String value, String source) {
        this.kind = kind;
        this.value = value;
        this.source = source;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.kind != null ? this.kind.hashCode() : 0);
        hash = 89 * hash + (this.value != null ? this.value.hashCode() : 0);
        hash = 89 * hash + (this.source != null ? this.source.hashCode() : 0);
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
        final DrugBankCalculatedProperty other = (DrugBankCalculatedProperty) obj;
        if ((this.kind == null) ? (other.kind != null) : !this.kind.equals(other.kind)) {
            return false;
        }
        if ((this.value == null) ? (other.value != null) : !this.value.equals(other.value)) {
            return false;
        }
        return !((this.source == null) ? (other.source != null) : !this.source.equals(other.source));
    }

    @Override
    public String toString() {
        return "DrugBankCalculatedProperty{" + "kind=" + kind + ", value=" + value + ", source=" + source + '}';
    }
}
