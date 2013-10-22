package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the ProteinDBReferencePropertyPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Embeddable
public class ProteinDBReferencePropertyPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinDBReference_WID")
    private long proteinDBReferenceWID;
    @Basic(optional = false)
    @Column(name = "AttribType")
    private String attribType;

    public ProteinDBReferencePropertyPK() {
    }

    public ProteinDBReferencePropertyPK(long proteinDBReferenceWID, String attribType) {
        this.proteinDBReferenceWID = proteinDBReferenceWID;
        this.attribType = attribType;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proteinDBReferenceWID;
        hash += (attribType != null ? attribType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinDBReferencePropertyPK)) {
            return false;
        }
        ProteinDBReferencePropertyPK other = (ProteinDBReferencePropertyPK) object;
        if (this.proteinDBReferenceWID != other.proteinDBReferenceWID) {
            return false;
        }
        if ((this.attribType == null && other.attribType != null) || (this.attribType != null && !this.attribType.equals(other.attribType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinDBReferencePropertyPK[ proteinDBReferenceWID=" + proteinDBReferenceWID + ", attribType=" + attribType + " ]";
    }
}
