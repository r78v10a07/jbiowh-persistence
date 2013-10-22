package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the ProteinRefSeqPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Embeddable
public class ProteinRefSeqPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;

    public ProteinRefSeqPK() {
    }

    public ProteinRefSeqPK(long proteinWID, String id) {
        this.proteinWID = proteinWID;
        this.id = id;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proteinWID;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinRefSeqPK)) {
            return false;
        }
        ProteinRefSeqPK other = (ProteinRefSeqPK) object;
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinRefSeqPK[ proteinWID=" + proteinWID + ", id=" + id + " ]";
    }
}
