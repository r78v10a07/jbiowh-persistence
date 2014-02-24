package org.jbiowhpersistence.datasets.protein.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 * This Class is the Protein Comment entity
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2012-10-03 22:11:05 +0200
 * (Wed, 03 Oct 2012) $ $LastChangedRevision: 270 $
 *
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
    @XmlElement
    @XmlInverseReference(mappedBy = "proteinComment")
    @XmlElementWrapper(name = "ProteinOtherLocations")
    private Set<ProteinOtherLocation> proteinOtherLocation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proteinComment")
    @XmlElement
    @XmlInverseReference(mappedBy = "proteinComment")
    @XmlElementWrapper(name = "ProteinCommentIsoforms")
    private Set<ProteinCommentIsoform> proteinCommentIsoform;
    @ElementCollection
    @CollectionTable(
            name = "ProteinCommentSubCellularLocation",
            joinColumns
            = @JoinColumn(name = "ProteinComment_WID"))
    @XmlElementWrapper(name = "ProteinCommentSubCellularLocations")
    private Collection<ProteinCommentSubCellularLocation> proteinCommentSubCellularLocation;
    @ElementCollection
    @CollectionTable(
            name = "ProteinCommentConflict",
            joinColumns
            = @JoinColumn(name = "ProteinComment_WID"))
    @XmlElementWrapper(name = "ProteinCommentConflicts")
    private Collection<ProteinCommentConflict> proteinCommentConflict;
    @ElementCollection
    @CollectionTable(
            name = "ProteinCommentLink",
            joinColumns
            = @JoinColumn(name = "ProteinComment_WID"))
    @XmlElementWrapper(name = "ProteinCommentLinks")
    private Collection<ProteinCommentLink> proteinCommentLink;
    @ElementCollection
    @CollectionTable(
            name = "ProteinCommentEvent",
            joinColumns
            = @JoinColumn(name = "ProteinComment_WID"))
    @XmlElementWrapper(name = "ProteinCommentEvents")
    private Collection<ProteinCommentEvent> proteinCommentEvent;
    @ElementCollection
    @CollectionTable(
            name = "ProteinCommentInteract",
            joinColumns
            = @JoinColumn(name = "ProteinComment_WID"))
    @XmlElementWrapper(name = "ProteinCommentInteracts")
    private Collection<ProteinCommentInteract> proteinCommentInteract;

    public ProteinComment() {
    }

    public ProteinComment(Long wid) {
        this.wid = wid;
    }

    public ProteinComment(Long wid, long proteinWID) {
        this.wid = wid;
        this.proteinWID = proteinWID;
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

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public Set<ProteinOtherLocation> getProteinOtherLocation() {
        return proteinOtherLocation;
    }

    public void setProteinOtherLocation(Set<ProteinOtherLocation> proteinOtherLocation) {
        this.proteinOtherLocation = proteinOtherLocation;
    }

    public Set<ProteinCommentIsoform> getProteinCommentIsoform() {
        return proteinCommentIsoform;
    }

    public void setProteinCommentIsoform(Set<ProteinCommentIsoform> proteinCommentIsoform) {
        this.proteinCommentIsoform = proteinCommentIsoform;
    }

    public Collection<ProteinCommentSubCellularLocation> getProteinCommentSubCellularLocation() {
        return proteinCommentSubCellularLocation;
    }

    public void setProteinCommentSubCellularLocation(Collection<ProteinCommentSubCellularLocation> proteinCommentSubCellularLocation) {
        this.proteinCommentSubCellularLocation = proteinCommentSubCellularLocation;
    }

    public Collection<ProteinCommentConflict> getProteinCommentConflict() {
        return proteinCommentConflict;
    }

    public void setProteinCommentConflict(Collection<ProteinCommentConflict> proteinCommentConflict) {
        this.proteinCommentConflict = proteinCommentConflict;
    }

    public Collection<ProteinCommentLink> getProteinCommentLink() {
        return proteinCommentLink;
    }

    public void setProteinCommentLink(Collection<ProteinCommentLink> proteinCommentLink) {
        this.proteinCommentLink = proteinCommentLink;
    }

    public Collection<ProteinCommentEvent> getProteinCommentEvent() {
        return proteinCommentEvent;
    }

    public void setProteinCommentEvent(Collection<ProteinCommentEvent> proteinCommentEvent) {
        this.proteinCommentEvent = proteinCommentEvent;
    }

    public Collection<ProteinCommentInteract> getProteinCommentInteract() {
        return proteinCommentInteract;
    }

    public void setProteinCommentInteract(Collection<ProteinCommentInteract> proteinCommentInteract) {
        this.proteinCommentInteract = proteinCommentInteract;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.wid != null ? this.wid.hashCode() : 0);
        hash = 89 * hash + (int) (this.proteinWID ^ (this.proteinWID >>> 32));
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.mass != null ? this.mass.hashCode() : 0);
        hash = 89 * hash + (this.error != null ? this.error.hashCode() : 0);
        hash = 89 * hash + (this.method != null ? this.method.hashCode() : 0);
        hash = 89 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 89 * hash + (this.locationType != null ? this.locationType.hashCode() : 0);
        hash = 89 * hash + (this.evidence != null ? this.evidence.hashCode() : 0);
        hash = 89 * hash + (this.text != null ? this.text.hashCode() : 0);
        hash = 89 * hash + (this.textEvidence != null ? this.textEvidence.hashCode() : 0);
        hash = 89 * hash + (this.textStatus != null ? this.textStatus.hashCode() : 0);
        hash = 89 * hash + (this.molecule != null ? this.molecule.hashCode() : 0);
        hash = 89 * hash + (this.organismsDiffer != null ? this.organismsDiffer.hashCode() : 0);
        hash = 89 * hash + (this.experiments != null ? this.experiments.hashCode() : 0);
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
        final ProteinComment other = (ProteinComment) obj;
        if (this.wid != other.wid && (this.wid == null || !this.wid.equals(other.wid))) {
            return false;
        }
        if (this.proteinWID != other.proteinWID) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.mass != other.mass && (this.mass == null || !this.mass.equals(other.mass))) {
            return false;
        }
        if ((this.error == null) ? (other.error != null) : !this.error.equals(other.error)) {
            return false;
        }
        if ((this.method == null) ? (other.method != null) : !this.method.equals(other.method)) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.locationType == null) ? (other.locationType != null) : !this.locationType.equals(other.locationType)) {
            return false;
        }
        if ((this.evidence == null) ? (other.evidence != null) : !this.evidence.equals(other.evidence)) {
            return false;
        }
        if ((this.text == null) ? (other.text != null) : !this.text.equals(other.text)) {
            return false;
        }
        if ((this.textEvidence == null) ? (other.textEvidence != null) : !this.textEvidence.equals(other.textEvidence)) {
            return false;
        }
        if ((this.textStatus == null) ? (other.textStatus != null) : !this.textStatus.equals(other.textStatus)) {
            return false;
        }
        if ((this.molecule == null) ? (other.molecule != null) : !this.molecule.equals(other.molecule)) {
            return false;
        }
        if ((this.organismsDiffer == null) ? (other.organismsDiffer != null) : !this.organismsDiffer.equals(other.organismsDiffer)) {
            return false;
        }
        if ((this.experiments == null) ? (other.experiments != null) : !this.experiments.equals(other.experiments)) {
            return false;
        }
        if (this.proteinOtherLocation != other.proteinOtherLocation && (this.proteinOtherLocation == null || !this.proteinOtherLocation.equals(other.proteinOtherLocation))) {
            return false;
        }
        if (this.proteinCommentIsoform != other.proteinCommentIsoform && (this.proteinCommentIsoform == null || !this.proteinCommentIsoform.equals(other.proteinCommentIsoform))) {
            return false;
        }
        if (this.proteinCommentSubCellularLocation != other.proteinCommentSubCellularLocation && (this.proteinCommentSubCellularLocation == null || !this.proteinCommentSubCellularLocation.equals(other.proteinCommentSubCellularLocation))) {
            return false;
        }
        if (this.proteinCommentConflict != other.proteinCommentConflict && (this.proteinCommentConflict == null || !this.proteinCommentConflict.equals(other.proteinCommentConflict))) {
            return false;
        }
        if (this.proteinCommentLink != other.proteinCommentLink && (this.proteinCommentLink == null || !this.proteinCommentLink.equals(other.proteinCommentLink))) {
            return false;
        }
        if (this.proteinCommentEvent != other.proteinCommentEvent && (this.proteinCommentEvent == null || !this.proteinCommentEvent.equals(other.proteinCommentEvent))) {
            return false;
        }
        return this.proteinCommentInteract == other.proteinCommentInteract || (this.proteinCommentInteract != null && this.proteinCommentInteract.equals(other.proteinCommentInteract));
    }

    @Override
    public String toString() {
        StringBuilder cData = new StringBuilder();

        if (getProteinOtherLocation() != null) {
            for (ProteinOtherLocation p : getProteinOtherLocation()) {
                cData.append("\t\t").append(p).append("\n");
            }
        }

        if (getProteinCommentSubCellularLocation() != null) {
            for (ProteinCommentSubCellularLocation p : getProteinCommentSubCellularLocation()) {
                cData.append("\t\t").append(p).append("\n");
            }
        }

        if (getProteinCommentConflict() != null) {
            for (ProteinCommentConflict p : getProteinCommentConflict()) {
                cData.append("\t\t").append(p).append("\n");
            }
        }

        if (getProteinCommentLink() != null) {
            for (ProteinCommentLink p : getProteinCommentLink()) {
                cData.append("\t\t").append(p).append("\n");
            }
        }

        if (getProteinCommentEvent() != null) {
            for (ProteinCommentEvent p : getProteinCommentEvent()) {
                cData.append("\t\t").append(p).append("\n");
            }
        }

        if (getProteinCommentIsoform() != null) {
            for (ProteinCommentIsoform p : getProteinCommentIsoform()) {
                cData.append("\t\t").append(p).append("\n");
            }
        }

        if (getProteinCommentInteract() != null) {
            for (ProteinCommentInteract p : getProteinCommentInteract()) {
                cData.append("\t\t").append(p).append("\n");
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
