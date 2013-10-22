package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein BioCyc entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinBioCyc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinBioCyc.findAll", query = "SELECT p FROM ProteinBioCyc p"),
    @NamedQuery(name = "ProteinBioCyc.findByProteinWID", query = "SELECT p FROM ProteinBioCyc p WHERE p.proteinBioCycPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinBioCyc.findById", query = "SELECT p FROM ProteinBioCyc p WHERE p.proteinBioCycPK.id = :id")})
public class ProteinBioCyc implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinBioCycPK proteinBioCycPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinBioCyc() {
    }

    public ProteinBioCyc(ProteinBioCycPK proteinBioCycPK) {
        this.proteinBioCycPK = proteinBioCycPK;
    }

    public ProteinBioCyc(long proteinWID, String id) {
        this.proteinBioCycPK = new ProteinBioCycPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinBioCycPK getProteinBioCycPK() {
        return proteinBioCycPK;
    }

    public void setProteinBioCycPK(ProteinBioCycPK proteinBioCycPK) {
        this.proteinBioCycPK = proteinBioCycPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinBioCycPK != null ? proteinBioCycPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinBioCyc)) {
            return false;
        }
        ProteinBioCyc other = (ProteinBioCyc) object;
        if ((this.proteinBioCycPK == null && other.proteinBioCycPK != null) || (this.proteinBioCycPK != null && !this.proteinBioCycPK.equals(other.proteinBioCycPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinBioCyc{"
                + " ProteinWID=" + proteinBioCycPK.getProteinWID()
                + " Id=" + proteinBioCycPK.getId()
                + '}';
    }
}
