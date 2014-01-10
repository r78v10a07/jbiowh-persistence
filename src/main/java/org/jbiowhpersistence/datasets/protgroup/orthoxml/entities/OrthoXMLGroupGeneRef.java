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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.jbiowhpersistence.datasets.protgroup.orthoxml.OrthoXMLTables;

/**
 * This class is the OrthoXMLGroupGeneRef entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "geneRef", propOrder = {
    "score",
    "notes"
})
@Entity
@Table(name = "OrthoXMLGroupGeneRef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrthoXMLGroupGeneRef.findAll", query = "SELECT o FROM OrthoXMLGroupGeneRef o"),
    @NamedQuery(name = "OrthoXMLGroupGeneRef.findByWid", query = "SELECT o FROM OrthoXMLGroupGeneRef o WHERE o.wid = :wid"),
    @NamedQuery(name = "OrthoXMLGroupGeneRef.findById", query = "SELECT o FROM OrthoXMLGroupGeneRef o WHERE o.id = :id"),
    @NamedQuery(name = "OrthoXMLGroupGeneRef.findByOrthoXMLGroupWID", query = "SELECT o FROM OrthoXMLGroupGeneRef o WHERE o.orthoXMLGroupWID = :orthoXMLGroupWID")})
public class OrthoXMLGroupGeneRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "Id")
    @XmlAttribute(name = "id", required = true)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "OrthoXMLGroup_WID")
    private long orthoXMLGroupWID;
    //Internal relations
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OrthoXMLGroup_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OrthoXMLGroup orthoXMLGroup;
    @ElementCollection
    @CollectionTable(
            name = OrthoXMLTables.ORTHOXMLGROUPGENEREFSCORE,
            joinColumns
            = @JoinColumn(name = "OrthoXMLGroupGeneRef_WID"))
    @XmlElementWrapper(name = "OrthoXMLGeneRefScores")
    private Collection<OrthoXMLGeneRefScore> orthoXMLGeneRefScore;

    public OrthoXMLGroupGeneRef() {
    }

    public OrthoXMLGroupGeneRef(Long wid) {
        this.wid = wid;
    }

    public OrthoXMLGroupGeneRef(Long wid, long orthoXMLGroupWID) {
        this.wid = wid;
        this.orthoXMLGroupWID = orthoXMLGroupWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getOrthoXMLGroupWID() {
        return orthoXMLGroupWID;
    }

    public void setOrthoXMLGroupWID(long orthoXMLGroupWID) {
        this.orthoXMLGroupWID = orthoXMLGroupWID;
    }

    @XmlTransient
    public OrthoXMLGroup getOrthoXMLGroup() {
        return orthoXMLGroup;
    }

    public void setOrthoXMLGroup(OrthoXMLGroup orthoXMLGroup) {
        this.orthoXMLGroup = orthoXMLGroup;
    }

    public Collection<OrthoXMLGeneRefScore> getOrthoXMLGeneRefScore() {
        return orthoXMLGeneRefScore;
    }

    public void setOrthoXMLGeneRefScore(Collection<OrthoXMLGeneRefScore> orthoXMLGeneRefScore) {
        this.orthoXMLGeneRefScore = orthoXMLGeneRefScore;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (int) (this.orthoXMLGroupWID ^ (this.orthoXMLGroupWID >>> 32));
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
        final OrthoXMLGroupGeneRef other = (OrthoXMLGroupGeneRef) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return this.orthoXMLGroupWID == other.orthoXMLGroupWID;
    }

    @Override
    public String toString() {
        return "OrthoXMLGroupGeneRef{" + "wid=" + wid + ", id=" + id + ", orthoXMLGroupWID=" + orthoXMLGroupWID + '}';
    }
}
