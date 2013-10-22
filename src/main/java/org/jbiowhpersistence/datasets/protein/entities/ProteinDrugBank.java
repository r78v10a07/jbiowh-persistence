package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein DrugBank entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinDrugBank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinDrugBank.findAll", query = "SELECT p FROM ProteinDrugBank p"),
    @NamedQuery(name = "ProteinDrugBank.findByProteinWID", query = "SELECT p FROM ProteinDrugBank p WHERE p.proteinDrugBankPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinDrugBank.findById", query = "SELECT p FROM ProteinDrugBank p WHERE p.proteinDrugBankPK.id = :id")})
public class ProteinDrugBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinDrugBankPK proteinDrugBankPK;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinDrugBank() {
    }

    public ProteinDrugBank(ProteinDrugBankPK proteinDrugBankPK) {
        this.proteinDrugBankPK = proteinDrugBankPK;
    }

    public ProteinDrugBank(long proteinWID, String id) {
        this.proteinDrugBankPK = new ProteinDrugBankPK(proteinWID, id);
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinDrugBankPK getProteinDrugBankPK() {
        return proteinDrugBankPK;
    }

    public void setProteinDrugBankPK(ProteinDrugBankPK proteinDrugBankPK) {
        this.proteinDrugBankPK = proteinDrugBankPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinDrugBankPK != null ? proteinDrugBankPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinDrugBank)) {
            return false;
        }
        ProteinDrugBank other = (ProteinDrugBank) object;
        if ((this.proteinDrugBankPK == null && other.proteinDrugBankPK != null) || (this.proteinDrugBankPK != null && !this.proteinDrugBankPK.equals(other.proteinDrugBankPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProteinDrugBank{"
                + " ProteinWID=" + proteinDrugBankPK.getProteinWID()
                + " Id=" + proteinDrugBankPK.getId()
                + '}';
    }
}
