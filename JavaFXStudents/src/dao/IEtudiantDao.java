package dao;

import java.util.List;

import model.Student;


public interface IEtudiantDao {
	public void add(Student s);
	public Student update(Student s);
	public List<Student> listStudents();

}
