package org.jbiowhpersistence.datasets.gene.genebank.util.geneassigment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.xml.bind.JAXBException;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.utils.JBioWHMessage;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBank;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDS;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCDSLocation;
import org.jbiowhpersistence.datasets.gene.genebank.entities.GeneBankCOG;
import org.jbiowhpersistence.datasets.protgroup.ncbiprotclust.entities.ProtClust;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;

/**
 * This class is assign genes using the Taxoner output file or a blast XML
 * result file
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Jan 2, 2014
 */
public class GeneAssignment {

    private Set<GeneAssignedData> geneAssignedData;
    private GenBankCDSAssignment genBankCDSAssignment;

    /**
     * Creates the gene assignment object
     */
    public GeneAssignment() {
        geneAssignedData = new TreeSet<GeneAssignedData>(GeneAssignedData.OutputDataComparator);
        genBankCDSAssignment = new GenBankCDSAssignment();
    }

    /**
     * Perform the gene assignment parsing the input file
     *
     * @param inputFileName a Taxoner output file or XML blast file name
     * @param fileType 1 for the Taxoner output file, 2 for the XML blast file
     * blast file
     * @param indToSort array with the indexes of the columns to sort the output
     * @param indInteger array with the indexes of the columns to be considered
     * as Integer
     * @param indToInvert array with the indexes of the columns to invert the
     * order
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public void assignGenes(String inputFileName, int fileType, int[] indToSort, int[] indInteger, int[] indToInvert) throws JAXBException, IOException {
        File f = new File(inputFileName);
        assignGenes(f, fileType, indToSort, indInteger, indToInvert);
    }

    /**
     * Perform the gene assignment parsing the input file
     *
     * @param inputFileName a Taxoner output file or XML blast file name
     * @param fileType 1 for the Taxoner output file, 2 for the XML blast file
     * blast file
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public void assignGenes(String inputFileName, int fileType) throws JAXBException, IOException {
        File f = new File(inputFileName);
        assignGenes(f, fileType);
    }

    /**
     * Perform the gene assigment parsing the file
     *
     * @param inputFile a Taxoner output file or XML blast file
     * @param fileType 1 for the Taxoner output file, 2 for the XML blast file
     * @param indToSort array with the indexes of the columns to sort the output
     * @param indInteger array with the indexes of the columns to be considered
     * as Integer
     * @param indToInvert array with the indexes of the columns to invert the
     * order
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public void assignGenes(File inputFile, int fileType, int[] indToSort, int[] indInteger, int[] indToInvert) throws JAXBException, IOException {
        GeneAssignedData.setIndexes(indToSort, indInteger, indToInvert);
        switch (fileType) {
            case 1: {
                genBankCDSAssignment.parseTaxoner(inputFile);
                break;
            }

            case 2: {
                genBankCDSAssignment.parseBlastXML(inputFile);
                break;
            }

            default: {
                throw new IOException("The fileType argument should be 1 for Taxoner or 2 for blast XML");
            }
        }
        createGeneAssignedData();
    }

    /**
     * Perform the gene assigment parsing the file
     *
     * @param inputFile a Taxoner output file or XML blast file
     * @param fileType 1 for the Taxoner output file, 2 for the XML blast file
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public void assignGenes(File inputFile, int fileType) throws JAXBException, IOException {
        switch (fileType) {
            case 1: {
                GeneAssignedData.setIndexes(new int[]{1, 6, 10, 8}, new int[]{1, 10}, new int[]{10});
                genBankCDSAssignment.parseTaxoner(inputFile);
                break;
            }

            case 2: {
                GeneAssignedData.setIndexes(new int[]{2, 0, 5, 3}, new int[]{2, 5}, new int[]{5});
                genBankCDSAssignment.parseBlastXML(inputFile);
                break;
            }

            default: {
                throw new IOException("The fileType argument should be 1 for Taxoner or 2 for blast XML");
            }
        }
        createGeneAssignedData();
    }

    /**
     * Perform the gene assigment parsing the file
     *
     * @param inputFile a Taxoner output file or XML blast file
     * @param fileType 1 for the Taxoner output file, 2 for the XML blast file
     * @param indToSort array with the indexes of the columns to sort the output
     * @param indInteger array with the indexes of the columns to be considered
     * as Integer
     * @param indToInvert array with the indexes of the columns to invert the
     * order
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public void assignGenes(InputStream inputFile, int fileType, int[] indToSort, int[] indInteger, int[] indToInvert) throws JAXBException, IOException {
        GeneAssignedData.setIndexes(indToSort, indInteger, indToInvert);
        switch (fileType) {
            case 1: {
                genBankCDSAssignment.parseTaxoner(inputFile);
                break;
            }

            case 2: {
                genBankCDSAssignment.parseBlastXML(inputFile);
                break;
            }

            default: {
                throw new IOException("The fileType argument should be 1 for Taxoner or 2 for blast XML");
            }
        }
        createGeneAssignedData();
    }

    /**
     * Perform the gene assigment parsing the file
     *
     * @param inputFile a Taxoner output file or XML blast file
     * @param fileType 1 for the Taxoner output file, 2 for the XML blast file
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public void assignGenes(InputStream inputFile, int fileType) throws JAXBException, IOException {
        switch (fileType) {
            case 1: {
                GeneAssignedData.setIndexes(new int[]{1, 6, 10, 8}, new int[]{1, 10}, new int[]{10});
                genBankCDSAssignment.parseTaxoner(inputFile);
                break;
            }

            case 2: {
                GeneAssignedData.setIndexes(new int[]{2, 0, 5, 3}, new int[]{2, 5}, new int[]{5});
                genBankCDSAssignment.parseBlastXML(inputFile);
                break;
            }

            default: {
                throw new IOException("The fileType argument should be 1 for Taxoner or 2 for blast XML");
            }
        }
        createGeneAssignedData();
    }

    private void createGeneAssignedData() {
        geneAssignedData.clear();
        JBioWHMessage.getInstance().setMessage("Processing CDS assigment");
        JBioWHMessage.getInstance().setValue(1);
        for (Long geneBank_WID : genBankCDSAssignment.getGenBankWIDMap().keySet()) {
            JBioWHMessage.getInstance().setMessage("Processing" + JBioWHMessage.getInstance().getValue() + " CDS assigment of " + genBankCDSAssignment.getGenBankWIDMap().keySet().size());
            JBioWHMessage.getInstance().increaseValue();
            GeneBank g = JBioWHPersistence.getInstance().createEntityManager().find(GeneBank.class, geneBank_WID);
            for (GenBankCDSAssignmentData assData : genBankCDSAssignment.getGenBankWIDMap().get(geneBank_WID)) {
                GeneBankCDS c = JBioWHPersistence.getInstance().createEntityManager().find(GeneBankCDS.class, assData.getGeneBankCDS_WID());
                GeneBankCDSLocation loc = c.getGeneBankCDSLocation().iterator().next();
                StringBuilder COG = new StringBuilder("");
                if (c.getGeneBankCOG() != null) {
                    for (GeneBankCOG cog : c.getGeneBankCOG()) {
                        COG.append(cog.getCogId()).append(",");
                    }
                }
                String locus = "";
                if (c.getLocusTag() != null) {
                    locus = c.getLocusTag();
                }
                String prot = "";
                if (c.getProteinId() != null) {
                    prot = c.getProteinId();
                }
                StringBuilder ncbiProtClust = new StringBuilder("");
                if (c.getGeneInfo() != null) {
                    for (GeneInfo gene : c.getGeneInfo()) {
                        if (gene.getProtClust() != null) {
                            for (ProtClust p : gene.getProtClust()) {
                                ncbiProtClust.append(p.getEntry()).append(",");
                            }
                        }
                    }
                }
                String line = assData.getSource().iterator().next() + "\t"
                        + g.getLocusName() + "\t"
                        + g.getTaxId() + "\t"
                        + prot + "\t"
                        + locus + "\t"
                        + assData.getScore().size() + "\t"
                        + loc.getpFrom().toString() + "\t"
                        + loc.getpTo().toString() + "\t"
                        + COG.toString().replaceAll(",$", "") + "\t"
                        + ncbiProtClust.toString().replaceAll(",$", "");
                geneAssignedData.add(new GeneAssignedData(Arrays.asList(line.split("\t"))));
            }
        }
        VerbLogger.getInstance().log(this.getClass(), "Found " + geneAssignedData.size() + " genes/proteins");
    }

    /**
     * Print the genes assigned to the file using the selected columns
     *
     * @param out the output file
     * @param columns array with the indexes of the selected columns to print,
     * use null to print all columns
     */
    public void printGenes(PrintWriter out, int[] columns) {
        if (geneAssignedData != null) {
            for (GeneAssignedData d : geneAssignedData) {
                out.println(d.toString(columns));
            }
        }
    }

    /**
     * Print the missing Gi in the out file
     *
     * @param out the out file to print the missing Gi
     */
    public void printMissingGenes(PrintWriter out) {
        if (genBankCDSAssignment != null && genBankCDSAssignment.getMissingGi() != null) {
            VerbLogger.getInstance().log(this.getClass(), "There are " + genBankCDSAssignment.getMissingGi().size() + " Gi that are not in the JBioWH database");
            for (Long l : genBankCDSAssignment.getMissingGi()) {
                out.println(l);
            }
        }
    }

    /**
     * Get the gene assigned data
     *
     * @return the gene assigned data
     */
    public Set<GeneAssignedData> getGeneAssignedData() {
        return geneAssignedData;
    }

    /**
     * Get the GenBankCDSAssignment object
     *
     * @return the GenBankCDSAssignment object
     */
    public GenBankCDSAssignment getGenBankCDSAssignment() {
        return genBankCDSAssignment;
    }

    public List<List<Integer>> getTaxResume(int[] indexes, int[] indToSum, int[] indSplit) {
        Map<Integer, SortedMap<Integer, Object>> taxMap = new HashMap();
        List<List<Integer>> taxs = new ArrayList<List<Integer>>();

        SortedMap<Integer, Object> internal;
        for (GeneAssignedData a : geneAssignedData) {
            internal = taxMap.get(Integer.valueOf(a.getData().get(indexes[0])));
            if (internal != null) {
                for (int i = 0; i < indexes.length; i++) {
                    if (indToSum != null) {
                        boolean flag = false;
                        for (int j = 0; j < indToSum.length; j++) {
                            if (indexes[i] == indToSum[j]) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            if (a.getData().size() > indexes[i] && a.getData().get(indexes[i]) != null && !a.getData().get(indexes[i]).isEmpty()) {
                                boolean flagSplit = false;
                                for (int j = 0; j < indSplit.length; j++) {
                                    if (indexes[i] == indSplit[j]) {
                                        flagSplit = true;
                                        break;
                                    }
                                }
                                if (!flagSplit) {
                                    ((Set) internal.get(i)).add(a.getData().get(indexes[i]));
                                } else {
                                    ((Set) internal.get(i)).addAll(Arrays.asList(a.getData().get(indexes[i]).split(",")));
                                }
                            }
                        } else {
                            if (a.getData().size() > indexes[i] && a.getData().get(indexes[i]) != null && !a.getData().get(indexes[i]).isEmpty()) {
                                internal.put(i, ((Integer) internal.get(i)) + Integer.valueOf(a.getData().get(indexes[i])));
                            }
                        }
                    } else {
                        if (a.getData().size() > indexes[i] && a.getData().get(indexes[i]) != null && !a.getData().get(indexes[i]).isEmpty()) {
                            if (indSplit == null) {
                                ((Set) internal.get(i)).add(a.getData().get(indexes[i]));
                            } else {
                                boolean flagSplit = false;
                                for (int j = 0; j < indSplit.length; j++) {
                                    if (indexes[i] == indSplit[j]) {
                                        flagSplit = true;
                                        break;
                                    }
                                }
                                if (!flagSplit) {
                                    ((Set) internal.get(i)).add(a.getData().get(indexes[i]));
                                } else {
                                    ((Set) internal.get(i)).addAll(Arrays.asList(a.getData().get(indexes[i]).split(",")));
                                }
                            }
                        }
                    }
                }
            } else {
                internal = new TreeMap<Integer, Object>();
                for (int i = 0; i < indexes.length; i++) {
                    if (indToSum != null) {
                        boolean flag = false;
                        for (int j = 0; j < indToSum.length; j++) {
                            if (indexes[i] == indToSum[j]) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            if (a.getData().size() > indexes[i] && a.getData().get(indexes[i]) != null && !a.getData().get(indexes[i]).isEmpty()) {
                                Set tree = new TreeSet();
                                boolean flagSplit = false;
                                for (int j = 0; j < indSplit.length; j++) {
                                    if (indexes[i] == indSplit[j]) {
                                        flagSplit = true;
                                        break;
                                    }
                                }
                                if (!flagSplit) {
                                    tree.add(a.getData().get(indexes[i]));
                                } else {
                                    tree.addAll(Arrays.asList(a.getData().get(indexes[i]).split(",")));
                                }
                                internal.put(i, tree);
                            } else {
                                Set tree = new TreeSet();
                                internal.put(i, tree);
                            }
                        } else {
                            if (a.getData().size() > indexes[i] && a.getData().get(indexes[i]) != null && !a.getData().get(indexes[i]).isEmpty()) {
                                internal.put(i, Integer.valueOf(a.getData().get(indexes[i])));
                            } else {
                                internal.put(i, 0);
                            }
                        }
                    } else {
                        if (a.getData().size() > indexes[i] && a.getData().get(indexes[i]) != null && !a.getData().get(indexes[i]).isEmpty()) {
                            Set tree = new TreeSet();
                            if (indSplit == null) {
                                tree.add(a.getData().get(indexes[i]));
                            } else {
                                boolean flagSplit = false;
                                for (int j = 0; j < indSplit.length; j++) {
                                    if (indexes[i] == indSplit[j]) {
                                        flagSplit = true;
                                        break;
                                    }
                                }
                                if (!flagSplit) {
                                    tree.add(a.getData().get(indexes[i]));
                                } else {
                                    tree.addAll(Arrays.asList(a.getData().get(indexes[i]).split(",")));
                                }
                            }
                            internal.put(i, tree);
                        }
                    }
                }
                taxMap.put(Integer.valueOf(a.getData().get(indexes[0])), internal);
            }
        }

        if (!taxMap.isEmpty()) {
            for (Integer t : taxMap.keySet()) {
                List<Integer> ts = new LinkedList<Integer>();

                ts.add(t);
                internal = taxMap.get(t);
                for (Integer i : internal.keySet()) {
                    Object o = internal.get(i);
                    if (o instanceof Set) {
                        ts.add(((Set) o).size());
                    } else if (o instanceof Integer) {
                        ts.add(((Integer) o));
                    }
                }
                taxs.add(ts);
            }
        }

        return taxs;
    }
}
