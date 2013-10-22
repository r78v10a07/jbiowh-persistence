package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Intact entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinIntAct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinIntAct.findAll", query = "SELECT p FROM ProteinIntAct p"),
    @NamedQuery(name = "ProteinIntAct.findByProteinWID", query = "SELECT p FROM ProteinIntAct p WHERE p.proteinIntActPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinIntAct.findById", query = "SELECT p FROM ProteinIntAct p WHERE p.proteinIntActPK.id = :id")})
public class ProteinIntAct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinIntActPK proteinIntActPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinIntAct() {
    }

    public ProteinIntAct(ProteinIntActPK proteinIntActPK) {
        this.proteinIntActPK = proteinIntActPK;
    }

    public ProteinIntAct(long proteinWID, String id) {
        this.proteinIntActPK = new ProteinIntActPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinIntActPK getProteinIntActPK() {
        return proteinIntActPK;
    }

    public void setProteinIntActPK(ProteinIntActPK proteinIntActPK) {
        this.proteinIntActPK = proteinIntActPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinIntActPK != null ? proteinIntActPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinIntAct)) {
            return false;
        }
        ProteinIntAct other = (ProteinIntAct) object;
        if ((this.proteinIntActPK == null && other.proteinIntActPK != null) || (this.proteinIntActPK != null && !this.proteinIntActPK.equals(other.proteinIntActPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinIntAct{"
                + " ProteinWID=" + proteinIntActPK.getProteinWID()
                + " Id=" + proteinIntActPK.getId()
                + '}';
    }
}
