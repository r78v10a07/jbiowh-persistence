package org.jbiowhpersistence.datasets.protgroup.pirsf.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 * This class is the PIRSF_has_Protein entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 11, 2013
 */
@Entity
@Table(name = "PIRSF_has_Protein")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PirsfhasProtein.findAll", query = "SELECT p FROM PirsfhasProtein p"),
    @NamedQuery(name = "PirsfhasProtein.findByPirsfWid", query = "SELECT p FROM PirsfhasProtein p WHERE p.pIRSFhasProteinPK.pirsfWid = :pirsfWid"),
    @NamedQuery(name = "PirsfhasProtein.findByProteinWID", query = "SELECT p FROM PirsfhasProtein p WHERE p.pIRSFhasProteinPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "PirsfhasProtein.findByStatus", query = "SELECT p FROM PirsfhasProtein p WHERE p.status = :status"),
    @NamedQuery(name = "PirsfhasProtein.findBySeed", query = "SELECT p FROM PirsfhasProtein p WHERE p.seed = :seed")})
public class PirsfhasProtein implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PirsfhasProteinPK pIRSFhasProteinPK;
    @Column(name = "Status")
    private Integer status;
    @Column(name = "Seed")
    private Integer seed;
    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "PIRSF_WID", insertable = false, unique = false, nullable = true, updatable = false)
    @XmlInverseReference(mappedBy = "pIRSFhasProtein")
    private Pirsf pirsf;
    @XmlTransient
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public PirsfhasProtein() {
    }

    public PirsfhasProtein(PirsfhasProteinPK pIRSFhasProteinPK) {
        this.pIRSFhasProteinPK = pIRSFhasProteinPK;
    }

    public PirsfhasProtein(long pirsfWid, long proteinWID) {
        this.pIRSFhasProteinPK = new PirsfhasProteinPK(pirsfWid, proteinWID);
    }

    @XmlTransient    
    public Pirsf getPirsf() {
        return pirsf;
    }

    public void setPirsf(Pirsf pirsf) {
        this.pirsf = pirsf;
    }

    @XmlTransient    
    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public PirsfhasProteinPK getPIRSFhasProteinPK() {
        return pIRSFhasProteinPK;
    }

    public void setPIRSFhasProteinPK(PirsfhasProteinPK pIRSFhasProteinPK) {
        this.pIRSFhasProteinPK = pIRSFhasProteinPK;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSeed() {
        return seed;
    }

    public void setSeed(Integer seed) {
        this.seed = seed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pIRSFhasProteinPK != null ? pIRSFhasProteinPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PirsfhasProtein)) {
            return false;
        }
        PirsfhasProtein other = (PirsfhasProtein) object;
        return (this.pIRSFhasProteinPK != null || other.pIRSFhasProteinPK == null) && (this.pIRSFhasProteinPK == null || this.pIRSFhasProteinPK.equals(other.pIRSFhasProteinPK));
    }

    @Override
    public String toString() {
        return "PirsfhasProtein{" + "pIRSFhasProteinPK=" + pIRSFhasProteinPK + ", status=" + status + ", seed=" + seed + '}';
    }
}
