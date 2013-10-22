package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the OMIMTX PK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Embeddable
public class OMIMTXPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "OMIM_WID")
    private long omimWid;
    @Basic(optional = false)
    @Column(name = "Tag")
    private String tag;

    public OMIMTXPK() {
    }

    public OMIMTXPK(long omimWid, String tag) {
        this.omimWid = omimWid;
        this.tag = tag;
    }

    public long getOmimWid() {
        return omimWid;
    }

    public void setOmimWid(long omimWid) {
        this.omimWid = omimWid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) omimWid;
        hash += (tag != null ? tag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMTXPK)) {
            return false;
        }
        OMIMTXPK other = (OMIMTXPK) object;
        if (this.omimWid != other.omimWid) {
            return false;
        }
        if ((this.tag == null && other.tag != null) || (this.tag != null && !this.tag.equals(other.tag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMTXPK{" + "omimWid=" + omimWid + ", tag=" + tag + '}';
    }
}
