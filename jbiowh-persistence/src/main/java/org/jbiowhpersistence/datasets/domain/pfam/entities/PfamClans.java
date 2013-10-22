package org.jbiowhpersistence.datasets.domain.pfam.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jbiowhpersistence.datasets.domain.pfam.PFamTables;

/**
 * This class is PfamClans entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-12-27 15:38:46 +0100 (Thu, 27 Dec 2012) $
 * $LastChangedRevision: 377 $
 * @since Nov 16, 2012
 */
@Entity
@Table(name = "PfamClans")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PfamClans.findAll", query = "SELECT p FROM PfamClans p"),
    @NamedQuery(name = "PfamClans.findByWid", query = "SELECT p FROM PfamClans p WHERE p.wid = :wid"),
    @NamedQuery(name = "PfamClans.findByClanAcc", query = "SELECT p FROM PfamClans p WHERE p.clanAcc = :clanAcc"),
    @NamedQuery(name = "PfamClans.findByClanId", query = "SELECT p FROM PfamClans p WHERE p.clanId = :clanId"),
    @NamedQuery(name = "PfamClans.findByPreviousId", query = "SELECT p FROM PfamClans p WHERE p.previousId = :previousId"),
    @NamedQuery(name = "PfamClans.findByClanDescription", query = "SELECT p FROM PfamClans p WHERE p.clanDescription = :clanDescription"),
    @NamedQuery(name = "PfamClans.findByClanAuthor", query = "SELECT p FROM PfamClans p WHERE p.clanAuthor = :clanAuthor"),
    @NamedQuery(name = "PfamClans.findByDepositedBy", query = "SELECT p FROM PfamClans p WHERE p.depositedBy = :depositedBy"),
    @NamedQuery(name = "PfamClans.findByUpdated", query = "SELECT p FROM PfamClans p WHERE p.updated = :updated"),
    @NamedQuery(name = "PfamClans.findByCreated", query = "SELECT p FROM PfamClans p WHERE p.created = :created"),
    @NamedQuery(name = "PfamClans.findByVersion", query = "SELECT p FROM PfamClans p WHERE p.version = :version"),
    @NamedQuery(name = "PfamClans.findByNumberStructures", query = "SELECT p FROM PfamClans p WHERE p.numberStructures = :numberStructures"),
    @NamedQuery(name = "PfamClans.findByNumberArchs", query = "SELECT p FROM PfamClans p WHERE p.numberArchs = :numberArchs"),
    @NamedQuery(name = "PfamClans.findByNumberSpecies", query = "SELECT p FROM PfamClans p WHERE p.numberSpecies = :numberSpecies"),
    @NamedQuery(name = "PfamClans.findByNumberSequences", query = "SELECT p FROM PfamClans p WHERE p.numberSequences = :numberSequences"),
    @NamedQuery(name = "PfamClans.findByCompeted", query = "SELECT p FROM PfamClans p WHERE p.competed = :competed")})
public class PfamClans implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "clan_acc")
    private String clanAcc;
    @Basic(optional = false)
    @Column(name = "clan_id")
    private String clanId;
    @Column(name = "previous_id")
    private String previousId;
    @Column(name = "clan_description")
    private String clanDescription;
    @Column(name = "clan_author")
    private String clanAuthor;
    @Basic(optional = false)
    @Column(name = "deposited_by")
    private String depositedBy;
    @Lob
    @Column(name = "clan_comment")
    private String clanComment;
    @Basic(optional = false)
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "version")
    private Short version;
    @Column(name = "number_structures")
    private Integer numberStructures;
    @Column(name = "number_archs")
    private Integer numberArchs;
    @Column(name = "number_species")
    private Integer numberSpecies;
    @Column(name = "number_sequences")
    private Integer numberSequences;
    @Column(name = "competed")
    private Boolean competed;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = PFamTables.PFAMCLANS_HAS_PFAMA,
    joinColumns =
    @JoinColumn(name = "PfamClans_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "PfamA_WID", referencedColumnName = "WID"))
    private Set<PfamAbioWH> pfamA;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = PFamTables.PFAMCLANS_HAS_PFAMARCHITECTURE,
    joinColumns =
    @JoinColumn(name = "PfamClans_WID", referencedColumnName = "WID"),
    inverseJoinColumns =
    @JoinColumn(name = "PfamArchitecture_WID", referencedColumnName = "WID"))
    private Set<PfamArchitecture> pfamArchitectures;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamClans")
    private Set<PfamClanDatabaseLinks> pfamClanDatabaseLinkses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pfamClans")
    @MapKey(name = "pfamClanshasPfamLiteratureReferencesPK")
    private Map<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences> pfamClanshasPfamLiteratureReferences;

    public PfamClans() {
    }

    public PfamClans(Long wid) {
        this.wid = wid;
    }

    public PfamClans(Long wid, String clanAcc, String clanId, String depositedBy, Date updated) {
        this.wid = wid;
        this.clanAcc = clanAcc;
        this.clanId = clanId;
        this.depositedBy = depositedBy;
        this.updated = updated;
    }

    public void setRelationsToNull() {
        setPfamA(null);
        setPfamArchitectures(null);
        setPfamClanshasPfamLiteratureReferences(null);
    }

    public Map<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences> getPfamClanshasPfamLiteratureReferences() {
        return pfamClanshasPfamLiteratureReferences;
    }

    public void setPfamClanshasPfamLiteratureReferences(Map<PfamClanshasPfamLiteratureReferencesPK, PfamClanshasPfamLiteratureReferences> pfamClanshasPfamLiteratureReferences) {
        this.pfamClanshasPfamLiteratureReferences = pfamClanshasPfamLiteratureReferences;
    }

    public Set<PfamClanDatabaseLinks> getPfamClanDatabaseLinkses() {
        return pfamClanDatabaseLinkses;
    }

    public void setPfamClanDatabaseLinkses(Set<PfamClanDatabaseLinks> pfamClanDatabaseLinkses) {
        this.pfamClanDatabaseLinkses = pfamClanDatabaseLinkses;
    }

    public Set<PfamAbioWH> getPfamA() {
        return pfamA;
    }

    public void setPfamA(Set<PfamAbioWH> pfamA) {
        this.pfamA = pfamA;
    }

    public Set<PfamArchitecture> getPfamArchitectures() {
        return pfamArchitectures;
    }

    public void setPfamArchitectures(Set<PfamArchitecture> pfamArchitectures) {
        this.pfamArchitectures = pfamArchitectures;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getClanAcc() {
        return clanAcc;
    }

    public void setClanAcc(String clanAcc) {
        this.clanAcc = clanAcc;
    }

    public String getClanId() {
        return clanId;
    }

    public void setClanId(String clanId) {
        this.clanId = clanId;
    }

    public String getPreviousId() {
        return previousId;
    }

    public void setPreviousId(String previousId) {
        this.previousId = previousId;
    }

    public String getClanDescription() {
        return clanDescription;
    }

    public void setClanDescription(String clanDescription) {
        this.clanDescription = clanDescription;
    }

    public String getClanAuthor() {
        return clanAuthor;
    }

    public void setClanAuthor(String clanAuthor) {
        this.clanAuthor = clanAuthor;
    }

    public String getDepositedBy() {
        return depositedBy;
    }

    public void setDepositedBy(String depositedBy) {
        this.depositedBy = depositedBy;
    }

    public String getClanComment() {
        return clanComment;
    }

    public void setClanComment(String clanComment) {
        this.clanComment = clanComment;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public Integer getNumberStructures() {
        return numberStructures;
    }

    public void setNumberStructures(Integer numberStructures) {
        this.numberStructures = numberStructures;
    }

    public Integer getNumberArchs() {
        return numberArchs;
    }

    public void setNumberArchs(Integer numberArchs) {
        this.numberArchs = numberArchs;
    }

    public Integer getNumberSpecies() {
        return numberSpecies;
    }

    public void setNumberSpecies(Integer numberSpecies) {
        this.numberSpecies = numberSpecies;
    }

    public Integer getNumberSequences() {
        return numberSequences;
    }

    public void setNumberSequences(Integer numberSequences) {
        this.numberSequences = numberSequences;
    }

    public Boolean getCompeted() {
        return competed;
    }

    public void setCompeted(Boolean competed) {
        this.competed = competed;
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
        final PfamClans other = (PfamClans) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (!Objects.equals(this.clanAcc, other.clanAcc)) {
            return false;
        }
        if (!Objects.equals(this.clanId, other.clanId)) {
            return false;
        }
        if (!Objects.equals(this.previousId, other.previousId)) {
            return false;
        }
        if (!Objects.equals(this.clanDescription, other.clanDescription)) {
            return false;
        }
        if (!Objects.equals(this.clanAuthor, other.clanAuthor)) {
            return false;
        }
        if (!Objects.equals(this.depositedBy, other.depositedBy)) {
            return false;
        }
        if (!Objects.equals(this.clanComment, other.clanComment)) {
            return false;
        }
        if (!Objects.equals(this.updated, other.updated)) {
            return false;
        }
        if (!Objects.equals(this.created, other.created)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.numberStructures, other.numberStructures)) {
            return false;
        }
        if (!Objects.equals(this.numberArchs, other.numberArchs)) {
            return false;
        }
        if (!Objects.equals(this.numberSpecies, other.numberSpecies)) {
            return false;
        }
        if (!Objects.equals(this.numberSequences, other.numberSequences)) {
            return false;
        }
        if (!Objects.equals(this.competed, other.competed)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PfamClans{" + "wid=" + wid + ", clanAcc=" + clanAcc + ", clanId=" + clanId + ", previousId=" + previousId + ", clanDescription=" + clanDescription + ", clanAuthor=" + clanAuthor + ", depositedBy=" + depositedBy + ", clanComment=" + clanComment + ", updated=" + updated + ", created=" + created + ", version=" + version + ", numberStructures=" + numberStructures + ", numberArchs=" + numberArchs + ", numberSpecies=" + numberSpecies + ", numberSequences=" + numberSequences + ", competed=" + competed + '}';
    }
}
