package org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan.KEGGGlycan;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;

/**
 * This class is the KEGGReaction entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGReaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGReaction.findAll", query = "SELECT k FROM KEGGReaction k"),
    @NamedQuery(name = "KEGGReaction.findByWid", query = "SELECT k FROM KEGGReaction k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGReaction.findByEntry", query = "SELECT k FROM KEGGReaction k WHERE k.entry = :entry"),
    @NamedQuery(name = "KEGGReaction.findByRemark", query = "SELECT k FROM KEGGReaction k WHERE k.remark = :remark"),
    @NamedQuery(name = "KEGGReaction.findByDataSetWID", query = "SELECT k FROM KEGGReaction k WHERE k.dataSetWID = :dataSetWID")})
public class KEGGReaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Entry")
    private String entry;
    @Lob
    @Column(name = "Comment")
    private String comment;
    @Lob
    @Column(name = "Definition")
    private String definition;
    @Lob
    @Column(name = "Equation")
    private String equation;
    @Column(name = "Remark")
    private String remark;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
     @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGReaction")
    private Set<KEGGReactionName> kEGGReactionName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGCOMPOUND_AS_PRODUCT,
    joinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"))
    private Set<KEGGCompound> kEGGCompoundAsProduct;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGCOMPOUND_AS_SUBSTRATE,
    joinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"))
    private Set<KEGGCompound> kEGGCompoundAsSubstrate;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGGLYCAN_AS_PRODUCT,
    joinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGGlycan_WID", referencedColumnName = "WID"))
    private Set<KEGGGlycan> kEGGGlycanAsProduct;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGGLYCAN_AS_SUBSTRATE,
    joinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGGlycan_WID", referencedColumnName = "WID"))
    private Set<KEGGGlycan> kEGGGlycanAsSubstrate;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGENZYME,
    joinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"))
    private Set<KEGGEnzyme> kEGGEnzyme;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGPATHWAY,
    joinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"))
    private Set<KEGGPathway> kEGGPathways;

    public KEGGReaction() {
    }

    public KEGGReaction(Long wid) {
        this.wid = wid;
    }

    public KEGGReaction(Long wid, long dataSetWID) {
        this.wid = wid;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        kEGGCompoundAsProduct = null;
        kEGGCompoundAsSubstrate = null;
        kEGGGlycanAsProduct = null;
        kEGGGlycanAsSubstrate = null;
        kEGGEnzyme = null;
        kEGGPathways = null;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @XmlTransient
    public Set<KEGGPathway> getkEGGPathways() {
        return kEGGPathways;
    }

    public void setkEGGPathways(Set<KEGGPathway> kEGGPathways) {
        this.kEGGPathways = kEGGPathways;
    }

    @XmlTransient
    public Set<KEGGEnzyme> getkEGGEnzyme() {
        return kEGGEnzyme;
    }

    public void setkEGGEnzyme(Set<KEGGEnzyme> kEGGEnzyme) {
        this.kEGGEnzyme = kEGGEnzyme;
    }

    @XmlTransient
    public Set<KEGGGlycan> getkEGGGlycanAsProduct() {
        return kEGGGlycanAsProduct;
    }

    public void setkEGGGlycanAsProduct(Set<KEGGGlycan> kEGGGlycanAsProduct) {
        this.kEGGGlycanAsProduct = kEGGGlycanAsProduct;
    }

    @XmlTransient
    public Set<KEGGGlycan> getkEGGGlycanAsSubstrate() {
        return kEGGGlycanAsSubstrate;
    }

    public void setkEGGGlycanAsSubstrate(Set<KEGGGlycan> kEGGGlycanAsSubstrate) {
        this.kEGGGlycanAsSubstrate = kEGGGlycanAsSubstrate;
    }

    @XmlTransient
    public Set<KEGGCompound> getkEGGCompoundAsProduct() {
        return kEGGCompoundAsProduct;
    }

    public void setkEGGCompoundAsProduct(Set<KEGGCompound> kEGGCompoundAsProduct) {
        this.kEGGCompoundAsProduct = kEGGCompoundAsProduct;
    }

    @XmlTransient
    public Set<KEGGCompound> getkEGGCompoundAsSubstrate() {
        return kEGGCompoundAsSubstrate;
    }

    public void setkEGGCompoundAsSubstrate(Set<KEGGCompound> kEGGCompoundAsSubstrate) {
        this.kEGGCompoundAsSubstrate = kEGGCompoundAsSubstrate;
    }

    @XmlTransient
    public Set<KEGGReactionName> getkEGGReactionName() {
        return kEGGReactionName;
    }

    public void setkEGGReactionName(Set<KEGGReactionName> kEGGReactionName) {
        this.kEGGReactionName = kEGGReactionName;
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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        final KEGGReaction other = (KEGGReaction) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.entry, other.entry)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.definition, other.definition)) {
            return false;
        }
        if (!Objects.equals(this.equation, other.equation)) {
            return false;
        }
        if (!Objects.equals(this.remark, other.remark)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGReaction{" + "wid=" + wid + ", entry=" + entry + ", comment=" + comment + ", definition=" + definition + ", equation=" + equation + ", remark=" + remark + ", dataSetWID=" + dataSetWID + '}';
    }
}
