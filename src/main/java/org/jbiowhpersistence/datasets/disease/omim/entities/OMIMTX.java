package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMTX entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jul 16, 2012
 */
@Embeddable
@Table(name = "OMIMTX")
@XmlRootElement
public class OMIMTX implements Serializable {

    @Basic(optional = false)
    @Column(name = "Tag")
    private String tag;
    @Basic(optional = false)
    @Lob
    @Column(name = "TX")
    private String tx;

    public OMIMTX() {
    }

    public OMIMTX(String tag) {
        this.tag = tag;
    }

    public OMIMTX(String tag, String tx) {
        this.tag = tag;
        this.tx = tx;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.tag != null ? this.tag.hashCode() : 0);
        hash = 53 * hash + (this.tx != null ? this.tx.hashCode() : 0);
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
        final OMIMTX other = (OMIMTX) obj;
        if ((this.tag == null) ? (other.tag != null) : !this.tag.equals(other.tag)) {
            return false;
        }
        return !((this.tx == null) ? (other.tx != null) : !this.tx.equals(other.tx));
    }

    @Override
    public String toString() {
        return "OMIMTX{tag=" + tag + ", tx=" + tx + '}';
    }
}
