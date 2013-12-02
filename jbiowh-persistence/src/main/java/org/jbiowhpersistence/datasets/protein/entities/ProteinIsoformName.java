package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Isoform Name entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinIsoformName")
@XmlRootElement
public class ProteinIsoformName implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinCommentIsoform_WID")
    private long proteinCommentIsoformWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;

    public ProteinIsoformName() {
    }

    public ProteinIsoformName(long proteinCommentIsoformWID, String name) {
        this.proteinCommentIsoformWID = proteinCommentIsoformWID;
        this.name = name;
    }

    public long getProteinCommentIsoformWID() {
        return proteinCommentIsoformWID;
    }

    public void setProteinCommentIsoformWID(long proteinCommentIsoformWID) {
        this.proteinCommentIsoformWID = proteinCommentIsoformWID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.proteinCommentIsoformWID ^ (this.proteinCommentIsoformWID >>> 32));
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        final ProteinIsoformName other = (ProteinIsoformName) obj;
        if (this.proteinCommentIsoformWID != other.proteinCommentIsoformWID) {
            return false;
        }
        return !((this.name == null) ? (other.name != null) : !this.name.equals(other.name));
    }

    @Override
    public String toString() {
        return "ProteinIsoformName{" + "proteinCommentIsoformWID=" + proteinCommentIsoformWID + ", name=" + name + '}';
    }
}
