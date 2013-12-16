package org.jbiowhpersistence.datasets.protgroup.pirsf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the PIRSFhasProtein PK
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 11, 2013
 */
@Embeddable
public class PirsfhasProteinPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PIRSF_WID")
    private long pirsfWid;
    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;

    public PirsfhasProteinPK() {
    }

    public PirsfhasProteinPK(long pirsfWid, long proteinWID) {
        this.pirsfWid = pirsfWid;
        this.proteinWID = proteinWID;
    }

    public long getPirsfWid() {
        return pirsfWid;
    }

    public void setPirsfWid(long pirsfWid) {
        this.pirsfWid = pirsfWid;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pirsfWid;
        hash += (int) proteinWID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PirsfhasProteinPK)) {
            return false;
        }
        PirsfhasProteinPK other = (PirsfhasProteinPK) object;
        if (this.pirsfWid != other.pirsfWid) {
            return false;
        }
        return this.proteinWID == other.proteinWID;
    }

    @Override
    public String toString() {
        return "PirsfhasProteinPK{" + "pirsfWid=" + pirsfWid + ", proteinWID=" + proteinWID + '}';
    }
}
