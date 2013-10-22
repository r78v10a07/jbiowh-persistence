package org.jbiowhpersistence.datasets.gene.genebank.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the GeneBankFeatures entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 2, 2013
 */
@Embeddable
public class GeneBankFeatures implements Serializable {

    @Basic(optional = false)
    @Column(name = "KeyName")
    private String keyName;
    @Basic(optional = false)
    @Column(name = "Location")
    private String location;
    @Basic(optional = true)
    @Column(name = "Gi")
    private Long gi;
    @Column(name = "Product")
    private String product;
    @Column(name = "Gene")
    private String gene;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getGi() {
        return gi;
    }

    public void setGi(Long gi) {
        this.gi = gi;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String Gene) {
        this.gene = Gene;
    }

    @Override
    public String toString() {
        return "GeneBankFeatures{" + "keyName=" + keyName + ", location=" + location + ", gi=" + gi + ", product=" + product + ", Gene=" + gene + '}';
    }
}
