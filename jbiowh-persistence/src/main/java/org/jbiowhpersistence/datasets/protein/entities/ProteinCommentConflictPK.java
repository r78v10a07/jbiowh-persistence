package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the ProteinCommentConflictPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Embeddable
public class ProteinCommentConflictPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinComment_WID")
    private long proteinCommentWID;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;

    public ProteinCommentConflictPK() {
    }

    public ProteinCommentConflictPK(long proteinCommentWID, String type) {
        this.proteinCommentWID = proteinCommentWID;
        this.type = type;
    }

    public long getProteinCommentWID() {
        return proteinCommentWID;
    }

    public void setProteinCommentWID(long proteinCommentWID) {
        this.proteinCommentWID = proteinCommentWID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proteinCommentWID;
        hash += (type != null ? type.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinCommentConflictPK)) {
            return false;
        }
        ProteinCommentConflictPK other = (ProteinCommentConflictPK) object;
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        if ((this.type == null && other.type != null) || (this.type != null && !this.type.equals(other.type))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinCommentConflictPK[ proteinCommentWID=" + proteinCommentWID + ", type=" + type + " ]";
    }
}
