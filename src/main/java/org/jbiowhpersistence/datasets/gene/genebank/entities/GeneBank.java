package org.jbiowhpersistence.datasets.gene.genebank.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This class is the GeneBank entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-07-26 09:54:20 +0200 (Fri, 26 Jul 2013) $ $LastChangedRevision: 638 $
 *
 * @since May 2, 2013
 */
@Entity
@Table(name = "GeneBank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneBank.findAll", query = "SELECT g FROM GeneBank g"),
    @NamedQuery(name = "GeneBank.findByWid", query = "SELECT g FROM GeneBank g WHERE g.wid = :wid"),
    @NamedQuery(name = "GeneBank.findByLocusName", query = "SELECT g FROM GeneBank g WHERE g.locusName = :locusName"),
    @NamedQuery(name = "GeneBank.findBySeqLengh", query = "SELECT g FROM GeneBank g WHERE g.seqLengh = :seqLengh"),
    @NamedQuery(name = "GeneBank.findByMolType", query = "SELECT g FROM GeneBank g WHERE g.molType = :molType"),
    @NamedQuery(name = "GeneBank.findByDivision", query = "SELECT g FROM GeneBank g WHERE g.division = :division"),
    @NamedQuery(name = "GeneBank.findByModDate", query = "SELECT g FROM GeneBank g WHERE g.modDate = :modDate"),
    @NamedQuery(name = "GeneBank.findByDefinition", query = "SELECT g FROM GeneBank g WHERE g.definition like :definition"),
    @NamedQuery(name = "GeneBank.findByVersion", query = "SELECT g FROM GeneBank g WHERE g.version = :version"),
    @NamedQuery(name = "GeneBank.findByGi", query = "SELECT g FROM GeneBank g WHERE g.gi = :gi"),
    @NamedQuery(name = "GeneBank.findByLocation", query = "SELECT g FROM GeneBank g WHERE g.location = :location"),
    @NamedQuery(name = "GeneBank.findByTaxId", query = "SELECT g FROM GeneBank g WHERE g.taxId = :taxId"),
    @NamedQuery(name = "GeneBank.findByDataSetWID", query = "SELECT g FROM GeneBank g WHERE g.dataSetWID = :dataSetWID"),
     @NamedQuery(name = "GeneBank.countByFileName", query = "SELECT COUNT(g) FROM GeneBank g WHERE g.fileName = :fileName"),
    @NamedQuery(name = "GeneBank.findCDSbyGiLocation", query = "SELECT c FROM GeneBank g INNER JOIN g.geneBankCDSs c WHERE g.gi = :gi AND c.location REGEXP :location"),
    @NamedQuery(name = "GeneBank.findFeaturebyGiLocation", query = "SELECT c FROM GeneBank g INNER JOIN g.geneBankFeatureses c WHERE g.gi = :gi AND c.location REGEXP :location AND c.keyName != 'gene'" )})
public class GeneBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "LocusName")
    private String locusName;
    @Basic(optional = false)
    @Column(name = "SeqLengh")
    private int seqLengh;
    @Basic(optional = false)
    @Column(name = "MolType")
    private String molType;
    @Basic(optional = false)
    @Column(name = "Division")
    private String division;
    @Basic(optional = false)
    @Column(name = "ModDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modDate;
    @Lob
    @Column(name = "Definition")
    private String definition;
    @Column(name = "Version")
    private String version;
    @Basic(optional = false)
    @Column(name = "Gi")
    private int gi;
    @Column(name = "Location")
    private String location;
    @Basic(optional = false)
    @Column(name = "TaxId")
    private int taxId;
    @Column(name = "Source")
    private String source;
    @Column(name = "Organism")
    private String organism;
    @Column(name = "Seq")
    private String seq;
    @Column(name = "FileName")
    private String fileName;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    @ElementCollection
    @CollectionTable(
            name = "GeneBankFeatures",
            joinColumns =
            @JoinColumn(name = "GeneBank_WID"))
    private Collection<GeneBankFeatures> geneBankFeatureses;
    @ElementCollection
    @CollectionTable(
            name = "GeneBankAccession",
            joinColumns =
            @JoinColumn(name = "GeneBank_WID"))
    private Collection<GeneBankAccession> geneBankAccessions;
    // Internal relationship
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneBank")
    private Set<GeneBankCDS> geneBankCDSs;
    // External relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxId", referencedColumnName = "TaxId", insertable = false, unique = false, nullable = false, updatable = false)
    private Taxonomy taxonomy;

    public GeneBank() {
    }

    public GeneBank(Long wid) {
        this.wid = wid;
    }

    public GeneBank(Long wid, String locusName, int seqLengh, String molType, String division, Date modDate, int gi, String location, int taxId, long dataSetWID) {
        this.wid = wid;
        this.locusName = locusName;
        this.seqLengh = seqLengh;
        this.molType = molType;
        this.division = division;
        this.modDate = modDate;
        this.gi = gi;
        this.location = location;
        this.taxId = taxId;
        this.dataSetWID = dataSetWID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Collection<GeneBankAccession> getGeneBankAccessions() {
        return geneBankAccessions;
    }

    public void setGeneBankAccessions(Collection<GeneBankAccession> geneBankAccessions) {
        this.geneBankAccessions = geneBankAccessions;
    }

    public Collection<GeneBankFeatures> getGeneBankFeatureses() {
        return geneBankFeatureses;
    }

    public void setGeneBankFeatureses(Collection<GeneBankFeatures> geneBankFeatureses) {
        this.geneBankFeatureses = geneBankFeatureses;
    }

    @XmlTransient
    public Set<GeneBankCDS> getGeneBankCDSs() {
        return geneBankCDSs;
    }

    public void setGeneBankCDSs(Set<GeneBankCDS> geneBankCDSs) {
        this.geneBankCDSs = geneBankCDSs;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getLocusName() {
        return locusName;
    }

    public void setLocusName(String locusName) {
        this.locusName = locusName;
    }

    public int getSeqLengh() {
        return seqLengh;
    }

    public void setSeqLengh(int seqLengh) {
        this.seqLengh = seqLengh;
    }

    public String getMolType() {
        return molType;
    }

    public void setMolType(String molType) {
        this.molType = molType;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getGi() {
        return gi;
    }

    public void setGi(int gi) {
        this.gi = gi;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
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
        if (!(object instanceof GeneBank)) {
            return false;
        }
        GeneBank other = (GeneBank) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder buider = new StringBuilder();

        if (!geneBankFeatureses.isEmpty()) {
            buider.append("\n");
            for (GeneBankFeatures f : geneBankFeatureses) {
                buider.append("\t").append(f.toString()).append("\n");
            }
        }
        if (!geneBankCDSs.isEmpty()) {
            buider.append("\n");
            for (GeneBankCDS f : geneBankCDSs) {
                buider.append("\t").append(f.toString()).append("\n");
            }
        }
        if (!geneBankAccessions.isEmpty()) {
            buider.append("\n");
            for (GeneBankAccession f : geneBankAccessions) {
                buider.append("\t").append(f.toString()).append("\n");
            }
        }
        return "GeneBank{" + "wid=" + wid
                + ", locusName=" + locusName
                + ", seqLengh=" + seqLengh
                + ", molType=" + molType
                + ", division=" + division
                + ", modDate=" + modDate
                + ", definition=" + definition
                + ", version=" + version
                + ", gi=" + gi
                + ", location=" + location
                + ", taxId=" + taxId
                + ", source=" + source
                + ", organism=" + organism
                + ", seq=" + seq
                + ", fileName=" + fileName
                + ", dataSetWID=" + dataSetWID + "}"
                + buider.toString();
    }
}
