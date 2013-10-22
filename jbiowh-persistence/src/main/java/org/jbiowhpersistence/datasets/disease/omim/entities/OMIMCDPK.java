package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OMIMCD PK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Embeddable
public class OMIMCDPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "OMIM_WID")
    private long omimWid;
    @Basic(optional = false)
    @Column(name = "CD")
    private String cd;

    public OMIMCDPK() {
    }

    public OMIMCDPK(long omimWid, String cd) {
        this.omimWid = omimWid;
        this.cd = cd;
    }

    public long getOmimWid() {
        return omimWid;
    }

    public void setOmimWid(long omimWid) {
        this.omimWid = omimWid;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) omimWid;
        hash += (cd != null ? cd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMCDPK)) {
            return false;
        }
        OMIMCDPK other = (OMIMCDPK) object;
        if (this.omimWid != other.omimWid) {
            return false;
        }
        if ((this.cd == null && other.cd != null) || (this.cd != null && !this.cd.equals(other.cd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMCDPK{" + "omimWid=" + omimWid + ", cd=" + cd + '}';
    }
}
