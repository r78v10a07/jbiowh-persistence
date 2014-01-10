package org.jbiowhpersistence.datasets.gene.genebank.util.geneassigment;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class is the GenBankCDS Assignment Data
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Dec 17, 2013
 */
public class GenBankCDSAssignmentData {

    private Long geneBankCDS_WID;
    private List<Float> bitScore;
    private List<Float> score;
    private List<Double> evalue;
    private Set<String> source;

    public GenBankCDSAssignmentData(Long geneBankCDS_WID) {
        this.geneBankCDS_WID = geneBankCDS_WID;
        bitScore = new LinkedList<Float>();
        score = new LinkedList<Float>();
        evalue = new LinkedList<Double>();
        source = new TreeSet<String>();
    }

    public Long getGeneBankCDS_WID() {
        return geneBankCDS_WID;
    }

    public void setGeneBankCDS_WID(Long geneBankCDS_WID) {
        this.geneBankCDS_WID = geneBankCDS_WID;
    }

    public List<Float> getBitScore() {
        return bitScore;
    }

    public void setBitScore(List<Float> bitScore) {
        this.bitScore = bitScore;
    }

    public List<Float> getScore() {
        return score;
    }

    public void setScore(List<Float> score) {
        this.score = score;
    }

    public List<Double> getEvalue() {
        return evalue;
    }

    public void setEvalue(List<Double> evalue) {
        this.evalue = evalue;
    }

    public Set<String> getSource() {
        return source;
    }

    public void setSource(Set<String> source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.geneBankCDS_WID != null ? this.geneBankCDS_WID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenBankCDSAssignmentData other = (GenBankCDSAssignmentData) obj;
        return this.geneBankCDS_WID == other.geneBankCDS_WID || (this.geneBankCDS_WID != null && this.geneBankCDS_WID.equals(other.geneBankCDS_WID));
    }

}
