package org.jbiowhpersistence.datasets.protgroup.cog.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 * This class is the COGFuncClass entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 19, 2013
 */
@Entity
@Table(name = "COGFuncClass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "COGFuncClass.findAll", query = "SELECT c FROM COGFuncClass c"),
    @NamedQuery(name = "COGFuncClass.findByWid", query = "SELECT c FROM COGFuncClass c WHERE c.wid = :wid"),
    @NamedQuery(name = "COGFuncClass.findByLetter", query = "SELECT c FROM COGFuncClass c WHERE c.letter = :letter"),
    @NamedQuery(name = "COGFuncClass.findByName", query = "SELECT c FROM COGFuncClass c WHERE c.name = :name"),
    @NamedQuery(name = "COGFuncClass.findByCOGFuncClassGroupWID", query = "SELECT c FROM COGFuncClass c WHERE c.cOGFuncClassGroupWID = :cOGFuncClassGroupWID")})
public class COGFuncClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Letter")
    private char letter;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "COGFuncClassGroup_WID")
    private long cOGFuncClassGroupWID;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COGFuncClassGroup_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private COGFuncClassGroup cogFuncClassGroup;    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cogFuncClass")
    @XmlElement
    @XmlInverseReference(mappedBy = "cogFuncClass")
    @XmlElementWrapper(name = "COGOrthologousGroups")
    private Set<COGOrthologousGroup> cogOrthologousGroup;

    public COGFuncClass() {
    }

    public COGFuncClass(Long wid) {
        this.wid = wid;
    }

    public COGFuncClass(Long wid, char letter, String name, long cOGFuncClassGroupWID) {
        this.wid = wid;
        this.letter = letter;
        this.name = name;
        this.cOGFuncClassGroupWID = cOGFuncClassGroupWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getcOGFuncClassGroupWID() {
        return cOGFuncClassGroupWID;
    }

    public void setcOGFuncClassGroupWID(long cOGFuncClassGroupWID) {
        this.cOGFuncClassGroupWID = cOGFuncClassGroupWID;
    }

    public COGFuncClassGroup getCogFuncClassGroup() {
        return cogFuncClassGroup;
    }

    public void setCogFuncClassGroup(COGFuncClassGroup cogFuncClassGroup) {
        this.cogFuncClassGroup = cogFuncClassGroup;
    }

    @XmlTransient
    public Set<COGOrthologousGroup> getCogOrthologousGroup() {
        return cogOrthologousGroup;
    }

    public void setCogOrthologousGroup(Set<COGOrthologousGroup> cogOrthologousGroup) {
        this.cogOrthologousGroup = cogOrthologousGroup;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 61 * hash + this.letter;
        hash = 61 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 61 * hash + (int) (this.cOGFuncClassGroupWID ^ (this.cOGFuncClassGroupWID >>> 32));
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
        final COGFuncClass other = (COGFuncClass) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.letter != other.letter) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return this.cOGFuncClassGroupWID == other.cOGFuncClassGroupWID;
    }

    @Override
    public String toString() {
        return "COGFuncClass{" + "wid=" + wid + ", letter=" + letter + ", name=" + name + '}';
    }

}
