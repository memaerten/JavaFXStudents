package service;

import java.util.List;

import model.Student;

//import fr.formation.afpa.model.Student;

public interface IEtudiantService {
	
	public List<Student> listEtudiant();
	
	public void ajouterEtudiant(Student e);
	
	public Student modifierEtudiant(Student e);
	
	

}
