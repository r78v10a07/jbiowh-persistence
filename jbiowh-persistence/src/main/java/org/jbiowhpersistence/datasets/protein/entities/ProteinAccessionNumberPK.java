package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the ProteinAccessionNumberPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Embeddable
public class ProteinAccessionNumberPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Basic(optional = false)
    @Column(name = "AccessionNumber")
    private String accessionNumber;

    public ProteinAccessionNumberPK() {
    }

    public ProteinAccessionNumberPK(long proteinWID, String accessionNumber) {
        this.proteinWID = proteinWID;
        this.accessionNumber = accessionNumber;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proteinWID;
        hash += (accessionNumber != null ? accessionNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinAccessionNumberPK)) {
            return false;
        }
        ProteinAccessionNumberPK other = (ProteinAccessionNumberPK) object;
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if ((this.accessionNumber == null && other.accessionNumber != null) || (this.accessionNumber != null && !this.accessionNumber.equals(other.accessionNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinAccessionNumberPK[ proteinWID=" + proteinWID + ", accessionNumber=" + accessionNumber + " ]";
    }
}
