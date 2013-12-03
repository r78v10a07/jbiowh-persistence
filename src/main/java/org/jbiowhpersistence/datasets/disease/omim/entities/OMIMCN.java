package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMCN entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jul 16, 2012
 */
@Embeddable
@Table(name = "OMIMCN")
@XmlRootElement
public class OMIMCN implements Serializable {

    @Basic(optional = false)
    @Column(name = "CN")
    private String cn;

    public OMIMCN() {
    }

    public OMIMCN(String cn) {
        this.cn = cn;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.cn != null ? this.cn.hashCode() : 0);
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
        final OMIMCN other = (OMIMCN) obj;
        return !((this.cn == null) ? (other.cn != null) : !this.cn.equals(other.cn));
    }

    @Override
    public String toString() {
        return "OMIMCN{cn=" + cn + '}';
    }    
}
