package org.jbiowhpersistence.datasets.pathway.kegg.entities.reaction;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Reaction Name entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 17, 2011
 */
@Entity
@Table(name = "KEGGReactionName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGReactionName.findAll", query = "SELECT k FROM KEGGReactionName k"),
    @NamedQuery(name = "KEGGReactionName.findByWid", query = "SELECT k FROM KEGGReactionName k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGReactionName.findByKEGGReactionWID", query = "SELECT k FROM KEGGReactionName k WHERE k.kEGGReactionWID = :kEGGReactionWID"),
    @NamedQuery(name = "KEGGReactionName.findByName", query = "SELECT k FROM KEGGReactionName k WHERE k.name = :name")})
public class KEGGReactionName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGReaction_WID")
    private long kEGGReactionWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGReaction_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGReaction kEGGReaction;

    public KEGGReactionName() {
    }

    public KEGGReactionName(Long wid) {
        this.wid = wid;
    }

    public KEGGReactionName(Long wid, long kEGGReactionWID, String name) {
        this.wid = wid;
        this.kEGGReactionWID = kEGGReactionWID;
        this.name = name;
    }

    public KEGGReaction getkEGGReaction() {
        return kEGGReaction;
    }

    public void setkEGGReaction(KEGGReaction kEGGReaction) {
        this.kEGGReaction = kEGGReaction;
    }

    public long getkEGGReactionWID() {
        return kEGGReactionWID;
    }

    public void setkEGGReactionWID(long kEGGReactionWID) {
        this.kEGGReactionWID = kEGGReactionWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getKEGGReactionWID() {
        return kEGGReactionWID;
    }

    public void setKEGGReactionWID(long kEGGReactionWID) {
        this.kEGGReactionWID = kEGGReactionWID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        final KEGGReactionName other = (KEGGReactionName) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGReactionWID != other.kEGGReactionWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGReactionName{" + "wid=" + wid + ", kEGGReactionWID=" + kEGGReactionWID + ", name=" + name + '}';
    }
}
