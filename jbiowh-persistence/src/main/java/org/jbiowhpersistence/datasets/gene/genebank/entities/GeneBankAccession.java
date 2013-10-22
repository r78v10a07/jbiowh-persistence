package org.jbiowhpersistence.datasets.gene.genebank.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the GeneBankAccession entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 2, 2013
 */
@Embeddable
public class GeneBankAccession implements Serializable {

    @Basic(optional = false)
    @Column(name = "Accession")
    private String accession;

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    @Override
    public String toString() {
        return "GeneBankAccession{" + "accession=" + accession + '}';
    }
}
