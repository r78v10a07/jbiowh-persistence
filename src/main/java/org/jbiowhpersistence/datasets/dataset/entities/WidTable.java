package org.jbiowhpersistence.datasets.dataset.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the WIDTable entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Jun 17, 2011
 */
@Entity
@Table(name = "WIDTable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WidTable.findAll", query = "SELECT w FROM WidTable w"),
    @NamedQuery(name = "WidTable.findByWid", query = "SELECT w FROM WidTable w WHERE w.wid = :wid"),
    @NamedQuery(name = "WidTable.findByPreviousWID", query = "SELECT w FROM WidTable w WHERE w.previousWID = :previousWID")})
public class WidTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "PreviousWID")
    private long previousWID;

    /**
     * Creates a WidTable entity
     */
    public WidTable() {
    }

    /**
     * Creates a WidTable entity with Id
     *
     * @param wid the WidTable's Id
     */
    public WidTable(Long wid) {
        this.wid = wid;
    }

    /**
     * Creates a WidTable entity with parameters
     *
     * @param wid the WidTable's Id
     * @param previousWID the WidTable's previous Id
     */
    public WidTable(Long wid, long previousWID) {
        this.wid = wid;
        this.previousWID = previousWID;
    }

    /**
     * Get the WidTable's Id
     *
     * @return the WidTable's Id
     */
    public Long getWid() {
        return wid;
    }

    /**
     * Set the WidTable's Id
     *
     * @param wid the WidTable's Id
     */
    public void setWid(Long wid) {
        this.wid = wid;
    }

    /**
     * Get the WidTable's previous Id
     *
     * @return the WidTable's previous Id
     */
    public long getPreviousWID() {
        return previousWID;
    }

    /**
     * Set the WidTable's previous Id
     *
     * @param previousWID the WidTable's previous Id
     */
    public void setPreviousWID(long previousWID) {
        this.previousWID = previousWID;
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
        final WidTable other = (WidTable) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.previousWID != other.previousWID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WidTable{" + "wid=" + wid + ", previousWID=" + previousWID + '}';
    }
}
