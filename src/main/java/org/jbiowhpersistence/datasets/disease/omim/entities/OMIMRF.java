package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMRF entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jul 16, 2012
 */
@Embeddable
@Table(name = "OMIMRF")
@XmlRootElement
public class OMIMRF implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "Reference")
    private String reference;

    public OMIMRF() {
    }

    public OMIMRF(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.reference != null ? this.reference.hashCode() : 0);
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
        final OMIMRF other = (OMIMRF) obj;
        return !((this.reference == null) ? (other.reference != null) : !this.reference.equals(other.reference));
    }

    @Override
    public String toString() {
        return "OMIMRF{reference=" + reference + '}';
    }
}
