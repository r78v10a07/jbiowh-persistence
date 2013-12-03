package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Comment Link entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinCommentLink")
@XmlRootElement
public class ProteinCommentLink implements Serializable {

    @Basic(optional = false)
    @Column(name = "URI")
    private String uri;

    public ProteinCommentLink() {
    }

    public ProteinCommentLink(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.uri != null ? this.uri.hashCode() : 0);
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
        final ProteinCommentLink other = (ProteinCommentLink) obj;
        return !((this.uri == null) ? (other.uri != null) : !this.uri.equals(other.uri));
    }

    @Override
    public String toString() {
        return "ProteinCommentLink{uri=" + uri + '}';
    }
}
