package org.jbiowhpersistence.datasets.protgroup.cog.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 * This class is the COGFuncClassGroup entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 19, 2013
 */
@Entity
@Table(name = "COGFuncClassGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "COGFuncClassGroup.findAll", query = "SELECT c FROM COGFuncClassGroup c"),
    @NamedQuery(name = "COGFuncClassGroup.findByWid", query = "SELECT c FROM COGFuncClassGroup c WHERE c.wid = :wid"),
    @NamedQuery(name = "COGFuncClassGroup.findByName", query = "SELECT c FROM COGFuncClassGroup c WHERE c.name = :name")})
public class COGFuncClassGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    // Internla relationship
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cogFuncClassGroup")
    @XmlElement
    @XmlInverseReference(mappedBy = "cogFuncClassGroup")
    @XmlElementWrapper(name = "COGFuncClasses")
    private Set<COGFuncClass> cogFuncClass;

    public COGFuncClassGroup() {
    }

    public COGFuncClassGroup(Long wid) {
        this.wid = wid;
    }

    public COGFuncClassGroup(Long wid, String name) {
        this.wid = wid;
        this.name = name;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Set<COGFuncClass> getCogFuncClass() {
        return cogFuncClass;
    }

    public void setCogFuncClass(Set<COGFuncClass> cogFuncClass) {
        this.cogFuncClass = cogFuncClass;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 73 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        final COGFuncClassGroup other = (COGFuncClassGroup) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        return !((this.name == null) ? (other.name != null) : !this.name.equals(other.name));
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();

        if (cogFuncClass != null) {
            for (COGFuncClass d : cogFuncClass) {
                b.append("\t").append(d).append("\n");
            }
        }
        return "COGFuncClassGroup{" + "wid=" + wid + ", name=" + name + "}\n" + b.toString();
    }
}
