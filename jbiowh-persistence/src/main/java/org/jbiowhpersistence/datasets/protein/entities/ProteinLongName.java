package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Long Name
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
 * @since Aug 11, 2011
 */
@Embeddable
@Table(name = "ProteinLongName")
@XmlRootElement
public class ProteinLongName implements Serializable {

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

    public ProteinLongName() {
    }

    public ProteinLongName(long proteinWID, String proteinNameDef, String name) {
        this.proteinWID = proteinWID;
        this.proteinNameDef = proteinNameDef;
        this.name = name;
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

    public Boolean isComponent() {
        return component;
    }

    public void setComponent(Boolean component) {
        this.component = component;
    }

    public Boolean isDomain() {
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
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.proteinWID ^ (this.proteinWID >>> 32));
        hash = 29 * hash + (this.proteinNameDef != null ? this.proteinNameDef.hashCode() : 0);
        hash = 29 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 29 * hash + (this.component != null ? this.component.hashCode() : 0);
        hash = 29 * hash + (this.domain != null ? this.domain.hashCode() : 0);
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.evidence != null ? this.evidence.hashCode() : 0);
        hash = 29 * hash + (this.status != null ? this.status.hashCode() : 0);
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
        final ProteinLongName other = (ProteinLongName) obj;
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if ((this.proteinNameDef == null) ? (other.proteinNameDef != null) : !this.proteinNameDef.equals(other.proteinNameDef)) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if (this.component != other.component && (this.component == null || !this.component.equals(other.component))) {
            return false;
        }
        if (this.domain != other.domain && (this.domain == null || !this.domain.equals(other.domain))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.evidence == null) ? (other.evidence != null) : !this.evidence.equals(other.evidence)) {
            return false;
        }
        return !((this.status == null) ? (other.status != null) : !this.status.equals(other.status));
    }

    @Override
    public String toString() {
        return "ProteinLongName{" + "proteinWID=" + proteinWID + ", proteinNameDef=" + proteinNameDef + ", type=" + type + ", component=" + component + ", domain=" + domain + ", name=" + name + ", evidence=" + evidence + ", status=" + status + '}';
    }
}
