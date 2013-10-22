package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Feature entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinFeature")
    private Set<ProteinFeatureVariation> proteinFeatureVariation;

    public ProteinFeature() {
    }

    public ProteinFeature(Long wid) {
        this.wid = wid;
    }

    public ProteinFeature(Long wid, long proteinWID) {
        this.wid = wid;
        this.proteinWID = proteinWID;
    }

    public Set<ProteinFeatureVariation> getProteinFeatureVariation() {
        return proteinFeatureVariation;
    }

    public void setProteinFeatureVariation(Set<ProteinFeatureVariation> proteinFeatureVariation) {
        this.proteinFeatureVariation = proteinFeatureVariation;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinFeature other = (ProteinFeature) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.evidence, other.evidence)) {
            return false;
        }
        if (!Objects.equals(this.ref, other.ref)) {
            return false;
        }
        if (!Objects.equals(this.original, other.original)) {
            return false;
        }
        if (!Objects.equals(this.proteinFeatureVariation, other.proteinFeatureVariation)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        Iterator it;
        StringBuilder pData = new StringBuilder();

        it = getProteinFeatureVariation().iterator();
        while (it.hasNext()) {
            pData.append("\t\t").append(it.next()).append("\n");
        }

        return "ProteinFeature{"
                + " wid=" + wid
                + " proteinWID=" + proteinWID
                + " type=" + type
                + " status=" + status
                + " id=" + id
                + " description=" + description
                + " evidence=" + evidence
                + " ref=" + ref
                + " original=" + original
                + "}\n"
                + pData;
    }
}
