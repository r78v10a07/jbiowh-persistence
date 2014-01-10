package org.jbiowhpersistence.datasets.protgroup.orthoxml.entities;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.OrthoXMLTables;

/**
 * This class is the OrthoXMLGroup entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "group", propOrder = {
    "score",
    "property",
    "geneRefOrParalogGroupOrOrthologGroup",
    "notes"
})
@Entity
@Table(name = "OrthoXMLGroup")
@NamedQueries({
    @NamedQuery(name = "OrthoXMLGroup.findAll", query = "SELECT o FROM OrthoXMLGroup o"),
    @NamedQuery(name = "OrthoXMLGroup.findByWid", query = "SELECT o FROM OrthoXMLGroup o WHERE o.wid = :wid"),
    @NamedQuery(name = "OrthoXMLGroup.findById", query = "SELECT o FROM OrthoXMLGroup o WHERE o.id = :id"),
    @NamedQuery(name = "OrthoXMLGroup.findByIsOrthologGroup", query = "SELECT o FROM OrthoXMLGroup o WHERE o.isOrthologGroup = :isOrthologGroup"),
    @NamedQuery(name = "OrthoXMLGroup.findByOrthoXMLWID", query = "SELECT o FROM OrthoXMLGroup o WHERE o.orthoXMLWID = :orthoXMLWID")})
public class OrthoXMLGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Id", length = 255)
    @XmlAttribute(name = "id")
    private String id;
    @Column(name = "isOrthologGroup")
    private Boolean isOrthologGroup;
    @Basic(optional = false)
    @Column(name = "OrthoXML_WID")
    private long orthoXMLWID;
    // internal relations
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OrthoXML_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OrthoXML orthoXML;
    @ElementCollection
    @CollectionTable(
            name = OrthoXMLTables.ORTHOXMLGROUPPROPERTY,
            joinColumns
            = @JoinColumn(name = "OrthoXMLGroup_WID"))
    private Collection<OrthoXMLGroupProperty> orthoXMLGroupProperty;
    @ElementCollection
    @CollectionTable(
            name = OrthoXMLTables.ORTHOXMLGROUPSCORE,
            joinColumns
            = @JoinColumn(name = "OrthoXMLGroup_WID"))
    private Collection<OrthoXMLGroupScore> orthoXMLGroupScore;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "")
    @XmlElement
    @XmlInverseReference(mappedBy = "")
    private Set<OrthoXMLGroupGeneRef> orthoXMLGroupGeneRef;

    public OrthoXMLGroup() {
    }

    public OrthoXMLGroup(Long wid) {
        this.wid = wid;
    }

    public OrthoXMLGroup(Long wid, long orthoXMLWID) {
        this.wid = wid;
        this.orthoXMLWID = orthoXMLWID;
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

    @XmlTransient
    public Boolean getIsOrthologGroup() {
        return isOrthologGroup;
    }

    public void setIsOrthologGroup(Boolean isOrthologGroup) {
        this.isOrthologGroup = isOrthologGroup;
    }

    public long getOrthoXMLWID() {
        return orthoXMLWID;
    }

    public void setOrthoXMLWID(long orthoXMLWID) {
        this.orthoXMLWID = orthoXMLWID;
    }

    @XmlTransient
    public OrthoXML getOrthoXML() {
        return orthoXML;
    }

    public void setOrthoXML(OrthoXML orthoXML) {
        this.orthoXML = orthoXML;
    }

    public Collection<OrthoXMLGroupProperty> getOrthoXMLGroupProperty() {
        return orthoXMLGroupProperty;
    }

    public void setOrthoXMLGroupProperty(Collection<OrthoXMLGroupProperty> orthoXMLGroupProperty) {
        this.orthoXMLGroupProperty = orthoXMLGroupProperty;
    }

    public Collection<OrthoXMLGroupScore> getOrthoXMLGroupScore() {
        return orthoXMLGroupScore;
    }

    public void setOrthoXMLGroupScore(Collection<OrthoXMLGroupScore> orthoXMLGroupScore) {
        this.orthoXMLGroupScore = orthoXMLGroupScore;
    }

    public Set<OrthoXMLGroupGeneRef> getOrthoXMLGroupGeneRef() {
        return orthoXMLGroupGeneRef;
    }

    public void setOrthoXMLGroupGeneRef(Set<OrthoXMLGroupGeneRef> orthoXMLGroupGeneRef) {
        this.orthoXMLGroupGeneRef = orthoXMLGroupGeneRef;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.isOrthologGroup != null ? this.isOrthologGroup.hashCode() : 0);
        hash = 37 * hash + (int) (this.orthoXMLWID ^ (this.orthoXMLWID >>> 32));
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
        final OrthoXMLGroup other = (OrthoXMLGroup) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if (this.isOrthologGroup != other.isOrthologGroup && (this.isOrthologGroup == null || !this.isOrthologGroup.equals(other.isOrthologGroup))) {
            return false;
        }
        return this.orthoXMLWID == other.orthoXMLWID;
    }

    @Override
    public String toString() {
        return "OrthoXMLGroup{" + "wid=" + wid + ", id=" + id + ", isOrthologGroup=" + isOrthologGroup + ", orthoXMLWID=" + orthoXMLWID + '}';
    }
}
