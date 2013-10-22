package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Feature Variation entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinFeatureVariation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinFeatureVariation.findAll", query = "SELECT p FROM ProteinFeatureVariation p"),
    @NamedQuery(name = "ProteinFeatureVariation.findByWid", query = "SELECT p FROM ProteinFeatureVariation p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinFeatureVariation.findByProteinFeatureWID", query = "SELECT p FROM ProteinFeatureVariation p WHERE p.proteinFeatureWID = :proteinFeatureWID")})
public class ProteinFeatureVariation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "ProteinFeature_WID")
    private long proteinFeatureWID;
    @Lob
    @Column(name = "Variation")
    private String variation;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinFeature_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinFeature proteinFeature;

    public ProteinFeatureVariation() {
    }

    public ProteinFeatureVariation(Long wid) {
        this.wid = wid;
    }

    public ProteinFeatureVariation(Long wid, long proteinFeatureWID) {
        this.wid = wid;
        this.proteinFeatureWID = proteinFeatureWID;
    }

    public ProteinFeature getProteinFeature() {
        return proteinFeature;
    }

    public void setProteinFeature(ProteinFeature proteinFeature) {
        this.proteinFeature = proteinFeature;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getProteinFeatureWID() {
        return proteinFeatureWID;
    }

    public void setProteinFeatureWID(long proteinFeatureWID) {
        this.proteinFeatureWID = proteinFeatureWID;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinFeatureVariation other = (ProteinFeatureVariation) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.proteinFeatureWID != other.proteinFeatureWID) {
            return false;
        }
        if (!Objects.equals(this.variation, other.variation)) {
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
        return "ProteinFeatureVariation{"
                + " wid=" + wid
                + " featureWID=" + proteinFeatureWID
                + " variation=" + variation
                + '}';
    }
}
