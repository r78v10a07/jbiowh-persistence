package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Comment Interact entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinCommentInteract")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinCommentInteract.findAll", query = "SELECT p FROM ProteinCommentInteract p"),
    @NamedQuery(name = "ProteinCommentInteract.findByWid", query = "SELECT p FROM ProteinCommentInteract p WHERE p.proteinCommentInteractPK.wid = :wid"),
    @NamedQuery(name = "ProteinCommentInteract.findByProteinCommentWID", query = "SELECT p FROM ProteinCommentInteract p WHERE p.proteinCommentInteractPK.proteinCommentWID = :proteinCommentWID"),
    @NamedQuery(name = "ProteinCommentInteract.findByIntactID", query = "SELECT p FROM ProteinCommentInteract p WHERE p.intactID = :intactID"),
    @NamedQuery(name = "ProteinCommentInteract.findById", query = "SELECT p FROM ProteinCommentInteract p WHERE p.id = :id"),
    @NamedQuery(name = "ProteinCommentInteract.findByLabel", query = "SELECT p FROM ProteinCommentInteract p WHERE p.label = :label")})
public class ProteinCommentInteract implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinCommentInteractPK proteinCommentInteractPK;
    @Basic(optional = false)
    @Column(name = "IntactID")
    private String intactID;
    @Column(name = "Id")
    private String id;
    @Column(name = "Label")
    private String label;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinComment_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinComment proteinComment;

    public ProteinCommentInteract() {
    }

    public ProteinCommentInteract(ProteinCommentInteractPK proteinCommentInteractPK) {
        this.proteinCommentInteractPK = proteinCommentInteractPK;
    }

    public ProteinCommentInteract(ProteinCommentInteractPK proteinCommentInteractPK, String intactID) {
        this.proteinCommentInteractPK = proteinCommentInteractPK;
        this.intactID = intactID;
    }

    public ProteinCommentInteract(long wid, long proteinCommentWID) {
        this.proteinCommentInteractPK = new ProteinCommentInteractPK(wid, proteinCommentWID);
    }

    public ProteinComment getProteinComment() {
        return proteinComment;
    }

    public void setProteinComment(ProteinComment proteinComment) {
        this.proteinComment = proteinComment;
    }

    public ProteinCommentInteractPK getProteinCommentInteractPK() {
        return proteinCommentInteractPK;
    }

    public void setProteinCommentInteractPK(ProteinCommentInteractPK proteinCommentInteractPK) {
        this.proteinCommentInteractPK = proteinCommentInteractPK;
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinCommentInteract other = (ProteinCommentInteract) obj;
        if (!Objects.equals(this.proteinCommentInteractPK, other.proteinCommentInteractPK)) {
            return false;
        }
        if (!Objects.equals(this.intactID, other.intactID)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinCommentInteractPK != null ? proteinCommentInteractPK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProteinCommentInteract{"
                + " commentWID=" + proteinCommentInteractPK.getProteinCommentWID()
                + " intactID=" + intactID
                + " id=" + id
                + " label=" + label
                + '}';
    }
}
