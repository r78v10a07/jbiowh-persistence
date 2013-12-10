package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankEnzymeRef entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankEnzymeRef")
@XmlRootElement
public class DrugBankEnzymeRef implements Serializable {

    @Lob
    @Column(name = "Cite")
    private String cite;
    @Lob
    @Column(name = "Link")
    private String link;

    public DrugBankEnzymeRef() {
    }

    public DrugBankEnzymeRef(String cite, String link) {
        this.cite = cite;
        this.link = link;
    }

    public String getCite() {
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.cite != null ? this.cite.hashCode() : 0);
        hash = 31 * hash + (this.link != null ? this.link.hashCode() : 0);
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
        final DrugBankEnzymeRef other = (DrugBankEnzymeRef) obj;
        if ((this.cite == null) ? (other.cite != null) : !this.cite.equals(other.cite)) {
            return false;
        }
        return !((this.link == null) ? (other.link != null) : !this.link.equals(other.link));
    }

    @Override
    public String toString() {
        return "DrugBankEnzymeRef{" + "cite=" + cite + ", link=" + link + '}';
    }
}
