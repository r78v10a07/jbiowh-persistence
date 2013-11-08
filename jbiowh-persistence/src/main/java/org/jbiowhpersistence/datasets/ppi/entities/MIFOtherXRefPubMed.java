package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the MIFOtherXRefPubMed entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFOtherXRefPubMed")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFOtherXRefPubMed.findAll", query = "SELECT m FROM MIFOtherXRefPubMed m"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findByWid", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findByMIFOtherWID", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.mIFOtherWID = :mIFOtherWID"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findByDb", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.db = :db"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findByDBAc", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.dBAc = :dBAc"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findById", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.id = :id"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findBySecondary", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.secondary = :secondary"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findByVersion", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.version = :version"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findByRefType", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.refType = :refType"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findByRefTypeAc", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.refTypeAc = :refTypeAc"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findByPrimaryRef", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.primaryRef = :primaryRef"),
    @NamedQuery(name = "MIFOtherXRefPubMed.findByDataSetWID", query = "SELECT m FROM MIFOtherXRefPubMed m WHERE m.dataSetWID = :dataSetWID")})
public class MIFOtherXRefPubMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFOtherWID")
    private long mIFOtherWID;
    @Column(name = "DB")
    private String db;
    @Column(name = "DBAc")
    private String dBAc;
    @Column(name = "Id")
    private String id;
    @Column(name = "Secondary")
    private String secondary;
    @Column(name = "Version")
    private String version;
    @Column(name = "RefType")
    private String refType;
    @Column(name = "RefTypeAc")
    private String refTypeAc;
    @Column(name = "PrimaryRef")
    private Boolean primaryRef;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryExperiment mifEntryExperiment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherXRefPubMed")
    private Set<MIFOtherAttribute> mifOtherAttribute;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherBibRef mifOtherBibref;
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

    public MIFOtherXRefPubMed() {
    }

    public MIFOtherXRefPubMed(Long wid) {
        this.wid = wid;
    }

    public MIFOtherXRefPubMed(Long wid, long mIFOtherWID, long dataSetWID) {
        this.wid = wid;
        this.mIFOtherWID = mIFOtherWID;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        mifEntryExperiment = null;
        mifOtherAttribute = null;
        mifOtherBibref = null;
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

    public MIFOtherBibRef getMifOtherBibref() {
        return mifOtherBibref;
    }

    public void setMifOtherBibref(MIFOtherBibRef mifOtherBibref) {
        this.mifOtherBibref = mifOtherBibref;
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

    @XmlTransient
    public Set<MIFOtherAttribute> getMifOtherAttribute() {
        return mifOtherAttribute;
    }

    public void setMifOtherAttribute(Set<MIFOtherAttribute> mifOtherAttribute) {
        this.mifOtherAttribute = mifOtherAttribute;
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

    public String getdBAc() {
        return dBAc;
    }

    public void setdBAc(String dBAc) {
        this.dBAc = dBAc;
    }

    public long getmIFOtherWID() {
        return mIFOtherWID;
    }

    public void setmIFOtherWID(long mIFOtherWID) {
        this.mIFOtherWID = mIFOtherWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getMIFOtherWID() {
        return mIFOtherWID;
    }

    public void setMIFOtherWID(long mIFOtherWID) {
        this.mIFOtherWID = mIFOtherWID;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDBAc() {
        return dBAc;
    }

    public void setDBAc(String dBAc) {
        this.dBAc = dBAc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getRefTypeAc() {
        return refTypeAc;
    }

    public void setRefTypeAc(String refTypeAc) {
        this.refTypeAc = refTypeAc;
    }

    public Boolean getPrimaryRef() {
        return primaryRef;
    }

    public void setPrimaryRef(Boolean primaryRef) {
        this.primaryRef = primaryRef;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
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
        final MIFOtherXRefPubMed other = (MIFOtherXRefPubMed) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFOtherWID != other.mIFOtherWID) {
            return false;
        }
        if (!Objects.equals(this.db, other.db)) {
            return false;
        }
        if (!Objects.equals(this.dBAc, other.dBAc)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.secondary, other.secondary)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.refType, other.refType)) {
            return false;
        }
        if (!Objects.equals(this.refTypeAc, other.refTypeAc)) {
            return false;
        }
        if (!Objects.equals(this.primaryRef, other.primaryRef)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFOtherXRefPubMed{" + "wid=" + wid + ", mIFOtherWID=" + mIFOtherWID + ", db=" + db + ", dBAc=" + dBAc + ", id=" + id + ", secondary=" + secondary + ", version=" + version + ", refType=" + refType + ", refTypeAc=" + refTypeAc + ", primaryRef=" + primaryRef + ", dataSetWID=" + dataSetWID + '}';
    }
}
