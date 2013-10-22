package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This Class is the MIFOtherBioSourceType entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFOtherBioSourceType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFOtherBioSourceType.findAll", query = "SELECT m FROM MIFOtherBioSourceType m"),
    @NamedQuery(name = "MIFOtherBioSourceType.findByWid", query = "SELECT m FROM MIFOtherBioSourceType m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFOtherBioSourceType.findByMIFOtherWID", query = "SELECT m FROM MIFOtherBioSourceType m WHERE m.mIFOtherWID = :mIFOtherWID"),
    @NamedQuery(name = "MIFOtherBioSourceType.findByTaxId", query = "SELECT m FROM MIFOtherBioSourceType m WHERE m.taxId = :taxId"),
    @NamedQuery(name = "MIFOtherBioSourceType.findByShortLabel", query = "SELECT m FROM MIFOtherBioSourceType m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFOtherBioSourceType.findByFullName", query = "SELECT m FROM MIFOtherBioSourceType m WHERE m.fullName = :fullName"),
    @NamedQuery(name = "MIFOtherBioSourceType.findByDataSetWID", query = "SELECT m FROM MIFOtherBioSourceType m WHERE m.dataSetWID = :dataSetWID")})
public class MIFOtherBioSourceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFOtherWID")
    private long mIFOtherWID;
    @Basic(optional = false)
    @Column(name = "TaxId")
    private long taxId;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryExperiment mifEntryExperiment;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteractor mifEntryInteractor;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionParticipant mifInteractionParticipant;
    @ManyToOne
    @JoinColumn(name = "taxId", referencedColumnName = "TaxId", insertable = false, unique = false, nullable = true, updatable = false)
    private Taxonomy taxonomy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherBioSourceType")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherBioSourceType")
    private Set<MIFBioSourceTypeCellType> mifBioSourceTypeCellType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherBioSourceType")
    private Set<MIFBioSourceTypeCompartment> mifBioSourceTypeCompartment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherBioSourceType")
    private Set<MIFBioSourceTypeTissue> mifBioSourceTypeTissueD;

    public MIFOtherBioSourceType() {
    }

    public MIFOtherBioSourceType(Long wid) {
        this.wid = wid;
    }

    public MIFOtherBioSourceType(Long wid, long mIFOtherWID, long taxId, long dataSetWID) {
        this.wid = wid;
        this.mIFOtherWID = mIFOtherWID;
        this.taxId = taxId;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        mifEntryExperiment = null;
        mifEntryInteractor = null;
        mifInteractionParticipant = null;
        mifOtherAlias = null;
    }

    public MIFEntryExperiment getMifEntryExperiment() {
        return mifEntryExperiment;
    }

    public void setMifEntryExperiment(MIFEntryExperiment mifEntryExperiment) {
        this.mifEntryExperiment = mifEntryExperiment;
    }

    public Set<MIFBioSourceTypeCellType> getMifBioSourceTypeCellType() {
        return mifBioSourceTypeCellType;
    }

    public void setMifBioSourceTypeCellType(Set<MIFBioSourceTypeCellType> mifBioSourceTypeCellType) {
        this.mifBioSourceTypeCellType = mifBioSourceTypeCellType;
    }

    public Set<MIFBioSourceTypeCompartment> getMifBioSourceTypeCompartment() {
        return mifBioSourceTypeCompartment;
    }

    public void setMifBioSourceTypeCompartment(Set<MIFBioSourceTypeCompartment> mifBioSourceTypeCompartment) {
        this.mifBioSourceTypeCompartment = mifBioSourceTypeCompartment;
    }

    public Set<MIFBioSourceTypeTissue> getMifBioSourceTypeTissueD() {
        return mifBioSourceTypeTissueD;
    }

    public void setMifBioSourceTypeTissueD(Set<MIFBioSourceTypeTissue> mifBioSourceTypeTissueD) {
        this.mifBioSourceTypeTissueD = mifBioSourceTypeTissueD;
    }

    public MIFEntryInteractor getMifEntryInteractor() {
        return mifEntryInteractor;
    }

    public void setMifEntryInteractor(MIFEntryInteractor mifEntryInteractor) {
        this.mifEntryInteractor = mifEntryInteractor;
    }

    public MIFInteractionParticipant getMifInteractionParticipant() {
        return mifInteractionParticipant;
    }

    public void setMifInteractionParticipant(MIFInteractionParticipant mifInteractionParticipant) {
        this.mifInteractionParticipant = mifInteractionParticipant;
    }

    public Set<MIFOtherAlias> getMifOtherAlias() {
        return mifOtherAlias;
    }

    public void setMifOtherAlias(Set<MIFOtherAlias> mifOtherAlias) {
        this.mifOtherAlias = mifOtherAlias;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
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

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
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
        final MIFOtherBioSourceType other = (MIFOtherBioSourceType) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFOtherWID != other.mIFOtherWID) {
            return false;
        }
        if (this.taxId != other.taxId) {
            return false;
        }
        if (!Objects.equals(this.shortLabel, other.shortLabel)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFOtherBioSourceType{" + "wid=" + wid + ", mIFOtherWID=" + mIFOtherWID + ", taxId=" + taxId + ", shortLabel=" + shortLabel + ", fullName=" + fullName + ", dataSetWID=" + dataSetWID + '}';
    }
}
