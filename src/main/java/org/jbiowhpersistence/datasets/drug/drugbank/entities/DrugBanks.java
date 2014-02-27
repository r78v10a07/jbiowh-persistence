package org.jbiowhpersistence.datasets.drug.drugbank.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the XML DrugBank class to hold an DrugBank array
 *
 * $Author$ $LastChangedDate$ $LastChangedRevision$
 *
 * @since Feb 26, 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "drugBanks")
public class DrugBanks {

    @XmlElement(name = "drugBank", type = DrugBank.class)
    private List<DrugBank> drugbanks = new ArrayList<DrugBank>();

    public DrugBanks() {
    }

    public DrugBanks(List<DrugBank> drugbanks) {
        this.drugbanks = drugbanks;
    }

    public List<DrugBank> getDrugBanks() {
        return drugbanks;
    }

    public void setDrugBanks(List<DrugBank> drugbanks) {
        this.drugbanks = drugbanks;
    }
}
