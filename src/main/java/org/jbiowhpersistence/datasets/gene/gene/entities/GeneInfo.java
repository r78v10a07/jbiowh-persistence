package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.disease.omim.entities.OMIM;
import org.jbiowhpersistence.datasets.gene.gene.GeneTables;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.gene.genome.entities.GeneRNT;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.protein.ProteinTables;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.cog.COGTables;
import org.jbiowhpersistence.datasets.protgroup.cog.entities.COGOrthologousGroup;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This Class is the GeneInfo entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100
 * (Tue, 19 Mar 2013) $ $LastChangedRevision: 664 $
 *
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "GeneInfo")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "GeneInfo.findAll", query = "SELECT g FROM GeneInfo g"),
    @NamedQuery(name = "GeneInfo.findByWid", query = "SELECT g FROM GeneInfo g WHERE g.wid = :wid"),
    @NamedQuery(name = "GeneInfo.findByGeneID", query = "SELECT g FROM GeneInfo g WHERE g.geneID = :geneID"),
    @NamedQuery(name = "GeneInfo.findByTaxID", query = "SELECT g FROM GeneInfo g WHERE g.taxID = :taxID"),
    @NamedQuery(name = "GeneInfo.findBySymbol", query = "SELECT g FROM GeneInfo g WHERE g.symbol LIKE :symbol"),
    @NamedQuery(name = "GeneInfo.findByLocusTag", query = "SELECT g FROM GeneInfo g WHERE g.locusTag LIKE :locusTag"),
    @NamedQuery(name = "GeneInfo.findByChromosome", query = "SELECT g FROM GeneInfo g WHERE g.chromosome = :chromosome"),
    @NamedQuery(name = "GeneInfo.findByMapLocation", query = "SELECT g FROM GeneInfo g WHERE g.mapLocation = :mapLocation"),
    @NamedQuery(name = "GeneInfo.findByDescription", query = "SELECT g FROM GeneInfo g WHERE g.description = :description"),
    @NamedQuery(name = "GeneInfo.findByTypeOfGene", query = "SELECT g FROM GeneInfo g WHERE g.typeOfGene = :typeOfGene"),
    @NamedQuery(name = "GeneInfo.findBySymbolFromNomenclature", query = "SELECT g FROM GeneInfo g WHERE g.symbolFromNomenclature = :symbolFromNomenclature"),
    @NamedQuery(name = "GeneInfo.findByFullNameFromNomenclatureAuthority", query = "SELECT g FROM GeneInfo g WHERE g.fullNameFromNomenclatureAuthority = :fullNameFromNomenclatureAuthority"),
    @NamedQuery(name = "GeneInfo.findByNomenclatureStatus", query = "SELECT g FROM GeneInfo g WHERE g.nomenclatureStatus = :nomenclatureStatus"),
    @NamedQuery(name = "GeneInfo.findByOtherDesignations", query = "SELECT g FROM GeneInfo g WHERE g.otherDesignations = :otherDesignations"),
    @NamedQuery(name = "GeneInfo.findByModificationDate", query = "SELECT g FROM GeneInfo g WHERE g.modificationDate = :modificationDate"),
    @NamedQuery(name = "GeneInfo.findByDataSetWID", query = "SELECT g FROM GeneInfo g WHERE g.dataSetWID = :dataSetWID"),
    @NamedQuery(name = "GeneInfo.findByProteinGi", query = "SELECT g FROM GeneInfo g INNER JOIN g.gene2ProteinAccession p WHERE p.proteinGi = :proteinGi"),
    @NamedQuery(name = "GeneInfo.findBySynonym", query = "SELECT g FROM GeneInfo g INNER JOIN g.geneInfoSynonym p WHERE p.synonyms like :synonyms"),
    @NamedQuery(name = "GeneInfo.findByDBXRef", query = "SELECT g FROM GeneInfo g INNER JOIN g.geneInfoDBXref p WHERE p.id like :id")})
public class GeneInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "GeneID")
    private long geneID;
    @Basic(optional = false)
    @Column(name = "TaxID")
    private long taxID;
    @Column(name = "Symbol")
    private String symbol;
    @Column(name = "LocusTag")
    private String locusTag;
    @Column(name = "Chromosome")
    private String chromosome;
    @Column(name = "MapLocation")
    private String mapLocation;
    @Column(name = "Description")
    private String description;
    @Column(name = "TypeOfGene")
    private String typeOfGene;
    @Column(name = "SymbolFromNomenclature")
    private String symbolFromNomenclature;
    @Column(name = "FullNameFromNomenclatureAuthority")
    private String fullNameFromNomenclatureAuthority;
    @Column(name = "NomenclatureStatus")
    private String nomenclatureStatus;
    @Column(name = "OtherDesignations")
    private String otherDesignations;
    @Column(name = "ModificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    // External Gene Relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSetWID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxID", referencedColumnName = "TaxId", insertable = false, unique = false, nullable = false, updatable = false)
    private Taxonomy taxonomy;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @XmlElement
    @XmlInverseReference(mappedBy = "geneInfo")
    private GenePTT genePTT;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @XmlElement
    @XmlInverseReference(mappedBy = "geneInfo")
    @XmlElementWrapper(name = "GeneRNTs")
    private Set<GeneRNT> geneRNT;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = GeneTables.GENEINFO_HAS_ONTOLOGY,
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Ontology_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "Ontologies")
    private Set<Ontology> ontology;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_GENEINFO,
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> protein;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = GeneTables.GENEINFO_HAS_KEGGGENE,
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "KEGGGene_WID", referencedColumnName = "WID"))
    private Set<KEGGGene> kEGGGenes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGPATHWAY_HAS_GENEINFO,
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"))
    private Set<KEGGPathway> kEGGPathways;
    @ManyToMany
    @JoinTable(name = GeneTables.GENEINFO_HAS_OMIM,
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "OMIM_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "OMIMs")
    private Set<OMIM> omim;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = COGTables.COGORTHOLOGOUSGROUP_HAS_GENEINFO,
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "COGOrthologousGroup_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "COGOrthologousGroups")
    private Set<COGOrthologousGroup> cogOrthologousGroup;
    // Internal Gene relationship        

    @ElementCollection
    @CollectionTable(
            name = "Gene2Ensembl",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "Gene2Ensembls")
    private Collection<Gene2Ensembl> gene2Ensembl;
    @ElementCollection
    @CollectionTable(
            name = "GeneInfoSynonyms",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "GeneInfoSynonyms")
    private Collection<GeneInfoSynonyms> geneInfoSynonym;
    @ElementCollection
    @CollectionTable(
            name = "GeneInfoDBXrefs",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "GeneInfoDBXrefs")
    private Collection<GeneInfoDBXrefs> geneInfoDBXref;
    @ElementCollection
    @CollectionTable(
            name = "Gene2PMID",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "Gene2PMIDs")
    private Collection<Gene2PMID> gene2PMID;
    @ElementCollection
    @CollectionTable(
            name = "Gene2STS",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "Gene2STSs")
    private Collection<Gene2STS> gene2STS;
    @ElementCollection
    @CollectionTable(
            name = "Gene2UniGene",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "Gene2UniGenes")
    private Collection<Gene2UniGene> gene2UniGene;
    @ElementCollection
    @CollectionTable(
            name = "GeneGroup",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "GeneGroups")
    private Collection<GeneGroup> geneGroup;
    @ElementCollection
    @CollectionTable(
            name = "Gene2ProteinAccession",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "Gene2ProteinAccessions")
    private Collection<Gene2ProteinAccession> gene2ProteinAccession;
    @ElementCollection
    @CollectionTable(
            name = "Gene2GenomicNucleotide",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "Gene2GenomicNucleotides")
    private Collection<Gene2GenomicNucleotide> gene2GenomicNucleotide;
    @ElementCollection
    @CollectionTable(
            name = "Gene2RNANucleotide",
            joinColumns
            = @JoinColumn(name = "GeneInfo_WID"))
    @XmlElementWrapper(name = "Gene2RNANucleotides")
    private Collection<Gene2RNANucleotide> gene2RNANucleotide;
    
    public GeneInfo() {
    }
    
    public GeneInfo(Long wid) {
        this.wid = wid;
    }
    
    public GeneInfo(Long wid, long geneID, long taxID, long dataSetWID) {
        this.wid = wid;
        this.geneID = geneID;
        this.taxID = taxID;
        this.dataSetWID = dataSetWID;
    }
    
    public void setRelationsToNull() {
        setProtein(null);
        setkEGGGenes(null);
        setkEGGPathways(null);
        setOmim(null);
        setCogOrthologousGroup(null);
    }
    
    @XmlTransient
    public Set<COGOrthologousGroup> getCogOrthologousGroup() {
        return cogOrthologousGroup;
    }
    
    public void setCogOrthologousGroup(Set<COGOrthologousGroup> cogOrthologousGroup) {
        this.cogOrthologousGroup = cogOrthologousGroup;
    }
    
    public Long getWid() {
        return wid;
    }
    
    public void setWid(Long wid) {
        this.wid = wid;
    }
    
    public long getGeneID() {
        return geneID;
    }
    
    public void setGeneID(long geneID) {
        this.geneID = geneID;
    }
    
    public long getTaxID() {
        return taxID;
    }
    
    public void setTaxID(long taxID) {
        this.taxID = taxID;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public String getLocusTag() {
        return locusTag;
    }
    
    public void setLocusTag(String locusTag) {
        this.locusTag = locusTag;
    }
    
    public String getChromosome() {
        return chromosome;
    }
    
    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
    
    public String getMapLocation() {
        return mapLocation;
    }
    
    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTypeOfGene() {
        return typeOfGene;
    }
    
    public void setTypeOfGene(String typeOfGene) {
        this.typeOfGene = typeOfGene;
    }
    
    public String getSymbolFromNomenclature() {
        return symbolFromNomenclature;
    }
    
    public void setSymbolFromNomenclature(String symbolFromNomenclature) {
        this.symbolFromNomenclature = symbolFromNomenclature;
    }
    
    public String getFullNameFromNomenclatureAuthority() {
        return fullNameFromNomenclatureAuthority;
    }
    
    public void setFullNameFromNomenclatureAuthority(String fullNameFromNomenclatureAuthority) {
        this.fullNameFromNomenclatureAuthority = fullNameFromNomenclatureAuthority;
    }
    
    public String getNomenclatureStatus() {
        return nomenclatureStatus;
    }
    
    public void setNomenclatureStatus(String nomenclatureStatus) {
        this.nomenclatureStatus = nomenclatureStatus;
    }
    
    public String getOtherDesignations() {
        return otherDesignations;
    }
    
    public void setOtherDesignations(String otherDesignations) {
        this.otherDesignations = otherDesignations;
    }
    
    public Date getModificationDate() {
        return modificationDate;
    }
    
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
    
    public long getDataSetWID() {
        return dataSetWID;
    }
    
    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }
    
    public DataSet getDataSet() {
        return dataSet;
    }
    
    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }
    
    public Taxonomy getTaxonomy() {
        return taxonomy;
    }
    
    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }
    
    public GenePTT getGenePTT() {
        return genePTT;
    }
    
    public void setGenePTT(GenePTT genePTT) {
        this.genePTT = genePTT;
    }
    
    public Set<GeneRNT> getGeneRNT() {
        return geneRNT;
    }
    
    public void setGeneRNT(Set<GeneRNT> geneRNT) {
        this.geneRNT = geneRNT;
    }
    
    public Set<Ontology> getOntology() {
        return ontology;
    }
    
    public void setOntology(Set<Ontology> ontology) {
        this.ontology = ontology;
    }
    
    @XmlTransient
    public Set<Protein> getProtein() {
        return protein;
    }
    
    public void setProtein(Set<Protein> protein) {
        this.protein = protein;
    }
    
    @XmlTransient
    public Set<KEGGGene> getkEGGGenes() {
        return kEGGGenes;
    }
    
    public void setkEGGGenes(Set<KEGGGene> kEGGGenes) {
        this.kEGGGenes = kEGGGenes;
    }
    
    @XmlTransient
    public Set<KEGGPathway> getkEGGPathways() {
        return kEGGPathways;
    }
    
    public void setkEGGPathways(Set<KEGGPathway> kEGGPathways) {
        this.kEGGPathways = kEGGPathways;
    }
    
    public Set<OMIM> getOmim() {
        return omim;
    }
    
    public void setOmim(Set<OMIM> omim) {
        this.omim = omim;
    }
    
    public Collection<Gene2Ensembl> getGene2Ensembl() {
        return gene2Ensembl;
    }
    
    public void setGene2Ensembl(Collection<Gene2Ensembl> gene2Ensembl) {
        this.gene2Ensembl = gene2Ensembl;
    }
    
    @XmlTransient
    public Collection<GeneInfoSynonyms> getGeneInfoSynonym() {
        return geneInfoSynonym;
    }
    
    public void setGeneInfoSynonym(Collection<GeneInfoSynonyms> geneInfoSynonym) {
        this.geneInfoSynonym = geneInfoSynonym;
    }
    
    public Collection<GeneInfoDBXrefs> getGeneInfoDBXref() {
        return geneInfoDBXref;
    }
    
    public void setGeneInfoDBXref(Collection<GeneInfoDBXrefs> geneInfoDBXref) {
        this.geneInfoDBXref = geneInfoDBXref;
    }
    
    public Collection<Gene2PMID> getGene2PMID() {
        return gene2PMID;
    }
    
    public void setGene2PMID(Collection<Gene2PMID> gene2PMID) {
        this.gene2PMID = gene2PMID;
    }
    
    public Collection<Gene2STS> getGene2STS() {
        return gene2STS;
    }
    
    public void setGene2STS(Collection<Gene2STS> gene2STS) {
        this.gene2STS = gene2STS;
    }
    
    public Collection<Gene2UniGene> getGene2UniGene() {
        return gene2UniGene;
    }
    
    public void setGene2UniGene(Collection<Gene2UniGene> gene2UniGene) {
        this.gene2UniGene = gene2UniGene;
    }
    
    public Collection<GeneGroup> getGeneGroup() {
        return geneGroup;
    }
    
    public void setGeneGroup(Collection<GeneGroup> geneGroup) {
        this.geneGroup = geneGroup;
    }
    
    public Collection<Gene2ProteinAccession> getGene2ProteinAccession() {
        return gene2ProteinAccession;
    }
    
    public void setGene2ProteinAccession(Collection<Gene2ProteinAccession> gene2ProteinAccession) {
        this.gene2ProteinAccession = gene2ProteinAccession;
    }
    
    public Collection<Gene2GenomicNucleotide> getGene2GenomicNucleotide() {
        return gene2GenomicNucleotide;
    }
    
    public void setGene2GenomicNucleotide(Collection<Gene2GenomicNucleotide> gene2GenomicNucleotide) {
        this.gene2GenomicNucleotide = gene2GenomicNucleotide;
    }
    
    public Collection<Gene2RNANucleotide> getGene2RNANucleotide() {
        return gene2RNANucleotide;
    }
    
    public void setGene2RNANucleotide(Collection<Gene2RNANucleotide> gene2RNANucleotide) {
        this.gene2RNANucleotide = gene2RNANucleotide;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 79 * hash + (int) (this.geneID ^ (this.geneID >>> 32));
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
        final GeneInfo other = (GeneInfo) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.geneID != other.geneID) {
            return false;
        }
        if (this.taxID != other.taxID) {
            return false;
        }
        if ((this.symbol == null) ? (other.symbol != null) : !this.symbol.equals(other.symbol)) {
            return false;
        }
        if ((this.locusTag == null) ? (other.locusTag != null) : !this.locusTag.equals(other.locusTag)) {
            return false;
        }
        if ((this.chromosome == null) ? (other.chromosome != null) : !this.chromosome.equals(other.chromosome)) {
            return false;
        }
        if ((this.mapLocation == null) ? (other.mapLocation != null) : !this.mapLocation.equals(other.mapLocation)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if ((this.typeOfGene == null) ? (other.typeOfGene != null) : !this.typeOfGene.equals(other.typeOfGene)) {
            return false;
        }
        if ((this.symbolFromNomenclature == null) ? (other.symbolFromNomenclature != null) : !this.symbolFromNomenclature.equals(other.symbolFromNomenclature)) {
            return false;
        }
        if ((this.fullNameFromNomenclatureAuthority == null) ? (other.fullNameFromNomenclatureAuthority != null) : !this.fullNameFromNomenclatureAuthority.equals(other.fullNameFromNomenclatureAuthority)) {
            return false;
        }
        if ((this.nomenclatureStatus == null) ? (other.nomenclatureStatus != null) : !this.nomenclatureStatus.equals(other.nomenclatureStatus)) {
            return false;
        }
        if ((this.otherDesignations == null) ? (other.otherDesignations != null) : !this.otherDesignations.equals(other.otherDesignations)) {
            return false;
        }
        if (this.modificationDate != other.modificationDate && (this.modificationDate == null || !this.modificationDate.equals(other.modificationDate))) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (this.gene2Ensembl != other.gene2Ensembl && (this.gene2Ensembl == null || !this.gene2Ensembl.equals(other.gene2Ensembl))) {
            return false;
        }
        if (this.geneInfoSynonym != other.geneInfoSynonym && (this.geneInfoSynonym == null || !this.geneInfoSynonym.equals(other.geneInfoSynonym))) {
            return false;
        }
        if (this.geneInfoDBXref != other.geneInfoDBXref && (this.geneInfoDBXref == null || !this.geneInfoDBXref.equals(other.geneInfoDBXref))) {
            return false;
        }
        if (this.gene2PMID != other.gene2PMID && (this.gene2PMID == null || !this.gene2PMID.equals(other.gene2PMID))) {
            return false;
        }
        if (this.gene2STS != other.gene2STS && (this.gene2STS == null || !this.gene2STS.equals(other.gene2STS))) {
            return false;
        }
        if (this.gene2UniGene != other.gene2UniGene && (this.gene2UniGene == null || !this.gene2UniGene.equals(other.gene2UniGene))) {
            return false;
        }
        if (this.geneGroup != other.geneGroup && (this.geneGroup == null || !this.geneGroup.equals(other.geneGroup))) {
            return false;
        }
        if (this.gene2ProteinAccession != other.gene2ProteinAccession && (this.gene2ProteinAccession == null || !this.gene2ProteinAccession.equals(other.gene2ProteinAccession))) {
            return false;
        }
        if (this.gene2GenomicNucleotide != other.gene2GenomicNucleotide && (this.gene2GenomicNucleotide == null || !this.gene2GenomicNucleotide.equals(other.gene2GenomicNucleotide))) {
            return false;
        }
        return this.gene2RNANucleotide == other.gene2RNANucleotide || (this.gene2RNANucleotide != null && this.gene2RNANucleotide.equals(other.gene2RNANucleotide));
    }
    
    @Override
    public String toString() {
        String tSynonym = null;
        StringBuilder toAdd = new StringBuilder();
        
        if (taxonomy != null) {
            tSynonym = taxonomy.getTaxonomySynonym();
        }
        if (getGeneInfoSynonym() != null && !getGeneInfoSynonym().isEmpty()) {
            for (GeneInfoSynonyms geneSynonyms : getGeneInfoSynonym()) {
                toAdd.append("\t").append(geneSynonyms).append("\n");
            }
        }
        if (getGeneInfoDBXref() != null && !getGeneInfoDBXref().isEmpty()) {
            for (GeneInfoDBXrefs geneDBXrefs : getGeneInfoDBXref()) {
                toAdd.append("\t").append(geneDBXrefs).append("\n");
            }
        }
        if (getGeneGroup() != null && !getGeneGroup().isEmpty()) {
            for (GeneGroup geGroup : getGeneGroup()) {
                toAdd.append("\t").append(geGroup).append("\n");
            }
        }
        if (getGene2Ensembl() != null && !getGene2Ensembl().isEmpty()) {
            for (Gene2Ensembl ge2Ensembl : getGene2Ensembl()) {
                toAdd.append("\t").append(ge2Ensembl).append("\n");
            }
        }
        if (getGene2PMID() != null && !getGene2PMID().isEmpty()) {
            for (Gene2PMID genePMID : getGene2PMID()) {
                toAdd.append("\t").append(genePMID).append("\n");
            }
        }
        if (getGene2STS() != null && !getGene2STS().isEmpty()) {
            for (Gene2STS geneSTS : getGene2STS()) {
                toAdd.append("\t").append(geneSTS).append("\n");
            }
        }
        if (getGene2UniGene() != null && !getGene2UniGene().isEmpty()) {
            for (Gene2UniGene geneUniGene : getGene2UniGene()) {
                toAdd.append("\t").append(geneUniGene).append("\n");
            }
        }
        if (gene2GenomicNucleotide != null && !gene2GenomicNucleotide.isEmpty()) {
            for (Gene2GenomicNucleotide g : gene2GenomicNucleotide) {
                toAdd.append("\t").append(g).append("\n");
            }
        }
        if (gene2ProteinAccession != null && !gene2ProteinAccession.isEmpty()) {
            for (Gene2ProteinAccession g : gene2ProteinAccession) {
                toAdd.append("\t").append(g).append("\n");
            }
        }
        if (gene2RNANucleotide != null && !gene2RNANucleotide.isEmpty()) {
            for (Gene2RNANucleotide g : gene2RNANucleotide) {
                toAdd.append("\t").append(g).append("\n");
            }
        }
        
        return "GeneInfo{"
                + " wid=" + wid
                + " taxId=" + taxID
                + " Organism=" + tSynonym
                + " geneID=" + geneID
                + " symbol=" + symbol
                + " locusTag=" + locusTag
                + " chromosome=" + chromosome
                + " mapLocation=" + mapLocation
                + " description=" + description
                + " typeOfGene=" + typeOfGene
                + " symbolFromNomenclature=" + symbolFromNomenclature
                + " fullNameFromNomenclatureAuthority=" + fullNameFromNomenclatureAuthority
                + " nomenclatureStatus=" + nomenclatureStatus
                + " otherDesignations=" + otherDesignations
                + " modificationDate=" + modificationDate
                + " dataSetWID=" + dataSetWID
                + "}\n"
                + toAdd;
    }
}
