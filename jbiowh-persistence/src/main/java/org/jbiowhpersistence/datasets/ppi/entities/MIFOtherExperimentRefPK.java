package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the MIFOtherExperimentRefPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Embeddable
public class MIFOtherExperimentRefPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MIFOtherWID")
    private long mIFOtherWID;
    @Basic(optional = false)
    @Column(name = "ExperimentRef")
    private int experimentRef;

    public MIFOtherExperimentRefPK() {
    }

    public MIFOtherExperimentRefPK(long mIFOtherWID, int experimentRef) {
        this.mIFOtherWID = mIFOtherWID;
        this.experimentRef = experimentRef;
    }

    public long getMIFOtherWID() {
        return mIFOtherWID;
    }

    public void setMIFOtherWID(long mIFOtherWID) {
        this.mIFOtherWID = mIFOtherWID;
    }

    public int getExperimentRef() {
        return experimentRef;
    }

    public void setExperimentRef(int experimentRef) {
        this.experimentRef = experimentRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mIFOtherWID;
        hash += (int) experimentRef;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MIFOtherExperimentRefPK)) {
            return false;
        }
        MIFOtherExperimentRefPK other = (MIFOtherExperimentRefPK) object;
        if (this.mIFOtherWID != other.mIFOtherWID) {
            return false;
        }
        if (this.experimentRef != other.experimentRef) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.mif25.entities.MIFOtherExperimentRefPK[ mIFOtherWID=" + mIFOtherWID + ", experimentRef=" + experimentRef + " ]";
    }
}
