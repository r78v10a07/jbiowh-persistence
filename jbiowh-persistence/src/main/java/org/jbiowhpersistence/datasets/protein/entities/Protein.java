package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
import org.jbiowhpersistence.datasets.taxonomy.entities.TaxonomySynonym;

/**
 * This Class is the Protein Entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-09-06 14:21:09 +0200 (Fri, 06 Sep 2013) $ 
 * $LastChangedRevision: 664 $
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
    @NamedQuery(name = "Protein.findProteinWIDByName", query = "SELECT p.wid FROM Protein p INNER JOIN p.proteinName n WHERE UPPER(n.proteinNamePK.name) LIKE :name GROUP BY p.wid"),
    @NamedQuery(name = "Protein.findProteinWIDByLongName", query = "SELECT p.wid FROM Protein p INNER JOIN p.proteinLongName n WHERE UPPER(n.name) LIKE :name GROUP BY p.wid"),
    @NamedQuery(name = "Protein.findProteinWIDByAccessionNumber", query = "SELECT p.wid FROM Protein p INNER JOIN p.proteinAccessionNumber n WHERE UPPER(n.proteinAccessionNumberPK.accessionNumber) LIKE :accessionNumber group by p.wid"),
    @NamedQuery(name = "Protein.findProteinByAccessionNumber", query = "SELECT p FROM Protein p INNER JOIN p.proteinAccessionNumber n WHERE UPPER(n.proteinAccessionNumberPK.accessionNumber) LIKE :accessionNumber group by p"),
    @NamedQuery(name = "Protein.findProteinWIDByEC", query = "SELECT p.wid FROM Protein p INNER JOIN p.proteinEC n WHERE n.proteinECPK.id LIKE :id GROUP BY p.wid"),
    @NamedQuery(name = "Protein.findEnsemblId", query = "SELECT p FROM Protein p INNER JOIN p.proteinDBReference n WHERE n.id LIKE :id AND n.type = 'Ensembl' GROUP BY p"),
    @NamedQuery(name = "Protein.findIPIId", query = "SELECT p FROM Protein p INNER JOIN p.proteinDBReference n WHERE n.id LIKE :id AND n.type = 'IPI' GROUP BY p")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinhasProteinKeywordPK")
    private Map<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword> proteinhasProteinKeyword;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinNamePK")
    private Map<ProteinNamePK, ProteinName> proteinName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinAccessionNumberPK")
    private Map<ProteinAccessionNumberPK, ProteinAccessionNumber> proteinAccessionNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    private Set<ProteinLongName> proteinLongName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    private Set<ProteinDBReference> proteinDBReference;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinPMIDPK")
    private Map<ProteinPMIDPK, ProteinPMID> proteinPMID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinECPK")
    private Map<ProteinECPK, ProteinEC> proteinEC;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinKEGGPK")
    private Map<ProteinKEGGPK, ProteinKEGG> proteinKEGG;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinBioCycPK")
    private Map<ProteinBioCycPK, ProteinBioCyc> proteinBioCyc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinPDBPK")
    private Map<ProteinPDBPK, ProteinPDB> proteinPDB;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinIntActPK")
    private Map<ProteinIntActPK, ProteinIntAct> proteinIntAct;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinDIPPK")
    private Map<ProteinDIPPK, ProteinDIP> proteinDIP;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinMINTPK")
    private Map<ProteinMINTPK, ProteinMINT> proteinMINT;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinDrugBankPK")
    private Map<ProteinDrugBankPK, ProteinDrugBank> proteinDrugBank;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    @MapKey(name = "proteinPFAMPK")
    private Map<ProteinPFAMPK, ProteinPFAM> proteinPFAM;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    private Set<ProteinFeature> proteinFeature;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    private Set<ProteinComment> proteinComment;
    // External Protein relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSetWID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_TAXONOMY,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"))
    private Taxonomy taxonomy;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_TAXONOMYHOST,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"))
    private Set<Taxonomy> taxonomyHost;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_ONTOLOGY,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Ontology_WID", referencedColumnName = "WID"))
    private Set<Ontology> ontology;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_GENEINFO,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    private Set<GeneInfo> geneInfo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_GENEPTT,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "GenePTT_ProteinGi", referencedColumnName = "ProteinGi"))
    private GenePTT genePTT;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = MIF25Tables.MIFINTERACTION_HAS_PROTEIN,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "MIFEntryInteraction_WID", referencedColumnName = "WID"))
    private Set<MIFEntryInteraction> mIFEntryInteraction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protein")
    private Set<UniRefMember> uniRefMember;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = UniRefTables.UNIREFENTRY_HAS_PROTEIN,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "UniRefEntry_WID", referencedColumnName = "WID"))
    private Set<UniRefEntry> uniRefEntry;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANK,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    private Set<DrugBank> drugBank;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASENZYME,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    private Set<DrugBank> drugBankAsEnzyme;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASTRANSPORTERS,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    private Set<DrugBank> drugBankAsTransporters;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASCARRIERS,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"))
    private Set<DrugBank> drugBankAsCarriers;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_KEGGENZYME,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"))
    private Set<KEGGEnzyme> kEGGEnzymes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGPATHWAY_HAS_PROTEN,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"))
    private Set<KEGGPathway> kEGGPathways;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMAREGFULLINSIGNIFICANT,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"))
    private Set<PfamAbioWH> pfamAInsignificant;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMAREGFULLSIGNIFICANT,
    joinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"))
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
            return proteinAccessionNumber.values().iterator().next().getProteinAccessionNumberPK().getAccessionNumber();
        }
        return null;
    }

    public String getProteinNameDirected() {
        if (!proteinName.isEmpty()) {
            return proteinName.values().iterator().next().proteinNamePK.getName();
        }
        return null;
    }

    public String getProteinLongNameDirected() {
        if (!proteinLongName.isEmpty()) {
            return proteinLongName.iterator().next().getName();
        }
        return null;
    }

    public GenePTT getGenePTT() {
        return genePTT;
    }

    public void setGenePTT(GenePTT genePTT) {
        this.genePTT = genePTT;
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

    public void setGeneInfo(Set<GeneInfo> geneInfo) {
        this.geneInfo = geneInfo;
    }

    @XmlTransient
    public Set<KEGGPathway> getkEGGPathways() {
        return kEGGPathways;
    }

    public void setkEGGPathways(Set<KEGGPathway> kEGGPathways) {
        this.kEGGPathways = kEGGPathways;
    }

    @XmlTransient
    public Set<KEGGEnzyme> getkEGGEnzymes() {
        return kEGGEnzymes;
    }

    public void setkEGGEnzymes(Set<KEGGEnzyme> kEGGEnzymes) {
        this.kEGGEnzymes = kEGGEnzymes;
    }

    @XmlTransient
    public Set<UniRefEntry> getUniRefEntry() {
        return uniRefEntry;
    }

    public void setUniRefEntry(Set<UniRefEntry> uniRefEntry) {
        this.uniRefEntry = uniRefEntry;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBankAsCarriers() {
        return drugBankAsCarriers;
    }

    public void setDrugBankAsCarriers(Set<DrugBank> drugBankAsCarriers) {
        this.drugBankAsCarriers = drugBankAsCarriers;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBankAsTransporters() {
        return drugBankAsTransporters;
    }

    public void setDrugBankAsTransporters(Set<DrugBank> drugBankAsTransporters) {
        this.drugBankAsTransporters = drugBankAsTransporters;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBankAsEnzyme() {
        return drugBankAsEnzyme;
    }

    public void setDrugBankAsEnzyme(Set<DrugBank> drugBankAsEnzyme) {
        this.drugBankAsEnzyme = drugBankAsEnzyme;
    }

    @XmlTransient
    public Set<DrugBank> getDrugBank() {
        return drugBank;
    }

    public void setDrugBank(Set<DrugBank> drugBank) {
        this.drugBank = drugBank;
    }

    @XmlTransient
    public Set<UniRefMember> getUniRefMember() {
        return uniRefMember;
    }

    public void setUniRefMember(Set<UniRefMember> uniRefMember) {
        this.uniRefMember = uniRefMember;
    }

    @XmlTransient
    public Set<MIFEntryInteraction> getmIFEntryInteraction() {
        return mIFEntryInteraction;
    }

    public void setmIFEntryInteraction(Set<MIFEntryInteraction> mIFEntryInteraction) {
        this.mIFEntryInteraction = mIFEntryInteraction;
    }

    @XmlTransient
    public Set<ProteinComment> getProteinComment() {
        return proteinComment;
    }

    public void setProteinComment(Set<ProteinComment> proteinComment) {
        this.proteinComment = proteinComment;
    }

    @XmlTransient
    public Set<ProteinFeature> getProteinFeature() {
        return proteinFeature;
    }

    public void setProteinFeature(Set<ProteinFeature> ProteinFeature) {
        this.proteinFeature = ProteinFeature;
    }

    @XmlTransient
    public Map<ProteinPFAMPK, ProteinPFAM> getProteinPFAM() {
        return proteinPFAM;
    }

    public void setProteinPFAM(Map<ProteinPFAMPK, ProteinPFAM> proteinPFAM) {
        this.proteinPFAM = proteinPFAM;
    }

    @XmlTransient
    public Map<ProteinDrugBankPK, ProteinDrugBank> getProteinDrugBank() {
        return proteinDrugBank;
    }

    public void setProteinDrugBank(Map<ProteinDrugBankPK, ProteinDrugBank> proteinDrugBank) {
        this.proteinDrugBank = proteinDrugBank;
    }

    @XmlTransient
    public Map<ProteinMINTPK, ProteinMINT> getProteinMINT() {
        return proteinMINT;
    }

    public void setProteinMINT(Map<ProteinMINTPK, ProteinMINT> proteinMINT) {
        this.proteinMINT = proteinMINT;
    }

    @XmlTransient
    public Map<ProteinDIPPK, ProteinDIP> getProteinDIP() {
        return proteinDIP;
    }

    public void setProteinDIP(Map<ProteinDIPPK, ProteinDIP> proteinDIP) {
        this.proteinDIP = proteinDIP;
    }

    @XmlTransient
    public Map<ProteinIntActPK, ProteinIntAct> getProteinIntAct() {
        return proteinIntAct;
    }

    public void setProteinIntAct(Map<ProteinIntActPK, ProteinIntAct> proteinIntAct) {
        this.proteinIntAct = proteinIntAct;
    }

    @XmlTransient
    public Map<ProteinPDBPK, ProteinPDB> getProteinPDB() {
        return proteinPDB;
    }

    public void setProteinPDB(Map<ProteinPDBPK, ProteinPDB> proteinPDB) {
        this.proteinPDB = proteinPDB;
    }

    @XmlTransient
    public Map<ProteinBioCycPK, ProteinBioCyc> getProteinBioCyc() {
        return proteinBioCyc;
    }

    public void setProteinBioCyc(Map<ProteinBioCycPK, ProteinBioCyc> proteinBioCyc) {
        this.proteinBioCyc = proteinBioCyc;
    }

    @XmlTransient
    public Map<ProteinKEGGPK, ProteinKEGG> getProteinKEGG() {
        return proteinKEGG;
    }

    public void setProteinKEGG(Map<ProteinKEGGPK, ProteinKEGG> proteinKEGG) {
        this.proteinKEGG = proteinKEGG;
    }

    @XmlTransient
    public Map<ProteinECPK, ProteinEC> getProteinEC() {
        return proteinEC;
    }

    public void setProteinEC(Map<ProteinECPK, ProteinEC> proteinEC) {
        this.proteinEC = proteinEC;
    }

    @XmlTransient
    public Map<ProteinPMIDPK, ProteinPMID> getProteinPMID() {
        return proteinPMID;
    }

    public void setProteinPMID(Map<ProteinPMIDPK, ProteinPMID> proteinPMID) {
        this.proteinPMID = proteinPMID;
    }

    @XmlTransient
    public Set<ProteinDBReference> getProteinDBReference() {
        return proteinDBReference;
    }

    public void setProteinDBReference(Set<ProteinDBReference> proteinWIDDBReference) {
        this.proteinDBReference = proteinWIDDBReference;
    }

    @XmlTransient
    public Set<ProteinLongName> getProteinLongName() {
        return proteinLongName;
    }

    public void setProteinLongName(Set<ProteinLongName> proteinLongName) {
        this.proteinLongName = proteinLongName;
    }

    @XmlTransient
    public Map<ProteinAccessionNumberPK, ProteinAccessionNumber> getProteinAccessionNumber() {
        return proteinAccessionNumber;
    }

    public void setProteinAccessionNumber(Map<ProteinAccessionNumberPK, ProteinAccessionNumber> proteinAccessionNumber) {
        this.proteinAccessionNumber = proteinAccessionNumber;
    }

    @XmlTransient
    public Map<ProteinNamePK, ProteinName> getProteinName() {
        return proteinName;
    }

    public void setProteinName(Map<ProteinNamePK, ProteinName> proteinName) {
        this.proteinName = proteinName;
    }

    @XmlTransient
    public Map<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword> getProteinhasProteinKeyword() {
        return proteinhasProteinKeyword;
    }

    public void setProteinhasProteinKeyword(Map<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword> proteinhasProteinKeyword) {
        this.proteinhasProteinKeyword = proteinhasProteinKeyword;
    }
    
    @XmlTransient
    public Set<Ontology> getOntology() {
        return ontology;
    }

    public void setOntology(Set<Ontology> ontology) {
        this.ontology = ontology;
    }

    @XmlTransient
    public Set<Taxonomy> getTaxonomyHost() {
        return taxonomyHost;
    }

    public void setTaxonomyHost(Set<Taxonomy> taxonomyHost) {
        this.taxonomyHost = taxonomyHost;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Protein other = (Protein) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.modified, other.modified)) {
            return false;
        }
        if (!Objects.equals(this.created, other.created)) {
            return false;
        }
        if (!Objects.equals(this.dataSetIn, other.dataSetIn)) {
            return false;
        }
        if (!Objects.equals(this.existence, other.existence)) {
            return false;
        }
        if (!Objects.equals(this.seqLength, other.seqLength)) {
            return false;
        }
        if (!Objects.equals(this.mass, other.mass)) {
            return false;
        }
        if (!Objects.equals(this.checksum, other.checksum)) {
            return false;
        }
        if (!Objects.equals(this.seqModified, other.seqModified)) {
            return false;
        }
        if (!Objects.equals(this.seqVersion, other.seqVersion)) {
            return false;
        }
        if (!Objects.equals(this.precursor, other.precursor)) {
            return false;
        }
        if (!Objects.equals(this.fragment, other.fragment)) {
            return false;
        }
        if (!Objects.equals(this.seq, other.seq)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (!Objects.equals(this.proteinhasProteinKeyword, other.proteinhasProteinKeyword)) {
            return false;
        }
        if (!Objects.equals(this.proteinName, other.proteinName)) {
            return false;
        }
        if (!Objects.equals(this.proteinAccessionNumber, other.proteinAccessionNumber)) {
            return false;
        }
        if (!Objects.equals(this.proteinLongName, other.proteinLongName)) {
            return false;
        }
        if (!Objects.equals(this.proteinDBReference, other.proteinDBReference)) {
            return false;
        }
        if (!Objects.equals(this.proteinPMID, other.proteinPMID)) {
            return false;
        }
        if (!Objects.equals(this.proteinEC, other.proteinEC)) {
            return false;
        }
        if (!Objects.equals(this.proteinKEGG, other.proteinKEGG)) {
            return false;
        }
        if (!Objects.equals(this.proteinBioCyc, other.proteinBioCyc)) {
            return false;
        }
        if (!Objects.equals(this.proteinPDB, other.proteinPDB)) {
            return false;
        }
        if (!Objects.equals(this.proteinIntAct, other.proteinIntAct)) {
            return false;
        }
        if (!Objects.equals(this.proteinDIP, other.proteinDIP)) {
            return false;
        }
        if (!Objects.equals(this.proteinMINT, other.proteinMINT)) {
            return false;
        }
        if (!Objects.equals(this.proteinDrugBank, other.proteinDrugBank)) {
            return false;
        }
        if (!Objects.equals(this.proteinPFAM, other.proteinPFAM)) {
            return false;
        }
        if (!Objects.equals(this.proteinFeature, other.proteinFeature)) {
            return false;
        }
        if (!Objects.equals(this.proteinComment, other.proteinComment)) {
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
        Iterator it;
        StringBuilder pData = new StringBuilder();
        String tSynonym = null;

        if (taxonomy.getSynonym() != null) {
            it = taxonomy.getSynonym().values().iterator();
            while (it.hasNext()) {
                TaxonomySynonym taxonomySynonym = (TaxonomySynonym) it.next();
                if (taxonomySynonym.getNameClass().getNameClass().equals("scientific name")) {
                    tSynonym = taxonomySynonym.getTaxonomySynonymPK().getSynonym();
                }
            }
        }

        it = getTaxonomyHost().iterator();
        while (it.hasNext()) {
            Taxonomy hTaxonomy = (Taxonomy) it.next();
            Iterator it1 = hTaxonomy.getSynonym().values().iterator();
            while (it1.hasNext()) {
                TaxonomySynonym taxonomySynonym = (TaxonomySynonym) it1.next();
                if (taxonomySynonym.getNameClass().getNameClass().equals("scientific name")) {
                    pData.append("\tOrganism Host=").append(
                            taxonomySynonym.getTaxonomySynonymPK().getSynonym()).append("\n");
                }
            }
        }

        if (getOntology() != null) {
            it = getOntology().iterator();
            while (it.hasNext()) {
                Ontology ont = (Ontology) it.next();
                pData.append("\tOntolgy=").append(ont.getId());
                pData.append(" Name=").append(ont.getName()).append("\n");
            }
        }
        if (getProteinName() != null) {
            if (!getProteinName().isEmpty()) {
                it = getProteinName().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinAccessionNumber() != null) {
            if (!getProteinAccessionNumber().isEmpty()) {
                it = getProteinAccessionNumber().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinLongName() != null) {
            it = getProteinLongName().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (getProteinDBReference() != null) {
            it = getProteinDBReference().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (getProteinPMID() != null) {
            if (!getProteinPMID().isEmpty()) {
                it = getProteinPMID().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinEC() != null) {
            if (!getProteinEC().isEmpty()) {
                it = getProteinEC().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinKEGG() != null) {
            if (!getProteinKEGG().isEmpty()) {
                it = getProteinKEGG().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinBioCyc() != null) {
            if (!getProteinBioCyc().isEmpty()) {
                it = getProteinBioCyc().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinPDB() != null) {
            if (!getProteinPDB().isEmpty()) {
                it = getProteinPDB().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinIntAct() != null) {
            if (!getProteinIntAct().isEmpty()) {
                it = getProteinIntAct().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinDIP() != null) {
            if (!getProteinDIP().isEmpty()) {
                it = getProteinDIP().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinMINT() != null) {
            if (!getProteinMINT().isEmpty()) {
                it = getProteinMINT().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinDrugBank() != null) {
            if (!getProteinDrugBank().isEmpty()) {
                it = getProteinDrugBank().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinPFAM() != null) {
            if (!getProteinPFAM().isEmpty()) {
                it = getProteinPFAM().values().iterator();
                while (it.hasNext()) {
                    pData.append("\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinFeature() != null) {
            it = getProteinFeature().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (getProteinComment() != null) {
            it = getProteinComment().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (getProteinhasProteinKeyword() != null) {
            it = getProteinhasProteinKeyword().values().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
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

    public String toFasta() {
        StringBuilder pData = new StringBuilder();

        pData.append(">").append(getProteinName().values().iterator().next().getProteinNamePK().getName());
        if (!getProteinLongName().isEmpty()) {
            pData.append(" ").append(((ProteinLongName) getProteinLongName().toArray()[0]).getName());
        }

        pData.append("\n");
        int j = 0;
        for (int i = 0; i < seq.length(); i++) {
            j++;
            pData.append(seq.charAt(i));
            if (j == 60 && i != seq.length() - 1) {
                pData.append("\n");
                j = 0;
            }
        }
        if (!seq.endsWith("\n")) {
            pData.append("\n");
        }

        return pData.toString();
    }
}
