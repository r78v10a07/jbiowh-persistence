package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Long Name
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinLongName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinLongName.findAll", query = "SELECT p FROM ProteinLongName p"),
    @NamedQuery(name = "ProteinLongName.findByWid", query = "SELECT p FROM ProteinLongName p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinLongName.findByProteinWID", query = "SELECT p FROM ProteinLongName p WHERE p.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinLongName.findByProteinNameDef", query = "SELECT p FROM ProteinLongName p WHERE p.proteinNameDef = :proteinNameDef"),
    @NamedQuery(name = "ProteinLongName.findByType", query = "SELECT p FROM ProteinLongName p WHERE p.type = :type"),
    @NamedQuery(name = "ProteinLongName.findByComponent", query = "SELECT p FROM ProteinLongName p WHERE p.component = :component"),
    @NamedQuery(name = "ProteinLongName.findByDomain", query = "SELECT p FROM ProteinLongName p WHERE p.domain = :domain"),
    @NamedQuery(name = "ProteinLongName.findByName", query = "SELECT p FROM ProteinLongName p WHERE p.name LIKE :name"),
    @NamedQuery(name = "ProteinLongName.findByEvidence", query = "SELECT p FROM ProteinLongName p WHERE p.evidence = :evidence"),
    @NamedQuery(name = "ProteinLongName.findByStatus", query = "SELECT p FROM ProteinLongName p WHERE p.status = :status")})
public class ProteinLongName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Basic(optional = false)
    @Column(name = "ProteinNameDef")
    private String proteinNameDef;
    @Column(name = "Type")
    private String type;
    @Column(name = "Component")
    private Boolean component;
    @Column(name = "Domain")
    private Boolean domain;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Evidence")
    private String evidence;
    @Column(name = "Status")
    private String status;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;

    public ProteinLongName() {
    }

    public ProteinLongName(Long wid) {
        this.wid = wid;
    }

    public ProteinLongName(Long wid, long proteinWID, String proteinNameDef, String name) {
        this.wid = wid;
        this.proteinWID = proteinWID;
        this.proteinNameDef = proteinNameDef;
        this.name = name;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getProteinWID() {
        return proteinWID;
    }

    public void setProteinWID(long proteinWID) {
        this.proteinWID = proteinWID;
    }

    public String getProteinNameDef() {
        return proteinNameDef;
    }

    public void setProteinNameDef(String proteinNameDef) {
        this.proteinNameDef = proteinNameDef;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getComponent() {
        return component;
    }

    public void setComponent(Boolean component) {
        this.component = component;
    }

    public Boolean getDomain() {
        return domain;
    }

    public void setDomain(Boolean domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinLongName other = (ProteinLongName) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if (!Objects.equals(this.proteinNameDef, other.proteinNameDef)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.component, other.component)) {
            return false;
        }
        if (!Objects.equals(this.domain, other.domain)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.evidence, other.evidence)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
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
        return "ProteinProteinName{"
                + " wid=" + wid
                + " proteinWID=" + proteinWID
                + " proteinNameDef=" + proteinNameDef
                + " type=" + type
                + " component=" + component
                + " domain=" + domain
                + " name=" + name
                + " evidence=" + evidence
                + " status=" + status
                + '}';
    }
}
