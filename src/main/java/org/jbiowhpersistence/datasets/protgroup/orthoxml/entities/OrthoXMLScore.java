package org.jbiowhpersistence.datasets.protgroup.orthoxml.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This class is the OrthoXMLScore entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@Embeddable
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scoreDef")
@Table(name = "OrthoXMLScore")
public class OrthoXMLScore implements Serializable {

    @Basic(optional = false)
    @Column(name = "Id", length = 255)
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    private String id;
    @Basic(optional = false)
    @XmlAttribute(name = "desc", required = true)
    @XmlSchemaType(name = "anySimpleType")
    @Column(name = "Desc_")
    private String desc_;

    public OrthoXMLScore() {
    }

    public OrthoXMLScore(String id, String desc_) {
        this.id = id;
        this.desc_ = desc_;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc_() {
        return desc_;
    }

    public void setDesc_(String desc_) {
        this.desc_ = desc_;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.desc_ != null ? this.desc_.hashCode() : 0);
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
        final OrthoXMLScore other = (OrthoXMLScore) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return !((this.desc_ == null) ? (other.desc_ != null) : !this.desc_.equals(other.desc_));
    }

    @Override
    public String toString() {
        return "OrthoXMLScore{" + "id=" + id + ", desc_=" + desc_ + '}';
    }
}
