package org.jbiowhpersistence.datasets.protgroup.pirsf.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.protgroup.pirsf.PirsfTables;

/**
 * This class is
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 11, 2013
 */
@Entity
@Table(name = "PIRSF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pirsf.findAll", query = "SELECT p FROM Pirsf p"),
    @NamedQuery(name = "Pirsf.findByWid", query = "SELECT p FROM Pirsf p WHERE p.wid = :wid"),
    @NamedQuery(name = "Pirsf.findByPIRSFnumber", query = "SELECT p FROM Pirsf p WHERE p.pIRSFnumber like :pIRSFnumber"),
    @NamedQuery(name = "Pirsf.findByCurationStatus", query = "SELECT p FROM Pirsf p WHERE p.curationStatus = :curationStatus"),
    @NamedQuery(name = "Pirsf.findByName", query = "SELECT p FROM Pirsf p WHERE p.name like :name"),
    @NamedQuery(name = "Pirsf.findByParent", query = "SELECT p FROM Pirsf p WHERE p.parent = :parent"),
    @NamedQuery(name = "Pirsf.findByDataSetWID", query = "SELECT p FROM Pirsf p WHERE p.dataSetWID = :dataSetWID")})
public class Pirsf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "PIRSFnumber")
    private String pIRSFnumber;
    @Basic(optional = false)
    @Column(name = "CurationStatus")
    private String curationStatus;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Parent")
    private String parent;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    // Internal Relations
    @ElementCollection
    @CollectionTable(
            name = PirsfTables.PIRSFPROTEIN,
            joinColumns
            = @JoinColumn(name = "PIRSF_WID"))
    @XmlElementWrapper(name = "PIRSFProteins")
    private Collection<PirsfProtein> pIRSFProtein;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pirsf")
    @MapKey(name = "pIRSFhasProteinPK")
    private Map<PirsfhasProteinPK, PirsfhasProtein> pIRSFhasProtein;

    public Pirsf() {
    }

    public Pirsf(Long wid) {
        this.wid = wid;
    }

    public Pirsf(Long wid, String pIRSFnumber, String curationStatus, String name, String parent, long dataSetWID) {
        this.wid = wid;
        this.pIRSFnumber = pIRSFnumber;
        this.curationStatus = curationStatus;
        this.name = name;
        this.parent = parent;
        this.dataSetWID = dataSetWID;
    }

    @XmlTransient
    public Map<PirsfhasProteinPK, PirsfhasProtein> getpIRSFhasProtein() {
        return pIRSFhasProtein;
    }

    public void setpIRSFhasProtein(Map<PirsfhasProteinPK, PirsfhasProtein> pIRSFhasProtein) {
        this.pIRSFhasProtein = pIRSFhasProtein;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getpIRSFnumber() {
        return pIRSFnumber;
    }

    public void setpIRSFnumber(String pIRSFnumber) {
        this.pIRSFnumber = pIRSFnumber;
    }

    public String getCurationStatus() {
        return curationStatus;
    }

    public void setCurationStatus(String curationStatus) {
        this.curationStatus = curationStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
    }

    public Collection<PirsfProtein> getpIRSFProtein() {
        return pIRSFProtein;
    }

    public void setpIRSFProtein(Collection<PirsfProtein> pIRSFProtein) {
        this.pIRSFProtein = pIRSFProtein;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 89 * hash + (this.pIRSFnumber != null ? this.pIRSFnumber.hashCode() : 0);
        hash = 89 * hash + (this.curationStatus != null ? this.curationStatus.hashCode() : 0);
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.parent != null ? this.parent.hashCode() : 0);
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
        final Pirsf other = (Pirsf) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if ((this.pIRSFnumber == null) ? (other.pIRSFnumber != null) : !this.pIRSFnumber.equals(other.pIRSFnumber)) {
            return false;
        }
        if ((this.curationStatus == null) ? (other.curationStatus != null) : !this.curationStatus.equals(other.curationStatus)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return (this.parent == null ? other.parent == null : this.parent.equals(other.parent)) || (this.parent != null && this.parent.equals(other.parent));
    }

    @Override
    public String toString() {
        StringBuilder pData = new StringBuilder();

        if (pIRSFProtein != null) {
            for (PirsfProtein p : pIRSFProtein) {
                pData.append("\t").append(p).append("\n");
            }
        }

        return "Pirsf{" + "wid=" + wid
                + ", pIRSFnumber=" + pIRSFnumber
                + ", curationStatus=" + curationStatus
                + ", name=" + name
                + ", parent=" + parent
                + ", dataSetWID=" + dataSetWID + "}\n"
                /*+ pData.toString()*/;
    }
}
