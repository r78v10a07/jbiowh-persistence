package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamA_has_PfamLiteratureReferences entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $
 * $LastChangedRevision: 377 $
 * @since Nov 26, 2012
 */
@Entity
@Table(name = "PfamA_has_PfamLiteratureReferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamAhasPfamLiteratureReferences.findAll", query = "SELECT p FROM PfamAhasPfamLiteratureReferences p"),
    @NamedQuery(name = "PfamAhasPfamLiteratureReferences.findByPfamAWID", query = "SELECT p FROM PfamAhasPfamLiteratureReferences p WHERE p.pfamAhasPfamLiteratureReferencesPK.pfamAWID = :pfamAWID"),
    @NamedQuery(name = "PfamAhasPfamLiteratureReferences.findByPfamLiteratureReferencesWID", query = "SELECT p FROM PfamAhasPfamLiteratureReferences p WHERE p.pfamAhasPfamLiteratureReferencesPK.pfamLiteratureReferencesWID = :pfamLiteratureReferencesWID"),
    @NamedQuery(name = "PfamAhasPfamLiteratureReferences.findByOrderAdded", query = "SELECT p FROM PfamAhasPfamLiteratureReferences p WHERE p.pfamAhasPfamLiteratureReferencesPK.orderAdded = :orderAdded"),
    @NamedQuery(name = "PfamAhasPfamLiteratureReferences.findByComment", query = "SELECT p FROM PfamAhasPfamLiteratureReferences p WHERE p.comment = :comment")})
public class PfamAhasPfamLiteratureReferences implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PfamAhasPfamLiteratureReferencesPK pfamAhasPfamLiteratureReferencesPK;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "PfamA_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamAbioWH pfamA;
    @ManyToOne
    @JoinColumn(name = "PfamLiteratureReferences_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamLiteratureReferences pfamLiteratureReferences;

    public PfamAhasPfamLiteratureReferences() {
    }

    public PfamAhasPfamLiteratureReferences(PfamAhasPfamLiteratureReferencesPK pfamAhasPfamLiteratureReferencesPK) {
        this.pfamAhasPfamLiteratureReferencesPK = pfamAhasPfamLiteratureReferencesPK;
    }

    public PfamAhasPfamLiteratureReferences(long pfamAWID, long pfamLiteratureReferencesWID, short orderAdded) {
        this.pfamAhasPfamLiteratureReferencesPK = new PfamAhasPfamLiteratureReferencesPK(pfamAWID, pfamLiteratureReferencesWID, orderAdded);
    }

    public PfamAbioWH getPfamA() {
        return pfamA;
    }

    public void setPfamA(PfamAbioWH pfamA) {
        this.pfamA = pfamA;
    }

    public PfamLiteratureReferences getPfamLiteratureReferences() {
        return pfamLiteratureReferences;
    }

    public void setPfamLiteratureReferences(PfamLiteratureReferences pfamLiteratureReferences) {
        this.pfamLiteratureReferences = pfamLiteratureReferences;
    }

    public PfamAhasPfamLiteratureReferencesPK getPfamAhasPfamLiteratureReferencesPK() {
        return pfamAhasPfamLiteratureReferencesPK;
    }

    public void setPfamAhasPfamLiteratureReferencesPK(PfamAhasPfamLiteratureReferencesPK pfamAhasPfamLiteratureReferencesPK) {
        this.pfamAhasPfamLiteratureReferencesPK = pfamAhasPfamLiteratureReferencesPK;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfamAhasPfamLiteratureReferencesPK != null ? pfamAhasPfamLiteratureReferencesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PfamAhasPfamLiteratureReferences)) {
            return false;
        }
        PfamAhasPfamLiteratureReferences other = (PfamAhasPfamLiteratureReferences) object;
        if ((this.pfamAhasPfamLiteratureReferencesPK == null && other.pfamAhasPfamLiteratureReferencesPK != null) || (this.pfamAhasPfamLiteratureReferencesPK != null && !this.pfamAhasPfamLiteratureReferencesPK.equals(other.pfamAhasPfamLiteratureReferencesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamAhasPfamLiteratureReferences{" + "pfamAhasPfamLiteratureReferencesPK=" + pfamAhasPfamLiteratureReferencesPK + ", comment=" + comment + '}';
    }
}
