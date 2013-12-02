package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein DB reference entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinDBReference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinDBReference.findAll", query = "SELECT p FROM ProteinDBReference p"),
    @NamedQuery(name = "ProteinDBReference.findByWid", query = "SELECT p FROM ProteinDBReference p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinDBReference.findByProteinWID", query = "SELECT p FROM ProteinDBReference p WHERE p.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinDBReference.findByType", query = "SELECT p FROM ProteinDBReference p WHERE p.type = :type"),
    @NamedQuery(name = "ProteinDBReference.findById", query = "SELECT p FROM ProteinDBReference p WHERE p.id = :id"),
    @NamedQuery(name = "ProteinDBReference.findByEvidence", query = "SELECT p FROM ProteinDBReference p WHERE p.evidence = :evidence")})
public class ProteinDBReference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;
    @Column(name = "Evidence")
    private String evidence;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;
    @ElementCollection
    @CollectionTable(
            name = "ProteinDBReferenceProperty",
            joinColumns
            = @JoinColumn(name = "ProteinDBReference_WID"))
    @XmlElementWrapper( name="ProteinDBReferencePropertys" )
    private Collection<ProteinDBReferenceProperty> proteinDBReferenceProperty;

    public ProteinDBReference() {
    }

    public ProteinDBReference(Long wid) {
        this.wid = wid;
    }

    public ProteinDBReference(Long wid, long proteinWID, String type, String id) {
        this.wid = wid;
        this.proteinWID = proteinWID;
        this.type = type;
        this.id = id;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
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

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public Collection<ProteinDBReferenceProperty> getProteinDBReferenceProperty() {
        return proteinDBReferenceProperty;
    }

    public void setProteinDBReferenceProperty(Collection<ProteinDBReferenceProperty> proteinDBReferenceProperty) {
        this.proteinDBReferenceProperty = proteinDBReferenceProperty;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinDBReference other = (ProteinDBReference) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.evidence, other.evidence)) {
            return false;
        }
        return Objects.equals(this.proteinDBReferenceProperty, other.proteinDBReferenceProperty);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder dProperty = new StringBuilder();

        if (proteinDBReferenceProperty != null && !proteinDBReferenceProperty.isEmpty()) {
            for (ProteinDBReferenceProperty p : proteinDBReferenceProperty) {
                dProperty.append("\t\t").append(p).append("\n");
            }
        }
        return "ProteinDBReference{" + "wid=" + wid + ", proteinWID=" + proteinWID + ", type=" + type + ", id=" + id + ", evidence=" + evidence + "\n" + dProperty.toString() + '}';
    }

}
