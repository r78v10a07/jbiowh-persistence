package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Comment Event entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinCommentEvent")
@XmlRootElement
public class ProteinCommentEvent implements Serializable {

    @Basic(optional = false)
    @Column(name = "EventType")
    private String eventType;

    public ProteinCommentEvent() {
    }

    public ProteinCommentEvent(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.eventType != null ? this.eventType.hashCode() : 0);
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
        final ProteinCommentEvent other = (ProteinCommentEvent) obj;
        return !((this.eventType == null) ? (other.eventType != null) : !this.eventType.equals(other.eventType));
    }

    @Override
    public String toString() {
        return "ProteinCommentEvent{eventType=" + eventType + '}';
    }
}
