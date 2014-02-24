package org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.ProtClustTables;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This class is the NCBI Protein Cluster entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 13, 2014
 */
@Entity
@Table(name = "ProtClust")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProtClust.findAll", query = "SELECT p FROM ProtClust p"),
    @NamedQuery(name = "ProtClust.findByWid", query = "SELECT p FROM ProtClust p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProtClust.findByEntry", query = "SELECT p FROM ProtClust p WHERE p.entry = :entry"),
    @NamedQuery(name = "ProtClust.findByLocus", query = "SELECT p FROM ProtClust p WHERE p.locus = :locus"),
    @NamedQuery(name = "ProtClust.findByStatus", query = "SELECT p FROM ProtClust p WHERE p.status = :status"),
    @NamedQuery(name = "ProtClust.findByDataSetWID", query = "SELECT p FROM ProtClust p WHERE p.dataSetWID = :dataSetWID")})
public class ProtClust implements Serializable {

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
    @Column(name = "Locus")
    private String locus;
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    // Internal relations
    @ElementCollection
    @CollectionTable(
            name = "ProtClustProteins",
            joinColumns
            = @JoinColumn(name = "ProtClust_WID"))
    @XmlElementWrapper(name = "ProtClustProteins")
    private Collection<ProtClustProteins> protClustProtein;
    @ElementCollection
    @CollectionTable(
            name = "ProtClustXRef",
            joinColumns
            = @JoinColumn(name = "ProtClust_WID"))
    @XmlElementWrapper(name = "ProtClustXRefs")
    private Collection<ProtClustXRef> protClustXRef;
    @ElementCollection
    @CollectionTable(
            name = "ProtClustPMID",
            joinColumns
            = @JoinColumn(name = "ProtClust_WID"))
    @XmlElementWrapper(name = "ProtClustPMIDs")
    private Collection<ProtClustPMID> protClustPMID;
    @ElementCollection
    @CollectionTable(
            name = "ProtClustEC",
            joinColumns
            = @JoinColumn(name = "ProtClust_WID"))
    @XmlElementWrapper(name = "ProtClustECs")
    private Collection<ProtClustEC> protClustEC;
    // External relations
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProtClustTables.PROTCLUST_HAS_TAXONOMY,
            joinColumns
            = @JoinColumn(name = "ProtClust_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"))
    private Set<Taxonomy> taxonomy;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProtClustTables.PROTCLUST_HAS_GENEINFO,
            joinColumns
            = @JoinColumn(name = "ProtClust_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    private Collection<GeneInfo> geneInfo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProtClustTables.PROTCLUST_HAS_PROTEIN,
            joinColumns
            = @JoinColumn(name = "ProtClust_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> protein;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProtClustTables.PROTCLUST_HAS_COGORTHOLOGOUSGROUP,
            joinColumns
            = @JoinColumn(name = "ProtClust_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "COGOrthologousGroup_WID", referencedColumnName = "WID"))
    private Set<COGOrthologousGroup> cogOrthologousGroup;

    public ProtClust() {
    }

    public ProtClust(Long wid) {
        this.wid = wid;
    }

    public ProtClust(Long wid, String entry, long dataSetWID) {
        this.wid = wid;
        this.entry = entry;
        this.dataSetWID = dataSetWID;
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

    public String getLocus() {
        return locus;
    }

    public void setLocus(String locus) {
        this.locus = locus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public Collection<ProtClustProteins> getProtClustProtein() {
        return protClustProtein;
    }

    public void setProtClustProtein(Collection<ProtClustProteins> protClustProtein) {
        this.protClustProtein = protClustProtein;
    }

    public Collection<ProtClustXRef> getProtClustXRef() {
        return protClustXRef;
    }

    public void setProtClustXRef(Collection<ProtClustXRef> protClustXRef) {
        this.protClustXRef = protClustXRef;
    }

    public Collection<ProtClustPMID> getProtClustPMID() {
        return protClustPMID;
    }

    public void setProtClustPMID(Collection<ProtClustPMID> protClustPMID) {
        this.protClustPMID = protClustPMID;
    }

    public Collection<ProtClustEC> getProtClustEC() {
        return protClustEC;
    }

    public void setProtClustEC(Collection<ProtClustEC> protClustEC) {
        this.protClustEC = protClustEC;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @XmlTransient
    public Set<Taxonomy> getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Set<Taxonomy> taxonomy) {
        this.taxonomy = taxonomy;
    }

    @XmlTransient
    public Collection<GeneInfo> getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(Collection<GeneInfo> geneInfo) {
        this.geneInfo = geneInfo;
    }

    @XmlTransient
    public Set<Protein> getProtein() {
        return protein;
    }

    public void setProtein(Set<Protein> protein) {
        this.protein = protein;
    }

    @XmlTransient
    public Set<COGOrthologousGroup> getCogOrthologousGroup() {
        return cogOrthologousGroup;
    }

    public void setCogOrthologousGroup(Set<COGOrthologousGroup> cogOrthologousGroup) {
        this.cogOrthologousGroup = cogOrthologousGroup;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 37 * hash + (this.entry != null ? this.entry.hashCode() : 0);
        hash = 37 * hash + (this.definition != null ? this.definition.hashCode() : 0);
        hash = 37 * hash + (this.locus != null ? this.locus.hashCode() : 0);
        hash = 37 * hash + (this.status != null ? this.status.hashCode() : 0);
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
        final ProtClust other = (ProtClust) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if ((this.entry == null) ? (other.entry != null) : !this.entry.equals(other.entry)) {
            return false;
        }
        if ((this.definition == null) ? (other.definition != null) : !this.definition.equals(other.definition)) {
            return false;
        }
        if ((this.locus == null) ? (other.locus != null) : !this.locus.equals(other.locus)) {
            return false;
        }
        return !((this.status == null) ? (other.status != null) : !this.status.equals(other.status));
    }

    @Override
    public String toString() {
        return "ProtClust{" + "wid=" + wid
                + ", entry=" + entry
                + ", definition=" + definition
                + ", locus=" + locus
                + ", status=" + status
                + ", dataSetWID=" + dataSetWID + '}';
    }
}
