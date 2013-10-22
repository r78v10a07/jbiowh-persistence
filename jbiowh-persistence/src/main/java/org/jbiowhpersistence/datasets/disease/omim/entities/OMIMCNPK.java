package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OMIMCN PK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Embeddable
public class OMIMCNPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "OMIM_WID")
    private long omimWid;
    @Basic(optional = false)
    @Column(name = "CN")
    private String cn;

    public OMIMCNPK() {
    }

    public OMIMCNPK(long omimWid, String cn) {
        this.omimWid = omimWid;
        this.cn = cn;
    }

    public long getOmimWid() {
        return omimWid;
    }

    public void setOmimWid(long omimWid) {
        this.omimWid = omimWid;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) omimWid;
        hash += (cn != null ? cn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMCNPK)) {
            return false;
        }
        OMIMCNPK other = (OMIMCNPK) object;
        if (this.omimWid != other.omimWid) {
            return false;
        }
        if ((this.cn == null && other.cn != null) || (this.cn != null && !this.cn.equals(other.cn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMCNPK{" + "omimWid=" + omimWid + ", cn=" + cn + '}';
    }
}
