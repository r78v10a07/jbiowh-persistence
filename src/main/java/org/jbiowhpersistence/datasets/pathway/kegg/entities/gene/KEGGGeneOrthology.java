package org.jbiowhpersistence.datasets.pathway.kegg.entities.gene;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is THE KEGG Gene Orthology entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since May 14, 2012
 */
@Entity
@Table(name = "KEGGGeneOrthology")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGeneOrthology.findAll", query = "SELECT k FROM KEGGGeneOrthology k"),
    @NamedQuery(name = "KEGGGeneOrthology.findByKEGGGeneWID", query = "SELECT k FROM KEGGGeneOrthology k WHERE k.kEGGGeneOrthologyPK.kEGGGeneWID = :kEGGGeneWID"),
    @NamedQuery(name = "KEGGGeneOrthology.findByEntry", query = "SELECT k FROM KEGGGeneOrthology k WHERE k.kEGGGeneOrthologyPK.entry = :entry"),
    @NamedQuery(name = "KEGGGeneOrthology.findByName", query = "SELECT k FROM KEGGGeneOrthology k WHERE k.name = :name")})
public class KEGGGeneOrthology implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KEGGGeneOrthologyPK kEGGGeneOrthologyPK;
    @Column(name = "Name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGGene_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGGene kEGGGene;

    public KEGGGeneOrthology() {
    }

    public KEGGGeneOrthology(KEGGGeneOrthologyPK kEGGGeneOrthologyPK) {
        this.kEGGGeneOrthologyPK = kEGGGeneOrthologyPK;
    }

    public KEGGGeneOrthology(long kEGGGeneWID, String entry) {
        this.kEGGGeneOrthologyPK = new KEGGGeneOrthologyPK(kEGGGeneWID, entry);
    }

    public KEGGGene getkEGGGene() {
        return kEGGGene;
    }

    public void setkEGGGene(KEGGGene kEGGGene) {
        this.kEGGGene = kEGGGene;
    }

    public KEGGGeneOrthologyPK getKEGGGeneOrthologyPK() {
        return kEGGGeneOrthologyPK;
    }

    public void setKEGGGeneOrthologyPK(KEGGGeneOrthologyPK kEGGGeneOrthologyPK) {
        this.kEGGGeneOrthologyPK = kEGGGeneOrthologyPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kEGGGeneOrthologyPK != null ? kEGGGeneOrthologyPK.hashCode() : 0);
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
        final KEGGGeneOrthology other = (KEGGGeneOrthology) obj;
        if (!Objects.equals(this.kEGGGeneOrthologyPK, other.kEGGGeneOrthologyPK)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGeneOrthology{" + "kEGGGeneOrthologyPK=" + kEGGGeneOrthologyPK + ", name=" + name + '}';
    }
}
