package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMSA entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jul 20, 2012
 */
@Embeddable
@Table(name = "OMIMSA")
@XmlRootElement
public class OMIMSA implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "SA")
    private String sa;

    public OMIMSA() {
    }

    public OMIMSA(String sa) {
        this.sa = sa;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.sa != null ? this.sa.hashCode() : 0);
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
        final OMIMSA other = (OMIMSA) obj;
        return !((this.sa == null) ? (other.sa != null) : !this.sa.equals(other.sa));
    }

    @Override
    public String toString() {
        return "OMIMSA{sa=" + sa + '}';
    }
}
