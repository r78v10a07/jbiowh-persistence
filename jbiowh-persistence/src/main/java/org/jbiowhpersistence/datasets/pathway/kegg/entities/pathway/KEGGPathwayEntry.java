package org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Pathway Entry entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 26, 2011
 */
@Entity
@Table(name = "KEGGPathwayEntry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGPathwayEntry.findAll", query = "SELECT k FROM KEGGPathwayEntry k"),
    @NamedQuery(name = "KEGGPathwayEntry.findByWid", query = "SELECT k FROM KEGGPathwayEntry k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGPathwayEntry.findByKEGGPathwayWID", query = "SELECT k FROM KEGGPathwayEntry k WHERE k.kEGGPathwayWID = :kEGGPathwayWID"),
    @NamedQuery(name = "KEGGPathwayEntry.findById", query = "SELECT k FROM KEGGPathwayEntry k WHERE k.id = :id"),
    @NamedQuery(name = "KEGGPathwayEntry.findByEntry", query = "SELECT k FROM KEGGPathwayEntry k WHERE k.entry = :entry"),
    @NamedQuery(name = "KEGGPathwayEntry.findByType", query = "SELECT k FROM KEGGPathwayEntry k WHERE k.type = :type"),
    @NamedQuery(name = "KEGGPathwayEntry.findByReaction", query = "SELECT k FROM KEGGPathwayEntry k WHERE k.reaction = :reaction"),
    @NamedQuery(name = "KEGGPathwayEntry.findByLink", query = "SELECT k FROM KEGGPathwayEntry k WHERE k.link = :link")})
public class KEGGPathwayEntry implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @Column(name = "Reaction")
    private String reaction;
    @Column(name = "Link")
    private String link;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEGGPathwayEntry")
    private Set<KEGGPathwayEntryGraphic> kEGGPathwayEntryGraphic;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGPathway_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGPathway kEGGPathway;

    public KEGGPathwayEntry() {
    }

    public KEGGPathwayEntry(Long wid) {
        this.wid = wid;
    }

    public KEGGPathwayEntry(Long wid, long kEGGPathwayWID, int id, String entry, String type, String reaction) {
        this.wid = wid;
        this.kEGGPathwayWID = kEGGPathwayWID;
        this.id = id;
        this.entry = entry;
        this.type = type;
        this.reaction = reaction;
    }

    public KEGGPathway getkEGGPathway() {
        return kEGGPathway;
    }

    public void setkEGGPathway(KEGGPathway kEGGPathway) {
        this.kEGGPathway = kEGGPathway;
    }

    public Set<KEGGPathwayEntryGraphic> getkEGGPathwayEntryGraphic() {
        return kEGGPathwayEntryGraphic;
    }

    public void setkEGGPathwayEntryGraphic(Set<KEGGPathwayEntryGraphic> kEGGPathwayEntryGraphic) {
        this.kEGGPathwayEntryGraphic = kEGGPathwayEntryGraphic;
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

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        final KEGGPathwayEntry other = (KEGGPathwayEntry) obj;
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
        if (!Objects.equals(this.reaction, other.reaction)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGPathwayEntry{" + "wid=" + wid + ", kEGGPathwayWID=" + kEGGPathwayWID + ", id=" + id + ", entry=" + entry + ", type=" + type + ", reaction=" + reaction + ", link=" + link + '}';
    }
}
