package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the GeneInfoSynonyms entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jul 27, 2011
 */
@Embeddable
@Table(name = "GeneInfoSynonyms")
@XmlRootElement
public class GeneInfoSynonyms implements Serializable {

    @Basic(optional = false)
    @Column(name = "Synonyms")
    private String synonyms;

    public GeneInfoSynonyms() {
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.synonyms != null ? this.synonyms.hashCode() : 0);
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
        final GeneInfoSynonyms other = (GeneInfoSynonyms) obj;
        return !((this.synonyms == null) ? (other.synonyms != null) : !this.synonyms.equals(other.synonyms));
    }

    @Override
    public String toString() {
        return "GeneInfoSynonyms{synonyms=" + synonyms + '}';
    }
}
