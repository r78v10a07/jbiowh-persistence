package org.jbiowhpersistence.datasets.taxonomy.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the TaxonomyUnParseCitation entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Jun 21, 2011
 */
@Entity
@Table(name = "TaxonomyUnParseCitation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxonomyUnParseCitation.findAll", query = "SELECT t FROM TaxonomyUnParseCitation t"),
    @NamedQuery(name = "TaxonomyUnParseCitation.findByWid", query = "SELECT t FROM TaxonomyUnParseCitation t WHERE t.wid = :wid"),
    @NamedQuery(name = "TaxonomyUnParseCitation.findByCitId", query = "SELECT t FROM TaxonomyUnParseCitation t WHERE t.citId = :citId"),
    @NamedQuery(name = "TaxonomyUnParseCitation.findByCitKey", query = "SELECT t FROM TaxonomyUnParseCitation t WHERE t.citKey = :citKey"),
    @NamedQuery(name = "TaxonomyUnParseCitation.findByUrl", query = "SELECT t FROM TaxonomyUnParseCitation t WHERE t.url = :url"),
    @NamedQuery(name = "TaxonomyUnParseCitation.findByText", query = "SELECT t FROM TaxonomyUnParseCitation t WHERE t.text = :text")})
public class TaxonomyUnParseCitation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "CitId")
    private int citId;
    @Column(name = "CitKey")
    private String citKey;
    @Column(name = "URL")
    private String url;
    @Column(name = "Text")
    private String text;

    public TaxonomyUnParseCitation() {
    }

    public TaxonomyUnParseCitation(Long wid) {
        this.wid = wid;
    }

    public TaxonomyUnParseCitation(Long wid, int citId) {
        this.wid = wid;
        this.citId = citId;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public int getCitId() {
        return citId;
    }

    public void setCitId(int citId) {
        this.citId = citId;
    }

    public String getCitKey() {
        return citKey;
    }

    public void setCitKey(String citKey) {
        this.citKey = citKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaxonomyUnParseCitation other = (TaxonomyUnParseCitation) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.citId != other.citId) {
            return false;
        }
        if (!Objects.equals(this.citKey, other.citKey)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.wid);
        return hash;
    }

    @Override
    public String toString() {
        return "TaxonomyUnParseCitation{"
                + "wid=" + wid
                + ", citId=" + citId
                + ", citKey=" + citKey
                + ", url=" + url
                + ", text=" + text + " }";
    }
}
