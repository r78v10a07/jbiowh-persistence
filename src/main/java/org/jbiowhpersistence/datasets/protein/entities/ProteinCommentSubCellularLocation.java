package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinCommentSubCellularLocation")
@XmlRootElement
public class ProteinCommentSubCellularLocation implements Serializable {

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

    public ProteinCommentSubCellularLocation() {
    }

    public ProteinCommentSubCellularLocation(long proteinCommentWID, String data, String element, String evidence, String status) {
        this.proteinCommentWID = proteinCommentWID;
        this.data = data;
        this.element = element;
        this.evidence = evidence;
        this.status = status;
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
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.proteinCommentWID ^ (this.proteinCommentWID >>> 32));
        hash = 53 * hash + (this.data != null ? this.data.hashCode() : 0);
        hash = 53 * hash + (this.element != null ? this.element.hashCode() : 0);
        hash = 53 * hash + (this.evidence != null ? this.evidence.hashCode() : 0);
        hash = 53 * hash + (this.status != null ? this.status.hashCode() : 0);
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
        final ProteinCommentSubCellularLocation other = (ProteinCommentSubCellularLocation) obj;
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        if ((this.data == null) ? (other.data != null) : !this.data.equals(other.data)) {
            return false;
        }
        if ((this.element == null) ? (other.element != null) : !this.element.equals(other.element)) {
            return false;
        }
        if ((this.evidence == null) ? (other.evidence != null) : !this.evidence.equals(other.evidence)) {
            return false;
        }
        return !((this.status == null) ? (other.status != null) : !this.status.equals(other.status));
    }

    @Override
    public String toString() {
        return "ProteinCommentSubCellularLocation{" + "proteinCommentWID=" + proteinCommentWID + ", data=" + data + ", element=" + element + ", evidence=" + evidence + ", status=" + status + '}';
    }
}
