package org.jbiowhpersistence.datasets.protgroup.orthoxml.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This class is the OrthoXMLGeneRefScore entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "score")
@Embeddable
@Table(name = "OrthoXMLGeneRefScore")
@XmlRootElement
public class OrthoXMLGeneRefScore implements Serializable {

    @Basic(optional = false)
    @Column(name = "Id", length = 255)
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    private String id;
    @Basic(optional = false)
    @Column(name = "Value", precision = 20, scale = 10)
    @XmlAttribute(name = "value", required = true)
    private BigDecimal value_;

    public OrthoXMLGeneRefScore() {
    }

    public OrthoXMLGeneRefScore(String id, BigDecimal value) {
        this.id = id;
        this.value_ = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValue_() {
        return value_;
    }

    public void setValue_(BigDecimal value_) {
        this.value_ = value_;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 71 * hash + (this.value_ != null ? this.value_.hashCode() : 0);
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
        final OrthoXMLGeneRefScore other = (OrthoXMLGeneRefScore) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return this.value_ == other.value_ || (this.value_ != null && this.value_.equals(other.value_));
    }

    @Override
    public String toString() {
        return "OrthoXMLGeneRefScore{" + "id=" + id + ", value=" + value_ + '}';
    }
}
