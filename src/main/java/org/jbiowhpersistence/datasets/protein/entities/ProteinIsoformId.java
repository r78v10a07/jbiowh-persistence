package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * This Class is the Protein Isoform Id entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinIsoformId")
public class ProteinIsoformId implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinCommentIsoform_WID")
    private long proteinCommentIsoformWID;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;

    public ProteinIsoformId() {
    }

    public ProteinIsoformId(long proteinCommentIsoformWID, String id) {
        this.proteinCommentIsoformWID = proteinCommentIsoformWID;
        this.id = id;
    }

    public long getProteinCommentIsoformWID() {
        return proteinCommentIsoformWID;
    }

    public void setProteinCommentIsoformWID(long proteinCommentIsoformWID) {
        this.proteinCommentIsoformWID = proteinCommentIsoformWID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.proteinCommentIsoformWID ^ (this.proteinCommentIsoformWID >>> 32));
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ProteinIsoformId other = (ProteinIsoformId) obj;
        if (this.proteinCommentIsoformWID != other.proteinCommentIsoformWID) {
            return false;
        }
        return !((this.id == null) ? (other.id != null) : !this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "ProteinIsoformId{" + "proteinCommentIsoformWID=" + proteinCommentIsoformWID + ", id=" + id + '}';
    }
}
