package org.jbiowhpersistence.datasets.ppi.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the MIFEntrySetEntry entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-08-31 15:46:36 +0200
 * (Fri, 31 Aug 2012) $ $LastChangedRevision: 322 $
 *
 * @since Aug 18, 2011
 */
@Entity
@Table(name = "MIFEntrySetEntry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MIFEntrySetEntry.findAll", query = "SELECT m FROM MIFEntrySetEntry m"),
    @NamedQuery(name = "MIFEntrySetEntry.findByWid", query = "SELECT m FROM MIFEntrySetEntry m WHERE m.wid = :wid"),
    @NamedQuery(name = "MIFEntrySetEntry.findByMIFEntrySetWID", query = "SELECT m FROM MIFEntrySetEntry m WHERE m.mIFEntrySetWID = :mIFEntrySetWID")})
public class MIFEntrySetEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "MIFEntrySet_WID")
    private long mIFEntrySetWID;
    // Internal Protein relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MIFEntrySet_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private MIFEntrySet mIFEntrySet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mIFEntrySetEntry")
    private Set<MIFEntrySource> mifEntrySource;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntrySetEntry")
    private Set<MIFEntryExperiment> mifEntryExperiment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mifEntrySetEntry")
    private Set<MIFEntryInteractor> mifEntryInteractor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mIFEntrySetEntry")
    private Set<MIFEntryInteraction> mifEntryInteraction;

    public MIFEntrySetEntry() {
    }

    public MIFEntrySetEntry(Long wid) {
        this.wid = wid;
    }

    public MIFEntrySetEntry(Long wid, long mIFEntrySetWID) {
        this.wid = wid;
        this.mIFEntrySetWID = mIFEntrySetWID;
    }

    public Set<MIFEntryExperiment> getMifEntryExperiment() {
        return mifEntryExperiment;
    }

    public void setMifEntryExperiment(Set<MIFEntryExperiment> mifEntryExperiment) {
        this.mifEntryExperiment = mifEntryExperiment;
    }

    public Set<MIFEntryInteraction> getMifEntryInteraction() {
        return mifEntryInteraction;
    }

    public void setMifEntryInteraction(Set<MIFEntryInteraction> mifEntryInteraction) {
        this.mifEntryInteraction = mifEntryInteraction;
    }

    public Set<MIFEntryInteractor> getMifEntryInteractor() {
        return mifEntryInteractor;
    }

    public void setMifEntryInteractor(Set<MIFEntryInteractor> mifEntryInteractor) {
        this.mifEntryInteractor = mifEntryInteractor;
    }

    public Set<MIFEntrySource> getMifEntrySource() {
        return mifEntrySource;
    }

    public void setMifEntrySource(Set<MIFEntrySource> mifEntrySource) {
        this.mifEntrySource = mifEntrySource;
    }

    public MIFEntrySet getmIFEntrySet() {
        return mIFEntrySet;
    }

    public void setmIFEntrySet(MIFEntrySet mIFEntrySet) {
        this.mIFEntrySet = mIFEntrySet;
    }

    public long getmIFEntrySetWID() {
        return mIFEntrySetWID;
    }

    public void setmIFEntrySetWID(long mIFEntrySetWID) {
        this.mIFEntrySetWID = mIFEntrySetWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
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
        final MIFEntrySetEntry other = (MIFEntrySetEntry) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.mIFEntrySetWID != other.mIFEntrySetWID) {
            return false;
        }
        if (!Objects.equals(this.mIFEntrySet, other.mIFEntrySet)) {
            return false;
        }
        if (!Objects.equals(this.mifEntrySource, other.mifEntrySource)) {
            return false;
        }
        if (!Objects.equals(this.mifEntryExperiment, other.mifEntryExperiment)) {
            return false;
        }
        if (!Objects.equals(this.mifEntryInteractor, other.mifEntryInteractor)) {
            return false;
        }
        if (!Objects.equals(this.mifEntryInteraction, other.mifEntryInteraction)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Iterator it;
        StringBuilder pData = new StringBuilder();

        if (getMifEntrySource() != null) {
            it = getMifEntrySource().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }
        if (getMifEntryExperiment() != null) {
            it = getMifEntryExperiment().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }
        if (getMifEntryInteraction() != null) {
            it = getMifEntryInteraction().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }
        if (getMifEntryInteractor() != null) {
            it = getMifEntryInteractor().iterator();
            while (it.hasNext()) {
                pData.append("\t").append(it.next()).append("\n");
            }
        }
        return "MIFEntrySetEntry[ wid=" + wid + " ]\n" + pData;
    }
}
