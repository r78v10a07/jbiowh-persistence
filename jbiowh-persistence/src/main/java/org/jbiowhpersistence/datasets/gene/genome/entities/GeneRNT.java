package org.jbiowhpersistence.datasets.gene.genome.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.GeneTables;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genome.GenePTTTables;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This class is the GeneRNT entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 24, 2013
 */
@Entity
@Table(name = "GeneRNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneRNT.findAll", query = "SELECT g FROM GeneRNT g"),
    @NamedQuery(name = "GeneRNT.findByWid", query = "SELECT g FROM GeneRNT g WHERE g.wid = :wid"),
    @NamedQuery(name = "GeneRNT.findByPFrom", query = "SELECT g FROM GeneRNT g WHERE g.pFrom = :pFrom"),
    @NamedQuery(name = "GeneRNT.findByPTo", query = "SELECT g FROM GeneRNT g WHERE g.pTo = :pTo"),
    @NamedQuery(name = "GeneRNT.findByLocation", query = "SELECT g FROM GeneRNT g WHERE g.location = :location"),
    @NamedQuery(name = "GeneRNT.findByStrand", query = "SELECT g FROM GeneRNT g WHERE g.strand = :strand"),
    @NamedQuery(name = "GeneRNT.findByPLength", query = "SELECT g FROM GeneRNT g WHERE g.pLength = :pLength"),
    @NamedQuery(name = "GeneRNT.findByGenomicNucleotideGi", query = "SELECT g FROM GeneRNT g WHERE g.genomicNucleotideGi = :genomicNucleotideGi"),
    @NamedQuery(name = "GeneRNT.findByGeneSymbol", query = "SELECT g FROM GeneRNT g WHERE g.geneSymbol = :geneSymbol"),
    @NamedQuery(name = "GeneRNT.findByGeneLocusTag", query = "SELECT g FROM GeneRNT g WHERE g.geneLocusTag = :geneLocusTag"),
    @NamedQuery(name = "GeneRNT.findByCode", query = "SELECT g FROM GeneRNT g WHERE g.code = :code"),
    @NamedQuery(name = "GeneRNT.findByCog", query = "SELECT g FROM GeneRNT g WHERE g.cog = :cog"),
    @NamedQuery(name = "GeneRNT.findByProduct", query = "SELECT g FROM GeneRNT g WHERE g.product = :product"),
    @NamedQuery(name = "GeneRNT.findByPTTFile", query = "SELECT g FROM GeneRNT g WHERE g.pTTFile = :pTTFile"),
    @NamedQuery(name = "GeneRNT.findByDataSetWID", query = "SELECT g FROM GeneRNT g WHERE g.dataSetWID = :dataSetWID")})
public class GeneRNT implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
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
    @Basic(optional = false)
    @Column(name = "GenomicNucleotideGi")
    private long genomicNucleotideGi;
    @Column(name = "GeneSymbol")
    private String geneSymbol;
    @Column(name = "GeneLocusTag")
    private String geneLocusTag;
    @Column(name = "Code")
    private String code;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = GeneTables.GENEINFO_HAS_GENERNT,
            joinColumns =
            @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns =
            @JoinColumn(name = "GeneRNT_WID", referencedColumnName = "WID"))
    private GeneInfo geneInfo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = GenePTTTables.GENERNT_HAS_TAXONOMY,
            joinColumns =
            @JoinColumn(name = "GeneRNT_WID", referencedColumnName = "WID"),
            inverseJoinColumns =
            @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"))
    private Taxonomy taxonomy;

    public GeneRNT() {
    }

    public GeneRNT(Long wid) {
        this.wid = wid;
    }

    public GeneRNT(Long wid, long pFrom, long pTo, String location, String strand, int pLength, long genomicNucleotideGi, String product, String pTTFile, long dataSetWID) {
        this.wid = wid;
        this.pFrom = pFrom;
        this.pTo = pTo;
        this.location = location;
        this.strand = strand;
        this.pLength = pLength;
        this.genomicNucleotideGi = genomicNucleotideGi;
        this.product = product;
        this.pTTFile = pTTFile;
        this.dataSetWID = dataSetWID;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getPFrom() {
        return pFrom;
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

    public long getGenomicNucleotideGi() {
        return genomicNucleotideGi;
    }

    public void setGenomicNucleotideGi(long genomicNucleotideGi) {
        this.genomicNucleotideGi = genomicNucleotideGi;
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
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneRNT)) {
            return false;
        }
        GeneRNT other = (GeneRNT) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneRNT{" + "wid=" + wid + ", pFrom=" + pFrom + ", pTo=" + pTo + ", location=" + location + ", strand=" + strand + ", pLength=" + pLength + ", genomicNucleotideGi=" + genomicNucleotideGi + ", geneSymbol=" + geneSymbol + ", geneLocusTag=" + geneLocusTag + ", code=" + code + ", cog=" + cog + ", product=" + product + ", pTTFile=" + pTTFile + ", dataSetWID=" + dataSetWID + '}';
    }
}
