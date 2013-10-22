package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamOtherReg entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ 
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamOtherReg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamOtherReg.findAll", query = "SELECT p FROM PfamOtherReg p"),
    @NamedQuery(name = "PfamOtherReg.findByRegionId", query = "SELECT p FROM PfamOtherReg p WHERE p.regionId = :regionId"),
    @NamedQuery(name = "PfamOtherReg.findByAutoPfamseq", query = "SELECT p FROM PfamOtherReg p WHERE p.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamOtherReg.findBySeqStart", query = "SELECT p FROM PfamOtherReg p WHERE p.seqStart = :seqStart"),
    @NamedQuery(name = "PfamOtherReg.findBySeqEnd", query = "SELECT p FROM PfamOtherReg p WHERE p.seqEnd = :seqEnd"),
    @NamedQuery(name = "PfamOtherReg.findByTypeId", query = "SELECT p FROM PfamOtherReg p WHERE p.typeId = :typeId"),
    @NamedQuery(name = "PfamOtherReg.findBySourceId", query = "SELECT p FROM PfamOtherReg p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "PfamOtherReg.findByScore", query = "SELECT p FROM PfamOtherReg p WHERE p.score = :score"),
    @NamedQuery(name = "PfamOtherReg.findByOrientation", query = "SELECT p FROM PfamOtherReg p WHERE p.orientation = :orientation")})
public class PfamOtherReg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "region_id")
    private Long regionId;
    @Basic(optional = false)
    @Column(name = "auto_pfamseq")
    private long autoPfamseq;
    @Basic(optional = false)
    @Column(name = "seq_start")
    private int seqStart;
    @Basic(optional = false)
    @Column(name = "seq_end")
    private int seqEnd;
    @Basic(optional = false)
    @Column(name = "type_id")
    private String typeId;
    @Basic(optional = false)
    @Column(name = "source_id")
    private String sourceId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "score")
    private Double score;
    @Column(name = "orientation")
    private String orientation;

    public PfamOtherReg() {
    }

    public PfamOtherReg(Long regionId) {
        this.regionId = regionId;
    }

    public PfamOtherReg(Long regionId, long autoPfamseq, int seqStart, int seqEnd, String typeId, String sourceId) {
        this.regionId = regionId;
        this.autoPfamseq = autoPfamseq;
        this.seqStart = seqStart;
        this.seqEnd = seqEnd;
        this.typeId = typeId;
        this.sourceId = sourceId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public long getAutoPfamseq() {
        return autoPfamseq;
    }

    public void setAutoPfamseq(long autoPfamseq) {
        this.autoPfamseq = autoPfamseq;
    }

    public int getSeqStart() {
        return seqStart;
    }

    public void setSeqStart(int seqStart) {
        this.seqStart = seqStart;
    }

    public int getSeqEnd() {
        return seqEnd;
    }

    public void setSeqEnd(int seqEnd) {
        this.seqEnd = seqEnd;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regionId != null ? regionId.hashCode() : 0);
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
        final PfamOtherReg other = (PfamOtherReg) obj;
        if (!Objects.equals(this.regionId, other.regionId)) {
            return false;
        }
        if (this.autoPfamseq != other.autoPfamseq) {
            return false;
        }
        if (this.seqStart != other.seqStart) {
            return false;
        }
        if (this.seqEnd != other.seqEnd) {
            return false;
        }
        if (!Objects.equals(this.typeId, other.typeId)) {
            return false;
        }
        if (!Objects.equals(this.sourceId, other.sourceId)) {
            return false;
        }
        if (!Objects.equals(this.score, other.score)) {
            return false;
        }
        if (!Objects.equals(this.orientation, other.orientation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamOtherReg{" + "regionId=" + regionId + ", autoPfamseq=" + autoPfamseq + ", seqStart=" + seqStart + ", seqEnd=" + seqEnd + ", typeId=" + typeId + ", sourceId=" + sourceId + ", score=" + score + ", orientation=" + orientation + '}';
    }
}
