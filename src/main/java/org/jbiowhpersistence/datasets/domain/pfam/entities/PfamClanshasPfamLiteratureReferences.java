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
 * This class is the PfamClans_has_PfamLiteratureReferences entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ $LastChangedRevision: 344 $
 *
 * @since Nov 26, 2012
 */
@Entity
@Table(name = "PfamClans_has_PfamLiteratureReferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamClanshasPfamLiteratureReferences.findAll", query = "SELECT p FROM PfamClanshasPfamLiteratureReferences p"),
    @NamedQuery(name = "PfamClanshasPfamLiteratureReferences.findByPfamClansWID", query = "SELECT p FROM PfamClanshasPfamLiteratureReferences p WHERE p.pfamClanshasPfamLiteratureReferencesPK.pfamClansWID = :pfamClansWID"),
    @NamedQuery(name = "PfamClanshasPfamLiteratureReferences.findByPfamLiteratureReferencesWID", query = "SELECT p FROM PfamClanshasPfamLiteratureReferences p WHERE p.pfamClanshasPfamLiteratureReferencesPK.pfamLiteratureReferencesWID = :pfamLiteratureReferencesWID"),
    @NamedQuery(name = "PfamClanshasPfamLiteratureReferences.findByOrderAdded", query = "SELECT p FROM PfamClanshasPfamLiteratureReferences p WHERE p.pfamClanshasPfamLiteratureReferencesPK.orderAdded = :orderAdded"),
    @NamedQuery(name = "PfamClanshasPfamLiteratureReferences.findByComment", query = "SELECT p FROM PfamClanshasPfamLiteratureReferences p WHERE p.comment = :comment")})
public class PfamClanshasPfamLiteratureReferences implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PfamClanshasPfamLiteratureReferencesPK pfamClanshasPfamLiteratureReferencesPK;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "PfamClans_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamClans pfamClans;
    @ManyToOne
    @JoinColumn(name = "PfamLiteratureReferences_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamLiteratureReferences pfamLiteratureReferences;

    public PfamClanshasPfamLiteratureReferences() {
    }

    public PfamClanshasPfamLiteratureReferences(PfamClanshasPfamLiteratureReferencesPK pfamClanshasPfamLiteratureReferencesPK) {
        this.pfamClanshasPfamLiteratureReferencesPK = pfamClanshasPfamLiteratureReferencesPK;
    }

    public PfamClanshasPfamLiteratureReferences(long pfamClansWID, long pfamLiteratureReferencesWID, short orderAdded) {
        this.pfamClanshasPfamLiteratureReferencesPK = new PfamClanshasPfamLiteratureReferencesPK(pfamClansWID, pfamLiteratureReferencesWID, orderAdded);
    }

    public PfamClans getPfamClans() {
        return pfamClans;
    }

    public void setPfamClans(PfamClans pfamClans) {
        this.pfamClans = pfamClans;
    }

    public PfamLiteratureReferences getPfamLiteratureReferences() {
        return pfamLiteratureReferences;
    }

    public void setPfamLiteratureReferences(PfamLiteratureReferences pfamLiteratureReferences) {
        this.pfamLiteratureReferences = pfamLiteratureReferences;
    }

    public PfamClanshasPfamLiteratureReferencesPK getPfamClanshasPfamLiteratureReferencesPK() {
        return pfamClanshasPfamLiteratureReferencesPK;
    }

    public void setPfamClanshasPfamLiteratureReferencesPK(PfamClanshasPfamLiteratureReferencesPK pfamClanshasPfamLiteratureReferencesPK) {
        this.pfamClanshasPfamLiteratureReferencesPK = pfamClanshasPfamLiteratureReferencesPK;
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
        hash += (pfamClanshasPfamLiteratureReferencesPK != null ? pfamClanshasPfamLiteratureReferencesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PfamClanshasPfamLiteratureReferences)) {
            return false;
        }
        PfamClanshasPfamLiteratureReferences other = (PfamClanshasPfamLiteratureReferences) object;
        if ((this.pfamClanshasPfamLiteratureReferencesPK == null && other.pfamClanshasPfamLiteratureReferencesPK != null) || (this.pfamClanshasPfamLiteratureReferencesPK != null && !this.pfamClanshasPfamLiteratureReferencesPK.equals(other.pfamClanshasPfamLiteratureReferencesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamClanshasPfamLiteratureReferences{" + "pfamClanshasPfamLiteratureReferencesPK=" + pfamClanshasPfamLiteratureReferencesPK + ", comment=" + comment + '}';
    }
}
