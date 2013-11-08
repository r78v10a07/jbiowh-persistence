package org.jbiowhpersistence.datasets.pathway.kegg.entities.compound;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;

/**
 * This class is the KEGG Compound entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGCompound")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGCompound.findAll", query = "SELECT k FROM KEGGCompound k"),
    @NamedQuery(name = "KEGGCompound.findByWid", query = "SELECT k FROM KEGGCompound k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGCompound.findByEntry", query = "SELECT k FROM KEGGCompound k WHERE k.entry = :entry"),
    @NamedQuery(name = "KEGGCompound.findByMass", query = "SELECT k FROM KEGGCompound k WHERE k.mass = :mass"),
    @NamedQuery(name = "KEGGCompound.findByRemark", query = "SELECT k FROM KEGGCompound k WHERE k.remark = :remark"),
    @NamedQuery(name = "KEGGCompound.findByFormula", query = "SELECT k FROM KEGGCompound k WHERE k.formula = :formula"),
    @NamedQuery(name = "KEGGCompound.findByDataSetWID", query = "SELECT k FROM KEGGCompound k WHERE k.dataSetWID = :dataSetWID")})
public class KEGGCompound implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Entry")
    private String entry;
    @Lob
    @Column(name = "Comment")
    private String comment;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Mass")
    private Float mass;
    @Column(name = "Remark")
    private String remark;
    @Column(name = "Formula")
    private String formula;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGCompound")
    @MapKey(name = "keggcompounddblinkPK")
    private Map<KEGGCompoundDBLinkPK, KEGGCompoundDBLink> kEGGCompoundDBLink;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGCompound")
    private Set<KEGGCompoundName> kEGGCompoundName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGCOMPOUND_AS_PRODUCT,
    joinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"))
    private Set<KEGGReaction> kEGGReactionAsProduct;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGCOMPOUND_AS_SUBSTRATE,
    joinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"))
    private Set<KEGGReaction> kEGGReactionAsSubstrate;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGENZYME_HAS_KEGGCOMPOUND_AS_COFACTOR,
    joinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"))
    private Set<KEGGEnzyme> kEGGEnzymeAsCofactor;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGENZYME_HAS_KEGGCOMPOUND_AS_EFFECTOR,
    joinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"))
    private Set<KEGGEnzyme> kEGGEnzymeAsEffector;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGENZYME_HAS_KEGGCOMPOUND_AS_INHIBITOR,
    joinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"))
    private Set<KEGGEnzyme> kEGGEnzymeAsInhibitor;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGRPAIR_HAS_KEGGCOMPOUND,
    joinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGRPair_WID", referencedColumnName = "WID"))
    private Set<KEGGRPair> kEGGRPair;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGCOMPOUND_HAS_KEGGPATHWAY,
    joinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"))
    private Set<KEGGPathway> kEGGPathways;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGCOMPOUND_HAS_DRUGBANK,
    joinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    private Set<DrugBank> drugBanks;

    public KEGGCompound() {
    }

    public KEGGCompound(Long wid) {
        this.wid = wid;
    }

    public KEGGCompound(Long wid, String entry, long dataSetWID) {
        this.wid = wid;
        this.entry = entry;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        kEGGReactionAsProduct = null;
        kEGGReactionAsSubstrate = null;
        kEGGEnzymeAsCofactor = null;
        kEGGEnzymeAsEffector = null;
        kEGGEnzymeAsInhibitor = null;
        kEGGRPair = null;
        kEGGPathways = null;
        drugBanks = null;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBanks() {
        return drugBanks;
    }

    public void setDrugBanks(Set<DrugBank> drugBanks) {
        this.drugBanks = drugBanks;
    }

    @XmlTransient
    public Set<KEGGPathway> getkEGGPathways() {
        return kEGGPathways;
    }

    public void setkEGGPathways(Set<KEGGPathway> kEGGPathways) {
        this.kEGGPathways = kEGGPathways;
    }

    @XmlTransient
    public Set<KEGGRPair> getkEGGRPair() {
        return kEGGRPair;
    }

    public void setkEGGRPair(Set<KEGGRPair> kEGGRPair) {
        this.kEGGRPair = kEGGRPair;
    }

    @XmlTransient
    public Set<KEGGEnzyme> getkEGGEnzymeAsCofactor() {
        return kEGGEnzymeAsCofactor;
    }

    public void setkEGGEnzymeAsCofactor(Set<KEGGEnzyme> kEGGEnzymeAsCofactor) {
        this.kEGGEnzymeAsCofactor = kEGGEnzymeAsCofactor;
    }

    @XmlTransient
    public Set<KEGGEnzyme> getkEGGEnzymeAsEffector() {
        return kEGGEnzymeAsEffector;
    }

    public void setkEGGEnzymeAsEffector(Set<KEGGEnzyme> kEGGEnzymeAsEffector) {
        this.kEGGEnzymeAsEffector = kEGGEnzymeAsEffector;
    }

    @XmlTransient
    public Set<KEGGEnzyme> getkEGGEnzymeAsInhibitor() {
        return kEGGEnzymeAsInhibitor;
    }

    public void setkEGGEnzymeAsInhibitor(Set<KEGGEnzyme> kEGGEnzymeAsInhibitor) {
        this.kEGGEnzymeAsInhibitor = kEGGEnzymeAsInhibitor;
    }

    @XmlTransient
    public Set<KEGGReaction> getkEGGReactionAsProduct() {
        return kEGGReactionAsProduct;
    }

    public void setkEGGReactionAsProduct(Set<KEGGReaction> kEGGReactionAsProduct) {
        this.kEGGReactionAsProduct = kEGGReactionAsProduct;
    }

    @XmlTransient
    public Set<KEGGReaction> getkEGGReactionAsSubstrate() {
        return kEGGReactionAsSubstrate;
    }

    public void setkEGGReactionAsSubstrate(Set<KEGGReaction> kEGGReactionAsSubstrate) {
        this.kEGGReactionAsSubstrate = kEGGReactionAsSubstrate;
    }

    @XmlTransient
    public Map<KEGGCompoundDBLinkPK, KEGGCompoundDBLink> getkEGGCompoundDBLink() {
        return kEGGCompoundDBLink;
    }

    public void setkEGGCompoundDBLink(Map<KEGGCompoundDBLinkPK, KEGGCompoundDBLink> kEGGCompoundDBLink) {
        this.kEGGCompoundDBLink = kEGGCompoundDBLink;
    }

    @XmlTransient
    public Set<KEGGCompoundName> getkEGGCompoundName() {
        return kEGGCompoundName;
    }

    public void setkEGGCompoundName(Set<KEGGCompoundName> kEGGCompoundName) {
        this.kEGGCompoundName = kEGGCompoundName;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getMass() {
        return mass;
    }

    public void setMass(Float mass) {
        this.mass = mass;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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
        final KEGGCompound other = (KEGGCompound) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.entry, other.entry)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.mass, other.mass)) {
            return false;
        }
        if (!Objects.equals(this.remark, other.remark)) {
            return false;
        }
        if (!Objects.equals(this.formula, other.formula)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGCompound{" + "wid=" + wid + ", entry=" + entry + ", comment=" + comment + ", mass=" + mass + ", remark=" + remark + ", formula=" + formula + ", dataSetWID=" + dataSetWID + '}';
    }
}
