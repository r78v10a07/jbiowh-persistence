package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the ProteinRefSeq entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinRefSeq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinRefSeq.findAll", query = "SELECT p FROM ProteinRefSeq p"),
    @NamedQuery(name = "ProteinRefSeq.findByProteinWID", query = "SELECT p FROM ProteinRefSeq p WHERE p.proteinRefSeqPK.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinRefSeq.findById", query = "SELECT p FROM ProteinRefSeq p WHERE p.proteinRefSeqPK.id = :id")})
public class ProteinRefSeq implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProteinRefSeqPK proteinRefSeqPK;

    public ProteinRefSeq() {
    }

    public ProteinRefSeq(ProteinRefSeqPK proteinRefSeqPK) {
        this.proteinRefSeqPK = proteinRefSeqPK;
    }

    public ProteinRefSeq(long proteinWID, String id) {
        this.proteinRefSeqPK = new ProteinRefSeqPK(proteinWID, id);
    }

    public ProteinRefSeqPK getProteinRefSeqPK() {
        return proteinRefSeqPK;
    }

    public void setProteinRefSeqPK(ProteinRefSeqPK proteinRefSeqPK) {
        this.proteinRefSeqPK = proteinRefSeqPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proteinRefSeqPK != null ? proteinRefSeqPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinRefSeq)) {
            return false;
        }
        ProteinRefSeq other = (ProteinRefSeq) object;
        return (this.proteinRefSeqPK != null || other.proteinRefSeqPK == null) && (this.proteinRefSeqPK == null || this.proteinRefSeqPK.equals(other.proteinRefSeqPK));
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinRefSeq[ proteinRefSeqPK=" + proteinRefSeqPK + " ]";
    }
}
