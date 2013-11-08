package org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.pathway.kegg.KEGGTables;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction.KEGGReaction;
import org.jbiowhpersistence.datasets.protein.ProteinTables;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 * This class is the KEGG Enzyme entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-08-31 15:46:36 +0200
 * (Fri, 31 Aug 2012) $ $LastChangedRevision: 322 $
 *
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGEnzyme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGEnzyme.findAll", query = "SELECT k FROM KEGGEnzyme k"),
    @NamedQuery(name = "KEGGEnzyme.findByWid", query = "SELECT k FROM KEGGEnzyme k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGEnzyme.findByEntry", query = "SELECT k FROM KEGGEnzyme k WHERE k.entry = :entry"),
    @NamedQuery(name = "KEGGEnzyme.findByDataSetWID", query = "SELECT k FROM KEGGEnzyme k WHERE k.dataSetWID = :dataSetWID")})
public class KEGGEnzyme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Entry")
    private String entry;
    @Lob
    @Column(name = "Comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGEnzyme")
    @MapKey(name = "kEGGEnzymeDBLinkPK")
    private Map<KEGGEnzymeDBLinkPK, KEGGEnzymeDBLink> kEGGEnzymeDBLink;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGEnzyme")
    private Set<KEGGEnzymeName> kEGGEnzymeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGEnzyme")
    private Set<KEGGEnzymeSysName> kEGGEnzymeSysName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGEnzyme")
    @MapKey(name = "kEGGEnzymeOrthologyPK")
    private Map<KEGGEnzymeOrthologyPK, KEGGEnzymeOrthology> kEGGEnzymeOrthology;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGENZYME_HAS_KEGGENZYMECLASS,
    joinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGEnzymeClass_WID", referencedColumnName = "WID"))
    private Set<KEGGEnzymeClass> kEGGEnzymeClass;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGREACTION_HAS_KEGGENZYME,
    joinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGReaction_WID", referencedColumnName = "WID"))
    private Set<KEGGReaction> kEGGReaction;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGENZYME_HAS_KEGGCOMPOUND_AS_COFACTOR,
    joinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"))
    private Set<KEGGCompound> kEGGCompoundAsCofactor;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGENZYME_HAS_KEGGCOMPOUND_AS_EFFECTOR,
    joinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"))
    private Set<KEGGCompound> kEGGCompoundAsEffector;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGENZYME_HAS_KEGGCOMPOUND_AS_INHIBITOR,
    joinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGCompound_WID", referencedColumnName = "WID"))
    private Set<KEGGCompound> kEGGCompoundAsInhibitor;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = KEGGTables.KEGGENZYME_HAS_KEGGPATHWAY,
    joinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "KEGGPathway_WID", referencedColumnName = "WID"))
    private Set<KEGGPathway> kEGGPathways;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ProteinTables.PROTEIN_HAS_KEGGENZYME,
    joinColumns =
    @JoinColumn(name = "KEGGEnzyme_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> protein;

    public KEGGEnzyme() {
    }

    public KEGGEnzyme(Long wid) {
        this.wid = wid;
    }

    public KEGGEnzyme(Long wid, long dataSetWID) {
        this.wid = wid;
        this.dataSetWID = dataSetWID;
    }

    public void setRelationsToNull() {
        kEGGReaction = null;
        kEGGCompoundAsCofactor = null;
        kEGGCompoundAsEffector = null;
        kEGGCompoundAsInhibitor = null;
        kEGGPathways = null;
        protein = null;
    }

    public String getkEGGEnzymeNameDirectly() {
        if (!getkEGGEnzymeName().isEmpty()) {
            return getkEGGEnzymeName().iterator().next().getName();
        }
        return null;
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
    public Map<KEGGEnzymeOrthologyPK, KEGGEnzymeOrthology> getkEGGEnzymeOrthology() {
        return kEGGEnzymeOrthology;
    }

    public void setkEGGEnzymeOrthology(Map<KEGGEnzymeOrthologyPK, KEGGEnzymeOrthology> kEGGEnzymeOrthology) {
        this.kEGGEnzymeOrthology = kEGGEnzymeOrthology;
    }

    @XmlTransient
    public Set<KEGGPathway> getkEGGPathways() {
        return kEGGPathways;
    }

    public void setkEGGPathways(Set<KEGGPathway> kEGGPathways) {
        this.kEGGPathways = kEGGPathways;
    }

    @XmlTransient
    public Set<KEGGCompound> getkEGGCompoundAsCofactor() {
        return kEGGCompoundAsCofactor;
    }

    public void setkEGGCompoundAsCofactor(Set<KEGGCompound> kEGGCompoundAsCofactor) {
        this.kEGGCompoundAsCofactor = kEGGCompoundAsCofactor;
    }

    @XmlTransient
    public Set<KEGGCompound> getkEGGCompoundAsEffector() {
        return kEGGCompoundAsEffector;
    }

    public void setkEGGCompoundAsEffector(Set<KEGGCompound> kEGGCompoundAsEffector) {
        this.kEGGCompoundAsEffector = kEGGCompoundAsEffector;
    }

    @XmlTransient
    public Set<KEGGCompound> getkEGGCompoundAsInhibitor() {
        return kEGGCompoundAsInhibitor;
    }

    public void setkEGGCompoundAsInhibitor(Set<KEGGCompound> kEGGCompoundAsInhibitor) {
        this.kEGGCompoundAsInhibitor = kEGGCompoundAsInhibitor;
    }

    @XmlTransient
    public Set<KEGGReaction> getkEGGReaction() {
        return kEGGReaction;
    }

    public void setkEGGReaction(Set<KEGGReaction> kEGGReaction) {
        this.kEGGReaction = kEGGReaction;
    }

    @XmlTransient
    public Set<KEGGEnzymeClass> getkEGGEnzymeClass() {
        return kEGGEnzymeClass;
    }

    public void setkEGGEnzymeClass(Set<KEGGEnzymeClass> kEGGEnzymeClass) {
        this.kEGGEnzymeClass = kEGGEnzymeClass;
    }

    @XmlTransient
    public Set<KEGGEnzymeSysName> getkEGGEnzymeSysName() {
        return kEGGEnzymeSysName;
    }

    public void setkEGGEnzymeSysName(Set<KEGGEnzymeSysName> kEGGEnzymeSysName) {
        this.kEGGEnzymeSysName = kEGGEnzymeSysName;
    }

    @XmlTransient
    public Set<KEGGEnzymeName> getkEGGEnzymeName() {
        return kEGGEnzymeName;
    }

    public void setkEGGEnzymeName(Set<KEGGEnzymeName> kEGGEnzymeName) {
        this.kEGGEnzymeName = kEGGEnzymeName;
    }

    @XmlTransient
    public Map<KEGGEnzymeDBLinkPK, KEGGEnzymeDBLink> getkEGGEnzymeDBLink() {
        return kEGGEnzymeDBLink;
    }

    public void setkEGGEnzymeDBLink(Map<KEGGEnzymeDBLinkPK, KEGGEnzymeDBLink> kEGGEnzymeDBLink) {
        this.kEGGEnzymeDBLink = kEGGEnzymeDBLink;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        final KEGGEnzyme other = (KEGGEnzyme) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.entry, other.entry)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGEnzyme{" + "wid=" + wid + ", entry=" + entry + ", comment=" + comment + ", dataSetWID=" + dataSetWID + '}';
    }
}
