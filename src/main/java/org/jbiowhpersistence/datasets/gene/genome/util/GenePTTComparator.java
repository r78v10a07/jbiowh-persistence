package org.jbiowhpersistence.datasets.gene.genome.util;

import java.util.Comparator;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;

/**
 * This class is used as Comparator for Set<GenePTT>
 *
 * $Author$ $LastChangedDate: 2013-05-29 11:24:54 +0200
 * (Wed, 29 May 2013) $ $LastChangedRevision$
 * @since Oct 3, 2012
 */
public class GenePTTComparator implements Comparator<GenePTT> {

    @Override
    public int compare(GenePTT o1, GenePTT o2) {
        int comp = o1.getPTTFile().compareTo(o2.getPTTFile());
        if (comp == 0) {
            if (o1.getPFrom() < o2.getPFrom()) {
                return -1;
            } else if (o1.getPFrom() == o2.getPFrom()) {
                if (o1.getPTo() < o2.getPTo()) {
                    return -1;
                } else if (o1.getPTo() == o2.getPTo()) {
                    if (o2.getProteinGi() != null && o1.getProteinGi() != null) {
                        return o2.getProteinGi().compareTo(o1.getProteinGi());
                    } else {
                        if (o2.getCode() != null && o1.getCode() != null) {
                            return o2.getCode().compareTo(o1.getCode());
                        } else {
                            if (o2.getGeneSymbol()!= null && o1.getGeneSymbol() != null) {
                                return o2.getGeneSymbol().compareTo(o1.getGeneSymbol());
                            } else {
                                if (o2.getGeneLocusTag()!= null && o1.getGeneLocusTag() != null) {
                                    return o2.getGeneLocusTag().compareTo(o1.getGeneLocusTag());
                                } else {
                                    return 0;
                                }
                            }
                        }
                    }
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
        return comp;
    }
}
