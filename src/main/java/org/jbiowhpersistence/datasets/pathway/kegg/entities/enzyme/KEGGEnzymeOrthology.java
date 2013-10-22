package org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the KEGG Enzyme Orthology entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since May 14, 2012
 */
@Entity
@Table(name = "KEGGEnzymeOrthology")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGEnzymeOrthology.findAll", query = "SELECT k FROM KEGGEnzymeOrthology k"),
    @NamedQuery(name = "KEGGEnzymeOrthology.findByKEGGEnzymeWID", query = "SELECT k FROM KEGGEnzymeOrthology k WHERE k.kEGGEnzymeOrthologyPK.kEGGEnzymeWID = :kEGGEnzymeWID"),
    @NamedQuery(name = "KEGGEnzymeOrthology.findByEntry", query = "SELECT k FROM KEGGEnzymeOrthology k WHERE k.kEGGEnzymeOrthologyPK.entry = :entry"),
    @NamedQuery(name = "KEGGEnzymeOrthology.findByName", query = "SELECT k FROM KEGGEnzymeOrthology k WHERE k.name = :name")})
public class KEGGEnzymeOrthology implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KEGGEnzymeOrthologyPK kEGGEnzymeOrthologyPK;
    @Column(name = "Name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGEnzyme_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGEnzyme kEGGEnzyme;

    public KEGGEnzymeOrthology() {
    }

    public KEGGEnzymeOrthology(KEGGEnzymeOrthologyPK kEGGEnzymeOrthologyPK) {
        this.kEGGEnzymeOrthologyPK = kEGGEnzymeOrthologyPK;
    }

    public KEGGEnzymeOrthology(long kEGGEnzymeWID, String entry) {
        this.kEGGEnzymeOrthologyPK = new KEGGEnzymeOrthologyPK(kEGGEnzymeWID, entry);
    }

    public KEGGEnzyme getkEGGEnzyme() {
        return kEGGEnzyme;
    }

    public void setkEGGEnzyme(KEGGEnzyme kEGGEnzyme) {
        this.kEGGEnzyme = kEGGEnzyme;
    }

    public KEGGEnzymeOrthologyPK getKEGGEnzymeOrthologyPK() {
        return kEGGEnzymeOrthologyPK;
    }

    public void setKEGGEnzymeOrthologyPK(KEGGEnzymeOrthologyPK kEGGEnzymeOrthologyPK) {
        this.kEGGEnzymeOrthologyPK = kEGGEnzymeOrthologyPK;
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
        hash += (kEGGEnzymeOrthologyPK != null ? kEGGEnzymeOrthologyPK.hashCode() : 0);
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
        final KEGGEnzymeOrthology other = (KEGGEnzymeOrthology) obj;
        if (!Objects.equals(this.kEGGEnzymeOrthologyPK, other.kEGGEnzymeOrthologyPK)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGEnzymeOrthology{" + "kEGGEnzymeOrthologyPK=" + kEGGEnzymeOrthologyPK + ", name=" + name + '}';
    }
}
