package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is the Protein Comment entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Aug 11, 2011
 */
@Entity
@Table(name = "ProteinComment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteinComment.findAll", query = "SELECT p FROM ProteinComment p"),
    @NamedQuery(name = "ProteinComment.findByWid", query = "SELECT p FROM ProteinComment p WHERE p.wid = :wid"),
    @NamedQuery(name = "ProteinComment.findByProteinWID", query = "SELECT p FROM ProteinComment p WHERE p.proteinWID = :proteinWID"),
    @NamedQuery(name = "ProteinComment.findByName", query = "SELECT p FROM ProteinComment p WHERE p.name = :name"),
    @NamedQuery(name = "ProteinComment.findByMass", query = "SELECT p FROM ProteinComment p WHERE p.mass = :mass"),
    @NamedQuery(name = "ProteinComment.findByError", query = "SELECT p FROM ProteinComment p WHERE p.error = :error"),
    @NamedQuery(name = "ProteinComment.findByMethod", query = "SELECT p FROM ProteinComment p WHERE p.method = :method"),
    @NamedQuery(name = "ProteinComment.findByType", query = "SELECT p FROM ProteinComment p WHERE p.type = :type"),
    @NamedQuery(name = "ProteinComment.findByLocationType", query = "SELECT p FROM ProteinComment p WHERE p.locationType = :locationType"),
    @NamedQuery(name = "ProteinComment.findByEvidence", query = "SELECT p FROM ProteinComment p WHERE p.evidence = :evidence"),
    @NamedQuery(name = "ProteinComment.findByTextEvidence", query = "SELECT p FROM ProteinComment p WHERE p.textEvidence = :textEvidence"),
    @NamedQuery(name = "ProteinComment.findByTextStatus", query = "SELECT p FROM ProteinComment p WHERE p.textStatus = :textStatus"),
    @NamedQuery(name = "ProteinComment.findByMolecule", query = "SELECT p FROM ProteinComment p WHERE p.molecule = :molecule"),
    @NamedQuery(name = "ProteinComment.findByOrganismsDiffer", query = "SELECT p FROM ProteinComment p WHERE p.organismsDiffer = :organismsDiffer"),
    @NamedQuery(name = "ProteinComment.findByExperiments", query = "SELECT p FROM ProteinComment p WHERE p.experiments = :experiments")})
public class ProteinComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "Protein_WID")
    private long proteinWID;
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Mass")
    private Float mass;
    @Column(name = "Error")
    private String error;
    @Column(name = "Method")
    private String method;
    @Column(name = "Type")
    private String type;
    @Column(name = "LocationType")
    private String locationType;
    @Column(name = "Evidence")
    private String evidence;
    @Lob
    @Column(name = "Text")
    private String text;
    @Column(name = "TextEvidence")
    private String textEvidence;
    @Column(name = "TextStatus")
    private String textStatus;
    @Column(name = "Molecule")
    private String molecule;
    @Column(name = "OrganismsDiffer")
    private String organismsDiffer;
    @Column(name = "Experiments")
    private String experiments;
    // Internla relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Protein_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private Protein protein;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinComment")
    private Set<ProteinOtherLocation> proteinOtherLocation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinComment")
    private Set<ProteinCommentSubCellularLocation> proteinCommentSubCellularLocation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinComment")
    @MapKey(name = "proteinCommentConflictPK")
    private Map<ProteinCommentConflictPK, ProteinCommentConflict> proteinCommentConflict;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinComment")
    @MapKey(name = "proteinCommentLinkPK")
    private Map<ProteinCommentLinkPK, ProteinCommentLink> proteinCommentLink;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinComment")
    @MapKey(name = "proteinCommentEventPK")
    private Map<ProteinCommentEventPK, ProteinCommentEvent> proteinCommentEvent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinComment")
    private Set<ProteinCommentIsoform> proteinCommentIsoform;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinComment")
    private Set<ProteinCommentInteract> proteinCommentInteract;

    public ProteinComment() {
    }

    public ProteinComment(Long wid) {
        this.wid = wid;
    }

    public ProteinComment(Long wid, long proteinWID) {
        this.wid = wid;
        this.proteinWID = proteinWID;
    }

    public Set<ProteinCommentInteract> getProteinCommentInteract() {
        return proteinCommentInteract;
    }

    public void setProteinCommentInteract(Set<ProteinCommentInteract> proteinCommentInteract) {
        this.proteinCommentInteract = proteinCommentInteract;
    }

    public Set<ProteinCommentIsoform> getProteinCommentIsoform() {
        return proteinCommentIsoform;
    }

    public void setProteinCommentIsoform(Set<ProteinCommentIsoform> proteinCommentIsoform) {
        this.proteinCommentIsoform = proteinCommentIsoform;
    }

    public Map<ProteinCommentEventPK, ProteinCommentEvent> getProteinCommentEvent() {
        return proteinCommentEvent;
    }

    public void setProteinCommentEvent(Map<ProteinCommentEventPK, ProteinCommentEvent> proteinCommentEvent) {
        this.proteinCommentEvent = proteinCommentEvent;
    }

    public Map<ProteinCommentLinkPK, ProteinCommentLink> getProteinCommentLink() {
        return proteinCommentLink;
    }

    public void setProteinCommentLink(Map<ProteinCommentLinkPK, ProteinCommentLink> proteinCommentLink) {
        this.proteinCommentLink = proteinCommentLink;
    }

    public Map<ProteinCommentConflictPK, ProteinCommentConflict> getProteinCommentConflict() {
        return proteinCommentConflict;
    }

    public void setProteinCommentConflict(Map<ProteinCommentConflictPK, ProteinCommentConflict> proteinCommentConflict) {
        this.proteinCommentConflict = proteinCommentConflict;
    }

    public Set<ProteinCommentSubCellularLocation> getProteinCommentSubCellularLocation() {
        return proteinCommentSubCellularLocation;
    }

    public void setProteinCommentSubCellularLocation(Set<ProteinCommentSubCellularLocation> proteinCommentSubCellularLocation) {
        this.proteinCommentSubCellularLocation = proteinCommentSubCellularLocation;
    }

    public Set<ProteinOtherLocation> getProteinOtherLocation() {
        return proteinOtherLocation;
    }

    public void setProteinOtherLocation(Set<ProteinOtherLocation> proteinOtherLocation) {
        this.proteinOtherLocation = proteinOtherLocation;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMass() {
        return mass;
    }

    public void setMass(Float mass) {
        this.mass = mass;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextEvidence() {
        return textEvidence;
    }

    public void setTextEvidence(String textEvidence) {
        this.textEvidence = textEvidence;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }

    public String getMolecule() {
        return molecule;
    }

    public void setMolecule(String molecule) {
        this.molecule = molecule;
    }

    public String getOrganismsDiffer() {
        return organismsDiffer;
    }

    public void setOrganismsDiffer(String organismsDiffer) {
        this.organismsDiffer = organismsDiffer;
    }

    public String getExperiments() {
        return experiments;
    }

    public void setExperiments(String experiments) {
        this.experiments = experiments;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProteinComment other = (ProteinComment) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.mass, other.mass)) {
            return false;
        }
        if (!Objects.equals(this.error, other.error)) {
            return false;
        }
        if (!Objects.equals(this.method, other.method)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.locationType, other.locationType)) {
            return false;
        }
        if (!Objects.equals(this.evidence, other.evidence)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.textEvidence, other.textEvidence)) {
            return false;
        }
        if (!Objects.equals(this.textStatus, other.textStatus)) {
            return false;
        }
        if (!Objects.equals(this.molecule, other.molecule)) {
            return false;
        }
        if (!Objects.equals(this.organismsDiffer, other.organismsDiffer)) {
            return false;
        }
        if (!Objects.equals(this.experiments, other.experiments)) {
            return false;
        }
        if (!Objects.equals(this.proteinOtherLocation, other.proteinOtherLocation)) {
            return false;
        }
        if (!Objects.equals(this.proteinCommentSubCellularLocation, other.proteinCommentSubCellularLocation)) {
            return false;
        }
        if (!Objects.equals(this.proteinCommentConflict, other.proteinCommentConflict)) {
            return false;
        }
        if (!Objects.equals(this.proteinCommentLink, other.proteinCommentLink)) {
            return false;
        }
        if (!Objects.equals(this.proteinCommentEvent, other.proteinCommentEvent)) {
            return false;
        }
        if (!Objects.equals(this.proteinCommentIsoform, other.proteinCommentIsoform)) {
            return false;
        }
        if (!Objects.equals(this.proteinCommentInteract, other.proteinCommentInteract)) {
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
        Iterator it;
        StringBuilder cData = new StringBuilder();

        if (getProteinOtherLocation() != null) {
            it = getProteinOtherLocation().iterator();
            while (it.hasNext()) {
                cData.append("\t\t").append(it.next()).append("\n");
            }
        }

        if (getProteinCommentSubCellularLocation() != null) {
            it = getProteinCommentSubCellularLocation().iterator();
            while (it.hasNext()) {
                cData.append("\t\t").append(it.next()).append("\n");
            }
        }

        if (getProteinCommentConflict() != null) {
            if (!getProteinCommentConflict().isEmpty()) {
                it = getProteinCommentConflict().values().iterator();
                while (it.hasNext()) {
                    cData.append("\t\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinCommentLink() != null) {
            if (!getProteinCommentLink().isEmpty()) {
                it = getProteinCommentLink().values().iterator();
                while (it.hasNext()) {
                    cData.append("\t\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinCommentEvent() != null) {
            if (!getProteinCommentEvent().isEmpty()) {
                it = getProteinCommentEvent().values().iterator();
                while (it.hasNext()) {
                    cData.append("\t\t").append(it.next()).append("\n");
                }
            }
        }

        if (getProteinCommentIsoform() != null) {
            it = getProteinCommentIsoform().iterator();
            while (it.hasNext()) {
                cData.append("\t\t").append(it.next()).append("\n");
            }
        }

        if (getProteinCommentInteract() != null) {
            it = getProteinCommentInteract().iterator();
            while (it.hasNext()) {
                cData.append("\t\t").append(it.next()).append("\n");
            }
        }

        return "ProteinWIDComment{"
                + " wid=" + wid
                + " proteinWID=" + proteinWID
                + " name=" + name
                + " mass=" + mass
                + " error=" + error
                + " method=" + method
                + " type=" + type
                + " locationType=" + locationType
                + " evidence=" + evidence
                + " text=" + text
                + " textEvidence=" + textEvidence
                + " textStatus=" + textStatus
                + " molecule=" + molecule
                + " organismsDiffer=" + organismsDiffer
                + " experiments=" + experiments
                + "}\n"
                + cData;
    }
}
