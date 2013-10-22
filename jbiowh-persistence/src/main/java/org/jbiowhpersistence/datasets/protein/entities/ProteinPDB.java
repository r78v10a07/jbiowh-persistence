package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is Protein PDB entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinPDB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinPDB.findAll", query = "SELECT p FROM ProteinPDB p"),
    @NamedQuery(name = "ProteinPDB.findByProteinWID", query = "SELECT p FROM ProteinPDB p WHERE p.proteinPDBPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinPDB.findById", query = "SELECT p FROM ProteinPDB p WHERE p.proteinPDBPK.id = :id")})
public class ProteinPDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinPDBPK proteinPDBPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinPDB() {
    }

    public ProteinPDB(ProteinPDBPK proteinPDBPK) {
        this.proteinPDBPK = proteinPDBPK;
    }

    public ProteinPDB(long proteinWID, String id) {
        this.proteinPDBPK = new ProteinPDBPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinPDBPK getProteinPDBPK() {
        return proteinPDBPK;
    }

    public void setProteinPDBPK(ProteinPDBPK proteinPDBPK) {
        this.proteinPDBPK = proteinPDBPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinPDBPK != null ? proteinPDBPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinPDB)) {
            return false;
        }
        ProteinPDB other = (ProteinPDB) object;
        if ((this.proteinPDBPK == null && other.proteinPDBPK != null) || (this.proteinPDBPK != null && !this.proteinPDBPK.equals(other.proteinPDBPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinPDB{"
                + " ProteinWID=" + proteinPDBPK.getProteinWID()
                + " Id=" + proteinPDBPK.getId()
                + '}';
    }
}
