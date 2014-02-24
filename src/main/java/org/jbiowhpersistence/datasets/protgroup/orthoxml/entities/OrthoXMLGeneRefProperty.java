package org.jbiowhpersistence.datasets.protgroup.orthoxml.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the OrthoXMLGeneRefProperty entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@Embeddable
@Table(name = "OrthoXMLGeneRefProperty")
@XmlRootElement
public class OrthoXMLGeneRefProperty implements Serializable {

    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Value")
    private String value_;

    public OrthoXMLGeneRefProperty() {
    }

    public OrthoXMLGeneRefProperty(String name, String value_) {
        this.name = name;
        this.value_ = value_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue_() {
        return value_;
    }

    public void setValue_(String value_) {
        this.value_ = value_;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.value_ != null ? this.value_.hashCode() : 0);
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
        final OrthoXMLGeneRefProperty other = (OrthoXMLGeneRefProperty) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return !((this.value_ == null) ? (other.value_ != null) : !this.value_.equals(other.value_));
    }

    @Override
    public String toString() {
        return "OrthoXMLGeneRefProperty{" + "name=" + name + ", value_=" + value_ + '}';
    }
}
