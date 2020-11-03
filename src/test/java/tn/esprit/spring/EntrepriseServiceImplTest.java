package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;
import static org.junit.Assert.assertEquals;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
	@Autowired
	IEntrepriseService IEntreprise;
	
	@Test
	public void testAjouterEntreprise() {
	
		Entreprise entr = new Entreprise("Esprit", "Ariana"); 
		int  entrepriseAdded = IEntreprise.ajouterEntreprise(entr);
		
		assertEquals(entr.getId(), entrepriseAdded);
		
	}
	@Test
	public void testAjouterDepartement() {
		Departement dep = new Departement("electronique");
		int  departementAdded = IEntreprise.ajouterDepartement(dep);
		assertEquals(departementAdded,dep.getId());
		
	}
	@Test
	public void getAllDepartementsNamesByEntreprise() {
		
		List<String>  names =  IEntreprise.getAllDepartementsNamesByEntreprise(1);
		
		assertEquals(2,names.size());
		
	}
	/*
	@Test
	public void testDeleteDepartementById() {
		IEntreprise.deleteDepartementById(2);
		
	} */
	@Test
	public void testGetEntrepriseById() {
		Entreprise e = IEntreprise.getEntrepriseById(1);
		assertEquals(1,e.getId());

		
	}
	@Test
	public void testAffecterDepartementAEntreprise() {
		IEntreprise.affecterDepartementAEntreprise(4, 1);
	}
	
	

}
