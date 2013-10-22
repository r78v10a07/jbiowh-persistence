package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the MIFOtherAvailabilityPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Embeddable
public class MIFOtherAvailabilityPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MIFOtherWID")
    private long mIFOtherWID;
    @Basic(optional = false)
    @Column(name = "Availability")
    private String availability;

    public MIFOtherAvailabilityPK() {
    }

    public MIFOtherAvailabilityPK(long mIFOtherWID, String availability) {
        this.mIFOtherWID = mIFOtherWID;
        this.availability = availability;
    }

    public long getMIFOtherWID() {
        return mIFOtherWID;
    }

    public void setMIFOtherWID(long mIFOtherWID) {
        this.mIFOtherWID = mIFOtherWID;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mIFOtherWID;
        hash += (availability != null ? availability.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MIFOtherAvailabilityPK)) {
            return false;
        }
        MIFOtherAvailabilityPK other = (MIFOtherAvailabilityPK) object;
        if (this.mIFOtherWID != other.mIFOtherWID) {
            return false;
        }
        if ((this.availability == null && other.availability != null) || (this.availability != null && !this.availability.equals(other.availability))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.mif25.entities.MIFOtherAvailabilityPK[ mIFOtherWID=" + mIFOtherWID + ", availability=" + availability + " ]";
    }
}
