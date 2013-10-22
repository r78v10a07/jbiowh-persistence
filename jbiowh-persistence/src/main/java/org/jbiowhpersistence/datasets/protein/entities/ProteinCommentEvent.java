package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Comment Event entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinCommentEvent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinCommentEvent.findAll", query = "SELECT p FROM ProteinCommentEvent p"),
    @NamedQuery(name = "ProteinCommentEvent.findByProteinCommentWID", query = "SELECT p FROM ProteinCommentEvent p WHERE p.proteinCommentEventPK.proteinCommentWID = :proteinCommentWID"),
    @NamedQuery(name = "ProteinCommentEvent.findByEventType", query = "SELECT p FROM ProteinCommentEvent p WHERE p.proteinCommentEventPK.eventType = :eventType")})
public class ProteinCommentEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinCommentEventPK proteinCommentEventPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinComment_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinComment proteinComment;

    public ProteinCommentEvent() {
    }

    public ProteinCommentEvent(ProteinCommentEventPK proteinCommentEventPK) {
        this.proteinCommentEventPK = proteinCommentEventPK;
    }

    public ProteinCommentEvent(long proteinCommentWID, String eventType) {
        this.proteinCommentEventPK = new ProteinCommentEventPK(proteinCommentWID, eventType);
    }

    public ProteinComment getProteinComment() {
        return proteinComment;
    }

    public void setProteinComment(ProteinComment proteinComment) {
        this.proteinComment = proteinComment;
    }

    public ProteinCommentEventPK getProteinCommentEventPK() {
        return proteinCommentEventPK;
    }

    public void setProteinCommentEventPK(ProteinCommentEventPK proteinCommentEventPK) {
        this.proteinCommentEventPK = proteinCommentEventPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinCommentEventPK != null ? proteinCommentEventPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinCommentEvent)) {
            return false;
        }
        ProteinCommentEvent other = (ProteinCommentEvent) object;
        if ((this.proteinCommentEventPK == null && other.proteinCommentEventPK != null) || (this.proteinCommentEventPK != null && !this.proteinCommentEventPK.equals(other.proteinCommentEventPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinCommentEvent{"
                + " CommentWID=" + proteinCommentEventPK.getProteinCommentWID()
                + " EventType=" + proteinCommentEventPK.getEventType()
                + '}';
    }
}
