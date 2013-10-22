package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMCN entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMCN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMCN.findAll", query = "SELECT o FROM OMIMCN o"),
    @NamedQuery(name = "OMIMCN.findByOmimWid", query = "SELECT o FROM OMIMCN o WHERE o.oMIMCNPK.omimWid = :omimWid"),
    @NamedQuery(name = "OMIMCN.findByCn", query = "SELECT o FROM OMIMCN o WHERE o.oMIMCNPK.cn = :cn")})
public class OMIMCN implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OMIMCNPK oMIMCNPK;
    @ManyToOne
    @JoinColumn(name = "OMIM_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OMIM omim;

    public OMIMCN() {
    }

    public OMIMCN(OMIMCNPK oMIMCNPK) {
        this.oMIMCNPK = oMIMCNPK;
    }

    public OMIMCN(long omimWid, String cn) {
        this.oMIMCNPK = new OMIMCNPK(omimWid, cn);
    }

    public OMIM getOmim() {
        return omim;
    }

    public void setOmim(OMIM omim) {
        this.omim = omim;
    }

    public OMIMCNPK getOMIMCNPK() {
        return oMIMCNPK;
    }

    public void setOMIMCNPK(OMIMCNPK oMIMCNPK) {
        this.oMIMCNPK = oMIMCNPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oMIMCNPK != null ? oMIMCNPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMCN)) {
            return false;
        }
        OMIMCN other = (OMIMCN) object;
        if ((this.oMIMCNPK == null && other.oMIMCNPK != null) || (this.oMIMCNPK != null && !this.oMIMCNPK.equals(other.oMIMCNPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMCN{" + "oMIMCNPK=" + oMIMCNPK + '}';
    }
}
