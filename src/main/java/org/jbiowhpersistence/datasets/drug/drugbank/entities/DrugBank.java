package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.drug.drugbank.DrugBankTables;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.protein.ProteinTables;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 * This class is the DrugBank entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-11-08 14:37:19 +0100
 * (Thu, 08 Nov 2012) $ $LastChangedRevision: 322 $
 *
 * @since Sep 15, 2011
 */
@Entity
@Table(name = "DrugBank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBank.findAll", query = "SELECT d FROM DrugBank d"),
    @NamedQuery(name = "DrugBank.findByWid", query = "SELECT d FROM DrugBank d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBank.findById", query = "SELECT d FROM DrugBank d WHERE d.id like :id"),
    @NamedQuery(name = "DrugBank.findByName", query = "SELECT d FROM DrugBank d WHERE d.name like :name"),
    @NamedQuery(name = "DrugBank.findByCASNumber", query = "SELECT d FROM DrugBank d WHERE d.cASNumber = :cASNumber"),
    @NamedQuery(name = "DrugBank.findByIndication", query = "SELECT d FROM DrugBank d WHERE d.indication like :indication"),
    @NamedQuery(name = "DrugBank.findByType", query = "SELECT d FROM DrugBank d WHERE d.type = :type"),
    @NamedQuery(name = "DrugBank.findByVersion", query = "SELECT d FROM DrugBank d WHERE d.version = :version"),
    @NamedQuery(name = "DrugBank.findByUpdated", query = "SELECT d FROM DrugBank d WHERE d.updated = :updated"),
    @NamedQuery(name = "DrugBank.findByCreated", query = "SELECT d FROM DrugBank d WHERE d.created = :created"),
    @NamedQuery(name = "DrugBank.findByDataSetWID", query = "SELECT d FROM DrugBank d WHERE d.dataSetWID = :dataSetWID")})
public class DrugBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "Description")
    private String description;
    @Column(name = "CASNumber")
    private String cASNumber;
    @Lob
    @Column(name = "SynthesisRef")
    private String synthesisRef;
    @Lob
    @Column(name = "Indication")
    private String indication;
    @Lob
    @Column(name = "Pharmacology")
    private String pharmacology;
    @Lob
    @Column(name = "MechanismOfAction")
    private String mechanismOfAction;
    @Lob
    @Column(name = "Toxicity")
    private String toxicity;
    @Lob
    @Column(name = "Biotransformation")
    private String biotransformation;
    @Lob
    @Column(name = "Absorption")
    private String absorption;
    @Lob
    @Column(name = "HalfLife")
    private String halfLife;
    @Lob
    @Column(name = "ProteinBinding")
    private String proteinBinding;
    @Lob
    @Column(name = "RouteOfElimination")
    private String routeOfElimination;
    @Lob
    @Column(name = "VolumeOfDistribution")
    private String volumeOfDistribution;
    @Lob
    @Column(name = "Clearance")
    private String clearance;
    @Column(name = "Type")
    private String type;
    @Column(name = "Version")
    private String version;
    @Column(name = "Updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Column(name = "Created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    // Internal DrugBank relationship
    @ElementCollection
    @CollectionTable(
            name = "DrugBankSecondAccessionNumber",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankSecondAccessionNumbers")
    private Collection<DrugBankSecondAccessionNumber> drugBankSecondAccessionNumber;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankGroup",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankGroups")
    private Collection<DrugBankGroup> drugBankGroup;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankTaxonomy",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankTaxonomys")
    private Collection<DrugBankTaxonomy> drugBankTaxonomy;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankTaxonomySubstructure",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankTaxonomySubstructures")
    private Collection<DrugBankTaxonomySubstructure> drugBankTaxonomySubstructure;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankSynonym",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankSynonyms")
    private Collection<DrugBankSynonym> drugBankSynonym;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankBrand",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankBrands")
    private Collection<DrugBankBrand> drugBankBrand;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankMixture",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankMixtures")
    private Collection<DrugBankMixture> drugBankMixture;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankPackager",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankPackagers")
    private Collection<DrugBankPackager> drugBankPackager;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankManufacturer",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankManufacturers")
    private Collection<DrugBankManufacturer> drugBankManufacturer;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankPrice",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankPrices")
    private Collection<DrugBankPrice> drugBankPrice;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankAffectedOrganism",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankAffectedOrganisms")
    private Collection<DrugBankAffectedOrganism> drugBankAffectedOrganism;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankDosage",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankDosages")
    private Collection<DrugBankDosage> drugBankDosage;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankATCCode",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankATCCodes")
    private Collection<DrugBankATCCode> drugBankATCCode;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankAHFSCode",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankAHFSCodes")
    private Collection<DrugBankAHFSCode> drugBankAHFSCode;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankFoodInteraction",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankFoodInteractions")
    private Collection<DrugBankFoodInteraction> drugBankFoodInteraction;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankDrugInteraction",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankDrugInteractions")
    private Collection<DrugBankDrugInteraction> drugBankDrugInteraction;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankProteinSequence",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankProteinSequences")
    private Collection<DrugBankProteinSequence> drugBankProteinSequence;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankCalculatedProperty",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankCalculatedProperties")
    private Collection<DrugBankCalculatedProperty> drugBankCalculatedProperty;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankExperimentalProperty",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankExperimentalProperties")
    private Collection<DrugBankExperimentalProperty> drugBankExperimentalProperty;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankExternalIdentifier",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankExternalIdentifiers")
    private Collection<DrugBankExternalIdentifier> drugBankExternalIdentifier;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankExternalLink",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankExternalLinks")
    private Collection<DrugBankExternalLink> drugBankExternalLink;
    @ElementCollection
    @CollectionTable(
            name = "DrugBankGeneralRef",
            joinColumns
            = @JoinColumn(name = "DrugBank_WID"))
    @XmlElementWrapper(name = "DrugBankGeneralRefs")
    private Collection<DrugBankGeneralRef> drugBankGeneralRef;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = DrugBankTables.DRUGBANK_HAS_DRUGBANKCATEGORIES,
            joinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "DrugBankCategory_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "DrugBankCategories")
    private Set<DrugBankCategory> drugBankCategory;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = DrugBankTables.DRUGBANK_HAS_DRUGBANKPATENTS,
            joinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "DrugBankPatent_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "DrugBankPatents")
    private Set<DrugBankPatent> drugBankPatent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    @XmlElementWrapper(name = "DrugBankTargets")
    private Set<DrugBankTarget> drugBankTarget;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    @XmlElementWrapper(name = "DrugBankEnzymes")
    private Set<DrugBankEnzyme> drugBankEnzyme;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    @XmlElementWrapper(name = "DrugBankTransporters")
    private Set<DrugBankTransporter> drugBankTransporter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    @XmlElementWrapper(name = "DrugBankCarriers")
    private Set<DrugBankCarrier> drugBankCarrier;
    // External Protein relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @XmlTransient
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANK,
            joinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> protein;
    @XmlTransient
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASENZYME,
            joinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> proteinAsEnzyme;
    @XmlTransient
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASTRANSPORTERS,
            joinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> proteinAsTransporters;
    @XmlTransient
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASCARRIERS,
            joinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> proteinAsCarriers;
    @XmlTransient
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGCOMPOUND_HAS_DRUGBANK,
            joinColumns
            = @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"))
    private Set<KEGGCompound> kEGGCompounds;

    public DrugBank() {
    }

    public DrugBank(Long wid) {
        this.wid = wid;
    }

    public DrugBank(Long wid, String id, String name, long dataSetWID) {
        this.wid = wid;
        this.id = id;
        this.name = name;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        setProtein(null);
        setProteinAsCarriers(null);
        setProteinAsEnzyme(null);
        setProteinAsTransporters(null);
        setkEGGCompounds(null);
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getcASNumber() {
        return cASNumber;
    }

    public void setcASNumber(String cASNumber) {
        this.cASNumber = cASNumber;
    }

    public String getSynthesisRef() {
        return synthesisRef;
    }

    public void setSynthesisRef(String synthesisRef) {
        this.synthesisRef = synthesisRef;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getPharmacology() {
        return pharmacology;
    }

    public void setPharmacology(String pharmacology) {
        this.pharmacology = pharmacology;
    }

    public String getMechanismOfAction() {
        return mechanismOfAction;
    }

    public void setMechanismOfAction(String mechanismOfAction) {
        this.mechanismOfAction = mechanismOfAction;
    }

    public String getToxicity() {
        return toxicity;
    }

    public void setToxicity(String toxicity) {
        this.toxicity = toxicity;
    }

    public String getBiotransformation() {
        return biotransformation;
    }

    public void setBiotransformation(String biotransformation) {
        this.biotransformation = biotransformation;
    }

    public String getAbsorption() {
        return absorption;
    }

    public void setAbsorption(String absorption) {
        this.absorption = absorption;
    }

    public String getHalfLife() {
        return halfLife;
    }

    public void setHalfLife(String halfLife) {
        this.halfLife = halfLife;
    }

    public String getProteinBinding() {
        return proteinBinding;
    }

    public void setProteinBinding(String proteinBinding) {
        this.proteinBinding = proteinBinding;
    }

    public String getRouteOfElimination() {
        return routeOfElimination;
    }

    public void setRouteOfElimination(String routeOfElimination) {
        this.routeOfElimination = routeOfElimination;
    }

    public String getVolumeOfDistribution() {
        return volumeOfDistribution;
    }

    public void setVolumeOfDistribution(String volumeOfDistribution) {
        this.volumeOfDistribution = volumeOfDistribution;
    }

    public String getClearance() {
        return clearance;
    }

    public void setClearance(String clearance) {
        this.clearance = clearance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public Collection<DrugBankSecondAccessionNumber> getDrugBankSecondAccessionNumber() {
        return drugBankSecondAccessionNumber;
    }

    public void setDrugBankSecondAccessionNumber(Collection<DrugBankSecondAccessionNumber> drugBankSecondAccessionNumber) {
        this.drugBankSecondAccessionNumber = drugBankSecondAccessionNumber;
    }

    public Collection<DrugBankGroup> getDrugBankGroup() {
        return drugBankGroup;
    }

    public void setDrugBankGroup(Collection<DrugBankGroup> drugBankGroup) {
        this.drugBankGroup = drugBankGroup;
    }

    public Collection<DrugBankTaxonomy> getDrugBankTaxonomy() {
        return drugBankTaxonomy;
    }

    public void setDrugBankTaxonomy(Collection<DrugBankTaxonomy> drugBankTaxonomy) {
        this.drugBankTaxonomy = drugBankTaxonomy;
    }

    public Collection<DrugBankTaxonomySubstructure> getDrugBankTaxonomySubstructure() {
        return drugBankTaxonomySubstructure;
    }

    public void setDrugBankTaxonomySubstructure(Collection<DrugBankTaxonomySubstructure> drugBankTaxonomySubstructure) {
        this.drugBankTaxonomySubstructure = drugBankTaxonomySubstructure;
    }

    public Collection<DrugBankSynonym> getDrugBankSynonym() {
        return drugBankSynonym;
    }

    public void setDrugBankSynonym(Collection<DrugBankSynonym> drugBankSynonym) {
        this.drugBankSynonym = drugBankSynonym;
    }

    public Collection<DrugBankBrand> getDrugBankBrand() {
        return drugBankBrand;
    }

    public void setDrugBankBrand(Collection<DrugBankBrand> drugBankBrand) {
        this.drugBankBrand = drugBankBrand;
    }

    public Collection<DrugBankMixture> getDrugBankMixture() {
        return drugBankMixture;
    }

    public void setDrugBankMixture(Collection<DrugBankMixture> drugBankMixture) {
        this.drugBankMixture = drugBankMixture;
    }

    public Collection<DrugBankPackager> getDrugBankPackager() {
        return drugBankPackager;
    }

    public void setDrugBankPackager(Collection<DrugBankPackager> drugBankPackager) {
        this.drugBankPackager = drugBankPackager;
    }

    public Collection<DrugBankManufacturer> getDrugBankManufacturer() {
        return drugBankManufacturer;
    }

    public void setDrugBankManufacturer(Collection<DrugBankManufacturer> drugBankManufacturer) {
        this.drugBankManufacturer = drugBankManufacturer;
    }

    public Collection<DrugBankPrice> getDrugBankPrice() {
        return drugBankPrice;
    }

    public void setDrugBankPrice(Collection<DrugBankPrice> drugBankPrice) {
        this.drugBankPrice = drugBankPrice;
    }

    public Collection<DrugBankAffectedOrganism> getDrugBankAffectedOrganism() {
        return drugBankAffectedOrganism;
    }

    public void setDrugBankAffectedOrganism(Collection<DrugBankAffectedOrganism> drugBankAffectedOrganism) {
        this.drugBankAffectedOrganism = drugBankAffectedOrganism;
    }

    public Collection<DrugBankDosage> getDrugBankDosage() {
        return drugBankDosage;
    }

    public void setDrugBankDosage(Collection<DrugBankDosage> drugBankDosage) {
        this.drugBankDosage = drugBankDosage;
    }

    public Collection<DrugBankATCCode> getDrugBankATCCode() {
        return drugBankATCCode;
    }

    public void setDrugBankATCCode(Collection<DrugBankATCCode> drugBankATCCode) {
        this.drugBankATCCode = drugBankATCCode;
    }

    public Collection<DrugBankAHFSCode> getDrugBankAHFSCode() {
        return drugBankAHFSCode;
    }

    public void setDrugBankAHFSCode(Collection<DrugBankAHFSCode> drugBankAHFSCode) {
        this.drugBankAHFSCode = drugBankAHFSCode;
    }

    public Collection<DrugBankFoodInteraction> getDrugBankFoodInteraction() {
        return drugBankFoodInteraction;
    }

    public void setDrugBankFoodInteraction(Collection<DrugBankFoodInteraction> drugBankFoodInteraction) {
        this.drugBankFoodInteraction = drugBankFoodInteraction;
    }

    public Collection<DrugBankDrugInteraction> getDrugBankDrugInteraction() {
        return drugBankDrugInteraction;
    }

    public void setDrugBankDrugInteraction(Collection<DrugBankDrugInteraction> drugBankDrugInteraction) {
        this.drugBankDrugInteraction = drugBankDrugInteraction;
    }

    public Collection<DrugBankProteinSequence> getDrugBankProteinSequence() {
        return drugBankProteinSequence;
    }

    public void setDrugBankProteinSequence(Collection<DrugBankProteinSequence> drugBankProteinSequence) {
        this.drugBankProteinSequence = drugBankProteinSequence;
    }

    public Collection<DrugBankCalculatedProperty> getDrugBankCalculatedProperty() {
        return drugBankCalculatedProperty;
    }

    public void setDrugBankCalculatedProperty(Collection<DrugBankCalculatedProperty> drugBankCalculatedProperty) {
        this.drugBankCalculatedProperty = drugBankCalculatedProperty;
    }

    public Collection<DrugBankExperimentalProperty> getDrugBankExperimentalProperty() {
        return drugBankExperimentalProperty;
    }

    public void setDrugBankExperimentalProperty(Collection<DrugBankExperimentalProperty> drugBankExperimentalProperty) {
        this.drugBankExperimentalProperty = drugBankExperimentalProperty;
    }

    public Collection<DrugBankExternalIdentifier> getDrugBankExternalIdentifier() {
        return drugBankExternalIdentifier;
    }

    public void setDrugBankExternalIdentifier(Collection<DrugBankExternalIdentifier> drugBankExternalIdentifier) {
        this.drugBankExternalIdentifier = drugBankExternalIdentifier;
    }

    public Collection<DrugBankExternalLink> getDrugBankExternalLink() {
        return drugBankExternalLink;
    }

    public void setDrugBankExternalLink(Collection<DrugBankExternalLink> drugBankExternalLink) {
        this.drugBankExternalLink = drugBankExternalLink;
    }

    public Collection<DrugBankGeneralRef> getDrugBankGeneralRef() {
        return drugBankGeneralRef;
    }

    public void setDrugBankGeneralRef(Collection<DrugBankGeneralRef> drugBankGeneralRef) {
        this.drugBankGeneralRef = drugBankGeneralRef;
    }

    public Set<DrugBankCategory> getDrugBankCategory() {
        return drugBankCategory;
    }

    public void setDrugBankCategory(Set<DrugBankCategory> drugBankCategory) {
        this.drugBankCategory = drugBankCategory;
    }

    public Set<DrugBankPatent> getDrugBankPatent() {
        return drugBankPatent;
    }

    public void setDrugBankPatent(Set<DrugBankPatent> drugBankPatent) {
        this.drugBankPatent = drugBankPatent;
    }

    public Set<DrugBankTarget> getDrugBankTarget() {
        return drugBankTarget;
    }

    public void setDrugBankTarget(Set<DrugBankTarget> drugBankTarget) {
        this.drugBankTarget = drugBankTarget;
    }

    public Set<DrugBankEnzyme> getDrugBankEnzyme() {
        return drugBankEnzyme;
    }

    public void setDrugBankEnzyme(Set<DrugBankEnzyme> drugBankEnzyme) {
        this.drugBankEnzyme = drugBankEnzyme;
    }

    public Set<DrugBankTransporter> getDrugBankTransporter() {
        return drugBankTransporter;
    }

    public void setDrugBankTransporter(Set<DrugBankTransporter> drugBankTransporter) {
        this.drugBankTransporter = drugBankTransporter;
    }

    public Set<DrugBankCarrier> getDrugBankCarrier() {
        return drugBankCarrier;
    }

    public void setDrugBankCarrier(Set<DrugBankCarrier> drugBankCarrier) {
        this.drugBankCarrier = drugBankCarrier;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @XmlTransient    
    public Set<Protein> getProtein() {
        return protein;
    }

    public void setProtein(Set<Protein> protein) {
        this.protein = protein;
    }

    @XmlTransient    
    public Set<Protein> getProteinAsEnzyme() {
        return proteinAsEnzyme;
    }

    public void setProteinAsEnzyme(Set<Protein> proteinAsEnzyme) {
        this.proteinAsEnzyme = proteinAsEnzyme;
    }

    @XmlTransient    
    public Set<Protein> getProteinAsTransporters() {
        return proteinAsTransporters;
    }

    public void setProteinAsTransporters(Set<Protein> proteinAsTransporters) {
        this.proteinAsTransporters = proteinAsTransporters;
    }

    @XmlTransient    
    public Set<Protein> getProteinAsCarriers() {
        return proteinAsCarriers;
    }

    public void setProteinAsCarriers(Set<Protein> proteinAsCarriers) {
        this.proteinAsCarriers = proteinAsCarriers;
    }

    @XmlTransient    
    public Set<KEGGCompound> getkEGGCompounds() {
        return kEGGCompounds;
    }

    public void setkEGGCompounds(Set<KEGGCompound> kEGGCompounds) {
        this.kEGGCompounds = kEGGCompounds;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 73 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 73 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 73 * hash + (this.cASNumber != null ? this.cASNumber.hashCode() : 0);
        hash = 73 * hash + (this.synthesisRef != null ? this.synthesisRef.hashCode() : 0);
        hash = 73 * hash + (this.indication != null ? this.indication.hashCode() : 0);
        hash = 73 * hash + (this.pharmacology != null ? this.pharmacology.hashCode() : 0);
        hash = 73 * hash + (this.mechanismOfAction != null ? this.mechanismOfAction.hashCode() : 0);
        hash = 73 * hash + (this.toxicity != null ? this.toxicity.hashCode() : 0);
        hash = 73 * hash + (this.biotransformation != null ? this.biotransformation.hashCode() : 0);
        hash = 73 * hash + (this.absorption != null ? this.absorption.hashCode() : 0);
        hash = 73 * hash + (this.halfLife != null ? this.halfLife.hashCode() : 0);
        hash = 73 * hash + (this.proteinBinding != null ? this.proteinBinding.hashCode() : 0);
        hash = 73 * hash + (this.volumeOfDistribution != null ? this.volumeOfDistribution.hashCode() : 0);
        hash = 73 * hash + (this.clearance != null ? this.clearance.hashCode() : 0);
        hash = 73 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 73 * hash + (this.version != null ? this.version.hashCode() : 0);
        hash = 73 * hash + (this.updated != null ? this.updated.hashCode() : 0);
        hash = 73 * hash + (this.created != null ? this.created.hashCode() : 0);
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
        final DrugBank other = (DrugBank) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if ((this.cASNumber == null) ? (other.cASNumber != null) : !this.cASNumber.equals(other.cASNumber)) {
            return false;
        }
        if ((this.synthesisRef == null) ? (other.synthesisRef != null) : !this.synthesisRef.equals(other.synthesisRef)) {
            return false;
        }
        if ((this.indication == null) ? (other.indication != null) : !this.indication.equals(other.indication)) {
            return false;
        }
        if ((this.pharmacology == null) ? (other.pharmacology != null) : !this.pharmacology.equals(other.pharmacology)) {
            return false;
        }
        if ((this.mechanismOfAction == null) ? (other.mechanismOfAction != null) : !this.mechanismOfAction.equals(other.mechanismOfAction)) {
            return false;
        }
        if ((this.toxicity == null) ? (other.toxicity != null) : !this.toxicity.equals(other.toxicity)) {
            return false;
        }
        if ((this.biotransformation == null) ? (other.biotransformation != null) : !this.biotransformation.equals(other.biotransformation)) {
            return false;
        }
        if ((this.absorption == null) ? (other.absorption != null) : !this.absorption.equals(other.absorption)) {
            return false;
        }
        if ((this.halfLife == null) ? (other.halfLife != null) : !this.halfLife.equals(other.halfLife)) {
            return false;
        }
        if ((this.proteinBinding == null) ? (other.proteinBinding != null) : !this.proteinBinding.equals(other.proteinBinding)) {
            return false;
        }
        if ((this.routeOfElimination == null) ? (other.routeOfElimination != null) : !this.routeOfElimination.equals(other.routeOfElimination)) {
            return false;
        }
        if ((this.volumeOfDistribution == null) ? (other.volumeOfDistribution != null) : !this.volumeOfDistribution.equals(other.volumeOfDistribution)) {
            return false;
        }
        if ((this.clearance == null) ? (other.clearance != null) : !this.clearance.equals(other.clearance)) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.version == null) ? (other.version != null) : !this.version.equals(other.version)) {
            return false;
        }
        if (this.updated != other.updated && (this.updated == null || !this.updated.equals(other.updated))) {
            return false;
        }
        if (this.created != other.created && (this.created == null || !this.created.equals(other.created))) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (this.drugBankSecondAccessionNumber != other.drugBankSecondAccessionNumber && (this.drugBankSecondAccessionNumber == null || !this.drugBankSecondAccessionNumber.equals(other.drugBankSecondAccessionNumber))) {
            return false;
        }
        if (this.drugBankGroup != other.drugBankGroup && (this.drugBankGroup == null || !this.drugBankGroup.equals(other.drugBankGroup))) {
            return false;
        }
        if (this.drugBankTaxonomy != other.drugBankTaxonomy && (this.drugBankTaxonomy == null || !this.drugBankTaxonomy.equals(other.drugBankTaxonomy))) {
            return false;
        }
        if (this.drugBankTaxonomySubstructure != other.drugBankTaxonomySubstructure && (this.drugBankTaxonomySubstructure == null || !this.drugBankTaxonomySubstructure.equals(other.drugBankTaxonomySubstructure))) {
            return false;
        }
        if (this.drugBankSynonym != other.drugBankSynonym && (this.drugBankSynonym == null || !this.drugBankSynonym.equals(other.drugBankSynonym))) {
            return false;
        }
        if (this.drugBankBrand != other.drugBankBrand && (this.drugBankBrand == null || !this.drugBankBrand.equals(other.drugBankBrand))) {
            return false;
        }
        if (this.drugBankMixture != other.drugBankMixture && (this.drugBankMixture == null || !this.drugBankMixture.equals(other.drugBankMixture))) {
            return false;
        }
        if (this.drugBankPackager != other.drugBankPackager && (this.drugBankPackager == null || !this.drugBankPackager.equals(other.drugBankPackager))) {
            return false;
        }
        if (this.drugBankManufacturer != other.drugBankManufacturer && (this.drugBankManufacturer == null || !this.drugBankManufacturer.equals(other.drugBankManufacturer))) {
            return false;
        }
        if (this.drugBankPrice != other.drugBankPrice && (this.drugBankPrice == null || !this.drugBankPrice.equals(other.drugBankPrice))) {
            return false;
        }
        if (this.drugBankAffectedOrganism != other.drugBankAffectedOrganism && (this.drugBankAffectedOrganism == null || !this.drugBankAffectedOrganism.equals(other.drugBankAffectedOrganism))) {
            return false;
        }
        if (this.drugBankDosage != other.drugBankDosage && (this.drugBankDosage == null || !this.drugBankDosage.equals(other.drugBankDosage))) {
            return false;
        }
        if (this.drugBankATCCode != other.drugBankATCCode && (this.drugBankATCCode == null || !this.drugBankATCCode.equals(other.drugBankATCCode))) {
            return false;
        }
        if (this.drugBankAHFSCode != other.drugBankAHFSCode && (this.drugBankAHFSCode == null || !this.drugBankAHFSCode.equals(other.drugBankAHFSCode))) {
            return false;
        }
        if (this.drugBankFoodInteraction != other.drugBankFoodInteraction && (this.drugBankFoodInteraction == null || !this.drugBankFoodInteraction.equals(other.drugBankFoodInteraction))) {
            return false;
        }
        if (this.drugBankDrugInteraction != other.drugBankDrugInteraction && (this.drugBankDrugInteraction == null || !this.drugBankDrugInteraction.equals(other.drugBankDrugInteraction))) {
            return false;
        }
        if (this.drugBankProteinSequence != other.drugBankProteinSequence && (this.drugBankProteinSequence == null || !this.drugBankProteinSequence.equals(other.drugBankProteinSequence))) {
            return false;
        }
        if (this.drugBankCalculatedProperty != other.drugBankCalculatedProperty && (this.drugBankCalculatedProperty == null || !this.drugBankCalculatedProperty.equals(other.drugBankCalculatedProperty))) {
            return false;
        }
        if (this.drugBankExperimentalProperty != other.drugBankExperimentalProperty && (this.drugBankExperimentalProperty == null || !this.drugBankExperimentalProperty.equals(other.drugBankExperimentalProperty))) {
            return false;
        }
        if (this.drugBankExternalIdentifier != other.drugBankExternalIdentifier && (this.drugBankExternalIdentifier == null || !this.drugBankExternalIdentifier.equals(other.drugBankExternalIdentifier))) {
            return false;
        }
        if (this.drugBankExternalLink != other.drugBankExternalLink && (this.drugBankExternalLink == null || !this.drugBankExternalLink.equals(other.drugBankExternalLink))) {
            return false;
        }
        if (this.drugBankGeneralRef != other.drugBankGeneralRef && (this.drugBankGeneralRef == null || !this.drugBankGeneralRef.equals(other.drugBankGeneralRef))) {
            return false;
        }
        if (this.drugBankCategory != other.drugBankCategory && (this.drugBankCategory == null || !this.drugBankCategory.equals(other.drugBankCategory))) {
            return false;
        }
        if (this.drugBankPatent != other.drugBankPatent && (this.drugBankPatent == null || !this.drugBankPatent.equals(other.drugBankPatent))) {
            return false;
        }
        if (this.drugBankTarget != other.drugBankTarget && (this.drugBankTarget == null || !this.drugBankTarget.equals(other.drugBankTarget))) {
            return false;
        }
        if (this.drugBankEnzyme != other.drugBankEnzyme && (this.drugBankEnzyme == null || !this.drugBankEnzyme.equals(other.drugBankEnzyme))) {
            return false;
        }
        if (this.drugBankTransporter != other.drugBankTransporter && (this.drugBankTransporter == null || !this.drugBankTransporter.equals(other.drugBankTransporter))) {
            return false;
        }
        return this.drugBankCarrier == other.drugBankCarrier || (this.drugBankCarrier != null && this.drugBankCarrier.equals(other.drugBankCarrier));
    }

    @Override
    public String toString() {
        StringBuilder pData = new StringBuilder();

        if (drugBankSecondAccessionNumber != null) {
            for (DrugBankSecondAccessionNumber p : drugBankSecondAccessionNumber) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankGroup != null) {
            for (DrugBankGroup p : drugBankGroup) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankTaxonomy != null) {
            for (DrugBankTaxonomy p : drugBankTaxonomy) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankTaxonomySubstructure != null) {
            for (DrugBankTaxonomySubstructure p : drugBankTaxonomySubstructure) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankSynonym != null) {
            for (DrugBankSynonym p : drugBankSynonym) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankBrand != null) {
            for (DrugBankBrand p : drugBankBrand) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankMixture != null) {
            for (DrugBankMixture p : drugBankMixture) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankPackager != null) {
            for (DrugBankPackager p : drugBankPackager) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankManufacturer != null) {
            for (DrugBankManufacturer p : drugBankManufacturer) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankPrice != null) {
            for (DrugBankPrice p : drugBankPrice) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankCategory != null) {
            for (DrugBankCategory p : drugBankCategory) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankAffectedOrganism != null) {
            for (DrugBankAffectedOrganism p : drugBankAffectedOrganism) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankDosage != null) {
            for (DrugBankDosage p : drugBankDosage) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankATCCode != null) {
            for (DrugBankATCCode p : drugBankATCCode) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankAHFSCode != null) {
            for (DrugBankAHFSCode p : drugBankAHFSCode) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankPatent != null) {
            for (DrugBankPatent p : drugBankPatent) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankFoodInteraction != null) {
            for (DrugBankFoodInteraction p : drugBankFoodInteraction) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankDrugInteraction != null) {
            for (DrugBankDrugInteraction p : drugBankDrugInteraction) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankProteinSequence != null) {
            for (DrugBankProteinSequence p : drugBankProteinSequence) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankCalculatedProperty != null) {
            for (DrugBankCalculatedProperty p : drugBankCalculatedProperty) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankExperimentalProperty != null) {
            for (DrugBankExperimentalProperty p : drugBankExperimentalProperty) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankExternalIdentifier != null) {
            for (DrugBankExternalIdentifier p : drugBankExternalIdentifier) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankExternalLink != null) {
            for (DrugBankExternalLink p : drugBankExternalLink) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankTarget != null) {
            for (DrugBankTarget p : drugBankTarget) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankEnzyme != null) {
            for (DrugBankEnzyme p : drugBankEnzyme) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankTransporter != null) {
            for (DrugBankTransporter p : drugBankTransporter) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankCarrier != null) {
            for (DrugBankCarrier p : drugBankCarrier) {
                pData.append("\t").append(p).append("\n");
            }
        }

        if (drugBankGeneralRef != null) {
            for (DrugBankGeneralRef p : drugBankGeneralRef) {
                pData.append("\t").append(p).append("\n");
            }
        }

        return "DrugBank{"
                + "wid=" + wid
                + ", id=" + id
                + ", name=" + name
                + ", description=" + description
                + ", cASNumber=" + cASNumber
                + ", synthesisRef=" + synthesisRef
                + ", indication=" + indication
                + ", pharmacology=" + pharmacology
                + ", mechanismOfAction=" + mechanismOfAction
                + ", toxicity=" + toxicity
                + ", biotransformation=" + biotransformation
                + ", absorption=" + absorption
                + ", halfLife=" + halfLife
                + ", proteinBinding=" + proteinBinding
                + ", routeOfElimination=" + routeOfElimination
                + ", volumeOfDistribution=" + volumeOfDistribution
                + ", clearance=" + clearance
                + ", type=" + type
                + ", version=" + version
                + ", updated=" + updated
                + ", created=" + created
                + ", dataSetWID=" + dataSetWID + "}\n"
                + pData;
    }
}
