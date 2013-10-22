package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamProteomeRegions entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamProteomeRegions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamProteomeRegions.findAll", query = "SELECT p FROM PfamProteomeRegions p"),
    @NamedQuery(name = "PfamProteomeRegions.findByPfamCompleteProteomesWID", query = "SELECT p FROM PfamProteomeRegions p WHERE p.pfamProteomeRegionsPK.pfamCompleteProteomesWID = :pfamCompleteProteomesWID"),
    @NamedQuery(name = "PfamProteomeRegions.findByAutoPfamseq", query = "SELECT p FROM PfamProteomeRegions p WHERE p.pfamProteomeRegionsPK.autoPfamseq = :autoPfamseq"),
    @NamedQuery(name = "PfamProteomeRegions.findByPfamAWID", query = "SELECT p FROM PfamProteomeRegions p WHERE p.pfamProteomeRegionsPK.pfamAWID = :pfamAWID"),
    @NamedQuery(name = "PfamProteomeRegions.findByCount", query = "SELECT p FROM PfamProteomeRegions p WHERE p.count = :count")})
public class PfamProteomeRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PfamProteomeRegionsPK pfamProteomeRegionsPK;
    @Basic(optional = false)
    @Column(name = "count")
    private int count;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamA_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamAbioWH pfamA;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamCompleteProteomes_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamCompleteProteomes pfamCompleteProteomes;

    public PfamProteomeRegions() {
    }

    public PfamProteomeRegions(PfamProteomeRegionsPK pfamProteomeRegionsPK) {
        this.pfamProteomeRegionsPK = pfamProteomeRegionsPK;
    }

    public PfamProteomeRegions(PfamProteomeRegionsPK pfamProteomeRegionsPK, int count) {
        this.pfamProteomeRegionsPK = pfamProteomeRegionsPK;
        this.count = count;
    }

    public PfamProteomeRegions(long pfamCompleteProteomesWID, long autoPfamseq, long pfamAWID) {
        this.pfamProteomeRegionsPK = new PfamProteomeRegionsPK(pfamCompleteProteomesWID, autoPfamseq, pfamAWID);
    }
    
    public void setRelationsToNull() {
        setPfamA(null);
        setPfamCompleteProteomes(null);
    }

    public PfamAbioWH getPfamA() {
        return pfamA;
    }

    public void setPfamA(PfamAbioWH pfamA) {
        this.pfamA = pfamA;
    }

    public PfamCompleteProteomes getPfamCompleteProteomes() {
        return pfamCompleteProteomes;
    }

    public void setPfamCompleteProteomes(PfamCompleteProteomes pfamCompleteProteomes) {
        this.pfamCompleteProteomes = pfamCompleteProteomes;
    }

    public PfamProteomeRegionsPK getPfamProteomeRegionsPK() {
        return pfamProteomeRegionsPK;
    }

    public void setPfamProteomeRegionsPK(PfamProteomeRegionsPK pfamProteomeRegionsPK) {
        this.pfamProteomeRegionsPK = pfamProteomeRegionsPK;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfamProteomeRegionsPK != null ? pfamProteomeRegionsPK.hashCode() : 0);
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
        final PfamProteomeRegions other = (PfamProteomeRegions) obj;
        if (!Objects.equals(this.pfamProteomeRegionsPK, other.pfamProteomeRegionsPK)) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamProteomeRegions{" + "pfamProteomeRegionsPK=" + pfamProteomeRegionsPK + ", count=" + count + '}';
    }
}
