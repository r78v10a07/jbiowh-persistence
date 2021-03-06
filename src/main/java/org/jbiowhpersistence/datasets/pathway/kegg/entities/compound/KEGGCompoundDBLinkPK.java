package org.jbiowhpersistence.datasets.pathway.kegg.entities.compound;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is the KEGG Compound DBLink PK entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Embeddable
public class KEGGCompoundDBLinkPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "KEGGCompound_WID")
    private long kEGGCompoundWID;
    @Basic(optional = false)
    @Column(name = "DB")
    private String db;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;

    public KEGGCompoundDBLinkPK() {
    }

    public KEGGCompoundDBLinkPK(long kEGGCompoundWID, String db, String id) {
        this.kEGGCompoundWID = kEGGCompoundWID;
        this.db = db;
        this.id = id;
    }

    public long getKEGGCompoundWID() {
        return kEGGCompoundWID;
    }

    public void setKEGGCompoundWID(long kEGGCompoundWID) {
        this.kEGGCompoundWID = kEGGCompoundWID;
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
        hash += (int) kEGGCompoundWID;
        hash += (db != null ? db.hashCode() : 0);
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGCompoundDBLinkPK)) {
            return false;
        }
        KEGGCompoundDBLinkPK other = (KEGGCompoundDBLinkPK) object;
        if (this.kEGGCompoundWID != other.kEGGCompoundWID) {
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

    @Override
    public String toString() {
        return "KEGGCompoundDBLinkPK{" + "kEGGCompoundWID=" + kEGGCompoundWID + ", db=" + db + ", id=" + id + '}';
    }
}
