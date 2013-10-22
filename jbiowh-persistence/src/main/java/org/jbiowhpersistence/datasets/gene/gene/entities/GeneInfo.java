package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
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
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.datasets.taxonomy.entities.TaxonomySynonym;

/**
 * This Class is the GeneInfo entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100
 * (Tue, 19 Mar 2013) $ $LastChangedRevision: 664 $
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "GeneInfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneInfo.findAll", query = "SELECT g FROM GeneInfo g"),
    @NamedQuery(name = "GeneInfo.findByWid", query = "SELECT g FROM GeneInfo g WHERE g.wid = :wid"),
    @NamedQuery(name = "GeneInfo.findByGeneID", query = "SELECT g FROM GeneInfo g WHERE g.geneID = :geneID"),
    @NamedQuery(name = "GeneInfo.findByTaxID", query = "SELECT g FROM GeneInfo g WHERE g.taxID = :taxID"),
    @NamedQuery(name = "GeneInfo.findBySymbol", query = "SELECT g FROM GeneInfo g WHERE UPPER(g.symbol) LIKE :symbol"),
    @NamedQuery(name = "GeneInfo.findByLocusTag", query = "SELECT g FROM GeneInfo g WHERE UPPER(g.locusTag) LIKE :locusTag"),
    @NamedQuery(name = "GeneInfo.findByChromosome", query = "SELECT g FROM GeneInfo g WHERE g.chromosome = :chromosome"),
    @NamedQuery(name = "GeneInfo.findByMapLocation", query = "SELECT g FROM GeneInfo g WHERE g.mapLocation = :mapLocation"),
    @NamedQuery(name = "GeneInfo.findByDescription", query = "SELECT g FROM GeneInfo g WHERE g.description = :description"),
    @NamedQuery(name = "GeneInfo.findByTypeOfGene", query = "SELECT g FROM GeneInfo g WHERE g.typeOfGene = :typeOfGene"),
    @NamedQuery(name = "GeneInfo.findBySymbolFromNomenclature", query = "SELECT g FROM GeneInfo g WHERE g.symbolFromNomenclature = :symbolFromNomenclature"),
    @NamedQuery(name = "GeneInfo.findByFullNameFromNomenclatureAuthority", query = "SELECT g FROM GeneInfo g WHERE g.fullNameFromNomenclatureAuthority = :fullNameFromNomenclatureAuthority"),
    @NamedQuery(name = "GeneInfo.findByNomenclatureStatus", query = "SELECT g FROM GeneInfo g WHERE g.nomenclatureStatus = :nomenclatureStatus"),
    @NamedQuery(name = "GeneInfo.findByOtherDesignations", query = "SELECT g FROM GeneInfo g WHERE g.otherDesignations = :otherDesignations"),
    @NamedQuery(name = "GeneInfo.findByModificationDate", query = "SELECT g FROM GeneInfo g WHERE g.modificationDate = :modificationDate"),
    @NamedQuery(name = "GeneInfo.findByProteinGi", query = "SELECT g FROM GeneInfo g INNER JOIN g.gene2Accession a WHERE a.proteinGi = :proteinGi group by g"),
    @NamedQuery(name = "GeneInfo.findByProteinAccession", query = "SELECT g FROM GeneInfo g INNER JOIN g.gene2Accession a WHERE a.proteinAccession = :proteinAccession group by g"),
    @NamedQuery(name = "GeneInfo.findByEnsemblProteinIdentifier", query = "SELECT g FROM GeneInfo g INNER JOIN g.gene2Ensembl e WHERE e.ensemblProteinIdentifier = :ensemblProteinIdentifier group by g"),
    @NamedQuery(name = "GeneInfo.findByDataSetWID", query = "SELECT g FROM GeneInfo g WHERE g.dataSetWID = :dataSetWID")})
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = GeneTables.GENEINFO_HAS_GENEPTT,
            joinColumns =
            @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns =
            @JoinColumn(name = "GenePTT_ProteinGi", referencedColumnName = "ProteinGi"))
    private GenePTT genePTT;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    private Set<GeneRNT> geneRNT;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = GeneTables.GENEINFO_HAS_ONTOLOGY,
            joinColumns =
            @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns =
            @JoinColumn(name = "Ontology_WID", referencedColumnName = "WID"))
    private Set<Ontology> ontology;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_GENEINFO,
            joinColumns =
            @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns =
            @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> protein;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = GeneTables.GENEINFO_HAS_KEGGGENE,
            joinColumns =
            @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns =
            @JoinColumn(name = "KEGGGene_WID", referencedColumnName = "WID"))
    private Set<KEGGGene> kEGGGenes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGPATHWAY_HAS_GENEINFO,
            joinColumns =
            @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns =
            @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"))
    private Set<KEGGPathway> kEGGPathways;
    @ManyToMany
    @JoinTable(name = GeneTables.GENEINFO_HAS_OMIM,
            joinColumns =
            @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"),
            inverseJoinColumns =
            @JoinColumn(name = "OMIM_WID", referencedColumnName = "WID"))
    private Set<OMIM> omim;
    // Internal Gene relationship        
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @MapKey(name = "geneInfoSynonymsPK")
    private Map<GeneInfoSynonymsPK, GeneInfoSynonyms> geneInfoSynonyms;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @MapKey(name = "geneInfoDBXrefsPK")
    private Map<GeneInfoDBXrefsPK, GeneInfoDBXrefs> geneInfoDBXrefs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @MapKey(name = "geneGroupPK")
    private Map<GeneGroupPK, GeneGroup> geneGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @MapKey(name = "gene2AccessionPK")
    private Map<Gene2AccessionPK, Gene2Accession> gene2Accession;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @JoinColumn(name = "GeneInfo_WID")
    private Set<Gene2Ensembl> gene2Ensembl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @MapKey(name = "gene2PMIDPK")
    private Map<Gene2PMIDPK, Gene2PMID> gene2PMID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @MapKey(name = "gene2STSPK")
    private Map<Gene2STSPK, Gene2STS> gene2STS;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneInfo")
    @MapKey(name = "gene2UniGenePK")
    private Map<Gene2UniGenePK, Gene2UniGene> gene2UniGene;

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
    }

    public Set<GeneRNT> getGeneRNT() {
        return geneRNT;
    }

    public void setGeneRNT(Set<GeneRNT> geneRNT) {
        this.geneRNT = geneRNT;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Set<Ontology> getOntology() {
        return ontology;
    }

    public void setOntology(Set<Ontology> ontology) {
        this.ontology = ontology;
    }

    public GenePTT getGenePTT() {
        return genePTT;
    }

    public void setGenePTT(GenePTT genePTT) {
        this.genePTT = genePTT;
    }

    public Set<OMIM> getOmim() {
        return omim;
    }

    public void setOmim(Set<OMIM> omim) {
        this.omim = omim;
    }

    public Set<KEGGPathway> getkEGGPathways() {
        return kEGGPathways;
    }

    public void setkEGGPathways(Set<KEGGPathway> kEGGPathways) {
        this.kEGGPathways = kEGGPathways;
    }

    public Set<KEGGGene> getkEGGGenes() {
        return kEGGGenes;
    }

    public void setkEGGGenes(Set<KEGGGene> kEGGGenes) {
        this.kEGGGenes =
                kEGGGenes;
    }

    public Set<Protein> getProtein() {
        return protein;
    }

    public void setProtein(Set<Protein> protein) {
        this.protein = protein;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullNameFromNomenclatureAuthority() {
        return fullNameFromNomenclatureAuthority;
    }

    public void setFullNameFromNomenclatureAuthority(String fullNameFromNomenclatureAuthority) {
        this.fullNameFromNomenclatureAuthority = fullNameFromNomenclatureAuthority;
    }

    public Map<Gene2AccessionPK, Gene2Accession> getGene2Accession() {
        return gene2Accession;
    }

    public void setGene2Accession(Map<Gene2AccessionPK, Gene2Accession> gene2Accession) {
        this.gene2Accession = gene2Accession;
    }

    public Set<Gene2Ensembl> getGene2Ensembl() {
        return gene2Ensembl;
    }

    public void setGene2Ensembl(Set<Gene2Ensembl> gene2Ensembl) {
        this.gene2Ensembl = gene2Ensembl;
    }

    public Map<Gene2PMIDPK, Gene2PMID> getGene2PMID() {
        return gene2PMID;
    }

    public void setGene2PMID(Map<Gene2PMIDPK, Gene2PMID> gene2PMID) {
        this.gene2PMID = gene2PMID;
    }

    public Map<Gene2STSPK, Gene2STS> getGene2STS() {
        return gene2STS;
    }

    public void setGene2STS(Map<Gene2STSPK, Gene2STS> gene2STS) {
        this.gene2STS = gene2STS;
    }

    public Map<Gene2UniGenePK, Gene2UniGene> getGene2UniGene() {
        return gene2UniGene;
    }

    public void setGene2UniGene(Map<Gene2UniGenePK, Gene2UniGene> gene2UniGene) {
        this.gene2UniGene = gene2UniGene;
    }

    public Map<GeneGroupPK, GeneGroup> getGeneGroup() {
        return geneGroup;
    }

    public void setGeneGroup(Map<GeneGroupPK, GeneGroup> geneGroup) {
        this.geneGroup = geneGroup;
    }

    public long getGeneID() {
        return geneID;
    }

    public void setGeneID(long geneID) {
        this.geneID = geneID;
    }

    public Map<GeneInfoDBXrefsPK, GeneInfoDBXrefs> getGeneInfoDBXrefs() {
        return geneInfoDBXrefs;
    }

    public void setGeneInfoDBXrefs(Map<GeneInfoDBXrefsPK, GeneInfoDBXrefs> geneInfoDBXrefs) {
        this.geneInfoDBXrefs = geneInfoDBXrefs;
    }

    public Map<GeneInfoSynonymsPK, GeneInfoSynonyms> getGeneInfoSynonyms() {
        return geneInfoSynonyms;
    }

    public void setGeneInfoSynonyms(Map<GeneInfoSynonymsPK, GeneInfoSynonyms> geneInfoSynonyms) {
        this.geneInfoSynonyms = geneInfoSynonyms;
    }

    public String getLocusTag() {
        return locusTag;
    }

    public void setLocusTag(String locusTag) {
        this.locusTag = locusTag;
    }

    public String getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbolFromNomenclature() {
        return symbolFromNomenclature;
    }

    public void setSymbolFromNomenclature(String symbolFromNomenclature) {
        this.symbolFromNomenclature = symbolFromNomenclature;
    }

    public long getTaxID() {
        return taxID;
    }

    public void setTaxID(long taxID) {
        this.taxID = taxID;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getTypeOfGene() {
        return typeOfGene;
    }

    public void setTypeOfGene(String typeOfGene) {
        this.typeOfGene = typeOfGene;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
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
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.geneID != other.geneID) {
            return false;
        }
        if (this.taxID != other.taxID) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.locusTag, other.locusTag)) {
            return false;
        }
        if (!Objects.equals(this.chromosome, other.chromosome)) {
            return false;
        }
        if (!Objects.equals(this.mapLocation, other.mapLocation)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.typeOfGene, other.typeOfGene)) {
            return false;
        }
        if (!Objects.equals(this.symbolFromNomenclature, other.symbolFromNomenclature)) {
            return false;
        }
        if (!Objects.equals(this.fullNameFromNomenclatureAuthority, other.fullNameFromNomenclatureAuthority)) {
            return false;
        }
        if (!Objects.equals(this.nomenclatureStatus, other.nomenclatureStatus)) {
            return false;
        }
        if (!Objects.equals(this.otherDesignations, other.otherDesignations)) {
            return false;
        }
        if (!Objects.equals(this.modificationDate, other.modificationDate)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (!Objects.equals(this.geneInfoSynonyms, other.geneInfoSynonyms)) {
            return false;
        }
        if (!Objects.equals(this.geneInfoDBXrefs, other.geneInfoDBXrefs)) {
            return false;
        }
        if (!Objects.equals(this.geneGroup, other.geneGroup)) {
            return false;
        }
        if (!Objects.equals(this.gene2Accession, other.gene2Accession)) {
            return false;
        }
        if (!Objects.equals(this.gene2Ensembl, other.gene2Ensembl)) {
            return false;
        }
        if (!Objects.equals(this.gene2PMID, other.gene2PMID)) {
            return false;
        }
        if (!Objects.equals(this.gene2STS, other.gene2STS)) {
            return false;
        }
        if (!Objects.equals(this.gene2UniGene, other.gene2UniGene)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        String tSynonym = null;
        StringBuilder ontologies = new StringBuilder();
        StringBuilder gSynonym = new StringBuilder();
        StringBuilder gXref = new StringBuilder();
        StringBuilder gGroup = new StringBuilder();
        StringBuilder gAccession = new StringBuilder();
        StringBuilder gEnsembl = new StringBuilder();
        StringBuilder g2Mim = new StringBuilder();
        StringBuilder g2PMID = new StringBuilder();
        StringBuilder g2STS = new StringBuilder();
        StringBuilder g2UniGene = new StringBuilder();

        if (taxonomy.getSynonym() != null) {
            for (TaxonomySynonym taxonomyWIDSynonym : taxonomy.getSynonym().values()) {
                if (taxonomyWIDSynonym.getNameClass().getNameClass().equals("scientific name")) {
                    tSynonym = taxonomyWIDSynonym.getTaxonomySynonymPK().getSynonym();
                }
            }
        }
        if (!getGeneInfoSynonyms().isEmpty()) {
            for (GeneInfoSynonyms geneSynonyms : getGeneInfoSynonyms().values()) {
                gSynonym.append("\t").append(geneSynonyms).append("\n");
            }
        }
        if (!getGeneInfoDBXrefs().isEmpty()) {
            for (GeneInfoDBXrefs geneDBXrefs : getGeneInfoDBXrefs().values()) {
                gXref.append("\t").append(geneDBXrefs).append("\n");
            }
        }
        if (!getGeneGroup().isEmpty()) {
            for (GeneGroup geGroup : getGeneGroup().values()) {
                gGroup.append("\t").append(geGroup).append("\n");
            }
        }
        if (!getGene2Accession().isEmpty()) {
            for (Gene2Accession ge2Accession : getGene2Accession().values()) {
                gAccession.append("\t").append(ge2Accession).append("\n");
            }
        }
        if (!getGene2Ensembl().isEmpty()) {
            for (Gene2Ensembl ge2Ensembl : getGene2Ensembl()) {
                gEnsembl.append("\t").append(ge2Ensembl).append("\n");
            }
        }
        if (!getGene2PMID().isEmpty()) {
            for (Gene2PMID genePMID : getGene2PMID().values()) {
                g2PMID.append("\t").append(genePMID).append("\n");
            }
        }
        if (!getGene2STS().isEmpty()) {
            for (Gene2STS geneSTS : getGene2STS().values()) {
                g2STS.append("\t").append(geneSTS).append("\n");
            }
        }
        if (!getGene2UniGene().isEmpty()) {
            for (Gene2UniGene geneUniGene : getGene2UniGene().values()) {
                g2UniGene.append("\t").append(geneUniGene).append("\n");
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
                + ontologies
                + gSynonym
                + gXref
                + gGroup
                + gAccession
                + gEnsembl
                + g2Mim
                + g2PMID
                + g2STS
                + g2UniGene;
    }
}
