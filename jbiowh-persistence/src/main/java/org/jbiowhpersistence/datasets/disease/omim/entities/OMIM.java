package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.GeneTables;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;

/**
 * This Class is the OMIM entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Jul 25, 2012
 */
@Entity
@Table(name = "OMIM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OMIM.findAll", query = "SELECT o FROM OMIM o"),
    @NamedQuery(name = "OMIM.findByWid", query = "SELECT o FROM OMIM o WHERE o.wid = :wid"),
    @NamedQuery(name = "OMIM.findByOmimId", query = "SELECT o FROM OMIM o WHERE o.omimId = :omimId"),
    @NamedQuery(name = "OMIM.findByDataSetWID", query = "SELECT o FROM OMIM o WHERE o.dataSetWID = :dataSetWID")})
public class OMIM implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "OMIM_ID")
    private long omimId;
    @Lob
    @Column(name = "TX")
    private String tx;
    @Basic(optional = false)
    @Column(name = "DataSet_WID")
    private long dataSetWID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    private Set<OMIMAV> omimAVs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    @MapKey(name = "oMIMCDPK")
    private Map<OMIMCDPK, OMIMCD> omimCDs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    @MapKey(name = "oMIMCNPK")
    private Map<OMIMCNPK, OMIMCN> omimCNs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    private Set<OMIMCS> omimCSs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    @MapKey(name = "oMIMEDPK")
    private Map<OMIMEDPK, OMIMED> omimEDs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    private Set<OMIMRF> omimRFs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    private Set<OMIMSA> omimSAs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    @MapKey(name = "oMIMTIPK")
    private Map<OMIMTIPK, OMIMTI> omimTIs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    @MapKey(name = "oMIMTXPK")
    private Map<OMIMTXPK, OMIMTX> omimTXs;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = GeneTables.GENEINFO_HAS_OMIM,
    joinColumns =
    @JoinColumn(name = "OMIM_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    private Set<GeneInfo> geneInfos;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DataSet_WID", referencedColumnName = "WID", insertable = false, unique = false, nullable = false, updatable = false)
    private DataSet dataSet;

    public OMIM() {
    }

    public OMIM(Long wid) {
        this.wid = wid;
    }

    public OMIM(Long wid, long omimId, long dataSetWID) {
        this.wid = wid;
        this.omimId = omimId;
        this.dataSetWID = dataSetWID;
    }
    
    public void setRelationsToNull() {
        setGeneInfos(null);
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @XmlTransient
    public Set<OMIMAV> getOmimAVs() {
        return omimAVs;
    }

    public void setOmimAVs(Set<OMIMAV> omimAVs) {
        this.omimAVs = omimAVs;
    }

    @XmlTransient
    public Map<OMIMCDPK, OMIMCD> getOmimCDs() {
        return omimCDs;
    }

    public void setOmimCDs(Map<OMIMCDPK, OMIMCD> omimCDs) {
        this.omimCDs = omimCDs;
    }

    @XmlTransient
    public Map<OMIMCNPK, OMIMCN> getOmimCNs() {
        return omimCNs;
    }

    public void setOmimCNs(Map<OMIMCNPK, OMIMCN> omimCNs) {
        this.omimCNs = omimCNs;
    }

    @XmlTransient
    public Set<OMIMCS> getOmimCSs() {
        return omimCSs;
    }

    public void setOmimCSs(Set<OMIMCS> omimCSs) {
        this.omimCSs = omimCSs;
    }

    @XmlTransient
    public Map<OMIMEDPK, OMIMED> getOmimEDs() {
        return omimEDs;
    }

    public void setOmimEDs(Map<OMIMEDPK, OMIMED> omimEDs) {
        this.omimEDs = omimEDs;
    }

    @XmlTransient
    public Set<OMIMRF> getOmimRFs() {
        return omimRFs;
    }

    public void setOmimRFs(Set<OMIMRF> omimRFs) {
        this.omimRFs = omimRFs;
    }

    @XmlTransient
    public Set<OMIMSA> getOmimSAs() {
        return omimSAs;
    }

    public void setOmimSAs(Set<OMIMSA> omimSAs) {
        this.omimSAs = omimSAs;
    }

    @XmlTransient
    public Map<OMIMTIPK, OMIMTI> getOmimTIs() {
        return omimTIs;
    }

    public void setOmimTIs(Map<OMIMTIPK, OMIMTI> omimTIs) {
        this.omimTIs = omimTIs;
    }

    @XmlTransient
    public Map<OMIMTXPK, OMIMTX> getOmimTXs() {
        return omimTXs;
    }

    public void setOmimTXs(Map<OMIMTXPK, OMIMTX> omimTXs) {
        this.omimTXs = omimTXs;
    }

    @XmlTransient
    public Set<GeneInfo> getGeneInfos() {
        return geneInfos;
    }

    public void setGeneInfos(Set<GeneInfo> geneInfos) {
        this.geneInfos = geneInfos;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getOmimId() {
        return omimId;
    }

    public void setOmimId(long omimId) {
        this.omimId = omimId;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public long getDataSetWID() {
        return dataSetWID;
    }

    public void setDataSetWID(long dataSetWID) {
        this.dataSetWID = dataSetWID;
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
        final OMIM other = (OMIM) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.omimId != other.omimId) {
            return false;
        }
        if (!Objects.equals(this.tx, other.tx)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (!Objects.equals(this.omimAVs, other.omimAVs)) {
            return false;
        }
        if (!Objects.equals(this.omimCDs, other.omimCDs)) {
            return false;
        }
        if (!Objects.equals(this.omimCNs, other.omimCNs)) {
            return false;
        }
        if (!Objects.equals(this.omimCSs, other.omimCSs)) {
            return false;
        }
        if (!Objects.equals(this.omimEDs, other.omimEDs)) {
            return false;
        }
        if (!Objects.equals(this.omimRFs, other.omimRFs)) {
            return false;
        }
        if (!Objects.equals(this.omimSAs, other.omimSAs)) {
            return false;
        }
        if (!Objects.equals(this.omimTIs, other.omimTIs)) {
            return false;
        }
        if (!Objects.equals(this.omimTXs, other.omimTXs)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OMIM{" + "wid=" + wid + ", omimId=" + omimId + ", tx=" + tx + ", dataSetWID=" + dataSetWID + '}';
    }
}
