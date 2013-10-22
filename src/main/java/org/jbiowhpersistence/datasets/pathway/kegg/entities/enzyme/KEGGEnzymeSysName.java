package org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Enzyme SysName entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGEnzymeSysName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGEnzymeSysName.findAll", query = "SELECT k FROM KEGGEnzymeSysName k"),
    @NamedQuery(name = "KEGGEnzymeSysName.findByWid", query = "SELECT k FROM KEGGEnzymeSysName k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGEnzymeSysName.findByKEGGEnzymeWID", query = "SELECT k FROM KEGGEnzymeSysName k WHERE k.kEGGEnzymeWID = :kEGGEnzymeWID"),
    @NamedQuery(name = "KEGGEnzymeSysName.findBySySName", query = "SELECT k FROM KEGGEnzymeSysName k WHERE k.sySName = :sySName")})
public class KEGGEnzymeSysName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGEnzyme_WID")
    private long kEGGEnzymeWID;
    @Basic(optional = false)
    @Column(name = "SySName")
    private String sySName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGEnzyme_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGEnzyme kEGGEnzyme;

    public KEGGEnzymeSysName() {
    }

    public KEGGEnzymeSysName(Long wid) {
        this.wid = wid;
    }

    public KEGGEnzymeSysName(Long wid, long kEGGEnzymeWID, String sySName) {
        this.wid = wid;
        this.kEGGEnzymeWID = kEGGEnzymeWID;
        this.sySName = sySName;
    }

    public KEGGEnzyme getkEGGEnzyme() {
        return kEGGEnzyme;
    }

    public void setkEGGEnzyme(KEGGEnzyme kEGGEnzyme) {
        this.kEGGEnzyme = kEGGEnzyme;
    }

    public long getkEGGEnzymeWID() {
        return kEGGEnzymeWID;
    }

    public void setkEGGEnzymeWID(long kEGGEnzymeWID) {
        this.kEGGEnzymeWID = kEGGEnzymeWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getKEGGEnzymeWID() {
        return kEGGEnzymeWID;
    }

    public void setKEGGEnzymeWID(long kEGGEnzymeWID) {
        this.kEGGEnzymeWID = kEGGEnzymeWID;
    }

    public String getSySName() {
        return sySName;
    }

    public void setSySName(String sySName) {
        this.sySName = sySName;
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
        final KEGGEnzymeSysName other = (KEGGEnzymeSysName) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGEnzymeWID != other.kEGGEnzymeWID) {
            return false;
        }
        if (!Objects.equals(this.sySName, other.sySName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGEnzymeSysName{" + "wid=" + wid + ", kEGGEnzymeWID=" + kEGGEnzymeWID + ", sySName=" + sySName + '}';
    }
}
