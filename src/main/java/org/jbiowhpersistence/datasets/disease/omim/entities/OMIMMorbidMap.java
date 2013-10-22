package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.disease.omim.OMIMTables;

/**
 * This Class is the OMIMMorbidMap entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMMorbidMap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMMorbidMap.findAll", query = "SELECT o FROM OMIMMorbidMap o"),
    @NamedQuery(name = "OMIMMorbidMap.findByWid", query = "SELECT o FROM OMIMMorbidMap o WHERE o.wid = :wid"),
    @NamedQuery(name = "OMIMMorbidMap.findByOmimId", query = "SELECT o FROM OMIMMorbidMap o WHERE o.omimId = :omimId"),
    @NamedQuery(name = "OMIMMorbidMap.findByDisorder", query = "SELECT o FROM OMIMMorbidMap o WHERE o.disorder = :disorder"),
    @NamedQuery(name = "OMIMMorbidMap.findByMIMNumber", query = "SELECT o FROM OMIMMorbidMap o WHERE o.mIMNumber = :mIMNumber"),
    @NamedQuery(name = "OMIMMorbidMap.findByCytogLog", query = "SELECT o FROM OMIMMorbidMap o WHERE o.cytogLog = :cytogLog")})
public class OMIMMorbidMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "OMIM_ID")
    private BigInteger omimId;
    @Basic(optional = false)
    @Column(name = "Disorder")
    private String disorder;
    @Column(name = "MIMNumber")
    private BigInteger mIMNumber;
    @Column(name = "CytogLog")
    private String cytogLog;
    @ManyToMany
    @JoinTable(name = OMIMTables.OMIMGENEMAP_HAS_OMIMMORBIDMAP,
    joinColumns =
    @JoinColumn(name = "OMIMMorbidMap_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "OMIMGeneMap_WID", referencedColumnName = "WID"))
    private Set<OMIMGeneMap> oMIMGeneMaps;

    public OMIMMorbidMap() {
    }

    public OMIMMorbidMap(Long wid) {
        this.wid = wid;
    }

    public OMIMMorbidMap(Long wid, String disorder) {
        this.wid = wid;
        this.disorder = disorder;
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

    public BigInteger getOmimId() {
        return omimId;
    }

    public void setOmimId(BigInteger omimId) {
        this.omimId = omimId;
    }

    public String getDisorder() {
        return disorder;
    }

    public void setDisorder(String disorder) {
        this.disorder = disorder;
    }

    public BigInteger getMIMNumber() {
        return mIMNumber;
    }

    public void setMIMNumber(BigInteger mIMNumber) {
        this.mIMNumber = mIMNumber;
    }

    public String getCytogLog() {
        return cytogLog;
    }

    public void setCytogLog(String cytogLog) {
        this.cytogLog = cytogLog;
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
        final OMIMMorbidMap other = (OMIMMorbidMap) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.omimId, other.omimId)) {
            return false;
        }
        if (!Objects.equals(this.disorder, other.disorder)) {
            return false;
        }
        if (!Objects.equals(this.mIMNumber, other.mIMNumber)) {
            return false;
        }
        if (!Objects.equals(this.cytogLog, other.cytogLog)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMMorbidMap{" + "wid=" + wid + ", omimId=" + omimId + ", disorder=" + disorder + ", mIMNumber=" + mIMNumber + ", cytogLog=" + cytogLog + '}';
    }
}
