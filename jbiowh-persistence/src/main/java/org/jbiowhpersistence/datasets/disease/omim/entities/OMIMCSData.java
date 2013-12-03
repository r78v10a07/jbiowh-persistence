package org.jbiowhpersistence.datasets.disease.omim.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OMIMCSData entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Jul 20, 2012
 */
@Embeddable
@Table(name = "OMIMCSData")
@XmlRootElement
public class OMIMCSData implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "Data")
    private String data;

    public OMIMCSData() {
    }

    public OMIMCSData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.data != null ? this.data.hashCode() : 0);
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
        final OMIMCSData other = (OMIMCSData) obj;
        return !((this.data == null) ? (other.data != null) : !this.data.equals(other.data));
    }

    @Override
    public String toString() {
        return "OMIMCSData{data=" + data + '}';
    }
}
