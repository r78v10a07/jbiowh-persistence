package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Name entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinName.findAll", query = "SELECT p FROM ProteinName p"),
    @NamedQuery(name = "ProteinName.findByProteinWID", query = "SELECT p FROM ProteinName p WHERE p.proteinNamePK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinName.findByName", query = "SELECT p FROM ProteinName p WHERE p.proteinNamePK.name = :name"),
    @NamedQuery(name = "ProteinName.findByOrderNumber", query = "SELECT p FROM ProteinName p WHERE p.orderNumber = :orderNumber")})
public class ProteinName implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinNamePK proteinNamePK;
    @Basic(optional = false)
    @Column(name = "OrderNumber")
    private int orderNumber;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinName() {
    }

    public ProteinName(ProteinNamePK proteinNamePK) {
        this.proteinNamePK = proteinNamePK;
    }

    public ProteinName(ProteinNamePK proteinNamePK, int orderNumber) {
        this.proteinNamePK = proteinNamePK;
        this.orderNumber = orderNumber;
    }

    public ProteinName(long proteinWID, String name) {
        this.proteinNamePK = new ProteinNamePK(proteinWID, name);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinNamePK getProteinNamePK() {
        return proteinNamePK;
    }

    public void setProteinNamePK(ProteinNamePK proteinNamePK) {
        this.proteinNamePK = proteinNamePK;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinName other = (ProteinName) obj;
        if (!Objects.equals(this.proteinNamePK, other.proteinNamePK)) {
            return false;
        }
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinNamePK != null ? proteinNamePK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProteinName{"
                + " ProteinWID=" + proteinNamePK.getProteinWID()
                + " Name=" + proteinNamePK.getName()
                + " orderNumber=" + orderNumber
                + '}';
    }
}
