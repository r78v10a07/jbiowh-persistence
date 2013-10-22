package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OMIMTI PK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Embeddable
public class OMIMTIPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "OMIM_WID")
    private long omimWid;
    @Basic(optional = false)
    @Column(name = "TI")
    private String ti;

    public OMIMTIPK() {
    }

    public OMIMTIPK(long omimWid, String ti) {
        this.omimWid = omimWid;
        this.ti = ti;
    }

    public long getOmimWid() {
        return omimWid;
    }

    public void setOmimWid(long omimWid) {
        this.omimWid = omimWid;
    }

    public String getTi() {
        return ti;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) omimWid;
        hash += (ti != null ? ti.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMTIPK)) {
            return false;
        }
        OMIMTIPK other = (OMIMTIPK) object;
        if (this.omimWid != other.omimWid) {
            return false;
        }
        if ((this.ti == null && other.ti != null) || (this.ti != null && !this.ti.equals(other.ti))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMTIPK{" + "omimWid=" + omimWid + ", ti=" + ti + '}';
    }
}
