package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the PfamMarkupKey entity
 *
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2012-11-27 12:19:50 +0100 (Tue, 27 Nov 2012) $ 
 * $LastChangedRevision: 344 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamMarkupKey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamMarkupKey.findAll", query = "SELECT p FROM PfamMarkupKey p"),
    @NamedQuery(name = "PfamMarkupKey.findByWid", query = "SELECT p FROM PfamMarkupKey p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamMarkupKey.findByLabel", query = "SELECT p FROM PfamMarkupKey p WHERE p.label = :label")})
public class PfamMarkupKey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Column(name = "label")
    private String label;

    public PfamMarkupKey() {
    }

    public PfamMarkupKey(Long wid) {
        this.wid = wid;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        final PfamMarkupKey other = (PfamMarkupKey) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamMarkupKey{" + "wid=" + wid + ", label=" + label + '}';
    }
}
