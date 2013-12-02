package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is Protein DB Reference Property entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinDBReferenceProperty")
@XmlRootElement
public class ProteinDBReferenceProperty implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinDBReference_WID")
    private long proteinDBReferenceWID;
    @Basic(optional = false)
    @Column(name = "AttribType")
    private String attribType;
    @Column(name = "AttribValue")
    private String attribValue;

    public ProteinDBReferenceProperty() {
    }

    public ProteinDBReferenceProperty(long proteinDBReferenceWID, String attribType, String attribValue) {
        this.proteinDBReferenceWID = proteinDBReferenceWID;
        this.attribType = attribType;
        this.attribValue = attribValue;
    }

    public long getProteinDBReferenceWID() {
        return proteinDBReferenceWID;
    }

    public void setProteinDBReferenceWID(long proteinDBReferenceWID) {
        this.proteinDBReferenceWID = proteinDBReferenceWID;
    }

    public String getAttribType() {
        return attribType;
    }

    public void setAttribType(String attribType) {
        this.attribType = attribType;
    }

    public String getAttribValue() {
        return attribValue;
    }

    public void setAttribValue(String attribValue) {
        this.attribValue = attribValue;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.proteinDBReferenceWID ^ (this.proteinDBReferenceWID >>> 32));
        hash = 53 * hash + (this.attribType != null ? this.attribType.hashCode() : 0);
        hash = 53 * hash + (this.attribValue != null ? this.attribValue.hashCode() : 0);
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
        final ProteinDBReferenceProperty other = (ProteinDBReferenceProperty) obj;
        if (this.proteinDBReferenceWID != other.proteinDBReferenceWID) {
            return false;
        }
        if ((this.attribType == null) ? (other.attribType != null) : !this.attribType.equals(other.attribType)) {
            return false;
        }
        return !((this.attribValue == null) ? (other.attribValue != null) : !this.attribValue.equals(other.attribValue));
    }

    @Override
    public String toString() {
        return "ProteinDBReferenceProperty{" + "proteinDBReferenceWID=" + proteinDBReferenceWID + ", attribType=" + attribType + ", attribValue=" + attribValue + '}';
    }
}
