package org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Pathway Relation SubType entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 29, 2011
 */
@Entity
@Table(name = "KEGGPathwayRelationSubType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGPathwayRelationSubType.findAll", query = "SELECT k FROM KEGGPathwayRelationSubType k"),
    @NamedQuery(name = "KEGGPathwayRelationSubType.findByKEGGPathwayRelationWID", query = "SELECT k FROM KEGGPathwayRelationSubType k WHERE k.keggpathwayrelationsubtypePK.kEGGPathwayRelationWID = :kEGGPathwayRelationWID"),
    @NamedQuery(name = "KEGGPathwayRelationSubType.findByName", query = "SELECT k FROM KEGGPathwayRelationSubType k WHERE k.keggpathwayrelationsubtypePK.name = :name"),
    @NamedQuery(name = "KEGGPathwayRelationSubType.findByValue", query = "SELECT k FROM KEGGPathwayRelationSubType k WHERE k.keggpathwayrelationsubtypePK.value = :value")})
public class KEGGPathwayRelationSubType implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KEGGPathwayRelationSubTypePK keggpathwayrelationsubtypePK;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGPathwayRelation_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGPathwayRelation kEGGPathwayRelation;

    public KEGGPathwayRelationSubType() {
    }

    public KEGGPathwayRelationSubType(KEGGPathwayRelationSubTypePK keggpathwayrelationsubtypePK) {
        this.keggpathwayrelationsubtypePK = keggpathwayrelationsubtypePK;
    }

    public KEGGPathwayRelationSubType(long kEGGPathwayRelationWID, String name, String value) {
        this.keggpathwayrelationsubtypePK = new KEGGPathwayRelationSubTypePK(kEGGPathwayRelationWID, name, value);
    }

    public KEGGPathwayRelation getkEGGPathwayRelation() {
        return kEGGPathwayRelation;
    }

    public void setkEGGPathwayRelation(KEGGPathwayRelation kEGGPathwayRelation) {
        this.kEGGPathwayRelation = kEGGPathwayRelation;
    }

    public KEGGPathwayRelationSubTypePK getKeggpathwayrelationsubtypePK() {
        return keggpathwayrelationsubtypePK;
    }

    public void setKeggpathwayrelationsubtypePK(KEGGPathwayRelationSubTypePK keggpathwayrelationsubtypePK) {
        this.keggpathwayrelationsubtypePK = keggpathwayrelationsubtypePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keggpathwayrelationsubtypePK != null ? keggpathwayrelationsubtypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof KEGGPathwayRelationSubType)) {
            return false;
        }
        KEGGPathwayRelationSubType other = (KEGGPathwayRelationSubType) object;
        if ((this.keggpathwayrelationsubtypePK == null && other.keggpathwayrelationsubtypePK != null) || (this.keggpathwayrelationsubtypePK != null && !this.keggpathwayrelationsubtypePK.equals(other.keggpathwayrelationsubtypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGPathwayRelationSubType{" + "keggpathwayrelationsubtypePK=" + keggpathwayrelationsubtypePK + '}';
    }
}
