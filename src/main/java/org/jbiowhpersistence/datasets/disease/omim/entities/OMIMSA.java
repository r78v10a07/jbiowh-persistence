package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMSA entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 20, 2012
 */
@Entity
@Table(name = "OMIMSA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMSA.findAll", query = "SELECT o FROM OMIMSA o"),
    @NamedQuery(name = "OMIMSA.findByWid", query = "SELECT o FROM OMIMSA o WHERE o.wid = :wid"),
    @NamedQuery(name = "OMIMSA.findByOmimWid", query = "SELECT o FROM OMIMSA o WHERE o.omimWid = :omimWid")})
public class OMIMSA implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "OMIM_WID")
    private long omimWid;
    @Basic(optional = false)
    @Lob
    @Column(name = "SA")
    private String sa;
    @ManyToOne
    @JoinColumn(name = "OMIM_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OMIM omim;

    public OMIMSA() {
    }

    public OMIMSA(Long wid) {
        this.wid = wid;
    }

    public OMIMSA(Long wid, long omimWid, String sa) {
        this.wid = wid;
        this.omimWid = omimWid;
        this.sa = sa;
    }

    public OMIM getOmim() {
        return omim;
    }

    public void setOmim(OMIM omim) {
        this.omim = omim;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getOmimWid() {
        return omimWid;
    }

    public void setOmimWid(long omimWid) {
        this.omimWid = omimWid;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OMIMSA other = (OMIMSA) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.omimWid != other.omimWid) {
            return false;
        }
        if (!Objects.equals(this.sa, other.sa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMSA{" + "wid=" + wid + ", omimWid=" + omimWid + ", sa=" + sa + '}';
    }
}
