package org.jbiowhpersistence.datasets.gene.genebank.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genebank.GeneBankTables;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;

/**
 * This class is the GeneBankCDS entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 638 $
 *
 * @since May 2, 2013
 */
@Entity
@Table(name = "GeneBankCDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneBankCDS.findAll", query = "SELECT g FROM GeneBankCDS g"),
    @NamedQuery(name = "GeneBankCDS.findByWid", query = "SELECT g FROM GeneBankCDS g WHERE g.wid = :wid"),
    @NamedQuery(name = "GeneBankCDS.findByProteinGi", query = "SELECT g FROM GeneBankCDS g WHERE g.proteinGi = :proteinGi"),
    @NamedQuery(name = "GeneBankCDS.findByLocation", query = "SELECT g FROM GeneBankCDS g WHERE g.location = :location"),
    @NamedQuery(name = "GeneBankCDS.findByProduct", query = "SELECT g FROM GeneBankCDS g WHERE g.product = :product"),
    @NamedQuery(name = "GeneBankCDS.findByProteinId", query = "SELECT g FROM GeneBankCDS g WHERE g.proteinId = :proteinId"),
    @NamedQuery(name = "GeneBankCDS.findByGene", query = "SELECT g FROM GeneBankCDS g WHERE g.gene = :gene"),
    @NamedQuery(name = "GeneBankCDS.findByLocusTag", query = "SELECT g FROM GeneBankCDS g WHERE g.locusTag = :locusTag"),
    @NamedQuery(name = "GeneBankCDS.findByGeneBankWID", query = "SELECT g FROM GeneBankCDS g WHERE g.geneBankWID = :geneBankWID")})
public class GeneBankCDS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "ProteinGi")
    private int proteinGi;
    @Basic(optional = false)
    @Column(name = "Location")
    private String location;
    @Column(name = "Product")
    private String product;
    @Column(name = "ProteinId")
    private String proteinId;
    @Column(name = "Gene")
    private String gene;
    @Column(name = "Locus_Tag")
    private String locusTag;
    @Basic(optional = false)
    @Column(name = "GeneBank_WID")
    private long geneBankWID;
    @ElementCollection
    @CollectionTable(
            name = "GeneBankCDSDBXref",
            joinColumns
            = @JoinColumn(name = "GeneBankCDS_WID"))
    private Collection<GeneBankCDSDBXref> geneBankCDSDBXrefs;
    // Internal relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneBank_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private GeneBank geneBank;
    // External relationship
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = GeneBankTables.GENEBANKCDS_HAS_GENEINFO,
            joinColumns
            = @JoinColumn(name = "GeneBankCDS_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    private Set<GeneInfo> geneInfo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinGi", insertable = false, unique = false, nullable = true, updatable = false)
    private GenePTT genePTT;

    public GeneBankCDS() {
    }

    public GeneBankCDS(Long wid) {
        this.wid = wid;
    }

    public GeneBankCDS(Long wid, int proteinGi, String location, long geneBankWID) {
        this.wid = wid;
        this.proteinGi = proteinGi;
        this.location = location;
        this.geneBankWID = geneBankWID;
    }

    public void setRelationsToNull() {
        setGeneInfo(null);
        setGenePTT(null);
        setGeneBank(null);
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getLocusTag() {
        return locusTag;
    }

    public void setLocusTag(String locusTag) {
        this.locusTag = locusTag;
    }

    public Collection<GeneBankCDSDBXref> getGeneBankCDSDBXrefs() {
        return geneBankCDSDBXrefs;
    }

    public void setGeneBankCDSDBXrefs(Collection<GeneBankCDSDBXref> geneBankCDSDBXrefs) {
        this.geneBankCDSDBXrefs = geneBankCDSDBXrefs;
    }

    public GeneBank getGeneBank() {
        return geneBank;
    }

    public void setGeneBank(GeneBank geneBank) {
        this.geneBank = geneBank;
    }

    @XmlTransient
    public Set<GeneInfo> getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(Set<GeneInfo> geneInfo) {
        this.geneInfo = geneInfo;
    }

    public GenePTT getGenePTT() {
        return genePTT;
    }

    public void setGenePTT(GenePTT genePTT) {
        this.genePTT = genePTT;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public int getProteinGi() {
        return proteinGi;
    }

    public void setProteinGi(int proteinGi) {
        this.proteinGi = proteinGi;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProteinId() {
        return proteinId;
    }

    public void setProteinId(String proteinId) {
        this.proteinId = proteinId;
    }

    public long getGeneBankWID() {
        return geneBankWID;
    }

    public void setGeneBankWID(long geneBankWID) {
        this.geneBankWID = geneBankWID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneBankCDS)) {
            return false;
        }
        GeneBankCDS other = (GeneBankCDS) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder buider = new StringBuilder();

        if (!geneBankCDSDBXrefs.isEmpty()) {
            buider.append("\n");
            for (GeneBankCDSDBXref f : geneBankCDSDBXrefs) {
                buider.append("\t\t").append(f.toString()).append("\n");
            }
        }
        return "GeneBankCDS{" + "wid=" + wid
                + ", proteinGi=" + proteinGi
                + ", location=" + location
                + ", product=" + product
                + ", proteinId=" + proteinId
                + ", gene=" + gene
                + ", locus_tag=" + locusTag
                + ", geneBankWID=" + geneBankWID + "}"
                + buider.toString();
    }
}
