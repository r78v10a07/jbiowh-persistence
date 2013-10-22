package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinCommentSubCellularLocation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinCommentSubCellularLocation.findAll", query = "SELECT p FROM ProteinCommentSubCellularLocation p"),
    @NamedQuery(name = "ProteinCommentSubCellularLocation.findByWid", query = "SELECT p FROM ProteinCommentSubCellularLocation p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinCommentSubCellularLocation.findByProteinCommentWID", query = "SELECT p FROM ProteinCommentSubCellularLocation p WHERE p.proteinCommentWID = :proteinCommentWID"),
    @NamedQuery(name = "ProteinCommentSubCellularLocation.findByData", query = "SELECT p FROM ProteinCommentSubCellularLocation p WHERE p.data = :data"),
    @NamedQuery(name = "ProteinCommentSubCellularLocation.findByElement", query = "SELECT p FROM ProteinCommentSubCellularLocation p WHERE p.element = :element"),
    @NamedQuery(name = "ProteinCommentSubCellularLocation.findByEvidence", query = "SELECT p FROM ProteinCommentSubCellularLocation p WHERE p.evidence = :evidence"),
    @NamedQuery(name = "ProteinCommentSubCellularLocation.findByStatus", query = "SELECT p FROM ProteinCommentSubCellularLocation p WHERE p.status = :status")})
public class ProteinCommentSubCellularLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "ProteinComment_WID")
    private long proteinCommentWID;
    @Column(name = "Data")
    private String data;
    @Column(name = "Element")
    private String element;
    @Column(name = "Evidence")
    private String evidence;
    @Column(name = "Status")
    private String status;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinComment_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinComment proteinComment;

    public ProteinCommentSubCellularLocation() {
    }

    public ProteinCommentSubCellularLocation(Long wid) {
        this.wid = wid;
    }

    public ProteinCommentSubCellularLocation(Long wid, long proteinCommentWID) {
        this.wid = wid;
        this.proteinCommentWID = proteinCommentWID;
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

    public long getProteinCommentWID() {
        return proteinCommentWID;
    }

    public void setProteinCommentWID(long proteinCommentWID) {
        this.proteinCommentWID = proteinCommentWID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinCommentSubCellularLocation other = (ProteinCommentSubCellularLocation) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.element, other.element)) {
            return false;
        }
        if (!Objects.equals(this.evidence, other.evidence)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
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
        return "ProteinCommentSubCellularLocation{"
                + " wid=" + wid
                + " commentWID=" + proteinCommentWID
                + " data=" + data
                + " element=" + element
                + " evidence=" + evidence
                + " status=" + status
                + '}';
    }
}
