package org.jbiowhpersistence.datasets.gene.genome.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.gene.GeneTables;
import org.jbiowhpersistence.datasets.gene.genome.GenePTTTables;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This Class is the GenePTT entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100
 * (Tue, 19 Mar 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jul 22, 2012
 */
@Entity
@Table(name = "GenePTT")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "GenePTT.findAll", query = "SELECT g FROM GenePTT g"),
    @NamedQuery(name = "GenePTT.findByPFrom", query = "SELECT g FROM GenePTT g WHERE g.pFrom = :pFrom"),
    @NamedQuery(name = "GenePTT.findByPTo", query = "SELECT g FROM GenePTT g WHERE g.pTo = :pTo"),
    @NamedQuery(name = "GenePTT.findByPFromPToPTTFile", query = "SELECT g FROM GenePTT g WHERE (g.pFrom >= :pFrom AND g.pTo <= :pTo) AND g.pTTFile = :pTTFile"),
    @NamedQuery(name = "GenePTT.findByLocation", query = "SELECT g FROM GenePTT g WHERE g.location = :location"),
    @NamedQuery(name = "GenePTT.findByStrand", query = "SELECT g FROM GenePTT g WHERE g.strand = :strand"),
    @NamedQuery(name = "GenePTT.findByPLength", query = "SELECT g FROM GenePTT g WHERE g.pLength = :pLength"),
    @NamedQuery(name = "GenePTT.findByProteinGi", query = "SELECT g FROM GenePTT g WHERE g.proteinGi = :proteinGi"),
    @NamedQuery(name = "GenePTT.findByGeneSymbol", query = "SELECT g FROM GenePTT g WHERE g.geneSymbol = :geneSymbol"),
    @NamedQuery(name = "GenePTT.findByGeneLocusTag", query = "SELECT g FROM GenePTT g WHERE g.geneLocusTag = :geneLocusTag"),
    @NamedQuery(name = "GenePTT.findByCode", query = "SELECT g FROM GenePTT g WHERE g.code = :code"),
    @NamedQuery(name = "GenePTT.findByCog", query = "SELECT g FROM GenePTT g WHERE g.cog = :cog"),
    @NamedQuery(name = "GenePTT.findByProduct", query = "SELECT g FROM GenePTT g WHERE g.product = :product"),
    @NamedQuery(name = "GenePTT.findByPTTFile", query = "SELECT g FROM GenePTT g WHERE g.pTTFile = :pTTFile"),
    @NamedQuery(name = "GenePTT.findByTaxonomy", query = "SELECT g FROM GenePTT g WHERE g.taxonomy = :taxonomy"),
    @NamedQuery(name = "GenePTT.findByTaxId", query = "SELECT g FROM GenePTT g WHERE g.taxonomy.taxId = :taxId"),
    @NamedQuery(name = "GenePTT.findPTTFileByTaxonomy", query = "SELECT g.pTTFile FROM GenePTT g WHERE g.taxonomy = :taxonomy GROUP BY g.pTTFile"),
    @NamedQuery(name = "GenePTT.findByDataSetWID", query = "SELECT g FROM GenePTT g WHERE g.dataSetWID = :dataSetWID")})
public class GenePTT implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "pFrom")
    private long pFrom;
    @Basic(optional = false)
    @Column(name = "pTo")
    private long pTo;
    @Basic(optional = false)
    @Column(name = "Location")
    private String location;
    @Basic(optional = false)
    @Column(name = "Strand")
    private String strand;
    @Basic(optional = false)
    @Column(name = "PLength")
    private int pLength;
    @Id
    @Basic(optional = false)
    @Column(name = "ProteinGi")
    private Long proteinGi;
    @Basic(optional = false)
    @Column(name = "GeneSymbol")
    private String geneSymbol;
    @Basic(optional = false)
    @Column(name = "GeneLocusTag")
    private String geneLocusTag;
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "COG")
    private String cog;
    @Basic(optional = false)
    @Column(name = "Product")
    private String product;
    @Basic(optional = false)
    @Column(name = "PTTFile")
    private String pTTFile;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    // External Gene Relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dataSetWID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @XmlTransient
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = GeneTables.GENEINFO_HAS_GENEPTT,
            joinColumns
            = @JoinColumn(name = "GenePTT_ProteinGi", referencedColumnName = "ProteinGi"),
            inverseJoinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    @XmlElement
    @XmlInverseReference(mappedBy = "genePTT")
    private GeneInfo geneInfo;
    @XmlTransient
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "genePTT")
    private Protein protein;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = GenePTTTables.GENEPTT_HAS_TAXONOMY,
            joinColumns
            = @JoinColumn(name = "GenePTT_ProteinGi", referencedColumnName = "ProteinGi"),
            inverseJoinColumns
            = @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"))
    private Taxonomy taxonomy;

    public GenePTT() {
    }

    public GenePTT(Long proteinGi) {
        this.proteinGi = proteinGi;
    }

    public GenePTT(Long proteinGi, long pFrom, long pTo, String location, String strand, int pLength, String geneSymbol, String geneLocusTag, String code, String cog, String product, String pTTFile, long dataSetWID) {
        this.proteinGi = proteinGi;
        this.pFrom = pFrom;
        this.pTo = pTo;
        this.location = location;
        this.strand = strand;
        this.pLength = pLength;
        this.geneSymbol = geneSymbol;
        this.geneLocusTag = geneLocusTag;
        this.code = code;
        this.cog = cog;
        this.product = product;
        this.pTTFile = pTTFile;
        this.dataSetWID = dataSetWID;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    @XmlTransient    
    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public long getPFrom() {
        return pFrom;
    }

    @XmlTransient    
    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }

    public void setPFrom(long pFrom) {
        this.pFrom = pFrom;
    }

    public long getPTo() {
        return pTo;
    }

    public void setPTo(long pTo) {
        this.pTo = pTo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public int getPLength() {
        return pLength;
    }

    public void setPLength(int pLength) {
        this.pLength = pLength;
    }

    public Long getProteinGi() {
        return proteinGi;
    }

    public void setProteinGi(Long proteinGi) {
        this.proteinGi = proteinGi;
    }

    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }

    public String getGeneLocusTag() {
        return geneLocusTag;
    }

    public void setGeneLocusTag(String geneLocusTag) {
        this.geneLocusTag = geneLocusTag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCog() {
        return cog;
    }

    public void setCog(String cog) {
        this.cog = cog;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPTTFile() {
        return pTTFile;
    }

    public void setPTTFile(String pTTFile) {
        this.pTTFile = pTTFile;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenePTT other = (GenePTT) obj;
        if (this.pFrom != other.pFrom) {
            return false;
        }
        if (this.pTo != other.pTo) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.strand, other.strand)) {
            return false;
        }
        if (this.pLength != other.pLength) {
            return false;
        }
        if (!Objects.equals(this.proteinGi, other.proteinGi)) {
            return false;
        }
        if (!Objects.equals(this.geneSymbol, other.geneSymbol)) {
            return false;
        }
        if (!Objects.equals(this.geneLocusTag, other.geneLocusTag)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.cog, other.cog)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.pTTFile, other.pTTFile)) {
            return false;
        }
        return this.dataSetWID == other.dataSetWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinGi != null ? proteinGi.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "GenePTT{" + "pFrom=" + pFrom + ", pTo=" + pTo + ", location=" + location + ", strand=" + strand + ", pLength=" + pLength + ", proteinGi=" + proteinGi + ", geneSymbol=" + geneSymbol + ", geneLocusTag=" + geneLocusTag + ", code=" + code + ", cog=" + cog + ", product=" + product + ", pTTFile=" + pTTFile + ", dataSetWID=" + dataSetWID + '}';
    }
}
