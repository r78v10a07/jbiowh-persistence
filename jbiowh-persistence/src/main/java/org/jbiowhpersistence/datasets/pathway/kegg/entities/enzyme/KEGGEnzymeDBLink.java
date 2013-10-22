package org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Enzyme DBLink entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGEnzymeDBLink")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGEnzymeDBLink.findAll", query = "SELECT k FROM KEGGEnzymeDBLink k"),
    @NamedQuery(name = "KEGGEnzymeDBLink.findByKEGGEnzymeWID", query = "SELECT k FROM KEGGEnzymeDBLink k WHERE k.kEGGEnzymeDBLinkPK.kEGGEnzymeWID = :kEGGEnzymeWID"),
    @NamedQuery(name = "KEGGEnzymeDBLink.findByDb", query = "SELECT k FROM KEGGEnzymeDBLink k WHERE k.kEGGEnzymeDBLinkPK.db = :db"),
    @NamedQuery(name = "KEGGEnzymeDBLink.findById", query = "SELECT k FROM KEGGEnzymeDBLink k WHERE k.kEGGEnzymeDBLinkPK.id = :id")})
public class KEGGEnzymeDBLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KEGGEnzymeDBLinkPK kEGGEnzymeDBLinkPK;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGEnzyme_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGEnzyme kEGGEnzyme;

    public KEGGEnzymeDBLink() {
    }

    public KEGGEnzymeDBLink(KEGGEnzymeDBLinkPK KEGGEnzymeDBLinkPK) {
        this.kEGGEnzymeDBLinkPK = KEGGEnzymeDBLinkPK;
    }

    public KEGGEnzymeDBLink(long kEGGEnzymeWID, String db, String id) {
        this.kEGGEnzymeDBLinkPK = new KEGGEnzymeDBLinkPK(kEGGEnzymeWID, db, id);
    }

    public KEGGEnzyme getkEGGEnzyme() {
        return kEGGEnzyme;
    }

    public void setkEGGEnzyme(KEGGEnzyme kEGGEnzyme) {
        this.kEGGEnzyme = kEGGEnzyme;
    }

    public KEGGEnzymeDBLinkPK getKEGGEnzymeDBLinkPK() {
        return kEGGEnzymeDBLinkPK;
    }

    public void setKEGGEnzymeDBLinkPK(KEGGEnzymeDBLinkPK KEGGEnzymeDBLinkPK) {
        this.kEGGEnzymeDBLinkPK = KEGGEnzymeDBLinkPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kEGGEnzymeDBLinkPK != null ? kEGGEnzymeDBLinkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGEnzymeDBLink)) {
            return false;
        }
        KEGGEnzymeDBLink other = (KEGGEnzymeDBLink) object;
        if ((this.kEGGEnzymeDBLinkPK == null && other.kEGGEnzymeDBLinkPK != null) || (this.kEGGEnzymeDBLinkPK != null && !this.kEGGEnzymeDBLinkPK.equals(other.kEGGEnzymeDBLinkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGEnzymeDBLink{" + "KEGGEnzymeDBLinkPK=" + kEGGEnzymeDBLinkPK + '}';
    }
}
