package org.jbiowhpersistence.datasets.protgroup.orthoxml.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * This class is the OrthoXMLGroupProperty entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "property")
@Embeddable
@Table(name = "OrthoXMLGroupProperty")
@XmlRootElement
public class OrthoXMLGroupProperty implements Serializable {

    @Basic(optional = false)
    @XmlAttribute(name = "name", required = true)
    @Column(name = "Name", length = 255)
    private String name;
    @Basic(optional = false)
    @XmlAttribute(name = "value")
    @XmlSchemaType(name = "anySimpleType")
    @Column(name = "Value")
    private String value;

    public OrthoXMLGroupProperty() {
    }

    public OrthoXMLGroupProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 47 * hash + (this.value != null ? this.value.hashCode() : 0);
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
        final OrthoXMLGroupProperty other = (OrthoXMLGroupProperty) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return !((this.value == null) ? (other.value != null) : !this.value.equals(other.value));
    }

    @Override
    public String toString() {
        return "OrthoXMLGroupProperty{" + "name=" + name + ", value=" + value + '}';
    }
}
