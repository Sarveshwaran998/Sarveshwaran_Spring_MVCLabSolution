package in.sarvesh.services;

import java.util.List;
import in.sarvesh.models.Student;

public interface StudentService {
	public List<Student> findAll();
	Student findById(int id);
	void save(Student student);
	void deleteById(int id);
	List<Student> searchBy(String name, String department);
}
