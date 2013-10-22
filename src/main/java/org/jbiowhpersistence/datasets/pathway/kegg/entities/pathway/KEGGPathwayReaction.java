package org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Pathway Reaction entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGPathwayReaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGPathwayReaction.findAll", query = "SELECT k FROM KEGGPathwayReaction k"),
    @NamedQuery(name = "KEGGPathwayReaction.findByWid", query = "SELECT k FROM KEGGPathwayReaction k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGPathwayReaction.findByKEGGPathwayWID", query = "SELECT k FROM KEGGPathwayReaction k WHERE k.kEGGPathwayWID = :kEGGPathwayWID"),
    @NamedQuery(name = "KEGGPathwayReaction.findById", query = "SELECT k FROM KEGGPathwayReaction k WHERE k.id = :id"),
    @NamedQuery(name = "KEGGPathwayReaction.findByEntry", query = "SELECT k FROM KEGGPathwayReaction k WHERE k.entry = :entry"),
    @NamedQuery(name = "KEGGPathwayReaction.findByType", query = "SELECT k FROM KEGGPathwayReaction k WHERE k.type = :type")})
public class KEGGPathwayReaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGPathway_WID")
    private long kEGGPathwayWID;
    @Basic(optional = false)
    @Column(name = "Id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Entry")
    private String entry;
    @Column(name = "Type")
    private String type;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGPathway_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGPathway kEGGPathway;

    public KEGGPathwayReaction() {
    }

    public KEGGPathwayReaction(Long wid) {
        this.wid = wid;
    }

    public KEGGPathwayReaction(Long wid, long kEGGPathwayWID, int id, String entry) {
        this.wid = wid;
        this.kEGGPathwayWID = kEGGPathwayWID;
        this.id = id;
        this.entry = entry;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
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
        final KEGGPathwayReaction other = (KEGGPathwayReaction) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGPathwayWID != other.kEGGPathwayWID) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.entry, other.entry)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGPathwayReaction{" + "wid=" + wid + ", kEGGPathwayWID=" + kEGGPathwayWID + ", id=" + id + ", entry=" + entry + ", type=" + type + '}';
    }
}
