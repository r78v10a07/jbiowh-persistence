package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMTX entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMTX")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMTX.findAll", query = "SELECT o FROM OMIMTX o"),
    @NamedQuery(name = "OMIMTX.findByOmimWid", query = "SELECT o FROM OMIMTX o WHERE o.oMIMTXPK.omimWid = :omimWid"),
    @NamedQuery(name = "OMIMTX.findByTag", query = "SELECT o FROM OMIMTX o WHERE o.oMIMTXPK.tag = :tag")})
public class OMIMTX implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OMIMTXPK oMIMTXPK;
    @Basic(optional = false)
    @Lob
    @Column(name = "TX")
    private String tx;
    @ManyToOne
    @JoinColumn(name = "OMIM_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OMIM omim;

    public OMIMTX() {
    }

    public OMIMTX(OMIMTXPK oMIMTXPK) {
        this.oMIMTXPK = oMIMTXPK;
    }

    public OMIMTX(OMIMTXPK oMIMTXPK, String tx) {
        this.oMIMTXPK = oMIMTXPK;
        this.tx = tx;
    }

    public OMIMTX(long omimWid, String tag) {
        this.oMIMTXPK = new OMIMTXPK(omimWid, tag);
    }

    public OMIM getOmim() {
        return omim;
    }

    public void setOmim(OMIM omim) {
        this.omim = omim;
    }

    public OMIMTXPK getOMIMTXPK() {
        return oMIMTXPK;
    }

    public void setOMIMTXPK(OMIMTXPK oMIMTXPK) {
        this.oMIMTXPK = oMIMTXPK;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oMIMTXPK != null ? oMIMTXPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OMIMTX)) {
            return false;
        }
        OMIMTX other = (OMIMTX) object;
        if ((this.oMIMTXPK == null && other.oMIMTXPK != null) || (this.oMIMTXPK != null && !this.oMIMTXPK.equals(other.oMIMTXPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMTX{" + "oMIMTXPK=" + oMIMTXPK + ", tx=" + tx + '}';
    }
}
