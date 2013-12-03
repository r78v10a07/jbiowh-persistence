package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMED entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jul 16, 2012
 */
@Embeddable
@Table(name = "OMIMED")
@XmlRootElement
public class OMIMED implements Serializable {

    @Basic(optional = false)
    @Column(name = "ED")
    private String ed;

    public OMIMED() {
    }

    public OMIMED(String ed) {
        this.ed = ed;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.ed != null ? this.ed.hashCode() : 0);
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
        final OMIMED other = (OMIMED) obj;
        return !((this.ed == null) ? (other.ed != null) : !this.ed.equals(other.ed));
    }

    @Override
    public String toString() {
        return "OMIMED{ed=" + ed + '}';
    }
}
