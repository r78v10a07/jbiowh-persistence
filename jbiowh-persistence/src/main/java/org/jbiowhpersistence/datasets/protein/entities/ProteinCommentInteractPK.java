package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the ProteinCommentInteractPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Embeddable
public class ProteinCommentInteractPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "WID")
    private long wid;
    @Basic(optional = false)
    @Column(name = "ProteinComment_WID")
    private long proteinCommentWID;

    public ProteinCommentInteractPK() {
    }

    public ProteinCommentInteractPK(long wid, long proteinCommentWID) {
        this.wid = wid;
        this.proteinCommentWID = proteinCommentWID;
    }

    public long getWid() {
        return wid;
    }

    public void setWid(long wid) {
        this.wid = wid;
    }

    public long getProteinCommentWID() {
        return proteinCommentWID;
    }

    public void setProteinCommentWID(long proteinCommentWID) {
        this.proteinCommentWID = proteinCommentWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) wid;
        hash += (int) proteinCommentWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinCommentInteractPK)) {
            return false;
        }
        ProteinCommentInteractPK other = (ProteinCommentInteractPK) object;
        if (this.wid != other.wid) {
            return false;
        }
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinCommentInteractPK[ wid=" + wid + ", proteinCommentWID=" + proteinCommentWID + " ]";
    }
}
