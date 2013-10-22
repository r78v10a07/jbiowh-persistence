package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamSeq_has_Protein entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ $LastChangedRevision: 344 $
 *
 * @since Nov 22, 2012
 */
@Entity
@Table(name = "PfamSeq_has_Protein")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamSeqhasProtein.findAll", query = "SELECT p FROM PfamSeqhasProtein p"),
    @NamedQuery(name = "PfamSeqhasProtein.findByAutoPfamseq", query = "SELECT p FROM PfamSeqhasProtein p WHERE p.pfamSeqhasProteinPK.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamSeqhasProtein.findByProteinWID", query = "SELECT p FROM PfamSeqhasProtein p WHERE p.pfamSeqhasProteinPK.proteinWID = :proteinWID")})
public class PfamSeqhasProtein implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PfamSeqhasProteinPK pfamSeqhasProteinPK;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_pfamseq", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamSeqhasUniProtId pfamSeqhasUniProtId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_pfamseq", referencedColumnName="auto_pfamseq", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamARegFullSignificant pfamARegFullSignificant;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_pfamseq", referencedColumnName="auto_pfamseq", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamARegFullInsignificant pfamARegFullInsignificant;

    public PfamSeqhasProtein() {
    }

    public PfamSeqhasProtein(PfamSeqhasProteinPK pfamSeqhasProteinPK) {
        this.pfamSeqhasProteinPK = pfamSeqhasProteinPK;
    }

    public PfamSeqhasProtein(long autoPfamseq, long proteinWID) {
        this.pfamSeqhasProteinPK = new PfamSeqhasProteinPK(autoPfamseq, proteinWID);
    }

    public PfamARegFullSignificant getPfamARegFullSignificant() {
        return pfamARegFullSignificant;
    }

    public void setPfamARegFullSignificant(PfamARegFullSignificant pfamARegFullSignificant) {
        this.pfamARegFullSignificant = pfamARegFullSignificant;
    }

    public PfamSeqhasProteinPK getPfamSeqhasProteinPK() {
        return pfamSeqhasProteinPK;
    }

    public void setPfamSeqhasProteinPK(PfamSeqhasProteinPK pfamSeqhasProteinPK) {
        this.pfamSeqhasProteinPK = pfamSeqhasProteinPK;
    }

    public PfamSeqhasUniProtId getPfamSeqhasUniProtId() {
        return pfamSeqhasUniProtId;
    }

    public void setPfamSeqhasUniProtId(PfamSeqhasUniProtId pfamSeqhasUniProtId) {
        this.pfamSeqhasUniProtId = pfamSeqhasUniProtId;
    }

    public PfamARegFullInsignificant getPfamARegFullInsignificant() {
        return pfamARegFullInsignificant;
    }

    public void setPfamARegFullInsignificant(PfamARegFullInsignificant pfamARegFullInsignificant) {
        this.pfamARegFullInsignificant = pfamARegFullInsignificant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfamSeqhasProteinPK != null ? pfamSeqhasProteinPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PfamSeqhasProtein)) {
            return false;
        }
        PfamSeqhasProtein other = (PfamSeqhasProtein) object;
        if ((this.pfamSeqhasProteinPK == null && other.pfamSeqhasProteinPK != null) || (this.pfamSeqhasProteinPK != null && !this.pfamSeqhasProteinPK.equals(other.pfamSeqhasProteinPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamSeqhasProtein{" + "pfamSeqhasProteinPK=" + pfamSeqhasProteinPK + '}';
    }
}
