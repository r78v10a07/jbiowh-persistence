package org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the KEGG Pathway Relation entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGPathwayRelation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGPathwayRelation.findAll", query = "SELECT k FROM KEGGPathwayRelation k"),
    @NamedQuery(name = "KEGGPathwayRelation.findByWid", query = "SELECT k FROM KEGGPathwayRelation k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGPathwayRelation.findByKEGGPathwayWID", query = "SELECT k FROM KEGGPathwayRelation k WHERE k.kEGGPathwayWID = :kEGGPathwayWID"),
    @NamedQuery(name = "KEGGPathwayRelation.findById1", query = "SELECT k FROM KEGGPathwayRelation k WHERE k.id1 = :id1"),
    @NamedQuery(name = "KEGGPathwayRelation.findById2", query = "SELECT k FROM KEGGPathwayRelation k WHERE k.id2 = :id2"),
    @NamedQuery(name = "KEGGPathwayRelation.findByType", query = "SELECT k FROM KEGGPathwayRelation k WHERE k.type = :type")})
public class KEGGPathwayRelation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGPathway_WID")
    private long kEGGPathwayWID;
    @Basic(optional = false)
    @Column(name = "Id1")
    private int id1;
    @Basic(optional = false)
    @Column(name = "Id2")
    private int id2;
    @Column(name = "Type")
    private String type;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGPathway_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGPathway kEGGPathway;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGPathwayRelation")
    @MapKey(name = "keggpathwayrelationsubtypePK")
    private Map<KEGGPathwayRelationSubTypePK, KEGGPathwayRelationSubType> kEGGPathwayRelationSubType;

    public KEGGPathwayRelation() {
    }

    public KEGGPathwayRelation(Long wid) {
        this.wid = wid;
    }

    public KEGGPathwayRelation(Long wid, long kEGGPathwayWID, int id1, int id2) {
        this.wid = wid;
        this.kEGGPathwayWID = kEGGPathwayWID;
        this.id1 = id1;
        this.id2 = id2;
    }

    @XmlTransient
    public Map<KEGGPathwayRelationSubTypePK, KEGGPathwayRelationSubType> getkEGGPathwayRelationSubType() {
        return kEGGPathwayRelationSubType;
    }

    public void setkEGGPathwayRelationSubType(Map<KEGGPathwayRelationSubTypePK, KEGGPathwayRelationSubType> kEGGPathwayRelationSubType) {
        this.kEGGPathwayRelationSubType = kEGGPathwayRelationSubType;
    }

    public KEGGPathway getkEGGPathway() {
        return kEGGPathway;
    }

    public void setkEGGPathway(KEGGPathway kEGGPathway) {
        this.kEGGPathway = kEGGPathway;
    }

    public long getkEGGPathwayWID() {
        return kEGGPathwayWID;
    }

    public void setkEGGPathwayWID(long kEGGPathwayWID) {
        this.kEGGPathwayWID = kEGGPathwayWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getKEGGPathwayWID() {
        return kEGGPathwayWID;
    }

    public void setKEGGPathwayWID(long kEGGPathwayWID) {
        this.kEGGPathwayWID = kEGGPathwayWID;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        final KEGGPathwayRelation other = (KEGGPathwayRelation) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGPathwayWID != other.kEGGPathwayWID) {
            return false;
        }
        if (this.id1 != other.id1) {
            return false;
        }
        if (this.id2 != other.id2) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGPathwayRelation{" + "wid=" + wid + ", kEGGPathwayWID=" + kEGGPathwayWID + ", id1=" + id1 + ", id2=" + id2 + ", type=" + type + '}';
    }
}
