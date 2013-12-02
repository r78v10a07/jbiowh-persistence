package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Comment Interact entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinCommentInteract")
@XmlRootElement
public class ProteinCommentInteract implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinComment_WID")
    private long proteinCommentWID;
    @Basic(optional = false)
    @Column(name = "IntactID")
    private String intactID;
    @Column(name = "Id")
    private String id;
    @Column(name = "Label")
    private String label;

    public ProteinCommentInteract() {
    }

    public ProteinCommentInteract(long proteinCommentWID, String intactID, String id, String label) {
        this.proteinCommentWID = proteinCommentWID;
        this.intactID = intactID;
        this.id = id;
        this.label = label;
    }

    public long getProteinCommentWID() {
        return proteinCommentWID;
    }

    public void setProteinCommentWID(long proteinCommentWID) {
        this.proteinCommentWID = proteinCommentWID;
    }

    public String getIntactID() {
        return intactID;
    }

    public void setIntactID(String intactID) {
        this.intactID = intactID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.proteinCommentWID ^ (this.proteinCommentWID >>> 32));
        hash = 29 * hash + (this.intactID != null ? this.intactID.hashCode() : 0);
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.label != null ? this.label.hashCode() : 0);
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
        final ProteinCommentInteract other = (ProteinCommentInteract) obj;
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        if ((this.intactID == null) ? (other.intactID != null) : !this.intactID.equals(other.intactID)) {
            return false;
        }
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return !((this.label == null) ? (other.label != null) : !this.label.equals(other.label));
    }

    @Override
    public String toString() {
        return "ProteinCommentInteract{" + "proteinCommentWID=" + proteinCommentWID + ", intactID=" + intactID + ", id=" + id + ", label=" + label + '}';
    }

}
