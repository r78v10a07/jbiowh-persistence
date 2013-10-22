package org.jbiowhpersistence.datasets.pathway.kegg.entities.gene;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Gene DBLink entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGGeneDBLink")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGeneDBLink.findAll", query = "SELECT k FROM KEGGGeneDBLink k"),
    @NamedQuery(name = "KEGGGeneDBLink.findByKEGGGeneWID", query = "SELECT k FROM KEGGGeneDBLink k WHERE k.kegggenedblinkPK.kEGGGeneWID = :kEGGGeneWID"),
    @NamedQuery(name = "KEGGGeneDBLink.findByDb", query = "SELECT k FROM KEGGGeneDBLink k WHERE k.kegggenedblinkPK.db = :db"),
    @NamedQuery(name = "KEGGGeneDBLink.findById", query = "SELECT k FROM KEGGGeneDBLink k WHERE k.kegggenedblinkPK.id = :id")})
public class KEGGGeneDBLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KEGGGeneDBLinkPK kegggenedblinkPK;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGGene_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGGene kEGGGene;

    public KEGGGeneDBLink() {
    }

    public KEGGGeneDBLink(KEGGGeneDBLinkPK kegggenedblinkPK) {
        this.kegggenedblinkPK = kegggenedblinkPK;
    }

    public KEGGGeneDBLink(long kEGGGeneWID, String db, String id) {
        this.kegggenedblinkPK = new KEGGGeneDBLinkPK(kEGGGeneWID, db, id);
    }

    public KEGGGene getkEGGGene() {
        return kEGGGene;
    }

    public void setkEGGGene(KEGGGene kEGGGene) {
        this.kEGGGene = kEGGGene;
    }

    public KEGGGeneDBLinkPK getKegggenedblinkPK() {
        return kegggenedblinkPK;
    }

    public void setKegggenedblinkPK(KEGGGeneDBLinkPK kegggenedblinkPK) {
        this.kegggenedblinkPK = kegggenedblinkPK;
    }

    public KEGGGeneDBLinkPK getKEGGGeneDBLinkPK() {
        return kegggenedblinkPK;
    }

    public void setKEGGGeneDBLinkPK(KEGGGeneDBLinkPK kegggenedblinkPK) {
        this.kegggenedblinkPK = kegggenedblinkPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kegggenedblinkPK != null ? kegggenedblinkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGGeneDBLink)) {
            return false;
        }
        KEGGGeneDBLink other = (KEGGGeneDBLink) object;
        if ((this.kegggenedblinkPK == null && other.kegggenedblinkPK != null) || (this.kegggenedblinkPK != null && !this.kegggenedblinkPK.equals(other.kegggenedblinkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGeneDBLink{" + "kegggenedblinkPK=" + kegggenedblinkPK + '}';
    }
}
