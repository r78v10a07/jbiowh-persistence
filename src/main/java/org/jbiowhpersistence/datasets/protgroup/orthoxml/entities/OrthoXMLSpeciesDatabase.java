package org.jbiowhpersistence.datasets.protgroup.orthoxml.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.OrthoXMLTables;

/**
 * This class is the OrthoXMLSpeciesDatabase entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "database", propOrder = {
    "genes"
})
@Entity
@Table(name = "OrthoXMLSpeciesDatabase")
@NamedQueries({
    @NamedQuery(name = "OrthoXMLSpeciesDatabase.findAll", query = "SELECT o FROM OrthoXMLSpeciesDatabase o"),
    @NamedQuery(name = "OrthoXMLSpeciesDatabase.findByWid", query = "SELECT o FROM OrthoXMLSpeciesDatabase o WHERE o.wid = :wid"),
    @NamedQuery(name = "OrthoXMLSpeciesDatabase.findByGeneLink", query = "SELECT o FROM OrthoXMLSpeciesDatabase o WHERE o.geneLink = :geneLink"),
    @NamedQuery(name = "OrthoXMLSpeciesDatabase.findByName", query = "SELECT o FROM OrthoXMLSpeciesDatabase o WHERE o.name = :name"),
    @NamedQuery(name = "OrthoXMLSpeciesDatabase.findByProtLink", query = "SELECT o FROM OrthoXMLSpeciesDatabase o WHERE o.protLink = :protLink"),
    @NamedQuery(name = "OrthoXMLSpeciesDatabase.findByTranscriptLink", query = "SELECT o FROM OrthoXMLSpeciesDatabase o WHERE o.transcriptLink = :transcriptLink"),
    @NamedQuery(name = "OrthoXMLSpeciesDatabase.findByVersion", query = "SELECT o FROM OrthoXMLSpeciesDatabase o WHERE o.version = :version"),
    @NamedQuery(name = "OrthoXMLSpeciesDatabase.findByOrthoXMLSpeciesWID", query = "SELECT o FROM OrthoXMLSpeciesDatabase o WHERE o.orthoXMLSpeciesWID = :orthoXMLSpeciesWID")})
public class OrthoXMLSpeciesDatabase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @XmlAttribute(name = "geneLink")
    @XmlSchemaType(name = "anyURI")
    @Column(name = "GeneLink")
    private String geneLink;
    @Column(name = "Name", length = 255)
    @XmlAttribute(name = "name", required = true)
    private String name;
    @Column(name = "ProtLink")
    @XmlAttribute(name = "protLink")
    @XmlSchemaType(name = "anyURI")
    private String protLink;
    @Column(name = "TranscriptLink")
    @XmlAttribute(name = "transcriptLink")
    @XmlSchemaType(name = "anyURI")
    private String transcriptLink;
    @Column(name = "Version")
    @XmlAttribute(name = "version", required = true)
    @XmlSchemaType(name = "anySimpleType")
    private String version;
    @Basic(optional = false)
    @Column(name = "OrthoXMLSpecies_WID")
    private long orthoXMLSpeciesWID;
    // Internla relations
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OrthoXMLSpecies_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OrthoXMLSpecie orthoXMLSpecie;
    @ElementCollection
    @CollectionTable(
            name = OrthoXMLTables.ORTHOXMLSPECIESDATABASEGENE,
            joinColumns
            = @JoinColumn(name = "OrthoXMLSpeciesDatabase_WID"))
    @XmlElementWrapper(name = "genes")
    private Collection<OrthoXMLSpeciesDatabaseGene> orthoXMLSpeciesDatabaseGene;

    public OrthoXMLSpeciesDatabase() {
    }

    public OrthoXMLSpeciesDatabase(Long wid) {
        this.wid = wid;
    }

    public OrthoXMLSpeciesDatabase(Long wid, long orthoXMLSpeciesWID) {
        this.wid = wid;
        this.orthoXMLSpeciesWID = orthoXMLSpeciesWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getGeneLink() {
        return geneLink;
    }

    public void setGeneLink(String geneLink) {
        this.geneLink = geneLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtLink() {
        return protLink;
    }

    public void setProtLink(String protLink) {
        this.protLink = protLink;
    }

    public String getTranscriptLink() {
        return transcriptLink;
    }

    public void setTranscriptLink(String transcriptLink) {
        this.transcriptLink = transcriptLink;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getOrthoXMLSpeciesWID() {
        return orthoXMLSpeciesWID;
    }

    public void setOrthoXMLSpeciesWID(long orthoXMLSpeciesWID) {
        this.orthoXMLSpeciesWID = orthoXMLSpeciesWID;
    }

    @XmlTransient
    public OrthoXMLSpecie getOrthoXMLSpecie() {
        return orthoXMLSpecie;
    }

    public void setOrthoXMLSpecie(OrthoXMLSpecie orthoXMLSpecie) {
        this.orthoXMLSpecie = orthoXMLSpecie;
    }

    public Collection<OrthoXMLSpeciesDatabaseGene> getOrthoXMLSpeciesDatabaseGene() {
        return orthoXMLSpeciesDatabaseGene;
    }

    public void setOrthoXMLSpeciesDatabaseGene(Collection<OrthoXMLSpeciesDatabaseGene> orthoXMLSpeciesDatabaseGene) {
        this.orthoXMLSpeciesDatabaseGene = orthoXMLSpeciesDatabaseGene;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 29 * hash + (this.geneLink != null ? this.geneLink.hashCode() : 0);
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.protLink != null ? this.protLink.hashCode() : 0);
        hash = 29 * hash + (this.transcriptLink != null ? this.transcriptLink.hashCode() : 0);
        hash = 29 * hash + (this.version != null ? this.version.hashCode() : 0);
        hash = 29 * hash + (int) (this.orthoXMLSpeciesWID ^ (this.orthoXMLSpeciesWID >>> 32));
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
        final OrthoXMLSpeciesDatabase other = (OrthoXMLSpeciesDatabase) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if ((this.geneLink == null) ? (other.geneLink != null) : !this.geneLink.equals(other.geneLink)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.protLink == null) ? (other.protLink != null) : !this.protLink.equals(other.protLink)) {
            return false;
        }
        if ((this.transcriptLink == null) ? (other.transcriptLink != null) : !this.transcriptLink.equals(other.transcriptLink)) {
            return false;
        }
        if ((this.version == null) ? (other.version != null) : !this.version.equals(other.version)) {
            return false;
        }
        return this.orthoXMLSpeciesWID == other.orthoXMLSpeciesWID;
    }

    @Override
    public String toString() {
        return "OrthoXMLSpeciesDatabase{" + "wid=" + wid + ", geneLink=" + geneLink + ", name=" + name + ", protLink=" + protLink + ", transcriptLink=" + transcriptLink + ", version=" + version + '}';
    }
}
