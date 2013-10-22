package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Comment Link entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinCommentLink")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinCommentLink.findAll", query = "SELECT p FROM ProteinCommentLink p"),
    @NamedQuery(name = "ProteinCommentLink.findByProteinCommentWID", query = "SELECT p FROM ProteinCommentLink p WHERE p.proteinCommentLinkPK.proteinCommentWID = :proteinCommentWID"),
    @NamedQuery(name = "ProteinCommentLink.findByUri", query = "SELECT p FROM ProteinCommentLink p WHERE p.proteinCommentLinkPK.uri = :uri")})
public class ProteinCommentLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinCommentLinkPK proteinCommentLinkPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinComment_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinComment proteinComment;

    public ProteinCommentLink() {
    }

    public ProteinCommentLink(ProteinCommentLinkPK proteinCommentLinkPK) {
        this.proteinCommentLinkPK = proteinCommentLinkPK;
    }

    public ProteinCommentLink(long proteinCommentWID, String uri) {
        this.proteinCommentLinkPK = new ProteinCommentLinkPK(proteinCommentWID, uri);
    }

    public ProteinComment getProteinComment() {
        return proteinComment;
    }

    public void setProteinComment(ProteinComment proteinComment) {
        this.proteinComment = proteinComment;
    }

    public ProteinCommentLinkPK getProteinCommentLinkPK() {
        return proteinCommentLinkPK;
    }

    public void setProteinCommentLinkPK(ProteinCommentLinkPK proteinCommentLinkPK) {
        this.proteinCommentLinkPK = proteinCommentLinkPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinCommentLinkPK != null ? proteinCommentLinkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinCommentLink)) {
            return false;
        }
        ProteinCommentLink other = (ProteinCommentLink) object;
        if ((this.proteinCommentLinkPK == null && other.proteinCommentLinkPK != null) || (this.proteinCommentLinkPK != null && !this.proteinCommentLinkPK.equals(other.proteinCommentLinkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinCommentLink{"
                + " CommentWID=" + proteinCommentLinkPK.getProteinCommentWID()
                + " Uri=" + proteinCommentLinkPK.getUri()
                + '}';
    }
}
