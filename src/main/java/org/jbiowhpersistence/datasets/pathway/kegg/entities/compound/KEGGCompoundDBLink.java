package org.jbiowhpersistence.datasets.pathway.kegg.entities.compound;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Compound DBLink entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGCompoundDBLink")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGCompoundDBLink.findAll", query = "SELECT k FROM KEGGCompoundDBLink k"),
    @NamedQuery(name = "KEGGCompoundDBLink.findByKEGGCompoundWID", query = "SELECT k FROM KEGGCompoundDBLink k WHERE k.keggcompounddblinkPK.kEGGCompoundWID = :kEGGCompoundWID"),
    @NamedQuery(name = "KEGGCompoundDBLink.findByDb", query = "SELECT k FROM KEGGCompoundDBLink k WHERE k.keggcompounddblinkPK.db = :db"),
    @NamedQuery(name = "KEGGCompoundDBLink.findById", query = "SELECT k FROM KEGGCompoundDBLink k WHERE k.keggcompounddblinkPK.id = :id")})
public class KEGGCompoundDBLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KEGGCompoundDBLinkPK keggcompounddblinkPK;
    @ManyToOne
    @JoinColumn(name = "KEGGCompound_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGCompound kEGGCompound;

    public KEGGCompoundDBLink() {
    }

    public KEGGCompoundDBLink(KEGGCompoundDBLinkPK keggcompounddblinkPK) {
        this.keggcompounddblinkPK = keggcompounddblinkPK;
    }

    public KEGGCompoundDBLink(long kEGGCompoundWID, String db, String id) {
        this.keggcompounddblinkPK = new KEGGCompoundDBLinkPK(kEGGCompoundWID, db, id);
    }

    public KEGGCompound getkEGGCompound() {
        return kEGGCompound;
    }

    public void setkEGGCompound(KEGGCompound kEGGCompound) {
        this.kEGGCompound = kEGGCompound;
    }

    public KEGGCompoundDBLinkPK getKEGGCompoundDBLinkPK() {
        return keggcompounddblinkPK;
    }

    public void setKEGGCompoundDBLinkPK(KEGGCompoundDBLinkPK keggcompounddblinkPK) {
        this.keggcompounddblinkPK = keggcompounddblinkPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keggcompounddblinkPK != null ? keggcompounddblinkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGCompoundDBLink)) {
            return false;
        }
        KEGGCompoundDBLink other = (KEGGCompoundDBLink) object;
        if ((this.keggcompounddblinkPK == null && other.keggcompounddblinkPK != null) || (this.keggcompounddblinkPK != null && !this.keggcompounddblinkPK.equals(other.keggcompounddblinkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGCompoundDBLink{" + "keggcompounddblinkPK=" + keggcompounddblinkPK + '}';
    }
}
