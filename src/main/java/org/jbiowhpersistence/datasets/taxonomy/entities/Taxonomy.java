package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;

/**
 * This Class is the Taxonomy entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-09-18 14:59:00 +0200
 * (Tue, 18 Sep 2012) $ $LastChangedRevision: 396 $
 *
 * @since Jun 21, 2011
 */
@Entity
@Table(name = "Taxonomy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taxonomy.findAll", query = "SELECT t FROM Taxonomy t"),
    @NamedQuery(name = "Taxonomy.findByWid", query = "SELECT t FROM Taxonomy t WHERE t.wid = :wid"),
    @NamedQuery(name = "Taxonomy.findByTaxId", query = "SELECT t FROM Taxonomy t WHERE t.taxId = :taxId"),
    @NamedQuery(name = "Taxonomy.findByParentTaxId", query = "SELECT t FROM Taxonomy t WHERE t.parentTaxId = :parentTaxId"),
    @NamedQuery(name = "Taxonomy.findTaxIdByParentTaxIds", query = "SELECT t.taxId FROM Taxonomy t WHERE t.parentTaxId IN :parentTaxId"),
    @NamedQuery(name = "Taxonomy.findByRank", query = "SELECT t FROM Taxonomy t WHERE t.rank = :rank"),
    @NamedQuery(name = "Taxonomy.findByEMBLCode", query = "SELECT t FROM Taxonomy t WHERE t.eMBLCode = :eMBLCode"),
    @NamedQuery(name = "Taxonomy.findByTaxonomyDivisionWID", query = "SELECT t FROM Taxonomy t WHERE t.taxonomyDivisionWID = :taxonomyDivisionWID"),
    @NamedQuery(name = "Taxonomy.findByInheritedDivision", query = "SELECT t FROM Taxonomy t WHERE t.inheritedDivision = :inheritedDivision"),
    @NamedQuery(name = "Taxonomy.findByTaxonomyGenCodeWID", query = "SELECT t FROM Taxonomy t WHERE t.taxonomyGenCodeWID = :taxonomyGenCodeWID"),
    @NamedQuery(name = "Taxonomy.findByInheritedGencode", query = "SELECT t FROM Taxonomy t WHERE t.inheritedGencode = :inheritedGencode"),
    @NamedQuery(name = "Taxonomy.findByTaxonomyMCGenCodeWID", query = "SELECT t FROM Taxonomy t WHERE t.taxonomyMCGenCodeWID = :taxonomyMCGenCodeWID"),
    @NamedQuery(name = "Taxonomy.findByInheritedMCGencode", query = "SELECT t FROM Taxonomy t WHERE t.inheritedMCGencode = :inheritedMCGencode"),
    @NamedQuery(name = "Taxonomy.findByDataSetWID", query = "SELECT t FROM Taxonomy t WHERE t.dataSetWID = :dataSetWID"),
    @NamedQuery(name = "Taxonomy.findTaxonomyWIDBySynonym", query = "SELECT t.wid FROM Taxonomy t INNER JOIN t.synonym s WHERE s.synonym like :synonym group by t.wid"),
    @NamedQuery(name = "Taxonomy.findTaxonomyWIDByNameClass", query = "SELECT t.wid FROM Taxonomy t INNER JOIN t.synonym s WHERE s.synonym like :synonym and s.nameClass.nameClass like :nameClass group by t.wid")})
public class Taxonomy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "TaxId")
    private long taxId;
    @Basic(optional = false)
    @Column(name = "ParentTaxId")
    private long parentTaxId;
    @Column(name = "Rank")
    private String rank;
    @Column(name = "EMBLCode")
    private String eMBLCode;
    @Basic(optional = false)
    @Column(name = "TaxonomyDivision_WID")
    private long taxonomyDivisionWID;
    @Column(name = "InheritedDivision")
    private String inheritedDivision;
    @Basic(optional = false)
    @Column(name = "TaxonomyGenCode_WID")
    private long taxonomyGenCodeWID;
    @Column(name = "InheritedGencode")
    private String inheritedGencode;
    @Basic(optional = false)
    @Column(name = "TaxonomyMCGenCode_WID")
    private long taxonomyMCGenCodeWID;
    @Column(name = "InheritedMCGencode")
    private String inheritedMCGencode;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    /*
     * Taxonomy relationships
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSetWID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TaxonomyDivision_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private TaxonomyDivision division;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TaxonomyGenCode_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private TaxonomyGenCode gencode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TaxonomyMCGenCode_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private TaxonomyGenCode mcgencode;
    @ElementCollection
    @CollectionTable(
            name = "TaxonomyPMID",
            joinColumns
            = @JoinColumn(name = "Taxonomy_WID"))
    @XmlElementWrapper(name = "TaxonomyPMIDs")
    private Collection<TaxonomyPMID> pmid;
    @ElementCollection
    @CollectionTable(
            name = "TaxonomySynonym",
            joinColumns
            = @JoinColumn(name = "Taxonomy_WID"))
    @XmlElementWrapper(name = "TaxonomySynonyms")
    private Collection<TaxonomySynonym> synonym;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Taxonomy_has_TaxonomyUnParseCitation",
            joinColumns
            = @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "TaxonomyUnParseCitation_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "TaxonomyUnParseCitations")
    private Set<TaxonomyUnParseCitation> unparse;

    public Taxonomy() {
    }

    public Taxonomy(Long wid) {
        this.wid = wid;
    }

    public Taxonomy(Long wid, long taxId, long parentTaxId, long taxonomyDivisionWID, long taxonomyGenCodeWID, long taxonomyMCGenCodeWID, long dataSetWID) {
        this.wid = wid;
        this.taxId = taxId;
        this.parentTaxId = parentTaxId;
        this.taxonomyDivisionWID = taxonomyDivisionWID;
        this.taxonomyGenCodeWID = taxonomyGenCodeWID;
        this.taxonomyMCGenCodeWID = taxonomyMCGenCodeWID;
        this.dataSetWID = dataSetWID;
    }

    /**
     * Get the scientific name for this Taxonomy
     *
     * @return the scientific name for this Taxonomy
     */
    @Transient
    public String getTaxonomySynonym() {
        return getTaxonomySynonym("scientific name");
    }

    @Transient
    public String getTaxonomySynonym(String nameClass) {
        if (getSynonym() != null) {
            for (TaxonomySynonym syn : getSynonym()) {
                if (syn.getNameClass().getNameClass().equals(nameClass)) {
                    return syn.getSynonym();
                }
            }
        }
        return null;
    }

    public Collection<TaxonomyPMID> getPmid() {
        return pmid;
    }

    public void setPmid(Collection<TaxonomyPMID> pmid) {
        this.pmid = pmid;
    }

    public Collection<TaxonomySynonym> getSynonym() {
        return synonym;
    }

    public void setSynonym(Collection<TaxonomySynonym> synonym) {
        this.synonym = synonym;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public TaxonomyDivision getDivision() {
        return division;
    }

    public void setDivision(TaxonomyDivision division) {
        this.division = division;
    }

    public String geteMBLCode() {
        return eMBLCode;
    }

    public void seteMBLCode(String eMBLCode) {
        this.eMBLCode = eMBLCode;
    }

    public TaxonomyGenCode getGencode() {
        return gencode;
    }

    public void setGencode(TaxonomyGenCode gencode) {
        this.gencode = gencode;
    }

    public String getInheritedDivision() {
        return inheritedDivision;
    }

    public void setInheritedDivision(String inheritedDivision) {
        this.inheritedDivision = inheritedDivision;
    }

    public String getInheritedGencode() {
        return inheritedGencode;
    }

    public void setInheritedGencode(String inheritedGencode) {
        this.inheritedGencode = inheritedGencode;
    }

    public String getInheritedMCGencode() {
        return inheritedMCGencode;
    }

    public void setInheritedMCGencode(String inheritedMCGencode) {
        this.inheritedMCGencode = inheritedMCGencode;
    }

    public TaxonomyGenCode getMcgencode() {
        return mcgencode;
    }

    public void setMcgencode(TaxonomyGenCode mcgencode) {
        this.mcgencode = mcgencode;
    }

    public long getParentTaxId() {
        return parentTaxId;
    }

    public void setParentTaxId(long parentTaxId) {
        this.parentTaxId = parentTaxId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public long getTaxonomyDivisionWID() {
        return taxonomyDivisionWID;
    }

    public void setTaxonomyDivisionWID(long taxonomyDivisionWID) {
        this.taxonomyDivisionWID = taxonomyDivisionWID;
    }

    public long getTaxonomyGenCodeWID() {
        return taxonomyGenCodeWID;
    }

    public void setTaxonomyGenCodeWID(long taxonomyGenCodeWID) {
        this.taxonomyGenCodeWID = taxonomyGenCodeWID;
    }

    public long getTaxonomyMCGenCodeWID() {
        return taxonomyMCGenCodeWID;
    }

    public void setTaxonomyMCGenCodeWID(long taxonomyMCGenCodeWID) {
        this.taxonomyMCGenCodeWID = taxonomyMCGenCodeWID;
    }

    @XmlTransient
    public Set<TaxonomyUnParseCitation> getUnparse() {
        return unparse;
    }

    public void setUnparse(Set<TaxonomyUnParseCitation> unparse) {
        this.unparse = unparse;
    }

    @Id
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
        final Taxonomy other = (Taxonomy) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.taxId != other.taxId) {
            return false;
        }
        if (this.parentTaxId != other.parentTaxId) {
            return false;
        }
        if (!Objects.equals(this.rank, other.rank)) {
            return false;
        }
        if (!Objects.equals(this.eMBLCode, other.eMBLCode)) {
            return false;
        }
        if (this.taxonomyDivisionWID != other.taxonomyDivisionWID) {
            return false;
        }
        if (!Objects.equals(this.inheritedDivision, other.inheritedDivision)) {
            return false;
        }
        if (this.taxonomyGenCodeWID != other.taxonomyGenCodeWID) {
            return false;
        }
        if (!Objects.equals(this.inheritedGencode, other.inheritedGencode)) {
            return false;
        }
        if (this.taxonomyMCGenCodeWID != other.taxonomyMCGenCodeWID) {
            return false;
        }
        if (!Objects.equals(this.inheritedMCGencode, other.inheritedMCGencode)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (!Objects.equals(this.dataSet, other.dataSet)) {
            return false;
        }
        if (!Objects.equals(this.division, other.division)) {
            return false;
        }
        if (!Objects.equals(this.gencode, other.gencode)) {
            return false;
        }
        if (!Objects.equals(this.mcgencode, other.mcgencode)) {
            return false;
        }
        if (!Objects.equals(this.pmid, other.pmid)) {
            return false;
        }
        if (!Objects.equals(this.synonym, other.synonym)) {
            return false;
        }
        if (!Objects.equals(this.unparse, other.unparse)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.wid);
        return hash;
    }

    @Override
    public String toString() {
        Iterator it;
        StringBuilder synonymString = new StringBuilder();
        StringBuilder pmidString = new StringBuilder();
        StringBuilder unparseString = new StringBuilder();

        if (synonym != null) {
            it = synonym.iterator();
            while (it.hasNext()) {
                synonymString.append("\n\t\t").append(((TaxonomySynonym) it.next()).toString());
            }
        }
        if (pmid != null) {
            it = pmid.iterator();
            while (it.hasNext()) {
                pmidString.append("\n\t\t").append(((TaxonomyPMID) it.next()).toString());
            }
        }

        if (unparse != null) {
            it = unparse.iterator();
            while (it.hasNext()) {
                unparseString.append("\n\t\t").append(((TaxonomyUnParseCitation) it.next()).toString());
            }
        }

        return "Taxonomy[ wid=" + wid
                + ", taxId=" + taxId
                + ", parentTaxId=" + parentTaxId
                + ", rank=" + rank
                + ", eMBLCode=" + eMBLCode
                + ", divisionWID=" + taxonomyDivisionWID
                + ", inheritedDivision=" + inheritedDivision
                + ", gencodeWID=" + taxonomyGenCodeWID
                + ", inheritedGencode=" + inheritedGencode
                + ", mCGencodeWID=" + taxonomyMCGenCodeWID
                + ", inheritedMCGencode=" + inheritedMCGencode
                + ", dataSetWID=" + dataSetWID
                + "]"
                + "\nDivision:\n\t" + division
                + "\nGenCode:\n\t" + gencode
                + "\nMC GenCode:\n\t" + mcgencode
                + "\nSynonym:\n\t" + synonymString.toString()
                + "\nPMID:\n\t" + pmidString.toString()
                + "\nUnParsed Citations:\n\t" + unparseString.toString();
    }
}
