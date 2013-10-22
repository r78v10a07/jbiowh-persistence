package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Other Location entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinOtherLocation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinOtherLocation.findAll", query = "SELECT p FROM ProteinOtherLocation p"),
    @NamedQuery(name = "ProteinOtherLocation.findByWid", query = "SELECT p FROM ProteinOtherLocation p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinOtherLocation.findByOtherWID", query = "SELECT p FROM ProteinOtherLocation p WHERE p.otherWID = :otherWID"),
    @NamedQuery(name = "ProteinOtherLocation.findByBeginPos", query = "SELECT p FROM ProteinOtherLocation p WHERE p.beginPos = :beginPos"),
    @NamedQuery(name = "ProteinOtherLocation.findByBeginStatus", query = "SELECT p FROM ProteinOtherLocation p WHERE p.beginStatus = :beginStatus"),
    @NamedQuery(name = "ProteinOtherLocation.findByEndPos", query = "SELECT p FROM ProteinOtherLocation p WHERE p.endPos = :endPos"),
    @NamedQuery(name = "ProteinOtherLocation.findByEndStatus", query = "SELECT p FROM ProteinOtherLocation p WHERE p.endStatus = :endStatus"),
    @NamedQuery(name = "ProteinOtherLocation.findByPosition", query = "SELECT p FROM ProteinOtherLocation p WHERE p.position = :position"),
    @NamedQuery(name = "ProteinOtherLocation.findByPositionStatus", query = "SELECT p FROM ProteinOtherLocation p WHERE p.positionStatus = :positionStatus"),
    @NamedQuery(name = "ProteinOtherLocation.findBySequence", query = "SELECT p FROM ProteinOtherLocation p WHERE p.sequence = :sequence"),
    @NamedQuery(name = "ProteinOtherLocation.findByDataSetWID", query = "SELECT p FROM ProteinOtherLocation p WHERE p.dataSetWID = :dataSetWID")})
public class ProteinOtherLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "OtherWID")
    private long otherWID;
    @Column(name = "BeginPos")
    private Integer beginPos;
    @Column(name = "BeginStatus")
    private String beginStatus;
    @Column(name = "EndPos")
    private Integer endPos;
    @Column(name = "EndStatus")
    private String endStatus;
    @Column(name = "Position")
    private Integer position;
    @Column(name = "PositionStatus")
    private String positionStatus;
    @Column(name = "Sequence")
    private String sequence;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinComment proteinComment;

    public ProteinOtherLocation() {
    }

    public ProteinOtherLocation(Long wid) {
        this.wid = wid;
    }

    public ProteinOtherLocation(Long wid, long otherWID, long dataSetWID) {
        this.wid = wid;
        this.otherWID = otherWID;
        this.dataSetWID = dataSetWID;
    }

    public ProteinComment getProteinComment() {
        return proteinComment;
    }

    public void setProteinComment(ProteinComment proteinComment) {
        this.proteinComment = proteinComment;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getOtherWID() {
        return otherWID;
    }

    public void setOtherWID(long otherWID) {
        this.otherWID = otherWID;
    }

    public Integer getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(Integer beginPos) {
        this.beginPos = beginPos;
    }

    public String getBeginStatus() {
        return beginStatus;
    }

    public void setBeginStatus(String beginStatus) {
        this.beginStatus = beginStatus;
    }

    public Integer getEndPos() {
        return endPos;
    }

    public void setEndPos(Integer endPos) {
        this.endPos = endPos;
    }

    public String getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(String endStatus) {
        this.endStatus = endStatus;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(String positionStatus) {
        this.positionStatus = positionStatus;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinOtherLocation other = (ProteinOtherLocation) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.otherWID != other.otherWID) {
            return false;
        }
        if (!Objects.equals(this.beginPos, other.beginPos)) {
            return false;
        }
        if (!Objects.equals(this.beginStatus, other.beginStatus)) {
            return false;
        }
        if (!Objects.equals(this.endPos, other.endPos)) {
            return false;
        }
        if (!Objects.equals(this.endStatus, other.endStatus)) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.positionStatus, other.positionStatus)) {
            return false;
        }
        if (!Objects.equals(this.sequence, other.sequence)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProteinOtherLocation{"
                + " wid=" + wid
                + " otherWID=" + otherWID
                + " beginPos=" + beginPos
                + " beginStatus=" + beginStatus
                + " endPos=" + endPos
                + " endStatus=" + endStatus
                + " position=" + position
                + " positionStatus=" + positionStatus
                + " sequence=" + sequence
                + " dataSetWID=" + dataSetWID + '}';
    }
}
