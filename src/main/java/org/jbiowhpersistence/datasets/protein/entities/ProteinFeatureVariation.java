package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Feature Variation entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinFeatureVariation")
@XmlRootElement
public class ProteinFeatureVariation implements Serializable {

    @Lob
    @Column(name = "Variation")
    private String variation;

    public ProteinFeatureVariation() {
    }

    public ProteinFeatureVariation(String variation) {
        this.variation = variation;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.variation != null ? this.variation.hashCode() : 0);
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
        final ProteinFeatureVariation other = (ProteinFeatureVariation) obj;
        return !((this.variation == null) ? (other.variation != null) : !this.variation.equals(other.variation));
    }

    @Override
    public String toString() {
        return "ProteinFeatureVariation{variation=" + variation + '}';
    }
}
