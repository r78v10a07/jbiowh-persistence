package org.jbiowhpersistence.datasets.protclust.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the UniRefMemberProperty entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Aug 31, 2011
 */
@Entity
@Table(name = "UniRefMemberProperty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniRefMemberProperty.findAll", query = "SELECT u FROM UniRefMemberProperty u"),
    @NamedQuery(name = "UniRefMemberProperty.findByWid", query = "SELECT u FROM UniRefMemberProperty u WHERE u.wid = :wid"),
    @NamedQuery(name = "UniRefMemberProperty.findByUniRefMemberWID", query = "SELECT u FROM UniRefMemberProperty u WHERE u.uniRefMemberWID = :uniRefMemberWID"),
    @NamedQuery(name = "UniRefMemberProperty.findByType", query = "SELECT u FROM UniRefMemberProperty u WHERE u.type = :type"),
    @NamedQuery(name = "UniRefMemberProperty.findByValue", query = "SELECT u FROM UniRefMemberProperty u WHERE u.value = :value")})
public class UniRefMemberProperty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "UniRefMember_WID")
    private long uniRefMemberWID;
    @Column(name = "Type")
    private String type;
    @Column(name = "Value")
    private String value;
    // Internal relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UniRefMember_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private UniRefMember uniRefMember;

    public UniRefMemberProperty() {
    }

    public UniRefMemberProperty(Long wid) {
        this.wid = wid;
    }

    public UniRefMemberProperty(Long wid, long uniRefMemberWID) {
        this.wid = wid;
        this.uniRefMemberWID = uniRefMemberWID;
    }

    public UniRefMember getUniRefMember() {
        return uniRefMember;
    }

    public void setUniRefMember(UniRefMember uniRefMember) {
        this.uniRefMember = uniRefMember;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getUniRefMemberWID() {
        return uniRefMemberWID;
    }

    public void setUniRefMemberWID(long uniRefMemberWID) {
        this.uniRefMemberWID = uniRefMemberWID;
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
        final UniRefMemberProperty other = (UniRefMemberProperty) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.uniRefMemberWID != other.uniRefMemberWID) {
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
        return "UniRefMemberProperty{" + "wid=" + wid + ", uniRefMemberWID=" + uniRefMemberWID + ", type=" + type + ", value=" + value + '}';
    }
}
