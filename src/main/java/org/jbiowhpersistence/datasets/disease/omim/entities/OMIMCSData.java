package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMCSData entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 20, 2012
 */
@Entity
@Table(name = "OMIMCSData")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMCSData.findAll", query = "SELECT o FROM OMIMCSData o"),
    @NamedQuery(name = "OMIMCSData.findByWid", query = "SELECT o FROM OMIMCSData o WHERE o.wid = :wid"),
    @NamedQuery(name = "OMIMCSData.findByOmimcsWid", query = "SELECT o FROM OMIMCSData o WHERE o.omimcsWid = :omimcsWid")})
public class OMIMCSData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "OMIMCS_WID")
    private long omimcsWid;
    @Basic(optional = false)
    @Lob
    @Column(name = "Data")
    private String data;
    @ManyToOne
    @JoinColumn(name = "OMIMCS_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OMIMCS omimCS;

    public OMIMCSData() {
    }

    public OMIMCSData(Long wid) {
        this.wid = wid;
    }

    public OMIMCSData(Long wid, long omimcsWid, String data) {
        this.wid = wid;
        this.omimcsWid = omimcsWid;
        this.data = data;
    }

    public OMIMCS getOmimCS() {
        return omimCS;
    }

    public void setOmimCS(OMIMCS omimCS) {
        this.omimCS = omimCS;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getOmimcsWid() {
        return omimcsWid;
    }

    public void setOmimcsWid(long omimcsWid) {
        this.omimcsWid = omimcsWid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        final OMIMCSData other = (OMIMCSData) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.omimcsWid != other.omimcsWid) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMCSData{" + "wid=" + wid + ", omimcsWid=" + omimcsWid + ", data=" + data + '}';
    }
}
