package org.jbiowhpersistence.datasets.pathway.kegg.entities.gene;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.GeneTables;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;

/**
 * This class is THE KEGG Gene entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGGene")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGene.findAll", query = "SELECT k FROM KEGGGene k"),
    @NamedQuery(name = "KEGGGene.findByWid", query = "SELECT k FROM KEGGGene k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGGene.findByEntry", query = "SELECT k FROM KEGGGene k WHERE k.entry = :entry"),
    @NamedQuery(name = "KEGGGene.findByPositionStart", query = "SELECT k FROM KEGGGene k WHERE k.positionStart = :positionStart"),
    @NamedQuery(name = "KEGGGene.findByPositionEnd", query = "SELECT k FROM KEGGGene k WHERE k.positionEnd = :positionEnd"),
    @NamedQuery(name = "KEGGGene.findByDataSetWID", query = "SELECT k FROM KEGGGene k WHERE k.dataSetWID = :dataSetWID")})
public class KEGGGene implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Entry")
    private String entry;
    @Lob
    @Column(name = "Definition")
    private String definition;
    @Column(name = "PositionStart")
    private Integer positionStart;
    @Column(name = "PositionEnd")
    private Integer positionEnd;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGGene")
    @MapKey(name = "kegggenedblinkPK")
    private Map<KEGGGeneDBLinkPK, KEGGGeneDBLink> kEGGGeneDBLink;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGGene")
    private Set<KEGGGeneName> kEGGGeneName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGGene")
    private Set<KEGGGeneDisease> kEGGGeneDisease;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGGene")
    @MapKey(name = "kEGGGeneDrugTargetPK")
    private Map<KEGGGeneDrugTargetPK, KEGGGeneDrugTarget> kEGGGeneDrugTarget;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGGene")
    @MapKey(name = "kEGGGeneOrthologyPK")
    private Map<KEGGGeneOrthologyPK, KEGGGeneOrthology> kEGGGeneOrthology;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGGENE_HAS_KEGGPATHWAY,
    joinColumns =
    @JoinColumn(name = "KEGGGene_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"))
    private Set<KEGGPathway> kEGGPathways;
    @ManyToMany
    @JoinTable(name = GeneTables.GENEINFO_HAS_KEGGGENE,
    joinColumns =
    @JoinColumn(name = "KEGGGene_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    private Set<GeneInfo> geneInfos;

    public KEGGGene() {
    }

    public KEGGGene(Long wid) {
        this.wid = wid;
    }

    public KEGGGene(Long wid, String entry, long dataSetWID) {
        this.wid = wid;
        this.entry = entry;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        kEGGPathways = null;
        geneInfos = null;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @XmlTransient
    public Set<GeneInfo> getGeneInfos() {
        return geneInfos;
    }

    public void setGeneInfos(Set<GeneInfo> geneInfos) {
        this.geneInfos = geneInfos;
    }

    @XmlTransient
    public Map<KEGGGeneOrthologyPK, KEGGGeneOrthology> getkEGGGeneOrthology() {
        return kEGGGeneOrthology;
    }

    public void setkEGGGeneOrthology(Map<KEGGGeneOrthologyPK, KEGGGeneOrthology> kEGGGeneOrthology) {
        this.kEGGGeneOrthology = kEGGGeneOrthology;
    }

    @XmlTransient
    public Map<KEGGGeneDrugTargetPK, KEGGGeneDrugTarget> getkEGGGeneDrugTarget() {
        return kEGGGeneDrugTarget;
    }

    public void setkEGGGeneDrugTarget(Map<KEGGGeneDrugTargetPK, KEGGGeneDrugTarget> kEGGGeneDrugTarget) {
        this.kEGGGeneDrugTarget = kEGGGeneDrugTarget;
    }

    @XmlTransient
    public Set<KEGGGeneDisease> getkEGGGeneDisease() {
        return kEGGGeneDisease;
    }

    public void setkEGGGeneDisease(Set<KEGGGeneDisease> kEGGGeneDisease) {
        this.kEGGGeneDisease = kEGGGeneDisease;
    }

    @XmlTransient
    public Set<KEGGPathway> getkEGGPathways() {
        return kEGGPathways;
    }

    public void setkEGGPathways(Set<KEGGPathway> kEGGPathways) {
        this.kEGGPathways = kEGGPathways;
    }

    public String getkEGGGeneNameDirectly() {
        if (!kEGGGeneName.isEmpty()) {
            return kEGGGeneName.iterator().next().getName();
        }
        return null;
    }

    @XmlTransient
    public Set<KEGGGeneName> getkEGGGeneName() {
        return kEGGGeneName;
    }

    public void setkEGGGeneName(Set<KEGGGeneName> kEGGGeneName) {
        this.kEGGGeneName = kEGGGeneName;
    }

    @XmlTransient
    public Map<KEGGGeneDBLinkPK, KEGGGeneDBLink> getkEGGGeneDBLink() {
        return kEGGGeneDBLink;
    }

    public void setkEGGGeneDBLink(Map<KEGGGeneDBLinkPK, KEGGGeneDBLink> kEGGGeneDBLink) {
        this.kEGGGeneDBLink = kEGGGeneDBLink;
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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Integer getPositionStart() {
        return positionStart;
    }

    public void setPositionStart(Integer positionStart) {
        this.positionStart = positionStart;
    }

    public Integer getPositionEnd() {
        return positionEnd;
    }

    public void setPositionEnd(Integer positionEnd) {
        this.positionEnd = positionEnd;
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
        final KEGGGene other = (KEGGGene) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.entry, other.entry)) {
            return false;
        }
        if (!Objects.equals(this.definition, other.definition)) {
            return false;
        }
        if (!Objects.equals(this.positionStart, other.positionStart)) {
            return false;
        }
        if (!Objects.equals(this.positionEnd, other.positionEnd)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGene{" + "wid=" + wid + ", entry=" + entry + ", definition=" + definition + ", positionStart=" + positionStart + ", positionEnd=" + positionEnd + ", dataSetWID=" + dataSetWID + '}';
    }
}
