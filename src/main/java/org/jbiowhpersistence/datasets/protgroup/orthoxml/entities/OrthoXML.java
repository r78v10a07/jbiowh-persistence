package org.jbiowhpersistence.datasets.protgroup.orthoxml.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.OrthoXMLTables;

/**
 * This class is the OrthoXML entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@Entity
@Table(name = "OrthoXML")
@XmlRootElement(name = "orthoXML")
@NamedQueries({
    @NamedQuery(name = "OrthoXML.findAll", query = "SELECT o FROM OrthoXML o"),
    @NamedQuery(name = "OrthoXML.findByWid", query = "SELECT o FROM OrthoXML o WHERE o.wid = :wid"),
    @NamedQuery(name = "OrthoXML.findByOrigin", query = "SELECT o FROM OrthoXML o WHERE o.origin = :origin"),
    @NamedQuery(name = "OrthoXML.findByOriginVersion", query = "SELECT o FROM OrthoXML o WHERE o.originVersion = :originVersion"),
    @NamedQuery(name = "OrthoXML.findByVersion", query = "SELECT o FROM OrthoXML o WHERE o.version = :version")})
public class OrthoXML implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Origin", length = 255)
    @XmlAttribute(name = "origin", required = true)
    private String origin;
    @Column(name = "OriginVersion", length = 255)
    @XmlAttribute(name = "originVersion", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    private String originVersion;
    @Column(name = "Version", precision = 20, scale = 10)
    @XmlAttribute(name = "version", required = true)
    private BigDecimal version;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    //Internal relations    
    @ElementCollection
    @CollectionTable(
            name = OrthoXMLTables.ORTHOXMLSCORE,
            joinColumns
            = @JoinColumn(name = "OrthoXML_WID"))
    private Collection<OrthoXMLScore> orthoXMLScore;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orthoXML")
    @XmlElement(required = true)
    @XmlInverseReference(mappedBy = "orthoXML")
    private Set<OrthoXMLSpecie> orthoXMLSpecie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orthoXML")
    @XmlElement(required = true)
    @XmlInverseReference(mappedBy = "orthoXML")
    @XmlElementWrapper(name = "groups")
    private Set<OrthoXMLGroup> orthoXMLGroup;
    // External Protein relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;

    public OrthoXML() {
    }

    public OrthoXML(Long wid) {
        this.wid = wid;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginVersion() {
        return originVersion;
    }

    public void setOriginVersion(String originVersion) {
        this.originVersion = originVersion;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public Collection<OrthoXMLScore> getOrthoXMLScore() {
        return orthoXMLScore;
    }

    public void setOrthoXMLScore(Collection<OrthoXMLScore> orthoXMLScore) {
        this.orthoXMLScore = orthoXMLScore;
    }

    public Set<OrthoXMLSpecie> getOrthoXMLSpecie() {
        return orthoXMLSpecie;
    }

    public void setOrthoXMLSpecie(Set<OrthoXMLSpecie> orthoXMLSpecie) {
        this.orthoXMLSpecie = orthoXMLSpecie;
    }

    public Set<OrthoXMLGroup> getOrthoXMLGroup() {
        return orthoXMLGroup;
    }

    public void setOrthoXMLGroup(Set<OrthoXMLGroup> orthoXMLGroup) {
        this.orthoXMLGroup = orthoXMLGroup;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 97 * hash + (this.origin != null ? this.origin.hashCode() : 0);
        hash = 97 * hash + (this.originVersion != null ? this.originVersion.hashCode() : 0);
        hash = 97 * hash + (this.version != null ? this.version.hashCode() : 0);
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
        final OrthoXML other = (OrthoXML) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if ((this.origin == null) ? (other.origin != null) : !this.origin.equals(other.origin)) {
            return false;
        }
        if ((this.originVersion == null) ? (other.originVersion != null) : !this.originVersion.equals(other.originVersion)) {
            return false;
        }
        return this.version == other.version || (this.version != null && this.version.equals(other.version));
    }

    @Override
    public String toString() {
        return "OrthoXML{" + "wid=" + wid + ", origin=" + origin + ", originVersion=" + originVersion + ", version=" + version + '}';
    }
}
