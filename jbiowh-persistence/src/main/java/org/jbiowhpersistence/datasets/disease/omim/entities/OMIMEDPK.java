package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OMIMED PK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Embeddable
public class OMIMEDPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "OMIM_WID")
    private long omimWid;
    @Basic(optional = false)
    @Column(name = "ED")
    private String ed;

    public OMIMEDPK() {
    }

    public OMIMEDPK(long omimWid, String ed) {
        this.omimWid = omimWid;
        this.ed = ed;
    }

    public long getOmimWid() {
        return omimWid;
    }

    public void setOmimWid(long omimWid) {
        this.omimWid = omimWid;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) omimWid;
        hash += (ed != null ? ed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMEDPK)) {
            return false;
        }
        OMIMEDPK other = (OMIMEDPK) object;
        if (this.omimWid != other.omimWid) {
            return false;
        }
        if ((this.ed == null && other.ed != null) || (this.ed != null && !this.ed.equals(other.ed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMEDPK{" + "omimWid=" + omimWid + ", ed=" + ed + '}';
    }
}
