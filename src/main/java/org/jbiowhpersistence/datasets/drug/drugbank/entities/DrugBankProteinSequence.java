package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the DrugBankProteinSequence entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Oct 6, 2011
 */
@Embeddable
@Table(name = "DrugBankProteinSequence")
@XmlRootElement
public class DrugBankProteinSequence implements Serializable {

    @Column(name = "Header")
    private String header;
    @Lob
    @Column(name = "Chain")
    private String chain;

    public DrugBankProteinSequence() {
    }

    public DrugBankProteinSequence(String header, String chain) {
        this.header = header;
        this.chain = chain;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.header != null ? this.header.hashCode() : 0);
        hash = 53 * hash + (this.chain != null ? this.chain.hashCode() : 0);
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
        final DrugBankProteinSequence other = (DrugBankProteinSequence) obj;
        if ((this.header == null) ? (other.header != null) : !this.header.equals(other.header)) {
            return false;
        }
        return !((this.chain == null) ? (other.chain != null) : !this.chain.equals(other.chain));
    }

    @Override
    public String toString() {
        return "DrugBankProteinSequence{" + "header=" + header + ", chain=" + chain + '}';
    }
}
