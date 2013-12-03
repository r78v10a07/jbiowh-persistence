package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMAV entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jul 16, 2012
 */
@Embeddable
@Table(name = "OMIMAV")
@XmlRootElement
public class OMIMAV implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "AV")
    private String av;

    public OMIMAV() {
    }

    public OMIMAV(String av) {
        this.av = av;
    }

    public String getAv() {
        return av;
    }

    public void setAv(String av) {
        this.av = av;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.av != null ? this.av.hashCode() : 0);
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
        final OMIMAV other = (OMIMAV) obj;
        return !((this.av == null) ? (other.av != null) : !this.av.equals(other.av));
    }

    @Override
    public String toString() {
        return "OMIMAV{av=" + av + '}';
    }
}
