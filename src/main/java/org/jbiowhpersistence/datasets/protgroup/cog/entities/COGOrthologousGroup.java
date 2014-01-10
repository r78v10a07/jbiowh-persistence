package org.jbiowhpersistence.datasets.protgroup.cog.entities;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protgroup.cog.COGTables;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This class is the COGOrthologousGroup entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 19, 2013
 */
@Entity
@Table(name = "COGOrthologousGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "COGOrthologousGroup.findAll", query = "SELECT c FROM COGOrthologousGroup c"),
    @NamedQuery(name = "COGOrthologousGroup.findByWid", query = "SELECT c FROM COGOrthologousGroup c WHERE c.wid = :wid"),
    @NamedQuery(name = "COGOrthologousGroup.findById", query = "SELECT c FROM COGOrthologousGroup c WHERE c.id = :id"),
    @NamedQuery(name = "COGOrthologousGroup.findByGroupFunction", query = "SELECT c FROM COGOrthologousGroup c WHERE c.groupFunction = :groupFunction"),
    @NamedQuery(name = "COGOrthologousGroup.findByDataSetWID", query = "SELECT c FROM COGOrthologousGroup c WHERE c.dataSetWID = :dataSetWID")})
public class COGOrthologousGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;
    @Column(name = "GroupFunction")
    private String groupFunction;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    // Internla relations
    @ElementCollection
    @CollectionTable(
            name = "COGMember",
            joinColumns
            = @JoinColumn(name = "COGOrthologousGroup_WID"))
    @XmlElementWrapper(name = "COGMembers")
    private Collection<COGMember> cogMember;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = COGTables.COGORTHOLOGOUSGROUP_HAS_COGFUNCCLASS,
            joinColumns
            = @JoinColumn(name = "COGOrthologousGroup_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "COGFuncClass_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "COGFuncClasses")
    private Set<COGFuncClass> cogFuncClass;
    // External relations
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = COGTables.COGORTHOLOGOUSGROUP_HAS_TAXONOMY,
            joinColumns
            = @JoinColumn(name = "COGOrthologousGroup_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Taxonomy_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "Taxonomies")
    private Set<Taxonomy> taxonomy;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = COGTables.COGORTHOLOGOUSGROUP_HAS_GENEINFO,
            joinColumns
            = @JoinColumn(name = "COGOrthologousGroup_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "GeneInfos")
    private Collection<GeneInfo> geneInfo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = COGTables.COGORTHOLOGOUSGROUP_HAS_PROTEIN,
            joinColumns
            = @JoinColumn(name = "COGOrthologousGroup_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "Proteins")
    private Set<Protein> protein;

    public COGOrthologousGroup() {
    }

    public COGOrthologousGroup(Long wid) {
        this.wid = wid;
    }

    public COGOrthologousGroup(Long wid, String id, long dataSetWID) {
        this.wid = wid;
        this.id = id;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        setGeneInfo(null);
        setProtein(null);
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

    public String getGroupFunction() {
        return groupFunction;
    }

    public void setGroupFunction(String groupFunction) {
        this.groupFunction = groupFunction;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public Collection<COGMember> getCogMember() {
        return cogMember;
    }

    public void setCogMember(Collection<COGMember> cogMember) {
        this.cogMember = cogMember;
    }

    public Set<COGFuncClass> getCogFuncClass() {
        return cogFuncClass;
    }

    public void setCogFuncClass(Set<COGFuncClass> cogFuncClass) {
        this.cogFuncClass = cogFuncClass;
    }

    @XmlTransient
    public Set<Taxonomy> getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Set<Taxonomy> taxonomy) {
        this.taxonomy = taxonomy;
    }

    @XmlTransient
    public Collection<GeneInfo> getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(Collection<GeneInfo> geneInfo) {
        this.geneInfo = geneInfo;
    }

    @XmlTransient
    public Set<Protein> getProtein() {
        return protein;
    }

    public void setProtein(Set<Protein> protein) {
        this.protein = protein;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 89 * hash + (this.groupFunction != null ? this.groupFunction.hashCode() : 0);
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
        final COGOrthologousGroup other = (COGOrthologousGroup) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return !((this.groupFunction == null) ? (other.groupFunction != null) : !this.groupFunction.equals(other.groupFunction));
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();

        if (cogMember != null) {
            for (COGMember m : cogMember) {
                b.append("\t").append(m).append("\n");
            }
        }

        if (cogFuncClass != null) {
            for (COGFuncClass c : cogFuncClass) {
                b.append("\t\t").append(c).append("\n");
                if (c.getCogFuncClassGroup() != null) {
                    b.append("\t\t\t").append(c.getCogFuncClassGroup().getName()).append("\n");
                }
            }
        }

        return "COGOrthologousGroup{"
                + "wid=" + wid
                + ", id=" + id
                + ", groupFunction=" + groupFunction
                + ", dataSetWID=" + dataSetWID + "}\n"
                + b.toString();
    }

}
