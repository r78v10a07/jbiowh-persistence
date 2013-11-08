package org.jbiowhpersistence.datasets.protclust.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This class is the UniRefMember entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Sep 6, 2011
 */
@Entity
@Table(name = "UniRefMember")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniRefMember.findAll", query = "SELECT u FROM UniRefMember u"),
    @NamedQuery(name = "UniRefMember.findByWid", query = "SELECT u FROM UniRefMember u WHERE u.wid = :wid"),
    @NamedQuery(name = "UniRefMember.findByUniRefEntryWID", query = "SELECT u FROM UniRefMember u WHERE u.uniRefEntryWID = :uniRefEntryWID"),
    @NamedQuery(name = "UniRefMember.findByProteinWID", query = "SELECT u FROM UniRefMember u WHERE u.proteinWID = :proteinWID"),
    @NamedQuery(name = "UniRefMember.findByTaxId", query = "SELECT u FROM UniRefMember u WHERE u.taxId = :taxId"),
    @NamedQuery(name = "UniRefMember.findByType", query = "SELECT u FROM UniRefMember u WHERE u.type = :type"),
    @NamedQuery(name = "UniRefMember.findById", query = "SELECT u FROM UniRefMember u WHERE u.id = :id"),
    @NamedQuery(name = "UniRefMember.findByIsRepresentative", query = "SELECT u FROM UniRefMember u WHERE u.isRepresentative = :isRepresentative")})
public class UniRefMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "UniRefEntry_WID")
    private long uniRefEntryWID;
    @Column(name = "Protein_WID")
    private BigInteger proteinWID;
    @Basic(optional = false)
    @Column(name = "TaxId")
    private long taxId;
    @Column(name = "Type")
    private String type;
    @Column(name = "Id")
    private String id;
    @Column(name = "IsRepresentative")
    private Boolean isRepresentative;
    // Internal Relationship
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uniRefMember")
    private Set<UniRefMemberProperty> uniRefMemberProperty;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UniRefEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private UniRefEntry uniRefEntry;
    // External Relationship    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxId", referencedColumnName = "TaxId", insertable = false, unique = false, nullable = true, updatable = false)
    private Taxonomy taxonomy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public UniRefMember() {
    }

    public UniRefMember(Long wid) {
        this.wid = wid;
    }

    public UniRefMember(Long wid, long uniRefEntryWID, long taxId) {
        this.wid = wid;
        this.uniRefEntryWID = uniRefEntryWID;
        this.taxId = taxId;
    }

    public void setRelationsToNull() {
        setUniRefEntry(null);
        setProtein(null);
    }
    
    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    public UniRefEntry getUniRefEntry() {
        return uniRefEntry;
    }

    public void setUniRefEntry(UniRefEntry uniRefEntry) {
        this.uniRefEntry = uniRefEntry;
    }

    @XmlTransient
    public Set<UniRefMemberProperty> getUniRefMemberProperty() {
        return uniRefMemberProperty;
    }

    public void setUniRefMemberProperty(Set<UniRefMemberProperty> uniRefMemberProperty) {
        this.uniRefMemberProperty = uniRefMemberProperty;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getUniRefEntryWID() {
        return uniRefEntryWID;
    }

    public void setUniRefEntryWID(long uniRefEntryWID) {
        this.uniRefEntryWID = uniRefEntryWID;
    }

    public BigInteger getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(BigInteger proteinWID) {
        this.proteinWID = proteinWID;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsRepresentative() {
        return isRepresentative;
    }

    public void setIsRepresentative(Boolean isRepresentative) {
        this.isRepresentative = isRepresentative;
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
        final UniRefMember other = (UniRefMember) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.uniRefEntryWID != other.uniRefEntryWID) {
            return false;
        }
        if (!Objects.equals(this.proteinWID, other.proteinWID)) {
            return false;
        }
        if (this.taxId != other.taxId) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.isRepresentative, other.isRepresentative)) {
            return false;
        }
        if (!Objects.equals(this.uniRefMemberProperty, other.uniRefMemberProperty)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UniRefMember{" + "wid=" + wid + ", uniRefEntryWID=" + uniRefEntryWID + ", proteinWID=" + proteinWID + ", taxId=" + taxId + ", type=" + type + ", id=" + id + ", isRepresentative=" + isRepresentative + '}';
    }
}
