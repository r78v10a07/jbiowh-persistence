package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This Class is the ProteinCommentLinkPK
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Embeddable
public class ProteinCommentLinkPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinComment_WID")
    private long proteinCommentWID;
    @Basic(optional = false)
    @Column(name = "URI")
    private String uri;

    public ProteinCommentLinkPK() {
    }

    public ProteinCommentLinkPK(long proteinCommentWID, String uri) {
        this.proteinCommentWID = proteinCommentWID;
        this.uri = uri;
    }

    public long getProteinCommentWID() {
        return proteinCommentWID;
    }

    public void setProteinCommentWID(long proteinCommentWID) {
        this.proteinCommentWID = proteinCommentWID;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proteinCommentWID;
        hash += (uri != null ? uri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProteinCommentLinkPK)) {
            return false;
        }
        ProteinCommentLinkPK other = (ProteinCommentLinkPK) object;
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.edu.cujae.biowh.parser.protein.entities.ProteinCommentLinkPK[ proteinCommentWID=" + proteinCommentWID + ", uri=" + uri + " ]";
    }
}
