package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the ProteinIsoformNamePK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Embeddable
public class ProteinIsoformNamePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinCommentIsoform_WID")
    private long proteinCommentIsoformWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;

    public ProteinIsoformNamePK() {
    }

    public ProteinIsoformNamePK(long proteinCommentIsoformWID, String name) {
        this.proteinCommentIsoformWID = proteinCommentIsoformWID;
        this.name = name;
    }

    public long getProteinCommentIsoformWID() {
        return proteinCommentIsoformWID;
    }

    public void setProteinCommentIsoformWID(long proteinCommentIsoformWID) {
        this.proteinCommentIsoformWID = proteinCommentIsoformWID;
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
        hash += (int) proteinCommentIsoformWID;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinIsoformNamePK)) {
            return false;
        }
        ProteinIsoformNamePK other = (ProteinIsoformNamePK) object;
        if (this.proteinCommentIsoformWID != other.proteinCommentIsoformWID) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinIsoformNamePK[ proteinCommentIsoformWID=" + proteinCommentIsoformWID + ", name=" + name + " ]";
    }
}
