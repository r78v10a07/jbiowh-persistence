package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMTI entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMTI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMTI.findAll", query = "SELECT o FROM OMIMTI o"),
    @NamedQuery(name = "OMIMTI.findByOmimWid", query = "SELECT o FROM OMIMTI o WHERE o.oMIMTIPK.omimWid = :omimWid"),
    @NamedQuery(name = "OMIMTI.findByTi", query = "SELECT o FROM OMIMTI o WHERE o.oMIMTIPK.ti = :ti")})
public class OMIMTI implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OMIMTIPK oMIMTIPK;
    @ManyToOne
    @JoinColumn(name = "OMIM_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OMIM omim;

    public OMIMTI() {
    }

    public OMIMTI(OMIMTIPK oMIMTIPK) {
        this.oMIMTIPK = oMIMTIPK;
    }

    public OMIMTI(long omimWid, String ti) {
        this.oMIMTIPK = new OMIMTIPK(omimWid, ti);
    }

    public OMIM getOmim() {
        return omim;
    }

    public void setOmim(OMIM omim) {
        this.omim = omim;
    }

    public OMIMTIPK getOMIMTIPK() {
        return oMIMTIPK;
    }

    public void setOMIMTIPK(OMIMTIPK oMIMTIPK) {
        this.oMIMTIPK = oMIMTIPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oMIMTIPK != null ? oMIMTIPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMTI)) {
            return false;
        }
        OMIMTI other = (OMIMTI) object;
        if ((this.oMIMTIPK == null && other.oMIMTIPK != null) || (this.oMIMTIPK != null && !this.oMIMTIPK.equals(other.oMIMTIPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMTI{" + "oMIMTIPK=" + oMIMTIPK + '}';
    }
}
