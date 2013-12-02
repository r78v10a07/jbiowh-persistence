package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the Protein_has_ProteinKeyword entity
 *
 * @author roberto
 * $LastChangedDate: 2012-10-15 09:23:43 +0200 (Mon, 15 Oct 2012) $
 * $LastChangedRevision: 285 $
 * @since Sep 4, 2012
 */
@Entity
@Table(name = "Protein_has_ProteinKeyword")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinhasProteinKeyword.findAll", query = "SELECT p FROM ProteinhasProteinKeyword p"),
    @NamedQuery(name = "ProteinhasProteinKeyword.findByProteinWID", query = "SELECT p FROM ProteinhasProteinKeyword p WHERE p.proteinhasProteinKeywordPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinhasProteinKeyword.findByProteinKeywordWID", query = "SELECT p FROM ProteinhasProteinKeyword p WHERE p.proteinhasProteinKeywordPK.proteinKeywordWID = :proteinKeywordWID"),
    @NamedQuery(name = "ProteinhasProteinKeyword.findByEvidence", query = "SELECT p FROM ProteinhasProteinKeyword p WHERE p.evidence = :evidence")})
public class ProteinhasProteinKeyword implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinhasProteinKeywordPK proteinhasProteinKeywordPK;
    @Column(name = "Evidence")
    private String evidence;
    @ManyToOne
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinKeyword_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinKeyword proteinKeyword;

    public ProteinhasProteinKeyword() {
    }

    public ProteinhasProteinKeyword(ProteinhasProteinKeywordPK proteinhasProteinKeywordPK) {
        this.proteinhasProteinKeywordPK = proteinhasProteinKeywordPK;
    }

    public ProteinhasProteinKeyword(long proteinWID, long proteinKeywordWID) {
        this.proteinhasProteinKeywordPK = new ProteinhasProteinKeywordPK(proteinWID, proteinKeywordWID);
    }

    public ProteinhasProteinKeywordPK getProteinhasProteinKeywordPK() {
        return proteinhasProteinKeywordPK;
    }

    public void setProteinhasProteinKeywordPK(ProteinhasProteinKeywordPK proteinhasProteinKeywordPK) {
        this.proteinhasProteinKeywordPK = proteinhasProteinKeywordPK;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public ProteinKeyword getProteinKeyword() {
        return proteinKeyword;
    }

    public void setProteinKeyword(ProteinKeyword proteinKeyword) {
        this.proteinKeyword = proteinKeyword;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinhasProteinKeyword other = (ProteinhasProteinKeyword) obj;
        if (!Objects.equals(this.proteinhasProteinKeywordPK, other.proteinhasProteinKeywordPK)) {
            return false;
        }
        return Objects.equals(this.evidence, other.evidence);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.proteinhasProteinKeywordPK);
        return hash;
    }

    @Override
    public String toString() {
        return proteinKeyword + " evidence=" + evidence;
    }

}
