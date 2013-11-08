package org.jbiowhpersistence.datasets.protclust.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.protclust.UniRefTables;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This class is the UniRefEntry entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 31, 2011
 */
@Entity
@Table(name = "UniRefEntry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniRefEntry.findAll", query = "SELECT u FROM UniRefEntry u"),
    @NamedQuery(name = "UniRefEntry.findByWid", query = "SELECT u FROM UniRefEntry u WHERE u.wid = :wid"),
    @NamedQuery(name = "UniRefEntry.findById", query = "SELECT u FROM UniRefEntry u WHERE UPPER(u.id) like :id"),
    @NamedQuery(name = "UniRefEntry.findByUpdated", query = "SELECT u FROM UniRefEntry u WHERE u.updated = :updated"),
    @NamedQuery(name = "UniRefEntry.findByName", query = "SELECT u FROM UniRefEntry u WHERE UPPER(u.name) like :name"),
    @NamedQuery(name = "UniRefEntry.findByTaxId", query = "SELECT u FROM UniRefEntry u WHERE u.taxId = :taxId"),
    @NamedQuery(name = "UniRefEntry.findByDataSetWID", query = "SELECT u FROM UniRefEntry u WHERE u.dataSetWID = :dataSetWID")})
public class UniRefEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;
    @Basic(optional = false)
    @Column(name = "Updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "TaxId")
    private long taxId;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    // Internal Relationship
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uniRefEntry")
    private Set<UniRefEntryProperty> uniRefEntryProperty;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uniRefEntry")
    private Set<UniRefMember> uniRefMember;
    // External Relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxId", referencedColumnName = "TaxId", insertable = false, unique = false, nullable = true, updatable = false)
    private Taxonomy taxonomy;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = UniRefTables.UNIREFENTRY_HAS_PROTEIN,
    joinColumns =
    @JoinColumn(name = "UniRefEntry_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "Protein_WID", referencedColumnName = "WID"))
    private Set<Protein> protein;

    public UniRefEntry() {
    }

    public UniRefEntry(Long wid) {
        this.wid = wid;
    }

    public UniRefEntry(Long wid, String id, Date updated, String name, long taxId, long dataSetWID) {
        this.wid = wid;
        this.id = id;
        this.updated = updated;
        this.name = name;
        this.taxId = taxId;
        this.dataSetWID = dataSetWID;
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
    public Set<UniRefMember> getUniRefMember() {
        return uniRefMember;
    }

    public void setUniRefMember(Set<UniRefMember> uniRefMember) {
        this.uniRefMember = uniRefMember;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    @XmlTransient
    public Set<UniRefEntryProperty> getUniRefEntryProperty() {
        return uniRefEntryProperty;
    }

    public void setUniRefEntryProperty(Set<UniRefEntryProperty> uniRefEntryProperty) {
        this.uniRefEntryProperty = uniRefEntryProperty;
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
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
        final UniRefEntry other = (UniRefEntry) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.updated, other.updated)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.taxId != other.taxId) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (!Objects.equals(this.uniRefEntryProperty, other.uniRefEntryProperty)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UniRefEntry{" + "wid=" + wid + ", id=" + id + ", updated=" + updated + ", name=" + name + ", taxId=" + taxId + ", dataSetWID=" + dataSetWID + ", uniRefEntryProperty=" + uniRefEntryProperty + '}';
    }
}
