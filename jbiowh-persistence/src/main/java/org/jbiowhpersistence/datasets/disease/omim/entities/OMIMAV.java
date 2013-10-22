package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMAV entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMAV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMAV.findAll", query = "SELECT o FROM OMIMAV o"),
    @NamedQuery(name = "OMIMAV.findByWid", query = "SELECT o FROM OMIMAV o WHERE o.wid = :wid"),
    @NamedQuery(name = "OMIMAV.findByOmimWid", query = "SELECT o FROM OMIMAV o WHERE o.omimWid = :omimWid")})
public class OMIMAV implements Serializable {

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
    @Column(name = "AV")
    private String av;
    @ManyToOne
    @JoinColumn(name = "OMIM_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OMIM omim;

    public OMIMAV() {
    }

    public OMIMAV(Long wid) {
        this.wid = wid;
    }

    public OMIMAV(Long wid, long omimWid, String av) {
        this.wid = wid;
        this.omimWid = omimWid;
        this.av = av;
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

    public String getAv() {
        return av;
    }

    public void setAv(String av) {
        this.av = av;
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
        final OMIMAV other = (OMIMAV) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.omimWid != other.omimWid) {
            return false;
        }
        if (!Objects.equals(this.av, other.av)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMAV{" + "wid=" + wid + ", omimWid=" + omimWid + ", av=" + av + '}';
    }
}
