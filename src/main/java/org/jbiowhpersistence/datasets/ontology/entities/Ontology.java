package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.protein.ProteinTables;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 * This Class is the Ontology Entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jun 28, 2011
 */
@Entity
@Table(name = "Ontology")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ontology.findAll", query = "SELECT o FROM Ontology o"),
    @NamedQuery(name = "Ontology.findByWid", query = "SELECT o FROM Ontology o WHERE o.wid = :wid"),
    @NamedQuery(name = "Ontology.findById", query = "SELECT o FROM Ontology o WHERE o.id like :id"),
    @NamedQuery(name = "Ontology.findByName", query = "SELECT o FROM Ontology o WHERE o.name like :name"),
    @NamedQuery(name = "Ontology.findByNameSpace", query = "SELECT o FROM Ontology o WHERE o.nameSpace = :nameSpace"),
    @NamedQuery(name = "Ontology.findByDef", query = "SELECT o FROM Ontology o WHERE o.def like :def"),
    @NamedQuery(name = "Ontology.findByComment", query = "SELECT o FROM Ontology o WHERE o.comment like :comment"),
    @NamedQuery(name = "Ontology.findByIsObsolete", query = "SELECT o FROM Ontology o WHERE o.isObsolete = :isObsolete"),
    @NamedQuery(name = "Ontology.findByDataSetWID", query = "SELECT o FROM Ontology o WHERE o.dataSetWID = :dataSetWID")})
public class Ontology implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "NameSpace")
    private String nameSpace;
    @Column(name = "Def")
    private String def;
    @Column(name = "Comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "IsObsolete")
    private boolean isObsolete;
    @Basic(optional = false)
    @Column(name = "DataSetWID")
    private long dataSetWID;
    /*
     * Ontology relationships
     */
    @ElementCollection
    @CollectionTable(
            name = "OntologyAlternativeId",
            joinColumns
            = @JoinColumn(name = "Ontology_WID"))
    @XmlElementWrapper(name = "OntologyAlternativeIds")
    private Collection<OntologyAlternativeId> ontologyAlternativeId;
    @ElementCollection
    @CollectionTable(
            name = "OntologyIsA",
            joinColumns
            = @JoinColumn(name = "Ontology_WID"))
    @XmlElementWrapper(name = "OntologyIsAs")
    private Collection<OntologyIsA> ontologyIsA;
    @ElementCollection
    @CollectionTable(
            name = "OntologyPMID",
            joinColumns
            = @JoinColumn(name = "Ontology_WID"))
    @XmlElementWrapper(name = "OntologyPMIDs")
    private Collection<OntologyPMID> ontologyPMID;
    @ElementCollection
    @CollectionTable(
            name = "OntologyRelation",
            joinColumns
            = @JoinColumn(name = "Ontology_WID"))
    @XmlElementWrapper(name = "OntologyRelations")
    private Collection<OntologyRelation> ontologyRelation;
    @ElementCollection
    @CollectionTable(
            name = "OntologyToConsider",
            joinColumns
            = @JoinColumn(name = "Ontology_WID"))
    @XmlElementWrapper(name = "OntologyToConsiders")
    private Collection<OntologyToConsider> ontologyToConsider;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Ontology_has_OntologySubset",
            joinColumns
            = @JoinColumn(name = "Ontology_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "OntologySubset_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "OntologySubsets")
    private Set<OntologySubset> ontologySubset; 
    //@XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ontology")
    @MapKey(name = "ontologyhasOntologySynonymPK")    
    private Map<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym> ontologyhasOntologySynonym;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Ontology_has_OntologyXRef",
            joinColumns
            = @JoinColumn(name = "Ontology_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "OntologyXRef_WID", referencedColumnName = "WID"))
    @XmlElementWrapper(name = "OntologyXRefs")
    private Set<OntologyXRef> ontologyXRef;
    // External Gene Relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSetWID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "ontology")
    @XmlElementWrapper(name = "proteins")
    @XmlTransient
    private Set<Protein> protein;    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "ontology")
    @XmlElementWrapper(name = "geneinfoes")
    @XmlTransient
    private Collection<GeneInfo> geneInfo;

    public Ontology() {
    }

    public Ontology(Long wid) {
        this.wid = wid;
    }

    public Ontology(Long wid, String id, String name, String nameSpace, boolean isObsolete, long dataSetWID) {
        this.wid = wid;
        this.id = id;
        this.name = name;
        this.nameSpace = nameSpace;
        this.isObsolete = isObsolete;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        setProtein(null);
        setGeneInfo(null);
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

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public Collection<OntologyAlternativeId> getOntologyAlternativeId() {
        return ontologyAlternativeId;
    }

    public void setOntologyAlternativeId(Collection<OntologyAlternativeId> ontologyAlternativeId) {
        this.ontologyAlternativeId = ontologyAlternativeId;
    }

    public Collection<OntologyIsA> getOntologyIsA() {
        return ontologyIsA;
    }

    public void setOntologyIsA(Collection<OntologyIsA> ontologyIsA) {
        this.ontologyIsA = ontologyIsA;
    }

    public Collection<OntologyPMID> getOntologyPMID() {
        return ontologyPMID;
    }

    public void setOntologyPMID(Collection<OntologyPMID> ontologyPMID) {
        this.ontologyPMID = ontologyPMID;
    }

    public Collection<OntologyRelation> getOntologyRelation() {
        return ontologyRelation;
    }

    public void setOntologyRelation(Collection<OntologyRelation> ontologyRelation) {
        this.ontologyRelation = ontologyRelation;
    }

    public Collection<OntologyToConsider> getOntologyToConsider() {
        return ontologyToConsider;
    }

    public void setOntologyToConsider(Collection<OntologyToConsider> ontologyToConsider) {
        this.ontologyToConsider = ontologyToConsider;
    }

    public Set<OntologySubset> getOntologySubset() {
        return ontologySubset;
    }

    public void setOntologySubset(Set<OntologySubset> ontologySubset) {
        this.ontologySubset = ontologySubset;
    }

    //@XmlTransient
    public Map<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym> getOntologyhasOntologySynonym() {
        return ontologyhasOntologySynonym;
    }

    public void setOntologyhasOntologySynonym(Map<OntologyhasOntologySynonymPK, OntologyhasOntologySynonym> ontologyhasOntologySynonym) {
        this.ontologyhasOntologySynonym = ontologyhasOntologySynonym;
    }

    public Set<OntologyXRef> getOntologyXRef() {
        return ontologyXRef;
    }

    public void setOntologyXRef(Set<OntologyXRef> ontologyXRef) {
        this.ontologyXRef = ontologyXRef;
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
    public Collection<GeneInfo> getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(Collection<GeneInfo> geneInfo) {
        this.geneInfo = geneInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ontology other = (Ontology) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.nameSpace, other.nameSpace)) {
            return false;
        }
        if (!Objects.equals(this.def, other.def)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (this.isObsolete != other.isObsolete) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (!Objects.equals(this.ontologyAlternativeId, other.ontologyAlternativeId)) {
            return false;
        }
        if (!Objects.equals(this.ontologyIsA, other.ontologyIsA)) {
            return false;
        }
        if (!Objects.equals(this.ontologyPMID, other.ontologyPMID)) {
            return false;
        }
        if (!Objects.equals(this.ontologyRelation, other.ontologyRelation)) {
            return false;
        }
        if (!Objects.equals(this.ontologySubset, other.ontologySubset)) {
            return false;
        }
        if (!Objects.equals(this.ontologyhasOntologySynonym, other.ontologyhasOntologySynonym)) {
            return false;
        }
        if (!Objects.equals(this.ontologyToConsider, other.ontologyToConsider)) {
            return false;
        }
        if (!Objects.equals(this.ontologyXRef, other.ontologyXRef)) {
            return false;
        }
        return Objects.equals(this.dataSet, other.dataSet);
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
        StringBuilder alIdString = new StringBuilder();
        StringBuilder isAString = new StringBuilder();
        StringBuilder pmidString = new StringBuilder();
        StringBuilder relationString = new StringBuilder();
        StringBuilder subsetString = new StringBuilder();
        StringBuilder synonymString = new StringBuilder();
        StringBuilder toConsiderString = new StringBuilder();
        StringBuilder xRefString = new StringBuilder();

        if (ontologyAlternativeId != null) {
            it = ontologyAlternativeId.iterator();
            while (it.hasNext()) {
                alIdString.append("\n\t\t").append(((OntologyAlternativeId) it.next()).toString());
            }
        }
        if (ontologyIsA != null) {
            it = ontologyIsA.iterator();
            while (it.hasNext()) {
                isAString.append("\n\t\t").append(((OntologyIsA) it.next()).toString());
            }
        }
        if (ontologyPMID != null) {
            it = ontologyPMID.iterator();
            while (it.hasNext()) {
                pmidString.append("\n\t\t").append(((OntologyPMID) it.next()).toString());
            }
        }
        if (ontologyRelation != null) {
            it = ontologyRelation.iterator();
            while (it.hasNext()) {
                relationString.append("\n\t\t").append(((OntologyRelation) it.next()).toString());
            }
        }
        if (ontologySubset != null) {
            it = ontologySubset.iterator();
            while (it.hasNext()) {
                subsetString.append("\n\t\t").append(((OntologySubset) it.next()).toString());
            }
        }
        if (ontologyhasOntologySynonym != null) {
            it = ontologyhasOntologySynonym.values().iterator();
            while (it.hasNext()) {
                synonymString.append("\n\t\t").append(((OntologyhasOntologySynonym) it.next()).toString());
            }
        }
        if (ontologyToConsider != null) {
            it = ontologyToConsider.iterator();
            while (it.hasNext()) {
                toConsiderString.append("\n\t\t").append(((OntologyToConsider) it.next()).toString());
            }
        }
        if (ontologyXRef != null) {
            it = ontologyXRef.iterator();
            while (it.hasNext()) {
                xRefString.append("\n\t\t").append(((OntologyXRef) it.next()).toString());
            }
        }
        return "Ontology["
                + " wid=" + wid
                + " id=" + id
                + " name=" + name
                + " nameSpace=" + nameSpace
                + " def=" + def
                + " comment=" + comment
                + " isObsolete=" + isObsolete
                + " dataSetWID=" + dataSetWID
                + " ]"
                + "\n\tAltId:" + alIdString.toString()
                + "\n\tIsA:" + isAString.toString()
                + "\n\tPMID:" + pmidString.toString()
                + "\n\tRelation:" + relationString.toString()
                + "\n\tSubset:" + subsetString.toString()
                + "\n\tSynonym:" + synonymString.toString()
                + "\n\tToConsider:" + toConsiderString.toString()
                + "\n\tXRef:" + xRefString.toString();
    }
}
