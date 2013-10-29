/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbiowhpersistence.datasets.protein.util;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import org.jbiowhpersistence.datasets.protein.entities.Protein;

/**
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Sep 28, 2012
 */
public class ProteinUtils {

    private static ProteinUtils singleton;

    private ProteinUtils() {
    }

    /**
     * Print the protein ID (name) on the write file
     *
     * @param writer the write file
     * @param proteins the protein list
     */
    public void printProteinName(PrintWriter writer, List<Protein> proteins) {
        for (Protein p : proteins) {
            writer.println(p.getProteinNameDirected());
        }
    }

    /**
     * Print the protein ID (name) on the PrintStream file
     *
     * @param out the PrintStream file
     * @param proteins the protein list
     */
    public void printProteinName(PrintStream out, List<Protein> proteins) {
        for (Protein p : proteins) {
            out.println(p.getProteinNameDirected());
        }
    }

    /**
     * Return a ProteinUtils instance
     *
     * @return a ProteinUtils instance
     */
    public static synchronized ProteinUtils getInstance() {
        if (singleton == null) {
            singleton = new ProteinUtils();
        }
        return singleton;
    }
}
