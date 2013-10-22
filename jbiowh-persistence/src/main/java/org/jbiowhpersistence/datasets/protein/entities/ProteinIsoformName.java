package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Isoform Name entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinIsoformName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinIsoformName.findAll", query = "SELECT p FROM ProteinIsoformName p"),
    @NamedQuery(name = "ProteinIsoformName.findByProteinCommentIsoformWID", query = "SELECT p FROM ProteinIsoformName p WHERE p.proteinIsoformNamePK.proteinCommentIsoformWID = :proteinCommentIsoformWID"),
    @NamedQuery(name = "ProteinIsoformName.findByName", query = "SELECT p FROM ProteinIsoformName p WHERE p.proteinIsoformNamePK.name = :name"),
    @NamedQuery(name = "ProteinIsoformName.findByEvidence", query = "SELECT p FROM ProteinIsoformName p WHERE p.evidence = :evidence")})
public class ProteinIsoformName implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinIsoformNamePK proteinIsoformNamePK;
    @Column(name = "Evidence")
    private String evidence;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinCommentIsoform_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinCommentIsoform proteinCommentIsoform;

    public ProteinIsoformName() {
    }

    public ProteinIsoformName(ProteinIsoformNamePK proteinIsoformNamePK) {
        this.proteinIsoformNamePK = proteinIsoformNamePK;
    }

    public ProteinIsoformName(long proteinCommentIsoformWID, String name) {
        this.proteinIsoformNamePK = new ProteinIsoformNamePK(proteinCommentIsoformWID, name);
    }

    public ProteinCommentIsoform getProteinCommentIsoform() {
        return proteinCommentIsoform;
    }

    public void setProteinCommentIsoform(ProteinCommentIsoform proteinCommentIsoform) {
        this.proteinCommentIsoform = proteinCommentIsoform;
    }

    public ProteinIsoformNamePK getProteinIsoformNamePK() {
        return proteinIsoformNamePK;
    }

    public void setProteinIsoformNamePK(ProteinIsoformNamePK proteinIsoformNamePK) {
        this.proteinIsoformNamePK = proteinIsoformNamePK;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinIsoformName other = (ProteinIsoformName) obj;
        if (!Objects.equals(this.proteinIsoformNamePK, other.proteinIsoformNamePK)) {
            return false;
        }
        if (!Objects.equals(this.evidence, other.evidence)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinIsoformNamePK != null ? proteinIsoformNamePK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProteinIsoformName{"
                + " IsoformWID=" + proteinIsoformNamePK.getProteinCommentIsoformWID()
                + " Name=" + proteinIsoformNamePK.getName()
                + " evidence=" + evidence
                + '}';
    }
}
