package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Accession NUmber entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinAccessionNumber")
@XmlRootElement
public class ProteinAccessionNumber implements Serializable {

    @Basic(optional = false)
    @Column(name = "AccessionNumber")
    private String accessionNumber;
    @Basic(optional = false)
    @Column(name = "OrderNumber")
    private int orderNumber;

    public ProteinAccessionNumber() {
    }

    public ProteinAccessionNumber(String accessionNumber, int orderNumber) {
        this.accessionNumber = accessionNumber;
        this.orderNumber = orderNumber;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.accessionNumber != null ? this.accessionNumber.hashCode() : 0);
        hash = 79 * hash + this.orderNumber;
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
        final ProteinAccessionNumber other = (ProteinAccessionNumber) obj;
        if ((this.accessionNumber == null) ? (other.accessionNumber != null) : !this.accessionNumber.equals(other.accessionNumber)) {
            return false;
        }
        return this.orderNumber == other.orderNumber;
    }

    @Override
    public String toString() {
        return "ProteinAccessionNumber{accessionNumber=" + accessionNumber + ", orderNumber=" + orderNumber + '}';
    }
}
