package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Isoform Id entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinIsoformId")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinIsoformId.findAll", query = "SELECT p FROM ProteinIsoformId p"),
    @NamedQuery(name = "ProteinIsoformId.findByProteinCommentIsoformWID", query = "SELECT p FROM ProteinIsoformId p WHERE p.proteinIsoformIdPK.proteinCommentIsoformWID = :proteinCommentIsoformWID"),
    @NamedQuery(name = "ProteinIsoformId.findById", query = "SELECT p FROM ProteinIsoformId p WHERE p.proteinIsoformIdPK.id = :id")})
public class ProteinIsoformId implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinIsoformIdPK proteinIsoformIdPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinCommentIsoform_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinCommentIsoform proteinCommentIsoform;

    public ProteinIsoformId() {
    }

    public ProteinIsoformId(ProteinIsoformIdPK proteinIsoformIdPK) {
        this.proteinIsoformIdPK = proteinIsoformIdPK;
    }

    public ProteinIsoformId(long proteinCommentIsoformWID, String id) {
        this.proteinIsoformIdPK = new ProteinIsoformIdPK(proteinCommentIsoformWID, id);
    }

    public ProteinCommentIsoform getProteinCommentIsoform() {
        return proteinCommentIsoform;
    }

    public void setProteinCommentIsoform(ProteinCommentIsoform proteinCommentIsoform) {
        this.proteinCommentIsoform = proteinCommentIsoform;
    }

    public ProteinIsoformIdPK getProteinIsoformIdPK() {
        return proteinIsoformIdPK;
    }

    public void setProteinIsoformIdPK(ProteinIsoformIdPK proteinIsoformIdPK) {
        this.proteinIsoformIdPK = proteinIsoformIdPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinIsoformIdPK != null ? proteinIsoformIdPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinIsoformId)) {
            return false;
        }
        ProteinIsoformId other = (ProteinIsoformId) object;
        if ((this.proteinIsoformIdPK == null && other.proteinIsoformIdPK != null) || (this.proteinIsoformIdPK != null && !this.proteinIsoformIdPK.equals(other.proteinIsoformIdPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinIsoformId{"
                + " IsoformWID=" + proteinIsoformIdPK.getProteinCommentIsoformWID()
                + " Id=" + proteinIsoformIdPK.getId()
                + '}';
    }
}
