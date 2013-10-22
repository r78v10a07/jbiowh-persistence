package org.jbiowhpersistence.datasets.pathway.kegg.entities.gene;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Class is THE KEGG Gene DrugTarget entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since May 14, 2012
 */
@Entity
@Table(name = "KEGGGeneDrugTarget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGGeneDrugTarget.findAll", query = "SELECT k FROM KEGGGeneDrugTarget k"),
    @NamedQuery(name = "KEGGGeneDrugTarget.findByKEGGGeneWID", query = "SELECT k FROM KEGGGeneDrugTarget k WHERE k.kEGGGeneDrugTargetPK.kEGGGeneWID = :kEGGGeneWID"),
    @NamedQuery(name = "KEGGGeneDrugTarget.findByName", query = "SELECT k FROM KEGGGeneDrugTarget k WHERE k.kEGGGeneDrugTargetPK.name = :name")})
public class KEGGGeneDrugTarget implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KEGGGeneDrugTargetPK kEGGGeneDrugTargetPK;
    @Lob
    @Column(name = "Entries")
    private String entries;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGGene_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGGene kEGGGene;

    public KEGGGeneDrugTarget() {
    }

    public KEGGGeneDrugTarget(KEGGGeneDrugTargetPK kEGGGeneDrugTargetPK) {
        this.kEGGGeneDrugTargetPK = kEGGGeneDrugTargetPK;
    }

    public KEGGGeneDrugTarget(long kEGGGeneWID, String name) {
        this.kEGGGeneDrugTargetPK = new KEGGGeneDrugTargetPK(kEGGGeneWID, name);
    }

    public KEGGGene getkEGGGene() {
        return kEGGGene;
    }

    public void setkEGGGene(KEGGGene kEGGGene) {
        this.kEGGGene = kEGGGene;
    }

    public KEGGGeneDrugTargetPK getKEGGGeneDrugTargetPK() {
        return kEGGGeneDrugTargetPK;
    }

    public void setKEGGGeneDrugTargetPK(KEGGGeneDrugTargetPK kEGGGeneDrugTargetPK) {
        this.kEGGGeneDrugTargetPK = kEGGGeneDrugTargetPK;
    }

    public String getEntries() {
        return entries;
    }

    public void setEntries(String entries) {
        this.entries = entries;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kEGGGeneDrugTargetPK != null ? kEGGGeneDrugTargetPK.hashCode() : 0);
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
        final KEGGGeneDrugTarget other = (KEGGGeneDrugTarget) obj;
        if (!Objects.equals(this.kEGGGeneDrugTargetPK, other.kEGGGeneDrugTargetPK)) {
            return false;
        }
        if (!Objects.equals(this.entries, other.entries)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGGeneDrugTarget{" + "kEGGGeneDrugTargetPK=" + kEGGGeneDrugTargetPK + ", entries=" + entries + '}';
    }
}
