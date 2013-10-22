package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFFeatureFeatureRange entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFFeatureFeatureRange")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFFeatureFeatureRange.findAll", query = "SELECT m FROM MIFFeatureFeatureRange m"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByWid", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByMIFParticipantFeatureWID", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.mIFParticipantFeatureWID = :mIFParticipantFeatureWID"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByStartShortLabel", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.startShortLabel = :startShortLabel"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByStartFullName", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.startFullName = :startFullName"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByEndShortLabel", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.endShortLabel = :endShortLabel"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByEndFullName", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.endFullName = :endFullName"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByBeginPosition", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.beginPosition = :beginPosition"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByBeginIntervalBegin", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.beginIntervalBegin = :beginIntervalBegin"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByBeginIntervalEnd", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.beginIntervalEnd = :beginIntervalEnd"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByEndPosition", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.endPosition = :endPosition"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByEndIntervalBegin", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.endIntervalBegin = :endIntervalBegin"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByEndIntervalEnd", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.endIntervalEnd = :endIntervalEnd"),
    @NamedQuery(name = "MIFFeatureFeatureRange.findByIsLink", query = "SELECT m FROM MIFFeatureFeatureRange m WHERE m.isLink = :isLink")})
public class MIFFeatureFeatureRange implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFParticipantFeature_WID")
    private long mIFParticipantFeatureWID;
    @Column(name = "StartShortLabel")
    private String startShortLabel;
    @Column(name = "StartFullName")
    private String startFullName;
    @Column(name = "EndShortLabel")
    private String endShortLabel;
    @Column(name = "EndFullName")
    private String endFullName;
    @Column(name = "BeginPosition")
    private Integer beginPosition;
    @Column(name = "BeginIntervalBegin")
    private Integer beginIntervalBegin;
    @Column(name = "BeginIntervalEnd")
    private Integer beginIntervalEnd;
    @Column(name = "EndPosition")
    private Integer endPosition;
    @Column(name = "EndIntervalBegin")
    private Integer endIntervalBegin;
    @Column(name = "EndIntervalEnd")
    private Integer endIntervalEnd;
    @Column(name = "IsLink")
    private Boolean isLink;
    @ManyToOne
    @JoinColumn(name = "MIFParticipantFeature_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantFeature mifParticipantFeature;

    public MIFFeatureFeatureRange() {
    }

    public MIFFeatureFeatureRange(Long wid) {
        this.wid = wid;
    }

    public MIFFeatureFeatureRange(Long wid, long mIFParticipantFeatureWID) {
        this.wid = wid;
        this.mIFParticipantFeatureWID = mIFParticipantFeatureWID;
    }

    public long getmIFParticipantFeatureWID() {
        return mIFParticipantFeatureWID;
    }

    public void setmIFParticipantFeatureWID(long mIFParticipantFeatureWID) {
        this.mIFParticipantFeatureWID = mIFParticipantFeatureWID;
    }

    public MIFParticipantFeature getMifParticipantFeature() {
        return mifParticipantFeature;
    }

    public void setMifParticipantFeature(MIFParticipantFeature mifParticipantFeature) {
        this.mifParticipantFeature = mifParticipantFeature;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getMIFParticipantFeatureWID() {
        return mIFParticipantFeatureWID;
    }

    public void setMIFParticipantFeatureWID(long mIFParticipantFeatureWID) {
        this.mIFParticipantFeatureWID = mIFParticipantFeatureWID;
    }

    public String getStartShortLabel() {
        return startShortLabel;
    }

    public void setStartShortLabel(String startShortLabel) {
        this.startShortLabel = startShortLabel;
    }

    public String getStartFullName() {
        return startFullName;
    }

    public void setStartFullName(String startFullName) {
        this.startFullName = startFullName;
    }

    public String getEndShortLabel() {
        return endShortLabel;
    }

    public void setEndShortLabel(String endShortLabel) {
        this.endShortLabel = endShortLabel;
    }

    public String getEndFullName() {
        return endFullName;
    }

    public void setEndFullName(String endFullName) {
        this.endFullName = endFullName;
    }

    public Integer getBeginPosition() {
        return beginPosition;
    }

    public void setBeginPosition(Integer beginPosition) {
        this.beginPosition = beginPosition;
    }

    public Integer getBeginIntervalBegin() {
        return beginIntervalBegin;
    }

    public void setBeginIntervalBegin(Integer beginIntervalBegin) {
        this.beginIntervalBegin = beginIntervalBegin;
    }

    public Integer getBeginIntervalEnd() {
        return beginIntervalEnd;
    }

    public void setBeginIntervalEnd(Integer beginIntervalEnd) {
        this.beginIntervalEnd = beginIntervalEnd;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }

    public Integer getEndIntervalBegin() {
        return endIntervalBegin;
    }

    public void setEndIntervalBegin(Integer endIntervalBegin) {
        this.endIntervalBegin = endIntervalBegin;
    }

    public Integer getEndIntervalEnd() {
        return endIntervalEnd;
    }

    public void setEndIntervalEnd(Integer endIntervalEnd) {
        this.endIntervalEnd = endIntervalEnd;
    }

    public Boolean getIsLink() {
        return isLink;
    }

    public void setIsLink(Boolean isLink) {
        this.isLink = isLink;
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
        final MIFFeatureFeatureRange other = (MIFFeatureFeatureRange) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFParticipantFeatureWID != other.mIFParticipantFeatureWID) {
            return false;
        }
        if (!Objects.equals(this.startShortLabel, other.startShortLabel)) {
            return false;
        }
        if (!Objects.equals(this.startFullName, other.startFullName)) {
            return false;
        }
        if (!Objects.equals(this.endShortLabel, other.endShortLabel)) {
            return false;
        }
        if (!Objects.equals(this.endFullName, other.endFullName)) {
            return false;
        }
        if (!Objects.equals(this.beginPosition, other.beginPosition)) {
            return false;
        }
        if (!Objects.equals(this.beginIntervalBegin, other.beginIntervalBegin)) {
            return false;
        }
        if (!Objects.equals(this.beginIntervalEnd, other.beginIntervalEnd)) {
            return false;
        }
        if (!Objects.equals(this.endPosition, other.endPosition)) {
            return false;
        }
        if (!Objects.equals(this.endIntervalBegin, other.endIntervalBegin)) {
            return false;
        }
        if (!Objects.equals(this.endIntervalEnd, other.endIntervalEnd)) {
            return false;
        }
        if (!Objects.equals(this.isLink, other.isLink)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFFeatureFeatureRange{" + "wid=" + wid + ", mIFParticipantFeatureWID=" + mIFParticipantFeatureWID + ", startShortLabel=" + startShortLabel + ", startFullName=" + startFullName + ", endShortLabel=" + endShortLabel + ", endFullName=" + endFullName + ", beginPosition=" + beginPosition + ", beginIntervalBegin=" + beginIntervalBegin + ", beginIntervalEnd=" + beginIntervalEnd + ", endPosition=" + endPosition + ", endIntervalBegin=" + endIntervalBegin + ", endIntervalEnd=" + endIntervalEnd + ", isLink=" + isLink + '}';
    }
}
