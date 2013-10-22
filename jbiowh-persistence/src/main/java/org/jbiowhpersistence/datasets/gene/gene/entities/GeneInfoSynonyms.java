package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the GeneInfoSynonyms entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $ 
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "GeneInfoSynonyms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneInfoSynonyms.findAll", query = "SELECT g FROM GeneInfoSynonyms g"),
    @NamedQuery(name = "GeneInfoSynonyms.findByGeneInfoWID", query = "SELECT g FROM GeneInfoSynonyms g WHERE g.geneInfoSynonymsPK.geneInfoWID = :geneInfoWID"),
    @NamedQuery(name = "GeneInfoSynonyms.findBySynonyms", query = "SELECT g FROM GeneInfoSynonyms g WHERE g.geneInfoSynonymsPK.synonyms = :synonyms")})
public class GeneInfoSynonyms implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GeneInfoSynonymsPK geneInfoSynonymsPK;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneInfo_WID", insertable = false, unique = false, nullable = false, updatable = false)
    private GeneInfo geneInfo;

    public GeneInfoSynonyms() {
    }

    public GeneInfoSynonyms(GeneInfoSynonymsPK geneInfoSynonymsPK) {
        this.geneInfoSynonymsPK = geneInfoSynonymsPK;
    }

    public GeneInfoSynonyms(long geneInfoWID, String synonyms) {
        this.geneInfoSynonymsPK = new GeneInfoSynonymsPK(geneInfoWID, synonyms);
    }

    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }

    public GeneInfoSynonymsPK getGeneInfoSynonymsPK() {
        return geneInfoSynonymsPK;
    }

    public void setGeneInfoSynonymsPK(GeneInfoSynonymsPK geneInfoSynonymsPK) {
        this.geneInfoSynonymsPK = geneInfoSynonymsPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geneInfoSynonymsPK != null ? geneInfoSynonymsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneInfoSynonyms)) {
            return false;
        }
        GeneInfoSynonyms other = (GeneInfoSynonyms) object;
        if ((this.geneInfoSynonymsPK == null && other.geneInfoSynonymsPK != null) || (this.geneInfoSynonymsPK != null && !this.geneInfoSynonymsPK.equals(other.geneInfoSynonymsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneInfoWIDSynonyms{"
                + " geneWID=" + geneInfoSynonymsPK.getGeneInfoWID()
                + " synonyms=" + geneInfoSynonymsPK.getSynonyms()
                + '}';
    }
}
