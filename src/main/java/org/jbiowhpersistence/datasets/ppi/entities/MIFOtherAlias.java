package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFOtherAlias entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFOtherAlias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFOtherAlias.findAll", query = "SELECT m FROM MIFOtherAlias m"),
    @NamedQuery(name = "MIFOtherAlias.findByWid", query = "SELECT m FROM MIFOtherAlias m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFOtherAlias.findByMIFOtherWID", query = "SELECT m FROM MIFOtherAlias m WHERE m.mIFOtherWID = :mIFOtherWID"),
    @NamedQuery(name = "MIFOtherAlias.findByAlias", query = "SELECT m FROM MIFOtherAlias m WHERE m.alias = :alias"),
    @NamedQuery(name = "MIFOtherAlias.findByTypeAc", query = "SELECT m FROM MIFOtherAlias m WHERE m.typeAc = :typeAc"),
    @NamedQuery(name = "MIFOtherAlias.findByType", query = "SELECT m FROM MIFOtherAlias m WHERE m.type = :type"),
    @NamedQuery(name = "MIFOtherAlias.findByDataSetWID", query = "SELECT m FROM MIFOtherAlias m WHERE m.dataSetWID = :dataSetWID")})
public class MIFOtherAlias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFOtherWID")
    private long mIFOtherWID;
    @Column(name = "Alias")
    private String alias;
    @Column(name = "TypeAc")
    private String typeAc;
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntrySource mifEntrySource;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryExperiment mifEntryExperiment;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherBioSourceType mifOtherBioSourceType;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFBioSourceTypeCellType mifBioSourceTypeCellType;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFBioSourceTypeCompartment mifBioSourceTypeCompartment;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFBioSourceTypeTissue mifBioSourceTypeTissue;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFExperimentInterDetecMethod mifExperimentInterDetecMethod;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFExperimentPartIdentMethod mifExperimentPartIdentMethod;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFExperimentFeatDetecMethod mifExperimentFeatDetecMethod;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherConfidence mifOtherConfidence;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteractor mifEntryInteractor;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractorInteractorType mifInteractorInteractorType;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionParticipant mifInteractionParticipant;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantPartIdentMeth mifParticipantPartIdentMeth;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantBiologicalRole mifParticipantBiologicalRole;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantExperimentalRole mifParticipantExperimentalRole;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantExperimentalPreparation mifParticipantExperimentalPreparation;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantFeature mifParticipantFeature;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFFeatureFeatureType mifFeatureFeatureType;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFFeatureFeatDetMeth mifFeatureFeatDetMeth;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionInteractionType mifInteractionInteractionType;

    public MIFOtherAlias() {
        mifEntrySource = null;
        mifEntryExperiment = null;
        mifOtherBioSourceType = null;
        mifBioSourceTypeCellType = null;
        mifBioSourceTypeCompartment = null;
        mifBioSourceTypeTissue = null;
        mifExperimentInterDetecMethod = null;
        mifExperimentPartIdentMethod = null;
        mifExperimentFeatDetecMethod = null;
        mifOtherConfidence = null;
        mifEntryInteractor = null;
        mifInteractorInteractorType = null;
        mifEntryInteraction = null;
        mifInteractionParticipant = null;
        mifParticipantPartIdentMeth = null;
        mifParticipantBiologicalRole = null;
        mifParticipantExperimentalRole = null;
        mifParticipantExperimentalPreparation = null;
        mifParticipantFeature = null;
        mifFeatureFeatureType = null;
        mifFeatureFeatDetMeth = null;
        mifInteractionInteractionType = null;
    }

    public MIFOtherAlias(Long wid) {
        this.wid = wid;
    }

    public MIFOtherAlias(Long wid, long mIFOtherWID, long dataSetWID) {
        this.wid = wid;
        this.mIFOtherWID = mIFOtherWID;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public long getmIFOtherWID() {
        return mIFOtherWID;
    }

    public void setmIFOtherWID(long mIFOtherWID) {
        this.mIFOtherWID = mIFOtherWID;
    }

    public MIFBioSourceTypeCellType getMifBioSourceTypeCellType() {
        return mifBioSourceTypeCellType;
    }

    public void setMifBioSourceTypeCellType(MIFBioSourceTypeCellType mifBioSourceTypeCellType) {
        this.mifBioSourceTypeCellType = mifBioSourceTypeCellType;
    }

    public MIFBioSourceTypeCompartment getMifBioSourceTypeCompartment() {
        return mifBioSourceTypeCompartment;
    }

    public void setMifBioSourceTypeCompartment(MIFBioSourceTypeCompartment mifBioSourceTypeCompartment) {
        this.mifBioSourceTypeCompartment = mifBioSourceTypeCompartment;
    }

    public MIFBioSourceTypeTissue getMifBioSourceTypeTissue() {
        return mifBioSourceTypeTissue;
    }

    public void setMifBioSourceTypeTissue(MIFBioSourceTypeTissue mifBioSourceTypeTissue) {
        this.mifBioSourceTypeTissue = mifBioSourceTypeTissue;
    }

    public MIFEntryExperiment getMifEntryExperiment() {
        return mifEntryExperiment;
    }

    public void setMifEntryExperiment(MIFEntryExperiment mifEntryExperiment) {
        this.mifEntryExperiment = mifEntryExperiment;
    }

    public MIFEntryInteraction getMifEntryInteraction() {
        return mifEntryInteraction;
    }

    public void setMifEntryInteraction(MIFEntryInteraction mifEntryInteraction) {
        this.mifEntryInteraction = mifEntryInteraction;
    }

    public MIFEntryInteractor getMifEntryInteractor() {
        return mifEntryInteractor;
    }

    public void setMifEntryInteractor(MIFEntryInteractor mifEntryInteractor) {
        this.mifEntryInteractor = mifEntryInteractor;
    }

    public MIFEntrySource getMifEntrySource() {
        return mifEntrySource;
    }

    public void setMifEntrySource(MIFEntrySource mifEntrySource) {
        this.mifEntrySource = mifEntrySource;
    }

    public MIFExperimentFeatDetecMethod getMifExperimentFeatDetecMethod() {
        return mifExperimentFeatDetecMethod;
    }

    public void setMifExperimentFeatDetecMethod(MIFExperimentFeatDetecMethod mifExperimentFeatDetecMethod) {
        this.mifExperimentFeatDetecMethod = mifExperimentFeatDetecMethod;
    }

    public MIFExperimentInterDetecMethod getMifExperimentInterDetecMethod() {
        return mifExperimentInterDetecMethod;
    }

    public void setMifExperimentInterDetecMethod(MIFExperimentInterDetecMethod mifExperimentInterDetecMethod) {
        this.mifExperimentInterDetecMethod = mifExperimentInterDetecMethod;
    }

    public MIFExperimentPartIdentMethod getMifExperimentPartIdentMethod() {
        return mifExperimentPartIdentMethod;
    }

    public void setMifExperimentPartIdentMethod(MIFExperimentPartIdentMethod mifExperimentPartIdentMethod) {
        this.mifExperimentPartIdentMethod = mifExperimentPartIdentMethod;
    }

    public MIFFeatureFeatDetMeth getMifFeatureFeatDetMeth() {
        return mifFeatureFeatDetMeth;
    }

    public void setMifFeatureFeatDetMeth(MIFFeatureFeatDetMeth mifFeatureFeatDetMeth) {
        this.mifFeatureFeatDetMeth = mifFeatureFeatDetMeth;
    }

    public MIFFeatureFeatureType getMifFeatureFeatureType() {
        return mifFeatureFeatureType;
    }

    public void setMifFeatureFeatureType(MIFFeatureFeatureType mifFeatureFeatureType) {
        this.mifFeatureFeatureType = mifFeatureFeatureType;
    }

    public MIFInteractionInteractionType getMifInteractionInteractionType() {
        return mifInteractionInteractionType;
    }

    public void setMifInteractionInteractionType(MIFInteractionInteractionType mifInteractionInteractionType) {
        this.mifInteractionInteractionType = mifInteractionInteractionType;
    }

    public MIFInteractionParticipant getMifInteractionParticipant() {
        return mifInteractionParticipant;
    }

    public void setMifInteractionParticipant(MIFInteractionParticipant mifInteractionParticipant) {
        this.mifInteractionParticipant = mifInteractionParticipant;
    }

    public MIFInteractorInteractorType getMifInteractorInteractorType() {
        return mifInteractorInteractorType;
    }

    public void setMifInteractorInteractorType(MIFInteractorInteractorType mifInteractorInteractorType) {
        this.mifInteractorInteractorType = mifInteractorInteractorType;
    }

    public MIFOtherBioSourceType getMifOtherBioSourceType() {
        return mifOtherBioSourceType;
    }

    public void setMifOtherBioSourceType(MIFOtherBioSourceType mifOtherBioSourceType) {
        this.mifOtherBioSourceType = mifOtherBioSourceType;
    }

    public MIFOtherConfidence getMifOtherConfidence() {
        return mifOtherConfidence;
    }

    public void setMifOtherConfidence(MIFOtherConfidence mifOtherConfidence) {
        this.mifOtherConfidence = mifOtherConfidence;
    }

    public MIFParticipantBiologicalRole getMifParticipantBiologicalRole() {
        return mifParticipantBiologicalRole;
    }

    public void setMifParticipantBiologicalRole(MIFParticipantBiologicalRole mifParticipantBiologicalRole) {
        this.mifParticipantBiologicalRole = mifParticipantBiologicalRole;
    }

    public MIFParticipantExperimentalPreparation getMifParticipantExperimentalPreparation() {
        return mifParticipantExperimentalPreparation;
    }

    public void setMifParticipantExperimentalPreparation(MIFParticipantExperimentalPreparation mifParticipantExperimentalPreparation) {
        this.mifParticipantExperimentalPreparation = mifParticipantExperimentalPreparation;
    }

    public MIFParticipantExperimentalRole getMifParticipantExperimentalRole() {
        return mifParticipantExperimentalRole;
    }

    public void setMifParticipantExperimentalRole(MIFParticipantExperimentalRole mifParticipantExperimentalRole) {
        this.mifParticipantExperimentalRole = mifParticipantExperimentalRole;
    }

    public MIFParticipantFeature getMifParticipantFeature() {
        return mifParticipantFeature;
    }

    public void setMifParticipantFeature(MIFParticipantFeature mifParticipantFeature) {
        this.mifParticipantFeature = mifParticipantFeature;
    }

    public MIFParticipantPartIdentMeth getMifParticipantPartIdentMeth() {
        return mifParticipantPartIdentMeth;
    }

    public void setMifParticipantPartIdentMeth(MIFParticipantPartIdentMeth mifParticipantPartIdentMeth) {
        this.mifParticipantPartIdentMeth = mifParticipantPartIdentMeth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeAc() {
        return typeAc;
    }

    public void setTypeAc(String typeAc) {
        this.typeAc = typeAc;
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
        final MIFOtherAlias other = (MIFOtherAlias) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFOtherWID != other.mIFOtherWID) {
            return false;
        }
        if (!Objects.equals(this.alias, other.alias)) {
            return false;
        }
        if (!Objects.equals(this.typeAc, other.typeAc)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFOtherAlias{" + "wid=" + wid + ", mIFOtherWID=" + mIFOtherWID + ", alias=" + alias + ", typeAc=" + typeAc + ", type=" + type + ", dataSetWID=" + dataSetWID + '}';
    }
}
