package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the OMIMCS entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMCS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMCS.findAll", query = "SELECT o FROM OMIMCS o"),
    @NamedQuery(name = "OMIMCS.findByWid", query = "SELECT o FROM OMIMCS o WHERE o.wid = :wid"),
    @NamedQuery(name = "OMIMCS.findByOmimWid", query = "SELECT o FROM OMIMCS o WHERE o.omimWid = :omimWid"),
    @NamedQuery(name = "OMIMCS.findByCs", query = "SELECT o FROM OMIMCS o WHERE o.cs = :cs")})
public class OMIMCS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "OMIM_WID")
    private long omimWid;
    @Basic(optional = false)
    @Column(name = "CS")
    private String cs;
    @ManyToOne
    @JoinColumn(name = "OMIM_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OMIM omim;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omimCS")
    private Set<OMIMCSData> omimCSDatas; 

    public OMIMCS() {
    }

    public OMIMCS(Long wid) {
        this.wid = wid;
    }

    public OMIMCS(Long wid, long omimWid, String cs) {
        this.wid = wid;
        this.omimWid = omimWid;
        this.cs = cs;
    }

    public OMIM getOmim() {
        return omim;
    }

    public void setOmim(OMIM omim) {
        this.omim = omim;
    }

    @XmlTransient
    public Set<OMIMCSData> getOmimCSDatas() {
        return omimCSDatas;
    }

    public void setOmimCSDatas(Set<OMIMCSData> omimCSDatas) {
        this.omimCSDatas = omimCSDatas;
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

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
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
        final OMIMCS other = (OMIMCS) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.omimWid != other.omimWid) {
            return false;
        }
        if (!Objects.equals(this.cs, other.cs)) {
            return false;
        }
        if (!Objects.equals(this.omimCSDatas, other.omimCSDatas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMCS{" + "wid=" + wid + ", omimWid=" + omimWid + ", cs=" + cs + ", data=" + omimCSDatas +'}';
    }
}
