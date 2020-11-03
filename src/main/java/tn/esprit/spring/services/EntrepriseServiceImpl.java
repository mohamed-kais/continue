package tn.esprit.spring.services;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	//log4j
	//without spring logger fi blast logManager
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		int id = 0 ; 
		try{
			l.info("Entreprise : " + entreprise);
			entrepriseRepoistory.save(entreprise);
			id = entreprise.getId();
			l.info("ajouterEntreprise() : " + id);
		}catch (Exception e) {l.error("Erreur : " + e);}
		
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		int id = 0 ; 
		try {
			l.info("Departement +++ : " + dep);
			deptRepoistory.save(dep);
			l.info("ajouterDepartement() : " + id);
		}catch (Exception e) {l.error("Erreur : " + e);}
		
		
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		// Le bout Master de cette relation N:1 est departement
				// donc il faut rajouter l'entreprise a departement
				// ==> c'est l'objet departement(le master) qui va mettre a jour
				// l'association
				// Rappel : la classe qui contient mappedBy represente le bout Slave
				// Rappel : Dans une relation oneToMany le mappedBy doit etre du cote
				// one.
				l.info("in departement id = " + depId);
				l.info("in entreprise id = " + entrepriseId);
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				l.info("entrepriseManagedEntity : " + entrepriseManagedEntity);
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				l.info("depManagedEntity : " + depManagedEntity);

				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for (Departement dep : entrepriseManagedEntity.getDepartements()) {
			depNames.add(dep.getName());
			l.info("depNames : " + depNames);
		}

		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		
		try {
			l.info("deleteEntrepriseById : " + entrepriseId);
			entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
		}catch (Exception e) {l.error("Erreur : " + e);}
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		try {
			l.info("deleteDepartementById : " + depId);
			deptRepoistory.delete(deptRepoistory.findById(depId).get());
			}catch (Exception e) {l.error("Erreur : " + e);}
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Entreprise entreprise = null;
		try {
			l.info("In getEntrepriseById(" + entrepriseId + ")");
			entreprise = entrepriseRepoistory.findById(entrepriseId).get();
			l.info("Out getEntrepriseById() : " + entreprise);
		} catch (Exception e) {
			l.error("Erreur : " + e);
		}

		return entreprise;
	}

}
