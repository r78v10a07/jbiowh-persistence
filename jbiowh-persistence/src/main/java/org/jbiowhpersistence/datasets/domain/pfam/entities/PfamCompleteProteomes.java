package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This class is the PfamCompleteProteomes entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ 
 * $LastChangedRevision: 396 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamCompleteProteomes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamCompleteProteomes.findAll", query = "SELECT p FROM PfamCompleteProteomes p"),
    @NamedQuery(name = "PfamCompleteProteomes.findByWid", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamCompleteProteomes.findBySpecies", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.species = :species"),
    @NamedQuery(name = "PfamCompleteProteomes.findByGrouping", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.grouping = :grouping"),
    @NamedQuery(name = "PfamCompleteProteomes.findByNumDistinctRegions", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.numDistinctRegions = :numDistinctRegions"),
    @NamedQuery(name = "PfamCompleteProteomes.findByNumTotalRegions", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.numTotalRegions = :numTotalRegions"),
    @NamedQuery(name = "PfamCompleteProteomes.findByNumProteins", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.numProteins = :numProteins"),
    @NamedQuery(name = "PfamCompleteProteomes.findBySequenceCoverage", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.sequenceCoverage = :sequenceCoverage"),
    @NamedQuery(name = "PfamCompleteProteomes.findByResidueCoverage", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.residueCoverage = :residueCoverage"),
    @NamedQuery(name = "PfamCompleteProteomes.findByTotalGenomeProteins", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.totalGenomeProteins = :totalGenomeProteins"),
    @NamedQuery(name = "PfamCompleteProteomes.findByTotalAaLength", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.totalAaLength = :totalAaLength"),
    @NamedQuery(name = "PfamCompleteProteomes.findByTotalAaCovered", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.totalAaCovered = :totalAaCovered"),
    @NamedQuery(name = "PfamCompleteProteomes.findByTotalSeqsCovered", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.totalSeqsCovered = :totalSeqsCovered"),
    @NamedQuery(name = "PfamCompleteProteomes.findByTaxId", query = "SELECT p FROM PfamCompleteProteomes p WHERE p.taxId = :taxId")})
public class PfamCompleteProteomes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "species")
    private String species;
    @Column(name = "grouping")
    private String grouping;
    @Basic(optional = false)
    @Column(name = "num_distinct_regions")
    private int numDistinctRegions;
    @Basic(optional = false)
    @Column(name = "num_total_regions")
    private int numTotalRegions;
    @Basic(optional = false)
    @Column(name = "num_proteins")
    private int numProteins;
    @Basic(optional = false)
    @Column(name = "sequence_coverage")
    private int sequenceCoverage;
    @Basic(optional = false)
    @Column(name = "residue_coverage")
    private int residueCoverage;
    @Basic(optional = false)
    @Column(name = "total_genome_proteins")
    private int totalGenomeProteins;
    @Basic(optional = false)
    @Column(name = "total_aa_length")
    private long totalAaLength;
    @Column(name = "total_aa_covered")
    private Integer totalAaCovered;
    @Column(name = "total_seqs_covered")
    private Integer totalSeqsCovered;
    @Basic(optional = false)
    @Column(name = "TaxId")
    private long taxId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxId", referencedColumnName = "TaxId", insertable = false, unique = false, nullable = false, updatable = false)
    private Taxonomy taxonomy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamCompleteProteomes")
    @MapKey(name = "pfamProteomeRegionsPK")
    private Map<PfamProteomeRegionsPK, PfamProteomeRegions> pfamProteomeRegions;

    public PfamCompleteProteomes() {
    }

    public PfamCompleteProteomes(Long wid) {
        this.wid = wid;
    }

    public PfamCompleteProteomes(Long wid, int numDistinctRegions, int numTotalRegions, int numProteins, int sequenceCoverage, int residueCoverage, int totalGenomeProteins, long totalAaLength, long taxId) {
        this.wid = wid;
        this.numDistinctRegions = numDistinctRegions;
        this.numTotalRegions = numTotalRegions;
        this.numProteins = numProteins;
        this.sequenceCoverage = sequenceCoverage;
        this.residueCoverage = residueCoverage;
        this.totalGenomeProteins = totalGenomeProteins;
        this.totalAaLength = totalAaLength;
        this.taxId = taxId;
    }
    
    public void setRelationsToNull() {
        setPfamProteomeRegions(null);
    }

    @XmlTransient
    public Map<PfamProteomeRegionsPK, PfamProteomeRegions> getPfamProteomeRegions() {
        return pfamProteomeRegions;
    }

    public void setPfamProteomeRegions(Map<PfamProteomeRegionsPK, PfamProteomeRegions> pfamProteomeRegions) {
        this.pfamProteomeRegions = pfamProteomeRegions;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public int getNumDistinctRegions() {
        return numDistinctRegions;
    }

    public void setNumDistinctRegions(int numDistinctRegions) {
        this.numDistinctRegions = numDistinctRegions;
    }

    public int getNumTotalRegions() {
        return numTotalRegions;
    }

    public void setNumTotalRegions(int numTotalRegions) {
        this.numTotalRegions = numTotalRegions;
    }

    public int getNumProteins() {
        return numProteins;
    }

    public void setNumProteins(int numProteins) {
        this.numProteins = numProteins;
    }

    public int getSequenceCoverage() {
        return sequenceCoverage;
    }

    public void setSequenceCoverage(int sequenceCoverage) {
        this.sequenceCoverage = sequenceCoverage;
    }

    public int getResidueCoverage() {
        return residueCoverage;
    }

    public void setResidueCoverage(int residueCoverage) {
        this.residueCoverage = residueCoverage;
    }

    public int getTotalGenomeProteins() {
        return totalGenomeProteins;
    }

    public void setTotalGenomeProteins(int totalGenomeProteins) {
        this.totalGenomeProteins = totalGenomeProteins;
    }

    public long getTotalAaLength() {
        return totalAaLength;
    }

    public void setTotalAaLength(long totalAaLength) {
        this.totalAaLength = totalAaLength;
    }

    public Integer getTotalAaCovered() {
        return totalAaCovered;
    }

    public void setTotalAaCovered(Integer totalAaCovered) {
        this.totalAaCovered = totalAaCovered;
    }

    public Integer getTotalSeqsCovered() {
        return totalSeqsCovered;
    }

    public void setTotalSeqsCovered(Integer totalSeqsCovered) {
        this.totalSeqsCovered = totalSeqsCovered;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
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
        final PfamCompleteProteomes other = (PfamCompleteProteomes) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.species, other.species)) {
            return false;
        }
        if (!Objects.equals(this.grouping, other.grouping)) {
            return false;
        }
        if (this.numDistinctRegions != other.numDistinctRegions) {
            return false;
        }
        if (this.numTotalRegions != other.numTotalRegions) {
            return false;
        }
        if (this.numProteins != other.numProteins) {
            return false;
        }
        if (this.sequenceCoverage != other.sequenceCoverage) {
            return false;
        }
        if (this.residueCoverage != other.residueCoverage) {
            return false;
        }
        if (this.totalGenomeProteins != other.totalGenomeProteins) {
            return false;
        }
        if (this.totalAaLength != other.totalAaLength) {
            return false;
        }
        if (!Objects.equals(this.totalAaCovered, other.totalAaCovered)) {
            return false;
        }
        if (!Objects.equals(this.totalSeqsCovered, other.totalSeqsCovered)) {
            return false;
        }
        if (this.taxId != other.taxId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamCompleteProteomes{" + "wid=" + wid + ", species=" + species + ", grouping=" + grouping + ", numDistinctRegions=" + numDistinctRegions + ", numTotalRegions=" + numTotalRegions + ", numProteins=" + numProteins + ", sequenceCoverage=" + sequenceCoverage + ", residueCoverage=" + residueCoverage + ", totalGenomeProteins=" + totalGenomeProteins + ", totalAaLength=" + totalAaLength + ", totalAaCovered=" + totalAaCovered + ", totalSeqsCovered=" + totalSeqsCovered + ", taxId=" + taxId + '}';
    }
}
