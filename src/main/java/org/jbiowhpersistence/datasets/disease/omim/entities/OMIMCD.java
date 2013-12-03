package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMCD entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jul 16, 2012
 */
@Embeddable
@Table(name = "OMIMCD")
@XmlRootElement
public class OMIMCD implements Serializable {

    @Basic(optional = false)
    @Column(name = "CD")
    private String cd;

    public OMIMCD() {
    }

    public OMIMCD(String cd) {
        this.cd = cd;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.cd != null ? this.cd.hashCode() : 0);
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
        final OMIMCD other = (OMIMCD) obj;
        return !((this.cd == null) ? (other.cd != null) : !this.cd.equals(other.cd));
    }

    @Override
    public String toString() {
        return "OMIMCD{cd=" + cd + '}';
    }
}
