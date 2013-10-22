package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the ProteinCommentEventPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Embeddable
public class ProteinCommentEventPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinComment_WID")
    private long proteinCommentWID;
    @Basic(optional = false)
    @Column(name = "EventType")
    private String eventType;

    public ProteinCommentEventPK() {
    }

    public ProteinCommentEventPK(long proteinCommentWID, String eventType) {
        this.proteinCommentWID = proteinCommentWID;
        this.eventType = eventType;
    }

    public long getProteinCommentWID() {
        return proteinCommentWID;
    }

    public void setProteinCommentWID(long proteinCommentWID) {
        this.proteinCommentWID = proteinCommentWID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proteinCommentWID;
        hash += (eventType != null ? eventType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinCommentEventPK)) {
            return false;
        }
        ProteinCommentEventPK other = (ProteinCommentEventPK) object;
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        if ((this.eventType == null && other.eventType != null) || (this.eventType != null && !this.eventType.equals(other.eventType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinCommentEventPK[ proteinCommentWID=" + proteinCommentWID + ", eventType=" + eventType + " ]";
    }
}
