package org.jbiowhpersistence.datasets.gene.genome.util;

import java.sql.SQLException;
import java.util.*;
import javax.persistence.NoResultException;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.gene.gene.controller.GeneInfoJpaController;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.gene.genome.controller.GenePTTJpaController;
import org.jbiowhpersistence.datasets.gene.genome.entities.GenePTT;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;
import org.jbiowhpersistence.datasets.taxonomy.util.TaxonomyGraph;
import org.jbiowhpersistence.utils.controller.AbstractController;
import org.jbiowhpersistence.utils.entitymanager.JBioWHPersistence;

/**
 * This Class handled Chromosome PTT
 *
 * $Author$ $LastChangedDate: 2012-11-08 14:37:19 +0100
 * (Thu, 08 Nov 2012) $ $LastChangedRevision$
 * @since Aug 22, 2012
 */
public class Chromosome extends AbstractController {

    private List<GenePTT> genePTTs;
    private int index = 0;

    /**
     * Creates the Chromosome
     */
    public Chromosome() {
        genePTTs = new ArrayList();
    }

    /**
     * Return the amount of genes on the chromosome
     *
     * @return the amount of genes on the chromosome
     */
    public int size() {
        return genePTTs.size();
    }

    /**
     * Clear the chromosome gene list
     *
     */
    public void clear() {
        genePTTs.clear();
        index = 0;
    }

    /**
     * Create a Chromosome from one of its genes
     *
     * @param taxId
     * @throws java.sql.SQLException
     */
    public void createChromosomeFromTaxonomy(Long taxId) throws SQLException, Exception {
        long startTime = System.currentTimeMillis();
        VerbLogger.getInstance().log(this.getClass(), "Getting genes into the same Chromosome than: " + taxId);
        HashMap parm = new HashMap();

        genePTTs.clear();
        parm.put("taxId", taxId);
        List<GenePTT> genesPTTTaxs = ((GenePTTJpaController) getController(GenePTTJpaController.class)).useNamedQuery("GenePTT.findByTaxId", parm);
        if (!genesPTTTaxs.isEmpty()) {
            genePTTs.addAll(genesPTTTaxs);
            VerbLogger.getInstance().log(this.getClass(), taxId + " has " + genesPTTTaxs.size() + " genes in PTT data");
        }

        VerbLogger.getInstance().log(this.getClass(), "Loaded " + getGenePTTs().size() + " genes from PTT data in "
                + ((long) (System.currentTimeMillis() - startTime) / 1000) + " s");
    }

    /**
     * Create a Chromosome from one of its genes
     *
     * @param taxId
     * @throws java.sql.SQLException
     */
    public void createChromosomeFromTaxonomyGraph(Long taxId) throws SQLException, Exception {
        long startTime = System.currentTimeMillis();
        VerbLogger.getInstance().log(this.getClass(), "Getting genes into the same Chromosome than: " + taxId);
        HashMap parm = new HashMap();
        TaxonomyGraph graph = new TaxonomyGraph(-1);

        graph.populate(taxId);

        genePTTs.clear();
        for (Taxonomy t : (Collection<Taxonomy>) graph.vertexSet()) {
            parm.put("taxonomy", t);
            List<GenePTT> genesPTTTaxs = ((GenePTTJpaController) getController(GenePTTJpaController.class)).useNamedQuery("GenePTT.findByTaxonomy", parm);
            if (!genesPTTTaxs.isEmpty()) {
                genePTTs.addAll(genesPTTTaxs);
                VerbLogger.getInstance().log(this.getClass(), t.getTaxonomySynonym() + " has " + genesPTTTaxs.size() + " genes in PTT data");
            }
        }
        VerbLogger.getInstance().log(this.getClass(), "Loaded " + getGenePTTs().size() + " genes from PTT data in "
                + ((long) (System.currentTimeMillis() - startTime) / 1000) + " s");
    }

    /**
     * Create a Chromosome from one of its genes
     *
     * @param geneId the gene Id to search the chromosome
     * @throws java.sql.SQLException
     */
    public void createChromosomeFromGeneInfo(Long geneId) throws SQLException {
        VerbLogger.getInstance().log(this.getClass(), "Getting genes into the same Chromosome than: " + geneId);
        createChromosomeFromGeneId(geneId);
    }

    /**
     * Create a Chromosome List (chromosome) from one of its genes
     *
     * @param geneId the gene Id to search the chromosome
     * @throws java.sql.SQLException
     */
    public void createChromosomeFromGeneId(Long geneId) throws SQLException {
        HashMap parm = new HashMap();
        parm.put("geneID", geneId);
        try {
            GeneInfo gene = (GeneInfo) ((GeneInfoJpaController) getController(GeneInfoJpaController.class)).useNamedQuerySingleResult("GeneInfo.findByGeneID", parm);
            createChromosomeFromGeneInfo(gene);
        } catch (NoResultException ex) {
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().DEBUG);
            VerbLogger.getInstance().log(this.getClass(), "There is not gene with GeneId: " + geneId);
            VerbLogger.getInstance().setLevel(VerbLogger.getInstance().getInitialLevel());
        }
    }

    /**
     * Return the gene position in the Chromosome
     *
     * @return the gene position in the Chromosome
     */
    public int getGenePosition() {
        return index + 1;
    }

    /**
     * Create a Chromosome from one of its genes
     *
     * @param gene the gene object
     * @throws java.sql.SQLException
     */
    public void createChromosomeFromGeneInfo(GeneInfo gene) throws SQLException {
        if (gene != null) {
            createChromosomeFromGenePTT(gene.getGenePTT());
        }
    }

    /**
     * Create a Chromosome List (chromosome) from one of its genes
     *
     * @param genePTT the GenePTT object
     * @throws java.sql.SQLException
     */
    public void createChromosomeFromGenePTT(GenePTT genePTT) throws SQLException {
        if (genePTT != null) {
            createChromosomeFromPTTFile(genePTT.getPTTFile());
            setCurrent(genePTT);
        }
    }

    /**
     * Create a Chromosome from one of its proteinGi
     *
     * @param proteinGi the gene Id to search the chromosome
     * @throws java.sql.SQLException
     */
    public void createChromosomeFromProteinGi(Long proteinGi) throws SQLException {
        if (proteinGi != null) {
            HashMap parm = new HashMap();
            parm.put("proteinGi", proteinGi);
            createChromosomeFromParm(parm);
        }
    }

    /**
     * Create a Chromosome List (chromosome) from one its PTT file name
     *
     * @param pttFile the PTT file name
     * @throws java.sql.SQLException
     */
    public void createChromosomeFromPTTFile(String pttFile) throws SQLException {
        if (pttFile != null && !pttFile.isEmpty()) {
            HashMap parm = new HashMap();
            parm.put("pTTFile", pttFile);
            VerbLogger.getInstance().log(this.getClass(), "Getting gene list from chromosome: " + pttFile);
            createChromosomeFromParm(parm);
        }
    }

    /**
     * Create a Chromosome from one its PTT file name
     *
     * @param parm theHasMap with the parameters
     * @throws java.sql.SQLException
     */
    public void createChromosomeFromParm(HashMap parm) throws SQLException {
        if (parm != null && !parm.isEmpty()) {
            long startTime = System.currentTimeMillis();
            setGenePTTs(((GenePTTJpaController) getController(GenePTTJpaController.class)).useNamedQuery("GenePTT.findByPTTFile", parm));
            VerbLogger.getInstance().log(this.getClass(), "Loaded " + getGenePTTs().size() + " genes from PTT data in "
                    + ((long) (System.currentTimeMillis() - startTime) / 1000) + " s");
        }
    }

    /**
     * Return the list of genes on the chromosome
     *
     * @return the list of genes on the chromosome
     */
    public List<GenePTT> getGenePTTs() {
        return genePTTs;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements.
     */
    public boolean isEmpty() {
        return genePTTs.isEmpty();
    }

    /**
     * Set the list of genes on the chromosome. Set the current gene to the
     * first gene on the chromosome
     *
     * @param genePTTs the list of genes on the chromosome.
     */
    public void setGenePTTs(List<GenePTT> genePTTs) {
        this.genePTTs = genePTTs;
        Collections.sort(this.genePTTs, new GenePTTComparator());
        if (!genePTTs.isEmpty()) {
            index = 0;
        }
    }

    /**
     * Order the genes by its position on the chromosome
     */
    public void orderByPFrom() {
        Collections.sort(genePTTs, new GenePTTComparator());
    }

    /**
     * Return the first gene on the chromosome
     *
     * @return the first gene on the chromosome
     */
    public GenePTT getFirst() {
        if (!genePTTs.isEmpty()) {
            index = 0;
            return genePTTs.get(index);
        }
        return null;
    }

    /**
     * Return the last gene on the chromosome
     *
     * @return the last gene on the chromosome
     */
    public GenePTT getLast() {
        if (!genePTTs.isEmpty()) {
            index = size() - 1;
            return genePTTs.get(index);
        }
        return null;
    }

    /**
     * Get the current gene on the chromosome list
     *
     * @return the current gene on the chromosome list
     */
    public GenePTT getCurrent() {
        return genePTTs.get(index);
    }

    /**
     * Set the index position to the index
     *
     * @param index the index position
     * @return -1 in an error occurs, the index if its an OK operation
     */
    public GenePTT setCurrent(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        this.index = index;
        return getCurrent();
    }

    /**
     * Set the current gene to the GenePTT (gene) object
     *
     * @param genePTT the GenePTT (gene) object
     * @return -1 in an error occurs, the index if its an OK operation
     */
    public int setCurrent(GenePTT genePTT) {
        int result = Collections.indexOfSubList(genePTTs, Arrays.asList(genePTT));
        if (result != -1) {
            index = result;
        }
        return result;
    }

    /**
     * Return the next gene to the current
     *
     * @return the next gene to the current
     */
    public GenePTT getNext() {
        if (index >= 0 && index < size() - 1) {
            index++;
            return genePTTs.get(index);
        }
        return null;
    }

    /**
     * Return the previous gene to the current
     *
     * @return the previous gene to the current
     */
    public GenePTT getPrevious() {
        if (index > 0 && index <= size() - 1) {
            index--;
            return genePTTs.get(index);
        }
        return null;
    }

    @Override
    protected HashMap<Class, Object> createController() {
        HashMap<Class, Object> controllers = new HashMap();
        controllers.put(GeneInfoJpaController.class, new GeneInfoJpaController(JBioWHPersistence.getInstance().getWHEntityManager()));
        controllers.put(GenePTTJpaController.class, new GenePTTJpaController(JBioWHPersistence.getInstance().getWHEntityManager()));
        return controllers;
    }
}
