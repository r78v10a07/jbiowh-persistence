package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Comment Isoform entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
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

    @ElementCollection
    @CollectionTable(
            name = "ProteinIsoformId",
            joinColumns
            = @JoinColumn(name = "ProteinCommentIsoform_WID"))
    @XmlElementWrapper( name="ProteinIsoformIds" )
    private Collection<ProteinIsoformId> proteinIsoformId;
    @ElementCollection
    @CollectionTable(
            name = "ProteinIsoformName",
            joinColumns
            = @JoinColumn(name = "ProteinCommentIsoform_WID"))
    @XmlElementWrapper( name="ProteinIsoformNames" )
    private Collection<ProteinIsoformName> proteinIsoformName;

    public ProteinCommentIsoform() {
    }

    public ProteinCommentIsoform(Long wid) {
        this.wid = wid;
    }

    public ProteinCommentIsoform(Long wid, long proteinCommentWID) {
        this.wid = wid;
        this.proteinCommentWID = proteinCommentWID;
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

    public ProteinComment getProteinComment() {
        return proteinComment;
    }

    public void setProteinComment(ProteinComment proteinComment) {
        this.proteinComment = proteinComment;
    }

    public Collection<ProteinIsoformId> getProteinIsoformId() {
        return proteinIsoformId;
    }

    public void setProteinIsoformId(Collection<ProteinIsoformId> proteinIsoformId) {
        this.proteinIsoformId = proteinIsoformId;
    }

    public Collection<ProteinIsoformName> getProteinIsoformName() {
        return proteinIsoformName;
    }

    public void setProteinIsoformName(Collection<ProteinIsoformName> proteinIsoformName) {
        this.proteinIsoformName = proteinIsoformName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 67 * hash + (int) (this.proteinCommentWID ^ (this.proteinCommentWID >>> 32));
        hash = 67 * hash + (this.seqType != null ? this.seqType.hashCode() : 0);
        hash = 67 * hash + (this.seqRef != null ? this.seqRef.hashCode() : 0);
        hash = 67 * hash + (this.note != null ? this.note.hashCode() : 0);
        hash = 67 * hash + (this.noteEvidence != null ? this.noteEvidence.hashCode() : 0);
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
        final ProteinCommentIsoform other = (ProteinCommentIsoform) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.proteinCommentWID != other.proteinCommentWID) {
            return false;
        }
        if ((this.seqType == null) ? (other.seqType != null) : !this.seqType.equals(other.seqType)) {
            return false;
        }
        if ((this.seqRef == null) ? (other.seqRef != null) : !this.seqRef.equals(other.seqRef)) {
            return false;
        }
        if ((this.note == null) ? (other.note != null) : !this.note.equals(other.note)) {
            return false;
        }
        if ((this.noteEvidence == null) ? (other.noteEvidence != null) : !this.noteEvidence.equals(other.noteEvidence)) {
            return false;
        }
        if (this.proteinIsoformId != other.proteinIsoformId && (this.proteinIsoformId == null || !this.proteinIsoformId.equals(other.proteinIsoformId))) {
            return false;
        }
        return this.proteinIsoformName == other.proteinIsoformName || (this.proteinIsoformName != null && this.proteinIsoformName.equals(other.proteinIsoformName));
    }

    @Override
    public String toString() {
        StringBuilder pData = new StringBuilder();

        for (ProteinIsoformId s : proteinIsoformId) {
            pData.append("\t").append(s).append("\n");
        }
        for (ProteinIsoformName s : proteinIsoformName) {
            pData.append("\t").append(s).append("\n");
        }
        return "ProteinCommentIsoform{"
                + "wid=" + wid
                + ", proteinCommentWID=" + proteinCommentWID
                + ", seqType=" + seqType
                + ", seqRef=" + seqRef
                + ", note=" + note
                + ", noteEvidence=" + noteEvidence
                + "\n"
                + pData.toString()
                + '}';
    }
}
