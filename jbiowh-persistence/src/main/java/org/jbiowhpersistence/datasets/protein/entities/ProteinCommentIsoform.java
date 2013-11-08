package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This Class is the Protein Comment Isoform entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinCommentIsoform")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinCommentIsoform.findAll", query = "SELECT p FROM ProteinCommentIsoform p"),
    @NamedQuery(name = "ProteinCommentIsoform.findByWid", query = "SELECT p FROM ProteinCommentIsoform p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinCommentIsoform.findByProteinCommentWID", query = "SELECT p FROM ProteinCommentIsoform p WHERE p.proteinCommentWID = :proteinCommentWID"),
    @NamedQuery(name = "ProteinCommentIsoform.findBySeqType", query = "SELECT p FROM ProteinCommentIsoform p WHERE p.seqType = :seqType"),
    @NamedQuery(name = "ProteinCommentIsoform.findBySeqRef", query = "SELECT p FROM ProteinCommentIsoform p WHERE p.seqRef = :seqRef"),
    @NamedQuery(name = "ProteinCommentIsoform.findByNoteEvidence", query = "SELECT p FROM ProteinCommentIsoform p WHERE p.noteEvidence = :noteEvidence")})
public class ProteinCommentIsoform implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "ProteinComment_WID")
    private long proteinCommentWID;
    @Column(name = "SeqType")
    private String seqType;
    @Column(name = "SeqRef")
    private String seqRef;
    @Lob
    @Column(name = "Note")
    private String note;
    @Column(name = "NoteEvidence")
    private String noteEvidence;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProteinComment_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private ProteinComment proteinComment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinCommentIsoform")
    @MapKey(name = "proteinIsoformIdPK")
    private Map<ProteinIsoformIdPK, ProteinIsoformId> proteinIsoformId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinCommentIsoform")
    @MapKey(name = "proteinIsoformNamePK")
    private Map<ProteinIsoformNamePK, ProteinIsoformName> proteinIsoformName;

    public ProteinCommentIsoform() {
    }

    public ProteinCommentIsoform(Long wid) {
        this.wid = wid;
    }

    public ProteinCommentIsoform(Long wid, long proteinCommentWID) {
        this.wid = wid;
        this.proteinCommentWID = proteinCommentWID;
    }

    @XmlTransient
    public Map<ProteinIsoformNamePK, ProteinIsoformName> getProteinIsoformName() {
        return proteinIsoformName;
    }

    public void setProteinIsoformName(Map<ProteinIsoformNamePK, ProteinIsoformName> proteinIsoformName) {
        this.proteinIsoformName = proteinIsoformName;
    }

    @XmlTransient
    public Map<ProteinIsoformIdPK, ProteinIsoformId> getProteinIsoformId() {
        return proteinIsoformId;
    }

    public void setProteinIsoformId(Map<ProteinIsoformIdPK, ProteinIsoformId> proteinIsoformId) {
        this.proteinIsoformId = proteinIsoformId;
    }

    public ProteinComment getProteinComment() {
        return proteinComment;
    }

    public void setProteinComment(ProteinComment proteinComment) {
        this.proteinComment = proteinComment;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getProteinCommentWID() {
        return proteinCommentWID;
    }

    public void setProteinCommentWID(long proteinCommentWID) {
        this.proteinCommentWID = proteinCommentWID;
    }

    public String getSeqType() {
        return seqType;
    }

    public void setSeqType(String seqType) {
        this.seqType = seqType;
    }

    public String getSeqRef() {
        return seqRef;
    }

    public void setSeqRef(String seqRef) {
        this.seqRef = seqRef;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteEvidence() {
        return noteEvidence;
    }

    public void setNoteEvidence(String noteEvidence) {
        this.noteEvidence = noteEvidence;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinCommentIsoform other = (ProteinCommentIsoform) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        if (!Objects.equals(this.seqType, other.seqType)) {
            return false;
        }
        if (!Objects.equals(this.seqRef, other.seqRef)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.noteEvidence, other.noteEvidence)) {
            return false;
        }
        if (!Objects.equals(this.proteinIsoformId, other.proteinIsoformId)) {
            return false;
        }
        if (!Objects.equals(this.proteinIsoformName, other.proteinIsoformName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        Iterator it;
        StringBuilder cData = new StringBuilder();

        if (getProteinIsoformId() != null) {
            if (!getProteinIsoformId().isEmpty()) {
                it = getProteinIsoformId().values().iterator();
                while (it.hasNext()) {
                    cData.append("\t\t\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinIsoformName() != null) {
            if (!getProteinIsoformName().isEmpty()) {
                it = getProteinIsoformName().values().iterator();
                while (it.hasNext()) {
                    cData.append("\t\t\t").append(it.next()).append("\n");
                }
            }
        }

        return "ProteinCommentIsoform{"
                + " wid=" + wid
                + " commentWID=" + proteinCommentWID
                + " seqType=" + seqType
                + " seqRef=" + seqRef
                + " note=" + note
                + " noteEvidence=" + noteEvidence
                + "}\n"
                + cData;
    }
}
