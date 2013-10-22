package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the PfamAhasPfamLiteratureReferences PK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $
 * $LastChangedRevision: 344 $
 * @since Nov 26, 2012
 */
@Embeddable
public class PfamAhasPfamLiteratureReferencesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PfamA_WID")
    private long pfamAWID;
    @Basic(optional = false)
    @Column(name = "PfamLiteratureReferences_WID")
    private long pfamLiteratureReferencesWID;
    @Basic(optional = false)
    @Column(name = "order_added")
    private short orderAdded;

    public PfamAhasPfamLiteratureReferencesPK() {
    }

    public PfamAhasPfamLiteratureReferencesPK(long pfamAWID, long pfamLiteratureReferencesWID, short orderAdded) {
        this.pfamAWID = pfamAWID;
        this.pfamLiteratureReferencesWID = pfamLiteratureReferencesWID;
        this.orderAdded = orderAdded;
    }

    public long getPfamAWID() {
        return pfamAWID;
    }

    public void setPfamAWID(long pfamAWID) {
        this.pfamAWID = pfamAWID;
    }

    public long getPfamLiteratureReferencesWID() {
        return pfamLiteratureReferencesWID;
    }

    public void setPfamLiteratureReferencesWID(long pfamLiteratureReferencesWID) {
        this.pfamLiteratureReferencesWID = pfamLiteratureReferencesWID;
    }

    public short getOrderAdded() {
        return orderAdded;
    }

    public void setOrderAdded(short orderAdded) {
        this.orderAdded = orderAdded;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfamAWID;
        hash += (int) pfamLiteratureReferencesWID;
        hash += (int) orderAdded;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PfamAhasPfamLiteratureReferencesPK)) {
            return false;
        }
        PfamAhasPfamLiteratureReferencesPK other = (PfamAhasPfamLiteratureReferencesPK) object;
        if (this.pfamAWID != other.pfamAWID) {
            return false;
        }
        if (this.pfamLiteratureReferencesWID != other.pfamLiteratureReferencesWID) {
            return false;
        }
        if (this.orderAdded != other.orderAdded) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamAhasPfamLiteratureReferencesPK{" + "pfamAWID=" + pfamAWID + ", pfamLiteratureReferencesWID=" + pfamLiteratureReferencesWID + ", orderAdded=" + orderAdded + '}';
    }
}
