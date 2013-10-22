package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFOtherAttribute entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-08-31 15:46:36 +0200
 * (Fri, 31 Aug 2012) $ $LastChangedRevision: 322 $
 *
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFOtherAttribute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFOtherAttribute.findAll", query = "SELECT m FROM MIFOtherAttribute m"),
    @NamedQuery(name = "MIFOtherAttribute.findByWid", query = "SELECT m FROM MIFOtherAttribute m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFOtherAttribute.findByMIFOtherWID", query = "SELECT m FROM MIFOtherAttribute m WHERE m.mIFOtherWID = :mIFOtherWID"),
    @NamedQuery(name = "MIFOtherAttribute.findByAttribute", query = "SELECT m FROM MIFOtherAttribute m WHERE m.attribute = :attribute"),
    @NamedQuery(name = "MIFOtherAttribute.findByNameAc", query = "SELECT m FROM MIFOtherAttribute m WHERE m.nameAc = :nameAc"),
    @NamedQuery(name = "MIFOtherAttribute.findByName", query = "SELECT m FROM MIFOtherAttribute m WHERE m.name = :name"),
    @NamedQuery(name = "MIFOtherAttribute.findByDataSetWID", query = "SELECT m FROM MIFOtherAttribute m WHERE m.dataSetWID = :dataSetWID")})
public class MIFOtherAttribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFOtherWID")
    private long mIFOtherWID;
    @Column(name = "Attribute")
    private String attribute;
    @Column(name = "NameAc")
    private String nameAc;
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherBibRef mifOtherBibref;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherXRef mifOtherXRef;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherXRefGO mifOtherXRefGO;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherXRefPubMed mifOtherXRefPubMed;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherXRefRefSeq mifOtherXRefRefSeq;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFOtherXRefUniprot mifOtherXRefUniprot;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntrySource mifEntrySource;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryExperiment mifEntryExperiment;
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
    private MIFEntryInteractor mifEntryInteractor;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionParticipant mifInteractionParticipant;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFParticipantFeature mifParticipantFeature;

    public MIFOtherAttribute() {
    }

    public MIFOtherAttribute(Long wid) {
        this.wid = wid;
    }

    public MIFOtherAttribute(Long wid, long mIFOtherWID, long dataSetWID) {
        this.wid = wid;
        this.mIFOtherWID = mIFOtherWID;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        mifOtherBibref = null;
        mifOtherXRef = null;
        mifOtherXRefGO = null;
        mifOtherXRefPubMed = null;
        mifOtherXRefRefSeq = null;
        mifOtherXRefUniprot = null;
        mifEntrySource = null;
        mifEntryExperiment = null;
        mifBioSourceTypeCellType = null;
        mifBioSourceTypeCompartment = null;
        mifBioSourceTypeTissue = null;
        mifEntryInteractor = null;
        mifEntryInteraction = null;
        mifInteractionParticipant = null;
        mifParticipantFeature = null;
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

    public MIFInteractionParticipant getMifInteractionParticipant() {
        return mifInteractionParticipant;
    }

    public void setMifInteractionParticipant(MIFInteractionParticipant mifInteractionParticipant) {
        this.mifInteractionParticipant = mifInteractionParticipant;
    }

    public MIFOtherBibRef getMifOtherBibref() {
        return mifOtherBibref;
    }

    public void setMifOtherBibref(MIFOtherBibRef mifOtherBibref) {
        this.mifOtherBibref = mifOtherBibref;
    }

    public MIFOtherXRef getMifOtherXRef() {
        return mifOtherXRef;
    }

    public void setMifOtherXRef(MIFOtherXRef mifOtherXRef) {
        this.mifOtherXRef = mifOtherXRef;
    }

    public MIFOtherXRefGO getMifOtherXRefGO() {
        return mifOtherXRefGO;
    }

    public void setMifOtherXRefGO(MIFOtherXRefGO mifOtherXRefGO) {
        this.mifOtherXRefGO = mifOtherXRefGO;
    }

    public MIFOtherXRefPubMed getMifOtherXRefPubMed() {
        return mifOtherXRefPubMed;
    }

    public void setMifOtherXRefPubMed(MIFOtherXRefPubMed mifOtherXRefPubMed) {
        this.mifOtherXRefPubMed = mifOtherXRefPubMed;
    }

    public MIFOtherXRefRefSeq getMifOtherXRefRefSeq() {
        return mifOtherXRefRefSeq;
    }

    public void setMifOtherXRefRefSeq(MIFOtherXRefRefSeq mifOtherXRefRefSeq) {
        this.mifOtherXRefRefSeq = mifOtherXRefRefSeq;
    }

    public MIFOtherXRefUniprot getMifOtherXRefUniprot() {
        return mifOtherXRefUniprot;
    }

    public void setMifOtherXRefUniprot(MIFOtherXRefUniprot mifOtherXRefUniprot) {
        this.mifOtherXRefUniprot = mifOtherXRefUniprot;
    }

    public MIFParticipantFeature getMifParticipantFeature() {
        return mifParticipantFeature;
    }

    public void setMifParticipantFeature(MIFParticipantFeature mifParticipantFeature) {
        this.mifParticipantFeature = mifParticipantFeature;
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

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getNameAc() {
        return nameAc;
    }

    public void setNameAc(String nameAc) {
        this.nameAc = nameAc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        final MIFOtherAttribute other = (MIFOtherAttribute) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFOtherWID != other.mIFOtherWID) {
            return false;
        }
        if (!Objects.equals(this.attribute, other.attribute)) {
            return false;
        }
        if (!Objects.equals(this.nameAc, other.nameAc)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFOtherAttribute{" + "wid=" + wid + ", mIFOtherWID=" + mIFOtherWID + ", attribute=" + attribute + ", nameAc=" + nameAc + ", name=" + name + ", dataSetWID=" + dataSetWID + '}';
    }
}
