package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFOtherAvailability entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFOtherAvailability")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFOtherAvailability.findAll", query = "SELECT m FROM MIFOtherAvailability m"),
    @NamedQuery(name = "MIFOtherAvailability.findByMIFOtherWID", query = "SELECT m FROM MIFOtherAvailability m WHERE m.mIFOtherAvailabilityPK.mIFOtherWID = :mIFOtherWID"),
    @NamedQuery(name = "MIFOtherAvailability.findByAvailability", query = "SELECT m FROM MIFOtherAvailability m WHERE m.mIFOtherAvailabilityPK.availability = :availability"),
    @NamedQuery(name = "MIFOtherAvailability.findById", query = "SELECT m FROM MIFOtherAvailability m WHERE m.id = :id"),
    @NamedQuery(name = "MIFOtherAvailability.findByDataSetWID", query = "SELECT m FROM MIFOtherAvailability m WHERE m.dataSetWID = :dataSetWID")})
public class MIFOtherAvailability implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MIFOtherAvailabilityPK mIFOtherAvailabilityPK;
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;

    public MIFOtherAvailability() {
    }

    public MIFOtherAvailability(MIFOtherAvailabilityPK mIFOtherAvailabilityPK) {
        this.mIFOtherAvailabilityPK = mIFOtherAvailabilityPK;
    }

    public MIFOtherAvailability(MIFOtherAvailabilityPK mIFOtherAvailabilityPK, long dataSetWID) {
        this.mIFOtherAvailabilityPK = mIFOtherAvailabilityPK;
        this.dataSetWID = dataSetWID;
    }

    public MIFOtherAvailability(long mIFOtherWID, String availability) {
        this.mIFOtherAvailabilityPK = new MIFOtherAvailabilityPK(mIFOtherWID, availability);
    }

    public MIFOtherAvailabilityPK getmIFOtherAvailabilityPK() {
        return mIFOtherAvailabilityPK;
    }

    public void setmIFOtherAvailabilityPK(MIFOtherAvailabilityPK mIFOtherAvailabilityPK) {
        this.mIFOtherAvailabilityPK = mIFOtherAvailabilityPK;
    }

    public MIFEntryInteraction getMifEntryInteraction() {
        return mifEntryInteraction;
    }

    public void setMifEntryInteraction(MIFEntryInteraction mifEntryInteraction) {
        this.mifEntryInteraction = mifEntryInteraction;
    }

    public MIFOtherAvailabilityPK getMIFOtherAvailabilityPK() {
        return mIFOtherAvailabilityPK;
    }

    public void setMIFOtherAvailabilityPK(MIFOtherAvailabilityPK mIFOtherAvailabilityPK) {
        this.mIFOtherAvailabilityPK = mIFOtherAvailabilityPK;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mIFOtherAvailabilityPK != null ? mIFOtherAvailabilityPK.hashCode() : 0);
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
        final MIFOtherAvailability other = (MIFOtherAvailability) obj;
        if (!Objects.equals(this.mIFOtherAvailabilityPK, other.mIFOtherAvailabilityPK)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "MIFOtherAvailability{" + "mIFOtherAvailabilityPK=" + mIFOtherAvailabilityPK + ", id=" + id + ", dataSetWID=" + dataSetWID + '}';
    }
}
