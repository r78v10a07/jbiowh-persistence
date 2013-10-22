package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.domain.pfam.PFamTables;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 * This class is the PfamAbioWH entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ $LastChangedRevision: 377 $
 *
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamAbioWH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamAbioWH.findAll", query = "SELECT p FROM PfamAbioWH p"),
    @NamedQuery(name = "PfamAbioWH.findByWid", query = "SELECT p FROM PfamAbioWH p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamAbioWH.findByPfamAbioWHacc", query = "SELECT p FROM PfamAbioWH p WHERE p.pfamAacc = :pfamAacc"),
    @NamedQuery(name = "PfamAbioWH.findByPfamAbioWHid", query = "SELECT p FROM PfamAbioWH p WHERE p.pfamAid = :pfamAid"),
    @NamedQuery(name = "PfamAbioWH.findByPreviousId", query = "SELECT p FROM PfamAbioWH p WHERE p.previousId = :previousId"),
    @NamedQuery(name = "PfamAbioWH.findByDescription", query = "SELECT p FROM PfamAbioWH p WHERE p.description = :description"),
    @NamedQuery(name = "PfamAbioWH.findByAuthor", query = "SELECT p FROM PfamAbioWH p WHERE p.author = :author"),
    @NamedQuery(name = "PfamAbioWH.findByDepositedBy", query = "SELECT p FROM PfamAbioWH p WHERE p.depositedBy = :depositedBy"),
    @NamedQuery(name = "PfamAbioWH.findBySeedSource", query = "SELECT p FROM PfamAbioWH p WHERE p.seedSource = :seedSource"),
    @NamedQuery(name = "PfamAbioWH.findByType", query = "SELECT p FROM PfamAbioWH p WHERE p.type = :type"),
    @NamedQuery(name = "PfamAbioWH.findBySequenceGA", query = "SELECT p FROM PfamAbioWH p WHERE p.sequenceGA = :sequenceGA"),
    @NamedQuery(name = "PfamAbioWH.findByDomainGA", query = "SELECT p FROM PfamAbioWH p WHERE p.domainGA = :domainGA"),
    @NamedQuery(name = "PfamAbioWH.findBySequenceTC", query = "SELECT p FROM PfamAbioWH p WHERE p.sequenceTC = :sequenceTC"),
    @NamedQuery(name = "PfamAbioWH.findByDomainTC", query = "SELECT p FROM PfamAbioWH p WHERE p.domainTC = :domainTC"),
    @NamedQuery(name = "PfamAbioWH.findBySequenceNC", query = "SELECT p FROM PfamAbioWH p WHERE p.sequenceNC = :sequenceNC"),
    @NamedQuery(name = "PfamAbioWH.findByDomainNC", query = "SELECT p FROM PfamAbioWH p WHERE p.domainNC = :domainNC"),
    @NamedQuery(name = "PfamAbioWH.findByBuildMethod", query = "SELECT p FROM PfamAbioWH p WHERE p.buildMethod = :buildMethod"),
    @NamedQuery(name = "PfamAbioWH.findByModelLength", query = "SELECT p FROM PfamAbioWH p WHERE p.modelLength = :modelLength"),
    @NamedQuery(name = "PfamAbioWH.findBySearchMethod", query = "SELECT p FROM PfamAbioWH p WHERE p.searchMethod = :searchMethod"),
    @NamedQuery(name = "PfamAbioWH.findByMsvLambda", query = "SELECT p FROM PfamAbioWH p WHERE p.msvLambda = :msvLambda"),
    @NamedQuery(name = "PfamAbioWH.findByMsvMu", query = "SELECT p FROM PfamAbioWH p WHERE p.msvMu = :msvMu"),
    @NamedQuery(name = "PfamAbioWH.findByViterbiLambda", query = "SELECT p FROM PfamAbioWH p WHERE p.viterbiLambda = :viterbiLambda"),
    @NamedQuery(name = "PfamAbioWH.findByViterbiMu", query = "SELECT p FROM PfamAbioWH p WHERE p.viterbiMu = :viterbiMu"),
    @NamedQuery(name = "PfamAbioWH.findByForwardLambda", query = "SELECT p FROM PfamAbioWH p WHERE p.forwardLambda = :forwardLambda"),
    @NamedQuery(name = "PfamAbioWH.findByForwardTau", query = "SELECT p FROM PfamAbioWH p WHERE p.forwardTau = :forwardTau"),
    @NamedQuery(name = "PfamAbioWH.findByNumSeed", query = "SELECT p FROM PfamAbioWH p WHERE p.numSeed = :numSeed"),
    @NamedQuery(name = "PfamAbioWH.findByNumFull", query = "SELECT p FROM PfamAbioWH p WHERE p.numFull = :numFull"),
    @NamedQuery(name = "PfamAbioWH.findByUpdated", query = "SELECT p FROM PfamAbioWH p WHERE p.updated = :updated"),
    @NamedQuery(name = "PfamAbioWH.findByCreated", query = "SELECT p FROM PfamAbioWH p WHERE p.created = :created"),
    @NamedQuery(name = "PfamAbioWH.findByVersion", query = "SELECT p FROM PfamAbioWH p WHERE p.version = :version"),
    @NamedQuery(name = "PfamAbioWH.findByNumberArchs", query = "SELECT p FROM PfamAbioWH p WHERE p.numberArchs = :numberArchs"),
    @NamedQuery(name = "PfamAbioWH.findByNumberSpecies", query = "SELECT p FROM PfamAbioWH p WHERE p.numberSpecies = :numberSpecies"),
    @NamedQuery(name = "PfamAbioWH.findByNumberStructures", query = "SELECT p FROM PfamAbioWH p WHERE p.numberStructures = :numberStructures"),
    @NamedQuery(name = "PfamAbioWH.findByNumberNcbi", query = "SELECT p FROM PfamAbioWH p WHERE p.numberNcbi = :numberNcbi"),
    @NamedQuery(name = "PfamAbioWH.findByNumberMeta", query = "SELECT p FROM PfamAbioWH p WHERE p.numberMeta = :numberMeta"),
    @NamedQuery(name = "PfamAbioWH.findByAverageLength", query = "SELECT p FROM PfamAbioWH p WHERE p.averageLength = :averageLength"),
    @NamedQuery(name = "PfamAbioWH.findByPercentageId", query = "SELECT p FROM PfamAbioWH p WHERE p.percentageId = :percentageId"),
    @NamedQuery(name = "PfamAbioWH.findByAverageCoverage", query = "SELECT p FROM PfamAbioWH p WHERE p.averageCoverage = :averageCoverage"),
    @NamedQuery(name = "PfamAbioWH.findByChangeStatus", query = "SELECT p FROM PfamAbioWH p WHERE p.changeStatus = :changeStatus"),
    @NamedQuery(name = "PfamAbioWH.findByNumberShuffledHits", query = "SELECT p FROM PfamAbioWH p WHERE p.numberShuffledHits = :numberShuffledHits"),
    @NamedQuery(name = "PfamAbioWH.findByDataSetWID", query = "SELECT p FROM PfamAbioWH p WHERE p.dataSetWID = :dataSetWID")})
public class PfamAbioWH implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "pfamA_acc")
    private String pfamAacc;
    @Basic(optional = false)
    @Column(name = "pfamA_id")
    private String pfamAid;
    @Column(name = "previous_id")
    private String previousId;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @Column(name = "deposited_by")
    private String depositedBy;
    @Basic(optional = false)
    @Column(name = "seed_source")
    private String seedSource;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "sequence_GA")
    private double sequenceGA;
    @Basic(optional = false)
    @Column(name = "domain_GA")
    private double domainGA;
    @Basic(optional = false)
    @Column(name = "sequence_TC")
    private double sequenceTC;
    @Basic(optional = false)
    @Column(name = "domain_TC")
    private double domainTC;
    @Basic(optional = false)
    @Column(name = "sequence_NC")
    private double sequenceNC;
    @Basic(optional = false)
    @Column(name = "domain_NC")
    private double domainNC;
    @Basic(optional = false)
    @Column(name = "buildMethod")
    private String buildMethod;
    @Basic(optional = false)
    @Column(name = "model_length")
    private int modelLength;
    @Basic(optional = false)
    @Column(name = "searchMethod")
    private String searchMethod;
    @Basic(optional = false)
    @Column(name = "msv_lambda")
    private double msvLambda;
    @Basic(optional = false)
    @Column(name = "msv_mu")
    private double msvMu;
    @Basic(optional = false)
    @Column(name = "viterbi_lambda")
    private double viterbiLambda;
    @Basic(optional = false)
    @Column(name = "viterbi_mu")
    private double viterbiMu;
    @Basic(optional = false)
    @Column(name = "forward_lambda")
    private double forwardLambda;
    @Basic(optional = false)
    @Column(name = "forward_tau")
    private double forwardTau;
    @Column(name = "num_seed")
    private Integer numSeed;
    @Column(name = "num_full")
    private Integer numFull;
    @Basic(optional = false)
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "version")
    private Short version;
    @Column(name = "number_archs")
    private Integer numberArchs;
    @Column(name = "number_species")
    private Integer numberSpecies;
    @Column(name = "number_structures")
    private Integer numberStructures;
    @Column(name = "number_ncbi")
    private Integer numberNcbi;
    @Column(name = "number_meta")
    private Integer numberMeta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "average_length")
    private Double averageLength;
    @Column(name = "percentage_id")
    private Integer percentageId;
    @Column(name = "average_coverage")
    private Double averageCoverage;
    @Column(name = "change_status")
    private String changeStatus;
    @Lob
    @Column(name = "seed_consensus")
    private String seedConsensus;
    @Lob
    @Column(name = "full_consensus")
    private String fullConsensus;
    @Column(name = "number_shuffled_hits")
    private Integer numberShuffledHits;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    // Internal links
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    private Set<PfamADatabaseLinks> pfamADatabaseLinkses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    private Set<PfamANCBIReg> pfamANCBIRegs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    private Set<PfamARegFullSignificant> pfamARegFullSignificants;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    private Set<PfamARegFullInsignificant> pfamARegFullInsignificants;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    @MapKey(name = "pfamARegSeedPK")
    private Map<PfamARegSeedPK, PfamARegSeed> pfamARegSeed;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMA_HAS_PFAMARCHITECTURE,
    joinColumns =
    @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "PfamArchitecture_WID", referencedColumnName = "WID"))
    private Set<PfamArchitecture> pfamArchitectures;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    @MapKey(name = "pfamAhasPfamLiteratureReferencesPK")
    private Map<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences> pfamAhasPfamLiteratureReferences;    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMCLANS_HAS_PFAMA,
    joinColumns =
    @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "PfamClans_WID", referencedColumnName = "WID"))
    private Set<PfamClans> pfamClanses;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    @MapKey(name = "pfamProteomeRegionsPK")
    private Map<PfamProteomeRegionsPK, PfamProteomeRegions> pfamProteomeRegions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    private Set<PfamContextRegions> pfamContextRegions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    private Set<PfamInterpro> pfamInterpros;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamA")
    private Set<PfamNestedLocations> pfamNestedLocationses;
    // External links
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMAREGFULLINSIGNIFICANT,
    joinColumns =
    @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> proteinInsignificant;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMAREGFULLSIGNIFICANT,
    joinColumns =
    @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> proteinSignificant;

    public PfamAbioWH() {
    }

    public PfamAbioWH(Long wid) {
        this.wid = wid;
    }

    public PfamAbioWH(Long wid, String pfamAacc, String pfamAid, String description, String author, String depositedBy, String seedSource, String type, double sequenceGA, double domainGA, double sequenceTC, double domainTC, double sequenceNC, double domainNC, String buildMethod, int modelLength, String searchMethod, double msvLambda, double msvMu, double viterbiLambda, double viterbiMu, double forwardLambda, double forwardTau, Date updated, long dataSetWID) {
        this.wid = wid;
        this.pfamAacc = pfamAacc;
        this.pfamAid = pfamAid;
        this.description = description;
        this.author = author;
        this.depositedBy = depositedBy;
        this.seedSource = seedSource;
        this.type = type;
        this.sequenceGA = sequenceGA;
        this.domainGA = domainGA;
        this.sequenceTC = sequenceTC;
        this.domainTC = domainTC;
        this.sequenceNC = sequenceNC;
        this.domainNC = domainNC;
        this.buildMethod = buildMethod;
        this.modelLength = modelLength;
        this.searchMethod = searchMethod;
        this.msvLambda = msvLambda;
        this.msvMu = msvMu;
        this.viterbiLambda = viterbiLambda;
        this.viterbiMu = viterbiMu;
        this.forwardLambda = forwardLambda;
        this.forwardTau = forwardTau;
        this.updated = updated;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        setPfamARegFullSignificants(null);
        setPfamARegFullInsignificants(null);
        setPfamArchitectures(null);
        setPfamClanses(null);
        setPfamAhasPfamLiteratureReferences(null);
        setPfamProteomeRegions(null);
        setProteinInsignificant(null);
        setProteinSignificant(null);
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public Set<Protein> getProteinInsignificant() {
        return proteinInsignificant;
    }

    public void setProteinInsignificant(Set<Protein> proteinInsignificant) {
        this.proteinInsignificant = proteinInsignificant;
    }

    public Set<Protein> getProteinSignificant() {
        return proteinSignificant;
    }

    public void setProteinSignificant(Set<Protein> proteinSignificant) {
        this.proteinSignificant = proteinSignificant;
    }

    public Set<PfamARegFullSignificant> getPfamARegFullSignificants() {
        return pfamARegFullSignificants;
    }

    public void setPfamARegFullSignificants(Set<PfamARegFullSignificant> pfamARegFullSignificants) {
        this.pfamARegFullSignificants = pfamARegFullSignificants;
    }

    public Set<PfamARegFullInsignificant> getPfamARegFullInsignificants() {
        return pfamARegFullInsignificants;
    }

    public void setPfamARegFullInsignificants(Set<PfamARegFullInsignificant> pfamARegFullInsignificants) {
        this.pfamARegFullInsignificants = pfamARegFullInsignificants;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Map<PfamARegSeedPK, PfamARegSeed> getPfamARegSeed() {
        return pfamARegSeed;
    }

    public void setPfamARegSeed(Map<PfamARegSeedPK, PfamARegSeed> pfamARegSeed) {
        this.pfamARegSeed = pfamARegSeed;
    }

    public Set<PfamArchitecture> getPfamArchitectures() {
        return pfamArchitectures;
    }

    public void setPfamArchitectures(Set<PfamArchitecture> pfamArchitectures) {
        this.pfamArchitectures = pfamArchitectures;
    }

    public Set<PfamClans> getPfamClanses() {
        return pfamClanses;
    }

    public void setPfamClanses(Set<PfamClans> pfamClanses) {
        this.pfamClanses = pfamClanses;
    }

    public Map<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences> getPfamAhasPfamLiteratureReferences() {
        return pfamAhasPfamLiteratureReferences;
    }

    public void setPfamAhasPfamLiteratureReferences(Map<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences> pfamAhasPfamLiteratureReferences) {
        this.pfamAhasPfamLiteratureReferences = pfamAhasPfamLiteratureReferences;
    }

    public Map<PfamProteomeRegionsPK, PfamProteomeRegions> getPfamProteomeRegions() {
        return pfamProteomeRegions;
    }

    public void setPfamProteomeRegions(Map<PfamProteomeRegionsPK, PfamProteomeRegions> pfamProteomeRegions) {
        this.pfamProteomeRegions = pfamProteomeRegions;
    }

    public Set<PfamContextRegions> getPfamContextRegions() {
        return pfamContextRegions;
    }

    public void setPfamContextRegions(Set<PfamContextRegions> pfamContextRegions) {
        this.pfamContextRegions = pfamContextRegions;
    }

    public Set<PfamInterpro> getPfamInterpros() {
        return pfamInterpros;
    }

    public void setPfamInterpros(Set<PfamInterpro> pfamInterpros) {
        this.pfamInterpros = pfamInterpros;
    }

    public Set<PfamNestedLocations> getPfamNestedLocationses() {
        return pfamNestedLocationses;
    }

    public void setPfamNestedLocationses(Set<PfamNestedLocations> pfamNestedLocationses) {
        this.pfamNestedLocationses = pfamNestedLocationses;
    }

    public Set<PfamANCBIReg> getPfamANCBIRegs() {
        return pfamANCBIRegs;
    }

    public void setPfamANCBIRegs(Set<PfamANCBIReg> pfamANCBIRegs) {
        this.pfamANCBIRegs = pfamANCBIRegs;
    }

    public Set<PfamADatabaseLinks> getPfamADatabaseLinkses() {
        return pfamADatabaseLinkses;
    }

    public void setPfamADatabaseLinkses(Set<PfamADatabaseLinks> pfamADatabaseLinkses) {
        this.pfamADatabaseLinkses = pfamADatabaseLinkses;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getPfamAacc() {
        return pfamAacc;
    }

    public void setPfamAacc(String pfamAacc) {
        this.pfamAacc = pfamAacc;
    }

    public String getPfamAid() {
        return pfamAid;
    }

    public void setPfamAid(String pfamAid) {
        this.pfamAid = pfamAid;
    }

    public String getPreviousId() {
        return previousId;
    }

    public void setPreviousId(String previousId) {
        this.previousId = previousId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDepositedBy() {
        return depositedBy;
    }

    public void setDepositedBy(String depositedBy) {
        this.depositedBy = depositedBy;
    }

    public String getSeedSource() {
        return seedSource;
    }

    public void setSeedSource(String seedSource) {
        this.seedSource = seedSource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getSequenceGA() {
        return sequenceGA;
    }

    public void setSequenceGA(double sequenceGA) {
        this.sequenceGA = sequenceGA;
    }

    public double getDomainGA() {
        return domainGA;
    }

    public void setDomainGA(double domainGA) {
        this.domainGA = domainGA;
    }

    public double getSequenceTC() {
        return sequenceTC;
    }

    public void setSequenceTC(double sequenceTC) {
        this.sequenceTC = sequenceTC;
    }

    public double getDomainTC() {
        return domainTC;
    }

    public void setDomainTC(double domainTC) {
        this.domainTC = domainTC;
    }

    public double getSequenceNC() {
        return sequenceNC;
    }

    public void setSequenceNC(double sequenceNC) {
        this.sequenceNC = sequenceNC;
    }

    public double getDomainNC() {
        return domainNC;
    }

    public void setDomainNC(double domainNC) {
        this.domainNC = domainNC;
    }

    public String getBuildMethod() {
        return buildMethod;
    }

    public void setBuildMethod(String buildMethod) {
        this.buildMethod = buildMethod;
    }

    public int getModelLength() {
        return modelLength;
    }

    public void setModelLength(int modelLength) {
        this.modelLength = modelLength;
    }

    public String getSearchMethod() {
        return searchMethod;
    }

    public void setSearchMethod(String searchMethod) {
        this.searchMethod = searchMethod;
    }

    public double getMsvLambda() {
        return msvLambda;
    }

    public void setMsvLambda(double msvLambda) {
        this.msvLambda = msvLambda;
    }

    public double getMsvMu() {
        return msvMu;
    }

    public void setMsvMu(double msvMu) {
        this.msvMu = msvMu;
    }

    public double getViterbiLambda() {
        return viterbiLambda;
    }

    public void setViterbiLambda(double viterbiLambda) {
        this.viterbiLambda = viterbiLambda;
    }

    public double getViterbiMu() {
        return viterbiMu;
    }

    public void setViterbiMu(double viterbiMu) {
        this.viterbiMu = viterbiMu;
    }

    public double getForwardLambda() {
        return forwardLambda;
    }

    public void setForwardLambda(double forwardLambda) {
        this.forwardLambda = forwardLambda;
    }

    public double getForwardTau() {
        return forwardTau;
    }

    public void setForwardTau(double forwardTau) {
        this.forwardTau = forwardTau;
    }

    public Integer getNumSeed() {
        return numSeed;
    }

    public void setNumSeed(Integer numSeed) {
        this.numSeed = numSeed;
    }

    public Integer getNumFull() {
        return numFull;
    }

    public void setNumFull(Integer numFull) {
        this.numFull = numFull;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public Integer getNumberArchs() {
        return numberArchs;
    }

    public void setNumberArchs(Integer numberArchs) {
        this.numberArchs = numberArchs;
    }

    public Integer getNumberSpecies() {
        return numberSpecies;
    }

    public void setNumberSpecies(Integer numberSpecies) {
        this.numberSpecies = numberSpecies;
    }

    public Integer getNumberStructures() {
        return numberStructures;
    }

    public void setNumberStructures(Integer numberStructures) {
        this.numberStructures = numberStructures;
    }

    public Integer getNumberNcbi() {
        return numberNcbi;
    }

    public void setNumberNcbi(Integer numberNcbi) {
        this.numberNcbi = numberNcbi;
    }

    public Integer getNumberMeta() {
        return numberMeta;
    }

    public void setNumberMeta(Integer numberMeta) {
        this.numberMeta = numberMeta;
    }

    public Double getAverageLength() {
        return averageLength;
    }

    public void setAverageLength(Double averageLength) {
        this.averageLength = averageLength;
    }

    public Integer getPercentageId() {
        return percentageId;
    }

    public void setPercentageId(Integer percentageId) {
        this.percentageId = percentageId;
    }

    public Double getAverageCoverage() {
        return averageCoverage;
    }

    public void setAverageCoverage(Double averageCoverage) {
        this.averageCoverage = averageCoverage;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    public String getSeedConsensus() {
        return seedConsensus;
    }

    public void setSeedConsensus(String seedConsensus) {
        this.seedConsensus = seedConsensus;
    }

    public String getFullConsensus() {
        return fullConsensus;
    }

    public void setFullConsensus(String fullConsensus) {
        this.fullConsensus = fullConsensus;
    }

    public Integer getNumberShuffledHits() {
        return numberShuffledHits;
    }

    public void setNumberShuffledHits(Integer numberShuffledHits) {
        this.numberShuffledHits = numberShuffledHits;
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
        final PfamAbioWH other = (PfamAbioWH) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.pfamAacc, other.pfamAacc)) {
            return false;
        }
        if (!Objects.equals(this.pfamAid, other.pfamAid)) {
            return false;
        }
        if (!Objects.equals(this.previousId, other.previousId)) {
            return false;
        }
        if (!Objects.equals(this.updated, other.updated)) {
            return false;
        }
        if (!Objects.equals(this.created, other.created)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamA{" + "wid=" + wid + ", pfamAacc=" + pfamAacc + ", pfamAid=" + pfamAid + ", previousId=" + previousId + ", description=" + description + ", author=" + author + ", depositedBy=" + depositedBy + ", seedSource=" + seedSource + ", type=" + type + ", comment=" + comment + ", sequenceGA=" + sequenceGA + ", domainGA=" + domainGA + ", sequenceTC=" + sequenceTC + ", domainTC=" + domainTC + ", sequenceNC=" + sequenceNC + ", domainNC=" + domainNC + ", buildMethod=" + buildMethod + ", modelLength=" + modelLength + ", searchMethod=" + searchMethod + ", msvLambda=" + msvLambda + ", msvMu=" + msvMu + ", viterbiLambda=" + viterbiLambda + ", viterbiMu=" + viterbiMu + ", forwardLambda=" + forwardLambda + ", forwardTau=" + forwardTau + ", numSeed=" + numSeed + ", numFull=" + numFull + ", updated=" + updated + ", created=" + created + ", version=" + version + ", numberArchs=" + numberArchs + ", numberSpecies=" + numberSpecies + ", numberStructures=" + numberStructures + ", numberNcbi=" + numberNcbi + ", numberMeta=" + numberMeta + ", averageLength=" + averageLength + ", percentageId=" + percentageId + ", averageCoverage=" + averageCoverage + ", changeStatus=" + changeStatus + ", seedConsensus=" + seedConsensus + ", fullConsensus=" + fullConsensus + ", numberShuffledHits=" + numberShuffledHits + '}';
    }
}
