package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamInterpro entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamInterpro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamInterpro.findAll", query = "SELECT p FROM PfamInterpro p"),
    @NamedQuery(name = "PfamInterpro.findByPfamAWID", query = "SELECT p FROM PfamInterpro p WHERE p.pfamAWID = :pfamAWID"),
    @NamedQuery(name = "PfamInterpro.findByInterproId", query = "SELECT p FROM PfamInterpro p WHERE p.interproId = :interproId")})
public class PfamInterpro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PfamA_WID")
    private Long pfamAWID;
    @Basic(optional = false)
    @Column(name = "interpro_id")
    private String interproId;
    @Basic(optional = false)
    @Lob
    @Column(name = "abstract")
    private String abstract1;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PfamA_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private PfamAbioWH pfamA;

    public PfamInterpro() {
    }

    public PfamInterpro(Long pfamAWID) {
        this.pfamAWID = pfamAWID;
    }

    public PfamInterpro(Long pfamAWID, String interproId, String abstract1) {
        this.pfamAWID = pfamAWID;
        this.interproId = interproId;
        this.abstract1 = abstract1;
    }

    public PfamAbioWH getPfamA() {
        return pfamA;
    }

    public void setPfamA(PfamAbioWH pfamA) {
        this.pfamA = pfamA;
    }

    public Long getPfamAWID() {
        return pfamAWID;
    }

    public void setPfamAWID(Long pfamAWID) {
        this.pfamAWID = pfamAWID;
    }

    public String getInterproId() {
        return interproId;
    }

    public void setInterproId(String interproId) {
        this.interproId = interproId;
    }

    public String getAbstract1() {
        return abstract1;
    }

    public void setAbstract1(String abstract1) {
        this.abstract1 = abstract1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfamAWID != null ? pfamAWID.hashCode() : 0);
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
        final PfamInterpro other = (PfamInterpro) obj;
        if (!Objects.equals(this.pfamAWID, other.pfamAWID)) {
            return false;
        }
        if (!Objects.equals(this.interproId, other.interproId)) {
            return false;
        }
        if (!Objects.equals(this.abstract1, other.abstract1)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamInterpro{" + "pfamAWID=" + pfamAWID + ", interproId=" + interproId + ", abstract1=" + abstract1 + '}';
    }
}
