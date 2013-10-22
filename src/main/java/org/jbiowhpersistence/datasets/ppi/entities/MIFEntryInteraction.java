package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 * This Class is the MIFEntryInteraction entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-08-31 15:46:36 +0200
 * (Fri, 31 Aug 2012) $ $LastChangedRevision: 322 $
 *
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFEntryInteraction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFEntryInteraction.findAll", query = "SELECT m FROM MIFEntryInteraction m"),
    @NamedQuery(name = "MIFEntryInteraction.findByWid", query = "SELECT m FROM MIFEntryInteraction m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFEntryInteraction.findByMIFEntrySetEntryWID", query = "SELECT m FROM MIFEntryInteraction m WHERE m.mIFEntrySetEntryWID = :mIFEntrySetEntryWID"),
    @NamedQuery(name = "MIFEntryInteraction.findByImexId", query = "SELECT m FROM MIFEntryInteraction m WHERE m.imexId = :imexId"),
    @NamedQuery(name = "MIFEntryInteraction.findById", query = "SELECT m FROM MIFEntryInteraction m WHERE m.id = :id"),
    @NamedQuery(name = "MIFEntryInteraction.findByShortLabel", query = "SELECT m FROM MIFEntryInteraction m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFEntryInteraction.findByFullName", query = "SELECT m FROM MIFEntryInteraction m WHERE m.fullName = :fullName"),
    @NamedQuery(name = "MIFEntryInteraction.findByAvailabilityRef", query = "SELECT m FROM MIFEntryInteraction m WHERE m.availabilityRef = :availabilityRef"),
    @NamedQuery(name = "MIFEntryInteraction.findByModelled", query = "SELECT m FROM MIFEntryInteraction m WHERE m.modelled = :modelled"),
    @NamedQuery(name = "MIFEntryInteraction.findByIntraMolecular", query = "SELECT m FROM MIFEntryInteraction m WHERE m.intraMolecular = :intraMolecular"),
    @NamedQuery(name = "MIFEntryInteraction.findByNegative", query = "SELECT m FROM MIFEntryInteraction m WHERE m.negative = :negative")})
public class MIFEntryInteraction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFEntrySetEntry_WID")
    private long mIFEntrySetEntryWID;
    @Column(name = "ImexId")
    private String imexId;
    @Basic(optional = false)
    @Column(name = "Id")
    private int id;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "AvailabilityRef")
    private Integer availabilityRef;
    @Column(name = "Modelled")
    private String modelled;
    @Column(name = "IntraMolecular")
    private String intraMolecular;
    @Column(name = "Negative")
    private String negative;
    // External Interaction relationship
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MIFInteraction_has_Protein",
    joinColumns =
    @JoinColumn(name = "MIFEntryInteraction_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> protein;
    // Internal Interaction relationship
    @ManyToOne
    @JoinColumn(name = "MIFEntrySetEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntrySetEntry mIFEntrySetEntry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    @MapKey(name = "mIFOtherAvailabilityPK")
    private Map<MIFOtherAvailabilityPK, MIFOtherAvailability> mifOtherAvailability;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    @MapKey(name = "mIFOtherExperimentRefPK")
    private Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherExperimentRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFEntryExperiment> mifEntryExperiment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFInteractionParticipant> mifInteractionParticipant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFInteractionInferredInteraction> mifInteractionInferredInteraction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFInteractionInteractionType> mifInteractionInteractionType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFOtherConfidence> mifOtherConfidence;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    @MapKey(name = "mIFParticipantParameterPK")
    private Map<MIFParticipantParameterPK, MIFParticipantParameter> mifParticipantParameter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntryInteraction")
    private Set<MIFOtherAttribute> mifOtherAttribute;

    public MIFEntryInteraction() {
    }

    public MIFEntryInteraction(Long wid) {
        this.wid = wid;
    }

    public MIFEntryInteraction(Long wid, long mIFEntrySetEntryWID, int id) {
        this.wid = wid;
        this.mIFEntrySetEntryWID = mIFEntrySetEntryWID;
        this.id = id;
    }

    public void setRelationsToNull() {
        protein = null;
        mIFEntrySetEntry = null;
    }

    public Integer getAvailabilityRef() {
        return availabilityRef;
    }

    public void setAvailabilityRef(Integer availabilityRef) {
        this.availabilityRef = availabilityRef;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImexId() {
        return imexId;
    }

    public void setImexId(String imexId) {
        this.imexId = imexId;
    }

    public String getIntraMolecular() {
        return intraMolecular;
    }

    public void setIntraMolecular(String intraMolecular) {
        this.intraMolecular = intraMolecular;
    }

    public MIFEntrySetEntry getmIFEntrySetEntry() {
        return mIFEntrySetEntry;
    }

    public void setmIFEntrySetEntry(MIFEntrySetEntry mIFEntrySetEntry) {
        this.mIFEntrySetEntry = mIFEntrySetEntry;
    }

    public long getmIFEntrySetEntryWID() {
        return mIFEntrySetEntryWID;
    }

    public void setmIFEntrySetEntryWID(long mIFEntrySetEntryWID) {
        this.mIFEntrySetEntryWID = mIFEntrySetEntryWID;
    }

    public Set<MIFEntryExperiment> getMifEntryExperiment() {
        return mifEntryExperiment;
    }

    public void setMifEntryExperiment(Set<MIFEntryExperiment> mifEntryExperiment) {
        this.mifEntryExperiment = mifEntryExperiment;
    }

    public Set<MIFInteractionInferredInteraction> getMifInteractionInferredInteraction() {
        return mifInteractionInferredInteraction;
    }

    public void setMifInteractionInferredInteraction(Set<MIFInteractionInferredInteraction> mifInteractionInferredInteraction) {
        this.mifInteractionInferredInteraction = mifInteractionInferredInteraction;
    }

    public Set<MIFInteractionInteractionType> getMifInteractionInteractionType() {
        return mifInteractionInteractionType;
    }

    public void setMifInteractionInteractionType(Set<MIFInteractionInteractionType> mifInteractionInteractionType) {
        this.mifInteractionInteractionType = mifInteractionInteractionType;
    }

    public Set<MIFInteractionParticipant> getMifInteractionParticipant() {
        return mifInteractionParticipant;
    }

    public void setMifInteractionParticipant(Set<MIFInteractionParticipant> mifInteractionParticipant) {
        this.mifInteractionParticipant = mifInteractionParticipant;
    }

    public Set<MIFOtherAlias> getMifOtherAlias() {
        return mifOtherAlias;
    }

    public void setMifOtherAlias(Set<MIFOtherAlias> mifOtherAlias) {
        this.mifOtherAlias = mifOtherAlias;
    }

    public Set<MIFOtherAttribute> getMifOtherAttribute() {
        return mifOtherAttribute;
    }

    public void setMifOtherAttribute(Set<MIFOtherAttribute> mifOtherAttribute) {
        this.mifOtherAttribute = mifOtherAttribute;
    }

    public Map<MIFOtherAvailabilityPK, MIFOtherAvailability> getMifOtherAvailability() {
        return mifOtherAvailability;
    }

    public void setMifOtherAvailability(Map<MIFOtherAvailabilityPK, MIFOtherAvailability> mifOtherAvailability) {
        this.mifOtherAvailability = mifOtherAvailability;
    }

    public Set<MIFOtherConfidence> getMifOtherConfidence() {
        return mifOtherConfidence;
    }

    public void setMifOtherConfidence(Set<MIFOtherConfidence> mifOtherConfidence) {
        this.mifOtherConfidence = mifOtherConfidence;
    }

    public Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> getMifOtherExperimentRef() {
        return mifOtherExperimentRef;
    }

    public void setMifOtherExperimentRef(Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherExperimentRef) {
        this.mifOtherExperimentRef = mifOtherExperimentRef;
    }

    public Set<MIFOtherXRef> getMifOtherXRef() {
        return mifOtherXRef;
    }

    public void setMifOtherXRef(Set<MIFOtherXRef> mifOtherXRef) {
        this.mifOtherXRef = mifOtherXRef;
    }

    public Set<MIFOtherXRefGO> getMifOtherXRefGO() {
        return mifOtherXRefGO;
    }

    public void setMifOtherXRefGO(Set<MIFOtherXRefGO> mifOtherXRefGO) {
        this.mifOtherXRefGO = mifOtherXRefGO;
    }

    public Set<MIFOtherXRefPubMed> getMifOtherXRefPubMed() {
        return mifOtherXRefPubMed;
    }

    public void setMifOtherXRefPubMed(Set<MIFOtherXRefPubMed> mifOtherXRefPubMed) {
        this.mifOtherXRefPubMed = mifOtherXRefPubMed;
    }

    public Set<MIFOtherXRefRefSeq> getMifOtherXRefRefSeq() {
        return mifOtherXRefRefSeq;
    }

    public void setMifOtherXRefRefSeq(Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq) {
        this.mifOtherXRefRefSeq = mifOtherXRefRefSeq;
    }

    public Set<MIFOtherXRefUniprot> getMifOtherXRefUniprot() {
        return mifOtherXRefUniprot;
    }

    public void setMifOtherXRefUniprot(Set<MIFOtherXRefUniprot> mifOtherXRefUniprot) {
        this.mifOtherXRefUniprot = mifOtherXRefUniprot;
    }

    public Map<MIFParticipantParameterPK, MIFParticipantParameter> getMifParticipantParameter() {
        return mifParticipantParameter;
    }

    public void setMifParticipantParameter(Map<MIFParticipantParameterPK, MIFParticipantParameter> mifParticipantParameter) {
        this.mifParticipantParameter = mifParticipantParameter;
    }

    public String getModelled() {
        return modelled;
    }

    public void setModelled(String modelled) {
        this.modelled = modelled;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public Set<Protein> getProtein() {
        return protein;
    }

    public void setProtein(Set<Protein> protein) {
        this.protein = protein;
    }

    public String getShortLabel() {
        return shortLabel;
    }

    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
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
        final MIFEntryInteraction other = (MIFEntryInteraction) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFEntrySetEntryWID != other.mIFEntrySetEntryWID) {
            return false;
        }
        if (!Objects.equals(this.imexId, other.imexId)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.shortLabel, other.shortLabel)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.availabilityRef, other.availabilityRef)) {
            return false;
        }
        if (!Objects.equals(this.modelled, other.modelled)) {
            return false;
        }
        if (!Objects.equals(this.intraMolecular, other.intraMolecular)) {
            return false;
        }
        if (!Objects.equals(this.negative, other.negative)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFEntryInteraction{" + "wid=" + wid + ", mIFEntrySetEntryWID=" + mIFEntrySetEntryWID + ", imexId=" + imexId + ", id=" + id + ", shortLabel=" + shortLabel + ", fullName=" + fullName + ", availabilityRef=" + availabilityRef + ", modelled=" + modelled + ", intraMolecular=" + intraMolecular + ", negative=" + negative + '}';
    }
}
