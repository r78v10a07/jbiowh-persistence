package org.jbiowhpersistence.datasets.gene.genebank.util.geneassigment;

import java.util.Comparator;
import java.util.List;

/**
 * This class is the list of objects to print
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 31, 2013
 */
public class GeneAssignedData {

    private final List<String> data;
    private static int[] indToSort;
    private static int[] indInteger;
    private static int[] indToInvert;

    /**
     * Create the output data and initialized the list
     *
     * @param data
     */
    public GeneAssignedData(List<String> data) {
        this.data = data;
    }

    /**
     * Set the indexes for the comparison
     *
     * @param indToSort the selected columns to sort
     * @param indInteger the selected columns that are integer
     * @param indToInvert the selected columns that have to be inverted the sort
     */
    public static void setIndexes(int[] indToSort, int[] indInteger, int[] indToInvert) {
        GeneAssignedData.indToSort = indToSort;
        GeneAssignedData.indInteger = indInteger;
        GeneAssignedData.indToInvert = indToInvert;
    }

    /**
     * Get the data list
     *
     * @return the data list
     */
    public List<String> getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) != null) {
                    b.append(data.get(i));
                } else {
                    b.append("");
                }
                if (i != data.size() - 1) {
                    b.append("\t");
                }
            }
        }
        return b.toString().trim();
    }

    /**
     * Creates a String with the selected columns
     *
     * @param indexes the selected columns
     * @return a String with the selected columns
     */
    public String toString(int[] indexes) {
        StringBuilder b = new StringBuilder();
        if (data != null) {
            if (indexes == null) {
                return toString();
            }
            for (int i = 0; i < indexes.length; i++) {
                if (indexes[i] >= 0 && indexes[i] < data.size()) {
                    if (data.get(indexes[i]) != null) {
                        b.append(data.get(indexes[i]));
                    } else {
                        b.append("");
                    }
                    if (i != indexes.length - 1) {
                        b.append("\t");
                    }
                }
            }
        }
        return b.toString().trim();
    }

    /**
     * Compare two data according with taxId (field 1), locus name (field 0),
     * from (field 6) and to (field 7)
     */
    public static Comparator<GeneAssignedData> OutputDataComparator = new Comparator<GeneAssignedData>() {
        @Override
        public int compare(GeneAssignedData o1, GeneAssignedData o2) {
            int comp;
            if (GeneAssignedData.indToSort != null) {
                for (int i = 0; i < GeneAssignedData.indToSort.length; i++) {
                    boolean flag = false;
                    if (GeneAssignedData.indInteger != null) {
                        for (int j = 0; j < GeneAssignedData.indInteger.length; j++) {
                            if (GeneAssignedData.indToSort[i] == GeneAssignedData.indInteger[j]) {
                                flag = true;
                                break;
                            }
                        }
                    }
                    boolean flagInv = false;
                    if (GeneAssignedData.indToInvert != null) {
                        for (int j = 0; j < GeneAssignedData.indToInvert.length; j++) {
                            if (GeneAssignedData.indToSort[i] == GeneAssignedData.indToInvert[j]) {
                                flagInv = true;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        comp = Integer.valueOf(o1.getData().get(GeneAssignedData.indToSort[i])).compareTo(Integer.valueOf(o2.getData().get(GeneAssignedData.indToSort[i])));
                    } else {
                        comp = o1.getData().get(GeneAssignedData.indToSort[i]).compareTo(o2.getData().get(GeneAssignedData.indToSort[i]));
                    }
                    if (comp != 0) {
                        if (flagInv) {
                            return -1 * comp;
                        }
                        return comp;
                    }
                }
            }
            return o1.getData().get(0).compareTo(o2.getData().get(0));
        }
    };
}
