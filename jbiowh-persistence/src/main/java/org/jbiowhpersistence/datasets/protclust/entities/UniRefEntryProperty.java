package org.jbiowhpersistence.datasets.protclust.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the UniRefEntryProperty entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 31, 2011
 */
@Entity
@Table(name = "UniRefEntryProperty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniRefEntryProperty.findAll", query = "SELECT u FROM UniRefEntryProperty u"),
    @NamedQuery(name = "UniRefEntryProperty.findByWid", query = "SELECT u FROM UniRefEntryProperty u WHERE u.wid = :wid"),
    @NamedQuery(name = "UniRefEntryProperty.findByUniRefEntryWID", query = "SELECT u FROM UniRefEntryProperty u WHERE u.uniRefEntryWID = :uniRefEntryWID"),
    @NamedQuery(name = "UniRefEntryProperty.findByType", query = "SELECT u FROM UniRefEntryProperty u WHERE u.type = :type"),
    @NamedQuery(name = "UniRefEntryProperty.findByValue", query = "SELECT u FROM UniRefEntryProperty u WHERE u.value = :value")})
public class UniRefEntryProperty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "UniRefEntry_WID")
    private long uniRefEntryWID;
    @Column(name = "Type")
    private String type;
    @Column(name = "Value")
    private String value;
    // Internal relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UniRefEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private UniRefEntry uniRefEntry;

    public UniRefEntryProperty() {
    }

    public UniRefEntryProperty(Long wid) {
        this.wid = wid;
    }

    public UniRefEntryProperty(Long wid, long uniRefEntryWID) {
        this.wid = wid;
        this.uniRefEntryWID = uniRefEntryWID;
    }

    public UniRefEntry getUniRefEntry() {
        return uniRefEntry;
    }

    public void setUniRefEntry(UniRefEntry uniRefEntry) {
        this.uniRefEntry = uniRefEntry;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getUniRefEntryWID() {
        return uniRefEntryWID;
    }

    public void setUniRefEntryWID(long uniRefEntryWID) {
        this.uniRefEntryWID = uniRefEntryWID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        final UniRefEntryProperty other = (UniRefEntryProperty) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.uniRefEntryWID != other.uniRefEntryWID) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UniRefEntryProperty{" + "wid=" + wid + ", uniRefEntryWID=" + uniRefEntryWID + ", type=" + type + ", value=" + value + ", uniRefEntry=" + uniRefEntry + '}';
    }
}
