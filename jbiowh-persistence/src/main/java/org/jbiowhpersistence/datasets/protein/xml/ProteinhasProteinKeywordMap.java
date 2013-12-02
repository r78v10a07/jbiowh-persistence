package org.jbiowhpersistence.datasets.protein.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the Protein has Protein Keyword Map
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Nov 20, 2013
 */
public class ProteinhasProteinKeywordMap {

    private List<ProteinhasProteinKeywordEntryMap> entry;

    public ProteinhasProteinKeywordMap() {
        entry = new ArrayList<ProteinhasProteinKeywordEntryMap>();
    }

    public void addEntry(ProteinhasProteinKeywordEntryMap e) {
        entry.add(e);
    }

    public List<ProteinhasProteinKeywordEntryMap> getEntry() {
        return entry;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(ProteinhasProteinKeywordEntryMap m : entry){
            builder.append(m.getValue()).append("\n");
        }
        return "ProteinhasProteinKeywordMap{\n" + builder.toString() + '}';
    }

}
