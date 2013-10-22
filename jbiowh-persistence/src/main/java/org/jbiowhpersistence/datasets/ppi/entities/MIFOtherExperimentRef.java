package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFOtherExperimentRef entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFOtherExperimentRef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFOtherExperimentRef.findAll", query = "SELECT m FROM MIFOtherExperimentRef m"),
    @NamedQuery(name = "MIFOtherExperimentRef.findByMIFOtherWID", query = "SELECT m FROM MIFOtherExperimentRef m WHERE m.mIFOtherExperimentRefPK.mIFOtherWID = :mIFOtherWID"),
    @NamedQuery(name = "MIFOtherExperimentRef.findByExperimentRef", query = "SELECT m FROM MIFOtherExperimentRef m WHERE m.mIFOtherExperimentRefPK.experimentRef = :experimentRef"),
    @NamedQuery(name = "MIFOtherExperimentRef.findByDataSetWID", query = "SELECT m FROM MIFOtherExperimentRef m WHERE m.dataSetWID = :dataSetWID")})
public class MIFOtherExperimentRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MIFOtherExperimentRefPK mIFOtherExperimentRefPK;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherConfidence mifOtherConfidence;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantPartIdentMeth mifParticipantPartIdentMeth;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantExperimentalRole mifParticipantExperimentalRole;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantExperimentalPreparation mifParticipantExperimentalPreparation;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantExperimentalInteractor mifParticipantExperimentalInteractor;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantFeature mifParticipantFeature;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionInferredInteraction mifInteractionInferredInteraction;

    public MIFOtherExperimentRef() {
    }

    public MIFOtherExperimentRef(MIFOtherExperimentRefPK mIFOtherExperimentRefPK) {
        this.mIFOtherExperimentRefPK = mIFOtherExperimentRefPK;
    }

    public MIFOtherExperimentRef(MIFOtherExperimentRefPK mIFOtherExperimentRefPK, long dataSetWID) {
        this.mIFOtherExperimentRefPK = mIFOtherExperimentRefPK;
        this.dataSetWID = dataSetWID;
    }

    public MIFOtherExperimentRef(long mIFOtherWID, int experimentRef) {
        this.mIFOtherExperimentRefPK = new MIFOtherExperimentRefPK(mIFOtherWID, experimentRef);
    }

    public MIFOtherExperimentRefPK getmIFOtherExperimentRefPK() {
        return mIFOtherExperimentRefPK;
    }

    public void setmIFOtherExperimentRefPK(MIFOtherExperimentRefPK mIFOtherExperimentRefPK) {
        this.mIFOtherExperimentRefPK = mIFOtherExperimentRefPK;
    }

    public MIFEntryInteraction getMifEntryInteraction() {
        return mifEntryInteraction;
    }

    public void setMifEntryInteraction(MIFEntryInteraction mifEntryInteraction) {
        this.mifEntryInteraction = mifEntryInteraction;
    }

    public MIFInteractionInferredInteraction getMifInteractionInferredInteraction() {
        return mifInteractionInferredInteraction;
    }

    public void setMifInteractionInferredInteraction(MIFInteractionInferredInteraction mifInteractionInferredInteraction) {
        this.mifInteractionInferredInteraction = mifInteractionInferredInteraction;
    }

    public MIFOtherConfidence getMifOtherConfidence() {
        return mifOtherConfidence;
    }

    public void setMifOtherConfidence(MIFOtherConfidence mifOtherConfidence) {
        this.mifOtherConfidence = mifOtherConfidence;
    }

    public MIFParticipantExperimentalInteractor getMifParticipantExperimentalInteractor() {
        return mifParticipantExperimentalInteractor;
    }

    public void setMifParticipantExperimentalInteractor(MIFParticipantExperimentalInteractor mifParticipantExperimentalInteractor) {
        this.mifParticipantExperimentalInteractor = mifParticipantExperimentalInteractor;
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

    public MIFOtherExperimentRefPK getMIFOtherExperimentRefPK() {
        return mIFOtherExperimentRefPK;
    }

    public void setMIFOtherExperimentRefPK(MIFOtherExperimentRefPK mIFOtherExperimentRefPK) {
        this.mIFOtherExperimentRefPK = mIFOtherExperimentRefPK;
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
        hash += (mIFOtherExperimentRefPK != null ? mIFOtherExperimentRefPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MIFOtherExperimentRef)) {
            return false;
        }
        MIFOtherExperimentRef other = (MIFOtherExperimentRef) object;
        if ((this.mIFOtherExperimentRefPK == null && other.mIFOtherExperimentRefPK != null) || (this.mIFOtherExperimentRefPK != null && !this.mIFOtherExperimentRefPK.equals(other.mIFOtherExperimentRefPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFOtherExperimentRef{" + "mIFOtherExperimentRefPK=" + mIFOtherExperimentRefPK + ", dataSetWID=" + dataSetWID + '}';
    }
}
