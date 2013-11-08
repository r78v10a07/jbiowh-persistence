package org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This class is the KEGG Pathway entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGPathway")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGPathway.findAll", query = "SELECT k FROM KEGGPathway k"),
    @NamedQuery(name = "KEGGPathway.findByWid", query = "SELECT k FROM KEGGPathway k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGPathway.findByEntry", query = "SELECT k FROM KEGGPathway k WHERE UPPER(k.entry) like :entry"),
    @NamedQuery(name = "KEGGPathway.findByOrg", query = "SELECT k FROM KEGGPathway k WHERE k.org = :org"),
    @NamedQuery(name = "KEGGPathway.findByNumber", query = "SELECT k FROM KEGGPathway k WHERE k.number = :number"),
    @NamedQuery(name = "KEGGPathway.findByTitle", query = "SELECT k FROM KEGGPathway k WHERE UPPER(k.title) like :title"),
    @NamedQuery(name = "KEGGPathway.findByImage", query = "SELECT k FROM KEGGPathway k WHERE k.image = :image"),
    @NamedQuery(name = "KEGGPathway.findByLink", query = "SELECT k FROM KEGGPathway k WHERE k.link = :link"),
    @NamedQuery(name = "KEGGPathway.findByDataSetWID", query = "SELECT k FROM KEGGPathway k WHERE k.dataSetWID = :dataSetWID"),
    @NamedQuery(name = "KEGGPathway.findKEGGPathwayByEC", query = "SELECT k.wid FROM KEGGPathway k INNER JOIN k.kEGGEnzymes n WHERE UPPER(n.entry) LIKE :entry GROUP BY k.wid")
})
public class KEGGPathway implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Entry")
    private String entry;
    @Column(name = "Org")
    private String org;
    @Basic(optional = false)
    @Column(name = "Number")
    private String number;
    @Column(name = "Title")
    private String title;
    @Column(name = "Image")
    private String image;
    @Column(name = "Link")
    private String link;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGPathway")
    private Set<KEGGPathwayEntry> kEGGPathwayEntry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGPathway")
    private Set<KEGGPathwayReaction> kEGGPathwayReaction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGPathway")
    private Set<KEGGPathwayRelation> kEGGPathwayRelation;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGCOMPOUND_HAS_KEGGPATHWAY,
    joinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"))
    private Set<KEGGCompound> kEGGCompounds;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGPATHWAY,
    joinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"))
    private Set<KEGGReaction> kEGGReactions;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGGENE_HAS_KEGGPATHWAY,
    joinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGGene_WID", referencedColumnName = "WID"))
    private Set<KEGGGene> kEGGGenes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGENZYME_HAS_KEGGPATHWAY,
    joinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"))
    private Set<KEGGEnzyme> kEGGEnzymes;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGPATHWAY_HAS_TAXONOMY,
    joinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"))
    private Taxonomy taxonomy;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGPATHWAY_HAS_PROTEN,
    joinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> protein;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGPATHWAY_HAS_GENEINFO,
    joinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    private Set<GeneInfo> geneInfo;

    public KEGGPathway() {
    }

    public KEGGPathway(Long wid) {
        this.wid = wid;
    }

    public KEGGPathway(Long wid, String entry, String number, long dataSetWID) {
        this.wid = wid;
        this.entry = entry;
        this.number = number;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        protein = null;
        geneInfo = null;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @XmlTransient
    public Set<GeneInfo> getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(Set<GeneInfo> geneInfos) {
        this.geneInfo = geneInfos;
    }

    @XmlTransient
    public Set<Protein> getProtein() {
        return protein;
    }

    public void setProtein(Set<Protein> protein) {
        this.protein = protein;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    @XmlTransient
    public Set<KEGGCompound> getkEGGCompounds() {
        return kEGGCompounds;
    }

    public void setkEGGCompounds(Set<KEGGCompound> kEGGCompounds) {
        this.kEGGCompounds = kEGGCompounds;
    }

    @XmlTransient
    public Set<KEGGEnzyme> getkEGGEnzymes() {
        return kEGGEnzymes;
    }

    public void setkEGGEnzymes(Set<KEGGEnzyme> kEGGEnzymes) {
        this.kEGGEnzymes = kEGGEnzymes;
    }

    @XmlTransient
    public Set<KEGGGene> getkEGGGenes() {
        return kEGGGenes;
    }

    public void setkEGGGenes(Set<KEGGGene> kEGGGenes) {
        this.kEGGGenes = kEGGGenes;
    }

    @XmlTransient
    public Set<KEGGReaction> getkEGGReactions() {
        return kEGGReactions;
    }

    public void setkEGGReactions(Set<KEGGReaction> kEGGReactions) {
        this.kEGGReactions = kEGGReactions;
    }

    @XmlTransient
    public Set<KEGGPathwayRelation> getkEGGPathwayRelation() {
        return kEGGPathwayRelation;
    }

    public void setkEGGPathwayRelation(Set<KEGGPathwayRelation> kEGGPathwayRelation) {
        this.kEGGPathwayRelation = kEGGPathwayRelation;
    }

    @XmlTransient
    public Set<KEGGPathwayReaction> getkEGGPathwayReaction() {
        return kEGGPathwayReaction;
    }

    public void setkEGGPathwayReaction(Set<KEGGPathwayReaction> kEGGPathwayReaction) {
        this.kEGGPathwayReaction = kEGGPathwayReaction;
    }

    @XmlTransient
    public Set<KEGGPathwayEntry> getkEGGPathwayEntry() {
        return kEGGPathwayEntry;
    }

    public void setkEGGPathwayEntry(Set<KEGGPathwayEntry> kEGGPathwayEntry) {
        this.kEGGPathwayEntry = kEGGPathwayEntry;
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

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        final KEGGPathway other = (KEGGPathway) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.entry, other.entry)) {
            return false;
        }
        if (!Objects.equals(this.org, other.org)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGPathway{" + "wid=" + wid + ", entry=" + entry + ", org=" + org + ", number=" + number + ", title=" + title + ", image=" + image + ", link=" + link + ", dataSetWID=" + dataSetWID + '}';
    }
}
