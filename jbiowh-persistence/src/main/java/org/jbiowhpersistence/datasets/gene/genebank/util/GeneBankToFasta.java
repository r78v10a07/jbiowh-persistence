package org.jbiowhpersistence.datasets.gene.genebank.util;

import org.jbiowhcore.utility.fileformats.fasta.FastaEntry;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;

/**
 * This Class is the converter from GenBank to fasta
 *
 * $Author$
 * $LastChangedDate$
 * $LastChangedRevision$
 * @since Jul 17, 2013
 */
public class GeneBankToFasta {

    private static GeneBankToFasta singleton;

    private GeneBankToFasta() {
    }

    /**
     * Return a GeneBankToFasta instance
     *
     * @return a GeneBankToFasta instance
     */
    public static synchronized GeneBankToFasta getInstance() {
        if (singleton == null) {
            singleton = new GeneBankToFasta();
        }
        return singleton;
    }
    
    public FastaEntry geneBankToFasta(GeneBank geneBank){
        FastaEntry f = new FastaEntry("gi|" + geneBank.getGi() + "|gb|" + geneBank.getLocusName() + "." + geneBank.getVersion() + "|" + geneBank.getDefinition());
        f.setSeq(geneBank.getSeq());
        return f;
    }
 }
