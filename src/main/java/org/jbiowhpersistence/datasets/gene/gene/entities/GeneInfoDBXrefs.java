package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the GeneInfoDBXrefs entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jul 27, 2011
 */
@Embeddable
@Table(name = "GeneInfoDBXrefs")
@XmlRootElement
public class GeneInfoDBXrefs implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "DBName")
    private String dBName;
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;

    public GeneInfoDBXrefs() {
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    public String getdBName() {
        return dBName;
    }

    public void setdBName(String dBName) {
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
        int hash = 7;
        hash = 67 * hash + (int) (this.geneInfoWID ^ (this.geneInfoWID >>> 32));
        hash = 67 * hash + (this.dBName != null ? this.dBName.hashCode() : 0);
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final GeneInfoDBXrefs other = (GeneInfoDBXrefs) obj;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if ((this.dBName == null) ? (other.dBName != null) : !this.dBName.equals(other.dBName)) {
            return false;
        }
        return !((this.id == null) ? (other.id != null) : !this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "GeneInfoDBXrefs{" + "geneInfoWID=" + geneInfoWID + ", dBName=" + dBName + ", id=" + id + '}';
    }

}
