package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.domain.pfam.PFamTables;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.ppi.MIF25Tables;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.protclust.UniRefTables;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMember;
import org.jbiowhpersistence.datasets.protein.ProteinTables;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This Class is the Protein Entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-09-06 14:21:09 +0200
 * (Fri, 06 Sep 2013) $ $LastChangedRevision: 664 $
 *
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "Protein")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Protein.findAll", query = "SELECT p FROM Protein p"),
    @NamedQuery(name = "Protein.findByWid", query = "SELECT p FROM Protein p WHERE p.wid = :wid"),
    @NamedQuery(name = "Protein.findByVersion", query = "SELECT p FROM Protein p WHERE p.version = :version"),
    @NamedQuery(name = "Protein.findByModified", query = "SELECT p FROM Protein p WHERE p.modified = :modified"),
    @NamedQuery(name = "Protein.findByCreated", query = "SELECT p FROM Protein p WHERE p.created = :created"),
    @NamedQuery(name = "Protein.findByDataSet", query = "SELECT p FROM Protein p WHERE UPPER(p.dataSetIn) = :dataSetIn"),
    @NamedQuery(name = "Protein.findByExistence", query = "SELECT p FROM Protein p WHERE p.existence = :existence"),
    @NamedQuery(name = "Protein.findBySeqLength", query = "SELECT p FROM Protein p WHERE p.seqLength = :seqLength"),
    @NamedQuery(name = "Protein.findByMass", query = "SELECT p FROM Protein p WHERE p.mass = :mass"),
    @NamedQuery(name = "Protein.findByChecksum", query = "SELECT p FROM Protein p WHERE p.checksum = :checksum"),
    @NamedQuery(name = "Protein.findBySeqModified", query = "SELECT p FROM Protein p WHERE p.seqModified = :seqModified"),
    @NamedQuery(name = "Protein.findBySeqVersion", query = "SELECT p FROM Protein p WHERE p.seqVersion = :seqVersion"),
    @NamedQuery(name = "Protein.findByPrecursor", query = "SELECT p FROM Protein p WHERE p.precursor = :precursor"),
    @NamedQuery(name = "Protein.findByFragment", query = "SELECT p FROM Protein p WHERE p.fragment = :fragment"),
    @NamedQuery(name = "Protein.findByDataSetWID", query = "SELECT p FROM Protein p WHERE p.dataSetWID = :dataSetWID"),
    @NamedQuery(name = "Protein.findProteinByName", query = "SELECT p FROM Protein p INNER JOIN p.proteinName n WHERE n.name LIKE :name GROUP BY p"),
    @NamedQuery(name = "Protein.findProteinWIDByLongName", query = "SELECT p.wid FROM Protein p INNER JOIN p.proteinLongName n WHERE n.name LIKE :name GROUP BY p.wid"),
    @NamedQuery(name = "Protein.findProteinWIDByAccessionNumber", query = "SELECT p FROM Protein p INNER JOIN p.proteinAccessionNumber n WHERE n.accessionNumber LIKE :accessionNumber group by p"),
    @NamedQuery(name = "Protein.findProteinByAccessionNumber", query = "SELECT p FROM Protein p INNER JOIN p.proteinAccessionNumber n WHERE n.accessionNumber LIKE :accessionNumber group by p"),
    @NamedQuery(name = "Protein.findProteinWIDByEC", query = "SELECT p.wid FROM Protein p INNER JOIN p.proteinEC n WHERE n.id LIKE :id GROUP BY p.wid"),
    @NamedQuery(name = "Protein.findEnsemblId", query = "SELECT p FROM Protein p INNER JOIN p.proteinDBReference n WHERE n.id LIKE :id AND n.type = 'Ensembl' GROUP BY p"),
    @NamedQuery(name = "Protein.findIPIId", query = "SELECT p FROM Protein p INNER JOIN p.proteinDBReference n WHERE n.id LIKE :id AND n.type = 'IPI' GROUP BY p"),
    @NamedQuery(name = "Protein.findByTaxId", query = "SELECT p FROM Protein p INNER JOIN p.taxonomy n WHERE n.taxId = :taxId GROUP BY p")
})
public class Protein implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Version")
    private Integer version;
    @Column(name = "Modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @Column(name = "Created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "DataSet")
    private String dataSetIn;
    @Column(name = "Existence")
    private String existence;
    @Column(name = "SeqLength")
    private Integer seqLength;
    @Column(name = "Mass")
    private Integer mass;
    @Column(name = "Checksum")
    private String checksum;
    @Column(name = "SeqModified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date seqModified;
    @Column(name = "SeqVersion")
    private Integer seqVersion;
    @Column(name = "Precursor")
    private String precursor;
    @Column(name = "Fragment")
    private String fragment;
    @Basic(optional = false)
    @Lob
    @Column(name = "Seq")
    private String seq;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    // Internal Protein relationship
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_PROTEINKEYWORD,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "ProteinKeyword_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="ProteinKeywords" )
    private Set<ProteinKeyword> proteinKeyword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @XmlElement
    @XmlInverseReference(mappedBy="protein")
    @XmlElementWrapper( name="ProteinFeatures" )
    private Set<ProteinFeature> proteinFeature;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @XmlElement
    @XmlInverseReference(mappedBy="protein")
    @XmlElementWrapper( name="ProteinComments" )
    private Set<ProteinComment> proteinComment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @XmlElement
    @XmlInverseReference(mappedBy="protein")
    @XmlElementWrapper( name="ProteinDBReferences" )
    private Set<ProteinDBReference> proteinDBReference;
    @ElementCollection
    @CollectionTable(
            name = "ProteinName",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinNames" )
    private Collection<ProteinName> proteinName;
    @ElementCollection
    @CollectionTable(
            name = "ProteinAccessionNumber",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinAccessionNumbers" )
    private Collection<ProteinAccessionNumber> proteinAccessionNumber;
    @ElementCollection
    @CollectionTable(
            name = "ProteinLongName",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinLongNames" )
    private Collection<ProteinLongName> proteinLongName;
    @ElementCollection
    @CollectionTable(
            name = "ProteinPMID",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinPMIDs" )
    private Collection<ProteinPMID> proteinPMID;
    @ElementCollection
    @CollectionTable(
            name = "ProteinEC",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinECs" )
    private Collection<ProteinEC> proteinEC;
    @ElementCollection
    @CollectionTable(
            name = "ProteinKEGG",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinKEGGs" )
    private Collection<ProteinKEGG> proteinKEGG;
    @ElementCollection
    @CollectionTable(
            name = "ProteinBioCyc",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinBioCycs" )
    private Collection<ProteinBioCyc> proteinBioCyc;
    @ElementCollection
    @CollectionTable(
            name = "ProteinPDB",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinPDBs" )
    private Collection<ProteinPDB> proteinPDB;
    @ElementCollection
    @CollectionTable(
            name = "ProteinIntAct",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinIntActs" )
    private Collection<ProteinIntAct> proteinIntAct;
    @ElementCollection
    @CollectionTable(
            name = "ProteinDIP",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinDIPs" )
    private Collection<ProteinDIP> proteinDIP;
    @ElementCollection
    @CollectionTable(
            name = "ProteinMINT",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinMINTs" )
    private Collection<ProteinMINT> proteinMINT;
    @ElementCollection
    @CollectionTable(
            name = "ProteinDrugBank",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinDrugBanks" )
    private Collection<ProteinDrugBank> proteinDrugBank;
    @ElementCollection
    @CollectionTable(
            name = "ProteinPFAM",
            joinColumns
            = @JoinColumn(name = "Protein_WID"))
    @XmlElementWrapper( name="ProteinPFAMs" )
    private Collection<ProteinPFAM> proteinPFAM;

    // External Protein relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSetWID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_TAXONOMY,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"))
    private Taxonomy taxonomy;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_TAXONOMYHOST,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="TaxonomyHosts" )
    private Set<Taxonomy> taxonomyHost;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_ONTOLOGY,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Ontology_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="Ontologies" )
    private Set<Ontology> ontology;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_GENEINFO,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    private Collection<GeneInfo> geneInfo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_GENEPTT,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "GenePTT_ProteinGi", referencedColumnName = "ProteinGi"))
    private GenePTT genePTT;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = MIF25Tables.MIFINTERACTION_HAS_PROTEIN,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "MIFEntryInteraction_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="MIFEntryInteractions" )
    private Set<MIFEntryInteraction> mIFEntryInteraction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    private Set<UniRefMember> uniRefMember;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = UniRefTables.UNIREFENTRY_HAS_PROTEIN,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "UniRefEntry_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="UniRefEntrys" )
    private Set<UniRefEntry> uniRefEntry;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANK,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="DrugBanks" )
    private Set<DrugBank> drugBank;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASENZYME,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="DrugBankAsEnzymes" )
    private Set<DrugBank> drugBankAsEnzyme;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASTRANSPORTERS,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="DrugBankAsTransporters" )
    private Set<DrugBank> drugBankAsTransporters;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASCARRIERS,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="DrugBankAsCarriers" )
    private Set<DrugBank> drugBankAsCarriers;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_KEGGENZYME,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="KEGGEnzymes" )
    private Set<KEGGEnzyme> kEGGEnzymes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGPATHWAY_HAS_PROTEN,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="KEGGPathways" )
    private Set<KEGGPathway> kEGGPathways;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMAREGFULLINSIGNIFICANT,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="PfamAInsignificants" )
    private Set<PfamAbioWH> pfamAInsignificant;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMAREGFULLSIGNIFICANT,
            joinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"))
    @XmlElementWrapper( name="PfamASignificants" )
    private Set<PfamAbioWH> pfamASignificant;

    public Protein() {
    }

    public Protein(Long wid) {
        this.wid = wid;
    }

    public Protein(Long wid, String seq, long dataSetWID) {
        this.wid = wid;
        this.seq = seq;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        setDrugBank(null);
        setDrugBankAsCarriers(null);
        setDrugBankAsEnzyme(null);
        setDrugBankAsTransporters(null);
        setGeneInfo(null);
        setGenePTT(null);
        setkEGGEnzymes(null);
        setkEGGPathways(null);
        setmIFEntryInteraction(null);
        setUniRefEntry(null);
        setUniRefMember(null);
        setPfamAInsignificant(null);
        setPfamASignificant(null);
    }

    public String getFastaFormat() {
        StringBuilder seqShort = new StringBuilder();
        for (int i = 0; i < seq.length(); i += 80) {
            if (seq.length() < i + 80) {
                seqShort.append(seq.substring(i, seq.length())).append("\n");
            } else {
                seqShort.append(seq.substring(i, i + 80)).append("\n");
            }
        }
        return ">"
                + getProteinNameDirected()
                + " "
                + getProteinLongNameDirected()
                + "\n"
                + seqShort.toString();

    }

    public String getProteinFirstAccessionNumber() {
        if (!proteinAccessionNumber.isEmpty()) {
            return proteinAccessionNumber.iterator().next().getAccessionNumber();
        }
        return null;
    }

    public String getProteinNameDirected() {
        if (!proteinName.isEmpty()) {
            return proteinName.iterator().next().getName();
        }
        return null;
    }

    public String getProteinLongNameDirected() {
        if (!proteinLongName.isEmpty()) {
            return proteinLongName.iterator().next().getName();
        }
        return null;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDataSetIn() {
        return dataSetIn;
    }

    public void setDataSetIn(String dataSetIn) {
        this.dataSetIn = dataSetIn;
    }

    public String getExistence() {
        return existence;
    }

    public void setExistence(String existence) {
        this.existence = existence;
    }

    public Integer getSeqLength() {
        return seqLength;
    }

    public void setSeqLength(Integer seqLength) {
        this.seqLength = seqLength;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Date getSeqModified() {
        return seqModified;
    }

    public void setSeqModified(Date seqModified) {
        this.seqModified = seqModified;
    }

    public Integer getSeqVersion() {
        return seqVersion;
    }

    public void setSeqVersion(Integer seqVersion) {
        this.seqVersion = seqVersion;
    }

    public String getPrecursor() {
        return precursor;
    }

    public void setPrecursor(String precursor) {
        this.precursor = precursor;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public Set<ProteinKeyword> getProteinKeyword() {
        return proteinKeyword;
    }

    public void setProteinKeyword(Set<ProteinKeyword> proteinKeyword) {
        this.proteinKeyword = proteinKeyword;
    }

    public Set<ProteinFeature> getProteinFeature() {
        return proteinFeature;
    }

    public void setProteinFeature(Set<ProteinFeature> proteinFeature) {
        this.proteinFeature = proteinFeature;
    }

    public Set<ProteinComment> getProteinComment() {
        return proteinComment;
    }

    public void setProteinComment(Set<ProteinComment> proteinComment) {
        this.proteinComment = proteinComment;
    }

    public Set<ProteinDBReference> getProteinDBReference() {
        return proteinDBReference;
    }

    public void setProteinDBReference(Set<ProteinDBReference> proteinDBReference) {
        this.proteinDBReference = proteinDBReference;
    }

    public Collection<ProteinName> getProteinName() {
        return proteinName;
    }

    public void setProteinName(Collection<ProteinName> proteinName) {
        this.proteinName = proteinName;
    }

    public Collection<ProteinAccessionNumber> getProteinAccessionNumber() {
        return proteinAccessionNumber;
    }

    public void setProteinAccessionNumber(Collection<ProteinAccessionNumber> proteinAccessionNumber) {
        this.proteinAccessionNumber = proteinAccessionNumber;
    }

    public Collection<ProteinLongName> getProteinLongName() {
        return proteinLongName;
    }

    public void setProteinLongName(Collection<ProteinLongName> proteinLongName) {
        this.proteinLongName = proteinLongName;
    }

    public Collection<ProteinPMID> getProteinPMID() {
        return proteinPMID;
    }

    public void setProteinPMID(Collection<ProteinPMID> proteinPMID) {
        this.proteinPMID = proteinPMID;
    }

    public Collection<ProteinEC> getProteinEC() {
        return proteinEC;
    }

    public void setProteinEC(Collection<ProteinEC> proteinEC) {
        this.proteinEC = proteinEC;
    }

    public Collection<ProteinKEGG> getProteinKEGG() {
        return proteinKEGG;
    }

    public void setProteinKEGG(Collection<ProteinKEGG> proteinKEGG) {
        this.proteinKEGG = proteinKEGG;
    }

    public Collection<ProteinBioCyc> getProteinBioCyc() {
        return proteinBioCyc;
    }

    public void setProteinBioCyc(Collection<ProteinBioCyc> proteinBioCyc) {
        this.proteinBioCyc = proteinBioCyc;
    }

    public Collection<ProteinPDB> getProteinPDB() {
        return proteinPDB;
    }

    public void setProteinPDB(Collection<ProteinPDB> proteinPDB) {
        this.proteinPDB = proteinPDB;
    }

    public Collection<ProteinIntAct> getProteinIntAct() {
        return proteinIntAct;
    }

    public void setProteinIntAct(Collection<ProteinIntAct> proteinIntAct) {
        this.proteinIntAct = proteinIntAct;
    }

    public Collection<ProteinDIP> getProteinDIP() {
        return proteinDIP;
    }

    public void setProteinDIP(Collection<ProteinDIP> proteinDIP) {
        this.proteinDIP = proteinDIP;
    }

    public Collection<ProteinMINT> getProteinMINT() {
        return proteinMINT;
    }

    public void setProteinMINT(Collection<ProteinMINT> proteinMINT) {
        this.proteinMINT = proteinMINT;
    }

    public Collection<ProteinDrugBank> getProteinDrugBank() {
        return proteinDrugBank;
    }

    public void setProteinDrugBank(Collection<ProteinDrugBank> proteinDrugBank) {
        this.proteinDrugBank = proteinDrugBank;
    }

    public Collection<ProteinPFAM> getProteinPFAM() {
        return proteinPFAM;
    }

    public void setProteinPFAM(Collection<ProteinPFAM> proteinPFAM) {
        this.proteinPFAM = proteinPFAM;
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

    public Set<Taxonomy> getTaxonomyHost() {
        return taxonomyHost;
    }

    public void setTaxonomyHost(Set<Taxonomy> taxonomyHost) {
        this.taxonomyHost = taxonomyHost;
    }

    public Set<Ontology> getOntology() {
        return ontology;
    }

    public void setOntology(Set<Ontology> ontology) {
        this.ontology = ontology;
    }

    @XmlTransient
    public Collection<GeneInfo> getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(Collection<GeneInfo> geneInfo) {
        this.geneInfo = geneInfo;
    }

    @XmlTransient
    public GenePTT getGenePTT() {
        return genePTT;
    }

    public void setGenePTT(GenePTT genePTT) {
        this.genePTT = genePTT;
    }

    public Set<MIFEntryInteraction> getmIFEntryInteraction() {
        return mIFEntryInteraction;
    }

    public void setmIFEntryInteraction(Set<MIFEntryInteraction> mIFEntryInteraction) {
        this.mIFEntryInteraction = mIFEntryInteraction;
    }

    @XmlTransient
    public Set<UniRefMember> getUniRefMember() {
        return uniRefMember;
    }

    public void setUniRefMember(Set<UniRefMember> uniRefMember) {
        this.uniRefMember = uniRefMember;
    }

    @XmlTransient
    public Set<UniRefEntry> getUniRefEntry() {
        return uniRefEntry;
    }

    public void setUniRefEntry(Set<UniRefEntry> uniRefEntry) {
        this.uniRefEntry = uniRefEntry;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBank() {
        return drugBank;
    }

    public void setDrugBank(Set<DrugBank> drugBank) {
        this.drugBank = drugBank;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBankAsEnzyme() {
        return drugBankAsEnzyme;
    }

    public void setDrugBankAsEnzyme(Set<DrugBank> drugBankAsEnzyme) {
        this.drugBankAsEnzyme = drugBankAsEnzyme;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBankAsTransporters() {
        return drugBankAsTransporters;
    }

    public void setDrugBankAsTransporters(Set<DrugBank> drugBankAsTransporters) {
        this.drugBankAsTransporters = drugBankAsTransporters;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBankAsCarriers() {
        return drugBankAsCarriers;
    }

    public void setDrugBankAsCarriers(Set<DrugBank> drugBankAsCarriers) {
        this.drugBankAsCarriers = drugBankAsCarriers;
    }

    @XmlTransient
    public Set<KEGGEnzyme> getkEGGEnzymes() {
        return kEGGEnzymes;
    }

    public void setkEGGEnzymes(Set<KEGGEnzyme> kEGGEnzymes) {
        this.kEGGEnzymes = kEGGEnzymes;
    }

    @XmlTransient
    public Set<KEGGPathway> getkEGGPathways() {
        return kEGGPathways;
    }

    public void setkEGGPathways(Set<KEGGPathway> kEGGPathways) {
        this.kEGGPathways = kEGGPathways;
    }

    @XmlTransient
    public Set<PfamAbioWH> getPfamAInsignificant() {
        return pfamAInsignificant;
    }

    public void setPfamAInsignificant(Set<PfamAbioWH> pfamAInsignificant) {
        this.pfamAInsignificant = pfamAInsignificant;
    }

    @XmlTransient
    public Set<PfamAbioWH> getPfamASignificant() {
        return pfamASignificant;
    }

    public void setPfamASignificant(Set<PfamAbioWH> pfamASignificant) {
        this.pfamASignificant = pfamASignificant;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.wid != null ? this.wid.hashCode() : 0);
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
        final Protein other = (Protein) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.version != other.version && (this.version == null || !this.version.equals(other.version))) {
            return false;
        }
        if (this.modified != other.modified && (this.modified == null || !this.modified.equals(other.modified))) {
            return false;
        }
        if (this.created != other.created && (this.created == null || !this.created.equals(other.created))) {
            return false;
        }
        if ((this.dataSetIn == null) ? (other.dataSetIn != null) : !this.dataSetIn.equals(other.dataSetIn)) {
            return false;
        }
        if ((this.existence == null) ? (other.existence != null) : !this.existence.equals(other.existence)) {
            return false;
        }
        if (this.seqLength != other.seqLength && (this.seqLength == null || !this.seqLength.equals(other.seqLength))) {
            return false;
        }
        if (this.mass != other.mass && (this.mass == null || !this.mass.equals(other.mass))) {
            return false;
        }
        if ((this.checksum == null) ? (other.checksum != null) : !this.checksum.equals(other.checksum)) {
            return false;
        }
        if (this.seqModified != other.seqModified && (this.seqModified == null || !this.seqModified.equals(other.seqModified))) {
            return false;
        }
        if (this.seqVersion != other.seqVersion && (this.seqVersion == null || !this.seqVersion.equals(other.seqVersion))) {
            return false;
        }
        if ((this.precursor == null) ? (other.precursor != null) : !this.precursor.equals(other.precursor)) {
            return false;
        }
        if ((this.fragment == null) ? (other.fragment != null) : !this.fragment.equals(other.fragment)) {
            return false;
        }
        return !((this.seq == null) ? (other.seq != null) : !this.seq.equals(other.seq));
    }

    @Override
    public String toString() {
        Iterator it;
        StringBuilder pData = new StringBuilder();
        String tSynonym = null;

        if (taxonomy != null && taxonomy.getSynonym() != null) {
            tSynonym = taxonomy.getTaxonomySynonym();
        }
        if (getTaxonomyHost() != null) {
            for (Taxonomy h : getTaxonomyHost()) {
                pData.append("\tOrganism Host=").append(h.getTaxonomySynonym()).append("\n");
            }
        }
        if (getOntology() != null) {
            for (Ontology ont : getOntology()) {
                pData.append("\tOntolgy=").append(ont.getId());
                pData.append(" Name=").append(ont.getName()).append("\n");
            }
        }
        if (getProteinName() != null) {
            for (ProteinName p : getProteinName()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinAccessionNumber() != null) {
            for (ProteinAccessionNumber p : getProteinAccessionNumber()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinLongName() != null) {
            for (ProteinLongName p : getProteinLongName()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinDBReference() != null) {
            for (ProteinDBReference p : getProteinDBReference()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinPMID() != null) {
            for (ProteinPMID p : getProteinPMID()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinEC() != null) {
            for (ProteinEC p : getProteinEC()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinKEGG() != null) {
            for (ProteinKEGG p : getProteinKEGG()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinBioCyc() != null) {
            for (ProteinBioCyc p : getProteinBioCyc()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinPDB() != null) {
            for (ProteinPDB p : getProteinPDB()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinIntAct() != null) {
            for (ProteinIntAct p : getProteinIntAct()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinDIP() != null) {
            for (ProteinDIP p : getProteinDIP()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinMINT() != null) {
            for (ProteinMINT p : getProteinMINT()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinDrugBank() != null) {
            for (ProteinDrugBank p : getProteinDrugBank()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinPFAM() != null) {
            for (ProteinPFAM p : getProteinPFAM()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinFeature() != null) {
            for (ProteinFeature p : getProteinFeature()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (getProteinComment() != null) {
            for (ProteinComment p : getProteinComment()) {
                pData.append("\t").append(p).append("\n");
            }
        }
        if (proteinKeyword != null) {
            for (ProteinKeyword p : proteinKeyword) {
                pData.append("\t").append(p).append("\n");
            }
        }

        return "Protein{"
                + " wid=" + wid
                + " version=" + version
                + " modified=" + modified
                + " created=" + created
                + " dataSet=" + dataSet
                + " existence=" + existence
                + " seqLength=" + seqLength
                + " mass=" + mass
                + " checksum=" + checksum
                + " seqModified=" + seqModified
                + " seqVersion=" + seqVersion
                + " precursor=" + precursor
                + " fragment=" + fragment
                + " seq=" + seq
                + " dataSetWID=" + dataSetWID + "}\n"
                + "\tOrganism=" + tSynonym + "\n"
                + pData;
    }
}
