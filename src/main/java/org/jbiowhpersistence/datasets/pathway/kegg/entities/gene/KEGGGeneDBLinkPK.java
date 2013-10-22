package org.jbiowhpersistence.datasets.pathway.kegg.entities.gene;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the KEGG Gene DBLink PK entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Embeddable
public class KEGGGeneDBLinkPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "KEGGGene_WID")
    private long kEGGGeneWID;
    @Basic(optional = false)
    @Column(name = "DB")
    private String db;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;

    public KEGGGeneDBLinkPK() {
    }

    public KEGGGeneDBLinkPK(long kEGGGeneWID, String db, String id) {
        this.kEGGGeneWID = kEGGGeneWID;
        this.db = db;
        this.id = id;
    }

    public long getKEGGGeneWID() {
        return kEGGGeneWID;
    }

    public void setKEGGGeneWID(long kEGGGeneWID) {
        this.kEGGGeneWID = kEGGGeneWID;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
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
        hash += (int) kEGGGeneWID;
        hash += (db != null ? db.hashCode() : 0);
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGGeneDBLinkPK)) {
            return false;
        }
        KEGGGeneDBLinkPK other = (KEGGGeneDBLinkPK) object;
        if (this.kEGGGeneWID != other.kEGGGeneWID) {
            return false;
        }
        if ((this.db == null && other.db != null) || (this.db != null && !this.db.equals(other.db))) {
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "KEGGGeneDBLinkPK{" + "kEGGGeneWID=" + kEGGGeneWID + ", db=" + db + ", id=" + id + '}';
    }
}
