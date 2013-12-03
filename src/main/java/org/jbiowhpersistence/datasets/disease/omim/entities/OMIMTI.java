package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMTI entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jul 16, 2012
 */
@Embeddable
@Table(name = "OMIMTI")
@XmlRootElement
public class OMIMTI implements Serializable {

    @Basic(optional = false)
    @Column(name = "TI")
    private String ti;

    public OMIMTI() {
    }

    public OMIMTI(String ti) {
        this.ti = ti;
    }

    public String getTi() {
        return ti;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.ti != null ? this.ti.hashCode() : 0);
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
        final OMIMTI other = (OMIMTI) obj;
        return !((this.ti == null) ? (other.ti != null) : !this.ti.equals(other.ti));
    }

    @Override
    public String toString() {
        return "OMIMTI{ti=" + ti + '}';
    }
}
