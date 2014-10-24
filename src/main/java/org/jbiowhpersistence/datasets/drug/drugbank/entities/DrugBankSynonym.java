package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankSynonym entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankSynonym")
@XmlRootElement
public class DrugBankSynonym implements Serializable {

    @Column(name = "Synonym")
    private String synonym;
    @Column(name = "Language")
    private String language;
    @Column(name = "Coder")
    private String coder;

    public DrugBankSynonym() {
    }

    public DrugBankSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCoder() {
        return coder;
    }

    public void setCoder(String coder) {
        this.coder = coder;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.synonym);
        hash = 31 * hash + Objects.hashCode(this.language);
        hash = 31 * hash + Objects.hashCode(this.coder);
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
        final DrugBankSynonym other = (DrugBankSynonym) obj;
        if (!Objects.equals(this.synonym, other.synonym)) {
            return false;
        }
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        return Objects.equals(this.coder, other.coder);
    }

    @Override
    public String toString() {
        return "DrugBankSynonym{" + "synonym=" + synonym + ", language=" + language + ", coder=" + coder + '}';
    }
}
