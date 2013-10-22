package org.jbiowhpersistence.datasets.domain.pfam.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamADatabaseLinks;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamANCBIReg;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamARegFullInsignificant;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamARegFullSignificant;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAbioWH;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAhasPfamLiteratureReferences;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamAhasPfamLiteratureReferencesPK;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamArchitecture;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamClans;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamContextRegions;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamInterpro;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamLiteratureReferences;
import org.jbiowhpersistence.datasets.domain.pfam.entities.PfamNestedLocations;
import org.jbiowhpersistence.datasets.protein.entities.Protein;
import org.jbiowhpersistence.utils.controller.exceptions.NonexistentEntityException;
import org.jbiowhpersistence.utils.controller.exceptions.PreexistingEntityException;

/**
 * This class is the PfamAbioWH Jpa Controller
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100 (Tue, 19 Mar 2013) $ $LastChangedRevision: 396 $
 *
 * @since Nov 26, 2012
 */
public class PfamAbioWHJpaController extends AbstractPFamJpaController<PfamAbioWH> implements Serializable {

    public PfamAbioWHJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PfamAbioWH pfamA) throws PreexistingEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Creating " + this.getClass().getSimpleName() + ": " + pfamA.getWid());
        if (pfamA.getPfamContextRegions() == null) {
            pfamA.setPfamContextRegions(new HashSet<PfamContextRegions>());
        }
        if (pfamA.getPfamInterpros() == null) {
            pfamA.setPfamInterpros(new HashSet<PfamInterpro>());
        }
        if (pfamA.getPfamNestedLocationses() == null) {
            pfamA.setPfamNestedLocationses(new HashSet<PfamNestedLocations>());
        }
        if (pfamA.getPfamANCBIRegs() == null) {
            pfamA.setPfamANCBIRegs(new HashSet<PfamANCBIReg>());
        }
        if (pfamA.getPfamADatabaseLinkses() == null) {
            pfamA.setPfamADatabaseLinkses(new HashSet<PfamADatabaseLinks>());
        }
        if (pfamA.getPfamAhasPfamLiteratureReferences() == null) {
            pfamA.setPfamAhasPfamLiteratureReferences(new HashMap<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            pfamA.setPfamArchitectures(createPfamArchitecture(emf, em, pfamA.getPfamArchitectures()));
            pfamA.setPfamClanses(createPfamClans(emf, em, pfamA.getPfamClanses()));
            pfamA.setPfamProteomeRegions(createPfamProteomeRegions(emf, em, pfamA.getPfamProteomeRegions()));
            pfamA.setProteinInsignificant(createProtein(emf, em, pfamA.getProteinInsignificant()));
            pfamA.setProteinSignificant(createProtein(emf, em, pfamA.getProteinSignificant()));
            pfamA.setPfamARegFullInsignificants(createPfamARegFullInsignificant(emf, em, pfamA.getPfamARegFullInsignificants()));
            pfamA.setPfamARegFullSignificants(createPfamARegFullSignificant(emf, em, pfamA.getPfamARegFullSignificants()));
            pfamA.setDataSet(createDataSet(emf, em, pfamA.getDataSet()));

            if (!pfamA.getPfamContextRegions().isEmpty()) {
                Set<PfamContextRegions> attachedPfamContextRegions = new HashSet<>();
                for (PfamContextRegions pfamContextRegionsPfamContextRegionsToAttach : pfamA.getPfamContextRegions()) {
                    PfamContextRegions pfamContextRegionsPfamContextRegionsToAttachOnDB = em.find(pfamContextRegionsPfamContextRegionsToAttach.getClass(), pfamContextRegionsPfamContextRegionsToAttach.getWid());
                    if (pfamContextRegionsPfamContextRegionsToAttachOnDB != null) {
                        attachedPfamContextRegions.add(pfamContextRegionsPfamContextRegionsToAttachOnDB);
                    } else {
                        attachedPfamContextRegions.add(pfamContextRegionsPfamContextRegionsToAttach);
                    }
                }
                pfamA.setPfamContextRegions(attachedPfamContextRegions);
            }
            if (!pfamA.getPfamInterpros().isEmpty()) {
                Set<PfamInterpro> attachedPfamInterpros = new HashSet<>();
                for (PfamInterpro pfamInterprosPfamInterproToAttach : pfamA.getPfamInterpros()) {
                    PfamInterpro pfamInterprosPfamInterproToAttachOnDB = em.find(pfamInterprosPfamInterproToAttach.getClass(), pfamInterprosPfamInterproToAttach.getPfamAWID());
                    if (pfamInterprosPfamInterproToAttachOnDB != null) {
                        attachedPfamInterpros.add(pfamInterprosPfamInterproToAttachOnDB);
                    } else {
                        attachedPfamInterpros.add(pfamInterprosPfamInterproToAttach);
                    }
                }
                pfamA.setPfamInterpros(attachedPfamInterpros);
            }
            if (!pfamA.getPfamNestedLocationses().isEmpty()) {
                Set<PfamNestedLocations> attachedPfamNestedLocationses = new HashSet<>();
                for (PfamNestedLocations pfamNestedLocationsesPfamNestedLocationsToAttach : pfamA.getPfamNestedLocationses()) {
                    PfamNestedLocations pfamNestedLocationsesPfamNestedLocationsToAttachOnDB = em.find(pfamNestedLocationsesPfamNestedLocationsToAttach.getClass(), pfamNestedLocationsesPfamNestedLocationsToAttach.getWid());
                    if (pfamNestedLocationsesPfamNestedLocationsToAttachOnDB != null) {
                        attachedPfamNestedLocationses.add(pfamNestedLocationsesPfamNestedLocationsToAttachOnDB);
                    } else {
                        attachedPfamNestedLocationses.add(pfamNestedLocationsesPfamNestedLocationsToAttach);
                    }
                }
                pfamA.setPfamNestedLocationses(attachedPfamNestedLocationses);
            }
            if (!pfamA.getPfamANCBIRegs().isEmpty()) {
                Set<PfamANCBIReg> attachedPfamANCBIRegs = new HashSet<>();
                for (PfamANCBIReg pfamANCBIRegsPfamANCBIRegToAttach : pfamA.getPfamANCBIRegs()) {
                    PfamANCBIReg pfamANCBIRegsPfamANCBIRegToAttachOnDB = em.find(pfamANCBIRegsPfamANCBIRegToAttach.getClass(), pfamANCBIRegsPfamANCBIRegToAttach.getWid());
                    if (pfamANCBIRegsPfamANCBIRegToAttachOnDB != null) {
                        attachedPfamANCBIRegs.add(pfamANCBIRegsPfamANCBIRegToAttachOnDB);
                    } else {
                        attachedPfamANCBIRegs.add(pfamANCBIRegsPfamANCBIRegToAttach);
                    }
                }
                pfamA.setPfamANCBIRegs(attachedPfamANCBIRegs);
            }
            if (!pfamA.getPfamADatabaseLinkses().isEmpty()) {
                Set<PfamADatabaseLinks> attachedPfamADatabaseLinkses = new HashSet<>();
                for (PfamADatabaseLinks pfamADatabaseLinksesPfamADatabaseLinksToAttach : pfamA.getPfamADatabaseLinkses()) {
                    PfamADatabaseLinks pfamADatabaseLinksesPfamADatabaseLinksToAttachOnDB = em.find(pfamADatabaseLinksesPfamADatabaseLinksToAttach.getClass(), pfamADatabaseLinksesPfamADatabaseLinksToAttach.getWid());
                    if (pfamADatabaseLinksesPfamADatabaseLinksToAttachOnDB != null) {
                        attachedPfamADatabaseLinkses.add(pfamADatabaseLinksesPfamADatabaseLinksToAttachOnDB);
                    } else {
                        attachedPfamADatabaseLinkses.add(pfamADatabaseLinksesPfamADatabaseLinksToAttach);
                    }
                }
                pfamA.setPfamADatabaseLinkses(attachedPfamADatabaseLinkses);
            }

            if (!pfamA.getPfamAhasPfamLiteratureReferences().isEmpty()) {
                PfamLiteratureReferencesJpaController kController = new PfamLiteratureReferencesJpaController(emf);
                Map<PfamAhasPfamLiteratureReferencesPK, PfamAhasPfamLiteratureReferences> attachPfamLiteratureReferences = new HashMap<>();
                for (PfamAhasPfamLiteratureReferences pfamAhasPfamLiteratureReferencesToAttach : pfamA.getPfamAhasPfamLiteratureReferences().values()) {
                    PfamAhasPfamLiteratureReferences pfamAhasPfamLiteratureReferencesOnDB = em.find(pfamAhasPfamLiteratureReferencesToAttach.getClass(), pfamAhasPfamLiteratureReferencesToAttach.getPfamAhasPfamLiteratureReferencesPK());
                    if (pfamAhasPfamLiteratureReferencesOnDB != null) {
                        attachPfamLiteratureReferences.put(pfamAhasPfamLiteratureReferencesOnDB.getPfamAhasPfamLiteratureReferencesPK(), pfamAhasPfamLiteratureReferencesOnDB);
                    } else {
                        PfamLiteratureReferences pfamLiteratureReferencesOnDB = em.find(PfamLiteratureReferences.class, pfamAhasPfamLiteratureReferencesToAttach.getPfamAhasPfamLiteratureReferencesPK().getPfamLiteratureReferencesWID());
                        if (pfamLiteratureReferencesOnDB != null) {
                            pfamAhasPfamLiteratureReferencesToAttach.setPfamLiteratureReferences(pfamLiteratureReferencesOnDB);
                        } else {
                            pfamLiteratureReferencesOnDB = pfamAhasPfamLiteratureReferencesToAttach.getPfamLiteratureReferences();
                            pfamLiteratureReferencesOnDB.setPfamAhasPfamLiteratureReferences(null);
                            kController.create(pfamLiteratureReferencesOnDB);
                            pfamAhasPfamLiteratureReferencesToAttach.setPfamLiteratureReferences(em.find(PfamLiteratureReferences.class, pfamLiteratureReferencesOnDB.getWid()));
                        }
                        attachPfamLiteratureReferences.put(pfamAhasPfamLiteratureReferencesToAttach.getPfamAhasPfamLiteratureReferencesPK(), pfamAhasPfamLiteratureReferencesToAttach);
                    }
                }
                pfamA.setPfamAhasPfamLiteratureReferences(attachPfamLiteratureReferences);
            }

            em.persist(pfamA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPfamA(pfamA.getWid()) != null) {
                throw new PreexistingEntityException("PfamA " + pfamA + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PfamAbioWH pfamA) throws NonexistentEntityException, Exception {
        VerbLogger.getInstance().log(this.getClass(), "Editing " + this.getClass().getSimpleName() + ": " + pfamA.getWid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PfamAbioWH persistentPfamA = em.find(PfamAbioWH.class, pfamA.getWid());
            Set<Protein> proteinInsignificantOld = persistentPfamA.getProteinInsignificant();
            Set<Protein> proteinInsignificantNew = pfamA.getProteinInsignificant();
            Set<Protein> proteinSignificantOld = persistentPfamA.getProteinSignificant();
            Set<Protein> proteinSignificantNew = pfamA.getProteinSignificant();
            Set<PfamARegFullSignificant> pfamARegFullSignificantsOld = persistentPfamA.getPfamARegFullSignificants();
            Set<PfamARegFullSignificant> pfamARegFullSignificantsNew = pfamA.getPfamARegFullSignificants();
            Set<PfamARegFullInsignificant> pfamARegFullInsignificantsOld = persistentPfamA.getPfamARegFullInsignificants();
            Set<PfamARegFullInsignificant> pfamARegFullInsignificantsNew = pfamA.getPfamARegFullInsignificants();
            Set<PfamArchitecture> pfamArchitecturesOld = persistentPfamA.getPfamArchitectures();
            Set<PfamArchitecture> pfamArchitecturesNew = pfamA.getPfamArchitectures();
            Set<PfamClans> pfamClansesOld = persistentPfamA.getPfamClanses();
            Set<PfamClans> pfamClansesNew = pfamA.getPfamClanses();
            Set<PfamContextRegions> pfamContextRegionsOld = persistentPfamA.getPfamContextRegions();
            Set<PfamContextRegions> pfamContextRegionsNew = pfamA.getPfamContextRegions();
            Set<PfamInterpro> pfamInterprosOld = persistentPfamA.getPfamInterpros();
            Set<PfamInterpro> pfamInterprosNew = pfamA.getPfamInterpros();
            Set<PfamNestedLocations> pfamNestedLocationsesOld = persistentPfamA.getPfamNestedLocationses();
            Set<PfamNestedLocations> pfamNestedLocationsesNew = pfamA.getPfamNestedLocationses();
            Set<PfamANCBIReg> pfamANCBIRegsOld = persistentPfamA.getPfamANCBIRegs();
            Set<PfamANCBIReg> pfamANCBIRegsNew = pfamA.getPfamANCBIRegs();
            Set<PfamADatabaseLinks> pfamADatabaseLinksesOld = persistentPfamA.getPfamADatabaseLinkses();
            Set<PfamADatabaseLinks> pfamADatabaseLinksesNew = pfamA.getPfamADatabaseLinkses();
            Set<Protein> attachedProteinInsignificantNew = new HashSet<>();
            for (Protein proteinInsignificantNewProteinToAttach : proteinInsignificantNew) {
                proteinInsignificantNewProteinToAttach = em.getReference(proteinInsignificantNewProteinToAttach.getClass(), proteinInsignificantNewProteinToAttach.getWid());
                attachedProteinInsignificantNew.add(proteinInsignificantNewProteinToAttach);
            }
            proteinInsignificantNew = attachedProteinInsignificantNew;
            pfamA.setProteinInsignificant(proteinInsignificantNew);
            Set<Protein> attachedProteinSignificantNew = new HashSet<>();
            for (Protein proteinSignificantNewProteinToAttach : proteinSignificantNew) {
                proteinSignificantNewProteinToAttach = em.getReference(proteinSignificantNewProteinToAttach.getClass(), proteinSignificantNewProteinToAttach.getWid());
                attachedProteinSignificantNew.add(proteinSignificantNewProteinToAttach);
            }
            proteinSignificantNew = attachedProteinSignificantNew;
            pfamA.setProteinSignificant(proteinSignificantNew);
            Set<PfamARegFullSignificant> attachedPfamARegFullSignificantsNew = new HashSet<>();
            for (PfamARegFullSignificant pfamARegFullSignificantsNewPfamARegFullSignificantToAttach : pfamARegFullSignificantsNew) {
                pfamARegFullSignificantsNewPfamARegFullSignificantToAttach = em.getReference(pfamARegFullSignificantsNewPfamARegFullSignificantToAttach.getClass(), pfamARegFullSignificantsNewPfamARegFullSignificantToAttach.getWid());
                attachedPfamARegFullSignificantsNew.add(pfamARegFullSignificantsNewPfamARegFullSignificantToAttach);
            }
            pfamARegFullSignificantsNew = attachedPfamARegFullSignificantsNew;
            pfamA.setPfamARegFullSignificants(pfamARegFullSignificantsNew);
            Set<PfamARegFullInsignificant> attachedPfamARegFullInsignificantsNew = new HashSet<>();
            for (PfamARegFullInsignificant pfamARegFullInsignificantsNewPfamARegFullInsignificantToAttach : pfamARegFullInsignificantsNew) {
                pfamARegFullInsignificantsNewPfamARegFullInsignificantToAttach = em.getReference(pfamARegFullInsignificantsNewPfamARegFullInsignificantToAttach.getClass(), pfamARegFullInsignificantsNewPfamARegFullInsignificantToAttach.getWid());
                attachedPfamARegFullInsignificantsNew.add(pfamARegFullInsignificantsNewPfamARegFullInsignificantToAttach);
            }
            pfamARegFullInsignificantsNew = attachedPfamARegFullInsignificantsNew;
            pfamA.setPfamARegFullInsignificants(pfamARegFullInsignificantsNew);
            Set<PfamArchitecture> attachedPfamArchitecturesNew = new HashSet<>();
            for (PfamArchitecture pfamArchitecturesNewPfamArchitectureToAttach : pfamArchitecturesNew) {
                pfamArchitecturesNewPfamArchitectureToAttach = em.getReference(pfamArchitecturesNewPfamArchitectureToAttach.getClass(), pfamArchitecturesNewPfamArchitectureToAttach.getWid());
                attachedPfamArchitecturesNew.add(pfamArchitecturesNewPfamArchitectureToAttach);
            }
            pfamArchitecturesNew = attachedPfamArchitecturesNew;
            pfamA.setPfamArchitectures(pfamArchitecturesNew);
            Set<PfamClans> attachedPfamClansesNew = new HashSet<>();
            for (PfamClans pfamClansesNewPfamClansToAttach : pfamClansesNew) {
                pfamClansesNewPfamClansToAttach = em.getReference(pfamClansesNewPfamClansToAttach.getClass(), pfamClansesNewPfamClansToAttach.getWid());
                attachedPfamClansesNew.add(pfamClansesNewPfamClansToAttach);
            }
            pfamClansesNew = attachedPfamClansesNew;
            pfamA.setPfamClanses(pfamClansesNew);
            Set<PfamContextRegions> attachedPfamContextRegionsNew = new HashSet<>();
            for (PfamContextRegions pfamContextRegionsNewPfamContextRegionsToAttach : pfamContextRegionsNew) {
                pfamContextRegionsNewPfamContextRegionsToAttach = em.getReference(pfamContextRegionsNewPfamContextRegionsToAttach.getClass(), pfamContextRegionsNewPfamContextRegionsToAttach.getWid());
                attachedPfamContextRegionsNew.add(pfamContextRegionsNewPfamContextRegionsToAttach);
            }
            pfamContextRegionsNew = attachedPfamContextRegionsNew;
            pfamA.setPfamContextRegions(pfamContextRegionsNew);
            Set<PfamInterpro> attachedPfamInterprosNew = new HashSet<>();
            for (PfamInterpro pfamInterprosNewPfamInterproToAttach : pfamInterprosNew) {
                pfamInterprosNewPfamInterproToAttach = em.getReference(pfamInterprosNewPfamInterproToAttach.getClass(), pfamInterprosNewPfamInterproToAttach.getPfamAWID());
                attachedPfamInterprosNew.add(pfamInterprosNewPfamInterproToAttach);
            }
            pfamInterprosNew = attachedPfamInterprosNew;
            pfamA.setPfamInterpros(pfamInterprosNew);
            Set<PfamNestedLocations> attachedPfamNestedLocationsesNew = new HashSet<>();
            for (PfamNestedLocations pfamNestedLocationsesNewPfamNestedLocationsToAttach : pfamNestedLocationsesNew) {
                pfamNestedLocationsesNewPfamNestedLocationsToAttach = em.getReference(pfamNestedLocationsesNewPfamNestedLocationsToAttach.getClass(), pfamNestedLocationsesNewPfamNestedLocationsToAttach.getWid());
                attachedPfamNestedLocationsesNew.add(pfamNestedLocationsesNewPfamNestedLocationsToAttach);
            }
            pfamNestedLocationsesNew = attachedPfamNestedLocationsesNew;
            pfamA.setPfamNestedLocationses(pfamNestedLocationsesNew);
            Set<PfamANCBIReg> attachedPfamANCBIRegsNew = new HashSet<>();
            for (PfamANCBIReg pfamANCBIRegsNewPfamANCBIRegToAttach : pfamANCBIRegsNew) {
                pfamANCBIRegsNewPfamANCBIRegToAttach = em.getReference(pfamANCBIRegsNewPfamANCBIRegToAttach.getClass(), pfamANCBIRegsNewPfamANCBIRegToAttach.getWid());
                attachedPfamANCBIRegsNew.add(pfamANCBIRegsNewPfamANCBIRegToAttach);
            }
            pfamANCBIRegsNew = attachedPfamANCBIRegsNew;
            pfamA.setPfamANCBIRegs(pfamANCBIRegsNew);
            Set<PfamADatabaseLinks> attachedPfamADatabaseLinksesNew = new HashSet<>();
            for (PfamADatabaseLinks pfamADatabaseLinksesNewPfamADatabaseLinksToAttach : pfamADatabaseLinksesNew) {
                pfamADatabaseLinksesNewPfamADatabaseLinksToAttach = em.getReference(pfamADatabaseLinksesNewPfamADatabaseLinksToAttach.getClass(), pfamADatabaseLinksesNewPfamADatabaseLinksToAttach.getWid());
                attachedPfamADatabaseLinksesNew.add(pfamADatabaseLinksesNewPfamADatabaseLinksToAttach);
            }
            pfamADatabaseLinksesNew = attachedPfamADatabaseLinksesNew;
            pfamA.setPfamADatabaseLinkses(pfamADatabaseLinksesNew);
            pfamA = em.merge(pfamA);
            for (Protein proteinInsignificantOldProtein : proteinInsignificantOld) {
                if (!proteinInsignificantNew.contains(proteinInsignificantOldProtein)) {
                    proteinInsignificantOldProtein.getPfamAInsignificant().remove(pfamA);
                    proteinInsignificantOldProtein = em.merge(proteinInsignificantOldProtein);
                }
            }
            for (Protein proteinInsignificantNewProtein : proteinInsignificantNew) {
                if (!proteinInsignificantOld.contains(proteinInsignificantNewProtein)) {
                    proteinInsignificantNewProtein.getPfamAInsignificant().add(pfamA);
                    proteinInsignificantNewProtein = em.merge(proteinInsignificantNewProtein);
                }
            }
            for (Protein proteinSignificantOldProtein : proteinSignificantOld) {
                if (!proteinSignificantNew.contains(proteinSignificantOldProtein)) {
                    proteinSignificantOldProtein.getPfamAInsignificant().remove(pfamA);
                    proteinSignificantOldProtein = em.merge(proteinSignificantOldProtein);
                }
            }
            for (Protein proteinSignificantNewProtein : proteinSignificantNew) {
                if (!proteinSignificantOld.contains(proteinSignificantNewProtein)) {
                    proteinSignificantNewProtein.getPfamAInsignificant().add(pfamA);
                    proteinSignificantNewProtein = em.merge(proteinSignificantNewProtein);
                }
            }
            for (PfamARegFullSignificant pfamARegFullSignificantsOldPfamARegFullSignificant : pfamARegFullSignificantsOld) {
                if (!pfamARegFullSignificantsNew.contains(pfamARegFullSignificantsOldPfamARegFullSignificant)) {
                    pfamARegFullSignificantsOldPfamARegFullSignificant.setPfamA(null);
                    pfamARegFullSignificantsOldPfamARegFullSignificant = em.merge(pfamARegFullSignificantsOldPfamARegFullSignificant);
                }
            }
            for (PfamARegFullSignificant pfamARegFullSignificantsNewPfamARegFullSignificant : pfamARegFullSignificantsNew) {
                if (!pfamARegFullSignificantsOld.contains(pfamARegFullSignificantsNewPfamARegFullSignificant)) {
                    PfamAbioWH oldPfamAOfPfamARegFullSignificantsNewPfamARegFullSignificant = pfamARegFullSignificantsNewPfamARegFullSignificant.getPfamA();
                    pfamARegFullSignificantsNewPfamARegFullSignificant.setPfamA(pfamA);
                    pfamARegFullSignificantsNewPfamARegFullSignificant = em.merge(pfamARegFullSignificantsNewPfamARegFullSignificant);
                    if (oldPfamAOfPfamARegFullSignificantsNewPfamARegFullSignificant != null && !oldPfamAOfPfamARegFullSignificantsNewPfamARegFullSignificant.equals(pfamA)) {
                        oldPfamAOfPfamARegFullSignificantsNewPfamARegFullSignificant.getPfamARegFullSignificants().remove(pfamARegFullSignificantsNewPfamARegFullSignificant);
                        oldPfamAOfPfamARegFullSignificantsNewPfamARegFullSignificant = em.merge(oldPfamAOfPfamARegFullSignificantsNewPfamARegFullSignificant);
                    }
                }
            }
            for (PfamARegFullInsignificant pfamARegFullInsignificantsOldPfamARegFullInsignificant : pfamARegFullInsignificantsOld) {
                if (!pfamARegFullInsignificantsNew.contains(pfamARegFullInsignificantsOldPfamARegFullInsignificant)) {
                    pfamARegFullInsignificantsOldPfamARegFullInsignificant.setPfamA(null);
                    pfamARegFullInsignificantsOldPfamARegFullInsignificant = em.merge(pfamARegFullInsignificantsOldPfamARegFullInsignificant);
                }
            }
            for (PfamARegFullInsignificant pfamARegFullInsignificantsNewPfamARegFullInsignificant : pfamARegFullInsignificantsNew) {
                if (!pfamARegFullInsignificantsOld.contains(pfamARegFullInsignificantsNewPfamARegFullInsignificant)) {
                    PfamAbioWH oldPfamAOfPfamARegFullInsignificantsNewPfamARegFullInsignificant = pfamARegFullInsignificantsNewPfamARegFullInsignificant.getPfamA();
                    pfamARegFullInsignificantsNewPfamARegFullInsignificant.setPfamA(pfamA);
                    pfamARegFullInsignificantsNewPfamARegFullInsignificant = em.merge(pfamARegFullInsignificantsNewPfamARegFullInsignificant);
                    if (oldPfamAOfPfamARegFullInsignificantsNewPfamARegFullInsignificant != null && !oldPfamAOfPfamARegFullInsignificantsNewPfamARegFullInsignificant.equals(pfamA)) {
                        oldPfamAOfPfamARegFullInsignificantsNewPfamARegFullInsignificant.getPfamARegFullInsignificants().remove(pfamARegFullInsignificantsNewPfamARegFullInsignificant);
                        oldPfamAOfPfamARegFullInsignificantsNewPfamARegFullInsignificant = em.merge(oldPfamAOfPfamARegFullInsignificantsNewPfamARegFullInsignificant);
                    }
                }
            }
            for (PfamArchitecture pfamArchitecturesOldPfamArchitecture : pfamArchitecturesOld) {
                if (!pfamArchitecturesNew.contains(pfamArchitecturesOldPfamArchitecture)) {
                    pfamArchitecturesOldPfamArchitecture.getPfamA().remove(pfamA);
                    pfamArchitecturesOldPfamArchitecture = em.merge(pfamArchitecturesOldPfamArchitecture);
                }
            }
            for (PfamArchitecture pfamArchitecturesNewPfamArchitecture : pfamArchitecturesNew) {
                if (!pfamArchitecturesOld.contains(pfamArchitecturesNewPfamArchitecture)) {
                    pfamArchitecturesNewPfamArchitecture.getPfamA().add(pfamA);
                    pfamArchitecturesNewPfamArchitecture = em.merge(pfamArchitecturesNewPfamArchitecture);
                }
            }
            for (PfamClans pfamClansesOldPfamClans : pfamClansesOld) {
                if (!pfamClansesNew.contains(pfamClansesOldPfamClans)) {
                    pfamClansesOldPfamClans.getPfamA().remove(pfamA);
                    pfamClansesOldPfamClans = em.merge(pfamClansesOldPfamClans);
                }
            }
            for (PfamClans pfamClansesNewPfamClans : pfamClansesNew) {
                if (!pfamClansesOld.contains(pfamClansesNewPfamClans)) {
                    pfamClansesNewPfamClans.getPfamA().add(pfamA);
                    pfamClansesNewPfamClans = em.merge(pfamClansesNewPfamClans);
                }
            }
            for (PfamContextRegions pfamContextRegionsOldPfamContextRegions : pfamContextRegionsOld) {
                if (!pfamContextRegionsNew.contains(pfamContextRegionsOldPfamContextRegions)) {
                    pfamContextRegionsOldPfamContextRegions.setPfamA(null);
                    pfamContextRegionsOldPfamContextRegions = em.merge(pfamContextRegionsOldPfamContextRegions);
                }
            }
            for (PfamContextRegions pfamContextRegionsNewPfamContextRegions : pfamContextRegionsNew) {
                if (!pfamContextRegionsOld.contains(pfamContextRegionsNewPfamContextRegions)) {
                    PfamAbioWH oldPfamAOfPfamContextRegionsNewPfamContextRegions = pfamContextRegionsNewPfamContextRegions.getPfamA();
                    pfamContextRegionsNewPfamContextRegions.setPfamA(pfamA);
                    pfamContextRegionsNewPfamContextRegions = em.merge(pfamContextRegionsNewPfamContextRegions);
                    if (oldPfamAOfPfamContextRegionsNewPfamContextRegions != null && !oldPfamAOfPfamContextRegionsNewPfamContextRegions.equals(pfamA)) {
                        oldPfamAOfPfamContextRegionsNewPfamContextRegions.getPfamContextRegions().remove(pfamContextRegionsNewPfamContextRegions);
                        oldPfamAOfPfamContextRegionsNewPfamContextRegions = em.merge(oldPfamAOfPfamContextRegionsNewPfamContextRegions);
                    }
                }
            }
            for (PfamInterpro pfamInterprosOldPfamInterpro : pfamInterprosOld) {
                if (!pfamInterprosNew.contains(pfamInterprosOldPfamInterpro)) {
                    pfamInterprosOldPfamInterpro.setPfamA(null);
                    pfamInterprosOldPfamInterpro = em.merge(pfamInterprosOldPfamInterpro);
                }
            }
            for (PfamInterpro pfamInterprosNewPfamInterpro : pfamInterprosNew) {
                if (!pfamInterprosOld.contains(pfamInterprosNewPfamInterpro)) {
                    PfamAbioWH oldPfamAOfPfamInterprosNewPfamInterpro = pfamInterprosNewPfamInterpro.getPfamA();
                    pfamInterprosNewPfamInterpro.setPfamA(pfamA);
                    pfamInterprosNewPfamInterpro = em.merge(pfamInterprosNewPfamInterpro);
                    if (oldPfamAOfPfamInterprosNewPfamInterpro != null && !oldPfamAOfPfamInterprosNewPfamInterpro.equals(pfamA)) {
                        oldPfamAOfPfamInterprosNewPfamInterpro.getPfamInterpros().remove(pfamInterprosNewPfamInterpro);
                        oldPfamAOfPfamInterprosNewPfamInterpro = em.merge(oldPfamAOfPfamInterprosNewPfamInterpro);
                    }
                }
            }
            for (PfamNestedLocations pfamNestedLocationsesOldPfamNestedLocations : pfamNestedLocationsesOld) {
                if (!pfamNestedLocationsesNew.contains(pfamNestedLocationsesOldPfamNestedLocations)) {
                    pfamNestedLocationsesOldPfamNestedLocations.setPfamA(null);
                    pfamNestedLocationsesOldPfamNestedLocations = em.merge(pfamNestedLocationsesOldPfamNestedLocations);
                }
            }
            for (PfamNestedLocations pfamNestedLocationsesNewPfamNestedLocations : pfamNestedLocationsesNew) {
                if (!pfamNestedLocationsesOld.contains(pfamNestedLocationsesNewPfamNestedLocations)) {
                    PfamAbioWH oldPfamAOfPfamNestedLocationsesNewPfamNestedLocations = pfamNestedLocationsesNewPfamNestedLocations.getPfamA();
                    pfamNestedLocationsesNewPfamNestedLocations.setPfamA(pfamA);
                    pfamNestedLocationsesNewPfamNestedLocations = em.merge(pfamNestedLocationsesNewPfamNestedLocations);
                    if (oldPfamAOfPfamNestedLocationsesNewPfamNestedLocations != null && !oldPfamAOfPfamNestedLocationsesNewPfamNestedLocations.equals(pfamA)) {
                        oldPfamAOfPfamNestedLocationsesNewPfamNestedLocations.getPfamNestedLocationses().remove(pfamNestedLocationsesNewPfamNestedLocations);
                        oldPfamAOfPfamNestedLocationsesNewPfamNestedLocations = em.merge(oldPfamAOfPfamNestedLocationsesNewPfamNestedLocations);
                    }
                }
            }
            for (PfamANCBIReg pfamANCBIRegsOldPfamANCBIReg : pfamANCBIRegsOld) {
                if (!pfamANCBIRegsNew.contains(pfamANCBIRegsOldPfamANCBIReg)) {
                    pfamANCBIRegsOldPfamANCBIReg.setPfamA(null);
                    pfamANCBIRegsOldPfamANCBIReg = em.merge(pfamANCBIRegsOldPfamANCBIReg);
                }
            }
            for (PfamANCBIReg pfamANCBIRegsNewPfamANCBIReg : pfamANCBIRegsNew) {
                if (!pfamANCBIRegsOld.contains(pfamANCBIRegsNewPfamANCBIReg)) {
                    PfamAbioWH oldPfamAOfPfamANCBIRegsNewPfamANCBIReg = pfamANCBIRegsNewPfamANCBIReg.getPfamA();
                    pfamANCBIRegsNewPfamANCBIReg.setPfamA(pfamA);
                    pfamANCBIRegsNewPfamANCBIReg = em.merge(pfamANCBIRegsNewPfamANCBIReg);
                    if (oldPfamAOfPfamANCBIRegsNewPfamANCBIReg != null && !oldPfamAOfPfamANCBIRegsNewPfamANCBIReg.equals(pfamA)) {
                        oldPfamAOfPfamANCBIRegsNewPfamANCBIReg.getPfamANCBIRegs().remove(pfamANCBIRegsNewPfamANCBIReg);
                        oldPfamAOfPfamANCBIRegsNewPfamANCBIReg = em.merge(oldPfamAOfPfamANCBIRegsNewPfamANCBIReg);
                    }
                }
            }
            for (PfamADatabaseLinks pfamADatabaseLinksesOldPfamADatabaseLinks : pfamADatabaseLinksesOld) {
                if (!pfamADatabaseLinksesNew.contains(pfamADatabaseLinksesOldPfamADatabaseLinks)) {
                    pfamADatabaseLinksesOldPfamADatabaseLinks.setPfamA(null);
                    pfamADatabaseLinksesOldPfamADatabaseLinks = em.merge(pfamADatabaseLinksesOldPfamADatabaseLinks);
                }
            }
            for (PfamADatabaseLinks pfamADatabaseLinksesNewPfamADatabaseLinks : pfamADatabaseLinksesNew) {
                if (!pfamADatabaseLinksesOld.contains(pfamADatabaseLinksesNewPfamADatabaseLinks)) {
                    PfamAbioWH oldPfamAOfPfamADatabaseLinksesNewPfamADatabaseLinks = pfamADatabaseLinksesNewPfamADatabaseLinks.getPfamA();
                    pfamADatabaseLinksesNewPfamADatabaseLinks.setPfamA(pfamA);
                    pfamADatabaseLinksesNewPfamADatabaseLinks = em.merge(pfamADatabaseLinksesNewPfamADatabaseLinks);
                    if (oldPfamAOfPfamADatabaseLinksesNewPfamADatabaseLinks != null && !oldPfamAOfPfamADatabaseLinksesNewPfamADatabaseLinks.equals(pfamA)) {
                        oldPfamAOfPfamADatabaseLinksesNewPfamADatabaseLinks.getPfamADatabaseLinkses().remove(pfamADatabaseLinksesNewPfamADatabaseLinks);
                        oldPfamAOfPfamADatabaseLinksesNewPfamADatabaseLinks = em.merge(oldPfamAOfPfamADatabaseLinksesNewPfamADatabaseLinks);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pfamA.getWid();
                if (findPfamA(id) == null) {
                    throw new NonexistentEntityException("The pfamA with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PfamAbioWH pfamA;
            try {
                pfamA = em.getReference(PfamAbioWH.class, id);
                pfamA.getWid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pfamA with id " + id + " no longer exists.", enfe);
            }
            Set<Protein> proteinInsignificant = pfamA.getProteinInsignificant();
            for (Protein proteinInsignificantProtein : proteinInsignificant) {
                proteinInsignificantProtein.getPfamAInsignificant().remove(pfamA);
                proteinInsignificantProtein = em.merge(proteinInsignificantProtein);
            }
            Set<Protein> proteinSignificant = pfamA.getProteinSignificant();
            for (Protein proteinSignificantProtein : proteinSignificant) {
                proteinSignificantProtein.getPfamAInsignificant().remove(pfamA);
                proteinSignificantProtein = em.merge(proteinSignificantProtein);
            }
            Set<PfamARegFullSignificant> pfamARegFullSignificants = pfamA.getPfamARegFullSignificants();
            for (PfamARegFullSignificant pfamARegFullSignificantsPfamARegFullSignificant : pfamARegFullSignificants) {
                pfamARegFullSignificantsPfamARegFullSignificant.setPfamA(null);
                pfamARegFullSignificantsPfamARegFullSignificant = em.merge(pfamARegFullSignificantsPfamARegFullSignificant);
            }
            Set<PfamARegFullInsignificant> pfamARegFullInsignificants = pfamA.getPfamARegFullInsignificants();
            for (PfamARegFullInsignificant pfamARegFullInsignificantsPfamARegFullInsignificant : pfamARegFullInsignificants) {
                pfamARegFullInsignificantsPfamARegFullInsignificant.setPfamA(null);
                pfamARegFullInsignificantsPfamARegFullInsignificant = em.merge(pfamARegFullInsignificantsPfamARegFullInsignificant);
            }
            Set<PfamArchitecture> pfamArchitectures = pfamA.getPfamArchitectures();
            for (PfamArchitecture pfamArchitecturesPfamArchitecture : pfamArchitectures) {
                pfamArchitecturesPfamArchitecture.getPfamA().remove(pfamA);
                pfamArchitecturesPfamArchitecture = em.merge(pfamArchitecturesPfamArchitecture);
            }
            Set<PfamClans> pfamClanses = pfamA.getPfamClanses();
            for (PfamClans pfamClansesPfamClans : pfamClanses) {
                pfamClansesPfamClans.getPfamA().remove(pfamA);
                pfamClansesPfamClans = em.merge(pfamClansesPfamClans);
            }
            Set<PfamContextRegions> pfamContextRegions = pfamA.getPfamContextRegions();
            for (PfamContextRegions pfamContextRegionsPfamContextRegions : pfamContextRegions) {
                pfamContextRegionsPfamContextRegions.setPfamA(null);
                pfamContextRegionsPfamContextRegions = em.merge(pfamContextRegionsPfamContextRegions);
            }
            Set<PfamInterpro> pfamInterpros = pfamA.getPfamInterpros();
            for (PfamInterpro pfamInterprosPfamInterpro : pfamInterpros) {
                pfamInterprosPfamInterpro.setPfamA(null);
                pfamInterprosPfamInterpro = em.merge(pfamInterprosPfamInterpro);
            }
            Set<PfamNestedLocations> pfamNestedLocationses = pfamA.getPfamNestedLocationses();
            for (PfamNestedLocations pfamNestedLocationsesPfamNestedLocations : pfamNestedLocationses) {
                pfamNestedLocationsesPfamNestedLocations.setPfamA(null);
                pfamNestedLocationsesPfamNestedLocations = em.merge(pfamNestedLocationsesPfamNestedLocations);
            }
            Set<PfamANCBIReg> pfamANCBIRegs = pfamA.getPfamANCBIRegs();
            for (PfamANCBIReg pfamANCBIRegsPfamANCBIReg : pfamANCBIRegs) {
                pfamANCBIRegsPfamANCBIReg.setPfamA(null);
                pfamANCBIRegsPfamANCBIReg = em.merge(pfamANCBIRegsPfamANCBIReg);
            }
            Set<PfamADatabaseLinks> pfamADatabaseLinkses = pfamA.getPfamADatabaseLinkses();
            for (PfamADatabaseLinks pfamADatabaseLinksesPfamADatabaseLinks : pfamADatabaseLinkses) {
                pfamADatabaseLinksesPfamADatabaseLinks.setPfamA(null);
                pfamADatabaseLinksesPfamADatabaseLinks = em.merge(pfamADatabaseLinksesPfamADatabaseLinks);
            }
            em.remove(pfamA);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PfamAbioWH> findPfamAEntities() {
        return findPfamAEntities(true, -1, -1);
    }

    public List<PfamAbioWH> findPfamAEntities(int maxResults, int firstResult) {
        return findPfamAEntities(false, maxResults, firstResult);
    }

    private List<PfamAbioWH> findPfamAEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PfamAbioWH.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PfamAbioWH findPfamA(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PfamAbioWH.class, id);
        } finally {
            em.close();
        }
    }

    public int getPfamACount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PfamAbioWH> rt = cq.from(PfamAbioWH.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
