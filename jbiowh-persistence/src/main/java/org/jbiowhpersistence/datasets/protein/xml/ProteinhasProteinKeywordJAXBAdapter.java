package org.jbiowhpersistence.datasets.protein.xml;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.jbiowhpersistence.datasets.protein.entities.ProteinhasProteinKeyword;
import org.jbiowhpersistence.datasets.protein.entities.ProteinhasProteinKeywordPK;

/**
 * This class is the ProteinhasProteinKeyword JAXB Adapter
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Nov 20, 2013
 */
public final class ProteinhasProteinKeywordJAXBAdapter extends
        XmlAdapter<ProteinhasProteinKeywordMap, Map<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword>> {

    @Override
    public Map<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword> unmarshal(ProteinhasProteinKeywordMap v) throws Exception {
        Map<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword> map = new HashMap<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword>();
        for (ProteinhasProteinKeywordEntryMap entry : v.getEntry()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    @Override
    public ProteinhasProteinKeywordMap marshal(Map<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword> v) throws Exception {
        ProteinhasProteinKeywordMap map = new ProteinhasProteinKeywordMap();
        System.out.println("Creating map");
        for (Entry<ProteinhasProteinKeywordPK, ProteinhasProteinKeyword> entry : v.entrySet()) {
            System.out.println("Adding map: " + entry.getKey() + "," + entry.getValue());
            ProteinhasProteinKeywordEntryMap entryMap = new ProteinhasProteinKeywordEntryMap(entry.getKey(), entry.getValue());
            map.addEntry(entryMap);
        }
        System.out.println("Returnning the map");
        System.out.println(map);
        return map;
    }

}
