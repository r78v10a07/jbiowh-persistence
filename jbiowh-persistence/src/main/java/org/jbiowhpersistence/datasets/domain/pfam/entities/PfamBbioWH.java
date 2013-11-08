package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the PfamBbioWH entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamBbioWH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamBbioWH.findAll", query = "SELECT p FROM PfamBbioWH p"),
    @NamedQuery(name = "PfamBbioWH.findByWid", query = "SELECT p FROM PfamBbioWH p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamBbioWH.findByPfamBacc", query = "SELECT p FROM PfamBbioWH p WHERE p.pfamBacc = :pfamBacc"),
    @NamedQuery(name = "PfamBbioWH.findByPfamBid", query = "SELECT p FROM PfamBbioWH p WHERE p.pfamBid = :pfamBid"),
    @NamedQuery(name = "PfamBbioWH.findByNumberArchs", query = "SELECT p FROM PfamBbioWH p WHERE p.numberArchs = :numberArchs"),
    @NamedQuery(name = "PfamBbioWH.findByNumberRegions", query = "SELECT p FROM PfamBbioWH p WHERE p.numberRegions = :numberRegions"),
    @NamedQuery(name = "PfamBbioWH.findByNumberSpecies", query = "SELECT p FROM PfamBbioWH p WHERE p.numberSpecies = :numberSpecies"),
    @NamedQuery(name = "PfamBbioWH.findByNumberStructures", query = "SELECT p FROM PfamBbioWH p WHERE p.numberStructures = :numberStructures")})
public class PfamBbioWH implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "pfamB_acc")
    private String pfamBacc;
    @Column(name = "pfamB_id")
    private String pfamBid;
    @Column(name = "number_archs")
    private Integer numberArchs;
    @Column(name = "number_regions")
    private Integer numberRegions;
    @Column(name = "number_species")
    private Integer numberSpecies;
    @Column(name = "number_structures")
    private Integer numberStructures;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamB")
    private Set<PfamBReg> pfamBRegs;

    public PfamBbioWH() {
    }

    public PfamBbioWH(Long wid) {
        this.wid = wid;
    }

    @XmlTransient
    public Set<PfamBReg> getPfamBRegs() {
        return pfamBRegs;
    }

    public void setPfamBRegs(Set<PfamBReg> pfamBRegs) {
        this.pfamBRegs = pfamBRegs;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getPfamBacc() {
        return pfamBacc;
    }

    public void setPfamBacc(String pfamBacc) {
        this.pfamBacc = pfamBacc;
    }

    public String getPfamBid() {
        return pfamBid;
    }

    public void setPfamBid(String pfamBid) {
        this.pfamBid = pfamBid;
    }

    public Integer getNumberArchs() {
        return numberArchs;
    }

    public void setNumberArchs(Integer numberArchs) {
        this.numberArchs = numberArchs;
    }

    public Integer getNumberRegions() {
        return numberRegions;
    }

    public void setNumberRegions(Integer numberRegions) {
        this.numberRegions = numberRegions;
    }

    public Integer getNumberSpecies() {
        return numberSpecies;
    }

    public void setNumberSpecies(Integer numberSpecies) {
        this.numberSpecies = numberSpecies;
    }

    public Integer getNumberStructures() {
        return numberStructures;
    }

    public void setNumberStructures(Integer numberStructures) {
        this.numberStructures = numberStructures;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
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
        final PfamBbioWH other = (PfamBbioWH) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.pfamBacc, other.pfamBacc)) {
            return false;
        }
        if (!Objects.equals(this.pfamBid, other.pfamBid)) {
            return false;
        }
        if (!Objects.equals(this.numberArchs, other.numberArchs)) {
            return false;
        }
        if (!Objects.equals(this.numberRegions, other.numberRegions)) {
            return false;
        }
        if (!Objects.equals(this.numberSpecies, other.numberSpecies)) {
            return false;
        }
        if (!Objects.equals(this.numberStructures, other.numberStructures)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamB{" + "wid=" + wid + ", pfamBacc=" + pfamBacc + ", pfamBid=" + pfamBid + ", numberArchs=" + numberArchs + ", numberRegions=" + numberRegions + ", numberSpecies=" + numberSpecies + ", numberStructures=" + numberStructures + '}';
    }
}
