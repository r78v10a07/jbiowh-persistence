package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the ProteinNamePK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Embeddable
public class ProteinNamePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;

    public ProteinNamePK() {
    }

    public ProteinNamePK(long proteinWID, String name) {
        this.proteinWID = proteinWID;
        this.name = name;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proteinWID;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinNamePK)) {
            return false;
        }
        ProteinNamePK other = (ProteinNamePK) object;
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinNamePK[ proteinWID=" + proteinWID + ", name=" + name + " ]";
    }
}
