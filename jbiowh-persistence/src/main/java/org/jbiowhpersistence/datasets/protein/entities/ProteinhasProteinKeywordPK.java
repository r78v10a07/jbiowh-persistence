package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the ProteinhasProteinKeyword PK
 *
 * @author roberto
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $
 * $LastChangedRevision: 285 $
 * @since Sep 4, 2012
 */
@Embeddable
public class ProteinhasProteinKeywordPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Basic(optional = false)
    @Column(name = "ProteinKeyword_WID")
    private long proteinKeywordWID;

    public ProteinhasProteinKeywordPK() {
    }

    public ProteinhasProteinKeywordPK(long proteinWID, long proteinKeywordWID) {
        this.proteinWID = proteinWID;
        this.proteinKeywordWID = proteinKeywordWID;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
    }

    public long getProteinKeywordWID() {
        return proteinKeywordWID;
    }

    public void setProteinKeywordWID(long proteinKeywordWID) {
        this.proteinKeywordWID = proteinKeywordWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proteinWID;
        hash += (int) proteinKeywordWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinhasProteinKeywordPK)) {
            return false;
        }
        ProteinhasProteinKeywordPK other = (ProteinhasProteinKeywordPK) object;
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        return this.proteinKeywordWID == other.proteinKeywordWID;
    }

    @Override
    public String toString() {
        return "ProteinhasProteinKeywordPK[ proteinWID=" + proteinWID + ", proteinKeywordWID=" + proteinKeywordWID + " ]";
    }

}
