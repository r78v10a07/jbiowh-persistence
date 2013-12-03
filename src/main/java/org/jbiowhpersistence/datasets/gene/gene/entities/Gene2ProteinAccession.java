package org.jbiowhpersistence.datasets.gene.gene.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the Gene2ProteinAccession entity
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Nov 14, 2013
 */
@Embeddable
@Table(name = "Gene2ProteinAccession")
@XmlRootElement
public class Gene2ProteinAccession implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProteinGi")
    private Long proteinGi;
    @Basic(optional = false)
    @Column(name = "ProteinAccession")
    private String proteinAccession;
    @Basic(optional = false)
    @Column(name = "ProteinAccessionVersion")
    private int proteinAccessionVersion;

    public Gene2ProteinAccession() {
    }

    public Gene2ProteinAccession(Long proteinGi) {
        this.proteinGi = proteinGi;
    }

    public Gene2ProteinAccession(Long proteinGi, String proteinAccession, int proteinAccessionVersion) {
        this.proteinGi = proteinGi;
        this.proteinAccession = proteinAccession;
        this.proteinAccessionVersion = proteinAccessionVersion;
    }

    public Long getProteinGi() {
        return proteinGi;
    }

    public void setProteinGi(Long proteinGi) {
        this.proteinGi = proteinGi;
    }

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public int getProteinAccessionVersion() {
        return proteinAccessionVersion;
    }

    public void setProteinAccessionVersion(int proteinAccessionVersion) {
        this.proteinAccessionVersion = proteinAccessionVersion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.proteinGi != null ? this.proteinGi.hashCode() : 0);
        hash = 61 * hash + (this.proteinAccession != null ? this.proteinAccession.hashCode() : 0);
        hash = 61 * hash + this.proteinAccessionVersion;
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
        final Gene2ProteinAccession other = (Gene2ProteinAccession) obj;
        if (this.proteinGi != other.proteinGi && (this.proteinGi == null || !this.proteinGi.equals(other.proteinGi))) {
            return false;
        }
        if ((this.proteinAccession == null) ? (other.proteinAccession != null) : !this.proteinAccession.equals(other.proteinAccession)) {
            return false;
        }
        return this.proteinAccessionVersion == other.proteinAccessionVersion;
    }

    @Override
    public String toString() {
        return "Gene2ProteinAccession{" + "proteinGi=" + proteinGi + ", proteinAccession=" + proteinAccession + ", proteinAccessionVersion=" + proteinAccessionVersion + '}';
    }
}
