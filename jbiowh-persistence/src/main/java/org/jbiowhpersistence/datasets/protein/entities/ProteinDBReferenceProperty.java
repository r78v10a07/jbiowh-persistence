package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is Protein DB Reference Property entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinDBReferenceProperty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinDBReferenceProperty.findAll", query = "SELECT p FROM ProteinDBReferenceProperty p"),
    @NamedQuery(name = "ProteinDBReferenceProperty.findByProteinDBReferenceWID", query = "SELECT p FROM ProteinDBReferenceProperty p WHERE p.proteinDBReferencePropertyPK.proteinDBReferenceWID = :proteinDBReferenceWID"),
    @NamedQuery(name = "ProteinDBReferenceProperty.findByAttribType", query = "SELECT p FROM ProteinDBReferenceProperty p WHERE p.proteinDBReferencePropertyPK.attribType = :attribType"),
    @NamedQuery(name = "ProteinDBReferenceProperty.findByAttribValue", query = "SELECT p FROM ProteinDBReferenceProperty p WHERE p.attribValue = :attribValue")})
public class ProteinDBReferenceProperty implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinDBReferencePropertyPK proteinDBReferencePropertyPK;
    @Column(name = "AttribValue")
    private String attribValue;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinDBReference_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinDBReference proteinDBReference;

    public ProteinDBReferenceProperty() {
    }

    public ProteinDBReferenceProperty(ProteinDBReferencePropertyPK proteinDBReferencePropertyPK) {
        this.proteinDBReferencePropertyPK = proteinDBReferencePropertyPK;
    }

    public ProteinDBReferenceProperty(long proteinDBReferenceWID, String attribType) {
        this.proteinDBReferencePropertyPK = new ProteinDBReferencePropertyPK(proteinDBReferenceWID, attribType);
    }

    public ProteinDBReference getProteinDBReference() {
        return proteinDBReference;
    }

    public void setProteinDBReference(ProteinDBReference proteinDBReference) {
        this.proteinDBReference = proteinDBReference;
    }

    public ProteinDBReferencePropertyPK getProteinDBReferencePropertyPK() {
        return proteinDBReferencePropertyPK;
    }

    public void setProteinDBReferencePropertyPK(ProteinDBReferencePropertyPK proteinDBReferencePropertyPK) {
        this.proteinDBReferencePropertyPK = proteinDBReferencePropertyPK;
    }

    public String getAttribValue() {
        return attribValue;
    }

    public void setAttribValue(String attribValue) {
        this.attribValue = attribValue;
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
        if (!Objects.equals(this.proteinDBReferencePropertyPK, other.proteinDBReferencePropertyPK)) {
            return false;
        }
        if (!Objects.equals(this.attribValue, other.attribValue)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinDBReferencePropertyPK != null ? proteinDBReferencePropertyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProteinDBReferenceProperty{"
                + " DBReferenceWID=" + proteinDBReferencePropertyPK.getProteinDBReferenceWID()
                + " AttribType=" + proteinDBReferencePropertyPK.getAttribType()
                + " attribValue=" + attribValue
                + '}';
    }
}
