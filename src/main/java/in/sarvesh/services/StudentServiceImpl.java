package in.sarvesh.services;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.sarvesh.models.Student;

@Repository
public class StudentServiceImpl implements StudentService{
	
	private SessionFactory sessionFactory;
	private Session session;
	
	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		}
		catch(HibernateException e) {
			session = sessionFactory.openSession();
		}
		
	}

	@Transactional
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		
		List<Student> students = session.createQuery("from Student").list();
		tx.commit();
		return students;
	}

	@Transactional
	public Student findById(int id) {
		Student student = new Student();

		
		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		student = session.get(Student.class, id);

		tx.commit();

		return student;
	}

	@Transactional
	public void save(Student theStudent) {
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(theStudent);

		tx.commit();
		
	}

	@Transactional
	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();

		// get transaction
		Student student = session.get(Student.class, id);

		// delete record
		session.delete(student);

		tx.commit();
		
	}

	@Transactional
	public List<Student> searchBy(String name, String department) {
		Transaction tx = session.beginTransaction();
		String query = "";
		if (name.length() != 0 && department.length() != 0)
			query = "from Student where name like '%" + name + "%' or department like '%" + department + "%'";
		else if (name.length() != 0)
			query = "from Student where name like '%" + name + "%'";
		else if (department.length() != 0)
			query = "from Student where department like '%" + department + "%'";
		else
			System.out.println("Cannot search without input data");

		List<Student> student = session.createQuery(query).list();

		tx.commit();

		return student;
	}
	// print the loop
	@Transactional
	public void print(List<Student> student) {

		for (Student b : student) {
			System.out.println(b);
		}
	}
}
