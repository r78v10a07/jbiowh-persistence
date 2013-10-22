package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein MINT entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinMINT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinMINT.findAll", query = "SELECT p FROM ProteinMINT p"),
    @NamedQuery(name = "ProteinMINT.findByProteinWID", query = "SELECT p FROM ProteinMINT p WHERE p.proteinMINTPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinMINT.findById", query = "SELECT p FROM ProteinMINT p WHERE p.proteinMINTPK.id = :id")})
public class ProteinMINT implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinMINTPK proteinMINTPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinMINT() {
    }

    public ProteinMINT(ProteinMINTPK proteinMINTPK) {
        this.proteinMINTPK = proteinMINTPK;
    }

    public ProteinMINT(long proteinWID, String id) {
        this.proteinMINTPK = new ProteinMINTPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinMINTPK getProteinMINTPK() {
        return proteinMINTPK;
    }

    public void setProteinMINTPK(ProteinMINTPK proteinMINTPK) {
        this.proteinMINTPK = proteinMINTPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinMINTPK != null ? proteinMINTPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinMINT)) {
            return false;
        }
        ProteinMINT other = (ProteinMINT) object;
        if ((this.proteinMINTPK == null && other.proteinMINTPK != null) || (this.proteinMINTPK != null && !this.proteinMINTPK.equals(other.proteinMINTPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinMINT{"
                + " ProteinWID=" + proteinMINTPK.getProteinWID()
                + " Id=" + proteinMINTPK.getId()
                + '}';
    }
}
