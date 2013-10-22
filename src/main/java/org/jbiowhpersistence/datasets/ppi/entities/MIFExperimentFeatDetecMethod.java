package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFExperimentFeatDetecMethod entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFExperimentFeatDetecMethod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFExperimentFeatDetecMethod.findAll", query = "SELECT m FROM MIFExperimentFeatDetecMethod m"),
    @NamedQuery(name = "MIFExperimentFeatDetecMethod.findByWid", query = "SELECT m FROM MIFExperimentFeatDetecMethod m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFExperimentFeatDetecMethod.findByMIFEntryExperimentWID", query = "SELECT m FROM MIFExperimentFeatDetecMethod m WHERE m.mIFEntryExperimentWID = :mIFEntryExperimentWID"),
    @NamedQuery(name = "MIFExperimentFeatDetecMethod.findByShortLabel", query = "SELECT m FROM MIFExperimentFeatDetecMethod m WHERE m.shortLabel = :shortLabel"),
    @NamedQuery(name = "MIFExperimentFeatDetecMethod.findByFullName", query = "SELECT m FROM MIFExperimentFeatDetecMethod m WHERE m.fullName = :fullName")})
public class MIFExperimentFeatDetecMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFEntryExperiment_WID")
    private long mIFEntryExperimentWID;
    @Column(name = "ShortLabel")
    private String shortLabel;
    @Column(name = "FullName")
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "MIFEntryExperiment_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntryExperiment mifEntryExperiment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifExperimentFeatDetecMethod")
    private Set<MIFOtherAlias> mifOtherAlias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifExperimentFeatDetecMethod")
    private Set<MIFOtherXRef> mifOtherXRef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifExperimentFeatDetecMethod")
    private Set<MIFOtherXRefGO> mifOtherXRefGO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifExperimentFeatDetecMethod")
    private Set<MIFOtherXRefPubMed> mifOtherXRefPubMed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifExperimentFeatDetecMethod")
    private Set<MIFOtherXRefRefSeq> mifOtherXRefRefSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifExperimentFeatDetecMethod")
    private Set<MIFOtherXRefUniprot> mifOtherXRefUniprot;

    public MIFExperimentFeatDetecMethod() {
    }

    public MIFExperimentFeatDetecMethod(Long wid) {
        this.wid = wid;
    }

    public MIFExperimentFeatDetecMethod(Long wid, long mIFEntryExperimentWID) {
        this.wid = wid;
        this.mIFEntryExperimentWID = mIFEntryExperimentWID;
    }

    public long getmIFEntryExperimentWID() {
        return mIFEntryExperimentWID;
    }

    public void setmIFEntryExperimentWID(long mIFEntryExperimentWID) {
        this.mIFEntryExperimentWID = mIFEntryExperimentWID;
    }

    public MIFEntryExperiment getMifEntryExperiment() {
        return mifEntryExperiment;
    }

    public void setMifEntryExperiment(MIFEntryExperiment mifEntryExperiment) {
        this.mifEntryExperiment = mifEntryExperiment;
    }

    public Set<MIFOtherAlias> getMifOtherAlias() {
        return mifOtherAlias;
    }

    public void setMifOtherAlias(Set<MIFOtherAlias> mifOtherAlias) {
        this.mifOtherAlias = mifOtherAlias;
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

    public long getMIFEntryExperimentWID() {
        return mIFEntryExperimentWID;
    }

    public void setMIFEntryExperimentWID(long mIFEntryExperimentWID) {
        this.mIFEntryExperimentWID = mIFEntryExperimentWID;
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
        final MIFExperimentFeatDetecMethod other = (MIFExperimentFeatDetecMethod) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFEntryExperimentWID != other.mIFEntryExperimentWID) {
            return false;
        }
        if (!Objects.equals(this.shortLabel, other.shortLabel)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MIFExperimentFeatDetecMethod{" + "wid=" + wid + ", mIFEntryExperimentWID=" + mIFEntryExperimentWID + ", shortLabel=" + shortLabel + ", fullName=" + fullName + '}';
    }
}
