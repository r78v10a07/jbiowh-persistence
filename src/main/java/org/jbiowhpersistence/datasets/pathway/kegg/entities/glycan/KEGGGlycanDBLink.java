package org.jbiowhpersistence.datasets.pathway.kegg.entities.glycan;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Glycan DBLink entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGGlycanDBLink")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGlycanDBLink.findAll", query = "SELECT k FROM KEGGGlycanDBLink k"),
    @NamedQuery(name = "KEGGGlycanDBLink.findByKEGGGlycanWID", query = "SELECT k FROM KEGGGlycanDBLink k WHERE k.keggglycandblinkPK.kEGGGlycanWID = :kEGGGlycanWID"),
    @NamedQuery(name = "KEGGGlycanDBLink.findByDb", query = "SELECT k FROM KEGGGlycanDBLink k WHERE k.keggglycandblinkPK.db = :db"),
    @NamedQuery(name = "KEGGGlycanDBLink.findById", query = "SELECT k FROM KEGGGlycanDBLink k WHERE k.keggglycandblinkPK.id = :id")})
public class KEGGGlycanDBLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KEGGGlycanDBLinkPK keggglycandblinkPK;
    @ManyToOne
    @JoinColumn(name = "KEGGGlycan_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGGlycan kEGGGlycan;

    public KEGGGlycanDBLink() {
    }

    public KEGGGlycanDBLink(KEGGGlycanDBLinkPK keggglycandblinkPK) {
        this.keggglycandblinkPK = keggglycandblinkPK;
    }

    public KEGGGlycanDBLink(long kEGGGlycanWID, String db, String id) {
        this.keggglycandblinkPK = new KEGGGlycanDBLinkPK(kEGGGlycanWID, db, id);
    }

    public KEGGGlycan getkEGGGlycan() {
        return kEGGGlycan;
    }

    public void setkEGGGlycan(KEGGGlycan kEGGGlycan) {
        this.kEGGGlycan = kEGGGlycan;
    }

    public KEGGGlycanDBLinkPK getKeggglycandblinkPK() {
        return keggglycandblinkPK;
    }

    public void setKeggglycandblinkPK(KEGGGlycanDBLinkPK keggglycandblinkPK) {
        this.keggglycandblinkPK = keggglycandblinkPK;
    }

    public KEGGGlycanDBLinkPK getKEGGGlycanDBLinkPK() {
        return keggglycandblinkPK;
    }

    public void setKEGGGlycanDBLinkPK(KEGGGlycanDBLinkPK keggglycandblinkPK) {
        this.keggglycandblinkPK = keggglycandblinkPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keggglycandblinkPK != null ? keggglycandblinkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGGlycanDBLink)) {
            return false;
        }
        KEGGGlycanDBLink other = (KEGGGlycanDBLink) object;
        if ((this.keggglycandblinkPK == null && other.keggglycandblinkPK != null) || (this.keggglycandblinkPK != null && !this.keggglycandblinkPK.equals(other.keggglycandblinkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGlycanDBLink{" + "keggglycandblinkPK=" + keggglycandblinkPK + '}';
    }
}
