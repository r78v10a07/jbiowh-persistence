package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamSeq_has_UniProtId entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ $LastChangedRevision: 344 $
 *
 * @since Nov 22, 2012
 */
@Entity
@Table(name = "PfamSeq_has_UniProtId")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamSeqhasUniProtId.findAll", query = "SELECT p FROM PfamSeqhasUniProtId p"),
    @NamedQuery(name = "PfamSeqhasUniProtId.findByAutoPfamseq", query = "SELECT p FROM PfamSeqhasUniProtId p WHERE p.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamSeqhasUniProtId.findByUniProtId", query = "SELECT p FROM PfamSeqhasUniProtId p WHERE p.uniProtId = :uniProtId")})
public class PfamSeqhasUniProtId implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private Long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "UniProt_Id")
    private String uniProtId;

    public PfamSeqhasUniProtId() {
    }

    public PfamSeqhasUniProtId(Long autoPfamseq) {
        this.autoPfamseq = autoPfamseq;
    }

    public PfamSeqhasUniProtId(Long autoPfamseq, String uniProtId) {
        this.autoPfamseq = autoPfamseq;
        this.uniProtId = uniProtId;
    }

    public Long getAutoPfamseq() {
        return autoPfamseq;
    }

    public void setAutoPfamseq(Long autoPfamseq) {
        this.autoPfamseq = autoPfamseq;
    }

    public String getUniProtId() {
        return uniProtId;
    }

    public void setUniProtId(String uniProtId) {
        this.uniProtId = uniProtId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autoPfamseq != null ? autoPfamseq.hashCode() : 0);
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
        final PfamSeqhasUniProtId other = (PfamSeqhasUniProtId) obj;
        if (!Objects.equals(this.autoPfamseq, other.autoPfamseq)) {
            return false;
        }
        if (!Objects.equals(this.uniProtId, other.uniProtId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamSeqhasUniProtId{" + "autoPfamseq=" + autoPfamseq + ", uniProtId=" + uniProtId + '}';
    }
}
