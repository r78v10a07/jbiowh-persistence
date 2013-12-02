package org.jbiowhpersistence.datasets.protein.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import org.jbiowhpersistence.datasets.protein.entities.ProteinhasProteinKeyword;
import org.jbiowhpersistence.datasets.protein.entities.ProteinhasProteinKeywordPK;

/**
 * This class is the Protein has ProteinKeyword EntryMap
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Nov 20, 2013
 */
public class ProteinhasProteinKeywordEntryMap {

    @XmlAttribute
    private ProteinhasProteinKeywordPK key;
    @XmlValue
    private ProteinhasProteinKeyword value;

    public ProteinhasProteinKeywordEntryMap(ProteinhasProteinKeywordPK key, ProteinhasProteinKeyword value) {
        this.key = key;
        this.value = value;
    }

    public ProteinhasProteinKeywordPK getKey() {
        return key;
    }

    public ProteinhasProteinKeyword getValue() {
        return value;
    }
}
