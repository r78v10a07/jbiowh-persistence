package org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;

/**
 * This class is the KEGG Glycan entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $ 
 * $LastChangedRevision: 322 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGGlycan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGlycan.findAll", query = "SELECT k FROM KEGGGlycan k"),
    @NamedQuery(name = "KEGGGlycan.findByWid", query = "SELECT k FROM KEGGGlycan k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGGlycan.findByEntry", query = "SELECT k FROM KEGGGlycan k WHERE k.entry = :entry"),
    @NamedQuery(name = "KEGGGlycan.findByMass", query = "SELECT k FROM KEGGGlycan k WHERE k.mass = :mass"),
    @NamedQuery(name = "KEGGGlycan.findByRemark", query = "SELECT k FROM KEGGGlycan k WHERE k.remark = :remark"),
    @NamedQuery(name = "KEGGGlycan.findByDataSetWID", query = "SELECT k FROM KEGGGlycan k WHERE k.dataSetWID = :dataSetWID")})
public class KEGGGlycan implements Serializable {

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
    @Column(name = "Mass")
    private String mass;
    @Column(name = "Remark")
    private String remark;
    @Lob
    @Column(name = "Composition")
    private String composition;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGGlycan")
    private Set<KEGGGlycanName> kEGGGlycanName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGGLYCAN_HAS_KEGGGLYCANCLASS,
    joinColumns =
    @JoinColumn(name = "KEGGGlycan_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGGlycanClass_WID", referencedColumnName = "WID"))
    private Set<KEGGGlycanClass> kEGGGlycanClass;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGGlycan")
    @MapKey(name = "keggglycandblinkPK")
    private Map<KEGGGlycanDBLinkPK, KEGGGlycanDBLink> kEGGGlycanDBLink;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGGLYCAN_AS_PRODUCT,
    joinColumns =
    @JoinColumn(name = "KEGGGlycan_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"))
    private Set<KEGGReaction> kEGGReactionAsProduct;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGGLYCAN_AS_SUBSTRATE,
    joinColumns =
    @JoinColumn(name = "KEGGGlycan_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"))
    private Set<KEGGReaction> kEGGReactionAsSubstrate;

    public KEGGGlycan() {
    }

    public KEGGGlycan(Long wid) {
        this.wid = wid;
    }

    public KEGGGlycan(Long wid, String entry, long dataSetWID) {
        this.wid = wid;
        this.entry = entry;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        kEGGReactionAsProduct = null;
        kEGGReactionAsSubstrate = null;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Set<KEGGGlycanName> getkEGGGlycanName() {
        return kEGGGlycanName;
    }

    public void setkEGGGlycanName(Set<KEGGGlycanName> kEGGGlycanName) {
        this.kEGGGlycanName = kEGGGlycanName;
    }

    public Set<KEGGReaction> getkEGGReactionAsProduct() {
        return kEGGReactionAsProduct;
    }

    public void setkEGGReactionAsProduct(Set<KEGGReaction> kEGGReactionAsProduct) {
        this.kEGGReactionAsProduct = kEGGReactionAsProduct;
    }

    public Set<KEGGReaction> getkEGGReactionAsSubstrate() {
        return kEGGReactionAsSubstrate;
    }

    public void setkEGGReactionAsSubstrate(Set<KEGGReaction> kEGGReactionAsSubstrate) {
        this.kEGGReactionAsSubstrate = kEGGReactionAsSubstrate;
    }

    public Map<KEGGGlycanDBLinkPK, KEGGGlycanDBLink> getkEGGGlycanDBLink() {
        return kEGGGlycanDBLink;
    }

    public void setkEGGGlycanDBLink(Map<KEGGGlycanDBLinkPK, KEGGGlycanDBLink> kEGGGlycanDBLink) {
        this.kEGGGlycanDBLink = kEGGGlycanDBLink;
    }

    public Set<KEGGGlycanClass> getkEGGGlycanClass() {
        return kEGGGlycanClass;
    }

    public void setkEGGGlycanClass(Set<KEGGGlycanClass> kEGGGlycanClass) {
        this.kEGGGlycanClass = kEGGGlycanClass;
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

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
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
    public boolean equals(Object object) {
        if (!(object instanceof KEGGGlycan)) {
            return false;
        }
        KEGGGlycan other = (KEGGGlycan) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGlycan{" + "wid=" + wid + ", entry=" + entry + ", comment=" + comment + ", mass=" + mass + ", remark=" + remark + ", composition=" + composition + ", dataSetWID=" + dataSetWID + '}';
    }
}
