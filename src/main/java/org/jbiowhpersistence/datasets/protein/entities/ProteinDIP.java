package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein DIP entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinDIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinDIP.findAll", query = "SELECT p FROM ProteinDIP p"),
    @NamedQuery(name = "ProteinDIP.findByProteinWID", query = "SELECT p FROM ProteinDIP p WHERE p.proteinDIPPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinDIP.findById", query = "SELECT p FROM ProteinDIP p WHERE p.proteinDIPPK.id = :id")})
public class ProteinDIP implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinDIPPK proteinDIPPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinDIP() {
    }

    public ProteinDIP(ProteinDIPPK proteinDIPPK) {
        this.proteinDIPPK = proteinDIPPK;
    }

    public ProteinDIP(long proteinWID, String id) {
        this.proteinDIPPK = new ProteinDIPPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinDIPPK getProteinDIPPK() {
        return proteinDIPPK;
    }

    public void setProteinDIPPK(ProteinDIPPK proteinDIPPK) {
        this.proteinDIPPK = proteinDIPPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinDIPPK != null ? proteinDIPPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinDIP)) {
            return false;
        }
        ProteinDIP other = (ProteinDIP) object;
        if ((this.proteinDIPPK == null && other.proteinDIPPK != null) || (this.proteinDIPPK != null && !this.proteinDIPPK.equals(other.proteinDIPPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinDIP{"
                + " ProteinWID=" + proteinDIPPK.getProteinWID()
                + " Id=" + proteinDIPPK.getId()
                + '}';
    }
}
