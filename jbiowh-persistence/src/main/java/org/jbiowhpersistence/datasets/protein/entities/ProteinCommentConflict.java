package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the ProteinCommentConflict entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinCommentConflict")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinCommentConflict.findAll", query = "SELECT p FROM ProteinCommentConflict p"),
    @NamedQuery(name = "ProteinCommentConflict.findByProteinCommentWID", query = "SELECT p FROM ProteinCommentConflict p WHERE p.proteinCommentConflictPK.proteinCommentWID = :proteinCommentWID"),
    @NamedQuery(name = "ProteinCommentConflict.findByType", query = "SELECT p FROM ProteinCommentConflict p WHERE p.proteinCommentConflictPK.type = :type"),
    @NamedQuery(name = "ProteinCommentConflict.findBySeqVersion", query = "SELECT p FROM ProteinCommentConflict p WHERE p.seqVersion = :seqVersion"),
    @NamedQuery(name = "ProteinCommentConflict.findBySeqResource", query = "SELECT p FROM ProteinCommentConflict p WHERE p.seqResource = :seqResource"),
    @NamedQuery(name = "ProteinCommentConflict.findBySeqID", query = "SELECT p FROM ProteinCommentConflict p WHERE p.seqID = :seqID")})
public class ProteinCommentConflict implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinCommentConflictPK proteinCommentConflictPK;
    @Column(name = "SeqVersion")
    private Integer seqVersion;
    @Column(name = "SeqResource")
    private String seqResource;
    @Column(name = "SeqID")
    private String seqID;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinComment_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinComment proteinComment;

    public ProteinCommentConflict() {
    }

    public ProteinCommentConflict(ProteinCommentConflictPK proteinCommentConflictPK) {
        this.proteinCommentConflictPK = proteinCommentConflictPK;
    }

    public ProteinCommentConflict(long proteinCommentWID, String type) {
        this.proteinCommentConflictPK = new ProteinCommentConflictPK(proteinCommentWID, type);
    }

    public ProteinComment getProteinComment() {
        return proteinComment;
    }

    public void setProteinComment(ProteinComment proteinComment) {
        this.proteinComment = proteinComment;
    }

    public ProteinCommentConflictPK getProteinCommentConflictPK() {
        return proteinCommentConflictPK;
    }

    public void setProteinCommentConflictPK(ProteinCommentConflictPK proteinCommentConflictPK) {
        this.proteinCommentConflictPK = proteinCommentConflictPK;
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinCommentConflict other = (ProteinCommentConflict) obj;
        if (!Objects.equals(this.proteinCommentConflictPK, other.proteinCommentConflictPK)) {
            return false;
        }
        if (!Objects.equals(this.seqVersion, other.seqVersion)) {
            return false;
        }
        if (!Objects.equals(this.seqResource, other.seqResource)) {
            return false;
        }
        if (!Objects.equals(this.seqID, other.seqID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinCommentConflictPK != null ? proteinCommentConflictPK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProteinCommentConflict{"
                + " CommentWID=" + proteinCommentConflictPK.getProteinCommentWID()
                + " Type=" + proteinCommentConflictPK.getType()
                + " seqVersion=" + seqVersion
                + " seqResource=" + seqResource
                + " seqID=" + seqID
                + '}';
    }
}
