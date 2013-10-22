package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFOtherConfidence entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFOtherConfidence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFOtherConfidence.findAll", query = "SELECT m FROM MIFOtherConfidence m"),
    @NamedQuery(name = "MIFOtherConfidence.findByWid", query = "SELECT m FROM MIFOtherConfidence m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFOtherConfidence.findByMIFOtherWID", query = "SELECT m FROM MIFOtherConfidence m WHERE m.mIFOtherWID = :mIFOtherWID"),
    @NamedQuery(name = "MIFOtherConfidence.findByShortLabel", query = "SELECT m FROM MIFOtherConfidence m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFOtherConfidence.findByFullName", query = "SELECT m FROM MIFOtherConfidence m WHERE m.fullName = :fullName"),
    @NamedQuery(name = "MIFOtherConfidence.findByValueVal", query = "SELECT m FROM MIFOtherConfidence m WHERE m.valueVal = :valueVal"),
    @NamedQuery(name = "MIFOtherConfidence.findByDataSetWID", query = "SELECT m FROM MIFOtherConfidence m WHERE m.dataSetWID = :dataSetWID")})
public class MIFOtherConfidence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFOtherWID")
    private long mIFOtherWID;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "ValueVal")
    private String valueVal;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryExperiment mifEntryExperiment;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFInteractionParticipant mifInteractionParticipant;
    @ManyToOne
    @JoinColumn(name = "MIFOtherWID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryInteraction mifEntryInteraction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherConfidence")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherConfidence")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherConfidence")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherConfidence")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherConfidence")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherConfidence")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifOtherConfidence")
    @MapKey(name = "mIFOtherExperimentRefPK")
    private Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherWIDExperimentRef;

    public MIFOtherConfidence() {
    }

    public MIFOtherConfidence(Long wid) {
        this.wid = wid;
    }

    public MIFOtherConfidence(Long wid, long mIFOtherWID, long dataSetWID) {
        this.wid = wid;
        this.mIFOtherWID = mIFOtherWID;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        mifEntryExperiment = null;
        mifInteractionParticipant = null;
        mifEntryInteraction = null;
        mifOtherAlias = null;
        mifOtherXRef = null;
        mifOtherXRefGO = null;
        mifOtherXRefPubMed = null;
        mifOtherXRefRefSeq = null;
        mifOtherXRefUniprot = null;
        mifOtherWIDExperimentRef = null;
    }

    public long getmIFOtherWID() {
        return mIFOtherWID;
    }

    public void setmIFOtherWID(long mIFOtherWID) {
        this.mIFOtherWID = mIFOtherWID;
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

    public Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> getMifOtherWIDExperimentRef() {
        return mifOtherWIDExperimentRef;
    }

    public void setMifOtherWIDExperimentRef(Map<MIFOtherExperimentRefPK, MIFOtherExperimentRef> mifOtherWIDExperimentRef) {
        this.mifOtherWIDExperimentRef = mifOtherWIDExperimentRef;
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

    public String getValueVal() {
        return valueVal;
    }

    public void setValueVal(String valueVal) {
        this.valueVal = valueVal;
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
        final MIFOtherConfidence other = (MIFOtherConfidence) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFOtherWID != other.mIFOtherWID) {
            return false;
        }
        if (!Objects.equals(this.shortLabel, other.shortLabel)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.valueVal, other.valueVal)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFOtherConfidence{" + "wid=" + wid + ", mIFOtherWID=" + mIFOtherWID + ", shortLabel=" + shortLabel + ", fullName=" + fullName + ", valueVal=" + valueVal + ", dataSetWID=" + dataSetWID + '}';
    }
}
