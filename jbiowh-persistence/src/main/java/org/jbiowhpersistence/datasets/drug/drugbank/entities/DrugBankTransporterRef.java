package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankTransporterRef entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankTransporterRef")
@XmlRootElement
public class DrugBankTransporterRef implements Serializable {

    @Lob
    @Column(name = "Cite")
    private String cite;
    @Lob
    @Column(name = "Link")
    private String link;

    public DrugBankTransporterRef() {
    }

    public DrugBankTransporterRef(String cite, String link) {
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
        hash = 97 * hash + (this.cite != null ? this.cite.hashCode() : 0);
        hash = 97 * hash + (this.link != null ? this.link.hashCode() : 0);
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
        final DrugBankTransporterRef other = (DrugBankTransporterRef) obj;
        if ((this.cite == null) ? (other.cite != null) : !this.cite.equals(other.cite)) {
            return false;
        }
        return !((this.link == null) ? (other.link != null) : !this.link.equals(other.link));
    }

    @Override
    public String toString() {
        return "DrugBankTransporterRef{" + "cite=" + cite + ", link=" + link + '}';
    }
}
