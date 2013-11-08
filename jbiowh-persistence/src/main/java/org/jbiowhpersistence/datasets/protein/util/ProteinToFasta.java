/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbiowhpersistence.datasets.protein.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.utils.ReadLinesToList;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.protein.search.SearchProtein;

/**
 * This class is used to print Proteins sequences in fasta format
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ $LastChangedRevision: 396 $
 *
 * @since Sep 28, 2012
 */
public class ProteinToFasta {

    private PrintWriter fastaFile;
    private SearchProtein sProt;

    /**
     * Create a ProteinToFasta object
     *
     * @param fastaFile the file to print the protein's sequences on fasta
     * format
     */
    public ProteinToFasta(PrintWriter fastaFile) {
        this.fastaFile = fastaFile;
        sProt = new SearchProtein();
    }

    /**
     * Read the Uniprot Id from the file name and print the fasta format in the
     * PrintWriter passed to the constructor
     *
     * @param idFileName the file's name with the UniProt Ids
     * @return a list with the Id that does not have proteins
     * @throws FileNotFoundException throw this exception if the file writer is
     * not initialized for printing
     * @throws IOException
     */
    public List<String> proteinFastatoFile(String idFileName)
            throws FileNotFoundException, IOException {
        return proteinFastatoFile(ReadLinesToList.getInstance().readLinesToList(idFileName));
    }

    /**
     * The method receives a collection and print the proteins in the file
     * passed to the constructor<br/> If the objects collection is a collection
     * of string with the UniProt Id or Accession Code the method search the
     * protein and print the fasta format.<br/> If the objects collection is a
     * collection of Proteins the method print the fasta format. *
     *
     * @param objects a collection and print the proteins in the file passed to
     * the constructor
     * @return a list with the Id that does not have proteins
     * @throws FileNotFoundException throw this exception if the file writer is
     * not initialized for printing
     */
    public List<String> proteinFastatoFile(Collection objects) throws FileNotFoundException {
        List<String> notFoundIds = new ArrayList();
        if (objects != null && !objects.isEmpty()) {
            for (Object obj : objects) {
                if (obj instanceof String) {
                    try {
                        List<Protein> prots = sProt.search((String) obj, null);
                        if (!prots.isEmpty()) {
                            proteinFastatoFile(prots);
                        } else {
                            notFoundIds.add((String) obj);
                        }
                    } catch (SQLException ex) {
                        VerbLogger.getInstance().setLevel(VerbLogger.getInstance().ERROR);
                        VerbLogger.getInstance().log(this.getClass(), ex.getMessage());
                        System.exit(1);
                    }
                } else if (obj instanceof Protein) {
                    if (fastaFile != null) {
                        fastaFile.print(((Protein) obj).getFastaFormat());
                    } else {
                        throw new FileNotFoundException("The file writes is not initialized for printing");
                    }
                }
            }
        }
        return notFoundIds;
    }

    /**
     * Get a list with all fields to be printed from a Dataset collection
     *
     * @param data the Dataset collection
     * @return a list with all fields to be printed from a Dataset collection
     */
    public List getFastaFromCollection(Collection data) {
        ArrayList result = new ArrayList();

        for (Object d : data) {
            String fasta = getFasta(d);
            if (fasta != null && !fasta.isEmpty()) {
                result.add(getFasta(d));
            }
        }

        return result;
    }

    /**
     * Verify if the object is a Protein entity and return the Fasta format for
     * this protein
     *
     * @param object the data object to extract the fields
     * @return the fasta format for this protein
     */
    public String getFasta(Object object) {
        if (object instanceof Protein) {
            return ((Protein) object).getFastaFormat();
        }
        return null;
    }
}
