package org.jbiowhpersistence.datasets.protgroup.orthoxml.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 * This class is the OrthoXMLSpecie entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "species", propOrder = {
    "database",
    "notes"
})
@Entity
@Table(name = "OrthoXMLSpecie")
@NamedQueries({
    @NamedQuery(name = "OrthoXMLSpecie.findAll", query = "SELECT o FROM OrthoXMLSpecie o"),
    @NamedQuery(name = "OrthoXMLSpecie.findByWid", query = "SELECT o FROM OrthoXMLSpecie o WHERE o.wid = :wid"),
    @NamedQuery(name = "OrthoXMLSpecie.findByTaxId", query = "SELECT o FROM OrthoXMLSpecie o WHERE o.taxId = :taxId"),
    @NamedQuery(name = "OrthoXMLSpecie.findByName", query = "SELECT o FROM OrthoXMLSpecie o WHERE o.name = :name"),
    @NamedQuery(name = "OrthoXMLSpecie.findByOrthoXMLWID", query = "SELECT o FROM OrthoXMLSpecie o WHERE o.orthoXMLWID = :orthoXMLWID")})
public class OrthoXMLSpecie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "TaxId", precision = 20, scale = 0)
    @XmlAttribute(name = "NCBITaxId", required = true)
    private BigInteger taxId;
    @Column(name = "Name")
    @XmlAttribute(name = "name", required = true)
    @XmlSchemaType(name = "anySimpleType")
    private String name;
    @Basic(optional = false)
    @Column(name = "OrthoXML_WID")
    private long orthoXMLWID;
    // Internla relations
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OrthoXML_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private OrthoXML orthoXML;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orthoXMLSpecie")
    @XmlElement(required = true)
    @XmlInverseReference(mappedBy = "orthoXMLSpecie")
    @XmlElementWrapper(name = "database")
    private Set<OrthoXMLSpeciesDatabase> orthoXMLSpeciesDatabase;

    public OrthoXMLSpecie() {
    }

    public OrthoXMLSpecie(Long wid) {
        this.wid = wid;
    }

    public OrthoXMLSpecie(Long wid, long orthoXMLWID) {
        this.wid = wid;
        this.orthoXMLWID = orthoXMLWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public BigInteger getTaxId() {
        return taxId;
    }

    public void setTaxId(BigInteger taxId) {
        this.taxId = taxId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<OrthoXMLSpeciesDatabase> getOrthoXMLSpeciesDatabase() {
        return orthoXMLSpeciesDatabase;
    }

    public void setOrthoXMLSpeciesDatabase(Set<OrthoXMLSpeciesDatabase> orthoXMLSpeciesDatabase) {
        this.orthoXMLSpeciesDatabase = orthoXMLSpeciesDatabase;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 97 * hash + (this.taxId != null ? this.taxId.hashCode() : 0);
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 97 * hash + (int) (this.orthoXMLWID ^ (this.orthoXMLWID >>> 32));
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
        final OrthoXMLSpecie other = (OrthoXMLSpecie) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.taxId != other.taxId && (this.taxId == null || !this.taxId.equals(other.taxId))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return this.orthoXMLWID == other.orthoXMLWID;
    }

    @Override
    public String toString() {
        return "OrthoXMLSpecie{"
                + "wid=" + wid
                + ", taxId=" + taxId
                + ", name=" + name
                + ", orthoXMLWID=" + orthoXMLWID
                + '}';
    }
}
