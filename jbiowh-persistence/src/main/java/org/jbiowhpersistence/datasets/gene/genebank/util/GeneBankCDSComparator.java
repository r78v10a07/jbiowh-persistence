package org.jbiowhpersistence.datasets.gene.genebank.util;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;

/**
 * This class is used as Comparator for Set<GeneBankCDS>
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 17, 2013
 */
public class GeneBankCDSComparator implements Comparator<GeneBankCDS> {

    @Override
    public int compare(GeneBankCDS o1, GeneBankCDS o2) {
        int comp = new Long(o1.getGeneBankWID()).compareTo(new Long(o2.getGeneBankWID()));

        if (comp == 0) {
            try {
                Pattern p = Pattern.compile("\\D*(\\d+)\\D*");
                Matcher m1 = p.matcher(o1.getLocation());
                Matcher m2 = p.matcher(o2.getLocation());
                m1.find();
                m2.find();
                return new Long(m1.group(1)).compareTo(new Long(m2.group(1)));
            } catch (IllegalStateException ex) {
            }
        }
        return comp;
    }

}
