package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.disease.omim.OMIMTables;

/**
 * This Class is the OMIMGeneMap entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jul 16, 2012
 */
@Entity
@Table(name = "OMIMGeneMap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIMGeneMap.findAll", query = "SELECT o FROM OMIMGeneMap o"),
    @NamedQuery(name = "OMIMGeneMap.findByWid", query = "SELECT o FROM OMIMGeneMap o WHERE o.wid = :wid"),
    @NamedQuery(name = "OMIMGeneMap.findByNumber", query = "SELECT o FROM OMIMGeneMap o WHERE o.number = :number"),
    @NamedQuery(name = "OMIMGeneMap.findByMonth", query = "SELECT o FROM OMIMGeneMap o WHERE o.month = :month"),
    @NamedQuery(name = "OMIMGeneMap.findByDay", query = "SELECT o FROM OMIMGeneMap o WHERE o.day = :day"),
    @NamedQuery(name = "OMIMGeneMap.findByYear", query = "SELECT o FROM OMIMGeneMap o WHERE o.year = :year"),
    @NamedQuery(name = "OMIMGeneMap.findByCytogLoc", query = "SELECT o FROM OMIMGeneMap o WHERE o.cytogLoc = :cytogLoc"),
    @NamedQuery(name = "OMIMGeneMap.findByGeneStatus", query = "SELECT o FROM OMIMGeneMap o WHERE o.geneStatus = :geneStatus"),
    @NamedQuery(name = "OMIMGeneMap.findByMIMNumber", query = "SELECT o FROM OMIMGeneMap o WHERE o.mIMNumber = :mIMNumber"),
    @NamedQuery(name = "OMIMGeneMap.findByMouseCorr", query = "SELECT o FROM OMIMGeneMap o WHERE o.mouseCorr = :mouseCorr"),
    @NamedQuery(name = "OMIMGeneMap.findByReference", query = "SELECT o FROM OMIMGeneMap o WHERE o.reference = :reference")})
public class OMIMGeneMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Number")
    private String number;
    @Column(name = "Month")
    private String month;
    @Column(name = "Day")
    private String day;
    @Column(name = "Year")
    private String year;
    @Column(name = "CytogLoc")
    private String cytogLoc;
    @Column(name = "GeneStatus")
    private String geneStatus;
    @Lob
    @Column(name = "Title")
    private String title;
    @Column(name = "MIMNumber")
    private BigInteger mIMNumber;
    @Lob
    @Column(name = "Comments")
    private String comments;
    @Column(name = "MouseCorr")
    private String mouseCorr;
    @Column(name = "Reference")
    private String reference;
    @ManyToMany
    @JoinTable(name = OMIMTables.OMIMGENEMAP_HAS_OMIMMETHOD,
    joinColumns =
    @JoinColumn(name = "OMIMGeneMap_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "OMIMMethod_WID", referencedColumnName = "WID"))
    private Set<OMIMMethod> oMIMMethods;
    @ManyToMany
    @JoinTable(name = OMIMTables.OMIMGENEMAP_HAS_OMIMMORBIDMAP,
    joinColumns =
    @JoinColumn(name = "OMIMGeneMap_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "OMIMMorbidMap_WID", referencedColumnName = "WID"))
    private Set<OMIMMorbidMap> oMIMMorbidMaps;

    public OMIMGeneMap() {
    }

    public OMIMGeneMap(Long wid) {
        this.wid = wid;
    }

    public OMIMGeneMap(Long wid, String number) {
        this.wid = wid;
        this.number = number;
    }

    @XmlTransient
    public Set<OMIMMethod> getoMIMMethods() {
        return oMIMMethods;
    }

    public void setoMIMMethods(Set<OMIMMethod> oMIMMethods) {
        this.oMIMMethods = oMIMMethods;
    }

    @XmlTransient
    public Set<OMIMMorbidMap> getoMIMMorbidMaps() {
        return oMIMMorbidMaps;
    }

    public void setoMIMMorbidMaps(Set<OMIMMorbidMap> oMIMMorbidMaps) {
        this.oMIMMorbidMaps = oMIMMorbidMaps;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCytogLoc() {
        return cytogLoc;
    }

    public void setCytogLoc(String cytogLoc) {
        this.cytogLoc = cytogLoc;
    }

    public String getGeneStatus() {
        return geneStatus;
    }

    public void setGeneStatus(String geneStatus) {
        this.geneStatus = geneStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigInteger getMIMNumber() {
        return mIMNumber;
    }

    public void setMIMNumber(BigInteger mIMNumber) {
        this.mIMNumber = mIMNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMouseCorr() {
        return mouseCorr;
    }

    public void setMouseCorr(String mouseCorr) {
        this.mouseCorr = mouseCorr;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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
        final OMIMGeneMap other = (OMIMGeneMap) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        if (!Objects.equals(this.day, other.day)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.cytogLoc, other.cytogLoc)) {
            return false;
        }
        if (!Objects.equals(this.geneStatus, other.geneStatus)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.mIMNumber, other.mIMNumber)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.mouseCorr, other.mouseCorr)) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIMGeneMap{" + "wid=" + wid + ", number=" + number + ", month=" + month + ", day=" + day + ", year=" + year + ", cytogLoc=" + cytogLoc + ", geneStatus=" + geneStatus + ", title=" + title + ", mIMNumber=" + mIMNumber + ", comments=" + comments + ", mouseCorr=" + mouseCorr + ", reference=" + reference + '}';
    }
}
