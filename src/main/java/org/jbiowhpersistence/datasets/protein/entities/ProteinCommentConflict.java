package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the ProteinCommentConflict entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinCommentConflict")
@XmlRootElement
public class ProteinCommentConflict implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinComment_WID")
    private long proteinCommentWID;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @Column(name = "SeqVersion")
    private Integer seqVersion;
    @Column(name = "SeqResource")
    private String seqResource;
    @Column(name = "SeqID")
    private String seqID;

    public ProteinCommentConflict() {
    }

    public ProteinCommentConflict(long proteinCommentWID, String type, Integer seqVersion, String seqResource, String seqID) {
        this.proteinCommentWID = proteinCommentWID;
        this.type = type;
        this.seqVersion = seqVersion;
        this.seqResource = seqResource;
        this.seqID = seqID;
    }

    public long getProteinCommentWID() {
        return proteinCommentWID;
    }

    public void setProteinCommentWID(long proteinCommentWID) {
        this.proteinCommentWID = proteinCommentWID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSeqVersion() {
        return seqVersion;
    }

    public void setSeqVersion(Integer seqVersion) {
        this.seqVersion = seqVersion;
    }

    public String getSeqResource() {
        return seqResource;
    }

    public void setSeqResource(String seqResource) {
        this.seqResource = seqResource;
    }

    public String getSeqID() {
        return seqID;
    }

    public void setSeqID(String seqID) {
        this.seqID = seqID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.proteinCommentWID ^ (this.proteinCommentWID >>> 32));
        hash = 41 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 41 * hash + (this.seqVersion != null ? this.seqVersion.hashCode() : 0);
        hash = 41 * hash + (this.seqResource != null ? this.seqResource.hashCode() : 0);
        hash = 41 * hash + (this.seqID != null ? this.seqID.hashCode() : 0);
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
        final ProteinCommentConflict other = (ProteinCommentConflict) obj;
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if (this.seqVersion != other.seqVersion && (this.seqVersion == null || !this.seqVersion.equals(other.seqVersion))) {
            return false;
        }
        if ((this.seqResource == null) ? (other.seqResource != null) : !this.seqResource.equals(other.seqResource)) {
            return false;
        }
        return !((this.seqID == null) ? (other.seqID != null) : !this.seqID.equals(other.seqID));
    }

    @Override
    public String toString() {
        return "ProteinCommentConflict{" + "proteinCommentWID=" + proteinCommentWID + ", type=" + type + ", seqVersion=" + seqVersion + ", seqResource=" + seqResource + ", seqID=" + seqID + '}';
    }
}
