package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.drug.drugbank.DrugBankTables;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.protein.ProteinTables;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 * This class is the DrugBank entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Sep 15, 2011
 */
@Entity
@Table(name = "DrugBank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DrugBank.findAll", query = "SELECT d FROM DrugBank d"),
    @NamedQuery(name = "DrugBank.findByWid", query = "SELECT d FROM DrugBank d WHERE d.wid = :wid"),
    @NamedQuery(name = "DrugBank.findById", query = "SELECT d FROM DrugBank d WHERE UPPER(d.id) like :id"),
    @NamedQuery(name = "DrugBank.findByName", query = "SELECT d FROM DrugBank d WHERE UPPER(d.name) like :name"),
    @NamedQuery(name = "DrugBank.findByCASNumber", query = "SELECT d FROM DrugBank d WHERE d.cASNumber = :cASNumber"),
    @NamedQuery(name = "DrugBank.findByIndication", query = "SELECT d FROM DrugBank d WHERE UPPER(d.indication) like :indication"),
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankSecondAccessionNumbers> drugBankSecondAccessionNumbers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankGroup> drugBankGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankTaxonomy> drugBankTaxonomy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankTaxonomySubstructures> drugBankTaxonomySubstructures;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankSynonyms> drugBankSynonyms;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankBrands> drugBankBrands;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankMixtures> drugBankMixtures;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankPackagers> drugBankPackagers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankManufacturers> drugBankManufacturers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankPrices> drugBankPrices;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = DrugBankTables.DRUGBANK_HAS_DRUGBANKCATEGORIES,
    joinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "DrugBankCategories_WID", referencedColumnName = "WID"))
    private Set<DrugBankCategories> drugBankCategories;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankAffectedOrganisms> drugBankAffectedOrganisms;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankDosages> drugBankDosages;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankATCCodes> drugBankATCCodes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankAHFSCodes> drugBankAHFSCodes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = DrugBankTables.DRUGBANK_HAS_DRUGBANKPATENTS,
    joinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "DrugBankPatents_WID", referencedColumnName = "WID"))
    private Set<DrugBankPatents> drugBankPatents;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankFoodInteractions> drugBankFoodInteractions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    @MapKey(name = "drugBankDrugInteractionsPK")
    private Map<DrugBankDrugInteractionsPK, DrugBankDrugInteractions> drugBankDrugInteractions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankProteinSequences> drugBankProteinSequences;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankCalculatedProperties> drugBankCalculatedProperties;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankExperimentalProperties> drugBankExperimentalProperties;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankExternalIdentifiers> drugBankExternalIdentifiers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankExternalLinks> drugBankExternalLinks;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankTargets> drugBankTargets;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankEnzymes> drugBankEnzymes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankTransporters> drugBankTransporters;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankCarriers> drugBankCarriers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBank")
    private Set<DrugBankGeneralRef> drugBankGeneralRef;
    // External Protein relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANK,
    joinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> protein;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASENZYME,
    joinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> proteinAsEnzyme;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASTRANSPORTERS,
    joinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> proteinAsTransporters;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_DRUGBANKASCARRIERS,
    joinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> proteinAsCarriers;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGCOMPOUND_HAS_DRUGBANK,
    joinColumns =
    @JoinColumn(name = "DrugBank_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"))
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

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }
    
    public Set<KEGGCompound> getkEGGCompounds() {
        return kEGGCompounds;
    }

    public void setkEGGCompounds(Set<KEGGCompound> kEGGCompounds) {
        this.kEGGCompounds = kEGGCompounds;
    }

    public Set<DrugBankGeneralRef> getDrugBankGeneralRef() {
        return drugBankGeneralRef;
    }

    public void setDrugBankGeneralRef(Set<DrugBankGeneralRef> drugBankGeneralRef) {
        this.drugBankGeneralRef = drugBankGeneralRef;
    }

    public Set<DrugBankCarriers> getDrugBankCarriers() {
        return drugBankCarriers;
    }

    public void setDrugBankCarriers(Set<DrugBankCarriers> drugBankCarriers) {
        this.drugBankCarriers = drugBankCarriers;
    }

    public Set<DrugBankTransporters> getDrugBankTransporters() {
        return drugBankTransporters;
    }

    public void setDrugBankTransporters(Set<DrugBankTransporters> drugBankTransporters) {
        this.drugBankTransporters = drugBankTransporters;
    }

    public Set<DrugBankEnzymes> getDrugBankEnzymes() {
        return drugBankEnzymes;
    }

    public void setDrugBankEnzymes(Set<DrugBankEnzymes> drugBankEnzymes) {
        this.drugBankEnzymes = drugBankEnzymes;
    }

    public Set<DrugBankTargets> getDrugBankTargets() {
        return drugBankTargets;
    }

    public void setDrugBankTargets(Set<DrugBankTargets> drugBankTargets) {
        this.drugBankTargets = drugBankTargets;
    }

    public Set<DrugBankExternalLinks> getDrugBankExternalLinks() {
        return drugBankExternalLinks;
    }

    public void setDrugBankExternalLinks(Set<DrugBankExternalLinks> drugBankExternalLinks) {
        this.drugBankExternalLinks = drugBankExternalLinks;
    }

    public Set<DrugBankExternalIdentifiers> getDrugBankExternalIdentifiers() {
        return drugBankExternalIdentifiers;
    }

    public void setDrugBankExternalIdentifiers(Set<DrugBankExternalIdentifiers> drugBankExternalIdentifiers) {
        this.drugBankExternalIdentifiers = drugBankExternalIdentifiers;
    }

    public Set<DrugBankExperimentalProperties> getDrugBankExperimentalProperties() {
        return drugBankExperimentalProperties;
    }

    public void setDrugBankExperimentalProperties(Set<DrugBankExperimentalProperties> drugBankExperimentalProperties) {
        this.drugBankExperimentalProperties = drugBankExperimentalProperties;
    }

    public Set<DrugBankCalculatedProperties> getDrugBankCalculatedProperties() {
        return drugBankCalculatedProperties;
    }

    public void setDrugBankCalculatedProperties(Set<DrugBankCalculatedProperties> drugBankCalculatedProperties) {
        this.drugBankCalculatedProperties = drugBankCalculatedProperties;
    }

    public Set<DrugBankProteinSequences> getDrugBankProteinSequences() {
        return drugBankProteinSequences;
    }

    public void setDrugBankProteinSequences(Set<DrugBankProteinSequences> drugBankProteinSequences) {
        this.drugBankProteinSequences = drugBankProteinSequences;
    }

    public Map<DrugBankDrugInteractionsPK, DrugBankDrugInteractions> getDrugBankDrugInteractions() {
        return drugBankDrugInteractions;
    }

    public void setDrugBankDrugInteractions(Map<DrugBankDrugInteractionsPK, DrugBankDrugInteractions> drugBankDrugInteractions) {
        this.drugBankDrugInteractions = drugBankDrugInteractions;
    }

    public Set<DrugBankFoodInteractions> getDrugBankFoodInteractions() {
        return drugBankFoodInteractions;
    }

    public void setDrugBankFoodInteractions(Set<DrugBankFoodInteractions> drugBankFoodInteractions) {
        this.drugBankFoodInteractions = drugBankFoodInteractions;
    }

    public Set<DrugBankPatents> getDrugBankPatents() {
        return drugBankPatents;
    }

    public void setDrugBankPatents(Set<DrugBankPatents> drugBankPatents) {
        this.drugBankPatents = drugBankPatents;
    }

    public Set<DrugBankAHFSCodes> getDrugBankAHFSCodes() {
        return drugBankAHFSCodes;
    }

    public void setDrugBankAHFSCodes(Set<DrugBankAHFSCodes> drugBankAHFSCodes) {
        this.drugBankAHFSCodes = drugBankAHFSCodes;
    }

    public Set<DrugBankATCCodes> getDrugBankATCCodes() {
        return drugBankATCCodes;
    }

    public void setDrugBankATCCodes(Set<DrugBankATCCodes> drugBankATCCodes) {
        this.drugBankATCCodes = drugBankATCCodes;
    }

    public Set<DrugBankDosages> getDrugBankDosages() {
        return drugBankDosages;
    }

    public void setDrugBankDosages(Set<DrugBankDosages> drugBankDosages) {
        this.drugBankDosages = drugBankDosages;
    }

    public Set<DrugBankAffectedOrganisms> getDrugBankAffectedOrganisms() {
        return drugBankAffectedOrganisms;
    }

    public void setDrugBankAffectedOrganisms(Set<DrugBankAffectedOrganisms> drugBankAffectedOrganisms) {
        this.drugBankAffectedOrganisms = drugBankAffectedOrganisms;
    }

    public Set<DrugBankCategories> getDrugBankCategories() {
        return drugBankCategories;
    }

    public void setDrugBankCategories(Set<DrugBankCategories> drugBankCategories) {
        this.drugBankCategories = drugBankCategories;
    }

    public Set<DrugBankPrices> getDrugBankPrices() {
        return drugBankPrices;
    }

    public void setDrugBankPrices(Set<DrugBankPrices> drugBankPrices) {
        this.drugBankPrices = drugBankPrices;
    }

    public Set<DrugBankManufacturers> getDrugBankManufacturers() {
        return drugBankManufacturers;
    }

    public void setDrugBankManufacturers(Set<DrugBankManufacturers> drugBankManufacturers) {
        this.drugBankManufacturers = drugBankManufacturers;
    }

    public Set<DrugBankPackagers> getDrugBankPackagers() {
        return drugBankPackagers;
    }

    public void setDrugBankPackagers(Set<DrugBankPackagers> drugBankPackagers) {
        this.drugBankPackagers = drugBankPackagers;
    }

    public Set<DrugBankMixtures> getDrugBankMixtures() {
        return drugBankMixtures;
    }

    public void setDrugBankMixtures(Set<DrugBankMixtures> drugBankMixtures) {
        this.drugBankMixtures = drugBankMixtures;
    }

    public Set<DrugBankBrands> getDrugBankBrands() {
        return drugBankBrands;
    }

    public void setDrugBankBrands(Set<DrugBankBrands> drugBankBrands) {
        this.drugBankBrands = drugBankBrands;
    }

    public Set<DrugBankSynonyms> getDrugBankSynonyms() {
        return drugBankSynonyms;
    }

    public void setDrugBankSynonyms(Set<DrugBankSynonyms> drugBankSynonyms) {
        this.drugBankSynonyms = drugBankSynonyms;
    }

    public Set<DrugBankTaxonomySubstructures> getDrugBankTaxonomySubstructures() {
        return drugBankTaxonomySubstructures;
    }

    public void setDrugBankTaxonomySubstructures(Set<DrugBankTaxonomySubstructures> drugBankTaxonomySubstructures) {
        this.drugBankTaxonomySubstructures = drugBankTaxonomySubstructures;
    }

    public Set<DrugBankTaxonomy> getDrugBankTaxonomy() {
        return drugBankTaxonomy;
    }

    public void setDrugBankTaxonomy(Set<DrugBankTaxonomy> drugBankTaxonomy) {
        this.drugBankTaxonomy = drugBankTaxonomy;
    }

    public Set<DrugBankGroup> getDrugBankGroup() {
        return drugBankGroup;
    }

    public void setDrugBankGroup(Set<DrugBankGroup> drugBankGroup) {
        this.drugBankGroup = drugBankGroup;
    }

    public Set<DrugBankSecondAccessionNumbers> getDrugBankSecondAccessionNumbers() {
        return drugBankSecondAccessionNumbers;
    }

    public void setDrugBankSecondAccessionNumbers(Set<DrugBankSecondAccessionNumbers> drugBankSecondAccessionNumbers) {
        this.drugBankSecondAccessionNumbers = drugBankSecondAccessionNumbers;
    }

    public String getcASNumber() {
        return cASNumber;
    }

    public void setcASNumber(String cASNumber) {
        this.cASNumber = cASNumber;
    }

    public Set<Protein> getProteinAsCarriers() {
        return proteinAsCarriers;
    }

    public void setProteinAsCarriers(Set<Protein> proteinAsCarriers) {
        this.proteinAsCarriers = proteinAsCarriers;
    }

    public Set<Protein> getProteinAsEnzyme() {
        return proteinAsEnzyme;
    }

    public void setProteinAsEnzyme(Set<Protein> proteinAsEnzyme) {
        this.proteinAsEnzyme = proteinAsEnzyme;
    }

    public Set<Protein> getProteinAsTransporters() {
        return proteinAsTransporters;
    }

    public void setProteinAsTransporters(Set<Protein> proteinAsTransporters) {
        this.proteinAsTransporters = proteinAsTransporters;
    }

    public Set<Protein> getProtein() {
        return protein;
    }

    public void setProtein(Set<Protein> protein) {
        this.protein = protein;
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

    public String getCASNumber() {
        return cASNumber;
    }

    public void setCASNumber(String cASNumber) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugBank other = (DrugBank) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.cASNumber, other.cASNumber)) {
            return false;
        }
        if (!Objects.equals(this.synthesisRef, other.synthesisRef)) {
            return false;
        }
        if (!Objects.equals(this.indication, other.indication)) {
            return false;
        }
        if (!Objects.equals(this.pharmacology, other.pharmacology)) {
            return false;
        }
        if (!Objects.equals(this.mechanismOfAction, other.mechanismOfAction)) {
            return false;
        }
        if (!Objects.equals(this.toxicity, other.toxicity)) {
            return false;
        }
        if (!Objects.equals(this.biotransformation, other.biotransformation)) {
            return false;
        }
        if (!Objects.equals(this.absorption, other.absorption)) {
            return false;
        }
        if (!Objects.equals(this.halfLife, other.halfLife)) {
            return false;
        }
        if (!Objects.equals(this.proteinBinding, other.proteinBinding)) {
            return false;
        }
        if (!Objects.equals(this.routeOfElimination, other.routeOfElimination)) {
            return false;
        }
        if (!Objects.equals(this.volumeOfDistribution, other.volumeOfDistribution)) {
            return false;
        }
        if (!Objects.equals(this.clearance, other.clearance)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.updated, other.updated)) {
            return false;
        }
        if (!Objects.equals(this.created, other.created)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (!Objects.equals(this.drugBankSecondAccessionNumbers, other.drugBankSecondAccessionNumbers)) {
            return false;
        }
        if (!Objects.equals(this.drugBankGroup, other.drugBankGroup)) {
            return false;
        }
        if (!Objects.equals(this.drugBankTaxonomy, other.drugBankTaxonomy)) {
            return false;
        }
        if (!Objects.equals(this.drugBankTaxonomySubstructures, other.drugBankTaxonomySubstructures)) {
            return false;
        }
        if (!Objects.equals(this.drugBankSynonyms, other.drugBankSynonyms)) {
            return false;
        }
        if (!Objects.equals(this.drugBankBrands, other.drugBankBrands)) {
            return false;
        }
        if (!Objects.equals(this.drugBankMixtures, other.drugBankMixtures)) {
            return false;
        }
        if (!Objects.equals(this.drugBankPackagers, other.drugBankPackagers)) {
            return false;
        }
        if (!Objects.equals(this.drugBankManufacturers, other.drugBankManufacturers)) {
            return false;
        }
        if (!Objects.equals(this.drugBankPrices, other.drugBankPrices)) {
            return false;
        }
        if (!Objects.equals(this.drugBankCategories, other.drugBankCategories)) {
            return false;
        }
        if (!Objects.equals(this.drugBankAffectedOrganisms, other.drugBankAffectedOrganisms)) {
            return false;
        }
        if (!Objects.equals(this.drugBankDosages, other.drugBankDosages)) {
            return false;
        }
        if (!Objects.equals(this.drugBankATCCodes, other.drugBankATCCodes)) {
            return false;
        }
        if (!Objects.equals(this.drugBankAHFSCodes, other.drugBankAHFSCodes)) {
            return false;
        }
        if (!Objects.equals(this.drugBankPatents, other.drugBankPatents)) {
            return false;
        }
        if (!Objects.equals(this.drugBankFoodInteractions, other.drugBankFoodInteractions)) {
            return false;
        }
        if (!Objects.equals(this.drugBankDrugInteractions, other.drugBankDrugInteractions)) {
            return false;
        }
        if (!Objects.equals(this.drugBankProteinSequences, other.drugBankProteinSequences)) {
            return false;
        }
        if (!Objects.equals(this.drugBankCalculatedProperties, other.drugBankCalculatedProperties)) {
            return false;
        }
        if (!Objects.equals(this.drugBankExperimentalProperties, other.drugBankExperimentalProperties)) {
            return false;
        }
        if (!Objects.equals(this.drugBankExternalIdentifiers, other.drugBankExternalIdentifiers)) {
            return false;
        }
        if (!Objects.equals(this.drugBankExternalLinks, other.drugBankExternalLinks)) {
            return false;
        }
        if (!Objects.equals(this.drugBankTargets, other.drugBankTargets)) {
            return false;
        }
        if (!Objects.equals(this.drugBankEnzymes, other.drugBankEnzymes)) {
            return false;
        }
        if (!Objects.equals(this.drugBankTransporters, other.drugBankTransporters)) {
            return false;
        }
        if (!Objects.equals(this.drugBankCarriers, other.drugBankCarriers)) {
            return false;
        }
        if (!Objects.equals(this.drugBankGeneralRef, other.drugBankGeneralRef)) {
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

        if (!getDrugBankSecondAccessionNumbers().isEmpty()) {
            it = getDrugBankSecondAccessionNumbers().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankGroup().isEmpty()) {
            it = getDrugBankGroup().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankTaxonomy().isEmpty()) {
            it = getDrugBankTaxonomy().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankTaxonomySubstructures().isEmpty()) {
            it = getDrugBankTaxonomySubstructures().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankSynonyms().isEmpty()) {
            it = getDrugBankSynonyms().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankBrands().isEmpty()) {
            it = getDrugBankBrands().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankMixtures().isEmpty()) {
            it = getDrugBankMixtures().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankPackagers().isEmpty()) {
            it = getDrugBankPackagers().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankManufacturers().isEmpty()) {
            it = getDrugBankManufacturers().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankPrices().isEmpty()) {
            it = getDrugBankPrices().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankCategories().isEmpty()) {
            it = getDrugBankCategories().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankAffectedOrganisms().isEmpty()) {
            it = getDrugBankAffectedOrganisms().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankDosages().isEmpty()) {
            it = getDrugBankDosages().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankATCCodes().isEmpty()) {
            it = getDrugBankATCCodes().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankAHFSCodes().isEmpty()) {
            it = getDrugBankAHFSCodes().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankPatents().isEmpty()) {
            it = getDrugBankPatents().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankFoodInteractions().isEmpty()) {
            it = getDrugBankFoodInteractions().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankDrugInteractions().isEmpty()) {
            it = getDrugBankDrugInteractions().values().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankProteinSequences().isEmpty()) {
            it = getDrugBankProteinSequences().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankCalculatedProperties().isEmpty()) {
            it = getDrugBankCalculatedProperties().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankExperimentalProperties().isEmpty()) {
            it = getDrugBankExperimentalProperties().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankExternalIdentifiers().isEmpty()) {
            it = getDrugBankExternalIdentifiers().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankExternalLinks().isEmpty()) {
            it = getDrugBankExternalLinks().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankTargets().isEmpty()) {
            it = getDrugBankTargets().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankEnzymes().isEmpty()) {
            it = getDrugBankEnzymes().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankTransporters().isEmpty()) {
            it = getDrugBankTransporters().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankCarriers().isEmpty()) {
            it = getDrugBankCarriers().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }

        if (!getDrugBankGeneralRef().isEmpty()) {
            it = getDrugBankGeneralRef().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
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
