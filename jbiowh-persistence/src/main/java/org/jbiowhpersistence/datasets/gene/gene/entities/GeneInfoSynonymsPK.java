package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the GeneInfoSynonymsPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Embeddable
public class GeneInfoSynonymsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "GeneInfo_WID")
    private long geneInfoWID;
    @Basic(optional = false)
    @Column(name = "Synonyms")
    private String synonyms;

    public GeneInfoSynonymsPK() {
    }

    public GeneInfoSynonymsPK(long geneInfoWID, String synonyms) {
        this.geneInfoWID = geneInfoWID;
        this.synonyms = synonyms;
    }

    public long getGeneInfoWID() {
        return geneInfoWID;
    }

    public void setGeneInfoWID(long geneInfoWID) {
        this.geneInfoWID = geneInfoWID;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) geneInfoWID;
        hash += (synonyms != null ? synonyms.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneInfoSynonymsPK)) {
            return false;
        }
        GeneInfoSynonymsPK other = (GeneInfoSynonymsPK) object;
        if (this.geneInfoWID != other.geneInfoWID) {
            return false;
        }
        if ((this.synonyms == null && other.synonyms != null) || (this.synonyms != null && !this.synonyms.equals(other.synonyms))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.gene.entities.GeneInfoSynonymsPK[ geneInfoWID=" + geneInfoWID + ", synonyms=" + synonyms + " ]";
    }
}
