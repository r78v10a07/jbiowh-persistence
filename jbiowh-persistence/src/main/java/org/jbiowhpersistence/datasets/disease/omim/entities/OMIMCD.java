package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMCD entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMCD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMCD.findAll", query = "SELECT o FROM OMIMCD o"),
    @NamedQuery(name = "OMIMCD.findByOmimWid", query = "SELECT o FROM OMIMCD o WHERE o.oMIMCDPK.omimWid = :omimWid"),
    @NamedQuery(name = "OMIMCD.findByCd", query = "SELECT o FROM OMIMCD o WHERE o.oMIMCDPK.cd = :cd")})
public class OMIMCD implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OMIMCDPK oMIMCDPK;
    @ManyToOne
    @JoinColumn(name = "OMIM_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OMIM omim;

    public OMIMCD() {
    }

    public OMIMCD(OMIMCDPK oMIMCDPK) {
        this.oMIMCDPK = oMIMCDPK;
    }

    public OMIMCD(long omimWid, String cd) {
        this.oMIMCDPK = new OMIMCDPK(omimWid, cd);
    }

    public OMIM getOmim() {
        return omim;
    }

    public void setOmim(OMIM omim) {
        this.omim = omim;
    }

    public OMIMCDPK getOMIMCDPK() {
        return oMIMCDPK;
    }

    public void setOMIMCDPK(OMIMCDPK oMIMCDPK) {
        this.oMIMCDPK = oMIMCDPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oMIMCDPK != null ? oMIMCDPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMCD)) {
            return false;
        }
        OMIMCD other = (OMIMCD) object;
        if ((this.oMIMCDPK == null && other.oMIMCDPK != null) || (this.oMIMCDPK != null && !this.oMIMCDPK.equals(other.oMIMCDPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMCD{" + "oMIMCDPK=" + oMIMCDPK + '}';
    }
}
