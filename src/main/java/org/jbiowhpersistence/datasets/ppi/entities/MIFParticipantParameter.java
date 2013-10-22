package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFParticipantParameter entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFParticipantParameter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFParticipantParameter.findAll", query = "SELECT m FROM MIFParticipantParameter m"),
    @NamedQuery(name = "MIFParticipantParameter.findByMIFInteractionParticipantWID", query = "SELECT m FROM MIFParticipantParameter m WHERE m.mIFParticipantParameterPK.mIFInteractionParticipantWID = :mIFInteractionParticipantWID"),
    @NamedQuery(name = "MIFParticipantParameter.findByExperimentRef", query = "SELECT m FROM MIFParticipantParameter m WHERE m.experimentRef = :experimentRef"),
    @NamedQuery(name = "MIFParticipantParameter.findByTerm", query = "SELECT m FROM MIFParticipantParameter m WHERE m.term = :term"),
    @NamedQuery(name = "MIFParticipantParameter.findByTermAc", query = "SELECT m FROM MIFParticipantParameter m WHERE m.mIFParticipantParameterPK.termAc = :termAc"),
    @NamedQuery(name = "MIFParticipantParameter.findByUnit", query = "SELECT m FROM MIFParticipantParameter m WHERE m.unit = :unit"),
    @NamedQuery(name = "MIFParticipantParameter.findByUnitAc", query = "SELECT m FROM MIFParticipantParameter m WHERE m.unitAc = :unitAc"),
    @NamedQuery(name = "MIFParticipantParameter.findByBase", query = "SELECT m FROM MIFParticipantParameter m WHERE m.base = :base"),
    @NamedQuery(name = "MIFParticipantParameter.findByExponent", query = "SELECT m FROM MIFParticipantParameter m WHERE m.exponent = :exponent"),
    @NamedQuery(name = "MIFParticipantParameter.findByFactor", query = "SELECT m FROM MIFParticipantParameter m WHERE m.factor = :factor"),
    @NamedQuery(name = "MIFParticipantParameter.findByUncertainty", query = "SELECT m FROM MIFParticipantParameter m WHERE m.uncertainty = :uncertainty")})
public class MIFParticipantParameter implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MIFParticipantParameterPK mIFParticipantParameterPK;
    @Column(name = "ExperimentRef")
    private Integer experimentRef;
    @Column(name = "Term")
    private String term;
    @Column(name = "Unit")
    private String unit;
    @Column(name = "UnitAc")
    private String unitAc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Base")
    private Float base;
    @Column(name = "Exponent")
    private Float exponent;
    @Column(name = "Factor")
    private Float factor;
    @Column(name = "Uncertainty")
    private Float uncertainty;
    @ManyToOne
    @JoinColumn(name = "MIFInteractionParticipant_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionParticipant mifInteractionParticipant;
    @ManyToOne
    @JoinColumn(name = "MIFInteractionParticipant_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;

    public MIFParticipantParameter() {
    }

    public MIFParticipantParameter(MIFParticipantParameterPK mIFParticipantParameterPK) {
        this.mIFParticipantParameterPK = mIFParticipantParameterPK;
    }

    public MIFParticipantParameter(long mIFInteractionParticipantWID, String termAc) {
        this.mIFParticipantParameterPK = new MIFParticipantParameterPK(mIFInteractionParticipantWID, termAc);
    }

    public MIFParticipantParameterPK getMIFParticipantParameterPK() {
        return mIFParticipantParameterPK;
    }

    public void setMIFParticipantParameterPK(MIFParticipantParameterPK mIFParticipantParameterPK) {
        this.mIFParticipantParameterPK = mIFParticipantParameterPK;
    }

    public MIFParticipantParameterPK getmIFParticipantParameterPK() {
        return mIFParticipantParameterPK;
    }

    public void setmIFParticipantParameterPK(MIFParticipantParameterPK mIFParticipantParameterPK) {
        this.mIFParticipantParameterPK = mIFParticipantParameterPK;
    }

    public MIFEntryInteraction getMifEntryInteraction() {
        return mifEntryInteraction;
    }

    public void setMifEntryInteraction(MIFEntryInteraction mifEntryInteraction) {
        this.mifEntryInteraction = mifEntryInteraction;
    }

    public MIFInteractionParticipant getMifInteractionParticipant() {
        return mifInteractionParticipant;
    }

    public void setMifInteractionParticipant(MIFInteractionParticipant mifInteractionParticipant) {
        this.mifInteractionParticipant = mifInteractionParticipant;
    }

    public Integer getExperimentRef() {
        return experimentRef;
    }

    public void setExperimentRef(Integer experimentRef) {
        this.experimentRef = experimentRef;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitAc() {
        return unitAc;
    }

    public void setUnitAc(String unitAc) {
        this.unitAc = unitAc;
    }

    public Float getBase() {
        return base;
    }

    public void setBase(Float base) {
        this.base = base;
    }

    public Float getExponent() {
        return exponent;
    }

    public void setExponent(Float exponent) {
        this.exponent = exponent;
    }

    public Float getFactor() {
        return factor;
    }

    public void setFactor(Float factor) {
        this.factor = factor;
    }

    public Float getUncertainty() {
        return uncertainty;
    }

    public void setUncertainty(Float uncertainty) {
        this.uncertainty = uncertainty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mIFParticipantParameterPK != null ? mIFParticipantParameterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MIFParticipantParameter other = (MIFParticipantParameter) obj;
        if (!Objects.equals(this.mIFParticipantParameterPK, other.mIFParticipantParameterPK)) {
            return false;
        }
        if (!Objects.equals(this.experimentRef, other.experimentRef)) {
            return false;
        }
        if (!Objects.equals(this.term, other.term)) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
            return false;
        }
        if (!Objects.equals(this.unitAc, other.unitAc)) {
            return false;
        }
        if (!Objects.equals(this.base, other.base)) {
            return false;
        }
        if (!Objects.equals(this.exponent, other.exponent)) {
            return false;
        }
        if (!Objects.equals(this.factor, other.factor)) {
            return false;
        }
        if (!Objects.equals(this.uncertainty, other.uncertainty)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFParticipantParameter{" + "mIFParticipantParameterPK=" + mIFParticipantParameterPK + ", experimentRef=" + experimentRef + ", term=" + term + ", unit=" + unit + ", unitAc=" + unitAc + ", base=" + base + ", exponent=" + exponent + ", factor=" + factor + ", uncertainty=" + uncertainty + '}';
    }
}
