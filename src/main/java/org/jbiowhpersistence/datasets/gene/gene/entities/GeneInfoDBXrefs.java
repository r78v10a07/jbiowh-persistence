package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the GeneInfoDBXrefs entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 27, 2011
 */
@Entity
@Table(name = "GeneInfoDBXrefs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneInfoDBXrefs.findAll", query = "SELECT g FROM GeneInfoDBXrefs g"),
    @NamedQuery(name = "GeneInfoDBXrefs.findByGeneInfoWID", query = "SELECT g FROM GeneInfoDBXrefs g WHERE g.geneInfoDBXrefsPK.geneInfoWID = :geneInfoWID"),
    @NamedQuery(name = "GeneInfoDBXrefs.findByDBName", query = "SELECT g FROM GeneInfoDBXrefs g WHERE g.geneInfoDBXrefsPK.dBName = :dBName"),
    @NamedQuery(name = "GeneInfoDBXrefs.findById", query = "SELECT g FROM GeneInfoDBXrefs g WHERE g.geneInfoDBXrefsPK.id = :id")})
public class GeneInfoDBXrefs implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GeneInfoDBXrefsPK geneInfoDBXrefsPK;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GeneInfo_WID", insertable = false, unique = false, nullable = false, updatable = false)
    private GeneInfo geneInfo;

    public GeneInfoDBXrefs() {
    }

    public GeneInfoDBXrefs(GeneInfoDBXrefsPK geneInfoDBXrefsPK) {
        this.geneInfoDBXrefsPK = geneInfoDBXrefsPK;
    }

    public GeneInfoDBXrefs(long geneInfoWID, String dBName, String id) {
        this.geneInfoDBXrefsPK = new GeneInfoDBXrefsPK(geneInfoWID, dBName, id);
    }

    public GeneInfo getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(GeneInfo geneInfo) {
        this.geneInfo = geneInfo;
    }

    public GeneInfoDBXrefsPK getGeneInfoDBXrefsPK() {
        return geneInfoDBXrefsPK;
    }

    public void setGeneInfoDBXrefsPK(GeneInfoDBXrefsPK geneInfoDBXrefsPK) {
        this.geneInfoDBXrefsPK = geneInfoDBXrefsPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geneInfoDBXrefsPK != null ? geneInfoDBXrefsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneInfoDBXrefs)) {
            return false;
        }
        GeneInfoDBXrefs other = (GeneInfoDBXrefs) object;
        if ((this.geneInfoDBXrefsPK == null && other.geneInfoDBXrefsPK != null) || (this.geneInfoDBXrefsPK != null && !this.geneInfoDBXrefsPK.equals(other.geneInfoDBXrefsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneInfoDBXrefs{"
                + " geneWID=" + geneInfoDBXrefsPK.getGeneInfoWID()
                + " Id=" + geneInfoDBXrefsPK.getId()
                + " DBName=" + geneInfoDBXrefsPK.getDBName()
                + '}';
    }
}
