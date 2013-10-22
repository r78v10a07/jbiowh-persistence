package org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class is KEGG Glycan DBLink PK entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Embeddable
public class KEGGGlycanDBLinkPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "KEGGGlycan_WID")
    private long kEGGGlycanWID;
    @Basic(optional = false)
    @Column(name = "DB")
    private String db;
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;

    public KEGGGlycanDBLinkPK() {
    }

    public KEGGGlycanDBLinkPK(long kEGGGlycanWID, String db, String id) {
        this.kEGGGlycanWID = kEGGGlycanWID;
        this.db = db;
        this.id = id;
    }

    public long getKEGGGlycanWID() {
        return kEGGGlycanWID;
    }

    public void setKEGGGlycanWID(long kEGGGlycanWID) {
        this.kEGGGlycanWID = kEGGGlycanWID;
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
        hash += (int) kEGGGlycanWID;
        hash += (db != null ? db.hashCode() : 0);
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGGlycanDBLinkPK)) {
            return false;
        }
        KEGGGlycanDBLinkPK other = (KEGGGlycanDBLinkPK) object;
        if (this.kEGGGlycanWID != other.kEGGGlycanWID) {
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
        return "KEGGGlycanDBLinkPK{" + "kEGGGlycanWID=" + kEGGGlycanWID + ", db=" + db + ", id=" + id + '}';
    }
}
