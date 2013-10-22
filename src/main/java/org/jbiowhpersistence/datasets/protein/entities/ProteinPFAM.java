package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein PFAM entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinPFAM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinPFAM.findAll", query = "SELECT p FROM ProteinPFAM p"),
    @NamedQuery(name = "ProteinPFAM.findByProteinWID", query = "SELECT p FROM ProteinPFAM p WHERE p.proteinPFAMPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinPFAM.findById", query = "SELECT p FROM ProteinPFAM p WHERE p.proteinPFAMPK.id = :id")})
public class ProteinPFAM implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinPFAMPK proteinPFAMPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinPFAM() {
    }

    public ProteinPFAM(ProteinPFAMPK proteinPFAMPK) {
        this.proteinPFAMPK = proteinPFAMPK;
    }

    public ProteinPFAM(long proteinWID, String id) {
        this.proteinPFAMPK = new ProteinPFAMPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinPFAMPK getProteinPFAMPK() {
        return proteinPFAMPK;
    }

    public void setProteinPFAMPK(ProteinPFAMPK proteinPFAMPK) {
        this.proteinPFAMPK = proteinPFAMPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinPFAMPK != null ? proteinPFAMPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinPFAM)) {
            return false;
        }
        ProteinPFAM other = (ProteinPFAM) object;
        if ((this.proteinPFAMPK == null && other.proteinPFAMPK != null) || (this.proteinPFAMPK != null && !this.proteinPFAMPK.equals(other.proteinPFAMPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ProteinPFAM{"
                + " proteinWID=" + proteinPFAMPK.getProteinWID()
                + " Id=" + proteinPFAMPK.getId()
                + "}\n";
    }
}
