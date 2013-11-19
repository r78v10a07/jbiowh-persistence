package org.jbiowhpersistence.datasets.dataset.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the DataSet entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-11-08 14:37:19 +0100 (Thu, 08 Nov 2012) $
 * $LastChangedRevision: 322 $
 * @since Jun 15, 2011
 */
@Entity
@Table(name = "DataSet")
@NamedQueries({
    @NamedQuery(name = "DataSet.findAll", query = "SELECT d FROM DataSet d"),
    @NamedQuery(name = "DataSet.findByWid", query = "SELECT d FROM DataSet d WHERE d.wid = :wid"),
    @NamedQuery(name = "DataSet.findByName", query = "SELECT d FROM DataSet d WHERE UPPER(d.name) like UPPER(:name)"),
    @NamedQuery(name = "DataSet.findByNoName", query = "SELECT d FROM DataSet d WHERE UPPER(d.name) not like UPPER(:name)"),
    @NamedQuery(name = "DataSet.findByVersion", query = "SELECT d FROM DataSet d WHERE d.version = :version"),
    @NamedQuery(name = "DataSet.findByReleaseDate", query = "SELECT d FROM DataSet d WHERE d.releaseDate = :releaseDate"),
    @NamedQuery(name = "DataSet.findByLoadDate", query = "SELECT d FROM DataSet d WHERE d.loadDate = :loadDate"),
    @NamedQuery(name = "DataSet.findByChangeDate", query = "SELECT d FROM DataSet d WHERE d.changeDate = :changeDate"),
    @NamedQuery(name = "DataSet.findByHomeURL", query = "SELECT d FROM DataSet d WHERE d.homeURL = :homeURL"),
    @NamedQuery(name = "DataSet.findByLoadedBy", query = "SELECT d FROM DataSet d WHERE d.loadedBy = :loadedBy"),
    @NamedQuery(name = "DataSet.findByApplication", query = "SELECT d FROM DataSet d WHERE d.application = :application"),
    @NamedQuery(name = "DataSet.findByApplicationVersion", query = "SELECT d FROM DataSet d WHERE d.applicationVersion = :applicationVersion"),
    @NamedQuery(name = "DataSet.findByStatus", query = "SELECT d FROM DataSet d WHERE UPPER(d.status) like UPPER(:status)"),
    @NamedQuery(name = "DataSet.findByNoStatus", query = "SELECT d FROM DataSet d WHERE UPPER(d.status) not like UPPER(:status)"),
    @NamedQuery(name = "DataSet.findMaxWID", query = "SELECT MAX(d.wid) FROM DataSet d")})
@XmlRootElement
public class DataSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")        
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Version")
    private String version;
    @Column(name = "ReleaseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;
    @Basic(optional = false)
    @Column(name = "LoadDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loadDate;
    @Column(name = "ChangeDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeDate;
    @Column(name = "HomeURL")
    private String homeURL;
    @Column(name = "LoadedBy")
    private String loadedBy;
    @Column(name = "Application")
    private String application;
    @Column(name = "ApplicationVersion")
    private String applicationVersion;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;

    /**
     * Creates a dataSet entity
     */
    public DataSet() {
    }

    /**
     * Creates a dataSet entity with an id
     *
     * @param wid the DataSet's entity Id
     */
    public DataSet(Long wid) {
        this.wid = wid;
    }

    /**
     * Creates a dataSet entity with the parameters
     *
     * @param wid the DataSet's entity Id
     * @param name the DataSet's entity name
     * @param loadDate the DataSet's entity Id load date
     * @param status the DataSet's entity status
     */
    public DataSet(Long wid, String name, Date loadDate, String status) {
        this.wid = wid;
        this.name = name;
        this.loadDate = loadDate;
        this.status = status;
    }

    /**
     * Get the DataSet's Id
     *
     * @return the DataSet's Id
     */
    public Long getWid() {
        return wid;
    }

    /**
     * Set the DataSet's Id
     *
     * @param wid the DataSet's Id
     */
    public void setWid(Long wid) {
        this.wid = wid;
    }

    /**
     * Get the DataSet's name
     *
     * @return the DataSet's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the DataSet's name
     *
     * @param name the DataSet's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the the DataSet's version
     *
     * @return the DataSet's version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Set the DataSet's version
     *
     * @param version the DataSet's version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Get the DataSet's release date
     *
     * @return the DataSet's release date
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Set the DataSet's release date
     *
     * @param releaseDate the DataSet's release date
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Get the DataSet's load date
     *
     * @return the DataSet's load date
     */
    public Date getLoadDate() {
        return loadDate;
    }

    /**
     * Set the DataSet's load date
     *
     * @param loadDate the DataSet's load date
     */
    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    /**
     * Get the DataSet's change date
     *
     * @return the DataSet's change date
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * Set the DataSet's change date
     *
     * @param changeDate the DataSet's change date
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * Get the the DataSet's Home URL
     *
     * @return the DataSet's Home URL
     */
    public String getHomeURL() {
        return homeURL;
    }

    /**
     * Set the DataSet's Home URL
     *
     * @param homeURL the DataSet's Home URL
     */
    public void setHomeURL(String homeURL) {
        this.homeURL = homeURL;
    }

    /**
     * Get the DataSet's load user
     *
     * @return the DataSet's load user
     */
    public String getLoadedBy() {
        return loadedBy;
    }

    /**
     * Set the DataSet's load user
     *
     * @param loadedBy the DataSet's load user
     */
    public void setLoadedBy(String loadedBy) {
        this.loadedBy = loadedBy;
    }

    /**
     * Get the the DataSet's application name
     *
     * @return the DataSet's application name
     */
    public String getApplication() {
        return application;
    }

    /**
     * Set the DataSet's application name
     *
     * @param application the DataSet's application name
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * Get the DataSet's application version
     *
     * @return the DataSet's application version
     */
    public String getApplicationVersion() {
        return applicationVersion;
    }

    /**
     * Set the DataSet's application version
     *
     * @param applicationVersion the DataSet's application version
     */
    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    /**
     * Get the DataSet's status
     *
     * @return the DataSet's status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the DataSet's status
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DataSet)) {
            return false;
        }
        DataSet other = (DataSet) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataSet{" + "wid=" + wid + ", name=" + name + ", version=" + version + ", releaseDate=" + releaseDate + ", loadDate=" + loadDate + ", changeDate=" + changeDate + ", homeURL=" + homeURL + ", loadedBy=" + loadedBy + ", application=" + application + ", applicationVersion=" + applicationVersion + ", status=" + status + '}';
    }
}
