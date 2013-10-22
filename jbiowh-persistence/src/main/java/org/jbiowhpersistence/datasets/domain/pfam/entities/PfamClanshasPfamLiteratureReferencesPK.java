package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the PfamClanshasPfamLiteratureReferences PK
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ $LastChangedRevision: 344 $
 *
 * @since Nov 26, 2012
 */
@Embeddable
public class PfamClanshasPfamLiteratureReferencesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PfamClans_WID")
    private long pfamClansWID;
    @Basic(optional = false)
    @Column(name = "PfamLiteratureReferences_WID")
    private long pfamLiteratureReferencesWID;
    @Basic(optional = false)
    @Column(name = "order_added")
    private short orderAdded;

    public PfamClanshasPfamLiteratureReferencesPK() {
    }

    public PfamClanshasPfamLiteratureReferencesPK(long pfamClansWID, long pfamLiteratureReferencesWID, short orderAdded) {
        this.pfamClansWID = pfamClansWID;
        this.pfamLiteratureReferencesWID = pfamLiteratureReferencesWID;
        this.orderAdded = orderAdded;
    }

    public long getPfamClansWID() {
        return pfamClansWID;
    }

    public void setPfamClansWID(long pfamClansWID) {
        this.pfamClansWID = pfamClansWID;
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
        hash += (int) pfamClansWID;
        hash += (int) pfamLiteratureReferencesWID;
        hash += (int) orderAdded;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PfamClanshasPfamLiteratureReferencesPK)) {
            return false;
        }
        PfamClanshasPfamLiteratureReferencesPK other = (PfamClanshasPfamLiteratureReferencesPK) object;
        if (this.pfamClansWID != other.pfamClansWID) {
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
        return "PfamClanshasPfamLiteratureReferencesPK{" + "pfamClansWID=" + pfamClansWID + ", pfamLiteratureReferencesWID=" + pfamLiteratureReferencesWID + ", orderAdded=" + orderAdded + '}';
    }
}
