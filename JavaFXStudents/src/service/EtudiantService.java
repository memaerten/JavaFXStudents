package service;

import java.util.List;

import dao.EtudiantDaoFile;
import dao.IEtudiantDao;
import model.Student;

public class EtudiantService implements IEtudiantService {
	
	private IEtudiantDao dao = new EtudiantDaoFile();

	public List<Student> listEtudiant() {
		return dao.listStudents();
	}

	public void ajouterEtudiant(Student e) {
		dao.add(e);
		// ajouter dans Etudiants.txt
	}

	public Student modifierEtudiant(Student e) {
		return dao.update(e);
	}

}
