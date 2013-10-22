package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the GeneInfoDBXrefsPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Embeddable
public class GeneInfoDBXrefsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "DBName")
    private String dBName;
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;

    public GeneInfoDBXrefsPK() {
    }

    public GeneInfoDBXrefsPK(long geneInfoWID, String dBName, String id) {
        this.geneInfoWID = geneInfoWID;
        this.dBName = dBName;
        this.id = id;
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    public String getDBName() {
        return dBName;
    }

    public void setDBName(String dBName) {
        this.dBName = dBName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) geneInfoWID;
        hash += (dBName != null ? dBName.hashCode() : 0);
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneInfoDBXrefsPK)) {
            return false;
        }
        GeneInfoDBXrefsPK other = (GeneInfoDBXrefsPK) object;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if ((this.dBName == null && other.dBName != null) || (this.dBName != null && !this.dBName.equals(other.dBName))) {
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.gene.entities.GeneInfoDBXrefsPK[ geneInfoWID=" + geneInfoWID + ", dBName=" + dBName + ", id=" + id + " ]";
    }
}
