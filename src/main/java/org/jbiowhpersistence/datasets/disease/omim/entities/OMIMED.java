package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMED entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMED.findAll", query = "SELECT o FROM OMIMED o"),
    @NamedQuery(name = "OMIMED.findByOmimWid", query = "SELECT o FROM OMIMED o WHERE o.oMIMEDPK.omimWid = :omimWid"),
    @NamedQuery(name = "OMIMED.findByEd", query = "SELECT o FROM OMIMED o WHERE o.oMIMEDPK.ed = :ed")})
public class OMIMED implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OMIMEDPK oMIMEDPK;
    @ManyToOne
    @JoinColumn(name = "OMIM_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OMIM omim;

    public OMIMED() {
    }

    public OMIMED(OMIMEDPK oMIMEDPK) {
        this.oMIMEDPK = oMIMEDPK;
    }

    public OMIMED(long omimWid, String ed) {
        this.oMIMEDPK = new OMIMEDPK(omimWid, ed);
    }

    public OMIM getOmim() {
        return omim;
    }

    public void setOmim(OMIM omim) {
        this.omim = omim;
    }

    public OMIMEDPK getOMIMEDPK() {
        return oMIMEDPK;
    }

    public void setOMIMEDPK(OMIMEDPK oMIMEDPK) {
        this.oMIMEDPK = oMIMEDPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oMIMEDPK != null ? oMIMEDPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMED)) {
            return false;
        }
        OMIMED other = (OMIMED) object;
        if ((this.oMIMEDPK == null && other.oMIMEDPK != null) || (this.oMIMEDPK != null && !this.oMIMEDPK.equals(other.oMIMEDPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMED{" + "oMIMEDPK=" + oMIMEDPK + '}';
    }
}
