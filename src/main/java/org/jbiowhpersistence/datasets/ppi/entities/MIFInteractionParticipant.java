package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the MIFInteractionParticipant entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFInteractionParticipant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFInteractionParticipant.findAll", query = "SELECT m FROM MIFInteractionParticipant m"),
    @NamedQuery(name = "MIFInteractionParticipant.findByWid", query = "SELECT m FROM MIFInteractionParticipant m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFInteractionParticipant.findByMIFEntryInteractionWID", query = "SELECT m FROM MIFInteractionParticipant m WHERE m.mIFEntryInteractionWID = :mIFEntryInteractionWID"),
    @NamedQuery(name = "MIFInteractionParticipant.findById", query = "SELECT m FROM MIFInteractionParticipant m WHERE m.id = :id"),
    @NamedQuery(name = "MIFInteractionParticipant.findByShortLabel", query = "SELECT m FROM MIFInteractionParticipant m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFInteractionParticipant.findByFullName", query = "SELECT m FROM MIFInteractionParticipant m WHERE m.fullName = :fullName"),
    @NamedQuery(name = "MIFInteractionParticipant.findByInteractorRef", query = "SELECT m FROM MIFInteractionParticipant m WHERE m.interactorRef = :interactorRef"),
    @NamedQuery(name = "MIFInteractionParticipant.findByInteractionRef", query = "SELECT m FROM MIFInteractionParticipant m WHERE m.interactionRef = :interactionRef")})
public class MIFInteractionParticipant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFEntryInteraction_WID")
    private long mIFEntryInteractionWID;
    @Basic(optional = false)
    @Column(name = "Id")
    private int id;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "InteractorRef")
    private Integer interactorRef;
    @Column(name = "InteractionRef")
    private Integer interactionRef;
    @ManyToOne
    @JoinColumn(name = "MIFEntryInteraction_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFEntryInteractor> mifEntryInteractor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFParticipantPartIdentMeth> mifParticipantPartIdentMeth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFParticipantBiologicalRole> mifParticipantBiologicalRole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFParticipantExperimentalRole> mifParticipantExperimentalRole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFParticipantExperimentalPreparation> mifParticipantExperimentalPreparation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFParticipantFeature> mifParticipantFeature;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFOtherBioSourceType> mifOtherBioSourceType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFOtherConfidence> mifOtherConfidence;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    @MapKey(name = "mIFParticipantParameterPK")
    private Map<MIFParticipantParameterPK, MIFParticipantParameter> mifParticipantParameter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifInteractionParticipant")
    private Set<MIFOtherAttribute> mifOtherAttribute;

    public MIFInteractionParticipant() {
    }

    public MIFInteractionParticipant(Long wid) {
        this.wid = wid;
    }

    public MIFInteractionParticipant(Long wid, long mIFEntryInteractionWID, int id) {
        this.wid = wid;
        this.mIFEntryInteractionWID = mIFEntryInteractionWID;
        this.id = id;
    }
    
    public void setRelationsToNull() {
        mifEntryInteraction = null;
    }

    public long getmIFEntryInteractionWID() {
        return mIFEntryInteractionWID;
    }

    public void setmIFEntryInteractionWID(long mIFEntryInteractionWID) {
        this.mIFEntryInteractionWID = mIFEntryInteractionWID;
    }

    public MIFEntryInteraction getMifEntryInteraction() {
        return mifEntryInteraction;
    }

    public void setMifEntryInteraction(MIFEntryInteraction mifEntryInteraction) {
        this.mifEntryInteraction = mifEntryInteraction;
    }

    @XmlTransient
    public Set<MIFEntryInteractor> getMifEntryInteractor() {
        return mifEntryInteractor;
    }

    public void setMifEntryInteractor(Set<MIFEntryInteractor> mifEntryInteractor) {
        this.mifEntryInteractor = mifEntryInteractor;
    }

    @XmlTransient
    public Set<MIFOtherAlias> getMifOtherAlias() {
        return mifOtherAlias;
    }

    public void setMifOtherAlias(Set<MIFOtherAlias> mifOtherAlias) {
        this.mifOtherAlias = mifOtherAlias;
    }

    @XmlTransient
    public Set<MIFOtherAttribute> getMifOtherAttribute() {
        return mifOtherAttribute;
    }

    public void setMifOtherAttribute(Set<MIFOtherAttribute> mifOtherAttribute) {
        this.mifOtherAttribute = mifOtherAttribute;
    }

    @XmlTransient
    public Set<MIFOtherBioSourceType> getMifOtherBioSourceType() {
        return mifOtherBioSourceType;
    }

    public void setMifOtherBioSourceType(Set<MIFOtherBioSourceType> mifOtherBioSourceType) {
        this.mifOtherBioSourceType = mifOtherBioSourceType;
    }

    @XmlTransient
    public Set<MIFOtherConfidence> getMifOtherConfidence() {
        return mifOtherConfidence;
    }

    public void setMifOtherConfidence(Set<MIFOtherConfidence> mifOtherConfidence) {
        this.mifOtherConfidence = mifOtherConfidence;
    }

    @XmlTransient
    public Set<MIFOtherXRef> getMifOtherXRef() {
        return mifOtherXRef;
    }

    public void setMifOtherXRef(Set<MIFOtherXRef> mifOtherXRef) {
        this.mifOtherXRef = mifOtherXRef;
    }

    @XmlTransient
    public Set<MIFOtherXRefGO> getMifOtherXRefGO() {
        return mifOtherXRefGO;
    }

    public void setMifOtherXRefGO(Set<MIFOtherXRefGO> mifOtherXRefGO) {
        this.mifOtherXRefGO = mifOtherXRefGO;
    }

    @XmlTransient
    public Set<MIFOtherXRefPubMed> getMifOtherXRefPubMed() {
        return mifOtherXRefPubMed;
    }

    public void setMifOtherXRefPubMed(Set<MIFOtherXRefPubMed> mifOtherXRefPubMed) {
        this.mifOtherXRefPubMed = mifOtherXRefPubMed;
    }

    @XmlTransient
    public Set<MIFOtherXRefRefSeq> getMifOtherXRefRefSeq() {
        return mifOtherXRefRefSeq;
    }

    public void setMifOtherXRefRefSeq(Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq) {
        this.mifOtherXRefRefSeq = mifOtherXRefRefSeq;
    }

    @XmlTransient
    public Set<MIFOtherXRefUniprot> getMifOtherXRefUniprot() {
        return mifOtherXRefUniprot;
    }

    public void setMifOtherXRefUniprot(Set<MIFOtherXRefUniprot> mifOtherXRefUniprot) {
        this.mifOtherXRefUniprot = mifOtherXRefUniprot;
    }

    @XmlTransient
    public Set<MIFParticipantBiologicalRole> getMifParticipantBiologicalRole() {
        return mifParticipantBiologicalRole;
    }

    public void setMifParticipantBiologicalRole(Set<MIFParticipantBiologicalRole> mifParticipantBiologicalRole) {
        this.mifParticipantBiologicalRole = mifParticipantBiologicalRole;
    }

    @XmlTransient
    public Set<MIFParticipantExperimentalPreparation> getMifParticipantExperimentalPreparation() {
        return mifParticipantExperimentalPreparation;
    }

    public void setMifParticipantExperimentalPreparation(Set<MIFParticipantExperimentalPreparation> mifParticipantExperimentalPreparation) {
        this.mifParticipantExperimentalPreparation = mifParticipantExperimentalPreparation;
    }

    @XmlTransient
    public Set<MIFParticipantExperimentalRole> getMifParticipantExperimentalRole() {
        return mifParticipantExperimentalRole;
    }

    public void setMifParticipantExperimentalRole(Set<MIFParticipantExperimentalRole> mifParticipantExperimentalRole) {
        this.mifParticipantExperimentalRole = mifParticipantExperimentalRole;
    }

    @XmlTransient
    public Set<MIFParticipantFeature> getMifParticipantFeature() {
        return mifParticipantFeature;
    }

    public void setMifParticipantFeature(Set<MIFParticipantFeature> mifParticipantFeature) {
        this.mifParticipantFeature = mifParticipantFeature;
    }

    @XmlTransient
    public Map<MIFParticipantParameterPK, MIFParticipantParameter> getMifParticipantParameter() {
        return mifParticipantParameter;
    }

    public void setMifParticipantParameter(Map<MIFParticipantParameterPK, MIFParticipantParameter> mifParticipantParameter) {
        this.mifParticipantParameter = mifParticipantParameter;
    }

    @XmlTransient
    public Set<MIFParticipantPartIdentMeth> getMifParticipantPartIdentMeth() {
        return mifParticipantPartIdentMeth;
    }

    public void setMifParticipantPartIdentMeth(Set<MIFParticipantPartIdentMeth> mifParticipantPartIdentMeth) {
        this.mifParticipantPartIdentMeth = mifParticipantPartIdentMeth;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getMIFEntryInteractionWID() {
        return mIFEntryInteractionWID;
    }

    public void setMIFEntryInteractionWID(long mIFEntryInteractionWID) {
        this.mIFEntryInteractionWID = mIFEntryInteractionWID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortLabel() {
        return shortLabel;
    }

    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getInteractorRef() {
        return interactorRef;
    }

    public void setInteractorRef(Integer interactorRef) {
        this.interactorRef = interactorRef;
    }

    public Integer getInteractionRef() {
        return interactionRef;
    }

    public void setInteractionRef(Integer interactionRef) {
        this.interactionRef = interactionRef;
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
        final MIFInteractionParticipant other = (MIFInteractionParticipant) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFEntryInteractionWID != other.mIFEntryInteractionWID) {
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
        if (!Objects.equals(this.interactorRef, other.interactorRef)) {
            return false;
        }
        if (!Objects.equals(this.interactionRef, other.interactionRef)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFInteractionParticipant{" + "wid=" + wid + ", mIFEntryInteractionWID=" + mIFEntryInteractionWID + ", id=" + id + ", shortLabel=" + shortLabel + ", fullName=" + fullName + ", interactorRef=" + interactorRef + ", interactionRef=" + interactionRef + '}';
    }
}
