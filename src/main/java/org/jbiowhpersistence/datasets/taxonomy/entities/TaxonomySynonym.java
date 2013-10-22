package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the TaxonomySynonym entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 21, 2011
 */
@Entity
@Table(name = "TaxonomySynonym")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxonomySynonym.findAll", query = "SELECT t FROM TaxonomySynonym t"),
    @NamedQuery(name = "TaxonomySynonym.findByTaxonomyWID", query = "SELECT t FROM TaxonomySynonym t WHERE t.taxonomySynonymPK.taxonomyWID = :taxonomyWID"),
    @NamedQuery(name = "TaxonomySynonym.findBySynonym", query = "SELECT t FROM TaxonomySynonym t WHERE t.taxonomySynonymPK.synonym like :synonym GROUP BY t.taxonomySynonymPK.taxonomyWID"),
    @NamedQuery(name = "TaxonomySynonym.findTaxonomyBySynonym", query = "SELECT t.taxonomy FROM TaxonomySynonym t WHERE t.taxonomySynonymPK.synonym like :synonym GROUP BY t.taxonomy"),
    @NamedQuery(name = "TaxonomySynonym.findByTaxonomySynonymNameClassWID", query = "SELECT t FROM TaxonomySynonym t WHERE t.taxonomySynonymPK.taxonomySynonymNameClassWID = :taxonomySynonymNameClassWID"),
    @NamedQuery(name = "TaxonomySynonym.findBySynonymAndNameClass", query = "SELECT t FROM TaxonomySynonym t WHERE t.taxonomySynonymPK.synonym like :synonym and t.nameClass.nameClass = :nameClass")})
public class TaxonomySynonym implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TaxonomySynonymPK taxonomySynonymPK;
    /*
     * TaxonomySynonym relationship
     */
    @ManyToOne
    private Taxonomy taxonomy;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TaxonomySynonymNameClass_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private TaxonomySynonymNameClass nameClass;

    public TaxonomySynonym() {
    }

    public TaxonomySynonym(TaxonomySynonymPK taxonomySynonymPK) {
        this.taxonomySynonymPK = taxonomySynonymPK;
    }

    public TaxonomySynonym(long taxonomyWID, String synonym, long taxonomySynonymNameClassWID) {
        this.taxonomySynonymPK = new TaxonomySynonymPK(taxonomyWID, synonym, taxonomySynonymNameClassWID);
    }

    public TaxonomySynonymNameClass getNameClass() {
        return nameClass;
    }

    public void setNameClass(TaxonomySynonymNameClass nameClass) {
        this.nameClass = nameClass;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    public TaxonomySynonymPK getTaxonomySynonymPK() {
        return taxonomySynonymPK;
    }

    public void setTaxonomySynonymPK(TaxonomySynonymPK taxonomySynonymPK) {
        this.taxonomySynonymPK = taxonomySynonymPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxonomySynonymPK != null ? taxonomySynonymPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TaxonomySynonym)) {
            return false;
        }
        TaxonomySynonym other = (TaxonomySynonym) object;
        if ((this.taxonomySynonymPK == null && other.taxonomySynonymPK != null) || (this.taxonomySynonymPK != null && !this.taxonomySynonymPK.equals(other.taxonomySynonymPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TaxonomyWIDSynonym[ taxonomyWID=" + taxonomySynonymPK.getTaxonomyWID()
                + ", synonym= " + taxonomySynonymPK.getSynonym()
                + ", NameClassWID= " + taxonomySynonymPK.getTaxonomySynonymNameClassWID()
                + ", NameClass= " + nameClass.getNameClass()
                + "]";
    }
}
