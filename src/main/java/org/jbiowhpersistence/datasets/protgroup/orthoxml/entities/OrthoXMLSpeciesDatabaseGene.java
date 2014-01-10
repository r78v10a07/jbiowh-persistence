package org.jbiowhpersistence.datasets.protgroup.orthoxml.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the OrthoXMLSpeciesDatabaseGene entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 7, 2014
 */
@Embeddable
@Table(name = "OrthoXMLSpeciesDatabaseGene")
@XmlRootElement
public class OrthoXMLSpeciesDatabaseGene implements Serializable {

    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "GeneId")
    private String geneId;
    @Basic(optional = false)
    @Column(name = "ProtId")
    private String protId;
    @Basic(optional = false)
    @Column(name = "TranscriptId")
    private String transcriptId;

    public OrthoXMLSpeciesDatabaseGene() {
    }

    public OrthoXMLSpeciesDatabaseGene(Integer id, String geneId, String protId, String transcriptId) {
        this.id = id;
        this.geneId = geneId;
        this.protId = protId;
        this.transcriptId = transcriptId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGeneId() {
        return geneId;
    }

    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    public String getProtId() {
        return protId;
    }

    public void setProtId(String protId) {
        this.protId = protId;
    }

    public String getTranscriptId() {
        return transcriptId;
    }

    public void setTranscriptId(String transcriptId) {
        this.transcriptId = transcriptId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.geneId != null ? this.geneId.hashCode() : 0);
        hash = 79 * hash + (this.protId != null ? this.protId.hashCode() : 0);
        hash = 79 * hash + (this.transcriptId != null ? this.transcriptId.hashCode() : 0);
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
        final OrthoXMLSpeciesDatabaseGene other = (OrthoXMLSpeciesDatabaseGene) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.geneId == null) ? (other.geneId != null) : !this.geneId.equals(other.geneId)) {
            return false;
        }
        if ((this.protId == null) ? (other.protId != null) : !this.protId.equals(other.protId)) {
            return false;
        }
        return !((this.transcriptId == null) ? (other.transcriptId != null) : !this.transcriptId.equals(other.transcriptId));
    }

    @Override
    public String toString() {
        return "OrthoXMLSpeciesDatabaseGene{" + "id=" + id + ", geneId=" + geneId + ", protId=" + protId + ", transcriptId=" + transcriptId + '}';
    }
}
