package org.jbiowhpersistence.datasets.gene.genebank.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genebank.GeneBankTables;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.ProtClustTables;
import org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities.ProtClust;

/**
 * This class is the GeneBankCDS entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 638 $
 *
 * @since May 2, 2013
 */
@Entity
@Table(name = "GeneBankCDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneBankCDS.findAll", query = "SELECT g FROM GeneBankCDS g"),
    @NamedQuery(name = "GeneBankCDS.findByWid", query = "SELECT g FROM GeneBankCDS g WHERE g.wid = :wid"),
    @NamedQuery(name = "GeneBankCDS.findByProteinGi", query = "SELECT g FROM GeneBankCDS g WHERE g.proteinGi = :proteinGi"),
    @NamedQuery(name = "GeneBankCDS.findByLocation", query = "SELECT g FROM GeneBankCDS g WHERE g.location = :location"),
    @NamedQuery(name = "GeneBankCDS.findByProduct", query = "SELECT g FROM GeneBankCDS g WHERE g.product = :product"),
    @NamedQuery(name = "GeneBankCDS.findByProteinId", query = "SELECT g FROM GeneBankCDS g WHERE g.proteinId = :proteinId"),
    @NamedQuery(name = "GeneBankCDS.findByGene", query = "SELECT g FROM GeneBankCDS g WHERE g.gene = :gene"),
    @NamedQuery(name = "GeneBankCDS.findByLocusTag", query = "SELECT g FROM GeneBankCDS g WHERE g.locusTag = :locusTag"),
    @NamedQuery(name = "GeneBankCDS.findByLocationFromGeneBankWID", query = "SELECT g FROM GeneBankCDS g INNER JOIN g.geneBankCDSLocation l WHERE g.geneBankWID = :geneBankWID AND ((l.pFrom <= :pFrom AND l.pTo >= :pFrom) OR (l.pFrom <= :pTo AND l.pTo >= :pTo))"),
    @NamedQuery(name = "GeneBankCDS.findBypFromGeneBankWID", query = "SELECT g FROM GeneBankCDS g INNER JOIN g.geneBankCDSLocation l WHERE g.geneBankWID = :geneBankWID AND l.pFrom = :pFrom"),
    @NamedQuery(name = "GeneBankCDS.findByGeneBankWID", query = "SELECT g FROM GeneBankCDS g WHERE g.geneBankWID = :geneBankWID")})
public class GeneBankCDS implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "ProteinGi")
    private int proteinGi;
    @Basic(optional = false)
    @Column(name = "Location")
    private String location;
    @Column(name = "Product")
    private String product;
    @Column(name = "ProteinId")
    private String proteinId;
    @Column(name = "Gene")
    private String gene;
    @Column(name = "Locus_Tag")
    private String locusTag;
    @Basic(optional = false)
    @Column(name = "Translation")
    private Boolean translation;
    @Basic(optional = false)
    @Column(name = "GeneBank_WID")
    private long geneBankWID;
    @ElementCollection
    @CollectionTable(
            name = "GeneBankCDSDBXref",
            joinColumns
            = @JoinColumn(name = "GeneBankCDS_WID"))
    private Collection<GeneBankCDSDBXref> geneBankCDSDBXrefs;
    @ElementCollection
    @CollectionTable(
            name = "GeneBankCDSLocation",
            joinColumns
            = @JoinColumn(name = "GeneBankCDS_WID"))
    private Collection<GeneBankCDSLocation> geneBankCDSLocation;
    // Internal relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    @XmlElement
    @XmlInverseReference(mappedBy = "geneBankCDSs")
    private GeneBank geneBank;
    @ElementCollection
    @CollectionTable(
            name = "GeneBankCOG",
            joinColumns
            = @JoinColumn(name = "GeneBankCDS_WID"))
    private Collection<GeneBankCOG> geneBankCOG;
    // External relationship
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = GeneBankTables.GENEBANKCDS_HAS_GENEINFO,
            joinColumns
            = @JoinColumn(name = "GeneBankCDS_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    private Collection<GeneInfo> geneInfo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinGi", insertable = false, unique = false, nullable = true, updatable = false)
    private GenePTT genePTT;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = GeneBankTables.GENEBANKCOG,
            joinColumns
            = @JoinColumn(name = "GeneBankCDS_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "COGId", referencedColumnName = "Id"))
    private Collection<COGOrthologousGroup> cogOrthologousGroup;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProtClustTables.PROTCLUSTPROTEINS,
            joinColumns
            = @JoinColumn(name = "ProteinGi", referencedColumnName = "ProteinGi"),
            inverseJoinColumns
            = @JoinColumn(name = "ProtClust_WID", referencedColumnName = "WID"))
    private Collection<ProtClust> protClust;
    
    public GeneBankCDS() {
    }
    
    public GeneBankCDS(Long wid) {
        this.wid = wid;
    }
    
    public GeneBankCDS(Long wid, int proteinGi, String location, long geneBankWID) {
        this.wid = wid;
        this.proteinGi = proteinGi;
        this.location = location;
        this.geneBankWID = geneBankWID;
    }
    
    public void setRelationsToNull() {
        setGeneInfo(null);
        setGenePTT(null);
        setGeneBank(null);
        setCogOrthologousGroup(null);
        setProtClust(null);
    }
    
    public Boolean isTranslation() {
        return translation;
    }
    
    public void setTranslation(Boolean translation) {
        this.translation = translation;
    }
    
    public String getGene() {
        return gene;
    }
    
    public void setGene(String gene) {
        this.gene = gene;
    }
    
    public String getLocusTag() {
        return locusTag;
    }
    
    public void setLocusTag(String locusTag) {
        this.locusTag = locusTag;
    }
    
    public Collection<GeneBankCOG> getGeneBankCOG() {
        return geneBankCOG;
    }
    
    public void setGeneBankCOG(Collection<GeneBankCOG> geneBankCOG) {
        this.geneBankCOG = geneBankCOG;
    }
    
    public Collection<ProtClust> getProtClust() {
        return protClust;
    }
    
    public void setProtClust(Collection<ProtClust> protClust) {
        this.protClust = protClust;
    }
    
    public Collection<COGOrthologousGroup> getCogOrthologousGroup() {
        return cogOrthologousGroup;
    }
    
    public void setCogOrthologousGroup(Collection<COGOrthologousGroup> cogOrthologousGroup) {
        this.cogOrthologousGroup = cogOrthologousGroup;
    }
    
    public Collection<GeneBankCDSDBXref> getGeneBankCDSDBXrefs() {
        return geneBankCDSDBXrefs;
    }
    
    public void setGeneBankCDSDBXrefs(Collection<GeneBankCDSDBXref> geneBankCDSDBXrefs) {
        this.geneBankCDSDBXrefs = geneBankCDSDBXrefs;
    }
    
    public Collection<GeneBankCDSLocation> getGeneBankCDSLocation() {
        return geneBankCDSLocation;
    }
    
    public void setGeneBankCDSLocation(Collection<GeneBankCDSLocation> geneBankCDSLocation) {
        this.geneBankCDSLocation = geneBankCDSLocation;
    }
    
    public GeneBank getGeneBank() {
        return geneBank;
    }
    
    public void setGeneBank(GeneBank geneBank) {
        this.geneBank = geneBank;
    }
    
    @XmlTransient
    public Collection<GeneInfo> getGeneInfo() {
        return geneInfo;
    }
    
    public void setGeneInfo(Collection<GeneInfo> geneInfo) {
        this.geneInfo = geneInfo;
    }
    
    public GenePTT getGenePTT() {
        return genePTT;
    }
    
    public void setGenePTT(GenePTT genePTT) {
        this.genePTT = genePTT;
    }
    
    public Long getWid() {
        return wid;
    }
    
    public void setWid(Long wid) {
        this.wid = wid;
    }
    
    public int getProteinGi() {
        return proteinGi;
    }
    
    public void setProteinGi(int proteinGi) {
        this.proteinGi = proteinGi;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getProduct() {
        return product;
    }
    
    public void setProduct(String product) {
        this.product = product;
    }
    
    public String getProteinId() {
        return proteinId;
    }
    
    public void setProteinId(String proteinId) {
        this.proteinId = proteinId;
    }
    
    public long getGeneBankWID() {
        return geneBankWID;
    }
    
    public void setGeneBankWID(long geneBankWID) {
        this.geneBankWID = geneBankWID;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 53 * hash + this.proteinGi;
        hash = 53 * hash + (this.location != null ? this.location.hashCode() : 0);
        hash = 53 * hash + (this.product != null ? this.product.hashCode() : 0);
        hash = 53 * hash + (this.proteinId != null ? this.proteinId.hashCode() : 0);
        hash = 53 * hash + (this.gene != null ? this.gene.hashCode() : 0);
        hash = 53 * hash + (this.locusTag != null ? this.locusTag.hashCode() : 0);
        hash = 53 * hash + (this.translation != null ? this.translation.hashCode() : 0);
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
        final GeneBankCDS other = (GeneBankCDS) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.proteinGi != other.proteinGi) {
            return false;
        }
        if ((this.location == null) ? (other.location != null) : !this.location.equals(other.location)) {
            return false;
        }
        if ((this.product == null) ? (other.product != null) : !this.product.equals(other.product)) {
            return false;
        }
        if ((this.proteinId == null) ? (other.proteinId != null) : !this.proteinId.equals(other.proteinId)) {
            return false;
        }
        if ((this.gene == null) ? (other.gene != null) : !this.gene.equals(other.gene)) {
            return false;
        }
        if ((this.locusTag == null) ? (other.locusTag != null) : !this.locusTag.equals(other.locusTag)) {
            return false;
        }
        return this.translation == other.translation || (this.translation != null && this.translation.equals(other.translation));
    }
    
    @Override
    public String toString() {
        StringBuilder buider = new StringBuilder();
        
        if (geneBankCDSLocation != null && !geneBankCDSLocation.isEmpty()) {
            for (GeneBankCDSLocation f : geneBankCDSLocation) {
                buider.append("\t\t").append(f.toString()).append("\n");
            }
        }
        
        if (!geneBankCDSDBXrefs.isEmpty()) {
            buider.append("\n");
            for (GeneBankCDSDBXref f : geneBankCDSDBXrefs) {
                buider.append("\t\t").append(f.toString()).append("\n");
            }
        }
        
        if (geneBankCOG != null && !geneBankCOG.isEmpty()) {
            for (GeneBankCOG f : geneBankCOG) {
                buider.append("\t\t").append(f.toString()).append("\n");
            }
        }
        
        return "GeneBankCDS{" + "wid=" + wid
                + ", proteinGi=" + proteinGi
                + ", location=" + location
                + ", product=" + product
                + ", proteinId=" + proteinId
                + ", gene=" + gene
                + ", locus_tag=" + locusTag
                + ", translation=" + translation
                + ", geneBankWID=" + geneBankWID + "}"
                + buider.toString();
    }
}
