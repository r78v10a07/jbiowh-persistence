package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankExternalLink entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankExternalLink")
@XmlRootElement
public class DrugBankExternalLink implements Serializable {

    @Column(name = "Resource")
    private String resource;
    @Lob
    @Column(name = "URL")
    private String url;

    public DrugBankExternalLink() {
    }

    public DrugBankExternalLink(String resource, String url) {
        this.resource = resource;
        this.url = url;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.resource != null ? this.resource.hashCode() : 0);
        hash = 97 * hash + (this.url != null ? this.url.hashCode() : 0);
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
        final DrugBankExternalLink other = (DrugBankExternalLink) obj;
        if ((this.resource == null) ? (other.resource != null) : !this.resource.equals(other.resource)) {
            return false;
        }
        return !((this.url == null) ? (other.url != null) : !this.url.equals(other.url));
    }

    @Override
    public String toString() {
        return "DrugBankExternalLink{" + "resource=" + resource + ", url=" + url + '}';
    }
}
