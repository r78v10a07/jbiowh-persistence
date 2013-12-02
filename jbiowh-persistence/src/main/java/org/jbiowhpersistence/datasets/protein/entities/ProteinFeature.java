package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Feature entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinFeature")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinFeature.findAll", query = "SELECT p FROM ProteinFeature p"),
    @NamedQuery(name = "ProteinFeature.findByWid", query = "SELECT p FROM ProteinFeature p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinFeature.findByProteinWID", query = "SELECT p FROM ProteinFeature p WHERE p.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinFeature.findByType", query = "SELECT p FROM ProteinFeature p WHERE p.type = :type"),
    @NamedQuery(name = "ProteinFeature.findByStatus", query = "SELECT p FROM ProteinFeature p WHERE p.status = :status"),
    @NamedQuery(name = "ProteinFeature.findById", query = "SELECT p FROM ProteinFeature p WHERE p.id = :id"),
    @NamedQuery(name = "ProteinFeature.findByEvidence", query = "SELECT p FROM ProteinFeature p WHERE p.evidence = :evidence"),
    @NamedQuery(name = "ProteinFeature.findByRef", query = "SELECT p FROM ProteinFeature p WHERE p.ref = :ref")})
public class ProteinFeature implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Column(name = "Type")
    private String type;
    @Column(name = "Status")
    private String status;
    @Column(name = "Id")
    private String id;
    @Lob
    @Column(name = "Description")
    private String description;
    @Column(name = "Evidence")
    private String evidence;
    @Column(name = "Ref")
    private String ref;
    @Lob
    @Column(name = "Original")
    private String original;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;
    @ElementCollection
    @CollectionTable(
            name = "ProteinFeatureVariation",
            joinColumns
            = @JoinColumn(name = "ProteinFeature_WID"))
    @XmlElementWrapper( name="ProteinFeatureVariations" )
    private Collection<ProteinFeatureVariation> proteinFeatureVariation;

    public ProteinFeature() {
    }

    public ProteinFeature(Long wid) {
        this.wid = wid;
    }

    public ProteinFeature(Long wid, long proteinWID) {
        this.wid = wid;
        this.proteinWID = proteinWID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public Collection<ProteinFeatureVariation> getProteinFeatureVariation() {
        return proteinFeatureVariation;
    }

    public void setProteinFeatureVariation(Collection<ProteinFeatureVariation> proteinFeatureVariation) {
        this.proteinFeatureVariation = proteinFeatureVariation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.wid != null ? this.wid.hashCode() : 0);
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
        final ProteinFeature other = (ProteinFeature) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if ((this.evidence == null) ? (other.evidence != null) : !this.evidence.equals(other.evidence)) {
            return false;
        }
        if ((this.ref == null) ? (other.ref != null) : !this.ref.equals(other.ref)) {
            return false;
        }
        if ((this.original == null) ? (other.original != null) : !this.original.equals(other.original)) {
            return false;
        }
        return this.proteinFeatureVariation == other.proteinFeatureVariation || (this.proteinFeatureVariation != null && this.proteinFeatureVariation.equals(other.proteinFeatureVariation));
    }

    @Override
    public String toString() {
        StringBuilder pData = new StringBuilder();

        if (proteinFeatureVariation != null) {
            for (ProteinFeatureVariation v : proteinFeatureVariation) {
                pData.append("\t\t").append(v).append("\n");
            }
        }
        return "ProteinFeature{" + "wid=" + wid + ", proteinWID=" + proteinWID + ", type=" + type + ", status=" + status + ", id=" + id + ", description=" + description + ", evidence=" + evidence + ", ref=" + ref + ", original=" + original + "\n" + pData.toString() + '}';
    }
}
