package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is Gene2STS entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
 * @since Jul 27, 2011
 */
@Embeddable
@Table(name = "Gene2STS")
@XmlRootElement
public class Gene2STS implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "UniSTSID")
    private long uniSTSID;

    public Gene2STS() {
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    public long getUniSTSID() {
        return uniSTSID;
    }

    public void setUniSTSID(long uniSTSID) {
        this.uniSTSID = uniSTSID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (int) (this.geneInfoWID ^ (this.geneInfoWID >>> 32));
        hash = 13 * hash + (int) (this.uniSTSID ^ (this.uniSTSID >>> 32));
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
        final Gene2STS other = (Gene2STS) obj;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        return this.uniSTSID == other.uniSTSID;
    }

    @Override
    public String toString() {
        return "Gene2STS{" + "geneInfoWID=" + geneInfoWID + ", uniSTSID=" + uniSTSID + '}';
    }
}
