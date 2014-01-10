package org.jbiowhpersistence.datasets.gene.genebank.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the GeneBankCDSDBXref entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since May 2, 2013
 */
@Embeddable
public class GeneBankCDSDBXref implements Serializable {

    @Basic(optional = false)
    @Column(name = "DBXrefs")
    private String dBXref;
    @Basic(optional = false)
    @Column(name = "DBIdent")
    private String dBIdent;

    public GeneBankCDSDBXref() {
    }

    public GeneBankCDSDBXref(String dBXref, String dBIdent) {
        this.dBXref = dBXref;
        this.dBIdent = dBIdent;
    }

    public String getdBXref() {
        return dBXref;
    }

    public void setdBXref(String dBXref) {
        this.dBXref = dBXref;
    }

    public String getdBIdent() {
        return dBIdent;
    }

    public void setdBIdent(String dBIdent) {
        this.dBIdent = dBIdent;
    }

    @Override
    public String toString() {
        return "GeneBankCDSDBXref{" + "dBXref=" + dBXref + ", dBIdent=" + dBIdent + '}';
    }
}
