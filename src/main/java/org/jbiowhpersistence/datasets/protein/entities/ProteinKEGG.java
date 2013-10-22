package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein KEGG entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinKEGG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinKEGG.findAll", query = "SELECT p FROM ProteinKEGG p"),
    @NamedQuery(name = "ProteinKEGG.findByProteinWID", query = "SELECT p FROM ProteinKEGG p WHERE p.proteinKEGGPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinKEGG.findById", query = "SELECT p FROM ProteinKEGG p WHERE p.proteinKEGGPK.id = :id")})
public class ProteinKEGG implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinKEGGPK proteinKEGGPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinKEGG() {
    }

    public ProteinKEGG(ProteinKEGGPK proteinKEGGPK) {
        this.proteinKEGGPK = proteinKEGGPK;
    }

    public ProteinKEGG(long proteinWID, String id) {
        this.proteinKEGGPK = new ProteinKEGGPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinKEGGPK getProteinKEGGPK() {
        return proteinKEGGPK;
    }

    public void setProteinKEGGPK(ProteinKEGGPK proteinKEGGPK) {
        this.proteinKEGGPK = proteinKEGGPK;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinKEGG other = (ProteinKEGG) obj;
        if (!Objects.equals(this.proteinKEGGPK, other.proteinKEGGPK)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinKEGGPK != null ? proteinKEGGPK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProteinKEGG{"
                + " ProteinWID=" + proteinKEGGPK.getProteinWID()
                + " Id=" + proteinKEGGPK.getId()
                + '}';
    }
}
