package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Accession NUmber entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinAccessionNumber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinAccessionNumber.findAll", query = "SELECT p FROM ProteinAccessionNumber p"),
    @NamedQuery(name = "ProteinAccessionNumber.findByProteinWID", query = "SELECT p FROM ProteinAccessionNumber p WHERE p.proteinAccessionNumberPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinAccessionNumber.findByAccessionNumber", query = "SELECT p FROM ProteinAccessionNumber p WHERE p.proteinAccessionNumberPK.accessionNumber = :accessionNumber"),
    @NamedQuery(name = "ProteinAccessionNumber.findByOrderNumber", query = "SELECT p FROM ProteinAccessionNumber p WHERE p.orderNumber = :orderNumber")})
public class ProteinAccessionNumber implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinAccessionNumberPK proteinAccessionNumberPK;
    @Basic(optional = false)
    @Column(name = "OrderNumber")
    private int orderNumber;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinAccessionNumber() {
    }

    public ProteinAccessionNumber(ProteinAccessionNumberPK proteinAccessionNumberPK) {
        this.proteinAccessionNumberPK = proteinAccessionNumberPK;
    }

    public ProteinAccessionNumber(ProteinAccessionNumberPK proteinAccessionNumberPK, int orderNumber) {
        this.proteinAccessionNumberPK = proteinAccessionNumberPK;
        this.orderNumber = orderNumber;
    }

    public ProteinAccessionNumber(long proteinWID, String accessionNumber) {
        this.proteinAccessionNumberPK = new ProteinAccessionNumberPK(proteinWID, accessionNumber);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinAccessionNumberPK getProteinAccessionNumberPK() {
        return proteinAccessionNumberPK;
    }

    public void setProteinAccessionNumberPK(ProteinAccessionNumberPK proteinAccessionNumberPK) {
        this.proteinAccessionNumberPK = proteinAccessionNumberPK;
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
        final ProteinAccessionNumber other = (ProteinAccessionNumber) obj;
        if (!Objects.equals(this.proteinAccessionNumberPK, other.proteinAccessionNumberPK)) {
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
        hash += (proteinAccessionNumberPK != null ? proteinAccessionNumberPK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProteinAccessionNumber{"
                + " ProteinWID=" + proteinAccessionNumberPK.getProteinWID()
                + " AccessionNumber=" + proteinAccessionNumberPK.getAccessionNumber()
                + " orderNumber=" + orderNumber
                + '}';
    }
}
