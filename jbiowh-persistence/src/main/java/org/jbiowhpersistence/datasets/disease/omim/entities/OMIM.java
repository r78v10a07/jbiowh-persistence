package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.gene.gene.GeneTables;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;

/**
 * This Class is the OMIM entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision: 591 $
 *
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

    /*
     * Internal links
     */
    @ElementCollection
    @CollectionTable(
            name = "OMIMAV",
            joinColumns
            = @JoinColumn(name = "OMIM_WID"))
    @XmlElementWrapper(name = "OMIMAVs")
    private Set<OMIMAV> omimAV;
    @ElementCollection
    @CollectionTable(
            name = "OMIMCN",
            joinColumns
            = @JoinColumn(name = "OMIM_WID"))
    @XmlElementWrapper(name = "OMIMCNs")
    private Set<OMIMCN> omimCN;
    @ElementCollection
    @CollectionTable(
            name = "OMIMED",
            joinColumns
            = @JoinColumn(name = "OMIM_WID"))
    @XmlElementWrapper(name = "OMIMEDs")
    private Set<OMIMED> omimED;
    @ElementCollection
    @CollectionTable(
            name = "OMIMRF",
            joinColumns
            = @JoinColumn(name = "OMIM_WID"))
    @XmlElementWrapper(name = "OMIMRFs")
    private Set<OMIMRF> omimRF;
    @ElementCollection
    @CollectionTable(
            name = "OMIMSA",
            joinColumns
            = @JoinColumn(name = "OMIM_WID"))
    @XmlElementWrapper(name = "OMIMSAs")
    private Set<OMIMSA> omimSA;
    @ElementCollection
    @CollectionTable(
            name = "OMIMTI",
            joinColumns
            = @JoinColumn(name = "OMIM_WID"))
    @XmlElementWrapper(name = "OMIMTIs")
    private Set<OMIMTI> omimTI;
    @ElementCollection
    @CollectionTable(
            name = "OMIMTX",
            joinColumns
            = @JoinColumn(name = "OMIM_WID"))
    @XmlElementWrapper(name = "OMIMTXs")
    private Set<OMIMTX> omimTX;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "omim")
    @XmlElement
    @XmlInverseReference(mappedBy = "omim")
    @XmlElementWrapper(name = "OMIMCSs")
    private Set<OMIMCS> omimCS;
    @ElementCollection
    @CollectionTable(
            name = "OMIMCD",
            joinColumns
            = @JoinColumn(name = "OMIM_WID"))
    @XmlElementWrapper(name = "OMIMCDs")
    private Set<OMIMCD> omimCD;
    /**
     * External links
     *
     */
    @XmlTransient
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = GeneTables.GENEINFO_HAS_OMIM,
            joinColumns
            = @JoinColumn(name = "OMIM_WID", referencedColumnName = "WID"),
            inverseJoinColumns
            = @JoinColumn(name = "GeneInfo_WID", referencedColumnName = "WID"))
    private Collection<GeneInfo> geneInfo;
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
        setGeneInfo(null);
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

    public Set<OMIMAV> getOmimAV() {
        return omimAV;
    }

    public void setOmimAV(Set<OMIMAV> omimAV) {
        this.omimAV = omimAV;
    }

    public Set<OMIMCD> getOmimCD() {
        return omimCD;
    }

    public void setOmimCD(Set<OMIMCD> omimCD) {
        this.omimCD = omimCD;
    }

    public Set<OMIMCN> getOmimCN() {
        return omimCN;
    }

    public void setOmimCN(Set<OMIMCN> omimCN) {
        this.omimCN = omimCN;
    }

    public Set<OMIMED> getOmimED() {
        return omimED;
    }

    public void setOmimED(Set<OMIMED> omimED) {
        this.omimED = omimED;
    }

    public Set<OMIMRF> getOmimRF() {
        return omimRF;
    }

    public void setOmimRF(Set<OMIMRF> omimRF) {
        this.omimRF = omimRF;
    }

    public Set<OMIMSA> getOmimSA() {
        return omimSA;
    }

    public void setOmimSA(Set<OMIMSA> omimSA) {
        this.omimSA = omimSA;
    }

    public Set<OMIMTI> getOmimTI() {
        return omimTI;
    }

    public void setOmimTI(Set<OMIMTI> omimTI) {
        this.omimTI = omimTI;
    }

    public Set<OMIMTX> getOmimTX() {
        return omimTX;
    }

    public void setOmimTX(Set<OMIMTX> omimTX) {
        this.omimTX = omimTX;
    }

    public Set<OMIMCS> getOmimCS() {
        return omimCS;
    }

    public void setOmimCS(Set<OMIMCS> omimCS) {
        this.omimCS = omimCS;
    }

    @XmlTransient
    public Collection<GeneInfo> getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(Collection<GeneInfo> geneInfo) {
        this.geneInfo = geneInfo;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 31 * hash + (int) (this.omimId ^ (this.omimId >>> 32));
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
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.omimId != other.omimId) {
            return false;
        }
        if ((this.tx == null) ? (other.tx != null) : !this.tx.equals(other.tx)) {
            return false;
        }
        if (this.dataSetWID != other.dataSetWID) {
            return false;
        }
        if (this.omimAV != other.omimAV && (this.omimAV == null || !this.omimAV.equals(other.omimAV))) {
            return false;
        }
        if (this.omimCD != other.omimCD && (this.omimCD == null || !this.omimCD.equals(other.omimCD))) {
            return false;
        }
        if (this.omimCN != other.omimCN && (this.omimCN == null || !this.omimCN.equals(other.omimCN))) {
            return false;
        }
        if (this.omimED != other.omimED && (this.omimED == null || !this.omimED.equals(other.omimED))) {
            return false;
        }
        if (this.omimRF != other.omimRF && (this.omimRF == null || !this.omimRF.equals(other.omimRF))) {
            return false;
        }
        if (this.omimSA != other.omimSA && (this.omimSA == null || !this.omimSA.equals(other.omimSA))) {
            return false;
        }
        if (this.omimTI != other.omimTI && (this.omimTI == null || !this.omimTI.equals(other.omimTI))) {
            return false;
        }
        if (this.omimTX != other.omimTX && (this.omimTX == null || !this.omimTX.equals(other.omimTX))) {
            return false;
        }
        return this.omimCS == other.omimCS || (this.omimCS != null && this.omimCS.equals(other.omimCS));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (omimAV != null) {
            for (OMIMAV a : omimAV) {
                builder.append("\t").append(a).append("\n");
            }
        }
        if (omimCD != null) {
            for (OMIMCD a : omimCD) {
                builder.append("\t").append(a).append("\n");
            }
        }
        if (omimCN != null) {
            for (OMIMCN a : omimCN) {
                builder.append("\t").append(a).append("\n");
            }
        }
        if (omimED != null) {
            for (OMIMED a : omimED) {
                builder.append("\t").append(a).append("\n");
            }
        }
        if (omimRF != null) {
            for (OMIMRF a : omimRF) {
                builder.append("\t").append(a).append("\n");
            }
        }
        if (omimSA != null) {
            for (OMIMSA a : omimSA) {
                builder.append("\t").append(a).append("\n");
            }
        }
        if (omimTI != null) {
            for (OMIMTI a : omimTI) {
                builder.append("\t").append(a).append("\n");
            }
        }
        if (omimTX != null) {
            for (OMIMTX a : omimTX) {
                builder.append("\t").append(a).append("\n");
            }
        }
        if (omimCS != null) {
            for (OMIMCS a : omimCS) {
                builder.append("\t").append(a).append("\n");
            }
        }
        return "OMIM{"
                + "wid=" + wid
                + ", omimId=" + omimId
                + ", tx=" + tx
                + ", dataSetWID=" + dataSetWID + "}\n"
                + builder.toString();
    }
}
