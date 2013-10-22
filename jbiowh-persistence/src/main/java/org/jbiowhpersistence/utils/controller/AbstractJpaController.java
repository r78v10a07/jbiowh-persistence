package org.jbiowhpersistence.utils.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.jbiowhpersistence.datasets.dataset.controller.DataSetJpaController;
import org.jbiowhpersistence.datasets.dataset.entities.DataSet;
import org.jbiowhpersistence.datasets.domain.pfam.controller.PfamAbioWHJpaController;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.drug.drugbank.controller.DrugBankJpaController;
import org.jbiowhpersistence.datasets.drug.drugbank.entities.DrugBank;
import org.jbiowhpersistence.datasets.gene.gene.controller.GeneInfoJpaController;
import org.jbiowhpersistence.datasets.gene.gene.entities.GeneInfo;
import org.jbiowhpersistence.datasets.ontology.controller.OntologyJpaController;
import org.jbiowhpersistence.datasets.ontology.entities.Ontology;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGCompoundJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGEnzymeJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGGeneJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.controller.KEGGPathwayJpaController;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.compound.KEGGCompound;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.enzyme.KEGGEnzyme;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.gene.KEGGGene;
import org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway.KEGGPathway;
import org.jbiowhpersistence.datasets.ppi.controller.MIFEntryInteractionJpaController;
import org.jbiowhpersistence.datasets.ppi.entities.MIFEntryInteraction;
import org.jbiowhpersistence.datasets.protclust.controller.UniRefEntryJpaController;
import org.jbiowhpersistence.datasets.protclust.controller.UniRefMemberJpaController;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefEntry;
import org.jbiowhpersistence.datasets.protclust.entities.UniRefMember;
import org.jbiowhpersistence.datasets.protein.controller.ProteinJpaController;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.datasets.taxonomy.controller.TaxonomyJpaController;
import org.jbiowhpersistence.datasets.taxonomy.entities.Taxonomy;

/**
 * This Class is the abstract class to JPA controller
 *
 * @param <T> 
 * $Author: r78v10a07@gmail.com $ 
 * $LastChangedDate: 2013-05-29 11:24:54 +0200 (Wed, 29 May 2013) $
 * $LastChangedRevision: 591 $
 * @since Nov 17, 2010
 */
public abstract class AbstractJpaController<T> {

    /**
     * This method return the Persistence EntityManager
     *
     * @return the Persistence EntityManager
     */
    public abstract EntityManager getEntityManager();

    /**
     * Return the DataSet the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param object the DataSet the be persisted
     * @return the DataSet the be persisted
     * @throws Exception
     */
    protected DataSet createDataSet(EntityManagerFactory emf, EntityManager em, DataSet object) throws Exception {
        if (object == null) {
            return null;
        }
        DataSet dataSet = em.find(DataSet.class, object.getWid());
        if (dataSet != null) {
            return dataSet;
        } else {
            DataSetJpaController taxController = new DataSetJpaController(emf);
            taxController.create(object);
            return em.getReference(DataSet.class, object.getWid());
        }
    }

    /**
     * Return the Taxonomy the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param object the Taxonomy the be persisted
     * @return the Taxonomy the be persisted
     * @throws Exception
     */
    protected Taxonomy createTaxonomy(EntityManagerFactory emf, EntityManager em, Taxonomy object) throws Exception {
        if (object == null) {
            return null;
        }
        Taxonomy tax = em.find(Taxonomy.class, object.getWid());
        if (tax != null) {
            return tax;
        } else {
            TaxonomyJpaController taxController = new TaxonomyJpaController(emf);
            taxController.create(object);
            return em.getReference(Taxonomy.class, object.getWid());
        }
    }

    /**
     * Return the Ontology list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the Ontology list the be persisted
     * @return the Ontology list the be persisted
     * @throws Exception
     */
    protected Set<Ontology> createOntology(EntityManagerFactory emf, EntityManager em, Set<Ontology> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            OntologyJpaController instance = new OntologyJpaController(emf);
            Set<Ontology> attachObjSet = new HashSet<>();
            for (Ontology object : objectList) {
                Ontology objectOnDB = em.find(Ontology.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(Ontology.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the GeneInfo list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the GeneInfo list the be persisted
     * @return the GeneInfo list the be persisted
     * @throws Exception
     */
    protected Set<GeneInfo> createGeneInfo(EntityManagerFactory emf, EntityManager em, Set<GeneInfo> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            GeneInfoJpaController instance = new GeneInfoJpaController(emf);
            Set<GeneInfo> attachObjSet = new HashSet<>();
            for (GeneInfo object : objectList) {
                GeneInfo objectOnDB = em.find(GeneInfo.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(GeneInfo.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the PfamAbioWH list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the PfamAbioWH list the be persisted
     * @return the Ontology list the be persisted
     * @throws Exception
     */
    protected Set<PfamAbioWH> createPfamA(EntityManagerFactory emf, EntityManager em, Set<PfamAbioWH> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            PfamAbioWHJpaController instance = new PfamAbioWHJpaController(emf);
            Set<PfamAbioWH> attachObjSet = new HashSet<>();
            for (PfamAbioWH object : objectList) {
                PfamAbioWH objectOnDB = em.find(PfamAbioWH.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(PfamAbioWH.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the Protein list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the Protein list the be persisted
     * @return the Protein list the be persisted
     * @throws Exception
     */
    protected Set<Protein> createProtein(EntityManagerFactory emf, EntityManager em, Set<Protein> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            ProteinJpaController instance = new ProteinJpaController(emf);
            Set<Protein> attachObjSet = new HashSet<>();
            for (Protein object : objectList) {
                Protein objectOnDB = em.find(Protein.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(Protein.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the KEGGPathway list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the KEGGPathway list the be persisted
     * @return the KEGGPathway list the be persisted
     * @throws Exception
     */
    protected Set<KEGGPathway> createKEGGPathway(EntityManagerFactory emf, EntityManager em, Set<KEGGPathway> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            KEGGPathwayJpaController instance = new KEGGPathwayJpaController(emf);
            Set<KEGGPathway> attachObjSet = new HashSet<>();
            for (KEGGPathway object : objectList) {
                KEGGPathway objectOnDB = em.find(KEGGPathway.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(KEGGPathway.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the KEGGGene list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the KEGGGene list the be persisted
     * @return the KEGGGene list the be persisted
     * @throws Exception
     */
    protected Set<KEGGGene> createKEGGGene(EntityManagerFactory emf, EntityManager em, Set<KEGGGene> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            KEGGGeneJpaController instance = new KEGGGeneJpaController(emf);
            Set<KEGGGene> attachObjSet = new HashSet<>();
            for (KEGGGene object : objectList) {
                KEGGGene objectOnDB = em.find(KEGGGene.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(KEGGGene.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the KEGGEnzyme list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the KEGGEnzyme list the be persisted
     * @return the KEGGEnzyme list the be persisted
     * @throws Exception
     */
    protected Set<KEGGEnzyme> createKEGGEnzyme(EntityManagerFactory emf, EntityManager em, Set<KEGGEnzyme> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            KEGGEnzymeJpaController instance = new KEGGEnzymeJpaController(emf);
            Set<KEGGEnzyme> attachObjSet = new HashSet<>();
            for (KEGGEnzyme object : objectList) {
                KEGGEnzyme objectOnDB = em.find(KEGGEnzyme.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(KEGGEnzyme.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the KEGGCompound list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the KEGGCompound list the be persisted
     * @return the KEGGEnzyme list the be persisted
     * @throws Exception
     */
    protected Set<KEGGCompound> createKEGGCompound(EntityManagerFactory emf, EntityManager em, Set<KEGGCompound> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            KEGGCompoundJpaController instance = new KEGGCompoundJpaController(emf);
            Set<KEGGCompound> attachObjSet = new HashSet<>();
            for (KEGGCompound object : objectList) {
                KEGGCompound objectOnDB = em.find(KEGGCompound.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(KEGGCompound.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the UniRefEntry list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the UniRefEntry list the be persisted
     * @return the UniRefEntry list the be persisted
     * @throws Exception
     */
    protected Set<UniRefEntry> createUniRefEntry(EntityManagerFactory emf, EntityManager em, Set<UniRefEntry> objectList, Protein protein) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            UniRefEntryJpaController instance = new UniRefEntryJpaController(emf);
            Set<UniRefEntry> attachObjSet = new HashSet<>();
            for (UniRefEntry object : objectList) {
                UniRefEntry objectOnDB = em.find(UniRefEntry.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.getProtein().remove(protein);
                    instance.create(object);
                    attachObjSet.add(em.getReference(UniRefEntry.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the UniRefMember list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the UniRefMember list the be persisted
     * @return the UniRefMember list the be persisted
     * @throws Exception
     */
    protected Set<UniRefMember> createUniRefMember(EntityManagerFactory emf, EntityManager em, Set<UniRefMember> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            UniRefMemberJpaController instance = new UniRefMemberJpaController(emf);
            Set<UniRefMember> attachObjSet = new HashSet<>();
            for (UniRefMember object : objectList) {
                UniRefMember objectOnDB = em.find(UniRefMember.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(UniRefMember.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the MIFEntryInteraction list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the MIFEntryInteraction list the be persisted
     * @return the MIFEntryInteraction list the be persisted
     * @throws Exception
     */
    protected Set<MIFEntryInteraction> createMIFEntryInteraction(EntityManagerFactory emf, EntityManager em, Set<MIFEntryInteraction> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            MIFEntryInteractionJpaController instance = new MIFEntryInteractionJpaController(emf);
            Set<MIFEntryInteraction> attachObjSet = new HashSet<>();
            for (MIFEntryInteraction object : objectList) {
                MIFEntryInteraction objectOnDB = em.find(MIFEntryInteraction.class, object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(MIFEntryInteraction.class, object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * Return the DrugBank list the be persisted
     *
     * @param emf Interface used to interact with the entity manager factory for
     * the persistence unit
     * @param em Interface used to interact with the persistence context
     * @param objectList the DrugBank list the be persisted
     * @return the DrugBank list the be persisted
     * @throws Exception
     */
    protected Set<DrugBank> createDrugBank(EntityManagerFactory emf, EntityManager em, Set<DrugBank> objectList) throws Exception {
        if (objectList != null && !objectList.isEmpty()) {
            DrugBankJpaController instance = new DrugBankJpaController(emf);
            Set<DrugBank> attachObjSet = new HashSet<>();
            for (DrugBank object : objectList) {
                DrugBank objectOnDB = em.find(object.getClass(), object.getWid());
                if (objectOnDB != null) {
                    attachObjSet.add(objectOnDB);
                } else {
                    object.setRelationsToNull();
                    instance.create(object);
                    attachObjSet.add(em.getReference(object.getClass(), object.getWid()));
                }
            }
            return attachObjSet;
        }
        return new HashSet<>();
    }

    /**
     * This method perform the NamedQuery over the selected entity
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @return a Entity List
     */
    public List useNamedQuery(String nameQuery) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery(nameQuery);
        List result = q.getResultList();
        em.close();
        return result;
    }

    /**
     * This method perform the NamedQuery over the selected entity in a defined
     * range
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @param range is the first number is the first result and the second
     * number is the max results
     * @return a Entity List
     */
    public List useNamedQuery(String nameQuery, int[] range) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery(nameQuery);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        List result = q.getResultList();
        em.close();
        return result;
    }

    /**
     * This method perform the NamedQuery over the selected entity with defined
     * parameters
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @param parameters is the map for parameter --> value into the nameQuery
     * @return a Entity List
     */
    public List useNamedQuery(String nameQuery, HashMap<String, Object> parameters) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery(nameQuery);

        for (String key : parameters.keySet()) {
            q.setParameter(key, parameters.get(key));
        }
        List result = q.getResultList();
        em.close();
        return result;
    }

    /**
     * This method perform the NamedQuery over the selected entity with defined
     * parameters in a defined range
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @param parameters is the map for parameter --> value into the nameQuery
     * @param range is the first number is the first result and the second
     * number is the max results
     * @return a Entity List
     */
    public List useNamedQuery(String nameQuery, HashMap<String, Object> parameters, int[] range) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery(nameQuery);

        for (String key : parameters.keySet()) {
            q.setParameter(key, parameters.get(key));
        }
        q.setMaxResults(range[1]);
        q.setFirstResult(range[0]);

        List result = q.getResultList();
        em.close();
        return result;
    }

    /**
     * This method perform the NamedQuery over the selected entity with a single
     * result
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @return a Entity List
     */
    public Object useNamedQuerySingleResult(String nameQuery) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery(nameQuery);
        Object result = q.getSingleResult();
        em.close();
        return result;
    }

    /**
     * This method perform the NamedQuery over the selected entity with a single
     * result with defined parameters
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @param parameters is the map for parameter --> value into the nameQuery
     * @return a Entity List
     */
    public Object useNamedQuerySingleResult(String nameQuery, HashMap<String, Object> parameters) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery(nameQuery);

        for (String key : parameters.keySet()) {
            q.setParameter(key, parameters.get(key));
        }
        Object result = q.getSingleResult();
        em.close();
        return result;
    }

    /**
     * This method perform the NamedQuery over the selected entity using the
     * executeUpdate sentence
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @return a Entity List
     */
    public int useNamedQueryExecuteUpdate(String nameQuery) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery(nameQuery);
        int i = q.executeUpdate();
        em.close();
        return i;
    }

    /**
     * This method perform the NamedQuery over the selected entity with a single
     * result with defined parameters using the executeUpdate sentence
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @param parameters is the map for parameter --> value into the nameQuery
     * @return a Entity List
     */
    public int useNamedQueryExecuteUpdate(String nameQuery, HashMap<String, Object> parameters) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery(nameQuery);

        for (String key : parameters.keySet()) {
            q.setParameter(key, parameters.get(key));
        }
        int i = q.executeUpdate();
        em.close();
        return i;
    }

    /**
     * This method perform the NamedQuery over the selected entity using the
     * executeUpdate sentence
     *
     * @param sqlQuery is the NamedQuery defined into the Entity
     * @return a Entity List
     */
    public int useCreateQueryExecuteUpdate(String sqlQuery) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(sqlQuery);
        int i = q.executeUpdate();
        em.close();
        return i;
    }

    /**
     * This method create and perform the NamedQuery over the selected entity
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @return a Entity List
     */
    public List createNamedQuery(String nameQuery) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(nameQuery);
        List result = q.getResultList();
        em.close();
        return result;
    }

    /**
     * This method create and perform the NamedQuery over the selected entity
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @param range is the first number is the first result and the second
     * number is the max results
     * @return a Entity List
     */
    public List createNamedQuery(String nameQuery, int[] range) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(nameQuery);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        List result = q.getResultList();
        em.close();
        return result;
    }

    /**
     * This method create and perform the NamedQuery over the selected entity
     * with defined parameters
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @param parameters is the map for parameter --> value into the nameQuery
     * @return a Entity List
     */
    public List createNamedQuery(String nameQuery, HashMap<String, Object> parameters) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(nameQuery);

        for (String key : parameters.keySet()) {
            q.setParameter(key, parameters.get(key));
        }
        List result = q.getResultList();
        em.close();
        return result;
    }

    /**
     * This method create and perform the NamedQuery over the selected entity
     * with defined parameters
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @param parameters is the map for parameter --> value into the nameQuery
     * @param range is the first number is the first result and the second
     * number is the max results
     * @return a Entity List
     */
    public List createNamedQuery(String nameQuery, HashMap<String, Object> parameters, int[] range) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(nameQuery);

        for (String key : parameters.keySet()) {
            q.setParameter(key, parameters.get(key));
        }
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        List result = q.getResultList();
        em.close();
        return result;
    }
    
    /**
     * This method create and perform the NamedQuery over the selected entity
     * with defined parameters
     *
     * @param nameQuery is the NamedQuery defined into the Entity
     * @param parameters is the map for parameter --> value into the nameQuery
     * @return a single result object
     */
    public Object createNamedQuerySingleResult(String nameQuery, HashMap<String, Object> parameters) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(nameQuery);

        for (String key : parameters.keySet()) {
            q.setParameter(key, parameters.get(key));
        }
        Object result = q.getSingleResult();
        em.close();
        return result;
    }
}
