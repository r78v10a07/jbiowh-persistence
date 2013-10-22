package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein EC entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinEC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinEC.findAll", query = "SELECT p FROM ProteinEC p"),
    @NamedQuery(name = "ProteinEC.findByProteinWID", query = "SELECT p FROM ProteinEC p WHERE p.proteinECPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinEC.findById", query = "SELECT p FROM ProteinEC p WHERE p.proteinECPK.id = :id")})
public class ProteinEC implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinECPK proteinECPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinEC() {
    }

    public ProteinEC(ProteinECPK proteinECPK) {
        this.proteinECPK = proteinECPK;
    }

    public ProteinEC(long proteinWID, String id) {
        this.proteinECPK = new ProteinECPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinECPK getProteinECPK() {
        return proteinECPK;
    }

    public void setProteinECPK(ProteinECPK proteinECPK) {
        this.proteinECPK = proteinECPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinECPK != null ? proteinECPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinEC)) {
            return false;
        }
        ProteinEC other = (ProteinEC) object;
        if ((this.proteinECPK == null && other.proteinECPK != null) || (this.proteinECPK != null && !this.proteinECPK.equals(other.proteinECPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinEC{"
                + " ProteinWID=" + proteinECPK.getProteinWID()
                + " Id=" + proteinECPK.getId()
                + '}';
    }
}
