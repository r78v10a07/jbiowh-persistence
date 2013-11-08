package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.domain.pfam.PFamTables;

/**
 * This class is the PfamArchitecture entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $ 
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamArchitecture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamArchitecture.findAll", query = "SELECT p FROM PfamArchitecture p"),
    @NamedQuery(name = "PfamArchitecture.findByWid", query = "SELECT p FROM PfamArchitecture p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamArchitecture.findByTypeExample", query = "SELECT p FROM PfamArchitecture p WHERE p.typeExample = :typeExample"),
    @NamedQuery(name = "PfamArchitecture.findByNoSeqs", query = "SELECT p FROM PfamArchitecture p WHERE p.noSeqs = :noSeqs")})
public class PfamArchitecture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Lob
    @Column(name = "architecture")
    private String architecture;
    @Basic(optional = false)
    @Column(name = "type_example")
    private int typeExample;
    @Basic(optional = false)
    @Column(name = "no_seqs")
    private int noSeqs;
    @Lob
    @Column(name = "architecture_acc")
    private String architectureAcc;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMA_HAS_PFAMARCHITECTURE,
    joinColumns =
    @JoinColumn(name = "PfamArchitecture_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"))
    private Set<PfamAbioWH> pfamA;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMCLANS_HAS_PFAMARCHITECTURE,
    joinColumns =
    @JoinColumn(name = "PfamArchitecture_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "PfamClans_WID", referencedColumnName = "WID"))
    private Set<PfamClans> pfamClanses;

    public PfamArchitecture() {
    }

    public PfamArchitecture(Long wid) {
        this.wid = wid;
    }

    public PfamArchitecture(Long wid, int typeExample, int noSeqs) {
        this.wid = wid;
        this.typeExample = typeExample;
        this.noSeqs = noSeqs;
    }
    
    public void setRelationsToNull() {
        setPfamA(null);
        setPfamClanses(null);
    }

    @XmlTransient
    public Set<PfamAbioWH> getPfamA() {
        return pfamA;
    }

    public void setPfamA(Set<PfamAbioWH> pfamA) {
        this.pfamA = pfamA;
    }

    @XmlTransient
    public Set<PfamClans> getPfamClanses() {
        return pfamClanses;
    }

    public void setPfamClanses(Set<PfamClans> pfamClanses) {
        this.pfamClanses = pfamClanses;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public int getTypeExample() {
        return typeExample;
    }

    public void setTypeExample(int typeExample) {
        this.typeExample = typeExample;
    }

    public int getNoSeqs() {
        return noSeqs;
    }

    public void setNoSeqs(int noSeqs) {
        this.noSeqs = noSeqs;
    }

    public String getArchitectureAcc() {
        return architectureAcc;
    }

    public void setArchitectureAcc(String architectureAcc) {
        this.architectureAcc = architectureAcc;
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
        final PfamArchitecture other = (PfamArchitecture) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.architecture, other.architecture)) {
            return false;
        }
        if (this.typeExample != other.typeExample) {
            return false;
        }
        if (this.noSeqs != other.noSeqs) {
            return false;
        }
        if (!Objects.equals(this.architectureAcc, other.architectureAcc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamArchitecture{" + "wid=" + wid + ", architecture=" + architecture + ", typeExample=" + typeExample + ", noSeqs=" + noSeqs + ", architectureAcc=" + architectureAcc + '}';
    }
}
