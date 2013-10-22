package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein PMID entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinPMID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinPMID.findAll", query = "SELECT p FROM ProteinPMID p"),
    @NamedQuery(name = "ProteinPMID.findByProteinWID", query = "SELECT p FROM ProteinPMID p WHERE p.proteinPMIDPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinPMID.findById", query = "SELECT p FROM ProteinPMID p WHERE p.proteinPMIDPK.id = :id")})
public class ProteinPMID implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinPMIDPK proteinPMIDPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinPMID() {
    }

    public ProteinPMID(ProteinPMIDPK proteinPMIDPK) {
        this.proteinPMIDPK = proteinPMIDPK;
    }

    public ProteinPMID(long proteinWID, String id) {
        this.proteinPMIDPK = new ProteinPMIDPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinPMIDPK getProteinPMIDPK() {
        return proteinPMIDPK;
    }

    public void setProteinPMIDPK(ProteinPMIDPK proteinPMIDPK) {
        this.proteinPMIDPK = proteinPMIDPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinPMIDPK != null ? proteinPMIDPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinPMID)) {
            return false;
        }
        ProteinPMID other = (ProteinPMID) object;
        if ((this.proteinPMIDPK == null && other.proteinPMIDPK != null) || (this.proteinPMIDPK != null && !this.proteinPMIDPK.equals(other.proteinPMIDPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinPMID{"
                + " ProteinWID=" + proteinPMIDPK.getProteinWID()
                + " Id=" + proteinPMIDPK.getId()
                + '}';
    }
}
