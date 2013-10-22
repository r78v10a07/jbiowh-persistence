package org.jbiowhpersistence.datasets.ontology.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the OntologyXRef entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $ 
 * $LastChangedRevision: 270 $
 * @since Jun 28, 2011
 */
@Entity
@Table(name = "OntologyXRef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OntologyXRef.findAll", query = "SELECT o FROM OntologyXRef o"),
    @NamedQuery(name = "OntologyXRef.findByWid", query = "SELECT o FROM OntologyXRef o WHERE o.wid = :wid"),
    @NamedQuery(name = "OntologyXRef.findByAcc", query = "SELECT o FROM OntologyXRef o WHERE o.acc = :acc"),
    @NamedQuery(name = "OntologyXRef.findByDBName", query = "SELECT o FROM OntologyXRef o WHERE o.dBName = :dBName"),
    @NamedQuery(name = "OntologyXRef.findByName", query = "SELECT o FROM OntologyXRef o WHERE o.name = :name"),
    @NamedQuery(name = "OntologyXRef.findByType", query = "SELECT o FROM OntologyXRef o WHERE o.type = :type")})
public class OntologyXRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "ACC")
    private String acc;
    @Basic(optional = false)
    @Column(name = "DBName")
    private String dBName;
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;

    public OntologyXRef() {
    }

    public OntologyXRef(Long wid) {
        this.wid = wid;
    }

    public OntologyXRef(Long wid, String acc, String dBName, String type) {
        this.wid = wid;
        this.acc = acc;
        this.dBName = dBName;
        this.type = type;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getDBName() {
        return dBName;
    }

    public void setDBName(String dBName) {
        this.dBName = dBName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OntologyXRef other = (OntologyXRef) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.acc, other.acc)) {
            return false;
        }
        if (!Objects.equals(this.dBName, other.dBName)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
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
        return "OntologyXRef["
                + " wid=" + wid
                + " acc=" + acc
                + " dBName=" + dBName
                + " name=" + name
                + " type=" + type + ']';
    }
}
