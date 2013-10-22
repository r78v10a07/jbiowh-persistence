package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.disease.omim.OMIMTables;

/**
 * This Class is the OMIMMethod entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMMethod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMMethod.findAll", query = "SELECT o FROM OMIMMethod o"),
    @NamedQuery(name = "OMIMMethod.findByWid", query = "SELECT o FROM OMIMMethod o WHERE o.wid = :wid"),
    @NamedQuery(name = "OMIMMethod.findByMethod", query = "SELECT o FROM OMIMMethod o WHERE o.method = :method")})
public class OMIMMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Method")
    private String method;
    @ManyToMany
    @JoinTable(name = OMIMTables.OMIMGENEMAP_HAS_OMIMMETHOD,
    joinColumns =
    @JoinColumn(name = "OMIMMethod_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "OMIMGeneMap_WID", referencedColumnName = "WID"))
    private Set<OMIMGeneMap> oMIMGeneMaps;

    public OMIMMethod() {
    }

    public OMIMMethod(Long wid) {
        this.wid = wid;
    }

    public OMIMMethod(Long wid, String method) {
        this.wid = wid;
        this.method = method;
    }

    public Set<OMIMGeneMap> getoMIMGeneMaps() {
        return oMIMGeneMaps;
    }

    public void setoMIMGeneMaps(Set<OMIMGeneMap> oMIMGeneMaps) {
        this.oMIMGeneMaps = oMIMGeneMaps;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
        final OMIMMethod other = (OMIMMethod) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.method, other.method)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMMethod{" + "wid=" + wid + ", method=" + method + '}';
    }
}
